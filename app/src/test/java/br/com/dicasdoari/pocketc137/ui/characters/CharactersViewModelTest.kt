package br.com.dicasdoari.pocketc137.ui.characters

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.dicasdoari.pocketc137.data.ResultOf
import br.com.dicasdoari.pocketc137.data.dto.CharacterDTO
import br.com.dicasdoari.pocketc137.data.repository.CharacterRepository
import br.com.dicasdoari.pocketc137.getCharactersMock
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val repository = mockk<CharacterRepository>()
    private val showCharactersEvent: Observer<List<CharacterDTO>> = mockk()
    private val showErrorEvent: Observer<String> = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `carregar a lista de personagens`() = runTest {
        val charactersMock = getCharactersMock().results.sortedBy { it.name }
        coEvery { repository.getCharacters() } returns ResultOf.Success(charactersMock)

        val viewModel = CharactersViewModel(repository)
        viewModel.showCharactersEvent.observeForever(showCharactersEvent)
        viewModel.fetchCharacters()

        coVerify { repository.getCharacters() }
        verify { showCharactersEvent.onChanged(charactersMock) }
    }

    @Test
    fun `carregar a lista de personagens e api retornar 404`() = runTest {

        coEvery { repository.getCharacters() } returns ResultOf.Failure(404)

        val viewModel = CharactersViewModel(repository)
        viewModel.showErrorEvent.observeForever(showErrorEvent)
        viewModel.fetchCharacters()

        coVerify { repository.getCharacters() }
        verify { showErrorEvent.onChanged("ops!") }
    }

    @Test
    fun `exemplo do spyk`() = runTest {

        val charactersMock = getCharactersMock().results.sortedBy { it.species }
        coEvery { repository.getCharacters() } returns ResultOf.Success(charactersMock)

        val viewModel = spyk(CharactersViewModel(repository), recordPrivateCalls = true)

        every { viewModel["sort"](any<List<CharacterDTO>>()) } returns charactersMock

        viewModel.showCharactersEvent.observeForever(showCharactersEvent)
        viewModel.fetchCharacters()

        coVerify { repository.getCharacters() }
        verify { showCharactersEvent.onChanged(charactersMock) }
    }
}