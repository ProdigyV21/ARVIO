package androidx.compose.animation;

import androidx.compose.ui.graphics.TransformOrigin;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/ui/graphics/TransformOrigin;", "it", "Landroidx/compose/animation/EnterExitState;", "invoke-LIALnN8", "(Landroidx/compose/animation/EnterExitState;)J"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class EnterExitTransitionKt$createGraphicsLayerBlock$1$transformOrigin$2 extends r implements l<EnterExitState, TransformOrigin> {
    final /* synthetic */ EnterTransition $enter;
    final /* synthetic */ ExitTransition $exit;
    final /* synthetic */ TransformOrigin $transformOriginWhenVisible;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EnterExitState.values().length];
            try {
                iArr[EnterExitState.Visible.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[EnterExitState.PreEnter.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[EnterExitState.PostExit.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnterExitTransitionKt$createGraphicsLayerBlock$1$transformOrigin$2(TransformOrigin transformOrigin, EnterTransition enterTransition, ExitTransition exitTransition) {
        super(1);
        this.$transformOriginWhenVisible = transformOrigin;
        this.$enter = enterTransition;
        this.$exit = exitTransition;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return TransformOrigin.m3828boximpl(m43invokeLIALnN8((EnterExitState) obj));
    }

    /* JADX INFO: renamed from: invoke-LIALnN8, reason: not valid java name */
    public final long m43invokeLIALnN8(EnterExitState enterExitState) {
        TransformOrigin transformOriginM3828boximpl;
        int i10 = WhenMappings.$EnumSwitchMapping$0[enterExitState.ordinal()];
        if (i10 != 1) {
            transformOriginM3828boximpl = null;
            if (i10 == 2) {
                Scale scale = this.$enter.getData().getScale();
                if (scale != null || (scale = this.$exit.getData().getScale()) != null) {
                    transformOriginM3828boximpl = TransformOrigin.m3828boximpl(scale.m66getTransformOriginSzJe1aQ());
                }
            } else {
                if (i10 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                Scale scale2 = this.$exit.getData().getScale();
                if (scale2 != null || (scale2 = this.$enter.getData().getScale()) != null) {
                    transformOriginM3828boximpl = TransformOrigin.m3828boximpl(scale2.m66getTransformOriginSzJe1aQ());
                }
            }
        } else {
            transformOriginM3828boximpl = this.$transformOriginWhenVisible;
        }
        return transformOriginM3828boximpl != null ? transformOriginM3828boximpl.getPackedValue() : TransformOrigin.INSTANCE.m3841getCenterSzJe1aQ();
    }
}
