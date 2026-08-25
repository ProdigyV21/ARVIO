package h2;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.gestures.BringIntoViewSpec;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
public final class d implements BringIntoViewSpec {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f15840a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f15841b;

    public d(b bVar, boolean z) {
        this.f15840a = bVar;
        this.f15841b = z;
    }

    @Override // androidx.compose.foundation.gestures.BringIntoViewSpec
    public final float calculateScrollDistance(float f10, float f11, float f12) {
        if (!this.f15841b) {
            return 0.0f;
        }
        float fAbs = Math.abs((f11 + f10) - f10);
        boolean z = fAbs <= f12;
        this.f15840a.getClass();
        float f13 = (0.3f * f12) - (0.0f * fAbs);
        float f14 = f12 - f13;
        if (z && f14 < fAbs) {
            f13 = f12 - fAbs;
        }
        return f10 - f13;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return p.a(this.f15840a, dVar.f15840a) && this.f15841b == dVar.f15841b;
    }

    @Override // androidx.compose.foundation.gestures.BringIntoViewSpec
    public final /* synthetic */ AnimationSpec getScrollAnimationSpec() {
        return androidx.compose.foundation.gestures.b.a(this);
    }

    public final int hashCode() {
        return (this.f15840a.hashCode() * 31) + (this.f15841b ? 1231 : 1237);
    }
}
