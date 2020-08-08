package co.com.mercadolibre.model;

import java.util.Date;


public class Log {
	
	private int 	idLog;
	private int 	codigoRespuesta;
	private long 	tiempoEjecucion;
	private int 	consumioApi;
	private long	tiempoEjecucionApi;
	private Date	fecha;
	public int getIdLog() {
		return idLog;
	}
	public void setIdLog(int idLog) {
		this.idLog = idLog;
	}
	public int getCodigoRespuesta() {
		return codigoRespuesta;
	}
	public void setCodigoRespuesta(int codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}
	public long getTiempoEjecucion() {
		return tiempoEjecucion;
	}
	public void setTiempoEjecucion(long tiempoEjecucion) {
		this.tiempoEjecucion = tiempoEjecucion;
	}
	public int getConsumioApi() {
		return consumioApi;
	}
	public void setConsumioApi(int consumioApi) {
		this.consumioApi = consumioApi;
	}
	public long getTiempoEjecucionApi() {
		return tiempoEjecucionApi;
	}
	public void setTiempoEjecucionApi(long tiempoEjecucionApi) {
		this.tiempoEjecucionApi = tiempoEjecucionApi;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	


}
