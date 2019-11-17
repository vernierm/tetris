package per.kotlin.tetris.view

import per.kotlin.tetris.model.NextTetrominoListener
import per.kotlin.tetris.model.tetromino.Tetromino
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JComponent

private const val brickSize = 30
private const val gridSize = 1
private const val defaultDim = 6
private val bgColor = Color.BLACK
private val borderColor = Color.GRAY
private val dimension: Dimension = Dimension(
        defaultDim * brickSize + (defaultDim + 1) * gridSize,
        defaultDim * brickSize + (defaultDim + 1) * gridSize
)

class NextTetrominoComponent : JComponent(), NextTetrominoListener {
    private var tetromino: Tetromino? = null

    override fun fire(newTetromino: Tetromino) {
        tetromino = newTetromino
        repaint()
    }

    override fun paintComponent(g: Graphics?) {
        if (g == null)
            return

        g.color = bgColor
        g.fillRect(0, 0, dimension.width, dimension.height)

        g.color = borderColor
        g.drawRect(5, 5, dimension.width - 10, dimension.height - 10)

        if (tetromino == null)
            return

        g.color = tetromino!!.getColor()
        for ((x, y) in tetromino!!.getCoverage()) {
            g.fillRect(
                    (y + 1) * gridSize + y * brickSize - 2 * brickSize,
                    (x + 1) * gridSize + x * brickSize + 2 * brickSize,
                    brickSize,
                    brickSize
            )
        }
    }

    override fun getPreferredSize(): Dimension = dimension

    override fun getMaximumSize(): Dimension = dimension

    override fun getMinimumSize(): Dimension = dimension
}