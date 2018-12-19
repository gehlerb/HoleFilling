package holeFillerHelpers;

import java.util.List;

import primitives.Pixel;

/**
 * This class represents a level of pixel connectivity of Eight
 *
 */
public class EightConnected implements Iconnected {

	private FourConnected mFourconnected;

	@Override
	public List<Pixel> getConnections(Pixel pixel) {
		int row = pixel.getRow();
		int col = pixel.getCol();

		mFourconnected = new FourConnected();
		List<Pixel> connectedPixels = mFourconnected.getConnections(pixel);

		connectedPixels.add(new Pixel(row - 1, col - 1));
		connectedPixels.add(new Pixel(row + 1, col - 1));
		connectedPixels.add(new Pixel(row - 1, col + 1));
		connectedPixels.add(new Pixel(row + 1, col + 1));

		return connectedPixels;
	}
}
