package io.ktor.http.cio;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0003\u0010\t\u001a\u00020\u00062\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"Lx6/x;", "", "Lio/ktor/http/cio/ConnectionOptions;", "t", "", "idx", "", "invoke", "(Lx6/x;I)Ljava/lang/Character;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ConnectionOptions$Companion$knownTypes$2 extends r implements p<x, Integer, Character> {
    public static final ConnectionOptions$Companion$knownTypes$2 INSTANCE = new ConnectionOptions$Companion$knownTypes$2();

    public ConnectionOptions$Companion$knownTypes$2() {
        super(2);
    }

    public final Character invoke(x xVar, int i10) {
        return Character.valueOf(((String) xVar.f22608i).charAt(i10));
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke((x) obj, ((Number) obj2).intValue());
    }
}
