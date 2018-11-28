package stonetree.com.mercadolivre.quotas.presenter;

import java.util.List;

interface IQuotasSelectionPresenter {

    void onCreate();

    void storeBundle();

    void storeSelectedQuota(String selectedQuota);

    List<String> getQuotas();

}
