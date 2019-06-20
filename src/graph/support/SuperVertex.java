package graph.support;

import java.util.HashSet;
import java.util.Set;

public class SuperVertex {
	
	private final Vertex vertex;
	private Set<SuperVertex> neighbors;
	private Set<SuperEdge> outEdges;
	
	public SuperVertex(Vertex vertex) {
		this.vertex = vertex;
		this.neighbors = new HashSet<>();
		this.outEdges = new HashSet<>();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vertex == null) ? 0 : vertex.hashCode());
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
		SuperVertex other = (SuperVertex) obj;
		if (vertex == null) {
			if (other.vertex != null)
				return false;
		} else if (!vertex.equals(other.vertex))
			return false;
		return true;
	}

	public Vertex getVertex() {
		return vertex;
	}
	
	public boolean addNeighbor(SuperVertex sv) {
		return neighbors.add(sv);
	}
	
	public Set<SuperVertex> getNeighbors() {
		return neighbors;
	}
	
	public boolean addOutEdge(SuperEdge se) {
		return outEdges.add(se);
	}
	
	public Set<SuperEdge> getOutEdges() {
		return outEdges;
	}
	
	public String toString() {
		return "[" + vertex.toString() + "]";
	}
	
}
