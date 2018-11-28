package stonetree.com.mercadolivre.utils;

import java.util.Collection;

public class Collections {

    public static boolean isNullOrEmpty(Collection<?> c) {
        return c == null || c.isEmpty();
    }

    public static boolean isNullOrEmpty( final String s ) {
        return s == null || s.isEmpty();
    }

}
