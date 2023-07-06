package hotel.pruebas;

import java.text.SimpleDateFormat;
import java.util.Date;

import hotel.modelo.Reserva;

public class ObtenerFechaPrueba {

	public static void main(String[] args) {
		Reserva reserva = new Reserva(new Date(123,6,6), new Date(2023,6,10), 10);
		System.out.println(reserva.getFechaEntrada());
	}
}
