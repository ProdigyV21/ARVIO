package io.ktor.http;

import ga.p;
import ga.t;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Lio/ktor/http/ContentType;", "", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class FileContentTypeKt$extensionsByContentType$2 extends r implements a<Map<ContentType, ? extends List<? extends String>>> {
    public static final FileContentTypeKt$extensionsByContentType$2 INSTANCE = new FileContentTypeKt$extensionsByContentType$2();

    /* JADX INFO: renamed from: io.ktor.http.FileContentTypeKt$extensionsByContentType$2$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u00002\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lx6/x;", "", "Lio/ktor/http/ContentType;", "<name for destructuring parameter 0>", "invoke", "(Lx6/x;)Lx6/x;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<x, x> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final x invoke(x xVar) {
            return new x((ContentType) xVar.f22609l, (String) xVar.f22608i);
        }
    }

    public FileContentTypeKt$extensionsByContentType$2() {
        super(0);
    }

    @Override // r7.a
    public final Map<ContentType, List<String>> invoke() {
        return FileContentTypeKt.groupByPairs(new t(new p(MimesKt.getMimes(), 3), AnonymousClass1.INSTANCE));
    }
}
