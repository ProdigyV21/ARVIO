package androidx.fragment.app;

import android.transition.Transition;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class s1 implements Transition.TransitionListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Object f2837a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ArrayList f2838b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f2839c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ ArrayList f2840d;

    public s1(u1 u1Var, Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2) {
        this.f2837a = obj;
        this.f2838b = arrayList;
        this.f2839c = obj2;
        this.f2840d = arrayList2;
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionCancel(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionEnd(Transition transition) {
        transition.removeListener(this);
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionPause(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionResume(Transition transition) {
    }

    @Override // android.transition.Transition.TransitionListener
    public final void onTransitionStart(Transition transition) {
        Object obj = this.f2837a;
        if (obj != null) {
            u1.d(obj, this.f2838b, null);
        }
        Object obj2 = this.f2839c;
        if (obj2 != null) {
            u1.d(obj2, this.f2840d, null);
        }
    }
}
