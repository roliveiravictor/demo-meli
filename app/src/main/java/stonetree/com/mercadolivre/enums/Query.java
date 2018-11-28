package stonetree.com.mercadolivre.enums;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import stonetree.com.mercadolivre.constants.Constants;

import static stonetree.com.mercadolivre.enums.Query.PAYMENT_METHOD_ID;

@StringDef({PAYMENT_METHOD_ID})
@Retention(RetentionPolicy.SOURCE)
public @interface Query {

    String PAYMENT_METHOD_ID = Constants.QUERY_SELECTOR + "payment_method_id";

}
