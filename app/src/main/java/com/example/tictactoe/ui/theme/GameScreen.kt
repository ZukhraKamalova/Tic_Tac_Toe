package com.example.tictactoe.ui.theme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GameScreen(
    viewModel:GameViewModel
) {
    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize() //сохранит размер
            .background(GreyBackground)
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally, //равномерное распределение
        verticalArrangement = Arrangement.SpaceEvenly
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth(), //займет всю ширину на экране
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(text = "Игрок 'X' : ${state.playerCrossCount} ", fontSize = 16.sp)
            Text(text = "Ничья: ${state.drawCount} ", fontSize = 16.sp)
            Text(text = "Игрок '0' : ${state.playerCircleCount}", fontSize = 16.sp)
        }
        //заголовок
        Text(
            text = "Крестики-нолики",
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive, //не работает на русский текст
            color = BlueCustom
        )
        //рамка для доски
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f) //соотношение сторон
                .shadow( //тень рамки
                    elevation = 10.dp,
                    shape = RoundedCornerShape(20.dp) //закругленная форма
                )
                .clip(RoundedCornerShape(20.dp)) //закругленная форма
                .background(GreyBackground),
            contentAlignment = Alignment.Center

        ) {
            BoardBase()
            //создание ячеек
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .aspectRatio(1f),
                columns = GridCells.Fixed(3)
            ) {
                viewModel.boardItems.forEach { (cellNo, boardCellValue) ->
                    //будет применяться ко всем элементам сетки
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                //интерактивная ячейка
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null
                                ) {

                                    viewModel.onAction(UserAction.BoardTapped(cellNo))
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            AnimatedVisibility(
                                visible = viewModel.boardItems[cellNo] != BoardCellValue.NONE,
                            enter = scaleIn(tween(1000)) //время анимации
                            )
                            {
                                if (boardCellValue == BoardCellValue.CROSS) {
                                Cross()
                            } else if (boardCellValue == BoardCellValue.CIRCLE) {
                                Circle()
                            }
                            }
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedVisibility(
                    visible = state.hasWon,
                    enter = fadeIn(tween(2000))
                )
                {
                    DrawVictoryLine(state = state)
                }
            }

        }
        //кнопка и текст-подсказка
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = state.hintText,
                fontSize = 21.sp,
                fontStyle = FontStyle.Italic,
            )
            Button(
                onClick = { 
                          viewModel.onAction(
                              UserAction.PlayAgainButtonClick
                          )
                },
                shape = RoundedCornerShape(5.dp), //закругление угла
                elevation = ButtonDefaults.buttonElevation(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BlueCustom,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Новая игра", fontSize = 16.sp)
            }
        }
    }
}
//красная линия победы
@Composable
fun DrawVictoryLine(
    state: GameState
){
    when(state.victoryType){
        VictoryType.HORIZONTAL1 -> WinHorizontalLine1()
        VictoryType.HORIZONTAL2 -> WinHorizontalLine2()
        VictoryType.HORIZONTAL3 -> WinHorizontalLine3()
        VictoryType.VERTICAL1 -> WinVerticalLine1()
        VictoryType.VERTICAL2 -> WinVerticalLine2()
        VictoryType.VERTICAL3 -> WinVerticalLine3()
        VictoryType.DIAGONAL1 -> WinDiagonalLine1()
        VictoryType.DIAGONAL2 -> WinDiagonalLine2()
        VictoryType.NONE -> {}
        }


    }



//функция предварительного просмотра
@Preview (showBackground = true)
@Composable
fun Prev(){
    GameScreen(
        viewModel = GameViewModel()
    )
}