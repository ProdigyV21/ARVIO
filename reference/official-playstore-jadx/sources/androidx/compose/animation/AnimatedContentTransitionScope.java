package androidx.compose.animation;

import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.unit.IntOffset;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import q7.g;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002:\u0001$J\u001e\u0010\u0006\u001a\u00020\u0003*\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H¦\u0004¢\u0006\u0004\b\u0006\u0010\u0007JO\u0010\u0016\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2#\b\u0002\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u000e0\rH&ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015JO\u0010\u001b\u001a\u00020\u00182\u0006\u0010\t\u001a\u00020\b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2#\b\u0002\u0010\u0017\u001a\u001d\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u000e0\rH&ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001f\u001a\u00020\u0018*\u00020\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010#\u001a\u00020 8&X¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"\u0082\u0001\u0001%ø\u0001\u0001\u0082\u0002\r\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u0006&À\u0006\u0001"}, d2 = {"Landroidx/compose/animation/AnimatedContentTransitionScope;", "S", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/animation/ContentTransform;", "Landroidx/compose/animation/SizeTransform;", "sizeTransform", "using", "(Landroidx/compose/animation/ContentTransform;Landroidx/compose/animation/SizeTransform;)Landroidx/compose/animation/ContentTransform;", "Landroidx/compose/animation/AnimatedContentTransitionScope$SlideDirection;", "towards", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Landroidx/compose/ui/unit/IntOffset;", "animationSpec", "Lkotlin/Function1;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "offsetForFullSlide", "initialOffset", "Landroidx/compose/animation/EnterTransition;", "slideIntoContainer-mOhB8PU", "(ILandroidx/compose/animation/core/FiniteAnimationSpec;Lr7/l;)Landroidx/compose/animation/EnterTransition;", "slideIntoContainer", "targetOffset", "Landroidx/compose/animation/ExitTransition;", "slideOutOfContainer-mOhB8PU", "(ILandroidx/compose/animation/core/FiniteAnimationSpec;Lr7/l;)Landroidx/compose/animation/ExitTransition;", "slideOutOfContainer", "Landroidx/compose/animation/ExitTransition$Companion;", "getKeepUntilTransitionsFinished", "(Landroidx/compose/animation/ExitTransition$Companion;)Landroidx/compose/animation/ExitTransition;", "KeepUntilTransitionsFinished", "Landroidx/compose/ui/Alignment;", "getContentAlignment", "()Landroidx/compose/ui/Alignment;", "contentAlignment", "SlideDirection", "Landroidx/compose/animation/AnimatedContentTransitionScopeImpl;", "animation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface AnimatedContentTransitionScope<S> extends Transition.Segment<S> {

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedContentTransitionScope$-CC, reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static ExitTransition a(AnimatedContentTransitionScope animatedContentTransitionScope, ExitTransition.Companion companion) {
            return companion.getKeepUntilTransitionsFinished$animation_release();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ EnterTransition b(AnimatedContentTransitionScope animatedContentTransitionScope, int i10, FiniteAnimationSpec finiteAnimationSpec, l lVar, int i11, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: slideIntoContainer-mOhB8PU");
            }
            if ((i11 & 2) != 0) {
                finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, IntOffset.m5793boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 3, null);
            }
            if ((i11 & 4) != 0) {
                lVar = AnimatedContentTransitionScope$slideIntoContainer$1.INSTANCE;
            }
            return animatedContentTransitionScope.mo6slideIntoContainermOhB8PU(i10, finiteAnimationSpec, lVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ExitTransition c(AnimatedContentTransitionScope animatedContentTransitionScope, int i10, FiniteAnimationSpec finiteAnimationSpec, l lVar, int i11, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: slideOutOfContainer-mOhB8PU");
            }
            if ((i11 & 2) != 0) {
                finiteAnimationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, IntOffset.m5793boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntOffset.INSTANCE)), 3, null);
            }
            if ((i11 & 4) != 0) {
                lVar = AnimatedContentTransitionScope$slideOutOfContainer$1.INSTANCE;
            }
            return animatedContentTransitionScope.mo7slideOutOfContainermOhB8PU(i10, finiteAnimationSpec, lVar);
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\f\u0010\u0005J\u000f\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u0012"}, d2 = {"Landroidx/compose/animation/AnimatedContentTransitionScope$SlideDirection;", "", "value", "", "constructor-impl", "(I)I", "equals", "", "other", "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "Companion", "animation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @g
    public static final class SlideDirection {
        private final int value;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final int Left = m9constructorimpl(0);
        private static final int Right = m9constructorimpl(1);
        private static final int Up = m9constructorimpl(2);
        private static final int Down = m9constructorimpl(3);
        private static final int Start = m9constructorimpl(4);
        private static final int End = m9constructorimpl(5);

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0019\u0010\n\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006R\u0019\u0010\f\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\r\u0010\u0006R\u0019\u0010\u000e\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000f\u0010\u0006R\u0019\u0010\u0010\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0011\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0012"}, d2 = {"Landroidx/compose/animation/AnimatedContentTransitionScope$SlideDirection$Companion;", "", "()V", "Down", "Landroidx/compose/animation/AnimatedContentTransitionScope$SlideDirection;", "getDown-DKzdypw", "()I", "I", "End", "getEnd-DKzdypw", "Left", "getLeft-DKzdypw", "Right", "getRight-DKzdypw", "Start", "getStart-DKzdypw", "Up", "getUp-DKzdypw", "animation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(h hVar) {
                this();
            }

            /* JADX INFO: renamed from: getDown-DKzdypw, reason: not valid java name */
            public final int m15getDownDKzdypw() {
                return SlideDirection.Down;
            }

            /* JADX INFO: renamed from: getEnd-DKzdypw, reason: not valid java name */
            public final int m16getEndDKzdypw() {
                return SlideDirection.End;
            }

            /* JADX INFO: renamed from: getLeft-DKzdypw, reason: not valid java name */
            public final int m17getLeftDKzdypw() {
                return SlideDirection.Left;
            }

            /* JADX INFO: renamed from: getRight-DKzdypw, reason: not valid java name */
            public final int m18getRightDKzdypw() {
                return SlideDirection.Right;
            }

            /* JADX INFO: renamed from: getStart-DKzdypw, reason: not valid java name */
            public final int m19getStartDKzdypw() {
                return SlideDirection.Start;
            }

            /* JADX INFO: renamed from: getUp-DKzdypw, reason: not valid java name */
            public final int m20getUpDKzdypw() {
                return SlideDirection.Up;
            }

            private Companion() {
            }
        }

        private /* synthetic */ SlideDirection(int i10) {
            this.value = i10;
        }

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ SlideDirection m8boximpl(int i10) {
            return new SlideDirection(i10);
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static int m9constructorimpl(int i10) {
            return i10;
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m10equalsimpl(int i10, Object obj) {
            return (obj instanceof SlideDirection) && i10 == ((SlideDirection) obj).getValue();
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m11equalsimpl0(int i10, int i11) {
            return i10 == i11;
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m12hashCodeimpl(int i10) {
            return i10;
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m13toStringimpl(int i10) {
            return m11equalsimpl0(i10, Left) ? "Left" : m11equalsimpl0(i10, Right) ? "Right" : m11equalsimpl0(i10, Up) ? "Up" : m11equalsimpl0(i10, Down) ? "Down" : m11equalsimpl0(i10, Start) ? "Start" : m11equalsimpl0(i10, End) ? "End" : "Invalid";
        }

        public boolean equals(Object obj) {
            return m10equalsimpl(this.value, obj);
        }

        public int hashCode() {
            return m12hashCodeimpl(this.value);
        }

        public String toString() {
            return m13toStringimpl(this.value);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
        public final /* synthetic */ int getValue() {
            return this.value;
        }
    }

    Alignment getContentAlignment();

    ExitTransition getKeepUntilTransitionsFinished(ExitTransition.Companion companion);

    /* JADX INFO: renamed from: slideIntoContainer-mOhB8PU, reason: not valid java name */
    EnterTransition mo6slideIntoContainermOhB8PU(int towards, FiniteAnimationSpec<IntOffset> animationSpec, l<? super Integer, Integer> initialOffset);

    /* JADX INFO: renamed from: slideOutOfContainer-mOhB8PU, reason: not valid java name */
    ExitTransition mo7slideOutOfContainermOhB8PU(int towards, FiniteAnimationSpec<IntOffset> animationSpec, l<? super Integer, Integer> targetOffset);

    ContentTransform using(ContentTransform contentTransform, SizeTransform sizeTransform);
}
