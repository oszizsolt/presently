package io.presently.service.list

import kotlinx.coroutines.flow.Flow

interface ListService {

    fun get(): Flow<List<SongList>>

    fun get(id: String): Flow<SongList?>

}