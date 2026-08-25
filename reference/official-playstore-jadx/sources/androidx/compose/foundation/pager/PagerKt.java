package androidx.compose.foundation.pager;

import a0.c;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpecKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.FinalSnappingItem;
import androidx.compose.foundation.gestures.snapping.LazyListSnapLayoutInfoProviderKt;
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider;
import androidx.compose.foundation.gestures.snapping.SnapPositionInLayoutKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import com.arflix.tv.data.repository.g;
import d7.k;
import io.ktor.http.ContentDisposition;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import qb.d;
import r7.l;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\r\u001aØ\u0001\u0010$\u001a\u00020\u001e2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00102%\b\u0002\u0010\u0018\u001a\u001f\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u001921\u0010!\u001a-\u0012\u0004\u0012\u00020\u001c\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e0\u001b¢\u0006\u0002\b\u001f¢\u0006\u0002\b H\u0007ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001aØ\u0001\u0010)\u001a\u00020\u001e2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010&\u001a\u00020%2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00102%\b\u0002\u0010\u0018\u001a\u001f\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u001921\u0010!\u001a-\u0012\u0004\u0012\u00020\u001c\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e0\u001b¢\u0006\u0002\b\u001f¢\u0006\u0002\b H\u0007ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a5\u00102\u001a\u0002012\u0006\u0010*\u001a\u00020\u00002\u0006\u0010,\u001a\u00020+2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020.0-2\u0006\u00100\u001a\u00020.H\u0002¢\u0006\u0004\b2\u00103\u001a#\u00105\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u00104\u001a\u00020\u0010H\u0001¢\u0006\u0004\b5\u00106\u001a\u001e\u0010:\u001a\u00020\u001e2\f\u00109\u001a\b\u0012\u0004\u0012\u00020807H\u0082\b¢\u0006\u0004\b:\u0010;\u001a\u0013\u0010<\u001a\u00020\u0010*\u00020\u0000H\u0002¢\u0006\u0004\b<\u0010=\u001a\u0013\u0010>\u001a\u00020.*\u00020\u0000H\u0002¢\u0006\u0004\b>\u0010?\"\u0014\u0010@\u001a\u00020\b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b@\u0010A\"\u0014\u0010B\u001a\u00020\u00108\u0000X\u0080T¢\u0006\u0006\n\u0004\bB\u0010C\"\u0014\u0010D\u001a\u00020\u00108\u0002X\u0082T¢\u0006\u0006\n\u0004\bD\u0010C\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006E"}, d2 = {"Landroidx/compose/foundation/pager/PagerState;", "state", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/foundation/layout/PaddingValues;", "contentPadding", "Landroidx/compose/foundation/pager/PageSize;", "pageSize", "", "beyondBoundsPageCount", "Landroidx/compose/ui/unit/Dp;", "pageSpacing", "Landroidx/compose/ui/Alignment$Vertical;", "verticalAlignment", "Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;", "flingBehavior", "", "userScrollEnabled", "reverseLayout", "Lkotlin/Function1;", "Lx6/y;", ContentDisposition.Parameters.Name, "index", "", "key", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "pageNestedScrollConnection", "Lkotlin/Function2;", "Landroidx/compose/foundation/pager/PagerScope;", "page", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "pageContent", "HorizontalPager-xYaah8o", "(Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/pager/PageSize;IFLandroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;ZZLr7/l;Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;Lr7/r;Landroidx/compose/runtime/Composer;III)V", "HorizontalPager", "Landroidx/compose/ui/Alignment$Horizontal;", "horizontalAlignment", "VerticalPager-xYaah8o", "(Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/pager/PageSize;IFLandroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;ZZLr7/l;Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;Lr7/r;Landroidx/compose/runtime/Composer;III)V", "VerticalPager", "pagerState", "Landroidx/compose/foundation/pager/PagerSnapDistance;", "pagerSnapDistance", "Landroidx/compose/animation/core/DecayAnimationSpec;", "", "decayAnimationSpec", "snapPositionalThreshold", "Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;", "SnapLayoutInfoProvider", "(Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/pager/PagerSnapDistance;Landroidx/compose/animation/core/DecayAnimationSpec;F)Landroidx/compose/foundation/gestures/snapping/SnapLayoutInfoProvider;", "isVertical", "pagerSemantics", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/pager/PagerState;ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "Lkotlin/Function0;", "", "generateMsg", "debugLog", "(Lr7/a;)V", "isScrollingForward", "(Landroidx/compose/foundation/pager/PagerState;)Z", "dragGestureDelta", "(Landroidx/compose/foundation/pager/PagerState;)F", "LowVelocityAnimationDefaultDuration", "I", "PagerDebugEnable", "Z", "DEBUG", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PagerKt {
    private static final boolean DEBUG = false;
    private static final int LowVelocityAnimationDefaultDuration = 500;
    public static final boolean PagerDebugEnable = false;

    /* JADX INFO: renamed from: androidx.compose.foundation.pager.PagerKt$pagerSemantics$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C03751 extends r implements l<SemanticsPropertyReceiver, t0> {
        final /* synthetic */ boolean $isVertical;
        final /* synthetic */ k0 $scope;
        final /* synthetic */ PagerState $state;

        /* JADX INFO: renamed from: androidx.compose.foundation.pager.PagerKt$pagerSemantics$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class C00361 extends r implements r7.a<Boolean> {
            final /* synthetic */ k0 $scope;
            final /* synthetic */ PagerState $state;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00361(PagerState pagerState, k0 k0Var) {
                super(0);
                this.$state = pagerState;
                this.$scope = k0Var;
            }

            @Override // r7.a
            public final Boolean invoke() {
                return Boolean.valueOf(PagerKt.pagerSemantics$performBackwardPaging(this.$state, this.$scope));
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.pager.PagerKt$pagerSemantics$1$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class AnonymousClass2 extends r implements r7.a<Boolean> {
            final /* synthetic */ k0 $scope;
            final /* synthetic */ PagerState $state;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(PagerState pagerState, k0 k0Var) {
                super(0);
                this.$state = pagerState;
                this.$scope = k0Var;
            }

            @Override // r7.a
            public final Boolean invoke() {
                return Boolean.valueOf(PagerKt.pagerSemantics$performForwardPaging(this.$state, this.$scope));
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.pager.PagerKt$pagerSemantics$1$3, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class AnonymousClass3 extends r implements r7.a<Boolean> {
            final /* synthetic */ k0 $scope;
            final /* synthetic */ PagerState $state;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(PagerState pagerState, k0 k0Var) {
                super(0);
                this.$state = pagerState;
                this.$scope = k0Var;
            }

            @Override // r7.a
            public final Boolean invoke() {
                return Boolean.valueOf(PagerKt.pagerSemantics$performBackwardPaging(this.$state, this.$scope));
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.pager.PagerKt$pagerSemantics$1$4, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class AnonymousClass4 extends r implements r7.a<Boolean> {
            final /* synthetic */ k0 $scope;
            final /* synthetic */ PagerState $state;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(PagerState pagerState, k0 k0Var) {
                super(0);
                this.$state = pagerState;
                this.$scope = k0Var;
            }

            @Override // r7.a
            public final Boolean invoke() {
                return Boolean.valueOf(PagerKt.pagerSemantics$performForwardPaging(this.$state, this.$scope));
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C03751(boolean z, PagerState pagerState, k0 k0Var) {
            super(1);
            this.$isVertical = z;
            this.$state = pagerState;
            this.$scope = k0Var;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SemanticsPropertyReceiver) obj);
            return t0.f22605a;
        }

        public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            if (this.$isVertical) {
                SemanticsPropertiesKt.pageUp$default(semanticsPropertyReceiver, null, new C00361(this.$state, this.$scope), 1, null);
                SemanticsPropertiesKt.pageDown$default(semanticsPropertyReceiver, null, new AnonymousClass2(this.$state, this.$scope), 1, null);
            } else {
                SemanticsPropertiesKt.pageLeft$default(semanticsPropertyReceiver, null, new AnonymousClass3(this.$state, this.$scope), 1, null);
                SemanticsPropertiesKt.pageRight$default(semanticsPropertyReceiver, null, new AnonymousClass4(this.$state, this.$scope), 1, null);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:208:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0104  */
    /* JADX INFO: renamed from: HorizontalPager-xYaah8o, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m755HorizontalPagerxYaah8o(androidx.compose.foundation.pager.PagerState r32, androidx.compose.ui.Modifier r33, androidx.compose.foundation.layout.PaddingValues r34, androidx.compose.foundation.pager.PageSize r35, int r36, float r37, androidx.compose.ui.Alignment.Vertical r38, androidx.compose.foundation.gestures.snapping.SnapFlingBehavior r39, boolean r40, boolean r41, r7.l<? super java.lang.Integer, ? extends java.lang.Object> r42, androidx.compose.ui.input.nestedscroll.NestedScrollConnection r43, r7.r<? super androidx.compose.foundation.pager.PagerScope, ? super java.lang.Integer, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r44, androidx.compose.runtime.Composer r45, int r46, int r47, int r48) {
        /*
            Method dump skipped, instruction units count: 891
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.PagerKt.m755HorizontalPagerxYaah8o(androidx.compose.foundation.pager.PagerState, androidx.compose.ui.Modifier, androidx.compose.foundation.layout.PaddingValues, androidx.compose.foundation.pager.PageSize, int, float, androidx.compose.ui.Alignment$Vertical, androidx.compose.foundation.gestures.snapping.SnapFlingBehavior, boolean, boolean, r7.l, androidx.compose.ui.input.nestedscroll.NestedScrollConnection, r7.r, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SnapLayoutInfoProvider SnapLayoutInfoProvider(final PagerState pagerState, final PagerSnapDistance pagerSnapDistance, final DecayAnimationSpec<Float> decayAnimationSpec, final float f10) {
        return new SnapLayoutInfoProvider() { // from class: androidx.compose.foundation.pager.PagerKt.SnapLayoutInfoProvider.1
            private final x searchForSnappingBounds() {
                int pageSpacing$foundation_release = pagerState.getPageSpacing$foundation_release() + pagerState.getPageSize$foundation_release();
                int currentPage = pagerState.getCurrentPage();
                int iCalculateCurrentPageLayoutOffset = PagerMeasurePolicyKt.calculateCurrentPageLayoutOffset(pagerState, pageSpacing$foundation_release);
                int size = getLayoutInfo().getVisiblePagesInfo().size() / 2;
                int i10 = currentPage;
                int i11 = iCalculateCurrentPageLayoutOffset;
                float f11 = Float.NEGATIVE_INFINITY;
                float f12 = Float.POSITIVE_INFINITY;
                while (true) {
                    int i12 = currentPage - size;
                    if (i12 < 0) {
                        i12 = 0;
                    }
                    if (i10 < i12) {
                        break;
                    }
                    float fCalculateDistanceToDesiredSnapPosition = SnapPositionInLayoutKt.calculateDistanceToDesiredSnapPosition(PagerLayoutInfoKt.getMainAxisViewportSize(getLayoutInfo()), getLayoutInfo().getBeforeContentPadding(), getLayoutInfo().getAfterContentPadding(), getLayoutInfo().getPageSize(), i11, i10, PagerStateKt.getSnapAlignmentStartToStart());
                    if (fCalculateDistanceToDesiredSnapPosition <= 0.0f && fCalculateDistanceToDesiredSnapPosition > f11) {
                        f11 = fCalculateDistanceToDesiredSnapPosition;
                    }
                    if (fCalculateDistanceToDesiredSnapPosition >= 0.0f && fCalculateDistanceToDesiredSnapPosition < f12) {
                        f12 = fCalculateDistanceToDesiredSnapPosition;
                    }
                    i11 -= pageSpacing$foundation_release;
                    i10--;
                }
                int i13 = currentPage + 1;
                int i14 = iCalculateCurrentPageLayoutOffset + pageSpacing$foundation_release;
                while (true) {
                    int i15 = currentPage + size;
                    int pageCount = pagerState.getPageCount() - 1;
                    if (i15 > pageCount) {
                        i15 = pageCount;
                    }
                    if (i13 > i15) {
                        break;
                    }
                    int i16 = i13;
                    float fCalculateDistanceToDesiredSnapPosition2 = SnapPositionInLayoutKt.calculateDistanceToDesiredSnapPosition(PagerLayoutInfoKt.getMainAxisViewportSize(getLayoutInfo()), getLayoutInfo().getBeforeContentPadding(), getLayoutInfo().getAfterContentPadding(), getLayoutInfo().getPageSize(), i14, i16, PagerStateKt.getSnapAlignmentStartToStart());
                    if (fCalculateDistanceToDesiredSnapPosition2 >= 0.0f && fCalculateDistanceToDesiredSnapPosition2 < f12) {
                        f12 = fCalculateDistanceToDesiredSnapPosition2;
                    }
                    if (fCalculateDistanceToDesiredSnapPosition2 <= 0.0f && fCalculateDistanceToDesiredSnapPosition2 > f11) {
                        f11 = fCalculateDistanceToDesiredSnapPosition2;
                    }
                    i14 += pageSpacing$foundation_release;
                    i13 = i16 + 1;
                }
                if (f11 == Float.NEGATIVE_INFINITY) {
                    f11 = f12;
                }
                if (f12 == Float.POSITIVE_INFINITY) {
                    f12 = f11;
                }
                return new x(Float.valueOf(f11), Float.valueOf(f12));
            }

            @Override // androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
            public float calculateApproachOffset(float initialVelocity) {
                int pageSpacing$foundation_release = pagerState.getPageSpacing$foundation_release() + pagerState.getPageSize$foundation_release();
                float fCalculateTargetValue = DecayAnimationSpecKt.calculateTargetValue(decayAnimationSpec, 0.0f, initialVelocity);
                int firstVisiblePage = initialVelocity < 0.0f ? pagerState.getFirstVisiblePage() + 1 : pagerState.getFirstVisiblePage();
                int iAbs = Math.abs((d.n(pagerSnapDistance.calculateTargetPage(firstVisiblePage, d.n(((int) (fCalculateTargetValue / pageSpacing$foundation_release)) + firstVisiblePage, 0, pagerState.getPageCount()), initialVelocity, pagerState.getPageSize$foundation_release(), pagerState.getPageSpacing$foundation_release()), 0, pagerState.getPageCount()) - firstVisiblePage) * pageSpacing$foundation_release) - pageSpacing$foundation_release;
                int i10 = iAbs >= 0 ? iAbs : 0;
                return i10 == 0 ? i10 : i10 * Math.signum(initialVelocity);
            }

            @Override // androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
            public float calculateSnappingOffset(float currentVelocity) {
                x xVarSearchForSnappingBounds = searchForSnappingBounds();
                float fFloatValue = ((Number) xVarSearchForSnappingBounds.f22608i).floatValue();
                float fFloatValue2 = ((Number) xVarSearchForSnappingBounds.f22609l).floatValue();
                boolean zIsScrollingForward = PagerKt.isScrollingForward(pagerState);
                float fDragGestureDelta = (PagerKt.dragGestureDelta(pagerState) / getLayoutInfo().getPageSize()) - ((int) r3);
                int iCalculateFinalSnappingItem = LazyListSnapLayoutInfoProviderKt.calculateFinalSnappingItem(pagerState.getDensity(), currentVelocity);
                FinalSnappingItem.Companion companion = FinalSnappingItem.INSTANCE;
                if (FinalSnappingItem.m400equalsimpl0(iCalculateFinalSnappingItem, companion.m404getClosestItembbeMdSM())) {
                    fFloatValue = Math.abs(fDragGestureDelta) > f10 ? fFloatValue2 : fFloatValue2;
                } else if (!FinalSnappingItem.m400equalsimpl0(iCalculateFinalSnappingItem, companion.m405getNextItembbeMdSM())) {
                    if (!FinalSnappingItem.m400equalsimpl0(iCalculateFinalSnappingItem, companion.m406getPreviousItembbeMdSM())) {
                        fFloatValue = 0.0f;
                    }
                }
                if (isValidDistance(fFloatValue)) {
                    return fFloatValue;
                }
                return 0.0f;
            }

            public final PagerLayoutInfo getLayoutInfo() {
                return pagerState.getLayoutInfo();
            }

            public final boolean isValidDistance(float f11) {
                return (f11 == Float.POSITIVE_INFINITY || f11 == Float.NEGATIVE_INFINITY) ? false : true;
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0366  */
    /* JADX WARN: Removed duplicated region for block: B:208:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0104  */
    /* JADX INFO: renamed from: VerticalPager-xYaah8o, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m756VerticalPagerxYaah8o(androidx.compose.foundation.pager.PagerState r32, androidx.compose.ui.Modifier r33, androidx.compose.foundation.layout.PaddingValues r34, androidx.compose.foundation.pager.PageSize r35, int r36, float r37, androidx.compose.ui.Alignment.Horizontal r38, androidx.compose.foundation.gestures.snapping.SnapFlingBehavior r39, boolean r40, boolean r41, r7.l<? super java.lang.Integer, ? extends java.lang.Object> r42, androidx.compose.ui.input.nestedscroll.NestedScrollConnection r43, r7.r<? super androidx.compose.foundation.pager.PagerScope, ? super java.lang.Integer, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r44, androidx.compose.runtime.Composer r45, int r46, int r47, int r48) {
        /*
            Method dump skipped, instruction units count: 894
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.PagerKt.m756VerticalPagerxYaah8o(androidx.compose.foundation.pager.PagerState, androidx.compose.ui.Modifier, androidx.compose.foundation.layout.PaddingValues, androidx.compose.foundation.pager.PageSize, int, float, androidx.compose.ui.Alignment$Horizontal, androidx.compose.foundation.gestures.snapping.SnapFlingBehavior, boolean, boolean, r7.l, androidx.compose.ui.input.nestedscroll.NestedScrollConnection, r7.r, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float dragGestureDelta(PagerState pagerState) {
        return pagerState.getLayoutInfo().getOrientation() == Orientation.Horizontal ? Offset.m3236getXimpl(pagerState.m765getUpDownDifferenceF1C5BW0$foundation_release()) : Offset.m3237getYimpl(pagerState.m765getUpDownDifferenceF1C5BW0$foundation_release());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isScrollingForward(PagerState pagerState) {
        return dragGestureDelta(pagerState) < 0.0f;
    }

    public static final Modifier pagerSemantics(Modifier modifier, PagerState pagerState, boolean z, Composer composer, int i10) {
        composer.startReplaceableGroup(1509835088);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1509835088, i10, -1, "androidx.compose.foundation.pager.pagerSemantics (Pager.kt:914)");
        }
        Object objG = c.g(773894976, composer, -492369756);
        if (objG == Composer.INSTANCE.getEmpty()) {
            objG = g.h(EffectsKt.createCompositionCoroutineScope(k.f14688i, composer), composer);
        }
        composer.endReplaceableGroup();
        k0 coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objG).getCoroutineScope();
        composer.endReplaceableGroup();
        Modifier modifierThen = modifier.then(SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, new C03751(z, pagerState, coroutineScope), 1, null));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return modifierThen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean pagerSemantics$performBackwardPaging(PagerState pagerState, k0 k0Var) {
        if (!pagerState.getCanScrollBackward()) {
            return false;
        }
        m0.p(k0Var, null, 0, new PagerKt$pagerSemantics$performBackwardPaging$1(pagerState, null), 3);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean pagerSemantics$performForwardPaging(PagerState pagerState, k0 k0Var) {
        if (!pagerState.getCanScrollForward()) {
            return false;
        }
        m0.p(k0Var, null, 0, new PagerKt$pagerSemantics$performForwardPaging$1(pagerState, null), 3);
        return true;
    }

    private static final void debugLog(r7.a<String> aVar) {
    }
}
