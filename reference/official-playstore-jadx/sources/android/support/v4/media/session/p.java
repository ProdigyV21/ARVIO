package android.support.v4.media.session;

import android.media.session.MediaSessionManager;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class p extends o {
    @Override // android.support.v4.media.session.o, android.support.v4.media.session.m
    public final void a(androidx.media.d dVar) {
    }

    @Override // android.support.v4.media.session.o, android.support.v4.media.session.m
    public final androidx.media.d getCurrentControllerInfo() {
        MediaSessionManager.RemoteUserInfo currentControllerInfo = this.f886a.getCurrentControllerInfo();
        androidx.media.d dVar = new androidx.media.d();
        String packageName = currentControllerInfo.getPackageName();
        if (packageName == null) {
            throw new NullPointerException("package shouldn't be null");
        }
        if (TextUtils.isEmpty(packageName)) {
            throw new IllegalArgumentException("packageName should be nonempty");
        }
        dVar.f3355a = new androidx.media.g(currentControllerInfo.getPackageName(), currentControllerInfo.getPid(), currentControllerInfo.getUid());
        return dVar;
    }
}
