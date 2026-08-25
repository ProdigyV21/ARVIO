package io.github.jan.supabase.postgrest.query;

import androidx.compose.material3.d;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.collections.x;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import q7.g;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\b\u0087@\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0011\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006\u0014"}, d2 = {"Lio/github/jan/supabase/postgrest/query/Columns;", "", "value", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "equals", "", "other", "equals-impl", "(Ljava/lang/String;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Ljava/lang/String;)I", "toString", "toString-impl", "Companion", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@g
public final class Columns {
    private final String value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String ALL = m6642constructorimpl("*");

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J'\u0010\b\u001a\u00020\u00042\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n\"\u00020\u000bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ!\u0010\b\u001a\u00020\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\f\u0010\u000fJ\u001b\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u000bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001e\u0010\u0014\u001a\u00020\u0004\"\u0006\b\u0000\u0010\u0015\u0018\u0001H\u0086\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0006R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0017"}, d2 = {"Lio/github/jan/supabase/postgrest/query/Columns$Companion;", "", "()V", "ALL", "Lio/github/jan/supabase/postgrest/query/Columns;", "getALL-U9NzzuM", "()Ljava/lang/String;", "Ljava/lang/String;", "list", "columns", "", "", "list-Y7uY_Gg", "([Ljava/lang/String;)Ljava/lang/String;", "", "(Ljava/util/List;)Ljava/lang/String;", "raw", "value", "raw-Y7uY_Gg", "(Ljava/lang/String;)Ljava/lang/String;", LinkHeader.Parameters.Type, "T", "type-U9NzzuM", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        /* JADX INFO: renamed from: getALL-U9NzzuM, reason: not valid java name */
        public final String m6648getALLU9NzzuM() {
            return Columns.ALL;
        }

        /* JADX INFO: renamed from: list-Y7uY_Gg, reason: not valid java name */
        public final String m6650listY7uY_Gg(String... columns) {
            return Columns.m6642constructorimpl(r.f0(columns, ",", null, null, null, 62));
        }

        /* JADX INFO: renamed from: raw-Y7uY_Gg, reason: not valid java name */
        public final String m6651rawY7uY_Gg(String value) {
            return Columns.m6642constructorimpl(value);
        }

        /* JADX INFO: renamed from: type-U9NzzuM, reason: not valid java name */
        public final <T> String m6652typeU9NzzuM() {
            p.h();
            throw null;
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: list-Y7uY_Gg, reason: not valid java name */
        public final String m6649listY7uY_Gg(List<String> columns) {
            return Columns.m6642constructorimpl(x.u0(columns, ",", null, null, null, 62));
        }
    }

    private /* synthetic */ Columns(String str) {
        this.value = str;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Columns m6641boximpl(String str) {
        return new Columns(str);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static String m6642constructorimpl(String str) {
        return str;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m6643equalsimpl(String str, Object obj) {
        return (obj instanceof Columns) && p.a(str, ((Columns) obj).m6647unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m6644equalsimpl0(String str, String str2) {
        return p.a(str, str2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m6645hashCodeimpl(String str) {
        return str.hashCode();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m6646toStringimpl(String str) {
        return d.i(')', "Columns(value=", str);
    }

    public boolean equals(Object obj) {
        return m6643equalsimpl(this.value, obj);
    }

    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        return m6645hashCodeimpl(this.value);
    }

    public String toString() {
        return m6646toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ String m6647unboximpl() {
        return this.value;
    }
}
