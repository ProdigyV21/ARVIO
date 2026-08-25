package io.github.jan.supabase.gotrue.mfa;

import ab.g;
import bb.c;
import cb.b1;
import cb.l1;
import d7.d;
import db.b;
import db.b0;
import db.m;
import io.github.jan.supabase.UtilsKt;
import io.github.jan.supabase.annotations.SupabaseInternal;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import q7.n;
import x6.e;
import ya.q;
import ya.r;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u000eB\u0011\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\t\u001a\u00028\u00002\u0006\u0010\b\u001a\u00020\u0007H§@¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u000b\u001a\u0004\b\f\u0010\r\u0082\u0001\u0001\u000f¨\u0006\u0010"}, d2 = {"Lio/github/jan/supabase/gotrue/mfa/FactorType;", "T", "", "", "value", "<init>", "(Ljava/lang/String;)V", "Ldb/b0;", "json", "decodeResponse", "(Ldb/b0;Ld7/d;)Ljava/lang/Object;", "Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "TOTP", "Lio/github/jan/supabase/gotrue/mfa/FactorType$TOTP;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public abstract class FactorType<T> {
    private final String value;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0014B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0096@¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fHÖ\u0003¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u0015"}, d2 = {"Lio/github/jan/supabase/gotrue/mfa/FactorType$TOTP;", "Lio/github/jan/supabase/gotrue/mfa/FactorType;", "Lio/github/jan/supabase/gotrue/mfa/FactorType$TOTP$Response;", "<init>", "()V", "Ldb/b0;", "json", "decodeResponse", "(Ldb/b0;Ld7/d;)Ljava/lang/Object;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Response", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class TOTP extends FactorType<Response> {
        public static final TOTP INSTANCE = new TOTP();

        @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0087\b\u0018\u0000 )2\u00020\u0001:\u0002*)B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007B;\b\u0011\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0006\u0010\fJ(\u0010\u0015\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010HÁ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0017J\u0010\u0010\u0019\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0017J.\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001c\u0010\u0017J\u0010\u0010\u001d\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u001a\u0010!\u001a\u00020 2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b!\u0010\"R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010#\u001a\u0004\b$\u0010\u0017R \u0010\u0004\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0004\u0010#\u0012\u0004\b&\u0010'\u001a\u0004\b%\u0010\u0017R\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010#\u001a\u0004\b(\u0010\u0017¨\u0006+"}, d2 = {"Lio/github/jan/supabase/gotrue/mfa/FactorType$TOTP$Response;", "", "", "secret", "qrCode", "uri", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "", "seen1", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcb/l1;)V", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self$gotrue_kt_release", "(Lio/github/jan/supabase/gotrue/mfa/FactorType$TOTP$Response;Lbb/c;Lab/g;)V", "write$Self", "component1", "()Ljava/lang/String;", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lio/github/jan/supabase/gotrue/mfa/FactorType$TOTP$Response;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getSecret", "getQrCode", "getQrCode$annotations", "()V", "getUri", "Companion", "$serializer", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        @r
        public static final /* data */ class Response {

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            public static final Companion INSTANCE = new Companion(null);
            private final String qrCode;
            private final String secret;
            private final String uri;

            @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/gotrue/mfa/FactorType$TOTP$Response$Companion;", "", "<init>", "()V", "Lya/h;", "Lio/github/jan/supabase/gotrue/mfa/FactorType$TOTP$Response;", "serializer", "()Lya/h;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(h hVar) {
                    this();
                }

                public final ya.h<Response> serializer() {
                    return FactorType$TOTP$Response$$serializer.INSTANCE;
                }

                private Companion() {
                }
            }

            @e
            public /* synthetic */ Response(int i10, String str, @q("qr_code") String str2, String str3, l1 l1Var) {
                if (7 != (i10 & 7)) {
                    b1.h(i10, 7, FactorType$TOTP$Response$$serializer.INSTANCE.getDescriptor());
                    throw null;
                }
                this.secret = str;
                this.qrCode = str2;
                this.uri = str3;
            }

            public static /* synthetic */ Response copy$default(Response response, String str, String str2, String str3, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = response.secret;
                }
                if ((i10 & 2) != 0) {
                    str2 = response.qrCode;
                }
                if ((i10 & 4) != 0) {
                    str3 = response.uri;
                }
                return response.copy(str, str2, str3);
            }

            @q("qr_code")
            public static /* synthetic */ void getQrCode$annotations() {
            }

            @n
            public static final /* synthetic */ void write$Self$gotrue_kt_release(Response self, c output, g serialDesc) {
                output.x(serialDesc, 0, self.secret);
                output.x(serialDesc, 1, self.qrCode);
                output.x(serialDesc, 2, self.uri);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getSecret() {
                return this.secret;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getQrCode() {
                return this.qrCode;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final String getUri() {
                return this.uri;
            }

            public final Response copy(String secret, String qrCode, String uri) {
                return new Response(secret, qrCode, uri);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Response)) {
                    return false;
                }
                Response response = (Response) other;
                return p.a(this.secret, response.secret) && p.a(this.qrCode, response.qrCode) && p.a(this.uri, response.uri);
            }

            public final String getQrCode() {
                return this.qrCode;
            }

            public final String getSecret() {
                return this.secret;
            }

            public final String getUri() {
                return this.uri;
            }

            public int hashCode() {
                return this.uri.hashCode() + androidx.compose.foundation.c.c(this.secret.hashCode() * 31, 31, this.qrCode);
            }

            public String toString() {
                StringBuilder sb2 = new StringBuilder("Response(secret=");
                sb2.append(this.secret);
                sb2.append(", qrCode=");
                sb2.append(this.qrCode);
                sb2.append(", uri=");
                return androidx.compose.foundation.c.u(sb2, this.uri, ')');
            }

            public Response(String str, String str2, String str3) {
                this.secret = str;
                this.qrCode = str2;
                this.uri = str3;
            }
        }

        private TOTP() {
            super("totp", null);
        }

        @Override // io.github.jan.supabase.gotrue.mfa.FactorType
        public Object decodeResponse(b0 b0Var, d<? super Response> dVar) {
            b supabaseJson = UtilsKt.getSupabaseJson();
            m mVar = (m) b0Var.get("totp");
            if (mVar == null) {
                throw new IllegalStateException("No 'totp' object found in factor response");
            }
            b0 b0VarF = db.n.f(mVar);
            supabaseJson.getClass();
            return supabaseJson.f(Response.INSTANCE.serializer(), b0VarF);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof TOTP);
        }

        public int hashCode() {
            return -640774738;
        }

        public String toString() {
            return "TOTP";
        }
    }

    public /* synthetic */ FactorType(String str, h hVar) {
        this(str);
    }

    @SupabaseInternal
    public abstract Object decodeResponse(b0 b0Var, d<? super T> dVar);

    public final String getValue() {
        return this.value;
    }

    private FactorType(String str) {
        this.value = str;
    }
}
