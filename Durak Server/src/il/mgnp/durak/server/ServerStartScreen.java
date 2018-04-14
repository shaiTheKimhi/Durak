package il.mgnp.durak.server;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ServerStartScreen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	//
	
	private ServerMain main;
	
	private Image cards;
	
	private String address;
	
	private int port;
	
	/**
	 * The selection that the host is currently at.
	 * true = 2 players
	 * false = 4 players.
	 */
	private boolean selection;
	
	/**
	 * Whether the host is currently selecting players or about to continue.
	 * true = selecting players
	 * false = about to continue.
	 */
	private boolean selecting;

	public ServerStartScreen(ServerMain main) {
		this.main = main;
		
		this.cards = new ImageIcon("./resources/cards-server.png").getImage();
		
		// Load the player's address
		try {
			URL url = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			this.address = in.readLine();
		} catch (Exception ex) {
			this.address = "-";
		}
		
		this.port = 44444;
		
		this.selection = true;
		
		this.selecting = true;
		
		this.setPreferredSize(new Dimension(600, 640));
		
		this.setBackground(Color.LIGHT_GRAY);
		
		this.setOpaque(true);
		
		this.setFocusable(true);
		
		this.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent event) {

				if (selecting) {
					// Right/Left key
					if (event.getKeyCode() == KeyEvent.VK_RIGHT || event.getKeyCode() == KeyEvent.VK_LEFT) {
						selection = !selection;
					} else {
						// Enter
						if (event.getKeyCode() == KeyEvent.VK_ENTER) {
							selecting = false;
						}
					}
				} else {
					// Arrow up
					if (event.getKeyCode() == KeyEvent.VK_UP) {
						selecting = true;
					} else {
						// Enter
						if (event.getKeyCode() == KeyEvent.VK_ENTER) {
							// TODO: Run the server...
							
							
						}
					}
				}

				repaint();
			}
			
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		// Enable Anti-Aliasing
		Graphics2D g2 = (Graphics2D)g;
		Key textAAk = RenderingHints.KEY_TEXT_ANTIALIASING;
		Object textAAv = RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
		RenderingHints rh = new RenderingHints(textAAk, textAAv);
		g2.setRenderingHints(rh);
		
		// Draw the main image
		g.drawImage(this.cards, 140, 20, null);
		
		// Write the text
		Font font = new Font("Comic Sans MS", Font.BOLD, 48);
		g.setFont(font);
		g.setColor(Color.BLACK);
		
		g.drawString("Durak Server", 144, 400);
		
		// Line 0
		font = new Font("Comic Sans MS", Font.PLAIN, 36);
		g.setFont(font);
		
		g.setColor(Color.BLACK);
		
		String text = "Address: " + this.address;
		int width = g.getFontMetrics().stringWidth(text);
		
		g.drawString(text, (int) (600 - width) / 2, 450);
		
		// Line 1
		g.drawString("Port: " + this.port, 198, 490);
		
		// Line 2
		g.setColor(selection ? Color.RED : Color.BLACK);
		
		g.drawString("2 players", 126, 530);
		
		g.setColor(!selection ? Color.RED : Color.BLACK);
		
		g.drawString("4 players", 320, 530);
		
		// Line 3
		g.setColor(!selecting ? Color.RED : Color.BLACK);
		
		font = new Font("Comic Sans MS", Font.BOLD, 48);
		g.setFont(font);
		
		g.drawString("Continue", 204, 590);
		
		// Copyright
		font = new Font("Comic Sans MS", Font.BOLD, 12);
		g.setFont(font);
		
		g.setColor(Color.WHITE);
		
		g.drawString("Copyright © 2018 MGNP", 2, 638);
	}
}
