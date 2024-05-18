package com.example.tictactoe.ui.theme

sealed class UserAction {
    //нажатие на любую ячейку доски и кнопку воспроизвести
    object PlayAgainButtonClick: UserAction()
    data class BoardTapped(val cellNo: Int): UserAction()
}