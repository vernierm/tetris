package per.kotlin.tetris.model.tetromino

import java.awt.Color

interface Tetromino {
    fun getPosition(): Pair<Int, Int>
    fun setPosition(position: Pair<Int, Int>)
    fun getOrientation(): Orientation
    fun setOrientation(orientation: Orientation)
    fun getColor(): Color
    fun getCoverage(): List<Pair<Int, Int>>
    fun clone(): Tetromino
}
