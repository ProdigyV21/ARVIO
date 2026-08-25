package androidx.tvprovider.media.tv;

import android.content.ContentValues;
import android.os.Build;
import androidx.media3.exoplayer.offline.DownloadService;
import io.ktor.http.LinkHeader;

/* JADX INFO: loaded from: classes3.dex */
public abstract class g extends i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String[] f6606c = (String[]) t7.a.j(i.f6608b, new String[]{"internal_provider_id", "preview_video_uri", "last_playback_position_millis", "duration_millis", "intent_uri", "transient", LinkHeader.Parameters.Type, "poster_art_aspect_ratio", "poster_thumbnail_aspect_ratio", "logo_uri", "availability", "starting_price", "offer_price", "release_date", "item_count", "live", "interaction_type", "interaction_count", "author", "browsable", DownloadService.KEY_CONTENT_ID, "logo_content_description", "genre", "start_time_utc_millis", "end_time_utc_millis", "preview_audio_uri", "tv_series_item_type"});

    public ContentValues a() {
        ContentValues contentValues = new ContentValues(this.f6609a);
        int i10 = Build.VERSION.SDK_INT;
        if (i10 < 24) {
            contentValues.remove("season_title");
        }
        if (i10 < 26) {
            contentValues.remove("review_rating_style");
            contentValues.remove("review_rating");
        }
        if (i10 < 26) {
            contentValues.remove("internal_provider_id");
            contentValues.remove("preview_video_uri");
            contentValues.remove("last_playback_position_millis");
            contentValues.remove("duration_millis");
            contentValues.remove("intent_uri");
            contentValues.remove("transient");
            contentValues.remove(LinkHeader.Parameters.Type);
            contentValues.remove("poster_art_aspect_ratio");
            contentValues.remove("poster_thumbnail_aspect_ratio");
            contentValues.remove("logo_uri");
            contentValues.remove("availability");
            contentValues.remove("starting_price");
            contentValues.remove("offer_price");
            contentValues.remove("release_date");
            contentValues.remove("item_count");
            contentValues.remove("live");
            contentValues.remove("interaction_count");
            contentValues.remove("author");
            contentValues.remove(DownloadService.KEY_CONTENT_ID);
            contentValues.remove("logo_content_description");
            contentValues.remove("genre");
            contentValues.remove("start_time_utc_millis");
            contentValues.remove("end_time_utc_millis");
            contentValues.remove("preview_audio_uri");
            contentValues.remove("tv_series_item_type");
        }
        contentValues.remove("browsable");
        return contentValues;
    }
}
