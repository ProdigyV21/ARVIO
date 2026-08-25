package io.ktor.http.parsing.regex;

import androidx.compose.material3.d;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/http/parsing/regex/GrammarRegex;", "", "regexRaw", "", "groupsCountRaw", "", "group", "", "(Ljava/lang/String;IZ)V", "groupsCount", "getGroupsCount", "()I", "regex", "getRegex", "()Ljava/lang/String;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class GrammarRegex {
    private final int groupsCount;
    private final String regex;

    public GrammarRegex(String str, int i10, boolean z) {
        this.regex = z ? d.i(')', "(", str) : str;
        this.groupsCount = z ? i10 + 1 : i10;
    }

    public final int getGroupsCount() {
        return this.groupsCount;
    }

    public final String getRegex() {
        return this.regex;
    }

    public /* synthetic */ GrammarRegex(String str, int i10, boolean z, int i11, h hVar) {
        this(str, (i11 & 2) != 0 ? 0 : i10, (i11 & 4) != 0 ? false : z);
    }
}
