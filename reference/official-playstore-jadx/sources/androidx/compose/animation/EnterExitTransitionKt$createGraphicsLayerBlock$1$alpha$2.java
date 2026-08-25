package androidx.compose.animation;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Landroidx/compose/animation/EnterExitState;", "invoke", "(Landroidx/compose/animation/EnterExitState;)Ljava/lang/Float;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class EnterExitTransitionKt$createGraphicsLayerBlock$1$alpha$2 extends r implements l<EnterExitState, Float> {
    final /* synthetic */ EnterTransition $enter;
    final /* synthetic */ ExitTransition $exit;

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
    public EnterExitTransitionKt$createGraphicsLayerBlock$1$alpha$2(EnterTransition enterTransition, ExitTransition exitTransition) {
        super(1);
        this.$enter = enterTransition;
        this.$exit = exitTransition;
    }

    @Override // r7.l
    public final Float invoke(EnterExitState enterExitState) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[enterExitState.ordinal()];
        float alpha = 1.0f;
        if (i10 != 1) {
            if (i10 == 2) {
                Fade fade = this.$enter.getData().getFade();
                if (fade != null) {
                    alpha = fade.getAlpha();
                }
            } else {
                if (i10 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                Fade fade2 = this.$exit.getData().getFade();
                if (fade2 != null) {
                    alpha = fade2.getAlpha();
                }
            }
        }
        return Float.valueOf(alpha);
    }
}
