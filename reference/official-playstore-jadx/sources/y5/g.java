package y5;

import android.content.Context;
import android.os.Bundle;
import com.google.common.util.concurrent.r0;
import kotlin.jvm.internal.p;
import w5.a0;
import w5.s;

/* JADX INFO: loaded from: classes4.dex */
public final class g {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final e f22895c = new e();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b1.a f22896d = r0.B(s.f22486b);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final kb.d f22897a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f22898b;

    public g(z4.g gVar, d7.j jVar, d7.j jVar2, com.google.firebase.installations.c cVar) {
        gVar.a();
        Context context = gVar.f23184a;
        a0 a0Var = a0.f22380a;
        w5.b bVarA = a0.a(gVar);
        kb.d dVar = new kb.d(context);
        i iVar = new i(bVarA, jVar);
        f22895c.getClass();
        c cVar2 = new c(jVar2, cVar, bVarA, iVar, (z0.g) f22896d.getValue(context, e.f22890a[0]));
        this.f22897a = dVar;
        this.f22898b = cVar2;
    }

    public final double a() {
        Bundle bundle = (Bundle) this.f22897a.f19673l;
        Double dValueOf = bundle.containsKey("firebase_sessions_sampling_rate") ? Double.valueOf(bundle.getDouble("firebase_sessions_sampling_rate")) : null;
        if (dValueOf != null) {
            double dDoubleValue = dValueOf.doubleValue();
            if (0.0d <= dDoubleValue && dDoubleValue <= 1.0d) {
                return dDoubleValue;
            }
        }
        d dVar = (d) this.f22898b.f22883n.f22908b;
        if (dVar == null) {
            p.i("sessionConfigs");
            throw null;
        }
        Double d4 = dVar.f22886b;
        if (d4 != null) {
            double dDoubleValue2 = d4.doubleValue();
            if (0.0d <= dDoubleValue2 && dDoubleValue2 <= 1.0d) {
                return dDoubleValue2;
            }
        }
        return 1.0d;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object b(f7.c r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof y5.f
            if (r0 == 0) goto L13
            r0 = r7
            y5.f r0 = (y5.f) r0
            int r1 = r0.f22894n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f22894n = r1
            goto L18
        L13:
            y5.f r0 = new y5.f
            r0.<init>(r6, r7)
        L18:
            java.lang.Object r7 = r0.f22892l
            int r1 = r0.f22894n
            x6.t0 r2 = x6.t0.f22605a
            r3 = 2
            r4 = 1
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L3a
            if (r1 == r4) goto L34
            if (r1 != r3) goto L2c
            k2.c.G(r7)
            goto L58
        L2c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L34:
            y5.g r1 = r0.f22891i
            k2.c.G(r7)
            goto L4a
        L3a:
            k2.c.G(r7)
            r0.f22891i = r6
            r0.f22894n = r4
            kb.d r7 = r6.f22897a
            r7.getClass()
            if (r2 != r5) goto L49
            goto L57
        L49:
            r1 = r6
        L4a:
            y5.c r7 = r1.f22898b
            r1 = 0
            r0.f22891i = r1
            r0.f22894n = r3
            java.lang.Object r7 = r7.b(r0)
            if (r7 != r5) goto L58
        L57:
            return r5
        L58:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: y5.g.b(f7.c):java.lang.Object");
    }
}
