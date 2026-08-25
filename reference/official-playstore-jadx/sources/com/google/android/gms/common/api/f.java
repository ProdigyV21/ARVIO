package com.google.android.gms.common.api;

import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class f {
    public static final int API_PRIORITY_GAMES = 1;
    public static final int API_PRIORITY_OTHER = Integer.MAX_VALUE;
    public static final int API_PRIORITY_PLUS = 2;

    public List<Scope> getImpliedScopes(Object obj) {
        return Collections.EMPTY_LIST;
    }

    public int getPriority() {
        return Integer.MAX_VALUE;
    }
}
