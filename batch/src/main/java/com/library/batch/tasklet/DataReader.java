package com.library.batch.tasklet;

import com.library.batch.dto.business.BorrowingDTO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
public class DataReader implements Tasklet, StepExecutionListener {
   private static final String DATE_FORMAT = "ddMMyyyy";
   private final String requestEndPoint;
   private final Integer retryDelay;
   private List<BorrowingDTO> borrowingDTOS;

   public DataReader(String requestEndPoint, Integer retryDelay) {
      this.requestEndPoint = requestEndPoint;
      this.retryDelay = retryDelay;
   }

   @SneakyThrows
   @Override
   public void beforeStep(StepExecution stepExecution) {
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<List<BorrowingDTO>> responseEntity = null;

      // calculate the date daysOfDelay before now
      SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
      String url = requestEndPoint + format.format(new Date());
      log.info("GET Request : " + url);

      try {
         responseEntity = restTemplate.exchange(
               url,
               HttpMethod.GET,
               null,
               new ParameterizedTypeReference<List<BorrowingDTO>>() {
               });
         this.borrowingDTOS = responseEntity.getBody();
      } catch (RestClientException ex) {
         log.error( ex.getMessage());
         log.info("Retry delay : " + retryDelay + " seconds" );
         if(retryDelay>0) {
            Thread.sleep(retryDelay * 1000L);
            beforeStep(stepExecution);
         }
      }

      log.info("Data Reader initialized.");
   }

   @Override
   public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {



         return RepeatStatus.FINISHED;
   }

   @Override
   public ExitStatus afterStep(StepExecution stepExecution) {
      stepExecution.getJobExecution().getExecutionContext().put("borrowings", this.borrowingDTOS);
      if(borrowingDTOS!=null) {
         for (BorrowingDTO b : borrowingDTOS) {
            log.info(b.toString());
         }
      }
      log.info("Datas Reader ended.");
      return ExitStatus.COMPLETED;
   }

}
