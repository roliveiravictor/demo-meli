package stonetree.com.mercadolivre.quotas.provider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import stonetree.com.mercadolivre.core.model.CoreRequestTask;
import stonetree.com.mercadolivre.core.model.Error;
import stonetree.com.mercadolivre.enums.Endpoint;
import stonetree.com.mercadolivre.enums.Query;
import stonetree.com.mercadolivre.provider.CoreProvider;
import stonetree.com.mercadolivre.provider.ICoreProvider;
import stonetree.com.mercadolivre.quotas.model.QuotaSelection;
import stonetree.com.mercadolivre.quotas.model.QuotasSelectionResponse;
import stonetree.com.mercadolivre.session.Session;
import stonetree.com.mercadolivre.utils.Collections;

public class QuotasSelectionProvider extends CoreProvider {

    private IQuotasSelectionProvider callback;

    public void getQuotasSelection(final IQuotasSelectionProvider callback) {
        this.callback = callback;

        final String quotasSelectionEndpoint = getUrlAppKey(Endpoint.QUOTAS_SELECTION,
                Query.AMOUNT_TO_PAY, String.valueOf(Session.getInstance().getAmountToPay()),
                Query.PAYMENT_METHOD_ID, Session.getInstance().getPaymentMethod(),
                Query.ISSUER, Session.getInstance().getCardIssuer());

        final ICoreProvider quotasSelectionCallback = getQuotasSelectionCallback();
        final CoreRequestTask quotasSelectionRequest = getPOSTRequest(quotasSelectionEndpoint, quotasSelectionCallback);

        quotasSelectionRequest.execute();
    }

    private ICoreProvider getQuotasSelectionCallback() {
        return new ICoreProvider() {
            @Override
            public void onSuccess(final String response) {
                final List<QuotaSelection> quotas = new Gson().fromJson(response, getJsonType());
                if (Collections.isNullOrEmpty(quotas)) {
                    callback.onFailure(Error.getDefault());
                } else {
                    final QuotasSelectionResponse quotasResponse = new QuotasSelectionResponse();
                    quotasResponse.getQuotas().addAll(quotas);
                    callback.onSuccess(quotasResponse);
                }
            }

            @Override
            public void onFailure(final Error response) {
                callback.onFailure(response);
            }
        };
    }

    private Type getJsonType() {
        return new TypeToken<List<QuotaSelection>>() {
        }.getType();
    }

}
