package com.example.t_cycle.ui.store;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StoreFragmentViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;

    public StoreFragmentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Store fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
