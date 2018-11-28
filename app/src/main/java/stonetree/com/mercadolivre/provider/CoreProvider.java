package stonetree.com.mercadolivre.provider;

import android.support.annotation.NonNull;

import stonetree.com.mercadolivre.core.model.CoreRequestTask;
import stonetree.com.mercadolivre.utils.StringUtils;

public class CoreProvider {

    protected CoreRequestTask getPOSTRequest(final String endpoint, ICoreProvider callback) {
        return new CoreRequestTask(callback, endpoint);
    }

    @NonNull
    protected String getUrlAppKey(final String endpoint, String... queryParams) {
        //TODO - App Key Should Be Stored In Safe Place
        return endpoint + "?public_key=444a9ef5-8a6b-429f-abdf-587639155d88" + StringUtils.getQueryParams(queryParams);

    }

}
