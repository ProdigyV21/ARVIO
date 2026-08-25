package q9;

import androidx.appcompat.app.o0;
import j8.p;
import v9.w;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends o0 implements e {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final p f21358m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public b(g8.a aVar, w wVar, f fVar) {
        super(wVar, fVar);
        if (wVar == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "receiverType", "kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/ExtensionReceiver", "<init>"));
        }
        this.f21358m = (p) aVar;
    }

    public final String toString() {
        return getType() + ": Ext {" + this.f21358m + "}";
    }
}
