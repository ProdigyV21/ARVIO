package e0;

/* JADX INFO: loaded from: classes3.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f14993a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public k f14994b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public l f14995c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f14996d;

    public final void a() {
        this.f14996d = true;
        k kVar = this.f14994b;
        if (kVar == null || !kVar.f14999l.n(null)) {
            return;
        }
        this.f14993a = null;
        this.f14994b = null;
        this.f14995c = null;
    }

    public final void finalize() {
        l lVar;
        k kVar = this.f14994b;
        if (kVar != null) {
            j jVar = kVar.f14999l;
            if (!jVar.isDone()) {
                jVar.o(new androidx.work.impl.utils.futures.b("The completer object was garbage collected - this future would otherwise never complete. The tag was: " + this.f14993a, 3));
            }
        }
        if (this.f14996d || (lVar = this.f14995c) == null) {
            return;
        }
        lVar.n(null);
    }
}
