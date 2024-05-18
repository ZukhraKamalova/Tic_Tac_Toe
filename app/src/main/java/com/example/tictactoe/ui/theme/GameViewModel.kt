package com.example.tictactoe.ui.theme


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.reflect.KProperty
import androidx.lifecycle.ViewModel



class GameViewModel: ViewModel() {
    //считывать и записывать значения в соответствии с состоянием экрана
    var state by mutableStateOf(GameState())
    //присвоить номера ячеек
    val boardItems: MutableMap<Int, BoardCellValue> = mutableMapOf(
        1 to BoardCellValue.NONE,
        2 to BoardCellValue.NONE,
        3 to BoardCellValue.NONE,
        4 to BoardCellValue.NONE,
        5 to BoardCellValue.NONE,
        6 to BoardCellValue.NONE,
        7 to BoardCellValue.NONE,
        8 to BoardCellValue.NONE,
        9 to BoardCellValue.NONE,
    )

    fun onAction(action: UserAction){
        when(action) {
            is UserAction.BoardTapped ->{
                addValueToBoard(action.cellNo)
            }
            UserAction.PlayAgainButtonClick -> {
                gameReset()

            }
        }

    }

    //сброс игры
    private fun gameReset() {
        boardItems.forEach { (i, _) ->
            boardItems[i] = BoardCellValue.NONE
        }
        state = state.copy(
            hintText = "Игрок 'X' ходит",
            currentTurn = BoardCellValue.CROSS,
            victoryType = VictoryType.NONE,
            hasWon = false
        )
    }

    //проверка заполненности ячеек
    private fun addValueToBoard(cellNo: Int) {
        if (boardItems[cellNo] != BoardCellValue.NONE) {
            return
        }
        if (state.currentTurn == BoardCellValue.CROSS){
            boardItems[cellNo] = BoardCellValue.CROSS
            if (checkVictory(BoardCellValue.CROSS)){
                state = state.copy(
                    hintText = "Игрок 'X' выиграл",
                    playerCrossCount = state.playerCrossCount + 1,
                    currentTurn = BoardCellValue.NONE,
                    hasWon = true
                )
            }
            //если доска заполнена
           else if (hasBoardFull()){
                state = state.copy(
                    hintText = "Ничья",
                    drawCount = state.drawCount + 1
                )
            }
            else {
                state = state.copy(
                    hintText = "Игрок '0' ходит", //после хода изменяется состояние игры
                    currentTurn = BoardCellValue.CIRCLE
                )
            }

        }
        else if (state.currentTurn == BoardCellValue.CIRCLE){
            boardItems[cellNo] = BoardCellValue.CIRCLE
            if (checkVictory(BoardCellValue.CIRCLE)){
                state = state.copy(
                    hintText = "Игрок '0' выиграл",
                    playerCircleCount = state.playerCircleCount + 1,
                    currentTurn = BoardCellValue.NONE,
                    hasWon = true
                )
            }
            else if (hasBoardFull()){
                state = state.copy(
                    hintText = "Ничья",
                    drawCount = state.drawCount + 1
                )
            }
            else {
                state = state.copy(
                    hintText = "Игрок 'X' ходит",
                    currentTurn = BoardCellValue.CROSS
                )
            }
        }
    }

    //проверка выигрыша
    private fun checkVictory(boardValue: BoardCellValue): Boolean {
        when {
            boardItems[1] == boardValue && boardItems[2] == boardValue && boardItems[3] == boardValue ->{
                state = state.copy(victoryType = VictoryType.HORIZONTAL1)
                return true
            }
            boardItems[4] == boardValue && boardItems[5] == boardValue && boardItems[6] == boardValue ->{
                state = state.copy(victoryType = VictoryType.HORIZONTAL2)
                return true
            }
            boardItems[7] == boardValue && boardItems[8] == boardValue && boardItems[9] == boardValue ->{
                state = state.copy(victoryType = VictoryType.HORIZONTAL3)
                return true
            }
            boardItems[1] == boardValue && boardItems[4] == boardValue && boardItems[7] == boardValue ->{
                state = state.copy(victoryType = VictoryType.VERTICAL1)
                return true
            }
            boardItems[2] == boardValue && boardItems[5] == boardValue && boardItems[8] == boardValue ->{
                state = state.copy(victoryType = VictoryType.VERTICAL2)
                return true
            }
            boardItems[3] == boardValue && boardItems[6] == boardValue && boardItems[9] == boardValue ->{
                state = state.copy(victoryType = VictoryType.VERTICAL3)
                return true
            }
            boardItems[1] == boardValue && boardItems[5] == boardValue && boardItems[9] == boardValue ->{
                state = state.copy(victoryType = VictoryType.DIAGONAL1)
                return true
            }
            boardItems[3] == boardValue && boardItems[5] == boardValue && boardItems[7] == boardValue ->{
                state = state.copy(victoryType = VictoryType.DIAGONAL2)
                return true
            }
            else -> return false
        }

    }

    //заполненность всех ячеек
    private fun hasBoardFull(): Boolean {
        if (boardItems.containsValue(BoardCellValue.NONE)) return false
        return true

    }
}