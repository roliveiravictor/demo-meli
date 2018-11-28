package stonetree.com.mercadolivre.enums;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static stonetree.com.mercadolivre.enums.Endpoint.PAYMENT_METHODS;

@StringDef({PAYMENT_METHODS})
@Retention(RetentionPolicy.SOURCE)
public @interface Endpoint {

    String PAYMENT_METHODS = "https://api.mercadopago.com/v1/payment_methods";
    String CARD_ISSUERS = "https://api.mercadopago.com/v1/payment_methods/card_issuers";

}
