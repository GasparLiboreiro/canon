package modelo;

public class Util {
	public static double[][] multiplicarMatrices(double[][] m1, double[][] m2){
		if(m1==null || m2==null || m1.length==0 || m2.length==0) throw new Error("invalid matrix for multiplication");
		int alt1=m1.length, anch1=m1[0].length, alt2=m2.length, anch2=m2[0].length;
		
		if(alt2 != anch1) throw new Error("impossible matrix multiplication");
		
		
		double[][] out = new double[alt1][anch2]; //[rows][columns]
		
		for(int x=0; x<alt1; x++)
			for(int y=0; y<anch2; y++)
				for(int k=0; k<alt2; k++)
					out[x][y] += m1[x][k] * m2[k][y];
		
		return out;
	}
	
	public static Vector3 multiplicarVectorYMatriz(double[][] m, Vector3 v) {
		double[][] res = multiplicarMatrices(m,new double[][] {{v.x},{v.y},{v.z},{1}});
		return new Vector3(res[0][0], res[1][0], res[2][0]);
	}
	
	public static double[][] translationMatrix(Vector3 pos){
		return new double[][] {
			{1, 0, 0, pos.x},
			{0, 1, 0, pos.y},
			{0, 0, 1, pos.z},
			{0, 0, 0, 1}
		};
	}
	
	public static double[][] rotationMatrixInX(double inc){
		return new double[][]{
			{Math.cos(inc*Math.PI), -Math.sin(inc*Math.PI), 0, 0},
			{Math.sin(inc*Math.PI), Math.cos(inc*Math.PI), 0, 0},	//m3 rotacion en z
			{0, 0, 1, 0},
			{0, 0, 0, 1}
		};
	}
	
	public static double[][] rotationMatrixInY(double inc){
		return new double[][]{
			{Math.cos(inc*Math.PI), 0, Math.sin(inc*Math.PI), 0},
			{0, 1, 0, 0},												//m2 rotacion en y
			{-Math.sin(inc*Math.PI), 0, Math.cos(inc*Math.PI), 0},
			{0, 0, 0, 1}
		};
	}
	
	public static double[][] rotationMatrixInZ(double inc){
		return new double[][]{
			{1, 0, 0, 0},
			{0, Math.cos(inc*Math.PI), -Math.sin(inc*Math.PI), 0},	//rotacion en x
			{0, Math.sin(inc*Math.PI), Math.cos(inc*Math.PI), 0},
			{0, 0, 0, 1}
		};
	}
	
	public static double[][] rotationMatrix(Vector3 inc){
		return Util.multiplicarMatrices(rotationMatrixInZ(inc.z), Util.multiplicarMatrices(rotationMatrixInY(inc.y), rotationMatrixInX(inc.x)));
	}
	
	public static void printMatrix(double[][] m) {
		for(int x=0; x<m.length; x++) {
			for(int y=0; y<m[x].length; y++) 
				System.out.print("|"+m[x][y]);
			System.out.print("|\n");
		}
	}
}
/*



double[][] transformacion = Util.multiplicarMatrices(new double[][]{
			{1, 0, 0, 0},
			{0, 1, 0, 0},
			{0, 0, 1, 0},
			{pos.x, pos.y, pos.z, 1}
		},Util.multiplicarMatrices(new double[][]{
			{Math.cos(inc.x*Math.PI), -Math.sin(inc.x*Math.PI), 0, 0},
			{Math.sin(inc.x*Math.PI), Math.cos(inc.x*Math.PI), 0, 0},	//m3 rotacion en z
			{0, 0, 1, 0},
			{0, 0, 0, 1}
		}, Util.multiplicarMatrices(new double[][]{
			{Math.cos(inc.x*Math.PI), 0, Math.sin(inc.x*Math.PI), 0},
			{0, 1, 0, 0},												//m2 rotacion en y
			{-Math.sin(inc.x*Math.PI), 0, Math.cos(inc.x*Math.PI), 0},
			{0, 0, 0, 1}
		}, new double[][]{
			{1, 0, 0, 0},
			{0, Math.cos(inc.x*Math.PI), -Math.sin(inc.x*Math.PI), 0},	//m1 rotacion en x
			{0, Math.sin(inc.x*Math.PI), Math.cos(inc.x*Math.PI), 0},
			{0, 0, 0, 1}
		})));





*/