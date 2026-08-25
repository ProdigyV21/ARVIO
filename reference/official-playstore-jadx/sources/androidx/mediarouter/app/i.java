package androidx.mediarouter.app;

import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public final class i implements View.OnClickListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ MediaRouteExpandCollapseButton f4295i;

    public i(MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton) {
        this.f4295i = mediaRouteExpandCollapseButton;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        MediaRouteExpandCollapseButton mediaRouteExpandCollapseButton = this.f4295i;
        boolean z = mediaRouteExpandCollapseButton.f4268r;
        mediaRouteExpandCollapseButton.f4268r = !z;
        if (z) {
            mediaRouteExpandCollapseButton.setImageDrawable(mediaRouteExpandCollapseButton.f4265o);
            mediaRouteExpandCollapseButton.f4265o.start();
            mediaRouteExpandCollapseButton.setContentDescription(mediaRouteExpandCollapseButton.f4266p);
        } else {
            mediaRouteExpandCollapseButton.setImageDrawable(mediaRouteExpandCollapseButton.f4264n);
            mediaRouteExpandCollapseButton.f4264n.start();
            mediaRouteExpandCollapseButton.setContentDescription(mediaRouteExpandCollapseButton.f4267q);
        }
        View.OnClickListener onClickListener = mediaRouteExpandCollapseButton.f4269s;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }
}
