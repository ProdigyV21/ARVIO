package io.sentry;

import java.io.IOException;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public enum z6 implements i2 {
    SESSION,
    BUFFER;

    @Override // io.sentry.i2
    public void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        l3Var.value(name().toLowerCase(Locale.ROOT));
    }
}
