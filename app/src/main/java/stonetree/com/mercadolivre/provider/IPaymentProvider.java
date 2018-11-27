package stonetree.com.mercadolivre.provider;

import stonetree.com.mercadolivre.core.model.Error;
import stonetree.com.mercadolivre.payment.model.PaymentResponse;

public interface IPaymentProvider {

    void onSuccess(final PaymentResponse response);
    void onFailure(final Error response);

}
