package br.com.dicasdoari.pocketc137.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dicasdoari.pocketc137.data.ResultOf
import br.com.dicasdoari.pocketc137.data.dto.CharacterDTO
import br.com.dicasdoari.pocketc137.data.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharactersViewModel(private val repository: CharacterRepository): ViewModel() {

    private val _showCharactersEvent = MutableLiveData<List<CharacterDTO>>()
    val showCharactersEvent: LiveData<List<CharacterDTO>>
        get() = _showCharactersEvent

    private val _showErrorEvent = MutableLiveData<String>()
    val showErrorEvent: LiveData<String>
        get() = _showErrorEvent

    fun fetchCharacters() {
       viewModelScope.launch {
           when (val result = repository.getCharacters()) {
               is ResultOf.Success -> _showCharactersEvent.postValue(sort(result.value))
               is ResultOf.Failure -> handleError(result)
           }
       }
    }

    private fun handleError(result: ResultOf.Failure) {
        _showErrorEvent.postValue("ops!")
    }

    private fun sort(value: List<CharacterDTO>): List<CharacterDTO> = value.sortedBy { it.name }


}