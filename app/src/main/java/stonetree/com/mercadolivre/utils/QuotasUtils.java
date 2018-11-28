package stonetree.com.mercadolivre.utils;

import java.util.ArrayList;
import java.util.List;

import stonetree.com.mercadolivre.quotas.model.PayerCosts;
import stonetree.com.mercadolivre.quotas.model.QuotaSelection;
import stonetree.com.mercadolivre.quotas.model.QuotasSelectionResponse;

public class QuotasUtils {

    public static List<String> getQuotasToSelect(QuotasSelectionResponse quotasSelectionResponse) {
        final List<String> quotasToSelect = new ArrayList<>();
        final List<QuotaSelection> quotasResponse = quotasSelectionResponse.getQuotas();
        for (QuotaSelection quotaSelection : quotasResponse) {
            for (PayerCosts payerCosts : quotaSelection.getPayerCosts()) {
                quotasToSelect.add(payerCosts.getRecommendedMessage());
            }
        }
        return quotasToSelect;
    }

}
