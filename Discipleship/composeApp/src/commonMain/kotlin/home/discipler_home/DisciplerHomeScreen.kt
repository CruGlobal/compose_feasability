package home.discipler_home

import disciple.Disciple
import Meeting
import Tool
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import calendar.CalendarPage
import calendar.ui.Calendar
import discipleship.composeapp.generated.resources.Res
import discipleship.composeapp.generated.resources.avatar
import discipleship.composeapp.generated.resources.social
import home.BottomBar
import home.MeetingSection
import home.ToolsSection
import home.TopBar
import navigation.ScreenData
import org.jetbrains.compose.resources.painterResource
import ui.theme.backgroundLight
import ui.theme.inversePrimaryLight
import ui.theme.primaryLight
import ui.theme.secondaryLight

// Will eventually be dynamically loaded
val disciplesList = mutableListOf(
    Disciple(
        firstName = "Zach",
        lastName = "Linscott",
        email = "zach7307@gmail.com",
        image = Res.drawable.avatar,
        bio = "I like food"
    ),
    Disciple(
        firstName = "Josh",
        lastName = "Ward",
        email = "idk",
        image = null,
        bio = "I like food too"
    ),
    Disciple(
        firstName = "Meep",
        lastName = "Moop",
        email = "meepmoop",
        image = null,
        bio = "I like food"
    ),
    Disciple(
        firstName = "Z",
        lastName = "L",
        email = "Zach2",
        image = null,
        bio = "I like food"
    ),
    )

// will eventually be populated dynamically
val meetingsList = mutableListOf(
    Meeting(
        date = "7/7/2024",
        time = "10:30AM",
        disciple = disciplesList[2],
        note = "Meep mooping"
    )
)

// will eventually be populated dynamically
val toolsList = mutableListOf(
    Tool(
        toolName = "Four Spiritual Laws",
        toolDescription = "This is a cool tool.",
        toolLink = "https://knowgod.com/en/fourlaws/0",
        languageList = listOf("English", "German")
    )
)

data class DisciplerHomeScreen(val screenData: ScreenData): Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = {
                TopBar(navigator = navigator, title = "Christ Companions")
            },
            bottomBar = {  // will refactor
                BottomBar(navigator = navigator, currentScreen = "Home", screenData = screenData)
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .background(backgroundLight)
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                MeetingSection()
                DiscipleSection()
                ToolsSection()
            }
        }
    }
}

