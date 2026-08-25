package io.ktor.http;

import androidx.compose.material3.d;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.text.u;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0002¨\u0006\u0004"}, d2 = {"encodeContentDispositionAttribute", "", "key", "value", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContentDispositionKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String encodeContentDispositionAttribute(String str, String str2) {
        if (!p.a(str, ContentDisposition.Parameters.FileNameAsterisk) || u.P(str2, "utf-8''", true)) {
            return str2;
        }
        for (int i10 = 0; i10 < str2.length(); i10++) {
            if (!CodecsKt.getATTRIBUTE_CHARACTERS().contains(Character.valueOf(str2.charAt(i10)))) {
                return d.C("utf-8''", CodecsKt.percentEncode(str2, CodecsKt.getATTRIBUTE_CHARACTERS()));
            }
        }
        return str2;
    }
}
