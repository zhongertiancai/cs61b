import java.util.Scanner;
public class NBody{
	public static double readRadius(String s){
		In in = new In(s);
		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		return secondItemInFile;
	}
	public static Planet[] readPlanets(String s){
		In in = new In(s);
		int firstItemInFile = in.readInt();
		Planet[] result = new Planet[firstItemInFile];
		double secondItemInFile = in.readDouble();
		for(int i = 0; i < firstItemInFile; i++){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			result[i] = new Planet(xP,yP,xV,yV,m,img);
		}
		return result;
	}
	public static void main(String[] arg){
		double T = Double.parseDouble(arg[0]);
		double dt = Double.parseDouble(arg[1]);
		String filename = arg[2]; 
		double radius = readRadius(filename);
		StdDraw.setScale(-radius,radius);
		StdDraw.clear();
		Planet[] ball = readPlanets(filename);
		for(int i = 0; i < ball.length ; i++){
			ball[i].draw();
		}
		StdDraw.enableDoubleBuffering();
		double t = 0;
		double[] xForces = new double[ball.length];
		double[] yForces = new double[ball.length];
		for(t = 0; t < T ; t += dt){
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (int i = 0; i < ball.length; i++){
				xForces[i] = ball[i].calcNetForceExertedByX(ball);
				yForces[i] = ball[i].calcNetForceExertedByY(ball);
				ball[i].update(dt, xForces[i] , yForces[i]);
				ball[i].draw();
				
			}
			StdDraw.show();
			StdDraw.pause(10);
			
		}

		StdOut.printf("%d\n", ball.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < ball.length; i++) {
  		  	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            xxPos, yyPos, xxVel,
            yyVel, mass, imgFileName);   
		}
	}
}