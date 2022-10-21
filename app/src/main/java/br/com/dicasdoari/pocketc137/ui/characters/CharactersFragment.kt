package br.com.dicasdoari.pocketc137.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.dicasdoari.pocketc137.databinding.CharactersFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment: Fragment() {

    private var _binding: CharactersFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharactersViewModel by viewModel()
    private val adapter: CharactersAdapter by lazy { CharactersAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CharactersFragmentBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenToShowCharacters()
        viewModel.fetchCharacters()
    }

    private fun listenToShowCharacters() {
        viewModel.showCharactersEvent.observe(viewLifecycleOwner) {
            adapter.addAll(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}