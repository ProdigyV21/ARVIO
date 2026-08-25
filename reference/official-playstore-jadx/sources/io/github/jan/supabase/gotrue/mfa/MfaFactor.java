package io.github.jan.supabase.gotrue.mfa;

import androidx.compose.foundation.c;
import androidx.compose.material3.d;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00028\u0000¢\u0006\u0002\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0004HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0004HÆ\u0003J\u000e\u0010\u0010\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\tJ2\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u0012J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0004HÖ\u0001R\u0013\u0010\u0006\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"Lio/github/jan/supabase/gotrue/mfa/MfaFactor;", "T", "", TtmlNode.ATTR_ID, "", LinkHeader.Parameters.Type, "data", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getId", "()Ljava/lang/String;", "getType", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Lio/github/jan/supabase/gotrue/mfa/MfaFactor;", "equals", "", "other", "hashCode", "", "toString", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class MfaFactor<T> {
    private final T data;
    private final String id;
    private final String type;

    public MfaFactor(String str, String str2, T t2) {
        this.id = str;
        this.type = str2;
        this.data = t2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MfaFactor copy$default(MfaFactor mfaFactor, String str, String str2, Object obj, int i10, Object obj2) {
        if ((i10 & 1) != 0) {
            str = mfaFactor.id;
        }
        if ((i10 & 2) != 0) {
            str2 = mfaFactor.type;
        }
        if ((i10 & 4) != 0) {
            obj = mfaFactor.data;
        }
        return mfaFactor.copy(str, str2, obj);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getType() {
        return this.type;
    }

    public final T component3() {
        return this.data;
    }

    public final MfaFactor<T> copy(String id, String type, T data) {
        return new MfaFactor<>(id, type, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MfaFactor)) {
            return false;
        }
        MfaFactor mfaFactor = (MfaFactor) other;
        return p.a(this.id, mfaFactor.id) && p.a(this.type, mfaFactor.type) && p.a(this.data, mfaFactor.data);
    }

    public final T getData() {
        return this.data;
    }

    public final String getId() {
        return this.id;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        int iC = c.c(this.id.hashCode() * 31, 31, this.type);
        T t2 = this.data;
        return iC + (t2 == null ? 0 : t2.hashCode());
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("MfaFactor(id=");
        sb2.append(this.id);
        sb2.append(", type=");
        sb2.append(this.type);
        sb2.append(", data=");
        return d.p(sb2, this.data, ')');
    }
}
