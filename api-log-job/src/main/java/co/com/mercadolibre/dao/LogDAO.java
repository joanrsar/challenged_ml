package co.com.mercadolibre.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.com.mercadolibre.exceptions.DaoException;
import co.com.mercadolibre.model.Log;
import co.com.mercadolibre.utils.Conexion;

public class LogDAO implements ILogDAO {

	private Logger logger = LoggerFactory.getLogger(LogDAO.class);
	
	private final static String SQL = "INSERT INTO LOG(CODIGO_RESPUESTA,TIEMPO_EJECUCION, CONSUMIO_API,TIEMPO_EJECUCION_API,FECHA)"
	 		+ " VALUES (?,?,?,?,?)";

	@Override
	public void save(Log log) throws DaoException, SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			 con = Conexion.conectar();
		
			 logger.info(SQL);
			
			 ps = con.prepareStatement(SQL);
	
			 int i = 1;
			 ps.setInt(i++, log.getCodigoRespuesta());
	         ps.setLong(i++, log.getTiempoEjecucion());
	         ps.setInt(i++, log.getConsumioApi());
	         ps.setLong(i++, log.getTiempoEjecucionApi());
	         ps.setTimestamp(i++, new Timestamp(log.getFecha().getTime()) );
	         ps.executeUpdate();
	    
		} catch (Exception e) {
			throw new DaoException(e, "Error en save " + e.getMessage());
		} finally {
			con.close();
			ps.close();
		}

	}

}
