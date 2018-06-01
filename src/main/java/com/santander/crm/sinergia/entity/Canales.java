package com.santander.crm.sinergia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SIN_MX_CAT_CANALES")
public class Canales {

	@Id
	@Column(name = "ID_CANAL")
	private Integer id;

	@Column(name = "TXT_NOM_CANAL")
	private String nombre;

	@Column(name = "NUM_UU_ID")
	private String uuid;

	@Column(name = "FLG_DISP")
	private Integer flg;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getFlg() {
		return flg;
	}

	public void setFlg(Integer flg) {
		this.flg = flg;
	}

}
