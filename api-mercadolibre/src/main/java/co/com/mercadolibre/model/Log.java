package co.com.mercadolibre.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "log")
public class Log {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
	private int 	idLog;
	private int 	codigoRespuesta;
	private long 	tiempoEjecucion;
	private int 	consumioApi;
	private long	tiempoEjecucionApi;
	@Temporal(TemporalType.TIMESTAMP)
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
