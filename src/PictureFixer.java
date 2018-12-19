import holeFiller.HoleFinder;
import holeFiller.IholeFiller;
import holeFillerHelpers.Iconnected;
import imageLoader.ImageConvertor;

/**
 * This class operates the filling of a hole in a picture
 *
 */
public class PictureFixer {
	private final String mImageSource;
	private Iconnected mConnections;
	private IholeFiller mHoleFiller;

	public PictureFixer(String imageSource, Iconnected connections, IholeFiller holeFiller) {
		this.mImageSource = imageSource;
		setConnections(connections);
		setHoleFiller(holeFiller);

	}

	public void setHoleFiller(IholeFiller holeFiller) {
		this.mHoleFiller = holeFiller;
	}

	public void setConnections(Iconnected connections) {
		this.mConnections = connections;
	}

	/**
	 * This function operates the following: 1. Convert a picture from a given path
	 * to a 2D array 2. Searches for the hole in the 2D array 3. Fill the hole of
	 * the 2D array 4. Convert the 2D array in a picture
	 * 
	 * @throws Exception
	 *             if a problem appear during the conversion from or to a picture
	 */
	public void fillTheHole() throws Exception {
		ImageConvertor imageToArr = new ImageConvertor(mImageSource);
		double[][] image = imageToArr.convertImageToArr();
		HoleFinder hole = new HoleFinder(image, mConnections);
		image = mHoleFiller.holeFiller(image, hole);
		imageToArr.convertArrToImage(image);
	}

}
