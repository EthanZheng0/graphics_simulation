package support.algorithm;

import java.util.Vector;

import support.graph.Edge;
import support.graph.SuperEdge;
import support.graph.Vertex;

public class Fairing {

	public static SuperEdge fairSuperEdge(SuperEdge se) {
		Vector<Vertex> currVertices = se.getVertices();
		Vector<Vertex> nextVertices = new Vector<>();
		Vector<Edge> nextEdges = new Vector<>();
		nextVertices.add(currVertices.firstElement());
		for (int i = 1; i < currVertices.size() - 1; ++i) {
			Vertex prev = currVertices.get(i - 1);
			Vertex curr = currVertices.get(i);
			Vertex next = currVertices.get(i + 1);
			double averagedX = (prev.getX() + curr.getX() + next.getX()) / 3;
			double averagedY = (prev.getY() + curr.getY() + next.getY()) / 3;
			Vertex averaged = new Vertex(averagedX, averagedY);
			nextVertices.add(averaged);
		}
		nextVertices.add(currVertices.lastElement());
		for (int i = 1; i < nextVertices.size(); ++i) {
			Edge e = new Edge(nextVertices.get(i - 1), nextVertices.get(i));
			nextEdges.add(e);
		}
		return new SuperEdge(nextVertices, nextEdges);
	}

}
