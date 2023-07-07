package hotel.pruebas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
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
