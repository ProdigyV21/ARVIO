package xb;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public final class f0 implements Comparable {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final String f22733l = File.separator;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final m f22734i;

    public f0(m mVar) {
        this.f22734i = mVar;
    }

    public final ArrayList a() {
        ArrayList arrayList = new ArrayList();
        int iA = yb.f.a(this);
        m mVar = this.f22734i;
        if (iA == -1) {
            iA = 0;
        } else if (iA < mVar.f() && mVar.m(iA) == 92) {
            iA++;
        }
        int iF = mVar.f();
        int i10 = iA;
        while (iA < iF) {
            if (mVar.m(iA) == 47 || mVar.m(iA) == 92) {
                arrayList.add(mVar.q(i10, iA));
                i10 = iA + 1;
            }
            iA++;
        }
        if (i10 < mVar.f()) {
            arrayList.add(mVar.q(i10, mVar.f()));
        }
        return arrayList;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.f22734i.compareTo(((f0) obj).f22734i);
    }

    public final f0 d() {
        m mVar = yb.f.f23031d;
        m mVar2 = this.f22734i;
        if (kotlin.jvm.internal.p.a(mVar2, mVar)) {
            return null;
        }
        m mVar3 = yb.f.f23028a;
        if (kotlin.jvm.internal.p.a(mVar2, mVar3)) {
            return null;
        }
        m mVar4 = yb.f.f23029b;
        if (kotlin.jvm.internal.p.a(mVar2, mVar4)) {
            return null;
        }
        m mVar5 = yb.f.f23032e;
        int iF = mVar2.f();
        byte[] bArr = mVar5.f22773i;
        if (mVar2.p(iF - bArr.length, mVar5, bArr.length) && (mVar2.f() == 2 || mVar2.p(mVar2.f() - 3, mVar3, 1) || mVar2.p(mVar2.f() - 3, mVar4, 1))) {
            return null;
        }
        mVar2.getClass();
        int iN = mVar2.n(mVar3.l());
        if (iN == -1) {
            mVar2.getClass();
            iN = mVar2.n(mVar4.l());
        }
        if (iN == 2 && k() != null) {
            if (mVar2.f() == 3) {
                return null;
            }
            return new f0(m.r(mVar2, 0, 3, 1));
        }
        if (iN == 1 && mVar2.p(0, mVar4, mVar4.f())) {
            return null;
        }
        if (iN != -1 || k() == null) {
            return iN == -1 ? new f0(mVar) : iN == 0 ? new f0(m.r(mVar2, 0, 1, 1)) : new f0(m.r(mVar2, 0, iN, 1));
        }
        if (mVar2.f() == 2) {
            return null;
        }
        return new f0(m.r(mVar2, 0, 2, 1));
    }

    public final boolean equals(Object obj) {
        return (obj instanceof f0) && kotlin.jvm.internal.p.a(((f0) obj).f22734i, this.f22734i);
    }

    public final f0 f(f0 f0Var) {
        int iA = yb.f.a(this);
        m mVar = this.f22734i;
        f0 f0Var2 = iA == -1 ? null : new f0(mVar.q(0, iA));
        f0Var.getClass();
        m mVar2 = f0Var.f22734i;
        int iA2 = yb.f.a(f0Var);
        if (!kotlin.jvm.internal.p.a(f0Var2, iA2 != -1 ? new f0(mVar2.q(0, iA2)) : null)) {
            throw new IllegalArgumentException(("Paths of different roots cannot be relative to each other: " + this + " and " + f0Var).toString());
        }
        ArrayList arrayListA = a();
        ArrayList arrayListA2 = f0Var.a();
        int iMin = Math.min(arrayListA.size(), arrayListA2.size());
        int i10 = 0;
        while (i10 < iMin && kotlin.jvm.internal.p.a(arrayListA.get(i10), arrayListA2.get(i10))) {
            i10++;
        }
        if (i10 == iMin && mVar.f() == mVar2.f()) {
            return v1.i.i(".");
        }
        if (arrayListA2.subList(i10, arrayListA2.size()).indexOf(yb.f.f23032e) != -1) {
            throw new IllegalArgumentException(("Impossible relative path to resolve: " + this + " and " + f0Var).toString());
        }
        j jVar = new j();
        m mVarC = yb.f.c(f0Var);
        if (mVarC == null && (mVarC = yb.f.c(this)) == null) {
            mVarC = yb.f.f(f22733l);
        }
        int size = arrayListA2.size();
        for (int i11 = i10; i11 < size; i11++) {
            jVar.J0(yb.f.f23032e);
            jVar.J0(mVarC);
        }
        int size2 = arrayListA.size();
        while (i10 < size2) {
            jVar.J0((m) arrayListA.get(i10));
            jVar.J0(mVarC);
            i10++;
        }
        return yb.f.d(jVar, false);
    }

    public final int hashCode() {
        return this.f22734i.hashCode();
    }

    public final f0 i(String str) {
        j jVar = new j();
        jVar.S0(str);
        return yb.f.b(this, yb.f.d(jVar, false), false);
    }

    public final Path j() {
        return Paths.get(this.f22734i.t(), new String[0]);
    }

    public final Character k() {
        m mVar = yb.f.f23028a;
        m mVar2 = this.f22734i;
        if (m.k(mVar2, mVar) != -1 || mVar2.f() < 2 || mVar2.m(1) != 58) {
            return null;
        }
        char cM = (char) mVar2.m(0);
        if (('a' > cM || cM >= '{') && ('A' > cM || cM >= '[')) {
            return null;
        }
        return Character.valueOf(cM);
    }

    public final File toFile() {
        return new File(this.f22734i.t());
    }

    public final String toString() {
        return this.f22734i.t();
    }
}
