package visualization.curvature;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;
import support.algorithm.CircumcircleCenter;
import support.graph.Edge;
import support.graph.SuperVertex;
import support.graph.Vertex;

public class VisualizationApp {

	public static void main(String[] args) {
		
		// Set canvas
		StdDraw.setXscale();
		StdDraw.setYscale();
		StdDraw.setCanvasSize(800, 800);
		
		// Draw
		Vertex v1 = new Vertex(0.5, 0.8);
		Vertex v2 = new Vertex(0.4, 0.73);
		Vertex v3 = new Vertex(0.58, 0.82);
		Edge e1 = new Edge(v1, v2);
		Edge e2 = new Edge(v2, v3);
		Edge e3 = new Edge(v3, v1);

		Vertex center = CircumcircleCenter.calculateCircleCenter2D(v1, v2, v3);
		SuperVertex centerSV = new SuperVertex(center);
		StdDraw.setPenColor(Color.GREEN);
		StdDraw.setPenRadius(0.006);
		StdDraw.circle(center.getX(), center.getY(), center.euclideanDistance(v1));
		e1.draw();
		e2.draw();
		e3.draw();
		v1.draw();
		v2.draw();
		v3.draw();
		centerSV.draw();
	}

}
