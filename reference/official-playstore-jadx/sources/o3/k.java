package o3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.r;
import com.google.android.gms.common.internal.t;

/* JADX INFO: loaded from: classes4.dex */
public class k extends r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Dialog f20751i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public DialogInterface.OnCancelListener f20752l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public AlertDialog f20753m;

    @Override // androidx.fragment.app.r, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.f20752l;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    @Override // androidx.fragment.app.r
    public final Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = this.f20751i;
        if (dialog != null) {
            return dialog;
        }
        setShowsDialog(false);
        if (this.f20753m == null) {
            Context context = getContext();
            t.i(context);
            this.f20753m = new AlertDialog.Builder(context).create();
        }
        return this.f20753m;
    }
}
