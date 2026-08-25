package androidx.compose.animation;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.ui.unit.IntSize;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001BO\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012<\u0010\u000b\u001a8\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n0\u0004¢\u0006\u0004\b\f\u0010\rJ(\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0016ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013RM\u0010\u000b\u001a8\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n0\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0017"}, d2 = {"Landroidx/compose/animation/SizeTransformImpl;", "Landroidx/compose/animation/SizeTransform;", "", "clip", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/IntSize;", "Lx6/y;", ContentDisposition.Parameters.Name, "initialSize", "targetSize", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "sizeAnimationSpec", "<init>", "(ZLr7/p;)V", "createAnimationSpec-TemP2vQ", "(JJ)Landroidx/compose/animation/core/FiniteAnimationSpec;", "createAnimationSpec", "Z", "getClip", "()Z", "Lr7/p;", "getSizeAnimationSpec", "()Lr7/p;", "animation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class SizeTransformImpl implements SizeTransform {
    private final boolean clip;
    private final p<IntSize, IntSize, FiniteAnimationSpec<IntSize>> sizeAnimationSpec;

    /* JADX WARN: Multi-variable type inference failed */
    public SizeTransformImpl(boolean z, p<? super IntSize, ? super IntSize, ? extends FiniteAnimationSpec<IntSize>> pVar) {
        this.clip = z;
        this.sizeAnimationSpec = pVar;
    }

    @Override // androidx.compose.animation.SizeTransform
    /* JADX INFO: renamed from: createAnimationSpec-TemP2vQ */
    public FiniteAnimationSpec<IntSize> mo78createAnimationSpecTemP2vQ(long initialSize, long targetSize) {
        return (FiniteAnimationSpec) this.sizeAnimationSpec.invoke(IntSize.m5836boximpl(initialSize), IntSize.m5836boximpl(targetSize));
    }

    @Override // androidx.compose.animation.SizeTransform
    public boolean getClip() {
        return this.clip;
    }

    public final p<IntSize, IntSize, FiniteAnimationSpec<IntSize>> getSizeAnimationSpec() {
        return this.sizeAnimationSpec;
    }

    public /* synthetic */ SizeTransformImpl(boolean z, p pVar, int i10, h hVar) {
        this((i10 & 1) != 0 ? true : z, pVar);
    }
}
