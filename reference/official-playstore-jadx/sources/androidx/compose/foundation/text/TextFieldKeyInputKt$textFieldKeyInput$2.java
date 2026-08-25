package androidx.compose.foundation.text;

import androidx.compose.foundation.text.selection.TextFieldSelectionManager;
import androidx.compose.foundation.text.selection.TextPreparedSelectionState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import kotlin.Metadata;
import kotlin.jvm.internal.m;
import kotlin.jvm.internal.r;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class TextFieldKeyInputKt$textFieldKeyInput$2 extends r implements q<Modifier, Composer, Integer, Modifier> {
    final /* synthetic */ boolean $editable;
    final /* synthetic */ int $imeAction;
    final /* synthetic */ TextFieldSelectionManager $manager;
    final /* synthetic */ OffsetMapping $offsetMapping;
    final /* synthetic */ l<TextFieldValue, t0> $onValueChange;
    final /* synthetic */ boolean $singleLine;
    final /* synthetic */ TextFieldState $state;
    final /* synthetic */ UndoManager $undoManager;
    final /* synthetic */ TextFieldValue $value;

    /* JADX INFO: renamed from: androidx.compose.foundation.text.TextFieldKeyInputKt$textFieldKeyInput$2$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class AnonymousClass1 extends m implements l<KeyEvent, Boolean> {
        public AnonymousClass1(Object obj) {
            super(1, 0, TextFieldKeyInput.class, obj, "process", "process-ZmokQxo(Landroid/view/KeyEvent;)Z");
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return m892invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
        }

        /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
        public final Boolean m892invokeZmokQxo(android.view.KeyEvent keyEvent) {
            return Boolean.valueOf(((TextFieldKeyInput) this.receiver).m889processZmokQxo(keyEvent));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TextFieldKeyInputKt$textFieldKeyInput$2(TextFieldState textFieldState, TextFieldSelectionManager textFieldSelectionManager, TextFieldValue textFieldValue, boolean z, boolean z5, OffsetMapping offsetMapping, UndoManager undoManager, l<? super TextFieldValue, t0> lVar, int i10) {
        super(3);
        this.$state = textFieldState;
        this.$manager = textFieldSelectionManager;
        this.$value = textFieldValue;
        this.$editable = z;
        this.$singleLine = z5;
        this.$offsetMapping = offsetMapping;
        this.$undoManager = undoManager;
        this.$onValueChange = lVar;
        this.$imeAction = i10;
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke((Modifier) obj, (Composer) obj2, ((Number) obj3).intValue());
    }

    public final Modifier invoke(Modifier modifier, Composer composer, int i10) {
        composer.startReplaceableGroup(2057323757);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2057323757, i10, -1, "androidx.compose.foundation.text.textFieldKeyInput.<anonymous> (TextFieldKeyInput.kt:245)");
        }
        composer.startReplaceableGroup(-492369756);
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = new TextPreparedSelectionState();
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        TextPreparedSelectionState textPreparedSelectionState = (TextPreparedSelectionState) objRememberedValue;
        composer.startReplaceableGroup(-492369756);
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new DeadKeyCombiner();
            composer.updateRememberedValue(objRememberedValue2);
        }
        composer.endReplaceableGroup();
        Modifier modifierOnKeyEvent = KeyInputModifierKt.onKeyEvent(Modifier.INSTANCE, new AnonymousClass1(new TextFieldKeyInput(this.$state, this.$manager, this.$value, this.$editable, this.$singleLine, textPreparedSelectionState, this.$offsetMapping, this.$undoManager, (DeadKeyCombiner) objRememberedValue2, null, this.$onValueChange, this.$imeAction, 512, null)));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return modifierOnKeyEvent;
    }
}
