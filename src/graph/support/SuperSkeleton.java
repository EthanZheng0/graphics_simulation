package graph.support;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class SuperSkeleton {

	private Vector<SuperEdge> superEdges;
	private Set<SuperVertex> superVertices;
	
	public SuperSkeleton() {
		this.superEdges = new Vector<>();
		this.superVertices = new HashSet<>();
	}
	
	public int addSuperEdge(SuperEdge se) {
		superEdges.add(se);
		superVertices.add(se.getStart());
		superVertices.add(se.getEnd());
		return superEdges.size() - 1;
	}
	
	public Vector<SuperEdge> getSuperEdges() {
		return superEdges;
	}
	
	public Set<SuperVertex> getSuperVertices() {
		return superVertices;
	}
	
	public SuperVertex getByCoordinates(double x, double y) {
		for(SuperVertex sv : superVertices) {
			Vertex v = sv.getVertex();
			double deltaX = v.getX() - x;
			double deltaY = v.getY() - y;
			double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
			if(distance < 0.015) {
				return sv;
			}
		}
		return null;
	}
	
}
