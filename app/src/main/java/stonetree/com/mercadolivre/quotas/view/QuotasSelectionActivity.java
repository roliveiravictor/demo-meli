package stonetree.com.mercadolivre.quotas.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

import stonetree.com.mercadolivre.R;
import stonetree.com.mercadolivre.core.view.CoreActivity;
import stonetree.com.mercadolivre.paymentAmount.model.PaymentAmount;
import stonetree.com.mercadolivre.quotas.presenter.QuotasSelectionPresenter;
import stonetree.com.mercadolivre.utils.IntentStarterUtils;

public class QuotasSelectionActivity extends CoreActivity {

    private QuotasSelectionPresenter presenter;

    private Button checkout;

    private Spinner quotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quotas_selection);

        presenter = new QuotasSelectionPresenter(this);
        presenter.onCreate();

        findComponents();
        loadComponents();
        setListeners();
    }

    public void findComponents() {
        super.findComponents();
        checkout = findViewById(R.id.checkout);
        quotas = findViewById(R.id.quotas_spinner);
    }


    private void loadComponents() {
        final List<String> quotasArray = presenter.getQuotas();

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, quotasArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        quotas.setAdapter(adapter);
    }

    private void setListeners() {
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceedToCheckout();
            }
        });

        quotas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                final String selectedQuota = (String) parentView.getSelectedItem();
                presenter.storeSelectedQuota(selectedQuota);
                quotas.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                quotas.setEnabled(false);
            }

        });
    }

    public void proceedToCheckout() {
        IntentStarterUtils.goFromTo(this, PaymentAmount.class);
    }
}

