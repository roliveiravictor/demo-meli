package stonetree.com.mercadolivre.paymentAmount.presenter;

import android.widget.Toast;

import stonetree.com.mercadolivre.constants.Constants;
import stonetree.com.mercadolivre.core.model.Error;
import stonetree.com.mercadolivre.paymentAmount.model.PaymentAmount;
import stonetree.com.mercadolivre.paymentAmount.view.PaymentAmountActivity;
import stonetree.com.mercadolivre.paymentMethods.model.PaymentMethodsResponse;
import stonetree.com.mercadolivre.provider.IPaymentMethodsProvider;
import stonetree.com.mercadolivre.provider.PaymentMethodsProvider;
import stonetree.com.mercadolivre.session.Session;

public class PaymentAmountPresenter implements IPaymentAmountPresenter {

    private final PaymentAmountActivity view;

    private PaymentAmount model;

    public PaymentAmountPresenter(final PaymentAmountActivity view) {
        this.view = view;
        this.model = new PaymentAmount();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void proceedWithPayment(long amountToPay) {
        Session.getInstance().setAmountToPay(amountToPay);
        new PaymentMethodsProvider().getPaymentMethods(new IPaymentMethodsProvider() {
            @Override
            public void onSuccess(PaymentMethodsResponse response) {
                view.proceedToCreditCardSelection(response);
            }

            @Override
            public void onFailure(Error response) {
                Toast.makeText(view.getApplicationContext(), response.getToastMessage(), Constants.TWO_SECONDS);
            }
        });
    }


}
