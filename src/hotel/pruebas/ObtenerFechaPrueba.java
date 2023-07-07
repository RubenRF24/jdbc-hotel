package hotel.pruebas;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import hotel.modelo.Reserva;

public class ObtenerFechaPrueba {

	public static void main(String[] args) throws ParseException {
		Reserva reserva = new Reserva(new Date(123,6,6), new Date(123,6,10), 10);

		int diff =  (int) (reserva.getFechaSalidaDate().getTime() - reserva.getFechaEntradaDate().getTime());
        TimeUnit time = TimeUnit.DAYS; 
        int difference = (int) time.convert(diff, TimeUnit.MILLISECONDS);
        System.out.println("The difference in days is : "+difference);
		
        System.out.println(reserva.getFechaEntrada());
        System.out.println(reserva.getFechaSalida());
		
		
	}
}
