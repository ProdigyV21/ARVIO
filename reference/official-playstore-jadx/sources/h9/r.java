package h9;

import kotlin.text.u;

/* JADX INFO: loaded from: classes5.dex */
public final class r extends t {
    public r() {
        super("HTML", 1);
    }

    @Override // h9.t
    public final String a(String str) {
        return u.O(u.O(str, "<", "&lt;", false), ">", "&gt;", false);
    }
}
