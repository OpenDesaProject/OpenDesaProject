package com.example.opendesa.ui.keluhan.screen.list

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.core.data.source.remote.model.response.Complaint
import com.example.opendesa.util.ext.toDate
import com.example.opendesa.util.ext.toDateString
import com.example.opendesa.util.ext.toTimeAgo
import java.util.*

@ExperimentalMaterialApi @Composable
fun ComplaintItem(
  complaint: Complaint,
  onSwipeDelete: (Complaint) -> Unit,
  modifier: Modifier = Modifier
) {
  val dismissState = rememberDismissState(
    confirmStateChange = {
      if(it == DismissValue.DismissedToStart) {
        onSwipeDelete.invoke(complaint)
      }
      false
    }
  )
  SwipeToDismiss(
    state = dismissState,
    modifier = modifier,
    directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
    dismissThresholds = { direction ->
      FractionalThreshold(if (direction == DismissDirection.StartToEnd) 0.25f else 0.5f)
    },
    background = {
      val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
      val color by animateColorAsState(
        when (dismissState.targetValue) {
          DismissValue.Default -> Color.LightGray
          else -> Color.Red
        }
      )
      val alignment = when (direction) {
        DismissDirection.StartToEnd -> Alignment.CenterStart
        DismissDirection.EndToStart -> Alignment.CenterEnd
      }
      val icon = Icons.Default.Delete
      val scale by animateFloatAsState(
        if (dismissState.targetValue == DismissValue.Default) 0.8f else 1.2f
      )
      val iconTint by animateColorAsState(
        if (dismissState.targetValue == DismissValue.Default) Color.Black else Color.White
      )

      Box(
        Modifier
          .fillMaxSize()
          .background(color)
          .padding(horizontal = 20.dp),
        contentAlignment = alignment
      ) {
        Icon(
          icon,
          contentDescription = "",
          modifier = Modifier.scale(scale),
          tint = iconTint
        )
      }
    },
    dismissContent = {
      val date: Date? = complaint.createdAt.toDate()
      val secondaryText = if((date?.compareTo(Date()) ?: 0) <= 7){
        date?.toTimeAgo()
      } else {
        date?.toDateString()
      }.orEmpty()

      Card(
        elevation = animateDpAsState(
          if (dismissState.dismissDirection != null) 4.dp else 0.dp
        ).value
      ) {
        ListItem(
          text = {
            Text(complaint.report)
          },
          secondaryText = { Text(secondaryText) }
        )
      }
    }
  )
}