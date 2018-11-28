package stonetree.com.mercadolivre.quotas.view;

import android.os.Bundle;

import stonetree.com.mercadolivre.R;
import stonetree.com.mercadolivre.core.view.CoreActivity;
import stonetree.com.mercadolivre.quotas.presenter.QuotasSelectionPresenter;

public class QuotasSelectionActivity extends CoreActivity {

    private QuotasSelectionPresenter presenter;

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
    }


    private void loadComponents() {

    }

    private void setListeners() {

    }

    public void proceedToQuotasSelection() {

    }

}

