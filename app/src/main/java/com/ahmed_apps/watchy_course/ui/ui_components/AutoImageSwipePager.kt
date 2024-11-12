package com.ahmed_apps.watchy_course.ui.ui_components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ahmed_apps.watchy_course.main.domain.model.Media
import com.ahmed_apps.watchy_course.ui.theme.Radius
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author Ahmed Guedmioui
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoImageSwipePager(
    mainNavController: NavController,
    mediaList: List<Media>
) {

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        mediaList.size
    }

    val scope = rememberCoroutineScope()
    val swipeInterval = 3000

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            state = pagerState,
            key = { mediaList[it].mediaId },
            pageSize = PageSize.Fill
        ) { index ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(Radius))
            ) {
                MediaItemImage(
                    media = mediaList[index],
                    mainNavController = mainNavController,
                    isPoster = false,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(0.5f),
                                    Color.Black,
                                )
                            )
                        )
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 12.dp,
                            top = 22.dp
                        )
                        .align(Alignment.BottomCenter)
                ) {
                    Text(
                        text = mediaList[index].title,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 17.sp
                    )
                }

                LaunchedEffect(true) {
                    while (true) {
                        delay(swipeInterval.toLong())

                        scope.launch {
                            if (pagerState.canScrollForward) {
                                pagerState.animateScrollToPage(
                                    pagerState.currentPage + 1
                                )
                            } else {
                                pagerState.animateScrollToPage(0)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        DotsIndicator(
            totalDots = mediaList.size,
            currentDot = pagerState.currentPage
        )

    }

}




















