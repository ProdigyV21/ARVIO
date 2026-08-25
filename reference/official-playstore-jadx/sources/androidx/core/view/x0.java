package androidx.core.view;

import android.os.Build;
import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public final class x0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f2333a;

    public x0(View view) {
        if (Build.VERSION.SDK_INT < 30) {
            this.f2333a = new f(view);
            return;
        }
        w0 w0Var = new w0(view);
        w0Var.f2327b = view;
        this.f2333a = w0Var;
    }
}
