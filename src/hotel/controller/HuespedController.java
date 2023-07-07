package hotel.controller;

import java.util.List;

import hotel.dao.HuespedDAO;
import hotel.modelo.Huesped;

public class HuespedController {

	public void guardar(Huesped huesped, String abreviacion) {
		huesped.setNacionalidad(abreviacion);
		new HuespedDAO().guardar(huesped);
	}

	public List<Huesped> cargarHuespedes() {
		return new HuespedDAO().listarHuespedes();
	}

}
