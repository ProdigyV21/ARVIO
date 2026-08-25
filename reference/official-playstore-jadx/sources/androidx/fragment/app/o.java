package androidx.fragment.app;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes3.dex */
public final class o implements DialogInterface.OnCancelListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ r f2803i;

    public o(r rVar) {
        this.f2803i = rVar;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        r rVar = this.f2803i;
        if (rVar.mDialog != null) {
            rVar.onCancel(rVar.mDialog);
        }
    }
}
