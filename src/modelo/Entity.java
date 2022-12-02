package modelo;

import java.util.ArrayList;

public class Entity {
	public Vector3  pos,  inc, tam;
	public Vector3 vpos, vinc;
	public Vector3 apos, ainc;
	
	public Modelo modelo;
	
	public Entity(Modelo modelo, Vector3 pos) 								{OnCreate(modelo, pos, new Vector3(), new Vector3());}
	
	public Entity(Modelo modelo, Vector3 pos, Vector3 inc) 					{OnCreate(modelo, pos, inc, new Vector3());}

	public Entity(Modelo modelo, Vector3 pos, Vector3 inc, Vector3 tam) 	{OnCreate(modelo, pos, inc, tam);}
	
	
	private void OnCreate(Modelo modelo, Vector3 pos, Vector3 inc, Vector3 tam) {
		this.modelo=modelo;
		this.pos=pos;
		this.inc=inc;
		this.tam=tam;
		vpos = new Vector3();
		vinc = new Vector3();
		apos = new Vector3();
		ainc = new Vector3();
	}
	
	public Modelo getRenderModel() {
		Modelo out = this.modelo.clone();
		
		double[][] transformacion = Util.multiplicarMatrices(Util.translationMatrix(pos),Util.rotationMatrix(inc));
		
		out.transformacion=transformacion;
		
		return out;
	}
}


/*
    double[][] vector = Util.multiplicarMatrices(transformacion, new double[][]{{v.x},{v.y},{v.z},{1}});
	v.x=vector[0][0];
	v.y=vector[1][0];
	v.z=vector[2][0];
*/
