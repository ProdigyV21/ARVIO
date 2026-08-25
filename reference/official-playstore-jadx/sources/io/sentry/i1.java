package io.sentry;

import j$.util.concurrent.ConcurrentHashMap;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/* JADX INFO: loaded from: classes4.dex */
public interface i1 {
    void a(Writer writer, Object obj);

    String b(ConcurrentHashMap concurrentHashMap);

    fi.iki.elonen.f c(BufferedInputStream bufferedInputStream);

    void d(fi.iki.elonen.f fVar, OutputStream outputStream);

    Object e(Reader reader, Class cls);
}
