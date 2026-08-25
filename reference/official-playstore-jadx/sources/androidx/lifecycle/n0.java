package androidx.lifecycle;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

/* JADX INFO: loaded from: classes3.dex */
public final class n0 extends h {
    final /* synthetic */ ProcessLifecycleOwner this$0;

    public static final class a extends h {
        final /* synthetic */ ProcessLifecycleOwner this$0;

        public a(ProcessLifecycleOwner processLifecycleOwner) {
            this.this$0 = processLifecycleOwner;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            this.this$0.a();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity) {
            ProcessLifecycleOwner processLifecycleOwner = this.this$0;
            int i10 = processLifecycleOwner.f3186i + 1;
            processLifecycleOwner.f3186i = i10;
            if (i10 == 1 && processLifecycleOwner.f3189n) {
                processLifecycleOwner.f3191p.c(p.ON_START);
                processLifecycleOwner.f3189n = false;
            }
        }
    }

    public n0(ProcessLifecycleOwner processLifecycleOwner) {
        this.this$0 = processLifecycleOwner;
    }

    @Override // androidx.lifecycle.h, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (Build.VERSION.SDK_INT < 29) {
            int i10 = t0.f3298l;
            ((t0) activity.getFragmentManager().findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag")).f3299i = this.this$0.f3193r;
        }
    }

    @Override // androidx.lifecycle.h, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        ProcessLifecycleOwner processLifecycleOwner = this.this$0;
        int i10 = processLifecycleOwner.f3187l - 1;
        processLifecycleOwner.f3187l = i10;
        if (i10 == 0) {
            processLifecycleOwner.f3190o.postDelayed(processLifecycleOwner.f3192q, 700L);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreCreated(Activity activity, Bundle bundle) {
        m0.a(activity, new a(this.this$0));
    }

    @Override // androidx.lifecycle.h, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        ProcessLifecycleOwner processLifecycleOwner = this.this$0;
        int i10 = processLifecycleOwner.f3186i - 1;
        processLifecycleOwner.f3186i = i10;
        if (i10 == 0 && processLifecycleOwner.f3188m) {
            processLifecycleOwner.f3191p.c(p.ON_STOP);
            processLifecycleOwner.f3189n = true;
        }
    }
}
