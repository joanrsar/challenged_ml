package co.com.mercadolibre.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@PropertySource("classpath:application.properties")
public class PropertiesConfiguration {

	private String itemUrl;
	private String childrenUrl;
	private String kafkaHost;
	private String topic;
	
	
	public String getItemUrl() {
		return itemUrl;
	}
	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}
	public String getChildrenUrl() {
		return childrenUrl;
	}
	public void setChildrenUrl(String childrenUrl) {
		this.childrenUrl = childrenUrl;
	}
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
