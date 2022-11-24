package modelo;

public class Camara {
	private Vector3  pos,  inc;
	
	private double t=0;
	
	public Camara(Vector3 pos, Vector3 inc) {
		this.pos=pos;
		this.inc=inc;
	}
	
	public void mover(double x, double y, double z) {
		pos.x+=x;
		pos.y+=y;
		pos.z+=z;
	}
	public void girar(double x, double y, double z) {
		inc.x=x;
		inc.y=y;
		inc.z=z;
	}
	public Vector3 getPos() {
		return pos;
	}
	public Vector3 getInc() {
		return inc;
	}
	
	public void update(double dt) {
		
		t+=dt;
		inc.y=-t/2+0.5;
		pos.x=Math.cos((t/2)*Math.PI)*7;
		pos.z=Math.sin((t/2)*Math.PI)*7;
		
	}
	
}
