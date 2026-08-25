package android.content;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J3\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u0007H&¢\u0006\u0004\b\n\u0010\u000bR\u001c\u0010\u0010\u001a\u00020\u00048&@&X¦\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Lcoil/util/Logger;", "", "", "tag", "", "priority", "message", "", "throwable", "Lx6/t0;", "log", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/Throwable;)V", "getLevel", "()I", "setLevel", "(I)V", "level", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface Logger {
    int getLevel();

    void log(String tag, int priority, String message, Throwable throwable);

    void setLevel(int i10);
}
