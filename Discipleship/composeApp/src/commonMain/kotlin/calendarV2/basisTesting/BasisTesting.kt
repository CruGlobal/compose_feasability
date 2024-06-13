package calendarV2.basisTesting

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import epicarchitect.calendar.compose.basis.BasisEpicCalendar
import epicarchitect.calendar.compose.basis.config.rememberMutableBasisEpicCalendarConfig
import epicarchitect.calendar.compose.basis.state.rememberMutableBasisEpicCalendarState
import epicarchitect.calendar.compose.ranges.drawEpicRanges
import calendarV2.TestingLayout
import calendarV2.rangesTesting.testRanges

@Composable
fun BasisTesting() {
    val config = rememberMutableBasisEpicCalendarConfig()
    val state = rememberMutableBasisEpicCalendarState(config)

    TestingLayout(
        controls = {
            BasisStateControls(state)
            BasisConfigControls(config)
        }
    ) {
        BasisEpicCalendar(
            state = state,
            modifier = Modifier.drawEpicRanges(
                ranges = testRanges,
                color = MaterialTheme.colorScheme.primaryContainer
            )
        )
    }
}