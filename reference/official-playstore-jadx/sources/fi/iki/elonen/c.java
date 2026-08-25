package fi.iki.elonen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.helper.HttpConnection;

/* JADX INFO: loaded from: classes4.dex */
public final class c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Pattern f15405e = Pattern.compile("[ |\t]*([^/^ ^;^,]+/[^ ^;^,]+)", 2);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Pattern f15406f = Pattern.compile("[ |\t]*(charset)[ |\t]*=[ |\t]*['|\"]?([^\"^'^;^,]*)['|\"]?", 2);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Pattern f15407g = Pattern.compile("[ |\t]*(boundary)[ |\t]*=[ |\t]*['|\"]?([^\"^'^;^,]*)['|\"]?", 2);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f15408a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f15409b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f15410c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f15411d;

    public c(String str) {
        this.f15408a = str;
        if (str != null) {
            Matcher matcher = f15405e.matcher(str);
            this.f15409b = matcher.find() ? matcher.group(1) : "";
            Matcher matcher2 = f15406f.matcher(str);
            this.f15410c = matcher2.find() ? matcher2.group(2) : null;
        } else {
            this.f15409b = "";
            this.f15410c = "UTF-8";
        }
        if (!HttpConnection.MULTIPART_FORM_DATA.equalsIgnoreCase(this.f15409b)) {
            this.f15411d = null;
        } else {
            Matcher matcher3 = f15407g.matcher(str);
            this.f15411d = matcher3.find() ? matcher3.group(2) : null;
        }
    }

    public final String a() {
        String str = this.f15410c;
        return str == null ? "US-ASCII" : str;
    }
}
