package io.github.jan.supabase.gotrue;

import ab.g;
import androidx.compose.foundation.c;
import bb.e;
import com.google.android.gms.cast.MediaTrack;
import db.j;
import db.m;
import db.n;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import xc.d;
import ya.h;
import ya.r;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0081\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lio/github/jan/supabase/gotrue/GoTrueErrorResponse;", "", "error", "", MediaTrack.ROLE_DESCRIPTION, "(Ljava/lang/String;Ljava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "getError", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@r(with = Companion.class)
public final /* data */ class GoTrueErrorResponse {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final g descriptor = d.I("GoTrueErrorResponse", new g[0], GoTrueErrorResponse$Companion$descriptor$1.INSTANCE);
    private final String description;
    private final String error;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001HÆ\u0001¢\u0006\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u00118\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lio/github/jan/supabase/gotrue/GoTrueErrorResponse$Companion;", "Lya/h;", "Lio/github/jan/supabase/gotrue/GoTrueErrorResponse;", "<init>", "()V", "Lbb/d;", "decoder", "deserialize", "(Lbb/d;)Lio/github/jan/supabase/gotrue/GoTrueErrorResponse;", "Lbb/e;", "encoder", "value", "Lx6/t0;", "serialize", "(Lbb/e;Lio/github/jan/supabase/gotrue/GoTrueErrorResponse;)V", "serializer", "()Lya/h;", "Lab/g;", "descriptor", "Lab/g;", "getDescriptor", "()Lab/g;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion implements h<GoTrueErrorResponse> {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        @Override // ya.s, ya.c
        public g getDescriptor() {
            return GoTrueErrorResponse.descriptor;
        }

        public final h<GoTrueErrorResponse> serializer() {
            return GoTrueErrorResponse.INSTANCE;
        }

        private Companion() {
        }

        @Override // ya.c
        public GoTrueErrorResponse deserialize(bb.d decoder) {
            String strB;
            m mVarE = ((j) decoder).e();
            m mVar = (m) n.f(mVarE).get("error");
            if (mVar == null || (strB = n.g(mVar).b()) == null) {
                m mVar2 = (m) n.f(mVarE).get("msg");
                strB = mVar2 != null ? n.g(mVar2).b() : mVarE.toString();
            }
            m mVar3 = (m) n.f(mVarE).get("error_description");
            return new GoTrueErrorResponse(strB, mVar3 != null ? n.g(mVar3).b() : null);
        }

        @Override // ya.s
        public void serialize(e encoder, GoTrueErrorResponse value) {
            throw new UnsupportedOperationException();
        }
    }

    public GoTrueErrorResponse(String str, String str2) {
        this.error = str;
        this.description = str2;
    }

    public static /* synthetic */ GoTrueErrorResponse copy$default(GoTrueErrorResponse goTrueErrorResponse, String str, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = goTrueErrorResponse.error;
        }
        if ((i10 & 2) != 0) {
            str2 = goTrueErrorResponse.description;
        }
        return goTrueErrorResponse.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getError() {
        return this.error;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    public final GoTrueErrorResponse copy(String error, String description) {
        return new GoTrueErrorResponse(error, description);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GoTrueErrorResponse)) {
            return false;
        }
        GoTrueErrorResponse goTrueErrorResponse = (GoTrueErrorResponse) other;
        return p.a(this.error, goTrueErrorResponse.error) && p.a(this.description, goTrueErrorResponse.description);
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getError() {
        return this.error;
    }

    public int hashCode() {
        int iHashCode = this.error.hashCode() * 31;
        String str = this.description;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("GoTrueErrorResponse(error=");
        sb2.append(this.error);
        sb2.append(", description=");
        return c.u(sb2, this.description, ')');
    }
}
