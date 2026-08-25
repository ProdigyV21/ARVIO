package androidx.appcompat.view.menu;

import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public final class v extends FrameLayout implements n.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final CollapsibleActionView f1393i;

    /* JADX WARN: Multi-variable type inference failed */
    public v(View view) {
        super(view.getContext());
        this.f1393i = (CollapsibleActionView) view;
        addView(view);
    }

    @Override // n.c
    public final void onActionViewCollapsed() {
        this.f1393i.onActionViewCollapsed();
    }

    @Override // n.c
    public final void onActionViewExpanded() {
        this.f1393i.onActionViewExpanded();
    }
}
