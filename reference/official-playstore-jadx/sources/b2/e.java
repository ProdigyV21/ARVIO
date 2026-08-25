package b2;

import android.os.Bundle;
import androidx.lifecycle.l;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
public final class e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f7115b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Bundle f7116c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f7117d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f7118e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p.f f7114a = new p.f();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f7119f = true;

    public final Bundle a(String str) {
        if (!this.f7117d) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
        }
        Bundle bundle = this.f7116c;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle.getBundle(str);
        Bundle bundle3 = this.f7116c;
        if (bundle3 != null) {
            bundle3.remove(str);
        }
        Bundle bundle4 = this.f7116c;
        if (bundle4 != null && !bundle4.isEmpty()) {
            return bundle2;
        }
        this.f7116c = null;
        return bundle2;
    }

    public final d b() {
        String str;
        d dVar;
        Iterator it = this.f7114a.iterator();
        do {
            p.b bVar = (p.b) it;
            if (!bVar.hasNext()) {
                return null;
            }
            Map.Entry entry = (Map.Entry) bVar.next();
            str = (String) entry.getKey();
            dVar = (d) entry.getValue();
        } while (!p.a(str, "androidx.lifecycle.internal.SavedStateHandlesProvider"));
        return dVar;
    }

    public final void c(String str, d dVar) {
        Object obj;
        p.f fVar = this.f7114a;
        p.c cVarA = fVar.a(str);
        if (cVarA != null) {
            obj = cVarA.f21026l;
        } else {
            p.c cVar = new p.c(str, dVar);
            fVar.f21035n++;
            p.c cVar2 = fVar.f21033l;
            if (cVar2 == null) {
                fVar.f21032i = cVar;
                fVar.f21033l = cVar;
            } else {
                cVar2.f21027m = cVar;
                cVar.f21028n = cVar2;
                fVar.f21033l = cVar;
            }
            obj = null;
        }
        if (((d) obj) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }

    public final void d() {
        if (!this.f7119f) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        a aVar = this.f7118e;
        if (aVar == null) {
            aVar = new a(this);
        }
        this.f7118e = aVar;
        try {
            l.class.getDeclaredConstructor(null);
            a aVar2 = this.f7118e;
            if (aVar2 != null) {
                aVar2.f7111a.add(l.class.getName());
            }
        } catch (NoSuchMethodException e5) {
            throw new IllegalArgumentException("Class " + l.class.getSimpleName() + " must have default constructor in order to be automatically recreated", e5);
        }
    }
}
