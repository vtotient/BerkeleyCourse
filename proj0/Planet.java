/** Project 0 */
public class Planet {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double G = 6.67e-11;

	/** Instantiates a planet */
	public Planet(double xP, double yP, double xV,
             	  double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/** Makes an indetical copy of planet on instantiation */
	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	/** Calculates the distance between two planets.
		@preconditions 2D cartesian coordinate system */
	public double calcDistance(Planet p) {
		double dx = this.xxPos - p.xxPos;
		double dy = this.yyPos - p.yyPos;

		return Math.sqrt(dx*dx+dy*dy);
	}

	/** Takes in a planet, and returns a double describing 
		the force exerted on this planet by the given planet */
	public double calcForceExertedBy(Planet p) {
		double rsqrd = this.calcDistance(p)*this.calcDistance(p);
		return (G*this.mass*p.mass)/rsqrd;
	}

	/** Calculates force exerted in the x-coor */
	public double calcForceExertedByX(Planet p) {
		double dx = p.xxPos - this.xxPos;
		return calcForceExertedBy(p)*dx/this.calcDistance(p);
	}

	/** Calculates force exerted in the y-coor */
	public double calcForceExertedByY(Planet p) {
		double dy = p.yyPos - this.yyPos;
		return calcForceExertedBy(p)*dy/this.calcDistance(p);
	}

	/** Calc the net force in x-coor */
	public double calcNetForceExertedByX(Planet[] allPlanets) {
		double netForce = 0;
		for(Planet planet : allPlanets) {
			if(!this.equals(planet)) {
				netForce += this.calcForceExertedByX(planet);
			}
		}
		return netForce;
	}

	/** Calc the net force in x-coor */
	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double netForce = 0;
		for(Planet planet : allPlanets) {
			if(!this.equals(planet)) {
				netForce += this.calcForceExertedByY(planet);
			}
		}
		return netForce;
	}

	/** A method that determines how much the forces exerted on the 
		planet will cause that planet to accelerate, and the 
		resulting change in the planet’s velocity and position in a 
		small period of time dt */
	public void update(double dt, double fX, double fY) {
		double aX, aY;

		aX = fX / this.mass; 
		aY = fY / this.mass;

		this.xxVel += dt * aX;
		this.yyVel += dt * aY;

		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	/** Using the StdDraw API draw the planet at the appropriate
		position */
	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, 
						"images/" + this.imgFileName);
	}

}
