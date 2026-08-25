package androidx.appcompat.app;

import android.content.Context;
import com.google.android.gms.cast.framework.media.widget.ExpandedControllerActivity;

/* JADX INFO: loaded from: classes.dex */
public final class s implements f.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ExpandedControllerActivity f1168a;

    public s(ExpandedControllerActivity expandedControllerActivity) {
        this.f1168a = expandedControllerActivity;
    }

    @Override // f.c
    public final void onContextAvailable(Context context) {
        ExpandedControllerActivity expandedControllerActivity = this.f1168a;
        z delegate = expandedControllerActivity.getDelegate();
        delegate.a();
        expandedControllerActivity.getSavedStateRegistry().a("androidx:appcompat");
        delegate.e();
    }
}
