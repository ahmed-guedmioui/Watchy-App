package com.ahmed_apps.watchy_course.ui.ui_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ahmed_apps.watchy_course.R
import com.ahmed_apps.watchy_course.main.domain.model.Media

/**
 * @author Ahmed Guedmioui
 */
@Composable
fun AutoSwipeSection(
    title: String,
    route: String = "",
    showSeeAll: Boolean = false,
    mainNavController: NavController,
    navController: NavController? = null,
    mediaList: List<Media>
) {

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp
            )

            if (showSeeAll) {
                Text(
                    modifier = Modifier.clickable {
                        navController?.navigate(route)
                    },
                    text = stringResource(id = R.string.see_all),
                    color = MaterialTheme.colorScheme.onBackground.copy(0.7f),
                    fontSize = 14.sp
                )
            }
        }

        AutoImageSwipePager(
            mainNavController = mainNavController,
            mediaList = mediaList
        )
    }

}























