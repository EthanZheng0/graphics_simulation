package test.graph;

import java.awt.Color;

import support.graph.Skeleton;

public class ReadFileTest {

	public static void main(String[] args) {
		Skeleton s = Skeleton.loadSkeletonFromFile();
		s.drawSkeleton(Color.RED, Color.BLACK, 0.001, 0.001, true, false);
	}

}
