package io.sentry.android.replay;

import java.io.File;

/* JADX INFO: loaded from: classes5.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final File f16938a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f16939b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f16940c;

    public f(File file, int i10, long j10) {
        this.f16938a = file;
        this.f16939b = i10;
        this.f16940c = j10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return kotlin.jvm.internal.p.a(this.f16938a, fVar.f16938a) && this.f16939b == fVar.f16939b && this.f16940c == fVar.f16940c;
    }

    public final int hashCode() {
        int iHashCode = ((this.f16938a.hashCode() * 31) + this.f16939b) * 31;
        long j10 = this.f16940c;
        return iHashCode + ((int) (j10 ^ (j10 >>> 32)));
    }

    public final String toString() {
        return "GeneratedVideo(video=" + this.f16938a + ", frameCount=" + this.f16939b + ", duration=" + this.f16940c + ')';
    }
}
