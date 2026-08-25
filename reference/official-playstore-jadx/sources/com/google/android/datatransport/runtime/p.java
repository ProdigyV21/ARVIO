package com.google.android.datatransport.runtime;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes4.dex */
public final class p implements e3.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f12603a;

    @Override // javax.inject.Provider
    public final Object get() {
        switch (this.f12603a) {
            case 0:
                return new s(Executors.newSingleThreadExecutor());
            default:
                io.sentry.util.l lVar = new io.sentry.util.l(8);
                HashMap map = new HashMap();
                Set set = Collections.EMPTY_SET;
                if (set == null) {
                    throw new NullPointerException("Null flags");
                }
                map.put(z2.c.f23164i, new k3.b(set, 30000L, 86400000L));
                if (set == null) {
                    throw new NullPointerException("Null flags");
                }
                map.put(z2.c.f23166m, new k3.b(set, 1000L, 86400000L));
                if (set == null) {
                    throw new NullPointerException("Null flags");
                }
                Set setUnmodifiableSet = Collections.unmodifiableSet(new HashSet(Arrays.asList(k3.c.f19478l)));
                if (setUnmodifiableSet == null) {
                    throw new NullPointerException("Null flags");
                }
                map.put(z2.c.f23165l, new k3.b(setUnmodifiableSet, 86400000L, 86400000L));
                if (map.keySet().size() < z2.c.values().length) {
                    throw new IllegalStateException("Not all priorities have been configured");
                }
                new HashMap();
                return new k3.a(lVar, map);
        }
    }
}
