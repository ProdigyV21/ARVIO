package io.sentry;

import java.io.IOException;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public enum a6 implements i2 {
    TRACE(1),
    DEBUG(5),
    INFO(9),
    WARN(13),
    ERROR(17),
    FATAL(21);

    private final int severityNumber;

    a6(int i10) {
        this.severityNumber = i10;
    }

    public int getSeverityNumber() {
        return this.severityNumber;
    }

    @Override // io.sentry.i2
    public void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        l3Var.value(name().toLowerCase(Locale.ROOT));
    }
}
