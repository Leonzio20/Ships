package com.ships.player;

import com.ships.board.Coordinates;
import com.ships.ship.Ship;
import com.ships.ship.ShipsGenerator;

public final class PlayerInitializer
{
  public static Player createComputerPlayer()
  {
    return ComputerPlayer.with(ShipsGenerator.INSTANCE().generateRandomShips());
  }

  public static Player createHumanPlayer()
  {
    return HumanPlayer.with(ShipsGenerator.INSTANCE().generateRandomShips());
  }
}
