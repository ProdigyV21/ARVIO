package io.sentry.android.replay.video;

import java.io.File;

/* JADX INFO: loaded from: classes5.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final File f17056a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f17057b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f17058c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f17059d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f17060e;

    public a(File file, int i10, int i11, int i12, int i13) {
        this.f17056a = file;
        this.f17057b = i10;
        this.f17058c = i11;
        this.f17059d = i12;
        this.f17060e = i13;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f17056a.equals(aVar.f17056a) && this.f17057b == aVar.f17057b && this.f17058c == aVar.f17058c && this.f17059d == aVar.f17059d && this.f17060e == aVar.f17060e;
    }

    public final int hashCode() {
        return (((((((((this.f17056a.hashCode() * 31) + this.f17057b) * 31) + this.f17058c) * 31) + this.f17059d) * 31) + this.f17060e) * 31) + 1331836730;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("MuxerConfig(file=");
        sb2.append(this.f17056a);
        sb2.append(", recordingWidth=");
        sb2.append(this.f17057b);
        sb2.append(", recordingHeight=");
        sb2.append(this.f17058c);
        sb2.append(", frameRate=");
        sb2.append(this.f17059d);
        sb2.append(", bitRate=");
        return androidx.compose.material3.d.j(this.f17060e, ", mimeType=video/avc)", sb2);
    }
}
