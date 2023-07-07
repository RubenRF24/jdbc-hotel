package hotel.main;

import javax.swing.JFrame;

import hotel.views.MenuPrincipal;

public class AluraHotel {
	public static void main(String[] args) {
		MenuPrincipal menuPrincipal = new MenuPrincipal();
		menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuPrincipal.setVisible(true);
	}
}
