package stonetree.com.mercadolivre.quotas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuotasSelectionResponse implements Serializable {

    @Expose
    @SerializedName("quotas_selection")
    private List<QuotaSelection> quotas = new ArrayList<>();

    public List<QuotaSelection> getQuotas() {
        return quotas;
    }

    public void setQuotas(List<QuotaSelection> quotas) {
        this.quotas = quotas;
    }

}
