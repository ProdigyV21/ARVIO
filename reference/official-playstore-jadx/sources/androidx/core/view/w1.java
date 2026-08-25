package androidx.core.view;

import android.view.ContentInfo;
import android.view.OnReceiveContentListener;
import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public final class w1 implements OnReceiveContentListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s0 f2328a;

    public w1(s0 s0Var) {
        this.f2328a = s0Var;
    }

    public final ContentInfo onReceiveContent(View view, ContentInfo contentInfo) {
        n nVar = new n(new h(contentInfo));
        n nVarA = this.f2328a.a(view, nVar);
        if (nVarA == null) {
            return null;
        }
        return nVarA == nVar ? contentInfo : nVarA.d();
    }
}
