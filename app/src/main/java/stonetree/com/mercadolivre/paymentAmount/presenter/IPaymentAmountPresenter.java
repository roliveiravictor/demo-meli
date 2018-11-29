package stonetree.com.mercadolivre.paymentAmount.presenter;

public interface IPaymentAmountPresenter {

    void onCreate();

    void onResume();

    void loadCheckout();

    void proceedWithPayment(long amountToPay);

    void setupNetworkStateReceiver();

    void destroyNetworkStateReceiver();

    String getFormattedPrice(String price);
}
