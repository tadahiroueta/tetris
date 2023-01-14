/**
 * Tetrad.java  4/30/2014
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */

import java.awt.Color;
import java.util.*;

// Represents a Tetris piece.
public class Tetrad
{
	private Block[] blocks;	// The blocks for the piece.

	// Constructs a Tetrad.
	public Tetrad(BoundedGrid<Block> grid)
	{
		blocks = new Block[4];
		for (int i = 0; i < 4; i++) {
			blocks[i] = new Block();
		}
		Color randomColor;
		int randomNumber = (int) (Math.random() * 7);
		switch (randomNumber) {
			case 0: {
				randomColor = Color.red;
				addToLocations(grid, new Location[] {
					new Location(0, 4),
					new Location(0, 3),
					new Location(0, 5),
					new Location(0, 6),
				});
				break;
			}
			case 1: {
				randomColor = Color.gray;
				addToLocations(grid, new Location[] {
					new Location(0, 4),
					new Location(0, 3),
					new Location(0, 5),
					new Location(1, 4),
				});
				break;
			}
			case 2: {
				randomColor = Color.cyan;
				addToLocations(grid, new Location[] {
						new Location(0, 4),
						new Location(0, 5),
						new Location(1, 4),
						new Location(1, 5),
				});
				break;
			}
			case 3: {
				randomColor = Color.yellow;
				addToLocations(grid, new Location[] {
						new Location(0, 5),
						new Location(0, 3),
						new Location(0, 4),
						new Location(1, 3),
				});
				break;
			}
			case 4: {
				randomColor = Color.magenta;
				addToLocations(grid, new Location[] {
						new Location(0, 5),
						new Location(0, 3),
						new Location(0, 4),
						new Location(1, 5),
				});
				break;
			}
			case 5: {
				randomColor = Color.blue;
				addToLocations(grid, new Location[] {
						new Location(0, 4),
						new Location(0, 5),
						new Location(1, 3),
						new Location(1, 4),
				});
				break;
			}
			default: {
				randomColor = Color.green;
				addToLocations(grid, new Location[] {
					new Location(0, 4),
					new Location(0, 3),
					new Location(1, 4),
					new Location(1, 5),
				});
				break;
			}
		}
		for (Block block: blocks)
			block.setColor(randomColor);
	}


	// Postcondition: Attempts to move this tetrad deltaRow rows down and
	//						deltaCol columns to the right, if those positions are
	//						valid and empty.
	//						Returns true if successful and false otherwise.
	public boolean translate(int deltaRow, int deltaCol)
	{
		BoundedGrid<Block> grid = blocks[0].getGrid();
		Location[] oldLocations = removeBlocks();
		Location[] locations = new Location[4];
		for (int i = 0; i < 4; i++) {
			Location oldLocation = oldLocations[i];
			locations[i] = new Location(oldLocation.getRow() + deltaRow, oldLocation.getCol() + deltaCol);
			try {
				if (grid.get(locations[i]) != null)
					throw new Exception("purposeful exception");
			} catch (Exception e) {
				addToLocations(grid, oldLocations);
				return false;
			}
		}
		addToLocations(grid, locations);
		return true;
	}

	// Postcondition: Attempts to rotate this tetrad clockwise by 90 degrees
	//                about its center, if the necessary positions are empty.
	//                Returns true if successful and false otherwise.
	public boolean rotate()
	{
		BoundedGrid<Block> grid = blocks[0].getGrid();
		Location[] oldLocations = removeBlocks(), locations = new Location[4];
		Location origin = oldLocations[0];
		int originRow = origin.getRow(), originCol = origin.getCol();
		for (int i = 0; i < 4; i++) {
			Location oldLocation = oldLocations[i];
			locations[i] = new Location(originRow - originCol + oldLocation.getCol(), originRow + originCol - oldLocation.getRow());
			try {
				if (grid.get(locations[i]) != null)
					throw new Exception("purposeful exception");
			} catch (Exception e) {
				addToLocations(grid, oldLocations);
				return false;
			}
		}
		addToLocations(grid, locations);
		return true;
	}


	// Precondition:  The elements of blocks are not in any grid;
	//                locs.length = 4.
	// Postcondition: The elements of blocks have been put in the grid
	//                and their locations match the elements of locs.
	private void addToLocations(BoundedGrid<Block> grid, Location[] locs)
	{
		for (int i = 0; i < 4; i++)
			blocks[i].putSelfInGrid(grid, locs[i]);
	}

	// Precondition:  The elements of blocks are in the grid.
	// Postcondition: The elements of blocks have been removed from the grid
	//                and their old locations returned.
	private Location[] removeBlocks()
	{
		Location[] oldLocations = new Location[4];
		for (int i = 0; i < 4; i++) {
			Block block = blocks[i];
			oldLocations[i] = block.getLocation();
			block.removeSelfFromGrid();
		}
		return oldLocations;
	}

	// Postcondition: Returns true if each of the elements of locs is valid
	//                and empty in grid; false otherwise.
	private boolean areEmpty(BoundedGrid<Block> grid, Location[] locs)
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");
	}
}
