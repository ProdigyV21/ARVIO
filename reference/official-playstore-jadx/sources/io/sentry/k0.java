package io.sentry;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class k0 implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f17417a;

    @Override // java.util.concurrent.Callable
    public final Object call() {
        switch (this.f17417a) {
            case 0:
                return InetAddress.getLocalHost();
            case 1:
                return null;
            default:
                return new ArrayList();
        }
    }
}
