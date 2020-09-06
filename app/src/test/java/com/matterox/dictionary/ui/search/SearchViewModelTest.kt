package com.matterox.dictionary.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.matterox.dictionary.data.domain.MeaningPreviewModel
import com.matterox.dictionary.data.domain.SearchedWordModel
import com.matterox.dictionary.data.network.Result
import com.matterox.dictionary.data.repository.DictionaryRepository
import com.matterox.dictionary.testutils.CoroutineRule
import com.matterox.dictionary.ui.base.navigation.NavManager
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    internal lateinit var navManager: NavManager

    @MockK
    internal lateinit var dictionaryRepository: DictionaryRepository

    private lateinit var vm: SearchViewModel

    object StubVars {
        val searchInput = "test input"
        val meaningId = "1"

        val meaningPreviewModel = MeaningPreviewModel(
            meaningId,
            "",
            "",
            "",
            "",
            ""
        )
        val searchedWordModel = SearchedWordModel(
            "test",
            listOf(),
            listOf()
        )

        val successResult = Result.Success(listOf(searchedWordModel))
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        vm = SearchViewModel(
            dictionaryRepository,
            navManager
        )
    }

    @Test
    fun testOnMeaningClicked() {
        val navDirections = SearchFragmentDirections.actionSearchFragmentToMeaningsFragment(
            meaningId = StubVars.meaningId
        )

        vm.onMeaningClicked(StubVars.meaningPreviewModel)

        verify { navManager.navigate(navDirections) }
    }

    @Test
    fun testSearchInput() {
        coEvery { dictionaryRepository.searchWord(StubVars.searchInput) } returns StubVars.successResult

        vm.searchInput(StubVars.searchInput)

        coVerify { dictionaryRepository.searchWord(StubVars.searchInput) }
    }
}
