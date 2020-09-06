package com.matterox.dictionary.data.helpers

import android.content.Context
import com.matterox.dictionary.R
import com.matterox.dictionary.data.domain.PartOfSpeechCode

class PartOfSpeechMapper(
    private val context: Context
) {
    fun transform(partOfSpeechCode: PartOfSpeechCode?): String? {
        return when(partOfSpeechCode) {
            PartOfSpeechCode.NOUN -> context.getString(R.string.part_of_speech_noun)
            PartOfSpeechCode.VERB -> context.getString(R.string.part_of_speech_verb)
            PartOfSpeechCode.ADJECTIVE -> context.getString(R.string.part_of_speech_adjective)
            PartOfSpeechCode.ADVERB -> context.getString(R.string.part_of_speech_adverb)
            PartOfSpeechCode.PREPOSITION -> context.getString(R.string.part_of_speech_preposition)
            PartOfSpeechCode.PRONOUN -> context.getString(R.string.part_of_speech_pronoun)
            PartOfSpeechCode.CARDINAL_NUMBER -> context.getString(R.string.part_of_speech_cardinal_number)
            PartOfSpeechCode.CONJUNCTION -> context.getString(R.string.part_of_speech_conjunction)
            PartOfSpeechCode.INTERJECTION -> context.getString(R.string.part_of_speech_interjection)
            PartOfSpeechCode.ARTICLE -> context.getString(R.string.part_of_speech_article)
            PartOfSpeechCode.ABBREVIATION -> context.getString(R.string.part_of_speech_abbreviation)
            PartOfSpeechCode.PARTICLE -> context.getString(R.string.part_of_speech_particle)
            PartOfSpeechCode.ORDINAL_NUMBER -> context.getString(R.string.part_of_speech_ordinal_number)
            PartOfSpeechCode.MODAL_VERB -> context.getString(R.string.part_of_speech_ordinal_modal_verb)
            PartOfSpeechCode.PHRASE -> context.getString(R.string.part_of_speech_ordinal_phrase)
            PartOfSpeechCode.IDIOM -> context.getString(R.string.part_of_speech_ordinal_idiom)
            else -> null
        }
    }
}