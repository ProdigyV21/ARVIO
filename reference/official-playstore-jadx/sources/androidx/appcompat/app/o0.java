package androidx.appcompat.app;

import android.content.IntentFilter;
import androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes.dex */
public abstract class o0 implements q9.f {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f1145i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f1146l;

    public o0() {
        this.f1145i = new CopyOnWriteArraySet();
        this.f1146l = new CopyOnWriteArraySet();
    }

    public static /* synthetic */ void c(int i10) {
        String str = (i10 == 1 || i10 == 2) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 1 || i10 == 2) ? 2 : 3];
        if (i10 == 1 || i10 == 2) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/AbstractReceiverValue";
        } else {
            objArr[0] = "receiverType";
        }
        if (i10 == 1) {
            objArr[1] = "getType";
        } else if (i10 != 2) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/AbstractReceiverValue";
        } else {
            objArr[1] = "getOriginal";
        }
        if (i10 != 1 && i10 != 2) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i10 != 1 && i10 != 2) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public void d(String str) {
        ((CopyOnWriteArraySet) this.f1145i).add(str);
        ((CopyOnWriteArraySet) this.f1146l).remove(str);
    }

    public void e() {
        n0 n0Var = (n0) this.f1145i;
        if (n0Var != null) {
            try {
                ((s0) this.f1146l).f1189u.unregisterReceiver(n0Var);
            } catch (IllegalArgumentException unused) {
            }
            this.f1145i = null;
        }
    }

    public abstract IntentFilter f();

    public abstract int g();

    @Override // q9.f
    public v9.w getType() {
        v9.w wVar = (v9.w) this.f1145i;
        if (wVar != null) {
            return wVar;
        }
        c(1);
        throw null;
    }

    public abstract void h();

    public void i(boolean z) {
        CopyOnWriteArraySet copyOnWriteArraySet = (CopyOnWriteArraySet) this.f1146l;
        CopyOnWriteArraySet copyOnWriteArraySet2 = (CopyOnWriteArraySet) this.f1145i;
        if (z) {
            copyOnWriteArraySet2.add("android.widget.ImageView");
            copyOnWriteArraySet.remove("android.widget.ImageView");
        } else {
            copyOnWriteArraySet.add("android.widget.ImageView");
            copyOnWriteArraySet2.remove("android.widget.ImageView");
        }
    }

    public void j(boolean z) {
        CopyOnWriteArraySet copyOnWriteArraySet = (CopyOnWriteArraySet) this.f1146l;
        CopyOnWriteArraySet copyOnWriteArraySet2 = (CopyOnWriteArraySet) this.f1145i;
        if (z) {
            copyOnWriteArraySet2.add(AndroidComposeViewAccessibilityDelegateCompat.TextClassName);
            copyOnWriteArraySet.remove(AndroidComposeViewAccessibilityDelegateCompat.TextClassName);
        } else {
            copyOnWriteArraySet.add(AndroidComposeViewAccessibilityDelegateCompat.TextClassName);
            copyOnWriteArraySet2.remove(AndroidComposeViewAccessibilityDelegateCompat.TextClassName);
        }
    }

    public void k() {
        e();
        IntentFilter intentFilterF = f();
        if (intentFilterF.countActions() == 0) {
            return;
        }
        if (((n0) this.f1145i) == null) {
            this.f1145i = new n0(this, 0);
        }
        ((s0) this.f1146l).f1189u.registerReceiver((n0) this.f1145i, intentFilterF);
    }

    public abstract void l();

    public o0(v9.w wVar, q9.f fVar) {
        if (wVar != null) {
            this.f1145i = wVar;
            this.f1146l = fVar == null ? this : fVar;
        } else {
            c(0);
            throw null;
        }
    }

    public o0(s0 s0Var) {
        this.f1146l = s0Var;
    }
}
