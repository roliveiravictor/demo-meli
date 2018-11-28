package stonetree.com.mercadolivre.cardIssuers.provider;

import stonetree.com.mercadolivre.core.model.Error;
import stonetree.com.mercadolivre.cardIssuers.model.CardIssuersResponse;

public interface ICardIssuersProvider {

    void onSuccess(final CardIssuersResponse response);
    void onFailure(final Error response);

}