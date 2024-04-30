package com.example.quizgame

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View.BaseSavedState

class ActionSavedState : BaseSavedState {

    private lateinit var state: ActionUiState

    constructor(superState: Parcelable) : super(superState)
    private constructor(parcelIn: Parcel) : super(parcelIn) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(
                ActionUiState::class.java.classLoader,
                ActionUiState::class.java
            ) as ActionUiState
        } else {
            parcelIn.readSerializable() as ActionUiState
        }
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore(): ActionUiState = state

    fun save(uiState: ActionUiState) {
        state = uiState
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<ActionSavedState> {
        override fun createFromParcel(parcel: Parcel): ActionSavedState =
            ActionSavedState(parcel)

        override fun newArray(size: Int): Array<ActionSavedState?> =
            arrayOfNulls(size)
    }
}