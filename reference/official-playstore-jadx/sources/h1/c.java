package h1;

import android.util.Log;
import androidx.fragment.app.b1;
import androidx.fragment.app.c0;
import androidx.fragment.app.strictmode.FragmentReuseViolation;
import androidx.fragment.app.strictmode.Violation;

/* JADX INFO: loaded from: classes3.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f15839a = b.f15838a;

    public static b a(c0 c0Var) {
        while (c0Var != null) {
            if (c0Var.isAdded()) {
                c0Var.getParentFragmentManager();
            }
            c0Var = c0Var.getParentFragment();
        }
        return f15839a;
    }

    public static void b(Violation violation) {
        if (b1.G(3)) {
            Log.d("FragmentManager", "StrictMode violation in ".concat(violation.f2841i.getClass().getName()), violation);
        }
    }

    public static final void c(c0 c0Var, String str) {
        b(new FragmentReuseViolation(c0Var, "Attempting to reuse fragment " + c0Var + " with previous ID " + str));
        a(c0Var).getClass();
    }
}
