package imageLoader;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * This class handle the conversions from an image to an array and from an array
 * to an image
 *
 */
public class ImageConvertor {

	private final String IMAGE_PATH = System.getProperty("user.dir") + "//src//pictures//";
	private static final int NORMAL_FACTOR = 255;
	private Mat mSrc;
	private String mImageName;

	public ImageConvertor(String s) {
		this.mImageName = new File(s).getName().replaceAll("[.][^.]+$", "");
		this.mSrc = Imgcodecs.imread(s);

	}

	public double[][] convertImageToArr() throws IOException {
		makeTheHole(mSrc);
		writeToImage(mSrc, mImageName + "Hole");
		Mat dst = new Mat();
		Imgproc.cvtColor(mSrc, dst, Imgproc.COLOR_RGB2GRAY);
		writeToImage(dst, mImageName + "Grayscale");
		double[][] image = convertMatToArray(dst);
		return image;
	}

	public void convertArrToImage(double[][] image) throws IOException {
		int rows = image.length;
		int cols = image[0].length;
		Mat dst = new Mat(rows, cols, CvType.CV_8UC1);
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++) {
				dst.put(i, j, image[i][j] * NORMAL_FACTOR);
			}
		writeToImage(dst, mImageName + "Result");
	}

	/**************************************
	 * PRIVATE HELPERS
	 **************************************/

	private void writeToImage(Mat mat, String imageName) throws IOException {
		BufferedImage image = convertMatToImage(mat);
		File ouFile = new File(IMAGE_PATH + imageName + ".jpg");

		try {
			ImageIO.write(image, "jpg", ouFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Desktop d = Desktop.getDesktop();
		d.open(ouFile);
	}

	private BufferedImage convertMatToImage(Mat mat) {
		MatOfByte byteMatData = new MatOfByte();
		Imgcodecs.imencode(".jpg", mat, byteMatData);
		byte[] byteArray = byteMatData.toArray();
		BufferedImage img = null;
		try {
			InputStream in = new ByteArrayInputStream(byteArray);
			img = ImageIO.read(in);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return img;
	}

	private double[][] convertMatToArray(Mat dst) {
		int rows = dst.rows();
		int cols = dst.cols();
		double[][] image = new double[rows][cols];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++) {
				if (dst.get(i, j)[0] == 255) {
					image[i][j] = -1;
				} else {
					image[i][j] = dst.get(i, j)[0] / NORMAL_FACTOR;
				}
			}
		return image;
	}

	private void makeTheHole(Mat dst) {
		for (int i = 200; i < 300; i++)
			for (int j = 80; j < 180; j++) {
				double[] data = { 255, 255, 255 };
				dst.put(i, j, data);
			}
	}

}
