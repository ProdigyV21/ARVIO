package androidx.compose.runtime;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/RememberObserverHolder;", "", "wrapped", "Landroidx/compose/runtime/RememberObserver;", TtmlNode.ANNOTATION_POSITION_AFTER, "Landroidx/compose/runtime/Anchor;", "(Landroidx/compose/runtime/RememberObserver;Landroidx/compose/runtime/Anchor;)V", "getAfter", "()Landroidx/compose/runtime/Anchor;", "setAfter", "(Landroidx/compose/runtime/Anchor;)V", "getWrapped", "()Landroidx/compose/runtime/RememberObserver;", "setWrapped", "(Landroidx/compose/runtime/RememberObserver;)V", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RememberObserverHolder {
    public static final int $stable = 8;
    private Anchor after;
    private RememberObserver wrapped;

    public RememberObserverHolder(RememberObserver rememberObserver, Anchor anchor) {
        this.wrapped = rememberObserver;
        this.after = anchor;
    }

    public final Anchor getAfter() {
        return this.after;
    }

    public final RememberObserver getWrapped() {
        return this.wrapped;
    }

    public final void setAfter(Anchor anchor) {
        this.after = anchor;
    }

    public final void setWrapped(RememberObserver rememberObserver) {
        this.wrapped = rememberObserver;
    }
}
