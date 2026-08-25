package a2;

import java.util.AbstractSet;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f101a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f102b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Set f103c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Set f104d;

    public f(String str, Map map, AbstractSet abstractSet, AbstractSet abstractSet2) {
        this.f101a = str;
        this.f102b = map;
        this.f103c = abstractSet;
        this.f104d = abstractSet2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x01ba, code lost:
    
        r8 = ac.b.c(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x01be, code lost:
    
        r2.close();
     */
    /* JADX WARN: Finally extract failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final a2.f a(d2.c r26, java.lang.String r27) {
        /*
            Method dump skipped, instruction units count: 477
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a2.f.a(d2.c, java.lang.String):a2.f");
    }

    public final boolean equals(Object obj) {
        Set set;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        if (!this.f101a.equals(fVar.f101a) || !this.f102b.equals(fVar.f102b) || !p.a(this.f103c, fVar.f103c)) {
            return false;
        }
        Set set2 = this.f104d;
        if (set2 == null || (set = fVar.f104d) == null) {
            return true;
        }
        return set2.equals(set);
    }

    public final int hashCode() {
        return this.f103c.hashCode() + ((this.f102b.hashCode() + (this.f101a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "TableInfo{name='" + this.f101a + "', columns=" + this.f102b + ", foreignKeys=" + this.f103c + ", indices=" + this.f104d + '}';
    }
}
