package support.algorithm;

import support.graph.Vertex;

public class CircumcircleCenter {

	public static Vertex calculateCircleCenter2D(Vertex v1, Vertex v2, Vertex v3) {
		double[][] matrix = new double[2][3];
		double x1 = v1.getX();
		double y1 = v1.getY();
		double x2 = v2.getX();
		double y2 = v2.getY();
		double x3 = v3.getX();
		double y3 = v3.getY();
		matrix[0][0] = 2 * (x1 - x2);
		matrix[0][1] = 2 * (y1 - y2);
		matrix[1][0] = 2 * (x2 - x3);
		matrix[1][1] = 2 * (y2 - y3);
		matrix[0][2] = x1 * x1 - x2 * x2 + y1 * y1 - y2 * y2;
		matrix[1][2] = x2 * x2 - x3 * x3 + y2 * y2 - y3 * y3;
		double[][] result = GaussElimination.eliminate(matrix);
		return new Vertex(result[0][2], result[1][2]);
	}

}
