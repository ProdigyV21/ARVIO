package androidx.compose.runtime;

import androidx.compose.runtime.tooling.CompositionObserver;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0001\u0018\u00002\u00020\u0001B\u001b\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/CompositionObserverHolder;", "", "observer", "Landroidx/compose/runtime/tooling/CompositionObserver;", "root", "", "(Landroidx/compose/runtime/tooling/CompositionObserver;Z)V", "getObserver", "()Landroidx/compose/runtime/tooling/CompositionObserver;", "setObserver", "(Landroidx/compose/runtime/tooling/CompositionObserver;)V", "getRoot", "()Z", "setRoot", "(Z)V", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CompositionObserverHolder {
    public static final int $stable = 8;
    private CompositionObserver observer;
    private boolean root;

    /* JADX WARN: Multi-variable type inference failed */
    public CompositionObserverHolder() {
        this(null, false, 3, 0 == true ? 1 : 0);
    }

    public final CompositionObserver getObserver() {
        return this.observer;
    }

    public final boolean getRoot() {
        return this.root;
    }

    public final void setObserver(CompositionObserver compositionObserver) {
        this.observer = compositionObserver;
    }

    public final void setRoot(boolean z) {
        this.root = z;
    }

    public CompositionObserverHolder(CompositionObserver compositionObserver, boolean z) {
        this.observer = compositionObserver;
        this.root = z;
    }

    public /* synthetic */ CompositionObserverHolder(CompositionObserver compositionObserver, boolean z, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : compositionObserver, (i10 & 2) != 0 ? false : z);
    }
}
