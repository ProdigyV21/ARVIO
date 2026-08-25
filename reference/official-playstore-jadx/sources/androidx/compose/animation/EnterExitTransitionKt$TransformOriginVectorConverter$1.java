package androidx.compose.animation;

import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.ui.graphics.TransformOrigin;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/animation/core/AnimationVector2D;", "it", "Landroidx/compose/ui/graphics/TransformOrigin;", "invoke-__ExYCQ", "(J)Landroidx/compose/animation/core/AnimationVector2D;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class EnterExitTransitionKt$TransformOriginVectorConverter$1 extends r implements l<TransformOrigin, AnimationVector2D> {
    public static final EnterExitTransitionKt$TransformOriginVectorConverter$1 INSTANCE = new EnterExitTransitionKt$TransformOriginVectorConverter$1();

    public EnterExitTransitionKt$TransformOriginVectorConverter$1() {
        super(1);
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return m41invoke__ExYCQ(((TransformOrigin) obj).getPackedValue());
    }

    /* JADX INFO: renamed from: invoke-__ExYCQ, reason: not valid java name */
    public final AnimationVector2D m41invoke__ExYCQ(long j10) {
        return new AnimationVector2D(TransformOrigin.m3836getPivotFractionXimpl(j10), TransformOrigin.m3837getPivotFractionYimpl(j10));
    }
}
