package androidx.tvprovider.media.tv;

import android.os.Build;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.LinkHeader;

/* JADX INFO: loaded from: classes3.dex */
public final class j {
    static {
        String[] strArr = {"_id", MediaTrack.ROLE_DESCRIPTION, "display_name", "display_number", "input_id", "internal_provider_data", "network_affiliation", "original_network_id", "package_name", "searchable", "service_id", "service_type", "transport_stream_id", LinkHeader.Parameters.Type, "video_format", "browsable", "locked"};
        String[] strArr2 = {"app_link_color", "app_link_icon_uri", "app_link_intent_uri", "app_link_poster_art_uri", "app_link_text", "internal_provider_flag1", "internal_provider_flag2", "internal_provider_flag3", "internal_provider_flag4"};
        String[] strArr3 = {"internal_provider_id", "transient", "system_approved", "configuration_display_order", "system_channel_key"};
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 30) {
        } else if (i10 >= 26) {
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof j) {
            throw null;
        }
        return false;
    }

    public final int hashCode() {
        throw null;
    }

    public final String toString() {
        throw null;
    }
}
