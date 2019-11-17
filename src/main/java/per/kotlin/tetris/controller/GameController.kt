package per.kotlin.tetris.controller

import per.kotlin.tetris.model.NextTetrominoListener
import per.kotlin.tetris.model.tetromino.Tetromino

interface GameController {
    fun start()
    fun leftPressed()
    fun rightPressed()
    fun downPressed()
    fun upPressed()
    fun addNextTetrominoListener(listener: NextTetrominoListener): Boolean
    fun removeNextTetrominoListener(listener: NextTetrominoListener): Boolean
    fun notifyNextTetrominoListeners(tetromino: Tetromino)
}