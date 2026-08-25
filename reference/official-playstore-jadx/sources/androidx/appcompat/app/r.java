package androidx.appcompat.app;

import android.os.Bundle;
import com.google.android.gms.cast.framework.media.widget.ExpandedControllerActivity;

/* JADX INFO: loaded from: classes.dex */
public final class r implements b2.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ExpandedControllerActivity f1166a;

    public r(ExpandedControllerActivity expandedControllerActivity) {
        this.f1166a = expandedControllerActivity;
    }

    @Override // b2.d
    public final Bundle a() {
        Bundle bundle = new Bundle();
        this.f1166a.getDelegate().getClass();
        return bundle;
    }
}
