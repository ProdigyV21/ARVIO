package androidx.media3.common;

import android.os.Bundle;
import android.util.Pair;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.Tracks;
import androidx.media3.common.audio.DefaultGainProvider;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import androidx.media3.extractor.mp4.Mp4Extractor;
import androidx.media3.extractor.mp4.Track;
import com.google.common.collect.b3;
import com.google.common.collect.f3;
import com.google.common.collect.r1;
import com.google.common.collect.t1;
import com.google.common.collect.v3;
import com.google.common.collect.w1;
import j$.util.Objects;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class e implements com.google.common.base.m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3371a;

    public /* synthetic */ e(int i10) {
        this.f3371a = i10;
    }

    @Override // com.google.common.base.m
    public final Object apply(Object obj) {
        boolean z = true;
        switch (this.f3371a) {
            case 0:
                return ((Label) obj).toBundle();
            case 1:
                return Label.fromBundle((Bundle) obj);
            case 2:
                return Format.lambda$toLogString$0((Label) obj);
            case 3:
                return ((StreamKey) obj).toBundle();
            case 4:
                return ((MediaItem.SubtitleConfiguration) obj).toBundle();
            case 5:
                return StreamKey.fromBundle((Bundle) obj);
            case 6:
                return MediaItem.SubtitleConfiguration.fromBundle((Bundle) obj);
            case 7:
                return Timeline.Window.fromBundle((Bundle) obj);
            case 8:
                return Timeline.Period.fromBundle((Bundle) obj);
            case 9:
                return Format.fromBundle((Bundle) obj);
            case 10:
                return ((TrackSelectionOverride) obj).toBundle();
            case 11:
                return TrackSelectionOverride.fromBundle((Bundle) obj);
            case 12:
                return ((Tracks.Group) obj).toBundle();
            case 13:
                return Tracks.Group.fromBundle((Bundle) obj);
            case 14:
                return DefaultGainProvider.Builder.lambda$new$0((Pair) obj);
            case 15:
                return CueGroup.lambda$static$0((Cue) obj);
            case 16:
                return Cue.fromBundle((Bundle) obj);
            case 17:
                return ((Cue) obj).toBinderBasedBundle();
            case 18:
                return Mp4Extractor.lambda$processMoovAtom$2((Track) obj);
            case 19:
                String str = (String) obj;
                com.google.common.base.a aVar = com.google.common.net.b.f14168g;
                aVar.getClass();
                int length = str.length() - 1;
                while (true) {
                    if (length >= 0) {
                        if (aVar.c(str.charAt(length))) {
                            length--;
                        } else {
                            z = false;
                        }
                    }
                }
                if (z && !str.isEmpty()) {
                    return str;
                }
                StringBuilder sb2 = new StringBuilder(str.length() + 16);
                sb2.append('\"');
                while (i < str.length()) {
                    char cCharAt = str.charAt(i);
                    if (cCharAt == '\r' || cCharAt == '\\' || cCharAt == '\"') {
                        sb2.append('\\');
                    }
                    sb2.append(cCharAt);
                    i++;
                }
                sb2.append('\"');
                return sb2.toString();
            default:
                Collection collection = (Collection) obj;
                int i10 = t1.f14110n;
                if (collection instanceof t1) {
                    return (t1) collection;
                }
                boolean z5 = collection instanceof b3;
                int size = z5 ? ((b3) collection).t0().size() : 11;
                r1 r1Var = new r1();
                r1Var.f14097b = false;
                f3 f3Var = new f3();
                f3Var.d(size);
                r1Var.f14096a = f3Var;
                if (z5) {
                    b3 b3Var = (b3) collection;
                    f3 f3Var2 = b3Var instanceof v3 ? ((v3) b3Var).f14128o : null;
                    if (f3Var2 != null) {
                        f3Var.a(Math.max(f3Var.f14000c, f3Var2.f14000c));
                        i = f3Var2.f14000c == 0 ? -1 : 0;
                        while (i >= 0) {
                            ac.b.n(i, f3Var2.f14000c);
                            Object obj2 = f3Var2.f13998a[i];
                            ac.b.n(i, f3Var2.f14000c);
                            r1Var.c(f3Var2.f13999b[i], obj2);
                            i++;
                            if (i >= f3Var2.f14000c) {
                                i = -1;
                            }
                        }
                    } else {
                        w1 w1VarD0 = b3Var.D0();
                        f3 f3Var3 = r1Var.f14096a;
                        f3Var3.a(Math.max(f3Var3.f14000c, w1VarD0.size()));
                        for (b3.a aVar2 : b3Var.D0()) {
                            r1Var.c(aVar2.getCount(), aVar2.a());
                        }
                    }
                } else {
                    Iterator it = collection.iterator();
                    while (it.hasNext()) {
                        r1Var.a(it.next());
                    }
                }
                Objects.requireNonNull(r1Var.f14096a);
                if (r1Var.f14096a.f14000c == 0) {
                    return v3.f14127r;
                }
                r1Var.f14097b = true;
                return new v3(r1Var.f14096a);
        }
    }
}
