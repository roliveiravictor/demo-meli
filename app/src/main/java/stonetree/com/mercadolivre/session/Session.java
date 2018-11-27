package stonetree.com.mercadolivre.session;

public class Session {

    private static Session session;

    private long amountToPay;

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
}
