package per.kotlin.tetris.view

import per.kotlin.tetris.controller.GameController
import per.kotlin.tetris.controller.GameControllerImpl
import per.kotlin.tetris.model.BoardModelImpl
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Font
import java.awt.KeyboardFocusManager
import java.awt.event.*
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JPanel
import kotlin.system.exitProcess


class TetrisFrame : JFrame("Tetris") {
    companion object {
        private const val HEIGHT = 20
        private const val WIDTH = 10
    }

    private val boardComponent = TetrisBoardComponent(WIDTH, HEIGHT)
    private val nextTetrominoComponent = NextTetrominoComponent()
    private val startButton = JButton("Start")

    private val gameController: GameController

    init {
        val boardModel = BoardModelImpl(WIDTH, HEIGHT).apply { addListener(boardComponent) }
        gameController = GameControllerImpl(boardModel)

        createUI()
        addListeners()
    }

    private fun createUI() {
        rootPane.contentPane = JPanel(BorderLayout()).apply {
            add(boardComponent, BorderLayout.CENTER)
            add(JPanel(BorderLayout()).apply {
                background = Color.BLACK
                add(startButton.apply {
                    background = Color.ORANGE
                    foreground = Color.WHITE
                    font = Font("Tahoma", Font.BOLD, 12)
                }, BorderLayout.PAGE_START)
                add(nextTetrominoComponent, BorderLayout.PAGE_END)
            }, BorderLayout.LINE_END)
        }
    }

    private fun addListeners() {
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                exitProcess(0)
            }
        })

        startButton.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                gameController.start()
            }
        })

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher { e ->
            if (e.id == KeyEvent.KEY_PRESSED)
                when (e?.keyCode) {
                    KeyEvent.VK_LEFT -> gameController.leftPressed()
                    KeyEvent.VK_UP -> gameController.upPressed()
                    KeyEvent.VK_RIGHT -> gameController.rightPressed()
                    KeyEvent.VK_DOWN -> gameController.downPressed()
                }

            false
        }

        gameController.addNextTetrominoListener(nextTetrominoComponent)
    }
}
