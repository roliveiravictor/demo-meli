package stonetree.com.mercadolivre.cardIssuers.presenter;

import stonetree.com.mercadolivre.cardIssuers.model.CardIssuers;
import stonetree.com.mercadolivre.cardIssuers.model.CardIssuersResponse;
import stonetree.com.mercadolivre.cardIssuers.view.CardIssuersActivity;
import stonetree.com.mercadolivre.constants.Constants;
import stonetree.com.mercadolivre.session.Session;

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
    public void storeCardIssuer(String issuerId) {
        Session.getInstance().setCardIssuer(issuerId);
    }

    @Override
    public void proceedWithQuotasSelection() {
        view.proceedToQuotasSelection();
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
