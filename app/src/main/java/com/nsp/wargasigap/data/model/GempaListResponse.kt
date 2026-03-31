package com.nsp.wargasigap.data.model

data class GempaListResponse(
    val Infogempa: InfoGempaList
)

data class InfoGempaList(
    val gempa: List<Gempa>
)