package j3;

import c3.e;
import com.google.android.datatransport.runtime.y;
import e3.d;
import java.util.concurrent.Executor;
import javax.inject.Provider;
import k3.j;

/* JADX INFO: loaded from: classes4.dex */
public final class b implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Provider f19205a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Provider f19206b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final y f19207c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Provider f19208d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Provider f19209e;

    public b(Provider provider, Provider provider2, y yVar, Provider provider3, Provider provider4) {
        this.f19205a = provider;
        this.f19206b = provider2;
        this.f19207c = yVar;
        this.f19208d = provider3;
        this.f19209e = provider4;
    }

    @Override // javax.inject.Provider
    public final Object get() {
        return new a((Executor) this.f19205a.get(), (e) this.f19206b.get(), (j) this.f19207c.get(), (com.google.android.datatransport.runtime.scheduling.persistence.d) this.f19208d.get(), (l3.a) this.f19209e.get());
    }
}
