package androidx.compose.foundation.text;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.unit.Dp;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import k2.c;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a4\u0010\b\u001a\u00020\t*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0000\"\u0016\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"DefaultCursorThickness", "Landroidx/compose/ui/unit/Dp;", "getDefaultCursorThickness", "()F", "F", "cursorAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "cursor", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/text/TextFieldState;", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "enabled", "", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextFieldCursorKt {
    private static final AnimationSpec<Float> cursorAnimationSpec = AnimationSpecKt.m93infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(TextFieldCursorKt$cursorAnimationSpec$1.INSTANCE), null, 0, 6, null);
    private static final float DefaultCursorThickness = Dp.m5678constructorimpl(2);

    /* JADX INFO: renamed from: androidx.compose.foundation.text.TextFieldCursorKt$cursor$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements q<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ Brush $cursorBrush;
        final /* synthetic */ OffsetMapping $offsetMapping;
        final /* synthetic */ TextFieldState $state;
        final /* synthetic */ TextFieldValue $value;

        /* JADX INFO: renamed from: androidx.compose.foundation.text.TextFieldCursorKt$cursor$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.text.TextFieldCursorKt$cursor$1$1", f = "TextFieldCursor.kt", l = {51}, m = "invokeSuspend")
        public static final class C00461 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ Animatable<Float, AnimationVector1D> $cursorAlpha;
            int label;

            /* JADX INFO: renamed from: androidx.compose.foundation.text.TextFieldCursorKt$cursor$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
            @e(c = "androidx.compose.foundation.text.TextFieldCursorKt$cursor$1$1$1", f = "TextFieldCursor.kt", l = {53, 55}, m = "invokeSuspend")
            public static final class C00471 extends j implements p<k0, d<? super t0>, Object> {
                final /* synthetic */ Animatable<Float, AnimationVector1D> $cursorAlpha;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00471(Animatable<Float, AnimationVector1D> animatable, d<? super C00471> dVar) {
                    super(2, dVar);
                    this.$cursorAlpha = animatable;
                }

                @Override // f7.a
                public final d<t0> create(Object obj, d<?> dVar) {
                    return new C00471(this.$cursorAlpha, dVar);
                }

                /* JADX WARN: Code restructure failed: missing block: B:14:0x0049, code lost:
                
                    if (androidx.compose.animation.core.Animatable.animateTo$default(r4, r5, r6, null, null, r12, 12, null) == r3) goto L15;
                 */
                @Override // f7.a
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r13) {
                    /*
                        r12 = this;
                        int r0 = r12.label
                        r1 = 2
                        r2 = 1
                        e7.a r3 = e7.a.f15033i
                        if (r0 == 0) goto L1c
                        if (r0 == r2) goto L18
                        if (r0 != r1) goto L10
                        k2.c.G(r13)
                        goto L4c
                    L10:
                        java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r13.<init>(r0)
                        throw r13
                    L18:
                        k2.c.G(r13)
                        goto L31
                    L1c:
                        k2.c.G(r13)
                        androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r13 = r12.$cursorAlpha
                        java.lang.Float r0 = new java.lang.Float
                        r4 = 1065353216(0x3f800000, float:1.0)
                        r0.<init>(r4)
                        r12.label = r2
                        java.lang.Object r13 = r13.snapTo(r0, r12)
                        if (r13 != r3) goto L31
                        goto L4b
                    L31:
                        androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r4 = r12.$cursorAlpha
                        java.lang.Float r5 = new java.lang.Float
                        r13 = 0
                        r5.<init>(r13)
                        androidx.compose.animation.core.AnimationSpec r6 = androidx.compose.foundation.text.TextFieldCursorKt.access$getCursorAnimationSpec$p()
                        r12.label = r1
                        r7 = 0
                        r8 = 0
                        r10 = 12
                        r11 = 0
                        r9 = r12
                        java.lang.Object r13 = androidx.compose.animation.core.Animatable.animateTo$default(r4, r5, r6, r7, r8, r9, r10, r11)
                        if (r13 != r3) goto L4c
                    L4b:
                        return r3
                    L4c:
                        x6.t0 r13 = x6.t0.f22605a
                        return r13
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.TextFieldCursorKt.AnonymousClass1.C00461.C00471.invokeSuspend(java.lang.Object):java.lang.Object");
                }

                @Override // r7.p
                public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                    return ((C00471) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00461(Animatable<Float, AnimationVector1D> animatable, d<? super C00461> dVar) {
                super(2, dVar);
                this.$cursorAlpha = animatable;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new C00461(this.$cursorAlpha, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    FixedMotionDurationScale fixedMotionDurationScale = FixedMotionDurationScale.INSTANCE;
                    C00471 c00471 = new C00471(this.$cursorAlpha, null);
                    this.label = 1;
                    Object objY = m0.y(fixedMotionDurationScale, c00471, this);
                    a aVar = a.f15033i;
                    if (objY == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((C00461) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.TextFieldCursorKt$cursor$1$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass2 extends r implements l<ContentDrawScope, t0> {
            final /* synthetic */ Animatable<Float, AnimationVector1D> $cursorAlpha;
            final /* synthetic */ Brush $cursorBrush;
            final /* synthetic */ OffsetMapping $offsetMapping;
            final /* synthetic */ TextFieldState $state;
            final /* synthetic */ TextFieldValue $value;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(Animatable<Float, AnimationVector1D> animatable, OffsetMapping offsetMapping, TextFieldValue textFieldValue, TextFieldState textFieldState, Brush brush) {
                super(1);
                this.$cursorAlpha = animatable;
                this.$offsetMapping = offsetMapping;
                this.$value = textFieldValue;
                this.$state = textFieldState;
                this.$cursorBrush = brush;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((ContentDrawScope) obj);
                return t0.f22605a;
            }

            public final void invoke(ContentDrawScope contentDrawScope) {
                Rect rect;
                TextLayoutResult value;
                contentDrawScope.drawContent();
                float fM = qb.d.m(this.$cursorAlpha.getValue().floatValue(), 0.0f, 1.0f);
                if (fM == 0.0f) {
                    return;
                }
                int iOriginalToTransformed = this.$offsetMapping.originalToTransformed(TextRange.m5192getStartimpl(this.$value.getSelection()));
                TextLayoutResultProxy layoutResult = this.$state.getLayoutResult();
                if (layoutResult == null || (value = layoutResult.getValue()) == null || (rect = value.getCursorRect(iOriginalToTransformed)) == null) {
                    rect = new Rect(0.0f, 0.0f, 0.0f, 0.0f);
                }
                float fMo285toPx0680j_4 = contentDrawScope.mo285toPx0680j_4(TextFieldCursorKt.getDefaultCursorThickness());
                float f10 = fMo285toPx0680j_4 / 2;
                float left = rect.getLeft() + f10;
                float fM3305getWidthimpl = Size.m3305getWidthimpl(contentDrawScope.mo3916getSizeNHjbRc()) - f10;
                if (left > fM3305getWidthimpl) {
                    left = fM3305getWidthimpl;
                }
                if (left >= f10) {
                    f10 = left;
                }
                androidx.compose.ui.graphics.drawscope.c.B(contentDrawScope, this.$cursorBrush, OffsetKt.Offset(f10, rect.getTop()), OffsetKt.Offset(f10, rect.getBottom()), fMo285toPx0680j_4, 0, null, fM, null, 0, 432, null);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Brush brush, TextFieldState textFieldState, TextFieldValue textFieldValue, OffsetMapping offsetMapping) {
            super(3);
            this.$cursorBrush = brush;
            this.$state = textFieldState;
            this.$value = textFieldValue;
            this.$offsetMapping = offsetMapping;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
        }

        public final Modifier invoke(Modifier modifier, Composer composer, int i10) {
            Modifier modifierDrawWithContent;
            composer.startReplaceableGroup(1634330012);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1634330012, i10, -1, "androidx.compose.foundation.text.cursor.<anonymous> (TextFieldCursor.kt:45)");
            }
            composer.startReplaceableGroup(-492369756);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = AnimatableKt.Animatable$default(1.0f, 0.0f, 2, null);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            Animatable animatable = (Animatable) objRememberedValue;
            Brush brush = this.$cursorBrush;
            boolean z = ((brush instanceof SolidColor) && ((SolidColor) brush).getValue() == Color.INSTANCE.m3508getUnspecified0d7_KjU()) ? false : true;
            if (this.$state.getHasFocus() && TextRange.m5186getCollapsedimpl(this.$value.getSelection()) && z) {
                EffectsKt.LaunchedEffect(this.$value.getAnnotatedString(), TextRange.m5180boximpl(this.$value.getSelection()), new C00461(animatable, null), composer, 512);
                modifierDrawWithContent = DrawModifierKt.drawWithContent(modifier, new AnonymousClass2(animatable, this.$offsetMapping, this.$value, this.$state, this.$cursorBrush));
            } else {
                modifierDrawWithContent = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return modifierDrawWithContent;
        }
    }

    public static final Modifier cursor(Modifier modifier, TextFieldState textFieldState, TextFieldValue textFieldValue, OffsetMapping offsetMapping, Brush brush, boolean z) {
        return z ? ComposedModifierKt.composed$default(modifier, null, new AnonymousClass1(brush, textFieldState, textFieldValue, offsetMapping), 1, null) : modifier;
    }

    public static final float getDefaultCursorThickness() {
        return DefaultCursorThickness;
    }
}
