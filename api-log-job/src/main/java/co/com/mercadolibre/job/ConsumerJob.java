package co.com.mercadolibre.job;

import java.util.List;
import java.util.Properties;
import java.util.stream.StreamSupport;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Service;

import co.com.mercadolibre.exceptions.ServiceException;
import co.com.mercadolibre.service.LogService;





public class ConsumerJob implements Runnable {

    private final KafkaConsumer<String, String> consumer;
    private final List<String> topics;
    private final int id;

	
	
	  public ConsumerJob( int id, String groupId, List<String> topics ){
        this.id = id;
        this.topics = topics;
        Properties props = new Properties();
        props.put( "bootstrap.servers", "localhost:9092" );
        props.put( "group.id", groupId );
        props.put( "auto.offset.reset", "earliest" );
        props.put( "key.deserializer", StringDeserializer.class.getName() );
        props.put( "value.deserializer", StringDeserializer.class.getName() );
        this.consumer = new KafkaConsumer<>( props );
    }
	public void run() {
		try {
			consumer.subscribe(topics);

			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
				StreamSupport.stream(records.
									spliterator(), false)
									.forEach(x -> {
										LogService service = new LogService();
										try {
											service.registrarLog(x.value());
										} catch (ServiceException e) {
											
											e.printStackTrace();
										}
										
									});
			}
		} catch (WakeupException e) {
			// ignore for shutdown
		} finally {
			consumer.close();
		}
	}
	
	 public void shutdown(){
	        consumer.wakeup();
	   }

}
