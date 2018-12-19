package holeFillerHelpers;

import primitives.Pixel;

/**
 * This class represents the default weight function given in the exercise
 *
 */
public class DefaultWeightFunction implements IweightFunction {

	private final double EPSILON;
	private final double z;

	/**
	 * 
	 * @param eps
	 *            a double, supposed to be very small just to avoid division by zero
	 * @param z
	 *            a double, the power of the distance between two pixels
	 */
	public DefaultWeightFunction(double eps, double z) {
		this.EPSILON = eps;
		this.z = z;
	}

	@Override
	public double weightFunction(Pixel a, Pixel b) {
		double result = 1 / (Math.pow(a.distanceToPixel(b), z) + EPSILON);
		return result;
	}

}
