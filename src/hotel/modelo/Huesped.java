package hotel.modelo;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Huesped {

	private int id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String nacionalidad;
	private BigInteger telefono;
	private int reservaId;

	public Huesped(String nombre, String apellido, Date fechaNacimiento, BigInteger telefono, int reservaId) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.reservaId = reservaId;
	}

	public Huesped(int id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			BigInteger telefono, int reservaId) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.reservaId = reservaId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getFechaNacimiento() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return String.valueOf(sdf.format(this.fechaNacimiento));
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public BigInteger getTelefono() {
		return telefono;
	}

	public void setTelefono(BigInteger telefono) {
		this.telefono = telefono;
	}

	public int getReservaId() {
		return reservaId;
	}

	public void setReservaId(int reservaId) {
		this.reservaId = reservaId;
	}

}
