package a2;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.p;
import kotlin.text.u;

/* JADX INFO: loaded from: classes3.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f97a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f98b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f99c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f100d;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.util.Collection, java.util.List] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.util.ArrayList] */
    public e(String str, boolean z, List list, List list2) {
        this.f97a = str;
        this.f98b = z;
        this.f99c = list;
        this.f100d = list2;
        if (list2.isEmpty()) {
            int size = list.size();
            list2 = new ArrayList(size);
            for (int i10 = 0; i10 < size; i10++) {
                list2.add("ASC");
            }
        }
        this.f100d = list2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof e) {
            e eVar = (e) obj;
            String str = eVar.f97a;
            if (this.f98b == eVar.f98b && p.a(this.f99c, eVar.f99c) && p.a(this.f100d, eVar.f100d)) {
                String str2 = this.f97a;
                return u.P(str2, "index_", false) ? u.P(str, "index_", false) : str2.equals(str);
            }
        }
        return false;
    }

    public final int hashCode() {
        String str = this.f97a;
        return this.f100d.hashCode() + androidx.compose.material3.d.e(this.f99c, (((u.P(str, "index_", false) ? -1184239155 : str.hashCode()) * 31) + (this.f98b ? 1 : 0)) * 31, 31);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("Index{name='");
        sb2.append(this.f97a);
        sb2.append("', unique=");
        sb2.append(this.f98b);
        sb2.append(", columns=");
        sb2.append(this.f99c);
        sb2.append(", orders=");
        return a0.c.q(sb2, this.f100d, "'}");
    }
}
