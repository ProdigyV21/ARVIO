package com.google.common.base;

/* JADX INFO: loaded from: classes4.dex */
public abstract class i implements r {
    public static String a(char c10) {
        char[] cArr = new char[6];
        cArr[0] = '\\';
        cArr[1] = 'u';
        cArr[2] = 0;
        cArr[3] = 0;
        cArr[4] = 0;
        cArr[5] = 0;
        for (int i10 = 0; i10 < 4; i10++) {
            cArr[5 - i10] = "0123456789ABCDEF".charAt(c10 & 15);
            c10 = (char) (c10 >> 4);
        }
        return String.copyValueOf(cArr);
    }

    public static i b(String str) {
        int length = str.length();
        return length != 0 ? length != 1 ? length != 2 ? new c(str) : new f(str.charAt(0), str.charAt(1)) : new e(str.charAt(0), 0) : b.f13891p;
    }

    public abstract boolean c(char c10);

    public i d() {
        return new c(this);
    }
}
