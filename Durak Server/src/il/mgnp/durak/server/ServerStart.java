package il.mgnp.durak.server;

import javax.swing.UIManager;

public class ServerStart {

	public static void main(String[] args) throws Exception {

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		new ServerMain();

	}
	
}
