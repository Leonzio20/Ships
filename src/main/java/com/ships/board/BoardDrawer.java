package com.ships.board;

import com.google.common.collect.ImmutableList;
import com.ships.ship.Ship;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ships.board.BoardConfiguration.COLUMN_NAMES;
import static com.ships.board.BoardConfiguration.ROW_NAMES;

/**
 * Draws game board.
 */
@RequiredArgsConstructor
public final class BoardDrawer
{
  private final Board board;
  private final List<Ship> ships;

  /**
   * Draw given board.
   *
   * @param board to draw
   */
  public static void draw(Board board)
  {
    draw(board, ImmutableList.of());
  }

  public static void draw(Board board, List<Ship> ships)
  {
    new BoardDrawer(board, ships).drawBoard();
  }

  private void drawBoard()
  {
    System.out.print("  ");
    COLUMN_NAMES.forEach(name -> System.out.print(" " + name));
    System.out.println();
    ROW_NAMES.forEach(this::drawSingleRow);
    System.out.println();
  }

  private void drawSingleRow(int rowName)
  {
    Map<Coordinates, ShootResult> boardValues = ((BoardImpl) board).getBoard();
    Set<Coordinates> currentRow = boardValues.keySet()
        .stream()
        .filter(shootResult -> shootResult.getRow() == rowName)
        .collect(Collectors.toSet());

    String singleRow = String.format("%2d", rowName);
    singleRow += COLUMN_NAMES.stream()
        .map(column -> createCellValue(Coordinates.of(column, rowName), currentRow))
        .collect(Collectors.joining());
    System.out.println(singleRow);
  }

  private String createCellValue(Coordinates coordinates, Set<Coordinates> currentRow)
  {
    char cellValue = ' ';
    Optional<Coordinates> cell = currentRow.stream()
        .filter(coordinates::isInTheSameColumn)
        .findFirst();
    if (cell.isPresent())
    {
      cellValue = cell.map(((BoardImpl) board).getBoard()::get)
          .map(ShootResult::getShootValue)
          .get();
    } else if (ships.stream().anyMatch(s -> s.has(coordinates)))
    {
      cellValue = '#';
    }
    return String.format(" %s", cellValue);
  }
}
