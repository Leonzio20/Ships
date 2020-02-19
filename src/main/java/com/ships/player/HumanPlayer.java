package com.ships.player;

import com.ships.board.BoardConfiguration;
import com.ships.board.BoardDrawer;
import com.ships.board.Coordinates;
import com.ships.ship.Ship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

class HumanPlayer extends PlayerImpl
{
  private HumanPlayer(List<Ship> ships)
  {
    super(ships);
  }

  static HumanPlayer with(List<Ship> ships)
  {
    return new HumanPlayer(ships);
  }

  @Override
  public Coordinates selectPointToShoot()
  {
    System.out.println("-Select point:");
    Optional<Coordinates> givenCoordinates = readCoordinates();
    return givenCoordinates.orElseGet(this::selectPointToShoot);
  }

  @Override
  public void drawBoards()
  {
    System.out.println(" - Human player turn - ");
    BoardDrawer.draw(getMyBoard(), getShips());
    System.out.println(" - Opponent board - ");
    BoardDrawer.draw(getOpponentBoard());
  }

  @Override
  public void wins()
  {
    System.out.println(" *** You Win *** ");
    BoardDrawer.draw(getOpponentBoard());
  }

  private Optional<Coordinates> readCoordinates()
  {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try
    {
      String input = reader.readLine();
      return BoardConfiguration.ALL_COORDINATES
          .stream()
          .filter(c -> c.sameAs(input))
          .findFirst();
    } catch (IOException e)
    {
      return readCoordinates();
    }
  }
}
