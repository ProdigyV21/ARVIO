package com.caverock.androidsvg;

/* JADX INFO: loaded from: classes4.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f12410a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f12411b;

    public q(long j10, int i10) {
        this.f12411b = j10;
        this.f12410a = i10;
    }

    public static q a(int i10, int i11, String str) {
        if (i10 >= i11) {
            return null;
        }
        long j10 = 0;
        int i12 = i10;
        while (i12 < i11) {
            char cCharAt = str.charAt(i12);
            if (cCharAt < '0' || cCharAt > '9') {
                break;
            }
            j10 = (j10 * 10) + ((long) (cCharAt - '0'));
            if (j10 > 2147483647L) {
                return null;
            }
            i12++;
        }
        if (i12 == i10) {
            return null;
        }
        return new q(j10, i12);
    }
}
