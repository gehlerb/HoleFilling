package holeFiller;

/**
 * This interface operates the filling of a hole in a 2D Array
 *
 */
public interface IholeFiller {

	/**
	 * This function fills a hole in a 2D Array
	 * 
	 * @param image
	 *            a 2D Array which represents the image
	 * @param hole
	 *            HoleFinder 
	 * @return a 2D Array of double
	 */
	public double[][] holeFiller(double[][] image, HoleFinder hole);
}
