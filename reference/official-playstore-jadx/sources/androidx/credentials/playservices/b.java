package androidx.credentials.playservices;

import android.graphics.Point;
import android.os.CancellationSignal;
import androidx.credentials.CredentialManagerCallback;
import androidx.media3.common.TrackGroup;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import io.sentry.ILogger;
import io.sentry.l4;
import io.sentry.m;
import io.sentry.r7;
import io.sentry.transport.p;
import io.sentry.w;
import io.sentry.w5;
import io.sentry.x;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;
import java.util.concurrent.Executor;
import qb.l;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class b implements com.google.android.gms.tasks.d, DefaultTrackSelector.TrackInfo.Factory, l4 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f2360i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f2361l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f2362m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f2363n;

    public /* synthetic */ b(Object obj, Object obj2, Object obj3, Object obj4) {
        this.f2360i = obj;
        this.f2361l = obj2;
        this.f2362m = obj3;
        this.f2363n = obj4;
    }

    @Override // io.sentry.l4
    public void a() {
        int i10;
        int i11;
        ILogger iLogger = (ILogger) this.f2360i;
        String str = (String) this.f2361l;
        final x xVar = (x) this.f2362m;
        File file = (File) this.f2363n;
        w5 w5Var = w5.DEBUG;
        int i12 = 1;
        int i13 = 0;
        iLogger.q(w5Var, "Started processing cached files from %s", str);
        r7 r7Var = xVar.f18048d;
        ILogger iLogger2 = xVar.f18046b;
        try {
            iLogger2.q(w5Var, "Processing dir. %s", file.getAbsolutePath());
            File[] fileArrListFiles = file.listFiles(new FilenameFilter() { // from class: io.sentry.v
                @Override // java.io.FilenameFilter
                public final boolean accept(File file2, String str2) {
                    return xVar.a(str2);
                }
            });
            if (fileArrListFiles != null) {
                iLogger2.q(w5Var, "Processing %d items from cache dir %s", Integer.valueOf(fileArrListFiles.length), file.getAbsolutePath());
                int length = fileArrListFiles.length;
                int i14 = 0;
                while (true) {
                    if (i14 >= length) {
                        break;
                    }
                    File file2 = fileArrListFiles[i14];
                    if (file2.isFile()) {
                        String absolutePath = file2.getAbsolutePath();
                        if (!r7Var.contains(absolutePath)) {
                            p pVarF = xVar.f18045a.f();
                            if (pVarF != null && pVarF.j(m.All)) {
                                iLogger2.q(w5.INFO, "DirectoryProcessor, rate limiting active.", new Object[i13]);
                                break;
                            }
                            w5 w5Var2 = w5.DEBUG;
                            i10 = i13;
                            try {
                                Object[] objArr = new Object[i12];
                                objArr[i10] = absolutePath;
                                iLogger2.q(w5Var2, "Processing file: %s", objArr);
                                i11 = i14;
                                xVar.b(file2, l.k(new w(xVar.f18047c, xVar.f18046b, absolutePath, r7Var)));
                                Thread.sleep(100L);
                                i14 = i11 + 1;
                                i13 = i10;
                                i12 = 1;
                            } catch (Throwable th) {
                                th = th;
                                w5 w5Var3 = w5.ERROR;
                                i12 = 1;
                                Object[] objArr2 = new Object[1];
                                objArr2[i10] = file.getAbsolutePath();
                                iLogger2.k(w5Var3, th, "Failed processing '%s'", objArr2);
                                w5 w5Var4 = w5.DEBUG;
                                Object[] objArr3 = new Object[i12];
                                objArr3[i10] = str;
                                iLogger.q(w5Var4, "Finished processing cached files from %s", objArr3);
                            }
                        } else {
                            w5 w5Var5 = w5.DEBUG;
                            Object[] objArr4 = new Object[i12];
                            objArr4[i13] = absolutePath;
                            iLogger2.q(w5Var5, "File '%s' has already been processed so it will not be processed again.", objArr4);
                        }
                    } else {
                        w5 w5Var6 = w5.DEBUG;
                        Object[] objArr5 = new Object[i12];
                        objArr5[i13] = file2.getAbsolutePath();
                        iLogger2.q(w5Var6, "File %s is not a File.", objArr5);
                    }
                    i10 = i13;
                    i11 = i14;
                    i14 = i11 + 1;
                    i13 = i10;
                    i12 = 1;
                }
            } else {
                iLogger2.q(w5.ERROR, "Cache dir %s is null or is not a directory.", file.getAbsolutePath());
            }
            i10 = i13;
        } catch (Throwable th2) {
            th = th2;
            i10 = 0;
        }
        w5 w5Var42 = w5.DEBUG;
        Object[] objArr32 = new Object[i12];
        objArr32[i10] = str;
        iLogger.q(w5Var42, "Finished processing cached files from %s", objArr32);
    }

    @Override // androidx.media3.exoplayer.trackselection.DefaultTrackSelector.TrackInfo.Factory
    public List create(int i10, TrackGroup trackGroup, int[] iArr) {
        return DefaultTrackSelector.lambda$selectVideoTrack$1((DefaultTrackSelector.Parameters) this.f2360i, (String) this.f2361l, (int[]) this.f2362m, (Point) this.f2363n, i10, trackGroup, iArr);
    }

    @Override // com.google.android.gms.tasks.d
    public void onFailure(Exception exc) {
        CredentialProviderPlayServicesImpl.onClearCredential$lambda$2((CredentialProviderPlayServicesImpl) this.f2360i, (CancellationSignal) this.f2361l, (Executor) this.f2362m, (CredentialManagerCallback) this.f2363n, exc);
    }
}
