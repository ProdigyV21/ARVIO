package o3;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import com.google.android.gms.common.internal.t;

/* JADX INFO: loaded from: classes4.dex */
public class c extends DialogFragment {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Dialog f20733i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public DialogInterface.OnCancelListener f20734l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public AlertDialog f20735m;

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.f20734l;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    @Override // android.app.DialogFragment
    public final Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = this.f20733i;
        if (dialog != null) {
            return dialog;
        }
        setShowsDialog(false);
        if (this.f20735m == null) {
            Activity activity = getActivity();
            t.i(activity);
            this.f20735m = new AlertDialog.Builder(activity).create();
        }
        return this.f20735m;
    }
}
