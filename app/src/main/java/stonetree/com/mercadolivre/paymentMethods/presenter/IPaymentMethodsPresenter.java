package stonetree.com.mercadolivre.paymentMethods.presenter;

import stonetree.com.mercadolivre.paymentMethods.model.PaymentMethodsResponse;

public interface IPaymentMethodsPresenter {

    void onCreate();

    void storeBundle();

    void storePaymentMethod(String methodId);

    PaymentMethodsResponse getPaymentMethodsResponse();
}
