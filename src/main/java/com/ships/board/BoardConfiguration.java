package com.ships.board;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class BoardConfiguration
{
  public static final int BOARD_SIZE = 10;

  public static final List<Character> COLUMN_NAMES = IntStream.rangeClosed('A', 'Z').mapToObj(var -> (char) var).collect(Collectors.toList()).subList(0, BOARD_SIZE);
  public static final List<Integer> ROW_NAMES = IntStream.rangeClosed(1, BOARD_SIZE).boxed().collect(Collectors.toList());

  public static final List<Coordinates> ALL_COORDINATES = allCoordinates();

  private static List<Coordinates> allCoordinates()
  {
    return COLUMN_NAMES.stream()
        .flatMap(column -> ROW_NAMES.stream()
            .map(row -> Coordinates.of(column, row)))
        .collect(Collectors.toList());
  }
}
