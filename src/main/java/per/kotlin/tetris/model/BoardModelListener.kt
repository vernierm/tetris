package per.kotlin.tetris.model

import java.awt.Color

interface BoardModelListener {
    fun boardChanged(colors: Array<Array<Color>>)
}