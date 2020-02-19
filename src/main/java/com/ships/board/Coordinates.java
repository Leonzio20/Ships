package com.ships.board;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@EqualsAndHashCode
@Getter
@RequiredArgsConstructor(staticName = "of")
public class Coordinates
{
  private final char column;
  private final int row;

  public boolean sameAs(String rawValue)
  {
    return Objects.equals(column + "" + row, rawValue.toUpperCase()) || Objects.equals(row + "" + column, rawValue.toUpperCase());
  }

  public boolean isInTheSameColumn(Coordinates coordinates)
  {
    return Objects.equals(column, coordinates.column);
  }
}
