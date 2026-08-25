package com.arflix.tv.ui.screens.details.discord;

import d7.d;
import f7.e;
import f7.j;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@e(c = "com.arflix.tv.ui.screens.details.discord.DiscordRpcManager$jniCallback$1$onStatusChanged$1", f = "DiscordRpcManager.kt", l = {117}, m = "invokeSuspend", v = 2)
public final class DiscordRpcManager$jniCallback$1$onStatusChanged$1 extends j implements p<k0, d<? super t0>, Object> {
    int label;

    public DiscordRpcManager$jniCallback$1$onStatusChanged$1(d<? super DiscordRpcManager$jniCallback$1$onStatusChanged$1> dVar) {
        super(2, dVar);
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new DiscordRpcManager$jniCallback$1$onStatusChanged$1(dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            DiscordRpcManager discordRpcManager = DiscordRpcManager.INSTANCE;
            this.label = 1;
            obj = discordRpcManager.refreshCurrentToken(this);
            e7.a aVar = e7.a.f15033i;
            if (obj == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
        }
        if (obj == null) {
            DiscordRpcManager.INSTANCE.logout();
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((DiscordRpcManager$jniCallback$1$onStatusChanged$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
