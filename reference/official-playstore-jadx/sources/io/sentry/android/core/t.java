package io.sentry.android.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f16745a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f16746b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f16747c;

    public t(List list) {
        this.f16747c = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            io.sentry.android.core.anr.f fVar = (io.sentry.android.core.anr.f) it.next();
            if (fVar != null) {
                ((ArrayList) this.f16747c).add(fVar);
            }
        }
        Collections.sort((ArrayList) this.f16747c);
        if (((ArrayList) this.f16747c).isEmpty()) {
            this.f16745a = 0L;
            this.f16746b = 0L;
        } else {
            this.f16745a = ((io.sentry.android.core.anr.f) ((ArrayList) this.f16747c).get(0)).f16436l;
            this.f16746b = ((io.sentry.android.core.anr.f) a0.c.h(1, (ArrayList) this.f16747c)).f16436l + 10000;
        }
    }

    public t(long j10, long j11, Date date) {
        this.f16745a = j10;
        this.f16746b = j11;
        this.f16747c = date;
    }
}
