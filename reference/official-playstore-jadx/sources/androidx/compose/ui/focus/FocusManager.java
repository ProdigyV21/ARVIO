package androidx.compose.ui.focus;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\u000b\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H&ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nø\u0001\u0001\u0082\u0002\r\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0003"}, d2 = {"Landroidx/compose/ui/focus/FocusManager;", "", "", "force", "Lx6/t0;", "clearFocus", "(Z)V", "Landroidx/compose/ui/focus/FocusDirection;", "focusDirection", "moveFocus-3ESFkO8", "(I)Z", "moveFocus", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface FocusManager {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    void clearFocus(boolean force);

    /* JADX INFO: renamed from: moveFocus-3ESFkO8, reason: not valid java name */
    boolean mo3168moveFocus3ESFkO8(int focusDirection);
}
