package il.mgnp.durak.client;

import javax.swing.UIManager;

public class ClientStart {

	public static void main(String[] args) throws Exception {
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		new ClientMain();
	
	}

}
