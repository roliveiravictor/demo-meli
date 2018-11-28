package stonetree.com.mercadolivre.paymentMethods.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import stonetree.com.mercadolivre.R;
import stonetree.com.mercadolivre.core.view.CoreActivity;
import stonetree.com.mercadolivre.paymentMethods.presenter.PaymentMethodsPresenter;
import stonetree.com.mercadolivre.paymentMethods.view.adapter.PaymentMethodsAdapter;
import stonetree.com.mercadolivre.quotas.view.QuotasSelectionActivity;
import stonetree.com.mercadolivre.utils.IntentStarterUtils;

public class PaymentMethodsActivity extends CoreActivity {

    private RecyclerView paymentMethodsRecycler;

    private PaymentMethodsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payment_methods);

        presenter = new PaymentMethodsPresenter(this);
        presenter.onCreate();

        findComponents();
        loadComponents();
        setListeners();
    }

    private void findComponents() {
        paymentMethodsRecycler = findViewById(R.id.methods);
    }


    private void loadComponents() {
        final PaymentMethodsAdapter adapter = new PaymentMethodsAdapter(this, presenter,  presenter.getPaymentMethodsResponse());

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        paymentMethodsRecycler.setLayoutManager(linearLayoutManager);
        paymentMethodsRecycler.setAdapter(adapter);
    }

    private void setListeners() {

    }

    public void proceedToQuotasSelection() {
        IntentStarterUtils.goFromTo(this, QuotasSelectionActivity.class);
    }

}
