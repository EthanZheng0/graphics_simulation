package support.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class Skeleton {
	
	static class Vertex {
		private final double x;
		private final double y;
		private final Map<Integer, Integer> neighbors;
		
		private Vertex(double x, double y) {
			this.x = x;
			this.y = y;
			this.neighbors = new HashMap<>();
		}
		
		private void addNeighbor(int vertexId, int edgeId) {
			neighbors.put(vertexId, edgeId);
		}
		
		private int getEdgeId(int vertexId) {
			Integer edgeId = neighbors.get(vertexId);
			if(edgeId == null) {
				System.out.println("Edge doesn't exist.");
				return -1;
			}
			return edgeId.intValue();
		}
	}
	
	static class Edge {
		private Vertex[] endpoints;
		private double length;
		
		private Edge(Vertex v1, Vertex v2) {
			this.endpoints = new Vertex[2];
			this.endpoints[0] = v1;
			this.endpoints[1] = v2;
			this.length = Math.sqrt(Math.pow((v1.x - v2.x), 2) + Math.pow((v1.y - v2.y), 2));
		}
	}
	
	private Vector<Vertex> adjacency_list;
	private Map<Vertex, Integer> vertex_id_map;
	private Vector<Edge> edge_list;
	private Map<Edge, Integer> edge_id_map;
	
	public Skeleton() {
		this.adjacency_list = new Vector<>();
		this.vertex_id_map = new HashMap<>();
		this.edge_list = new Vector<>();
		this.edge_id_map = new HashMap<>();
	}
	
	public int addVertex(double x, double y) {
		Vertex v = new Vertex(x, y);
		int id = adjacency_list.size();
		adjacency_list.add(v);
		vertex_id_map.put(v, id);
		return id;
	}
	
	public int addEdge(int vertexAId, int vertexBId) {
		Vertex a = adjacency_list.elementAt(vertexAId);
		Vertex b = adjacency_list.elementAt(vertexBId);
		Edge ab = new Edge(a, b);
		if(a.neighbors.containsKey(vertexBId)) {
			System.out.println("Edge already exists.");
			return -1;
		}
		int id = edge_list.size();
		edge_list.add(ab);
		edge_id_map.put(ab, id);
		a.addNeighbor(vertex_id_map.get(b), id);
		b.addNeighbor(vertex_id_map.get(a), id);
		return id;
	}
	
	public int getEdgeIdByEndpoints(int vertexAId, int vertexBId) {
		return adjacency_list.get(vertexAId).getEdgeId(vertexBId);
	}
	
	public int[] getEndpointsIdByEdge(int edgeId) {
		Vertex[] endpoints;
		try {
			endpoints = edge_list.get(edgeId).endpoints;
		}
		catch(Exception e) {
			System.out.println("Edge doesn't exist.");
			return null;
		}
		int[] endpointsId = new int[2];
		endpointsId[0] = vertex_id_map.get(endpoints[0]);
		endpointsId[1] = vertex_id_map.get(endpoints[1]);
		return endpointsId;
	}
	
	public double getEdgeLength(int edgeId) {
		double length;
		try {
			length = edge_list.get(edgeId).length;
		}
		catch(Exception e) {
			System.out.println("Edge doesn't exist.");
			return -1;
		}
		return length;
	}
	
	public int removeEdge(int edgeId) {
		if(edgeId >= edge_list.size()) {
			System.out.println("Edge doesn't exist.");
			return -1;
		}
		Edge e = edge_list.get(edgeId);
		if(e == null) {
			System.out.println("Edge doesn't exist.");
			return -1;
		}
		edge_list.set(edgeId, null);
		edge_id_map.remove(e);
		Vertex[] endpoints = e.endpoints;
		endpoints[0].neighbors.remove(vertex_id_map.get(endpoints[1]));
		endpoints[1].neighbors.remove(vertex_id_map.get(endpoints[0]));
		return edgeId;
	}
	
	public int removeVertex(int vertexId) {
		if(vertexId >= adjacency_list.size()) {
			System.out.println("Vertex doesn't exist.");
			return -1;
		}
		Vertex v = adjacency_list.get(vertexId);
		if(v == null) {
			System.out.println("Vertex doesn't exist.");
			return -1;
		}
		if(!adjacency_list.get(vertexId).neighbors.isEmpty()) {
			System.out.println("Vertex isn't isolated.");
			System.out.println("Please first remove all the connected edges.");
			return -1;
		}
		adjacency_list.set(vertexId, null);
		vertex_id_map.remove(v);
		return vertexId;
	}
	
	public Set<Integer> getAllVertices() {
		return new HashSet<Integer>(vertex_id_map.values());
	}
	
	public Set<Integer> getAllEdges() {
		return new HashSet<Integer>(edge_id_map.values());
	}
	
	public Set<Integer> getNeighbors(int vertexId) {
		return adjacency_list.get(vertexId).neighbors.keySet();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Graph:\n");
		sb.append("---------------\n");
		for(int i = 0; i < adjacency_list.size(); ++i) {
			Vertex v = adjacency_list.get(i);
			if(v != null) {
				sb.append("Vert ");
				sb.append(i);
				sb.append(": ");
				for(Integer neighborId : v.neighbors.keySet()) {
					sb.append(neighborId);
					sb.append(",");
				}
				if(sb.charAt(sb.length() - 1) == ',') {
					sb.deleteCharAt(sb.length() - 1);
				}
				sb.append("\n");
			}
		}
		for(int i = 0; i < edge_list.size(); ++i) {
			Edge e = edge_list.get(i);
			if(e != null) {
				sb.append("Edge ");
				sb.append(edge_id_map.get(e));
				sb.append(": ");
				Vertex[] endpoints = e.endpoints;
				sb.append('(');
				sb.append(vertex_id_map.get(endpoints[0]));
				sb.append(',');
				sb.append(vertex_id_map.get(endpoints[1]));
				sb.append(")\n");
			}
		}
		sb.append("---------------");
		return sb.toString();
	}
}
