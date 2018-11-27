package stonetree.com.mercadolivre.provider;


import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import stonetree.com.mercadolivre.core.model.Error;
import stonetree.com.mercadolivre.payment.model.PaymentResponse;

@RunWith(AndroidJUnit4.class)
public class PaymentProviderTest extends CoreProviderTest {

    @Before
    public void setTestData() {

    }

    @Test
    public void getPaymentTest() {
        new PaymentProvider().getPayment(new IPaymentProvider() {
            @Override
            public void onSuccess(PaymentResponse response) {
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

