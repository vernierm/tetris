package per.kotlin.tetris.view

import per.kotlin.tetris.model.BoardModelListener
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JComponent

class TetrisBoardComponent(
        private val boardWidth: Int,
        private val boardHeight: Int
) : JComponent(), BoardModelListener {
    companion object {
        private const val BRICK_SIZE = 30
        private const val GRID_SIZE = 1
        private val BG_COLOR = Color.GRAY
    }

    private val dimension = Dimension(
            boardWidth * BRICK_SIZE + (boardWidth + 1) * GRID_SIZE,
            boardHeight * BRICK_SIZE + (boardHeight + 1) * GRID_SIZE
    )

    private var colors = Array(boardHeight) { Array(boardWidth) { Color.BLACK } }

    override fun boardChanged(colors: Array<Array<Color>>) {
        setColors(colors)
        repaint()
    }

    override fun paintComponent(g: Graphics?) {
        if (g === null)
            return

        g.color = BG_COLOR
        g.fillRect(0, 0, dimension.width, dimension.height)

        for (i in 0 until boardHeight) {
            for (j in 0 until boardWidth) {
                g.color = colors[i][j]
                g.fillRect(
                        (j + 1) * GRID_SIZE + j * BRICK_SIZE,
                        (i + 1) * GRID_SIZE + i * BRICK_SIZE,
                        BRICK_SIZE,
                        BRICK_SIZE
                )
            }
        }
    }

    override fun getPreferredSize() = dimension

    override fun getMaximumSize() = dimension

    override fun getMinimumSize() = dimension

    private fun setColors(colors: Array<Array<Color>>) {
        this.colors = colors
    }
}
