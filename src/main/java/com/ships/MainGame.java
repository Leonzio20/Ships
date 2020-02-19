package com.ships;

import com.ships.board.Coordinates;
import com.ships.board.ShootResult;
import com.ships.player.Player;
import com.ships.player.PlayerInitializer;

public class MainGame
{
  private final Player human = PlayerInitializer.createHumanPlayer();
  private final Player computer = PlayerInitializer.createComputerPlayer();

  public void start()
  {
    Player currentPlayer = human;
    Player secondPlayer = computer;

    do
    {
      System.out.flush();
      currentPlayer.drawBoards();
      ShootResult shootResult = doTurn(currentPlayer, secondPlayer);
      if (shootResult == ShootResult.MISS)
      {
        Player sup = currentPlayer;
        currentPlayer = secondPlayer;
        secondPlayer = sup;
      }
    } while (!secondPlayer.loose());

    currentPlayer.wins();
  }

  private ShootResult doTurn(Player current, Player other)
  {
    Coordinates coordinatesToShoot = current.selectPointToShoot();
    return current.shootAt(coordinatesToShoot, other);
  }
}
