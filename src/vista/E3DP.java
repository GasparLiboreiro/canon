package vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.function.Function;

import javax.swing.JPanel;

import modelo.Camara;
import modelo.Entity;
import modelo.GameLoop;
import modelo.ModeloFactory;
import modelo.Vector3;

public class E3DP extends JPanel{
	private ArrayList<Entity> escena = new ArrayList<Entity>();
	private GameLoop gameLoop;
	private Camara camara;
	
	public E3DP() {
		//inicio parametros de renderizado
		Renderer.setAlfa(120*(Math.PI/180));
		Renderer.setN(0.5);
		Renderer.setF(10);
		
		//inicio gameloop
		Function<Void, Void> repaint = a -> {this.repaint();return null;};
		gameLoop = new GameLoop(repaint, (dt)->this.update(dt));
		
		//cargo escena
		escena1();
	}
	
	private void escena1() {
		Entity entidadCamara = new Entity(ModeloFactory.sinModelo(), new Vector3(0,0,6), new Vector3(0,0,0));
		camara=new Camara(entidadCamara.pos, entidadCamara.inc);
		
		escena.add(entidadCamara);
		

		escena.add(new Entity(ModeloFactory.cubo(2), new Vector3(0, 0, 0), new Vector3(0, 0,0)));
		escena.add(new Entity(ModeloFactory.cubo(2), new Vector3(0, 3, 0), new Vector3(0, 0,0)));
		escena.add(new Entity(ModeloFactory.cubo(2), new Vector3(0, -3, 0), new Vector3(0, 0,0)));
		escena.add(new Entity(ModeloFactory.cubo(2), new Vector3(3, 0, 0), new Vector3(0, 0,0)));
		escena.add(new Entity(ModeloFactory.cubo(2), new Vector3(-3, 0, 0), new Vector3(0, 0,0)));
		escena.add(new Entity(ModeloFactory.cubo(2), new Vector3(3, 3, 0), new Vector3(0.25, 0, 0)));
		escena.add(new Entity(ModeloFactory.cubo(2), new Vector3(3, -3, 0), new Vector3(0.25, 0,0)));
		escena.add(new Entity(ModeloFactory.cubo(2), new Vector3(-3, 3, 0), new Vector3(0.25, 0,0)));
		escena.add(new Entity(ModeloFactory.cubo(2), new Vector3(-3, -3, 0), new Vector3(0.25, 0,0)));
		entidadCamara.apos.x=1;
		entidadCamara.apos.y=1;
		entidadCamara.apos.z=-1;
		entidadCamara.ainc.z=0.0625*2;
		entidadCamara.ainc.y=0.0625*4;
		entidadCamara.ainc.x=0.125*2;
	}
	
	protected Void update(double dt) {
		for(int x=0; x<escena.size(); x++) {
			Entity e = escena.get(x);
			e.pos.sumar(e.vpos.x*dt, e.vpos.y*dt, e.vpos.z*dt);
			e.inc.sumar(e.vinc.x*dt, e.vinc.y*dt, e.vinc.z*dt);
			e.vpos.sumar(e.apos.x*dt, e.apos.y*dt, e.apos.z*dt);
			e.vinc.sumar(e.ainc.x*dt, e.ainc.y*dt, e.ainc.z*dt);
		}
		return null;
	}
	
	public void paint(Graphics g) {
		g.setColor(new Color(0,100,100));
		for(int x=0; x<this.getWidth(); x++)
			g.drawLine(x, 0, x, this.getHeight());
		g.setColor(new Color(255, 197, 89));
		
		
		
		Renderer.renderEscena(camara,escena,g, this.getWidth(), this.getHeight());
		g.setColor(new Color(230,230,250));
		g.drawString("FPS:"+gameLoop.getAverageFPS(), 0, 10);
	}
	
	
	public void start() {
		gameLoop.startLoop();
	}
	
	public void stop() {
		gameLoop.pauseLoop();
	}

}
