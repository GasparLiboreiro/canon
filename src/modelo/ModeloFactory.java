package modelo;

import java.util.ArrayList;

public class ModeloFactory {
	public static Modelo cubo(double tam) {
		ArrayList<Vector3> vertices = new ArrayList<Vector3>();
		ArrayList<int[]> triangulos = new ArrayList<int[]>();
		
		for(int a=0; a<=1; a++)
			for(int b=0; b<=1; b++)
				for(int c=0; c<=1; c++) {Vector3 v = new Vector3(-tam/2+a*tam, -tam/2+b*tam, -tam/2+c*tam);
				v.n=c+b*2+a*4;
					vertices.add(v);}
		
		triangulos.add(new int[] {0,1,2});
		triangulos.add(new int[] {1,2,3});
		triangulos.add(new int[] {4,5,6});
		triangulos.add(new int[] {5,6,7});
		triangulos.add(new int[] {2,3,6});
		triangulos.add(new int[] {3,6,7});
		triangulos.add(new int[] {0,1,4});
		triangulos.add(new int[] {1,4,5});
		triangulos.add(new int[] {0,2,4});
		triangulos.add(new int[] {2,4,6});
		triangulos.add(new int[] {1,3,5});
		triangulos.add(new int[] {3,5,7});
		
		return new Modelo(vertices, triangulos);
	}
	
	public static Modelo esfera(double r, double dalfa) {
		ArrayList<Vector3> vertices = new ArrayList<Vector3>();
		ArrayList<int[]> triangulos = new ArrayList<int[]>();
		Vector3[][] verts = new Vector3[(int)(1/dalfa)][(int)(2/dalfa)];
		for(int y=0; y<Math.PI; y+=dalfa) {
			for(int x=0; x<2*Math.PI; x+=dalfa) {
				verts[x][y] = new Vector3();
			}
		}
		
		return null;
	}
	
	public static Modelo sinModelo() {
		return new Modelo(new ArrayList<Vector3>(), new ArrayList<int[]>());
	}
}
