package com.example.pagomovil.ui.balance;

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
import android.widget.Toast;

import com.example.pagomovil.R;

public class BalanceFragment extends Fragment {

    private BalanceViewModel mViewModel;
    private final String NOMBER = "2662";
    private boolean isBusy = false;

    protected Button csc;
    protected Button cst;
    protected Button cmc;
    protected Button cmt;

    public static BalanceFragment newInstance() {
        return new BalanceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.balance_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BalanceViewModel.class);
        // TODO: Use the ViewModel
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        csc = view.findViewById(R.id.csc);
        cst = view.findViewById(R.id.cst);
        cmc = view.findViewById(R.id.cmc);
        cmt = view.findViewById(R.id.cmt);

        csc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage("CSC");
            }
        });

        cst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage("CST");
            }
        });

        cmc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage("CMC");
            }
        });

        cmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage("CMT");
            }
        });
    }

    protected void sendMessage(String text){
        if (isBusy)
            return;

        isBusy = true;
        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(NOMBER, null, text, null, null);

            showToast("Procesando...");
        }
        catch (Exception e){
            showToast(e.getMessage());
        }
        finally {
            isBusy = false;
        }
    }

    protected void showToast(String text){
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }
}
