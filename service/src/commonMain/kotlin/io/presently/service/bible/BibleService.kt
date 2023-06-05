package io.presently.service.bible

import kotlinx.coroutines.flow.Flow

interface BibleService {

    fun get(translationId: String): Flow<List<BibleBook>>

    fun getTranslations(): Flow<List<BibleTranslation>>

}