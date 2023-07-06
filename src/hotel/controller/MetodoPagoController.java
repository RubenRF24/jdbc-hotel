package hotel.controller;

import java.util.List;

import hotel.dao.MetodoPagoDAO;
import hotel.modelo.MetodoPago;

public class MetodoPagoController {

	public List<MetodoPago> listar(){
		return new MetodoPagoDAO().listar();
	}
}
