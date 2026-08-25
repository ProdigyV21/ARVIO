package androidx.media3.session;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaSession;
import androidx.media3.session.PlayerInfo;
import androidx.media3.session.legacy.MediaBrowserServiceCompat;
import androidx.media3.session.legacy.PlaybackStateCompat;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes3.dex */
public final class MediaUtils {
    public static final long POSITION_DIFF_TOLERANCE_MS = 100;
    private static final String TAG = "MediaUtils";
    public static final int TRANSACTION_SIZE_LIMIT_IN_BYTES = 262144;
    public static final MediaBrowserServiceCompat.BrowserRoot defaultBrowserRoot = new MediaBrowserServiceCompat.BrowserRoot(MediaLibraryService.SERVICE_INTERFACE, null);

    private MediaUtils() {
    }

    public static boolean areEqualError(PlaybackStateCompat playbackStateCompat, PlaybackStateCompat playbackStateCompat2) {
        boolean z = playbackStateCompat != null && playbackStateCompat.getState() == 7;
        boolean z5 = playbackStateCompat2 != null && playbackStateCompat2.getState() == 7;
        return (z && z5) ? ((PlaybackStateCompat) Util.castNonNull(playbackStateCompat)).getErrorCode() == ((PlaybackStateCompat) Util.castNonNull(playbackStateCompat2)).getErrorCode() && TextUtils.equals(((PlaybackStateCompat) Util.castNonNull(playbackStateCompat)).getErrorMessage(), ((PlaybackStateCompat) Util.castNonNull(playbackStateCompat2)).getErrorMessage()) : z == z5;
    }

    public static boolean areSessionPositionInfosInSamePeriodOrAd(SessionPositionInfo sessionPositionInfo, SessionPositionInfo sessionPositionInfo2) {
        Player.PositionInfo positionInfo = sessionPositionInfo.positionInfo;
        int i10 = positionInfo.mediaItemIndex;
        Player.PositionInfo positionInfo2 = sessionPositionInfo2.positionInfo;
        return i10 == positionInfo2.mediaItemIndex && positionInfo.periodIndex == positionInfo2.periodIndex && positionInfo.adGroupIndex == positionInfo2.adGroupIndex && positionInfo.adIndexInAdGroup == positionInfo2.adIndexInAdGroup;
    }

    public static int calculateBufferedPercentage(long j10, long j11) {
        if (j10 == C.TIME_UNSET || j11 == C.TIME_UNSET) {
            return 0;
        }
        if (j11 == 0) {
            return 100;
        }
        return Util.constrainValue(Util.percentInt(j10, j11), 0, 100);
    }

    public static Player.Commands createPlayerCommandsWith(int i10) {
        return new Player.Commands.Builder().add(i10).build();
    }

    public static Player.Commands createPlayerCommandsWithout(int i10) {
        return new Player.Commands.Builder().addAllCommands().remove(i10).build();
    }

    public static int[] generateUnshuffledIndices(int i10) {
        int[] iArr = new int[i10];
        for (int i11 = 0; i11 < i10; i11++) {
            iArr[i11] = i11;
        }
        return iArr;
    }

    public static long getUpdatedCurrentPositionMs(PlayerInfo playerInfo, long j10, long j11, long j12) {
        boolean z = playerInfo.sessionPositionInfo.equals(SessionPositionInfo.DEFAULT) || j11 < playerInfo.sessionPositionInfo.eventTimeMs;
        if (playerInfo.isPlaying) {
            if (z || j10 == C.TIME_UNSET) {
                if (j12 == C.TIME_UNSET) {
                    j12 = SystemClock.elapsedRealtime() - playerInfo.sessionPositionInfo.eventTimeMs;
                }
                SessionPositionInfo sessionPositionInfo = playerInfo.sessionPositionInfo;
                long j13 = sessionPositionInfo.positionInfo.positionMs + ((long) (j12 * playerInfo.playbackParameters.speed));
                long j14 = sessionPositionInfo.durationMs;
                return j14 != C.TIME_UNSET ? Math.min(j13, j14) : j13;
            }
        } else if (z || j10 == C.TIME_UNSET) {
            return playerInfo.sessionPositionInfo.positionInfo.positionMs;
        }
        return j10;
    }

    public static Player.Commands intersect(Player.Commands commands, Player.Commands commands2) {
        if (commands == null || commands2 == null) {
            return Player.Commands.EMPTY;
        }
        Player.Commands.Builder builder = new Player.Commands.Builder();
        for (int i10 = 0; i10 < commands.size(); i10++) {
            if (commands2.contains(commands.get(i10))) {
                builder.add(commands.get(i10));
            }
        }
        return builder.build();
    }

    public static PlayerInfo mergePlayerInfo(PlayerInfo playerInfo, PlayerInfo playerInfo2, PlayerInfo.BundlingExclusions bundlingExclusions, Player.Commands commands, boolean z) {
        PlayerInfo playerInfoCopyWithTimeline = (bundlingExclusions.isTimelineExcluded && commands.contains(17)) ? playerInfo2.copyWithTimeline(playerInfo.timeline) : playerInfo2;
        if (bundlingExclusions.areCurrentTracksExcluded && commands.contains(30)) {
            playerInfoCopyWithTimeline = playerInfoCopyWithTimeline.copyWithCurrentTracks(playerInfo.currentTracks);
        }
        return (z && playerInfo2.volume == 0.0f) ? playerInfoCopyWithTimeline.copyWithUnmuteVolume(playerInfo.unmuteVolume) : playerInfoCopyWithTimeline;
    }

    public static <T> List<T> removeNullElements(List<T> list) {
        ArrayList arrayList = new ArrayList();
        for (T t2 : list) {
            if (t2 != null) {
                arrayList.add(t2);
            }
        }
        return arrayList;
    }

    public static void setMediaItemsWithStartIndexAndPosition(Player player, MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition) {
        if (mediaItemsWithStartPosition.startIndex == -1) {
            if (player.isCommandAvailable(20)) {
                player.setMediaItems(mediaItemsWithStartPosition.mediaItems, true);
                return;
            } else {
                if (mediaItemsWithStartPosition.mediaItems.isEmpty()) {
                    return;
                }
                player.setMediaItem((MediaItem) mediaItemsWithStartPosition.mediaItems.get(0), true);
                return;
            }
        }
        if (player.isCommandAvailable(20)) {
            player.setMediaItems(mediaItemsWithStartPosition.mediaItems, mediaItemsWithStartPosition.startIndex, mediaItemsWithStartPosition.startPositionMs);
        } else {
            if (mediaItemsWithStartPosition.mediaItems.isEmpty()) {
                return;
            }
            player.setMediaItem((MediaItem) mediaItemsWithStartPosition.mediaItems.get(0), mediaItemsWithStartPosition.startPositionMs);
        }
    }

    public static <T extends Parcelable> List<T> truncateListBySize(List<T> list, int i10) {
        ArrayList arrayList = new ArrayList();
        Parcel parcelObtain = Parcel.obtain();
        for (int i11 = 0; i11 < list.size(); i11++) {
            try {
                T t2 = list.get(i11);
                parcelObtain.writeParcelable(t2, 0);
                if (parcelObtain.dataSize() >= i10) {
                    break;
                }
                arrayList.add(t2);
            } finally {
                parcelObtain.recycle();
            }
        }
        return arrayList;
    }
}
