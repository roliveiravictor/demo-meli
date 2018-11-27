package stonetree.com.mercadolivre.payment.presenter;

import stonetree.com.mercadolivre.core.model.Error;
import stonetree.com.mercadolivre.payment.model.Payment;
import stonetree.com.mercadolivre.payment.model.PaymentResponse;
import stonetree.com.mercadolivre.payment.view.PaymentActivity;
import stonetree.com.mercadolivre.provider.IPaymentProvider;
import stonetree.com.mercadolivre.provider.PaymentProvider;

public class PaymentPresenter implements IPaymentPresenter {

    private final PaymentActivity view;

    private Payment model;

    public PaymentPresenter(final PaymentActivity view) {
        this.view = view;
        this.model = new Payment();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void proceedWithPayment() {
        new PaymentProvider().getPayment(new IPaymentProvider() {
            @Override
            public void onSuccess(PaymentResponse response) {
                view.proceedToCreditCardSelection();
            }

            @Override
            public void onFailure(Error response) {

            }
        });
    }


}
