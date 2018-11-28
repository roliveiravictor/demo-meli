package stonetree.com.mercadolivre.paymentAmount.presenter;

public interface IPaymentAmountPresenter {

    void onCreate();

    void proceedWithPayment(long amountToPay);

    void setupNetworkStateReceiver();

    void destroyNetworkStateReceiver();
}
