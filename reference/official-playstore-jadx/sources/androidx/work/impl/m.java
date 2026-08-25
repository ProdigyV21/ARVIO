package androidx.work.impl;

import android.text.TextUtils;
import com.google.common.util.concurrent.r0;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import m2.a0;
import m2.g0;

/* JADX INFO: loaded from: classes3.dex */
public final class m extends r0 {
    public static final String k = m2.t.f("WorkContinuationImpl");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final r f6897c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f6898d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f6899e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final List f6900f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f6901g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ArrayList f6902h = new ArrayList();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f6903i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public io.sentry.internal.debugmeta.c f6904j;

    public m(r rVar, String str, int i10, List list, int i11) {
        this.f6897c = rVar;
        this.f6898d = str;
        this.f6899e = i10;
        this.f6900f = list;
        this.f6901g = new ArrayList(list.size());
        for (int i12 = 0; i12 < list.size(); i12++) {
            if (i10 == 1 && ((g0) list.get(i12)).f20199b.f6941u != Long.MAX_VALUE) {
                throw new IllegalArgumentException("Next Schedule Time Override must be used with ExistingPeriodicWorkPolicyUPDATE (preferably) or KEEP");
            }
            String string = ((g0) list.get(i12)).f20198a.toString();
            this.f6901g.add(string);
            this.f6902h.add(string);
        }
    }

    public static HashSet K(m mVar) {
        HashSet hashSet = new HashSet();
        mVar.getClass();
        return hashSet;
    }

    public final a0 J() {
        if (this.f6903i) {
            m2.t.d().g(k, "Already enqueued work ids (" + TextUtils.join(", ", this.f6901g) + ")");
        } else {
            io.sentry.internal.debugmeta.c cVar = new io.sentry.internal.debugmeta.c(4);
            this.f6897c.f6967d.d(new androidx.work.impl.utils.c(this, cVar));
            this.f6904j = cVar;
        }
        return this.f6904j;
    }
}
