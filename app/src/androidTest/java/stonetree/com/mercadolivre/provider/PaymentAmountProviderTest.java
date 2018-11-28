package stonetree.com.mercadolivre.provider;


import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import stonetree.com.mercadolivre.core.model.Error;
import stonetree.com.mercadolivre.paymentMethods.model.PaymentMethodsResponse;
import stonetree.com.mercadolivre.paymentMethods.provider.IPaymentMethodsProvider;
import stonetree.com.mercadolivre.paymentMethods.provider.PaymentMethodsProvider;

@RunWith(AndroidJUnit4.class)
public class PaymentAmountProviderTest extends CoreProviderTest {

    @Before
    public void setTestData() {

    }

    @Test
    public void getPaymentTest() {
        new PaymentMethodsProvider().getPaymentMethods(new IPaymentMethodsProvider() {
            @Override
            public void onSuccess(PaymentMethodsResponse response) {
                executeCoreSuccess(response);
            }

            @Override
            public void onFailure(Error response) {
                executeCoreFailure(response);
            }
        });

        waitProviderResponse();
    }

}

