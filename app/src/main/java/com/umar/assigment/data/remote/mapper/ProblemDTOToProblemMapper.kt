package com.umar.assigment.data.remote.mapper

import com.umar.assigment.data.remote.model.ProblemsDTO
import com.umar.assigment.domain.model.AssociatedDrug
import com.umar.assigment.domain.model.Problem

fun ProblemsDTO.toProblem(): List<Problem> {
    val problems = mutableListOf<Problem>()

    problemsList.forEach { problemMap ->
        problemMap.keys.forEach { problemKey ->
            val problemDetails = problemMap[problemKey]
            val medicationsClasses = problemDetails
                ?.getOrNull(0)
                ?.medications
                ?.getOrNull(0)
                ?.medicationsClasses
                ?.getOrNull(0)

            if (medicationsClasses != null) {
                processMedicationClasses(problemKey, medicationsClasses, problems)
            } else {
                // Add problem with no medication classes
                problems.add(Problem(problemName = problemKey))
            }
        }
    }
    return problems
}

private fun processMedicationClasses(
    problemKey: String,
    medicationsClasses: Map<String, Any>,
    problems: MutableList<Problem>
) {
    medicationsClasses.forEach { (classNameKey, classValues) ->
        val associatedDrugs = extractAssociatedDrugs(classValues)
        problems.add(
            Problem(
                problemName = problemKey,
                className = classNameKey,
                associatedDrugs = associatedDrugs
            )
        )
    }
}

private fun extractAssociatedDrugs(classValues: Any): List<AssociatedDrug> {
    val associatedDrugs = mutableListOf<AssociatedDrug>()
    if (classValues is List<*>) {
        classValues.forEach { item ->
            if (item is Map<*, *>) {
                item.forEach { (innerKey, innerValue) ->
                    if (innerValue is List<*>) {
                        innerValue.forEach { drugDetails ->
                            if (drugDetails is Map<*, *>) {
                                associatedDrugs.add(
                                    AssociatedDrug(
                                        id = innerKey.toString(),
                                        name = drugDetails["name"]?.toString(),
                                        dose = drugDetails["dose"]?.toString(),
                                        strength = drugDetails["strength"]?.toString()
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    return associatedDrugs
}