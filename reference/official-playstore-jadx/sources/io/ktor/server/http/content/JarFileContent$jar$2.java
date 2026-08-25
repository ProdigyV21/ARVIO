package io.ktor.server.http.content;

import java.util.jar.JarFile;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/util/jar/JarFile;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class JarFileContent$jar$2 extends r implements a<JarFile> {
    final /* synthetic */ JarFileContent this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JarFileContent$jar$2(JarFileContent jarFileContent) {
        super(0);
        this.this$0 = jarFileContent;
    }

    @Override // r7.a
    public final JarFile invoke() {
        return new JarFile(this.this$0.getJarFile());
    }
}
