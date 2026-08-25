package androidx.lifecycle;

import android.os.Bundle;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class a1 implements b2.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b2.e f3205a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f3206b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Bundle f3207c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final x6.i0 f3208d;

    public a1(b2.e eVar, j1 j1Var) {
        this.f3205a = eVar;
        this.f3208d = new x6.i0(new a8.g0(j1Var, 6));
    }

    @Override // b2.d
    public final Bundle a() {
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.f3207c;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        for (Map.Entry<String, w0> entry : ((SavedStateHandlesVM) this.f3208d.getValue()).getHandles().entrySet()) {
            String key = entry.getKey();
            Bundle bundleA = entry.getValue().f3308e.a();
            if (!kotlin.jvm.internal.p.a(bundleA, Bundle.EMPTY)) {
                bundle.putBundle(key, bundleA);
            }
        }
        this.f3206b = false;
        return bundle;
    }

    public final void b() {
        if (this.f3206b) {
            return;
        }
        Bundle bundleA = this.f3205a.a("androidx.lifecycle.internal.SavedStateHandlesProvider");
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.f3207c;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        if (bundleA != null) {
            bundle.putAll(bundleA);
        }
        this.f3207c = bundle;
        this.f3206b = true;
    }
}
