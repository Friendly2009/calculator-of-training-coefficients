package com.example.calculatoroftrainingcoefficients

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.calculatoroftrainingcoefficients.core.formatAverage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorScreenTest {
    @get:Rule val compose = createAndroidComposeRule<MainActivity>()
    private fun scrollTo(tag: String) {
        compose.onNodeWithTag("calculator_list").performScrollToNode(hasTestTag(tag))
    }
    private fun add(grade: Int) { scrollTo("add_$grade"); compose.onNodeWithTag("add_$grade").performClick() }
    private fun average(value: Double?) {
        scrollTo("average"); compose.onNodeWithTag("average").assertTextEquals(formatAverage(value))
    }
    @Test fun recalculatesClearsAndUndoes() {
        average(null)
        add(5); average(5.0)
        add(2); average(3.5)
        scrollTo("clear"); compose.onNodeWithTag("clear").performClick()
        compose.onNodeWithTag("confirm_clear").performClick(); average(null)
        compose.onNodeWithText(compose.activity.getString(R.string.undo)).performClick(); average(3.5)
    }
    @Test fun returnsFromSupportAndRestoresActivity() {
        add(4)
        compose.onNodeWithTag("support").performClick()
        compose.onNodeWithTag("support_back").performClick(); average(4.0)
        compose.activityRule.scenario.recreate(); average(4.0)
    }
    @Test fun validatesCustomWeightAndAcceptsComma() {
        scrollTo("custom_weight"); compose.onNodeWithTag("custom_weight").performScrollTo().performClick()
        compose.onNodeWithTag("weight_input").performTextReplacement("0")
        compose.onNodeWithTag("save_weight").assertIsNotEnabled()
        compose.onNodeWithTag("weight_input").performTextReplacement("1,5")
        compose.onNodeWithTag("save_weight").assertIsEnabled().performClick()
        add(5)
        scrollTo("weight_1.0"); compose.onNodeWithTag("weight_1.0").performScrollTo().performClick()
        add(2); average(3.8)
    }
}
