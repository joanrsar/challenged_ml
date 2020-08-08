package co.com.mercadolibre.model.vo;

import java.util.Date;

public class ItemVO {
    
	private String id;
    private String title;
    private String category_id;
    private long   price;
    private Date   start_time;
    private Date   stop_time;
    
    
    
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getStop_time() {
		return stop_time;
	}
	public void setStop_time(Date stop_time) {
		this.stop_time = stop_time;
	}
	
	@Override
	public String toString() {
		return "ItemVO [itemId=" + id + ", title=" + title + ", category_id=" + category_id + ", price=" + price
				+ ", start_time=" + start_time + ", stop_time=" + stop_time + "]";
	}
	

    
    
    


    
    
    
    
}
