package com.google.android.gms.internal.cast;

import android.widget.TextView;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class t1 extends UIController {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f13610a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TextView f13611b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f13612c;

    public t1(TextView textView, List list, int i10) {
        this.f13610a = i10;
        switch (i10) {
            case 1:
                ArrayList arrayList = new ArrayList();
                this.f13612c = arrayList;
                this.f13611b = textView;
                arrayList.addAll(list);
                break;
            default:
                ArrayList arrayList2 = new ArrayList();
                this.f13612c = arrayList2;
                this.f13611b = textView;
                arrayList2.addAll(list);
                break;
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        MediaQueueItem preloadedItem;
        MediaInfo media;
        MediaMetadata metadata;
        TextView textView;
        String str;
        MediaMetadata metadata2;
        TextView textView2;
        String str2;
        switch (this.f13610a) {
            case 0:
                RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
                if (remoteMediaClient != null && remoteMediaClient.hasMediaSession() && (preloadedItem = remoteMediaClient.getPreloadedItem()) != null && (media = preloadedItem.getMedia()) != null && (metadata = media.getMetadata()) != null) {
                    Iterator it = this.f13612c.iterator();
                    do {
                        boolean zHasNext = it.hasNext();
                        textView = this.f13611b;
                        if (!zHasNext) {
                            textView.setText("");
                        } else {
                            str = (String) it.next();
                        }
                        break;
                    } while (!metadata.containsKey(str));
                    textView.setText(metadata.getString(str));
                    break;
                }
                break;
            default:
                RemoteMediaClient remoteMediaClient2 = getRemoteMediaClient();
                if (remoteMediaClient2 != null && remoteMediaClient2.hasMediaSession()) {
                    MediaStatus mediaStatus = remoteMediaClient2.getMediaStatus();
                    com.google.android.gms.common.internal.t.i(mediaStatus);
                    MediaInfo mediaInfo = mediaStatus.getMediaInfo();
                    if (mediaInfo != null && (metadata2 = mediaInfo.getMetadata()) != null) {
                        Iterator it2 = this.f13612c.iterator();
                        do {
                            boolean zHasNext2 = it2.hasNext();
                            textView2 = this.f13611b;
                            if (!zHasNext2) {
                                textView2.setText("");
                            } else {
                                str2 = (String) it2.next();
                            }
                            break;
                        } while (!metadata2.containsKey(str2));
                        textView2.setText(metadata2.getString(str2));
                        break;
                    }
                }
                break;
        }
    }
}
