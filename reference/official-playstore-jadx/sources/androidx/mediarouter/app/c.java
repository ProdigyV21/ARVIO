package androidx.mediarouter.app;

import android.app.Dialog;
import android.view.View;
import io.sentry.android.core.s1;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class c implements View.OnClickListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4273i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Dialog f4274l;

    public /* synthetic */ c(Dialog dialog, int i10) {
        this.f4273i = i10;
        this.f4274l = dialog;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.f4273i) {
            case 0:
                ((g) this.f4274l).dismiss();
                break;
            default:
                ((s1) this.f4274l).cancel();
                break;
        }
    }
}
