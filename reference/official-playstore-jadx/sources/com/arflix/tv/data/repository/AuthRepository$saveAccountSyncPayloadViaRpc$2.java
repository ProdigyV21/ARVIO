package com.arflix.tv.data.repository;

import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import com.arflix.tv.util.Constants;
import gb.s0;
import io.github.jan.supabase.gotrue.user.UserSession;
import java.nio.charset.Charset;
import java.util.regex.Pattern;
import kotlin.Metadata;
import org.json.JSONException;
import org.json.JSONObject;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.AuthRepository$saveAccountSyncPayloadViaRpc$2", f = "AuthRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class AuthRepository$saveAccountSyncPayloadViaRpc$2 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ String $payload;
    final /* synthetic */ UserSession $session;
    int label;
    final /* synthetic */ AuthRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AuthRepository$saveAccountSyncPayloadViaRpc$2(String str, AuthRepository authRepository, UserSession userSession, d7.d<? super AuthRepository$saveAccountSyncPayloadViaRpc$2> dVar) {
        super(2, dVar);
        this.$payload = str;
        this.this$0 = authRepository;
        this.$session = userSession;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new AuthRepository$saveAccountSyncPayloadViaRpc$2(this.$payload, this.this$0, this.$session, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        String string = new JSONObject().put("p_payload", this.$payload).toString();
        gb.d0 d0VarI0 = this.this$0.jsonMediaType;
        Charset charset = kotlin.text.a.f19924a;
        JSONObject jSONObject = null;
        if (d0VarI0 != null) {
            Pattern pattern = gb.d0.f15633d;
            Charset charsetA = d0VarI0.a(null);
            if (charsetA == null) {
                try {
                    d0VarI0 = xc.d.i0(d0VarI0 + "; charset=utf-8");
                } catch (IllegalArgumentException unused) {
                    d0VarI0 = null;
                }
            } else {
                charset = charsetA;
            }
        }
        byte[] bytes = string.getBytes(charset);
        int length = bytes.length;
        ib.c.c(bytes.length, 0, length);
        gb.m0 m0Var = new gb.m0(d0VarI0, length, bytes);
        gb.j0 j0Var = new gb.j0();
        Constants constants = Constants.INSTANCE;
        j0Var.i(constants.getSUPABASE_URL() + "/rest/v1/rpc/save_account_sync_payload");
        j0Var.f15724c.h("apikey", constants.getSUPABASE_ANON_KEY());
        j0Var.f15724c.h("Authorization", androidx.compose.material3.d.C("Bearer ", this.$session.getAccessToken()));
        j0Var.f15724c.h("Cache-Control", "no-cache, no-store");
        j0Var.f(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST, m0Var);
        gb.p0 p0VarD = this.this$0.okHttpClient.a(j0Var.b()).d();
        try {
            s0 s0Var = p0VarD.f15786q;
            String strX = s0Var != null ? s0Var.x() : null;
            if (strX == null) {
                strX = "";
            }
            if (!p0VarD.k()) {
                throw new IllegalStateException("Cloud sync upload failed (" + p0VarD.f15783n + "): " + AuthRepositoryKt.safePostgrestError(strX));
            }
            try {
                jSONObject = new JSONObject(strX);
            } catch (JSONException unused2) {
            }
            if (jSONObject != null && !jSONObject.optBoolean("accepted", true)) {
                throw new AccountSyncPayloadRejectedException("Cloud sync upload rejected: " + jSONObject.optString("reason", "existing_snapshot_is_richer"));
            }
            p0VarD.close();
            return t0.f22605a;
        } finally {
        }
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((AuthRepository$saveAccountSyncPayloadViaRpc$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
