package androidx.compose.ui.text.input;

import android.view.Choreographer;
import android.view.inputmethod.EditorInfo;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardCapitalization;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.emoji2.text.o;
import androidx.media3.extractor.ts.TsExtractor;
import java.util.concurrent.Executor;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0013\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0002¢\u0006\u0004\b\u0002\u0010\u0003\u001a#\u0010\b\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u0013\u0010\f\u001a\u00020\u000b*\u00020\nH\u0000¢\u0006\u0004\b\f\u0010\r\u001a\u001f\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0012\u0010\u0013\"\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroid/view/inputmethod/EditorInfo;", "Lx6/t0;", "updateWithEmojiCompat", "(Landroid/view/inputmethod/EditorInfo;)V", "Landroidx/compose/ui/text/input/ImeOptions;", "imeOptions", "Landroidx/compose/ui/text/input/TextFieldValue;", "textFieldValue", "update", "(Landroid/view/inputmethod/EditorInfo;Landroidx/compose/ui/text/input/ImeOptions;Landroidx/compose/ui/text/input/TextFieldValue;)V", "Landroid/view/Choreographer;", "Ljava/util/concurrent/Executor;", "asExecutor", "(Landroid/view/Choreographer;)Ljava/util/concurrent/Executor;", "", "bits", "flag", "", "hasFlag", "(II)Z", "", "DEBUG_CLASS", "Ljava/lang/String;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextInputServiceAndroid_androidKt {
    private static final String DEBUG_CLASS = "TextInputServiceAndroid";

    public static final Executor asExecutor(Choreographer choreographer) {
        return new c(choreographer, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void asExecutor$lambda$2(Choreographer choreographer, Runnable runnable) {
        choreographer.postFrameCallback(new b(runnable));
    }

    private static final boolean hasFlag(int i10, int i11) {
        return (i10 & i11) == i11;
    }

    public static final void update(EditorInfo editorInfo, ImeOptions imeOptions, TextFieldValue textFieldValue) {
        String privateImeOptions;
        int iM5380getImeActioneUduSuo = imeOptions.getImeAction();
        ImeAction.Companion companion = ImeAction.INSTANCE;
        int i10 = 6;
        if (ImeAction.m5355equalsimpl0(iM5380getImeActioneUduSuo, companion.m5367getDefaulteUduSuo())) {
            if (!imeOptions.getSingleLine()) {
                i10 = 0;
            }
        } else if (ImeAction.m5355equalsimpl0(iM5380getImeActioneUduSuo, companion.m5371getNoneeUduSuo())) {
            i10 = 1;
        } else if (ImeAction.m5355equalsimpl0(iM5380getImeActioneUduSuo, companion.m5369getGoeUduSuo())) {
            i10 = 2;
        } else if (ImeAction.m5355equalsimpl0(iM5380getImeActioneUduSuo, companion.m5370getNexteUduSuo())) {
            i10 = 5;
        } else if (ImeAction.m5355equalsimpl0(iM5380getImeActioneUduSuo, companion.m5372getPreviouseUduSuo())) {
            i10 = 7;
        } else if (ImeAction.m5355equalsimpl0(iM5380getImeActioneUduSuo, companion.m5373getSearcheUduSuo())) {
            i10 = 3;
        } else if (ImeAction.m5355equalsimpl0(iM5380getImeActioneUduSuo, companion.m5374getSendeUduSuo())) {
            i10 = 4;
        } else if (!ImeAction.m5355equalsimpl0(iM5380getImeActioneUduSuo, companion.m5368getDoneeUduSuo())) {
            throw new IllegalStateException("invalid ImeAction");
        }
        editorInfo.imeOptions = i10;
        PlatformImeOptions platformImeOptions = imeOptions.getPlatformImeOptions();
        if (platformImeOptions != null && (privateImeOptions = platformImeOptions.getPrivateImeOptions()) != null) {
            editorInfo.privateImeOptions = privateImeOptions;
        }
        int iM5381getKeyboardTypePjHm6EE = imeOptions.getKeyboardType();
        KeyboardType.Companion companion2 = KeyboardType.INSTANCE;
        if (KeyboardType.m5402equalsimpl0(iM5381getKeyboardTypePjHm6EE, companion2.m5422getTextPjHm6EE())) {
            editorInfo.inputType = 1;
        } else if (KeyboardType.m5402equalsimpl0(iM5381getKeyboardTypePjHm6EE, companion2.m5415getAsciiPjHm6EE())) {
            editorInfo.inputType = 1;
            editorInfo.imeOptions |= Integer.MIN_VALUE;
        } else if (KeyboardType.m5402equalsimpl0(iM5381getKeyboardTypePjHm6EE, companion2.m5418getNumberPjHm6EE())) {
            editorInfo.inputType = 2;
        } else if (KeyboardType.m5402equalsimpl0(iM5381getKeyboardTypePjHm6EE, companion2.m5421getPhonePjHm6EE())) {
            editorInfo.inputType = 3;
        } else if (KeyboardType.m5402equalsimpl0(iM5381getKeyboardTypePjHm6EE, companion2.m5423getUriPjHm6EE())) {
            editorInfo.inputType = 17;
        } else if (KeyboardType.m5402equalsimpl0(iM5381getKeyboardTypePjHm6EE, companion2.m5417getEmailPjHm6EE())) {
            editorInfo.inputType = 33;
        } else if (KeyboardType.m5402equalsimpl0(iM5381getKeyboardTypePjHm6EE, companion2.m5420getPasswordPjHm6EE())) {
            editorInfo.inputType = TsExtractor.TS_STREAM_TYPE_AC3;
        } else if (KeyboardType.m5402equalsimpl0(iM5381getKeyboardTypePjHm6EE, companion2.m5419getNumberPasswordPjHm6EE())) {
            editorInfo.inputType = 18;
        } else {
            if (!KeyboardType.m5402equalsimpl0(iM5381getKeyboardTypePjHm6EE, companion2.m5416getDecimalPjHm6EE())) {
                throw new IllegalStateException("Invalid Keyboard Type");
            }
            editorInfo.inputType = 8194;
        }
        if (!imeOptions.getSingleLine() && hasFlag(editorInfo.inputType, 1)) {
            editorInfo.inputType |= 131072;
            if (ImeAction.m5355equalsimpl0(imeOptions.getImeAction(), companion.m5367getDefaulteUduSuo())) {
                editorInfo.imeOptions |= 1073741824;
            }
        }
        if (hasFlag(editorInfo.inputType, 1)) {
            int iM5379getCapitalizationIUNYP9k = imeOptions.getCapitalization();
            KeyboardCapitalization.Companion companion3 = KeyboardCapitalization.INSTANCE;
            if (KeyboardCapitalization.m5387equalsimpl0(iM5379getCapitalizationIUNYP9k, companion3.m5395getCharactersIUNYP9k())) {
                editorInfo.inputType |= 4096;
            } else if (KeyboardCapitalization.m5387equalsimpl0(iM5379getCapitalizationIUNYP9k, companion3.m5398getWordsIUNYP9k())) {
                editorInfo.inputType |= 8192;
            } else if (KeyboardCapitalization.m5387equalsimpl0(iM5379getCapitalizationIUNYP9k, companion3.m5397getSentencesIUNYP9k())) {
                editorInfo.inputType |= 16384;
            }
            if (imeOptions.getAutoCorrect()) {
                editorInfo.inputType |= 32768;
            }
        }
        editorInfo.initialSelStart = TextRange.m5192getStartimpl(textFieldValue.getSelection());
        editorInfo.initialSelEnd = TextRange.m5187getEndimpl(textFieldValue.getSelection());
        androidx.core.view.inputmethod.b.c(editorInfo, textFieldValue.getText());
        editorInfo.imeOptions |= 33554432;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateWithEmojiCompat(EditorInfo editorInfo) {
        if (o.c()) {
            o.a().h(editorInfo);
        }
    }
}
