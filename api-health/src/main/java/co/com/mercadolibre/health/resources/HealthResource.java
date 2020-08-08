package co.com.mercadolibre.health.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.mercadolibre.health.exceptions.ResourceBadRequestException;
import co.com.mercadolibre.health.exceptions.ServiceException;
import co.com.mercadolibre.health.model.response.HealthResponse;
import co.com.mercadolibre.health.service.HealthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/health")
@Api(tags = { "Servicio que retorna el estado de salud del servicio" })
public class HealthResource {

	private static final Logger logger = LoggerFactory.getLogger(HealthResource.class);

	@Autowired
	private HealthService service;
	
	@GetMapping
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Health encontrado satisfactoriamente"),
			@ApiResponse(code = 404, message = "Item no encontrado") })
	public ResponseEntity<List<HealthResponse>> getItem()
			throws ServiceException, Exception {
		try {
			List<HealthResponse> response = service.consultarLog();
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (ResourceBadRequestException e) {
			logger.error("Error[getItem]" + e.getMessage(), e);
			throw new ResourceBadRequestException(e.getMessage(), e);
		}
	}
}
