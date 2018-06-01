package com.santander.crm.sinergia.service.impl;

import java.util.Date;

import javax.validation.ValidationException;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.santander.crm.sinergia.dao.CanalesRepository;
import com.santander.crm.sinergia.dao.TokenRepository;
import com.santander.crm.sinergia.entity.Canales;
import com.santander.crm.sinergia.entity.Token;
import com.santander.crm.sinergia.response.GenericTokenRes;
import com.santander.crm.sinergia.service.AccesoService;
import com.santander.crm.sinergia.util.Tokens;

@Service
public class AccesoServiceImpl implements AccesoService {

	@Autowired
	CanalesRepository canalesRepository;
	
	@Autowired
	TokenRepository tokenRepository;
	
	private static Logger LOGGER = LoggerFactory.getLogger(AccesoServiceImpl.class);

	@Override
	public String generaToken(String canal) {
		String sToken = ""; 
		try {
			byte[] message = Base64.decodeBase64(canal);
			String idCanal = new String(message);
			Canales c = canalesRepository.findCanalByUuid(idCanal);
			if (c != null) {
				Date now = new Date();
				Date exp = new Date(System.currentTimeMillis() + (1000 * 3600)); // 3600 seconds
				sToken = Tokens.generateToken(idCanal, "genera token 2", now, exp);
				Token token = new Token();
				token.setsToken(sToken);
				token.setFechaAlta(now);
				token.setFechaVigencia(exp);
				System.out.println("--->"+token.getFechaVigencia());
				Token tokenSaved = tokenRepository.save(token);
				Tokens.verifyToken(sToken);
			} else {
				sToken = "No es un canal valido de acceso";
				throw new ValidationException(sToken);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		}
		return sToken;
	}

	@Override
	public GenericTokenRes consultaToken(String sToken) {
		GenericTokenRes response = new GenericTokenRes();
		try {
			Token token = tokenRepository.getBySToken(sToken);
			response.setToken(token);
			response.setHttpStatus(HttpStatus.OK);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

}
