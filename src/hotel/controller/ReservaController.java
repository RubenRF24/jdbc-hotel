package hotel.controller;

import java.util.List;

import hotel.dao.ReservaDAO;
import hotel.modelo.Reserva;

public class ReservaController {

	public int guardar(Reserva reserva, Integer formaPagoId) {
		reserva.setFormaPagoId(formaPagoId);
		return new ReservaDAO().guardar(reserva);
	}

	public List<Reserva> cargarReservas() {
		return new ReservaDAO().listarReservas();
	}

}
