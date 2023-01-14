/**
 * Tetris.java  4/30/2014
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */

// Represents a Tetris game.
public class Tetris implements ArrowListener
{
	private BoundedGrid<Block> grid;	// The grid containing the Tetris pieces.
	private BlockDisplay display;		// Displays the grid.
	private Tetrad activeTetrad;		// The active Tetrad (Tetris Piece).

	// Constructs a Tetris Game
	public Tetris()
	{
		grid = new BoundedGrid<Block>(20, 10);
		display = new BlockDisplay(grid);
		display.setTitle("Tetris");
		display.setArrowListener(this);
		activeTetrad = new Tetrad(grid);
		play();
	}

	// Play the Tetris Game
	public void play()
	{
		new Block().putSelfInGrid(grid, new Location(19, 0));
		new Block().putSelfInGrid(grid, new Location(19, 1));
		new Block().putSelfInGrid(grid, new Location(19, 2));
		new Block().putSelfInGrid(grid, new Location(19, 3));
		new Block().putSelfInGrid(grid, new Location(19, 4));
		new Block().putSelfInGrid(grid, new Location(19, 5));
		new Block().putSelfInGrid(grid, new Location(19, 6));
		new Block().putSelfInGrid(grid, new Location(19, 7));
		new Block().putSelfInGrid(grid, new Location(19, 8));
		while (true) {
			display.showBlocks();
			sleep(1);
			if (!activeTetrad.translate(1, 0)) {
				for (int rowI = 0; rowI < grid.getNumRows(); rowI++)
					if (isCompletedRow(rowI))
						clearRow(rowI);
				activeTetrad = new Tetrad(grid);
			}
		}
	}


	// Precondition:  0 <= row < number of rows
	// Postcondition: Returns true if every cell in the given row
	//                is occupied; false otherwise.
	private boolean isCompletedRow(int row)
	{
		for (int i = 0; i < grid.getNumCols(); i++)
			if (grid.get(new Location(row, i)) == null)
				return false;
		return true;
	}

	// Precondition:  0 <= row < number of rows;
	//                The given row is full of blocks.
	// Postcondition: Every block in the given row has been removed, and
	//                every block above row has been moved down one row.
	private void clearRow(int row)
	{
		for (int i = 0; i < grid.getNumCols(); i++)
			grid.get(new Location(row, i)).removeSelfFromGrid();
		for (int rowI = grid.getNumRows() - 2; rowI >=0; rowI--)
			for (int colI = 0; colI < grid.getNumCols(); colI++) {
				Block block = grid.get(new Location(rowI, colI));
				if (block != null)
					block.moveTo(new Location(rowI + 1, colI));
			}
	}

	// Postcondition: All completed rows have been cleared.
	private void clearCompletedRows()
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	// Sleeps (suspends the active thread) for duration seconds.
	private void sleep(double duration)
	{
		final int MILLISECONDS_PER_SECOND = 1000;

		int milliseconds = (int)(duration * MILLISECONDS_PER_SECOND);

		try
		{
			Thread.sleep(milliseconds);
		}
		catch (InterruptedException e)
		{
			System.err.println("Can't sleep!");
		}
	}


	// Creates and plays the Tetris game.
	public static void main(String[] args)
	{
		Tetris obj = new Tetris();
	}

	@Override
	public void upPressed() {
		activeTetrad.rotate();
	}

	@Override
	public void downPressed() {
		activeTetrad.translate(1, 0);
	}

	@Override
	public void leftPressed() {
		activeTetrad.translate(0, -1);
	}

	@Override
	public void rightPressed() {
		activeTetrad.translate(0, 1);
	}
}
