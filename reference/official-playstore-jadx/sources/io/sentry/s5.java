package io.sentry;

import androidx.media3.common.C;
import j$.time.Instant;

/* JADX INFO: loaded from: classes4.dex */
public final class s5 extends z4 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Instant f17856i = Instant.now();

    @Override // io.sentry.z4
    public final long i() {
        Instant instant = this.f17856i;
        return (instant.getEpochSecond() * C.NANOS_PER_SECOND) + ((long) instant.getNano());
    }
}
