package com.progressive.sampleapp.tests.espresso

import com.progressive.kherkin.sampleapp.R
import com.progressive.kherkin.espresso.steps.actions.IClearField
import com.progressive.kherkin.espresso.steps.actions.IDismissKeyboard
import com.progressive.kherkin.espresso.steps.actions.IEnterTextIntoField
import com.progressive.kherkin.espresso.steps.actions.IEnterTextIntoFieldAndDismissKeyboard
import com.progressive.kherkin.espresso.steps.actions.IEnterTextIntoFieldWithLabel
import com.progressive.kherkin.espresso.steps.actions.IEnterTextIntoFieldWithLabelWithParent
import com.progressive.kherkin.espresso.steps.actions.IEnterTextIntoFieldWithParent
import com.progressive.kherkin.espresso.steps.actions.ILeaveFieldEmpty
import com.progressive.kherkin.espresso.steps.actions.ILeaveFieldWithLabelEmpty
import com.progressive.kherkin.espresso.steps.actions.ILeaveFieldWithParentEmpty
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSee
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSeeTextField
import com.progressive.kherkin.espresso.steps.assertion.IShouldNotSeeTextFieldWithLabelAndText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeFieldWithMaskedText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeHintText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeMaskedText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextField
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextFieldContaining
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextFieldDoesNotHaveFocus
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextFieldHasFocus
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextFieldIsDisabled
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextFieldIsEmpty
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextFieldIsEnabled
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextFieldWithHint
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextFieldWithLabelAndText
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextFieldWithParent
import com.progressive.kherkin.espresso.steps.assertion.IShouldSeeTextFieldWithText
import com.progressive.kherkin.espresso.steps.assertion.IVerifyTextFieldLength
import com.progressive.kherkin.espresso.steps.setup.IRenderScreen
import com.progressive.kherkin.common.testcore.And
import com.progressive.kherkin.common.testcore.Given
import com.progressive.kherkin.common.testcore.Then
import com.progressive.kherkin.common.testcore.When
import com.progressive.sampleapp.screens.espresso.TextFieldScreen
import com.progressive.sampleapp.setup.SampleBaseIntegrationTestCase
import org.junit.Test

class TestTextFieldSteps : SampleBaseIntegrationTestCase() {

    @Test
    fun testEnabledAndDisabledTextFields() {
        Given.IRenderScreen(TextFieldScreen())
        And.IShouldSeeTextField(R.id.editTextEnabledLeft)
        And.IShouldSeeHintText("Enabled")
        And.IShouldSeeTextField(R.id.editTextEnabledLeft)
        And.IEnterTextIntoField(R.id.editTextEnabledLeft, "enable")
        And.IDismissKeyboard()
        And.IShouldSeeTextFieldWithText(R.id.editTextEnabledLeft, "enable")
        And.IShouldSeeTextFieldContaining(R.id.editTextEnabledLeft, "nab")
        And.IShouldSeeTextFieldIsEnabled(R.id.editTextDisabledRight)
        When.IClearField(R.id.editTextEnabledLeft)
        Then.IShouldSeeTextFieldIsDisabled(R.id.editTextDisabledRight)
    }

    @Test
    fun testVisibilityTextFields() {
        Given.IRenderScreen(TextFieldScreen())
        And.IShouldNotSeeTextField(R.id.editTextInvisibleRight)
        And.ILeaveFieldEmpty(R.id.editTextVisibleLeft)
        And.IShouldSeeTextFieldIsEmpty(R.id.editTextVisibleLeft)
        And.IShouldNotSeeTextField(R.id.editTextInvisibleRight)
        When.IEnterTextIntoField(R.id.editTextVisibleLeft, "a")
        Then.IShouldSeeTextFieldWithHint(R.id.editTextInvisibleRight, "Invisible")
        And.IShouldSeeTextFieldHasFocus(R.id.editTextInvisibleRight)
        And.IShouldSeeTextFieldDoesNotHaveFocus(R.id.editTextVisibleLeft)
    }

    @Test
    fun testTextFieldWithLabel() {
        Given.IRenderScreen(TextFieldScreen())
        And.IShouldSeeTextFieldWithParent(R.id.editTextWithLabel, R.id.editTextContainer)
        And.ILeaveFieldWithLabelEmpty(R.id.editTextWithLabel, "Number Input:")
        And.IShouldSeeText("Masked text is empty")
        And.IEnterTextIntoFieldWithLabel(R.id.editTextWithLabel2, "Hides:", "2")
        And.IShouldSeeTextFieldWithLabelAndText(R.id.editTextWithLabel2, "Hides:", "2")
        And.IEnterTextIntoFieldAndDismissKeyboard(R.id.editTextWithLabel, "123")
        And.IShouldNotSeeTextFieldWithLabelAndText(R.id.editTextWithLabel2, "Hides:", "2")
        And.IShouldSeeMaskedText("123")
        And.IShouldSeeText("Masked text: 123")
        When.IEnterTextIntoFieldWithLabelWithParent(R.id.editTextWithLabel, R.id.editTextContainer, "Number Input:", "456")
        Then.IShouldSeeText("Masked text: 123456")
        And.IShouldSeeFieldWithMaskedText(R.id.editTextWithLabel, "123456")
        And.IVerifyTextFieldLength(R.id.editTextWithLabel, 6)
    }

    @Test
    fun testTextFieldWithParent() {
        Given.IRenderScreen(TextFieldScreen())
        And.ILeaveFieldWithParentEmpty(R.id.editTextWithLabel, R.id.editTextContainer)
        And.IShouldSeeHintText("To be hidden")
        When.IEnterTextIntoFieldWithParent(R.id.editTextWithLabel, R.id.editTextContainer, "123")
        Then.IShouldNotSee(R.id.editTextWithLabel2)
    }
}