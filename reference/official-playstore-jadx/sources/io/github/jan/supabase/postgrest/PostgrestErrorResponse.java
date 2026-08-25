package io.github.jan.supabase.postgrest;

import ab.g;
import bb.c;
import cb.b1;
import cb.l1;
import cb.r1;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import q7.n;
import x6.e;
import ya.r;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\n\b\u0081\b\u0018\u0000 *2\u00020\u0001:\u0002+*B3\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0007\u0010\bBC\b\u0011\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0007\u0010\rJ(\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0011HÁ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0017\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0018J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u0018J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u0018J>\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001e\u0010\u0018J\u0010\u0010\u001f\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\u001f\u0010 J\u001a\u0010#\u001a\u00020\"2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b#\u0010$R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010%\u001a\u0004\b&\u0010\u0018R\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010%\u001a\u0004\b'\u0010\u0018R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010%\u001a\u0004\b(\u0010\u0018R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010%\u001a\u0004\b)\u0010\u0018¨\u0006,"}, d2 = {"Lio/github/jan/supabase/postgrest/PostgrestErrorResponse;", "", "", "message", "hint", "details", "code", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "", "seen1", "Lcb/l1;", "serializationConstructorMarker", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcb/l1;)V", "self", "Lbb/c;", "output", "Lab/g;", "serialDesc", "Lx6/t0;", "write$Self$postgrest_kt_release", "(Lio/github/jan/supabase/postgrest/PostgrestErrorResponse;Lbb/c;Lab/g;)V", "write$Self", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lio/github/jan/supabase/postgrest/PostgrestErrorResponse;", "toString", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getMessage", "getHint", "getDetails", "getCode", "Companion", "$serializer", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@r
public final /* data */ class PostgrestErrorResponse {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String code;
    private final String details;
    private final String hint;
    private final String message;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/postgrest/PostgrestErrorResponse$Companion;", "", "<init>", "()V", "Lya/h;", "Lio/github/jan/supabase/postgrest/PostgrestErrorResponse;", "serializer", "()Lya/h;", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final ya.h<PostgrestErrorResponse> serializer() {
            return PostgrestErrorResponse$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    @e
    public /* synthetic */ PostgrestErrorResponse(int i10, String str, String str2, String str3, String str4, l1 l1Var) {
        if (1 != (i10 & 1)) {
            b1.h(i10, 1, PostgrestErrorResponse$$serializer.INSTANCE.getDescriptor());
            throw null;
        }
        this.message = str;
        if ((i10 & 2) == 0) {
            this.hint = null;
        } else {
            this.hint = str2;
        }
        if ((i10 & 4) == 0) {
            this.details = null;
        } else {
            this.details = str3;
        }
        if ((i10 & 8) == 0) {
            this.code = null;
        } else {
            this.code = str4;
        }
    }

    public static /* synthetic */ PostgrestErrorResponse copy$default(PostgrestErrorResponse postgrestErrorResponse, String str, String str2, String str3, String str4, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = postgrestErrorResponse.message;
        }
        if ((i10 & 2) != 0) {
            str2 = postgrestErrorResponse.hint;
        }
        if ((i10 & 4) != 0) {
            str3 = postgrestErrorResponse.details;
        }
        if ((i10 & 8) != 0) {
            str4 = postgrestErrorResponse.code;
        }
        return postgrestErrorResponse.copy(str, str2, str3, str4);
    }

    @n
    public static final /* synthetic */ void write$Self$postgrest_kt_release(PostgrestErrorResponse self, c output, g serialDesc) {
        output.x(serialDesc, 0, self.message);
        if (output.C() || self.hint != null) {
            output.m(serialDesc, 1, r1.f7553a, self.hint);
        }
        if (output.C() || self.details != null) {
            output.m(serialDesc, 2, r1.f7553a, self.details);
        }
        if (!output.C() && self.code == null) {
            return;
        }
        output.m(serialDesc, 3, r1.f7553a, self.code);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getHint() {
        return this.hint;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDetails() {
        return this.details;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getCode() {
        return this.code;
    }

    public final PostgrestErrorResponse copy(String message, String hint, String details, String code) {
        return new PostgrestErrorResponse(message, hint, details, code);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PostgrestErrorResponse)) {
            return false;
        }
        PostgrestErrorResponse postgrestErrorResponse = (PostgrestErrorResponse) other;
        return p.a(this.message, postgrestErrorResponse.message) && p.a(this.hint, postgrestErrorResponse.hint) && p.a(this.details, postgrestErrorResponse.details) && p.a(this.code, postgrestErrorResponse.code);
    }

    public final String getCode() {
        return this.code;
    }

    public final String getDetails() {
        return this.details;
    }

    public final String getHint() {
        return this.hint;
    }

    public final String getMessage() {
        return this.message;
    }

    public int hashCode() {
        int iHashCode = this.message.hashCode() * 31;
        String str = this.hint;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.details;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.code;
        return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("PostgrestErrorResponse(message=");
        sb2.append(this.message);
        sb2.append(", hint=");
        sb2.append(this.hint);
        sb2.append(", details=");
        sb2.append(this.details);
        sb2.append(", code=");
        return androidx.compose.foundation.c.u(sb2, this.code, ')');
    }

    public PostgrestErrorResponse(String str, String str2, String str3, String str4) {
        this.message = str;
        this.hint = str2;
        this.details = str3;
        this.code = str4;
    }

    public /* synthetic */ PostgrestErrorResponse(String str, String str2, String str3, String str4, int i10, h hVar) {
        this(str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3, (i10 & 8) != 0 ? null : str4);
    }
}
