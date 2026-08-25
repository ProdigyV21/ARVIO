package e0;

/* JADX INFO: loaded from: classes3.dex */
public final class j extends g {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ k f14997r;

    public j(k kVar) {
        this.f14997r = kVar;
    }

    @Override // e0.g
    public final String l() {
        h hVar = (h) this.f14997r.f14998i.get();
        if (hVar == null) {
            return "Completer object has been garbage collected, future will fail soon";
        }
        return "tag=[" + hVar.f14993a + "]";
    }
}
