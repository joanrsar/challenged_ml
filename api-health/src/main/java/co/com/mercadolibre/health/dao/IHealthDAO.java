package co.com.mercadolibre.health.dao;

import java.sql.SQLException;
import java.util.List;

import co.com.mercadolibre.health.exceptions.DaoException;
import co.com.mercadolibre.health.model.response.HealthResponse;

public interface IHealthDAO {

	public List<HealthResponse> consultarLog() throws DaoException, SQLException;
}
