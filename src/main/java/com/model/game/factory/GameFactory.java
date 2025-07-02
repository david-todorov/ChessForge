package com.model.game.factory;

import com.model.editor.StringBoard;
import com.model.game.Game;
import com.model.player.pair.PlayerPair;
import com.model.problems.Problem;

public interface GameFactory {

    Game classicGame(PlayerPair players);

    Game pawnHordeVariantGame(PlayerPair players);

    Game pieceSwapVariantGame(PlayerPair players);

    Game pawnMovemementVariantGame(PlayerPair players);

    Game threeColumnsVariantGame(PlayerPair players);

    Game oneDimensionVariantGame(PlayerPair players);

    Game rookBishopMovementVariantGame(PlayerPair players);

    Game bombVariantGame(PlayerPair players);

    Game everyPieceMovesLikeRooksVariantGame(PlayerPair players);

    Game kingMovesAsQueenVariantGame(PlayerPair players);

    Game chessProblemGameType(PlayerPair players, Problem chessProblem);

    Game customizedBoardVariantGame(PlayerPair players, StringBoard startingBoardInfo);

}
