package androidx.work.impl;

import android.database.Cursor;
import android.os.Looper;
import j$.util.DesugarCollections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.collections.b0;
import y1.z;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u0000B\u0007¢\u0006\u0004\b\u0001\u0010\u0002¨\u0006\u0003"}, d2 = {"Landroidx/work/impl/WorkDatabase;", "<init>", "()V", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class WorkDatabase {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile d2.c f6709a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Executor f6710b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c2.d f6711c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6713e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public List f6714f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final z f6712d = d();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final LinkedHashMap f6715g = new LinkedHashMap();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ReentrantReadWriteLock f6716h = new ReentrantReadWriteLock();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ThreadLocal f6717i = new ThreadLocal();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Map f6718j = DesugarCollections.synchronizedMap(new LinkedHashMap());
    public final LinkedHashMap k = new LinkedHashMap();

    public static Object q(Class cls, c2.d dVar) {
        if (cls.isInstance(dVar)) {
            return dVar;
        }
        if (dVar instanceof y1.i) {
            return q(cls, ((y1.i) dVar).getDelegate());
        }
        return null;
    }

    public final void a() {
        if (!this.f6713e && Looper.getMainLooper().getThread() == Thread.currentThread()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    public final void b() {
        if (!h().getWritableDatabase().E0() && this.f6717i.get() != null) {
            throw new IllegalStateException("Cannot access database on a different coroutine context inherited from a suspending transaction.");
        }
    }

    public final void c() {
        a();
        a();
        c2.b writableDatabase = h().getWritableDatabase();
        this.f6712d.c(writableDatabase);
        if (writableDatabase.H0()) {
            writableDatabase.E();
        } else {
            writableDatabase.m();
        }
    }

    public abstract z d();

    public abstract c2.d e(y1.g gVar);

    public abstract androidx.work.impl.model.b f();

    public List g() {
        return kotlin.collections.z.f19728i;
    }

    public final c2.d h() {
        c2.d dVar = this.f6711c;
        if (dVar != null) {
            return dVar;
        }
        kotlin.jvm.internal.p.i("internalOpenHelper");
        throw null;
    }

    public Set i() {
        return b0.f19686i;
    }

    public Map j() {
        return a0.f19683i;
    }

    public final void k() {
        h().getWritableDatabase().K();
        if (h().getWritableDatabase().E0()) {
            return;
        }
        z zVar = this.f6712d;
        if (zVar.f22866e.compareAndSet(false, true)) {
            Executor executor = zVar.f22862a.f6710b;
            if (executor != null) {
                executor.execute(zVar.f22872l);
            } else {
                kotlin.jvm.internal.p.i("internalQueryExecutor");
                throw null;
            }
        }
    }

    public abstract androidx.work.impl.model.e l();

    public final Cursor m(c2.f fVar) {
        a();
        b();
        return h().getWritableDatabase().A0(fVar);
    }

    public final Object n(Callable callable) {
        c();
        try {
            Object objCall = callable.call();
            o();
            return objCall;
        } finally {
            k();
        }
    }

    public final void o() {
        h().getWritableDatabase().C();
    }

    public abstract androidx.work.impl.model.h p();

    public abstract androidx.work.impl.model.l r();

    public abstract androidx.work.impl.model.n s();

    public abstract androidx.work.impl.model.q t();

    public abstract androidx.work.impl.model.t u();
}
