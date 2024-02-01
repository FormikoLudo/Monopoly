package game;

import java.util.ArrayList;

/**
 * This class represents a bot player that can play at the place of the real players.
 */
public class BotPlayer extends Player
{
  /**
   * Constructor of a BotPlayer.
   * @param name The name of the bot.
   * @param id   The id of the player.
   */
  public BotPlayer(String name, int id)
  {
    super(name,id);
  }
}
