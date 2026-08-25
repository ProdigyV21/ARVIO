package androidx.fragment.app;

import android.view.View;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class k1 implements View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ View f2767i;

    public k1(View view) {
        this.f2767i = view;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        View view2 = this.f2767i;
        view2.removeOnAttachStateChangeListener(this);
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        androidx.core.view.i1.c(view2);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
    }
}
