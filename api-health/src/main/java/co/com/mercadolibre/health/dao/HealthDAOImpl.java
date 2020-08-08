package co.com.mercadolibre.health.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.com.mercadolibre.health.exceptions.DaoException;
import co.com.mercadolibre.health.model.response.HealthResponse;
import co.com.mercadolibre.health.model.vo.DetalleLogVO;
import co.com.mercadolibre.health.model.vo.LogVO;
import co.com.mercadolibre.health.utils.Conexion;

public class HealthDAOImpl implements IHealthDAO {

	private Logger logger = LoggerFactory.getLogger(HealthDAOImpl.class);

	private final static String SQL_CONSULTA = "select  TO_CHAR(fecha,'YYYY-MM-DD HH-MI') fecha, "
			+ "		avg(l.tiempo_ejecucion) promedio_respuesta, " + "		count(1) total_peticiones, "
			+ "		avg(l.tiempo_ejecucion_api) promedio_respuesta_api, " + "		sum(l.consumio_api) cantidad_apis "
			+ " from log l where l.fecha >= current_date -2  group by TO_CHAR(fecha,'YYYY-MM-DD HH-MI') ";

	private final static String SQL_DETALLE = "select  CODIGO_RESPUESTA, COUNT(1) CONTADOR from log l "
			+ "where to_char(L.FECHA,'YYYY-MM-DD HH-MI') = ? group by CODIGO_RESPUESTA;  ";

	@Override
	public List<HealthResponse> consultarLog() throws DaoException, SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<HealthResponse> lista = new ArrayList<HealthResponse>();
		try {
			con = Conexion.conectar();
			logger.info(SQL_CONSULTA);
			ps = con.prepareStatement(SQL_CONSULTA);
			rs = ps.executeQuery();
			while (rs.next()) {
				LogVO logVO = new LogVO();
				HealthResponse response = new HealthResponse();
				logVO.setDate(rs.getString("FECHA"));
				logVO.setAvgResponseTime(rs.getLong("PROMEDIO_RESPUESTA"));
				logVO.setTotalRequest(rs.getInt("TOTAL_PETICIONES"));
				logVO.setAvgResponseTimeApiCalls(rs.getLong("PROMEDIO_RESPUESTA_API"));
				logVO.setTotalCountApiCalls(rs.getInt("CANTIDAD_APIS"));
				
				List<DetalleLogVO> detalle = new ArrayList<>();
				detalle = this.detalleLog(logVO.getDate());
				response.setLog(logVO);
				response.setInfoRequest(detalle);
				
				lista.add(response);
				
			}

		} catch (Exception e) {
			throw new DaoException(e, "Error en consultarLog " + e.getMessage());
		} finally {
			con.close();
			if(ps != null ) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
			
			
		}
		return lista;
	}

	public List<DetalleLogVO> detalleLog(String fecha) throws DaoException, SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<DetalleLogVO>  lista = new ArrayList<DetalleLogVO>();
		int i = 1;
		try {
			con = Conexion.conectar();
			ps = con.prepareStatement(SQL_DETALLE);
			logger.info("Fecha "+fecha);
			logger.info(SQL_DETALLE);
			ps.setString(i, fecha);
			rs = ps.executeQuery();
			while (rs.next()) {
				DetalleLogVO detalleLogVO = new DetalleLogVO();
				detalleLogVO.setStatusCode(rs.getInt("CODIGO_RESPUESTA"));
				detalleLogVO.setCount(rs.getInt("CONTADOR"));
				lista.add(detalleLogVO);
			}

		} catch (Exception e) {
			throw new DaoException(e, "Error en detalleLog " + e.getMessage());
		} finally {
			con.close();
			if(ps != null ) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		}
		return lista;
	}

}
