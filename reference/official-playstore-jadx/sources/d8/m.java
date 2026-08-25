package d8;

import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public enum m {
    BOOLEAN("Boolean"),
    CHAR("Char"),
    BYTE("Byte"),
    SHORT("Short"),
    INT("Int"),
    FLOAT("Float"),
    LONG("Long"),
    DOUBLE("Double");


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final f9.f f14721i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final f9.f f14722l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f14723m = f4.f.o(2, new l(this, 1));

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f14724n = f4.f.o(2, new l(this, 0));

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final Set f14712o = kotlin.collections.r.p0(new m[]{CHAR, BYTE, SHORT, INT, FLOAT, LONG, DOUBLE});

    m(String str) {
        this.f14721i = f9.f.j(str);
        this.f14722l = f9.f.j(str.concat("Array"));
    }
}
