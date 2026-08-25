package io.github.jan.supabase.gotrue.providers.builtin;

import ab.g;
import androidx.media3.extractor.text.ttml.TtmlNode;
import bb.c;
import cb.b1;
import cb.l1;
import cb.r1;
import d7.d;
import db.b;
import db.b0;
import io.github.jan.supabase.SupabaseClient;
import io.github.jan.supabase.UtilsKt;
import io.github.jan.supabase.exceptions.SupabaseEncodingException;
import io.github.jan.supabase.gotrue.providers.IDTokenProvider;
import io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider;
import io.github.jan.supabase.gotrue.user.UserSession;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlinx.serialization.MissingFieldException;
import q7.n;
import r7.l;
import x6.e;
import x6.t0;
import xa.a;
import ya.q;
import ya.r;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\bÆ\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u001e\u001fB\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\b\u0010\tJ(\u0010\u000e\u001a\u00020\u00062\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\fH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0019\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016HÖ\u0003¢\u0006\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u00108\u0016X\u0096D¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u0012¨\u0006 "}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/IDToken;", "Lio/github/jan/supabase/gotrue/providers/builtin/DefaultAuthProvider;", "Lio/github/jan/supabase/gotrue/providers/builtin/IDToken$Config;", "Lio/github/jan/supabase/gotrue/providers/builtin/IDToken$Result;", "<init>", "()V", "Ldb/b0;", "json", "decodeResult", "(Ldb/b0;)Lio/github/jan/supabase/gotrue/providers/builtin/IDToken$Result;", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "credentials", "encodeCredentials", "(Lr7/l;)Ldb/b0;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "grantType", "Ljava/lang/String;", "getGrantType", "Config", "Result", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class IDToken implements DefaultAuthProvider<Config, Result> {
    public static final IDToken INSTANCE = new IDToken();
    private static final String grantType = "id_token";

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\b\u0087\b\u0018\u0000 :2\u00020\u0001:\u0002;:B5\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\tB]\b\u0011\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\n\b\u0001\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\b\u0010\u0011J(\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0015HÁ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u001cJ\u0012\u0010 \u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b \u0010\u001cJ>\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b#\u0010\u001cJ\u0010\u0010$\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b$\u0010%J\u001a\u0010)\u001a\u00020(2\b\u0010'\u001a\u0004\u0018\u00010&HÖ\u0003¢\u0006\u0004\b)\u0010*R(\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b\u0003\u0010+\u0012\u0004\b/\u00100\u001a\u0004\b,\u0010\u001c\"\u0004\b-\u0010.R$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u00101\u001a\u0004\b2\u0010\u001e\"\u0004\b3\u00104R*\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b\u0006\u0010+\u0012\u0004\b7\u00100\u001a\u0004\b5\u0010\u001c\"\u0004\b6\u0010.R$\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010+\u001a\u0004\b8\u0010\u001c\"\u0004\b9\u0010.¨\u0006<"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/IDToken$Config;", "Lio/github/jan/supabase/gotrue/providers/builtin/DefaultAuthProvider$Config;", "", "idToken", "Lio/github/jan/supabase/gotrue/providers/IDTokenProvider;", "provider", "accessToken", "nonce", "<init>", "(Ljava/lang/String;Lio/github/jan/supabase/gotrue/providers/IDTokenProvider;Ljava/lang/String;Ljava/lang/String;)V", "", "seen1", "captchaToken", "Ldb/b0;", "data", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;Ldb/b0;Ljava/lang/String;Lio/github/jan/supabase/gotrue/providers/IDTokenProvider;Ljava/lang/String;Ljava/lang/String;Lcb/l1;)V", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self$gotrue_kt_release", "(Lio/github/jan/supabase/gotrue/providers/builtin/IDToken$Config;Lbb/c;Lab/g;)V", "write$Self", "component1", "()Ljava/lang/String;", "component2", "()Lio/github/jan/supabase/gotrue/providers/IDTokenProvider;", "component3", "component4", "copy", "(Ljava/lang/String;Lio/github/jan/supabase/gotrue/providers/IDTokenProvider;Ljava/lang/String;Ljava/lang/String;)Lio/github/jan/supabase/gotrue/providers/builtin/IDToken$Config;", "toString", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getIdToken", "setIdToken", "(Ljava/lang/String;)V", "getIdToken$annotations", "()V", "Lio/github/jan/supabase/gotrue/providers/IDTokenProvider;", "getProvider", "setProvider", "(Lio/github/jan/supabase/gotrue/providers/IDTokenProvider;)V", "getAccessToken", "setAccessToken", "getAccessToken$annotations", "getNonce", "setNonce", "Companion", "$serializer", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @r
    public static final /* data */ class Config extends DefaultAuthProvider.Config {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private String accessToken;
        private String idToken;
        private String nonce;
        private IDTokenProvider provider;

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/IDToken$Config$Companion;", "", "<init>", "()V", "Lya/h;", "Lio/github/jan/supabase/gotrue/providers/builtin/IDToken$Config;", "serializer", "()Lya/h;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(h hVar) {
                this();
            }

            public final ya.h<Config> serializer() {
                return IDToken$Config$$serializer.INSTANCE;
            }

            private Companion() {
            }
        }

        public Config() {
            this(null, null, null, null, 15, null);
        }

        public static /* synthetic */ Config copy$default(Config config, String str, IDTokenProvider iDTokenProvider, String str2, String str3, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = config.idToken;
            }
            if ((i10 & 2) != 0) {
                iDTokenProvider = config.provider;
            }
            if ((i10 & 4) != 0) {
                str2 = config.accessToken;
            }
            if ((i10 & 8) != 0) {
                str3 = config.nonce;
            }
            return config.copy(str, iDTokenProvider, str2, str3);
        }

        @q("access_token")
        public static /* synthetic */ void getAccessToken$annotations() {
        }

        @q("id_token")
        public static /* synthetic */ void getIdToken$annotations() {
        }

        @n
        public static final /* synthetic */ void write$Self$gotrue_kt_release(Config self, c output, g serialDesc) {
            DefaultAuthProvider.Config.write$Self(self, output, serialDesc);
            if (output.C() || !p.a(self.idToken, "")) {
                output.x(serialDesc, 2, self.idToken);
            }
            if (output.C() || self.provider != null) {
                output.m(serialDesc, 3, IDTokenProvider.INSTANCE, self.provider);
            }
            if (output.C() || self.accessToken != null) {
                output.m(serialDesc, 4, r1.f7553a, self.accessToken);
            }
            if (!output.C() && self.nonce == null) {
                return;
            }
            output.m(serialDesc, 5, r1.f7553a, self.nonce);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getIdToken() {
            return this.idToken;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final IDTokenProvider getProvider() {
            return this.provider;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getAccessToken() {
            return this.accessToken;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getNonce() {
            return this.nonce;
        }

        public final Config copy(String idToken, IDTokenProvider provider, String accessToken, String nonce) {
            return new Config(idToken, provider, accessToken, nonce);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Config)) {
                return false;
            }
            Config config = (Config) other;
            return p.a(this.idToken, config.idToken) && p.a(this.provider, config.provider) && p.a(this.accessToken, config.accessToken) && p.a(this.nonce, config.nonce);
        }

        public final String getAccessToken() {
            return this.accessToken;
        }

        public final String getIdToken() {
            return this.idToken;
        }

        public final String getNonce() {
            return this.nonce;
        }

        public final IDTokenProvider getProvider() {
            return this.provider;
        }

        public int hashCode() {
            int iHashCode = this.idToken.hashCode() * 31;
            IDTokenProvider iDTokenProvider = this.provider;
            int iHashCode2 = (iHashCode + (iDTokenProvider == null ? 0 : iDTokenProvider.hashCode())) * 31;
            String str = this.accessToken;
            int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.nonce;
            return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
        }

        public final void setAccessToken(String str) {
            this.accessToken = str;
        }

        public final void setIdToken(String str) {
            this.idToken = str;
        }

        public final void setNonce(String str) {
            this.nonce = str;
        }

        public final void setProvider(IDTokenProvider iDTokenProvider) {
            this.provider = iDTokenProvider;
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("Config(idToken=");
            sb2.append(this.idToken);
            sb2.append(", provider=");
            sb2.append(this.provider);
            sb2.append(", accessToken=");
            sb2.append(this.accessToken);
            sb2.append(", nonce=");
            return androidx.compose.foundation.c.u(sb2, this.nonce, ')');
        }

        @e
        public /* synthetic */ Config(int i10, @r(with = CaptchaTokenSerializer.class) @q("gotrue_meta_security") String str, b0 b0Var, @q("id_token") String str2, IDTokenProvider iDTokenProvider, @q("access_token") String str3, String str4, l1 l1Var) {
            super(i10, str, b0Var, l1Var);
            if ((i10 & 4) == 0) {
                this.idToken = "";
            } else {
                this.idToken = str2;
            }
            if ((i10 & 8) == 0) {
                this.provider = null;
            } else {
                this.provider = iDTokenProvider;
            }
            if ((i10 & 16) == 0) {
                this.accessToken = null;
            } else {
                this.accessToken = str3;
            }
            if ((i10 & 32) == 0) {
                this.nonce = null;
            } else {
                this.nonce = str4;
            }
        }

        public /* synthetic */ Config(String str, IDTokenProvider iDTokenProvider, String str2, String str3, int i10, h hVar) {
            this((i10 & 1) != 0 ? "" : str, (i10 & 2) != 0 ? null : iDTokenProvider, (i10 & 4) != 0 ? null : str2, (i10 & 8) != 0 ? null : str3);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Config(String str, IDTokenProvider iDTokenProvider, String str2, String str3) {
            super((String) null, (b0) (0 == true ? 1 : 0), 3, (h) (0 == true ? 1 : 0));
            this.idToken = str;
            this.provider = iDTokenProvider;
            this.accessToken = str2;
            this.nonce = str3;
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u000f\b\u0087\b\u0018\u0000 12\u00020\u0001:\u000221B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\tBI\b\u0011\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\b\u0010\u000eJ(\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012HÁ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001bJ\u0010\u0010\u001d\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001bJ8\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b \u0010\u0019J\u0010\u0010!\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b!\u0010\"J\u001a\u0010%\u001a\u00020$2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b%\u0010&R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010'\u001a\u0004\b(\u0010\u0019R \u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0005\u0010)\u0012\u0004\b+\u0010,\u001a\u0004\b*\u0010\u001bR \u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0006\u0010)\u0012\u0004\b.\u0010,\u001a\u0004\b-\u0010\u001bR \u0010\u0007\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0007\u0010)\u0012\u0004\b0\u0010,\u001a\u0004\b/\u0010\u001b¨\u00063"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/IDToken$Result;", "", "", TtmlNode.ATTR_ID, "Lwa/c;", "confirmationSentAt", "createdAt", "updatedAt", "<init>", "(Ljava/lang/String;Lwa/c;Lwa/c;Lwa/c;)V", "", "seen1", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;Lwa/c;Lwa/c;Lwa/c;Lcb/l1;)V", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self$gotrue_kt_release", "(Lio/github/jan/supabase/gotrue/providers/builtin/IDToken$Result;Lbb/c;Lab/g;)V", "write$Self", "component1", "()Ljava/lang/String;", "component2", "()Lwa/c;", "component3", "component4", "copy", "(Ljava/lang/String;Lwa/c;Lwa/c;Lwa/c;)Lio/github/jan/supabase/gotrue/providers/builtin/IDToken$Result;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "Lwa/c;", "getConfirmationSentAt", "getConfirmationSentAt$annotations", "()V", "getCreatedAt", "getCreatedAt$annotations", "getUpdatedAt", "getUpdatedAt$annotations", "Companion", "$serializer", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    @r
    public static final /* data */ class Result {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final wa.c confirmationSentAt;
        private final wa.c createdAt;
        private final String id;
        private final wa.c updatedAt;

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/builtin/IDToken$Result$Companion;", "", "<init>", "()V", "Lya/h;", "Lio/github/jan/supabase/gotrue/providers/builtin/IDToken$Result;", "serializer", "()Lya/h;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(h hVar) {
                this();
            }

            public final ya.h<Result> serializer() {
                return IDToken$Result$$serializer.INSTANCE;
            }

            private Companion() {
            }
        }

        @e
        public /* synthetic */ Result(int i10, String str, @q("confirmation_sent_at") wa.c cVar, @q("created_at") wa.c cVar2, @q("updated_at") wa.c cVar3, l1 l1Var) {
            if (15 != (i10 & 15)) {
                b1.h(i10, 15, IDToken$Result$$serializer.INSTANCE.getDescriptor());
                throw null;
            }
            this.id = str;
            this.confirmationSentAt = cVar;
            this.createdAt = cVar2;
            this.updatedAt = cVar3;
        }

        public static /* synthetic */ Result copy$default(Result result, String str, wa.c cVar, wa.c cVar2, wa.c cVar3, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = result.id;
            }
            if ((i10 & 2) != 0) {
                cVar = result.confirmationSentAt;
            }
            if ((i10 & 4) != 0) {
                cVar2 = result.createdAt;
            }
            if ((i10 & 8) != 0) {
                cVar3 = result.updatedAt;
            }
            return result.copy(str, cVar, cVar2, cVar3);
        }

        @q("confirmation_sent_at")
        public static /* synthetic */ void getConfirmationSentAt$annotations() {
        }

        @q("created_at")
        public static /* synthetic */ void getCreatedAt$annotations() {
        }

        @q("updated_at")
        public static /* synthetic */ void getUpdatedAt$annotations() {
        }

        @n
        public static final /* synthetic */ void write$Self$gotrue_kt_release(Result self, c output, g serialDesc) {
            output.x(serialDesc, 0, self.id);
            a aVar = a.f22712a;
            output.j(serialDesc, 1, aVar, self.confirmationSentAt);
            output.j(serialDesc, 2, aVar, self.createdAt);
            output.j(serialDesc, 3, aVar, self.updatedAt);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final wa.c getConfirmationSentAt() {
            return this.confirmationSentAt;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final wa.c getCreatedAt() {
            return this.createdAt;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final wa.c getUpdatedAt() {
            return this.updatedAt;
        }

        public final Result copy(String id, wa.c confirmationSentAt, wa.c createdAt, wa.c updatedAt) {
            return new Result(id, confirmationSentAt, createdAt, updatedAt);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return p.a(this.id, result.id) && p.a(this.confirmationSentAt, result.confirmationSentAt) && p.a(this.createdAt, result.createdAt) && p.a(this.updatedAt, result.updatedAt);
        }

        public final wa.c getConfirmationSentAt() {
            return this.confirmationSentAt;
        }

        public final wa.c getCreatedAt() {
            return this.createdAt;
        }

        public final String getId() {
            return this.id;
        }

        public final wa.c getUpdatedAt() {
            return this.updatedAt;
        }

        public int hashCode() {
            return this.updatedAt.f22539i.hashCode() + ((this.createdAt.f22539i.hashCode() + ((this.confirmationSentAt.f22539i.hashCode() + (this.id.hashCode() * 31)) * 31)) * 31);
        }

        public String toString() {
            return "Result(id=" + this.id + ", confirmationSentAt=" + this.confirmationSentAt + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ')';
        }

        public Result(String str, wa.c cVar, wa.c cVar2, wa.c cVar3) {
            this.id = str;
            this.confirmationSentAt = cVar;
            this.createdAt = cVar2;
            this.updatedAt = cVar3;
        }
    }

    private IDToken() {
    }

    @Override // io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider
    public b0 encodeCredentials(l<? super Config, t0> credentials) {
        b supabaseJson = UtilsKt.getSupabaseJson();
        Config config = new Config(null, null, null, null, 15, null);
        credentials.invoke(config);
        supabaseJson.getClass();
        return db.n.f(supabaseJson.g(Config.INSTANCE.serializer(), config));
    }

    public boolean equals(Object other) {
        return this == other || (other instanceof IDToken);
    }

    @Override // io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider
    public String getGrantType() {
        return grantType;
    }

    public int hashCode() {
        return 135843199;
    }

    @Override // io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider, io.github.jan.supabase.gotrue.providers.AuthProvider
    public Object login(SupabaseClient supabaseClient, r7.p<? super UserSession, ? super d<? super t0>, ? extends Object> pVar, String str, l<? super Config, t0> lVar, d<? super t0> dVar) {
        return DefaultAuthProvider.DefaultImpls.login(this, supabaseClient, pVar, str, lVar, dVar);
    }

    @Override // io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider, io.github.jan.supabase.gotrue.providers.AuthProvider
    public Object signUp(SupabaseClient supabaseClient, r7.p<? super UserSession, ? super d<? super t0>, ? extends Object> pVar, String str, l<? super Config, t0> lVar, d<? super Result> dVar) {
        return DefaultAuthProvider.DefaultImpls.signUp(this, supabaseClient, pVar, str, lVar, dVar);
    }

    public String toString() {
        return "IDToken";
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.github.jan.supabase.gotrue.providers.builtin.DefaultAuthProvider
    public Result decodeResult(b0 json) throws SupabaseEncodingException {
        try {
            b supabaseJson = UtilsKt.getSupabaseJson();
            supabaseJson.getClass();
            return (Result) supabaseJson.f(Result.INSTANCE.serializer(), json);
        } catch (MissingFieldException unused) {
            throw new SupabaseEncodingException("Couldn't decode sign up id token result. Input: " + json);
        }
    }
}
