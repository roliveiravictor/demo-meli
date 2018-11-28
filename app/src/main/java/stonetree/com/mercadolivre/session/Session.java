package stonetree.com.mercadolivre.session;

import android.graphics.Bitmap;

import stonetree.com.mercadolivre.constants.Constants;

public class Session {

    private static Session session;

    private long amountToPay;

    private String paymentMethod;
    private String cardIssuer;
    private String quota;

    private Bitmap creditThumbnail;
    private Bitmap issuerThumbnail;

    private boolean networkOnline;

    public static Session getInstance() {
        if (session == null)
            session = new Session();

        return session;
    }

    public long getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(long amountToPay) {
        this.amountToPay = amountToPay;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardIssuer() {
        return cardIssuer;
    }

    public void setCardIssuer(String cardIssuer) {
        this.cardIssuer = cardIssuer;
    }

    public boolean isNetworkOnline() {
        return networkOnline;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public Bitmap getCreditThumbnail() {
        return creditThumbnail;
    }

    public void setCreditThumbnail(Bitmap creditThumbnail) {
        this.creditThumbnail = creditThumbnail;
    }

    public Bitmap getIssuerThumbnail() {
        return issuerThumbnail;
    }

    public void setIssuerThumbnail(Bitmap issuerThumbnail) {
        this.issuerThumbnail = issuerThumbnail;
    }

    public void setNetworkOnline(boolean networkOnline) {
        this.networkOnline = networkOnline;
    }

    public void purge() {
        this.amountToPay = 0;
        this.paymentMethod = Constants.EMPTY;
        this.cardIssuer = Constants.EMPTY;
        this.quota = Constants.EMPTY;
        this.creditThumbnail = null;
    }
}
