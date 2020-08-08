package co.com.mercadolibre.dao;

import java.sql.SQLException;

import co.com.mercadolibre.exceptions.DaoException;
import co.com.mercadolibre.model.Log;

public interface ILogDAO {

	
	public void save(Log log) throws DaoException, SQLException;
}
