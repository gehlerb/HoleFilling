package holeFillerHelpers;

import java.util.ArrayList;
import java.util.List;

import primitives.Pixel;

/**
 * This class represents a level of pixel connectivity of Four
 *
 */
public class FourConnected implements Iconnected {

	@Override
	public List<Pixel> getConnections(Pixel pixel) {

		int row = pixel.getRow();
		int col = pixel.getCol();

		List<Pixel> connectedPixels = new ArrayList<Pixel>();
		connectedPixels.add(new Pixel(row - 1, col));
		connectedPixels.add(new Pixel(row + 1, col));
		connectedPixels.add(new Pixel(row, col + 1));
		connectedPixels.add(new Pixel(row, col - 1));

		return connectedPixels;
	}

}
