package com.example.zealcasestudy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import com.example.zealcasestudy.api.Repository
import com.example.zealcasestudy.model.Lottery
import com.example.zealcasestudy.model.LotteryName
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    private val repository = mockk<Repository>(relaxed = true)

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = MainViewModel(repository)
    }

    @Test
    fun `data is updated when repository returns data`() = runTest {
        val expectedData = listOf(
            Lottery(LotteryName.LOTTO, mockk(), mockk()),
            Lottery(LotteryName.EUROJACKPOT, mockk(), mockk())
        )
        var actualData = listOf<Lottery>()
        coEvery { repository.getData() } returns flowOf(expectedData)

        viewModel.viewModelScope.launch {
            repository.getData().collect {
                actualData = it
            }
        }

        assertEquals(expectedData, actualData)
    }

    @Test
    fun `data is null initially`() {
        assertEquals(null, viewModel.data.value)
    }
}
