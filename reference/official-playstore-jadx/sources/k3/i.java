package k3;

import com.google.android.datatransport.runtime.y;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class i implements e3.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Provider f19508a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Provider f19509b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final y f19510c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Provider f19511d;

    public i(Provider provider, Provider provider2, y yVar, Provider provider3) {
        this.f19508a = provider;
        this.f19509b = provider2;
        this.f19510c = yVar;
        this.f19511d = provider3;
    }

    @Override // javax.inject.Provider
    public final Object get() {
        return new h((Executor) this.f19508a.get(), (com.google.android.datatransport.runtime.scheduling.persistence.d) this.f19509b.get(), (j) this.f19510c.get(), (l3.a) this.f19511d.get());
    }
}
