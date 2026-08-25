package io.github.jan.supabase.exceptions;

import io.ktor.client.statement.HttpResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lio/github/jan/supabase/exceptions/BadRequestRestException;", "Lio/github/jan/supabase/exceptions/RestException;", "error", "", "response", "Lio/ktor/client/statement/HttpResponse;", "message", "(Ljava/lang/String;Lio/ktor/client/statement/HttpResponse;Ljava/lang/String;)V", "supabase-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class BadRequestRestException extends RestException {
    public BadRequestRestException(String str, HttpResponse httpResponse, String str2) {
        super(str, httpResponse, str2, (h) null);
    }

    public /* synthetic */ BadRequestRestException(String str, HttpResponse httpResponse, String str2, int i10, h hVar) {
        this(str, httpResponse, (i10 & 4) != 0 ? null : str2);
    }
}
