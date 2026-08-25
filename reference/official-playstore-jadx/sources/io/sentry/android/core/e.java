package io.sentry.android.core;

import io.sentry.e4;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class e implements e4, io.sentry.util.j {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ String f16472i;

    public /* synthetic */ e(io.sentry.protocol.v vVar, String str) {
        this.f16472i = str;
    }

    @Override // io.sentry.e4
    public void e(io.sentry.a1 a1Var) {
        a1Var.G(this.f16472i);
    }

    @Override // io.sentry.util.j
    public Object g() {
        Charset charset = io.sentry.util.s.f17974a;
        String str = this.f16472i;
        if (str.equals("0000-0000")) {
            str = "00000000-0000-0000-0000-000000000000";
        }
        return str.replace("-", "");
    }
}
