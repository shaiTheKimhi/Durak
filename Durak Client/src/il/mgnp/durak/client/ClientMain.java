package il.mgnp.durak.client;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ClientMain {
	
	private ClientStartScreen startScreen;
	
	public ClientMain() {
		
		// Initialize the graphics
		initGraphics();
		
	}
	
	private void initGraphics() {
		
		JFrame frame = new JFrame("Durak");
		startScreen = new ClientStartScreen(this);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(startScreen);
		frame.pack();
		frame.setLocationRelativeTo(null);
		
		Image icon = new ImageIcon("./resources/icon.png").getImage();
		frame.setIconImage(icon);
		
	}
	
	
}
