package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerLibraryIdentity;", "", "<init>", "()V", "stableNativeId", "", "sourceRef", "", "itemId", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class HomeServerLibraryIdentity {
    public static final int $stable = 0;
    public static final HomeServerLibraryIdentity INSTANCE = new HomeServerLibraryIdentity();

    private HomeServerLibraryIdentity() {
    }

    public final int stableNativeId(String sourceRef, String itemId) {
        int iHashCode = (sourceRef + ":" + itemId).hashCode() & Integer.MAX_VALUE;
        if (iHashCode < 1) {
            iHashCode = 1;
        }
        return -iHashCode;
    }
}
