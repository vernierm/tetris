package per.kotlin.tetris.controller

import per.kotlin.tetris.model.BoardModel
import per.kotlin.tetris.model.NextTetrominoListener
import per.kotlin.tetris.model.tetromino.NoOpTetromino
import per.kotlin.tetris.model.tetromino.Orientation
import per.kotlin.tetris.model.tetromino.Tetromino
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.timerTask


class GameControllerImpl(private val boardModel: BoardModel) : GameController {
    companion object {
        private const val PERIOD = 1000L
    }

    private val listeners = mutableListOf<NextTetrominoListener>()
    private var activeTetromino: Tetromino = NoOpTetromino()
    private var nextTetromino: Tetromino = NoOpTetromino()

    private val timer = Timer(true)
    private var inGame = AtomicBoolean(false)
    private lateinit var timerTask: TimerTask

    override fun start() {
        if (inGame.get())
            return
        inGame.set(true)

        nextTetromino = generateRandomTetromino()
        createNewTetromino()

        timerTask = timerTask { downPressed() }
        timer.scheduleAtFixedRate(timerTask, PERIOD, PERIOD)
    }

    override fun leftPressed() {
        if (!inGame.get())
            return

        applyMove {
            val position = it.getPosition()
            it.setPosition(Pair(position.first, position.second - 1))
        }
    }

    override fun rightPressed() {
        if (!inGame.get())
            return

        applyMove {
            val position = it.getPosition()
            it.setPosition(Pair(position.first, position.second + 1))
        }
    }

    override fun downPressed() {
        if (!inGame.get())
            return

        val applied = applyMove {
            val position = it.getPosition()
            it.setPosition(Pair(position.first + 1, position.second))
        }

        if (!applied) {
            boardModel.plant(activeTetromino, false)
            if (!createNewTetromino())
                gameOver()
        }
    }

    override fun upPressed() {
        if (!inGame.get())
            return

        applyMove {
            val nextOrientation = when (it.getOrientation()) {
                Orientation.NORTH -> Orientation.EAST
                Orientation.EAST -> Orientation.SOUTH
                Orientation.SOUTH -> Orientation.WEST
                Orientation.WEST -> Orientation.NORTH
            }
            it.setOrientation(nextOrientation)
        }
    }

    override fun addNextTetrominoListener(listener: NextTetrominoListener) =
            listeners.add(listener)

    override fun removeNextTetrominoListener(listener: NextTetrominoListener) =
            listeners.remove(listener)

    override fun notifyNextTetrominoListeners(tetromino: Tetromino) = listeners.forEach { it.fire(tetromino) }

    private fun applyMove(move: (Tetromino) -> Unit): Boolean {
        val tmpTetromino = activeTetromino.clone().also {
            move(it)
        }

        if (!boardModel.isValid(tmpTetromino))
            return false

        activeTetromino = tmpTetromino
        return boardModel.plant(activeTetromino, true)
    }

    private fun createNewTetromino(): Boolean {
        activeTetromino = nextTetromino
        nextTetromino = generateRandomTetromino()
        notifyNextTetrominoListeners(nextTetromino)

        return boardModel.plant(activeTetromino, true)
    }

    private fun gameOver() {
        inGame.set(false)
        timerTask.cancel()

        boardModel.clearBoard()
        activeTetromino = NoOpTetromino()
        boardModel.plant(activeTetromino, true)

        nextTetromino = NoOpTetromino()
        notifyNextTetrominoListeners(nextTetromino)
    }
}
