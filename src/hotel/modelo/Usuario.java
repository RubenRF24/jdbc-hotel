package hotel.modelo;

public class Usuario {

	private Integer id;
	private String login;
	private String clave;
	
	public Usuario(Integer id, String login) {
		this.id = id;
		this.login = login;
	}
	
	public Usuario(Integer id, String login, String clave) {
		this.id = id;
		this.login = login;
		this.clave = clave;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return login;
	}

	public void setUsuario(String login) {
		this.login = login;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	
	
	
}
