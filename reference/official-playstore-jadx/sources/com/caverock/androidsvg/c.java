package com.caverock.androidsvg;

/* JADX INFO: loaded from: classes4.dex */
public final class c extends n0 {
    public c(String str) {
        super(str.replaceAll("(?s)/\\*.*?\\*/", ""));
    }

    public static int w(int i10) {
        if (i10 >= 48 && i10 <= 57) {
            return i10 - 48;
        }
        if (i10 >= 65 && i10 <= 70) {
            return i10 - 55;
        }
        if (i10 < 97 || i10 > 102) {
            return -1;
        }
        return i10 - 87;
    }

    public final String x() {
        int iW;
        if (j()) {
            return null;
        }
        char cCharAt = ((String) this.f12381c).charAt(this.f12379a);
        if (cCharAt != '\'' && cCharAt != '\"') {
            return null;
        }
        StringBuilder sb2 = new StringBuilder();
        this.f12379a++;
        int iIntValue = m().intValue();
        while (iIntValue != -1 && iIntValue != cCharAt) {
            if (iIntValue == 92) {
                iIntValue = m().intValue();
                if (iIntValue != -1) {
                    if (iIntValue == 10 || iIntValue == 13 || iIntValue == 12) {
                        iIntValue = m().intValue();
                    } else {
                        int iW2 = w(iIntValue);
                        if (iW2 != -1) {
                            for (int i10 = 1; i10 <= 5 && (iW = w((iIntValue = m().intValue()))) != -1; i10++) {
                                iW2 = (iW2 * 16) + iW;
                            }
                            sb2.append((char) iW2);
                        }
                    }
                }
            }
            sb2.append((char) iIntValue);
            iIntValue = m().intValue();
        }
        return sb2.toString();
    }

    public final String y() {
        int i10;
        int i11;
        String str = (String) this.f12381c;
        if (j()) {
            i11 = this.f12379a;
        } else {
            int i12 = this.f12379a;
            int iCharAt = str.charAt(i12);
            if (iCharAt == 45) {
                iCharAt = d();
            }
            if ((iCharAt < 65 || iCharAt > 90) && ((iCharAt < 97 || iCharAt > 122) && iCharAt != 95)) {
                i10 = i12;
            } else {
                int iD = d();
                while (true) {
                    if ((iD < 65 || iD > 90) && ((iD < 97 || iD > 122) && !((iD >= 48 && iD <= 57) || iD == 45 || iD == 95))) {
                        break;
                    }
                    iD = d();
                }
                i10 = this.f12379a;
            }
            this.f12379a = i12;
            i11 = i10;
        }
        int i13 = this.f12379a;
        if (i11 == i13) {
            return null;
        }
        String strSubstring = str.substring(i13, i11);
        this.f12379a = i11;
        return strSubstring;
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException
        */
    public final java.util.ArrayList z() {
        /*
            Method dump skipped, instruction units count: 1190
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.c.z():java.util.ArrayList");
    }
}
