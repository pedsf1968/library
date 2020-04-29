# Batch

[![pipeline status](https://gitlab.com/library3/batch/batch-api/badges/master/pipeline.svg)](https://gitlab.com/library3/batch/batch-api/-/commits/master)

## Object

Proceeds to process late reminders for a library subscription.
The batch is divided in three processes.

- Reading : the batch queries the Library API for information on Users with delays and the media used.
- Processing : the batch build the messages with the recovered data.
- Writing : the Batch send the messages to a Kafka server.

## Properties
### sheduler expression
It's run the job once every days.
- library.batch.cron.expression=0 0 1 * * *

### Request path to get borrowing delayed list
Endpoint to query the borrowing delayed.
- library.batch.request.endpoint=http://localhost:7000/borrowings/delayed/

### Retry delay
Waitind delay in second if the connection is refused
- library.batch.retry.delay=60

### Message content and subject
Mail subject, content using String.format method.
- library.mail.subject=Borrowing limit exceeded
- library.mail.content=Media return date has passed for the media : %s \n with the title : %s \n identified by : %s \n that you borrow the %s \n thank you for reporting it as soon as possible.

the %s is standing for :
* first %s : the media type
* second %s : the title
* third %s : the ID of the media
* four %s : the borrowing date

### Message sender
library.mail.noReply=no-reply@lagrandelibrairie.com

## Kafka
Name of the Spring Kafka topic
- spring.kafka.topic : name of the Spring Kafka topic
