package androidx.compose.animation;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import io.ktor.http.ContentDisposition;
import java.util.Collections;
import kotlin.Metadata;
import kotlin.jvm.internal.i;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a)\u0010\u0005\u001a\u00020\u00042\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0005\u0010\u0006\u001a)\u0010\t\u001a\u00020\b2\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\t\u0010\n\u001aB\u0010\u0012\u001a\u00020\u00042\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00002!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u000b0\fH\u0007¢\u0006\u0004\b\u0012\u0010\u0013\u001aB\u0010\u0015\u001a\u00020\b2\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00002!\u0010\u0014\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u000b0\fH\u0007¢\u0006\u0004\b\u0015\u0010\u0016\u001a6\u0010\u001c\u001a\u00020\u00042\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0017\u001a\u00020\u00012\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001a6\u0010 \u001a\u00020\b2\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u001d\u001a\u00020\u00012\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001aX\u0010&\u001a\u00020\u00042\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\r0\u00002\b\b\u0002\u0010\"\u001a\u00020!2\b\b\u0002\u0010$\u001a\u00020#2#\b\u0002\u0010%\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\r0\fH\u0007¢\u0006\u0004\b&\u0010'\u001aX\u0010*\u001a\u00020\b2\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\r0\u00002\b\b\u0002\u0010(\u001a\u00020!2\b\b\u0002\u0010$\u001a\u00020#2#\b\u0002\u0010)\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\r0\fH\u0007¢\u0006\u0004\b*\u0010+\u001aX\u00100\u001a\u00020\u00042\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\r0\u00002\b\b\u0002\u0010\"\u001a\u00020,2\b\b\u0002\u0010$\u001a\u00020#2#\b\u0002\u0010/\u001a\u001d\u0012\u0013\u0012\u00110-¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020-0\fH\u0007¢\u0006\u0004\b0\u00101\u001aX\u00105\u001a\u00020\u00042\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\r0\u00002\b\b\u0002\u0010\"\u001a\u0002022\b\b\u0002\u0010$\u001a\u00020#2#\b\u0002\u00104\u001a\u001d\u0012\u0013\u0012\u00110-¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020-0\fH\u0007¢\u0006\u0004\b5\u00106\u001aX\u00108\u001a\u00020\b2\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\r0\u00002\b\b\u0002\u0010(\u001a\u00020,2\b\b\u0002\u0010$\u001a\u00020#2#\b\u0002\u00107\u001a\u001d\u0012\u0013\u0012\u00110-¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020-0\fH\u0007¢\u0006\u0004\b8\u00109\u001aX\u0010;\u001a\u00020\b2\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\r0\u00002\b\b\u0002\u0010(\u001a\u0002022\b\b\u0002\u0010$\u001a\u00020#2#\b\u0002\u0010:\u001a\u001d\u0012\u0013\u0012\u00110-¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020-0\fH\u0007¢\u0006\u0004\b;\u0010<\u001aD\u0010>\u001a\u00020\u00042\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00002#\b\u0002\u0010=\u001a\u001d\u0012\u0013\u0012\u00110-¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020-0\fH\u0007¢\u0006\u0004\b>\u0010\u0013\u001aD\u0010@\u001a\u00020\u00042\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00002#\b\u0002\u0010?\u001a\u001d\u0012\u0013\u0012\u00110-¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020-0\fH\u0007¢\u0006\u0004\b@\u0010\u0013\u001aD\u0010B\u001a\u00020\b2\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00002#\b\u0002\u0010A\u001a\u001d\u0012\u0013\u0012\u00110-¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020-0\fH\u0007¢\u0006\u0004\bB\u0010\u0016\u001aD\u0010D\u001a\u00020\b2\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00002#\b\u0002\u0010C\u001a\u001d\u0012\u0013\u0012\u00110-¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020-0\fH\u0007¢\u0006\u0004\bD\u0010\u0016\u001a'\u0010J\u001a\u00020\u00042\u0006\u0010F\u001a\u00020E2\u000e\u0010I\u001a\n\u0012\u0006\b\u0001\u0012\u00020H0GH\u0000¢\u0006\u0004\bJ\u0010K\u001a'\u0010L\u001a\u00020\b2\u0006\u0010F\u001a\u00020E2\u000e\u0010I\u001a\n\u0012\u0006\b\u0001\u0012\u00020H0GH\u0000¢\u0006\u0004\bL\u0010M\u001a\u0013\u0010N\u001a\u00020!*\u00020,H\u0002¢\u0006\u0004\bN\u0010O\u001a\u0013\u0010N\u001a\u00020!*\u000202H\u0002¢\u0006\u0004\bN\u0010P\u001a&\u0010Q\u001a\f\u0012\u0006\b\u0001\u0012\u00020H\u0018\u00010G*\u00020\u00042\u0006\u0010F\u001a\u00020EH\u0080\u0002¢\u0006\u0004\bQ\u0010R\u001a&\u0010Q\u001a\f\u0012\u0006\b\u0001\u0012\u00020H\u0018\u00010G*\u00020\b2\u0006\u0010F\u001a\u00020EH\u0080\u0002¢\u0006\u0004\bQ\u0010S\u001a1\u0010[\u001a\u00020Z*\b\u0012\u0004\u0012\u00020U0T2\u0006\u0010V\u001a\u00020\u00042\u0006\u0010W\u001a\u00020\b2\u0006\u0010Y\u001a\u00020XH\u0001¢\u0006\u0004\b[\u0010\\\u001a!\u0010]\u001a\u00020\u0004*\b\u0012\u0004\u0012\u00020U0T2\u0006\u0010V\u001a\u00020\u0004H\u0001¢\u0006\u0004\b]\u0010^\u001a!\u0010_\u001a\u00020\b*\b\u0012\u0004\u0012\u00020U0T2\u0006\u0010W\u001a\u00020\bH\u0001¢\u0006\u0004\b_\u0010`\u001a1\u0010b\u001a\u00020a*\b\u0012\u0004\u0012\u00020U0T2\u0006\u0010V\u001a\u00020\u00042\u0006\u0010W\u001a\u00020\b2\u0006\u0010Y\u001a\u00020XH\u0003¢\u0006\u0004\bb\u0010c\" \u0010f\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020e0d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bf\u0010g\"\u001a\u0010i\u001a\b\u0012\u0004\u0012\u00020\u00010h8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bi\u0010j\"\u001a\u0010k\u001a\b\u0012\u0004\u0012\u00020\u000b0h8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bk\u0010j\"\u001a\u0010l\u001a\b\u0012\u0004\u0012\u00020\r0h8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bl\u0010j\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006o²\u0006\u000e\u0010m\u001a\u00020\u00048\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010n\u001a\u00020\b8\n@\nX\u008a\u008e\u0002"}, d2 = {"Landroidx/compose/animation/core/FiniteAnimationSpec;", "", "animationSpec", "initialAlpha", "Landroidx/compose/animation/EnterTransition;", "fadeIn", "(Landroidx/compose/animation/core/FiniteAnimationSpec;F)Landroidx/compose/animation/EnterTransition;", "targetAlpha", "Landroidx/compose/animation/ExitTransition;", "fadeOut", "(Landroidx/compose/animation/core/FiniteAnimationSpec;F)Landroidx/compose/animation/ExitTransition;", "Landroidx/compose/ui/unit/IntOffset;", "Lkotlin/Function1;", "Landroidx/compose/ui/unit/IntSize;", "Lx6/y;", ContentDisposition.Parameters.Name, "fullSize", "initialOffset", "slideIn", "(Landroidx/compose/animation/core/FiniteAnimationSpec;Lr7/l;)Landroidx/compose/animation/EnterTransition;", "targetOffset", "slideOut", "(Landroidx/compose/animation/core/FiniteAnimationSpec;Lr7/l;)Landroidx/compose/animation/ExitTransition;", "initialScale", "Landroidx/compose/ui/graphics/TransformOrigin;", "transformOrigin", "scaleIn-L8ZKh-E", "(Landroidx/compose/animation/core/FiniteAnimationSpec;FJ)Landroidx/compose/animation/EnterTransition;", "scaleIn", "targetScale", "scaleOut-L8ZKh-E", "(Landroidx/compose/animation/core/FiniteAnimationSpec;FJ)Landroidx/compose/animation/ExitTransition;", "scaleOut", "Landroidx/compose/ui/Alignment;", "expandFrom", "", "clip", "initialSize", "expandIn", "(Landroidx/compose/animation/core/FiniteAnimationSpec;Landroidx/compose/ui/Alignment;ZLr7/l;)Landroidx/compose/animation/EnterTransition;", "shrinkTowards", "targetSize", "shrinkOut", "(Landroidx/compose/animation/core/FiniteAnimationSpec;Landroidx/compose/ui/Alignment;ZLr7/l;)Landroidx/compose/animation/ExitTransition;", "Landroidx/compose/ui/Alignment$Horizontal;", "", "fullWidth", "initialWidth", "expandHorizontally", "(Landroidx/compose/animation/core/FiniteAnimationSpec;Landroidx/compose/ui/Alignment$Horizontal;ZLr7/l;)Landroidx/compose/animation/EnterTransition;", "Landroidx/compose/ui/Alignment$Vertical;", "fullHeight", "initialHeight", "expandVertically", "(Landroidx/compose/animation/core/FiniteAnimationSpec;Landroidx/compose/ui/Alignment$Vertical;ZLr7/l;)Landroidx/compose/animation/EnterTransition;", "targetWidth", "shrinkHorizontally", "(Landroidx/compose/animation/core/FiniteAnimationSpec;Landroidx/compose/ui/Alignment$Horizontal;ZLr7/l;)Landroidx/compose/animation/ExitTransition;", "targetHeight", "shrinkVertically", "(Landroidx/compose/animation/core/FiniteAnimationSpec;Landroidx/compose/ui/Alignment$Vertical;ZLr7/l;)Landroidx/compose/animation/ExitTransition;", "initialOffsetX", "slideInHorizontally", "initialOffsetY", "slideInVertically", "targetOffsetX", "slideOutHorizontally", "targetOffsetY", "slideOutVertically", "", "key", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/ui/Modifier$Node;", "node", "EnterTransition", "(Ljava/lang/Object;Landroidx/compose/ui/node/ModifierNodeElement;)Landroidx/compose/animation/EnterTransition;", "ExitTransition", "(Ljava/lang/Object;Landroidx/compose/ui/node/ModifierNodeElement;)Landroidx/compose/animation/ExitTransition;", "toAlignment", "(Landroidx/compose/ui/Alignment$Horizontal;)Landroidx/compose/ui/Alignment;", "(Landroidx/compose/ui/Alignment$Vertical;)Landroidx/compose/ui/Alignment;", "get", "(Landroidx/compose/animation/EnterTransition;Ljava/lang/Object;)Landroidx/compose/ui/node/ModifierNodeElement;", "(Landroidx/compose/animation/ExitTransition;Ljava/lang/Object;)Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/animation/core/Transition;", "Landroidx/compose/animation/EnterExitState;", "enter", "exit", "", "label", "Landroidx/compose/ui/Modifier;", "createModifier", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "trackActiveEnter", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/EnterTransition;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/EnterTransition;", "trackActiveExit", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/ExitTransition;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/ExitTransition;", "Landroidx/compose/animation/GraphicsLayerBlockForEnterExit;", "createGraphicsLayerBlock", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/GraphicsLayerBlockForEnterExit;", "Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/animation/core/AnimationVector2D;", "TransformOriginVectorConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/animation/core/SpringSpec;", "DefaultAlphaAndScaleSpring", "Landroidx/compose/animation/core/SpringSpec;", "DefaultOffsetAnimationSpec", "DefaultSizeAnimationSpec", "activeEnter", "activeExit", "animation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EnterExitTransitionKt {
    private static final TwoWayConverter<TransformOrigin, AnimationVector2D> TransformOriginVectorConverter = VectorConvertersKt.TwoWayConverter(EnterExitTransitionKt$TransformOriginVectorConverter$1.INSTANCE, EnterExitTransitionKt$TransformOriginVectorConverter$2.INSTANCE);
    private static final SpringSpec<Float> DefaultAlphaAndScaleSpring = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
    private static final SpringSpec<IntOffset> DefaultOffsetAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m5793boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
    private static final SpringSpec<IntSize> DefaultSizeAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m5836boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);

    /* JADX INFO: renamed from: androidx.compose.animation.EnterExitTransitionKt$expandHorizontally$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "invoke", "(I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<Integer, Integer> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final Integer invoke(int i10) {
            return 0;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.EnterExitTransitionKt$expandHorizontally$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/IntSize;", "it", "invoke-mzRDjE0", "(J)J"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements l<IntSize, IntSize> {
        final /* synthetic */ l<Integer, Integer> $initialWidth;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(l<? super Integer, Integer> lVar) {
            super(1);
            this.$initialWidth = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return IntSize.m5836boximpl(m44invokemzRDjE0(((IntSize) obj).getPackedValue()));
        }

        /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
        public final long m44invokemzRDjE0(long j10) {
            return IntSizeKt.IntSize(((Number) this.$initialWidth.invoke(Integer.valueOf(IntSize.m5844getWidthimpl(j10)))).intValue(), IntSize.m5843getHeightimpl(j10));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.EnterExitTransitionKt$expandIn$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/IntSize;", "it", "invoke-mzRDjE0", "(J)J"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02501 extends r implements l<IntSize, IntSize> {
        public static final C02501 INSTANCE = new C02501();

        public C02501() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return IntSize.m5836boximpl(m45invokemzRDjE0(((IntSize) obj).getPackedValue()));
        }

        /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
        public final long m45invokemzRDjE0(long j10) {
            return IntSizeKt.IntSize(0, 0);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.EnterExitTransitionKt$expandVertically$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "invoke", "(I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02511 extends r implements l<Integer, Integer> {
        public static final C02511 INSTANCE = new C02511();

        public C02511() {
            super(1);
        }

        public final Integer invoke(int i10) {
            return 0;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.EnterExitTransitionKt$expandVertically$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/IntSize;", "it", "invoke-mzRDjE0", "(J)J"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02522 extends r implements l<IntSize, IntSize> {
        final /* synthetic */ l<Integer, Integer> $initialHeight;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02522(l<? super Integer, Integer> lVar) {
            super(1);
            this.$initialHeight = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return IntSize.m5836boximpl(m46invokemzRDjE0(((IntSize) obj).getPackedValue()));
        }

        /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
        public final long m46invokemzRDjE0(long j10) {
            return IntSizeKt.IntSize(IntSize.m5844getWidthimpl(j10), ((Number) this.$initialHeight.invoke(Integer.valueOf(IntSize.m5843getHeightimpl(j10)))).intValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.EnterExitTransitionKt$shrinkHorizontally$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "invoke", "(I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02531 extends r implements l<Integer, Integer> {
        public static final C02531 INSTANCE = new C02531();

        public C02531() {
            super(1);
        }

        public final Integer invoke(int i10) {
            return 0;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.EnterExitTransitionKt$shrinkHorizontally$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/IntSize;", "it", "invoke-mzRDjE0", "(J)J"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02542 extends r implements l<IntSize, IntSize> {
        final /* synthetic */ l<Integer, Integer> $targetWidth;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02542(l<? super Integer, Integer> lVar) {
            super(1);
            this.$targetWidth = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return IntSize.m5836boximpl(m47invokemzRDjE0(((IntSize) obj).getPackedValue()));
        }

        /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
        public final long m47invokemzRDjE0(long j10) {
            return IntSizeKt.IntSize(((Number) this.$targetWidth.invoke(Integer.valueOf(IntSize.m5844getWidthimpl(j10)))).intValue(), IntSize.m5843getHeightimpl(j10));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.EnterExitTransitionKt$shrinkOut$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/IntSize;", "it", "invoke-mzRDjE0", "(J)J"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02551 extends r implements l<IntSize, IntSize> {
        public static final C02551 INSTANCE = new C02551();

        public C02551() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return IntSize.m5836boximpl(m48invokemzRDjE0(((IntSize) obj).getPackedValue()));
        }

        /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
        public final long m48invokemzRDjE0(long j10) {
            return IntSizeKt.IntSize(0, 0);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.EnterExitTransitionKt$shrinkVertically$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "invoke", "(I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02561 extends r implements l<Integer, Integer> {
        public static final C02561 INSTANCE = new C02561();

        public C02561() {
            super(1);
        }

        public final Integer invoke(int i10) {
            return 0;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.EnterExitTransitionKt$shrinkVertically$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/IntSize;", "it", "invoke-mzRDjE0", "(J)J"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02572 extends r implements l<IntSize, IntSize> {
        final /* synthetic */ l<Integer, Integer> $targetHeight;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02572(l<? super Integer, Integer> lVar) {
            super(1);
            this.$targetHeight = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return IntSize.m5836boximpl(m49invokemzRDjE0(((IntSize) obj).getPackedValue()));
        }

        /* JADX INFO: renamed from: invoke-mzRDjE0, reason: not valid java name */
        public final long m49invokemzRDjE0(long j10) {
            return IntSizeKt.IntSize(IntSize.m5844getWidthimpl(j10), ((Number) this.$targetHeight.invoke(Integer.valueOf(IntSize.m5843getHeightimpl(j10)))).intValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.EnterExitTransitionKt$slideInHorizontally$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "invoke", "(I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02581 extends r implements l<Integer, Integer> {
        public static final C02581 INSTANCE = new C02581();

        public C02581() {
            super(1);
        }

        public final Integer invoke(int i10) {
            return Integer.valueOf((-i10) / 2);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.EnterExitTransitionKt$slideInHorizontally$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/IntOffset;", "it", "Landroidx/compose/ui/unit/IntSize;", "invoke-mHKZG7I", "(J)J"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02592 extends r implements l<IntSize, IntOffset> {
        final /* synthetic */ l<Integer, Integer> $initialOffsetX;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02592(l<? super Integer, Integer> lVar) {
            super(1);
            this.$initialOffsetX = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return IntOffset.m5793boximpl(m50invokemHKZG7I(((IntSize) obj).getPackedValue()));
        }

        /* JADX INFO: renamed from: invoke-mHKZG7I, reason: not valid java name */
        public final long m50invokemHKZG7I(long j10) {
            return IntOffsetKt.IntOffset(((Number) this.$initialOffsetX.invoke(Integer.valueOf(IntSize.m5844getWidthimpl(j10)))).intValue(), 0);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.EnterExitTransitionKt$slideInVertically$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "invoke", "(I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02601 extends r implements l<Integer, Integer> {
        public static final C02601 INSTANCE = new C02601();

        public C02601() {
            super(1);
        }

        public final Integer invoke(int i10) {
            return Integer.valueOf((-i10) / 2);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.EnterExitTransitionKt$slideInVertically$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/IntOffset;", "it", "Landroidx/compose/ui/unit/IntSize;", "invoke-mHKZG7I", "(J)J"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02612 extends r implements l<IntSize, IntOffset> {
        final /* synthetic */ l<Integer, Integer> $initialOffsetY;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02612(l<? super Integer, Integer> lVar) {
            super(1);
            this.$initialOffsetY = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return IntOffset.m5793boximpl(m51invokemHKZG7I(((IntSize) obj).getPackedValue()));
        }

        /* JADX INFO: renamed from: invoke-mHKZG7I, reason: not valid java name */
        public final long m51invokemHKZG7I(long j10) {
            return IntOffsetKt.IntOffset(0, ((Number) this.$initialOffsetY.invoke(Integer.valueOf(IntSize.m5843getHeightimpl(j10)))).intValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.EnterExitTransitionKt$slideOutHorizontally$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "invoke", "(I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02621 extends r implements l<Integer, Integer> {
        public static final C02621 INSTANCE = new C02621();

        public C02621() {
            super(1);
        }

        public final Integer invoke(int i10) {
            return Integer.valueOf((-i10) / 2);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.EnterExitTransitionKt$slideOutHorizontally$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/IntOffset;", "it", "Landroidx/compose/ui/unit/IntSize;", "invoke-mHKZG7I", "(J)J"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02632 extends r implements l<IntSize, IntOffset> {
        final /* synthetic */ l<Integer, Integer> $targetOffsetX;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02632(l<? super Integer, Integer> lVar) {
            super(1);
            this.$targetOffsetX = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return IntOffset.m5793boximpl(m52invokemHKZG7I(((IntSize) obj).getPackedValue()));
        }

        /* JADX INFO: renamed from: invoke-mHKZG7I, reason: not valid java name */
        public final long m52invokemHKZG7I(long j10) {
            return IntOffsetKt.IntOffset(((Number) this.$targetOffsetX.invoke(Integer.valueOf(IntSize.m5844getWidthimpl(j10)))).intValue(), 0);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.EnterExitTransitionKt$slideOutVertically$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "invoke", "(I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02641 extends r implements l<Integer, Integer> {
        public static final C02641 INSTANCE = new C02641();

        public C02641() {
            super(1);
        }

        public final Integer invoke(int i10) {
            return Integer.valueOf((-i10) / 2);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).intValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.EnterExitTransitionKt$slideOutVertically$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/ui/unit/IntOffset;", "it", "Landroidx/compose/ui/unit/IntSize;", "invoke-mHKZG7I", "(J)J"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02652 extends r implements l<IntSize, IntOffset> {
        final /* synthetic */ l<Integer, Integer> $targetOffsetY;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C02652(l<? super Integer, Integer> lVar) {
            super(1);
            this.$targetOffsetY = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return IntOffset.m5793boximpl(m53invokemHKZG7I(((IntSize) obj).getPackedValue()));
        }

        /* JADX INFO: renamed from: invoke-mHKZG7I, reason: not valid java name */
        public final long m53invokemHKZG7I(long j10) {
            return IntOffsetKt.IntOffset(0, ((Number) this.$targetOffsetY.invoke(Integer.valueOf(IntSize.m5843getHeightimpl(j10)))).intValue());
        }
    }

    public static final EnterTransition EnterTransition(Object obj, ModifierNodeElement<? extends Modifier.Node> modifierNodeElement) {
        return new EnterTransitionImpl(new TransitionData(null, null, null, null, false, Collections.singletonMap(obj, modifierNodeElement), 31, null));
    }

    public static final ExitTransition ExitTransition(Object obj, ModifierNodeElement<? extends Modifier.Node> modifierNodeElement) {
        return new ExitTransitionImpl(new TransitionData(null, null, null, null, false, Collections.singletonMap(obj, modifierNodeElement), 31, null));
    }

    private static final GraphicsLayerBlockForEnterExit createGraphicsLayerBlock(final Transition<EnterExitState> transition, final EnterTransition enterTransition, final ExitTransition exitTransition, String str, Composer composer, int i10) {
        final Transition.DeferredAnimation deferredAnimation;
        final Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation;
        Composer composer2 = composer;
        composer2.startReplaceableGroup(642253525);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(642253525, i10, -1, "androidx.compose.animation.createGraphicsLayerBlock (EnterExitTransition.kt:942)");
        }
        boolean z = (enterTransition.getData().getFade() == null && exitTransition.getData().getFade() == null) ? false : true;
        boolean z5 = (enterTransition.getData().getScale() == null && exitTransition.getData().getScale() == null) ? false : true;
        composer2.startReplaceableGroup(-1158245383);
        i iVar = i.f19743a;
        if (z) {
            TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(iVar);
            composer2.startReplaceableGroup(-492369756);
            Object objRememberedValue = composer2.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = str + " alpha";
                composer2.updateRememberedValue(objRememberedValue);
            }
            composer2.endReplaceableGroup();
            Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation2 = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, vectorConverter, (String) objRememberedValue, composer2, (i10 & 14) | 448, 0);
            composer2 = composer2;
            deferredAnimation = deferredAnimationCreateDeferredAnimation2;
        } else {
            deferredAnimation = null;
        }
        composer2.endReplaceableGroup();
        composer2.startReplaceableGroup(-1158245186);
        if (z5) {
            TwoWayConverter<Float, AnimationVector1D> vectorConverter2 = VectorConvertersKt.getVectorConverter(iVar);
            composer2.startReplaceableGroup(-492369756);
            Object objRememberedValue2 = composer2.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = str + " scale";
                composer2.updateRememberedValue(objRememberedValue2);
            }
            composer2.endReplaceableGroup();
            deferredAnimationCreateDeferredAnimation = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, vectorConverter2, (String) objRememberedValue2, composer2, (i10 & 14) | 448, 0);
        } else {
            deferredAnimationCreateDeferredAnimation = null;
        }
        composer.endReplaceableGroup();
        final Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation3 = z5 ? androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, TransformOriginVectorConverter, "TransformOriginInterruptionHandling", composer, (i10 & 14) | 448, 0) : null;
        GraphicsLayerBlockForEnterExit graphicsLayerBlockForEnterExit = new GraphicsLayerBlockForEnterExit() { // from class: androidx.compose.animation.a
            @Override // androidx.compose.animation.GraphicsLayerBlockForEnterExit
            public final l init() {
                return EnterExitTransitionKt.createGraphicsLayerBlock$lambda$11(deferredAnimation, deferredAnimationCreateDeferredAnimation, transition, enterTransition, exitTransition, deferredAnimationCreateDeferredAnimation3);
            }
        };
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return graphicsLayerBlockForEnterExit;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final r7.l createGraphicsLayerBlock$lambda$11(androidx.compose.animation.core.Transition.DeferredAnimation r3, androidx.compose.animation.core.Transition.DeferredAnimation r4, androidx.compose.animation.core.Transition r5, androidx.compose.animation.EnterTransition r6, androidx.compose.animation.ExitTransition r7, androidx.compose.animation.core.Transition.DeferredAnimation r8) {
        /*
            r0 = 0
            if (r3 == 0) goto L12
            androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$alpha$1 r1 = new androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$alpha$1
            r1.<init>(r6, r7)
            androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$alpha$2 r2 = new androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$alpha$2
            r2.<init>(r6, r7)
            androidx.compose.runtime.State r3 = r3.animate(r1, r2)
            goto L13
        L12:
            r3 = r0
        L13:
            if (r4 == 0) goto L24
            androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$scale$1 r1 = new androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$scale$1
            r1.<init>(r6, r7)
            androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$scale$2 r2 = new androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$scale$2
            r2.<init>(r6, r7)
            androidx.compose.runtime.State r4 = r4.animate(r1, r2)
            goto L25
        L24:
            r4 = r0
        L25:
            java.lang.Object r5 = r5.getCurrentState()
            androidx.compose.animation.EnterExitState r1 = androidx.compose.animation.EnterExitState.PreEnter
            if (r5 != r1) goto L4d
            androidx.compose.animation.TransitionData r5 = r6.getData()
            androidx.compose.animation.Scale r5 = r5.getScale()
            if (r5 == 0) goto L40
        L37:
            long r1 = r5.m66getTransformOriginSzJe1aQ()
            androidx.compose.ui.graphics.TransformOrigin r5 = androidx.compose.ui.graphics.TransformOrigin.m3828boximpl(r1)
            goto L6b
        L40:
            androidx.compose.animation.TransitionData r5 = r7.getData()
            androidx.compose.animation.Scale r5 = r5.getScale()
            if (r5 == 0) goto L4b
            goto L37
        L4b:
            r5 = r0
            goto L6b
        L4d:
            androidx.compose.animation.TransitionData r5 = r7.getData()
            androidx.compose.animation.Scale r5 = r5.getScale()
            if (r5 == 0) goto L60
        L57:
            long r1 = r5.m66getTransformOriginSzJe1aQ()
            androidx.compose.ui.graphics.TransformOrigin r5 = androidx.compose.ui.graphics.TransformOrigin.m3828boximpl(r1)
            goto L6b
        L60:
            androidx.compose.animation.TransitionData r5 = r6.getData()
            androidx.compose.animation.Scale r5 = r5.getScale()
            if (r5 == 0) goto L4b
            goto L57
        L6b:
            if (r8 == 0) goto L78
            androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$transformOrigin$1 r0 = androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$transformOrigin$1.INSTANCE
            androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$transformOrigin$2 r1 = new androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$transformOrigin$2
            r1.<init>(r5, r6, r7)
            androidx.compose.runtime.State r0 = r8.animate(r0, r1)
        L78:
            androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$block$1 r5 = new androidx.compose.animation.EnterExitTransitionKt$createGraphicsLayerBlock$1$block$1
            r5.<init>(r3, r4, r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.EnterExitTransitionKt.createGraphicsLayerBlock$lambda$11(androidx.compose.animation.core.Transition$DeferredAnimation, androidx.compose.animation.core.Transition$DeferredAnimation, androidx.compose.animation.core.Transition, androidx.compose.animation.EnterTransition, androidx.compose.animation.ExitTransition, androidx.compose.animation.core.Transition$DeferredAnimation):r7.l");
    }

    public static final Modifier createModifier(Transition<EnterExitState> transition, EnterTransition enterTransition, ExitTransition exitTransition, String str, Composer composer, int i10) {
        Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation;
        Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation2;
        ChangeSize changeSize;
        composer.startReplaceableGroup(914000546);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(914000546, i10, -1, "androidx.compose.animation.createModifier (EnterExitTransition.kt:855)");
        }
        int i11 = i10 & 14;
        EnterTransition enterTransitionTrackActiveEnter = trackActiveEnter(transition, enterTransition, composer, i10 & 126);
        ExitTransition exitTransitionTrackActiveExit = trackActiveExit(transition, exitTransition, composer, ((i10 >> 3) & 112) | i11);
        boolean z = (enterTransitionTrackActiveEnter.getData().getSlide() == null && exitTransitionTrackActiveExit.getData().getSlide() == null) ? false : true;
        boolean z5 = (enterTransitionTrackActiveEnter.getData().getChangeSize() == null && exitTransitionTrackActiveExit.getData().getChangeSize() == null) ? false : true;
        composer.startReplaceableGroup(1657242209);
        Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation3 = null;
        if (z) {
            TwoWayConverter<IntOffset, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(IntOffset.INSTANCE);
            composer.startReplaceableGroup(-492369756);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = str + " slide";
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            deferredAnimationCreateDeferredAnimation = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, vectorConverter, (String) objRememberedValue, composer, i11 | 448, 0);
        } else {
            deferredAnimationCreateDeferredAnimation = null;
        }
        composer.endReplaceableGroup();
        composer.startReplaceableGroup(1657242379);
        if (z5) {
            TwoWayConverter<IntSize, AnimationVector2D> vectorConverter2 = VectorConvertersKt.getVectorConverter(IntSize.INSTANCE);
            composer.startReplaceableGroup(-492369756);
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = str + " shrink/expand";
                composer.updateRememberedValue(objRememberedValue2);
            }
            composer.endReplaceableGroup();
            deferredAnimationCreateDeferredAnimation2 = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, vectorConverter2, (String) objRememberedValue2, composer, i11 | 448, 0);
        } else {
            deferredAnimationCreateDeferredAnimation2 = null;
        }
        composer.endReplaceableGroup();
        composer.startReplaceableGroup(1657242547);
        if (z5) {
            TwoWayConverter<IntOffset, AnimationVector2D> vectorConverter3 = VectorConvertersKt.getVectorConverter(IntOffset.INSTANCE);
            composer.startReplaceableGroup(-492369756);
            Object objRememberedValue3 = composer.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = str + " InterruptionHandlingOffset";
                composer.updateRememberedValue(objRememberedValue3);
            }
            composer.endReplaceableGroup();
            deferredAnimationCreateDeferredAnimation3 = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(transition, vectorConverter3, (String) objRememberedValue3, composer, i11 | 448, 0);
        }
        composer.endReplaceableGroup();
        ChangeSize changeSize2 = enterTransitionTrackActiveEnter.getData().getChangeSize();
        Modifier modifierThen = GraphicsLayerModifierKt.m3633graphicsLayerAp8cVGQ$default(Modifier.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, null, !(((changeSize2 == null || changeSize2.getClip()) && ((changeSize = exitTransitionTrackActiveExit.getData().getChangeSize()) == null || changeSize.getClip()) && z5) ? false : true), null, 0L, 0L, 0, 126975, null).then(new EnterExitTransitionElement(transition, deferredAnimationCreateDeferredAnimation2, deferredAnimationCreateDeferredAnimation3, deferredAnimationCreateDeferredAnimation, enterTransitionTrackActiveEnter, exitTransitionTrackActiveExit, createGraphicsLayerBlock(transition, enterTransitionTrackActiveEnter, exitTransitionTrackActiveExit, str, composer, i10 & 7182)));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return modifierThen;
    }

    public static final EnterTransition expandHorizontally(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment.Horizontal horizontal, boolean z, l<? super Integer, Integer> lVar) {
        return expandIn(finiteAnimationSpec, toAlignment(horizontal), z, new AnonymousClass2(lVar));
    }

    public static /* synthetic */ EnterTransition expandHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Horizontal horizontal, boolean z, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m5836boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i10 & 2) != 0) {
            horizontal = Alignment.INSTANCE.getEnd();
        }
        if ((i10 & 4) != 0) {
            z = true;
        }
        if ((i10 & 8) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        return expandHorizontally(finiteAnimationSpec, horizontal, z, lVar);
    }

    public static final EnterTransition expandIn(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment alignment, boolean z, l<? super IntSize, IntSize> lVar) {
        return new EnterTransitionImpl(new TransitionData(null, null, new ChangeSize(alignment, lVar, finiteAnimationSpec, z), null, false, null, 59, null));
    }

    public static /* synthetic */ EnterTransition expandIn$default(FiniteAnimationSpec finiteAnimationSpec, Alignment alignment, boolean z, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m5836boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i10 & 2) != 0) {
            alignment = Alignment.INSTANCE.getBottomEnd();
        }
        if ((i10 & 4) != 0) {
            z = true;
        }
        if ((i10 & 8) != 0) {
            lVar = C02501.INSTANCE;
        }
        return expandIn(finiteAnimationSpec, alignment, z, lVar);
    }

    public static final EnterTransition expandVertically(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment.Vertical vertical, boolean z, l<? super Integer, Integer> lVar) {
        return expandIn(finiteAnimationSpec, toAlignment(vertical), z, new C02522(lVar));
    }

    public static /* synthetic */ EnterTransition expandVertically$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Vertical vertical, boolean z, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m5836boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i10 & 2) != 0) {
            vertical = Alignment.INSTANCE.getBottom();
        }
        if ((i10 & 4) != 0) {
            z = true;
        }
        if ((i10 & 8) != 0) {
            lVar = C02511.INSTANCE;
        }
        return expandVertically(finiteAnimationSpec, vertical, z, lVar);
    }

    public static final EnterTransition fadeIn(FiniteAnimationSpec<Float> finiteAnimationSpec, float f10) {
        return new EnterTransitionImpl(new TransitionData(new Fade(f10, finiteAnimationSpec), null, null, null, false, null, 62, null));
    }

    public static /* synthetic */ EnterTransition fadeIn$default(FiniteAnimationSpec finiteAnimationSpec, float f10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i10 & 2) != 0) {
            f10 = 0.0f;
        }
        return fadeIn(finiteAnimationSpec, f10);
    }

    public static final ExitTransition fadeOut(FiniteAnimationSpec<Float> finiteAnimationSpec, float f10) {
        return new ExitTransitionImpl(new TransitionData(new Fade(f10, finiteAnimationSpec), null, null, null, false, null, 62, null));
    }

    public static /* synthetic */ ExitTransition fadeOut$default(FiniteAnimationSpec finiteAnimationSpec, float f10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i10 & 2) != 0) {
            f10 = 0.0f;
        }
        return fadeOut(finiteAnimationSpec, f10);
    }

    public static final ModifierNodeElement<? extends Modifier.Node> get(EnterTransition enterTransition, Object obj) {
        return enterTransition.getData().getEffectsMap().get(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: scaleIn-L8ZKh-E, reason: not valid java name */
    public static final EnterTransition m37scaleInL8ZKhE(FiniteAnimationSpec<Float> finiteAnimationSpec, float f10, long j10) {
        return new EnterTransitionImpl(new TransitionData(null, null, null, new Scale(f10, j10, finiteAnimationSpec, null), false, 0 == true ? 1 : 0, 55, null));
    }

    /* JADX INFO: renamed from: scaleIn-L8ZKh-E$default, reason: not valid java name */
    public static /* synthetic */ EnterTransition m38scaleInL8ZKhE$default(FiniteAnimationSpec finiteAnimationSpec, float f10, long j10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i10 & 2) != 0) {
            f10 = 0.0f;
        }
        if ((i10 & 4) != 0) {
            j10 = TransformOrigin.INSTANCE.m3841getCenterSzJe1aQ();
        }
        return m37scaleInL8ZKhE(finiteAnimationSpec, f10, j10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: scaleOut-L8ZKh-E, reason: not valid java name */
    public static final ExitTransition m39scaleOutL8ZKhE(FiniteAnimationSpec<Float> finiteAnimationSpec, float f10, long j10) {
        return new ExitTransitionImpl(new TransitionData(null, null, null, new Scale(f10, j10, finiteAnimationSpec, null), false, 0 == true ? 1 : 0, 55, null));
    }

    /* JADX INFO: renamed from: scaleOut-L8ZKh-E$default, reason: not valid java name */
    public static /* synthetic */ ExitTransition m40scaleOutL8ZKhE$default(FiniteAnimationSpec finiteAnimationSpec, float f10, long j10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null);
        }
        if ((i10 & 2) != 0) {
            f10 = 0.0f;
        }
        if ((i10 & 4) != 0) {
            j10 = TransformOrigin.INSTANCE.m3841getCenterSzJe1aQ();
        }
        return m39scaleOutL8ZKhE(finiteAnimationSpec, f10, j10);
    }

    public static final ExitTransition shrinkHorizontally(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment.Horizontal horizontal, boolean z, l<? super Integer, Integer> lVar) {
        return shrinkOut(finiteAnimationSpec, toAlignment(horizontal), z, new C02542(lVar));
    }

    public static /* synthetic */ ExitTransition shrinkHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Horizontal horizontal, boolean z, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m5836boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i10 & 2) != 0) {
            horizontal = Alignment.INSTANCE.getEnd();
        }
        if ((i10 & 4) != 0) {
            z = true;
        }
        if ((i10 & 8) != 0) {
            lVar = C02531.INSTANCE;
        }
        return shrinkHorizontally(finiteAnimationSpec, horizontal, z, lVar);
    }

    public static final ExitTransition shrinkOut(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment alignment, boolean z, l<? super IntSize, IntSize> lVar) {
        return new ExitTransitionImpl(new TransitionData(null, null, new ChangeSize(alignment, lVar, finiteAnimationSpec, z), null, false, null, 59, null));
    }

    public static /* synthetic */ ExitTransition shrinkOut$default(FiniteAnimationSpec finiteAnimationSpec, Alignment alignment, boolean z, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m5836boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i10 & 2) != 0) {
            alignment = Alignment.INSTANCE.getBottomEnd();
        }
        if ((i10 & 4) != 0) {
            z = true;
        }
        if ((i10 & 8) != 0) {
            lVar = C02551.INSTANCE;
        }
        return shrinkOut(finiteAnimationSpec, alignment, z, lVar);
    }

    public static final ExitTransition shrinkVertically(FiniteAnimationSpec<IntSize> finiteAnimationSpec, Alignment.Vertical vertical, boolean z, l<? super Integer, Integer> lVar) {
        return shrinkOut(finiteAnimationSpec, toAlignment(vertical), z, new C02572(lVar));
    }

    public static /* synthetic */ ExitTransition shrinkVertically$default(FiniteAnimationSpec finiteAnimationSpec, Alignment.Vertical vertical, boolean z, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m5836boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
        if ((i10 & 2) != 0) {
            vertical = Alignment.INSTANCE.getBottom();
        }
        if ((i10 & 4) != 0) {
            z = true;
        }
        if ((i10 & 8) != 0) {
            lVar = C02561.INSTANCE;
        }
        return shrinkVertically(finiteAnimationSpec, vertical, z, lVar);
    }

    public static final EnterTransition slideIn(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, l<? super IntSize, IntOffset> lVar) {
        return new EnterTransitionImpl(new TransitionData(null, new Slide(lVar, finiteAnimationSpec), null, null, false, null, 61, null));
    }

    public static /* synthetic */ EnterTransition slideIn$default(FiniteAnimationSpec finiteAnimationSpec, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m5793boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        return slideIn(finiteAnimationSpec, lVar);
    }

    public static final EnterTransition slideInHorizontally(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, l<? super Integer, Integer> lVar) {
        return slideIn(finiteAnimationSpec, new C02592(lVar));
    }

    public static /* synthetic */ EnterTransition slideInHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m5793boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        if ((i10 & 2) != 0) {
            lVar = C02581.INSTANCE;
        }
        return slideInHorizontally(finiteAnimationSpec, lVar);
    }

    public static final EnterTransition slideInVertically(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, l<? super Integer, Integer> lVar) {
        return slideIn(finiteAnimationSpec, new C02612(lVar));
    }

    public static /* synthetic */ EnterTransition slideInVertically$default(FiniteAnimationSpec finiteAnimationSpec, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m5793boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        if ((i10 & 2) != 0) {
            lVar = C02601.INSTANCE;
        }
        return slideInVertically(finiteAnimationSpec, lVar);
    }

    public static final ExitTransition slideOut(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, l<? super IntSize, IntOffset> lVar) {
        return new ExitTransitionImpl(new TransitionData(null, new Slide(lVar, finiteAnimationSpec), null, null, false, null, 61, null));
    }

    public static /* synthetic */ ExitTransition slideOut$default(FiniteAnimationSpec finiteAnimationSpec, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m5793boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        return slideOut(finiteAnimationSpec, lVar);
    }

    public static final ExitTransition slideOutHorizontally(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, l<? super Integer, Integer> lVar) {
        return slideOut(finiteAnimationSpec, new C02632(lVar));
    }

    public static /* synthetic */ ExitTransition slideOutHorizontally$default(FiniteAnimationSpec finiteAnimationSpec, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m5793boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        if ((i10 & 2) != 0) {
            lVar = C02621.INSTANCE;
        }
        return slideOutHorizontally(finiteAnimationSpec, lVar);
    }

    public static final ExitTransition slideOutVertically(FiniteAnimationSpec<IntOffset> finiteAnimationSpec, l<? super Integer, Integer> lVar) {
        return slideOut(finiteAnimationSpec, new C02652(lVar));
    }

    public static /* synthetic */ ExitTransition slideOutVertically$default(FiniteAnimationSpec finiteAnimationSpec, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, IntOffset.m5793boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 1, null);
        }
        if ((i10 & 2) != 0) {
            lVar = C02641.INSTANCE;
        }
        return slideOutVertically(finiteAnimationSpec, lVar);
    }

    private static final Alignment toAlignment(Alignment.Horizontal horizontal) {
        Alignment.Companion companion = Alignment.INSTANCE;
        return p.a(horizontal, companion.getStart()) ? companion.getCenterStart() : p.a(horizontal, companion.getEnd()) ? companion.getCenterEnd() : companion.getCenter();
    }

    public static final EnterTransition trackActiveEnter(Transition<EnterExitState> transition, EnterTransition enterTransition, Composer composer, int i10) {
        composer.startReplaceableGroup(21614502);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(21614502, i10, -1, "androidx.compose.animation.trackActiveEnter (EnterExitTransition.kt:894)");
        }
        composer.startReplaceableGroup(1157296644);
        boolean zChanged = composer.changed(transition);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(enterTransition, null, 2, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        MutableState mutableState = (MutableState) objRememberedValue;
        if (transition.getCurrentState() == transition.getTargetState() && transition.getCurrentState() == EnterExitState.Visible) {
            if (transition.isSeeking()) {
                mutableState.setValue(enterTransition);
            } else {
                mutableState.setValue(EnterTransition.INSTANCE.getNone());
            }
        } else if (transition.getTargetState() == EnterExitState.Visible) {
            mutableState.setValue(trackActiveEnter$lambda$4(mutableState).plus(enterTransition));
        }
        EnterTransition enterTransitionTrackActiveEnter$lambda$4 = trackActiveEnter$lambda$4(mutableState);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return enterTransitionTrackActiveEnter$lambda$4;
    }

    private static final EnterTransition trackActiveEnter$lambda$4(MutableState<EnterTransition> mutableState) {
        return mutableState.getValue();
    }

    public static final ExitTransition trackActiveExit(Transition<EnterExitState> transition, ExitTransition exitTransition, Composer composer, int i10) {
        composer.startReplaceableGroup(-1363864804);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1363864804, i10, -1, "androidx.compose.animation.trackActiveExit (EnterExitTransition.kt:914)");
        }
        composer.startReplaceableGroup(1157296644);
        boolean zChanged = composer.changed(transition);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(exitTransition, null, 2, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        MutableState mutableState = (MutableState) objRememberedValue;
        if (transition.getCurrentState() == transition.getTargetState() && transition.getCurrentState() == EnterExitState.Visible) {
            if (transition.isSeeking()) {
                mutableState.setValue(exitTransition);
            } else {
                mutableState.setValue(ExitTransition.INSTANCE.getNone());
            }
        } else if (transition.getTargetState() != EnterExitState.Visible) {
            mutableState.setValue(trackActiveExit$lambda$7(mutableState).plus(exitTransition));
        }
        ExitTransition exitTransitionTrackActiveExit$lambda$7 = trackActiveExit$lambda$7(mutableState);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return exitTransitionTrackActiveExit$lambda$7;
    }

    private static final ExitTransition trackActiveExit$lambda$7(MutableState<ExitTransition> mutableState) {
        return mutableState.getValue();
    }

    public static final ModifierNodeElement<? extends Modifier.Node> get(ExitTransition exitTransition, Object obj) {
        return exitTransition.getData().getEffectsMap().get(obj);
    }

    private static final Alignment toAlignment(Alignment.Vertical vertical) {
        Alignment.Companion companion = Alignment.INSTANCE;
        return p.a(vertical, companion.getTop()) ? companion.getTopCenter() : p.a(vertical, companion.getBottom()) ? companion.getBottomCenter() : companion.getCenter();
    }
}
