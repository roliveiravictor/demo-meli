package stonetree.com.mercadolivre.network;

public interface INetworkStateReceiver {

    void onNetworkAvailable();

    void onNetworkUnavailable();

}
