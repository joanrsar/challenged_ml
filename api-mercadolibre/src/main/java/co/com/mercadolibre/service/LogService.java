package co.com.mercadolibre.service;

import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import co.com.mercadolibre.configuration.PropertiesConfiguration;
import co.com.mercadolibre.model.vo.LogVO;

@Service
public class LogService {

private static final Logger logger = LoggerFactory.getLogger(LogService.class);
	
	
	@Autowired
	private  PropertiesConfiguration myProps;
	
	
	@Async
	public  void logManager(int codigoRespuesta,long tiempoTotalEjecucion,
							 boolean callApi, long tiempollamadaApi
							 ) {
		Properties props = new Properties();
		LogVO logVO = new LogVO();
		Gson gson = new Gson();
		Producer<String, String> producer = null;
		try{
			
			logVO.setCallApi(callApi);
			logVO.setCodigoRespuesta(codigoRespuesta);
			logVO.setTiempollamadaApi(tiempollamadaApi);
			logVO.setTiempoTotalEjecucion(tiempoTotalEjecucion);
			logVO.setFechalog(new Date());
			
			String representacionJSON = gson.toJson(logVO);
			String kafkaHost = myProps.getKafkaHost();
	        props.put("bootstrap.servers",kafkaHost);
	        props.put("acks", "all");
	        props.put("retries", 5);
	        props.put("batch.size", 16384);
	        props.put("linger.ms", 1);
	        props.put("buffer.memory", 33554432);
	        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	        props.put( "client.id", "api-test");

	        String topic = myProps.getTopic();
	        logger.info("Kafka "+myProps.getKafkaHost()+" Topic "+topic);
	        producer = new KafkaProducer<>( props );
	        
	        ProducerRecord<String, String> message = new ProducerRecord<>( topic, representacionJSON );
	        producer.send( message );
	        
	        logger.info("Mensaje enviado "+message);
	        
		}
		catch(Exception e) {
			logger.error("Error en logManager "+e.getMessage(),e);
		}finally {
			producer.close();
		}
	}
	
}
