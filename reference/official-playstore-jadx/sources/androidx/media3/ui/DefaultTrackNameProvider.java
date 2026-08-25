package androidx.media3.ui;

import android.content.res.Resources;
import android.text.TextUtils;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Util;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class DefaultTrackNameProvider implements TrackNameProvider {
    private final Resources resources;

    public DefaultTrackNameProvider(Resources resources) {
        resources.getClass();
        this.resources = resources;
    }

    private String buildAudioChannelString(Format format) {
        int i10 = format.channelCount;
        return (i10 == -1 || i10 < 1) ? "" : i10 != 1 ? i10 != 2 ? (i10 == 6 || i10 == 7) ? this.resources.getString(R.string.exo_track_surround_5_point_1) : i10 != 8 ? this.resources.getString(R.string.exo_track_surround) : this.resources.getString(R.string.exo_track_surround_7_point_1) : this.resources.getString(R.string.exo_track_stereo) : this.resources.getString(R.string.exo_track_mono);
    }

    private String buildBitrateString(Format format) {
        int i10 = format.bitrate;
        return i10 == -1 ? "" : this.resources.getString(R.string.exo_track_bitrate, Float.valueOf(i10 / 1000000.0f));
    }

    private String buildLabelString(Format format) {
        return TextUtils.isEmpty(format.label) ? "" : format.label;
    }

    private String buildLanguageOrLabelString(Format format) {
        String strJoinWithSeparator = joinWithSeparator(buildLanguageString(format), buildRoleString(format));
        return TextUtils.isEmpty(strJoinWithSeparator) ? buildLabelString(format) : strJoinWithSeparator;
    }

    private String buildLanguageString(Format format) {
        String str = format.language;
        if (TextUtils.isEmpty(str) || C.LANGUAGE_UNDETERMINED.equals(str)) {
            return "";
        }
        Locale localeForLanguageTag = Locale.forLanguageTag(str);
        Locale defaultDisplayLocale = Util.getDefaultDisplayLocale();
        String displayName = localeForLanguageTag.getDisplayName(defaultDisplayLocale);
        if (TextUtils.isEmpty(displayName)) {
            return "";
        }
        try {
            int iOffsetByCodePoints = displayName.offsetByCodePoints(0, 1);
            return displayName.substring(0, iOffsetByCodePoints).toUpperCase(defaultDisplayLocale) + displayName.substring(iOffsetByCodePoints);
        } catch (IndexOutOfBoundsException unused) {
            return displayName;
        }
    }

    private String buildResolutionString(Format format) {
        int i10 = format.width;
        int i11 = format.height;
        return (i10 == -1 || i11 == -1) ? "" : this.resources.getString(R.string.exo_track_resolution, Integer.valueOf(i10), Integer.valueOf(i11));
    }

    private String buildRoleString(Format format) {
        String string = (format.roleFlags & 2) != 0 ? this.resources.getString(R.string.exo_track_role_alternate) : "";
        if ((format.roleFlags & 4) != 0) {
            string = joinWithSeparator(string, this.resources.getString(R.string.exo_track_role_supplementary));
        }
        if ((format.roleFlags & 8) != 0) {
            string = joinWithSeparator(string, this.resources.getString(R.string.exo_track_role_commentary));
        }
        return (format.roleFlags & 1088) != 0 ? joinWithSeparator(string, this.resources.getString(R.string.exo_track_role_closed_captions)) : string;
    }

    private static int inferPrimaryTrackType(Format format) {
        int trackType = MimeTypes.getTrackType(format.sampleMimeType);
        if (trackType != -1) {
            return trackType;
        }
        if (MimeTypes.getVideoMediaMimeType(format.codecs) != null) {
            return 2;
        }
        if (MimeTypes.getAudioMediaMimeType(format.codecs) != null) {
            return 1;
        }
        if (format.width == -1 && format.height == -1) {
            return (format.channelCount == -1 && format.sampleRate == -1) ? -1 : 1;
        }
        return 2;
    }

    private String joinWithSeparator(String... strArr) {
        String string = "";
        for (String str : strArr) {
            if (!str.isEmpty()) {
                string = TextUtils.isEmpty(string) ? str : this.resources.getString(R.string.exo_item_list, string, str);
            }
        }
        return string;
    }

    @Override // androidx.media3.ui.TrackNameProvider
    public String getTrackName(Format format) {
        int iInferPrimaryTrackType = inferPrimaryTrackType(format);
        String strJoinWithSeparator = iInferPrimaryTrackType == 2 ? joinWithSeparator(buildRoleString(format), buildResolutionString(format), buildBitrateString(format)) : iInferPrimaryTrackType == 1 ? joinWithSeparator(buildLanguageOrLabelString(format), buildAudioChannelString(format), buildBitrateString(format)) : buildLanguageOrLabelString(format);
        if (!strJoinWithSeparator.isEmpty()) {
            return strJoinWithSeparator;
        }
        String str = format.language;
        return (str == null || str.trim().isEmpty()) ? this.resources.getString(R.string.exo_track_unknown) : this.resources.getString(R.string.exo_track_unknown_name, str);
    }
}
