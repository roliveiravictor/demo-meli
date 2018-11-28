package stonetree.com.mercadolivre.paymentMethods.model;

import android.graphics.Bitmap;

public class PaymentMethods {

    private PaymentMethodsResponse paymentMethodsResponse;

    private Bitmap cardThumbnail;

    public PaymentMethodsResponse getPaymentMethodsResponse() {
        return paymentMethodsResponse;
    }

    public void setPaymentMethodsResponse(PaymentMethodsResponse paymentMethodsResponse) {
        this.paymentMethodsResponse = paymentMethodsResponse;
    }

    public Bitmap getCardThumbnail() {
        return cardThumbnail;
    }

    public void setCardThumbnail(Bitmap cardThumbnail) {
        this.cardThumbnail = cardThumbnail;
    }

}
