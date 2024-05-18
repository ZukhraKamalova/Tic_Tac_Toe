package com.example.tictactoe.ui.theme


//класс данных
data class GameState (
    val playerCrossCount: Int = 0, //начальное значение очков игрока X
    val playerCircleCount: Int = 0, //начальное значение очков игрока 0
    val drawCount: Int=0, //количество ничьих
    val hintText: String = "Игрок X ходит",
    val currentTurn: BoardCellValue = BoardCellValue.CROSS, //сохранить значение ячейки
    val victoryType: VictoryType = VictoryType.NONE, //сохранить линии победы
    val hasWon: Boolean = false
)

enum class BoardCellValue {
    CROSS,
    CIRCLE,
    NONE
}

enum class VictoryType{
    HORIZONTAL1,
    HORIZONTAL2,
    HORIZONTAL3,
    VERTICAL1,
    VERTICAL2,
    VERTICAL3,
    DIAGONAL1,
    DIAGONAL2,
    NONE
}