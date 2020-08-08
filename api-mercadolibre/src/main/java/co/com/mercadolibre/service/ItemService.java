package co.com.mercadolibre.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.mercadolibre.configuration.PropertiesConfiguration;
import co.com.mercadolibre.exceptions.NotDataFoundApiException;
import co.com.mercadolibre.exceptions.ServiceException;
import co.com.mercadolibre.model.Item;
import co.com.mercadolibre.model.ItemChildren;
import co.com.mercadolibre.model.response.ItemResponse;
import co.com.mercadolibre.model.vo.ChildrenVO;
import co.com.mercadolibre.model.vo.ItemVO;
import co.com.mercadolibre.repository.IItemChildrenRepository;
import co.com.mercadolibre.repository.IItemRepository;
import co.com.mercadolibre.utils.ApiManager;

@Service
@Transactional
public class ItemService {

	private Logger logger = LoggerFactory.getLogger(ItemService.class);
	
	
	@Autowired
	private PropertiesConfiguration myProps;
	
	
	@Autowired
	private IItemRepository repo;
	
	@Autowired
	private IItemChildrenRepository childrenRepo;
	
	
	@Transactional
	public ItemResponse consultarItem(String itemId) throws ServiceException {
		ItemResponse itemResponse = new ItemResponse();
		
		try {		
	
			itemResponse = consultarApiExterna(itemId);

		}catch(Exception e) {
			logger.error("Error en[consultarApiMercadolibre]", e);
			throw new ServiceException(e, e.getMessage() );
		}
		return itemResponse;
	}
	
	/**
	 * Consulta las Apis de mercado libre
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	private ItemResponse consultarApiExterna(String itemId) throws Exception  {
		ItemResponse itemResponse = new ItemResponse();
		Item item = new Item();
		ApiManager service = new ApiManager();
		try {
			String itemUrl = myProps.getItemUrl().replace("$ITEMID", itemId);
			String childrenItem = myProps.getChildrenUrl().replace("$ITEMID", itemId);;
			
			CompletableFuture<ItemVO> apiItems = service.callApiItem(itemUrl);
			CompletableFuture<List<ChildrenVO>> apiChildrenitem = service.callApiChild(childrenItem);
			
			if (apiItems != null ) {
				item = new Item();
				item.setItemId(apiItems.get().getId());
				item.setCategoryId(apiItems.get().getCategory_id());
				item.setPrice(apiItems.get().getPrice());
				item.setStartTime(apiItems.get().getStart_time());
				item.setStopTime(apiItems.get().getStop_time());
				item.setTitle(apiItems.get().getTitle());
				
				repo.save(item);
				itemResponse.setItem(apiItems.get());
				logger.info("item correctamente guardado");
				
				if( apiChildrenitem.get().size() > 0 ) {
					List <ChildrenVO> lista = new ArrayList<>(apiChildrenitem.get()
																.stream()
																.collect(Collectors.toList()));
					
					
					
					logger.info("Cantidad de hijos "+lista.size());
					List <ItemChildren> childrenList = new ArrayList<ItemChildren>();
					for (ChildrenVO childrenVO : lista) {
						ItemChildren children = new ItemChildren();
						children.setItemId(item.getItemId());
						children.setItemChildrenId(childrenVO.getId());
						children.setStopTime(childrenVO.getStop_time());
						childrenList.add(children);
						
						ItemChildren children2 = new ItemChildren();
						children2.setItemId(item.getItemId());
						children2.setItemChildrenId(item.getItemId());
						children2.setStopTime(item.getStartTime());
						childrenList.add(children2);
						
					}
					itemResponse.setChildren(lista);
					childrenRepo.saveAll(childrenList);
					logger.info("Children almacenados");
					
				}
				
			}
			

			
			
		}catch(NotDataFoundApiException e) {
			logger.error("Error en [consultarApiExterna] No se encontro informaciòn en los servicios"+e.getMessage(),e);
			return null;
		}
		catch(Exception e) {
			logger.error("Error en [consultarApiExterna] "+e.getMessage(),e);
			throw new Exception(e.getMessage());
		}
		
		return itemResponse;
	}
	
	public ItemResponse consultaritemDB(String itemId) throws ServiceException {
		ItemResponse itemResponse = new ItemResponse();
		ItemVO itemVO = new ItemVO();
		List<ChildrenVO> listaChildren = new ArrayList<ChildrenVO>();
		try {		
			Item item = repo.getByItemId(itemId);
			if( item != null ){
				logger.info("Encontre en BD");
				itemVO.setId(item.getItemId());
				itemVO.setCategory_id(item.getCategoryId());
				itemVO.setPrice(item.getPrice());
				itemVO.setStart_time(item.getStartTime());
				itemVO.setStop_time(item.getStopTime());
				itemVO.setTitle(item.getTitle());
				
				itemResponse.setItem(itemVO);
				
				List<ItemChildren> children = childrenRepo.getByItemId(itemId);
				logger.info("Se econtraron "+children.size()+" children para el itemId "+itemId);
				for (ItemChildren child : children){
					logger.info("Seteando children");
					ChildrenVO childrenVO = new ChildrenVO();
					childrenVO.setId(child.getItemChildrenId());
					childrenVO.setStop_time(child.getStopTime());
					listaChildren.add(childrenVO);
					
				}
				
				itemResponse.setChildren(listaChildren);
			}
			

		}catch(Exception e) {
			logger.error("Error en[consultarApiMercadolibre]", e);
			throw new ServiceException(e, e.getMessage() );
		}
		return itemResponse;
	}
	
}
