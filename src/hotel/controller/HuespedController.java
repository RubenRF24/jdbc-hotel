package hotel.controller;

import hotel.modelo.Huesped;

public class HuespedController {

	public void guardar(Huesped huesped, String abreviacion) {
		huesped.setNacionalidad(abreviacion);
		new HuespedDAO().guardar(huesped);
	}

}
