package androidx.appcompat.widget;

import android.content.Context;
import android.view.View;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes.dex */
public final class k extends androidx.appcompat.view.menu.c0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ m f1620l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(m mVar, Context context, androidx.appcompat.view.menu.q qVar, View view) {
        super(context, qVar, view, true, R.attr.actionOverflowMenuStyle, 0);
        this.f1620l = mVar;
        this.f1270f = 8388613;
        l lVar = mVar.G;
        this.f1272h = lVar;
        androidx.appcompat.view.menu.z zVar = this.f1273i;
        if (zVar != null) {
            zVar.c(lVar);
        }
    }

    @Override // androidx.appcompat.view.menu.c0
    public final void c() {
        m mVar = this.f1620l;
        androidx.appcompat.view.menu.q qVar = mVar.f1277m;
        if (qVar != null) {
            qVar.c(true);
        }
        mVar.C = null;
        super.c();
    }
}
