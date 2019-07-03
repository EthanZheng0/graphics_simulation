package support.algorithm;

public class Geometry {
	
	public static double getEuclideanDistance(double x1, double x2, double y1, double y2) {
		double deltaX = x1 - x2;
		double deltaY = y1 - y2;
		return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	}
	
}
