package co.com.mercadolibre.health.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.com.mercadolibre.health.dao.HealthDAOImpl;
import co.com.mercadolibre.health.dao.IHealthDAO;
import co.com.mercadolibre.health.exceptions.ServiceException;
import co.com.mercadolibre.health.model.response.HealthResponse;





@Service
public class HealthService {

	private Logger logger = LoggerFactory.getLogger(HealthService.class);
	
	private IHealthDAO dao;
	
	public List<HealthResponse> consultarLog() throws ServiceException {
		try {
			dao = new HealthDAOImpl();
			List<HealthResponse> health = dao.consultarLog();
			return health;
		}
		catch(Exception e) {
			logger.error("Error en[consultarApiMercadolibre]", e);
			throw new ServiceException(e, e.getMessage() );
		}
	
	
	}
}
