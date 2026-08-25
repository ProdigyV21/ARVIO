package com.arflix.tv.data.repository;

import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import com.arflix.tv.R;
import com.arflix.tv.util.Constants;
import com.google.android.gms.cast.MediaError;
import gb.s0;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.concurrent.CancellationException;
import java.util.regex.Pattern;
import kotlin.Metadata;
import org.json.JSONException;
import org.json.JSONObject;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/d0;", "Lcom/arflix/tv/data/repository/TvDeviceAuthSession;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.TvDeviceAuthRepository$startSession$2", f = "TvDeviceAuthRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class TvDeviceAuthRepository$startSession$2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
    int label;
    final /* synthetic */ TvDeviceAuthRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvDeviceAuthRepository$startSession$2(TvDeviceAuthRepository tvDeviceAuthRepository, d7.d<? super TvDeviceAuthRepository$startSession$2> dVar) {
        super(2, dVar);
        this.this$0 = tvDeviceAuthRepository;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvDeviceAuthRepository$startSession$2(this.this$0, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object c0Var;
        gb.p0 p0VarD;
        TvDeviceAuthRepository tvDeviceAuthRepository;
        String strX;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        try {
            gb.j0 j0Var = new gb.j0();
            Constants constants = Constants.INSTANCE;
            j0Var.i(constants.getTV_AUTH_START_URL());
            j0Var.f15724c.h("apikey", constants.getAPP_ANON_KEY());
            j0Var.f15724c.h("Authorization", "Bearer " + constants.getAPP_ANON_KEY());
            gb.d0 d0VarI0 = this.this$0.jsonMediaType;
            Charset charset = kotlin.text.a.f19924a;
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
            byte[] bytes = "{}".getBytes(charset);
            int length = bytes.length;
            ib.c.c(bytes.length, 0, length);
            j0Var.f(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST, new gb.m0(d0VarI0, length, bytes));
            p0VarD = this.this$0.okHttpClient.a(j0Var.b()).d();
            tvDeviceAuthRepository = this.this$0;
            try {
                s0 s0Var = p0VarD.f15786q;
                strX = s0Var != null ? s0Var.x() : null;
                if (strX == null) {
                    strX = "";
                }
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
        if (!p0VarD.k()) {
            throw new IllegalStateException(tvDeviceAuthRepository.parseError(strX, tvDeviceAuthRepository.context.getString(R.string.tv_link_failed_start)));
        }
        JSONObject jSONObject = new JSONObject(strX);
        String string = jSONObject.getString("user_code");
        String strOptString = jSONObject.optString("verification_url");
        if (kotlin.text.o.h0(strOptString)) {
            strOptString = jSONObject.optString("verification_uri");
        }
        if (kotlin.text.o.h0(strOptString)) {
            strOptString = "https://auth.arvio.tv/?code=" + URLEncoder.encode(string, "UTF-8");
        }
        c0Var = new TvDeviceAuthSession(string, jSONObject.getString("device_code"), strOptString, jSONObject.optInt("expires_in", MediaError.DetailedErrorCode.TEXT_UNKNOWN), jSONObject.optInt("interval", 3));
        p0VarD.close();
        return new x6.d0(c0Var);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
        return ((TvDeviceAuthRepository$startSession$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
