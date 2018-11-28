package stonetree.com.mercadolivre.paymentMethods.view.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import stonetree.com.mercadolivre.R;
import stonetree.com.mercadolivre.paymentMethods.model.PaymentMethod;
import stonetree.com.mercadolivre.paymentMethods.model.PaymentMethodsResponse;
import stonetree.com.mercadolivre.paymentMethods.presenter.PaymentMethodsPresenter;
import stonetree.com.mercadolivre.paymentMethods.view.PaymentMethodsActivity;
import stonetree.com.mercadolivre.tasks.ImageDownloaderCallback;
import stonetree.com.mercadolivre.tasks.ImageDownloaderTask;

public class PaymentMethodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private PaymentMethodsResponse response;

    private PaymentMethodsActivity view;

    private PaymentMethodsPresenter presenter;

    public PaymentMethodsAdapter(PaymentMethodsActivity view, PaymentMethodsPresenter presenter, PaymentMethodsResponse response) {
        this.view = view;
        this.presenter = presenter;
        this.response = response;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout card;

        private ImageView thumbnail;

        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            card = (LinearLayout) itemView.findViewById(R.id.card);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PaymentMethodsAdapter.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_card_row, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int rowPosition) {
        final ViewHolder holder = ((ViewHolder) viewHolder);

        PaymentMethod paymentMethod = response.getPaymentMethods().get(rowPosition);

        setThumbnail(holder, paymentMethod);
        setName(holder, paymentMethod);
        setListener(holder, paymentMethod);
    }

    private void setListener(ViewHolder holder, final PaymentMethod paymentMethod) {
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View selectedCard) {
                final String methodId = paymentMethod.getId();
                presenter.storePaymentMethod(methodId);
                view.proceedToQuotasSelection();
            }
        });
    }

    private void setThumbnail(ViewHolder holder, PaymentMethod paymentMethod) {
        final ImageDownloaderCallback imageDownloaderCallback = getImageDownloaderCallback(holder.thumbnail);
        new ImageDownloaderTask(imageDownloaderCallback, paymentMethod.getThumbnailUrl()).execute();
    }

    private ImageDownloaderCallback getImageDownloaderCallback(final ImageView thumbnail) {
        return new ImageDownloaderCallback() {
            @Override
            public void onDownloadedImage(Bitmap bitmap) {
                thumbnail.setImageBitmap(bitmap);
                notifyDataSetChanged();
            }
        };
    }

    private void setName(ViewHolder holder, PaymentMethod paymentMethod) {
        holder.name.setText(paymentMethod.getName());
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return response.getPaymentMethods().size();
    }

}
