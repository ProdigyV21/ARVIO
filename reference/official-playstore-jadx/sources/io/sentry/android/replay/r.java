package io.sentry.android.replay;

import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes5.dex */
public final class r implements q2.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile Object f16996a;

    public boolean a(s sVar) {
        switch (q.f16995a[((s) this.f16996a).ordinal()]) {
            case 1:
                return sVar == s.STARTED || sVar == s.CLOSED;
            case 2:
                return sVar == s.PAUSED || sVar == s.STOPPED || sVar == s.CLOSED;
            case 3:
                return sVar == s.PAUSED || sVar == s.STOPPED || sVar == s.CLOSED;
            case 4:
                return sVar == s.RESUMED || sVar == s.STOPPED || sVar == s.CLOSED;
            case 5:
                return sVar == s.STARTED || sVar == s.CLOSED;
            case 6:
                return false;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
