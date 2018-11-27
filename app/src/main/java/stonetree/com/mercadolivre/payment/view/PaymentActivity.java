package stonetree.com.mercadolivre.payment.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import stonetree.com.mercadolivre.R;
import stonetree.com.mercadolivre.payment.presenter.PaymentPresenter;
import stonetree.com.mercadolivre.session.Session;

public class PaymentActivity extends AppCompatActivity {

    private Button payButton;

    private EditText amountToPay;

    private PaymentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payment);

        presenter = new PaymentPresenter(this);
        presenter.onCreate();

        findComponents();
        setListeners();
    }

    private void setListeners() {
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final long amount = Long.valueOf(amountToPay.getText().toString());
                Session.getInstance().setAmountToPay(amount);
                presenter.proceedWithPayment();
            }
        });
    }

    private void findComponents() {
        amountToPay = findViewById(R.id.amount);
        payButton = findViewById(R.id.pay);
    }

    public void proceedToCreditCardSelection() {

    }
}
