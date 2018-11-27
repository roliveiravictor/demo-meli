package stonetree.com.mercadolivre.utils;

import java.util.Collection;

public class Collections {

    public static boolean isNotNullOrEmpty(Collection<?> c) {
        return c != null || !c.isEmpty();
    }

}
