package com.davidg.candyspacetask.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.davidg.candyspacetask.domain.common.NetworkResultState
import com.davidg.candyspacetask.domain.model.ErrorModel
import com.davidg.candyspacetask.domain.model.StackUsersModel
import com.davidg.candyspacetask.domain.usecase.GetUsersUseCase
import com.util.InstantExecutorExtension
import com.util.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.junit.*
import org.junit.Assert.*
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var list: ArrayList<StackUsersModel>

    private var getUsersUseCase: GetUsersUseCase = mockk()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    //Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Test
    fun `test search user`() {
        // Let's do an answer for the liveData
        val name = "David Gormally"
        val stackResponse = StackUsersModel(
            accountID = 6102474,
            userName = "David Gormally",
            avatar = "https://www.gravatar.com/avatar/5476d6d1524e3d7c35cfecab0a521f15?s=256&d=identicon&r=PG&f=1",
            creation_date = 1428446243,
            reputation = 0,
            location = null,
            noOfBronze = 1,
            noOfGold = 0,
            noOfSilver = 0
        )

        list = ArrayList()
        list.add(stackResponse)
        //1- Mock calls
        coEvery { getUsersUseCase.execute(name, 20) } returns flow {
            emit(NetworkResultState.Success(list))
        }
        mainViewModel = MainViewModel(getUsersUseCase)
        mainViewModel.getUsers(name, 20)

        //2-Call
        GlobalScope.launch {
            mainViewModel._usersStateFlow.collect { }
        }

        val result = mainViewModel._usersStateFlow.value?.data?.get(0)

        assertEquals(0, result?.reputation)

        assertEquals(1428446243, result?.creation_date?.toInt())

        assertEquals(stackResponse, result)
    }

    @Test
    fun `test get user Server error`() {
        val param = ""
        val stackResponse = ErrorModel.ServerError(400, "pagesize")
        //1- Mock calls
        coEvery { getUsersUseCase.execute(param, 200000) } returns flow {
            emit(NetworkResultState.Error(stackResponse))
        }
        mainViewModel = MainViewModel(getUsersUseCase)

        //2-Call
        mainViewModel.getUsers(param, 200000)

        GlobalScope.launch {
            mainViewModel._usersStateFlow.collect { }
        }

        val result = mainViewModel._usersStateFlow.value?.error as ErrorModel.ServerError

        assertEquals(true, result is ErrorModel.ServerError)

        assertEquals(400, result.code)

        assertEquals("pagesize", result.message)

    }

    @Test
    fun `test users empty`() {
        val name = ""
        //1- Mock calls
        list = ArrayList()
        //1- Mock calls
        coEvery { getUsersUseCase.execute(name, 20) } returns flow {
            emit(NetworkResultState.Success(list))
        }
        mainViewModel = MainViewModel(getUsersUseCase)

        //2-Call
        mainViewModel.getUsers(name, 20)

        GlobalScope.launch {
            mainViewModel._usersStateFlow.collect { }
        }

        val result = mainViewModel._usersStateFlow.value?.data?.isEmpty()

        assertEquals(true, result)
    }

}

