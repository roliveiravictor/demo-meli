package stonetree.com.mercadolivre.paymentMethods.presenter;

import stonetree.com.mercadolivre.paymentMethods.model.PaymentMethodsResponse;

public interface IPaymentMethodsPresenter {

    void onCreate();

    void onResume();

    void storeBundle();

    void storePaymentMethod(String methodId);

    void proceedWithCardIssuers();

    PaymentMethodsResponse getPaymentMethodsResponse();
}
