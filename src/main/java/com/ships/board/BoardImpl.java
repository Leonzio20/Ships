package com.ships.board;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import static com.ships.board.BoardConfiguration.BOARD_SIZE;

@AllArgsConstructor(staticName = "create")
@Getter(AccessLevel.PACKAGE)
class BoardImpl implements Board
{
  private final Map<Coordinates, ShootResult> board = new HashMap<>(BOARD_SIZE);

  @Override
  public void select(Coordinates coordinates, ShootResult shootResult)
  {
    board.put(coordinates, shootResult);
  }
}
