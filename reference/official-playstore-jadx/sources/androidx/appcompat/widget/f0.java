package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.internal.cast.b6;
import com.google.android.gms.internal.cast.c6;
import com.google.android.gms.internal.cast.z4;
import j$.util.DesugarCollections;
import java.io.File;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class f0 implements com.google.android.gms.tasks.e {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1565i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1566l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f1567m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f1568n;

    public /* synthetic */ f0(int i10) {
        this.f1565i = i10;
    }

    public static void c(String str) {
        if (kotlin.text.u.L(str, ":memory:", true)) {
            return;
        }
        int length = str.length() - 1;
        int i10 = 0;
        boolean z = false;
        while (i10 <= length) {
            boolean z5 = kotlin.jvm.internal.p.c(str.charAt(!z ? i10 : length), 32) <= 0;
            if (z) {
                if (!z5) {
                    break;
                } else {
                    length--;
                }
            } else if (z5) {
                i10++;
            } else {
                z = true;
            }
        }
        if (str.subSequence(i10, length + 1).toString().length() == 0) {
            return;
        }
        Log.w("SupportSQLite", "deleting the database file: ".concat(str));
        try {
            SQLiteDatabase.deleteDatabase(new File(str));
        } catch (Exception e5) {
            Log.w("SupportSQLite", "delete failed: ", e5);
        }
    }

    public void a() {
        g4 g4Var;
        ImageView imageView = (ImageView) this.f1567m;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            z1.a(drawable);
        }
        if (drawable == null || (g4Var = (g4) this.f1568n) == null) {
            return;
        }
        a0.d(drawable, g4Var, imageView.getDrawableState());
    }

    public p5.b b() {
        if ("".isEmpty()) {
            return new p5.b((String) this.f1567m, ((Long) this.f1568n).longValue(), this.f1566l);
        }
        throw new IllegalStateException("Missing required properties:".concat(""));
    }

    public String d() {
        StringBuilder sb2 = new StringBuilder("$");
        int i10 = this.f1566l + 1;
        for (int i11 = 0; i11 < i10; i11++) {
            Object obj = ((Object[]) this.f1567m)[i11];
            if (obj instanceof ab.g) {
                ab.g gVar = (ab.g) obj;
                if (!kotlin.jvm.internal.p.a(gVar.getKind(), ab.n.f819c)) {
                    int i12 = ((int[]) this.f1568n)[i11];
                    if (i12 >= 0) {
                        sb2.append(".");
                        sb2.append(gVar.e(i12));
                    }
                } else if (((int[]) this.f1568n)[i11] != -1) {
                    sb2.append("[");
                    sb2.append(((int[]) this.f1568n)[i11]);
                    sb2.append("]");
                }
            } else if (obj != eb.s.f15174a) {
                sb2.append("['");
                sb2.append(obj);
                sb2.append("']");
            }
        }
        return sb2.toString();
    }

    public void e(AttributeSet attributeSet, int i10) {
        int resourceId;
        ImageView imageView = (ImageView) this.f1567m;
        Context context = imageView.getContext();
        int[] iArr = j.a.f19022f;
        androidx.appcompat.app.i1 i1VarZ = androidx.appcompat.app.i1.Z(context, attributeSet, iArr, i10);
        TypedArray typedArray = (TypedArray) i1VarZ.f1060m;
        androidx.core.view.b2.g(imageView, imageView.getContext(), iArr, attributeSet, (TypedArray) i1VarZ.f1060m, i10);
        try {
            Drawable drawable = imageView.getDrawable();
            if (drawable == null && (resourceId = typedArray.getResourceId(1, -1)) != -1 && (drawable = m2.f0.o(imageView.getContext(), resourceId)) != null) {
                imageView.setImageDrawable(drawable);
            }
            if (drawable != null) {
                z1.a(drawable);
            }
            if (typedArray.hasValue(2)) {
                u0.g.c(imageView, i1VarZ.S(2));
            }
            if (typedArray.hasValue(3)) {
                u0.g.d(imageView, z1.b(typedArray.getInt(3, -1), null));
            }
            i1VarZ.b0();
        } catch (Throwable th) {
            i1VarZ.b0();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void f(d2.c r5) throws java.io.IOException {
        /*
            r4 = this;
            java.lang.Object r0 = r4.f1568n
            a8.e r0 = (a8.e) r0
            java.lang.String r1 = "SELECT count(*) FROM sqlite_master WHERE name != 'android_metadata'"
            android.database.Cursor r1 = r5.j(r1)
            boolean r2 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L19
            r3 = 0
            if (r2 == 0) goto L1b
            int r2 = r1.getInt(r3)     // Catch: java.lang.Throwable -> L19
            if (r2 != 0) goto L1b
            r2 = 1
            goto L1c
        L19:
            r5 = move-exception
            goto L6a
        L1b:
            r2 = r3
        L1c:
            r1.close()
            a8.e.F(r5)
            if (r2 != 0) goto L43
            io.sentry.android.core.p0 r1 = a8.e.M(r5)
            boolean r2 = r1.f16686a
            if (r2 == 0) goto L2d
            goto L43
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Pre-packaged database has an invalid schema: "
            r0.<init>(r2)
            java.lang.String r1 = r1.f16687b
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        L43:
            java.lang.String r1 = "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)"
            r5.p(r1)
            java.lang.String r1 = "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '7d73d21f1bd82c9e5268b6dcf9fde2cb')"
            r5.p(r1)
            java.lang.Object r5 = r0.f183l
            androidx.work.impl.WorkDatabase_Impl r5 = (androidx.work.impl.WorkDatabase_Impl) r5
            java.util.List r0 = r5.f6714f
            if (r0 == 0) goto L69
            int r0 = r0.size()
        L59:
            if (r3 >= r0) goto L69
            java.util.List r1 = r5.f6714f
            java.lang.Object r1 = r1.get(r3)
            androidx.work.impl.b r1 = (androidx.work.impl.b) r1
            r1.getClass()
            int r3 = r3 + 1
            goto L59
        L69:
            return
        L6a:
            throw r5     // Catch: java.lang.Throwable -> L6b
        L6b:
            r0 = move-exception
            xc.d.L(r1, r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.f0.f(d2.c):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void g(d2.c r11) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 292
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.f0.g(d2.c):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0078 A[EDGE_INSN: B:105:0x0078->B:37:0x0078 BREAK  A[LOOP:3: B:12:0x0022->B:108:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void h(d2.c r13, int r14, int r15) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 382
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.f0.h(d2.c, int, int):void");
    }

    public void i(com.google.android.gms.common.api.internal.a0 a0Var) {
        Map map = (Map) this.f1567m;
        if (map.containsKey("ConnectionlessLifecycleHelper")) {
            StringBuilder sb2 = new StringBuilder("ConnectionlessLifecycleHelper".length() + 59);
            sb2.append("LifecycleCallback with tag ConnectionlessLifecycleHelper already added to this fragment.");
            throw new IllegalArgumentException(sb2.toString());
        }
        map.put("ConnectionlessLifecycleHelper", a0Var);
        if (this.f1566l > 0) {
            new androidx.loader.content.j(Looper.getMainLooper(), 4).post(new com.google.common.util.concurrent.w0(15, this, false, a0Var));
        }
    }

    public void j(Bundle bundle) {
        this.f1566l = 1;
        this.f1568n = bundle;
        for (Map.Entry entry : ((Map) this.f1567m).entrySet()) {
            ((com.google.android.gms.common.api.internal.a0) entry.getValue()).b(bundle != null ? bundle.getBundle((String) entry.getKey()) : null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0089 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x000c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void k(int r10, int r11, android.content.Intent r12) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.f1567m
            java.util.Map r0 = (java.util.Map) r0
            java.util.Collection r0 = r0.values()
            java.util.Iterator r0 = r0.iterator()
        Lc:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L95
            java.lang.Object r1 = r0.next()
            com.google.android.gms.common.api.internal.a0 r1 = (com.google.android.gms.common.api.internal.a0) r1
            java.util.concurrent.atomic.AtomicReference r2 = r1.f12695m
            com.google.android.gms.common.api.internal.h r3 = r1.f12699q
            java.util.concurrent.atomic.AtomicReference r4 = r1.f12695m
            java.lang.Object r2 = r2.get()
            com.google.android.gms.common.api.internal.e1 r2 = (com.google.android.gms.common.api.internal.e1) r2
            r5 = 1
            r6 = 3
            r7 = 0
            if (r10 == r5) goto L55
            r5 = 2
            if (r10 == r5) goto L2d
            goto L87
        L2d:
            o3.e r5 = r1.f12697o
            android.app.Activity r1 = r1.a()
            int r8 = o3.f.f20742a
            int r1 = r5.b(r1, r8)
            if (r1 != 0) goto L48
            r4.set(r7)
            androidx.loader.content.j r1 = r3.x
            android.os.Message r2 = r1.obtainMessage(r6)
            r1.sendMessage(r2)
            goto Lc
        L48:
            if (r2 == 0) goto Lc
            o3.b r5 = r2.f12729b
            int r5 = r5.f20729l
            r6 = 18
            if (r5 != r6) goto L87
            if (r1 != r6) goto L87
            goto Lc
        L55:
            r1 = -1
            if (r11 != r1) goto L65
            r4.set(r7)
            androidx.loader.content.j r1 = r3.x
            android.os.Message r2 = r1.obtainMessage(r6)
            r1.sendMessage(r2)
            goto Lc
        L65:
            if (r11 != 0) goto L87
            if (r2 == 0) goto Lc
            r1 = 13
            if (r12 == 0) goto L73
            java.lang.String r5 = "<<ResolutionFailureErrorDetail>>"
            int r1 = r12.getIntExtra(r5, r1)
        L73:
            o3.b r5 = new o3.b
            o3.b r6 = r2.f12729b
            java.lang.String r6 = r6.toString()
            r5.<init>(r1, r7, r6)
            int r1 = r2.f12728a
            r4.set(r7)
            r3.j(r5, r1)
            goto Lc
        L87:
            if (r2 == 0) goto Lc
            o3.b r1 = r2.f12729b
            int r2 = r2.f12728a
            r4.set(r7)
            r3.j(r1, r2)
            goto Lc
        L95:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.f0.k(int, int, android.content.Intent):void");
    }

    public void l(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        for (Map.Entry entry : ((Map) this.f1567m).entrySet()) {
            Bundle bundle2 = new Bundle();
            com.google.android.gms.common.api.internal.e1 e1Var = (com.google.android.gms.common.api.internal.e1) ((com.google.android.gms.common.api.internal.a0) entry.getValue()).f12695m.get();
            if (e1Var != null) {
                o3.b bVar = e1Var.f12729b;
                bundle2.putBoolean("resolving_error", true);
                bundle2.putInt("failed_client_id", e1Var.f12728a);
                bundle2.putInt("failed_status", bVar.f20729l);
                bundle2.putParcelable("failed_resolution", bVar.f20730m);
            }
            bundle.putBundle((String) entry.getKey(), bundle2);
        }
    }

    @Override // com.google.android.gms.tasks.e
    public void onSuccess(Object obj) {
        z4 z4Var = (z4) this.f1567m;
        c6 c6Var = (c6) this.f1568n;
        int i10 = this.f1566l;
        if (((Boolean) obj).booleanValue()) {
            b6 b6VarP = c6.p(c6Var);
            String str = z4Var.f13712d;
            b6VarP.c();
            ((c6) b6VarP.f13530l).s(str);
            b6VarP.c();
            ((c6) b6VarP.f13530l).t(str);
            Long l10 = z4Var.f13713e;
            if (l10 != null) {
                int iLongValue = (int) l10.longValue();
                b6VarP.c();
                ((c6) b6VarP.f13530l).u(iLongValue);
            }
            c6 c6Var2 = (c6) b6VarP.e();
            int i11 = z4Var.f13717i;
            int i12 = i11 - 1;
            if (i11 == 0) {
                throw null;
            }
            int i13 = i10 - 1;
            z2.c cVar = z2.c.f23165l;
            z2.a aVar = (i12 == 0 || i12 != 1) ? new z2.a(Integer.valueOf(i13), c6Var2, cVar) : new z2.a(Integer.valueOf(i13), c6Var2, z2.c.f23164i);
            z4.f13708j.d("analytics event: %s", aVar);
            z2.e eVar = z4Var.f13715g;
            if (eVar != null) {
                eVar.a(aVar);
            }
        }
    }

    public String toString() {
        switch (this.f1565i) {
            case 4:
                return d();
            case 5:
                StringBuilder sb2 = new StringBuilder();
                if (((gb.i0) this.f1567m) == gb.i0.HTTP_1_0) {
                    sb2.append("HTTP/1.0");
                } else {
                    sb2.append("HTTP/1.1");
                }
                sb2.append(' ');
                sb2.append(this.f1566l);
                sb2.append(' ');
                sb2.append((String) this.f1568n);
                return sb2.toString();
            default:
                return super.toString();
        }
    }

    public /* synthetic */ f0(int i10, int i11) {
        this.f1565i = i11;
        this.f1566l = i10;
    }

    public /* synthetic */ f0(z4 z4Var, c6 c6Var, int i10) {
        this.f1565i = 3;
        this.f1567m = z4Var;
        this.f1568n = c6Var;
        this.f1566l = i10;
    }

    public f0() {
        this.f1565i = 1;
        this.f1567m = DesugarCollections.synchronizedMap(new t.e(0));
        this.f1566l = 0;
    }

    public f0(gb.i0 i0Var, int i10, String str) {
        this.f1565i = 5;
        this.f1567m = i0Var;
        this.f1566l = i10;
        this.f1568n = str;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public f0(y1.g gVar, a8.e eVar) {
        this(20, 7);
        this.f1565i = 7;
        this.f1567m = gVar;
        this.f1568n = eVar;
    }

    public f0(ImageView imageView) {
        this.f1565i = 0;
        this.f1566l = 0;
        this.f1567m = imageView;
    }
}
