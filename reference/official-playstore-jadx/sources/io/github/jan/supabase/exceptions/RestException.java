package io.github.jan.supabase.exceptions;

import androidx.compose.material3.d;
import com.google.android.gms.cast.MediaTrack;
import f4.f;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpResponseKt;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00060\u0001j\u0002`\u0002B#\b\u0014\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\bB!\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0002\u0010\nR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f\u0082\u0001\u0004\u000e\u000f\u0010\u0011¨\u0006\u0012"}, d2 = {"Lio/github/jan/supabase/exceptions/RestException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "error", "", "response", "Lio/ktor/client/statement/HttpResponse;", "message", "(Ljava/lang/String;Lio/ktor/client/statement/HttpResponse;Ljava/lang/String;)V", MediaTrack.ROLE_DESCRIPTION, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "getError", "Lio/github/jan/supabase/exceptions/BadRequestRestException;", "Lio/github/jan/supabase/exceptions/NotFoundRestException;", "Lio/github/jan/supabase/exceptions/UnauthorizedRestException;", "Lio/github/jan/supabase/exceptions/UnknownRestException;", "supabase-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public abstract class RestException extends Exception {
    private final String description;
    private final String error;

    public /* synthetic */ RestException(String str, HttpResponse httpResponse, String str2, h hVar) {
        this(str, httpResponse, str2);
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getError() {
        return this.error;
    }

    public /* synthetic */ RestException(String str, String str2, String str3, h hVar) {
        this(str, str2, str3);
    }

    private RestException(String str, String str2, String str3) {
        super(str3);
        this.error = str;
        this.description = str2;
    }

    private RestException(String str, HttpResponse httpResponse, String str2) {
        String strI;
        StringBuilder sb2 = new StringBuilder("\n        ");
        sb2.append(str);
        sb2.append((str2 == null || (strI = d.i(')', " (", str2)) == null) ? "" : strI);
        sb2.append("\n        URL: ");
        sb2.append(HttpResponseKt.getRequest(httpResponse).getUrl());
        sb2.append("\n        Headers: ");
        sb2.append(HttpResponseKt.getRequest(httpResponse).getHeaders().entries());
        sb2.append("\n        Http Method: ");
        sb2.append(HttpResponseKt.getRequest(httpResponse).getMethod().getValue());
        sb2.append("\n    ");
        this(str, str2, f.y(sb2.toString()), (h) null);
    }

    public /* synthetic */ RestException(String str, HttpResponse httpResponse, String str2, int i10, h hVar) {
        this(str, httpResponse, (i10 & 4) != 0 ? null : str2, (h) null);
    }
}
