package androidx.fragment.app;

import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public abstract class p1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final u1 f2822a = new u1();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final u1 f2823b;

    static {
        u1 u1Var = null;
        try {
            u1Var = (u1) Class.forName("androidx.transition.FragmentTransitionSupport").getDeclaredConstructor(null).newInstance(null);
        } catch (Exception unused) {
        }
        f2823b = u1Var;
    }

    public static void a(int i10, ArrayList arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((View) arrayList.get(size)).setVisibility(i10);
        }
    }
}
