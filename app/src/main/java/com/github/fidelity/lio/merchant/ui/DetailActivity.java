package com.github.fidelity.lio.merchant.ui;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.AuthConfig;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.github.fidelity.lio.merchant.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
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

}
