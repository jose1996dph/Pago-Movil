package com.example.pagomovil.ui.pay;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PayViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> phone;
    private MutableLiveData<String> mount;
    private MutableLiveData<String> dni;

    public  PayViewModel(){
        phone = new MutableLiveData<String>();
        mount = new MutableLiveData<String>();
        dni = new MutableLiveData<String>();
    }

    public LiveData<String> getDni() {
        return dni;
    }

    public void setDni(String dni) { this.dni.setValue(dni); }

    public LiveData<String> getMount() {
        return mount;
    }

    public void setMount(String mount) {
        this.mount.setValue(mount);
    }

    public LiveData<String> getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.setValue(phone);
    }
}
