package support.algorithm;

public class GaussElimination {

	public static double[][] eliminate(double[][] matrix) {
		// Check validity
		if (matrix.length == 0) {
			throw new IllegalArgumentException("array can't be of length 0");
		}
		for (int i = 1; i < matrix.length; ++i) {
			if (matrix[i].length != matrix[i - 1].length) {
				throw new IllegalArgumentException("all rows must have the same length");
			}
		}

		// Copy array (non-mutation method)
		double[][] result = new double[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; ++i) {
			for (int j = 0; j < matrix[0].length; ++j) {
				result[i][j] = matrix[i][j];
			}
		}

		// Eliminate
		for (int row = 0; row < Math.min(result.length, result[0].length - 1); ++row) {
			normalize(result, row);
			subtract(result, row);
		}

		return result;
	}

	private static void normalize(double[][] target, int row) {
		double divisor = target[row][row];
		for (int col = row; col < target[row].length; ++col) {
			target[row][col] /= divisor;
		}
	}

	private static void subtract(double[][] target, int row) {
		for (int otherRow = 0; otherRow < target.length; ++otherRow) {
			if (otherRow != row) {
				double multiple = target[otherRow][row];
				for (int col = row; col < target[otherRow].length; ++col) {
					target[otherRow][col] -= multiple * target[row][col];
				}
			}
		}
	}

}
