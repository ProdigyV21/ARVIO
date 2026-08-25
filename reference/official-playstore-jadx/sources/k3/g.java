package k3;

import android.content.Context;
import com.google.android.datatransport.runtime.y;
import io.sentry.util.l;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class g implements e3.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Provider f19497a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Provider f19498b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Provider f19499c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final y f19500d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Provider f19501e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Provider f19502f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Provider f19503g;

    public g(Provider provider, Provider provider2, Provider provider3, y yVar, Provider provider4, Provider provider5, Provider provider6) {
        this.f19497a = provider;
        this.f19498b = provider2;
        this.f19499c = provider3;
        this.f19500d = yVar;
        this.f19501e = provider4;
        this.f19502f = provider5;
        this.f19503g = provider6;
    }

    @Override // javax.inject.Provider
    public final Object get() {
        return new f((Context) this.f19497a.get(), (c3.e) this.f19498b.get(), (com.google.android.datatransport.runtime.scheduling.persistence.d) this.f19499c.get(), (j) this.f19500d.get(), (Executor) this.f19501e.get(), (l3.a) this.f19502f.get(), new l(8), new l(7), (com.google.android.datatransport.runtime.scheduling.persistence.c) this.f19503g.get());
    }
}
