package discipler.introQuestions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DisciplerQuestions () {
    var bibleKnowledge by remember { mutableStateOf(0f)}
    var years by remember { mutableStateOf("")}

    val experience = setOf("None", "Some", "Decent", "A Lot")
    val selectedExperience = remember { mutableStateListOf<String>() }

    val options = setOf("Yes", "No")
    var discipledBefore by remember { mutableStateOf("")}

    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        Text(
            text = "discipler",
            modifier = Modifier.align(Alignment.Start),
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(text = "Have you discipled someone before?")
        Row {
            options.forEach {
                Column(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    RadioButton(
                        selected = discipledBefore == it,
                        onClick = { discipledBefore = it },
                    )
                    Text(text = it, style = MaterialTheme.typography.caption)
                }
            }
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Text(text = "How many years of experience do you  have discipling?")
        OutlinedTextField(
            value = years,
            onValueChange = {years = it},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(text = "Rate your knowledge of the Bible on a scale of 1 to 10")
        Column {
            Slider(
                value = bibleKnowledge,
                onValueChange = { bibleKnowledge = it },
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colors.secondary,
                    activeTrackColor = MaterialTheme.colors.secondary,
                    inactiveTickColor = MaterialTheme.colors.secondary,
                ),
                steps = 9,
                valueRange = 0f..10f

            )
            Text(text = bibleKnowledge.toInt().toString())

        }

        Spacer(modifier = Modifier.padding(8.dp))

        Text("Select your experience level sharing your faith")
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            experience.forEach {
                Column(modifier = Modifier.padding(horizontal = 4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Checkbox(
                        checked = selectedExperience.contains(it),
                        onCheckedChange = { unchecked ->
                            if (!unchecked) selectedExperience.remove(it)
                            else selectedExperience.add(it)
                        },
                    )
                    Text(text = it, style = MaterialTheme.typography.caption)
                }
            }
        }

        Button(
            onClick = {},
            modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}