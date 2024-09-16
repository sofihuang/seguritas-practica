package com.example.seguritaspractica.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.seguritaspractica.domain.Punto

@Composable
fun Marker(
    punto: Punto,
    isSelected: Boolean,
    onPuntoUpdated: (Punto) -> Unit,
    onDragStarted: () -> Unit,
    onDragEnded: () -> Unit
) {
    var offset by remember { mutableStateOf(punto.coordenada) }

    Box(
        modifier = Modifier
            .offset(offset.x.dp, offset.y.dp)
            .size(26.dp)
            .background(
                color = if (isSelected) Color.Cyan else Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .border(2.dp, Color.Black, shape = RoundedCornerShape(8.dp))
            .pointerInput(isSelected) {
                if (isSelected) {
                    detectDragGestures(
                        onDragStart = {
                            onDragStarted()
                        },
                        onDragEnd = {
                            onDragEnded()
                            onPuntoUpdated(punto.copy(coordenada = offset))
                        },
                        onDrag = { change, dragAmount ->
                            change.consume()
                            val newOffset = Offset(
                                x = (offset.x + dragAmount.x).coerceIn(0f, 300f - 26f),
                                y = (offset.y + dragAmount.y).coerceIn(0f, 300f - 26f)
                            )
                            offset = newOffset
                        }
                    )
                }
            }
    ) {
        Text(
            text = punto.id.toString(),
            fontSize = 10.sp,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
