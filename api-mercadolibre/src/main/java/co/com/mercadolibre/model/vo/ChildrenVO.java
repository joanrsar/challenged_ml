package co.com.mercadolibre.model.vo;

import java.util.Date;

public class ChildrenVO {

	private String id;
	private Date   stop_time;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getStop_time() {
		return stop_time;
	}
	public void setStop_time(Date stop_time) {
		this.stop_time = stop_time;
	}
	@Override
	public String toString() {
		return "ChildrenVO [id=" + id + ", stop_time=" + stop_time + "]";
	}
	
	
	
	
}
