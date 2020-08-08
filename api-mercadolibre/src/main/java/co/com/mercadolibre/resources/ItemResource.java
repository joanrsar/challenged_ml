package co.com.mercadolibre.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.mercadolibre.exceptions.ResourceBadRequestException;
import co.com.mercadolibre.exceptions.ServiceException;
import co.com.mercadolibre.model.Item;
import co.com.mercadolibre.model.response.ItemResponse;
import co.com.mercadolibre.service.ItemService;
import co.com.mercadolibre.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/items")
@Api(tags = { "Servicio que retorna los detalles asociados a un Item" })
public class ItemResource {

	private static final Logger logger = LoggerFactory.getLogger(ItemResource.class);
	
	@Autowired
	private ItemService service;
	
	@Autowired
	private LogService log;
	
	@GetMapping("/{itemId}")
	@ApiOperation(value="Consultar Item", notes= "Servicio para consultar los Items y sus Children")
	@ApiResponses(value= {@ApiResponse(code=200,message="Item encontrado satisfactoriamente"),
						 @ApiResponse(code=404,message="Item no encontrado")
						} )
	public ResponseEntity<ItemResponse> getItem(@PathVariable("itemId") String itemId) throws ServiceException, Exception {
		ItemResponse response = new ItemResponse();
		long inicio = System.currentTimeMillis();
		boolean apiCalls = false;
		long tiempoIniApi = 0;
		long tiempoTotalApi = 0;
		int  codigoRespuesta = 0;
		try {
			logger.info("Buscando servicios");
			response = service.consultaritemDB(itemId);
			if( response.getItem() == null ) {
				apiCalls = true;
				logger.info("No encontre informacón en base de datos");
				tiempoIniApi = System.currentTimeMillis();
				response = service.consultarItem(itemId);
				tiempoTotalApi = System.currentTimeMillis() - tiempoIniApi;
				if(response == null ) {
					codigoRespuesta = HttpStatus.NOT_FOUND.value();
					return new ResponseEntity<ItemResponse>(HttpStatus.NOT_FOUND);
				}
			}
			
			codigoRespuesta = HttpStatus.OK.value();
			return new ResponseEntity<>(response,HttpStatus.OK);
		}
		catch(ResourceBadRequestException e){
			codigoRespuesta = HttpStatus.BAD_REQUEST.value();
			logger.error("Error[getItem]"+e.getMessage(),e);
			throw new ResourceBadRequestException(e.getMessage(),e);
		}finally {
			long total = System.currentTimeMillis() - inicio;
			log.logManager(codigoRespuesta, total, apiCalls, tiempoTotalApi);
		}

	}

}
