package com.library.batch.configuration;

import com.library.batch.dto.global.MessageDTO;
import com.library.batch.tasklet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@Configuration
@EnableBatchProcessing
public class BatchConfig {

   @Value("${library.batch.request.endpoint}")
   private String requestEndPoint;
   @Value("${library.batch.retry.delay}")
   private Integer retryDelay;
   @Value("${library.mail.noReply}")
   private String noReplyEmail;
   @Value("${library.mail.subject}")
   private String libraryMailLimitSubject;
   @Value("${library.mail.content}")
   private String libraryMailLimitContent;
   @Value("${spring.kafka.topic}")
   private String topic;

   private JobExecutionListener jobListener = new JobListener();

   private final JobBuilderFactory jobs;
   private final StepBuilderFactory steps;
   private final KafkaTemplate<String, MessageDTO> kafkaTemplate;

   public BatchConfig(JobBuilderFactory jobs, StepBuilderFactory steps, KafkaTemplate<String, MessageDTO> kafkaTemplate) {
      this.jobs = jobs;
      this.steps = steps;
      this.kafkaTemplate = kafkaTemplate;
   }

   @Bean
   protected Step readDatas(){
      return steps
            .get("readDatas")
            .tasklet(new DataReader(requestEndPoint, retryDelay))
            .build();
   }

   @Bean
   protected Step processDatas() {
      return steps
            .get("processDatas")
            .tasklet(new DataProcessor(noReplyEmail, libraryMailLimitSubject, libraryMailLimitContent))
            .build();
   }

   @Bean
   protected Step writeDatas() {
      return steps
            .get("writeDatas")
            .tasklet(new DataWriter(topic, kafkaTemplate))
            .build();
   }

   @Bean
   public Job job() {
      return jobs
      .get("taskletsJob")
      .start(readDatas())
      .next(processDatas())
      .next(writeDatas())
      .listener( jobListener)
      .build();
   }


   public class JobListener implements JobExecutionListener {

      @Override
      public void beforeJob(JobExecution jobExecution) {
         // Nothing to do
      }

      @Override
      public void afterJob(JobExecution jobExecution){
         if( jobExecution.getStatus() == BatchStatus.COMPLETED ){
            log.info("JOB SUCCEED");
         }
         else if(jobExecution.getStatus() == BatchStatus.FAILED){
            //job failure
            log.info("JOB FAILED");
         }
      }
   }

}
