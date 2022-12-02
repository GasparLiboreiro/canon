package vista;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import modelo.Camara;
import modelo.Entity;
import modelo.Modelo;
import modelo.Util;
import modelo.Vector2;
import modelo.Vector3;

public class Renderer {
	private static double alfa, f, n;
	
	public static void setAlfa(double alfa) { Renderer.alfa=alfa; }
	public static void setF(double f) { Renderer.f=f; }
	public static void setN(double n) { Renderer.n=n; }
	
	public static void renderEscena(Camara camara, ArrayList<Entity> escena, Graphics g, double h, double w) {
		
		double[][] normalizarCam = Util.multiplicarMatrices(
				Util.rotationMatrix(new Vector3(-camara.getInc().x, -camara.getInc().y, -camara.getInc().z)), 
				Util.translationMatrix(new Vector3(-camara.getPos().x, -camara.getPos().y, -camara.getPos().z)));
		
		for(int x=0; x<escena.size(); x++) {
			Modelo modelo = escena.get(x).getRenderModel();
			
			//Util.printMatrix(modelo.transformacion);
			
			
			double[][] transformacionFinal = Util.multiplicarMatrices(
					viewingTransform(), 
					Util.multiplicarMatrices(
							normalizarCam, 
							modelo.transformacion));
			/*
			modelo.vertices.forEach((v)->{
				double[][] ret = Util.multiplicarMatrices(modelo.transformacion, new double[][]{{v.x}, {v.y}, {v.z}, {1}});
				v.x=ret[0][0];
				v.y=ret[1][0];
				v.z=ret[2][0];
				System.out.println("transformacion");
			});
			
			modelo.vertices.forEach((v)->{
				double[][] ret = Util.multiplicarMatrices(normalizarCam, new double[][]{{v.x}, {v.y}, {v.z}, {1}});
				v.x=ret[0][0];
				v.y=ret[1][0];
				v.z=ret[2][0];
				System.out.println("normalizacion");
			});
			
			modelo.vertices.forEach((v)->{
				double[][] ret = Util.multiplicarMatrices(Renderer.viewingTransform(), new double[][]{{v.x}, {v.y}, {v.z}, {1}});
				v.x=ret[0][0]/ret[3][0];
				v.y=ret[1][0]/ret[3][0];
				v.z=ret[2][0]/ret[3][0];
				System.out.println("view");
			});*/

			modelo.vertices.forEach((v)->{
				double[][] ret = Util.multiplicarMatrices(transformacionFinal, new double[][]{{v.x}, {v.y}, {v.z}, {1}});
				v.x=ret[0][0]/ret[3][0];
				v.y=ret[1][0]/ret[3][0];
				v.z=ret[2][0]/ret[3][0];
			});
			
			modelo.triangulos.forEach((t)->{
				dibujarTriangulo(new Vector2[] {
						new Vector2(modelo.vertices.get(t[0]).x, modelo.vertices.get(t[0]).y),
						new Vector2(modelo.vertices.get(t[1]).x, modelo.vertices.get(t[1]).y),
						new Vector2(modelo.vertices.get(t[2]).x, modelo.vertices.get(t[2]).y)}, g, w, h);
			});
			
		}
	}
	
	private static void dibujarTriangulo(Vector2[] vertices, Graphics g, double h, double w) {
		g.drawLine((int)(vertices[0].x*(h/2)+(w/2)), (int)(vertices[0].y*(h/2)+(h/2)), (int)(vertices[1].x*(h/2)+(w/2)), (int)(vertices[1].y*(h/2)+(h/2)));
		g.drawLine((int)(vertices[1].x*(h/2)+(w/2)), (int)(vertices[1].y*(h/2)+(h/2)), (int)(vertices[2].x*(h/2)+(w/2)), (int)(vertices[2].y*(h/2)+(h/2)));
		g.drawLine((int)(vertices[2].x*(h/2)+(w/2)), (int)(vertices[2].y*(h/2)+(h/2)), (int)(vertices[0].x*(h/2)+(w/2)), (int)(vertices[0].y*(h/2)+(h/2)));
	}
	public static double[][] viewingTransform(){
		return new double[][]{			//	la magica y poderosa
			{ Math.atan(alfa/2), 0, 0, 0},// ★     ★      ☆
			{ 0, Math.atan(alfa/2), 0, 0},//     ☆   ★
			{ 0,    0, 	  (f+n)/(f-n),-1},
			{ 0,    0,  (2*f*n)/(f-n), 0}
		};
	}
}


/*


public static void renderEscena(Camara camara, ArrayList<Entity> escena, Graphics g, double h, double w) {
		
		double[][] normalizarCam = Util.multiplicarMatrices(
				Util.rotationMatrix(new Vector3(-camara.getInc().x, -camara.getInc().y, -camara.getInc().z)), 
				Util.translationMatrix(new Vector3(-camara.getPos().x, -camara.getPos().y, -camara.getPos().z)));
		
		for(int x=0; x<escena.size(); x++) {
			Modelo modelo = escena.get(x).getRenderModel();
			
			modelo.vertices.forEach((v)->{
				System.out.println(v.toString());
			});
			
			double[][] transformacionFinal = Util.multiplicarMatrices(
					viewingTransform(), 
					Util.multiplicarMatrices(
							normalizarCam, 
							modelo.transformacion));
			
			modelo.vertices.forEach((v)->{
				double[][] ret = Util.multiplicarMatrices(transformacionFinal, new double[][]{{v.x}, {v.y}, {v.z}, {1}});
				v.x=ret[0][0]/ret[3][0];
				v.y=ret[1][0]/ret[3][0];
				v.z=ret[2][0]/ret[3][0];
				g.drawString(""+v.n, (int)(v.x*(w/2)+(w/2)), (int)(v.y*(h/2)+(h/2)));
			});
			
			modelo.triangulos.forEach((t)->{
				dibujarTriangulo(new Vector2[] {
						new Vector2(modelo.vertices.get(t[0]).x, modelo.vertices.get(t[0]).y),
						new Vector2(modelo.vertices.get(t[1]).x, modelo.vertices.get(t[1]).y),
						new Vector2(modelo.vertices.get(t[2]).x, modelo.vertices.get(t[2]).y)}, g, w, h);
			});
			
		}
	}


*/