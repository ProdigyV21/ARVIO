package io.sentry.android.core.anr;

import androidx.compose.material3.MenuKt;
import io.sentry.ILogger;
import io.sentry.cache.tape.g;
import io.sentry.cache.tape.j;
import io.sentry.w5;
import io.sentry.y6;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* JADX INFO: loaded from: classes5.dex */
public final class d implements AutoCloseable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final g f16432i;

    public d(y6 y6Var, File file) {
        j jVar;
        ILogger logger = y6Var.getLogger();
        try {
            try {
                try {
                    jVar = new j(file, j.k(file), MenuKt.InTransitionDuration);
                } catch (Throwable th) {
                    throw th;
                }
            } catch (IOException e5) {
                logger.l(w5.ERROR, "Failed to create stacktrace queue", e5);
                jVar = null;
            }
        } catch (IOException unused) {
            if (!file.delete()) {
                throw new IOException("Could not delete file");
            }
            RandomAccessFile randomAccessFileK = j.k(file);
            try {
                jVar = new j(file, randomAccessFileK, MenuKt.InTransitionDuration);
            } finally {
                randomAccessFileK.close();
            }
        }
        if (jVar == null) {
            this.f16432i = new io.sentry.cache.tape.b();
        } else {
            this.f16432i = new io.sentry.cache.tape.e(jVar, new a1.a());
        }
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        this.f16432i.close();
    }
}
