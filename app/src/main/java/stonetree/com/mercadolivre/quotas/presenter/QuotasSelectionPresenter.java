package stonetree.com.mercadolivre.quotas.presenter;

import java.util.List;

import stonetree.com.mercadolivre.constants.Constants;
import stonetree.com.mercadolivre.quotas.model.Quotas;
import stonetree.com.mercadolivre.quotas.model.QuotasSelectionResponse;
import stonetree.com.mercadolivre.quotas.view.QuotasSelectionActivity;
import stonetree.com.mercadolivre.session.Session;
import stonetree.com.mercadolivre.utils.QuotasUtils;

public class QuotasSelectionPresenter implements IQuotasSelectionPresenter {

    private QuotasSelectionActivity view;

    private Quotas model = new Quotas();

    public QuotasSelectionPresenter(QuotasSelectionActivity view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
        storeBundle();
    }

    @Override
    public void storeBundle() {
        final QuotasSelectionResponse response = (QuotasSelectionResponse) view.loadBundle(Constants.QUOTAS_SELECTION_RESPONSE);
        model.setQuotasSelectionResponse(response);
    }

    @Override
    public void storeSelectedQuota(String selectedQuota) {
        Session.getInstance().setQuota(selectedQuota);
    }

    @Override
    public List<String> getQuotas() {
        return QuotasUtils.getQuotasToSelect(model.getQuotasSelectionResponse());
    }


}
