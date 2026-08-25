package androidx.tv.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.foundation.gestures.ScrollScope;

/* JADX INFO: loaded from: classes3.dex */
public final class x3 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6531i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ float f6532l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f6533m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f6534n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x3(float f10, kotlin.jvm.internal.h0 h0Var, ScrollScope scrollScope) {
        super(1);
        this.f6532l = f10;
        this.f6533m = h0Var;
        this.f6534n = scrollScope;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0021 A[PHI: r2
      0x0021: PHI (r2v8 float) = (r2v4 float), (r2v11 float) binds: [B:14:0x0034, B:7:0x001e] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // r7.l
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invoke(java.lang.Object r6) {
        /*
            r5 = this;
            int r0 = r5.f6531i
            switch(r0) {
                case 0: goto L61;
                default: goto L5;
            }
        L5:
            androidx.compose.animation.core.AnimationScope r6 = (androidx.compose.animation.core.AnimationScope) r6
            java.lang.Object r0 = r5.f6533m
            kotlin.jvm.internal.h0 r0 = (kotlin.jvm.internal.h0) r0
            float r1 = r5.f6532l
            r2 = 0
            int r3 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r3 <= 0) goto L24
            java.lang.Object r2 = r6.getValue()
            java.lang.Number r2 = (java.lang.Number) r2
            float r2 = r2.floatValue()
            int r3 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
            if (r3 <= 0) goto L21
            goto L22
        L21:
            r1 = r2
        L22:
            r2 = r1
            goto L37
        L24:
            int r3 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r3 >= 0) goto L37
            java.lang.Object r2 = r6.getValue()
            java.lang.Number r2 = (java.lang.Number) r2
            float r2 = r2.floatValue()
            int r3 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
            if (r3 >= 0) goto L21
            goto L22
        L37:
            float r1 = r0.f19742i
            float r1 = r2 - r1
            java.lang.Object r3 = r5.f6534n
            androidx.compose.foundation.gestures.ScrollScope r3 = (androidx.compose.foundation.gestures.ScrollScope) r3
            float r3 = r3.scrollBy(r1)
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 != 0) goto L56
            java.lang.Object r3 = r6.getValue()
            java.lang.Number r3 = (java.lang.Number) r3
            float r3 = r3.floatValue()
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 != 0) goto L56
            goto L59
        L56:
            r6.cancelAnimation()
        L59:
            float r6 = r0.f19742i
            float r6 = r6 + r1
            r0.f19742i = r6
            x6.t0 r6 = x6.t0.f22605a
            return r6
        L61:
            androidx.compose.runtime.DisposableEffectScope r6 = (androidx.compose.runtime.DisposableEffectScope) r6
            java.lang.Object r6 = r5.f6533m
            androidx.compose.animation.core.Animatable r6 = (androidx.compose.animation.core.Animatable) r6
            java.lang.Object r0 = r6.getTargetValue()
            java.lang.Number r0 = (java.lang.Number) r0
            float r0 = r0.floatValue()
            float r1 = r5.f6532l
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 != 0) goto L78
            goto L88
        L78:
            java.lang.Object r0 = r5.f6534n
            ka.k0 r0 = (ka.k0) r0
            androidx.tv.material3.w3 r2 = new androidx.tv.material3.w3
            r3 = 0
            r4 = 0
            r2.<init>(r6, r1, r4, r3)
            r6 = 3
            r1 = 0
            ka.m0.p(r0, r4, r1, r2, r6)
        L88:
            androidx.navigation.compose.s r6 = new androidx.navigation.compose.s
            r0 = 1
            r6.<init>(r0)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.x3.invoke(java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x3(Animatable animatable, float f10, ka.k0 k0Var) {
        super(1);
        this.f6533m = animatable;
        this.f6532l = f10;
        this.f6534n = k0Var;
    }
}
