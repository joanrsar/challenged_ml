package co.com.mercadolibre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.mercadolibre.model.Item;

public interface IItemRepository extends JpaRepository<Item,String>{

	public Item getByItemId(String itemId);
}
