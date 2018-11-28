package stonetree.com.mercadolivre.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import stonetree.com.mercadolivre.session.Session;

public class NetworkStateReceiver extends BroadcastReceiver {

    protected INetworkStateReceiver networkStateReceiverCallback;

    private ConnectivityManager connectivityManager;

    public NetworkStateReceiver(@NonNull Context context, @NonNull INetworkStateReceiver networkStateReceiverCallback) {
        this.connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        this.networkStateReceiverCallback = networkStateReceiverCallback;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getExtras() == null)
            return;

        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
            Session.getInstance().setNetworkOnline(true);
        } else if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {
            Session.getInstance().setNetworkOnline(false);
        }

        notifyNetworkState();
    }

    private void notifyNetworkState() {
        if (Session.getInstance().isNetworkOnline()) {
            networkStateReceiverCallback.onNetworkAvailable();
        } else {
            networkStateReceiverCallback.onNetworkUnavailable();
        }
    }

}