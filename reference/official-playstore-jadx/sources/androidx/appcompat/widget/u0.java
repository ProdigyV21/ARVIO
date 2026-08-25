package androidx.appcompat.widget;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ListAdapter;
import androidx.appcompat.app.AlertController$RecycleListView;

/* JADX INFO: loaded from: classes.dex */
public final class u0 implements b1, DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public androidx.appcompat.app.q f1768i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public v0 f1769l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public CharSequence f1770m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ c1 f1771n;

    public u0(c1 c1Var) {
        this.f1771n = c1Var;
    }

    @Override // androidx.appcompat.widget.b1
    public final boolean a() {
        androidx.appcompat.app.q qVar = this.f1768i;
        if (qVar != null) {
            return qVar.isShowing();
        }
        return false;
    }

    @Override // androidx.appcompat.widget.b1
    public final int b() {
        return 0;
    }

    @Override // androidx.appcompat.widget.b1
    public final void d(int i10) {
        Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
    }

    @Override // androidx.appcompat.widget.b1
    public final void dismiss() {
        androidx.appcompat.app.q qVar = this.f1768i;
        if (qVar != null) {
            qVar.dismiss();
            this.f1768i = null;
        }
    }

    @Override // androidx.appcompat.widget.b1
    public final CharSequence e() {
        return this.f1770m;
    }

    @Override // androidx.appcompat.widget.b1
    public final Drawable f() {
        return null;
    }

    @Override // androidx.appcompat.widget.b1
    public final void g(CharSequence charSequence) {
        this.f1770m = charSequence;
    }

    @Override // androidx.appcompat.widget.b1
    public final int getVerticalOffset() {
        return 0;
    }

    @Override // androidx.appcompat.widget.b1
    public final void h(int i10) {
        Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
    }

    @Override // androidx.appcompat.widget.b1
    public final void i(int i10) {
        Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
    }

    @Override // androidx.appcompat.widget.b1
    public final void j(int i10, int i11) {
        if (this.f1769l == null) {
            return;
        }
        c1 c1Var = this.f1771n;
        androidx.appcompat.app.p pVar = new androidx.appcompat.app.p(c1Var.getPopupContext());
        CharSequence charSequence = this.f1770m;
        if (charSequence != null) {
            pVar.setTitle(charSequence);
        }
        v0 v0Var = this.f1769l;
        int selectedItemPosition = c1Var.getSelectedItemPosition();
        androidx.appcompat.app.l lVar = pVar.f1147a;
        lVar.k = v0Var;
        lVar.f1082l = this;
        lVar.f1085o = selectedItemPosition;
        lVar.f1084n = true;
        androidx.appcompat.app.q qVarCreate = pVar.create();
        this.f1768i = qVarCreate;
        AlertController$RecycleListView alertController$RecycleListView = qVarCreate.f1150m.f1126e;
        s0.d(alertController$RecycleListView, i10);
        s0.c(alertController$RecycleListView, i11);
        this.f1768i.show();
    }

    @Override // androidx.appcompat.widget.b1
    public final void k(ListAdapter listAdapter) {
        this.f1769l = (v0) listAdapter;
    }

    @Override // androidx.appcompat.widget.b1
    public final void m(Drawable drawable) {
        Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i10) {
        c1 c1Var = this.f1771n;
        c1Var.setSelection(i10);
        if (c1Var.getOnItemClickListener() != null) {
            c1Var.performItemClick(null, i10, this.f1769l.getItemId(i10));
        }
        dismiss();
    }
}
