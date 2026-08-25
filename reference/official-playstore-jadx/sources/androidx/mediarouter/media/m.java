package androidx.mediarouter.media;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/* JADX INFO: loaded from: classes3.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Bundle f4440a;

    public m(Bundle bundle) {
        this.f4440a = bundle;
    }

    public final HashSet a() {
        Bundle bundle = this.f4440a;
        return !bundle.containsKey("allowedPackages") ? new HashSet() : new HashSet(bundle.getStringArrayList("allowedPackages"));
    }

    public final ArrayList b() {
        Bundle bundle = this.f4440a;
        return !bundle.containsKey("controlFilters") ? new ArrayList() : new ArrayList(bundle.getParcelableArrayList("controlFilters"));
    }

    public final ArrayList c() {
        Bundle bundle = this.f4440a;
        return !bundle.containsKey("groupMemberIds") ? new ArrayList() : new ArrayList(bundle.getStringArrayList("groupMemberIds"));
    }

    public final String d() {
        return this.f4440a.getString(TtmlNode.ATTR_ID);
    }

    public final boolean e() {
        return (TextUtils.isEmpty(d()) || TextUtils.isEmpty(this.f4440a.getString(ContentDisposition.Parameters.Name)) || b().contains(null)) ? false : true;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("MediaRouteDescriptor{ id=");
        sb2.append(d());
        sb2.append(", groupMemberIds=");
        sb2.append(c());
        sb2.append(", name=");
        Bundle bundle = this.f4440a;
        sb2.append(bundle.getString(ContentDisposition.Parameters.Name));
        sb2.append(", description=");
        sb2.append(bundle.getString("status"));
        sb2.append(", iconUri=");
        String string = bundle.getString("iconUri");
        sb2.append(string == null ? null : Uri.parse(string));
        sb2.append(", isEnabled=");
        sb2.append(bundle.getBoolean("enabled", true));
        sb2.append(", isSystemRoute=");
        sb2.append(bundle.getBoolean("isSystemRoute", false));
        sb2.append(", connectionState=");
        sb2.append(bundle.getInt("connectionState", 0));
        sb2.append(", controlFilters=");
        sb2.append(Arrays.toString(b().toArray()));
        sb2.append(", playbackType=");
        sb2.append(bundle.getInt("playbackType", 1));
        sb2.append(", playbackStream=");
        sb2.append(bundle.getInt("playbackStream", -1));
        sb2.append(", deviceType=");
        sb2.append(bundle.getInt("deviceType"));
        sb2.append(", volume=");
        sb2.append(bundle.getInt("volume"));
        sb2.append(", volumeMax=");
        sb2.append(bundle.getInt("volumeMax"));
        sb2.append(", volumeHandling=");
        sb2.append(bundle.getInt("volumeHandling", 0));
        sb2.append(", presentationDisplayId=");
        sb2.append(bundle.getInt("presentationDisplayId", -1));
        sb2.append(", extras=");
        sb2.append(bundle.getBundle("extras"));
        sb2.append(", isValid=");
        sb2.append(e());
        sb2.append(", minClientVersion=");
        sb2.append(bundle.getInt("minClientVersion", 1));
        sb2.append(", maxClientVersion=");
        sb2.append(bundle.getInt("maxClientVersion", Integer.MAX_VALUE));
        sb2.append(", isVisibilityPublic=");
        sb2.append(bundle.getBoolean("isVisibilityPublic", true));
        sb2.append(", allowedPackages=");
        sb2.append(Arrays.toString(a().toArray()));
        sb2.append(" }");
        return sb2.toString();
    }
}
