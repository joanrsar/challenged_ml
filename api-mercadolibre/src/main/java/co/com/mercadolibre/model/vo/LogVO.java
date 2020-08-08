package co.com.mercadolibre.model.vo;

import java.util.Date;

public class LogVO {

	private int 	codigoRespuesta;
	private long 	tiempoTotalEjecucion;
	private boolean callApi;
	private long 	tiempollamadaApi;
	private Date 	fechalog;
	
	public int getCodigoRespuesta() {
		return codigoRespuesta;
	}
	public void setCodigoRespuesta(int codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}
	public long getTiempoTotalEjecucion() {
		return tiempoTotalEjecucion;
	}
	public void setTiempoTotalEjecucion(long tiempoTotalEjecucion) {
		this.tiempoTotalEjecucion = tiempoTotalEjecucion;
	}
	public boolean isCallApi() {
		return callApi;
	}
	public void setCallApi(boolean callApi) {
		this.callApi = callApi;
	}
	public long getTiempollamadaApi() {
		return tiempollamadaApi;
	}
	public void setTiempollamadaApi(long tiempollamadaApi) {
		this.tiempollamadaApi = tiempollamadaApi;
	}
	public Date getFechalog() {
		return fechalog;
	}
	public void setFechalog(Date fechalog) {
		this.fechalog = fechalog;
	}
	
	
	
}
