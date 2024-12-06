package com.umar.assigment.data.remote.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.json.JsonObject
import org.json.JSONArray
import org.json.JSONObject

data class ProblemsDTO(
    @SerializedName("problems") val problemsList: List<Map<String, List<ProblemDetail>>>
)

data class ProblemDetail(
    @SerializedName("medications") val medications: List<Medication>? = null,
    @SerializedName("labs") val labs: List<Lab>? = null
)

data class Medication(
    @SerializedName("medicationsClasses") val medicationsClasses: List<Map<String , Any>>? = null
)

//data class MedicationClass(
//    @SerializedName("className") val className: List<AssociatedDrugGroup>? = null,
//    @SerializedName("className2") val className2: List<AssociatedDrugGroup>? = null
//)
//
//data class AssociatedDrugGroup(
//    @SerializedName("associatedDrug") val associatedDrug: List<AssociatedDrug>? = null,
//    @SerializedName("associatedDrug#2") val associatedDrug2: List<AssociatedDrug>? = null
//)
//
//data class AssociatedDrug(
//    @SerializedName("name") val name: String? = null,
//    @SerializedName("dose") val dose: String? = null,
//    @SerializedName("strength") val strength: String? = null
//)
//
data class Lab(
    @SerializedName("missing_field") val missingField: String? = null
)
