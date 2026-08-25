package a2;

import java.util.List;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f88a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f89b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f90c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f91d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f92e;

    public c(String str, String str2, String str3, List list, List list2) {
        this.f88a = str;
        this.f89b = str2;
        this.f90c = str3;
        this.f91d = list;
        this.f92e = list2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (p.a(this.f88a, cVar.f88a) && p.a(this.f89b, cVar.f89b) && p.a(this.f90c, cVar.f90c) && p.a(this.f91d, cVar.f91d)) {
            return p.a(this.f92e, cVar.f92e);
        }
        return false;
    }

    public final int hashCode() {
        return this.f92e.hashCode() + androidx.compose.material3.d.e(this.f91d, androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.f88a.hashCode() * 31, 31, this.f89b), 31, this.f90c), 31);
    }

    public final String toString() {
        return "ForeignKey{referenceTable='" + this.f88a + "', onDelete='" + this.f89b + " +', onUpdate='" + this.f90c + "', columnNames=" + this.f91d + ", referenceColumnNames=" + this.f92e + '}';
    }
}
