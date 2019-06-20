package fairing.visualization;

import java.awt.Color;
import java.util.Vector;
import com.sun.glass.events.KeyEvent;
import edu.princeton.cs.introcs.*;
import graph.support.Edge;
import graph.support.SuperEdge;
import graph.support.Vertex;

public class VisualizationApp {

	@SuppressWarnings({ "deprecation" })
	public static void main(String[] args) {

		// Set canvas
		StdDraw.setXscale();
		StdDraw.setYscale();
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setPenRadius(0.006);

		int NUMBER_OF_ITERATIONS = 5;

		// User drawing
		Vector<Vertex> vertices = new Vector<>();
		Vector<Edge> edges = new Vector<>();
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
					vertices.add(v);
					v.draw();
					if (vertices.size() > 1) {
						Vertex prev = vertices.get(vertices.size() - 2);
						Edge e = new Edge(prev, v);
						edges.add(e);
						e.draw();
					}
				}
			} else {
				isPressed = false;
			}
		}

		// Fairing
		StdDraw.show(200);
		Vector<Vertex> tempVertices = vertices;
		Vector<Edge> tempEdges = edges;
		for (int i = 0; i < NUMBER_OF_ITERATIONS; ++i) {
			StdDraw.clear();
			SuperEdge faired = Fairing.fairSuperEdge(tempVertices, tempEdges);
			tempVertices = faired.getVertices();
			tempEdges = faired.getEdges();
			for (Vertex tv : tempVertices) {
				tv.draw(Color.GRAY);
			}
			for (Edge te : tempEdges) {
				te.draw(Color.GREEN);
			}
			for (Vertex v : vertices) {
				v.draw();
			}
			for (Edge e : edges) {
				e.draw();
			}
			StdDraw.show(200);
		}
	}
}
