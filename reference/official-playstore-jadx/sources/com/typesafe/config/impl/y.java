package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;

/* JADX INFO: loaded from: classes4.dex */
public abstract class y {
    public static boolean a(Object obj, Object obj2) {
        if (obj == null && obj2 != null) {
            return false;
        }
        if (obj != null && obj2 == null) {
            return false;
        }
        if (obj == obj2) {
            return true;
        }
        return obj.equals(obj2);
    }

    public static ConfigException b(ExceptionInInitializerError exceptionInInitializerError) {
        Throwable cause = exceptionInInitializerError.getCause();
        if (cause == null || !(cause instanceof ConfigException)) {
            throw exceptionInInitializerError;
        }
        return (ConfigException) cause;
    }

    public static boolean c(int i10) {
        if (i10 == 10 || i10 == 32 || i10 == 160 || i10 == 8199 || i10 == 8239 || i10 == 65279) {
            return true;
        }
        return Character.isWhitespace(i10);
    }

    public static String d(String str) {
        StringBuilder sb2 = new StringBuilder("\"");
        for (int i10 = 0; i10 < str.length(); i10++) {
            char cCharAt = str.charAt(i10);
            if (cCharAt == '\f') {
                sb2.append("\\f");
            } else if (cCharAt == '\r') {
                sb2.append("\\r");
            } else if (cCharAt == '\"') {
                sb2.append("\\\"");
            } else if (cCharAt != '\\') {
                switch (cCharAt) {
                    case '\b':
                        sb2.append("\\b");
                        break;
                    case '\t':
                        sb2.append("\\t");
                        break;
                    case '\n':
                        sb2.append("\\n");
                        break;
                    default:
                        if (cCharAt < 0 || cCharAt > 31) {
                            sb2.append(cCharAt);
                        } else {
                            sb2.append(String.format("\\u%04x", Integer.valueOf(cCharAt)));
                        }
                        break;
                }
            } else {
                sb2.append("\\\\");
            }
        }
        sb2.append('\"');
        return sb2.toString();
    }

    public static int e(String str) {
        if (str == null) {
            return 0;
        }
        if (str.endsWith(".json")) {
            return 1;
        }
        if (str.endsWith(".conf")) {
            return 2;
        }
        return str.endsWith(".properties") ? 3 : 0;
    }
}
