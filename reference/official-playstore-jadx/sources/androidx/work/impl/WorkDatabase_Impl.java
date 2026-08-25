package androidx.work.impl;

import androidx.appcompat.widget.f0;
import androidx.emoji2.text.e0;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import y1.z;

/* JADX INFO: loaded from: classes3.dex */
public final class WorkDatabase_Impl extends WorkDatabase {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile androidx.work.impl.model.r f6719l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile io.sentry.internal.debugmeta.c f6720m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile io.sentry.internal.debugmeta.c f6721n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public volatile e0 f6722o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public volatile io.sentry.internal.debugmeta.c f6723p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public volatile e0 f6724q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public volatile io.sentry.internal.debugmeta.c f6725r;

    @Override // androidx.work.impl.WorkDatabase
    public final z d() {
        return new z(this, new HashMap(0), new HashMap(0), "Dependency", "WorkSpec", "WorkTag", "SystemIdInfo", "WorkName", "WorkProgress", "Preference");
    }

    @Override // androidx.work.impl.WorkDatabase
    public final c2.d e(y1.g gVar) {
        return gVar.f22825c.b(new c2.c(gVar.f22823a, gVar.f22824b, new f0(gVar, new a8.e(this, 6)), false, false));
    }

    @Override // androidx.work.impl.WorkDatabase
    public final androidx.work.impl.model.b f() {
        io.sentry.internal.debugmeta.c cVar;
        if (this.f6720m != null) {
            return this.f6720m;
        }
        synchronized (this) {
            try {
                if (this.f6720m == null) {
                    this.f6720m = new io.sentry.internal.debugmeta.c(this, 7);
                }
                cVar = this.f6720m;
            } catch (Throwable th) {
                throw th;
            }
        }
        return cVar;
    }

    @Override // androidx.work.impl.WorkDatabase
    public final List g() {
        int i10 = 13;
        int i11 = 14;
        int i12 = 17;
        int i13 = 18;
        return Arrays.asList(new e(i10, i11, 10), new e(11), new e(16, i12, 12), new e(i12, i13, i10), new e(i13, 19, i11), new e(15));
    }

    @Override // androidx.work.impl.WorkDatabase
    public final Set i() {
        return new HashSet();
    }

    @Override // androidx.work.impl.WorkDatabase
    public final Map j() {
        HashMap map = new HashMap();
        List list = Collections.EMPTY_LIST;
        map.put(androidx.work.impl.model.q.class, list);
        map.put(androidx.work.impl.model.b.class, list);
        map.put(androidx.work.impl.model.t.class, list);
        map.put(androidx.work.impl.model.h.class, list);
        map.put(androidx.work.impl.model.l.class, list);
        map.put(androidx.work.impl.model.n.class, list);
        map.put(androidx.work.impl.model.e.class, list);
        map.put(androidx.work.impl.model.f.class, list);
        return map;
    }

    @Override // androidx.work.impl.WorkDatabase
    public final androidx.work.impl.model.e l() {
        io.sentry.internal.debugmeta.c cVar;
        if (this.f6725r != null) {
            return this.f6725r;
        }
        synchronized (this) {
            try {
                if (this.f6725r == null) {
                    this.f6725r = new io.sentry.internal.debugmeta.c((WorkDatabase) this);
                }
                cVar = this.f6725r;
            } catch (Throwable th) {
                throw th;
            }
        }
        return cVar;
    }

    @Override // androidx.work.impl.WorkDatabase
    public final androidx.work.impl.model.h p() {
        e0 e0Var;
        if (this.f6722o != null) {
            return this.f6722o;
        }
        synchronized (this) {
            try {
                if (this.f6722o == null) {
                    this.f6722o = new e0(this, 3);
                }
                e0Var = this.f6722o;
            } catch (Throwable th) {
                throw th;
            }
        }
        return e0Var;
    }

    @Override // androidx.work.impl.WorkDatabase
    public final androidx.work.impl.model.l r() {
        io.sentry.internal.debugmeta.c cVar;
        if (this.f6723p != null) {
            return this.f6723p;
        }
        synchronized (this) {
            try {
                if (this.f6723p == null) {
                    this.f6723p = new io.sentry.internal.debugmeta.c(this, 9);
                }
                cVar = this.f6723p;
            } catch (Throwable th) {
                throw th;
            }
        }
        return cVar;
    }

    @Override // androidx.work.impl.WorkDatabase
    public final androidx.work.impl.model.n s() {
        e0 e0Var;
        if (this.f6724q != null) {
            return this.f6724q;
        }
        synchronized (this) {
            try {
                if (this.f6724q == null) {
                    this.f6724q = new e0(this, 4);
                }
                e0Var = this.f6724q;
            } catch (Throwable th) {
                throw th;
            }
        }
        return e0Var;
    }

    @Override // androidx.work.impl.WorkDatabase
    public final androidx.work.impl.model.q t() {
        androidx.work.impl.model.r rVar;
        if (this.f6719l != null) {
            return this.f6719l;
        }
        synchronized (this) {
            try {
                if (this.f6719l == null) {
                    this.f6719l = new androidx.work.impl.model.r(this);
                }
                rVar = this.f6719l;
            } catch (Throwable th) {
                throw th;
            }
        }
        return rVar;
    }

    @Override // androidx.work.impl.WorkDatabase
    public final androidx.work.impl.model.t u() {
        io.sentry.internal.debugmeta.c cVar;
        if (this.f6721n != null) {
            return this.f6721n;
        }
        synchronized (this) {
            try {
                if (this.f6721n == null) {
                    this.f6721n = new io.sentry.internal.debugmeta.c(this, 10);
                }
                cVar = this.f6721n;
            } catch (Throwable th) {
                throw th;
            }
        }
        return cVar;
    }
}
