package com.typesafe.config.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Comparator;

/* JADX INFO: loaded from: classes4.dex */
public final class u1 implements Comparator, Serializable {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        boolean z;
        String str = (String) obj;
        String str2 = (String) obj2;
        int length = str.length();
        boolean z5 = false;
        if (length == 0) {
            z = false;
            break;
        }
        for (int i10 = 0; i10 < length; i10++) {
            if (!Character.isDigit(str.charAt(i10))) {
                z = false;
                break;
            }
        }
        z = true;
        int length2 = str2.length();
        if (length2 != 0) {
            int i11 = 0;
            while (true) {
                if (i11 >= length2) {
                    z5 = true;
                    break;
                }
                if (!Character.isDigit(str2.charAt(i11))) {
                    break;
                }
                i11++;
            }
        }
        if (z && z5) {
            return new BigInteger(str).compareTo(new BigInteger(str2));
        }
        if (z) {
            return -1;
        }
        if (z5) {
            return 1;
        }
        return str.compareTo(str2);
    }
}
