package androidx.leanback.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes3.dex */
class PlaybackControlsRowView extends LinearLayout {

    public interface a {
    }

    public PlaybackControlsRowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i10, Rect rect) {
        View viewFindFocus = findFocus();
        if (viewFindFocus == null || !viewFindFocus.requestFocus(i10, rect)) {
            return super.onRequestFocusInDescendants(i10, rect);
        }
        return true;
    }
}
