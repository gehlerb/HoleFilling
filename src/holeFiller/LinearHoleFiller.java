package holeFiller;

import java.util.List;
import java.util.Queue;

import holeFillerHelpers.Iconnected;
import primitives.Pixel;

/**
 * This class operates the filling of a hole in a 2D Array from the boundary For
 * each pixel which is in the boundary, remove it from the boundary and look for
 * his uncolored connected pixel. Now, each uncolored pixel found is connected
 * to colored pixels. For each uncolored pixel found, color it with the average
 * color of his connected colored pixel and add it to the boundary.
 */
public class LinearHoleFiller implements IholeFiller {

	private Iconnected mConnections;

	public LinearHoleFiller(Iconnected connections) {
		setConnections(connections);
	}

	public void setConnections(Iconnected connections) {
		this.mConnections = connections;
	}

	@Override
	public double[][] holeFiller(double[][] image, HoleFinder hole) {
		Queue<Pixel> boundaryPixels = (Queue<Pixel>) hole.getBoundary();
		while (!boundaryPixels.isEmpty()) {

			Pixel currentPixel = boundaryPixels.remove();

			List<Pixel> uncoloredConnections = getUncoloredConnections(currentPixel, image);
			for (Pixel p : uncoloredConnections) {
				colorPixel(p, image);
				boundaryPixels.add(p);
			}

		}

		return image;
	}

	/**************************************
	 * PRIVATE HELPERS
	 **************************************/

	/**
	 * This function color a pixel using the average color of his connected pixels
	 * 
	 * @param toColor
	 *            a Pixel
	 * @param image
	 *            a 2D Array of double
	 */
	private void colorPixel(Pixel toColor, double[][] image) {

		List<Pixel> coloredConnections = mConnections.getConnections(toColor);
		coloredConnections.removeIf(p -> image[p.getRow()][p.getCol()] == -1);

		double color = 0;
		for (Pixel p : coloredConnections)
			color += image[p.getRow()][p.getCol()];
		color /= coloredConnections.size();

		image[toColor.getRow()][toColor.getCol()] = color;

	}

	/**
	 * This function searches for the uncolored connected pixels to a given pixel
	 * 
	 * @param currentPixel
	 *            a Pixel
	 * @param image
	 *            a 2D Array of double
	 * @return a List of Pixel
	 */
	private List<Pixel> getUncoloredConnections(Pixel currentPixel, double[][] image) {

		List<Pixel> uncoloredPixels = mConnections.getConnections(currentPixel);
		uncoloredPixels.removeIf(p -> image[p.getRow()][p.getCol()] != -1);

		return uncoloredPixels;
	}

}
