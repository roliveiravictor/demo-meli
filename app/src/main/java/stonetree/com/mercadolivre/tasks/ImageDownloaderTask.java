package stonetree.com.mercadolivre.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_OK;

public class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {

    private ImageDownloaderCallback imageDownloaderCallback;
    private String url;

    public ImageDownloaderTask(ImageDownloaderCallback imageDownloaderCallback, String url) {
        this.url = url;
        this.imageDownloaderCallback = imageDownloaderCallback;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return downloadImage(url);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = getDummyBitmap();
        }
        imageDownloaderCallback.onDownloadedImage(bitmap);
    }

    private Bitmap downloadImage(String url) {
        HttpURLConnection urlConnection = null;
        try {

            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();

            int statusCode = urlConnection.getResponseCode();
            if (statusCode != HTTP_OK) {
                return getDummyBitmap();
            }

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }

        } catch (Exception e) {
            Log.e(ImageDownloaderTask.class.getName(), "Error downloading image from " + url);
        } finally {
            disconnect(urlConnection);
        }

        return getDummyBitmap();
    }

    private Bitmap getDummyBitmap() {
        return BitmapFactory.decodeStream(new InputStream() {
            @Override
            public int read() {
                return 0;
            }
        });
    }

    private void disconnect(HttpURLConnection urlConnection) {
        if (urlConnection != null) {
            urlConnection.disconnect();
        }
    }

}

