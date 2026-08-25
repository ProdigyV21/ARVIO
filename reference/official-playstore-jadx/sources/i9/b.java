package i9;

import android.graphics.Bitmap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import kotlin.jvm.internal.k0;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16244i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f16245l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f16246m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(Object obj, Object obj2, int i10) {
        super(2);
        this.f16244i = i10;
        this.f16245l = obj;
        this.f16246m = obj2;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) throws IOException {
        switch (this.f16244i) {
            case 0:
                return Boolean.valueOf(kotlin.jvm.internal.p.a((g8.k) obj, (g8.a) this.f16245l) && kotlin.jvm.internal.p.a((g8.k) obj2, (g8.a) this.f16246m));
            default:
                io.sentry.android.replay.m mVar = (io.sentry.android.replay.m) obj;
                long jLongValue = ((Number) obj2).longValue();
                Bitmap bitmap = (Bitmap) this.f16245l;
                String str = (String) ((k0) this.f16246m).f19746i;
                if (mVar.k() != null && !bitmap.isRecycled()) {
                    File fileK = mVar.k();
                    if (fileK != null) {
                        fileK.mkdirs();
                    }
                    File file = new File(mVar.k(), jLongValue + ".jpg");
                    file.createNewFile();
                    synchronized (bitmap) {
                        if (!bitmap.isRecycled()) {
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            try {
                                bitmap.compress(Bitmap.CompressFormat.JPEG, mVar.f16979i.getSessionReplay().f17219p.screenshotQuality, fileOutputStream);
                                fileOutputStream.flush();
                                fileOutputStream.close();
                                mVar.i(file, str, jLongValue);
                            } finally {
                            }
                        }
                    }
                }
                return t0.f22605a;
        }
    }
}
