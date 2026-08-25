package t8;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v9.w f21976a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f21977b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f21978c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f21979d;

    public v(v9.w wVar, List list, ArrayList arrayList, List list2) {
        this.f21976a = wVar;
        this.f21977b = list;
        this.f21978c = arrayList;
        this.f21979d = list2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof v)) {
            return false;
        }
        v vVar = (v) obj;
        return this.f21976a.equals(vVar.f21976a) && this.f21977b.equals(vVar.f21977b) && this.f21978c.equals(vVar.f21978c) && this.f21979d.equals(vVar.f21979d);
    }

    public final int hashCode() {
        return this.f21979d.hashCode() + ((((this.f21978c.hashCode() + androidx.compose.material3.d.e(this.f21977b, this.f21976a.hashCode() * 961, 31)) * 31) + 1237) * 31);
    }

    public final String toString() {
        return "MethodSignatureData(returnType=" + this.f21976a + ", receiverType=null, valueParameters=" + this.f21977b + ", typeParameters=" + this.f21978c + ", hasStableParameterNames=false, errors=" + this.f21979d + ')';
    }
}
