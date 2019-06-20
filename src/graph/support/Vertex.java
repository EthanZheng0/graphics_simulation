package graph.support;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class Vertex {

	private final double x;
	private final double y;

	public Vertex(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void draw() {
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.02);
		StdDraw.point(x, y);
	}

	public void draw(Color c) {
		StdDraw.setPenColor(c);
		StdDraw.setPenRadius(0.02);
		StdDraw.point(x, y);
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}

}
