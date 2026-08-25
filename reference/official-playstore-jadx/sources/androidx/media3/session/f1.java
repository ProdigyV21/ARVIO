package androidx.media3.session;

import androidx.media3.common.FlagSet;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class f1 implements ListenerSet.Event, ListenerSet.IterationFinishedEvent {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ MediaControllerImplLegacy f3977i;

    public /* synthetic */ f1(MediaControllerImplLegacy mediaControllerImplLegacy) {
        this.f3977i = mediaControllerImplLegacy;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        this.f3977i.lambda$updateControllerInfo$14((Player.Listener) obj);
    }

    @Override // androidx.media3.common.util.ListenerSet.IterationFinishedEvent
    public void invoke(Object obj, FlagSet flagSet) {
        this.f3977i.lambda$new$0((Player.Listener) obj, flagSet);
    }
}
