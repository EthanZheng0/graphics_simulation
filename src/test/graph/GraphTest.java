package test.graph;

import support.graph.Skeleton;

public class GraphTest {

	public static void main(String[] args) {
		Skeleton graph = new Skeleton();
		int vertex0 = graph.addVertex(0.5, 0.5);
		int vertex1 = graph.addVertex(0.1, 0.2);
		int vertex2 = graph.addVertex(0.1, 0.5);
		System.out.println(graph);
		System.out.println("actual: " + graph.getEdgeIdByEndpoints(vertex0, vertex1) + ", expected: -1");
		int edge01 = graph.addEdge(vertex0, vertex1);
		System.out.println("actual: " + graph.getEdgeIdByEndpoints(vertex0, vertex1) + ", expected: " + edge01);
		System.out.println("actual: " + graph.getEdgeIdByEndpoints(vertex0, vertex2) + ", expected: -1");
		System.out.println("actual: " + graph.getEdgeIdByEndpoints(vertex1, vertex2) + ", expected: -1");
		System.out.println("actual: " + graph.getEdgeLength(1) + ", expected: -1.0");
		System.out.println("actual: " + graph.getEdgeLength(edge01) + ", expected: 0.5");
		System.out.println(graph);
		int edge02 = graph.addEdge(vertex0, vertex2);
		int edge12 = graph.addEdge(vertex1, vertex2);
		System.out.println("actual: " + graph.getEdgeIdByEndpoints(vertex0, vertex2) + ", expected: 1");
		System.out.println("actual: " + graph.getEdgeIdByEndpoints(vertex1, vertex2) + ", expected: 2");
		System.out.println("actual: " + graph.getEdgeLength(edge02) + ", expected: 0.4");
		System.out.println("actual: " + graph.getEdgeLength(edge12) + ", expected: 0.3");
		System.out.println("actual: " + graph.updateVertexCoordinates(vertex2, 0.5, 0.2) + ", expected: " + vertex2);
		System.out.println("actual: " + graph.getEdgeLength(edge02) + ", expected: 0.3");
		System.out.println("actual: " + graph.getEdgeLength(edge12) + ", expected: 0.4");
		System.out.println("actual: " + endpointsToString(graph.getEndpointsIdByEdge(4)) + ", expected: null");
		System.out.println("actual: " + endpointsToString(graph.getEndpointsIdByEdge(edge01)) + ", expected: (" + vertex0 + "," + vertex1 + ")");
		System.out.println("actual: " + endpointsToString(graph.getEndpointsIdByEdge(edge02)) + ", expected: (" + vertex0 + "," + vertex2 + ")");
		System.out.println("actual: " + endpointsToString(graph.getEndpointsIdByEdge(edge12)) + ", expected: (" + vertex1 + "," + vertex2 + ")");
		System.out.println(graph);
		System.out.println("actual: " + graph.addVertex(0.5, 0.5) + " expected: 3");
		System.out.println("actual: " + graph.addEdge(vertex0, vertex1) + " expected: -1");
		System.out.println("actual: " + graph.removeEdge(3) + ", expected: -1");
		System.out.println("actual: " + graph.removeEdge(edge01) + ", expected: " + edge01);
		System.out.println("actual: " + graph.removeEdge(edge01) + ", expected: -1");
		System.out.println(graph);
		edge01 = graph.addEdge(vertex0, vertex1);
		System.out.println("actual: " + edge01 + ", expected: 3");
		System.out.println(graph);
		System.out.println("actual: " + graph.removeVertex(3) + ", expected: 3");
		System.out.println("actual: " + graph.removeVertex(2) + ", expected: -1");
		System.out.println("actual: " + graph.removeEdge(edge12) + ", expected: " + edge12);
		System.out.println("actual: " + graph.removeEdge(edge02) + ", expected: " + edge02);
		System.out.println("actual: " + graph.removeVertex(2) + ", expected: 2");
		System.out.println(graph);
		System.out.println("actual: " + graph.removeEdge(edge01) + ", expected: " + edge01);
		System.out.println("actual: " + graph.removeVertex(0) + ", expected: 0");
		System.out.println("actual: " + graph.removeVertex(1) + ", expected: 1");
		System.out.println(graph);

	}

	private static String endpointsToString(int[] endpointIds) {
		if(endpointIds == null) {
			return null;
		}
		return "(" + endpointIds[0] + "," + endpointIds[1] + ")";
	}
}
