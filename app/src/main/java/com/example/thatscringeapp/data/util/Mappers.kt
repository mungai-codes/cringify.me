package com.example.thatscringeapp.data.util

import com.example.thatscringeapp.data.remote.dto.ResponseDto
import com.example.thatscringeapp.domain.model.Choice

fun ResponseDto.toChoice(): List<Choice> {
    return choices.map {
        Choice(
            finishReason = it.finishReason,
            index = it.index,
            logProbs = it.logProbs,
            text = it.text
        )
    }
}