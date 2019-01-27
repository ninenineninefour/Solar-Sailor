
public class KeplerBody implements Body {
	private static final int INVERSE_KEPLER_EQ_ITERATIONS = 10;
	
	private double m;
	private double lightPower;
	private Vec3 pos;
	
	private Body parent;
	private double e;
	private double a;
	private double epoch;
	private double orbitTimer;
	private double period;
	private double meanMotion;
	
	private Mat3 rotMatrix;
	
	public KeplerBody(double m, double lightPower, Body parent, double e, double a, double inc, double aNodeAng, double periAng, double epoch) {
		this.m = m;
		this.lightPower = lightPower;
		pos = new Vec3();
		this.parent = parent;
		this.e = e;
		this.a = a;
		this.epoch = epoch;
		orbitTimer = 0;
		meanMotion = Math.sqrt(a*a*a/parent.mu());
		period = 2*Math.PI*meanMotion;
		
		rotMatrix = Mat3.rotZ(-aNodeAng);
		rotMatrix.mult(Mat3.rotX(inc));
		rotMatrix.mult(Mat3.rotZ(aNodeAng + periAng));
	}
	
	public double m() {
		return m;
	}
	
	public double mu() {
		return m*Const.G;
	}
	
	public double lightPower() {
		return lightPower;
	}
	
	public Vec3 pos() {
		return pos;
	}
	
	public void gravitate(Body b, double dt) {}

	public void move(double dt) {
		orbitTimer = (orbitTimer + dt)%period;
		
		double meanAnomaly = (orbitTimer + epoch)*meanMotion;
		double eccAnomaly = inverseKeplerEquation(meanAnomaly);
		double trueAnomaly = trueAnomaly(eccAnomaly);
		double radius = radius(trueAnomaly);
		
		pos = new Vec3(radius*Math.cos(trueAnomaly), radius*Math.sin(trueAnomaly), 0);
		pos.mult(rotMatrix);
		pos.add(parent.pos());
	}
	
	private double trueAnomaly(double eccAnomaly) {
		return Math.atan2(Math.cos(eccAnomaly) - e, Math.sqrt(1 - e*e)*Math.sin(eccAnomaly));
	}
	
	private double radius(double trueAnomaly) {
		return a*(1 - e*e)/(1 + e*Math.cos(trueAnomaly));
	}
	
	private double inverseKeplerEquation(double meanAnomaly) {
		double eccAnomaly = meanAnomaly;
		for(int i = 0; i < INVERSE_KEPLER_EQ_ITERATIONS; i++) {
			eccAnomaly = meanAnomaly + e*Math.sin(eccAnomaly);
		}
		return eccAnomaly;
	}
}
