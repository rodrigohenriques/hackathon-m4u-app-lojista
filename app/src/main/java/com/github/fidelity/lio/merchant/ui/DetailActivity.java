package com.github.fidelity.lio.merchant.ui;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.AuthConfig;
import com.digits.sdk.android.Digits;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.github.fidelity.lio.lojista.domain.Order;
import com.github.fidelity.lio.lojista.domain.RemoteFidelityRepository;
import com.github.fidelity.lio.merchant.MerchantApplication;
import com.github.fidelity.lio.merchant.R;
import com.github.fidelity.lio.merchant.entities.Extra;
import com.github.fidelity.lio.merchant.ui.view.SelectableEditText;
import com.github.fidelity.lio.merchant.ui.view.SelectableEditText.Item;
import com.github.fidelity.lio.merchant.utils.formatter.DateTimeFormatter;
import com.github.fidelity.lio.merchant.utils.formatter.Formatter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetailActivity extends BaseActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.orderId) TextView orderId;
    @Bind(R.id.orderDate) TextView orderDate;
    @Bind(R.id.amount) TextView amount;
    @Bind(R.id.editTextPoints) EditText editTextPoints;
    @Bind(R.id.editTextDiscount) EditText editTextDiscount;

    @Inject RemoteFidelityRepository remoteFidelityRepository;
    @Inject Formatter<String, String> currencyFormatter;
    @Inject DateTimeFormatter dateTimeFormatter;

    @BindColor(android.R.color.white) int white;

    private Order order;
    FidelityItem fidelityItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        ButterKnife.bind(this);
        ((MerchantApplication) getApplication()).getApplicationComponent().inject(this);

        List<FidelityItem> fidelityPoints = buildFidelityPoints();
        ArrayAdapter<FidelityItem> fidelityItemArrayAdapter = new ArrayAdapter<>(getBaseContext(), R.layout.spinner_dropdown_item, fidelityPoints);

        SelectableEditText<FidelityItem> fidelitySelectableEditText = (SelectableEditText) findViewById(R.id.fidelitySpinner);
        fidelitySelectableEditText.setAdapter(fidelityItemArrayAdapter);
        fidelitySelectableEditText.setOnItemSelectedListener((item, selectedIndex) -> fidelityItem = item);

        order = (Order) getIntent().getSerializableExtra(Extra.ORDER);

        String remaining = order.getRemaining().toString();

        orderId.setText(order.getNumber());
        orderDate.setText(dateTimeFormatter.format(order.getCreatedAt()));
        amount.setText(currencyFormatter.format(remaining));


        toolbar.setTitle("Detalhe de pedidos");
        toolbar.setTitleTextColor(white);
    }

    private List<FidelityItem> buildFidelityPoints() {
        List<FidelityItem> fidelityItems = new ArrayList<>();
        fidelityItems.add(new FidelityItem("DOTZ"));
        fidelityItems.add(new FidelityItem("LIVELO"));
        fidelityItems.add(new FidelityItem("MULTIPLUS"));
        fidelityItems.add(new FidelityItem("SMILES"));
        return fidelityItems;
    }

    @OnClick(R.id.finishButton)
    public void finishButtonOnClick(View view) {
        if (fidelityItem == null) {
            Toast.makeText(this, "Plano fidelidade não selecionado", Toast.LENGTH_LONG).show();
        } else if (editTextPoints.getText().length() == 0) {
            Toast.makeText(this, "Quantidade de pontos não informada", Toast.LENGTH_LONG).show();
        } else {
            startDialog();
        }
    }

    private void startDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.phone_number_dialog, null);

        dialogBuilder.setView(dialogView);

        Button smsReceiver = (Button) dialogView.findViewById(R.id.smsReceiver);
        EditText phoneNumberField = (EditText) dialogView.findViewById(R.id.phoneNumberField);
        smsReceiver.setOnClickListener(view -> {
            AuthConfig.Builder authConfigBuilder = new AuthConfig.Builder()
                    .withAuthCallBack(new AuthCallback() {
                        @Override
                        public void success(DigitsSession session, String phoneNumber) {
                            DetailActivity.this.finish();
                            Long amount;
                            if (editTextDiscount.getText().toString().equals("") || editTextDiscount.getText().toString().isEmpty()) {
                                amount = Long.valueOf(order.getRemaining());
                            } else {
                                amount = Long.valueOf(editTextDiscount.getText().toString());
                            }
                            remoteFidelityRepository.checkoutOrder("00000000000100", order.getId(),
                                    Long.valueOf(editTextPoints.getText().toString()), amount,
                                    fidelityItem.name.toLowerCase(), "+5521979442064")
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(
                                            aVoid -> {
                                                Toast.makeText(DetailActivity.this, "Transação executada com sucesso", Toast.LENGTH_LONG).show();
                                            },
                                            error -> {
                                                Toast.makeText(DetailActivity.this, "Erro: " + error.getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                    );
                        }

                        @Override
                        public void failure(DigitsException error) {
                            Toast.makeText(DetailActivity.this, "Erro ao validar SMS Token: " + error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .withPhoneNumber("+55" + phoneNumberField.getText().toString());

            Digits.authenticate(authConfigBuilder.build());
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    public class FidelityItem implements Item {

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
