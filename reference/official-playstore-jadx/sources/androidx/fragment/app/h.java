package androidx.fragment.app;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class h implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2732i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f2733l;

    public /* synthetic */ h(Object obj, int i10) {
        this.f2732i = i10;
        this.f2733l = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f2732i) {
            case 0:
                i iVar = (i) this.f2733l;
                iVar.f2735b.endViewTransition(iVar.f2736c);
                iVar.f2737d.a();
                break;
            case 1:
                p1.a(4, (ArrayList) this.f2733l);
                break;
            case 2:
                r rVar = (r) this.f2733l;
                rVar.mOnDismissListener.onDismiss(rVar.mDialog);
                break;
            case 3:
                ((n) this.f2733l).d();
                break;
            default:
                ((b1) this.f2733l).x(true);
                break;
        }
    }
}
