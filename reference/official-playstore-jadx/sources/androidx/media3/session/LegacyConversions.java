package androidx.media3.session;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.media3.common.AdPlaybackState;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.HeartRating;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PercentageRating;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.StarRating;
import androidx.media3.common.ThumbRating;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.CommandButton;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.SessionCommands;
import androidx.media3.session.legacy.MediaBrowserCompat;
import androidx.media3.session.legacy.MediaBrowserServiceCompat;
import androidx.media3.session.legacy.MediaControllerCompat;
import androidx.media3.session.legacy.MediaDescriptionCompat;
import androidx.media3.session.legacy.MediaMetadataCompat;
import androidx.media3.session.legacy.MediaSessionCompat;
import androidx.media3.session.legacy.PlaybackStateCompat;
import androidx.media3.session.legacy.RatingCompat;
import com.google.common.collect.o3;
import com.google.common.collect.o4;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes3.dex */
class LegacyConversions {
    public static final com.google.common.collect.w1 KNOWN_METADATA_COMPAT_KEYS;
    private static final String TAG = "LegacyConversions";

    public static class ConversionException extends Exception {
        private ConversionException(String str) {
            super(str);
        }
    }

    static {
        String[] strArr = {MediaMetadataCompat.METADATA_KEY_COMPOSER, MediaMetadataCompat.METADATA_KEY_COMPILATION, MediaMetadataCompat.METADATA_KEY_DATE, MediaMetadataCompat.METADATA_KEY_YEAR, MediaMetadataCompat.METADATA_KEY_GENRE, MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER, MediaMetadataCompat.METADATA_KEY_NUM_TRACKS, MediaMetadataCompat.METADATA_KEY_DISC_NUMBER, MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST, MediaMetadataCompat.METADATA_KEY_ART, MediaMetadataCompat.METADATA_KEY_ART_URI, MediaMetadataCompat.METADATA_KEY_ALBUM_ART, MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, MediaMetadataCompat.METADATA_KEY_USER_RATING, MediaMetadataCompat.METADATA_KEY_RATING, MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE, MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE, MediaMetadataCompat.METADATA_KEY_DISPLAY_DESCRIPTION, MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON, MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI, MediaMetadataCompat.METADATA_KEY_MEDIA_ID, MediaMetadataCompat.METADATA_KEY_MEDIA_URI, MediaMetadataCompat.METADATA_KEY_BT_FOLDER_TYPE, "android.media.metadata.ADVERTISEMENT", MediaMetadataCompat.METADATA_KEY_DOWNLOAD_STATUS, MediaConstants.EXTRAS_KEY_MEDIA_TYPE_COMPAT};
        int i10 = com.google.common.collect.w1.f14132m;
        Object[] objArr = new Object[32];
        objArr[0] = MediaMetadataCompat.METADATA_KEY_TITLE;
        objArr[1] = MediaMetadataCompat.METADATA_KEY_ARTIST;
        objArr[2] = MediaMetadataCompat.METADATA_KEY_DURATION;
        objArr[3] = MediaMetadataCompat.METADATA_KEY_ALBUM;
        objArr[4] = MediaMetadataCompat.METADATA_KEY_AUTHOR;
        objArr[5] = MediaMetadataCompat.METADATA_KEY_WRITER;
        System.arraycopy(strArr, 0, objArr, 6, 26);
        KNOWN_METADATA_COMPAT_KEYS = com.google.common.collect.w1.k(32, objArr);
    }

    private LegacyConversions() {
    }

    public static com.google.common.collect.h1 convertBrowserItemListToMediaItemList(List<MediaBrowserCompat.MediaItem> list) {
        com.google.common.collect.a0.c(4, "initialCapacity");
        Object[] objArrCopyOf = new Object[4];
        int i10 = 0;
        int i11 = 0;
        while (i10 < list.size()) {
            MediaItem mediaItemConvertToMediaItem = convertToMediaItem(list.get(i10));
            mediaItemConvertToMediaItem.getClass();
            int i12 = i11 + 1;
            int iB = com.google.common.collect.b1.b(objArrCopyOf.length, i12);
            if (iB > objArrCopyOf.length) {
                objArrCopyOf = Arrays.copyOf(objArrCopyOf, iB);
            }
            objArrCopyOf[i11] = mediaItemConvertToMediaItem;
            i10++;
            i11 = i12;
        }
        return com.google.common.collect.h1.h(i11, objArrCopyOf);
    }

    public static CommandButton convertCustomBrowseActionToCommandButton(Bundle bundle) {
        String string = bundle.getString(androidx.media3.session.legacy.MediaConstants.EXTRAS_KEY_CUSTOM_BROWSER_ACTION_ID);
        if (string == null) {
            return null;
        }
        CommandButton.Builder sessionCommand = new CommandButton.Builder().setSessionCommand(new SessionCommand(string, Bundle.EMPTY));
        String string2 = bundle.getString(androidx.media3.session.legacy.MediaConstants.EXTRAS_KEY_CUSTOM_BROWSER_ACTION_LABEL);
        if (string2 != null) {
            sessionCommand.setDisplayName(string2);
        }
        String string3 = bundle.getString(androidx.media3.session.legacy.MediaConstants.EXTRAS_KEY_CUSTOM_BROWSER_ACTION_ICON_URI);
        if (string3 != null) {
            try {
                sessionCommand.setIconUri(Uri.parse(string3));
            } catch (Throwable th) {
                Log.e(TAG, "error parsing icon URI of legacy browser action ".concat(string), th);
            }
        }
        Bundle bundle2 = bundle.getBundle(androidx.media3.session.legacy.MediaConstants.EXTRAS_KEY_CUSTOM_BROWSER_ACTION_EXTRAS);
        if (bundle2 != null) {
            sessionCommand.setExtras(bundle2);
        }
        return sessionCommand.build();
    }

    public static MediaBrowserCompat.MediaItem convertToBrowserItem(MediaItem mediaItem, Bitmap bitmap) {
        MediaDescriptionCompat mediaDescriptionCompatConvertToMediaDescriptionCompat = convertToMediaDescriptionCompat(mediaItem, bitmap);
        MediaMetadata mediaMetadata = mediaItem.mediaMetadata;
        Boolean bool = mediaMetadata.isBrowsable;
        int i10 = (bool == null || !bool.booleanValue()) ? 0 : 1;
        Boolean bool2 = mediaMetadata.isPlayable;
        if (bool2 != null && bool2.booleanValue()) {
            i10 |= 2;
        }
        return new MediaBrowserCompat.MediaItem(mediaDescriptionCompatConvertToMediaDescriptionCompat, i10);
    }

