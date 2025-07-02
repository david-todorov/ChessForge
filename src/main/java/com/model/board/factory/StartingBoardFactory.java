package com.model.board.factory;

import com.model.board.Board;
import com.model.player.pair.PlayerPair;

public interface StartingBoardFactory {

    Board classicBoard(PlayerPair players);

    Board pawnHordeBoard(PlayerPair players);

    Board threeColumnsBoard(PlayerPair players);

    Board oneDimensionBoard(PlayerPair players);

    Board customizedBoard(String startingBoard, int columns, int rows, PlayerPair players);

    Board problemOneBoard(PlayerPair players);

    Board problemTwoBoard(PlayerPair players);

    Board problemThreeBoard(PlayerPair players);

    Board problemFourBoard(PlayerPair players);

    Board problemFiveBoard(PlayerPair players);
}
