public class NBody {

	public static final String starfield = "./images/starfield.jpg";

	/** Reads the second line in a file which coresponds to the radius */
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		in.readLine();
		return in.readDouble();
	}

	public static Planet fetchPlanet(In in) {
		double xP, yP, xV, yV, m;
		String name;
		xP = in.readDouble();
		yP = in.readDouble();
		xV = in.readDouble();
		yV = in.readDouble();
		m  = in.readDouble();
		name = in.readString();

		return new Planet(xP, yP, xV, yV, m, name);
	}

	/** Returns an array of existing planets in the universe */
	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int numPlanets = in.readInt();
		Planet[] planets = new Planet[numPlanets];
		double r = in.readDouble();
		
		for(int i = 0; i < numPlanets; i++) {
			planets[i] = fetchPlanet(in);
		}

		return planets;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String fileName = args[2];
		double radius = NBody.readRadius(fileName);

		Planet[] planets = NBody.readPlanets(fileName);
		int N = planets.length;
		double[] xForces = new double[N];
		double[] yForces = new double[N];

		StdDraw.enableDoubleBuffering();

		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, starfield);
		StdDraw.show();

		for(double time = 0; time < T; time += dt) {
	
			for(int i = 0; i < N; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
			}

	
			for(int i = 0; i < N; i++) {
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

	
			for(int i = 0; i < N; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, starfield);

			for(Planet i: planets) {
				i.draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}