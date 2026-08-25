package androidx.vectordrawable.graphics.drawable;

import android.animation.TypeEvaluator;

/* JADX INFO: loaded from: classes3.dex */
public final class i implements TypeEvaluator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public androidx.core.graphics.f[] f6634a;

    @Override // android.animation.TypeEvaluator
    public final Object evaluate(float f10, Object obj, Object obj2) {
        androidx.core.graphics.f[] fVarArr = (androidx.core.graphics.f[]) obj;
        androidx.core.graphics.f[] fVarArr2 = (androidx.core.graphics.f[]) obj2;
        if (!androidx.core.graphics.o.a(fVarArr, fVarArr2)) {
            throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
        }
        if (!androidx.core.graphics.o.a(this.f6634a, fVarArr)) {
            this.f6634a = androidx.core.graphics.o.k(fVarArr);
        }
        for (int i10 = 0; i10 < fVarArr.length; i10++) {
            androidx.core.graphics.f fVar = this.f6634a[i10];
            androidx.core.graphics.f fVar2 = fVarArr[i10];
            androidx.core.graphics.f fVar3 = fVarArr2[i10];
            fVar.getClass();
            fVar.f2101a = fVar2.f2101a;
            int i11 = 0;
            while (true) {
                float[] fArr = fVar2.f2102b;
                if (i11 < fArr.length) {
                    fVar.f2102b[i11] = (fVar3.f2102b[i11] * f10) + ((1.0f - f10) * fArr[i11]);
                    i11++;
                }
            }
        }
        return this.f6634a;
    }
}
