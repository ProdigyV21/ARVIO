package androidx.vectordrawable.graphics.drawable;

/* JADX INFO: loaded from: classes3.dex */
public abstract class p extends o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public androidx.core.graphics.f[] f6657a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f6658b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f6659c;

    public p() {
        this.f6657a = null;
        this.f6659c = 0;
    }

    public androidx.core.graphics.f[] getPathData() {
        return this.f6657a;
    }

    public String getPathName() {
        return this.f6658b;
    }

    public void setPathData(androidx.core.graphics.f[] fVarArr) {
        if (!androidx.core.graphics.o.a(this.f6657a, fVarArr)) {
            this.f6657a = androidx.core.graphics.o.k(fVarArr);
            return;
        }
        androidx.core.graphics.f[] fVarArr2 = this.f6657a;
        for (int i10 = 0; i10 < fVarArr.length; i10++) {
            fVarArr2[i10].f2101a = fVarArr[i10].f2101a;
            int i11 = 0;
            while (true) {
                float[] fArr = fVarArr[i10].f2102b;
                if (i11 < fArr.length) {
                    fVarArr2[i10].f2102b[i11] = fArr[i11];
                    i11++;
                }
            }
        }
    }

    public p(p pVar) {
        this.f6657a = null;
        this.f6659c = 0;
        this.f6658b = pVar.f6658b;
        this.f6657a = androidx.core.graphics.o.k(pVar.f6657a);
    }
}
