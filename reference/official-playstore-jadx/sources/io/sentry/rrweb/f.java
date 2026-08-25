package io.sentry.rrweb;

import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.l3;
import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public enum f implements i2 {
    MouseUp,
    MouseDown,
    Click,
    ContextMenu,
    DblClick,
    Focus,
    Blur,
    TouchStart,
    TouchMove_Departed,
    TouchEnd,
    TouchCancel;

    @Override // io.sentry.i2
    public void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        l3Var.d(ordinal());
    }
}
