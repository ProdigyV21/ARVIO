package io.github.jan.supabase.gotrue.providers;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nHÖ\u0003J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\t\u0010\r\u001a\u00020\u0004HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/Twitter;", "Lio/github/jan/supabase/gotrue/providers/OAuthProvider;", "()V", ContentDisposition.Parameters.Name, "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "toString", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class Twitter extends OAuthProvider {
    public static final Twitter INSTANCE = new Twitter();
    private static final String name = "twitter";

    private Twitter() {
    }

    public boolean equals(Object other) {
        return this == other || (other instanceof Twitter);
    }

    @Override // io.github.jan.supabase.gotrue.providers.OAuthProvider
    public String getName() {
        return name;
    }

    public int hashCode() {
        return 2021710079;
    }

    public String toString() {
        return "Twitter";
    }
}
