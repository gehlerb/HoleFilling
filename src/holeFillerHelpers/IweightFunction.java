package holeFillerHelpers;

import primitives.Pixel;

/**
 * Interface who represents weight functions
 * weight functions calculate a weight average between two pixels
 *
 */
public interface IweightFunction {

	/**
	 * 
	 * @param a
	 *            a Pixel
	 * @param b
	 *            a Pixel
	 * @return the weight of a two pixels in the image
	 */
	public double weightFunction(Pixel a, Pixel b);
}
