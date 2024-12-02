package com.progressive.kherkin.common.testcore

/**
 * Preconditions are used for any setup logic that must be set for a test to load in the desired
 * state. They are also necessary for navigation logic in end-to-end tests.
 */
object PreconditionsData {
    private val defaults: Map<String, Any> by lazy {
        mapOf<String, Any>()}

    private val conditions: MutableMap<String, Any> = defaults.toMutableMap()

    val preconditions: Map<String, Any>
        get() = conditions

    @JvmStatic
    fun reset() {
        conditions.clear()
        conditions.putAll(defaults)
    }

    fun conditionsMatch(definedPreconditions: Map<String, Any>): Int {
        if(definedPreconditions.isEmpty()) return 1

        var matches = 0

        definedPreconditions.keys.forEach { key ->
            if (definedPreconditions[key] != conditions[key]) {
                return 0
            } else if (definedPreconditions[key] == conditions[key]) {
                matches += 10
            }
        }

        return matches
    }

    fun addCondition(key: String, value: Any) {
        conditions[key] = value
    }

    fun addMultipleConditions(ourConditions: Map<String, Any>) {
        conditions.putAll(ourConditions)
    }
}