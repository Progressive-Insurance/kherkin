package com.progressive.kherkin.common.testcore

/**
 * Interface that allows every step definition to use any of the Gherkin grammar rules.
 */
interface Gherkin

object Given : Gherkin
object When : Gherkin
object Then : Gherkin
object And : Gherkin