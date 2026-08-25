package androidx.leanback.widget;

import com.google.android.gms.cast.MediaStatus;

/* JADX INFO: loaded from: classes3.dex */
public class r implements u7.c, ra.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3109a;

    @Override // ra.i
    public int a() {
        return this.f3109a;
    }

    @Override // u7.c
    public Object getValue(Object obj, kotlin.reflect.m mVar) {
        return ((ba.d) obj).f7274i.get(this.f3109a);
    }

    public r(MediaStatus mediaStatus) {
        this.f3109a = mediaStatus.getPlayerState();
    }
}
