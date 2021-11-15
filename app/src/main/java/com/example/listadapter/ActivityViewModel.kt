package com.example.listadapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

sealed class UiState {
    data class ShowList(val abc: Abc, val data: List<XYZ>?): UiState()
    object Loading: UiState()
}

class ActivityViewModel : ViewModel() {

    var abc: Abc? = null
    private var xyz: List<XYZ>? = null
    private var _uiState: MutableLiveData<UiState> = MutableLiveData()
    var uiState: LiveData<UiState> = _uiState

    fun fetchData() {
        viewModelScope.launch {

            val firstAsync = async {
                if (abc == null) {
//                    val response = ApiInterface.create().getAbc()
                    abc = mockAbc()
                }
            }
            val secondAsync = async {
//                val response = ApiInterface.create().getXyz()
                xyz = mockXYZ()
            }
            firstAsync.await()
            secondAsync.await()

            abc?.let {
                _uiState.postValue(UiState.ShowList(it, xyz))
            }
        }
    }

    private fun mockAbc(): Abc {
        return Abc(
            id = "1",
            name = "Vivek"
        )
    }

    private fun mockXYZ(): List<XYZ> {
        return listOf(
            XYZ(
                id = "1",
                title = "Abc",
                count = 1,
                status = "open",
                item = Qqq(
                    id = "1.1",
                    rr = Rr(
                        firstName = "Vivek",
                        lastName = "Modi",
                        id = "1.2"
                    )
                )
            ),
            XYZ(
                id = "2",
                title = "qwe",
                count = 2,
                status = "close",
                item = Qqq(
                    id = "2.1",
                    rr = Rr(
                        firstName = "Modi",
                        lastName = "Vivek",
                        id = "2.2"
                    )
                )
            )
        )
    }
}