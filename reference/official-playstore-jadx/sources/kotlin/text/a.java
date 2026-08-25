package kotlin.text;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes5.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Charset f19924a = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Charset f19925b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Charset f19926c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile Charset f19927d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile Charset f19928e;

    static {
        Charset.forName("UTF-16");
        Charset.forName("UTF-16BE");
        Charset.forName("UTF-16LE");
        f19925b = Charset.forName("US-ASCII");
        f19926c = Charset.forName("ISO-8859-1");
    }
}
