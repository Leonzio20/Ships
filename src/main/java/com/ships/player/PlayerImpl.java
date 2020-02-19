package com.ships.player;

import com.ships.board.Board;
import com.ships.board.Coordinates;
import com.ships.board.ShootResult;
import com.ships.ship.Ship;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter(AccessLevel.PACKAGE)
abstract class PlayerImpl implements Player
{
  private final Board myBoard = Board.create();
  private final Board opponentBoard = Board.create();
  private final List<Ship> ships;

  private Coordinates lastSuccessCoordinates;

  @Override
  public ShootResult shootAt(Coordinates coordinates, Player opponent)
  {
    // check already shootted point !!!!
    ShootResult shootResult = tryToShootAtShip(coordinates, (PlayerImpl) opponent);
    lastSuccessCoordinates = shootResult == ShootResult.HIT ? coordinates : null;
    ((PlayerImpl) opponent).myBoard.select(coordinates, shootResult);
    opponentBoard.select(coordinates, shootResult);
    return shootResult;
  }

  private ShootResult tryToShootAtShip(Coordinates coordinates, PlayerImpl opponent)
  {
    return opponent.ships
        .stream()
        .filter(ship -> ship.isHit(coordinates))
        .findFirst()
        .map(ship -> ShootResult.HIT)
        .orElse(ShootResult.MISS);
  }

  @Override
  public boolean loose()
  {
    return ships.stream().allMatch(Ship::isSunk);
  }
}
