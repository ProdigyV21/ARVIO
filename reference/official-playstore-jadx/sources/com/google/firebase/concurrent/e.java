package com.google.firebase.concurrent;

import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class e implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14328i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ f f14329l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Runnable f14330m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ g f14331n;

    public /* synthetic */ e(f fVar, Runnable runnable, g gVar, int i10) {
        this.f14328i = i10;
        this.f14329l = fVar;
        this.f14330m = runnable;
        this.f14331n = gVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f14328i) {
            case 0:
                ExecutorService executorService = this.f14329l.f14332i;
                final int i10 = 0;
                final Runnable runnable = this.f14330m;
                final g gVar = this.f14331n;
                executorService.execute(new Runnable() { // from class: com.google.firebase.concurrent.b
                    @Override // java.lang.Runnable
                    public final void run() throws Exception {
                        switch (i10) {
                            case 0:
                                try {
                                    runnable.run();
                                    return;
                                } catch (Exception e5) {
                                    gVar.b(e5);
                                    throw e5;
                                }
                            case 1:
                                try {
                                    runnable.run();
                                    return;
                                } catch (Exception e6) {
                                    gVar.b(e6);
                                    return;
                                }
                            default:
                                Runnable runnable2 = runnable;
                                g gVar2 = gVar;
                                try {
                                    runnable2.run();
                                    gVar2.a(null);
                                    return;
                                } catch (Exception e10) {
                                    gVar2.b(e10);
                                    return;
                                }
                        }
                    }
                });
                break;
            case 1:
                ExecutorService executorService2 = this.f14329l.f14332i;
                final int i11 = 2;
                final Runnable runnable2 = this.f14330m;
                final g gVar2 = this.f14331n;
                executorService2.execute(new Runnable() { // from class: com.google.firebase.concurrent.b
                    @Override // java.lang.Runnable
                    public final void run() throws Exception {
                        switch (i11) {
                            case 0:
                                try {
                                    runnable2.run();
                                    return;
                                } catch (Exception e5) {
                                    gVar2.b(e5);
                                    throw e5;
                                }
                            case 1:
                                try {
                                    runnable2.run();
                                    return;
                                } catch (Exception e6) {
                                    gVar2.b(e6);
                                    return;
                                }
                            default:
                                Runnable runnable22 = runnable2;
                                g gVar22 = gVar2;
                                try {
                                    runnable22.run();
                                    gVar22.a(null);
                                    return;
                                } catch (Exception e10) {
                                    gVar22.b(e10);
                                    return;
                                }
                        }
                    }
                });
                break;
            default:
                ExecutorService executorService3 = this.f14329l.f14332i;
                final int i12 = 1;
                final Runnable runnable3 = this.f14330m;
                final g gVar3 = this.f14331n;
                executorService3.execute(new Runnable() { // from class: com.google.firebase.concurrent.b
                    @Override // java.lang.Runnable
                    public final void run() throws Exception {
                        switch (i12) {
                            case 0:
                                try {
                                    runnable3.run();
                                    return;
                                } catch (Exception e5) {
                                    gVar3.b(e5);
                                    throw e5;
                                }
                            case 1:
                                try {
                                    runnable3.run();
                                    return;
                                } catch (Exception e6) {
                                    gVar3.b(e6);
                                    return;
                                }
                            default:
                                Runnable runnable22 = runnable3;
                                g gVar22 = gVar3;
                                try {
                                    runnable22.run();
                                    gVar22.a(null);
                                    return;
                                } catch (Exception e10) {
                                    gVar22.b(e10);
                                    return;
                                }
                        }
                    }
                });
                break;
        }
    }
}
