package stonetree.com.mercadolivre.cardIssuers.presenter;

import android.widget.Toast;

import stonetree.com.mercadolivre.cardIssuers.model.CardIssuers;
import stonetree.com.mercadolivre.cardIssuers.model.CardIssuersResponse;
import stonetree.com.mercadolivre.cardIssuers.view.CardIssuersActivity;
import stonetree.com.mercadolivre.constants.Constants;
import stonetree.com.mercadolivre.core.model.Error;
import stonetree.com.mercadolivre.quotas.model.QuotasSelectionResponse;
import stonetree.com.mercadolivre.quotas.provider.IQuotasSelectionProvider;
import stonetree.com.mercadolivre.quotas.provider.QuotasSelectionProvider;
import stonetree.com.mercadolivre.session.Session;
import stonetree.com.mercadolivre.utils.ToastUtils;

public class CardIssuersPresenter implements ICardIssuersPresenter {

    private CardIssuersActivity view;

    private CardIssuers model = new CardIssuers();

    public CardIssuersPresenter(CardIssuersActivity view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
        storeBundle();
    }

    @Override
    public void onResume() {
        view.hideLoading();
    }

    @Override
    public void storeCardIssuer(String issuerId) {
        Session.getInstance().setCardIssuer(issuerId);
    }

    @Override
    public void proceedWithQuotasSelection() {
        view.showLoading();
        new QuotasSelectionProvider().getQuotasSelection(new IQuotasSelectionProvider() {
            @Override
            public void onSuccess(QuotasSelectionResponse response) {
                view.proceedToQuotasSelection(response);
            }

            @Override
            public void onFailure(Error response) {
                ToastUtils.show(view, response.getToastMessage(), Toast.LENGTH_LONG);
                view.hideLoading();
            }
        });
    }

    @Override
    public void storeBundle() {
        final CardIssuersResponse response = (CardIssuersResponse) view.loadBundle(Constants.CARD_ISSUERS_RESPONSE);
        model.setCardIssuersResponse(response);
    }

    @Override
    public CardIssuersResponse getCardIssuersResponse() {
        return model.getCardIssuersResponse();
    }


}
