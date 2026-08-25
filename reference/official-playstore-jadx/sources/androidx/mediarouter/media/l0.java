package androidx.mediarouter.media;

import android.os.Handler;
import android.os.Looper;
import java.io.File;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f4435a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f4436b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f4437c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f4438d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f4439e;

    public l0(a aVar) {
        this.f4438d = new Handler(Looper.getMainLooper());
        this.f4439e = aVar;
    }

    public l0(long j10, long j11, boolean z, File file, HashMap map) {
        this.f4435a = j10;
        this.f4438d = file;
        this.f4436b = j11;
        this.f4439e = map;
        this.f4437c = z;
    }
}
