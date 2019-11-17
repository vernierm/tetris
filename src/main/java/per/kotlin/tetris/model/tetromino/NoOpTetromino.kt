package per.kotlin.tetris.model.tetromino

import java.awt.Color

class NoOpTetromino : Tetromino {
    override fun getPosition(): Pair<Int, Int> = Pair(-1, -1)

    override fun setPosition(position: Pair<Int, Int>) {
    }

    override fun getOrientation(): Orientation = Orientation.NORTH

    override fun setOrientation(orientation: Orientation) {
    }

    override fun getColor(): Color = Color.BLACK

    override fun getCoverage(): List<Pair<Int, Int>> = emptyList()

    override fun clone(): Tetromino = this
}