package co.com.mercadolibre.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@PropertySource("classpath:application.properties")
public class PropertiesConfiguration {


	private String kafkaHost;
	private String topic;
	
	
	public String getKafkaHost() {
		return kafkaHost;
	}
	public void setKafkaHost(String kafkaHost) {
		this.kafkaHost = kafkaHost;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	
	
	

	
	
	
}
