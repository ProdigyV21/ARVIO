package androidx.leanback.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes3.dex */
public class BrowseFrameLayout extends FrameLayout {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public b f2936i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public a f2937l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public View.OnKeyListener f2938m;

    public interface a {
        void a();

        boolean b();
    }

    public interface b {
        View a();
    }

    public BrowseFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        boolean zDispatchKeyEvent = super.dispatchKeyEvent(keyEvent);
        View.OnKeyListener onKeyListener = this.f2938m;
        return (onKeyListener == null || zDispatchKeyEvent) ? zDispatchKeyEvent : onKeyListener.onKey(getRootView(), keyEvent.getKeyCode(), keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final View focusSearch(View view, int i10) {
        View viewA;
        b bVar = this.f2936i;
        return (bVar == null || (viewA = bVar.a()) == null) ? super.focusSearch(view, i10) : viewA;
    }

    public a getOnChildFocusListener() {
        return this.f2937l;
    }

    public b getOnFocusSearchListener() {
        return this.f2936i;
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i10, Rect rect) {
        a aVar = this.f2937l;
        if (aVar == null || !aVar.b()) {
            return super.onRequestFocusInDescendants(i10, rect);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        a aVar = this.f2937l;
        if (aVar != null) {
            aVar.a();
        }
        super.requestChildFocus(view, view2);
    }

    public void setOnChildFocusListener(a aVar) {
        this.f2937l = aVar;
    }

    public void setOnDispatchKeyListener(View.OnKeyListener onKeyListener) {
        this.f2938m = onKeyListener;
    }

    public void setOnFocusSearchListener(b bVar) {
        this.f2936i = bVar;
    }
}
