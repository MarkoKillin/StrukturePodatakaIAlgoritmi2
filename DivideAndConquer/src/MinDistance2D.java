import java.util.Arrays;
import java.util.Comparator;

public class MinDistance2D {
	Point2d[] points;

	public MinDistance2D(Point2d[] points) {
		if(points.length<2)
			throw new IllegalArgumentException();
		this.points = points;
	}
	private static class Xcom implements Comparator<Point2d>{
		@Override
		public int compare(Point2d o1, Point2d o2) {
			double xd = o1.x-o2.x;
			if(xd<0)
				return -1;
			else if(xd>0)
				return 1;
			else
				return 0;
		}
	}
	private static class Ycom implements Comparator<Point2d>{
		@Override
		public int compare(Point2d o1, Point2d o2) {
			double xd = o1.y-o2.y;
			if(xd<0)
				return -1;
			else if(xd>0)
				return 1;
			else
				return 0;
		}
	}
	public double findDQ() {
		Arrays.sort(points, new Xcom());
		for (int i = 0; i < points.length-1; i++) {
			if(points[i].coincident(points[i+1]))
				return 0.0;
		}
		return findDQ(0, points.length-1);
	}
	private double findDQ(int from, int to) {
		if(from==to)
			return Double.MAX_VALUE;
		if(to-from==1)
			return points[to].distance(points[from]);
		int median = (from+to)/2;
		double dl = findDQ(from, median);
		double dr = findDQ(median+1, to);
		double delta = Math.min(dl, dr);
		double cross = findCrossing(from, to, median, delta);
		return Math.min(delta, cross);
	}
	private double findCrossing(int from, int to, int median, double delta) {
		double medianX = points[median].x;
		Point2d[] box = new Point2d[points.length];
		int boxCnt = 0;
		for (int i = from; i <= to; i++) {
			if(Math.abs(medianX-points[i].x) < delta)
				box[boxCnt++] = points[i];
		}
		Arrays.sort(box,0,boxCnt,new Ycom());
		double min = Double.MAX_VALUE;
		for (int i = 0; i < boxCnt-1; i++) {
			Point2d p = box[i];
			for (int j = i+1; j < boxCnt; j++) {
				if(box[j].y-p.y >= delta)
					break;
				double d = box[j].distance(p);
				if(d < min)
					min = d;
			}
		}
		return min;
	}
}

























