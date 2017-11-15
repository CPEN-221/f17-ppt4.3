package sequencepairs;

public abstract class AbstractSequencePair {
	private char[] x;
	private char[] y;

	public abstract int similarity();

	/**
	 * Initialize the sequence pair
	 * 
	 * @param x
	 *            should be the same length as y
	 * @param y
	 *            should be the same length as x
	 */
	public AbstractSequencePair(char[] x, char[] y) {
		int length = x.length;
		this.x = new char[length];
		this.y = new char[length];
		for(int i = 0; i < length; i++) {
			this.x[i] = x[i];
			this.y[i] = y[i];
		}
	}

	/**
	 * Swap the characters at index i between arrays x and y
	 * 
	 * @modifies: this
	 * @effects: x_post[i] = y_pre[i] and y_post[i] = x_pre[i]
	 * 
	 * @param index,
	 *            0 <= index <= x.length-1
	 */
	public void swapChars(int index) {
		// TODO implement this method
	}
	
	/**
	 * Obtain the string representation of x
	 * @return x as a String
	 */
	public String getXString() {
		return new String(x);
	}

	/**
	 * Obtain the string representation of y
	 * @return y as a String
	 */
	public String getYString() {
		return new String(y);
	}

}
