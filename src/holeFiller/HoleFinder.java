package holeFiller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import holeFillerHelpers.Iconnected;
import primitives.Pixel;

/**
 * 
 * This class searches the hole in the 2D Array Note that a hole can be defined
 * by the boundary or by the hole itself I chose to define the hole with both
 *
 */
public class HoleFinder {

	private static final int HOLE = -1;
	private double[][] mImage;
	private int mRows;
	private int mCols;
	private Iconnected mConnections;

	public HoleFinder(double[][] image, Iconnected connections) {
		setImage(image);
		setConnections(connections);
	}

	public double[][] getImage() {
		return mImage;
	}

	public void setImage(double[][] image) {
		this.mImage = image;
		mRows = image.length;
		mCols = image[0].length;
	}

	public void setConnections(Iconnected connections) {
		this.mConnections = connections;
	}

	public Collection<Pixel> getBoundary() {
		return holeFinder().get(0);
	}
	
	public Collection<Pixel> getHole(){
		return holeFinder().get(1);
	}
	
	/**
	 * This function use BFS algorithm to find the hole
	 * 
	 * @return a List<Collection<Pixel> at index(0) the boundary, at index(1) the
	 *         hole
	 */
	private List<Collection<Pixel>> holeFinder() {

		List<Pixel> holePixels = new LinkedList<Pixel>();
		List<Pixel> boundaryPixels = new LinkedList<Pixel>();
		Queue<Pixel> bfsQueue = new LinkedList<Pixel>();
		boolean[][] marked = new boolean[mRows][mCols];

		Pixel pixel = findFirstHolePixel();
		bfsQueue.add(pixel);
		holePixels.add(pixel);
		marked[pixel.getRow()][pixel.getCol()] = true;

		while (!bfsQueue.isEmpty()) {
			Pixel currentPixel = bfsQueue.remove();

			List<Pixel> connectedPixels = mConnections.getConnections(currentPixel);

			// iterates on the pixels who are connected to current pixel
			// if the pixel was already visited skip it
			// else, if the pixel is a hole add it to the list of the holes and to the BFS Queue to expand the hole
			// 		 else add it to the result list, the pixel belongs to the boundary
			for (Pixel p : connectedPixels) {
				int indexRow = p.getRow();
				int indexCol = p.getCol();

				if (!marked[indexRow][indexCol]) {
					marked[indexRow][indexCol] = true;

					if (mImage[indexRow][indexCol] == HOLE) {
						holePixels.add(p);
						bfsQueue.add(p);
					}

					else {
						p.setColor(mImage[p.getRow()][p.getCol()]);
						boundaryPixels.add(p);
					}
				}
			}
		}

		List<Collection<Pixel>> result = new LinkedList<Collection<Pixel>>();
		result.add(boundaryPixels);
		result.add(holePixels);
		return result;
	}

	/**************************************
	 * PRIVATE HELPERS
	 **************************************/

	/**
	 * This function iterates on the 2D Array which represents the picture to find
	 * the first pixel of the hole
	 * 
	 * @return a Pixel
	 * @throws Exception
	 *             if the picture has no hole
	 */
	private Pixel findFirstHolePixel() {
		for (int i = 0; i < mRows; ++i) {
			for (int j = 0; j < mCols; ++j) {
				if (mImage[i][j] == HOLE)
					return new Pixel(i, j);
			}
		}

		return null;
	}

}
