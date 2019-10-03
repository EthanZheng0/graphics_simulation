package visualization.fairing;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Vector;
import edu.princeton.cs.introcs.*;
import support.algorithm.Fairing;
import support.graph.Edge;
import support.graph.SuperEdge;
import support.graph.SuperSkeleton;
import support.graph.SuperVertex;
import support.graph.Vertex;

public class FairingApp {

	@SuppressWarnings({ "deprecation" })
	public static void main(String[] args) {

		int NUMBER_OF_ITERATIONS = 5;

		// Set canvas
		StdDraw.setXscale();
		StdDraw.setYscale();
		StdDraw.setCanvasSize(800, 800);

		// User drawing
		SuperSkeleton skeleton = new SuperSkeleton();
		boolean isDrawing = false;
		boolean isSpacePressed = false;
		System.out.println("\nInstructions:\n");
		System.out.println("-- Click on the canvas to draw an edge.");
		System.out.println("-- Press space to save the edge you draw.");
		System.out.println("-- Press enter to visualize fairing after \ndrawing and saving all the edges.");
		drawEdgesLoop: while (true) {
			if (!isDrawing) {
				isDrawing = true;
				Vector<Vertex> drawnVertices = new Vector<>();
				Vector<Edge> drawnEdges = new Vector<>();
				boolean isPressed = false;
				drawOneEdgeLoop: while (true) {
					if (StdDraw.isKeyPressed(KeyEvent.VK_ENTER)) {
						break drawEdgesLoop;
					}
					if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
						if (!isSpacePressed) {
							isSpacePressed = true;
							isDrawing = false;
							break drawOneEdgeLoop;
						}
					} else {
						isSpacePressed = false;
					}
					if (StdDraw.isMousePressed()) {
						if (!isPressed) {
							isPressed = true;
							double mouseX = StdDraw.mouseX();
							double mouseY = StdDraw.mouseY();
							Vertex v = new Vertex(mouseX, mouseY);
							;
							SuperVertex sv = skeleton.getByCoordinates(mouseX, mouseY);
							if (sv != null) {
								v = sv.getVertex();
							} else {
								v.draw();
							}
							drawnVertices.add(v);
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
				if (drawnVertices.size() == 0) {
					continue drawEdgesLoop;
				}
				SuperEdge drawn = new SuperEdge(drawnVertices, drawnEdges);
				skeleton.addSuperEdge(drawn);
				for (SuperVertex sv : skeleton.getSuperVertices()) {
					sv.draw();
				}
			}
		}

		// Fairing
		System.out.print("\nFairing");
		StdDraw.show(200);
		Vector<SuperEdge> temp = new Vector<>(skeleton.getSuperEdges());
		for (int i = 0; i < NUMBER_OF_ITERATIONS; ++i) {
			System.out.print(".");
			StdDraw.clear();
			for (int j = 0; j < temp.size(); ++j) {
				SuperEdge faired = Fairing.fairSuperEdge(temp.get(j));
				SuperEdge original = skeleton.getSuperEdges().get(j);
				for (Vertex v : original.getVertices()) {
					v.draw();
				}
				for (Edge e : original.getEdges()) {
					e.draw();
				}
				for (Vertex tv : faired.getVertices()) {
					tv.draw(Color.GRAY);
				}
				for (Edge te : faired.getEdges()) {
					te.draw(Color.GREEN);
				}
				for (SuperVertex sv : skeleton.getSuperVertices()) {
					sv.draw();
				}
				temp.set(j, faired);
			}
			StdDraw.show(200);
		}
		System.out.println("\n\nDone.");
	}
}
