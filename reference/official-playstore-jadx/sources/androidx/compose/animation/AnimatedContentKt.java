package androidx.compose.animation;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.IntSize;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000x\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a¶\u0001\u0010\u0016\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0001\u001a\u00028\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u001f\b\u0002\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2%\b\u0002\u0010\u0010\u001a\u001f\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000421\u0010\u0015\u001a-\u0012\u0004\u0012\u00020\u0012\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0001\u0012\u0004\u0012\u00020\u00130\u0011¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0004\b\u0016\u0010\u0017\u001aW\u0010 \u001a\u00020\u001f2\b\b\u0002\u0010\u0019\u001a\u00020\u00182>\b\u0002\u0010\u001e\u001a8\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u001d0\u0011¢\u0006\u0004\b \u0010!\u001a\u001c\u0010%\u001a\u00020\u0006*\u00020\"2\u0006\u0010$\u001a\u00020#H\u0086\u0004¢\u0006\u0004\b%\u0010&\u001a\u001c\u0010'\u001a\u00020\u0006*\u00020\"2\u0006\u0010$\u001a\u00020#H\u0087\u0004¢\u0006\u0004\b'\u0010&\u001a®\u0001\u0010\u0016\u001a\u00020\u0013\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000(2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u001f\b\u0002\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u00072\b\b\u0002\u0010\n\u001a\u00020\t2%\b\u0002\u0010\u0010\u001a\u001f\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000421\u0010\u0015\u001a-\u0012\u0004\u0012\u00020\u0012\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0001\u0012\u0004\u0012\u00020\u00130\u0011¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0004\b\u0016\u0010)¨\u0006*"}, d2 = {"S", "targetState", "Landroidx/compose/ui/Modifier;", "modifier", "Lkotlin/Function1;", "Landroidx/compose/animation/AnimatedContentTransitionScope;", "Landroidx/compose/animation/ContentTransform;", "Lx6/n;", "transitionSpec", "Landroidx/compose/ui/Alignment;", "contentAlignment", "", "label", "Lx6/y;", ContentDisposition.Parameters.Name, "", "contentKey", "Lkotlin/Function2;", "Landroidx/compose/animation/AnimatedContentScope;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "content", "AnimatedContent", "(Ljava/lang/Object;Landroidx/compose/ui/Modifier;Lr7/l;Landroidx/compose/ui/Alignment;Ljava/lang/String;Lr7/l;Lr7/r;Landroidx/compose/runtime/Composer;II)V", "", "clip", "Landroidx/compose/ui/unit/IntSize;", "initialSize", "targetSize", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "sizeAnimationSpec", "Landroidx/compose/animation/SizeTransform;", "SizeTransform", "(ZLr7/p;)Landroidx/compose/animation/SizeTransform;", "Landroidx/compose/animation/EnterTransition;", "Landroidx/compose/animation/ExitTransition;", "exit", "togetherWith", "(Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;)Landroidx/compose/animation/ContentTransform;", "with", "Landroidx/compose/animation/core/Transition;", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/ui/Modifier;Lr7/l;Landroidx/compose/ui/Alignment;Lr7/l;Lr7/r;Landroidx/compose/runtime/Composer;II)V", "animation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AnimatedContentKt {

    /* JADX INFO: Add missing generic type declarations: [S] */
    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedContentKt$AnimatedContent$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1<S> extends r implements l<AnimatedContentTransitionScope<S>, ContentTransform> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final ContentTransform invoke(AnimatedContentTransitionScope<S> animatedContentTransitionScope) {
            return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.0f, 2, null).plus(EnterExitTransitionKt.m38scaleInL8ZKhE$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.92f, 0L, 4, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(90, 0, null, 6, null), 0.0f, 2, null));
        }
    }

    /* JADX INFO: Add missing generic type declarations: [S] */
    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedContentKt$AnimatedContent$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2<S> extends r implements l<S, S> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        @Override // r7.l
        public final S invoke(S s10) {
            return s10;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedContentKt$AnimatedContent$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ r7.r<AnimatedContentScope, S, Composer, Integer, t0> $content;
        final /* synthetic */ Alignment $contentAlignment;
        final /* synthetic */ l<S, Object> $contentKey;
        final /* synthetic */ String $label;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ S $targetState;
        final /* synthetic */ l<AnimatedContentTransitionScope<S>, ContentTransform> $transitionSpec;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(S s10, Modifier modifier, l<? super AnimatedContentTransitionScope<S>, ContentTransform> lVar, Alignment alignment, String str, l<? super S, ? extends Object> lVar2, r7.r<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, t0> rVar, int i10, int i11) {
            super(2);
            this.$targetState = s10;
            this.$modifier = modifier;
            this.$transitionSpec = lVar;
            this.$contentAlignment = alignment;
            this.$label = str;
            this.$contentKey = lVar2;
            this.$content = rVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AnimatedContentKt.AnimatedContent(this.$targetState, this.$modifier, this.$transitionSpec, this.$contentAlignment, this.$label, this.$contentKey, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [S] */
    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedContentKt$AnimatedContent$4, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4<S> extends r implements l<AnimatedContentTransitionScope<S>, ContentTransform> {
        public static final AnonymousClass4 INSTANCE = new AnonymousClass4();

        public AnonymousClass4() {
            super(1);
        }

        @Override // r7.l
        public final ContentTransform invoke(AnimatedContentTransitionScope<S> animatedContentTransitionScope) {
            return AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.0f, 2, null).plus(EnterExitTransitionKt.m38scaleInL8ZKhE$default(AnimationSpecKt.tween$default(220, 90, null, 4, null), 0.92f, 0L, 4, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(90, 0, null, 6, null), 0.0f, 2, null));
        }
    }

    /* JADX INFO: Add missing generic type declarations: [S] */
    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedContentKt$AnimatedContent$5, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass5<S> extends r implements l<S, S> {
        public static final AnonymousClass5 INSTANCE = new AnonymousClass5();

        public AnonymousClass5() {
            super(1);
        }

        @Override // r7.l
        public final S invoke(S s10) {
            return s10;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedContentKt$AnimatedContent$9, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass9 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ r7.r<AnimatedContentScope, S, Composer, Integer, t0> $content;
        final /* synthetic */ Alignment $contentAlignment;
        final /* synthetic */ l<S, Object> $contentKey;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ Transition<S> $this_AnimatedContent;
        final /* synthetic */ l<AnimatedContentTransitionScope<S>, ContentTransform> $transitionSpec;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass9(Transition<S> transition, Modifier modifier, l<? super AnimatedContentTransitionScope<S>, ContentTransform> lVar, Alignment alignment, l<? super S, ? extends Object> lVar2, r7.r<? super AnimatedContentScope, ? super S, ? super Composer, ? super Integer, t0> rVar, int i10, int i11) {
            super(2);
            this.$this_AnimatedContent = transition;
            this.$modifier = modifier;
            this.$transitionSpec = lVar;
            this.$contentAlignment = alignment;
            this.$contentKey = lVar2;
            this.$content = rVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AnimatedContentKt.AnimatedContent(this.$this_AnimatedContent, this.$modifier, this.$transitionSpec, this.$contentAlignment, this.$contentKey, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedContentKt$SizeTransform$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Landroidx/compose/animation/core/SpringSpec;", "Landroidx/compose/ui/unit/IntSize;", "<anonymous parameter 0>", "<anonymous parameter 1>", "invoke-TemP2vQ", "(JJ)Landroidx/compose/animation/core/SpringSpec;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C02451 extends r implements p<IntSize, IntSize, SpringSpec<IntSize>> {
        public static final C02451 INSTANCE = new C02451();

        public C02451() {
            super(2);
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return m4invokeTemP2vQ(((IntSize) obj).getPackedValue(), ((IntSize) obj2).getPackedValue());
        }

        /* JADX INFO: renamed from: invoke-TemP2vQ, reason: not valid java name */
        public final SpringSpec<IntSize> m4invokeTemP2vQ(long j10, long j11) {
            return AnimationSpecKt.spring$default(0.0f, 400.0f, IntSize.m5836boximpl(VisibilityThresholdsKt.getVisibilityThreshold(IntSize.INSTANCE)), 1, null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:108:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x011f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <S> void AnimatedContent(S r18, androidx.compose.ui.Modifier r19, r7.l<? super androidx.compose.animation.AnimatedContentTransitionScope<S>, androidx.compose.animation.ContentTransform> r20, androidx.compose.ui.Alignment r21, java.lang.String r22, r7.l<? super S, ? extends java.lang.Object> r23, r7.r<? super androidx.compose.animation.AnimatedContentScope, ? super S, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r24, androidx.compose.runtime.Composer r25, int r26, int r27) {
        /*
            Method dump skipped, instruction units count: 361
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedContentKt.AnimatedContent(java.lang.Object, androidx.compose.ui.Modifier, r7.l, androidx.compose.ui.Alignment, java.lang.String, r7.l, r7.r, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final SizeTransform SizeTransform(boolean z, p<? super IntSize, ? super IntSize, ? extends FiniteAnimationSpec<IntSize>> pVar) {
        return new SizeTransformImpl(z, pVar);
    }

    public static /* synthetic */ SizeTransform SizeTransform$default(boolean z, p pVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = true;
        }
        if ((i10 & 2) != 0) {
            pVar = C02451.INSTANCE;
        }
        return SizeTransform(z, pVar);
    }

    public static final ContentTransform togetherWith(EnterTransition enterTransition, ExitTransition exitTransition) {
        return new ContentTransform(enterTransition, exitTransition, 0.0f, null, 12, null);
    }

    @e
    public static final ContentTransform with(EnterTransition enterTransition, ExitTransition exitTransition) {
        return new ContentTransform(enterTransition, exitTransition, 0.0f, null, 12, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0248 A[LOOP:2: B:141:0x0246->B:142:0x0248, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0369  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0375  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0214 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:185:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0143  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <S> void AnimatedContent(androidx.compose.animation.core.Transition<S> r19, androidx.compose.ui.Modifier r20, r7.l<? super androidx.compose.animation.AnimatedContentTransitionScope<S>, androidx.compose.animation.ContentTransform> r21, androidx.compose.ui.Alignment r22, r7.l<? super S, ? extends java.lang.Object> r23, r7.r<? super androidx.compose.animation.AnimatedContentScope, ? super S, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r24, androidx.compose.runtime.Composer r25, int r26, int r27) {
        /*
            Method dump skipped, instruction units count: 900
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedContentKt.AnimatedContent(androidx.compose.animation.core.Transition, androidx.compose.ui.Modifier, r7.l, androidx.compose.ui.Alignment, r7.l, r7.r, androidx.compose.runtime.Composer, int, int):void");
    }
}
