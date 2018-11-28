package stonetree.com.mercadolivre.core.model;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import stonetree.com.mercadolivre.provider.CoreProvider;
import stonetree.com.mercadolivre.provider.ICoreProvider;
import stonetree.com.mercadolivre.utils.Collections;

import static java.net.HttpURLConnection.HTTP_OK;

public class CoreRequestTask extends AsyncTask<String, Void, CoreResponse> {

    private String url;

    private ICoreProvider callback;

    public CoreRequestTask(ICoreProvider callback, String url) {
        this.url = url;
        this.callback = callback;
    }

    @Override
    protected CoreResponse doInBackground(String... params) {
        return post(url);
    }

    private CoreResponse post(String url) {
        final CoreResponse coreResponse = new CoreResponse();

        HttpURLConnection urlConnection = null;
        try {

            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();

            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HTTP_OK) {
                coreResponse.setError(getError(urlConnection));
            }

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                final String result = convertStreamToString(inputStream);
                coreResponse.setResult(result);
            }
        } catch (IOException e) {
            Log.e(CoreProvider.class.getName(), "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(CoreProvider.class.getName(), "Error posting from: " + url);
        } finally {
            disconnect(urlConnection);
        }

        return coreResponse;
    }

    @Override
    protected void onPostExecute(CoreResponse coreResponse) {
        if (isCancelled())
            callback.onFailure(Error.getDefault());

        if (Collections.isNullOrEmpty(coreResponse.getResult()))
            callback.onFailure(Error.getDefault());

        callback.onSuccess(coreResponse.getResult());
    }

    private Error getError(HttpURLConnection urlConnection) throws IOException {
        return new Error(urlConnection.getResponseCode(), urlConnection.getResponseMessage());
    }

    private void disconnect(HttpURLConnection urlConnection) {
        if (urlConnection != null) {
            urlConnection.disconnect();
        }
    }

    private String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