    public static int convertToBufferedPercentage(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat, long j10) {
        return MediaUtils.calculateBufferedPercentage(convertToBufferedPositionMs(playbackStateCompat, mediaMetadataCompat, j10), convertToDurationMs(mediaMetadataCompat));
    }

    public static long convertToBufferedPositionMs(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat, long j10) {
        long bufferedPosition = playbackStateCompat == null ? 0L : playbackStateCompat.getBufferedPosition();
        long jConvertToCurrentPositionMs = convertToCurrentPositionMs(playbackStateCompat, mediaMetadataCompat, j10);
        long jConvertToDurationMs = convertToDurationMs(mediaMetadataCompat);
        return jConvertToDurationMs == C.TIME_UNSET ? Math.max(jConvertToCurrentPositionMs, bufferedPosition) : Util.constrainValue(bufferedPosition, jConvertToCurrentPositionMs, jConvertToDurationMs);
    }

    public static Bundle convertToBundle(CommandButton commandButton) {
        Bundle bundle = new Bundle();
        SessionCommand sessionCommand = commandButton.sessionCommand;
        if (sessionCommand != null) {
            bundle.putString(androidx.media3.session.legacy.MediaConstants.EXTRAS_KEY_CUSTOM_BROWSER_ACTION_ID, sessionCommand.customAction);
        }
        bundle.putString(androidx.media3.session.legacy.MediaConstants.EXTRAS_KEY_CUSTOM_BROWSER_ACTION_LABEL, commandButton.displayName.toString());
        Uri uri = commandButton.iconUri;
        if (uri != null) {
            bundle.putString(androidx.media3.session.legacy.MediaConstants.EXTRAS_KEY_CUSTOM_BROWSER_ACTION_ICON_URI, uri.toString());
        }
        if (!commandButton.extras.isEmpty()) {
            bundle.putBundle(androidx.media3.session.legacy.MediaConstants.EXTRAS_KEY_CUSTOM_BROWSER_ACTION_EXTRAS, commandButton.extras);
        }
        return bundle;
    }

    public static long convertToCurrentPositionMs(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat, long j10) {
        if (playbackStateCompat == null) {
            return 0L;
        }
        long currentPosition = playbackStateCompat.getState() == 3 ? getCurrentPosition(playbackStateCompat, j10) : playbackStateCompat.getPosition();
        long jConvertToDurationMs = convertToDurationMs(mediaMetadataCompat);
        return jConvertToDurationMs == C.TIME_UNSET ? Math.max(0L, currentPosition) : Util.constrainValue(currentPosition, 0L, jConvertToDurationMs);
    }

    public static DeviceInfo convertToDeviceInfo(MediaControllerCompat.PlaybackInfo playbackInfo) {
        if (playbackInfo == null) {
            return DeviceInfo.UNKNOWN;
        }
        return new DeviceInfo.Builder(playbackInfo.getPlaybackType() == 2 ? 1 : 0).setMaxVolume(playbackInfo.getMaxVolume()).setRoutingControllerId(playbackInfo.getVolumeControlId()).build();
    }

    public static int convertToDeviceVolume(MediaControllerCompat.PlaybackInfo playbackInfo) {
        if (playbackInfo == null) {
            return 0;
        }
        return playbackInfo.getCurrentVolume();
    }

    public static long convertToDurationMs(MediaMetadataCompat mediaMetadataCompat) {
        if (mediaMetadataCompat == null || !mediaMetadataCompat.containsKey(MediaMetadataCompat.METADATA_KEY_DURATION)) {
            return C.TIME_UNSET;
        }
        long j10 = mediaMetadataCompat.getLong(MediaMetadataCompat.METADATA_KEY_DURATION);
        return j10 <= 0 ? C.TIME_UNSET : j10;
    }

    private static long convertToExtraBtFolderType(int i10) {
        switch (i10) {
            case 0:
                return 0L;
            case 1:
                return 1L;
            case 2:
                return 2L;
            case 3:
                return 3L;
            case 4:
                return 4L;
            case 5:
                return 5L;
            case 6:
                return 6L;
            default:
                throw new IllegalArgumentException(a0.c.i(i10, "Unrecognized FolderType: "));
        }
    }

    private static int convertToFolderType(long j10) {
        if (j10 == 0) {
            return 0;
        }
        if (j10 == 1) {
            return 1;
        }
        if (j10 == 2) {
            return 2;
        }
        if (j10 == 3) {
            return 3;
        }
        if (j10 == 4) {
            return 4;
        }
        if (j10 == 5) {
            return 5;
        }
        return j10 == 6 ? 6 : 0;
    }

    public static boolean convertToIsDeviceMuted(MediaControllerCompat.PlaybackInfo playbackInfo) {
        return playbackInfo != null && playbackInfo.getCurrentVolume() == 0;
    }

    private static boolean convertToIsEnded(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat, long j10) {
        long jConvertToDurationMs = convertToDurationMs(mediaMetadataCompat);
        return jConvertToDurationMs != C.TIME_UNSET && convertToCurrentPositionMs(playbackStateCompat, mediaMetadataCompat, j10) >= jConvertToDurationMs;
    }

    public static boolean convertToIsPlaying(PlaybackStateCompat playbackStateCompat) {
        return playbackStateCompat != null && playbackStateCompat.getState() == 3;
    }

    public static boolean convertToIsPlayingAd(MediaMetadataCompat mediaMetadataCompat) {
        return (mediaMetadataCompat == null || mediaMetadataCompat.getLong("android.media.metadata.ADVERTISEMENT") == 0) ? false : true;
    }

    public static int convertToLegacyErrorCode(int i10) {
        if (i10 == -110) {
            return 8;
        }
        if (i10 == -109) {
            return 11;
        }
        if (i10 == -6) {
            return 2;
        }
        if (i10 == -2) {
            return 1;
        }
        if (i10 == 1) {
            return 10;
        }
        switch (i10) {
            case -107:
                return 9;
            case -106:
                return 7;
            case -105:
                return 6;
            case -104:
                return 5;
            case -103:
                return 4;
            case -102:
                return 3;
            default:
                return 0;
        }
    }

