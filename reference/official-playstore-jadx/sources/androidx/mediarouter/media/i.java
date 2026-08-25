package androidx.mediarouter.media;

/* JADX INFO: loaded from: classes3.dex */
public final class i extends u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4384a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h f4385b;

    public i(String str, h hVar) {
        this.f4384a = str;
        this.f4385b = hVar;
    }

    @Override // androidx.mediarouter.media.u
    public final void f(int i10) {
        h hVar;
        String str = this.f4384a;
        if (str == null || (hVar = this.f4385b) == null) {
            return;
        }
        hVar.l(i10, str);
    }

    @Override // androidx.mediarouter.media.u
    public final void i(int i10) {
        h hVar;
        String str = this.f4384a;
        if (str == null || (hVar = this.f4385b) == null) {
            return;
        }
        hVar.m(i10, str);
    }
}
