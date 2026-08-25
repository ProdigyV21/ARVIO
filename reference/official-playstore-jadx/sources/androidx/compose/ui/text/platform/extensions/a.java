package androidx.compose.ui.text.platform.extensions;

import android.media.MediaCodec;
import android.os.Build;
import android.os.Handler;
import android.os.LocaleList;
import android.text.style.LocaleSpan;
import android.view.Window;
import android.view.Window$OnFrameMetricsAvailableListener;
import com.google.android.gms.internal.cast.v9;
import com.google.android.gms.internal.cast.y9;
import com.google.common.util.concurrent.i1;
import com.google.common.util.concurrent.l1;
import com.google.firebase.concurrent.f;
import io.sentry.transport.n;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class a {
    public static void a(Window window, Window$OnFrameMetricsAvailableListener window$OnFrameMetricsAvailableListener, Handler handler) {
        if (window$OnFrameMetricsAvailableListener == null) {
            return;
        }
        window.addOnFrameMetricsAvailableListener(window$OnFrameMetricsAvailableListener, handler);
    }

    public static void b(Window window, Window$OnFrameMetricsAvailableListener window$OnFrameMetricsAvailableListener) {
        if (window$OnFrameMetricsAvailableListener == null) {
            return;
        }
        window.removeOnFrameMetricsAvailableListener(window$OnFrameMetricsAvailableListener);
    }

    public static /* synthetic */ MediaCodec.CryptoInfo.Pattern c() {
        return new MediaCodec.CryptoInfo.Pattern(0, 0);
    }

    public static /* synthetic */ LocaleList d(Locale[] localeArr) {
        return new LocaleList(localeArr);
    }

    public static /* synthetic */ LocaleSpan e(LocaleList localeList) {
        return new LocaleSpan(localeList);
    }

    public static void f(v9 v9Var) {
        boolean zIsTerminated;
        ExecutorService executorService = v9Var.f13645i;
        if ((Build.VERSION.SDK_INT <= 23 || v9Var != ForkJoinPool.commonPool()) && !(zIsTerminated = executorService.isTerminated())) {
            v9Var.shutdown();
            boolean z = false;
            while (!zIsTerminated) {
                try {
                    zIsTerminated = executorService.awaitTermination(1L, TimeUnit.DAYS);
                } catch (InterruptedException unused) {
                    if (!z) {
                        v9Var.shutdownNow();
                        z = true;
                    }
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void g(y9 y9Var) {
        boolean zIsTerminated;
        ExecutorService executorService = y9Var.f13645i;
        if ((Build.VERSION.SDK_INT <= 23 || y9Var != ForkJoinPool.commonPool()) && !(zIsTerminated = executorService.isTerminated())) {
            y9Var.shutdown();
            boolean z = false;
            while (!zIsTerminated) {
                try {
                    zIsTerminated = executorService.awaitTermination(1L, TimeUnit.DAYS);
                } catch (InterruptedException unused) {
                    if (!z) {
                        y9Var.shutdownNow();
                        z = true;
                    }
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void h(i1 i1Var) {
        boolean zIsTerminated;
        ExecutorService executorService = i1Var.f14207i;
        if ((Build.VERSION.SDK_INT <= 23 || i1Var != ForkJoinPool.commonPool()) && !(zIsTerminated = executorService.isTerminated())) {
            i1Var.shutdown();
            boolean z = false;
            while (!zIsTerminated) {
                try {
                    zIsTerminated = executorService.awaitTermination(1L, TimeUnit.DAYS);
                } catch (InterruptedException unused) {
                    if (!z) {
                        i1Var.shutdownNow();
                        z = true;
                    }
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void i(l1 l1Var) {
        boolean zIsTerminated;
        ExecutorService executorService = l1Var.f14207i;
        if ((Build.VERSION.SDK_INT <= 23 || l1Var != ForkJoinPool.commonPool()) && !(zIsTerminated = executorService.isTerminated())) {
            l1Var.shutdown();
            boolean z = false;
            while (!zIsTerminated) {
                try {
                    zIsTerminated = executorService.awaitTermination(1L, TimeUnit.DAYS);
                } catch (InterruptedException unused) {
                    if (!z) {
                        l1Var.shutdownNow();
                        z = true;
                    }
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void j(f fVar) {
        if ((Build.VERSION.SDK_INT <= 23 || fVar != ForkJoinPool.commonPool()) && !fVar.f14332i.isTerminated()) {
            fVar.shutdown();
            throw null;
        }
    }

    public static void k(io.sentry.android.replay.util.f fVar) {
        boolean zIsTerminated;
        ScheduledExecutorService scheduledExecutorService = fVar.f17050i;
        if ((Build.VERSION.SDK_INT <= 23 || fVar != ForkJoinPool.commonPool()) && !(zIsTerminated = scheduledExecutorService.isTerminated())) {
            fVar.shutdown();
            boolean z = false;
            while (!zIsTerminated) {
                try {
                    zIsTerminated = scheduledExecutorService.awaitTermination(1L, TimeUnit.DAYS);
                } catch (InterruptedException unused) {
                    if (!z) {
                        fVar.shutdownNow();
                        z = true;
                    }
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static /* synthetic */ void l(n nVar) {
        boolean zIsTerminated;
        if ((Build.VERSION.SDK_INT <= 23 || nVar != ForkJoinPool.commonPool()) && !(zIsTerminated = nVar.isTerminated())) {
            nVar.shutdown();
            boolean z = false;
            while (!zIsTerminated) {
                try {
                    zIsTerminated = nVar.awaitTermination(1L, TimeUnit.DAYS);
                } catch (InterruptedException unused) {
                    if (!z) {
                        nVar.shutdownNow();
                        z = true;
                    }
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static /* synthetic */ void m(ExecutorService executorService) {
        boolean zIsTerminated;
        if ((Build.VERSION.SDK_INT <= 23 || executorService != ForkJoinPool.commonPool()) && !(zIsTerminated = executorService.isTerminated())) {
            executorService.shutdown();
            boolean z = false;
            while (!zIsTerminated) {
                try {
                    zIsTerminated = executorService.awaitTermination(1L, TimeUnit.DAYS);
                } catch (InterruptedException unused) {
                    if (!z) {
                        executorService.shutdownNow();
                        z = true;
                    }
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
