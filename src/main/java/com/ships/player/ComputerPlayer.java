package com.ships.player;

import com.ships.board.BoardConfiguration;
import com.ships.board.BoardDrawer;
import com.ships.board.Coordinates;
import com.ships.ship.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class ComputerPlayer extends PlayerImpl
{
  private final List<Coordinates> availableCoordinates = new ArrayList<>(BoardConfiguration.ALL_COORDINATES);

  private ComputerPlayer(List<Ship> ships)
  {
    super(ships);
  }

  static ComputerPlayer with(List<Ship> ships)
  {
    return new ComputerPlayer(ships);
  }

  @Override
  public void drawBoards()
  {
    System.out.println(" - Computer turn - ");
    System.out.println(" - Opponent board - ");
    BoardDrawer.draw(getOpponentBoard());
  }

  @Override
  public Coordinates selectPointToShoot()
  {
    int index = ThreadLocalRandom.current().nextInt(availableCoordinates.size());
    return availableCoordinates.remove(index);
  }

  @Override
  public void wins()
  {
    System.out.println(" *** Computer wins *** ");
    BoardDrawer.draw(getOpponentBoard());
  }
}
