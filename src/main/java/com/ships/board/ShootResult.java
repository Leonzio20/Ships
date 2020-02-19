package com.ships.board;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents result of shooting at ship on the board.
 */
@Getter(AccessLevel.PACKAGE)
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum ShootResult
{
  HIT('x'), MISS('*');

  private final Character shootValue;
}
