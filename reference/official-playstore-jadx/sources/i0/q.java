package i0;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.SparseArray;
import android.util.TypedValue;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public abstract class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal f16004a = new ThreadLocal();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final WeakHashMap f16005b = new WeakHashMap(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f16006c = new Object();

    public static void a(n nVar, int i10, ColorStateList colorStateList, Resources.Theme theme) {
        synchronized (f16006c) {
            try {
                WeakHashMap weakHashMap = f16005b;
                SparseArray sparseArray = (SparseArray) weakHashMap.get(nVar);
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                    weakHashMap.put(nVar, sparseArray);
                }
                sparseArray.append(i10, new m(colorStateList, nVar.f16002a.getConfiguration(), theme));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static Typeface b(Context context, int i10) {
        if (context.isRestricted()) {
            return null;
        }
        return c(context, i10, new TypedValue(), 0, null, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Typeface c(android.content.Context r12, int r13, android.util.TypedValue r14, int r15, i0.o r16, boolean r17) {
        /*
            Method dump skipped, instruction units count: 248
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: i0.q.c(android.content.Context, int, android.util.TypedValue, int, i0.o, boolean):android.graphics.Typeface");
    }
}
