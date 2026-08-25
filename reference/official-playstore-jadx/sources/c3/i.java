package c3;

import android.content.Context;
import androidx.appcompat.app.i1;
import com.google.android.datatransport.runtime.scheduling.persistence.m;
import com.google.android.datatransport.runtime.scheduling.persistence.p;
import io.sentry.util.l;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class i implements e3.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7398a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Provider f7399b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Provider f7400c;

    public /* synthetic */ i(Provider provider, Provider provider2, int i10) {
        this.f7398a = i10;
        this.f7399b = provider;
        this.f7400c = provider2;
    }

    @Override // javax.inject.Provider
    public final Object get() {
        switch (this.f7398a) {
            case 0:
                return new h((Context) ((g) this.f7399b).f7394b, (i1) ((g) this.f7400c).get());
            default:
                return new m(new l(8), new l(7), com.google.android.datatransport.runtime.scheduling.persistence.a.f12609f, (p) this.f7399b.get(), this.f7400c);
        }
    }
}
