
public class Vec3 {
	public static final int DIMENSIONS = 3;
	public static final int X = 0;
	public static final int Y = 1;
	public static final int Z = 2;
	private double[] v;
	
	public Vec3() {
		v = new double[DIMENSIONS];
	}
	public Vec3(double x, double y, double z) {
		v = new double[] {x, y, z};
	}
	public Vec3(double[] v) {
		if(v.length == DIMENSIONS) {
			this.v = v.clone();
		} else {
			this.v = new double[DIMENSIONS];
		}
	}
	
	public double x() {
		return v[X];
	}
	public double y() {
		return v[Y];
	}
	public double z() {
		return v[Z];
	}
	public double get(int dimension) {
		return v[dimension];
	}
	
	public void setX(double x) {
		v[X] = x;
	}
	public void setY(double y) {
		v[Y] = y;
	}
	public void setZ(double z) {
		v[Z] = z;
	}
	public void set(double value, int dimension) {
		v[dimension] = value;
	}
	
	public double theta() {
		return Math.atan2(y(), x());
	}
	public double phi() {
		return Math.atan2(Math.sqrt(y()*y() + x()*x()), z());
	}
	
	public double lengthSquared() {
		double r = 0;
		for(int i = 0; i < DIMENSIONS; i++) {
			r += v[i]*v[i];
		}
		return r;
	}
	public double length() {
		return Math.sqrt(lengthSquared());
	}
	
	public Vec3 mult(double d) {
		for(int i = 0; i < DIMENSIONS; i++) {
			v[i] *= d;
		}
		return this;
	}
	public Vec3 mult(Mat3 mat) {
		v = mult(this, mat).v;
		return this;
	}
	public Vec3 add(Vec3 vec) {
		for(int i = 0; i < DIMENSIONS; i++) {
			v[i] += vec.v[i];
		}
		return this;
	}
	public Vec3 sub(Vec3 vec) {
		for(int i = 0; i < DIMENSIONS; i++) {
			v[i] -= vec.v[i];
		}
		return this;
	}
	public Vec3 invert() {
		for(int i = 0; i < DIMENSIONS; i++) {
			v[i] = -v[i];
		}
		return this;
	}
	public Vec3 normalize() {
		mult(1/length());
		return this;
	}
	
	public static double dotProduct(Vec3 a, Vec3 b) {
		double prod = 0;
		for(int i = 0; i < DIMENSIONS; i++) {
			prod += a.v[i]*b.v[i];
		}
		return prod;
	}
	public static Vec3 mult(Vec3 vec, double d) {
		return vec.clone().mult(d);
	}
	public static Vec3 mult(Vec3 vec, Mat3 mat) {
		Vec3 ans = new Vec3();
		for(int i = 0; i < DIMENSIONS; i++) {
			for(int j = 0; j < DIMENSIONS; j++) {
				ans.v[i] += vec.v[j]*mat.get(i, j);
			}
		}
		
		return ans;
	}
	public static Vec3 add(Vec3 a, Vec3 b) {
		return a.clone().add(b);
	}
	public static Vec3 sub(Vec3 a, Vec3 b) {
		return a.clone().sub(b);
	}
	public static Vec3 inverse(Vec3 vec) {
		return vec.clone().invert();
	}
	public static Vec3 normalized(Vec3 vec) {
		return vec.clone().normalize();
	}
	
	public Vec3 clone() {
		return new Vec3(x(), y(), z());
	}
}
