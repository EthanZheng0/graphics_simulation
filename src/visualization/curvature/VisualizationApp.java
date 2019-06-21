package visualization.curvature;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Vector;

import edu.princeton.cs.introcs.StdDraw;
import support.algorithm.CircumcircleCenter;
import support.algorithm.Fairing;
import support.graph.Edge;
import support.graph.SuperEdge;
import support.graph.SuperVertex;
import support.graph.Vertex;

public class VisualizationApp {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		// Set canvas
		StdDraw.setXscale();
		StdDraw.setYscale();
		StdDraw.setCanvasSize(800, 800);

		// Draw edge
		Vector<Vertex> drawnVertices = new Vector<>();
		Vector<Edge> drawnEdges = new Vector<>();
		boolean isPressed = false;
		while (true) {
			if (StdDraw.isKeyPressed(KeyEvent.VK_ENTER)) {
				break;
			}
			if (StdDraw.isMousePressed()) {
				if (!isPressed) {
					isPressed = true;
					double mouseX = StdDraw.mouseX();
					double mouseY = StdDraw.mouseY();
					Vertex v = new Vertex(mouseX, mouseY);
					drawnVertices.add(v);
					v.draw();
					if (drawnVertices.size() > 1) {
						Vertex prev = drawnVertices.get(drawnVertices.size() - 2);
						Edge e = new Edge(prev, v);
						drawnEdges.add(e);
						e.draw();
					}
				}
			} else {
				isPressed = false;
			}
		}
		SuperEdge drawn = new SuperEdge(drawnVertices, drawnEdges);
		
		// Fairing
		for (int i = 0; i < 3; ++i) {
			drawn = Fairing.fairSuperEdge(drawn);
		}
		
		// Mouse hover interaction
		while (true) {
			StdDraw.clear();
			for (Edge e : drawn.getEdges()) {
				e.draw();
			}
			for (Vertex v : drawn.getVertices()) {
				v.draw();
			}
			drawn.getStart().draw();
			drawn.getEnd().draw();
			double mouseX = StdDraw.mouseX();
			double mouseY = StdDraw.mouseY();
			int hoverOnVertexIndex = drawn.getIndexByCoordinates(mouseX, mouseY);
			if (hoverOnVertexIndex != -1 && hoverOnVertexIndex != 0 && hoverOnVertexIndex != drawn.getVertices().size() - 1) {
				Vertex prev = drawn.getVertices().get(hoverOnVertexIndex - 1);
				Vertex curr = drawn.getVertices().get(hoverOnVertexIndex);
				Vertex next = drawn.getVertices().get(hoverOnVertexIndex + 1);
				Vertex center = CircumcircleCenter.calculateCircleCenter2D(prev, curr, next);
				SuperVertex centerSV = new SuperVertex(center);
				StdDraw.setPenColor(Color.GREEN);
				StdDraw.setPenRadius(0.006);
				double radius = center.euclideanDistance(curr);
				StdDraw.circle(center.getX(), center.getY(), radius);
				StdDraw.setPenColor(Color.BLACK);
				StdDraw.text(0.1, 0.95, "Radius: " + Math.round(radius * 1000) / 100.0);
				StdDraw.text(0.12, 0.92, "Curvature: " + Math.round(1000.0 / radius) / 10000.0);
				centerSV.draw();
			}
			StdDraw.show(30);
		}
	}

}
