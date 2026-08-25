package androidx.emoji2.text;

import android.content.Context;
import androidx.lifecycle.ProcessLifecycleInitializer;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class EmojiCompatInitializer implements f2.b<Boolean> {
    public final void a(Context context) {
        Object objB;
        f2.a aVarC = f2.a.c(context);
        aVarC.getClass();
        synchronized (f2.a.f15247e) {
            try {
                objB = aVarC.f15248a.get(ProcessLifecycleInitializer.class);
                if (objB == null) {
                    objB = aVarC.b(ProcessLifecycleInitializer.class, new HashSet());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        androidx.lifecycle.r lifecycle = ((androidx.lifecycle.y) objB).getLifecycle();
        lifecycle.addObserver(new p(this, lifecycle));
    }

    @Override // f2.b
    public final Object create(Context context) {
        b0 b0Var = new b0(new x(context));
        b0Var.f2612a = 1;
        if (o.k == null) {
            synchronized (o.f2626j) {
                try {
                    if (o.k == null) {
                        o.k = new o(b0Var);
                    }
                } finally {
                }
            }
        }
        a(context);
        return Boolean.TRUE;
    }

    @Override // f2.b
    public final List dependencies() {
        return Collections.singletonList(ProcessLifecycleInitializer.class);
    }
}
