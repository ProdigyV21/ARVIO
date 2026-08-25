package androidx.fragment.app;

import android.util.Log;
import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public final class w implements q.a, androidx.lifecycle.k0, androidx.core.os.f {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2852i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f2853l;

    public /* synthetic */ w(Object obj, int i10) {
        this.f2852i = i10;
        this.f2853l = obj;
    }

    @Override // q.a
    public Object apply() {
        switch (this.f2852i) {
            case 0:
                c0 c0Var = (c0) this.f2853l;
                Object obj = c0Var.mHost;
                return obj instanceof androidx.activity.result.l ? ((androidx.activity.result.l) obj).getActivityResultRegistry() : c0Var.requireActivity().getActivityResultRegistry();
            default:
                return (androidx.activity.result.k) this.f2853l;
        }
    }

    @Override // androidx.lifecycle.k0
    public void c(Object obj) {
        androidx.lifecycle.y yVar = (androidx.lifecycle.y) obj;
        r rVar = (r) this.f2853l;
        if (yVar == null || !rVar.mShowsDialog) {
            return;
        }
        View viewRequireView = rVar.requireView();
        if (viewRequireView.getParent() != null) {
            throw new IllegalStateException("DialogFragment can not be attached to a container view");
        }
        if (rVar.mDialog != null) {
            if (b1.G(3)) {
                Log.d("FragmentManager", "DialogFragment " + this + " setting the content view on " + rVar.mDialog);
            }
            rVar.mDialog.setContentView(viewRequireView);
        }
    }

    @Override // androidx.core.os.f
    public void onCancel() {
        ((z1) this.f2853l).a();
    }
}
