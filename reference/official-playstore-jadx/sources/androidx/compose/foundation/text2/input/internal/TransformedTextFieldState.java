package androidx.compose.foundation.text2.input.internal;

import androidx.compose.foundation.text2.input.CodepointTransformation;
import androidx.compose.foundation.text2.input.CodepointTransformationKt;
import androidx.compose.foundation.text2.input.InputTransformation;
import androidx.compose.foundation.text2.input.TextFieldCharSequence;
import androidx.compose.foundation.text2.input.TextFieldCharSequenceKt;
import androidx.compose.foundation.text2.input.TextFieldState;
import androidx.compose.foundation.text2.input.internal.undo.TextFieldEditUndoBehavior;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import q7.n;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\r\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0001\u0018\u0000 V2\u00020\u0001:\u0002VWB#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\u0018\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000fø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u000fø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0012J\u0015\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\f¢\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001d\u001a\u00020\f¢\u0006\u0004\b\u001d\u0010\u001cJ*\u0010#\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u000f2\b\b\u0002\u0010 \u001a\u00020\u001fø\u0001\u0000¢\u0006\u0004\b!\u0010\"J)\u0010&\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010%\u001a\u00020$2\b\b\u0002\u0010 \u001a\u00020\u001f¢\u0006\u0004\b&\u0010'J\r\u0010(\u001a\u00020\f¢\u0006\u0004\b(\u0010\u001cJ\r\u0010)\u001a\u00020\f¢\u0006\u0004\b)\u0010\u001cJ\r\u0010*\u001a\u00020\f¢\u0006\u0004\b*\u0010\u001cJ\r\u0010+\u001a\u00020\f¢\u0006\u0004\b+\u0010\u001cJ3\u00101\u001a\u00020\f2\b\b\u0002\u0010,\u001a\u00020$2\u0017\u00100\u001a\u0013\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\f0-¢\u0006\u0002\b/H\u0086\b¢\u0006\u0004\b1\u00102J\u001b\u00106\u001a\u00020\u000f2\u0006\u00103\u001a\u00020\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b4\u00105J\u0018\u00106\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u000fø\u0001\u0000¢\u0006\u0004\b7\u00108J\u0015\u00109\u001a\u00020\n2\u0006\u00103\u001a\u00020\n¢\u0006\u0004\b9\u0010:J\u0018\u00109\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u000fø\u0001\u0000¢\u0006\u0004\b;\u00108J\u0018\u0010?\u001a\u00020>2\u0006\u0010=\u001a\u00020<H\u0086@¢\u0006\u0004\b?\u0010@J\u001a\u0010B\u001a\u00020$2\b\u0010A\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\bB\u0010CJ\u000f\u0010D\u001a\u00020\nH\u0016¢\u0006\u0004\bD\u0010EJ\u000f\u0010G\u001a\u00020FH\u0016¢\u0006\u0004\bG\u0010HR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010IR\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010JR\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010KR\u001e\u0010N\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010M\u0018\u00010L8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bN\u0010OR\u0011\u0010S\u001a\u00020P8F¢\u0006\u0006\u001a\u0004\bQ\u0010RR\u0011\u0010U\u001a\u00020P8F¢\u0006\u0006\u001a\u0004\bT\u0010R\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006X"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState;", "", "Landroidx/compose/foundation/text2/input/TextFieldState;", "textFieldState", "Landroidx/compose/foundation/text2/input/InputTransformation;", "inputTransformation", "Landroidx/compose/foundation/text2/input/CodepointTransformation;", "codepointTransformation", "<init>", "(Landroidx/compose/foundation/text2/input/TextFieldState;Landroidx/compose/foundation/text2/input/InputTransformation;Landroidx/compose/foundation/text2/input/CodepointTransformation;)V", "", "transformedOffset", "Lx6/t0;", "placeCursorBeforeCharAt", "(I)V", "Landroidx/compose/ui/text/TextRange;", "transformedRange", "selectCharsIn-5zc-tL8", "(J)V", "selectCharsIn", "untransformedRange", "selectUntransformedCharsIn-5zc-tL8", "selectUntransformedCharsIn", "", "newText", "replaceAll", "(Ljava/lang/CharSequence;)V", "selectAll", "()V", "deleteSelectedText", "range", "Landroidx/compose/foundation/text2/input/internal/undo/TextFieldEditUndoBehavior;", "undoBehavior", "replaceText-Sb-Bc2M", "(Ljava/lang/CharSequence;JLandroidx/compose/foundation/text2/input/internal/undo/TextFieldEditUndoBehavior;)V", "replaceText", "", "clearComposition", "replaceSelectedText", "(Ljava/lang/CharSequence;ZLandroidx/compose/foundation/text2/input/internal/undo/TextFieldEditUndoBehavior;)V", "collapseSelectionToMax", "collapseSelectionToEnd", "undo", "redo", "notifyImeOfChanges", "Lkotlin/Function1;", "Landroidx/compose/foundation/text2/input/internal/EditingBuffer;", "Lx6/n;", "block", "editUntransformedTextAsUser", "(ZLr7/l;)V", "offset", "mapToTransformed--jx7JFs", "(I)J", "mapToTransformed", "mapToTransformed-GEjPoXI", "(J)J", "mapFromTransformed", "(I)I", "mapFromTransformed-GEjPoXI", "Landroidx/compose/foundation/text2/input/TextFieldState$NotifyImeListener;", "notifyImeListener", "", "collectImeNotifications", "(Landroidx/compose/foundation/text2/input/TextFieldState$NotifyImeListener;Ld7/d;)Ljava/lang/Object;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "Landroidx/compose/foundation/text2/input/TextFieldState;", "Landroidx/compose/foundation/text2/input/InputTransformation;", "Landroidx/compose/foundation/text2/input/CodepointTransformation;", "Landroidx/compose/runtime/State;", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState$TransformedText;", "transformedText", "Landroidx/compose/runtime/State;", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "getText", "()Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "text", "getUntransformedText", "untransformedText", "Companion", "TransformedText", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TransformedTextFieldState {
    public static final int $stable = 0;
    private static final Companion Companion = new Companion(null);
    private final CodepointTransformation codepointTransformation;
    private final InputTransformation inputTransformation;
    private final TextFieldState textFieldState;
    private final State<TransformedText> transformedText;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0003J\"\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0003ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\"\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0003ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u000f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0012"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState$Companion;", "", "()V", "calculateTransformedText", "Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState$TransformedText;", "untransformedText", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "codepointTransformation", "Landroidx/compose/foundation/text2/input/CodepointTransformation;", "mapFromTransformed", "Landroidx/compose/ui/text/TextRange;", "range", "mapping", "Landroidx/compose/foundation/text2/input/internal/OffsetMappingCalculator;", "mapFromTransformed-xdX6-G0", "(JLandroidx/compose/foundation/text2/input/internal/OffsetMappingCalculator;)J", "mapToTransformed", "mapToTransformed-xdX6-G0", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        @n
        public final TransformedText calculateTransformedText(TextFieldCharSequence untransformedText, CodepointTransformation codepointTransformation) {
            OffsetMappingCalculator offsetMappingCalculator = new OffsetMappingCalculator();
            CharSequence visualText = CodepointTransformationKt.toVisualText(untransformedText, codepointTransformation, offsetMappingCalculator);
            TextRange textRangeM5180boximpl = null;
            if (visualText == untransformedText) {
                return null;
            }
            long jM1145mapToTransformedxdX6G0 = m1145mapToTransformedxdX6G0(untransformedText.getSelectionInChars(), offsetMappingCalculator);
            TextRange textRangeMo1064getCompositionInCharsMzsxiRA = untransformedText.getCompositionInChars();
            if (textRangeMo1064getCompositionInCharsMzsxiRA != null) {
                textRangeM5180boximpl = TextRange.m5180boximpl(TransformedTextFieldState.Companion.m1145mapToTransformedxdX6G0(textRangeMo1064getCompositionInCharsMzsxiRA.getPackedValue(), offsetMappingCalculator));
            }
            return new TransformedText(TextFieldCharSequenceKt.m1066TextFieldCharSequence3r_uNRQ(visualText, jM1145mapToTransformedxdX6G0, textRangeM5180boximpl), offsetMappingCalculator);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @n
        /* JADX INFO: renamed from: mapFromTransformed-xdX6-G0, reason: not valid java name */
        public final long m1144mapFromTransformedxdX6G0(long range, OffsetMappingCalculator mapping) {
            long jM1088mapFromDestjx7JFs = mapping.m1088mapFromDestjx7JFs(TextRange.m5192getStartimpl(range));
            long jM1088mapFromDestjx7JFs2 = TextRange.m5186getCollapsedimpl(range) ? jM1088mapFromDestjx7JFs : mapping.m1088mapFromDestjx7JFs(TextRange.m5187getEndimpl(range));
            int iMin = Math.min(TextRange.m5190getMinimpl(jM1088mapFromDestjx7JFs), TextRange.m5190getMinimpl(jM1088mapFromDestjx7JFs2));
            int iMax = Math.max(TextRange.m5189getMaximpl(jM1088mapFromDestjx7JFs), TextRange.m5189getMaximpl(jM1088mapFromDestjx7JFs2));
            return TextRange.m5191getReversedimpl(range) ? TextRangeKt.TextRange(iMax, iMin) : TextRangeKt.TextRange(iMin, iMax);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @n
        /* JADX INFO: renamed from: mapToTransformed-xdX6-G0, reason: not valid java name */
        public final long m1145mapToTransformedxdX6G0(long range, OffsetMappingCalculator mapping) {
            long jM1089mapFromSourcejx7JFs = mapping.m1089mapFromSourcejx7JFs(TextRange.m5192getStartimpl(range));
            long jM1089mapFromSourcejx7JFs2 = TextRange.m5186getCollapsedimpl(range) ? jM1089mapFromSourcejx7JFs : mapping.m1089mapFromSourcejx7JFs(TextRange.m5187getEndimpl(range));
            int iMin = Math.min(TextRange.m5190getMinimpl(jM1089mapFromSourcejx7JFs), TextRange.m5190getMinimpl(jM1089mapFromSourcejx7JFs2));
            int iMax = Math.max(TextRange.m5189getMaximpl(jM1089mapFromSourcejx7JFs), TextRange.m5189getMaximpl(jM1089mapFromSourcejx7JFs2));
            return TextRange.m5191getReversedimpl(range) ? TextRangeKt.TextRange(iMax, iMin) : TextRangeKt.TextRange(iMin, iMax);
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Landroidx/compose/foundation/text2/input/internal/TransformedTextFieldState$TransformedText;", "", "text", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "offsetMapping", "Landroidx/compose/foundation/text2/input/internal/OffsetMappingCalculator;", "(Landroidx/compose/foundation/text2/input/TextFieldCharSequence;Landroidx/compose/foundation/text2/input/internal/OffsetMappingCalculator;)V", "getOffsetMapping", "()Landroidx/compose/foundation/text2/input/internal/OffsetMappingCalculator;", "getText", "()Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final /* data */ class TransformedText {
        private final OffsetMappingCalculator offsetMapping;
        private final TextFieldCharSequence text;

        public TransformedText(TextFieldCharSequence textFieldCharSequence, OffsetMappingCalculator offsetMappingCalculator) {
            this.text = textFieldCharSequence;
            this.offsetMapping = offsetMappingCalculator;
        }

        public static /* synthetic */ TransformedText copy$default(TransformedText transformedText, TextFieldCharSequence textFieldCharSequence, OffsetMappingCalculator offsetMappingCalculator, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                textFieldCharSequence = transformedText.text;
            }
            if ((i10 & 2) != 0) {
                offsetMappingCalculator = transformedText.offsetMapping;
            }
            return transformedText.copy(textFieldCharSequence, offsetMappingCalculator);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final TextFieldCharSequence getText() {
            return this.text;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final OffsetMappingCalculator getOffsetMapping() {
            return this.offsetMapping;
        }

        public final TransformedText copy(TextFieldCharSequence text, OffsetMappingCalculator offsetMapping) {
            return new TransformedText(text, offsetMapping);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TransformedText)) {
                return false;
            }
            TransformedText transformedText = (TransformedText) other;
            return p.a(this.text, transformedText.text) && p.a(this.offsetMapping, transformedText.offsetMapping);
        }

        public final OffsetMappingCalculator getOffsetMapping() {
            return this.offsetMapping;
        }

        public final TextFieldCharSequence getText() {
            return this.text;
        }

        public int hashCode() {
            return this.offsetMapping.hashCode() + (this.text.hashCode() * 31);
        }

        public String toString() {
            return "TransformedText(text=" + ((Object) this.text) + ", offsetMapping=" + this.offsetMapping + ')';
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.internal.TransformedTextFieldState$collectImeNotifications$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.foundation.text2.input.internal.TransformedTextFieldState", f = "TransformedTextFieldState.kt", l = {394}, m = "collectImeNotifications")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TransformedTextFieldState.this.collectImeNotifications(null, this);
        }
    }

    public TransformedTextFieldState(TextFieldState textFieldState, InputTransformation inputTransformation, CodepointTransformation codepointTransformation) {
        this.textFieldState = textFieldState;
        this.inputTransformation = inputTransformation;
        this.codepointTransformation = codepointTransformation;
        this.transformedText = codepointTransformation != null ? SnapshotStateKt.derivedStateOf(new TransformedTextFieldState$transformedText$1$1(this, codepointTransformation)) : null;
    }

    @n
    private static final TransformedText calculateTransformedText(TextFieldCharSequence textFieldCharSequence, CodepointTransformation codepointTransformation) {
        return Companion.calculateTransformedText(textFieldCharSequence, codepointTransformation);
    }

    public static /* synthetic */ void editUntransformedTextAsUser$default(TransformedTextFieldState transformedTextFieldState, boolean z, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = true;
        }
        TextFieldState textFieldState = transformedTextFieldState.textFieldState;
        InputTransformation inputTransformation = transformedTextFieldState.inputTransformation;
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        lVar.invoke(textFieldState.getMainBuffer());
        if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5185equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1084getSelectiond9O1mEE()) && p.a(text.getCompositionInChars(), textFieldState.getMainBuffer().m1083getCompositionMzsxiRA())) {
            return;
        }
        textFieldState.commitEditAsUser(text, inputTransformation, z, textFieldEditUndoBehavior);
    }

    @n
    /* JADX INFO: renamed from: mapFromTransformed-xdX6-G0, reason: not valid java name */
    private static final long m1133mapFromTransformedxdX6G0(long j10, OffsetMappingCalculator offsetMappingCalculator) {
        return Companion.m1144mapFromTransformedxdX6G0(j10, offsetMappingCalculator);
    }

    @n
    /* JADX INFO: renamed from: mapToTransformed-xdX6-G0, reason: not valid java name */
    private static final long m1134mapToTransformedxdX6G0(long j10, OffsetMappingCalculator offsetMappingCalculator) {
        return Companion.m1145mapToTransformedxdX6G0(j10, offsetMappingCalculator);
    }

    public static /* synthetic */ void replaceSelectedText$default(TransformedTextFieldState transformedTextFieldState, CharSequence charSequence, boolean z, TextFieldEditUndoBehavior textFieldEditUndoBehavior, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        if ((i10 & 4) != 0) {
            textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        }
        transformedTextFieldState.replaceSelectedText(charSequence, z, textFieldEditUndoBehavior);
    }

    /* JADX INFO: renamed from: replaceText-Sb-Bc2M$default, reason: not valid java name */
    public static /* synthetic */ void m1135replaceTextSbBc2M$default(TransformedTextFieldState transformedTextFieldState, CharSequence charSequence, long j10, TextFieldEditUndoBehavior textFieldEditUndoBehavior, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        }
        transformedTextFieldState.m1139replaceTextSbBc2M(charSequence, j10, textFieldEditUndoBehavior);
    }

    public final void collapseSelectionToEnd() {
        TextFieldState textFieldState = this.textFieldState;
        InputTransformation inputTransformation = this.inputTransformation;
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        EditingBuffer mainBuffer = textFieldState.getMainBuffer();
        mainBuffer.setSelection(TextRange.m5187getEndimpl(mainBuffer.m1084getSelectiond9O1mEE()), TextRange.m5187getEndimpl(mainBuffer.m1084getSelectiond9O1mEE()));
        if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5185equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1084getSelectiond9O1mEE()) && p.a(text.getCompositionInChars(), textFieldState.getMainBuffer().m1083getCompositionMzsxiRA())) {
            return;
        }
        textFieldState.commitEditAsUser(text, inputTransformation, true, textFieldEditUndoBehavior);
    }

    public final void collapseSelectionToMax() {
        TextFieldState textFieldState = this.textFieldState;
        InputTransformation inputTransformation = this.inputTransformation;
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        EditingBuffer mainBuffer = textFieldState.getMainBuffer();
        mainBuffer.setSelection(TextRange.m5189getMaximpl(mainBuffer.m1084getSelectiond9O1mEE()), TextRange.m5189getMaximpl(mainBuffer.m1084getSelectiond9O1mEE()));
        if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5185equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1084getSelectiond9O1mEE()) && p.a(text.getCompositionInChars(), textFieldState.getMainBuffer().m1083getCompositionMzsxiRA())) {
            return;
        }
        textFieldState.commitEditAsUser(text, inputTransformation, true, textFieldEditUndoBehavior);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object collectImeNotifications(androidx.compose.foundation.text2.input.TextFieldState.NotifyImeListener r5, d7.d<?> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof androidx.compose.foundation.text2.input.internal.TransformedTextFieldState.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            androidx.compose.foundation.text2.input.internal.TransformedTextFieldState$collectImeNotifications$1 r0 = (androidx.compose.foundation.text2.input.internal.TransformedTextFieldState.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.text2.input.internal.TransformedTextFieldState$collectImeNotifications$1 r0 = new androidx.compose.foundation.text2.input.internal.TransformedTextFieldState$collectImeNotifications$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L35
            if (r1 == r2) goto L29
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L29:
            java.lang.Object r5 = r0.L$1
            androidx.compose.foundation.text2.input.TextFieldState$NotifyImeListener r5 = (androidx.compose.foundation.text2.input.TextFieldState.NotifyImeListener) r5
            java.lang.Object r5 = r0.L$0
            androidx.compose.foundation.text2.input.internal.TransformedTextFieldState r5 = (androidx.compose.foundation.text2.input.internal.TransformedTextFieldState) r5
            k2.c.G(r6)
            goto L62
        L35:
            k2.c.G(r6)
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r2
            ka.l r6 = new ka.l
            d7.d r0 = t7.a.A(r0)
            r6.<init>(r2, r0)
            r6.s()
            androidx.compose.foundation.text2.input.TextFieldState r0 = access$getTextFieldState$p(r4)
            r0.addNotifyImeListener$foundation_release(r5)
            androidx.compose.foundation.text2.input.internal.TransformedTextFieldState$collectImeNotifications$2$1 r0 = new androidx.compose.foundation.text2.input.internal.TransformedTextFieldState$collectImeNotifications$2$1
            r0.<init>(r4, r5)
            r6.c(r0)
            java.lang.Object r5 = r6.r()
            e7.a r6 = e7.a.f15033i
            if (r5 != r6) goto L62
            return r6
        L62:
            kotlin.KotlinNothingValueException r5 = new kotlin.KotlinNothingValueException
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.TransformedTextFieldState.collectImeNotifications(androidx.compose.foundation.text2.input.TextFieldState$NotifyImeListener, d7.d):java.lang.Object");
    }

    public final void deleteSelectedText() {
        TextFieldState textFieldState = this.textFieldState;
        InputTransformation inputTransformation = this.inputTransformation;
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.NeverMerge;
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        EditingBuffer mainBuffer = textFieldState.getMainBuffer();
        mainBuffer.delete(TextRange.m5190getMinimpl(mainBuffer.m1084getSelectiond9O1mEE()), TextRange.m5189getMaximpl(mainBuffer.m1084getSelectiond9O1mEE()));
        mainBuffer.setSelection(TextRange.m5190getMinimpl(mainBuffer.m1084getSelectiond9O1mEE()), TextRange.m5190getMinimpl(mainBuffer.m1084getSelectiond9O1mEE()));
        if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5185equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1084getSelectiond9O1mEE()) && p.a(text.getCompositionInChars(), textFieldState.getMainBuffer().m1083getCompositionMzsxiRA())) {
            return;
        }
        textFieldState.commitEditAsUser(text, inputTransformation, true, textFieldEditUndoBehavior);
    }

    public final void editUntransformedTextAsUser(boolean notifyImeOfChanges, l<? super EditingBuffer, t0> block) {
        TextFieldState textFieldState = this.textFieldState;
        InputTransformation inputTransformation = this.inputTransformation;
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        block.invoke(textFieldState.getMainBuffer());
        if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5185equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1084getSelectiond9O1mEE()) && p.a(text.getCompositionInChars(), textFieldState.getMainBuffer().m1083getCompositionMzsxiRA())) {
            return;
        }
        textFieldState.commitEditAsUser(text, inputTransformation, notifyImeOfChanges, textFieldEditUndoBehavior);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TransformedTextFieldState)) {
            return false;
        }
        TransformedTextFieldState transformedTextFieldState = (TransformedTextFieldState) other;
        if (p.a(this.textFieldState, transformedTextFieldState.textFieldState)) {
            return p.a(this.codepointTransformation, transformedTextFieldState.codepointTransformation);
        }
        return false;
    }

    public final TextFieldCharSequence getText() {
        TransformedText value;
        TextFieldCharSequence text;
        State<TransformedText> state = this.transformedText;
        return (state == null || (value = state.getValue()) == null || (text = value.getText()) == null) ? this.textFieldState.getText() : text;
    }

    public final TextFieldCharSequence getUntransformedText() {
        return this.textFieldState.getText();
    }

    public int hashCode() {
        int iHashCode = this.textFieldState.hashCode() * 31;
        CodepointTransformation codepointTransformation = this.codepointTransformation;
        return iHashCode + (codepointTransformation != null ? codepointTransformation.hashCode() : 0);
    }

    public final int mapFromTransformed(int offset) {
        TransformedText value;
        OffsetMappingCalculator offsetMapping;
        State<TransformedText> state = this.transformedText;
        return (state == null || (value = state.getValue()) == null || (offsetMapping = value.getOffsetMapping()) == null) ? offset : TextRange.m5190getMinimpl(offsetMapping.m1088mapFromDestjx7JFs(offset));
    }

    /* JADX INFO: renamed from: mapFromTransformed-GEjPoXI, reason: not valid java name */
    public final long m1136mapFromTransformedGEjPoXI(long range) {
        TransformedText value;
        OffsetMappingCalculator offsetMapping;
        State<TransformedText> state = this.transformedText;
        return (state == null || (value = state.getValue()) == null || (offsetMapping = value.getOffsetMapping()) == null) ? range : Companion.m1144mapFromTransformedxdX6G0(range, offsetMapping);
    }

    /* JADX INFO: renamed from: mapToTransformed--jx7JFs, reason: not valid java name */
    public final long m1137mapToTransformedjx7JFs(int offset) {
        TransformedText value;
        OffsetMappingCalculator offsetMapping;
        State<TransformedText> state = this.transformedText;
        return (state == null || (value = state.getValue()) == null || (offsetMapping = value.getOffsetMapping()) == null) ? TextRangeKt.TextRange(offset) : offsetMapping.m1089mapFromSourcejx7JFs(offset);
    }

    /* JADX INFO: renamed from: mapToTransformed-GEjPoXI, reason: not valid java name */
    public final long m1138mapToTransformedGEjPoXI(long range) {
        TransformedText value;
        OffsetMappingCalculator offsetMapping;
        State<TransformedText> state = this.transformedText;
        return (state == null || (value = state.getValue()) == null || (offsetMapping = value.getOffsetMapping()) == null) ? range : Companion.m1145mapToTransformedxdX6G0(range, offsetMapping);
    }

    public final void placeCursorBeforeCharAt(int transformedOffset) {
        m1140selectCharsIn5zctL8(TextRangeKt.TextRange(transformedOffset));
    }

    public final void redo() {
        this.textFieldState.getUndoState().redo();
    }

    public final void replaceAll(CharSequence newText) {
        TextFieldState textFieldState = this.textFieldState;
        InputTransformation inputTransformation = this.inputTransformation;
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        EditingBuffer mainBuffer = textFieldState.getMainBuffer();
        EditCommandKt.deleteAll(mainBuffer);
        EditCommandKt.commitText(mainBuffer, newText.toString(), 1);
        if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5185equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1084getSelectiond9O1mEE()) && p.a(text.getCompositionInChars(), textFieldState.getMainBuffer().m1083getCompositionMzsxiRA())) {
            return;
        }
        textFieldState.commitEditAsUser(text, inputTransformation, true, textFieldEditUndoBehavior);
    }

    public final void replaceSelectedText(CharSequence newText, boolean clearComposition, TextFieldEditUndoBehavior undoBehavior) {
        TextFieldState textFieldState = this.textFieldState;
        InputTransformation inputTransformation = this.inputTransformation;
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        EditingBuffer mainBuffer = textFieldState.getMainBuffer();
        if (clearComposition) {
            mainBuffer.commitComposition();
        }
        long jM1084getSelectiond9O1mEE = mainBuffer.m1084getSelectiond9O1mEE();
        mainBuffer.replace(TextRange.m5190getMinimpl(jM1084getSelectiond9O1mEE), TextRange.m5189getMaximpl(jM1084getSelectiond9O1mEE), newText);
        int length = newText.length() + TextRange.m5190getMinimpl(jM1084getSelectiond9O1mEE);
        mainBuffer.setSelection(length, length);
        if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5185equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1084getSelectiond9O1mEE()) && p.a(text.getCompositionInChars(), textFieldState.getMainBuffer().m1083getCompositionMzsxiRA())) {
            return;
        }
        textFieldState.commitEditAsUser(text, inputTransformation, true, undoBehavior);
    }

    /* JADX INFO: renamed from: replaceText-Sb-Bc2M, reason: not valid java name */
    public final void m1139replaceTextSbBc2M(CharSequence newText, long range, TextFieldEditUndoBehavior undoBehavior) {
        TextFieldState textFieldState = this.textFieldState;
        InputTransformation inputTransformation = this.inputTransformation;
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        EditingBuffer mainBuffer = textFieldState.getMainBuffer();
        long jM1136mapFromTransformedGEjPoXI = m1136mapFromTransformedGEjPoXI(range);
        mainBuffer.replace(TextRange.m5190getMinimpl(jM1136mapFromTransformedGEjPoXI), TextRange.m5189getMaximpl(jM1136mapFromTransformedGEjPoXI), newText);
        int length = newText.length() + TextRange.m5190getMinimpl(jM1136mapFromTransformedGEjPoXI);
        mainBuffer.setSelection(length, length);
        if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5185equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1084getSelectiond9O1mEE()) && p.a(text.getCompositionInChars(), textFieldState.getMainBuffer().m1083getCompositionMzsxiRA())) {
            return;
        }
        textFieldState.commitEditAsUser(text, inputTransformation, true, undoBehavior);
    }

    public final void selectAll() {
        TextFieldState textFieldState = this.textFieldState;
        InputTransformation inputTransformation = this.inputTransformation;
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        EditingBuffer mainBuffer = textFieldState.getMainBuffer();
        mainBuffer.setSelection(0, mainBuffer.getLength());
        if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5185equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1084getSelectiond9O1mEE()) && p.a(text.getCompositionInChars(), textFieldState.getMainBuffer().m1083getCompositionMzsxiRA())) {
            return;
        }
        textFieldState.commitEditAsUser(text, inputTransformation, true, textFieldEditUndoBehavior);
    }

    /* JADX INFO: renamed from: selectCharsIn-5zc-tL8, reason: not valid java name */
    public final void m1140selectCharsIn5zctL8(long transformedRange) {
        m1141selectUntransformedCharsIn5zctL8(m1136mapFromTransformedGEjPoXI(transformedRange));
    }

    /* JADX INFO: renamed from: selectUntransformedCharsIn-5zc-tL8, reason: not valid java name */
    public final void m1141selectUntransformedCharsIn5zctL8(long untransformedRange) {
        TextFieldState textFieldState = this.textFieldState;
        InputTransformation inputTransformation = this.inputTransformation;
        TextFieldEditUndoBehavior textFieldEditUndoBehavior = TextFieldEditUndoBehavior.MergeIfPossible;
        TextFieldCharSequence text = textFieldState.getText();
        textFieldState.getMainBuffer().getChangeTracker().clearChanges();
        textFieldState.getMainBuffer().setSelection(TextRange.m5192getStartimpl(untransformedRange), TextRange.m5187getEndimpl(untransformedRange));
        if (textFieldState.getMainBuffer().getChangeTracker().getChangeCount() == 0 && TextRange.m5185equalsimpl0(text.getSelectionInChars(), textFieldState.getMainBuffer().m1084getSelectiond9O1mEE()) && p.a(text.getCompositionInChars(), textFieldState.getMainBuffer().m1083getCompositionMzsxiRA())) {
            return;
        }
        textFieldState.commitEditAsUser(text, inputTransformation, true, textFieldEditUndoBehavior);
    }

    public String toString() {
        return "TransformedTextFieldState(textFieldState=" + this.textFieldState + ", codepointTransformation=" + this.codepointTransformation + ", transformedText=" + this.transformedText + ", text=\"" + ((Object) getText()) + "\")";
    }

    public final void undo() {
        this.textFieldState.getUndoState().undo();
    }
}
