package f1;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f15221a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f15222b;

    public g(long j10, long j11) {
        if (j11 == 0) {
            this.f15221a = 0L;
            this.f15222b = 1L;
        } else {
            this.f15221a = j10;
            this.f15222b = j11;
        }
    }

    public final String toString() {
        return this.f15221a + DomExceptionUtils.SEPARATOR + this.f15222b;
    }
}
