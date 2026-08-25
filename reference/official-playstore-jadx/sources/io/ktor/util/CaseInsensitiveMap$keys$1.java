package io.ktor.util;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Value", "", "Lio/ktor/util/CaseInsensitiveString;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class CaseInsensitiveMap$keys$1 extends r implements l<CaseInsensitiveString, String> {
    public static final CaseInsensitiveMap$keys$1 INSTANCE = new CaseInsensitiveMap$keys$1();

    public CaseInsensitiveMap$keys$1() {
        super(1);
    }

    @Override // r7.l
    public final String invoke(CaseInsensitiveString caseInsensitiveString) {
        return caseInsensitiveString.getContent();
    }
}
