package modelo;

import java.util.ArrayList;

public class Modelo {
	public ArrayList<Vector3> vertices;
	public ArrayList<int[]> triangulos;
	public double[][] transformacion=null;
	public Modelo(ArrayList<Vector3> vertices, ArrayList<int[]> triangulos) {
		this.vertices=vertices;
		this.triangulos=triangulos;
	}
	
	public Modelo clone() {
		ArrayList<Vector3> v = new ArrayList<Vector3>();
		ArrayList<int[]> t = new ArrayList<int[]>();
		
		vertices.forEach((ver)->{  v.add(ver.clone());  });
		
		triangulos.forEach((tr)->{  t.add(new int[] {tr[0], tr[1], tr[2]});  });
		Modelo out = new Modelo(v, t);
		if(this.transformacion!=null)
			out.transformacion=this.transformacion.clone();
		return out;
	}
	
}
