package androidx.credentials.playservices;

import r7.l;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements com.google.android.gms.tasks.e {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2358i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ l f2359l;

    public /* synthetic */ a(l lVar, int i10) {
        this.f2358i = i10;
        this.f2359l = lVar;
    }

    @Override // com.google.android.gms.tasks.e
    public final void onSuccess(Object obj) {
        switch (this.f2358i) {
            case 0:
                this.f2359l.invoke(obj);
                break;
            case 1:
                this.f2359l.invoke(obj);
                break;
            case 2:
                this.f2359l.invoke(obj);
                break;
            case 3:
                this.f2359l.invoke(obj);
                break;
            default:
                this.f2359l.invoke(obj);
                break;
        }
    }
}
