package stonetree.com.mercadolivre.paymentMethods.presenter;

import stonetree.com.mercadolivre.constants.Constants;
import stonetree.com.mercadolivre.paymentMethods.model.PaymentMethods;
import stonetree.com.mercadolivre.paymentMethods.model.PaymentMethodsResponse;
import stonetree.com.mercadolivre.paymentMethods.view.PaymentMethodsActivity;
import stonetree.com.mercadolivre.paymentMethods.view.adapter.PaymentMethodsAdapter;
import stonetree.com.mercadolivre.session.Session;

public class PaymentMethodsPresenter implements IPaymentMethodsPresenter {

    private final PaymentMethodsActivity view;

    private PaymentMethodsAdapter paymentMethodsAdapter;

    private PaymentMethods model;

    public PaymentMethodsPresenter(final PaymentMethodsActivity view) {
        this.view = view;
        this.model = new PaymentMethods();
    }

    @Override
    public void onCreate() {
        storeBundle();
    }

    @Override
    public void storeBundle() {
        final PaymentMethodsResponse response = (PaymentMethodsResponse) view.loadBundle(Constants.PAYMENT_METHODS_RESPONSE);
        model.setPaymentMethodsResponse(response);
    }

    @Override
    public void storePaymentMethod(String methodId) {
        Session.getInstance().setPaymentMethod(methodId);
    }

    @Override
    public PaymentMethodsResponse getPaymentMethodsResponse() {
        return model.getPaymentMethodsResponse();
    }


}
