package com.example.dialogviewpager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onFinishOnboarding: () -> Unit) {
    val pages = listOf(
        OnboardingPageData(
            title = "Welcome to Our App",
            description = "Discover features and make your experience better.",
            imageRes = android.R.drawable.ic_menu_gallery
        ),
        OnboardingPageData(
            title = "Welcome to Our App",
            description = "Discover features and make your experience better.",
            imageRes = android.R.drawable.ic_menu_gallery
        ),
        OnboardingPageData(
            title = "Welcome to Our App",
            description = "Discover features and make your experience better.",
            imageRes = android.R.drawable.ic_menu_gallery
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val coroutineScope = rememberCoroutineScope()
    var showSkipDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) { position ->
                OnboardingPageContent(page = pages[position])
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(onClick = { showSkipDialog = true }) {
                    Text(
                        text = "Skip",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 16.sp
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(pages.size) { index ->
                        val isSelected = pagerState.currentPage == index
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(if (isSelected) 10.dp else 8.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isSelected) MaterialTheme.colorScheme.primary
                                    else Color.LightGray
                                )
                        )
                    }
                }

                Button(
                    onClick = {
                        if (pagerState.currentPage < pages.size - 1) {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        } else {
                            onFinishOnboarding()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = if (pagerState.currentPage == pages.size - 1) "Get Started" else "Next",
                        fontSize = 16.sp
                    )
                }
            }
        }

        if (showSkipDialog) {
            AlertDialog(
                onDismissRequest = {},
                title = {
                    Text(text = "Skip onboarding?", fontWeight = FontWeight.Bold)
                },
                text = {
                    Text(text = "Are you sure you want to skip the onboarding?")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showSkipDialog = false
                            onFinishOnboarding()
                        }
                    ) {
                        Text("Skip")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showSkipDialog = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}