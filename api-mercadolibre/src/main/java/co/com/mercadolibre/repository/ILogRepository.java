package co.com.mercadolibre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.mercadolibre.model.Log;

public interface ILogRepository extends JpaRepository< Log,String>{

}
