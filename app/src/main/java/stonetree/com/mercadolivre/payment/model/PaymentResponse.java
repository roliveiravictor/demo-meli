package stonetree.com.mercadolivre.payment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PaymentResponse {

    @Expose
    @SerializedName("payment_methods")
    private List<PaymentMethod> paymentMethods = new ArrayList<>();

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
}
