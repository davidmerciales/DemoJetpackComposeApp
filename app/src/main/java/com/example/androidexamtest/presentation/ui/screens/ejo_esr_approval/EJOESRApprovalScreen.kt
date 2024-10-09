package com.example.androidexamtest.presentation.ui.screens.ejo_esr_approval

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidexamtest.LocalLoadingSession

@Preview
@Composable
fun EJOESRApprovalScreen() {
    LocalLoadingSession.current.value = false
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center) {

        Text(text = "EJO - ESR Approval")
    }
}