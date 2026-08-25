package c9;

/* JADX INFO: loaded from: classes5.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7430a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f7431b;

    public d(int i10, int i11) {
        this.f7430a = i10;
        this.f7431b = i11;
    }

    public static b a(d dVar) {
        return new b(dVar.f7430a + dVar.f7431b, 1);
    }

    public static b b() {
        return new b(0, 1);
    }
}
