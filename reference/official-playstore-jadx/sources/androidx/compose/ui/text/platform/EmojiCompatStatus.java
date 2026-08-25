package androidx.compose.ui.text.platform;

import androidx.compose.runtime.State;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\b\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001H\u0001¢\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\t\u001a\u00020\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\nR\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/text/platform/EmojiCompatStatus;", "Landroidx/compose/ui/text/platform/EmojiCompatStatusDelegate;", "<init>", "()V", "newDelegate", "Lx6/t0;", "setDelegateForTesting$ui_text_release", "(Landroidx/compose/ui/text/platform/EmojiCompatStatusDelegate;)V", "setDelegateForTesting", "delegate", "Landroidx/compose/ui/text/platform/EmojiCompatStatusDelegate;", "Landroidx/compose/runtime/State;", "", "getFontLoaded", "()Landroidx/compose/runtime/State;", "fontLoaded", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class EmojiCompatStatus implements EmojiCompatStatusDelegate {
    public static final EmojiCompatStatus INSTANCE = new EmojiCompatStatus();
    private static EmojiCompatStatusDelegate delegate = new DefaultImpl();
    public static final int $stable = 8;

    private EmojiCompatStatus() {
    }

    @Override // androidx.compose.ui.text.platform.EmojiCompatStatusDelegate
    public State<Boolean> getFontLoaded() {
        return delegate.getFontLoaded();
    }

    public final void setDelegateForTesting$ui_text_release(EmojiCompatStatusDelegate newDelegate) {
        if (newDelegate == null) {
            newDelegate = new DefaultImpl();
        }
        delegate = newDelegate;
    }
}
