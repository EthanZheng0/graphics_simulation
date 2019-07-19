package test.graph;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;
import support.graph.Skeleton;

public class ReadFileTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Skeleton s = Skeleton.loadSkeletonFromFile();
		s.drawSkeleton(Color.RED, Color.BLACK, 0.001, 0.001, true, false);
		
		StdDraw.show(500);
	}

}
