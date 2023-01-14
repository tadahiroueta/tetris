/**
 * Block.java  04/30/14
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */

import java.awt.Color;

public class Block
{
	private BoundedGrid<Block> grid;
	private Location location;
	private Color color;

	// Constructs a blue block, because blue is the greatest color ever!
	public Block()
	{
		color = Color.BLUE;
		grid = null;
		location = null;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public BoundedGrid<Block> getGrid() {
		return grid;
	}

	public Location getLocation() {
		return location;
	}

	// Removes this block from its grid.
	// Precondition:  this block is contained in a grid.
	public void removeSelfFromGrid()
	{
		grid.remove(location);
		location = null;
		grid = null;
	}

	// Puts this block into location loc of grid gr.
	// If there is another block at loc, it is removed.
	// Precondition:  (1) this block is not contained in a grid.
	//                (2) loc is valid in gr.
	public void putSelfInGrid(BoundedGrid<Block> gr, Location loc)
	{
		grid = gr;
		location = loc;
		Block toBeRemoved = grid.get(location);
		if (toBeRemoved != null) toBeRemoved.removeSelfFromGrid();
		grid.put(location, this);
	}

	// Moves this block to newLocation.
	// If there is another block at newLocation, it is removed.
	// Precondition:  (1) this block is contained in a grid.
	//                (2) newLocation is valid in the grid of this block.
	public void moveTo(Location newLocation)
	{
		grid.remove(location);
		location = newLocation;
		Block toBeRemoved = grid.get(location);
		if (toBeRemoved != null) toBeRemoved.removeSelfFromGrid();
		grid.put(location, this);
	}

	// Returns a string with the location and color of this block.
	public String toString()
	{
		return "Block[location=" + location + ",color=" + color + "]";
	}
}