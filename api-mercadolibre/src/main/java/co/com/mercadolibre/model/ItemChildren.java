package co.com.mercadolibre.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "item_children")
public class ItemChildren  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String itemId;
	
	private String itemChildrenId;

	@Temporal(TemporalType.TIMESTAMP)
    private Date   stopTime;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public Date getStopTime() {
		return stopTime;
	}
	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}
	
	public String getItemChildrenId() {
		return itemChildrenId;
	}
	public void setItemChildrenId(String itemChildrenId) {
		this.itemChildrenId = itemChildrenId;
	}
	
	
	
    
    

}
