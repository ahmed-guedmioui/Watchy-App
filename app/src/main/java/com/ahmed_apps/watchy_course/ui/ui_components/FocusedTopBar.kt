package com.ahmed_apps.watchy_course.ui.ui_components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmed_apps.watchy_course.R
import com.ahmed_apps.watchy_course.search.peresentation.SearchState
import com.ahmed_apps.watchy_course.search.peresentation.SearchUiEvents
import com.ahmed_apps.watchy_course.ui.theme.BigRadius

/**
 * @author Ahmed Guedmioui
 */
@Composable
fun FocusedTopBar(
    toolbarOffsetHeightPx: Int,
    searchState: SearchState,
    onEvent: (SearchUiEvents) -> Unit
) {

    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(horizontal = 8.dp, vertical = 12.dp)
            .height(BigRadius)
            .offset {
                IntOffset(x = 0, y = toolbarOffsetHeightPx)
            }
    ) {
        SearchBar(
            searchState = searchState,
            onEvent = onEvent
        )
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchBar(
    searchState: SearchState,
    onEvent: (SearchUiEvents) -> Unit
) {

    val focusRequester = remember {
        FocusRequester()
    }

    LaunchedEffect(true) {
        focusRequester.requestFocus()
    }

    val textState = rememberTextFieldState(searchState.searchQuery)

    BasicTextField2(
        inputTransformation = { originalValue, valueWithChanges ->
            if (originalValue != valueWithChanges.asCharSequence()) {
                onEvent(
                    SearchUiEvents.OnSearchQueryChange(
                        valueWithChanges.toString()
                    )
                )
            }
        },
        state = textState,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(100.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .focusRequester(focusRequester),
        lineLimits = TextFieldLineLimits.SingleLine,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 17.sp
        ),
        decorator = { innerTextField ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = stringResource(
                        R.string.search_a_movies_or_tv_series
                    ),
                    tint = MaterialTheme.colorScheme.onBackground.copy(0.4f),
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(28.dp)
                )

                Box(modifier = Modifier.weight(1f)) {
                    if (textState.text.isEmpty()) {
                        Text(
                            text = stringResource(
                                R.string.search_a_movies_or_tv_series
                            ),
                            color = MaterialTheme.colorScheme.onBackground.copy(0.4f),
                            fontSize = 17.sp
                        )
                    }

                    innerTextField()

                }

                if (textState.text.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = stringResource(R.string.clear_search),
                        tint = MaterialTheme.colorScheme.onBackground.copy(0.6f),
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(28.dp)
                            .clickable {
                                textState.edit {
                                    this.replace(
                                        0, this.length, ""
                                    )
                                }
                                onEvent(
                                    SearchUiEvents.OnSearchQueryChange("")
                                )
                            }
                    )
                }

            }
        }
    )

}



















