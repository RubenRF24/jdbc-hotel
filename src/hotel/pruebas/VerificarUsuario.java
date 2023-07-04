package hotel.pruebas;

import hotel.controller.UsuarioController;

public class VerificarUsuario {

	public static void main(String[] args) {
		UsuarioController usuarioController= new UsuarioController();
		usuarioController.verificarLogin("ruben", "12345");
	}
}
