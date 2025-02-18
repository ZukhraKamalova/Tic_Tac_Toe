package com.example.tictactoe.ui.theme
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier //import android.graphics.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


//основа
@Composable
fun BoardBase(){
    //холст
     Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    )
     {
         //рисование линий
         drawLine(
             color = Color.Gray,
             strokeWidth = 5f,
             cap = StrokeCap.Round,
             //расположение линии 1 (начальная точка)
             start = Offset(x = size.width*1/3, y=0f), //динамическое расположение
             end = Offset(x = size.width*1/3, y=size.height)
         )
            //2 линия
         drawLine(
             color = Color.Gray,
             strokeWidth = 5f,
             cap = StrokeCap.Round,
             start = Offset(x = size.width*2/3, y=0f),
             end = Offset(x = size.width*2/3, y=size.height)
         )

             //3 линия
         drawLine(
             color = Color.Gray,
             strokeWidth = 5f,
             cap = StrokeCap.Round,
             start = Offset(x = 0f, y=size.height*1/3),
             end = Offset(x = size.width, y=size.height*1/3)
         )

           //4 линия
         drawLine(
             color = Color.Gray,
             strokeWidth = 5f,
             cap = StrokeCap.Round,
             start = Offset(x = 0f, y=size.height*2/3),
             end = Offset(x = size.width, y=size.height*2/3)
         )
     }
}

//крестики
@Composable
fun Cross(){
    Canvas(modifier = Modifier
        .size(60.dp)
        .padding(5.dp)
    ) {
        drawLine(
            color = DarkGreen,
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            //начальное и коничное значение точек
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = size.height)
        )

        drawLine(
            color = DarkGreen,
            strokeWidth = 20f,
            cap = StrokeCap.Round,
            //начальное и коничное значение точек
            start = Offset(x = 0f, y = size.height),
            end = Offset(x = size.width, y = 0f)
        )
    }
}

//нолики
@Composable
fun Circle(){
    Canvas(modifier = Modifier
        .size(60.dp)
        .padding(5.dp)
    ) {
        drawCircle(
            color = Purple, //ширина обводки
            style = Stroke(width = 20f)

        )
    }
}

//линии победы
@Composable
fun WinHorizontalLine1() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height*1/6),
            end = Offset(x = size.width, y = size.height*1/6),
        )
    }
}

@Composable
fun WinHorizontalLine2() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height*3/6),
            end = Offset(x = size.width, y = size.height*3/6),
        )
    }
}

@Composable
fun WinHorizontalLine3() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height*5/6),
            end = Offset(x = size.width, y = size.height*5/6),
        )
    }
}

@Composable
fun WinVerticalLine1() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width*1/6, y = 0f),
            end = Offset(x = size.width*1/6, y = size.height),
        )
    }
}

@Composable
fun WinVerticalLine2() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width*3/6, y = 0f),
            end = Offset(x = size.width*3/6, y = size.height),
        )
    }
}

@Composable
fun WinVerticalLine3() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width*5/6, y = 0f),
            end = Offset(x = size.width*5/6, y = size.height),
        )
    }
}

@Composable
fun WinDiagonalLine1() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = size.height),
        )
    }
}

@Composable
fun WinDiagonalLine2() {
    Canvas(modifier = Modifier.size(300.dp)) {
        drawLine(
            color = Color.Red,
            strokeWidth = 10f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height),
            end = Offset(x = size.width, y = 0f),
        )
    }
}
//предварительный просмотр
@Preview(showBackground = true)
@Composable
fun Previews(){
    //BoardBase()
        WinHorizontalLine1()
        WinHorizontalLine2()
        WinHorizontalLine3()
        WinVerticalLine1()
        WinVerticalLine2()
        WinVerticalLine3()
        WinDiagonalLine1()
        WinDiagonalLine2()

}