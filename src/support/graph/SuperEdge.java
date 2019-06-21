package support.graph;

import java.util.Vector;

public class SuperEdge {

	private final Vector<Vertex> vertices;
	private final Vector<Edge> edges;
	private final double length;
	private final SuperVertex start;
	private final SuperVertex end;

	public SuperEdge(Vector<Vertex> vertices, Vector<Edge> edges) {
		this.vertices = vertices;
		this.edges = edges;
		double length = 0;
		for (Edge e : edges) {
			length += e.getLength();
		}
		this.length = length;
		this.start = new SuperVertex(vertices.firstElement());
		this.end = new SuperVertex(vertices.lastElement());
		this.start.addNeighbor(end);
		this.end.addNeighbor(start);
		this.start.addOutEdge(this);
		this.end.addOutEdge(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edges == null) ? 0 : edges.hashCode());
		result = prime * result + ((vertices == null) ? 0 : vertices.hashCode());
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
		SuperEdge other = (SuperEdge) obj;
		if (edges == null) {
			if (other.edges != null)
				return false;
		} else if (!edges.equals(other.edges))
			return false;
		if (vertices == null) {
			if (other.vertices != null)
				return false;
		} else if (!vertices.equals(other.vertices))
			return false;
		return true;
	}

	public Vector<Vertex> getVertices() {
		return vertices;
	}

	public Vector<Edge> getEdges() {
		return edges;
	}

	public double getLength() {
		return length;
	}

	public SuperVertex getStart() {
		return start;
	}

	public SuperVertex getEnd() {
		return end;
	}

	public Vertex getByCoordinates(double x, double y) {
		for (Vertex v : vertices) {
			double deltaX = v.getX() - x;
			double deltaY = v.getY() - y;
			double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
			if (distance < 0.01) {
				return v;
			}
		}
		return null;
	}
	
	public int getIndexByCoordinates(double x, double y) {
		for (int i = 0; i < vertices.size(); ++i) {
			double deltaX = vertices.get(i).getX() - x;
			double deltaY = vertices.get(i).getY() - y;
			double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
			if (distance < 0.01) {
				return i;
			}
		}
		return -1;
	}

	public String toString() {
		return start.toString() + " <-> " + end.toString();
	}
}
