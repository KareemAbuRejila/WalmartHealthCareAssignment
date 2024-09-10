package com.dotech.walmarthealthcareassignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dotech.walmarthealthcareassignment.data.models.RemoteResponse
import com.dotech.walmarthealthcareassignment.domain.models.Country
import com.dotech.walmarthealthcareassignment.domain.repositories.CountriesRepo
import com.dotech.walmarthealthcareassignment.presentation.ui.countries.CountriesViewModel
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.anyString
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CountriesViewModelTest {

    @get:Rule
    val instanceExecutor = InstantTaskExecutorRule() // For LiveData testing

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    private lateinit var mockCountriesRepo: CountriesRepo

    private lateinit var mockViewModel: CountriesViewModel

    @Mock
    private lateinit var mockListObserver: Observer<List<Country>>
    @Mock
    private lateinit var mockLoadingObserver: Observer<Boolean>
    @Mock
    private lateinit var mockErrorObserver: Observer<String>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        mockViewModel = CountriesViewModel(mockCountriesRepo)
        mockViewModel.countries.observeForever(mockListObserver)
        mockViewModel.isLoading.observeForever(mockLoadingObserver)
        mockViewModel.error.observeForever(mockErrorObserver)
    }

    @Test
    fun `test that countries are fetched successfully`(): Unit = runBlocking {
        //given
        val countryList = listOf(
            Country("FR", "France", "Europe", "Paris"),
            Country("DE", "Germany", "Europe", "Berlin")
        )
        //When
        `when`(mockCountriesRepo.getAllCountries()).thenReturn(
            RemoteResponse.Success(countryList)
        )


        // Act: fetch countries from ViewModel
        mockViewModel.fetchAllCountries()

        // Assert: verify LiveData updates and repository interaction
        verify(mockLoadingObserver, times(2)).onChanged(true)
        verify(mockListObserver, times(1)).onChanged(countryList)
        verify(mockCountriesRepo, times(2)).getAllCountries()
    }


    @Test
    fun `test empty countries list scenario`() : Unit = runBlocking {
        // Arrange: return an empty list from repository
        `when`(mockCountriesRepo.getAllCountries()).thenReturn(
            RemoteResponse.Success(emptyList())
        )

        // Act: fetch countries from ViewModel
        mockViewModel.fetchAllCountries()

        // Assert: check that the LiveData emits an empty list
        verify(mockLoadingObserver, times(2)).onChanged(true)
        verify(mockCountriesRepo, times(2)).getAllCountries()
    }

    @Test
    fun `test loading scenario`() : Unit = runBlocking {
        // Arrange: return an empty list from repository
        `when`(mockCountriesRepo.getAllCountries()).thenReturn(
            RemoteResponse.Loading
        )

        // Act: fetch countries from ViewModel
        mockViewModel.fetchAllCountries()

        // Assert: check that the LiveData emits an empty list
        verify(mockLoadingObserver, times(2)).onChanged(true)
    }
    @Test
    fun `test repository failure scenario`(): Unit = runBlocking {
        // Arrange: simulate an exception from repository
        `when`(mockCountriesRepo.getAllCountries()).thenReturn(
            RemoteResponse.Error("ERROR")
        )

        // Act: fetch countries from ViewModel and assert an empty result
        mockViewModel.fetchAllCountries()

        // Assert: verify that no data was emitted in case of failure
        verify(mockLoadingObserver, times(2)).onChanged(true)
        verify(mockCountriesRepo, times(2)).getAllCountries()    }
}