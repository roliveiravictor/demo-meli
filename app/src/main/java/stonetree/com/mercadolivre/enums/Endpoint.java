package stonetree.com.mercadolivre.enums;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static stonetree.com.mercadolivre.enums.Endpoint.PAYMENT;

@StringDef({PAYMENT})
@Retention(RetentionPolicy.SOURCE)
public @interface Endpoint {

    String PAYMENT = "https://api.mercadopago.com/v1/payment_methods";

}
