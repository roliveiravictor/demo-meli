package stonetree.com.mercadolivre.paymentAmount.model;

import stonetree.com.mercadolivre.network.NetworkStateReceiver;

public class PaymentAmount {

    private NetworkStateReceiver networkStateReceiver;

    public NetworkStateReceiver getNetworkStateReceiver() {
        return networkStateReceiver;
    }

    public void setNetworkStateReceiver(NetworkStateReceiver networkStateReceiver) {
        this.networkStateReceiver = networkStateReceiver;
    }
}
