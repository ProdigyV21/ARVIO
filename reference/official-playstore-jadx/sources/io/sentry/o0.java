package io.sentry;

import java.io.Closeable;

/* JADX INFO: loaded from: classes4.dex */
public interface o0 extends Closeable {

    public enum a {
        UNKNOWN,
        CONNECTED,
        DISCONNECTED,
        NO_PERMISSION
    }

    public interface b {
        void i(a aVar);
    }

    void G0(b bVar);

    a M();

    String s();

    boolean y0(b bVar);
}
