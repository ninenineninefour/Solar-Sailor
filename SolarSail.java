
public class SolarSail extends DynamicBody {
	// Surface area of the sail perpendicular to the direction it is facing, in m^2
	private double sailArea;
	// Percentage of photons reflected; unitless, ranges from 0 to 1
	private double reflectiveness;
	private boolean doubleSided = false;
	private Vec3 orient = new Vec3(1, 0, 0);
	
	public SolarSail() {
		super();
		sailArea = 1;
		reflectiveness = 1;
	}
	public SolarSail(double m, double x, double y, double z, double vx, double vy, double vz, double sailArea, double reflectiveness, boolean doubleSided) {
		super(m, x, y, z, vx, vy,  vz, 0);
		this.sailArea = sailArea;
		this.reflectiveness = Math.min(1, Math.max(0, reflectiveness));
		this.doubleSided = doubleSided;
	}
	
	public void pointInDirection(double theta, double phi) {
		double cosPhi = Math.cos(phi);
		orient = new Vec3(Math.cos(theta)*cosPhi, Math.sin(theta)*cosPhi, Math.sin(phi));
	}
	public void pointInDirection(Vec3 vec) {
		orient = vec.clone().normalize();
	}
	
	public void pointTowards(Body b) {
		pointInDirection(Vec3.sub(b.pos(), pos()));
	}
	
	public void reflect(Body b, double dt) {
		if(this == b || b.lightPower() <= 0)
			return;
		
		Vec3 inc = Vec3.sub(b.pos(), pos());
		double dotProd = Vec3.dotProduct(inc, orient);
		
		if(!doubleSided && dotProd > 0)
			return;
		
		double r = inc.length();
		double coef = -(1 + reflectiveness)*dt*b.lightPower()*sailArea*dotProd;
		coef /= 4*Math.PI*Const.C*r*r*r*m();
		
		vels().add(Vec3.mult(orient, coef));
	}
	
	public void gravitate(Body b, double dt) {
		super.gravitate(b, dt);
		reflect(b, dt);
	}
}
