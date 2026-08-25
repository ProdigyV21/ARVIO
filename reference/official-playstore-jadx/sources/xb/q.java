package xb;

import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f22783a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f22784b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final f0 f22785c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Long f22786d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Long f22787e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Long f22788f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Long f22789g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Map f22790h;

    public q(boolean z, boolean z5, f0 f0Var, Long l10, Long l11, Long l12, Long l13, Map map) {
        this.f22783a = z;
        this.f22784b = z5;
        this.f22785c = f0Var;
        this.f22786d = l10;
        this.f22787e = l11;
        this.f22788f = l12;
        this.f22789g = l13;
        this.f22790h = kotlin.collections.h0.B0(map);
    }

    public final String toString() {
        ArrayList arrayList = new ArrayList();
        if (this.f22783a) {
            arrayList.add("isRegularFile");
        }
        if (this.f22784b) {
            arrayList.add("isDirectory");
        }
        Long l10 = this.f22786d;
        if (l10 != null) {
            arrayList.add("byteCount=" + l10);
        }
        Long l11 = this.f22787e;
        if (l11 != null) {
            arrayList.add("createdAt=" + l11);
        }
        Long l12 = this.f22788f;
        if (l12 != null) {
            arrayList.add("lastModifiedAt=" + l12);
        }
        Long l13 = this.f22789g;
        if (l13 != null) {
            arrayList.add("lastAccessedAt=" + l13);
        }
        Map map = this.f22790h;
        if (!map.isEmpty()) {
            arrayList.add("extras=" + map);
        }
        return kotlin.collections.x.u0(arrayList, ", ", "FileMetadata(", ")", null, 56);
    }

    public /* synthetic */ q(boolean z, boolean z5, f0 f0Var, Long l10, Long l11, Long l12, Long l13) {
        this(z, z5, f0Var, l10, l11, l12, l13, kotlin.collections.a0.f19683i);
    }
}
