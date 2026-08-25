package androidx.compose.ui.text.input;

import io.github.jan.supabase.gotrue.SettingsSessionManager;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import r7.l;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0017\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005JM\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0018\u0010\u000e\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0004\u0012\u00020\r0\n2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\r0\nH\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0012\u001a\u00020\rH\u0007¢\u0006\u0004\b\u0012\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0016\u001a\u00020\rH\u0007¢\u0006\u0004\b\u0016\u0010\u0014J\u000f\u0010\u0018\u001a\u00020\rH\u0007¢\u0006\u0004\b\u0018\u0010\u0014J\u000f\u0010\u0019\u001a\u00020\rH\u0007¢\u0006\u0004\b\u0019\u0010\u0014R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001aR(\u0010\u001d\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u001bj\n\u0012\u0006\u0012\u0004\u0018\u00010\u0011`\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010!\u001a\u0004\u0018\u00010\u00118@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 ¨\u0006\""}, d2 = {"Landroidx/compose/ui/text/input/TextInputService;", "", "Landroidx/compose/ui/text/input/PlatformTextInputService;", "platformTextInputService", "<init>", "(Landroidx/compose/ui/text/input/PlatformTextInputService;)V", "Landroidx/compose/ui/text/input/TextFieldValue;", "value", "Landroidx/compose/ui/text/input/ImeOptions;", "imeOptions", "Lkotlin/Function1;", "", "Landroidx/compose/ui/text/input/EditCommand;", "Lx6/t0;", "onEditCommand", "Landroidx/compose/ui/text/input/ImeAction;", "onImeActionPerformed", "Landroidx/compose/ui/text/input/TextInputSession;", "startInput", "(Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/ui/text/input/ImeOptions;Lr7/l;Lr7/l;)Landroidx/compose/ui/text/input/TextInputSession;", "()V", SettingsSessionManager.SETTINGS_KEY, "stopInput", "(Landroidx/compose/ui/text/input/TextInputSession;)V", "showSoftwareKeyboard", "hideSoftwareKeyboard", "Landroidx/compose/ui/text/input/PlatformTextInputService;", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/ui/text/AtomicReference;", "_currentInputSession", "Ljava/util/concurrent/atomic/AtomicReference;", "getCurrentInputSession$ui_text_release", "()Landroidx/compose/ui/text/input/TextInputSession;", "currentInputSession", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class TextInputService {
    public static final int $stable = 8;
    private final AtomicReference<TextInputSession> _currentInputSession = new AtomicReference<>(null);
    private final PlatformTextInputService platformTextInputService;

    public TextInputService(PlatformTextInputService platformTextInputService) {
        this.platformTextInputService = platformTextInputService;
    }

    public final TextInputSession getCurrentInputSession$ui_text_release() {
        return this._currentInputSession.get();
    }

    @e
    public final void hideSoftwareKeyboard() {
        this.platformTextInputService.hideSoftwareKeyboard();
    }

    @e
    public final void showSoftwareKeyboard() {
        if (getCurrentInputSession$ui_text_release() != null) {
            this.platformTextInputService.showSoftwareKeyboard();
        }
    }

    public TextInputSession startInput(TextFieldValue value, ImeOptions imeOptions, l<? super List<? extends EditCommand>, t0> onEditCommand, l<? super ImeAction, t0> onImeActionPerformed) {
        this.platformTextInputService.startInput(value, imeOptions, onEditCommand, onImeActionPerformed);
        TextInputSession textInputSession = new TextInputSession(this, this.platformTextInputService);
        this._currentInputSession.set(textInputSession);
        return textInputSession;
    }

    public void stopInput(TextInputSession session) {
        AtomicReference<TextInputSession> atomicReference = this._currentInputSession;
        while (!atomicReference.compareAndSet(session, null)) {
            if (atomicReference.get() != session) {
                return;
            }
        }
        this.platformTextInputService.stopInput();
    }

    public final void startInput() {
        this.platformTextInputService.startInput();
    }

    public final void stopInput() {
        this.platformTextInputService.stopInput();
    }
}
