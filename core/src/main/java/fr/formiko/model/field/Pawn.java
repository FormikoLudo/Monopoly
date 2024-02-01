package field;
import game.*;
/**
 * A Pawn is the representation of the player on the board, it indicates at which position
 * the player is.
 */
public class Pawn {

    /**
     * Indicates to which player this pawn belongs.
     */
    private final int id;

    /**
     * Indicates the position of this pawn on the board.
     */
    private int position;

    /**
     * Instantiates a Pawn object positioned at the beginning of field.
     * @param id The id of this pawn.
     */
    public Pawn(int id){
    this.id = id;
    position = 0;
    }

    /**
     * Moves this pawn by the specified amount of tiles.
     * @param move The number of tiles this pawn has to move
     * return True if the player passed by the start case.
     */
    public boolean move(int move)
    {
      //The position before the move.
      int pos = position;
      position = (position + move) % Field.getLength();
      return(position < pos);
    }

  /**
   * Getter of the location of the pawn.
   * @return The position of the pawn on the field.
   */
    public int getLocation()
    {
      return this.position;
    }

    /**
     * Setter of location.
     * @param location The position of the pawn on the field.
     */
    public void setLocation(int location)
    {
      this.position = location ;
    }

    public int getId()
    {
            return this.id;
    }
}
