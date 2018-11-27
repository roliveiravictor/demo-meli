package stonetree.com.mercadolivre.provider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import stonetree.com.mercadolivre.core.model.CoreRequestTask;
import stonetree.com.mercadolivre.core.model.Error;
import stonetree.com.mercadolivre.enums.Endpoint;
import stonetree.com.mercadolivre.payment.model.PaymentMethod;
import stonetree.com.mercadolivre.payment.model.PaymentResponse;

public class PaymentProvider extends CoreProvider {

    private IPaymentProvider callback;

    public void getPayment(final IPaymentProvider callback) {
        this.callback = callback;
        final String paymentEndpoint = getUrlAppKey(Endpoint.PAYMENT);

        final ICoreProvider paymentCallback = getPaymentCallback();
        final CoreRequestTask paymentRequest = getPOSTRequest(paymentEndpoint, paymentCallback);

        paymentRequest.execute();
    }

    private ICoreProvider getPaymentCallback() {
        return new ICoreProvider() {
            @Override
            public void onSuccess(final String response) {
                final List<PaymentMethod> paymentMethods = new Gson().fromJson(response, getJsonType());
                final PaymentResponse paymentResponse = new PaymentResponse();
                paymentResponse.getPaymentMethods().addAll(paymentMethods);
                callback.onSuccess(paymentResponse);
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
