package a9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes5.dex */
public final class p extends kotlin.reflect.jvm.internal.impl.protobuf.p implements r {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f610n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f611o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public List f612p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public List f613q;

    public static p i() {
        p pVar = new p();
        pVar.f611o = 6;
        List list = Collections.EMPTY_LIST;
        pVar.f612p = list;
        pVar.f613q = list;
        return pVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        q qVarH = h();
        if (qVarH.isInitialized()) {
            return qVarH;
        }
        throw new UninitializedMessageException();
    }

    public final Object clone() {
        p pVarI = i();
        pVarI.j(h());
        return pVarI;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        j((q) uVar);
        return this;
    }

    public final q h() {
        q qVar = new q(this);
        int i10 = this.f610n;
        int i11 = (i10 & 1) != 1 ? 0 : 1;
        qVar.f635n = this.f611o;
        if ((i10 & 2) == 2) {
            this.f612p = Collections.unmodifiableList(this.f612p);
            this.f610n &= -3;
        }
        qVar.f636o = this.f612p;
        if ((this.f610n & 4) == 4) {
            this.f613q = Collections.unmodifiableList(this.f613q);
            this.f610n &= -5;
        }
        qVar.f637p = this.f613q;
        qVar.f634m = i11;
        return qVar;
    }

    public final void j(q qVar) {
        if (qVar == q.f631s) {
            return;
        }
        if ((qVar.f634m & 1) == 1) {
            int i10 = qVar.f635n;
            this.f610n = 1 | this.f610n;
            this.f611o = i10;
        }
        if (!qVar.f636o.isEmpty()) {
            if (this.f612p.isEmpty()) {
                this.f612p = qVar.f636o;
                this.f610n &= -3;
            } else {
                if ((this.f610n & 2) != 2) {
                    this.f612p = new ArrayList(this.f612p);
                    this.f610n |= 2;
                }
                this.f612p.addAll(qVar.f636o);
            }
        }
        if (!qVar.f637p.isEmpty()) {
            if (this.f613q.isEmpty()) {
                this.f613q = qVar.f637p;
                this.f610n &= -5;
            } else {
                if ((this.f610n & 4) != 4) {
                    this.f613q = new ArrayList(this.f613q);
                    this.f610n |= 4;
                }
                this.f613q.addAll(qVar.f637p);
            }
        }
        g(qVar);
        this.f19815i = this.f19815i.b(qVar.f633l);
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
            a9.a r1 = a9.q.f632t     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.q r1 = new a9.q     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.j(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.q r4 = (a9.q) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: a9.p.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}
