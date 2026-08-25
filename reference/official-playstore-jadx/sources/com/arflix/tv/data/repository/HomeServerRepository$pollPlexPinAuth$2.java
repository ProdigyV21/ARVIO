package com.arflix.tv.data.repository;

import com.arflix.tv.R;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import gb.s0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/d0;", "", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$pollPlexPinAuth$2", f = "HomeServerRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class HomeServerRepository$pollPlexPinAuth$2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
    final /* synthetic */ String $pinId;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ HomeServerRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeServerRepository$pollPlexPinAuth$2(String str, HomeServerRepository homeServerRepository, d7.d<? super HomeServerRepository$pollPlexPinAuth$2> dVar) {
        super(2, dVar);
        this.$pinId = str;
        this.this$0 = homeServerRepository;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        HomeServerRepository$pollPlexPinAuth$2 homeServerRepository$pollPlexPinAuth$2 = new HomeServerRepository$pollPlexPinAuth$2(this.$pinId, this.this$0, dVar);
        homeServerRepository$pollPlexPinAuth$2.L$0 = obj;
        return homeServerRepository$pollPlexPinAuth$2;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object c0Var;
        gb.b0 b0VarD;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        String str = this.$pinId;
        HomeServerRepository homeServerRepository = this.this$0;
        try {
            String str2 = "https://plex.tv/api/v2/pins/" + str;
            try {
                androidx.tv.foundation.lazy.list.k kVar = new androidx.tv.foundation.lazy.list.k(1);
                kVar.i(null, str2);
                b0VarD = kVar.d();
            } catch (IllegalArgumentException unused) {
                b0VarD = null;
            }
            if (b0VarD == null) {
                throw new IllegalStateException(homeServerRepository.context.getString(R.string.homeserver_invalid_code_url).toString());
            }
            androidx.tv.foundation.lazy.list.k kVarF = b0VarD.f();
            kVarF.c("X-Plex-Client-Identifier", homeServerRepository.deviceId());
            String str3 = kVarF.d().f15625i;
            gb.j0 j0Var = new gb.j0();
            j0Var.i(str3);
            j0Var.d();
            j0Var.f15724c = homeServerRepository.plexPublicHeaders().c();
            gb.p0 p0VarD = homeServerRepository.okHttpClient.a(j0Var.b()).d();
            try {
                s0 s0Var = p0VarD.f15786q;
                String strX = s0Var != null ? s0Var.x() : null;
                if (strX == null) {
                    strX = "";
                }
                if (!p0VarD.k()) {
                    throw new IllegalStateException(homeServerRepository.context.getString(R.string.homeserver_code_poll_failed, new Integer(p0VarD.f15783n)).toString());
                }
                JsonObject jsonObjectAsJsonObjectOrNull = homeServerRepository.asJsonObjectOrNull(new JsonParser().parse(strX));
                if (jsonObjectAsJsonObjectOrNull == null) {
                    jsonObjectAsJsonObjectOrNull = new JsonObject();
                }
                String strString = homeServerRepository.string(jsonObjectAsJsonObjectOrNull, "authToken");
                if (kotlin.text.o.h0(strString)) {
                    strString = homeServerRepository.string(jsonObjectAsJsonObjectOrNull, "auth_token");
                }
                c0Var = kotlin.text.o.h0(strString) ? null : strString;
                p0VarD.close();
            } finally {
            }
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        return new x6.d0(c0Var);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
        return ((HomeServerRepository$pollPlexPinAuth$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
