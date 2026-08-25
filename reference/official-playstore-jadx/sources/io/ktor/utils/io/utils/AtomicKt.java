package io.ktor.utils.io.utils;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.reflect.o;
import kotlin.text.u;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u001a6\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00030\u0002H\u0080\b¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001f\u0010\f\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"", "Owner", "Lkotlin/reflect/o;", "", TtmlNode.TAG_P, "Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;", "longUpdater", "(Lkotlin/reflect/o;)Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;", "", ContentDisposition.Parameters.Name, "", "default", "getIOIntProperty", "(Ljava/lang/String;I)I", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AtomicKt {
    public static final int getIOIntProperty(String str, int i10) {
        String property;
        Integer numR;
        try {
            property = System.getProperty("io.ktor.utils.io.".concat(str));
        } catch (SecurityException unused) {
            property = null;
        }
        return (property == null || (numR = u.R(property)) == null) ? i10 : numR.intValue();
    }

    public static final <Owner> AtomicLongFieldUpdater<Owner> longUpdater(o<Owner, Long> oVar) {
        p.h();
        throw null;
    }
}
