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

		int NUMBER_OF_ITERATIONS = 5;
		
		// Set canvas
		StdDraw.setXscale();
		StdDraw.setYscale();
		StdDraw.setCanvasSize(800, 800);
		StdDraw.setPenRadius(0.006);

		// User drawing & constructing SuperEdge
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
		StdDraw.show(200);
		SuperEdge temp = drawn;
		for (int i = 0; i < NUMBER_OF_ITERATIONS; ++i) {
			StdDraw.clear();
			SuperEdge faired = Fairing.fairSuperEdge(temp);
			for (Vertex tv : faired.getVertices()) {
				tv.draw(Color.GRAY);
			}
			for (Edge te : faired.getEdges()) {
				te.draw(Color.GREEN);
			}
			for (Vertex v : drawn.getVertices()) {
				v.draw();
			}
			for (Edge e : drawn.getEdges()) {
				e.draw();
			}
			temp = faired;
			StdDraw.show(200);
		}
	}
}
