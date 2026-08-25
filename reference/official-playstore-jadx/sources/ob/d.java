package ob;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a[] f20892a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Map f20893b;

    static {
        a aVar = new a("", a.f20870i);
        xb.m mVar = a.f20867f;
        a aVar2 = new a("GET", mVar);
        a aVar3 = new a(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST, mVar);
        xb.m mVar2 = a.f20868g;
        a aVar4 = new a(DomExceptionUtils.SEPARATOR, mVar2);
        a aVar5 = new a("/index.html", mVar2);
        xb.m mVar3 = a.f20869h;
        a aVar6 = new a("http", mVar3);
        a aVar7 = new a("https", mVar3);
        xb.m mVar4 = a.f20866e;
        a[] aVarArr = {aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, new a("200", mVar4), new a("204", mVar4), new a("206", mVar4), new a("304", mVar4), new a("400", mVar4), new a("404", mVar4), new a("500", mVar4), new a("accept-charset", ""), new a("accept-encoding", "gzip, deflate"), new a("accept-language", ""), new a("accept-ranges", ""), new a("accept", ""), new a("access-control-allow-origin", ""), new a("age", ""), new a("allow", ""), new a("authorization", ""), new a("cache-control", ""), new a("content-disposition", ""), new a("content-encoding", ""), new a("content-language", ""), new a("content-length", ""), new a("content-location", ""), new a("content-range", ""), new a("content-type", ""), new a("cookie", ""), new a("date", ""), new a("etag", ""), new a("expect", ""), new a("expires", ""), new a("from", ""), new a("host", ""), new a("if-match", ""), new a("if-modified-since", ""), new a("if-none-match", ""), new a("if-range", ""), new a("if-unmodified-since", ""), new a("last-modified", ""), new a("link", ""), new a("location", ""), new a("max-forwards", ""), new a("proxy-authenticate", ""), new a("proxy-authorization", ""), new a("range", ""), new a("referer", ""), new a("refresh", ""), new a("retry-after", ""), new a("server", ""), new a("set-cookie", ""), new a("strict-transport-security", ""), new a("transfer-encoding", ""), new a("user-agent", ""), new a("vary", ""), new a("via", ""), new a("www-authenticate", "")};
        f20892a = aVarArr;
        LinkedHashMap linkedHashMap = new LinkedHashMap(61);
        for (int i10 = 0; i10 < 61; i10++) {
            if (!linkedHashMap.containsKey(aVarArr[i10].f20871a)) {
                linkedHashMap.put(aVarArr[i10].f20871a, Integer.valueOf(i10));
            }
        }
        f20893b = Collections.unmodifiableMap(linkedHashMap);
    }

    public static void a(xb.m mVar) throws IOException {
        int iF = mVar.f();
        for (int i10 = 0; i10 < iF; i10++) {
            byte bM = mVar.m(i10);
            if (65 <= bM && bM < 91) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: ".concat(mVar.t()));
            }
        }
    }
}
