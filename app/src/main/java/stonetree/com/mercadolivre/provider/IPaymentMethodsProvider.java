package stonetree.com.mercadolivre.provider;

import stonetree.com.mercadolivre.core.model.Error;
import stonetree.com.mercadolivre.paymentMethods.model.PaymentMethodsResponse;

public interface IPaymentMethodsProvider {

    void onSuccess(final PaymentMethodsResponse response);
    void onFailure(final Error response);

}
