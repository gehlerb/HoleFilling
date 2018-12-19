package holeFillerHelpers;

import java.util.List;

import primitives.Pixel;

/**
 * Interface who represents the pixel connectivity
 *
 */
public interface Iconnected {

	/**
	 * This function searches all the connected pixels to a given pixel
	 * 
	 * @param pixel
	 *            a Pixel
	 * @return a List of Pixels List<Pixel>
	 */
	public List<Pixel> getConnections(Pixel pixel);
}
