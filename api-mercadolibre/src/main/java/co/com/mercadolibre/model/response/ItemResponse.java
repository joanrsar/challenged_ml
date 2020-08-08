package co.com.mercadolibre.model.response;

import java.util.List;

import co.com.mercadolibre.model.vo.ChildrenVO;
import co.com.mercadolibre.model.vo.ItemVO;

public class ItemResponse {

	private ItemVO item;
	private List<ChildrenVO> children;
	
	
	public ItemVO getItem() {
		return item;
	}
	public void setItem(ItemVO item) {
		this.item = item;
	}
	public List<ChildrenVO> getChildren() {
		return children;
	}
	public void setChildren(List<ChildrenVO> children) {
		this.children = children;
	}
	
	
	
	
}
