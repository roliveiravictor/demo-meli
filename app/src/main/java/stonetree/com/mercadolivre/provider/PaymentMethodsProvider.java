package stonetree.com.mercadolivre.provider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import stonetree.com.mercadolivre.core.model.CoreRequestTask;
import stonetree.com.mercadolivre.core.model.Error;
import stonetree.com.mercadolivre.enums.Endpoint;
import stonetree.com.mercadolivre.paymentMethods.model.PaymentMethod;
import stonetree.com.mercadolivre.paymentMethods.model.PaymentMethodsResponse;

public class PaymentMethodsProvider extends CoreProvider {

    private IPaymentMethodsProvider callback;

    public void getPaymentMethods(final IPaymentMethodsProvider callback) {
        this.callback = callback;
        final String paymentMethodsEndpoint = getUrlAppKey(Endpoint.PAYMENT_METHODS);

        final ICoreProvider paymentMethodsCallback = getPaymentMethodsCallback();
        final CoreRequestTask paymentMethodsRequest = getPOSTRequest(paymentMethodsEndpoint, paymentMethodsCallback);

        paymentMethodsRequest.execute();
    }

    private ICoreProvider getPaymentMethodsCallback() {
        return new ICoreProvider() {
            @Override
            public void onSuccess(final String response) {
                final List<PaymentMethod> paymentMethods = new Gson().fromJson(response, getJsonType());
                final PaymentMethodsResponse paymentMethodsResponse = new PaymentMethodsResponse();
                paymentMethodsResponse.getPaymentMethods().addAll(paymentMethods);
                callback.onSuccess(paymentMethodsResponse);
            }

            @Override
            public void onFailure(final Error response) {
                callback.onFailure(response);
            }
        };
    }

    private Type getJsonType() {
        return new TypeToken<List<PaymentMethod>>() {
        }.getType();
    }

}
