package com.example.pagomovil.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> phone;
    private MutableLiveData<String> dni;

    public  MainViewModel(){
        phone = new MutableLiveData<String>();
        dni = new MutableLiveData<String>();
    }

    public LiveData<String> getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni.setValue(dni);
    }

    public void setPhone(String phone) {
        this.phone.setValue(phone);
    }

    public LiveData<String> getPhone() {
        return phone;
    }
}
