package io.ktor.server.routing;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "invoke", "(Ljava/lang/String;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class RoutingPath$Companion$parse$segments$1 extends r implements l<String, Boolean> {
    public static final RoutingPath$Companion$parse$segments$1 INSTANCE = new RoutingPath$Companion$parse$segments$1();

    public RoutingPath$Companion$parse$segments$1() {
        super(1);
    }

    @Override // r7.l
    public final Boolean invoke(String str) {
        return Boolean.valueOf(str.length() > 0);
    }
}
