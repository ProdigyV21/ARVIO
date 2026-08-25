package androidx.core.app;

import android.os.Build;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public class FrameMetricsAggregator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a1.a f1949a;

    @Retention(RetentionPolicy.SOURCE)
    public @interface a {
    }

    public FrameMetricsAggregator() {
        this(1);
    }

    public FrameMetricsAggregator(int i10) {
        if (Build.VERSION.SDK_INT >= 24) {
            this.f1949a = new o(i10);
        } else {
            this.f1949a = new a1.a();
        }
    }
}
