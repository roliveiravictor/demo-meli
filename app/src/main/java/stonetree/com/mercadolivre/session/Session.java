package stonetree.com.mercadolivre.session;

public class Session {

    private static Session session;

    private long amountToPay;

    private String paymentMethod;
    private String cardIssuer;

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
}
