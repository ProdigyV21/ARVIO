package io.sentry.android.replay;

import io.sentry.z6;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y f16954a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m f16955b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Date f16956c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f16957d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f16958e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final z6 f16959f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f16960g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final List f16961h;

    public g(y yVar, m mVar, Date date, int i10, long j10, z6 z6Var, String str, List list) {
        this.f16954a = yVar;
        this.f16955b = mVar;
        this.f16956c = date;
        this.f16957d = i10;
        this.f16958e = j10;
        this.f16959f = z6Var;
        this.f16960g = str;
        this.f16961h = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        return kotlin.jvm.internal.p.a(this.f16954a, gVar.f16954a) && kotlin.jvm.internal.p.a(this.f16955b, gVar.f16955b) && kotlin.jvm.internal.p.a(this.f16956c, gVar.f16956c) && this.f16957d == gVar.f16957d && this.f16958e == gVar.f16958e && this.f16959f == gVar.f16959f && kotlin.jvm.internal.p.a(this.f16960g, gVar.f16960g) && kotlin.jvm.internal.p.a(this.f16961h, gVar.f16961h);
    }

    public final int hashCode() {
        int iHashCode = (((this.f16956c.hashCode() + ((this.f16955b.hashCode() + (this.f16954a.hashCode() * 31)) * 31)) * 31) + this.f16957d) * 31;
        long j10 = this.f16958e;
        int iHashCode2 = (this.f16959f.hashCode() + ((iHashCode + ((int) (j10 ^ (j10 >>> 32)))) * 31)) * 31;
        String str = this.f16960g;
        return this.f16961h.hashCode() + ((iHashCode2 + (str == null ? 0 : str.hashCode())) * 31);
    }

    public final String toString() {
        return "LastSegmentData(recorderConfig=" + this.f16954a + ", cache=" + this.f16955b + ", timestamp=" + this.f16956c + ", id=" + this.f16957d + ", duration=" + this.f16958e + ", replayType=" + this.f16959f + ", screenAtStart=" + this.f16960g + ", events=" + this.f16961h + ')';
    }
}
