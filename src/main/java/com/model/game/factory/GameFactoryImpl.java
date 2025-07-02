package com.model.game.factory;

import com.model.board.Board;
import com.model.board.factory.StartingBoardFactory;
import com.model.board.factory.StartingBoardFactoryImpl;
import com.model.editor.StringBoard;
import com.model.game.Game;
import com.model.game.controllers.ClassicGameController;
import com.model.game.controllers.GameController;
import com.model.game.controllers.PieceSwapVariantGameController;
import com.model.game.types.GameType;
import com.model.movement.manager.BombVariantMovementManager;
import com.model.movement.manager.ChessProblemsMovementManager;
import com.model.movement.manager.ClassicMovementManager;
import com.model.movement.manager.PieceSwapVariantMovementManager;
import com.model.piece.movements.*;
import com.model.player.pair.PlayerPair;
import com.model.problems.Problem;

public class GameFactoryImpl implements GameFactory {

    private final StartingBoardFactory startingBoardFactory = new StartingBoardFactoryImpl();

    private Game allClassicApartFromMovementStrategy(final PlayerPair players,
                                                     final PieceMovementStrategies pieceMovementStrategy, final GameType type) {
        final GameController gameController = new ClassicGameController(this.startingBoardFactory.classicBoard(players),
                pieceMovementStrategy, players);

        return Game.builder().type(type).gameController(gameController)
                .movementManager(new ClassicMovementManager(gameController)).build();
    }

    private Game allClassicDifferentBoard(final PlayerPair players, final Board startingBoard, final GameType type) {
        final GameController gameController = new ClassicGameController(startingBoard,
                new ClassicNoCastlingPieceMovementStrategies(), players);

        return Game.builder().type(type).gameController(gameController)
                .movementManager(new ClassicMovementManager(gameController)).build();
    }

    @Override
    public Game classicGame(final PlayerPair players) {
        return this.allClassicApartFromMovementStrategy(players, new ClassicWithCastlingPieceMovementStrategies(),
                GameType.CLASSIC);
    }

    @Override
    public Game pawnMovemementVariantGame(final PlayerPair players) {
        return this.allClassicApartFromMovementStrategy(players, new PawnVariantPieceMovementStrategies(),
                GameType.PAWN_MOVEMENT_VARIANT);
    }

    @Override
    public Game kingMovesAsQueenVariantGame(final PlayerPair players) {
        return this.allClassicApartFromMovementStrategy(players, new KingAsQueenPieceMovementStrategies(),
                GameType.KING_MOVES_LIKE_QUEEN);
    }

    @Override
    public Game everyPieceMovesLikeRooksVariantGame(final PlayerPair players) {
        return this.allClassicApartFromMovementStrategy(players, new EveryoneMovesLikeRooksPieceMovementStrategies(),
                GameType.EVERYONE_MOVES_LIKE_A_ROOK);
    }

    @Override
    public Game rookBishopMovementVariantGame(final PlayerPair players) {
        return this.allClassicApartFromMovementStrategy(players, new RookAndBishopVariantPieceMovementStrategies(),
                GameType.ROOK_AND_BISHOP_MOVEMENT_VARIANT);
    }

    @Override
    public Game pawnHordeVariantGame(final PlayerPair players) {
        return this.allClassicDifferentBoard(players, this.startingBoardFactory.pawnHordeBoard(players),
                GameType.PAWN_HORDE_VARIANT);
    }

    @Override
    public Game threeColumnsVariantGame(final PlayerPair players) {
        return this.allClassicDifferentBoard(players, this.startingBoardFactory.threeColumnsBoard(players),
                GameType.THREE_COLUMNS_VARIANT);
    }

    @Override
    public Game oneDimensionVariantGame(final PlayerPair players) {
        final GameController gameController = new ClassicGameController(
                this.startingBoardFactory.oneDimensionBoard(players), new OneDimensionPieceMovementStrategies(),
                players);

        return Game.builder().type(GameType.ONE_DIMENSION_VARIANT).gameController(gameController)
                .movementManager(new ClassicMovementManager(gameController)).build();
    }

    @Override
    public Game pieceSwapVariantGame(final PlayerPair players) {
        final GameController gameController = new PieceSwapVariantGameController(
                this.startingBoardFactory.classicBoard(players), new ClassicNoCastlingPieceMovementStrategies(),
                players);

        return Game.builder().type(GameType.PIECE_SWAP_VARIANT).gameController(gameController)
                .movementManager(new PieceSwapVariantMovementManager(gameController)).build();
    }

    @Override
    public Game bombVariantGame(final PlayerPair players) {
        final GameController gameController = new ClassicGameController(this.startingBoardFactory.classicBoard(players),
                new ClassicNoCastlingPieceMovementStrategies(), players);

        return Game.builder().type(GameType.BOMB_VARIANT).gameController(gameController)
                .movementManager(new BombVariantMovementManager(gameController)).build();
    }

    @Override
    public Game chessProblemGameType(final PlayerPair players, final Problem chessProblem) {
        final PieceMovementStrategies pmsf = new ClassicNoCastlingPieceMovementStrategies();
        final GameController gameController = new ClassicGameController(chessProblem.getStartingBoard(), pmsf, players);

        return Game.builder().type(GameType.CHESS_PROBLEM).gameController(gameController)
                .movementManager(new ChessProblemsMovementManager(gameController, chessProblem.getCorrectMoves()))
                .build();
    }

    @Override
    public Game customizedBoardVariantGame(final PlayerPair players, final StringBoard startingBoardInfo) {
        return this.allClassicDifferentBoard(
                players, this.startingBoardFactory.customizedBoard(startingBoardInfo.getBoard(),
                        startingBoardInfo.getColumns(), startingBoardInfo.getRows(), players),
                GameType.CUSTOM_BOARD_VARIANT);
    }

}
