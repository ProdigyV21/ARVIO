package io.sentry;

import java.util.TimerTask;

/* JADX INFO: loaded from: classes4.dex */
public final class f7 extends TimerTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f17284i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ h7 f17285l;

    public /* synthetic */ f7(h7 h7Var, int i10) {
        this.f17284i = i10;
        this.f17285l = h7Var;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        switch (this.f17284i) {
            case 0:
                h7 h7Var = this.f17285l;
                q7 status = h7Var.getStatus();
                if (status == null) {
                    status = q7.OK;
                }
                h7Var.q(status, null);
                h7Var.f17333l.set(false);
                break;
            default:
                h7 h7Var2 = this.f17285l;
                q7 status2 = h7Var2.getStatus();
                if (status2 == null) {
                    status2 = q7.DEADLINE_EXCEEDED;
                }
                h7Var2.c(status2, h7Var2.f17339r.f18042g != null, null);
                h7Var2.f17334m.set(false);
                break;
        }
    }
}
