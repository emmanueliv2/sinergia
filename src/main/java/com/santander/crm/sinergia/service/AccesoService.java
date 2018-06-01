package com.santander.crm.sinergia.service;

import com.santander.crm.sinergia.response.GenericTokenRes;

public interface AccesoService {
	
	String generaToken(String canal);
	
	GenericTokenRes consultaToken(String sToken);

}
