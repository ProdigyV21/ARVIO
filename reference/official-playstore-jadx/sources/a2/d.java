package a2;

/* JADX INFO: loaded from: classes3.dex */
public final class d implements Comparable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f93i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f94l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f95m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f96n;

    public d(int i10, int i11, String str, String str2) {
        this.f93i = i10;
        this.f94l = i11;
        this.f95m = str;
        this.f96n = str2;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        d dVar = (d) obj;
        int i10 = this.f93i - dVar.f93i;
        return i10 == 0 ? this.f94l - dVar.f94l : i10;
    }
}
