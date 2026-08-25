package io.sentry;

import j$.util.Objects;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
public final class g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f17292a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Pattern f17293b;

    public g0(String str) {
        Pattern patternCompile;
        this.f17292a = str;
        try {
            patternCompile = Pattern.compile(str);
        } catch (Throwable unused) {
            s4.b().getOptions().getLogger().q(w5.DEBUG, "Only using filter string for String comparison as it could not be parsed as regex: %s", str);
            patternCompile = null;
        }
        this.f17293b = patternCompile;
    }

    public final boolean equals(Object obj) {
        if (obj == null || g0.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.f17292a, ((g0) obj).f17292a);
    }

    public final int hashCode() {
        return Objects.hash(this.f17292a);
    }
}
