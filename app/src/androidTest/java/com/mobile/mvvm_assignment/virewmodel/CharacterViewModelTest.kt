package com.mobile.mvvm_assignment.virewmodel


import androidx.lifecycle.Observer
import com.mobile.mvvm_assignment.fragment.CharactersFragment
import com.mobile.mvvm_assignment.fragment.StaffFragment
import com.mobile.mvvm_assignment.fragment.StudentsFragment
import com.mobile.mvvm_assignment.hiltmodule.NetworkModule
import com.mobile.mvvm_assignment.model.AllCharacters
import com.mobile.mvvm_assignment.repository.CharacterRepository
import com.mobile.mvvm_assignment.viewmodel.CharactersViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
@UninstallModules(NetworkModule::class)
class CharacterViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var characterAPIRepository: CharacterRepository

    private lateinit var charactersFragment: CharactersFragment
    private lateinit var staffFragment: StaffFragment
    private lateinit var studentsFragment: StudentsFragment

    private lateinit var charactersViewModel: CharactersViewModel

    @Before
    fun setup() {
        hiltRule.inject()
        charactersViewModel = CharactersViewModel(characterAPIRepository)
    }

    @Test
    fun testFetchCharactersSuccess() {
        val observer = Observer<AllCharacters?> { characters ->
            assertNotNull(characters)
            assertEquals(charactersFragment.total, characters?.size) // Example assertion
        }
        charactersViewModel.fetchCharacters()
    }

    @Test
    fun testFetchCharactersFailure() {
        val observer = Observer<AllCharacters?> { characters ->
            assertNotNull(characters)
            assertEquals(charactersFragment.total - 1, characters?.size) // Example assertion
        }
        charactersViewModel.fetchCharacters()
    }

    @Test
    fun testFetchStaffSuccess() {
        val observer = Observer<AllCharacters?> { characters ->
            assertNotNull(characters)
            assertEquals(staffFragment.total, characters?.size) // Example assertion
        }
        charactersViewModel.fetchCharacters()
    }

    @Test
    fun testFetchStaffFailure() {
        val observer = Observer<AllCharacters?> { characters ->
            assertNotNull(characters)
            assertEquals(staffFragment.total - 1, characters?.size) // Example assertion
        }
        charactersViewModel.fetchCharacters()
    }

    @Test
    fun testFetchStudentsSuccess() {
        val observer = Observer<AllCharacters?> { characters ->
            assertNotNull(characters)
            assertEquals(studentsFragment.total, characters?.size) // Example assertion
        }
        charactersViewModel.fetchCharacters()
    }

    @Test
    fun testFetchStudentsFailure() {
        val observer = Observer<AllCharacters?> { characters ->
            assertNotNull(characters)
            assertEquals(studentsFragment.total - 1, characters?.size) // Example assertion
        }
        charactersViewModel.fetchCharacters()
    }
}