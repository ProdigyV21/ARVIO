package io.sentry.rrweb;

import io.sentry.ILogger;
import io.sentry.i2;
import io.sentry.l3;
import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public enum d implements i2 {
    Mutation,
    MouseMove,
    MouseInteraction,
    Scroll,
    ViewportResize,
    Input,
    TouchMove,
    MediaInteraction,
    StyleSheetRule,
    CanvasMutation,
    Font,
    Log,
    Drag,
    StyleDeclaration,
    Selection,
    AdoptedStyleSheet,
    CustomElement;

    @Override // io.sentry.i2
    public void serialize(l3 l3Var, ILogger iLogger) throws IOException {
        l3Var.d(ordinal());
    }
}
