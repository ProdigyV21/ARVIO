package androidx.lifecycle;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Landroidx/lifecycle/ProcessLifecycleInitializer;", "Lf2/b;", "Landroidx/lifecycle/y;", "<init>", "()V", "lifecycle-process_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ProcessLifecycleInitializer implements f2.b<y> {
    @Override // f2.b
    public final Object create(Context context) {
        if (!f2.a.c(context).f15249b.contains(ProcessLifecycleInitializer.class)) {
            throw new IllegalStateException("ProcessLifecycleInitializer cannot be initialized lazily.\n               Please ensure that you have:\n               <meta-data\n                   android:name='androidx.lifecycle.ProcessLifecycleInitializer'\n                   android:value='androidx.startup' />\n               under InitializationProvider in your AndroidManifest.xml");
        }
        if (!v.f3300a.getAndSet(true)) {
            ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new u());
        }
        ProcessLifecycleOwner processLifecycleOwner = ProcessLifecycleOwner.f3185s;
        processLifecycleOwner.getClass();
        processLifecycleOwner.f3190o = new Handler();
        processLifecycleOwner.f3191p.c(p.ON_CREATE);
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new n0(processLifecycleOwner));
        return processLifecycleOwner;
    }

    @Override // f2.b
    public final List dependencies() {
        return kotlin.collections.z.f19728i;
    }
}
