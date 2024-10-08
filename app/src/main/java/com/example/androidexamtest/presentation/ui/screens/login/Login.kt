package com.example.androidexamtest.presentation.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidexamtest.R


@Composable
fun HomeScreen(
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_crane),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxHeight(0.48f)
                .fillMaxWidth(0.7f)
                .background(Color.White.copy(alpha = 0.75f), RoundedCornerShape(10.dp))
                .align(Alignment.Center)
        ) {
            val height = this.maxHeight
            val width = this.maxWidth


            Column(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .offset(y = height.times(0.1f)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    modifier = Modifier
                        .width(width)
                        .height(height.times(0.155f)),
                    painter = painterResource(id = R.drawable.repnotes),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.height(30.dp))

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier.size(height.times(0.05f)),
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color(0Xff061269)
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "Username",
                            style = TextStyle(
                                fontSize = 15.sp,
                                color = Color(0Xff061269)
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, RoundedCornerShape(3.dp))
                            .padding(13.dp),
                        value = loginViewModel.state.username,
                        onValueChange = { title ->
                            loginViewModel.state.username = title
                        },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 12.sp,
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false
                            )
                        ),
                        singleLine = true,
                        maxLines = 1
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier.size(height.times(0.05f)),
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = Color(0Xff061269)
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "Password",
                            style = TextStyle(
                                fontSize = 15.sp,
                                color = Color(0Xff061269)
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Box(modifier = Modifier.fillMaxWidth()) {
                        BasicTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White, RoundedCornerShape(3.dp))
                                .padding(13.dp)
                                .padding(end = 40.dp),
                            value = loginViewModel.state.password,
                            onValueChange = { title ->
                                loginViewModel.state.password = title
                            },
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontSize = 12.sp,
                                platformStyle = PlatformTextStyle(
                                    includeFontPadding = false
                                )
                            ),
                            singleLine = true,
                            maxLines = 1,
                            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
                        )

                        Icon(
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(horizontal = 13.dp)
                                .clickable { isPasswordVisible = !isPasswordVisible },
                            imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = null,
                            tint = Color(0Xff061269)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.28f)
                        .background(Color(0Xff061269))
                        .clickable {
                            loginViewModel.setEvent(LoginContract.LoginEvent.OnLoginButtonClicked)
                        }
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center),
                        text = "Login",
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    modifier = Modifier
                        .clickable {
                            loginViewModel.setEvent(LoginContract.LoginEvent.OnForgotPasswordClicked)
                        },
                    text = "Forgot Password?",
                    style = TextStyle(
                        color = Color(0Xff061269)
                    )
                )
            }
        }
    }
}