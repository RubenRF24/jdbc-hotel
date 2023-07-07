package hotel.modelo;

public class Nacionalidad {

	private String pais;
	private String gentilicio;
	private String abreviacion;

	public Nacionalidad(String pais, String abreviacion) {
		this.pais = pais;
		this.abreviacion = abreviacion;
	}
	
	public Nacionalidad(String pais, String gentilicio, String abreviacion) {
		this.pais = pais;
		this.gentilicio = gentilicio;
		this.abreviacion = abreviacion;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getGentilicio() {
		return gentilicio;
	}

	public void setGentilicio(String gentilicio) {
		this.gentilicio = gentilicio;
	}

	public String getAbreviacion() {
		return abreviacion;
	}

	public void setAbreviacion(String abreviacion) {
		this.abreviacion = abreviacion;
	}

	@Override
	public String toString() {
		return this.pais + " - "+ this.abreviacion;
	}

	
}
