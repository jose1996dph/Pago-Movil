package com.example.pagomovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Debug;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pagomovil.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    private final String NOMBER = "2662";
    private boolean isBusy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        configView();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
        if(ActivityCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(
                MainActivity.this,Manifest
                        .permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]
                    { Manifest.permission.SEND_SMS,},1000);
        }else{

        };

    }

    protected void configView(){
        banks = (Spinner)findViewById(R.id.banks);
        operators = (Spinner)findViewById(R.id.operators);
        phone = (EditText)findViewById(R.id.phone);
        dni = (EditText)findViewById(R.id.dni);
        mount = (EditText)findViewById(R.id.mount);

    }

    public void onClickedPay(View v) {
        if (isBusy)
            return;

        isBusy = true;

        Button pay = (Button) v;
        pay.setEnabled(false);

        String number =  this.getBankNumber((String)banks.getSelectedItem());
        String ope = (String) this.operators.getSelectedItem();
        if (number == null || number.equals("")) {
            pay.setEnabled(true);
            return;
        }
        if (ope == null || ope.equals("")){
            pay.setEnabled(true);
            return;
        }
        if (phone.getText() == null || phone.getText().toString().isEmpty()) {
            showToast("Telefono no valido");
            pay.setEnabled(true);
            return;
        }
        if (dni.getText() == null || dni.getText().toString().isEmpty()) {
            showToast("Cedula no valida");
            pay.setEnabled(true);
            return;
        }
        if (mount.getText() == null || mount.getText().toString().isEmpty()) {
            showToast("Monto no valido");
            pay.setEnabled(true);
            return;
        }
        // Code here
        String mount = this.mount.getText().toString();
        mount = editMount(mount);

        String text = "Pagar " +  number + " " + ope + phone.getText() + " " + dni.getText() + " " +  mount;

        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(NOMBER, null, text, null, null);
            this.phone.setText("");
            this.dni.setText("");
            this.mount.setText("");

            showToast("Procesando...");
        }
        catch (Exception e){
            showToast(e.getMessage());
        }
        finally {
            pay.setEnabled(true);
            isBusy = false;
        }
    }

    protected String editMount(String mount){

        mount = mount.replace(",", "");
        mount = mount.replace(".", ",");

        if (mount.contains(",")) {
            String[] mounts = mount.split(",");
            String decimal = mounts[mounts.length - 1];
            mount = mounts[0] + ",";

            for (int i = 0; i < 2; i++){
                boolean x = (decimal.length() - 1 > i);
                mount += (decimal.length() > i) ? decimal.charAt(i) : "0";
            }
        } else {
            mount += ",00";
        }
        return mount;
    }
    protected void showToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    protected EditText phone;
    protected EditText dni;
    protected EditText mount;
    protected Spinner banks;
    protected Spinner operators;

    protected String getBankNumber(String name){
        switch (name){
            case "100% Banco":
                return "0156";
            case "Bancamiga":
                return "0172";
            case "Bancaribe":
                return "0114";
            case "Banco Agrícola de Venezuela":
                return "0166";
            case "Banco Caroní":
                return "0128";
            case "Banco de Venezuela, S.A.C.A.":
                return "0102";
            case "Banco del Tesoro":
                return "0163";
            case "Banco Plaza":
                return "0138";
            case "Bancrecer":
                return "0168";
            case "Banesco":
                return "0134";
            case "Banfanb":
                return "0177";
            case "Banplus":
                return "0174";
            case "BFC Banco Fondo Común":
                return "0151";
            case "Bicentenario del Pueblo":
                return "0175";
            case "BNC Nacional de Crédito":
                return "0191";
            case "Del Sur":
                return "0157";
            case "Exterior":
                return "0115";
            case "Mercantil":
                return "0105";
            case "Mi Banco":
                return "0169";
            case "Occidental de Descuento":
                return "0116";
            case "Provincial":
                return "0108";
            case "Venezolano de Crédito":
                return "0104";
            default:
                return "";
        }
    }

    /*100% Banco 0156
Bancamiga 0172
Bancaribe 0114
Banco Agrícola de Venezuela 0166
Banco Caroní 0128
Banco de Venezuela, S.A.C.A. 0102
Banco del Tesoro 0163
Banco Plaza 0138
Bancrecer 0168
Banesco 0134
Banfanb 0177
Banplus 0174
BFC Banco Fondo Común 0151
Bicentenario del Pueblo 0175
BNC Nacional de Crédito 0191
Del Sur 0157
Exterior 0115
Mercantil 0105
Mi Banco 0169
Occidental de Descuento 0116
Provincial 0108
Venezolano de Crédito 0104

        * */
}
