package com.mogsev.forservice.util;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public final class BooleanHelper {
    private static final String TAG = BooleanHelper.class.getSimpleName();

    private BooleanHelper() {
        // empty
    }

    /**
     * Return byte true = 1 or false = 0
     * @param boo byte
     * @return
     */
    public static byte toByte(boolean boo) {
        return (byte) (boo ? 1 : 0);
    }

    public static boolean toBoolean(byte b) {
        return b != 0;
    }

}
