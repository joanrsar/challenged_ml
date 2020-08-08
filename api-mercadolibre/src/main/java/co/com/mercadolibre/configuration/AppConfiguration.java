package co.com.mercadolibre.configuration;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class AppConfiguration {

	  public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) 
	    {
	        return restTemplateBuilder.
	        		setConnectTimeout(Duration.ofMillis(5000)).build();
	        		
	           
	    }
}
