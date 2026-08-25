package androidx.compose.foundation.text;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.gestures.ScrollableKt;
import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.foundation.gestures.ScrollableStateKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TransformedText;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import d7.d;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a1\u0010\u0007\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a;\u0010\u0010\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u000e\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a=\u0010\u001c\u001a\u00020\u001b*\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Landroidx/compose/ui/Modifier;", "Landroidx/compose/foundation/text/TextFieldScrollerPosition;", "scrollerPosition", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "", "enabled", "textFieldScrollable", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/text/TextFieldScrollerPosition;Landroidx/compose/foundation/interaction/MutableInteractionSource;Z)Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/text/input/TextFieldValue;", "textFieldValue", "Landroidx/compose/ui/text/input/VisualTransformation;", "visualTransformation", "Lkotlin/Function0;", "Landroidx/compose/foundation/text/TextLayoutResultProxy;", "textLayoutResultProvider", "textFieldScroll", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/text/TextFieldScrollerPosition;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/ui/text/input/VisualTransformation;Lr7/a;)Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/unit/Density;", "", "cursorOffset", "Landroidx/compose/ui/text/input/TransformedText;", "transformedText", "Landroidx/compose/ui/text/TextLayoutResult;", "textLayoutResult", "rtl", "textFieldWidth", "Landroidx/compose/ui/geometry/Rect;", "getCursorRectInScroller", "(Landroidx/compose/ui/unit/Density;ILandroidx/compose/ui/text/input/TransformedText;Landroidx/compose/ui/text/TextLayoutResult;ZI)Landroidx/compose/ui/geometry/Rect;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextFieldScrollKt {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Orientation.values().length];
            try {
                iArr[Orientation.Vertical.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Orientation.Horizontal.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.TextFieldScrollKt$textFieldScrollable$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements q<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ TextFieldScrollerPosition $scrollerPosition;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(TextFieldScrollerPosition textFieldScrollerPosition, boolean z, MutableInteractionSource mutableInteractionSource) {
            super(3);
            this.$scrollerPosition = textFieldScrollerPosition;
            this.$enabled = z;
            this.$interactionSource = mutableInteractionSource;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
        }

        public final Modifier invoke(Modifier modifier, Composer composer, int i10) {
            composer.startReplaceableGroup(805428266);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(805428266, i10, -1, "androidx.compose.foundation.text.textFieldScrollable.<anonymous> (TextFieldScroll.kt:68)");
            }
            boolean z = this.$scrollerPosition.getOrientation() == Orientation.Vertical || !(composer.consume(CompositionLocalsKt.getLocalLayoutDirection()) == LayoutDirection.Rtl);
            composer.startReplaceableGroup(753734506);
            boolean zChanged = composer.changed(this.$scrollerPosition);
            TextFieldScrollerPosition textFieldScrollerPosition = this.$scrollerPosition;
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new TextFieldScrollKt$textFieldScrollable$2$scrollableState$1$1(textFieldScrollerPosition);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            final ScrollableState scrollableStateRememberScrollableState = ScrollableStateKt.rememberScrollableState((l) objRememberedValue, composer, 0);
            final TextFieldScrollerPosition textFieldScrollerPosition2 = this.$scrollerPosition;
            composer.startReplaceableGroup(511388516);
            boolean zChanged2 = composer.changed(scrollableStateRememberScrollableState) | composer.changed(textFieldScrollerPosition2);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new ScrollableState(textFieldScrollerPosition2) { // from class: androidx.compose.foundation.text.TextFieldScrollKt$textFieldScrollable$2$wrappedScrollableState$1$1

                    /* JADX INFO: renamed from: canScrollBackward$delegate, reason: from kotlin metadata */
                    private final State canScrollBackward;

                    /* JADX INFO: renamed from: canScrollForward$delegate, reason: from kotlin metadata */
                    private final State canScrollForward;

                    {
                        this.canScrollForward = SnapshotStateKt.derivedStateOf(new TextFieldScrollKt$textFieldScrollable$2$wrappedScrollableState$1$1$canScrollForward$2(textFieldScrollerPosition2));
                        this.canScrollBackward = SnapshotStateKt.derivedStateOf(new TextFieldScrollKt$textFieldScrollable$2$wrappedScrollableState$1$1$canScrollBackward$2(textFieldScrollerPosition2));
                    }

                    @Override // androidx.compose.foundation.gestures.ScrollableState
                    public float dispatchRawDelta(float delta) {
                        return this.$$delegate_0.dispatchRawDelta(delta);
                    }

                    @Override // androidx.compose.foundation.gestures.ScrollableState
                    public boolean getCanScrollBackward() {
                        return ((Boolean) this.canScrollBackward.getValue()).booleanValue();
                    }

                    @Override // androidx.compose.foundation.gestures.ScrollableState
                    public boolean getCanScrollForward() {
                        return ((Boolean) this.canScrollForward.getValue()).booleanValue();
                    }

                    @Override // androidx.compose.foundation.gestures.ScrollableState
                    public boolean isScrollInProgress() {
                        return this.$$delegate_0.isScrollInProgress();
                    }

                    @Override // androidx.compose.foundation.gestures.ScrollableState
                    public Object scroll(MutatePriority mutatePriority, p<? super ScrollScope, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) {
                        return this.$$delegate_0.scroll(mutatePriority, pVar, dVar);
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            composer.endReplaceableGroup();
            Modifier modifierScrollable$default = ScrollableKt.scrollable$default(Modifier.INSTANCE, (TextFieldScrollKt$textFieldScrollable$2$wrappedScrollableState$1$1) objRememberedValue2, this.$scrollerPosition.getOrientation(), this.$enabled && this.$scrollerPosition.getMaximum() != 0.0f, z, null, this.$interactionSource, 16, null);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return modifierScrollable$default;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Rect getCursorRectInScroller(Density density, int i10, TransformedText transformedText, TextLayoutResult textLayoutResult, boolean z, int i11) {
        Rect zero;
        if (textLayoutResult == null || (zero = textLayoutResult.getCursorRect(transformedText.getOffsetMapping().originalToTransformed(i10))) == null) {
            zero = Rect.INSTANCE.getZero();
        }
        Rect rect = zero;
        int iMo279roundToPx0680j_4 = density.mo279roundToPx0680j_4(TextFieldCursorKt.getDefaultCursorThickness());
        return Rect.copy$default(rect, z ? (i11 - rect.getLeft()) - iMo279roundToPx0680j_4 : rect.getLeft(), 0.0f, z ? i11 - rect.getLeft() : iMo279roundToPx0680j_4 + rect.getLeft(), 0.0f, 10, null);
    }

    public static final Modifier textFieldScroll(Modifier modifier, TextFieldScrollerPosition textFieldScrollerPosition, TextFieldValue textFieldValue, VisualTransformation visualTransformation, a<TextLayoutResultProxy> aVar) {
        Modifier verticalScrollLayoutModifier;
        Orientation orientation = textFieldScrollerPosition.getOrientation();
        int iM896getOffsetToFollow5zctL8 = textFieldScrollerPosition.m896getOffsetToFollow5zctL8(textFieldValue.getSelection());
        textFieldScrollerPosition.m898setPreviousSelection5zctL8(textFieldValue.getSelection());
        TransformedText transformedTextFilterWithValidation = ValidatingOffsetMappingKt.filterWithValidation(visualTransformation, textFieldValue.getAnnotatedString());
        int i10 = WhenMappings.$EnumSwitchMapping$0[orientation.ordinal()];
        if (i10 == 1) {
            verticalScrollLayoutModifier = new VerticalScrollLayoutModifier(textFieldScrollerPosition, iM896getOffsetToFollow5zctL8, transformedTextFilterWithValidation, aVar);
        } else {
            if (i10 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            verticalScrollLayoutModifier = new HorizontalScrollLayoutModifier(textFieldScrollerPosition, iM896getOffsetToFollow5zctL8, transformedTextFilterWithValidation, aVar);
        }
        return ClipKt.clipToBounds(modifier).then(verticalScrollLayoutModifier);
    }

    public static final Modifier textFieldScrollable(Modifier modifier, TextFieldScrollerPosition textFieldScrollerPosition, MutableInteractionSource mutableInteractionSource, boolean z) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new TextFieldScrollKt$textFieldScrollable$$inlined$debugInspectorInfo$1(textFieldScrollerPosition, mutableInteractionSource, z) : InspectableValueKt.getNoInspectorInfo(), new AnonymousClass2(textFieldScrollerPosition, z, mutableInteractionSource));
    }

    public static /* synthetic */ Modifier textFieldScrollable$default(Modifier modifier, TextFieldScrollerPosition textFieldScrollerPosition, MutableInteractionSource mutableInteractionSource, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            mutableInteractionSource = null;
        }
        if ((i10 & 4) != 0) {
            z = true;
        }
        return textFieldScrollable(modifier, textFieldScrollerPosition, mutableInteractionSource, z);
    }
}
