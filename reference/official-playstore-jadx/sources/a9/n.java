package a9;

import androidx.media3.extractor.ts.TsExtractor;
import java.util.Collections;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class n extends kotlin.reflect.jvm.internal.impl.protobuf.q implements o {
    public static final n T;
    public static final a U = new a(3);
    public List A;
    public List B;
    public List C;
    public List D;
    public List E;
    public int F;
    public int G;
    public k1 H;
    public int I;
    public List J;
    public int K;
    public List L;
    public List M;
    public int N;
    public u1 O;
    public List P;
    public f2 Q;
    public byte R;
    public int S;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final kotlin.reflect.jvm.internal.impl.protobuf.g f581l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f582m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f583n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f584o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f585p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public List f586q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public List f587r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public List f588s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f589t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public List f590u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f591v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public List f592w;
    public List x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f593y;
    public List z;

    static {
        n nVar = new n();
        T = nVar;
        nVar.o();
    }

    public n(l lVar) {
        super(lVar);
        this.f589t = -1;
        this.f591v = -1;
        this.f593y = -1;
        this.F = -1;
        this.K = -1;
        this.N = -1;
        this.R = (byte) -1;
        this.S = -1;
        this.f581l = lVar.f19815i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final int a() {
        int i10 = this.S;
        if (i10 != -1) {
            return i10;
        }
        int iB = (this.f582m & 1) == 1 ? kotlin.reflect.jvm.internal.impl.protobuf.j.b(1, this.f583n) : 0;
        int iC = 0;
        for (int i11 = 0; i11 < this.f588s.size(); i11++) {
            iC += kotlin.reflect.jvm.internal.impl.protobuf.j.c(((Integer) this.f588s.get(i11)).intValue());
        }
        int iD = iB + iC;
        if (!this.f588s.isEmpty()) {
            iD = iD + 1 + kotlin.reflect.jvm.internal.impl.protobuf.j.c(iC);
        }
        this.f589t = iC;
        if ((this.f582m & 2) == 2) {
            iD += kotlin.reflect.jvm.internal.impl.protobuf.j.b(3, this.f584o);
        }
        if ((this.f582m & 4) == 4) {
            iD += kotlin.reflect.jvm.internal.impl.protobuf.j.b(4, this.f585p);
        }
        for (int i12 = 0; i12 < this.f586q.size(); i12++) {
            iD += kotlin.reflect.jvm.internal.impl.protobuf.j.d(5, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f586q.get(i12));
        }
        for (int i13 = 0; i13 < this.f587r.size(); i13++) {
            iD += kotlin.reflect.jvm.internal.impl.protobuf.j.d(6, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f587r.get(i13));
        }
        int iC2 = 0;
        for (int i14 = 0; i14 < this.f590u.size(); i14++) {
            iC2 += kotlin.reflect.jvm.internal.impl.protobuf.j.c(((Integer) this.f590u.get(i14)).intValue());
        }
        int iD2 = iD + iC2;
        if (!this.f590u.isEmpty()) {
            iD2 = iD2 + 1 + kotlin.reflect.jvm.internal.impl.protobuf.j.c(iC2);
        }
        this.f591v = iC2;
        for (int i15 = 0; i15 < this.z.size(); i15++) {
            iD2 += kotlin.reflect.jvm.internal.impl.protobuf.j.d(8, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.z.get(i15));
        }
        for (int i16 = 0; i16 < this.A.size(); i16++) {
            iD2 += kotlin.reflect.jvm.internal.impl.protobuf.j.d(9, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.A.get(i16));
        }
        for (int i17 = 0; i17 < this.B.size(); i17++) {
            iD2 += kotlin.reflect.jvm.internal.impl.protobuf.j.d(10, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.B.get(i17));
        }
        for (int i18 = 0; i18 < this.C.size(); i18++) {
            iD2 += kotlin.reflect.jvm.internal.impl.protobuf.j.d(11, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.C.get(i18));
        }
        for (int i19 = 0; i19 < this.D.size(); i19++) {
            iD2 += kotlin.reflect.jvm.internal.impl.protobuf.j.d(13, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.D.get(i19));
        }
        int iC3 = 0;
        for (int i20 = 0; i20 < this.E.size(); i20++) {
            iC3 += kotlin.reflect.jvm.internal.impl.protobuf.j.c(((Integer) this.E.get(i20)).intValue());
        }
        int iD3 = iD2 + iC3;
        if (!this.E.isEmpty()) {
            iD3 = iD3 + 2 + kotlin.reflect.jvm.internal.impl.protobuf.j.c(iC3);
        }
        this.F = iC3;
        if ((this.f582m & 8) == 8) {
            iD3 += kotlin.reflect.jvm.internal.impl.protobuf.j.b(17, this.G);
        }
        if ((this.f582m & 16) == 16) {
            iD3 += kotlin.reflect.jvm.internal.impl.protobuf.j.d(18, this.H);
        }
        if ((this.f582m & 32) == 32) {
            iD3 += kotlin.reflect.jvm.internal.impl.protobuf.j.b(19, this.I);
        }
        for (int i21 = 0; i21 < this.f592w.size(); i21++) {
            iD3 += kotlin.reflect.jvm.internal.impl.protobuf.j.d(20, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f592w.get(i21));
        }
        int iC4 = 0;
        for (int i22 = 0; i22 < this.x.size(); i22++) {
            iC4 += kotlin.reflect.jvm.internal.impl.protobuf.j.c(((Integer) this.x.get(i22)).intValue());
        }
        int iC5 = iD3 + iC4;
        if (!this.x.isEmpty()) {
            iC5 = iC5 + 2 + kotlin.reflect.jvm.internal.impl.protobuf.j.c(iC4);
        }
        this.f593y = iC4;
        int iC6 = 0;
        for (int i23 = 0; i23 < this.J.size(); i23++) {
            iC6 += kotlin.reflect.jvm.internal.impl.protobuf.j.c(((Integer) this.J.get(i23)).intValue());
        }
        int iD4 = iC5 + iC6;
        if (!this.J.isEmpty()) {
            iD4 = iD4 + 2 + kotlin.reflect.jvm.internal.impl.protobuf.j.c(iC6);
        }
        this.K = iC6;
        for (int i24 = 0; i24 < this.L.size(); i24++) {
            iD4 += kotlin.reflect.jvm.internal.impl.protobuf.j.d(23, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.L.get(i24));
        }
        int iC7 = 0;
        for (int i25 = 0; i25 < this.M.size(); i25++) {
            iC7 += kotlin.reflect.jvm.internal.impl.protobuf.j.c(((Integer) this.M.get(i25)).intValue());
        }
        int iD5 = iD4 + iC7;
        if (!this.M.isEmpty()) {
            iD5 = iD5 + 2 + kotlin.reflect.jvm.internal.impl.protobuf.j.c(iC7);
        }
        this.N = iC7;
        if ((this.f582m & 64) == 64) {
            iD5 += kotlin.reflect.jvm.internal.impl.protobuf.j.d(30, this.O);
        }
        int iC8 = 0;
        for (int i26 = 0; i26 < this.P.size(); i26++) {
            iC8 += kotlin.reflect.jvm.internal.impl.protobuf.j.c(((Integer) this.P.get(i26)).intValue());
        }
        int size = (this.P.size() * 2) + iD5 + iC8;
        if ((this.f582m & 128) == 128) {
            size += kotlin.reflect.jvm.internal.impl.protobuf.j.d(32, this.Q);
        }
        int size2 = this.f581l.size() + i() + size;
        this.S = size2;
        return size2;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a b() {
        l lVarI = l.i();
        lVarI.j(this);
        return lVarI;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final c0.a c() {
        return l.i();
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final kotlin.reflect.jvm.internal.impl.protobuf.c0 d() {
        return T;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.c0
    public final void e(kotlin.reflect.jvm.internal.impl.protobuf.j jVar) {
        a();
        fi.iki.elonen.f fVar = new fi.iki.elonen.f(this);
        if ((this.f582m & 1) == 1) {
            jVar.m(1, this.f583n);
        }
        if (this.f588s.size() > 0) {
            jVar.v(18);
            jVar.v(this.f589t);
        }
        for (int i10 = 0; i10 < this.f588s.size(); i10++) {
            jVar.n(((Integer) this.f588s.get(i10)).intValue());
        }
        if ((this.f582m & 2) == 2) {
            jVar.m(3, this.f584o);
        }
        if ((this.f582m & 4) == 4) {
            jVar.m(4, this.f585p);
        }
        for (int i11 = 0; i11 < this.f586q.size(); i11++) {
            jVar.o(5, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f586q.get(i11));
        }
        for (int i12 = 0; i12 < this.f587r.size(); i12++) {
            jVar.o(6, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f587r.get(i12));
        }
        if (this.f590u.size() > 0) {
            jVar.v(58);
            jVar.v(this.f591v);
        }
        for (int i13 = 0; i13 < this.f590u.size(); i13++) {
            jVar.n(((Integer) this.f590u.get(i13)).intValue());
        }
        for (int i14 = 0; i14 < this.z.size(); i14++) {
            jVar.o(8, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.z.get(i14));
        }
        for (int i15 = 0; i15 < this.A.size(); i15++) {
            jVar.o(9, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.A.get(i15));
        }
        for (int i16 = 0; i16 < this.B.size(); i16++) {
            jVar.o(10, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.B.get(i16));
        }
        for (int i17 = 0; i17 < this.C.size(); i17++) {
            jVar.o(11, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.C.get(i17));
        }
        for (int i18 = 0; i18 < this.D.size(); i18++) {
            jVar.o(13, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.D.get(i18));
        }
        if (this.E.size() > 0) {
            jVar.v(TsExtractor.TS_STREAM_TYPE_HDMV_DTS);
            jVar.v(this.F);
        }
        for (int i19 = 0; i19 < this.E.size(); i19++) {
            jVar.n(((Integer) this.E.get(i19)).intValue());
        }
        if ((this.f582m & 8) == 8) {
            jVar.m(17, this.G);
        }
        if ((this.f582m & 16) == 16) {
            jVar.o(18, this.H);
        }
        if ((this.f582m & 32) == 32) {
            jVar.m(19, this.I);
        }
        for (int i20 = 0; i20 < this.f592w.size(); i20++) {
            jVar.o(20, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.f592w.get(i20));
        }
        if (this.x.size() > 0) {
            jVar.v(170);
            jVar.v(this.f593y);
        }
        for (int i21 = 0; i21 < this.x.size(); i21++) {
            jVar.n(((Integer) this.x.get(i21)).intValue());
        }
        if (this.J.size() > 0) {
            jVar.v(178);
            jVar.v(this.K);
        }
        for (int i22 = 0; i22 < this.J.size(); i22++) {
            jVar.n(((Integer) this.J.get(i22)).intValue());
        }
        for (int i23 = 0; i23 < this.L.size(); i23++) {
            jVar.o(23, (kotlin.reflect.jvm.internal.impl.protobuf.c0) this.L.get(i23));
        }
        if (this.M.size() > 0) {
            jVar.v(194);
            jVar.v(this.N);
        }
        for (int i24 = 0; i24 < this.M.size(); i24++) {
            jVar.n(((Integer) this.M.get(i24)).intValue());
        }
        if ((this.f582m & 64) == 64) {
            jVar.o(30, this.O);
        }
        for (int i25 = 0; i25 < this.P.size(); i25++) {
            jVar.m(31, ((Integer) this.P.get(i25)).intValue());
        }
        if ((this.f582m & 128) == 128) {
            jVar.o(32, this.Q);
        }
        fVar.f0(19000, jVar);
        jVar.r(this.f581l);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.d0
    public final boolean isInitialized() {
        byte b10 = this.R;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        if ((this.f582m & 2) != 2) {
            this.R = (byte) 0;
            return false;
        }
        for (int i10 = 0; i10 < this.f586q.size(); i10++) {
            if (!((r1) this.f586q.get(i10)).isInitialized()) {
                this.R = (byte) 0;
                return false;
            }
        }
        for (int i11 = 0; i11 < this.f587r.size(); i11++) {
            if (!((k1) this.f587r.get(i11)).isInitialized()) {
                this.R = (byte) 0;
                return false;
            }
        }
        for (int i12 = 0; i12 < this.f592w.size(); i12++) {
            if (!((k1) this.f592w.get(i12)).isInitialized()) {
                this.R = (byte) 0;
                return false;
            }
        }
        for (int i13 = 0; i13 < this.z.size(); i13++) {
            if (!((q) this.z.get(i13)).isInitialized()) {
                this.R = (byte) 0;
                return false;
            }
        }
        for (int i14 = 0; i14 < this.A.size(); i14++) {
            if (!((i0) this.A.get(i14)).isInitialized()) {
                this.R = (byte) 0;
                return false;
            }
        }
        for (int i15 = 0; i15 < this.B.size(); i15++) {
            if (!((t0) this.B.get(i15)).isInitialized()) {
                this.R = (byte) 0;
                return false;
            }
        }
        for (int i16 = 0; i16 < this.C.size(); i16++) {
            if (!((m1) this.C.get(i16)).isInitialized()) {
                this.R = (byte) 0;
                return false;
            }
        }
        for (int i17 = 0; i17 < this.D.size(); i17++) {
            if (!((b0) this.D.get(i17)).isInitialized()) {
                this.R = (byte) 0;
                return false;
            }
        }
        if ((this.f582m & 16) == 16 && !this.H.isInitialized()) {
            this.R = (byte) 0;
            return false;
        }
        for (int i18 = 0; i18 < this.L.size(); i18++) {
            if (!((k1) this.L.get(i18)).isInitialized()) {
                this.R = (byte) 0;
                return false;
            }
        }
        if ((this.f582m & 64) == 64 && !this.O.isInitialized()) {
            this.R = (byte) 0;
            return false;
        }
        if (h()) {
            this.R = (byte) 1;
            return true;
        }
        this.R = (byte) 0;
        return false;
    }

    public final void o() {
        this.f583n = 6;
        this.f584o = 0;
        this.f585p = 0;
        List list = Collections.EMPTY_LIST;
        this.f586q = list;
        this.f587r = list;
        this.f588s = list;
        this.f590u = list;
        this.f592w = list;
        this.x = list;
        this.z = list;
        this.A = list;
        this.B = list;
        this.C = list;
        this.D = list;
        this.E = list;
        this.G = 0;
        this.H = k1.D;
        this.I = 0;
        this.J = list;
        this.L = list;
        this.M = list;
        this.O = u1.f692q;
        this.P = list;
        this.Q = f2.f440o;
    }

    public n() {
        this.f589t = -1;
        this.f591v = -1;
        this.f593y = -1;
        this.F = -1;
        this.K = -1;
        this.N = -1;
        this.R = (byte) -1;
        this.S = -1;
        this.f581l = kotlin.reflect.jvm.internal.impl.protobuf.g.f19772i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:9:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public n(kotlin.reflect.jvm.internal.impl.protobuf.i r22, kotlin.reflect.jvm.internal.impl.protobuf.l r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1756
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a9.n.<init>(kotlin.reflect.jvm.internal.impl.protobuf.i, kotlin.reflect.jvm.internal.impl.protobuf.l):void");
    }
}
