package androidx.appcompat.app;

import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: loaded from: classes.dex */
public final class j implements AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ o f1062i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ l f1063l;

    public j(l lVar, o oVar) {
        this.f1063l = lVar;
        this.f1062i = oVar;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i10, long j10) {
        l lVar = this.f1063l;
        DialogInterface.OnClickListener onClickListener = lVar.f1082l;
        o oVar = this.f1062i;
        onClickListener.onClick(oVar.f1123b, i10);
        if (lVar.f1084n) {
            return;
        }
        oVar.f1123b.dismiss();
    }
}
