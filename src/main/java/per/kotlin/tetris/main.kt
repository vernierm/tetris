package per.kotlin.tetris

import per.kotlin.tetris.view.TetrisFrame

fun main() {
    TetrisFrame().apply {
        pack()
        setLocationRelativeTo(null)
        isVisible = true
        isResizable = false
    }
}