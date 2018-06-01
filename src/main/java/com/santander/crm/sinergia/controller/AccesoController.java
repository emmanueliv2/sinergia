package com.santander.crm.sinergia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.santander.crm.sinergia.entity.Token;
import com.santander.crm.sinergia.response.GenericTokenRes;
import com.santander.crm.sinergia.service.AccesoService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@RequestMapping("/sinergia/acceso/")
public class AccesoController {
	
	@Autowired
	@Qualifier("accesoServiceImpl")
	AccesoService accesoService;
	
	/**
	 * Endpoint POST para generar y guardar un token
	 * @param canal enviado por NEO
	 * @return token
	 */
	@RequestMapping(value = "token/generaToken", method = { RequestMethod.POST })
	@CrossOrigin(origins = "*")
	public ResponseEntity<String> pruebas(@RequestHeader(value = "canal", required = true) String canal) {
		
		String token = accesoService.generaToken(canal);
		
		HttpStatus hs = HttpStatus.OK;		
		HttpHeaders header = new HttpHeaders();
		return new ResponseEntity<String>(token,header, hs);
	}
	
	/**
	 * Endpoint GET para consultar un token
	 * @param sToken token enviado por NEO
	 * @return token encontrado
	 */
	@RequestMapping(value = "/token/consultaToken", method = { RequestMethod.POST })
	@CrossOrigin(origins = "*")
	public ResponseEntity<Token> consultarProspecto(@RequestHeader(value = "sToken", required = true) String sToken) {
		GenericTokenRes res = accesoService.consultaToken(sToken);
		
		HttpStatus hs = res.getHttpStatus();
		HttpHeaders header = new HttpHeaders();
		header.add("errorMessage", res.getMessage());

		Token token = res.getToken();
		return new ResponseEntity<Token>(token, header, hs);
	}

}
