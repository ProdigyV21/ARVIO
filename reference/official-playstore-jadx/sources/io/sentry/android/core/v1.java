package io.sentry.android.core;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class v1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Integer f16795a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Boolean f16796b;

    public v1(Integer num, Boolean bool) {
        this.f16795a = num;
        this.f16796b = bool;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof v1)) {
            return false;
        }
        v1 v1Var = (v1) obj;
        return ac.b.C(this.f16795a, v1Var.f16795a) && ac.b.C(this.f16796b, v1Var.f16796b);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16795a, this.f16796b});
    }
}
