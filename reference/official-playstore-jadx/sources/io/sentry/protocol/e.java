package io.sentry.protocol;

import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.l3;
import java.io.IOException;
import java.util.Locale;

/* JADX INFO: loaded from: classes5.dex */
public enum e implements i2 {
    PORTRAIT,
    LANDSCAPE;

    @Override // io.sentry.i2
    public void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        l3Var.value(toString().toLowerCase(Locale.ROOT));
    }
}
