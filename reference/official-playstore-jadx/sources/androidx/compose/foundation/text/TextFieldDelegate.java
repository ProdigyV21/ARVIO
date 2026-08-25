package androidx.compose.foundation.text;

import androidx.compose.foundation.text.selection.SelectionManagerKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.PlatformSpanStyle;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextPainter;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.EditCommand;
import androidx.compose.ui.text.input.EditProcessor;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputSession;
import androidx.compose.ui.text.input.TransformedText;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.github.jan.supabase.gotrue.SettingsSessionManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.k0;
import q7.n;
import r7.l;
import x6.j0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/compose/foundation/text/TextFieldDelegate;", "", "()V", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextFieldDelegate {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0001ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ7\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u0018H\u0001¢\u0006\u0004\b\u001b\u0010\u001cJG\u0010&\u001a\u00020\u001a2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020 2\u0006\u0010#\u001a\u00020\"2\u0006\u0010\u0016\u001a\u00020\u0015H\u0001¢\u0006\u0004\b$\u0010%J/\u0010+\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020 2\u0006\u0010'\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020(H\u0001¢\u0006\u0004\b)\u0010*JC\u00106\u001a\u00020\u001a2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020-0,2\u0006\u00100\u001a\u00020/2\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001a012\b\u00103\u001a\u0004\u0018\u00010 H\u0001¢\u0006\u0004\b4\u00105JF\u0010;\u001a\u00020\u001a2\u0006\u00108\u001a\u0002072\u0006\u0010\u0017\u001a\u00020(2\u0006\u00100\u001a\u00020/2\u0006\u0010\u0016\u001a\u00020\u00152\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001a01H\u0001ø\u0001\u0000¢\u0006\u0004\b9\u0010:JW\u0010D\u001a\u00020 2\u0006\u0010=\u001a\u00020<2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u00100\u001a\u00020/2\u0006\u0010?\u001a\u00020>2\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001a012\u0012\u0010A\u001a\u000e\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020\u001a01H\u0001¢\u0006\u0004\bB\u0010CJW\u0010F\u001a\u00020 2\u0006\u0010=\u001a\u00020<2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u00100\u001a\u00020/2\u0006\u0010?\u001a\u00020>2\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001a012\u0012\u0010A\u001a\u000e\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020\u001a01H\u0001¢\u0006\u0004\bE\u0010CJ3\u0010I\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020 2\u0006\u00100\u001a\u00020/2\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001a01H\u0001¢\u0006\u0004\bG\u0010HJ \u0010P\u001a\u00020L2\u0006\u0010K\u001a\u00020J2\u0006\u0010M\u001a\u00020Lø\u0001\u0000¢\u0006\u0004\bN\u0010O\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006Q"}, d2 = {"Landroidx/compose/foundation/text/TextFieldDelegate$Companion;", "", "<init>", "()V", "Landroidx/compose/foundation/text/TextDelegate;", "textDelegate", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "Landroidx/compose/ui/text/TextLayoutResult;", "prevResultText", "Lx6/j0;", "", "layout-_EkL_-Y$foundation_release", "(Landroidx/compose/foundation/text/TextDelegate;JLandroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/text/TextLayoutResult;)Lx6/j0;", TtmlNode.TAG_LAYOUT, "Landroidx/compose/ui/graphics/Canvas;", "canvas", "Landroidx/compose/ui/text/input/TextFieldValue;", "value", "Landroidx/compose/ui/text/input/OffsetMapping;", "offsetMapping", "textLayoutResult", "Landroidx/compose/ui/graphics/Paint;", "selectionPaint", "Lx6/t0;", "draw$foundation_release", "(Landroidx/compose/ui/graphics/Canvas;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/ui/text/input/OffsetMapping;Landroidx/compose/ui/text/TextLayoutResult;Landroidx/compose/ui/graphics/Paint;)V", "draw", "Landroidx/compose/ui/layout/LayoutCoordinates;", "layoutCoordinates", "Landroidx/compose/ui/text/input/TextInputSession;", "textInputSession", "", "hasFocus", "notifyFocusedRect$foundation_release", "(Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/foundation/text/TextDelegate;Landroidx/compose/ui/text/TextLayoutResult;Landroidx/compose/ui/layout/LayoutCoordinates;Landroidx/compose/ui/text/input/TextInputSession;ZLandroidx/compose/ui/text/input/OffsetMapping;)V", "notifyFocusedRect", "textFieldValue", "Landroidx/compose/foundation/text/TextLayoutResultProxy;", "updateTextLayoutResult$foundation_release", "(Landroidx/compose/ui/text/input/TextInputSession;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/ui/text/input/OffsetMapping;Landroidx/compose/foundation/text/TextLayoutResultProxy;)V", "updateTextLayoutResult", "", "Landroidx/compose/ui/text/input/EditCommand;", "ops", "Landroidx/compose/ui/text/input/EditProcessor;", "editProcessor", "Lkotlin/Function1;", "onValueChange", SettingsSessionManager.SETTINGS_KEY, "onEditCommand$foundation_release", "(Ljava/util/List;Landroidx/compose/ui/text/input/EditProcessor;Lr7/l;Landroidx/compose/ui/text/input/TextInputSession;)V", "onEditCommand", "Landroidx/compose/ui/geometry/Offset;", "position", "setCursorOffset-ULxng0E$foundation_release", "(JLandroidx/compose/foundation/text/TextLayoutResultProxy;Landroidx/compose/ui/text/input/EditProcessor;Landroidx/compose/ui/text/input/OffsetMapping;Lr7/l;)V", "setCursorOffset", "Landroidx/compose/ui/text/input/TextInputService;", "textInputService", "Landroidx/compose/ui/text/input/ImeOptions;", "imeOptions", "Landroidx/compose/ui/text/input/ImeAction;", "onImeActionPerformed", "restartInput$foundation_release", "(Landroidx/compose/ui/text/input/TextInputService;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/ui/text/input/EditProcessor;Landroidx/compose/ui/text/input/ImeOptions;Lr7/l;Lr7/l;)Landroidx/compose/ui/text/input/TextInputSession;", "restartInput", "onFocus$foundation_release", "onFocus", "onBlur$foundation_release", "(Landroidx/compose/ui/text/input/TextInputSession;Landroidx/compose/ui/text/input/EditProcessor;Lr7/l;)V", "onBlur", "Landroidx/compose/ui/text/TextRange;", "compositionRange", "Landroidx/compose/ui/text/input/TransformedText;", "transformed", "applyCompositionDecoration-72CqOWE", "(JLandroidx/compose/ui/text/input/TransformedText;)Landroidx/compose/ui/text/input/TransformedText;", "applyCompositionDecoration", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        /* JADX INFO: renamed from: layout-_EkL_-Y$foundation_release$default, reason: not valid java name */
        public static /* synthetic */ j0 m880layout_EkL_Y$foundation_release$default(Companion companion, TextDelegate textDelegate, long j10, LayoutDirection layoutDirection, TextLayoutResult textLayoutResult, int i10, Object obj) {
            if ((i10 & 8) != 0) {
                textLayoutResult = null;
            }
            return companion.m882layout_EkL_Y$foundation_release(textDelegate, j10, layoutDirection, textLayoutResult);
        }

        /* JADX INFO: renamed from: applyCompositionDecoration-72CqOWE, reason: not valid java name */
        public final TransformedText m881applyCompositionDecoration72CqOWE(long compositionRange, TransformedText transformed) {
            int iOriginalToTransformed = transformed.getOffsetMapping().originalToTransformed(TextRange.m5192getStartimpl(compositionRange));
            int iOriginalToTransformed2 = transformed.getOffsetMapping().originalToTransformed(TextRange.m5187getEndimpl(compositionRange));
            int iMin = Math.min(iOriginalToTransformed, iOriginalToTransformed2);
            int iMax = Math.max(iOriginalToTransformed, iOriginalToTransformed2);
            AnnotatedString.Builder builder = new AnnotatedString.Builder(transformed.getText());
            builder.addStyle(new SpanStyle(0L, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, TextDecoration.INSTANCE.getUnderline(), (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 61439, (h) null), iMin, iMax);
            return new TransformedText(builder.toAnnotatedString(), transformed.getOffsetMapping());
        }

        @n
        public final void draw$foundation_release(Canvas canvas, TextFieldValue value, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, Paint selectionPaint) {
            int iOriginalToTransformed;
            int iOriginalToTransformed2;
            if (!TextRange.m5186getCollapsedimpl(value.getSelection()) && (iOriginalToTransformed = offsetMapping.originalToTransformed(TextRange.m5190getMinimpl(value.getSelection()))) != (iOriginalToTransformed2 = offsetMapping.originalToTransformed(TextRange.m5189getMaximpl(value.getSelection())))) {
                canvas.drawPath(textLayoutResult.getPathForRange(iOriginalToTransformed, iOriginalToTransformed2), selectionPaint);
            }
            TextPainter.INSTANCE.paint(canvas, textLayoutResult);
        }

        @n
        /* JADX INFO: renamed from: layout-_EkL_-Y$foundation_release, reason: not valid java name */
        public final j0 m882layout_EkL_Y$foundation_release(TextDelegate textDelegate, long constraints, LayoutDirection layoutDirection, TextLayoutResult prevResultText) {
            TextLayoutResult textLayoutResultM872layoutNN6EwU = textDelegate.m872layoutNN6EwU(constraints, layoutDirection, prevResultText);
            return new j0(Integer.valueOf(IntSize.m5844getWidthimpl(textLayoutResultM872layoutNN6EwU.getSize())), Integer.valueOf(IntSize.m5843getHeightimpl(textLayoutResultM872layoutNN6EwU.getSize())), textLayoutResultM872layoutNN6EwU);
        }

        @n
        public final void notifyFocusedRect$foundation_release(TextFieldValue value, TextDelegate textDelegate, TextLayoutResult textLayoutResult, LayoutCoordinates layoutCoordinates, TextInputSession textInputSession, boolean hasFocus, OffsetMapping offsetMapping) {
            if (hasFocus) {
                int iOriginalToTransformed = offsetMapping.originalToTransformed(TextRange.m5189getMaximpl(value.getSelection()));
                Rect boundingBox = iOriginalToTransformed < textLayoutResult.getLayoutInput().getText().length() ? textLayoutResult.getBoundingBox(iOriginalToTransformed) : iOriginalToTransformed != 0 ? textLayoutResult.getBoundingBox(iOriginalToTransformed - 1) : new Rect(0.0f, 0.0f, 1.0f, IntSize.m5843getHeightimpl(TextFieldDelegateKt.computeSizeForDefaultText$default(textDelegate.getStyle(), textDelegate.getDensity(), textDelegate.getFontFamilyResolver(), null, 0, 24, null)));
                long jMo4640localToRootMKHz9U = layoutCoordinates.mo4640localToRootMKHz9U(OffsetKt.Offset(boundingBox.getLeft(), boundingBox.getTop()));
                textInputSession.notifyFocusedRect(RectKt.m3276Recttz77jQw(OffsetKt.Offset(Offset.m3236getXimpl(jMo4640localToRootMKHz9U), Offset.m3237getYimpl(jMo4640localToRootMKHz9U)), SizeKt.Size(boundingBox.getWidth(), boundingBox.getHeight())));
            }
        }

        @n
        public final void onBlur$foundation_release(TextInputSession textInputSession, EditProcessor editProcessor, l<? super TextFieldValue, t0> onValueChange) {
            onValueChange.invoke(TextFieldValue.m5424copy3r_uNRQ$default(editProcessor.toTextFieldValue(), (AnnotatedString) null, 0L, (TextRange) null, 3, (Object) null));
            textInputSession.dispose();
        }

        @n
        public final void onEditCommand$foundation_release(List<? extends EditCommand> ops, EditProcessor editProcessor, l<? super TextFieldValue, t0> onValueChange, TextInputSession session) {
            TextFieldValue textFieldValueApply = editProcessor.apply(ops);
            if (session != null) {
                session.updateState(null, textFieldValueApply);
            }
            onValueChange.invoke(textFieldValueApply);
        }

        @n
        public final TextInputSession onFocus$foundation_release(TextInputService textInputService, TextFieldValue value, EditProcessor editProcessor, ImeOptions imeOptions, l<? super TextFieldValue, t0> onValueChange, l<? super ImeAction, t0> onImeActionPerformed) {
            return restartInput$foundation_release(textInputService, value, editProcessor, imeOptions, onValueChange, onImeActionPerformed);
        }

        @n
        public final TextInputSession restartInput$foundation_release(TextInputService textInputService, TextFieldValue value, EditProcessor editProcessor, ImeOptions imeOptions, l<? super TextFieldValue, t0> onValueChange, l<? super ImeAction, t0> onImeActionPerformed) {
            k0 k0Var = new k0();
            TextInputSession textInputSessionStartInput = textInputService.startInput(value, imeOptions, new TextFieldDelegate$Companion$restartInput$1(editProcessor, onValueChange, k0Var), onImeActionPerformed);
            k0Var.f19746i = textInputSessionStartInput;
            return textInputSessionStartInput;
        }

        @n
        /* JADX INFO: renamed from: setCursorOffset-ULxng0E$foundation_release, reason: not valid java name */
        public final void m883setCursorOffsetULxng0E$foundation_release(long position, TextLayoutResultProxy textLayoutResult, EditProcessor editProcessor, OffsetMapping offsetMapping, l<? super TextFieldValue, t0> onValueChange) {
            onValueChange.invoke(TextFieldValue.m5424copy3r_uNRQ$default(editProcessor.toTextFieldValue(), (AnnotatedString) null, TextRangeKt.TextRange(offsetMapping.transformedToOriginal(TextLayoutResultProxy.m908getOffsetForPosition3MmeM6k$default(textLayoutResult, position, false, 2, null))), (TextRange) null, 5, (Object) null));
        }

        @n
        public final void updateTextLayoutResult$foundation_release(TextInputSession textInputSession, TextFieldValue textFieldValue, OffsetMapping offsetMapping, TextLayoutResultProxy textLayoutResult) {
            LayoutCoordinates decorationBoxCoordinates;
            LayoutCoordinates innerTextFieldCoordinates = textLayoutResult.getInnerTextFieldCoordinates();
            if (innerTextFieldCoordinates == null || !innerTextFieldCoordinates.isAttached() || (decorationBoxCoordinates = textLayoutResult.getDecorationBoxCoordinates()) == null) {
                return;
            }
            textInputSession.updateTextLayoutResult(textFieldValue, offsetMapping, textLayoutResult.getValue(), new TextFieldDelegate$Companion$updateTextLayoutResult$1$1$1(innerTextFieldCoordinates), SelectionManagerKt.visibleBounds(innerTextFieldCoordinates), innerTextFieldCoordinates.localBoundingBoxOf(decorationBoxCoordinates, false));
        }

        private Companion() {
        }
    }
}
