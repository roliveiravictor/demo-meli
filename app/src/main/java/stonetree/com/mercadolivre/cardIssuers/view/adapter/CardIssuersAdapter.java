package stonetree.com.mercadolivre.cardIssuers.view.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import stonetree.com.mercadolivre.R;
import stonetree.com.mercadolivre.cardIssuers.model.CardIssuer;
import stonetree.com.mercadolivre.cardIssuers.model.CardIssuersResponse;
import stonetree.com.mercadolivre.cardIssuers.presenter.CardIssuersPresenter;
import stonetree.com.mercadolivre.cardIssuers.view.CardIssuersActivity;
import stonetree.com.mercadolivre.session.Session;
import stonetree.com.mercadolivre.tasks.ImageDownloaderCallback;
import stonetree.com.mercadolivre.tasks.ImageDownloaderTask;

public class CardIssuersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private CardIssuersResponse response;

    private CardIssuersActivity view;

    private CardIssuersPresenter presenter;

    public CardIssuersAdapter(CardIssuersActivity view, CardIssuersPresenter presenter, CardIssuersResponse response) {
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
        return new CardIssuersAdapter.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.card_issuer_row, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int rowPosition) {
        final ViewHolder holder = ((ViewHolder) viewHolder);

        CardIssuer cardIssuer = response.getCardIssuers().get(rowPosition);

        setThumbnail(holder, cardIssuer);
        setName(holder, cardIssuer);
        setListener(holder, cardIssuer);
    }

    private void setListener(final ViewHolder holder, final CardIssuer cardIssuer) {
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View selectedCard) {
                final BitmapDrawable drawable = (BitmapDrawable) holder.thumbnail.getDrawable();
                final Bitmap bitmap = drawable.getBitmap();

                Session.getInstance().setIssuerThumbnail(bitmap);

                final String issuerId = cardIssuer.getId();
                presenter.storeCardIssuer(issuerId);
                presenter.proceedWithQuotasSelection();
            }
        });

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.card.performClick();
            }
        });

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.card.performClick();
            }
        });
    }

    private void setThumbnail(ViewHolder holder, CardIssuer cardIssuer) {
        final ImageDownloaderCallback imageDownloaderCallback = getImageDownloaderCallback(holder.thumbnail);
        new ImageDownloaderTask(imageDownloaderCallback, cardIssuer.getThumbnailUrl()).execute();
    }

    private ImageDownloaderCallback getImageDownloaderCallback(final ImageView thumbnail) {
        return new ImageDownloaderCallback() {
            @Override
            public void onDownloadedImage(Bitmap bitmap) {
                thumbnail.setImageBitmap(bitmap);
            }
        };
    }

    private void setName(ViewHolder holder, CardIssuer cardIssuer) {
        holder.name.setText(cardIssuer.getName());
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
        return response.getCardIssuers().size();
    }

}
