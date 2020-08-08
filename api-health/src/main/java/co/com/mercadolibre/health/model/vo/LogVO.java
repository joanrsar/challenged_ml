package co.com.mercadolibre.health.model.vo;

public class LogVO {

	
	private String date;
	private long   avgResponseTime;
	private int    totalRequest;
	private long   avgResponseTimeApiCalls;
	private int	   totalCountApiCalls;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public long getAvgResponseTime() {
		return avgResponseTime;
	}
	public void setAvgResponseTime(long avgResponseTime) {
		this.avgResponseTime = avgResponseTime;
	}
	public int getTotalRequest() {
		return totalRequest;
	}
	public void setTotalRequest(int totalRequest) {
		this.totalRequest = totalRequest;
	}
	public long getAvgResponseTimeApiCalls() {
		return avgResponseTimeApiCalls;
	}
	public void setAvgResponseTimeApiCalls(long avgResponseTimeApiCalls) {
		this.avgResponseTimeApiCalls = avgResponseTimeApiCalls;
	}
	public int getTotalCountApiCalls() {
		return totalCountApiCalls;
	}
	public void setTotalCountApiCalls(int totalCountApiCalls) {
		this.totalCountApiCalls = totalCountApiCalls;
	}
	
	
	
}
