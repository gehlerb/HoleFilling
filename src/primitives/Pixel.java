package primitives;

/**
 * This class represents a pixel in a picture
 *
 */
public class Pixel {
	private int mRow;
	private int mCol;
	private double mColor;

	public Pixel(int row, int col) {
		this.setRow(row);
		this.setCol(col);
	}

	public Pixel(int row, int col, double color) {
		this.setRow(row);
		this.setCol(col);
		this.setColor(color);
	}

	public int getRow() {
		return mRow;
	}

	public void setRow(int row) {
		this.mRow = row;
	}

	public int getCol() {
		return mCol;
	}

	public void setCol(int col) {
		this.mCol = col;
	}

	public double getColor() {
		return mColor;
	}

	public void setColor(double color) {
		this.mColor = color;
	}

	/**
	 * Get the distance between this pixel to Pixel a
	 * 
	 * @param a
	 *            The second Pixel
	 * @return a double
	 */
	public double distanceToPixel(Pixel a) {
		if (this.equals(a))
			return 0;
		return Math.sqrt(Math.pow((mRow - a.mRow), 2) + Math.pow((mCol - a.mCol), 2));
	}

}
