package stonetree.com.mercadolivre.quotas.provider;

import stonetree.com.mercadolivre.core.model.Error;
import stonetree.com.mercadolivre.quotas.model.QuotasSelectionResponse;

public interface IQuotasSelectionProvider {

    void onSuccess(final QuotasSelectionResponse response);
    void onFailure(final Error response);

}