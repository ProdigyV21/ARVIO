package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
public class t0 extends Fragment {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ int f3298l = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public o0 f3299i;

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\bf\u0018\u00002\u00020\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0002À\u0006\u0001"}, d2 = {"Landroidx/lifecycle/t0$a;", "", "lifecycle-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface a {
    }

    public static final class b implements Application.ActivityLifecycleCallbacks {
        public static final v0 Companion = new v0();

        @q7.n
        public static final void registerIn(Activity activity) {
            Companion.getClass();
            v0.a(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostCreated(Activity activity, Bundle bundle) {
            int i10 = t0.f3298l;
            u0.a(activity, p.ON_CREATE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            int i10 = t0.f3298l;
            u0.a(activity, p.ON_RESUME);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity) {
            int i10 = t0.f3298l;
            u0.a(activity, p.ON_START);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreDestroyed(Activity activity) {
            int i10 = t0.f3298l;
            u0.a(activity, p.ON_DESTROY);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPrePaused(Activity activity) {
            int i10 = t0.f3298l;
            u0.a(activity, p.ON_PAUSE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreStopped(Activity activity) {
            int i10 = t0.f3298l;
            u0.a(activity, p.ON_STOP);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    public final void a(p pVar) {
        if (Build.VERSION.SDK_INT < 29) {
            u0.a(getActivity(), pVar);
        }
    }

    @Override // android.app.Fragment
    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a(p.ON_CREATE);
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        a(p.ON_DESTROY);
        this.f3299i = null;
    }

    @Override // android.app.Fragment
    public final void onPause() {
        super.onPause();
        a(p.ON_PAUSE);
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        o0 o0Var = this.f3299i;
        if (o0Var != null) {
            o0Var.f3261a.a();
        }
        a(p.ON_RESUME);
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        o0 o0Var = this.f3299i;
        if (o0Var != null) {
            ProcessLifecycleOwner processLifecycleOwner = o0Var.f3261a;
            int i10 = processLifecycleOwner.f3186i + 1;
            processLifecycleOwner.f3186i = i10;
            if (i10 == 1 && processLifecycleOwner.f3189n) {
                processLifecycleOwner.f3191p.c(p.ON_START);
                processLifecycleOwner.f3189n = false;
            }
        }
        a(p.ON_START);
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        a(p.ON_STOP);
    }
}
