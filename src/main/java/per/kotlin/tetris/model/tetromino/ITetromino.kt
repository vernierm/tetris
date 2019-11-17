package per.kotlin.tetris.model.tetromino

import per.kotlin.tetris.model.tetromino.Orientation.*
import java.awt.Color

class ITetromino(
        private var position: Pair<Int, Int>,
        private var orientation: Orientation = NORTH
) : Tetromino {
    override fun getPosition(): Pair<Int, Int> = position

    override fun setPosition(position: Pair<Int, Int>) {
        this.position = position
    }

    override fun getOrientation(): Orientation = orientation

    override fun setOrientation(orientation: Orientation) {
        this.orientation = orientation
    }

    override fun getColor(): Color = Color.GREEN

    override fun getCoverage(): List<Pair<Int, Int>> =
            when (orientation) {
                EAST, WEST -> listOf(
                        Pair(position.first - 1, position.second),
                        Pair(position.first, position.second),
                        Pair(position.first + 1, position.second),
                        Pair(position.first + 2, position.second)
                )
                NORTH, SOUTH -> listOf(
                        Pair(position.first, position.second - 1),
                        Pair(position.first, position.second),
                        Pair(position.first, position.second + 1),
                        Pair(position.first, position.second + 2)
                )
            }

    override fun clone(): Tetromino = ITetromino(position, orientation)
}
