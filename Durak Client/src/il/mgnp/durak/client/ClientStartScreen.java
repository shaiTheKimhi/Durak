package il.mgnp.durak.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import il.mgnp.durak.client.additional.ClientHelpfulStuff;

public class ClientStartScreen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	//
	
	private ClientMain main;
	
	private Image cards;
	
	private String nickname = "";
	
	private String address = "";
	
	/**
	 * Whether the player is currently typing the nickname or the address.
	 */
	private boolean isTypingNick = true;
	
	public ClientStartScreen(ClientMain main) {
		this.main = main;
		
		this.cards = new ImageIcon("./resources/cards.png").getImage();
		
		this.setPreferredSize(new Dimension(600, 600));
		
		this.setBackground(Color.LIGHT_GRAY);
		
		this.setOpaque(true);
		
		/*
		 * The ability to write the server address.
		 */
		
		setFocusable(true);
		
		this.addKeyListener(new KeyAdapter() {
			
			@Override @SuppressWarnings("deprecation")
			public void keyPressed(KeyEvent event) {
				
				if (isTypingNick) {
					// Backspace
					if (event.getKeyChar() == '\b') {
						if (nickname.isEmpty()) {
							return;
						}
						String temp = nickname.substring(0, nickname.length() - 1);
						nickname = temp;
					}
					
					// ABC or digit
					if (nickname.length() < 12) {
						if (event.getKeyChar() >= '0' && event.getKeyChar() <= '9' ||
								event.getKeyChar() >= 'a' && event.getKeyChar() <= 'z' || 
								event.getKeyChar() >= 'A' && event.getKeyChar() <= 'Z') {
							String temp = nickname + event.getKeyChar();
							nickname = temp;
						}
					}
					
					// Paste
					if (event.getKeyCode() == KeyEvent.VK_V &&
							(event.getModifiers() & Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()) != 0) {
						String toAdd = ClientHelpfulStuff.getSysClipboardText();
						if (toAdd.length() + nickname.length() < 15) {
							String temp = nickname + toAdd;
							nickname = temp;
						}
					}
					
					// Arrow down
					if (event.getKeyCode() == KeyEvent.VK_DOWN) {
						isTypingNick = false;
					}
				} else {
					// Backspace
					if (event.getKeyChar() == '\b') {
						if (address.isEmpty()) {
							return;
						}
						String temp = address.substring(0, address.length() - 1);
						address = temp;
					}
					
					// Digit or period
					if (address.length() < 15 && event.getKeyChar() >= '0' && event.getKeyChar() <= '9' || event.getKeyChar() == '.') {
						String temp = address + event.getKeyChar();
						address = temp;
					}
					
					// Paste
					if (event.getKeyCode() == KeyEvent.VK_V &&
							(event.getModifiers() & Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()) != 0) {
						String toAdd = ClientHelpfulStuff.getSysClipboardText();
						if (toAdd.length() + address.length() < 15) {
							String temp = address + toAdd;
							address = temp;
						}
					}
					
					// Arrow up
					if (event.getKeyCode() == KeyEvent.VK_UP) {
						isTypingNick = true;
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
		
		String text = "Welcome to Durak!";
		int width = g.getFontMetrics().stringWidth(text);
		
		g.drawString(text, (int) (600 - width) / 2, 400);
		
		font = new Font("Comic Sans MS", Font.PLAIN, 36);
		g.setFont(font);
		g.setColor(this.isTypingNick ? Color.RED : Color.BLACK);
		
		text = "Nick name: " + this.nickname;
		width = g.getFontMetrics().stringWidth(text);
		
		g.drawString(text, (int) (600 - width) / 2, 450);
		
		g.setColor(this.isTypingNick ? Color.BLACK : Color.RED);
		
		text = "Address: " + this.address;
		width = g.getFontMetrics().stringWidth(text);
		
		g.drawString(text, (int) (600 - width) / 2, 490);
	}
	
	

}
