package com.arflix.tv.util;

import com.arflix.tv.ui.screens.player.w0;
import com.google.common.util.concurrent.r0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\u0004\u001a\u0004\u0018\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u000b\u001a\u00020\fJ6\u0010\r\u001a\u0004\u0018\u00010\u00052\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013¨\u0006\u0014"}, d2 = {"Lcom/arflix/tv/util/ContinueWatchingSelector;", "", "<init>", "()V", "selectInProgressEpisode", "Lcom/arflix/tv/util/EpisodePointer;", "inProgress", "", "Lcom/arflix/tv/util/InProgressSnapshot;", "watched", "", "completionThreshold", "", "selectNextEpisodeAfterLastWatched", "episodes", "Lcom/arflix/tv/util/EpisodeProgressSnapshot;", "lastWatched", "Lcom/arflix/tv/util/WatchedEpisodeSnapshot;", "includeSpecials", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ContinueWatchingSelector {
    public static final int $stable = 0;
    public static final ContinueWatchingSelector INSTANCE = new ContinueWatchingSelector();

    private ContinueWatchingSelector() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Comparable selectNextEpisodeAfterLastWatched$lambda$1(EpisodeProgressSnapshot episodeProgressSnapshot) {
        return Integer.valueOf(episodeProgressSnapshot.getSeason());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Comparable selectNextEpisodeAfterLastWatched$lambda$2(EpisodeProgressSnapshot episodeProgressSnapshot) {
        return Integer.valueOf(episodeProgressSnapshot.getEpisode());
    }

    public final EpisodePointer selectInProgressEpisode(List<InProgressSnapshot> inProgress, Set<EpisodePointer> watched, float completionThreshold) {
        Object next;
        ArrayList arrayList = new ArrayList();
        for (Object obj : inProgress) {
            InProgressSnapshot inProgressSnapshot = (InProgressSnapshot) obj;
            if (inProgressSnapshot.getProgress() > 0.0f && inProgressSnapshot.getProgress() < completionThreshold) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            InProgressSnapshot inProgressSnapshot2 = (InProgressSnapshot) obj2;
            if (!watched.contains(new EpisodePointer(inProgressSnapshot2.getSeason(), inProgressSnapshot2.getEpisode()))) {
                arrayList2.add(obj2);
            }
        }
        Iterator it = arrayList2.iterator();
        if (it.hasNext()) {
            next = it.next();
            if (it.hasNext()) {
                String updatedAt = ((InProgressSnapshot) next).getUpdatedAt();
                if (updatedAt == null) {
                    updatedAt = "";
                }
                do {
                    Object next2 = it.next();
                    String updatedAt2 = ((InProgressSnapshot) next2).getUpdatedAt();
                    if (updatedAt2 == null) {
                        updatedAt2 = "";
                    }
                    if (updatedAt.compareTo(updatedAt2) < 0) {
                        next = next2;
                        updatedAt = updatedAt2;
                    }
                } while (it.hasNext());
            }
        } else {
            next = null;
        }
        InProgressSnapshot inProgressSnapshot3 = (InProgressSnapshot) next;
        if (inProgressSnapshot3 != null) {
            return new EpisodePointer(inProgressSnapshot3.getSeason(), inProgressSnapshot3.getEpisode());
        }
        return null;
    }

    public final EpisodePointer selectNextEpisodeAfterLastWatched(List<EpisodeProgressSnapshot> episodes, Set<EpisodePointer> watched, WatchedEpisodeSnapshot lastWatched, boolean includeSpecials) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : episodes) {
            EpisodeProgressSnapshot episodeProgressSnapshot = (EpisodeProgressSnapshot) obj;
            if (includeSpecials || episodeProgressSnapshot.getSeason() != 0) {
                arrayList.add(obj);
            }
        }
        List listW0 = x.W0(arrayList, r0.d(new w0(25), new w0(26)));
        if (listW0.isEmpty()) {
            return null;
        }
        int i10 = -1;
        if (lastWatched != null) {
            Iterator it = listW0.iterator();
            int i11 = 0;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                EpisodeProgressSnapshot episodeProgressSnapshot2 = (EpisodeProgressSnapshot) it.next();
                if (episodeProgressSnapshot2.getSeason() == lastWatched.getSeason() && episodeProgressSnapshot2.getEpisode() == lastWatched.getEpisode()) {
                    i10 = i11;
                    break;
                }
                i11++;
            }
        }
        int size = listW0.size();
        for (int i12 = i10 >= 0 ? i10 + 1 : 0; i12 < size; i12++) {
            EpisodeProgressSnapshot episodeProgressSnapshot3 = (EpisodeProgressSnapshot) listW0.get(i12);
            EpisodePointer episodePointer = new EpisodePointer(episodeProgressSnapshot3.getSeason(), episodeProgressSnapshot3.getEpisode());
            if (!episodeProgressSnapshot3.getCompleted() && !watched.contains(episodePointer)) {
                return episodePointer;
            }
        }
        return null;
    }
}
