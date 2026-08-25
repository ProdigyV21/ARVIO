package a2;

import java.util.Locale;
import kotlin.jvm.internal.p;
import kotlin.text.o;

/* JADX INFO: loaded from: classes3.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f81a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f82b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f83c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f84d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f85e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f86f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f87g;

    public a(int i10, String str, String str2, String str3, boolean z, int i11) {
        this.f81a = str;
        this.f82b = str2;
        this.f83c = z;
        this.f84d = i10;
        this.f85e = str3;
        this.f86f = i11;
        String upperCase = str2.toUpperCase(Locale.US);
        this.f87g = o.T(upperCase, "INT", false) ? 3 : (o.T(upperCase, "CHAR", false) || o.T(upperCase, "CLOB", false) || o.T(upperCase, "TEXT", false)) ? 2 : o.T(upperCase, "BLOB", false) ? 5 : (o.T(upperCase, "REAL", false) || o.T(upperCase, "FLOA", false) || o.T(upperCase, "DOUB", false)) ? 4 : 1;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (this.f84d != aVar.f84d) {
                return false;
            }
            int i10 = aVar.f86f;
            String str = aVar.f85e;
            if (!p.a(this.f81a, aVar.f81a) || this.f83c != aVar.f83c) {
                return false;
            }
            String str2 = this.f85e;
            int i11 = this.f86f;
            if (i11 == 1 && i10 == 2 && str2 != null && !ac.b.y(str2, str)) {
                return false;
            }
            if (i11 == 2 && i10 == 1 && str != null && !ac.b.y(str, str2)) {
                return false;
            }
            if (i11 != 0 && i11 == i10) {
                if (str2 != null) {
                    if (!ac.b.y(str2, str)) {
                        return false;
                    }
                } else if (str != null) {
                    return false;
                }
            }
            if (this.f87g != aVar.f87g) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        return (((((this.f81a.hashCode() * 31) + this.f87g) * 31) + (this.f83c ? 1231 : 1237)) * 31) + this.f84d;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("Column{name='");
        sb2.append(this.f81a);
        sb2.append("', type='");
        sb2.append(this.f82b);
        sb2.append("', affinity='");
        sb2.append(this.f87g);
        sb2.append("', notNull=");
        sb2.append(this.f83c);
        sb2.append(", primaryKeyPosition=");
        sb2.append(this.f84d);
        sb2.append(", defaultValue='");
        String str = this.f85e;
        if (str == null) {
            str = "undefined";
        }
        return a0.c.p(sb2, str, "'}");
    }
}
