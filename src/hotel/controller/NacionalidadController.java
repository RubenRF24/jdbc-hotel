package hotel.controller;

import java.util.List;

import hotel.dao.NacionalidadDAO;
import hotel.modelo.Nacionalidad;

public class NacionalidadController {

	public List<Nacionalidad> listar(){
		return new NacionalidadDAO().listar();
	}
}
