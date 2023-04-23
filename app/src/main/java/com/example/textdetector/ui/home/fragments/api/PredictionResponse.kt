package com.example.textdetector.ui.home.fragments.api

import com.google.gson.annotations.SerializedName

data class PredictionResponse(

	@field:SerializedName("class_label")
	val classLabel: Int? = null,

	@field:SerializedName("class_probs")
	val classProbs: ClassProbs? = null
)

data class ClassProbs(

	@field:SerializedName("Offensive")
	val offensive: String? = null,

	@field:SerializedName("Hate")
	val hate: String? = null,

	@field:SerializedName("Neither")
	val neither: String? = null
)
