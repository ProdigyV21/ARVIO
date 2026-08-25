package androidx.media3.datasource;

import android.graphics.Bitmap;
import android.net.Uri;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import io.sentry.ILogger;
import io.sentry.android.core.ScreenshotEventProcessor;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.android.core.s0;
import io.sentry.android.core.u0;
import io.sentry.w5;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class c implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3450a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f3451b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f3452c;

    public /* synthetic */ c(Object obj, Object obj2, int i10) {
        this.f3450a = i10;
        this.f3451b = obj;
        this.f3452c = obj2;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        switch (this.f3450a) {
            case 0:
                return ((DataSourceBitmapLoader) this.f3451b).lambda$decodeBitmap$1((byte[]) this.f3452c);
            case 1:
                return ((DataSourceBitmapLoader) this.f3451b).lambda$loadBitmap$2((Uri) this.f3452c);
            case 2:
                return ((UserMetadata) this.f3451b).lambda$updateRolloutsState$1((List) this.f3452c);
            case 3:
                s0 s0Var = (s0) this.f3451b;
                return u0.c(s0Var.f16738i, (SentryAndroidOptions) this.f3452c);
            default:
                ScreenshotEventProcessor screenshotEventProcessor = (ScreenshotEventProcessor) this.f3451b;
                Bitmap bitmap = (Bitmap) this.f3452c;
                ILogger logger = screenshotEventProcessor.f16352i.getLogger();
                byte[] bArr = null;
                if (!bitmap.isRecycled()) {
                    try {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
                            bitmap.recycle();
                            if (byteArrayOutputStream.size() <= 0) {
                                logger.q(w5.DEBUG, "Screenshot is 0 bytes, not attaching the image.", new Object[0]);
                                byteArrayOutputStream.close();
                            } else {
                                byte[] byteArray = byteArrayOutputStream.toByteArray();
                                byteArrayOutputStream.close();
                                bArr = byteArray;
                            }
                        } finally {
                            try {
                                break;
                            } catch (Throwable th) {
                            }
                        }
                    } catch (Throwable th2) {
                        logger.l(w5.ERROR, "Compressing bitmap failed.", th2);
                    }
                    break;
                }
                return bArr;
        }
    }
}
