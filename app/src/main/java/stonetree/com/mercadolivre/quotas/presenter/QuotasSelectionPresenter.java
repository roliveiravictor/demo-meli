package stonetree.com.mercadolivre.quotas.presenter;

import stonetree.com.mercadolivre.quotas.view.QuotasSelectionActivity;

public class QuotasSelectionPresenter implements IQuotasSelectionPresenter {

    private QuotasSelectionActivity view;

    public QuotasSelectionPresenter(QuotasSelectionActivity view) {
        this.view = view;
    }

    @Override
    public void onCreate() {

    }
}
