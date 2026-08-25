package androidx.fragment.app;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes3.dex */
public final class p implements DialogInterface.OnDismissListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ r f2819i;

    public p(r rVar) {
        this.f2819i = rVar;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        r rVar = this.f2819i;
        if (rVar.mDialog != null) {
            rVar.onDismiss(rVar.mDialog);
        }
    }
}
