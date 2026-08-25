package u8;

import h.f0;
import java.util.Set;
import kotlin.jvm.internal.p;
import v9.b0;

/* JADX INFO: loaded from: classes5.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Set f22056a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f22057b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f22058c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f22059d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f22060e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Set f22061f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final b0 f22062g;

    public a(int i10, int i11, boolean z, boolean z5, Set set, b0 b0Var) {
        this.f22056a = set;
        this.f22057b = i10;
        this.f22058c = i11;
        this.f22059d = z;
        this.f22060e = z5;
        this.f22061f = set;
        this.f22062g = b0Var;
    }

    public static a a(a aVar, int i10, boolean z, Set set, b0 b0Var, int i11) {
        int i12 = aVar.f22057b;
        if ((i11 & 2) != 0) {
            i10 = aVar.f22058c;
        }
        int i13 = i10;
        if ((i11 & 4) != 0) {
            z = aVar.f22059d;
        }
        boolean z5 = z;
        boolean z10 = aVar.f22060e;
        if ((i11 & 16) != 0) {
            set = aVar.f22061f;
        }
        Set set2 = set;
        if ((i11 & 32) != 0) {
            b0Var = aVar.f22062g;
        }
        aVar.getClass();
        return new a(i12, i13, z5, z10, set2, b0Var);
    }

    public final a b(int i10) {
        return a(this, i10, false, null, null, 61);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return p.a(aVar.f22062g, this.f22062g) && aVar.f22057b == this.f22057b && aVar.f22058c == this.f22058c && aVar.f22059d == this.f22059d && aVar.f22060e == this.f22060e;
    }

    public final int hashCode() {
        b0 b0Var = this.f22062g;
        int iHashCode = b0Var != null ? b0Var.hashCode() : 0;
        int iC = f0.c(this.f22057b) + (iHashCode * 31) + iHashCode;
        int iC2 = f0.c(this.f22058c) + (iC * 31) + iC;
        int i10 = (iC2 * 31) + (this.f22059d ? 1 : 0) + iC2;
        return (i10 * 31) + (this.f22060e ? 1 : 0) + i10;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("JavaTypeAttributes(howThisTypeIsUsed=");
        int i10 = this.f22057b;
        sb2.append(i10 != 1 ? i10 != 2 ? "null" : "COMMON" : "SUPERTYPE");
        sb2.append(", flexibility=");
        int i11 = this.f22058c;
        sb2.append(i11 != 1 ? i11 != 2 ? i11 != 3 ? "null" : "FLEXIBLE_LOWER_BOUND" : "FLEXIBLE_UPPER_BOUND" : "INFLEXIBLE");
        sb2.append(", isRaw=");
        sb2.append(this.f22059d);
        sb2.append(", isForAnnotationParameter=");
        sb2.append(this.f22060e);
        sb2.append(", visitedTypeParameters=");
        sb2.append(this.f22061f);
        sb2.append(", defaultType=");
        sb2.append(this.f22062g);
        sb2.append(')');
        return sb2.toString();
    }

    public /* synthetic */ a(int i10, boolean z, boolean z5, Set set, int i11) {
        this(i10, 1, (i11 & 4) != 0 ? false : z, (i11 & 8) != 0 ? false : z5, (i11 & 16) != 0 ? null : set, null);
    }
}
