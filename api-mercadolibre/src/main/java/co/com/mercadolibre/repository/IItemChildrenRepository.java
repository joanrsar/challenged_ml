package co.com.mercadolibre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.mercadolibre.model.ItemChildren;



public interface IItemChildrenRepository extends JpaRepository<ItemChildren,String> {
	
	public List<ItemChildren> getByItemId(String itemId);

}
