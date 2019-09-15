package holeFiller;

import java.util.List;

import holeFillerHelpers.IweightFunction;
import primitives.Pixel;

/**
 * 
 * This class represents the default hole filler presented in the exercise
 *
 */
public class DefaultHoleFiller implements IholeFiller {

	private IweightFunction mWeightFunction;

	public DefaultHoleFiller(IweightFunction weightFunction) {
		setWeightFunction(weightFunction);
	}

	public void setWeightFunction(IweightFunction weightFunction) {
		this.mWeightFunction = weightFunction;
	}

	@Override
	public double[][] holeFiller(double[][] image, IholeFinder hole) {

		List<Pixel> boundaryPixels = (List<Pixel>) hole.getBoundary();
		List<Pixel> holePixels = (List<Pixel>) hole.getHole();
		for (Pixel p : holePixels) {
			image[p.getRow()][p.getCol()] = colorPixel(p, boundaryPixels);
		}
		return image;

	}

	/**************************************
	 * PRIVATE HELPERS
	 **************************************/

	/**
	 * This function calculates the color of a pixel iterating on the boundary and
	 * using a weight function
	 * 
	 * @param p
	 *            a Pixel
	 * @param boundaryPixels
	 *            a List of Pixel
	 * @return a double
	 */
	private double colorPixel(Pixel p, List<Pixel> boundaryPixels) {
		double numerator = 0;
		double denominator = 0;
		for (Pixel b : boundaryPixels) {
			double weight = mWeightFunction.weightFunction(p, b);
			numerator += (weight * b.getColor());
			denominator += weight;
		}
		return numerator / denominator;
	}

}
