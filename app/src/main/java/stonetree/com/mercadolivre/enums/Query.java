package stonetree.com.mercadolivre.enums;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import stonetree.com.mercadolivre.constants.Constants;

import static stonetree.com.mercadolivre.enums.Query.AMOUNT_TO_PAY;
import static stonetree.com.mercadolivre.enums.Query.ISSUER;
import static stonetree.com.mercadolivre.enums.Query.PAYMENT_METHOD_ID;

@StringDef({PAYMENT_METHOD_ID, AMOUNT_TO_PAY, ISSUER})
@Retention(RetentionPolicy.SOURCE)
public @interface Query {

    String PAYMENT_METHOD_ID = Constants.QUERY_SELECTOR + "payment_method_id";
    String AMOUNT_TO_PAY = Constants.QUERY_SELECTOR + "amount";
    String ISSUER = Constants.QUERY_SELECTOR + "issuer.id";

}
