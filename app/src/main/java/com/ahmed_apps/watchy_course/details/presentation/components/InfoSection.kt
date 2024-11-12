package com.ahmed_apps.watchy_course.details.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmed_apps.watchy_course.R
import com.ahmed_apps.watchy_course.main.domain.model.Media
import com.ahmed_apps.watchy_course.main.domain.usecase.GenreIdsToString
import com.ahmed_apps.watchy_course.ui.ui_components.RatingBar

/**
 * @author Ahmed Guedmioui
 */
@Composable
fun InfoSection(
    media: Media,
    readableTime: String
) {

    Column(
        modifier = Modifier.padding(end = 8.dp)
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = media.title,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (media.voteAverage != 0.0) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RatingBar(
                    starsModifier = Modifier.size(18.dp),
                    rating = media.voteAverage.div(2),
                    starsColor = Color(0xFFf4cb45)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = media.voteAverage.div(
                        2
                    ).toString().take(3),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(7.dp))

        Text(
            text = if (media.adult) {
                stringResource(R.string._18)
            } else {
                stringResource(R.string._12)
            },
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 12.sp,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground,
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(horizontal = 6.dp, vertical = 0.5.dp)
        )

        Spacer(modifier = Modifier.height(7.dp))

        if (media.releaseDate.isNotEmpty()) {
            Text(
                text = media.releaseDate,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(7.dp))
        }

        if (media.genreIds.isNotEmpty()) {
            Text(
                text = GenreIdsToString.genreIdsToString(media.genreIds),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.height(7.dp))
        }

        if (readableTime.isNotEmpty()) {
            Text(
                text = readableTime,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp
            )
        }

    }

}





















