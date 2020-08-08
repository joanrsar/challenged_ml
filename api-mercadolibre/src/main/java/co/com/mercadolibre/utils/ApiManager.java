package co.com.mercadolibre.utils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

import co.com.mercadolibre.configuration.PropertiesConfiguration;
import co.com.mercadolibre.exceptions.NotDataFoundApiException;
import co.com.mercadolibre.model.vo.ChildrenVO;
import co.com.mercadolibre.model.vo.ItemVO;

public class ApiManager {

	RestTemplate restTemplate = new RestTemplate();
	private static final Logger logger = LoggerFactory.getLogger(ApiManager.class);

	@Async
	public CompletableFuture<ItemVO> callApiItem(String url) throws NotDataFoundApiException {
		try {
			logger.info("Consumiendo el siguiente servicio: " + url);
			ItemVO response = restTemplate.getForObject(url, ItemVO.class);
			response.toString();
			logger.info(response.toString());
			return CompletableFuture.completedFuture(response);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new NotDataFoundApiException(e,e.getMessage());
		}

	}

	@Async
	public CompletableFuture<List<ChildrenVO>> callApiChild(String url) throws NotDataFoundApiException {
		try {
			logger.info("Consumiendo el siguiente servicio: " + url);
			ChildrenVO[] response = restTemplate.getForObject(url, ChildrenVO[].class);
			response.toString();
			logger.info("" + response.toString());
			return CompletableFuture.completedFuture(Arrays.asList(response));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new NotDataFoundApiException(e,e.getMessage());
		}

	}

}