    public static MediaLibraryService.LibraryParams convertToLibraryParams(Context context, Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        try {
            bundle.setClassLoader(context.getClassLoader());
            int i10 = bundle.getInt(androidx.media3.session.legacy.MediaConstants.BROWSER_ROOT_HINTS_KEY_ROOT_CHILDREN_SUPPORTED_FLAGS, -1);
            if (i10 >= 0) {
                bundle.remove(androidx.media3.session.legacy.MediaConstants.BROWSER_ROOT_HINTS_KEY_ROOT_CHILDREN_SUPPORTED_FLAGS);
                boolean z = true;
                if (i10 != 1) {
                    z = false;
                }
                bundle.putBoolean(MediaConstants.EXTRA_KEY_ROOT_CHILDREN_BROWSABLE_ONLY, z);
            }
            return new MediaLibraryService.LibraryParams.Builder().setExtras(bundle).setRecent(bundle.getBoolean(MediaBrowserServiceCompat.BrowserRoot.EXTRA_RECENT)).setOffline(bundle.getBoolean(MediaBrowserServiceCompat.BrowserRoot.EXTRA_OFFLINE)).setSuggested(bundle.getBoolean(MediaBrowserServiceCompat.BrowserRoot.EXTRA_SUGGESTED)).build();
        } catch (Exception unused) {
            return new MediaLibraryService.LibraryParams.Builder().setExtras(bundle).build();
        }
    }

    public static com.google.common.collect.h1 convertToMediaButtonPreferences(PlaybackStateCompat playbackStateCompat, Player.Commands commands, Bundle bundle) {
        if (playbackStateCompat == null) {
            com.google.common.collect.f1 f1Var = com.google.common.collect.h1.f14020l;
            return o3.f14078o;
        }
        List<PlaybackStateCompat.CustomAction> customActions = playbackStateCompat.getCustomActions();
        com.google.common.collect.a0.c(4, "initialCapacity");
        Object[] objArrCopyOf = new Object[4];
        int i10 = 0;
        for (PlaybackStateCompat.CustomAction customAction : customActions) {
            String action = customAction.getAction();
            Bundle extras = customAction.getExtras();
            CommandButton.Builder enabled = new CommandButton.Builder(extras != null ? extras.getInt(MediaConstants.EXTRAS_KEY_COMMAND_BUTTON_ICON_COMPAT, 0) : 0, customAction.getIcon()).setSessionCommand(new SessionCommand(action, extras == null ? Bundle.EMPTY : extras)).setDisplayName(customAction.getName()).setEnabled(true);
            if (extras != null) {
                enabled.setExtras(extras);
            }
            String string = extras != null ? extras.getString(MediaConstants.EXTRAS_KEY_COMMAND_BUTTON_ICON_URI_COMPAT) : null;
            if (string != null) {
                Uri uri = Uri.parse(string);
                String scheme = uri.getScheme();
                if (Objects.equals(scheme, "content") || Objects.equals(scheme, "android.resource")) {
                    enabled.setIconUri(uri);
                }
            }
            CommandButton commandButtonBuild = enabled.build();
            commandButtonBuild.getClass();
            int i11 = i10 + 1;
            int iB = com.google.common.collect.b1.b(objArrCopyOf.length, i11);
            if (iB > objArrCopyOf.length) {
                objArrCopyOf = Arrays.copyOf(objArrCopyOf, iB);
            }
            objArrCopyOf[i10] = commandButtonBuild;
            i10 = i11;
        }
        return CommandButton.getMediaButtonPreferencesFromCustomLayout(com.google.common.collect.h1.h(i10, objArrCopyOf), commands, bundle);
    }

