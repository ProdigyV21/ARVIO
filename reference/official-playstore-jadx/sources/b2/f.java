package b2;

import android.os.Bundle;
import androidx.lifecycle.m;
import androidx.lifecycle.q;
import androidx.lifecycle.r;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g f7120a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f7121b = new e();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f7122c;

    public f(g gVar) {
        this.f7120a = gVar;
    }

    public final void a() {
        g gVar = this.f7120a;
        r lifecycle = gVar.getLifecycle();
        if (lifecycle.getCurrentState() != q.f3267l) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        lifecycle.addObserver(new b(gVar, 0));
        e eVar = this.f7121b;
        if (eVar.f7115b) {
            throw new IllegalStateException("SavedStateRegistry was already attached.");
        }
        lifecycle.addObserver(new m(eVar, 2));
        eVar.f7115b = true;
        this.f7122c = true;
    }

    public final void b(Bundle bundle) {
        if (!this.f7122c) {
            a();
        }
        r lifecycle = this.f7120a.getLifecycle();
        if (lifecycle.getCurrentState().a(q.f3269n)) {
            throw new IllegalStateException(("performRestore cannot be called when owner is " + lifecycle.getCurrentState()).toString());
        }
        e eVar = this.f7121b;
        if (!eVar.f7115b) {
            throw new IllegalStateException("You must call performAttach() before calling performRestore(Bundle).");
        }
        if (eVar.f7117d) {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        eVar.f7116c = bundle != null ? bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key") : null;
        eVar.f7117d = true;
    }

    public final void c(Bundle bundle) {
        e eVar = this.f7121b;
        eVar.getClass();
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = eVar.f7116c;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        p.f fVar = eVar.f7114a;
        fVar.getClass();
        p.d dVar = new p.d(fVar);
        fVar.f21034m.put(dVar, Boolean.FALSE);
        while (dVar.hasNext()) {
            Map.Entry entry = (Map.Entry) dVar.next();
            bundle2.putBundle((String) entry.getKey(), ((d) entry.getValue()).a());
        }
        if (bundle2.isEmpty()) {
            return;
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }
}
