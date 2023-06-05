package io.presently.service.song

import kotlinx.coroutines.flow.Flow

interface SongService {

    fun get(id: String): Flow<Song?>

    fun get(): Flow<List<Song>>

    fun put(song: Song)

}