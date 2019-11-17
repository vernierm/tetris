package per.kotlin.tetris.model

import per.kotlin.tetris.model.tetromino.Tetromino

interface BoardModel {
    fun isValid(tetromino: Tetromino): Boolean
    fun plant(tetromino: Tetromino, isTemporary: Boolean): Boolean
    fun clearBoard()
    fun addListener(listener: BoardModelListener)
    fun removeListener(listener: BoardModelListener)
    fun notifyListeners()
}