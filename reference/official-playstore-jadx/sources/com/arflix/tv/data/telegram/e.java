package com.arflix.tv.data.telegram;

import io.ktor.server.application.Application;
import io.ktor.server.routing.Routing;
import r7.l;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class e implements l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7725i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ TelegramStreamingProxy f7726l;

    public /* synthetic */ e(TelegramStreamingProxy telegramStreamingProxy, int i10) {
        this.f7725i = i10;
        this.f7726l = telegramStreamingProxy;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7725i) {
            case 0:
                return TelegramStreamingProxy.start$lambda$0(this.f7726l, (Application) obj);
            default:
                return TelegramStreamingProxy.start$lambda$0$0(this.f7726l, (Routing) obj);
        }
    }
}
