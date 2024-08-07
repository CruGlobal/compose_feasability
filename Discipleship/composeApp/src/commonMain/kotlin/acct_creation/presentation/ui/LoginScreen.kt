package acct_creation.presentation.ui

import acct_creation.presentation.viewmodel.LoginScreenViewModel
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import arrow.core.raise.Null
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.touchlab.kermit.Logger
import discipleship.composeapp.generated.resources.Res
import discipleship.composeapp.generated.resources.crulogo
import discipleship.composeapp.generated.resources.dove
import home.presentation.ui.disciple_home.DiscipleHomeScreen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import realm.data.remote.RealmApi
import realm.data.repository.RealmRepoImpl
import realm.domain.model.UserEntity
import screenmodel.ScreenData
import ui.theme.backgroundLight
import ui.theme.errorLight
import ui.theme.primaryContainerLight
import ui.theme.primaryLight
import ui.theme.secondaryLight

/* Author: Zach and Josh
* This is the primary login screen for the application.
* TODO: Complete "Forgot password?" functionality, notifying user upon incorrect login info, etc
* */
class LoginScreen: Screen {
    override val key: ScreenKey = "LoginScreen"
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val cru: DrawableResource = Res.drawable.crulogo // image of the Cru logo
        val navigator = LocalNavigator.currentOrThrow
        val loginViewModel = LoginScreenViewModel(
            realmRepository = RealmRepoImpl(
                RealmApi()
            )
        )
        var currentUser: io.realm.kotlin.mongodb.User? by remember { mutableStateOf(null) }
        var userEntity: UserEntity? by remember { mutableStateOf(null) }
        var passwordVisible by remember { mutableStateOf(false) }
        passwordVisible = false


        // Container for everything on the screen
        // This screen only needs to be displayed if the user
        // is not logged in
        Scaffold {
//            if (currentUser == null) {
                Column(
                    modifier = Modifier
                        .background(
                            backgroundLight
                        )
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
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
                        text = "Christ Companions",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        color = primaryLight
                    )
                    Image(
                        modifier = Modifier.size(120.dp),
                        painter = painterResource(Res.drawable.dove),
                        contentDescription = "Dove"
                    )

                    Spacer(modifier = Modifier.padding(16.dp))

                    // Login fields
                    Column {
                        Text(
                            modifier = Modifier
                                .padding(),
                            text = "Login to your account",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                            color = primaryLight
                        )

                        OutlinedTextField(
                            value = loginViewModel.email,
                            onValueChange = {
                                email ->
                                    loginViewModel.updateEmail(email)
                                    Logger.i("email value change: $email")
                                            },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = primaryLight,
                                focusedIndicatorColor = primaryContainerLight,
                                cursorColor = secondaryLight
                            ),
                            label = {
                                Text(text = "Email address", color = secondaryLight)
                            },
                        )
                        if (loginViewModel.emailResult.errorMessage != null) {
                            Text(
                                text = "${loginViewModel.emailResult.errorMessage}",
                                color = errorLight
                            )
                        }

                        Spacer(modifier = Modifier.padding(8.dp))

                        OutlinedTextField(
                            value = loginViewModel.password,
                            onValueChange = { password ->
                                loginViewModel.updatePassword(password) },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = primaryLight,
                                focusedIndicatorColor = primaryContainerLight,
                                cursorColor = secondaryLight
                            ),
                            label = {
                                Text(text = "Password", color = secondaryLight)
                            },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        )
                        if (loginViewModel.passwordResult.errorMessage != null) {
                            Text(
                                text = "${loginViewModel.passwordResult.errorMessage}",
                                color = errorLight
                            )
                        }

                        TextButton(modifier = Modifier.align(Alignment.End), onClick = {}) {
                            Text(text = "Forgot password?", color = secondaryLight)
                        }
                    }

                    // Login button
                    Button(
                        modifier = Modifier.clip(RoundedCornerShape(12.dp)).width(100.dp),
                        onClick = {
                            Logger.i("Login button click success")
                            if (loginViewModel.loginIsValid()) {
                                currentUser = loginViewModel.atlasAuth()
                                Logger.i("$currentUser in LoginScreen")
                            }
                            try {
                                if (currentUser != null) {
                                    userEntity = loginViewModel.fetchUserData(currentUser)
                                    Logger.i("userEntity value update: $userEntity")
                                    if (userEntity?.isDisciple == true) {
                                        navigator.replaceAll(DiscipleHomeScreen(screenData = ScreenData(userEntity)))
                                    }
                                    else if (userEntity?.isDisciple == false) {
                                        navigator.replaceAll(DiscipleHomeScreen(screenData = ScreenData(userEntity)))
                                    }
                                    else {
                                        navigator.replaceAll(DorDScreen(screenData = ScreenData(userEntity)))
                                    }
                                }
                                else throw NullPointerException("Atlas User is null in LoginScreen, " +
                                        "likely bad connection.")
                            }
                            catch (e: Exception) {
                                if (e.message != null) Logger.e(e.message!!)
                                else Logger.e("Exception $e in LoginScreen")
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = primaryContainerLight, contentColor = secondaryLight
                        ),
                        content = {
                            Text("Login", color = secondaryLight)
                        }
                    )

                    Spacer(modifier = Modifier.padding(bottom = 8.dp))
                    Divider(color = secondaryLight)
                    Text(text = "or", color = secondaryLight)

                    // Signup button (needs to go to SignupScreen on click)
                    Button(modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .width(100.dp),
                        onClick = {navigator.push(SignupScreen())},
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Gray, contentColor = Color.White
                        ),
                        content = {
                            Text("Signup")
                        }
                    )
                }
            //}
//            else {
//                // switch to DiscipleEntity
//                navigator.replaceAll(DiscipleHomeScreen(screenData = ScreenData(false, userEntity, null)))
            //}
        }
    }
}
