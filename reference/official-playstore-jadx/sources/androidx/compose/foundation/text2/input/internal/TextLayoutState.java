package androidx.compose.foundation.text2.input.internal;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0007\u001a\u00020\u0004*\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006J-\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f¢\u0006\u0004\b\u0010\u0010\u0011J0\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0018ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\"\u0010#\u001a\u00020 2\u0006\u0010\u001e\u001a\u00020\u00042\b\b\u0002\u0010\u001f\u001a\u00020\fø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u0018\u0010'\u001a\u00020\f2\u0006\u0010$\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b%\u0010&R\u0016\u0010)\u001a\u00020(8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*RC\u0010.\u001a#\u0012\u0004\u0012\u00020\u0012\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0,\u0012\u0004\u0012\u00020\u000f\u0018\u00010+¢\u0006\u0002\b-8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b.\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001d\u00107\u001a\u0004\u0018\u00010\u001a8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b4\u0010*\u001a\u0004\b5\u00106R/\u0010@\u001a\u0004\u0018\u0001082\b\u00109\u001a\u0004\u0018\u0001088F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R/\u0010D\u001a\u0004\u0018\u0001082\b\u00109\u001a\u0004\u0018\u0001088F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bA\u0010;\u001a\u0004\bB\u0010=\"\u0004\bC\u0010?R/\u0010H\u001a\u0004\u0018\u0001082\b\u00109\u001a\u0004\u0018\u0001088F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bE\u0010;\u001a\u0004\bF\u0010=\"\u0004\bG\u0010?R1\u0010O\u001a\u00020I2\u0006\u00109\u001a\u00020I8F@FX\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\bJ\u0010;\u001a\u0004\bK\u0010L\"\u0004\bM\u0010N\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006P"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/TextLayoutState;", "", "<init>", "()V", "Landroidx/compose/ui/geometry/Offset;", "coercedInVisibleBoundsOfInputText-MK-Hz9U", "(J)J", "coercedInVisibleBoundsOfInputText", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "textFieldState", "Landroidx/compose/ui/text/TextStyle;", "textStyle", "", "singleLine", "softWrap", "Lx6/t0;", "updateNonMeasureInputs", "(Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;Landroidx/compose/ui/text/TextStyle;ZZ)V", "Landroidx/compose/ui/unit/Density;", "density", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "fontFamilyResolver", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/text/TextLayoutResult;", "layoutWithNewMeasureInputs--hBUhpc", "(Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/text/font/FontFamily$Resolver;J)Landroidx/compose/ui/text/TextLayoutResult;", "layoutWithNewMeasureInputs", "position", "coerceInVisibleBounds", "", "getOffsetForPosition-3MmeM6k", "(JZ)I", "getOffsetForPosition", "offset", "isPositionOnText-k-4lQ0M", "(J)Z", "isPositionOnText", "Landroidx/compose/foundation/text2/input/internal/TextFieldLayoutStateCache;", "layoutCache", "Landroidx/compose/foundation/text2/input/internal/TextFieldLayoutStateCache;", "Lkotlin/Function2;", "Lkotlin/Function0;", "Lx6/n;", "onTextLayout", "Lr7/p;", "getOnTextLayout", "()Lr7/p;", "setOnTextLayout", "(Lr7/p;)V", "layoutResult$delegate", "getLayoutResult", "()Landroidx/compose/ui/text/TextLayoutResult;", "layoutResult", "Landroidx/compose/ui/layout/LayoutCoordinates;", "<set-?>", "textLayoutNodeCoordinates$delegate", "Landroidx/compose/runtime/MutableState;", "getTextLayoutNodeCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "setTextLayoutNodeCoordinates", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "textLayoutNodeCoordinates", "coreNodeCoordinates$delegate", "getCoreNodeCoordinates", "setCoreNodeCoordinates", "coreNodeCoordinates", "decoratorNodeCoordinates$delegate", "getDecoratorNodeCoordinates", "setDecoratorNodeCoordinates", "decoratorNodeCoordinates", "Landroidx/compose/ui/unit/Dp;", "minHeightForSingleLineField$delegate", "getMinHeightForSingleLineField-D9Ej5fM", "()F", "setMinHeightForSingleLineField-0680j_4", "(F)V", "minHeightForSingleLineField", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextLayoutState {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: coreNodeCoordinates$delegate, reason: from kotlin metadata */
    private final MutableState coreNodeCoordinates;

    /* JADX INFO: renamed from: decoratorNodeCoordinates$delegate, reason: from kotlin metadata */
    private final MutableState decoratorNodeCoordinates;
    private TextFieldLayoutStateCache layoutCache;

    /* JADX INFO: renamed from: layoutResult$delegate, reason: from kotlin metadata */
    private final TextFieldLayoutStateCache layoutResult;

    /* JADX INFO: renamed from: minHeightForSingleLineField$delegate, reason: from kotlin metadata */
    private final MutableState minHeightForSingleLineField;
    private p<? super Density, ? super r7.a<TextLayoutResult>, t0> onTextLayout;

    /* JADX INFO: renamed from: textLayoutNodeCoordinates$delegate, reason: from kotlin metadata */
    private final MutableState textLayoutNodeCoordinates;

    public TextLayoutState() {
        TextFieldLayoutStateCache textFieldLayoutStateCache = new TextFieldLayoutStateCache();
        this.layoutCache = textFieldLayoutStateCache;
        this.layoutResult = textFieldLayoutStateCache;
        this.textLayoutNodeCoordinates = SnapshotStateKt.mutableStateOf(null, SnapshotStateKt.neverEqualPolicy());
        this.coreNodeCoordinates = SnapshotStateKt.mutableStateOf(null, SnapshotStateKt.neverEqualPolicy());
        this.decoratorNodeCoordinates = SnapshotStateKt.mutableStateOf(null, SnapshotStateKt.neverEqualPolicy());
        this.minHeightForSingleLineField = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Dp.m5676boximpl(Dp.m5678constructorimpl(0)), null, 2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0022  */
    /* JADX INFO: renamed from: coercedInVisibleBoundsOfInputText-MK-Hz9U, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final long m1123coercedInVisibleBoundsOfInputTextMKHz9U(long r6) {
        /*
            r5 = this;
            androidx.compose.ui.layout.LayoutCoordinates r0 = r5.getTextLayoutNodeCoordinates()
            if (r0 == 0) goto L22
            boolean r1 = r0.isAttached()
            if (r1 == 0) goto L1a
            androidx.compose.ui.layout.LayoutCoordinates r1 = r5.getDecoratorNodeCoordinates()
            r2 = 0
            if (r1 == 0) goto L20
            r3 = 0
            r4 = 2
            androidx.compose.ui.geometry.Rect r2 = androidx.compose.ui.layout.c.c(r1, r0, r3, r4, r2)
            goto L20
        L1a:
            androidx.compose.ui.geometry.Rect$Companion r0 = androidx.compose.ui.geometry.Rect.INSTANCE
            androidx.compose.ui.geometry.Rect r2 = r0.getZero()
        L20:
            if (r2 != 0) goto L28
        L22:
            androidx.compose.ui.geometry.Rect$Companion r0 = androidx.compose.ui.geometry.Rect.INSTANCE
            androidx.compose.ui.geometry.Rect r2 = r0.getZero()
        L28:
            long r6 = androidx.compose.foundation.text2.input.internal.TextLayoutStateKt.m1130coerceIn3MmeM6k(r6, r2)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.TextLayoutState.m1123coercedInVisibleBoundsOfInputTextMKHz9U(long):long");
    }

    /* JADX INFO: renamed from: getOffsetForPosition-3MmeM6k$default, reason: not valid java name */
    public static /* synthetic */ int m1124getOffsetForPosition3MmeM6k$default(TextLayoutState textLayoutState, long j10, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = true;
        }
        return textLayoutState.m1126getOffsetForPosition3MmeM6k(j10, z);
    }

    public final LayoutCoordinates getCoreNodeCoordinates() {
        return (LayoutCoordinates) this.coreNodeCoordinates.getValue();
    }

    public final LayoutCoordinates getDecoratorNodeCoordinates() {
        return (LayoutCoordinates) this.decoratorNodeCoordinates.getValue();
    }

    public final TextLayoutResult getLayoutResult() {
        return this.layoutResult.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getMinHeightForSingleLineField-D9Ej5fM, reason: not valid java name */
    public final float m1125getMinHeightForSingleLineFieldD9Ej5fM() {
        return ((Dp) this.minHeightForSingleLineField.getValue()).m5692unboximpl();
    }

    /* JADX INFO: renamed from: getOffsetForPosition-3MmeM6k, reason: not valid java name */
    public final int m1126getOffsetForPosition3MmeM6k(long position, boolean coerceInVisibleBounds) {
        TextLayoutResult layoutResult = getLayoutResult();
        if (layoutResult == null) {
            return -1;
        }
        if (coerceInVisibleBounds) {
            position = m1123coercedInVisibleBoundsOfInputTextMKHz9U(position);
        }
        return layoutResult.m5164getOffsetForPositionk4lQ0M(TextLayoutStateKt.m1131fromDecorationToTextLayoutUv8p0NA(this, position));
    }

    public final p<Density, r7.a<TextLayoutResult>, t0> getOnTextLayout() {
        return this.onTextLayout;
    }

    public final LayoutCoordinates getTextLayoutNodeCoordinates() {
        return (LayoutCoordinates) this.textLayoutNodeCoordinates.getValue();
    }

    /* JADX INFO: renamed from: isPositionOnText-k-4lQ0M, reason: not valid java name */
    public final boolean m1127isPositionOnTextk4lQ0M(long offset) {
        TextLayoutResult layoutResult = getLayoutResult();
        if (layoutResult == null) {
            return false;
        }
        long jM1131fromDecorationToTextLayoutUv8p0NA = TextLayoutStateKt.m1131fromDecorationToTextLayoutUv8p0NA(this, m1123coercedInVisibleBoundsOfInputTextMKHz9U(offset));
        int lineForVerticalPosition = layoutResult.getLineForVerticalPosition(Offset.m3237getYimpl(jM1131fromDecorationToTextLayoutUv8p0NA));
        return Offset.m3236getXimpl(jM1131fromDecorationToTextLayoutUv8p0NA) >= layoutResult.getLineLeft(lineForVerticalPosition) && Offset.m3236getXimpl(jM1131fromDecorationToTextLayoutUv8p0NA) <= layoutResult.getLineRight(lineForVerticalPosition);
    }

    /* JADX INFO: renamed from: layoutWithNewMeasureInputs--hBUhpc, reason: not valid java name */
    public final TextLayoutResult m1128layoutWithNewMeasureInputshBUhpc(Density density, LayoutDirection layoutDirection, FontFamily.Resolver fontFamilyResolver, long constraints) {
        TextLayoutResult textLayoutResultM1117layoutWithNewMeasureInputshBUhpc = this.layoutCache.m1117layoutWithNewMeasureInputshBUhpc(density, layoutDirection, fontFamilyResolver, constraints);
        p<? super Density, ? super r7.a<TextLayoutResult>, t0> pVar = this.onTextLayout;
        if (pVar != null) {
            pVar.invoke(density, new TextLayoutState$layoutWithNewMeasureInputs$1$textLayoutProvider$1(this));
        }
        return textLayoutResultM1117layoutWithNewMeasureInputshBUhpc;
    }

    public final void setCoreNodeCoordinates(LayoutCoordinates layoutCoordinates) {
        this.coreNodeCoordinates.setValue(layoutCoordinates);
    }

    public final void setDecoratorNodeCoordinates(LayoutCoordinates layoutCoordinates) {
        this.decoratorNodeCoordinates.setValue(layoutCoordinates);
    }

    /* JADX INFO: renamed from: setMinHeightForSingleLineField-0680j_4, reason: not valid java name */
    public final void m1129setMinHeightForSingleLineField0680j_4(float f10) {
        this.minHeightForSingleLineField.setValue(Dp.m5676boximpl(f10));
    }

    public final void setOnTextLayout(p<? super Density, ? super r7.a<TextLayoutResult>, t0> pVar) {
        this.onTextLayout = pVar;
    }

    public final void setTextLayoutNodeCoordinates(LayoutCoordinates layoutCoordinates) {
        this.textLayoutNodeCoordinates.setValue(layoutCoordinates);
    }

    public final void updateNonMeasureInputs(TransformedTextFieldState textFieldState, TextStyle textStyle, boolean singleLine, boolean softWrap) {
        this.layoutCache.updateNonMeasureInputs(textFieldState, textStyle, singleLine, softWrap);
    }
}
