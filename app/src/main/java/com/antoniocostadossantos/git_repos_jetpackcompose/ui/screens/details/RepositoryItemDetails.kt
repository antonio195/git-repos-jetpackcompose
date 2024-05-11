package com.antoniocostadossantos.git_repos_jetpackcompose.ui.screens.details

import android.content.res.Configuration
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Divider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import coil.compose.AsyncImage
import com.antoniocostadossantos.git_repos_jetpackcompose.mock.sampleItemRepository
import com.antoniocostadossantos.git_repos_jetpackcompose.model.repo.Item
import com.antoniocostadossantos.git_repos_jetpackcompose.ui.screens.commits.CommitsScreen
import com.antoniocostadossantos.git_repos_jetpackcompose.ui.screens.issues.IssuesScreen

data class RepositoryItemDetails(
    private val item: Item,
    private val modifier: Modifier = Modifier
) : Screen {

    @Composable
    override fun Content() {

        val minHeight = 130.dp

        var tabIndex by remember { mutableStateOf(0) }
        val tabs = listOf("Commits", "Issues")

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(all = 8.dp)
                .scrollable(
                    orientation = Orientation.Vertical,
                    state = rememberScrollState()
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                        .heightIn(min = minHeight),
                    verticalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Text(
                        text = item.name,
                        fontSize = 24.sp,
                        maxLines = 2,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Divider()
                    Text(
                        text = item.fullName,
                        fontSize = 24.sp,
                        maxLines = 2,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    AsyncImage(
                        item.owner.avatarUrl,
                        null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(130.dp)
                    )
                }
            }

            Divider(Modifier.padding(vertical = 8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Description",
                    fontSize = 26.sp,
                    fontWeight = FontWeight(500)
                )
                Spacer(Modifier.heightIn(5.dp))
                Text(
                    text = item.description,
                    fontSize = 18.sp,
                    maxLines = 4
                )
            }

            Divider(Modifier.padding(vertical = 8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Score: ${item.score}", fontSize = 20.sp)
                Text(text = "Forks: ${item.forksCount}", fontSize = 20.sp)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Open issues: ${item.openIssues}", fontSize = 20.sp)
                Text(text = "Stars: ${item.stargazersCount}", fontSize = 20.sp)
            }

            TabRow(selectedTabIndex = tabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
            when (tabIndex) {
                0 -> CommitsScreen(owner = item.owner.login, repo = item.name).Content()
                1 -> IssuesScreen().Content()
            }

        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun RepositoryItemDetailsPreview() {
    RepositoryItemDetails(sampleItemRepository).Content()
}

