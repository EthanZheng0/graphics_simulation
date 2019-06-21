package support.graph;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class Edge {

	private final Vertex start;
	private final Vertex end;
	private final double length;

	public Edge(Vertex start, Vertex end) {
		this.start = start;
		this.end = end;
		double deltaX = end.getX() - start.getX();
		double deltaY = end.getY() - start.getY();
		this.length = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
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
		Edge other = (Edge) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

	public Vertex getStart() {
		return start;
	}

	public Vertex getEnd() {
		return end;
	}

	public double getLength() {
		return length;
	}

	public void draw() {
		StdDraw.setPenColor(Color.BLUE);
		StdDraw.setPenRadius(0.006);
		StdDraw.line(start.getX(), start.getY(), end.getX(), end.getY());
	}

	public void draw(Color c) {
		StdDraw.setPenColor(c);
		StdDraw.setPenRadius(0.006);
		StdDraw.line(start.getX(), start.getY(), end.getX(), end.getY());
	}

	public String toString() {
		return start.toString() + " <-> " + end.toString();
	}
}
