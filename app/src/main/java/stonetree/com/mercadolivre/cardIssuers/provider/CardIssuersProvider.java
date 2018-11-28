package stonetree.com.mercadolivre.cardIssuers.provider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import stonetree.com.mercadolivre.cardIssuers.model.CardIssuer;
import stonetree.com.mercadolivre.cardIssuers.model.CardIssuersResponse;
import stonetree.com.mercadolivre.core.model.CoreRequestTask;
import stonetree.com.mercadolivre.core.model.Error;
import stonetree.com.mercadolivre.enums.Endpoint;
import stonetree.com.mercadolivre.enums.Query;
import stonetree.com.mercadolivre.provider.CoreProvider;
import stonetree.com.mercadolivre.provider.ICoreProvider;
import stonetree.com.mercadolivre.session.Session;

public class CardIssuersProvider extends CoreProvider {

    private ICardIssuersProvider callback;

    public void getCardIssuers(final ICardIssuersProvider callback) {
        this.callback = callback;
        final String paymentMethodsEndpoint = getUrlAppKey(Endpoint.CARD_ISSUERS, Query.PAYMENT_METHOD_ID, Session.getInstance().getPaymentMethod());

        final ICoreProvider cardIssuersCallback = getCardIssuersCallback();
        final CoreRequestTask cardIssuersRequest = getPOSTRequest(paymentMethodsEndpoint, cardIssuersCallback);

        cardIssuersRequest.execute();
    }

    private ICoreProvider getCardIssuersCallback() {
        return new ICoreProvider() {
            @Override
            public void onSuccess(final String response) {
                final List<CardIssuer> cardIssuers = new Gson().fromJson(response, getJsonType());
                final CardIssuersResponse cardIssuersResponse = new CardIssuersResponse();
                cardIssuersResponse.getCardIssuers().addAll(cardIssuers);
                callback.onSuccess(cardIssuersResponse);
            }

            @Override
            public void onFailure(final Error response) {
                callback.onFailure(response);
            }
        };
    }

    private Type getJsonType() {
        return new TypeToken<List<CardIssuer>>() {
        }.getType();
    }

}
