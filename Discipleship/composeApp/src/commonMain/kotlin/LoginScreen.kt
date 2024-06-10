import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import discipleship.composeapp.generated.resources.Res
import discipleship.composeapp.generated.resources.crulogo
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import  androidx.compose.ui.graphics.Color

/* Author: Zachery Linscott
*
* This is the primary login screen for the application.
* TODO: Change color of input fields to blue or something other than purple
* */

@Composable
fun LoginScreen() {
    val cru: DrawableResource = Res.drawable.crulogo // image of the Cru logo

    // Container for everything on the screen
    Column(modifier = Modifier
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.Blue.copy(alpha = .1f),
                    Color.White,
                    Color.Blue.copy(alpha = .15f))
            )
        )
        .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painterResource(cru),
            contentDescription = "Cru logo",
            modifier = Modifier
                .size(200.dp)
        )

        Text(
            text = "ChristCompanions",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )

        Spacer(modifier = Modifier.padding(16.dp))

        // Login fields
        Column {
            Text(modifier = Modifier
                .padding(),
                text = "Login to your account",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
            )

            OutlinedTextField(value = "", onValueChange = {}, label = {
                Text(text = "Email address")
            })

            Spacer(modifier = Modifier.padding(8.dp))

            OutlinedTextField(value = "", onValueChange = {}, label = {
                Text(text = "Password")
            })

            TextButton(modifier = Modifier.align(Alignment.End), onClick = {}) {
                Text(text = "Forgot password?", color = Color.Blue)
            }
        }

        // Login button
        Button(modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .width(100.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue, contentColor = Color.White
            ),
            content = {
                Text("Login")
            }
        )

        Spacer(modifier = Modifier.padding(bottom = 8.dp))
        Divider()
        Text(text = "or", color = Color.Gray)

        // Signup button (needs to go to SignupScreen on click)
        Button(modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .width(100.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Gray, contentColor = Color.White
            ),
            content = {
                Text("Signup")
            }
        )
    }
}