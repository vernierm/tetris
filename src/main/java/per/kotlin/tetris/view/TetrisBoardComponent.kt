package per.kotlin.tetris.view

import per.kotlin.tetris.model.BoardModelListener
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JComponent

class TetrisBoardComponent(private val boardWidth: Int, private val boardHeight: Int) : JComponent(), BoardModelListener {
    private val brickSize = 30
    private val gridSize = 1
    private val bgColor = Color.GRAY
    private val dimension: Dimension = Dimension(
            boardWidth * brickSize + (boardWidth + 1) * gridSize,
            boardHeight * brickSize + (boardHeight + 1) * gridSize
    )

    private var colors: Array<Array<Color>> = Array(boardHeight) { Array(boardWidth) { Color.BLACK } }

    override fun boardChanged(colors: Array<Array<Color>>) {
        setColors(colors)
        repaint()
    }

    override fun paintComponent(g: Graphics?) {
        if (g == null)
            return

        g.color = bgColor
        g.fillRect(0, 0, dimension.width, dimension.height)

        for (i in 0 until boardHeight) {
            for (j in 0 until boardWidth) {
                g.color = colors[i][j]
                g.fillRect(
                        (j + 1) * gridSize + j * brickSize,
                        (i + 1) * gridSize + i * brickSize,
                        brickSize,
                        brickSize
                )
            }
        }
    }

    override fun getPreferredSize(): Dimension = dimension

    override fun getMaximumSize(): Dimension = dimension

    override fun getMinimumSize(): Dimension = dimension

    private fun setColors(colors: Array<Array<Color>>) {
        this.colors = colors
    }
}
