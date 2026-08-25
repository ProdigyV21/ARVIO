package androidx.appcompat.widget;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class a implements androidx.core.view.o2, com.google.android.gms.internal.fido.s0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1499a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1500b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f1501c;

    public a(MessageDigest messageDigest, int i10) {
        ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
        this.f1501c = messageDigest;
        this.f1499a = i10;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object d(androidx.appcompat.widget.a r10, x6.d r11, f7.a r12) {
        /*
            java.lang.Object r0 = r10.f1501c
            eb.a r0 = (eb.a) r0
            boolean r1 = r12 instanceof eb.b0
            if (r1 == 0) goto L17
            r1 = r12
            eb.b0 r1 = (eb.b0) r1
            int r2 = r1.f15117q
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L17
            int r2 = r2 - r3
            r1.f15117q = r2
            goto L1c
        L17:
            eb.b0 r1 = new eb.b0
            r1.<init>(r10, r12)
        L1c:
            java.lang.Object r12 = r1.f15115o
            int r2 = r1.f15117q
            r3 = 0
            r4 = 0
            r5 = 7
            r6 = 6
            r7 = 1
            r8 = 4
            if (r2 == 0) goto L5c
            if (r2 != r7) goto L54
            java.lang.String r10 = r1.f15114n
            java.util.LinkedHashMap r11 = r1.f15113m
            androidx.appcompat.widget.a r0 = r1.f15112l
            x6.d r2 = r1.f15111i
            k2.c.G(r12)
            db.m r12 = (db.m) r12
            r11.put(r10, r12)
            java.lang.Object r10 = r0.f1501c
            eb.a r10 = (eb.a) r10
            byte r10 = r10.h()
            if (r10 == r8) goto L51
            if (r10 != r5) goto L47
            goto L9c
        L47:
            java.lang.Object r10 = r0.f1501c
            eb.a r10 = (eb.a) r10
            java.lang.String r11 = "Expected end of the object or comma"
            eb.a.s(r10, r11, r4, r6)
            throw r3
        L51:
            r12 = r10
            r10 = r0
            goto L70
        L54:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L5c:
            k2.c.G(r12)
            byte r12 = r0.i(r6)
            byte r2 = r0.w()
            if (r2 == r8) goto Lb4
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            r2 = r11
            r11 = r0
        L70:
            java.lang.Object r0 = r10.f1501c
            eb.a r0 = (eb.a) r0
            boolean r9 = r0.c()
            if (r9 == 0) goto L9a
            boolean r12 = r10.f1500b
            if (r12 == 0) goto L83
            java.lang.String r12 = r0.n()
            goto L87
        L83:
            java.lang.String r12 = r0.l()
        L87:
            r3 = 5
            r0.i(r3)
            r1.f15111i = r2
            r1.f15112l = r10
            r1.f15113m = r11
            r1.f15114n = r12
            r1.f15117q = r7
            r2.f22578l = r1
            e7.a r10 = e7.a.f15033i
            return r10
        L9a:
            r0 = r10
            r10 = r12
        L9c:
            java.lang.Object r12 = r0.f1501c
            eb.a r12 = (eb.a) r12
            if (r10 != r6) goto La6
            r12.i(r5)
            goto La8
        La6:
            if (r10 == r8) goto Lae
        La8:
            db.b0 r10 = new db.b0
            r10.<init>(r11)
            return r10
        Lae:
            java.lang.String r10 = "Unexpected trailing comma"
            eb.a.s(r12, r10, r4, r6)
            throw r3
        Lb4:
            java.lang.String r10 = "Unexpected leading comma"
            eb.a.s(r0, r10, r4, r6)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.a.d(androidx.appcompat.widget.a, x6.d, f7.a):java.lang.Object");
    }

    @Override // androidx.core.view.o2
    public void a() {
        this.f1500b = true;
    }

    @Override // androidx.core.view.o2
    public void b() {
        super/*android.view.ViewGroup*/.setVisibility(0);
        this.f1500b = false;
    }

    @Override // androidx.core.view.o2
    public void c() {
        if (this.f1500b) {
            return;
        }
        ActionBarContextView actionBarContextView = (ActionBarContextView) this.f1501c;
        actionBarContextView.f1415p = null;
        super/*android.view.ViewGroup*/.setVisibility(this.f1499a);
    }

    public db.m e() {
        db.m b0Var;
        Object obj;
        eb.a aVar = (eb.a) this.f1501c;
        byte bW = aVar.w();
        if (bW == 1) {
            return g(true);
        }
        if (bW == 0) {
            return g(false);
        }
        if (bW != 6) {
            if (bW == 8) {
                return f();
            }
            eb.a.s(aVar, "Cannot begin reading element, unexpected token: " + ((int) bW), 0, 6);
            throw null;
        }
        int i10 = this.f1499a + 1;
        this.f1499a = i10;
        if (i10 == 200) {
            eb.a0 a0Var = new eb.a0(this, null);
            x6.d dVar = new x6.d();
            dVar.f22577i = a0Var;
            dVar.f22578l = dVar;
            e7.a aVar2 = e7.a.f15033i;
            dVar.f22579m = aVar2;
            while (true) {
                obj = dVar.f22579m;
                d7.d dVar2 = dVar.f22578l;
                if (dVar2 == null) {
                    break;
                }
                if (aVar2.equals(obj)) {
                    try {
                        eb.a0 a0Var2 = dVar.f22577i;
                        kotlin.jvm.internal.q0.e(3, a0Var2);
                        eb.a0 a0Var3 = new eb.a0(a0Var2.f15106m, dVar2);
                        a0Var3.f15105l = dVar;
                        Object objInvokeSuspend = a0Var3.invokeSuspend(x6.t0.f22605a);
                        if (objInvokeSuspend != aVar2) {
                            dVar2.resumeWith(objInvokeSuspend);
                        }
                    } catch (Throwable th) {
                        dVar2.resumeWith(new x6.c0(th));
                    }
                } else {
                    dVar.f22579m = aVar2;
                    dVar2.resumeWith(obj);
                }
            }
            k2.c.G(obj);
            b0Var = (db.m) obj;
        } else {
            byte bI = aVar.i((byte) 6);
            if (aVar.w() == 4) {
                eb.a.s(aVar, "Unexpected leading comma", 0, 6);
                throw null;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            while (true) {
                if (!aVar.c()) {
                    break;
                }
                String strN = this.f1500b ? aVar.n() : aVar.l();
                aVar.i((byte) 5);
                linkedHashMap.put(strN, e());
                bI = aVar.h();
                if (bI != 4) {
                    if (bI != 7) {
                        eb.a.s(aVar, "Expected end of the object or comma", 0, 6);
                        throw null;
                    }
                }
            }
            if (bI == 6) {
                aVar.i((byte) 7);
            } else if (bI == 4) {
                eb.a.s(aVar, "Unexpected trailing comma", 0, 6);
                throw null;
            }
            b0Var = new db.b0(linkedHashMap);
        }
        this.f1499a--;
        return b0Var;
    }

    public db.d f() {
        eb.a aVar = (eb.a) this.f1501c;
        byte bH = aVar.h();
        if (aVar.w() == 4) {
            eb.a.s(aVar, "Unexpected leading comma", 0, 6);
            throw null;
        }
        ArrayList arrayList = new ArrayList();
        while (aVar.c()) {
            arrayList.add(e());
            bH = aVar.h();
            if (bH != 4) {
                boolean z = bH == 9;
                int i10 = aVar.f15100a;
                if (!z) {
                    eb.a.s(aVar, "Expected end of the array or comma", i10, 4);
                    throw null;
                }
            }
        }
        if (bH == 8) {
            aVar.i((byte) 9);
        } else if (bH == 4) {
            eb.a.s(aVar, "Unexpected trailing comma", 0, 6);
            throw null;
        }
        return new db.d(arrayList);
    }

    public db.g0 g(boolean z) {
        eb.a aVar = (eb.a) this.f1501c;
        String strN = (this.f1500b || !z) ? aVar.n() : aVar.l();
        return (z || !kotlin.jvm.internal.p.a(strN, "null")) ? new db.u(strN, z, null) : db.y.INSTANCE;
    }

    public a(db.i iVar, eb.a aVar) {
        this.f1501c = aVar;
        this.f1500b = iVar.f14903c;
    }

    public a(v9.b0 b0Var, int i10, boolean z) {
        this.f1501c = b0Var;
        this.f1499a = i10;
        this.f1500b = z;
    }

    public a(ActionBarContextView actionBarContextView) {
        this.f1501c = actionBarContextView;
        this.f1500b = false;
    }
}
