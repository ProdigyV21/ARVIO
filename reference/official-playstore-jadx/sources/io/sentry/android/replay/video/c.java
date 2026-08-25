package io.sentry.android.replay.video;

import android.media.MediaMuxer;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class c implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f17061a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final MediaMuxer f17062b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f17063c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f17064d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f17065e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f17066f;

    public c(String str, float f10) {
        this.f17061a = (long) (TimeUnit.SECONDS.toMicros(1L) / f10);
        this.f17062b = new MediaMuxer(str, 0);
    }
}
