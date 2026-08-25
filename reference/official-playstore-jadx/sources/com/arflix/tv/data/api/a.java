package com.arflix.tv.data.api;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class a {
    public static /* synthetic */ Object a(AniSkipApi aniSkipApi, String str, int i10, List list, int i11, d7.d dVar, int i12, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getSkipTimes");
        }
        if ((i12 & 8) != 0) {
            i11 = 0;
        }
        return aniSkipApi.getSkipTimes(str, i10, list, i11, dVar);
    }
}
