package a9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.UninitializedMessageException;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends kotlin.reflect.jvm.internal.impl.protobuf.o implements f {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f348l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public d f349m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f350n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f351o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public double f352p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f353q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f354r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f355s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public j f356t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public List f357u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f358v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f359w;

    public static c h() {
        c cVar = new c();
        cVar.f349m = d.BYTE;
        cVar.f356t = j.f498q;
        cVar.f357u = Collections.EMPTY_LIST;
        return cVar;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0.a
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 build() {
        e eVarG = g();
        if (eVarG.isInitialized()) {
            return eVarG;
        }
        throw new UninitializedMessageException();
    }

    public final Object clone() {
        c cVarH = h();
        cVarH.i(g());
        return cVarH;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.o
    public final /* bridge */ /* synthetic */ kotlin.reflect.jvm.internal.impl.protobuf.o f(kotlin.reflect.jvm.internal.impl.protobuf.u uVar) {
        i((e) uVar);
        return this;
    }

    public final e g() {
        e eVar = new e(this);
        int i10 = this.f348l;
        int i11 = (i10 & 1) != 1 ? 0 : 1;
        eVar.f404m = this.f349m;
        if ((i10 & 2) == 2) {
            i11 |= 2;
        }
        eVar.f405n = this.f350n;
        if ((i10 & 4) == 4) {
            i11 |= 4;
        }
        eVar.f406o = this.f351o;
        if ((i10 & 8) == 8) {
            i11 |= 8;
        }
        eVar.f407p = this.f352p;
        if ((i10 & 16) == 16) {
            i11 |= 16;
        }
        eVar.f408q = this.f353q;
        if ((i10 & 32) == 32) {
            i11 |= 32;
        }
        eVar.f409r = this.f354r;
        if ((i10 & 64) == 64) {
            i11 |= 64;
        }
        eVar.f410s = this.f355s;
        if ((i10 & 128) == 128) {
            i11 |= 128;
        }
        eVar.f411t = this.f356t;
        if ((i10 & 256) == 256) {
            this.f357u = Collections.unmodifiableList(this.f357u);
            this.f348l &= -257;
        }
        eVar.f412u = this.f357u;
        if ((i10 & 512) == 512) {
            i11 |= 256;
        }
        eVar.f413v = this.f358v;
        if ((i10 & 1024) == 1024) {
            i11 |= 512;
        }
        eVar.f414w = this.f359w;
        eVar.f403l = i11;
        return eVar;
    }

    public final void i(e eVar) {
        j jVar;
        if (eVar == e.z) {
            return;
        }
        if ((eVar.f403l & 1) == 1) {
            d dVar = eVar.f404m;
            dVar.getClass();
            this.f348l = 1 | this.f348l;
            this.f349m = dVar;
        }
        int i10 = eVar.f403l;
        if ((i10 & 2) == 2) {
            long j10 = eVar.f405n;
            this.f348l |= 2;
            this.f350n = j10;
        }
        if ((i10 & 4) == 4) {
            float f10 = eVar.f406o;
            this.f348l = 4 | this.f348l;
            this.f351o = f10;
        }
        if ((i10 & 8) == 8) {
            double d4 = eVar.f407p;
            this.f348l |= 8;
            this.f352p = d4;
        }
        if ((i10 & 16) == 16) {
            int i11 = eVar.f408q;
            this.f348l = 16 | this.f348l;
            this.f353q = i11;
        }
        if ((i10 & 32) == 32) {
            int i12 = eVar.f409r;
            this.f348l = 32 | this.f348l;
            this.f354r = i12;
        }
        if ((i10 & 64) == 64) {
            int i13 = eVar.f410s;
            this.f348l = 64 | this.f348l;
            this.f355s = i13;
        }
        if ((i10 & 128) == 128) {
            j jVar2 = eVar.f411t;
            if ((this.f348l & 128) != 128 || (jVar = this.f356t) == j.f498q) {
                this.f356t = jVar2;
            } else {
                i iVar = new i();
                iVar.f484n = Collections.EMPTY_LIST;
                iVar.h(jVar);
                iVar.h(jVar2);
                this.f356t = iVar.g();
            }
            this.f348l |= 128;
        }
        if (!eVar.f412u.isEmpty()) {
            if (this.f357u.isEmpty()) {
                this.f357u = eVar.f412u;
                this.f348l &= -257;
            } else {
                if ((this.f348l & 256) != 256) {
                    this.f357u = new ArrayList(this.f357u);
                    this.f348l |= 256;
                }
                this.f357u.addAll(eVar.f412u);
            }
        }
        int i14 = eVar.f403l;
        if ((i14 & 256) == 256) {
            int i15 = eVar.f413v;
            this.f348l |= 512;
            this.f358v = i15;
        }
        if ((i14 & 512) == 512) {
            int i16 = eVar.f414w;
            this.f348l |= 1024;
            this.f359w = i16;
        }
        this.f19815i = this.f19815i.b(eVar.f402i);
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
            a9.a r1 = a9.e.A     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            a9.e r1 = new a9.e     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf kotlin.reflect.jvm.internal.impl.protobuf.InvalidProtocolBufferException -> L11
            r2.i(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            kotlin.reflect.jvm.internal.impl.protobuf.c0 r4 = r3.f19759i     // Catch: java.lang.Throwable -> Lf
            a9.e r4 = (a9.e) r4     // Catch: java.lang.Throwable -> Lf
            throw r3     // Catch: java.lang.Throwable -> L17
        L17:
            r3 = move-exception
            r0 = r4
        L19:
            if (r0 == 0) goto L1e
            r2.i(r0)
        L1e:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: a9.c.y(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):kotlin.reflect.jvm.internal.impl.protobuf.c0$a");
    }
}
