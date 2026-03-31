package com.nsp.wargasigap.ai

fun getDisasterResponse(userInput: String): String {
    val input = userInput.lowercase()

    // 🔴 PRIORITAS TINGGI
    if (input.contains("terjebak") || input.contains("tidak bisa keluar")) {
        return DisasterContent.TERJEBAK
    }

    // 🌊 BANJIR KONTEKS KHUSUS
    if (input.contains("banjir") && input.contains("malam")) {
        return DisasterContent.BANJIR_MALAM
    }

    // 🔥 Mapping keyword → type
    val disasterType = when {
        input.contains("gempa") -> "gempa"
        input.contains("tsunami") -> "tsunami"
        input.contains("gunung") || input.contains("erupsi") -> "gunung"
        input.contains("longsor") || input.contains("roboh") -> "longsor"
        input.contains("banjir") -> "banjir"
        input.contains("kering") || input.contains("kekeringan") -> "kekeringan"
        input.contains("kebakaran") || input.contains("asap") -> "kebakaran"
        input.contains("angin") || input.contains("puting beliung") -> "puting_beliung"
        input.contains("panik") || input.contains("takut") -> "panik"
        else -> "default"
    }

    val steps = DisasterContent.DATA[disasterType]
        ?: DisasterContent.DATA["default"]!!

    return steps.joinToString("\n\n")
}