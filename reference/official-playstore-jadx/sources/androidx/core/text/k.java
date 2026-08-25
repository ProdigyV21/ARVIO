package androidx.core.text;

/* JADX INFO: loaded from: classes3.dex */
public final class k implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j f2187a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f2188b;

    public k(i iVar, boolean z) {
        this.f2187a = iVar;
        this.f2188b = z;
    }

    public final boolean a() {
        return this.f2188b;
    }

    public final boolean b(CharSequence charSequence, int i10) {
        if (charSequence == null || i10 < 0 || charSequence.length() - i10 < 0) {
            throw new IllegalArgumentException();
        }
        j jVar = this.f2187a;
        if (jVar == null) {
            return a();
        }
        int iA = jVar.a(charSequence, i10);
        if (iA == 0) {
            return true;
        }
        if (iA != 1) {
            return a();
        }
        return false;
    }
}
