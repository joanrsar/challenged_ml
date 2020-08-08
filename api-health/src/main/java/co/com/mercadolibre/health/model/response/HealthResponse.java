package co.com.mercadolibre.health.model.response;

import java.util.List;

import co.com.mercadolibre.health.model.vo.DetalleLogVO;
import co.com.mercadolibre.health.model.vo.LogVO;

public class HealthResponse {

	private LogVO log;
	private List<DetalleLogVO> infoRequest;
	public LogVO getLog() {
		return log;
	}
	public void setLog(LogVO log) {
		this.log = log;
	}
	public List<DetalleLogVO> getInfoRequest() {
		return infoRequest;
	}
	public void setInfoRequest(List<DetalleLogVO> infoRequest) {
		this.infoRequest = infoRequest;
	}
	
	
	
	
	
	
}
