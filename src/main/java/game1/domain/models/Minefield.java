package game1.domain.models;

import org.springframework.util.Assert;

public class Minefield {
    private final SmartList<Square> squares;
    private final int height;
    private final int width;

    public Minefield(int rows, int cols) {
        this.height = rows;
        this.width = cols;
        Assert.isTrue(rows > 0 && cols > 0);

        this.squares = createSquares(rows, cols);
    }

    private static SmartList<Square> createSquares(int height, int width) {
        SmartList<Square> squares = new SmartList<Square>();
        for (int row=0; row<height; row++) {
            for (int column=0; column<width; column++) {
                squares.add(new Square(row, column));
            }
        }
        squares.freeze();
        return squares;
    }

    /**
     * Returns the Square object from the given zero-based coordinates.
     *
     * @throws IllegalArgumentException
     *             if the row and column parameters are not within the allowed
     *             coordinates.
     */
    public Square getSquare(int row, int col) {
        Assert.isTrue(validateCoordinates(row, col));

        return this.squares.get(row * this.width + col);
    }

    private boolean validateCoordinates(int row, int col) {
        return row >= 0 && col >= 0 && row < this.height && col < this.width;
    }

    /**
     * Returns an unmodifiable list of squares that are next to the given square
     * or connected with it by their corners.
     */
    public SmartList<Square> getNeighbors(Square square) {
        return this.squares.select(x -> x.isNeighborOf(square)).freeze();
    }

    /**
     * Returns an unmodifiable copy of this minefield's square list.
     * @return
     */
    public SmartList<Square> getSquares() {
        return new SmartList<Square>(this.squares).freeze();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Creates a String representation of this minefield. The String contains as many lines
     * - opened squares show the number of mines next to them
     */
    @Override
    public String toString() {
        String[] output = new String[getHeight()];
        for (int row=0; row < getHeight(); row++) {
            StringBuilder builder = new StringBuilder();
            for (int col=0; col < getWidth(); col++) {
                Square current = getSquare(row, col);
                builder.append(getCharForSquare(current));
            }
            output[row] = builder.toString();
        }
        return String.join("\n", output);
    }

    private char getCharForSquare(Square square) {
        if (square.isFlagged()) {
            return 'F';
        } if (square.isOpen() && square.isMine()) {
            return 'M';
        } else if (square.isOpen()) {
            int mineCount = getNeighbors(square).select(x -> x.isMine()).size();
            return String.valueOf(mineCount).charAt(0);
        } else {
            return '?';
        }
    }
}
