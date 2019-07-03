package support.graph;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import edu.princeton.cs.introcs.StdDraw;

import java.util.Set;
import java.util.Vector;

import support.algorithm.Geometry;

/**
 * 
 * @author Yishan Ethan Zheng (https://github.com/EthanZheng0)
 *
 */

@SuppressWarnings("unused")
public class Skeleton {
	
	private static class Vertex {
		private double x;
		private double y;
		private double thickness;
		private Map<Integer, Integer> neighbors;
		
		private Vertex(double x, double y, double thickness) {
			this.x = x;
			this.y = y;
			this.thickness = thickness;
			this.neighbors = new HashMap<>();
		}
	}
	
	private static class Edge {
		private Vertex[] endPoints;
		private double length;
		
		private Edge(Vertex v1, Vertex v2) {
			this.endPoints = new Vertex[2];
			this.endPoints[0] = v1;
			this.endPoints[1] = v2;
			this.length = Geometry.getEuclideanDistance(v1.x, v2.x, v1.y, v2.y);
		}
	}
	
	/**
	 * 
	 * adjacency_list: 	A vector containing all the vertices. 
	 * 					The index at which a vertex is contained is its id.
	 * 					Indices for added but later removed vertices will be kept as null.
	 * vertex_id_map: 	A map with all the vertices as keys and their corresponding id as values.
	 * edge_list: 		A vector containing all the edges. 
	 * 					The index at which an edge is contained is its id.
	 * 			  		Indices for added but later removed edges will be kept as null.
	 * edge_id_map: 	A map with all the edges as keys and their corresponding id as values.
	 * 
	 */
	private Vector<Vertex> vertex_list;
	private Map<Vertex, Integer> vertex_id_map;
	private Vector<Edge> edge_list;
	private Map<Edge, Integer> edge_id_map;
	
	public Skeleton() {
		this.vertex_list = new Vector<>();
		this.vertex_id_map = new HashMap<>();
		this.edge_list = new Vector<>();
		this.edge_id_map = new HashMap<>();
	}
	
