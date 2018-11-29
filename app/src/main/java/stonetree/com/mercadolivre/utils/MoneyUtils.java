package stonetree.com.mercadolivre.utils;

import stonetree.com.mercadolivre.constants.Constants;

public class MoneyUtils {

    public static String cleanMask(String price) {
        final String cleanedMoneyLabel = price.replace(Constants.MONEY_LABEL, Constants.EMPTY);
        final String cleanedCents = cleanedMoneyLabel.replace(Constants.CENTS, Constants.EMPTY);
        final String cleanedCentsFirstDelete = cleanedCents.replace(Constants.CENTS_FIRST_DELETE, Constants.EMPTY);
        return cleanedCentsFirstDelete.replaceAll("[$,.]", Constants.EMPTY);
    }

}
