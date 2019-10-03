package visualization.rotate;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class RotateApp {

	public static void main(String[] args) {
		double[][] matrix = {	
				{0, 0, 0}, 
				{0, 0, 1}, 
				{0, 1, 0}, 
				{0, 1, 1}, 
				{1, 0, 0}, 
				{1, 0, 1}, 
				{1, 1, 0},
				{1, 1, 1},
				{0, 0, 0.5},
				{1, 1, 0.5},
				{0, 0, 0.25},
				{1, 1, 0.75},
				{0, 0, 0.75},
				{1, 1, 0.25},
		};
		
		int[][] lines = {
				{0, 1}, 
				{0, 2}, 
				{0, 4}, 
				{1, 3}, 
				{1, 5}, 
				{2, 3}, 
				{2, 6}, 
				{3, 7}, 
				{4, 5}, 
				{4, 6}, 
				{5, 7}, 
				{6, 7},
				{0, 7},
				{1, 6},
				{8, 9},
				{10, 11},
				{12, 13}
		};
		
		StdDraw.setXscale(-2, 2);
		StdDraw.setYscale(-2, 2);
		StdDraw.setPenColor(Color.RED);
		StdDraw.setPenRadius(0.01);
		
		int angle = 0;
		while(true) {
			double[][] projectionMatrix = {	
					{0, 1},
					{Math.sin(Math.PI * angle / 180), 0},
					{Math.cos(Math.PI * angle / 180), 0}
			};
			
			double[][] projected = matrixMultiplication(matrix, projectionMatrix);
			for(int i = 0; i < lines.length; ++i) {
				int p1 = lines[i][0];
				int p2 = lines[i][1];
				StdDraw.line(projected[p1][0], projected[p1][1], projected[p2][0], projected[p2][1]);
			}
			StdDraw.show(20);
			StdDraw.clear();
			if(angle < 360) {
				angle++;
			} else {
				angle = 0;
			}
		}
	}
	
	public static double[][] matrixMultiplication(double[][] a, double[][] b) {
		if(a[0].length != b.length) {
			throw new IllegalArgumentException();
		}
		double[][] result = new double[a.length][b[0].length];
		for(int i = 0; i < result.length; ++i) {
			for(int j = 0; j < result[i].length; ++j) {
				for(int k = 0; k < b.length; ++k) {
					result[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		return result;
	}
}