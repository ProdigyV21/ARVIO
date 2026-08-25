package db;

/* JADX INFO: loaded from: classes5.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f14901a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f14902b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f14903c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f14904d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f14905e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f14906f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f14907g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f14908h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f14909i;

    public i(boolean z, boolean z5, boolean z10, boolean z11, boolean z12, String str, String str2, boolean z13, boolean z14) {
        this.f14901a = z;
        this.f14902b = z5;
        this.f14903c = z10;
        this.f14904d = z11;
        this.f14905e = z12;
        this.f14906f = str;
        this.f14907g = str2;
        this.f14908h = z13;
        this.f14909i = z14;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("JsonConfiguration(encodeDefaults=");
        sb2.append(this.f14901a);
        sb2.append(", ignoreUnknownKeys=");
        sb2.append(this.f14902b);
        sb2.append(", isLenient=");
        sb2.append(this.f14903c);
        sb2.append(", allowStructuredMapKeys=");
        sb2.append(this.f14904d);
        sb2.append(", prettyPrint=false, explicitNulls=");
        sb2.append(this.f14905e);
        sb2.append(", prettyPrintIndent='");
        sb2.append(this.f14906f);
        sb2.append("', coerceInputValues=false, useArrayPolymorphism=false, classDiscriminator='");
        sb2.append(this.f14907g);
        sb2.append("', allowSpecialFloatingPointValues=");
        sb2.append(this.f14908h);
        sb2.append(", useAlternativeNames=");
        return a0.c.m(", namingStrategy=null)", this.f14909i, sb2);
    }
}
