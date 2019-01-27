
public class Mat3 {
	public static final int DIMENSIONS = 3;
	public static final int X = 0;
	public static final int Y = 1;
	public static final int Z = 2;
	private double[][] m;
	
	public Mat3() {
		m = new double[DIMENSIONS][];
		for(int i = 0; i < DIMENSIONS; i++) {
			m[i] = new double[DIMENSIONS];
		}
	}
	
	public double get(int r, int c) {
		return m[r][c];
	}
	
	public void set(int r, int c, double value) {
		m[r][c] = value;
	}
	
	public Mat3 mult(Mat3 mat) {
		m = mult(this, mat).m;
		return this;
	}
	
	public static Mat3 rotX(double angle) {
		Mat3 rotX = new Mat3();
		rotX.m[Y][Y] = Math.cos(angle);
		rotX.m[Z][Y] = Math.sin(angle);
		rotX.m[Y][Z] = -rotX.m[Z][Y];
		rotX.m[Z][Z] = rotX.m[Y][Y];
		rotX.m[X][X] = 1.0;
		return rotX;
	}
	
	public static Mat3 rotY(double angle) {
		Mat3 rotY = new Mat3();
		rotY.m[X][X] = Math.cos(angle);
		rotY.m[X][Z] = Math.sin(angle);
		rotY.m[Z][X] = -rotY.m[X][Z];
		rotY.m[Z][Z] = rotY.m[X][X];
		rotY.m[Y][Y] = 1.0;
		return rotY;
	}
	
	public static Mat3 rotZ(double angle) {
		Mat3 rotZ = new Mat3();
		rotZ.m[X][X] = Math.cos(angle);
		rotZ.m[Y][X] = Math.sin(angle);
		rotZ.m[X][Y] = -rotZ.m[Y][X];
		rotZ.m[Y][Y] = rotZ.m[X][X];
		rotZ.m[Z][Z] = 1.0;
		return rotZ;
	}
	
	public static Mat3 mult(Mat3 m1, Mat3 m2) {
		Mat3 ans = new Mat3();
		for(int r = 0; r < DIMENSIONS; r++) {
			for(int c = 0; c < DIMENSIONS; c++) {
				for(int i = 0; i < DIMENSIONS; i++) {
					ans.m[r][c] += m1.m[r][i]*m2.m[i][c];
				}
			}
		}
		return ans;
	}
}
