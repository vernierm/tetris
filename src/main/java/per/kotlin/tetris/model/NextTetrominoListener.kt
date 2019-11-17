package per.kotlin.tetris.model

import per.kotlin.tetris.model.tetromino.Tetromino

interface NextTetrominoListener {
    fun fire(newTetromino: Tetromino)
}