package co.com.mercadolibre.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import co.com.mercadolibre.dao.ILogDAO;
import co.com.mercadolibre.dao.LogDAO;
import co.com.mercadolibre.exceptions.ServiceException;
import co.com.mercadolibre.model.Log;
import co.com.mercadolibre.model.vo.LogVO;


@Service
public class LogService {

	private Logger logger = LoggerFactory.getLogger(LogService.class);
	


	
	public void registrarLog(String mensaje) throws ServiceException {
		Log log = new Log();
		ILogDAO dao = new LogDAO();
		Gson gson = new Gson();
		try {
	
			System.out.println("Llego el mensaje al service "+mensaje);
			LogVO logVO = gson.fromJson(mensaje, LogVO.class);			
			System.out.println("Llegue al service"+logVO.toString());
			log.setCodigoRespuesta(logVO.getCodigoRespuesta());
			int consumio = logVO.isCallApi() ? 1 : 0;
			log.setConsumioApi(consumio);
			log.setFecha(logVO.getFechalog());
			log.setTiempoEjecucion(logVO.getTiempoTotalEjecucion());
			log.setTiempoEjecucionApi(logVO.getTiempollamadaApi());
			
			
			dao.save(log);
		}
		catch(Exception e) {
			logger.error("Error en[registrarLog]", e);
			throw new ServiceException(e, e.getMessage() );
		}
	}
	
}
