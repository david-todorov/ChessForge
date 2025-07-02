package com.model.game.types;

import com.model.game.Game;
import com.model.game.factory.GameFactory;
import com.model.game.factory.GameFactoryImpl;
import com.model.player.pair.PlayerPair;

public enum GameType {

    CLASSIC("Classic", GameFactory::classicGame, GameTypeDescription.classicGameType()),


    PAWN_HORDE_VARIANT("Pawn Horde", GameFactory::pawnHordeVariantGame, GameTypeDescription.pawnHordeVariant()),


    PAWN_MOVEMENT_VARIANT("Spectacular Pawn", GameFactory::pawnMovemementVariantGame,
            GameTypeDescription.pawnMovemementVariant()),


    PIECE_SWAP_VARIANT("Swappiness", GameFactory::pieceSwapVariantGame, GameTypeDescription.pieceSwapVariant()),


    THREE_COLUMNS_VARIANT("3-Col", GameFactory::threeColumnsVariantGame, GameTypeDescription.threeColumnsVariant()),


    BOMB_VARIANT("Bombastic", GameFactory::bombVariantGame, GameTypeDescription.bombVariant()),


    ONE_DIMENSION_VARIANT("1-Dimension", GameFactory::oneDimensionVariantGame,
            GameTypeDescription.oneDimensionVariant()),


    ROOK_AND_BISHOP_MOVEMENT_VARIANT("Rook & Bishop", GameFactory::rookBishopMovementVariantGame,
            GameTypeDescription.rookBishopMovementVariant()),


    EVERYONE_MOVES_LIKE_A_ROOK("EveryRook", GameFactory::everyPieceMovesLikeRooksVariantGame,
            GameTypeDescription.everyoneMovesLikeRooks()),


    KING_MOVES_LIKE_QUEEN("QueenK", GameFactory::kingMovesAsQueenVariantGame, GameTypeDescription.kingMovesLikeQueen()),


    CUSTOM_BOARD_VARIANT("Custom Variant", null, null),


    CHESS_PROBLEM("Chess Problem", null, null);

    private final String name;
    private final GameGenerationStrategy gameGeneratorStrategy;
    private final GameFactory gameTypeFactory = new GameFactoryImpl();
    private final String description;

    GameType(final String name, final GameGenerationStrategy gameTypeGeneratorStrategy,
             final String gameTypeDescription) {
        this.name = name;
        this.gameGeneratorStrategy = gameTypeGeneratorStrategy;
        this.description = gameTypeDescription;
    }


    public Game getGameInstance(final PlayerPair players) {
        return this.gameGeneratorStrategy.generate(this.gameTypeFactory, players);
    }


    public String getDescription() {
        return this.description;
    }


    public String getName() {
        return this.name;
    }

}
