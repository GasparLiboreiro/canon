package modelo;

public class Vector2 {
	public double x,y;
	public static final Vector2 CERO = new Vector2(0,0);
	
	public Vector2(double x, double y) { this.x=x; this.y=y; }
	
	public Vector2() { x=0; y=0; }
	
	
	public double distancia(Vector2 pos) {
		return Math.sqrt(Math.pow(this.x-pos.x, 2)+Math.pow(this.y-pos.y, 2));
	}
	public void set(double x, double y) {
		this.x=x;
		this.y=y;
	}
	public void mover(double derecha, double arriba) {
		x+=derecha;
		y+=arriba;
	}
	public Vector2 clone() {
		return new Vector2(x,y);
	}
	public String toString() {
		return "("+x+";"+y+")";
	}
}
