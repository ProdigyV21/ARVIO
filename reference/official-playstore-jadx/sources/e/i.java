package e;

import androidx.activity.d0;
import java.lang.reflect.InvocationTargetException;
import ka.k0;

/* JADX INFO: loaded from: classes.dex */
public final class i extends d0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public k0 f14951d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public r7.p f14952e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public androidx.loader.app.d f14953f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f14954g;

    @Override // androidx.activity.d0
    public final void a() throws IllegalAccessException, InvocationTargetException {
        androidx.loader.app.d dVar = this.f14953f;
        if (dVar != null) {
            dVar.d();
        }
        androidx.loader.app.d dVar2 = this.f14953f;
        if (dVar2 != null) {
            dVar2.f3323l = false;
        }
        this.f14954g = false;
    }

    @Override // androidx.activity.d0
    public final void b() throws IllegalAccessException, InvocationTargetException {
        androidx.loader.app.d dVar = this.f14953f;
        if (dVar != null && !dVar.f3323l) {
            dVar.d();
            this.f14953f = null;
        }
        if (this.f14953f == null) {
            this.f14953f = new androidx.loader.app.d(this.f14951d, false, this.f14952e, this);
        }
        androidx.loader.app.d dVar2 = this.f14953f;
        if (dVar2 != null) {
            ((ma.f) dVar2.f3324m).close(null);
        }
        androidx.loader.app.d dVar3 = this.f14953f;
        if (dVar3 != null) {
            dVar3.f3323l = false;
        }
        this.f14954g = false;
    }

    @Override // androidx.activity.d0
    public final void c(androidx.activity.c cVar) {
        androidx.loader.app.d dVar = this.f14953f;
        if (dVar != null) {
            ((ma.f) dVar.f3324m).mo6685trySendJP2dKIU(cVar);
        }
    }

    @Override // androidx.activity.d0
    public final void d() throws IllegalAccessException, InvocationTargetException {
        androidx.loader.app.d dVar = this.f14953f;
        if (dVar != null) {
            dVar.d();
        }
        if (this.f931a) {
            this.f14953f = new androidx.loader.app.d(this.f14951d, true, this.f14952e, this);
        }
        this.f14954g = true;
    }
}
