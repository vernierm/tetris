package per.kotlin.tetris.model

import per.kotlin.tetris.model.tetromino.Tetromino
import java.awt.Color

class BoardModelImpl(
        private val boardWidth: Int,
        private val boardHeight: Int
) : BoardModel {
    private val grid = Array(boardHeight) { Array(boardWidth) { Color.BLACK } }
    private val listeners = mutableListOf<BoardModelListener>()

    private lateinit var tetromino: Tetromino

    override fun isValid(tetromino: Tetromino) = tetromino.getCoverage().all { (x, y) ->
        isValidEmptySpace(x, y)
    }

    private fun isInsideBoard(x: Int, y: Int) = x in 0 until boardHeight && y in 0 until boardWidth

    private fun isValidEmptySpace(x: Int, y: Int) = isInsideBoard(x, y) && grid[x][y] == Color.BLACK

    override fun plant(tetromino: Tetromino, isTemporary: Boolean): Boolean {
        if (!isValid(tetromino))
            return false

        if (isTemporary)
            this.tetromino = tetromino
        else
            plantPermanently(tetromino, grid)
        notifyListeners()

        return true
    }

    override fun clearBoard() {
        (0 until boardHeight).forEach { i ->
            (0 until boardWidth).forEach { j ->
                grid[i][j] = Color.BLACK
            }
        }
        notifyListeners()
    }

    override fun addListener(listener: BoardModelListener) {
        listeners.add(listener)
    }

    override fun removeListener(listener: BoardModelListener) {
        listeners.remove(listener)
    }

    override fun notifyListeners() = listeners.forEach { it.boardChanged(boardSnapshot()) }

    private fun boardSnapshot() = grid.copy().also {
        plantPermanently(this.tetromino, it)
    }

    private fun plantPermanently(tetromino: Tetromino, grid: Array<Array<Color>>) {
        tetromino.getCoverage().forEach { (x, y) -> grid[x][y] = tetromino.getColor() }
        tryReduceLines()
    }

    private fun tryReduceLines() {
        for (i in boardHeight - 1 downTo 0) {
            if (lineFull(i)) {
                clearLine(i)
                i.dec()
            }
        }
    }

    private fun lineFull(i: Int) = (0 until boardWidth).all { grid[i][it] !== Color.BLACK }

    private fun clearLine(lineIndex: Int) {
        (lineIndex downTo 1).forEach { i ->
            (0 until boardWidth).forEach { j -> grid[i][j] = grid[i - 1][j] }
        }
        (0 until boardWidth).forEach { j -> grid[0][j] = Color.BLACK }
    }
}

fun Array<Array<Color>>.copy() = Array(size) { get(it).clone() }
