package com.ships.ship;

import com.google.common.collect.ImmutableSet;
import com.ships.board.BoardConfiguration;
import com.ships.board.Coordinates;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@NoArgsConstructor(staticName = "INSTANCE")
public final class ShipsGenerator
{
  private static final int NUMBER_OF_SHIPS = 5;

  private final List<Coordinates> availableShipCoordinates = new ArrayList<>(BoardConfiguration.ALL_COORDINATES);

  public List<Ship> generateRandomShips()
  {
    return IntStream.rangeClosed(1, NUMBER_OF_SHIPS)
        .mapToObj(i -> createOneShip())
        .collect(Collectors.toList());
  }

  private Ship createOneShip()
  {
    int random = ThreadLocalRandom.current().nextInt(availableShipCoordinates.size());
    Coordinates coordinates = availableShipCoordinates.remove(random);
    availableShipCoordinates.removeAll(calculateAdjectiveCoordinates(coordinates));
    return Ship.create(coordinates);
  }

  private Set<Coordinates> calculateAdjectiveCoordinates(Coordinates coordinates)
  {
    Coordinates left = Coordinates.of(Character.toChars(coordinates.getColumn() - 1)[0], coordinates.getRow());
    Coordinates right = Coordinates.of(Character.toChars(coordinates.getColumn() + 1)[0], coordinates.getRow());
    Coordinates up = Coordinates.of(coordinates.getColumn(), Character.toChars(coordinates.getRow() + 1)[0]);
    Coordinates down = Coordinates.of(coordinates.getColumn(), Character.toChars(coordinates.getRow() - 1)[0]);
    return ImmutableSet.of(left, right, up, down);
  }
}
