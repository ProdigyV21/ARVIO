package androidx.media;

import android.media.VolumeProvider;
import androidx.appcompat.app.i1;
import androidx.emoji2.text.y;

/* JADX INFO: loaded from: classes3.dex */
public final class j extends VolumeProvider {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ y f3360a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(y yVar, int i10, int i11, int i12) {
        super(i10, i11, i12);
        this.f3360a = yVar;
    }

    @Override // android.media.VolumeProvider
    public final void onAdjustVolume(int i10) {
        y yVar = this.f3360a;
        ((androidx.mediarouter.media.f) ((i1) yVar.f2656f).f1061n).f4326a.post(new androidx.mediarouter.media.d(yVar, i10, 1));
    }

    @Override // android.media.VolumeProvider
    public final void onSetVolumeTo(int i10) {
        y yVar = this.f3360a;
        ((androidx.mediarouter.media.f) ((i1) yVar.f2656f).f1061n).f4326a.post(new androidx.mediarouter.media.d(yVar, i10, 0));
    }
}
