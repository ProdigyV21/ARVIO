package androidx.work.impl;

import java.util.ArrayList;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class f implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6855a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f6856b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f6857c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f6858d;

    public /* synthetic */ f(Object obj, Object obj2, Object obj3, int i10) {
        this.f6855a = i10;
        this.f6856b = obj;
        this.f6857c = obj2;
        this.f6858d = obj3;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        switch (this.f6855a) {
            case 0:
                g gVar = (g) this.f6856b;
                ArrayList arrayList = (ArrayList) this.f6857c;
                String str = (String) this.f6858d;
                WorkDatabase workDatabase = gVar.f6883e;
                arrayList.addAll(workDatabase.u().f(str));
                return workDatabase.t().k(str);
            default:
                return ((com.google.firebase.concurrent.f) this.f6856b).f14332i.submit(new androidx.activity.s((Callable) this.f6857c, (com.google.firebase.concurrent.g) this.f6858d, 21));
        }
    }
}
