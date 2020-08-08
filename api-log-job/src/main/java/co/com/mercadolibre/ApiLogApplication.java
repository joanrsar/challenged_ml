package co.com.mercadolibre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import co.com.mercadolibre.job.ConsumerJob;

@SpringBootApplication
public class ApiLogApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiLogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	    int numConsumers = 3;
	    String groupId = "api-test";
	    List<String> topics = Arrays.asList( "log_topic" );

	    ExecutorService executor = Executors.newFixedThreadPool( numConsumers );
	    final List<ConsumerJob> consumers = new ArrayList<>();

	    
	    ConsumerJob consumer = new ConsumerJob( 1, groupId, topics );
	    consumers.add( consumer );
	    executor.submit( consumer );
	    
	    
	    Runtime.getRuntime().addShutdownHook( new Thread(){
	        @Override
	        public void run(){
	            for( ConsumerJob consumer : consumers ){
	                consumer.shutdown();
	            }
	            executor.shutdown();
	            try{
	                executor.awaitTermination( 5000, TimeUnit.MILLISECONDS );
	            }catch( InterruptedException e ){
	                e.printStackTrace();
	            }
	        }
	    } );
		
	}
	
	

}
