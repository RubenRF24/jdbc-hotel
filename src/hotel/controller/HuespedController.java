package hotel.controller;

import java.math.BigInteger;
import java.util.Date;
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

	public int modificar(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			BigInteger telefono, Integer numeroReserva) {
		return new HuespedDAO().modificar(id, nombre, apellido, fechaNacimiento,
								nacionalidad, telefono, numeroReserva);
	}

	public int eliminar(Integer id) {
		return new HuespedDAO().eliminar(id);
	}

}
