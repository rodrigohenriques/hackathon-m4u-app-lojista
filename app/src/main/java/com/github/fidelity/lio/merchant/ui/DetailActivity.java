package com.github.fidelity.lio.merchant.ui;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.AuthConfig;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.github.fidelity.lio.merchant.R;
import com.github.fidelity.lio.merchant.ui.view.Spinner;
import com.github.fidelity.lio.merchant.ui.view.Spinner.SpinnerItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        List<FidelityItem> fidelityPoints = buildFidelityPoints();
        ArrayAdapter<FidelityItem> fidelityItemArrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, fidelityPoints);

        Spinner<FidelityItem> fidelitySpinner = (Spinner<FidelityItem>) findViewById(R.id.fidelitySpinner);
        fidelitySpinner.setAdapter(fidelityItemArrayAdapter);
        ButterKnife.bind(this);

    }

    private List<FidelityItem> buildFidelityPoints() {
        List<FidelityItem> fidelityItems = new ArrayList<>();
        fidelityItems.add(new FidelityItem("DOTS"));
        fidelityItems.add(new FidelityItem("LIVERO"));
        fidelityItems.add(new FidelityItem("MULTIPLUS"));
        fidelityItems.add(new FidelityItem("SMYLES"));
        return fidelityItems;
    }

    @OnClick(R.id.finishButton)
    public void finishButtonOnClick(View view) {
        startDialog();
    }

    private void startDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.phone_number_dialog, null);
        dialogBuilder.setView(dialogView);

        Button smsReceiver = (Button) dialogView.findViewById(R.id.smsReceiver);
        smsReceiver.setOnClickListener(view -> {
            AuthConfig.Builder authConfigBuilder = new AuthConfig.Builder()
                    .withAuthCallBack(new AuthCallback() {
                        @Override
                        public void success(DigitsSession session, String phoneNumber) {

                        }

                        @Override
                        public void failure(DigitsException error) {

                        }
                    })
                    .withPhoneNumber("+5521967319761");

            Digits.authenticate(authConfigBuilder.build());
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    public class FidelityItem implements SpinnerItem {

        private String name;

        public FidelityItem(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

    }

}
