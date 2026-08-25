package com.arflix.tv.data.repository;

import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import com.arflix.tv.R;
import com.arflix.tv.util.Constants;
import gb.s0;
import java.io.IOException;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import org.json.JSONException;
import org.json.JSONObject;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/d0;", "Lcom/arflix/tv/data/repository/TvDeviceAuthStatus;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.TvDeviceAuthRepository$pollStatus$2", f = "TvDeviceAuthRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class TvDeviceAuthRepository$pollStatus$2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
    final /* synthetic */ String $deviceCode;
    int label;
    final /* synthetic */ TvDeviceAuthRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvDeviceAuthRepository$pollStatus$2(String str, TvDeviceAuthRepository tvDeviceAuthRepository, d7.d<? super TvDeviceAuthRepository$pollStatus$2> dVar) {
        super(2, dVar);
        this.$deviceCode = str;
        this.this$0 = tvDeviceAuthRepository;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvDeviceAuthRepository$pollStatus$2(this.$deviceCode, this.this$0, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object c0Var;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        try {
            String string = new JSONObject().put("device_code", this.$deviceCode).toString();
            gb.j0 j0Var = new gb.j0();
            Constants constants = Constants.INSTANCE;
            j0Var.i(constants.getTV_AUTH_STATUS_URL());
            j0Var.f15724c.h("apikey", constants.getAPP_ANON_KEY());
            j0Var.f15724c.h("Authorization", "Bearer " + constants.getAPP_ANON_KEY());
            j0Var.f(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST, gb.u.d(string, this.this$0.jsonMediaType));
            gb.p0 p0VarD = this.this$0.okHttpClient.a(j0Var.b()).d();
            TvDeviceAuthRepository tvDeviceAuthRepository = this.this$0;
            try {
                s0 s0Var = p0VarD.f15786q;
                String strX = s0Var != null ? s0Var.x() : null;
                String str = "";
                if (strX == null) {
                    strX = "";
                }
                if (p0VarD.f15783n == 404) {
                    gb.j0 j0Var2 = new gb.j0();
                    j0Var2.i(constants.getTV_AUTH_POLL_URL());
                    j0Var2.f15724c.h("apikey", constants.getAPP_ANON_KEY());
                    j0Var2.f15724c.h("Authorization", "Bearer " + constants.getAPP_ANON_KEY());
                    j0Var2.f(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST, gb.u.d(string, tvDeviceAuthRepository.jsonMediaType));
                    p0VarD = tvDeviceAuthRepository.okHttpClient.a(j0Var2.b()).d();
                    try {
                        s0 s0Var2 = p0VarD.f15786q;
                        String strX2 = s0Var2 != null ? s0Var2.x() : null;
                        if (strX2 != null) {
                            str = strX2;
                        }
                        if (!p0VarD.k()) {
                            throw new IllegalStateException(tvDeviceAuthRepository.parseError(str, tvDeviceAuthRepository.context.getString(R.string.tv_link_failed_poll)));
                        }
                        c0Var = tvDeviceAuthRepository.parseStatus(str);
                        p0VarD.close();
                    } finally {
                    }
                } else {
                    if (!p0VarD.k()) {
                        throw new IllegalStateException(tvDeviceAuthRepository.parseError(strX, tvDeviceAuthRepository.context.getString(R.string.tv_link_failed_poll)));
                    }
                    c0Var = tvDeviceAuthRepository.parseStatus(strX);
                }
                p0VarD.close();
            } finally {
            }
        } catch (IOException e5) {
            c0Var = new x6.c0(e5);
        } catch (CancellationException e6) {
            throw e6;
        } catch (JSONException e10) {
            c0Var = new x6.c0(e10);
        } catch (Exception e11) {
            c0Var = new x6.c0(e11);
        }
        return new x6.d0(c0Var);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
        return ((TvDeviceAuthRepository$pollStatus$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
