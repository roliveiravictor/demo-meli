package stonetree.com.mercadolivre.cardIssuers.presenter;

import stonetree.com.mercadolivre.cardIssuers.model.CardIssuersResponse;

interface ICardIssuersPresenter {

    void onCreate();

    void storeCardIssuer(String issuerId);

    void proceedWithQuotasSelection();

    void storeBundle();

    CardIssuersResponse getCardIssuersResponse();
}
