package com.example.nguyennotes

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestCaseLoginScreen {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<LoginActivity>()

    @Test
    fun testTitleIsDisplayed() {
        composeTestRule.onNodeWithText("Login").assertIsDisplayed()
    }

    @Test
    fun testButtonIsDisplayed() {
        composeTestRule.onNodeWithText("Let notes begin").assertIsDisplayed()
    }

    @Test
    fun testTextFieldIsDisplayed() {
        composeTestRule.onNodeWithText("Enter your name").assertIsDisplayed()
        composeTestRule.onNodeWithText("Enter your name").performTextInput("Nguyen")
        composeTestRule.onNodeWithText("Nguyen").assertIsDisplayed()
    }

    @Test
    fun testClickButtonWithBlank() {
        composeTestRule.onNodeWithText("Enter your name").performTextInput("nguyen")
        composeTestRule.onNodeWithText("Login").performClick()
    }
}
