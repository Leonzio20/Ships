package com.ships.player;

import com.ships.board.Coordinates;
import com.ships.board.ShootResult;

public interface Player
{
  void drawBoards();

  Coordinates selectPointToShoot();

  ShootResult shootAt(Coordinates coordinates, Player otherPlayer);

  boolean loose();

  void wins();
}
