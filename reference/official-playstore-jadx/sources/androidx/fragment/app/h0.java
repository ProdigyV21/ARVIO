package androidx.fragment.app;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import androidx.core.app.d2;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes3.dex */
public abstract class h0 extends androidx.activity.t implements androidx.core.app.e, androidx.core.app.g {
    static final String LIFECYCLE_TAG = "android:support:lifecycle";
    boolean mCreated;
    boolean mResumed;
    final l0 mFragments = new l0(new g0(this));
    final androidx.lifecycle.a0 mFragmentLifecycleRegistry = new androidx.lifecycle.a0(this);
    boolean mStopped = true;

    public h0() {
        getSavedStateRegistry().c(LIFECYCLE_TAG, new d0(this, 0));
        final int i10 = 0;
        addOnConfigurationChangedListener(new p0.a(this) { // from class: androidx.fragment.app.e0

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ h0 f2714b;

            {
                this.f2714b = this;
            }

            @Override // p0.a
            public final void accept(Object obj) {
                switch (i10) {
                    case 0:
                        this.f2714b.mFragments.a();
                        break;
                    default:
                        this.f2714b.mFragments.a();
                        break;
                }
            }
        });
        final int i11 = 1;
        addOnNewIntentListener(new p0.a(this) { // from class: androidx.fragment.app.e0

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ h0 f2714b;

            {
                this.f2714b = this;
            }

            @Override // p0.a
            public final void accept(Object obj) {
                switch (i11) {
                    case 0:
                        this.f2714b.mFragments.a();
                        break;
                    default:
                        this.f2714b.mFragments.a();
                        break;
                }
            }
        });
        addOnContextAvailableListener(new f.c() { // from class: androidx.fragment.app.f0
            @Override // f.c
            public final void onContextAvailable(Context context) {
                g0 g0Var = this.f2728a.mFragments.f2770a;
                g0Var.f2782n.b(g0Var, g0Var, null);
            }
        });
    }

    public static boolean d(b1 b1Var) {
        boolean zD = false;
        for (c0 c0Var : b1Var.f2669c.f()) {
            if (c0Var != null) {
                if (c0Var.getHost() != null) {
                    zD |= d(c0Var.getChildFragmentManager());
                }
                w1 w1Var = c0Var.mViewLifecycleOwner;
                androidx.lifecycle.q qVar = androidx.lifecycle.q.f3268m;
                androidx.lifecycle.q qVar2 = androidx.lifecycle.q.f3269n;
                if (w1Var != null) {
                    w1Var.b();
                    if (w1Var.f2858n.f3198c.a(qVar2)) {
                        c0Var.mViewLifecycleOwner.f2858n.e(qVar);
                        zD = true;
                    }
                }
                if (c0Var.mLifecycleRegistry.f3198c.a(qVar2)) {
                    c0Var.mLifecycleRegistry.e(qVar);
                    zD = true;
                }
            }
        }
        return zD;
    }

