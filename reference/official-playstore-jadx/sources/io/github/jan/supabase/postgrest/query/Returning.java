package io.github.jan.supabase.postgrest.query;

import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0007\bB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\t\n¨\u0006\u000b"}, d2 = {"Lio/github/jan/supabase/postgrest/query/Returning;", "", "identifier", "", "(Ljava/lang/String;)V", "getIdentifier", "()Ljava/lang/String;", "Minimal", "Representation", "Lio/github/jan/supabase/postgrest/query/Returning$Minimal;", "Lio/github/jan/supabase/postgrest/query/Returning$Representation;", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public abstract class Returning {
    private final String identifier;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\u0007\u001a\u00020\bHÖ\u0001J\t\u0010\t\u001a\u00020\nHÖ\u0001¨\u0006\u000b"}, d2 = {"Lio/github/jan/supabase/postgrest/query/Returning$Minimal;", "Lio/github/jan/supabase/postgrest/query/Returning;", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Minimal extends Returning {
        public static final Minimal INSTANCE = new Minimal();

        private Minimal() {
            super("minimal", null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Minimal);
        }

        public int hashCode() {
            return -1374471108;
        }

        public String toString() {
            return "Minimal";
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\b\u001a\u00020\u0002HÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001ø\u0001\u0001¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u0007J\u0010\u0010\u000f\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\u0007\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u0018"}, d2 = {"Lio/github/jan/supabase/postgrest/query/Returning$Representation;", "Lio/github/jan/supabase/postgrest/query/Returning;", "Lio/github/jan/supabase/postgrest/query/Columns;", "columns", "<init>", "(Ljava/lang/String;Lkotlin/jvm/internal/h;)V", "component1-U9NzzuM", "()Ljava/lang/String;", "component1", "copy-fYsiLaM", "(Ljava/lang/String;)Lio/github/jan/supabase/postgrest/query/Returning$Representation;", "copy", "", "toString", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getColumns-U9NzzuM", "postgrest-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Representation extends Returning {
        private final String columns;

        public /* synthetic */ Representation(String str, h hVar) {
            this(str);
        }

        /* JADX INFO: renamed from: copy-fYsiLaM$default, reason: not valid java name */
        public static /* synthetic */ Representation m6658copyfYsiLaM$default(Representation representation, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = representation.columns;
            }
            return representation.m6660copyfYsiLaM(str);
        }

        /* JADX INFO: renamed from: component1-U9NzzuM, reason: not valid java name and from getter */
        public final String getColumns() {
            return this.columns;
        }

        /* JADX INFO: renamed from: copy-fYsiLaM, reason: not valid java name */
        public final Representation m6660copyfYsiLaM(String columns) {
            return new Representation(columns, null);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Representation) && Columns.m6644equalsimpl0(this.columns, ((Representation) other).columns);
        }

        /* JADX INFO: renamed from: getColumns-U9NzzuM, reason: not valid java name */
        public final String m6661getColumnsU9NzzuM() {
            return this.columns;
        }

        public int hashCode() {
            return Columns.m6645hashCodeimpl(this.columns);
        }

        public String toString() {
            return "Representation(columns=" + ((Object) Columns.m6646toStringimpl(this.columns)) + ')';
        }

        private Representation(String str) {
            super("representation", null);
            this.columns = str;
        }

        public /* synthetic */ Representation(String str, int i10, h hVar) {
            this((i10 & 1) != 0 ? Columns.INSTANCE.m6648getALLU9NzzuM() : str, null);
        }
    }

    public /* synthetic */ Returning(String str, h hVar) {
        this(str);
    }

    public final String getIdentifier() {
        return this.identifier;
    }

    private Returning(String str) {
        this.identifier = str;
    }
}
