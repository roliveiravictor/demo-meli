package stonetree.com.mercadolivre.paymentMethods.presenter;

import android.widget.Toast;

import stonetree.com.mercadolivre.cardIssuers.model.CardIssuersResponse;
import stonetree.com.mercadolivre.cardIssuers.provider.CardIssuersProvider;
import stonetree.com.mercadolivre.cardIssuers.provider.ICardIssuersProvider;
import stonetree.com.mercadolivre.constants.Constants;
import stonetree.com.mercadolivre.core.model.Error;
import stonetree.com.mercadolivre.paymentMethods.model.PaymentMethods;
import stonetree.com.mercadolivre.paymentMethods.model.PaymentMethodsResponse;
import stonetree.com.mercadolivre.paymentMethods.view.PaymentMethodsActivity;
import stonetree.com.mercadolivre.session.Session;
import stonetree.com.mercadolivre.utils.ToastUtils;

public class PaymentMethodsPresenter implements IPaymentMethodsPresenter {

    private final PaymentMethodsActivity view;

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
    public void onResume() {
        view.hideLoading();
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
    public void proceedWithCardIssuers() {
        view.showLoading();
        new CardIssuersProvider().getCardIssuers(new ICardIssuersProvider() {
            @Override
            public void onSuccess(CardIssuersResponse response) {
                view.proceedToCardIssuerSelection(response);
            }

            @Override
            public void onFailure(Error response) {
                ToastUtils.show(view, response.getToastMessage(), Toast.LENGTH_LONG);
                view.hideLoading();
            }
        });
    }

    @Override
    public PaymentMethodsResponse getPaymentMethodsResponse() {
        return model.getPaymentMethodsResponse();
    }


}
