
public class StaticBody implements Body {
	private double m;
	private double lightPower;
	private Vec3 pos;
	private Vec3 vels;
	
	public StaticBody(double m, double lightPower, Vec3 pos, Vec3 vels) {
		this.m = m;
		this.lightPower = lightPower;
		this.pos = pos;
		this.vels = vels;
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
	
	public void gravitate(Body b, double dt) {
		return;
	}
	
	public void move(double dt) {
		pos.add(Vec3.mult(vels, dt));
	}

}
