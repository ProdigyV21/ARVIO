package y2;

import android.os.Handler;
import android.os.Looper;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes4.dex */
public final class b extends r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final b f22874i = new b(0);

    @Override // r7.a
    public final Object invoke() {
        return new Handler(Looper.getMainLooper());
    }
}