    public static MediaDescriptionCompat convertToMediaDescriptionCompat(MediaItem mediaItem, Bitmap bitmap) {
        CharSequence charSequence;
        CharSequence charSequence2;
        MediaDescriptionCompat.Builder mediaId = new MediaDescriptionCompat.Builder().setMediaId(mediaItem.mediaId.equals("") ? null : mediaItem.mediaId);
        MediaMetadata mediaMetadata = mediaItem.mediaMetadata;
        if (bitmap != null) {
            mediaId.setIconBitmap(bitmap);
        }
        Bundle bundle = mediaMetadata.extras;
        if (bundle != null) {
            bundle = new Bundle(bundle);
        }
        Integer num = mediaMetadata.folderType;
        boolean z = (num == null || num.intValue() == -1) ? false : true;
        boolean z5 = mediaMetadata.mediaType != null;
        if (z || z5) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            if (z) {
                Integer num2 = mediaMetadata.folderType;
                num2.getClass();
                bundle.putLong(MediaDescriptionCompat.EXTRA_BT_FOLDER_TYPE, convertToExtraBtFolderType(num2.intValue()));
            }
            if (z5) {
                mediaMetadata.mediaType.getClass();
                bundle.putLong(MediaConstants.EXTRAS_KEY_MEDIA_TYPE_COMPAT, r2.intValue());
            }
        }
        if (!mediaMetadata.supportedCommands.isEmpty()) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putStringArrayList(androidx.media3.session.legacy.MediaConstants.DESCRIPTION_EXTRAS_KEY_CUSTOM_BROWSER_ACTION_ID_LIST, new ArrayList<>(mediaMetadata.supportedCommands));
        }
        CharSequence charSequence3 = mediaMetadata.displayTitle;
        if (charSequence3 != null) {
            charSequence = mediaMetadata.subtitle;
            charSequence2 = mediaMetadata.description;
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putCharSequence("androidx.media3.mediadescriptioncompat.title", mediaMetadata.title);
        } else {
            CharSequence[] charSequenceArr = new CharSequence[3];
            int i10 = 0;
            int i11 = 0;
            while (i10 < 3) {
                String[] strArr = MediaMetadataCompat.PREFERRED_DESCRIPTION_ORDER;
                if (i11 >= strArr.length) {
                    break;
                }
                int i12 = i11 + 1;
                CharSequence text = getText(strArr[i11], mediaMetadata);
                if (!TextUtils.isEmpty(text)) {
                    charSequenceArr[i10] = text;
                    i10++;
                }
                i11 = i12;
            }
            charSequence3 = charSequenceArr[0];
            charSequence = charSequenceArr[1];
            charSequence2 = charSequenceArr[2];
        }
        return mediaId.setTitle(charSequence3).setSubtitle(charSequence).setDescription(charSequence2).setIconUri(mediaMetadata.artworkUri).setMediaUri(mediaItem.requestMetadata.mediaUri).setExtras(bundle).build();
    }

    public static MediaItem convertToMediaItem(MediaBrowserCompat.MediaItem mediaItem) {
        return convertToMediaItem(mediaItem.getDescription(), mediaItem.isBrowsable(), mediaItem.isPlayable());
    }

    public static List<MediaItem> convertToMediaItemList(Timeline timeline) {
        ArrayList arrayList = new ArrayList();
        Timeline.Window window = new Timeline.Window();
        for (int i10 = 0; i10 < timeline.getWindowCount(); i10++) {
            arrayList.add(timeline.getWindow(i10, window).mediaItem);
        }
        return arrayList;
    }

    public static MediaMetadata convertToMediaMetadata(CharSequence charSequence) {
        return charSequence == null ? MediaMetadata.EMPTY : new MediaMetadata.Builder().setTitle(charSequence).build();
    }

    public static MediaMetadataCompat convertToMediaMetadataCompat(MediaMetadata mediaMetadata, String str, Uri uri, long j10, Bitmap bitmap) {
        Long l10;
        MediaMetadataCompat.Builder builderPutString = new MediaMetadataCompat.Builder().putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, str);
        CharSequence charSequence = mediaMetadata.title;
        if (charSequence != null) {
            builderPutString.putText(MediaMetadataCompat.METADATA_KEY_TITLE, charSequence);
        }
        CharSequence charSequence2 = mediaMetadata.displayTitle;
        if (charSequence2 != null) {
            builderPutString.putText(MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE, charSequence2);
        }
        CharSequence charSequence3 = mediaMetadata.subtitle;
        if (charSequence3 != null) {
            builderPutString.putText(MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE, charSequence3);
        }
        CharSequence charSequence4 = mediaMetadata.description;
        if (charSequence4 != null) {
            builderPutString.putText(MediaMetadataCompat.METADATA_KEY_DISPLAY_DESCRIPTION, charSequence4);
        }
        CharSequence charSequence5 = mediaMetadata.artist;
        if (charSequence5 != null) {
            builderPutString.putText(MediaMetadataCompat.METADATA_KEY_ARTIST, charSequence5);
        }
        CharSequence charSequence6 = mediaMetadata.albumTitle;
        if (charSequence6 != null) {
            builderPutString.putText(MediaMetadataCompat.METADATA_KEY_ALBUM, charSequence6);
        }
        CharSequence charSequence7 = mediaMetadata.albumArtist;
        if (charSequence7 != null) {
            builderPutString.putText(MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST, charSequence7);
        }
        if (mediaMetadata.recordingYear != null) {
            builderPutString.putLong(MediaMetadataCompat.METADATA_KEY_YEAR, r0.intValue());
        }
        if (uri != null) {
            builderPutString.putString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI, uri.toString());
        }
        Uri uri2 = mediaMetadata.artworkUri;
        if (uri2 != null) {
            builderPutString.putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI, uri2.toString());
            builderPutString.putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, mediaMetadata.artworkUri.toString());
            builderPutString.putString(MediaMetadataCompat.METADATA_KEY_ART_URI, mediaMetadata.artworkUri.toString());
        }
        if (bitmap != null) {
            builderPutString.putBitmap(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON, bitmap);
            builderPutString.putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, bitmap);
        }
        Integer num = mediaMetadata.folderType;
        if (num != null && num.intValue() != -1) {
            builderPutString.putLong(MediaMetadataCompat.METADATA_KEY_BT_FOLDER_TYPE, convertToExtraBtFolderType(mediaMetadata.folderType.intValue()));
        }
        if (j10 == C.TIME_UNSET && (l10 = mediaMetadata.durationMs) != null) {
            j10 = l10.longValue();
        }
        if (j10 == C.TIME_UNSET) {
            j10 = -1;
        }
        builderPutString.putLong(MediaMetadataCompat.METADATA_KEY_DURATION, j10);
        RatingCompat ratingCompatConvertToRatingCompat = convertToRatingCompat(mediaMetadata.userRating);
        if (ratingCompatConvertToRatingCompat != null) {
            builderPutString.putRating(MediaMetadataCompat.METADATA_KEY_USER_RATING, ratingCompatConvertToRatingCompat);
        }
        RatingCompat ratingCompatConvertToRatingCompat2 = convertToRatingCompat(mediaMetadata.overallRating);
        if (ratingCompatConvertToRatingCompat2 != null) {
            builderPutString.putRating(MediaMetadataCompat.METADATA_KEY_RATING, ratingCompatConvertToRatingCompat2);
        }
        if (mediaMetadata.mediaType != null) {
            builderPutString.putLong(MediaConstants.EXTRAS_KEY_MEDIA_TYPE_COMPAT, r5.intValue());
        }
        Bundle bundle = mediaMetadata.extras;
        if (bundle != null) {
            for (String str2 : bundle.keySet()) {
                Object obj = mediaMetadata.extras.get(str2);
                if (obj == null || (obj instanceof CharSequence)) {
                    builderPutString.putText(str2, (CharSequence) obj);
                } else if ((obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long)) {
                    builderPutString.putLong(str2, ((Number) obj).longValue());
                }
            }
        }
        return builderPutString.build();
    }

    public static Timeline.Period convertToPeriod(int i10) {
        Timeline.Period period = new Timeline.Period();
        period.set(null, null, i10, C.TIME_UNSET, 0L, AdPlaybackState.NONE, true);
        return period;
    }

    public static boolean convertToPlayWhenReady(PlaybackStateCompat playbackStateCompat) {
        if (playbackStateCompat == null) {
            return false;
        }
        switch (playbackStateCompat.getState()) {
        }
        return false;
    }

    public static PlaybackException convertToPlaybackException(PlaybackStateCompat playbackStateCompat, Context context) {
        if (playbackStateCompat == null || playbackStateCompat.getState() != 7) {
            return null;
        }
        CharSequence errorMessage = playbackStateCompat.getErrorMessage();
        if (errorMessage == null) {
            errorMessage = getSessionErrorMessage(convertToSessionErrorCode(playbackStateCompat.getErrorCode()), context);
        }
        Bundle extras = playbackStateCompat.getExtras();
        String string = errorMessage != null ? errorMessage.toString() : null;
        int iConvertToPlaybackExceptionErrorCode = convertToPlaybackExceptionErrorCode(playbackStateCompat.getErrorCode());
        if (extras == null) {
            extras = Bundle.EMPTY;
        }
        return new PlaybackException(string, null, iConvertToPlaybackExceptionErrorCode, extras);
    }

    private static int convertToPlaybackExceptionErrorCode(int i10) {
        int iConvertToSessionErrorCode = convertToSessionErrorCode(i10);
        if (iConvertToSessionErrorCode == -5) {
            return 2000;
        }
        if (iConvertToSessionErrorCode != -1) {
            return iConvertToSessionErrorCode;
        }
        return 1000;
    }

    public static PlaybackParameters convertToPlaybackParameters(PlaybackStateCompat playbackStateCompat) {
        return playbackStateCompat == null ? PlaybackParameters.DEFAULT : new PlaybackParameters(playbackStateCompat.getPlaybackSpeed());
    }

    public static int convertToPlaybackState(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat, long j10) throws ConversionException {
        if (playbackStateCompat == null) {
            return 1;
        }
        boolean zConvertToIsEnded = convertToIsEnded(playbackStateCompat, mediaMetadataCompat, j10);
        switch (playbackStateCompat.getState()) {
            case 1:
                if (zConvertToIsEnded) {
                    return 4;
                }
            case 0:
            case 7:
            case 8:
                return 1;
            case 2:
                return zConvertToIsEnded ? 4 : 3;
            case 3:
                return 3;
            case 4:
            case 5:
            case 6:
            case 9:
            case 10:
            case 11:
                return 2;
            default:
                throw new ConversionException("Invalid state of PlaybackStateCompat: " + playbackStateCompat.getState());
        }
    }

    public static int convertToPlaybackStateCompatRepeatMode(int i10) {
        if (i10 == 0) {
            return 0;
        }
        int i11 = 1;
        if (i10 != 1) {
            i11 = 2;
            if (i10 != 2) {
                Log.w(TAG, "Unrecognized RepeatMode: " + i10 + " was converted to `PlaybackStateCompat.REPEAT_MODE_NONE`");
                return 0;
            }
        }
        return i11;
    }

    public static int convertToPlaybackStateCompatShuffleMode(boolean z) {
        return z ? 1 : 0;
    }

    public static int convertToPlaybackStateCompatState(Player player, boolean z) {
        if (player.getPlayerError() != null) {
            return 7;
        }
        int playbackState = player.getPlaybackState();
        if (playbackState == 1) {
            return 0;
        }
        if (playbackState == 2) {
            return z ? 2 : 6;
        }
        if (playbackState == 3) {
            return z ? 2 : 3;
        }
        if (playbackState == 4) {
            return 1;
        }
        throw new IllegalArgumentException(a0.c.i(playbackState, "Unrecognized State: "));
    }

    public static Player.Commands convertToPlayerCommands(PlaybackStateCompat playbackStateCompat, int i10, long j10, boolean z) {
        Player.Commands.Builder builder = new Player.Commands.Builder();
        long actions = playbackStateCompat == null ? 0L : playbackStateCompat.getActions();
        boolean zConvertToPlayWhenReady = convertToPlayWhenReady(playbackStateCompat);
        if ((hasAction(actions, 4L) && !zConvertToPlayWhenReady) || ((hasAction(actions, 2L) && zConvertToPlayWhenReady) || hasAction(actions, 512L))) {
            builder.add(1);
        }
        if (hasAction(actions, 16384L)) {
            builder.add(2);
        }
        if ((hasAction(actions, 32768L) && hasAction(actions, 1024L)) || ((hasAction(actions, 65536L) && hasAction(actions, 2048L)) || (hasAction(actions, 131072L) && hasAction(actions, 8192L)))) {
            builder.addAll(31, 2);
        }
        if (hasAction(actions, 8L)) {
            builder.add(11);
        }
        if (hasAction(actions, 64L)) {
            builder.add(12);
        }
        if (hasAction(actions, 256L)) {
            builder.addAll(5, 4);
        }
        if (hasAction(actions, 32L)) {
            builder.addAll(9, 8);
        }
        if (hasAction(actions, 16L)) {
            builder.addAll(7, 6);
        }
        if (hasAction(actions, 4194304L)) {
            builder.add(13);
        }
        if (hasAction(actions, 1L)) {
            builder.add(3);
        }
        if (i10 == 1) {
            builder.addAll(26, 34);
        } else if (i10 == 2) {
            builder.addAll(26, 34, 25, 33);
        }
        builder.addAll(23, 17, 18, 16, 21, 32);
        if ((j10 & 4) != 0) {
            builder.add(20);
            if (hasAction(actions, 4096L)) {
                builder.add(10);
            }
        }
        if (z) {
            if (hasAction(actions, 262144L)) {
                builder.add(15);
            }
            if (hasAction(actions, 2097152L)) {
                builder.add(14);
            }
        }
        return builder.build();
    }

    public static MediaSessionCompat.QueueItem convertToQueueItem(MediaItem mediaItem, int i10, Bitmap bitmap) {
        return new MediaSessionCompat.QueueItem(convertToMediaDescriptionCompat(mediaItem, bitmap), convertToQueueItemId(i10));
    }

    public static long convertToQueueItemId(int i10) {
        if (i10 == -1) {
            return -1L;
        }
        return i10;
    }

    public static Rating convertToRating(RatingCompat ratingCompat) {
        if (ratingCompat == null) {
            return null;
        }
        switch (ratingCompat.getRatingStyle()) {
            case 1:
                if (!ratingCompat.isRated()) {
                }
                break;
            case 2:
                if (!ratingCompat.isRated()) {
                }
                break;
            case 3:
                if (!ratingCompat.isRated()) {
                }
                break;
            case 4:
                if (!ratingCompat.isRated()) {
                }
                break;
            case 5:
                if (!ratingCompat.isRated()) {
                }
                break;
            case 6:
                if (!ratingCompat.isRated()) {
                }
                break;
        }
        return null;
    }

    public static RatingCompat convertToRatingCompat(Rating rating) {
        if (rating == null) {
            return null;
        }
        int ratingCompatStyle = getRatingCompatStyle(rating);
        if (!rating.isRated()) {
            return RatingCompat.newUnratedRating(ratingCompatStyle);
        }
        switch (ratingCompatStyle) {
            case 1:
                return RatingCompat.newHeartRating(((HeartRating) rating).isHeart());
            case 2:
                return RatingCompat.newThumbRating(((ThumbRating) rating).isThumbsUp());
            case 3:
            case 4:
            case 5:
                return RatingCompat.newStarRating(ratingCompatStyle, ((StarRating) rating).getStarRating());
            case 6:
                return RatingCompat.newPercentageRating(((PercentageRating) rating).getPercent());
            default:
                return null;
        }
    }

    public static int convertToRepeatMode(int i10) {
        if (i10 == -1 || i10 == 0) {
            return 0;
        }
        int i11 = 1;
        if (i10 != 1) {
            i11 = 2;
            if (i10 != 2 && i10 != 3) {
                Log.w(TAG, "Unrecognized PlaybackStateCompat.RepeatMode: " + i10 + " was converted to `Player.REPEAT_MODE_OFF`");
                return 0;
            }
        }
        return i11;
    }

    public static Bundle convertToRootHints(MediaLibraryService.LibraryParams libraryParams) {
        if (libraryParams == null) {
            return null;
        }
        Bundle bundle = new Bundle(libraryParams.extras);
        if (libraryParams.extras.containsKey(MediaConstants.EXTRA_KEY_ROOT_CHILDREN_BROWSABLE_ONLY)) {
            boolean z = libraryParams.extras.getBoolean(MediaConstants.EXTRA_KEY_ROOT_CHILDREN_BROWSABLE_ONLY, false);
            bundle.remove(MediaConstants.EXTRA_KEY_ROOT_CHILDREN_BROWSABLE_ONLY);
            bundle.putInt(androidx.media3.session.legacy.MediaConstants.BROWSER_ROOT_HINTS_KEY_ROOT_CHILDREN_SUPPORTED_FLAGS, z ? 1 : 3);
        }
        bundle.putBoolean(MediaBrowserServiceCompat.BrowserRoot.EXTRA_RECENT, libraryParams.isRecent);
        bundle.putBoolean(MediaBrowserServiceCompat.BrowserRoot.EXTRA_OFFLINE, libraryParams.isOffline);
        bundle.putBoolean(MediaBrowserServiceCompat.BrowserRoot.EXTRA_SUGGESTED, libraryParams.isSuggested);
        return bundle;
    }

    public static SessionCommands convertToSessionCommands(PlaybackStateCompat playbackStateCompat, boolean z) {
        SessionCommands.Builder builder = new SessionCommands.Builder();
        builder.addAllSessionCommands();
        if (!z) {
            builder.remove(SessionCommand.COMMAND_CODE_SESSION_SET_RATING);
        }
        if (playbackStateCompat != null) {
            for (PlaybackStateCompat.CustomAction customAction : playbackStateCompat.getCustomActions()) {
                String action = customAction.getAction();
                Bundle extras = customAction.getExtras();
                if (extras == null) {
                    extras = Bundle.EMPTY;
                }
                builder.add(new SessionCommand(action, extras));
            }
        }
        return builder.build();
    }

    public static SessionError convertToSessionError(PlaybackStateCompat playbackStateCompat, Context context) {
        if (playbackStateCompat == null) {
            return null;
        }
        return convertToSessionError(playbackStateCompat.getState(), playbackStateCompat.getErrorCode(), playbackStateCompat.getErrorMessage(), playbackStateCompat.getExtras(), context);
    }

    private static int convertToSessionErrorCode(int i10) {
        switch (i10) {
            case 1:
                return -2;
            case 2:
                return -6;
            case 3:
                return -102;
            case 4:
                return -103;
            case 5:
                return -104;
            case 6:
                return -105;
            case 7:
                return -106;
            case 8:
                return -110;
            case 9:
                return -107;
            case 10:
                return 1;
            case 11:
                return -109;
            default:
                return -1;
        }
    }

    public static boolean convertToShuffleModeEnabled(int i10) {
        if (i10 == -1 || i10 == 0) {
            return false;
        }
        if (i10 == 1 || i10 == 2) {
            return true;
        }
        throw new IllegalArgumentException(a0.c.i(i10, "Unrecognized ShuffleMode: "));
    }

    public static long convertToTotalBufferedDurationMs(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat, long j10) {
        return convertToBufferedPositionMs(playbackStateCompat, mediaMetadataCompat, j10) - convertToCurrentPositionMs(playbackStateCompat, mediaMetadataCompat, j10);
    }

    public static Timeline.Window convertToWindow(MediaItem mediaItem, int i10) {
        Timeline.Window window = new Timeline.Window();
        window.set(0, mediaItem, null, 0L, 0L, 0L, true, false, null, 0L, C.TIME_UNSET, i10, i10, 0L);
        return window;
    }

    public static int extractMaxCommandsForMediaItemFromRootHints(Bundle bundle) {
        return Math.max(0, bundle.getInt(androidx.media3.session.legacy.MediaConstants.BROWSER_ROOT_HINTS_KEY_CUSTOM_BROWSER_ACTION_LIMIT, 0));
    }

    private static long getCurrentPosition(PlaybackStateCompat playbackStateCompat, long j10) {
        return playbackStateCompat.getCurrentPosition(j10 == C.TIME_UNSET ? null : Long.valueOf(j10));
    }

    public static <T> T getFutureResult(Future<T> future, long j10) throws ExecutionException, TimeoutException {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        boolean z = false;
        long j11 = j10;
        while (true) {
            try {
                try {
                    return future.get(j11, TimeUnit.MILLISECONDS);
                } catch (InterruptedException unused) {
                    z = true;
                    long jElapsedRealtime2 = SystemClock.elapsedRealtime() - jElapsedRealtime;
                    if (jElapsedRealtime2 >= j10) {
                        throw new TimeoutException();
                    }
                    j11 = j10 - jElapsedRealtime2;
                }
            } finally {
                if (z) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static int getRatingCompatStyle(Rating rating) {
        if (rating instanceof HeartRating) {
            return 1;
        }
        if (rating instanceof ThumbRating) {
            return 2;
        }
        if (!(rating instanceof StarRating)) {
            return rating instanceof PercentageRating ? 6 : 0;
        }
        int maxStars = ((StarRating) rating).getMaxStars();
        int i10 = 3;
        if (maxStars != 3) {
            i10 = 4;
            if (maxStars != 4) {
                i10 = 5;
                if (maxStars != 5) {
                    return 0;
                }
            }
        }
        return i10;
    }

    private static String getSessionErrorMessage(int i10, Context context) {
        if (i10 == -100) {
            return context.getString(R.string.error_message_disconnected);
        }
        if (i10 == 1) {
            return context.getString(R.string.error_message_info_cancelled);
        }
        if (i10 == -6) {
            return context.getString(R.string.error_message_not_supported);
        }
        if (i10 == -5) {
            return context.getString(R.string.error_message_io);
        }
        if (i10 == -4) {
            return context.getString(R.string.error_message_permission_denied);
        }
        if (i10 == -3) {
            return context.getString(R.string.error_message_bad_value);
        }
        if (i10 == -2) {
            return context.getString(R.string.error_message_invalid_state);
        }
        switch (i10) {
            case -110:
                return context.getString(R.string.error_message_content_already_playing);
            case -109:
                return context.getString(R.string.error_message_end_of_playlist);
            case -108:
                return context.getString(R.string.error_message_setup_required);
            case -107:
                return context.getString(R.string.error_message_skip_limit_reached);
            case -106:
                return context.getString(R.string.error_message_not_available_in_region);
            case -105:
                return context.getString(R.string.error_message_parental_control_restricted);
            case -104:
                return context.getString(R.string.error_message_concurrent_stream_limit);
            case -103:
                return context.getString(R.string.error_message_premium_account_required);
            case -102:
                return context.getString(R.string.error_message_authentication_expired);
            default:
                return context.getString(R.string.error_message_fallback);
        }
    }

    private static CharSequence getText(String str, MediaMetadata mediaMetadata) {
        str.getClass();
        switch (str) {
            case "android.media.metadata.ARTIST":
                return mediaMetadata.artist;
            case "android.media.metadata.WRITER":
                return mediaMetadata.writer;
            case "android.media.metadata.DISPLAY_SUBTITLE":
                return mediaMetadata.subtitle;
            case "android.media.metadata.COMPOSER":
                return mediaMetadata.composer;
            case "android.media.metadata.ALBUM":
                return mediaMetadata.albumTitle;
            case "android.media.metadata.TITLE":
                return mediaMetadata.title;
            case "android.media.metadata.ALBUM_ARTIST":
                return mediaMetadata.albumArtist;
            default:
                return null;
        }
    }

    private static boolean hasAction(long j10, long j11) {
        return (j10 & j11) != 0;
    }

    public static int convertToLegacyErrorCode(PlaybackException playbackException) {
        return convertToLegacyErrorCode(playbackException.errorCode);
    }

    public static MediaItem convertToMediaItem(MediaSessionCompat.QueueItem queueItem) {
        return convertToMediaItem(queueItem.getDescription());
    }

    public static MediaItem convertToMediaItem(MediaMetadataCompat mediaMetadataCompat, int i10) {
        return convertToMediaItem(mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID), mediaMetadataCompat, i10);
    }

    public static MediaMetadata convertToMediaMetadata(MediaDescriptionCompat mediaDescriptionCompat, int i10) {
        return convertToMediaMetadata(mediaDescriptionCompat, i10, false, true);
    }

    private static MediaMetadata convertToMediaMetadata(MediaDescriptionCompat mediaDescriptionCompat, int i10, boolean z, boolean z5) {
        if (mediaDescriptionCompat == null) {
            return MediaMetadata.EMPTY;
        }
        MediaMetadata.Builder builder = new MediaMetadata.Builder();
        builder.setSubtitle(mediaDescriptionCompat.getSubtitle()).setDescription(mediaDescriptionCompat.getDescription()).setArtworkUri(mediaDescriptionCompat.getIconUri()).setUserRating(convertToRating(RatingCompat.newUnratedRating(i10)));
        byte[] iconBitmapData = mediaDescriptionCompat.getIconBitmapData();
        if (iconBitmapData != null) {
            builder.setArtworkData(iconBitmapData, 3);
        }
        Bundle extras = mediaDescriptionCompat.getExtras();
        Bundle bundle = extras == null ? null : new Bundle(extras);
        if (bundle != null && bundle.containsKey(MediaDescriptionCompat.EXTRA_BT_FOLDER_TYPE)) {
            builder.setFolderType(Integer.valueOf(convertToFolderType(bundle.getLong(MediaDescriptionCompat.EXTRA_BT_FOLDER_TYPE))));
            bundle.remove(MediaDescriptionCompat.EXTRA_BT_FOLDER_TYPE);
        }
        builder.setIsBrowsable(Boolean.valueOf(z));
        if (bundle != null && bundle.containsKey(MediaConstants.EXTRAS_KEY_MEDIA_TYPE_COMPAT)) {
            builder.setMediaType(Integer.valueOf((int) bundle.getLong(MediaConstants.EXTRAS_KEY_MEDIA_TYPE_COMPAT)));
            bundle.remove(MediaConstants.EXTRAS_KEY_MEDIA_TYPE_COMPAT);
        }
        if (bundle != null && bundle.containsKey(androidx.media3.session.legacy.MediaConstants.DESCRIPTION_EXTRAS_KEY_CUSTOM_BROWSER_ACTION_ID_LIST)) {
            ArrayList<String> stringArrayList = bundle.getStringArrayList(androidx.media3.session.legacy.MediaConstants.DESCRIPTION_EXTRAS_KEY_CUSTOM_BROWSER_ACTION_ID_LIST);
            stringArrayList.getClass();
            builder.setSupportedCommands(com.google.common.collect.h1.n(stringArrayList));
        }
        if (bundle != null && bundle.containsKey("androidx.media3.mediadescriptioncompat.title")) {
            builder.setTitle(bundle.getCharSequence("androidx.media3.mediadescriptioncompat.title"));
            builder.setDisplayTitle(mediaDescriptionCompat.getTitle());
            bundle.remove("androidx.media3.mediadescriptioncompat.title");
        } else {
            builder.setTitle(mediaDescriptionCompat.getTitle());
        }
        if (bundle != null && !bundle.isEmpty()) {
            builder.setExtras(bundle);
        }
        builder.setIsPlayable(Boolean.valueOf(z5));
        return builder.build();
    }

    public static MediaItem convertToMediaItem(String str, MediaMetadataCompat mediaMetadataCompat, int i10) {
        MediaItem.Builder builder = new MediaItem.Builder();
        if (str != null) {
            builder.setMediaId(str);
        }
        String string = mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_URI);
        if (string != null) {
            builder.setRequestMetadata(new MediaItem.RequestMetadata.Builder().setMediaUri(Uri.parse(string)).build());
        }
        builder.setMediaMetadata(convertToMediaMetadata(mediaMetadataCompat, i10));
        return builder.build();
    }

    public static SessionError convertToSessionError(int i10, int i11, CharSequence charSequence, Bundle bundle, Context context) {
        String sessionErrorMessage;
        if (i10 == 7 || i11 == 0) {
            return null;
        }
        int iConvertToSessionErrorCode = convertToSessionErrorCode(i11);
        if (charSequence != null) {
            sessionErrorMessage = charSequence.toString();
        } else {
            sessionErrorMessage = getSessionErrorMessage(iConvertToSessionErrorCode, context);
        }
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        return new SessionError(iConvertToSessionErrorCode, sessionErrorMessage, bundle);
    }

    private static MediaItem convertToMediaItem(MediaDescriptionCompat mediaDescriptionCompat, boolean z, boolean z5) {
        String mediaId = mediaDescriptionCompat.getMediaId();
        MediaItem.Builder builder = new MediaItem.Builder();
        if (mediaId == null) {
            mediaId = "";
        }
        return builder.setMediaId(mediaId).setRequestMetadata(new MediaItem.RequestMetadata.Builder().setMediaUri(mediaDescriptionCompat.getMediaUri()).build()).setMediaMetadata(convertToMediaMetadata(mediaDescriptionCompat, 0, z, z5)).build();
    }

    public static MediaItem convertToMediaItem(MediaDescriptionCompat mediaDescriptionCompat) {
        mediaDescriptionCompat.getClass();
        return convertToMediaItem(mediaDescriptionCompat, false, true);
    }

    public static MediaMetadata convertToMediaMetadata(MediaMetadataCompat mediaMetadataCompat, int i10) {
        CharSequence text;
        CharSequence text2;
        if (mediaMetadataCompat == null) {
            return MediaMetadata.EMPTY;
        }
        MediaMetadata.Builder builder = new MediaMetadata.Builder();
        CharSequence text3 = mediaMetadataCompat.getText(MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE);
        if (text3 != null) {
            text2 = mediaMetadataCompat.getText(MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE);
            text = mediaMetadataCompat.getText(MediaMetadataCompat.METADATA_KEY_DISPLAY_DESCRIPTION);
        } else {
            CharSequence[] charSequenceArr = new CharSequence[3];
            int i11 = 0;
            int i12 = 0;
            while (i11 < 3) {
                String[] strArr = MediaMetadataCompat.PREFERRED_DESCRIPTION_ORDER;
                if (i12 >= strArr.length) {
                    break;
                }
                int i13 = i12 + 1;
                CharSequence text4 = mediaMetadataCompat.getText(strArr[i12]);
                if (!TextUtils.isEmpty(text4)) {
                    charSequenceArr[i11] = text4;
                    i11++;
                }
                i12 = i13;
            }
            CharSequence charSequence = charSequenceArr[0];
            CharSequence charSequence2 = charSequenceArr[1];
            text = charSequenceArr[2];
            text3 = charSequence;
            text2 = charSequence2;
        }
        CharSequence text5 = mediaMetadataCompat.getText(MediaMetadataCompat.METADATA_KEY_TITLE);
        if (text5 == null) {
            text5 = text3;
        }
        builder.setTitle(text5).setDisplayTitle(text3).setSubtitle(text2).setDescription(text).setArtist(mediaMetadataCompat.getText(MediaMetadataCompat.METADATA_KEY_ARTIST)).setAlbumTitle(mediaMetadataCompat.getText(MediaMetadataCompat.METADATA_KEY_ALBUM)).setAlbumArtist(mediaMetadataCompat.getText(MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST)).setOverallRating(convertToRating(mediaMetadataCompat.getRating(MediaMetadataCompat.METADATA_KEY_RATING)));
        if (mediaMetadataCompat.containsKey(MediaMetadataCompat.METADATA_KEY_DURATION)) {
            long j10 = mediaMetadataCompat.getLong(MediaMetadataCompat.METADATA_KEY_DURATION);
            if (j10 >= 0) {
                builder.setDurationMs(Long.valueOf(j10));
            }
        }
        Rating ratingConvertToRating = convertToRating(mediaMetadataCompat.getRating(MediaMetadataCompat.METADATA_KEY_USER_RATING));
        if (ratingConvertToRating != null) {
            builder.setUserRating(ratingConvertToRating);
        } else {
            builder.setUserRating(convertToRating(RatingCompat.newUnratedRating(i10)));
        }
        if (mediaMetadataCompat.containsKey(MediaMetadataCompat.METADATA_KEY_YEAR)) {
            builder.setRecordingYear(Integer.valueOf((int) mediaMetadataCompat.getLong(MediaMetadataCompat.METADATA_KEY_YEAR)));
        }
        Uri mostRelevantArtworkUri = mediaMetadataCompat.getMostRelevantArtworkUri();
        if (mostRelevantArtworkUri != null) {
            builder.setArtworkUri(mostRelevantArtworkUri);
        }
        byte[] mostRelevantArtworkBitmapData = mediaMetadataCompat.getMostRelevantArtworkBitmapData();
        if (mostRelevantArtworkBitmapData != null) {
            builder.setArtworkData(mostRelevantArtworkBitmapData, 3);
        }
        boolean zContainsKey = mediaMetadataCompat.containsKey(MediaMetadataCompat.METADATA_KEY_BT_FOLDER_TYPE);
        builder.setIsBrowsable(Boolean.valueOf(zContainsKey));
        if (zContainsKey) {
            builder.setFolderType(Integer.valueOf(convertToFolderType(mediaMetadataCompat.getLong(MediaMetadataCompat.METADATA_KEY_BT_FOLDER_TYPE))));
        }
        if (mediaMetadataCompat.containsKey(MediaConstants.EXTRAS_KEY_MEDIA_TYPE_COMPAT)) {
            builder.setMediaType(Integer.valueOf((int) mediaMetadataCompat.getLong(MediaConstants.EXTRAS_KEY_MEDIA_TYPE_COMPAT)));
        }
        builder.setIsPlayable(Boolean.TRUE);
        Bundle bundle = mediaMetadataCompat.getBundle();
        o4 it = KNOWN_METADATA_COMPAT_KEYS.iterator();
        while (it.hasNext()) {
            bundle.remove((String) it.next());
        }
        if (!bundle.isEmpty()) {
            builder.setExtras(bundle);
        }
        return builder.build();
    }
}
