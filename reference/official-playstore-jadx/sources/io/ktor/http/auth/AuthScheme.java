package io.ktor.http.auth;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lio/ktor/http/auth/AuthScheme;", "", "()V", AuthScheme.Basic, "", AuthScheme.Bearer, AuthScheme.Digest, AuthScheme.Negotiate, AuthScheme.OAuth, "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AuthScheme {
    public static final String Basic = "Basic";
    public static final String Bearer = "Bearer";
    public static final String Digest = "Digest";
    public static final AuthScheme INSTANCE = new AuthScheme();
    public static final String Negotiate = "Negotiate";
    public static final String OAuth = "OAuth";

    private AuthScheme() {
    }
}
