package modelo;

public class Vector3 {
	public double x,y,z;
	public static final Vector3 CERO = new Vector3(0,0,0);
	public int n=-1;
	public Vector3(double x, double y, double z) { this.x=x; this.y=y; this.z=z;}
	
	public Vector3() { x=0; y=0; z=0;}
	
	
	public double distancia(Vector3 pos) {
		return Math.sqrt(Math.pow(this.x-pos.x, 2)+Math.pow(this.y-pos.y, 2)+Math.pow(this.z-pos.z, 2));
	}
	public void set(Vector3 pos) {
		this.x=pos.x;
		this.y=pos.y;
		this.z=pos.z;
	}
	public void sumar(Vector3 pos) {
		this.x+=pos.x;
		this.y+=pos.y;
		this.z+=pos.z;
	}
	public void set(double x, double y, double z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
	public void sumar(double x, double y, double z) {
		this.x+=x;
		this.y+=y;
		this.z+=z;
	}
	public Vector3 clone() {
		Vector3 v = new Vector3(x,y,z);
		v.n=n;
		return v;
	}
	public String toString() {
		return "("+x+";"+y+";"+z+")";
	}
}
