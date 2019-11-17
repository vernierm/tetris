package per.kotlin.tetris.model.tetromino

import java.awt.Color

class LTetromino(
        private var position: Pair<Int, Int>,
        private var orientation: Orientation = Orientation.NORTH
) : Tetromino {
    override fun getPosition(): Pair<Int, Int> = position

    override fun setPosition(position: Pair<Int, Int>) {
        this.position = position
    }

    override fun getOrientation(): Orientation = orientation

    override fun setOrientation(orientation: Orientation) {
        this.orientation = orientation
    }

    override fun getColor(): Color = Color.CYAN

    override fun getCoverage(): List<Pair<Int, Int>> =
            when (orientation) {
                Orientation.NORTH -> listOf(
                        Pair(position.first + 1, position.second),
                        Pair(position.first, position.second),
                        Pair(position.first, position.second + 1),
                        Pair(position.first, position.second + 2)
                )
                Orientation.EAST -> listOf(
                        Pair(position.first, position.second - 1),
                        Pair(position.first, position.second),
                        Pair(position.first + 1, position.second),
                        Pair(position.first + 2, position.second)
                )
                Orientation.SOUTH -> listOf(
                        Pair(position.first - 1, position.second),
                        Pair(position.first, position.second),
                        Pair(position.first, position.second - 1),
                        Pair(position.first, position.second - 2)
                )
                Orientation.WEST -> listOf(
                        Pair(position.first, position.second + 1),
                        Pair(position.first, position.second),
                        Pair(position.first - 1, position.second),
                        Pair(position.first - 2, position.second)
                )
            }

    override fun clone(): Tetromino = LTetromino(position, orientation)
}