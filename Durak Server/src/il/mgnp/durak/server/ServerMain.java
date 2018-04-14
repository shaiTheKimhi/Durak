package il.mgnp.durak.server;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ServerMain {
	
	private ServerStartScreen startScreen;
	
	public ServerMain() {
		
		// Initialize the graphics
		initGraphics();
		
	}

	private void initGraphics() {
		
		JFrame frame = new JFrame("Durak Server");
		startScreen = new ServerStartScreen(this);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(startScreen);
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		Image icon = new ImageIcon("./resources/icon-server.png").getImage();
		frame.setIconImage(icon);
		
	}
}
