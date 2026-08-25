package com.arflix.tv.ui.focus;

import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J%\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0011R\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0011R\u0016\u0010\u0012\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0014\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/arflix/tv/ui/focus/ArvioDpadRepeatGate;", "", "", "horizontalMinRepeatIntervalMs", "verticalMinRepeatIntervalMs", "<init>", "(JJ)V", "", "keyCode", "repeatCount", "nowMs", "", "shouldSkip", "(IIJ)Z", "Lx6/t0;", "reset", "()V", "J", "lastKeyCode", "I", "lastHandledAtMs", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ArvioDpadRepeatGate {
    public static final int $stable = 0;
    private final long horizontalMinRepeatIntervalMs;
    private long lastHandledAtMs;
    private int lastKeyCode;
    private final long verticalMinRepeatIntervalMs;

    public ArvioDpadRepeatGate(long j10, long j11) {
        this.horizontalMinRepeatIntervalMs = j10;
        this.verticalMinRepeatIntervalMs = j11;
        this.lastKeyCode = Integer.MIN_VALUE;
    }

    public final void reset() {
        this.lastKeyCode = Integer.MIN_VALUE;
        this.lastHandledAtMs = 0L;
    }

    public final boolean shouldSkip(int keyCode, int repeatCount, long nowMs) {
        boolean z = false;
        if (repeatCount <= 0) {
            this.lastKeyCode = keyCode;
            this.lastHandledAtMs = nowMs;
            return false;
        }
        long j10 = (keyCode == 19 || keyCode == 20) ? this.verticalMinRepeatIntervalMs : this.horizontalMinRepeatIntervalMs;
        if (keyCode == this.lastKeyCode && nowMs - this.lastHandledAtMs < j10) {
            z = true;
        }
        if (!z) {
            this.lastKeyCode = keyCode;
            this.lastHandledAtMs = nowMs;
        }
        return z;
    }

    public /* synthetic */ ArvioDpadRepeatGate(long j10, long j11, int i10, h hVar) {
        this(j10, (i10 & 2) != 0 ? j10 : j11);
    }
}
