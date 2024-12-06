package com.umar.assigment.domain.model

import android.telephony.SignalStrength
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer


@Serializable
@Entity( tableName = "table_problem")
data class Problem (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var problemName : String?  = null,
    var className : String? = null,
    var associatedDrugs: List<AssociatedDrug> =  listOf<AssociatedDrug>()
)

//data class ProblemItem(
//    var name : String? = null,
//    var problemDetails: List<ProblemDetail> = listOf<ProblemDetail>()
//)
//
//
//data class ProblemDetail(
//    var medications: List<Medication>
//    // Add Lab as well
//)
//
//data class Medication(
//    var medicineClasses: List<MedicineClass>
//)
//
//data class MedicineClass(
//    var medicineClassname : String? = null,
//    var associatedDrugs: List<AssociatedDrug> = listOf<AssociatedDrug>()
//)
//
@Serializable
 data class AssociatedDrug (
    var id: String? =null,
    var name: String? = null,
    var dose: String? = null,
    var strength: String? = null
)



