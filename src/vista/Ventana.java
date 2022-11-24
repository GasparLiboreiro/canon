package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Ventana extends JFrame implements KeyListener{
	
	private E3DP instance;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	public Ventana() {
		this.setBounds(100, 100, 500, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.addKeyListener(this);
		instance = new E3DP();
		this.getContentPane().add(instance);
		instance.start();
	}


	@Override
	public void keyTyped(KeyEvent e) {
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
