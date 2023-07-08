package hotel.controller;

import java.util.Date;
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

	public int modificar(Integer id, Date fechaEntrada, Date fechaSalida, Double valor, Integer formaPago) {
		return new ReservaDAO().modificar(id,fechaEntrada, fechaSalida, valor, formaPago);
	}

}
