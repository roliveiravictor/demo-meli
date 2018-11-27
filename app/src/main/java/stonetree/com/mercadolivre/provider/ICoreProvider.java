package stonetree.com.mercadolivre.provider;

import stonetree.com.mercadolivre.core.model.Error;

public interface ICoreProvider {

    void onSuccess(final String response);
    void onFailure(final Error response);

}
