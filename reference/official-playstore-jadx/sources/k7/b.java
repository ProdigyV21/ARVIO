package k7;

import f4.f;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import x7.i;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends f {
    public final kotlin.text.f E(MatchResult matchResult, String str) {
        Matcher matcher = matchResult instanceof Matcher ? (Matcher) matchResult : null;
        if (matcher == null) {
            throw new UnsupportedOperationException("Retrieving groups by name is not supported on this platform.");
        }
        int iStart = matcher.start(str);
        i iVar = new i(iStart, matcher.end(str) - 1, 1);
        if (iStart >= 0) {
            return new kotlin.text.f(matcher.group(str), iVar);
        }
        return null;
    }
}
