package co.com.mercadolibre.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ParametersUtils {

	private static Logger logger = LoggerFactory.getLogger(ParametersUtils.class);
	
	public static String getVariable(String nombre) {
		String resultado = "";
		try {
			resultado = System.getenv(nombre);
			logger.info("El valor para la variable "+nombre+ " es "+resultado);
		}
		catch(Exception e) {
			logger.error("Error en[getVariable]", e);
		}
		return resultado;
		
	}
}
