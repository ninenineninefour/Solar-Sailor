
public class DynamicBody implements Body {
	// Mass of the object in kg
	private double m;
	// Position of the object in meters
	private Vec3 pos;
	// Velocity of the object along each axis in m/s
	private Vec3 vels;
	// Power of the light emitted by the object in watts
	private double lightPower;
	
	public DynamicBody() {
		m = 1;
		pos = new Vec3();
		vels = new Vec3();
		lightPower = 0;
	}
	public DynamicBody(double m, double x, double y, double z, double vx, double vy, double vz, double lightPower) {
		this.m = m;
		this.pos = new Vec3(x, y, z);
		this.vels = new Vec3(vx, vy, vz);
		this.lightPower = lightPower;
	}
	
	public double m() {
		return m;
	}
	public double mu() {
		return m*Const.G;
	}
	public Vec3 pos() {
		return pos;
	}
	public Vec3 vels() {
		return vels;
	}
	public double lightPower() {
		return lightPower;
	}
	
	public void move(double dt) {
		pos.add(Vec3.mult(vels, dt));
	}
	
	public void gravitate(Body b, double dt) {
		if(this == b)
			return;
		
		Vec3 disp = Vec3.sub(b.pos(), pos);
		double r = disp.length();
		vels.add(Vec3.mult(vels, dt*b.mu()/(r*r*r)));
	}
}
