package androidx.core.view;

import android.os.Build;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.WindowInsetsAnimation$Callback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class y2 extends WindowInsetsAnimation$Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t2 f2334a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List f2335b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayList f2336c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashMap f2337d;

    public y2(t2 t2Var) {
        super(t2Var.getDispatchMode());
        this.f2337d = new HashMap();
        this.f2334a = t2Var;
    }

    public final b3 a(WindowInsetsAnimation windowInsetsAnimation) {
        b3 b3Var = (b3) this.f2337d.get(windowInsetsAnimation);
        if (b3Var == null) {
            b3Var = new b3(0, null, 0L);
            if (Build.VERSION.SDK_INT >= 30) {
                b3Var.f2205a = new z2(windowInsetsAnimation);
            }
            this.f2337d.put(windowInsetsAnimation, b3Var);
        }
        return b3Var;
    }

    public final void onEnd(WindowInsetsAnimation windowInsetsAnimation) {
        this.f2334a.onEnd(a(windowInsetsAnimation));
        this.f2337d.remove(windowInsetsAnimation);
    }

    public final void onPrepare(WindowInsetsAnimation windowInsetsAnimation) {
        this.f2334a.onPrepare(a(windowInsetsAnimation));
    }

    public final WindowInsets onProgress(WindowInsets windowInsets, List list) {
        ArrayList arrayList = this.f2336c;
        if (arrayList == null) {
            ArrayList arrayList2 = new ArrayList(list.size());
            this.f2336c = arrayList2;
            this.f2335b = Collections.unmodifiableList(arrayList2);
        } else {
            arrayList.clear();
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            WindowInsetsAnimation windowInsetsAnimation = (WindowInsetsAnimation) list.get(size);
            b3 b3VarA = a(windowInsetsAnimation);
            b3VarA.f2205a.c(windowInsetsAnimation.getFraction());
            this.f2336c.add(b3VarA);
        }
        return this.f2334a.onProgress(q3.c(null, windowInsets), this.f2335b).b();
    }

    public final WindowInsetsAnimation.Bounds onStart(WindowInsetsAnimation windowInsetsAnimation, WindowInsetsAnimation.Bounds bounds) {
        r2 r2VarOnStart = this.f2334a.onStart(a(windowInsetsAnimation), new r2(bounds));
        r2VarOnStart.getClass();
        x2.c();
        return x2.a(r2VarOnStart.f2309a.d(), r2VarOnStart.f2310b.d());
    }
}
