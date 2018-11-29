package stonetree.com.mercadolivre.paymentAmount.view;

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
import stonetree.com.mercadolivre.watcher.PriceTextWatcher;

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

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroyNetworkStateReceiver();
    }

    @Override
    public void findComponents() {
        super.findComponents();
        payButton = findViewById(R.id.pay);
        amountToPay = findViewById(R.id.amount);
    }

    private void setListeners() {
        amountToPay.addTextChangedListener(new PriceTextWatcher(amountToPay));

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final String price = amountToPay.getText().toString();
                    final long amount = Long.valueOf(presenter.getFormattedPrice(price));
                    presenter.proceedWithPayment(amount);
                } catch (NumberFormatException e) {

                }
            }
        });
    }

    public void proceedToCreditCardSelection(PaymentMethodsResponse response) {
        final Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.PAYMENT_METHODS_RESPONSE, response);

        IntentStarterUtils.goFromWithExtraBundleTo(this, PaymentMethodsActivity.class, bundle);
    }

    public void enablePayment() {
        payButton.setEnabled(true);
    }

    public void disablePayment() {
        payButton.setEnabled(false);
    }
}