	public static Skeleton loadSkeletonFromFile() {
		Skeleton s = new Skeleton();
		FileDialog fd = new FileDialog((Frame)null, "Open", FileDialog.LOAD);
	    fd.setVisible(true);
	    String filePath = fd.getDirectory() + fd.getFile();
	    Scanner sc = null;
	    try {
			sc = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    while(sc.hasNextLine()) {
	    	String line = sc.nextLine();
	    	String[] data = line.split(" ");
	    	if(data.length == 5) {
	    		double thickness = Double.parseDouble(data[0]);
	    		double x = Double.parseDouble(data[2]) / 350;
	    		double y = 1 - Double.parseDouble(data[4]) / 350;
	    		s.addVertex(x, y, thickness);
	    	}
	    	if(data.length == 2) {
	    		int vertexAId = Integer.parseInt(data[0]);
	    		int vertexBId = Integer.parseInt(data[1]);
	    		s.addEdge(vertexAId, vertexBId);
	    	}
	    }
		return s;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return id of the newly added vertex
	 * 
	 */
	public int addVertex(double x, double y, double thickness) {
		Vertex v = new Vertex(x, y, thickness);
		int id = vertex_list.size();
		vertex_list.add(v);
		vertex_id_map.put(v, id);
		return id;
	}
	
	/**
	 * 
	 * @param vertexAId
	 * @param vertexBId
	 * @return id of the newly added edge
	 * @return -1 if either of vertex doesn't exist
	 * @return -2 if edge already exists
	 * 
	 */
	public int addEdge(int vertexAId, int vertexBId) {
		if(vertexAId >= vertex_list.size() || vertexBId >= vertex_list.size()) {
			return -1;
		}
		Vertex a = vertex_list.elementAt(vertexAId);
		Vertex b = vertex_list.elementAt(vertexBId);
		if(a == null || b == null) {
			return -1;
		}
		if(a.neighbors.containsKey(vertexBId)) {
			return -2;
		}
		Edge ab = new Edge(a, b);
		int id = edge_list.size();
		edge_list.add(ab);
		edge_id_map.put(ab, id);
		a.neighbors.put(vertex_id_map.get(b), id);
		b.neighbors.put(vertex_id_map.get(a), id);
		return id;
	}
	
	public Set<Integer> getAllVertices() {
		return new HashSet<Integer>(vertex_id_map.values());
	}
	
	public Set<Integer> getAllEdges() {
		return new HashSet<Integer>(edge_id_map.values());
	}
	
	/**
	 * 
	 * @param vertexAId
	 * @param vertexBId
	 * @return id of the edge defined by the two vertices
	 * @return -1 if either one of the vertices doesn't exist
	 * @return -2 if no edge exists between the two vertices
	 * 
	 */
	public int getEdgeIdByEndPoints(int vertexAId, int vertexBId) {
		if(vertexAId >= vertex_list.size() || vertexBId >= vertex_list.size()) {
			return -1;
		}
		Vertex v1 = vertex_list.get(vertexAId);
		Vertex v2 = vertex_list.get(vertexAId);
		if(v1 == null || v2 == null) {
			return -1;
		}
		Integer edgeId = vertex_list.get(vertexAId).neighbors.get(vertexBId);
		if(edgeId == null) {
			return -2;
		}
		return edgeId.intValue();
	}
	
	/**
	 * 
	 * @param edgeId
	 * @return an array of size 2 containing the id of the end points
	 * @return null if edge doesn't exist
	 * 
	 */
	public int[] getEndPointsIdByEdge(int edgeId) {
		if(edgeId >= edge_list.size()) {
			return null;
		}
		Edge e = edge_list.get(edgeId);
		if(e == null) {
			return null;
		}
		return new int[] {vertex_id_map.get(e.endPoints[0]), vertex_id_map.get(e.endPoints[1])};
	}
	
	/**
	 * 
	 * @param edgeId
	 * @return the length of edge
	 * @return -1 if edge doesn't 
	 * 
	 */
	public double getEdgeLength(int edgeId) {
		if(edgeId >= edge_list.size()) {
			return -1;
		}
		Edge e = edge_list.get(edgeId);
		if(e == null) {
			return -1;
		}
		return e.length;
	}
	
	/**
	 * 
	 * @param vertexId
	 * @return an array of size 2 containing the coordinates of the vertex (x at 0 and y at 1)
	 * @return null if vertex doesn't exist
	 * 
	 */
	public double[] getVertexCoordinates(int vertexId) {
		if(vertexId >= vertex_list.size()) {
			return null;
		}
		Vertex v = vertex_list.get(vertexId);
		if(v == null) {
			return null;
		}
		return new double[] {v.x, v.y};
	}
	
	/**
	 * 
	 * @param edgeId
	 * @return the id of the edge that gets removed
	 * @return -1 if edge doesn't exist
	 * 
	 */
	public int removeEdge(int edgeId) {
		if(edgeId >= edge_list.size()) {
			return -1;
		}
		Edge e = edge_list.get(edgeId);
		if(e == null) {
			return -1;
		}
		edge_list.set(edgeId, null);
		edge_id_map.remove(e);
		e.endPoints[0].neighbors.remove(vertex_id_map.get(e.endPoints[1]));
		e.endPoints[1].neighbors.remove(vertex_id_map.get(e.endPoints[0]));
		return edgeId;
	}
	
	/**
	 * 
	 * @param vertexId
	 * @return the id of the vertex that gets removed
	 * @return -1 if vertex doesn't exist
	 * @return -2 if vertex isn't isolated (there are still edges connected to it)
	 * 
	 */
	public int removeVertex(int vertexId) {
		if(vertexId >= vertex_list.size()) {
			return -1;
		}
		Vertex v = vertex_list.get(vertexId);
		if(v == null) {
			return -1;
		}
		if(!vertex_list.get(vertexId).neighbors.isEmpty()) {
			return -2;
		}
		vertex_list.set(vertexId, null);
		vertex_id_map.remove(v);
		return vertexId;
	}
	
	/**
	 * 
	 * @param vertexId
	 * @return a set containing the id of the vertex's neighboring vertices
	 * @return null if vertex doesn't exist
	 * 
	 */
	public Set<Integer> getVertexNeighbors(int vertexId) {
		if(vertexId >= vertex_list.size()) {
			return null;
		}
		Vertex v = vertex_list.get(vertexId);
		if(v == null) {
			return null;
		}
		return vertex_list.get(vertexId).neighbors.keySet();
	}
	
	/**
	 * 
	 * @param vertexId
	 * @return a set containing the id of the vertex's connected edges
	 * @return null if vertex doesn't exist
	 */
	public Set<Integer> getVertexOutEdges(int vertexId) {
		if(vertexId >= vertex_list.size()) {
			return null;
		}
		Vertex v = vertex_list.get(vertexId);
		if(v == null) {
			return null;
		}
		return new HashSet<>(vertex_list.get(vertexId).neighbors.values());
	}
	
	/**
	 * 
	 * @param vertexId
	 * @param x
	 * @param y
	 * @return the id of the vertex whose coordinates get updated
	 * @return -1 if vertex doesn't exist
	 * 
	 */
	public int updateVertexCoordinates(int vertexId, double x, double y) {
		if(vertexId >= vertex_list.size()) {
			return -1;
		}
		Vertex v = vertex_list.get(vertexId);
		if(v == null) {
			return -1;
		}
		v.x = x;
		v.y = y;
		for(Entry<Integer, Integer> entry : v.neighbors.entrySet()) {
			Vertex neighbor = vertex_list.get(entry.getKey());
			Edge outEdge = edge_list.get(entry.getValue());
			double newLength = Geometry.getEuclideanDistance(x, neighbor.x, y, neighbor.y);
			outEdge.length = newLength;
		}
		return vertexId;
	}
	
	/**
	 * 
	 * @param vertexAId
	 * @param vertexBId
	 * @return the euclidean distance between the two vertices
	 * @return -1 if either of the verties doesn't exist
	 * 
	 */
	public double getVerticesRelativeDistance(int vertexAId, int vertexBId) {
		int edgeId = getEdgeIdByEndPoints(vertexAId, vertexBId);
		if(edgeId == -1) {
			return -1;
		}
		else if(edgeId == -2) {
			Vertex a = vertex_list.get(vertexAId);
			Vertex b = vertex_list.get(vertexBId);
			return Geometry.getEuclideanDistance(a.x, b.x, a.y, b.y);
		}
		return edge_list.get(edgeId).length;
	}
	
	public void drawSkeleton(Color edgeColor, Color vertexColor, double edgeThickness, double vertexRadius) {
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setPenColor(edgeColor);
		StdDraw.setPenRadius(edgeThickness);
		for(Edge e : edge_list) {
			if(e != null) {
				StdDraw.line(e.endPoints[0].x, e.endPoints[0].y, e.endPoints[1].x, e.endPoints[1].y);
			}
		}
//		StdDraw.setPenColor(vertexColor);
//		StdDraw.setPenRadius(vertexRadius);
//		for(Vertex v : vertex_list) {
//			if(v != null) {
//				StdDraw.point(v.x, v.y);
//			}
//		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Graph:\n");
		sb.append("---------------\n");
		for(int i = 0; i < vertex_list.size(); ++i) {
			Vertex v = vertex_list.get(i);
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
				Vertex[] endPoints = e.endPoints;
				sb.append('(');
				sb.append(vertex_id_map.get(endPoints[0]));
				sb.append(',');
				sb.append(vertex_id_map.get(endPoints[1]));
				sb.append(")\n");
			}
		}
		sb.append("---------------");
		return sb.toString();
	}
}
