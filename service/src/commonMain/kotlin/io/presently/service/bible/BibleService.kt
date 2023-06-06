package io.presently.service.bible

import kotlinx.coroutines.flow.Flow

interface BibleService {

    fun get(translationId: String): Flow<List<BibleBook>>

    fun translations(): Flow<List<BibleTranslation>>

}