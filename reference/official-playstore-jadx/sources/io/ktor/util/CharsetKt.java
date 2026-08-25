package io.ktor.util;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0019\n\u0002\u0010\u000e\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0005¨\u0006\u0006"}, d2 = {"isLowerCase", "", "", "toCharArray", "", "", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CharsetKt {
    public static final boolean isLowerCase(char c10) {
        return Character.toLowerCase(c10) == c10;
    }

    public static final char[] toCharArray(String str) {
        int length = str.length();
        char[] cArr = new char[length];
        for (int i10 = 0; i10 < length; i10++) {
            cArr[i10] = str.charAt(i10);
        }
        return cArr;
    }
}
