package androidx.appcompat.widget;

import android.content.Context;
import android.view.View;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes.dex */
public final class g extends androidx.appcompat.view.menu.c0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ m f1572l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(m mVar, Context context, androidx.appcompat.view.menu.i0 i0Var, View view) {
        super(context, i0Var, view, false, R.attr.actionOverflowMenuStyle, 0);
        this.f1572l = mVar;
        if ((i0Var.A.x & 32) != 32) {
            View view2 = mVar.f1659s;
            this.f1269e = view2 == null ? (View) mVar.f1282r : view2;
        }
        l lVar = mVar.G;
        this.f1272h = lVar;
        androidx.appcompat.view.menu.z zVar = this.f1273i;
        if (zVar != null) {
            zVar.c(lVar);
        }
    }

    @Override // androidx.appcompat.view.menu.c0
    public final void c() {
        m mVar = this.f1572l;
        mVar.D = null;
        mVar.getClass();
        super.c();
    }
}
