package androidx.compose.foundation.text2.input;

import androidx.compose.foundation.text2.input.TextFieldState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.TextFieldValue;
import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import na.j;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0003\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\u00020\u0002H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a&\u0010\u000f\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a\u001b\u0010\u0012\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\tH\u0007¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u001b\u0010\u0014\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\tH\u0007¢\u0006\u0004\b\u0014\u0010\u0013\u001a\u0013\u0010\u0015\u001a\u00020\u0011*\u00020\u0002H\u0007¢\u0006\u0004\b\u0015\u0010\u0016\u001a8\u0010\u001c\u001a\u00020\u001b*\u00020\u00022\"\u0010\u001a\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0017H\u0087@¢\u0006\u0004\b\u001c\u0010\u001d\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001e"}, d2 = {"Landroidx/compose/ui/text/input/TextFieldValue;", "initialValue", "Landroidx/compose/foundation/text2/input/TextFieldState;", "TextFieldState", "(Landroidx/compose/ui/text/input/TextFieldValue;)Landroidx/compose/foundation/text2/input/TextFieldState;", "Lna/j;", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "textAsFlow", "(Landroidx/compose/foundation/text2/input/TextFieldState;)Lna/j;", "", "initialText", "Landroidx/compose/ui/text/TextRange;", "initialSelectionInChars", "rememberTextFieldState-Le-punE", "(Ljava/lang/String;JLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/text2/input/TextFieldState;", "rememberTextFieldState", "text", "Lx6/t0;", "setTextAndPlaceCursorAtEnd", "(Landroidx/compose/foundation/text2/input/TextFieldState;Ljava/lang/String;)V", "setTextAndSelectAll", "clearText", "(Landroidx/compose/foundation/text2/input/TextFieldState;)V", "Lkotlin/Function2;", "Ld7/d;", "", "block", "", "forEachTextValue", "(Landroidx/compose/foundation/text2/input/TextFieldState;Lr7/p;Ld7/d;)Ljava/lang/Object;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextFieldStateKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.TextFieldStateKt$forEachTextValue$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "androidx.compose.foundation.text2.input.TextFieldStateKt", f = "TextFieldState.kt", l = {566}, m = "forEachTextValue")
    public static final class AnonymousClass1 extends c {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TextFieldStateKt.forEachTextValue(null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text2.input.TextFieldStateKt$textAsFlow$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/text2/input/TextFieldCharSequence;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04081 extends r implements r7.a<TextFieldCharSequence> {
        final /* synthetic */ TextFieldState $this_textAsFlow;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04081(TextFieldState textFieldState) {
            super(0);
            this.$this_textAsFlow = textFieldState;
        }

        @Override // r7.a
        public final TextFieldCharSequence invoke() {
            return this.$this_textAsFlow.getText();
        }
    }

    public static final TextFieldState TextFieldState(TextFieldValue textFieldValue) {
        return new TextFieldState(textFieldValue.getText(), textFieldValue.getSelection(), (h) null);
    }

    public static final void clearText(TextFieldState textFieldState) {
        TextFieldBuffer textFieldBufferStartEdit = textFieldState.startEdit(textFieldState.getText());
        TextFieldBufferKt.delete(textFieldBufferStartEdit, 0, textFieldBufferStartEdit.getLength());
        TextFieldBufferKt.placeCursorAtEnd(textFieldBufferStartEdit);
        textFieldState.commitEdit(textFieldBufferStartEdit);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object forEachTextValue(androidx.compose.foundation.text2.input.TextFieldState r4, r7.p<? super androidx.compose.foundation.text2.input.TextFieldCharSequence, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r5, d7.d<?> r6) {
        /*
            boolean r0 = r6 instanceof androidx.compose.foundation.text2.input.TextFieldStateKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            androidx.compose.foundation.text2.input.TextFieldStateKt$forEachTextValue$1 r0 = (androidx.compose.foundation.text2.input.TextFieldStateKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.text2.input.TextFieldStateKt$forEachTextValue$1 r0 = new androidx.compose.foundation.text2.input.TextFieldStateKt$forEachTextValue$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 == r2) goto L29
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L29:
            k2.c.G(r6)
            goto L3f
        L2d:
            k2.c.G(r6)
            na.j r4 = textAsFlow(r4)
            r0.label = r2
            java.lang.Object r4 = na.y0.g(r4, r5, r0)
            e7.a r5 = e7.a.f15033i
            if (r4 != r5) goto L3f
            return r5
        L3f:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "textAsFlow expected not to complete without exception"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.TextFieldStateKt.forEachTextValue(androidx.compose.foundation.text2.input.TextFieldState, r7.p, d7.d):java.lang.Object");
    }

    /* JADX INFO: renamed from: rememberTextFieldState-Le-punE, reason: not valid java name */
    public static final TextFieldState m1070rememberTextFieldStateLepunE(String str, long j10, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-855595317);
        if ((i11 & 1) != 0) {
            str = "";
        }
        if ((i11 & 2) != 0) {
            j10 = TextRangeKt.TextRange(str.length());
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-855595317, i10, -1, "androidx.compose.foundation.text2.input.rememberTextFieldState (TextFieldState.kt:471)");
        }
        Object[] objArr = new Object[0];
        TextFieldState.Saver saver = TextFieldState.Saver.INSTANCE;
        composer.startReplaceableGroup(650674345);
        boolean zChanged = composer.changed(str) | composer.changed(j10);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new TextFieldStateKt$rememberTextFieldState$1$1(str, j10);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        TextFieldState textFieldState = (TextFieldState) RememberSaveableKt.m3084rememberSaveable(objArr, (Saver) saver, (String) null, (r7.a) objRememberedValue, composer, 56, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return textFieldState;
    }

    public static final void setTextAndPlaceCursorAtEnd(TextFieldState textFieldState, String str) {
        TextFieldBuffer textFieldBufferStartEdit = textFieldState.startEdit(textFieldState.getText());
        textFieldBufferStartEdit.replace(0, textFieldBufferStartEdit.getLength(), str);
        TextFieldBufferKt.placeCursorAtEnd(textFieldBufferStartEdit);
        textFieldState.commitEdit(textFieldBufferStartEdit);
    }

    public static final void setTextAndSelectAll(TextFieldState textFieldState, String str) {
        TextFieldBuffer textFieldBufferStartEdit = textFieldState.startEdit(textFieldState.getText());
        textFieldBufferStartEdit.replace(0, textFieldBufferStartEdit.getLength(), str);
        TextFieldBufferKt.selectAll(textFieldBufferStartEdit);
        textFieldState.commitEdit(textFieldBufferStartEdit);
    }

    public static final j<TextFieldCharSequence> textAsFlow(TextFieldState textFieldState) {
        return SnapshotStateKt.snapshotFlow(new C04081(textFieldState));
    }
}
