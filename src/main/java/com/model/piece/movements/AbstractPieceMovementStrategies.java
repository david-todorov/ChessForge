package com.model.piece.movements;


import com.model.board.Board;
import com.model.board.position.BoardPosition;
import com.model.board.position.BoardPositionImpl;
import com.model.piece.Piece;
import com.model.piece.PieceType;
import com.other.Pair;
import com.other.functional.TriFunction;
import one.util.streamex.StreamEx;


import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;


public abstract class AbstractPieceMovementStrategies implements PieceMovementStrategies {


    protected static final int SINGLE_INCREMENT = 1;

    protected static final int DOUBLE_INCREMENT = 2;

    private final BiFunction<BoardPosition, Pair<Integer, Integer>, BoardPosition> sumBoardPosWithPair = (pos,
                                                                                                          pair) -> new BoardPositionImpl(pos.getX() + pair.getX(), pos.getY() + pair.getY());

    private final Function<Pair<Integer, Integer>, UnaryOperator<BoardPosition>> unaryOperatorFromAxis = (
            axis) -> (p) -> this.sumBoardPosWithPair.apply(p, axis);

    private final Function<Board, Integer> fromBoardToMaximumLimit = (board) -> board.getRows() + board.getColumns();

    private final TriFunction<Piece, Vectors, Board, Set<BoardPosition>> specularNoLimitDirection = (piece, axis,
                                                                                                     board) -> StreamEx.of(
                    this.getDestinationsFromFunction(this.unaryOperatorFromAxis.apply(axis.getAxis()), piece, board,
                            this.fromBoardToMaximumLimit.apply(board)),
                    this.getDestinationsFromFunction(this.unaryOperatorFromAxis.apply(axis.getOpposite()), piece, board,
                            this.fromBoardToMaximumLimit.apply(board)))
                    .flatMap(Set::stream).toSet();

    private final Map<PieceType, Function<Piece, MovementStrategy>> fromPieceTypeToStrategy = new EnumMap<>(
            PieceType.class) {
        private static final long serialVersionUID = 1L;
        {
            put(PieceType.PAWN, AbstractPieceMovementStrategies.this::getPawnMovementStrategy);
            put(PieceType.ROOK, AbstractPieceMovementStrategies.this::getRookMovementStrategy);
            put(PieceType.KNIGHT, AbstractPieceMovementStrategies.this::getKnightMovementStrategy);
            put(PieceType.BISHOP, AbstractPieceMovementStrategies.this::getBishopMovementStrategy);
            put(PieceType.QUEEN, AbstractPieceMovementStrategies.this::getQueenMovementStrategy);
            put(PieceType.KING, AbstractPieceMovementStrategies.this::getKingMovementStrategy);
        }
    };

    protected final Set<BoardPosition> getDestinationsFromFunction(final UnaryOperator<BoardPosition> function,
            final Piece piece, final Board board, final int limit) {

        final Predicate<BoardPosition> isEnemyOrEmpty = (pos) -> board.getPieceAtPosition(pos)
                .map(p -> !p.getPlayer().equals(piece.getPlayer())).orElse(true);

        final Predicate<BoardPosition> isPositionEmpty = (pos) -> board.getPieceAtPosition(pos).isEmpty();

        return StreamEx.iterate(function.apply(piece.getPiecePosition()), function).takeWhile(board::contains)
                .takeWhileInclusive(isPositionEmpty).filter(isEnemyOrEmpty).limit(limit).toSet();
    }

    @Override
    public final MovementStrategy getPieceMovementStrategy(final Piece piece) {
        return this.fromPieceTypeToStrategy.get(piece.getType()).apply(piece);
    }


    protected final TriFunction<Piece, Vectors, Board, Set<BoardPosition>> getSpecularNoLimitDirection() {
        return this.specularNoLimitDirection;
    }


    protected abstract MovementStrategy getPawnMovementStrategy(Piece piece);


    protected abstract MovementStrategy getRookMovementStrategy(Piece piece);


    protected abstract MovementStrategy getKnightMovementStrategy(Piece piece);

    protected abstract MovementStrategy getBishopMovementStrategy(Piece piece);


    protected abstract MovementStrategy getQueenMovementStrategy(Piece piece);

    protected abstract MovementStrategy getKingMovementStrategy(Piece piece);
}
