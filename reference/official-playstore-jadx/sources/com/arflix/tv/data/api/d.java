package com.arflix.tv.data.api;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class d {
    public static /* synthetic */ Object a(MdbListApi mdbListApi, String str, int i10, int i11, d7.d dVar, int i12, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getWatched");
        }
        if ((i12 & 2) != 0) {
            i10 = 1000;
        }
        if ((i12 & 4) != 0) {
            i11 = 0;
        }
        return mdbListApi.getWatched(str, i10, i11, dVar);
    }

    public static /* synthetic */ Object b(MdbListApi mdbListApi, String str, int i10, int i11, String str2, d7.d dVar, int i12, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getWatchlistItems");
        }
        if ((i12 & 2) != 0) {
            i10 = 1000;
        }
        int i13 = i10;
        if ((i12 & 4) != 0) {
            i11 = 0;
        }
        int i14 = i11;
        if ((i12 & 8) != 0) {
            str2 = "true";
        }
        return mdbListApi.getWatchlistItems(str, i13, i14, str2, dVar);
    }
}
