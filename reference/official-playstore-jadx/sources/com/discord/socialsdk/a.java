package com.discord.socialsdk;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f12555i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f12556l;

    public /* synthetic */ a(boolean z, int i10) {
        this.f12555i = i10;
        this.f12556l = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f12555i) {
            case 0:
                NativeCalls.setPreventCommsForBluetoothOnMainThread(this.f12556l);
                break;
            default:
                NativeCalls.setActiveCallOnMainThread(this.f12556l);
                break;
        }
    }
}
