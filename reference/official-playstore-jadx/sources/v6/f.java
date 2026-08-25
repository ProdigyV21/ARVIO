package v6;

import android.graphics.Bitmap;
import java.security.SecureRandom;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes5.dex */
public final class f extends r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final f f22174i = new f(0);

    @Override // r7.a
    public final Object invoke() {
        int i10 = c.f22162a;
        try {
            Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } catch (Throwable unused) {
        }
        return new SecureRandom();
    }
}
