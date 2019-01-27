
public interface Body {
	public double m();
	public double mu();
	public double lightPower();
	public Vec3 pos();
	public void gravitate(Body b, double dt);
	public void move(double dt);
}
