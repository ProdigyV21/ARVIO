package org.jsoup.helper;

import a0.c;

/* JADX INFO: loaded from: classes5.dex */
public final class Validate {
    private Validate() {
    }

    public static boolean assertFail(String str) {
        fail(str);
        return false;
    }

    public static Object ensureNotNull(Object obj) {
        if (obj != null) {
            return obj;
        }
        throw new ValidationException("Object must not be null");
    }

    public static void fail(String str) {
        throw new ValidationException(str);
    }

    public static void isFalse(boolean z) {
        if (z) {
            throw new ValidationException("Must be false");
        }
    }

    public static void isTrue(boolean z) {
        if (!z) {
            throw new ValidationException("Must be true");
        }
    }

    public static void noNullElements(Object[] objArr) {
        noNullElements(objArr, "Array must not contain any null objects");
    }

    public static void notEmpty(String str) {
        if (str == null || str.length() == 0) {
            throw new ValidationException("String must not be empty");
        }
    }

    public static void notEmptyParam(String str, String str2) {
        if (str == null || str.length() == 0) {
            throw new ValidationException(c.l("The '", str2, "' parameter must not be empty."));
        }
    }

    public static void notNull(Object obj) {
        if (obj == null) {
            throw new ValidationException("Object must not be null");
        }
    }

    public static void notNullParam(Object obj, String str) {
        if (obj == null) {
            throw new ValidationException(c.l("The parameter '", str, "' must not be null."));
        }
    }

    public static void wtf(String str) {
        throw new IllegalStateException(str);
    }

    public static Object ensureNotNull(Object obj, String str, Object... objArr) {
        if (obj != null) {
            return obj;
        }
        throw new ValidationException(String.format(str, objArr));
    }

    public static void fail(String str, Object... objArr) {
        throw new ValidationException(String.format(str, objArr));
    }

    public static void isFalse(boolean z, String str) {
        if (z) {
            throw new ValidationException(str);
        }
    }

    public static void isTrue(boolean z, String str) {
        if (!z) {
            throw new ValidationException(str);
        }
    }

    public static void noNullElements(Object[] objArr, String str) {
        for (Object obj : objArr) {
            if (obj == null) {
                throw new ValidationException(str);
            }
        }
    }

    public static void notNull(Object obj, String str) {
        if (obj == null) {
            throw new ValidationException(str);
        }
    }

    public static void notEmpty(String str, String str2) {
        if (str == null || str.length() == 0) {
            throw new ValidationException(str2);
        }
    }
}
