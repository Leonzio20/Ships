package com.ships.ship;

import com.google.common.collect.ImmutableSet;
import com.ships.board.Coordinates;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Ship
{
  private final Set<Coordinates> coordinates;
  private final Set<Coordinates> hits = new HashSet<>();

  public static Ship create(Coordinates... coordinates)
  {
    return new Ship(ImmutableSet.of(coordinates));
  }

  public boolean isHit(Coordinates coordinates)
  {
    boolean hit = this.coordinates.contains(coordinates);
    if (hit)
    {
      hits.add(coordinates);
    }
    return hit;
  }

  public boolean isSunk()
  {
    return hits.containsAll(coordinates);
  }

  public boolean has(Coordinates coordinates)
  {
    return this.coordinates.contains(coordinates);
  }
}
