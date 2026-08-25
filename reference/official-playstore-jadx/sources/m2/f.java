package m2;

import android.os.Build;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class f {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final f f20187i = new f(1, false, false, false, false, -1, -1, kotlin.collections.b0.f19686i);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f20188a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f20189b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f20190c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f20191d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f20192e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f20193f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f20194g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Set f20195h;

    public f(int i10, boolean z, boolean z5, boolean z10, boolean z11, long j10, long j11, Set set) {
        this.f20188a = i10;
        this.f20189b = z;
        this.f20190c = z5;
        this.f20191d = z10;
        this.f20192e = z11;
        this.f20193f = j10;
        this.f20194g = j11;
        this.f20195h = set;
    }

    public final boolean a() {
        return Build.VERSION.SDK_INT < 24 || !this.f20195h.isEmpty();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !f.class.equals(obj.getClass())) {
            return false;
        }
        f fVar = (f) obj;
        if (this.f20189b == fVar.f20189b && this.f20190c == fVar.f20190c && this.f20191d == fVar.f20191d && this.f20192e == fVar.f20192e && this.f20193f == fVar.f20193f && this.f20194g == fVar.f20194g && this.f20188a == fVar.f20188a) {
            return kotlin.jvm.internal.p.a(this.f20195h, fVar.f20195h);
        }
        return false;
    }

    public final int hashCode() {
        int iC = ((((((((h.f0.c(this.f20188a) * 31) + (this.f20189b ? 1 : 0)) * 31) + (this.f20190c ? 1 : 0)) * 31) + (this.f20191d ? 1 : 0)) * 31) + (this.f20192e ? 1 : 0)) * 31;
        long j10 = this.f20193f;
        int i10 = (iC + ((int) (j10 ^ (j10 >>> 32)))) * 31;
        long j11 = this.f20194g;
        return this.f20195h.hashCode() + ((i10 + ((int) (j11 ^ (j11 >>> 32)))) * 31);
    }

    public final String toString() {
        return "Constraints{requiredNetworkType=" + g8.b.t(this.f20188a) + ", requiresCharging=" + this.f20189b + ", requiresDeviceIdle=" + this.f20190c + ", requiresBatteryNotLow=" + this.f20191d + ", requiresStorageNotLow=" + this.f20192e + ", contentTriggerUpdateDelayMillis=" + this.f20193f + ", contentTriggerMaxDelayMillis=" + this.f20194g + ", contentUriTriggers=" + this.f20195h + ", }";
    }

    public f(f fVar) {
        this.f20189b = fVar.f20189b;
        this.f20190c = fVar.f20190c;
        this.f20188a = fVar.f20188a;
        this.f20191d = fVar.f20191d;
        this.f20192e = fVar.f20192e;
        this.f20195h = fVar.f20195h;
        this.f20193f = fVar.f20193f;
        this.f20194g = fVar.f20194g;
    }
}
