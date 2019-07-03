package support.algorithm;

public class Geometry {
	
	public static double getEuclideanDistance(double x1, double x2, double y1, double y2) {
		double deltaX = x1 - x2;
		double deltaY = y1 - y2;
		return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	}
	
	public static double[] circumcircleCenter(double x1, double x2, double x3, double y1, double y2, double y3) {
		double[][] matrix = new double[2][3];
		matrix[0][0] = 2 * (x1 - x2);
		matrix[0][1] = 2 * (y1 - y2);
		matrix[1][0] = 2 * (x2 - x3);
		matrix[1][1] = 2 * (y2 - y3);
		matrix[0][2] = x1 * x1 - x2 * x2 + y1 * y1 - y2 * y2;
		matrix[1][2] = x2 * x2 - x3 * x3 + y2 * y2 - y3 * y3;
		double[][] result = GaussElimination.eliminate(matrix);
		return new double[] {result[0][2], result[1][2]};
	}
	
}
