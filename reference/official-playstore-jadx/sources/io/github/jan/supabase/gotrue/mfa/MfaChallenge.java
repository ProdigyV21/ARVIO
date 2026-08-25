package io.github.jan.supabase.gotrue.mfa;

import ab.g;
import androidx.media3.extractor.text.ttml.TtmlNode;
import bb.c;
import cb.b1;
import cb.l1;
import j$.time.DateTimeException;
import j$.time.Instant;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import q7.n;
import wa.b;
import x6.e;
import ya.q;
import ya.r;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0087\b\u0018\u0000 *2\u00020\u0001:\u0002+*B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B/\b\u0011\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0001\u0010\t\u001a\u00020\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0004\u0010\fJ(\u0010\u0015\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010HÁ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001a\u0010\u0017J\u0010\u0010\u001b\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u001a\u0010\u001f\u001a\u00020\u001e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001f\u0010 R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010!\u001a\u0004\b\"\u0010\u0017R\u001a\u0010\t\u001a\u00020\b8\u0002X\u0083D¢\u0006\f\n\u0004\b\t\u0010#\u0012\u0004\b$\u0010%R\u0011\u0010)\u001a\u00020&8F¢\u0006\u0006\u001a\u0004\b'\u0010(¨\u0006,"}, d2 = {"Lio/github/jan/supabase/gotrue/mfa/MfaChallenge;", "", "", TtmlNode.ATTR_ID, "<init>", "(Ljava/lang/String;)V", "", "seen1", "", "expiresAtSeconds", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;JLcb/l1;)V", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self$gotrue_kt_release", "(Lio/github/jan/supabase/gotrue/mfa/MfaChallenge;Lbb/c;Lab/g;)V", "write$Self", "component1", "()Ljava/lang/String;", "copy", "(Ljava/lang/String;)Lio/github/jan/supabase/gotrue/mfa/MfaChallenge;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "J", "getExpiresAtSeconds$annotations", "()V", "Lwa/c;", "getExpiresAt", "()Lwa/c;", "expiresAt", "Companion", "$serializer", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@r
public final /* data */ class MfaChallenge {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final long expiresAtSeconds;
    private final String id;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/gotrue/mfa/MfaChallenge$Companion;", "", "<init>", "()V", "Lya/h;", "Lio/github/jan/supabase/gotrue/mfa/MfaChallenge;", "serializer", "()Lya/h;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final ya.h<MfaChallenge> serializer() {
            return MfaChallenge$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    @e
    public /* synthetic */ MfaChallenge(int i10, String str, @q("expires_at") long j10, l1 l1Var) {
        if (1 != (i10 & 1)) {
            b1.h(i10, 1, MfaChallenge$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.id = str;
        if ((i10 & 2) == 0) {
            this.expiresAtSeconds = 0L;
        } else {
            this.expiresAtSeconds = j10;
        }
    }

    public static /* synthetic */ MfaChallenge copy$default(MfaChallenge mfaChallenge, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = mfaChallenge.id;
        }
        return mfaChallenge.copy(str);
    }

    @q("expires_at")
    private static /* synthetic */ void getExpiresAtSeconds$annotations() {
    }

    @n
    public static final /* synthetic */ void write$Self$gotrue_kt_release(MfaChallenge self, c output, g serialDesc) {
        output.x(serialDesc, 0, self.id);
        if (!output.C() && self.expiresAtSeconds == 0) {
            return;
        }
        output.g(serialDesc, 1, self.expiresAtSeconds);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final MfaChallenge copy(String id) {
        return new MfaChallenge(id);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof MfaChallenge) && p.a(this.id, ((MfaChallenge) other).id);
    }

    public final wa.c getExpiresAt() throws Exception {
        b bVar = wa.c.Companion;
        long j10 = this.expiresAtSeconds;
        bVar.getClass();
        try {
            return new wa.c(Instant.ofEpochSecond(j10, 0L));
        } catch (Exception e5) {
            if ((e5 instanceof ArithmeticException) || (e5 instanceof DateTimeException)) {
                return j10 > 0 ? wa.c.f22538m : wa.c.f22537l;
            }
            throw e5;
        }
    }

    public final String getId() {
        return this.id;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public String toString() {
        return androidx.compose.foundation.c.u(new StringBuilder("MfaChallenge(id="), this.id, ')');
    }

    public MfaChallenge(String str) {
        this.id = str;
    }
}
