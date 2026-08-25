package io.github.jan.supabase.gotrue;

import d7.d;
import ja.a;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0096@¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0096@¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0006H\u0096@¢\u0006\u0004\b\u000b\u0010\nR/\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u00048B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lio/github/jan/supabase/gotrue/MemoryCodeVerifierCache;", "Lio/github/jan/supabase/gotrue/CodeVerifierCache;", "<init>", "()V", "", "codeVerifier", "Lx6/t0;", "saveCodeVerifier", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "loadCodeVerifier", "(Ld7/d;)Ljava/lang/Object;", "deleteCodeVerifier", "<set-?>", "codeVerifier$delegate", "Lja/a;", "getCodeVerifier", "()Ljava/lang/String;", "setCodeVerifier", "(Ljava/lang/String;)V", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class MemoryCodeVerifierCache implements CodeVerifierCache {

    /* JADX INFO: renamed from: codeVerifier$delegate, reason: from kotlin metadata */
    private final a codeVerifier = new a(null);

    private final String getCodeVerifier() {
        return (String) this.codeVerifier.b();
    }

    private final void setCodeVerifier(String str) {
        this.codeVerifier.c(str);
    }

    @Override // io.github.jan.supabase.gotrue.CodeVerifierCache
    public Object deleteCodeVerifier(d<? super t0> dVar) {
        setCodeVerifier(null);
        return t0.f22605a;
    }

    @Override // io.github.jan.supabase.gotrue.CodeVerifierCache
    public Object loadCodeVerifier(d<? super String> dVar) {
        return getCodeVerifier();
    }

    @Override // io.github.jan.supabase.gotrue.CodeVerifierCache
    public Object saveCodeVerifier(String str, d<? super t0> dVar) {
        setCodeVerifier(str);
        return t0.f22605a;
    }
}
