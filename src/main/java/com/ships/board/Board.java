package com.ships.board;

public interface Board
{
  static Board create()
  {
    return BoardImpl.create();
  }

  void select(Coordinates coordinates, ShootResult shootResult);
}
