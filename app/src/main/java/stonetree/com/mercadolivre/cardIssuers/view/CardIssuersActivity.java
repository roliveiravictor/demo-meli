package stonetree.com.mercadolivre.cardIssuers.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import stonetree.com.mercadolivre.R;
import stonetree.com.mercadolivre.cardIssuers.presenter.CardIssuersPresenter;
import stonetree.com.mercadolivre.cardIssuers.view.adapter.CardIssuersAdapter;
import stonetree.com.mercadolivre.core.view.CoreActivity;
import stonetree.com.mercadolivre.quotas.view.QuotasSelectionActivity;
import stonetree.com.mercadolivre.utils.IntentStarterUtils;

public class CardIssuersActivity extends CoreActivity {

    private RecyclerView cardIssuerReycler;

    private CardIssuersPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card_issuers);

        presenter = new CardIssuersPresenter(this);
        presenter.onCreate();

        findComponents();
        loadComponents();
        setListeners();
    }

    public void findComponents() {
        super.findComponents();
        cardIssuerReycler = findViewById(R.id.issuers);
    }


    private void loadComponents() {
        final CardIssuersAdapter adapter = new CardIssuersAdapter(this, presenter, presenter.getCardIssuersResponse());

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        cardIssuerReycler.setLayoutManager(linearLayoutManager);
        cardIssuerReycler.setAdapter(adapter);
    }

    private void setListeners() {

    }

    public void proceedToQuotasSelection() {
        IntentStarterUtils.goFromTo(this, QuotasSelectionActivity.class);
    }

}
