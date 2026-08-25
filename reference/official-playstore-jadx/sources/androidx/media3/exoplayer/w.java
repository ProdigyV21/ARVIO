package androidx.media3.exoplayer;

import android.media.MediaRoute2Info;
import androidx.media3.common.Metadata;
import androidx.media3.common.Player;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.DefaultRendererCapabilitiesList;
import androidx.media3.exoplayer.metadata.MetadataOutput;
import androidx.media3.exoplayer.text.TextOutput;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class w implements ListenerSet.Event, TextOutput, MetadataOutput {
    public static /* bridge */ /* synthetic */ MediaRoute2Info a(Object obj) {
        return (MediaRoute2Info) obj;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        ExoPlayerImpl.lambda$release$9((Player.Listener) obj);
    }

    @Override // androidx.media3.exoplayer.text.TextOutput
    public void onCues(CueGroup cueGroup) {
        DefaultRendererCapabilitiesList.Factory.lambda$createRendererCapabilitiesList$0(cueGroup);
    }

    @Override // androidx.media3.exoplayer.metadata.MetadataOutput
    public void onMetadata(Metadata metadata) {
        DefaultRendererCapabilitiesList.Factory.lambda$createRendererCapabilitiesList$1(metadata);
    }

    @Override // androidx.media3.exoplayer.text.TextOutput
    public /* synthetic */ void onCues(List list) {
        androidx.media3.exoplayer.text.b.a(this, list);
    }
}
