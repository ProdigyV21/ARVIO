package ob;

/* JADX INFO: loaded from: classes5.dex */
public final class a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final xb.m f20865d = v1.i.g(":");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final xb.m f20866e = v1.i.g(":status");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final xb.m f20867f = v1.i.g(":method");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final xb.m f20868g = v1.i.g(":path");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final xb.m f20869h = v1.i.g(":scheme");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final xb.m f20870i = v1.i.g(":authority");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final xb.m f20871a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final xb.m f20872b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f20873c;

    public a(xb.m mVar, xb.m mVar2) {
        this.f20871a = mVar;
        this.f20872b = mVar2;
        this.f20873c = mVar2.f() + mVar.f() + 32;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return kotlin.jvm.internal.p.a(this.f20871a, aVar.f20871a) && kotlin.jvm.internal.p.a(this.f20872b, aVar.f20872b);
    }

    public final int hashCode() {
        return this.f20872b.hashCode() + (this.f20871a.hashCode() * 31);
    }

    public final String toString() {
        return this.f20871a.t() + ": " + this.f20872b.t();
    }

    public a(String str, String str2) {
        xb.m mVar = new xb.m(str.getBytes(kotlin.text.a.f19924a));
        mVar.f22775m = str;
        xb.m mVar2 = new xb.m(str2.getBytes(kotlin.text.a.f19924a));
        mVar2.f22775m = str2;
        this(mVar, mVar2);
    }

    public a(String str, xb.m mVar) {
        xb.m mVar2 = new xb.m(str.getBytes(kotlin.text.a.f19924a));
        mVar2.f22775m = str;
        this(mVar, mVar2);
    }
}
