package stonetree.com.mercadolivre.quotas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class QuotaSelection implements Serializable {

    @Expose
    @SerializedName("payer_costs")
    private List<PayerCosts> payerCosts;

    public List<PayerCosts> getPayerCosts() {
        return payerCosts;
    }

    public void setPayerCosts(List<PayerCosts> payerCosts) {
        this.payerCosts = payerCosts;
    }

}
