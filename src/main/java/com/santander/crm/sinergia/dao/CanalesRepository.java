package com.santander.crm.sinergia.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.santander.crm.sinergia.entity.Canales;

public interface CanalesRepository extends CrudRepository<Canales, Integer> {
	
	@Query("SELECT c FROM Canales c WHERE c.uuid = :uuidCanal AND c.flg = 1 ")
	Canales findCanalByUuid (@Param("uuidCanal") String uuidCanal);

}
