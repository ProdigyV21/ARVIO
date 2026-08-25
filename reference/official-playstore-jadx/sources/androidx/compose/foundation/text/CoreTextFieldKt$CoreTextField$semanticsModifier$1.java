package androidx.compose.foundation.text;

import androidx.compose.foundation.c;
import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.text.input.CommitTextCommand;
import androidx.compose.ui.text.input.DeleteAllCommand;
import androidx.compose.ui.text.input.FinishComposingTextCommand;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.ImeOptions;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.TextInputSession;
import androidx.compose.ui.text.input.TransformedText;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class CoreTextFieldKt$CoreTextField$semanticsModifier$1 extends r implements l<SemanticsPropertyReceiver, t0> {
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ FocusRequester $focusRequester;
    final /* synthetic */ ImeOptions $imeOptions;
    final /* synthetic */ boolean $isPassword;
    final /* synthetic */ TextFieldSelectionManager $manager;
    final /* synthetic */ OffsetMapping $offsetMapping;
    final /* synthetic */ boolean $readOnly;
    final /* synthetic */ TextFieldState $state;
    final /* synthetic */ TransformedText $transformedText;
    final /* synthetic */ TextFieldValue $value;

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "Landroidx/compose/ui/text/TextLayoutResult;", "invoke", "(Ljava/util/List;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<List<TextLayoutResult>, Boolean> {
        final /* synthetic */ TextFieldState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(TextFieldState textFieldState) {
            super(1);
            this.$state = textFieldState;
        }

        @Override // r7.l
        public final Boolean invoke(List<TextLayoutResult> list) {
            boolean z;
            if (this.$state.getLayoutResult() != null) {
                list.add(this.$state.getLayoutResult().getValue());
                z = true;
            } else {
                z = false;
            }
            return Boolean.valueOf(z);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$10, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass10 extends r implements a<Boolean> {
        final /* synthetic */ TextFieldSelectionManager $manager;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass10(TextFieldSelectionManager textFieldSelectionManager) {
            super(0);
            this.$manager = textFieldSelectionManager;
        }

        @Override // r7.a
        public final Boolean invoke() {
            this.$manager.paste$foundation_release();
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "text", "Landroidx/compose/ui/text/AnnotatedString;", "invoke", "(Landroidx/compose/ui/text/AnnotatedString;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements l<AnnotatedString, Boolean> {
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ TextFieldState $state;
        final /* synthetic */ SemanticsPropertyReceiver $this_semantics;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(boolean z, boolean z5, TextFieldState textFieldState, SemanticsPropertyReceiver semanticsPropertyReceiver) {
            super(1);
            this.$readOnly = z;
            this.$enabled = z5;
            this.$state = textFieldState;
            this.$this_semantics = semanticsPropertyReceiver;
        }

        @Override // r7.l
        public final Boolean invoke(AnnotatedString annotatedString) {
            t0 t0Var;
            if (this.$readOnly || !this.$enabled) {
                return Boolean.FALSE;
            }
            TextInputSession inputSession = this.$state.getInputSession();
            if (inputSession != null) {
                TextFieldState textFieldState = this.$state;
                TextFieldDelegate.INSTANCE.onEditCommand$foundation_release(t7.a.E(new DeleteAllCommand(), new CommitTextCommand(annotatedString, 1)), textFieldState.getProcessor(), textFieldState.getOnValueChange(), inputSession);
                t0Var = t0.f22605a;
            } else {
                t0Var = null;
            }
            if (t0Var == null) {
                this.$state.getOnValueChange().invoke(new TextFieldValue(annotatedString.getText(), TextRangeKt.TextRange(annotatedString.getText().length()), (TextRange) null, 4, (h) null));
            }
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "text", "Landroidx/compose/ui/text/AnnotatedString;", "invoke", "(Landroidx/compose/ui/text/AnnotatedString;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements l<AnnotatedString, Boolean> {
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ TextFieldState $state;
        final /* synthetic */ SemanticsPropertyReceiver $this_semantics;
        final /* synthetic */ TextFieldValue $value;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(boolean z, boolean z5, TextFieldState textFieldState, SemanticsPropertyReceiver semanticsPropertyReceiver, TextFieldValue textFieldValue) {
            super(1);
            this.$readOnly = z;
            this.$enabled = z5;
            this.$state = textFieldState;
            this.$this_semantics = semanticsPropertyReceiver;
            this.$value = textFieldValue;
        }

        @Override // r7.l
        public final Boolean invoke(AnnotatedString annotatedString) {
            t0 t0Var;
            if (this.$readOnly || !this.$enabled) {
                return Boolean.FALSE;
            }
            TextInputSession inputSession = this.$state.getInputSession();
            if (inputSession != null) {
                TextFieldState textFieldState = this.$state;
                TextFieldDelegate.INSTANCE.onEditCommand$foundation_release(t7.a.E(new FinishComposingTextCommand(), new CommitTextCommand(annotatedString, 1)), textFieldState.getProcessor(), textFieldState.getOnValueChange(), inputSession);
                t0Var = t0.f22605a;
            } else {
                t0Var = null;
            }
            if (t0Var == null) {
                TextFieldValue textFieldValue = this.$value;
                TextFieldState textFieldState2 = this.$state;
                String text = textFieldValue.getText();
                int iM5192getStartimpl = TextRange.m5192getStartimpl(textFieldValue.getSelection());
                int iM5187getEndimpl = TextRange.m5187getEndimpl(textFieldValue.getSelection());
                if (iM5187getEndimpl < iM5192getStartimpl) {
                    throw new IndexOutOfBoundsException(c.s("End index (", iM5187getEndimpl, ") is less than start index (", iM5192getStartimpl, ")."));
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append((CharSequence) text, 0, iM5192getStartimpl);
                sb2.append((CharSequence) annotatedString);
                sb2.append((CharSequence) text, iM5187getEndimpl, text.length());
                textFieldState2.getOnValueChange().invoke(new TextFieldValue(sb2.toString(), TextRangeKt.TextRange(annotatedString.length() + TextRange.m5192getStartimpl(textFieldValue.getSelection())), (TextRange) null, 4, (h) null));
            }
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "selectionStart", "", "selectionEnd", "relativeToOriginalText", "invoke", "(IIZ)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements q<Integer, Integer, Boolean, Boolean> {
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ TextFieldSelectionManager $manager;
        final /* synthetic */ OffsetMapping $offsetMapping;
        final /* synthetic */ TextFieldState $state;
        final /* synthetic */ TextFieldValue $value;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(OffsetMapping offsetMapping, boolean z, TextFieldValue textFieldValue, TextFieldSelectionManager textFieldSelectionManager, TextFieldState textFieldState) {
            super(3);
            this.$offsetMapping = offsetMapping;
            this.$enabled = z;
            this.$value = textFieldValue;
            this.$manager = textFieldSelectionManager;
            this.$state = textFieldState;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke(((Number) obj).intValue(), ((Number) obj2).intValue(), ((Boolean) obj3).booleanValue());
        }

        public final Boolean invoke(int i10, int i11, boolean z) {
            if (!z) {
                i10 = this.$offsetMapping.transformedToOriginal(i10);
            }
            if (!z) {
                i11 = this.$offsetMapping.transformedToOriginal(i11);
            }
            boolean z5 = false;
            if (this.$enabled && (i10 != TextRange.m5192getStartimpl(this.$value.getSelection()) || i11 != TextRange.m5187getEndimpl(this.$value.getSelection()))) {
                if (Math.min(i10, i11) < 0 || Math.max(i10, i11) > this.$value.getAnnotatedString().length()) {
                    this.$manager.exitSelectionMode$foundation_release();
                } else {
                    if (z || i10 == i11) {
                        this.$manager.exitSelectionMode$foundation_release();
                    } else {
                        TextFieldSelectionManager.enterSelectionMode$foundation_release$default(this.$manager, false, 1, null);
                    }
                    this.$state.getOnValueChange().invoke(new TextFieldValue(this.$value.getAnnotatedString(), TextRangeKt.TextRange(i10, i11), (TextRange) null, 4, (h) null));
                    z5 = true;
                }
            }
            return Boolean.valueOf(z5);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass5 extends r implements a<Boolean> {
        final /* synthetic */ ImeOptions $imeOptions;
        final /* synthetic */ TextFieldState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(TextFieldState textFieldState, ImeOptions imeOptions) {
            super(0);
            this.$state = textFieldState;
            this.$imeOptions = imeOptions;
        }

        @Override // r7.a
        public final Boolean invoke() {
            this.$state.getOnImeActionPerformed().invoke(ImeAction.m5352boximpl(this.$imeOptions.getImeAction()));
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$6, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass6 extends r implements a<Boolean> {
        final /* synthetic */ FocusRequester $focusRequester;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ TextFieldState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass6(TextFieldState textFieldState, FocusRequester focusRequester, boolean z) {
            super(0);
            this.$state = textFieldState;
            this.$focusRequester = focusRequester;
            this.$readOnly = z;
        }

        @Override // r7.a
        public final Boolean invoke() {
            CoreTextFieldKt.tapToFocus(this.$state, this.$focusRequester, !this.$readOnly);
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$7, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass7 extends r implements a<Boolean> {
        final /* synthetic */ TextFieldSelectionManager $manager;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass7(TextFieldSelectionManager textFieldSelectionManager) {
            super(0);
            this.$manager = textFieldSelectionManager;
        }

        @Override // r7.a
        public final Boolean invoke() {
            TextFieldSelectionManager.enterSelectionMode$foundation_release$default(this.$manager, false, 1, null);
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$8, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass8 extends r implements a<Boolean> {
        final /* synthetic */ TextFieldSelectionManager $manager;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass8(TextFieldSelectionManager textFieldSelectionManager) {
            super(0);
            this.$manager = textFieldSelectionManager;
        }

        @Override // r7.a
        public final Boolean invoke() {
            TextFieldSelectionManager.copy$foundation_release$default(this.$manager, false, 1, null);
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.CoreTextFieldKt$CoreTextField$semanticsModifier$1$9, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass9 extends r implements a<Boolean> {
        final /* synthetic */ TextFieldSelectionManager $manager;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass9(TextFieldSelectionManager textFieldSelectionManager) {
            super(0);
            this.$manager = textFieldSelectionManager;
        }

        @Override // r7.a
        public final Boolean invoke() {
            this.$manager.cut$foundation_release();
            return Boolean.TRUE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoreTextFieldKt$CoreTextField$semanticsModifier$1(TransformedText transformedText, TextFieldValue textFieldValue, boolean z, boolean z5, ImeOptions imeOptions, boolean z10, TextFieldState textFieldState, OffsetMapping offsetMapping, TextFieldSelectionManager textFieldSelectionManager, FocusRequester focusRequester) {
        super(1);
        this.$transformedText = transformedText;
        this.$value = textFieldValue;
        this.$enabled = z;
        this.$isPassword = z5;
        this.$imeOptions = imeOptions;
        this.$readOnly = z10;
        this.$state = textFieldState;
        this.$offsetMapping = offsetMapping;
        this.$manager = textFieldSelectionManager;
        this.$focusRequester = focusRequester;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((SemanticsPropertyReceiver) obj);
        return t0.f22605a;
    }

    public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setEditableText(semanticsPropertyReceiver, this.$transformedText.getText());
        SemanticsPropertiesKt.m5040setTextSelectionRangeFDrldGo(semanticsPropertyReceiver, this.$value.getSelection());
        if (!this.$enabled) {
            SemanticsPropertiesKt.disabled(semanticsPropertyReceiver);
        }
        if (this.$isPassword) {
            SemanticsPropertiesKt.password(semanticsPropertyReceiver);
        }
        SemanticsPropertiesKt.getTextLayoutResult$default(semanticsPropertyReceiver, null, new AnonymousClass1(this.$state), 1, null);
        SemanticsPropertiesKt.setText$default(semanticsPropertyReceiver, null, new AnonymousClass2(this.$readOnly, this.$enabled, this.$state, semanticsPropertyReceiver), 1, null);
        SemanticsPropertiesKt.insertTextAtCursor$default(semanticsPropertyReceiver, null, new AnonymousClass3(this.$readOnly, this.$enabled, this.$state, semanticsPropertyReceiver, this.$value), 1, null);
        SemanticsPropertiesKt.setSelection$default(semanticsPropertyReceiver, null, new AnonymousClass4(this.$offsetMapping, this.$enabled, this.$value, this.$manager, this.$state), 1, null);
        SemanticsPropertiesKt.m5036onImeAction9UiTYpY$default(semanticsPropertyReceiver, this.$imeOptions.getImeAction(), null, new AnonymousClass5(this.$state, this.$imeOptions), 2, null);
        SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new AnonymousClass6(this.$state, this.$focusRequester, this.$readOnly), 1, null);
        SemanticsPropertiesKt.onLongClick$default(semanticsPropertyReceiver, null, new AnonymousClass7(this.$manager), 1, null);
        if (!TextRange.m5186getCollapsedimpl(this.$value.getSelection()) && !this.$isPassword) {
            SemanticsPropertiesKt.copyText$default(semanticsPropertyReceiver, null, new AnonymousClass8(this.$manager), 1, null);
            if (this.$enabled && !this.$readOnly) {
                SemanticsPropertiesKt.cutText$default(semanticsPropertyReceiver, null, new AnonymousClass9(this.$manager), 1, null);
            }
        }
        if (!this.$enabled || this.$readOnly) {
            return;
        }
        SemanticsPropertiesKt.pasteText$default(semanticsPropertyReceiver, null, new AnonymousClass10(this.$manager), 1, null);
    }
}