    public final View dispatchFragmentsOnCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return this.mFragments.f2770a.f2782n.f2672f.onCreateView(view, str, context, attributeSet);
    }

    @Override // android.app.Activity
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        if (shouldDumpInternalState(strArr)) {
            printWriter.print(str);
            printWriter.print("Local FragmentActivity ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(" State:");
            String str2 = str + "  ";
            printWriter.print(str2);
            printWriter.print("mCreated=");
            printWriter.print(this.mCreated);
            printWriter.print(" mResumed=");
            printWriter.print(this.mResumed);
            printWriter.print(" mStopped=");
            printWriter.print(this.mStopped);
            if (getApplication() != null) {
                androidx.loader.app.b.a(this).c(str2, fileDescriptor, printWriter, strArr);
            }
            this.mFragments.f2770a.f2782n.u(str, fileDescriptor, printWriter, strArr);
        }
    }

    public b1 getSupportFragmentManager() {
        return this.mFragments.f2770a.f2782n;
    }

    @Deprecated
    public androidx.loader.app.b getSupportLoaderManager() {
        return androidx.loader.app.b.a(this);
    }

    public void markFragmentsCreated() {
        while (d(getSupportFragmentManager())) {
        }
    }

    @Override // androidx.activity.t, android.app.Activity
    public void onActivityResult(int i10, int i11, Intent intent) {
        this.mFragments.a();
        super.onActivityResult(i10, i11, intent);
    }

    @Deprecated
    public void onAttachFragment(c0 c0Var) {
    }

    @Override // androidx.activity.t, androidx.core.app.m, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFragmentLifecycleRegistry.c(androidx.lifecycle.p.ON_CREATE);
        c1 c1Var = this.mFragments.f2770a.f2782n;
        c1Var.F = false;
        c1Var.G = false;
        c1Var.M.setIsStateSaved(false);
        c1Var.t(1);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewDispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(view, str, context, attributeSet);
        return viewDispatchFragmentsOnCreateView == null ? super.onCreateView(view, str, context, attributeSet) : viewDispatchFragmentsOnCreateView;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.mFragments.f2770a.f2782n.k();
        this.mFragmentLifecycleRegistry.c(androidx.lifecycle.p.ON_DESTROY);
    }

    @Override // androidx.activity.t, android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i10, MenuItem menuItem) {
        if (super.onMenuItemSelected(i10, menuItem)) {
            return true;
        }
        if (i10 == 6) {
            return this.mFragments.f2770a.f2782n.i(menuItem);
        }
        return false;
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.mResumed = false;
        this.mFragments.f2770a.f2782n.t(5);
        this.mFragmentLifecycleRegistry.c(androidx.lifecycle.p.ON_PAUSE);
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        onResumeFragments();
    }

    @Override // androidx.activity.t, android.app.Activity
    public void onRequestPermissionsResult(int i10, String[] strArr, int[] iArr) {
        this.mFragments.a();
        super.onRequestPermissionsResult(i10, strArr, iArr);
    }

    @Override // android.app.Activity
    public void onResume() {
        this.mFragments.a();
        super.onResume();
        this.mResumed = true;
        this.mFragments.f2770a.f2782n.x(true);
    }

    public void onResumeFragments() {
        this.mFragmentLifecycleRegistry.c(androidx.lifecycle.p.ON_RESUME);
        c1 c1Var = this.mFragments.f2770a.f2782n;
        c1Var.F = false;
        c1Var.G = false;
        c1Var.M.setIsStateSaved(false);
        c1Var.t(7);
    }

    @Override // android.app.Activity
    public void onStart() {
        this.mFragments.a();
        super.onStart();
        this.mStopped = false;
        if (!this.mCreated) {
            this.mCreated = true;
            c1 c1Var = this.mFragments.f2770a.f2782n;
            c1Var.F = false;
            c1Var.G = false;
            c1Var.M.setIsStateSaved(false);
            c1Var.t(4);
        }
        this.mFragments.f2770a.f2782n.x(true);
        this.mFragmentLifecycleRegistry.c(androidx.lifecycle.p.ON_START);
        c1 c1Var2 = this.mFragments.f2770a.f2782n;
        c1Var2.F = false;
        c1Var2.G = false;
        c1Var2.M.setIsStateSaved(false);
        c1Var2.t(5);
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        this.mFragments.a();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        this.mStopped = true;
        markFragmentsCreated();
        c1 c1Var = this.mFragments.f2770a.f2782n;
        c1Var.G = true;
        c1Var.M.setIsStateSaved(true);
        c1Var.t(4);
        this.mFragmentLifecycleRegistry.c(androidx.lifecycle.p.ON_STOP);
    }

    public void setEnterSharedElementCallback(d2 d2Var) {
        androidx.core.app.a.c(this, null);
    }

    public void setExitSharedElementCallback(d2 d2Var) {
        androidx.core.app.a.d(this, null);
    }

    public void startActivityFromFragment(c0 c0Var, Intent intent, int i10, Bundle bundle) {
        if (i10 == -1) {
            startActivityForResult(intent, -1, bundle);
        } else {
            c0Var.startActivityForResult(intent, i10, bundle);
        }
    }

    @Deprecated
    public void startIntentSenderFromFragment(c0 c0Var, IntentSender intentSender, int i10, Intent intent, int i11, int i12, int i13, Bundle bundle) throws IntentSender.SendIntentException {
        if (i10 == -1) {
            startIntentSenderForResult(intentSender, i10, intent, i11, i12, i13, bundle);
        } else {
            c0Var.startIntentSenderForResult(intentSender, i10, intent, i11, i12, i13, bundle);
        }
    }

    public void supportFinishAfterTransition() {
        androidx.core.app.a.a(this);
    }

    @Deprecated
    public void supportInvalidateOptionsMenu() {
        invalidateMenu();
    }

    public void supportPostponeEnterTransition() {
        androidx.core.app.a.b(this);
    }

    public void supportStartPostponedEnterTransition() {
        androidx.core.app.a.e(this);
    }

    @Override // androidx.core.app.g
    @Deprecated
    public final void validateRequestPermissionsRequestCode(int i10) {
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View viewDispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(null, str, context, attributeSet);
        return viewDispatchFragmentsOnCreateView == null ? super.onCreateView(str, context, attributeSet) : viewDispatchFragmentsOnCreateView;
    }

    public void startActivityFromFragment(c0 c0Var, Intent intent, int i10) {
        startActivityFromFragment(c0Var, intent, i10, (Bundle) null);
    }
}
