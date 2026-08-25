package a9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes5.dex */
public final class p1 extends kotlin.reflect.jvm.internal.impl.protobuf.p implements s1 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f624n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f625o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f626p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f627q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public q1 f628r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public List f629s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public List f630t;

    public static p1 i() {
        p1 p1Var = new p1();
        p1Var.f628r = q1.INV;
        List list = Collections.EMPTY_LIST;
        p1Var.f629s = list;
        p1Var.f630t = list;
        return p1Var;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        r1 r1VarH = h();
        if (r1VarH.isInitialized()) {
            return r1VarH;
        }
        throw new UninitializedMessageException();
    }

    public final Object clone() {
        p1 p1VarI = i();
        p1VarI.j(h());
        return p1VarI;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        j((r1) uVar);
        return this;
    }

    public final r1 h() {
        r1 r1Var = new r1(this);
        int i10 = this.f624n;
        int i11 = (i10 & 1) != 1 ? 0 : 1;
        r1Var.f648n = this.f625o;
        if ((i10 & 2) == 2) {
            i11 |= 2;
        }
        r1Var.f649o = this.f626p;
        if ((i10 & 4) == 4) {
            i11 |= 4;
        }
        r1Var.f650p = this.f627q;
        if ((i10 & 8) == 8) {
            i11 |= 8;
        }
        r1Var.f651q = this.f628r;
        if ((i10 & 16) == 16) {
            this.f629s = Collections.unmodifiableList(this.f629s);
            this.f624n &= -17;
        }
        r1Var.f652r = this.f629s;
        if ((this.f624n & 32) == 32) {
            this.f630t = Collections.unmodifiableList(this.f630t);
            this.f624n &= -33;
        }
        r1Var.f653s = this.f630t;
        r1Var.f647m = i11;
        return r1Var;
    }

    public final void j(r1 r1Var) {
        if (r1Var == r1.f645w) {
            return;
        }
        int i10 = r1Var.f647m;
        if ((i10 & 1) == 1) {
            int i11 = r1Var.f648n;
            this.f624n = 1 | this.f624n;
            this.f625o = i11;
        }
        if ((i10 & 2) == 2) {
            int i12 = r1Var.f649o;
            this.f624n = 2 | this.f624n;
            this.f626p = i12;
        }
        if ((i10 & 4) == 4) {
            boolean z = r1Var.f650p;
            this.f624n = 4 | this.f624n;
            this.f627q = z;
        }
        if ((i10 & 8) == 8) {
            q1 q1Var = r1Var.f651q;
            q1Var.getClass();
            this.f624n = 8 | this.f624n;
            this.f628r = q1Var;
        }
        if (!r1Var.f652r.isEmpty()) {
            if (this.f629s.isEmpty()) {
                this.f629s = r1Var.f652r;
                this.f624n &= -17;
            } else {
                if ((this.f624n & 16) != 16) {
                    this.f629s = new ArrayList(this.f629s);
                    this.f624n |= 16;
                }
                this.f629s.addAll(r1Var.f652r);
            }
        }
        if (!r1Var.f653s.isEmpty()) {
            if (this.f630t.isEmpty()) {
                this.f630t = r1Var.f653s;
                this.f624n &= -33;
            } else {
                if ((this.f624n & 32) != 32) {
                    this.f630t = new ArrayList(this.f630t);
                    this.f624n |= 32;
                }
                this.f630t.addAll(r1Var.f653s);
            }
        }
        g(r1Var);
        this.f19815i = this.f19815i.b(r1Var.f646l);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001b  */
    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0.a y(kotlin.reflect.jvm.internal.impl.protobuf.i r3, kotlin.reflect.jvm.internal.impl.protobuf.l r4) throws java.lang.Throwable {
        /*
            r2 = this;
            r0 = 0
            a9.a r1 = a9.r1.x     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.r1 r1 = new a9.r1     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.j(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.r1 r4 = (a9.r1) r4     // Catch: java.lang.Throwable -> Lf
            throw r3     // Catch: java.lang.Throwable -> L17
        L17:
            r3 = move-exception
            r0 = r4
        L19:
            if (r0 == 0) goto L1e
            r2.j(r0)
        L1e:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: a9.p1.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}
