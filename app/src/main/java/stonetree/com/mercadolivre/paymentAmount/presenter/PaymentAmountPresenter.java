package stonetree.com.mercadolivre.paymentAmount.presenter;

import android.content.DialogInterface;
import android.content.IntentFilter;
import android.view.Gravity;
import android.widget.Toast;

import stonetree.com.mercadolivre.R;
import stonetree.com.mercadolivre.core.model.Error;
import stonetree.com.mercadolivre.network.INetworkStateReceiver;
import stonetree.com.mercadolivre.network.NetworkStateReceiver;
import stonetree.com.mercadolivre.paymentAmount.model.PaymentAmount;
import stonetree.com.mercadolivre.paymentAmount.view.PaymentAmountActivity;
import stonetree.com.mercadolivre.paymentMethods.model.PaymentMethodsResponse;
import stonetree.com.mercadolivre.paymentMethods.provider.IPaymentMethodsProvider;
import stonetree.com.mercadolivre.paymentMethods.provider.PaymentMethodsProvider;
import stonetree.com.mercadolivre.session.Session;
import stonetree.com.mercadolivre.utils.Collections;
import stonetree.com.mercadolivre.utils.DialogUtils;
import stonetree.com.mercadolivre.utils.MoneyUtils;
import stonetree.com.mercadolivre.utils.ToastUtils;

public class PaymentAmountPresenter implements IPaymentAmountPresenter, INetworkStateReceiver {

    private final PaymentAmountActivity view;

    private PaymentAmount model;

    public PaymentAmountPresenter(final PaymentAmountActivity view) {
        this.view = view;
        this.model = new PaymentAmount();
    }

    @Override
    public void onCreate() {
        setupNetworkStateReceiver();
        loadCheckout();
    }

    @Override
    public void onResume() {
        view.hideLoading();
    }

    @Override
    public void loadCheckout() {
        if (!Collections.isNullOrEmpty(Session.getInstance().getQuota())) {
            final String title = view.getString(R.string.checkout_title);
            final String positiveMessage = view.getString(R.string.checkout_ok);

            DialogUtils.showMessage(view, title, positiveMessage, getPositiveListener(), Gravity.CENTER);
        }
    }

    private DialogInterface.OnClickListener getPositiveListener() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int buttonOption) {
                Session.getInstance().purge();
            }
        };
    }

    @Override
    public void proceedWithPayment(long amountToPay) {
        view.showLoading();
        Session.getInstance().setAmountToPay(amountToPay);
        new PaymentMethodsProvider().getPaymentMethods(new IPaymentMethodsProvider() {
            @Override
            public void onSuccess(PaymentMethodsResponse response) {
                view.proceedToCreditCardSelection(response);
            }

            @Override
            public void onFailure(Error response) {
                ToastUtils.show(view, response.getToastMessage(), Toast.LENGTH_LONG);
                view.hideLoading();
            }
        });
    }

    @Override
    public void setupNetworkStateReceiver() {
        final NetworkStateReceiver networkStateReceiver = new NetworkStateReceiver(view, this);

        view.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));

        model.setNetworkStateReceiver(networkStateReceiver);
    }

    @Override
    public void destroyNetworkStateReceiver() {
        final NetworkStateReceiver networkStateReceiver = model.getNetworkStateReceiver();
        view.unregisterReceiver(networkStateReceiver);
    }

    @Override
    public String getFormattedPrice(String price) {
        return MoneyUtils.cleanMask(price);
    }


    @Override
    public void onNetworkAvailable() {
        view.enablePayment();
    }

    @Override
    public void onNetworkUnavailable() {
        view.disablePayment();
    }
}
