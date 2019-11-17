package per.kotlin.tetris.model.tetromino

import java.awt.Color

class OTetromino(
        private var position: Pair<Int, Int>
) : Tetromino {
    override fun getPosition(): Pair<Int, Int> = position

    override fun setPosition(position: Pair<Int, Int>) {
        this.position = position
    }

    override fun getOrientation(): Orientation = Orientation.NORTH

    override fun setOrientation(orientation: Orientation) = Unit

    override fun getColor(): Color = Color.BLUE

    override fun getCoverage(): List<Pair<Int, Int>> = listOf(
            Pair(position.first, position.second),
            Pair(position.first + 1, position.second),
            Pair(position.first, position.second + 1),
            Pair(position.first + 1, position.second + 1)
    )

    override fun clone(): Tetromino = OTetromino(position)
}
