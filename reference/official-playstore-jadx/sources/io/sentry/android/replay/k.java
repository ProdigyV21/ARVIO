package io.sentry.android.replay;

import io.sentry.w5;
import io.sentry.y6;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public final class k extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16973i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ m f16974l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k(m mVar, int i10) {
        super(0);
        this.f16973i = i10;
        this.f16974l = mVar;
    }

    @Override // r7.a
    public final Object invoke() throws IOException {
        switch (this.f16973i) {
            case 0:
                m mVar = this.f16974l;
                if (mVar.k() == null) {
                    return null;
                }
                File file = new File(mVar.k(), ".ongoing_segment");
                if (!file.exists()) {
                    file.createNewFile();
                }
                return file;
            default:
                m mVar2 = this.f16974l;
                y6 y6Var = mVar2.f16979i;
                io.sentry.protocol.v vVar = mVar2.f16980l;
                String cacheDirPath = y6Var.getCacheDirPath();
                if (cacheDirPath == null || cacheDirPath.length() == 0) {
                    y6Var.getLogger().q(w5.WARNING, "SentryOptions.cacheDirPath is not set, session replay is no-op", new Object[0]);
                    return null;
                }
                File file2 = new File(y6Var.getCacheDirPath(), "replay_" + vVar);
                file2.mkdirs();
                return file2;
        }
    }
}
