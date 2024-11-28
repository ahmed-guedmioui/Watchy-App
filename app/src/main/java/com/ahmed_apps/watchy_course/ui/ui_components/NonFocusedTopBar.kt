package com.ahmed_apps.watchy_course.ui.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ahmed_apps.watchy_course.ui.theme.BigRadius
import com.ahmed_apps.watchy_course.ui.theme.HugeRadius

/**
 * @author Ahmed Guedmioui
 */
@Composable
fun NonFocusedTopBar(
    name: String = "",
    title: String = "",
    mainNavController: NavController,
    toolbarOffsetHeightPx: Int
) {

    Box(
        modifier = Modifier
            .height(
                if (title.isNotEmpty()) HugeRadius
                else BigRadius
            )
            .offset {
                IntOffset(x = 0, y = toolbarOffsetHeightPx)
            }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    if (title.isNotEmpty())
                        MaterialTheme.colorScheme.background
                    else
                        Color.Transparent
                )
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            NonFocusedSearchBar(
                name = name,
                mainNavController = mainNavController
            )

            if (title.isNotEmpty()) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 19.sp
                )
            }

        }

    }

}






















