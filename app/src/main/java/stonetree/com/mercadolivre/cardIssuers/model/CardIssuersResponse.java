package stonetree.com.mercadolivre.cardIssuers.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CardIssuersResponse implements Serializable {

    @Expose
    @SerializedName("card_issuers")
    private List<CardIssuer> cardIssuers = new ArrayList<>();

    public List<CardIssuer> getCardIssuers() {
        return cardIssuers;
    }

    public void setCardIssuers(List<CardIssuer> cardIssuers) {
        this.cardIssuers = cardIssuers;
    }
}
