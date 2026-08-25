package com.arflix.tv.data.api;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class b {
    public static /* synthetic */ Object a(ArmApi armApi, String str, String str2, d7.d dVar, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resolve");
        }
        if ((i10 & 2) != 0) {
            str2 = "myanimelist";
        }
        return armApi.resolve(str, str2, dVar);
    }
}
