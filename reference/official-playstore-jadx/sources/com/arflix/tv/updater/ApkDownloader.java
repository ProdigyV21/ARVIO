package com.arflix.tv.updater;

import android.content.Context;
import com.arflix.tv.R;
import d7.d;
import dagger.hilt.android.qualifiers.ApplicationContext;
import gb.g0;
import gb.h0;
import gb.j0;
import gb.p0;
import gb.s0;
import io.ktor.http.ContentDisposition;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import r7.p;
import x6.c0;
import x6.d0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Singleton
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J`\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\u00142\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n28\u0010\u0013\u001a4\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00120\fH\u0086@¢\u0006\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0018R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/updater/ApkDownloader;", "", "Landroid/content/Context;", "context", "Lgb/h0;", "okHttpClient", "<init>", "(Landroid/content/Context;Lgb/h0;)V", "", "url", "Ljava/io/File;", "destinationFile", "Lkotlin/Function2;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "downloadedBytes", "totalBytes", "Lx6/t0;", "onProgress", "Lx6/d0;", "download-BWLJW6A", "(Ljava/lang/String;Ljava/io/File;Lr7/p;Ld7/d;)Ljava/lang/Object;", "download", "Landroid/content/Context;", "Lgb/h0;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ApkDownloader {
    public static final int $stable = 8;
    private final Context context;
    private final h0 okHttpClient;

    @Inject
    public ApkDownloader(@ApplicationContext Context context, h0 h0Var) {
        this.context = context;
        this.okHttpClient = h0Var;
    }

    /* JADX INFO: renamed from: download-BWLJW6A, reason: not valid java name */
    public final Object m6612downloadBWLJW6A(String str, File file, p<? super Long, ? super Long, t0> pVar, d<? super d0> dVar) {
        FileOutputStream fileOutputStream;
        byte[] bArr;
        try {
            File parentFile = file.getParentFile();
            if (parentFile != null) {
                parentFile.mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
            g0 g0VarB = this.okHttpClient.b();
            TimeUnit timeUnit = TimeUnit.SECONDS;
            g0VarB.b(60L, timeUnit);
            g0VarB.e(120L, timeUnit);
            h0 h0Var = new h0(g0VarB);
            j0 j0Var = new j0();
            j0Var.i(str);
            p0 p0VarD = h0Var.a(j0Var.b()).d();
            try {
                if (!p0VarD.k()) {
                    throw new IllegalStateException(this.context.getString(R.string.update_error_download_http, new Integer(p0VarD.f15783n)).toString());
                }
                s0 s0Var = p0VarD.f15786q;
                if (s0Var == null) {
                    throw new IllegalStateException(this.context.getString(R.string.update_error_empty_download_body).toString());
                }
                Long l10 = new Long(s0Var.k());
                long j10 = 0;
                if (l10.longValue() <= 0) {
                    l10 = null;
                }
                InputStream inputStreamI = s0Var.i();
                try {
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        bArr = new byte[8192];
                    } finally {
                    }
                } finally {
                }
                while (true) {
                    int i10 = inputStreamI.read(bArr);
                    if (i10 <= 0) {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        inputStreamI.close();
                        p0VarD.close();
                        return file;
                    }
                    fileOutputStream.write(bArr, 0, i10);
                    j10 += (long) i10;
                    pVar.invoke(new Long(j10), l10);
                }
            } finally {
            }
        } catch (Throwable th) {
            return new c0(th);
        }
        return new c0(th);
    }
}
