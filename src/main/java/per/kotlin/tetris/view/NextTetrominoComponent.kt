package per.kotlin.tetris.view

import per.kotlin.tetris.model.NextTetrominoListener
import per.kotlin.tetris.model.tetromino.Tetromino
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JComponent

class NextTetrominoComponent : JComponent(), NextTetrominoListener {
    companion object {
        private const val BRICK_SIZE = 30
        private const val GRID_SIZE = 1
        private const val DEFAULT_DIM = 6

        private val bgColor = Color.BLACK
        private val borderColor = Color.GRAY
        private val dimension = Dimension(
                DEFAULT_DIM * BRICK_SIZE + (DEFAULT_DIM + 1) * GRID_SIZE,
                DEFAULT_DIM * BRICK_SIZE + (DEFAULT_DIM + 1) * GRID_SIZE
        )
    }

    private lateinit var tetromino: Tetromino

    override fun fire(newTetromino: Tetromino) {
        tetromino = newTetromino
        repaint()
    }

    override fun paintComponent(g: Graphics?) {
        if (g === null)
            return

        g.color = bgColor
        g.fillRect(0, 0, dimension.width, dimension.height)

        g.color = borderColor
        g.drawRect(5, 5, dimension.width - 10, dimension.height - 10)

        if (::tetromino.isInitialized.not())
            return

        g.color = tetromino.getColor()
        for ((x, y) in tetromino.getCoverage()) {
            g.fillRect(
                    (y + 1) * GRID_SIZE + y * BRICK_SIZE - 2 * BRICK_SIZE,
                    (x + 1) * GRID_SIZE + x * BRICK_SIZE + 2 * BRICK_SIZE,
                    BRICK_SIZE,
                    BRICK_SIZE
            )
        }
    }

    override fun getPreferredSize() = dimension

    override fun getMaximumSize() = dimension

    override fun getMinimumSize() = dimension
}