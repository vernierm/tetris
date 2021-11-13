package per.kotlin.tetris.controller

import per.kotlin.tetris.model.tetromino.*

private val STARTING_POSITION = Pair(0, 4)
private val TETROMINO_FACTORY_FUNCTIONS = listOf(
        { ITetromino(STARTING_POSITION) },
        { JTetromino(STARTING_POSITION) },
        { LTetromino(STARTING_POSITION) },
        { OTetromino(STARTING_POSITION) },
        { STetromino(STARTING_POSITION) },
        { TTetromino(STARTING_POSITION) },
        { ZTetromino(STARTING_POSITION) }
)

fun generateRandomTetromino() = TETROMINO_FACTORY_FUNCTIONS.random().invoke()