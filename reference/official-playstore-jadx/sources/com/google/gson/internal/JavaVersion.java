package com.google.gson.internal;

/* JADX INFO: loaded from: classes4.dex */
public final class JavaVersion {
    private static final int majorJavaVersion = determineMajorJavaVersion();

    private JavaVersion() {
    }

    private static int determineMajorJavaVersion() {
        return getMajorJavaVersion(System.getProperty("java.version"));
    }

    private static int extractBeginningInt(String str) {
        try {
            StringBuilder sb2 = new StringBuilder();
            for (int i10 = 0; i10 < str.length(); i10++) {
                char cCharAt = str.charAt(i10);
                if (!Character.isDigit(cCharAt)) {
                    break;
                }
                sb2.append(cCharAt);
            }
            return Integer.parseInt(sb2.toString());
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public static int getMajorJavaVersion(String str) {
        int dotted = parseDotted(str);
        if (dotted == -1) {
            dotted = extractBeginningInt(str);
        }
        if (dotted == -1) {
            return 6;
        }
        return dotted;
    }

    public static boolean isJava9OrLater() {
        return majorJavaVersion >= 9;
    }

    private static int parseDotted(String str) {
        try {
            String[] strArrSplit = str.split("[._]");
            int i10 = Integer.parseInt(strArrSplit[0]);
            return (i10 != 1 || strArrSplit.length <= 1) ? i10 : Integer.parseInt(strArrSplit[1]);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public static int getMajorJavaVersion() {
        return majorJavaVersion;
    }
}
