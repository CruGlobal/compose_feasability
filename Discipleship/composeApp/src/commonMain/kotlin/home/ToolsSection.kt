package home

import Tool
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import home.discipler_home.toolsList
import ui.theme.primaryContainerLight
import ui.theme.primaryLight

/*
* Author: Zachery Linscott
* Meeting(s) section code
TODO: Fix padding at the end of the lazy rows
    (can be done by adding a unique pad value to the last item)
*/

@Composable
fun ToolsSection() {
    val containerPad = 16.dp
    SectionTitle("Recommended tools")
    LazyRow {
        items(toolsList.size) {
            ToolCard(toolsList[it])
        }
    }
}



// the time, data, and disciple parameters will need to be redefined to the specific object
// e.g. a disciple.Disciple object
@Composable
fun ToolCard(tool: Tool) {
    val tName = tool.toolName
    Card(
        modifier = Modifier
            .width(240.dp)
            .height(140.dp)
            .padding(start = 16.dp, end = 8.dp, bottom = 16.dp)
            .background(primaryContainerLight)
            .clickable {},
        elevation = 12.dp
    ) {
        Column(modifier = Modifier
            .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = tName,
                    fontSize = 26.sp,
                    color = primaryLight,
                    fontWeight = FontWeight.SemiBold
                )
            }

        }
    }
}