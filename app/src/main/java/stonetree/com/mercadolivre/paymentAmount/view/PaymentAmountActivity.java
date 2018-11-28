package stonetree.com.mercadolivre.paymentAmount.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import stonetree.com.mercadolivre.R;
import stonetree.com.mercadolivre.constants.Constants;
import stonetree.com.mercadolivre.core.view.CoreActivity;
import stonetree.com.mercadolivre.paymentAmount.presenter.PaymentAmountPresenter;
import stonetree.com.mercadolivre.paymentMethods.model.PaymentMethodsResponse;
import stonetree.com.mercadolivre.paymentMethods.view.PaymentMethodsActivity;
import stonetree.com.mercadolivre.utils.IntentStarterUtils;

public class PaymentAmountActivity extends CoreActivity {

    private Button payButton;

    private EditText amountToPay;

    private PaymentAmountPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payment_amount);

        presenter = new PaymentAmountPresenter(this);
        presenter.onCreate();

        findComponents();
        setListeners();
    }

    private void findComponents() {
        payButton = findViewById(R.id.pay);
        amountToPay = findViewById(R.id.amount);
    }

    private void setListeners() {
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final long amount = Long.valueOf(amountToPay.getText().toString());
                    presenter.proceedWithPayment(amount);
                } catch (NumberFormatException e) {
                    //TODO - Nice Try =)
                }
            }
        });
    }

    public void proceedToCreditCardSelection(PaymentMethodsResponse response) {
        final Intent intent = new Intent();
        final Bundle bundle = new Bundle();

        bundle.putSerializable(Constants.PAYMENT_METHODS_RESPONSE, response);
        intent.putExtras(bundle);

        IntentStarterUtils.goFromWithExtraBundleTo(this, PaymentMethodsActivity.class, bundle);
    }
}
