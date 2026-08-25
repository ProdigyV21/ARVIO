package k6;

import com.squareup.moshi.l;
import kotlin.jvm.internal.p;
import kotlin.reflect.o;

/* JADX INFO: loaded from: classes4.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f19513a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final l f19514b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final o f19515c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final kotlin.reflect.l f19516d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f19517e;

    public a(String str, l lVar, o oVar, kotlin.reflect.l lVar2, int i10) {
        this.f19513a = str;
        this.f19514b = lVar;
        this.f19515c = oVar;
        this.f19516d = lVar2;
        this.f19517e = i10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return p.a(this.f19513a, aVar.f19513a) && p.a(this.f19514b, aVar.f19514b) && p.a(this.f19515c, aVar.f19515c) && p.a(this.f19516d, aVar.f19516d) && this.f19517e == aVar.f19517e;
    }

    public final int hashCode() {
        int iHashCode = (this.f19515c.hashCode() + ((this.f19514b.hashCode() + (this.f19513a.hashCode() * 31)) * 31)) * 31;
        kotlin.reflect.l lVar = this.f19516d;
        return ((iHashCode + (lVar == null ? 0 : lVar.hashCode())) * 31) + this.f19517e;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("Binding(jsonName=");
        sb2.append(this.f19513a);
        sb2.append(", adapter=");
        sb2.append(this.f19514b);
        sb2.append(", property=");
        sb2.append(this.f19515c);
        sb2.append(", parameter=");
        sb2.append(this.f19516d);
        sb2.append(", propertyIndex=");
        return a0.c.o(sb2, this.f19517e, ')');
    }
}
