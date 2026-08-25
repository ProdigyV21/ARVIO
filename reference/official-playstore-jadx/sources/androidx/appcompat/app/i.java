package androidx.appcompat.app;

import android.os.Message;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class i implements View.OnClickListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ o f1056i;

    public i(o oVar) {
        this.f1056i = oVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Message message;
        Message message2;
        Message message3;
        o oVar = this.f1056i;
        Message messageObtain = (view != oVar.f1129h || (message3 = oVar.f1131j) == null) ? (view != oVar.k || (message2 = oVar.f1133m) == null) ? (view != oVar.f1134n || (message = oVar.f1136p) == null) ? null : Message.obtain(message) : Message.obtain(message2) : Message.obtain(message3);
        if (messageObtain != null) {
            messageObtain.sendToTarget();
        }
        oVar.D.obtainMessage(1, oVar.f1123b).sendToTarget();
    }
}
