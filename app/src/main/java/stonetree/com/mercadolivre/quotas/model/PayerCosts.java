package stonetree.com.mercadolivre.quotas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PayerCosts implements Serializable {

    @Expose
    @SerializedName("recommended_message")
    private String recommendedMessage;

    public String getRecommendedMessage() {
        return recommendedMessage;
    }

    public void setRecommendedMessage(String recommendedMessage) {
        this.recommendedMessage = recommendedMessage;
    }

}
