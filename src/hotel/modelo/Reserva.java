package hotel.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva {

	private int id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private double valor;
	private int formaPagoId;

	public Reserva(Date fechaEntrada, Date fechaSalida, double valor) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFechaEntrada() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return String.valueOf(sdf.format(this.fechaEntrada));
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public String getFechaSalida() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return String.valueOf(sdf.format(this.fechaSalida));
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getFormaPagoId() {
		return formaPagoId;
	}

	public void setFormaPagoId(int formaPagoId) {
		this.formaPagoId = formaPagoId;
	}
	
	
	
	
}
