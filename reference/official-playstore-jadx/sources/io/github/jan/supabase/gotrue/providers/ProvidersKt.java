package io.github.jan.supabase.gotrue.providers;

import io.github.jan.supabase.gotrue.providers.OAuthProvider;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\u0002¨\u0006\u0005"}, d2 = {"invoke", "Lio/github/jan/supabase/gotrue/providers/OAuthProvider;", "Lio/github/jan/supabase/gotrue/providers/OAuthProvider$Companion;", "provider", "", "gotrue-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class ProvidersKt {
    public static final OAuthProvider invoke(OAuthProvider.Companion companion, String str) {
        return new OAuthProvider(str) { // from class: io.github.jan.supabase.gotrue.providers.ProvidersKt.invoke.1
            private final String name;

            {
                this.name = str;
            }

            @Override // io.github.jan.supabase.gotrue.providers.OAuthProvider
            public String getName() {
                return this.name;
            }
        };
    }
}
