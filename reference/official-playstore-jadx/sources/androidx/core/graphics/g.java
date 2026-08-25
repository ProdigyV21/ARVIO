package androidx.core.graphics;

import android.content.res.Resources;
import t.r;

/* JADX INFO: loaded from: classes3.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final o f2103a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final r f2104b;

    /* JADX WARN: Removed duplicated region for block: B:18:0x003f  */
    static {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 29
            if (r0 < r1) goto Le
            androidx.core.graphics.l r0 = new androidx.core.graphics.l
            r0.<init>()
            androidx.core.graphics.g.f2103a = r0
            goto L46
        Le:
            r1 = 28
            if (r0 < r1) goto L1a
            androidx.core.graphics.k r0 = new androidx.core.graphics.k
            r0.<init>()
            androidx.core.graphics.g.f2103a = r0
            goto L46
        L1a:
            r1 = 26
            if (r0 < r1) goto L26
            androidx.core.graphics.j r0 = new androidx.core.graphics.j
            r0.<init>()
            androidx.core.graphics.g.f2103a = r0
            goto L46
        L26:
            r1 = 24
            if (r0 < r1) goto L3f
            java.lang.reflect.Method r0 = androidx.core.graphics.i.f2112c
            if (r0 != 0) goto L35
            java.lang.String r1 = "TypefaceCompatApi24Impl"
            java.lang.String r2 = "Unable to collect necessary private methods.Fallback to legacy implementation."
            android.util.Log.w(r1, r2)
        L35:
            if (r0 == 0) goto L3f
            androidx.core.graphics.i r0 = new androidx.core.graphics.i
            r0.<init>()
            androidx.core.graphics.g.f2103a = r0
            goto L46
        L3f:
            androidx.core.graphics.h r0 = new androidx.core.graphics.h
            r0.<init>()
            androidx.core.graphics.g.f2103a = r0
        L46:
            t.r r0 = new t.r
            r1 = 16
            r0.<init>(r1)
            androidx.core.graphics.g.f2104b = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.g.<clinit>():void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Typeface a(android.content.Context r14, i0.f r15, android.content.res.Resources r16, int r17, java.lang.String r18, int r19, int r20, i0.o r21, boolean r22) {
        /*
            Method dump skipped, instruction units count: 402
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.g.a(android.content.Context, i0.f, android.content.res.Resources, int, java.lang.String, int, int, i0.o, boolean):android.graphics.Typeface");
    }

    public static String b(Resources resources, int i10, String str, int i11, int i12) {
        return resources.getResourcePackageName(i10) + '-' + str + '-' + i11 + '-' + i10 + '-' + i12;
    }
}
