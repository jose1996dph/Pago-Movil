package com.example.pagomovil.ui.pay;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pagomovil.R;
import com.example.pagomovil.ui.main.MainFragment;
import com.example.pagomovil.ui.main.MainViewModel;

public class PayFragment extends Fragment {

    private PayViewModel mViewModel;
    private boolean isBusy = false;
    private final String NOMBER = "2662";

    protected EditText phone;
    protected EditText dni;
    protected EditText mount;
    protected Spinner banks;
    protected Spinner operators;
    protected Button pay;

    public static PayFragment newInstance() {
        return new PayFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PayViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pay_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        banks = (Spinner)view.findViewById(R.id.banks);
        operators = (Spinner)view.findViewById(R.id.operators);
        phone = (EditText)view.findViewById(R.id.phone);
        dni = (EditText)view.findViewById(R.id.dni);
        mount = (EditText)view.findViewById(R.id.mount);
        pay = (Button) view.findViewById(R.id.btnPay);

        mViewModel.getDni().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                dni.setText(s);
            }
        });

        mViewModel.getMount().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mount.setText(s);
            }
        });

        mViewModel.getPhone().observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                phone.setText(s);
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickedPay(v);
            }
        });
    }

    protected void onClickedPay(View v) {
        if (isBusy)
            return;

        isBusy = true;

        final Button pay = (Button) v;
        pay.setEnabled(false);

        final String number =  this.getBankNumber((String)banks.getSelectedItem());
        final String ope = (String) this.operators.getSelectedItem();
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

        final String text = "Pagar " +  number + " " + ope + phone.getText() + " " + dni.getText() + " " +  mount;

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
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }

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
}
