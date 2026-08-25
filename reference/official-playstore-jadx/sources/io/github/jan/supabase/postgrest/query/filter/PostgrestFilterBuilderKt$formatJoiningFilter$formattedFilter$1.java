package io.github.jan.supabase.postgrest.query.filter;

import a0.c;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import kotlin.text.u;
import r7.l;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00020\u0000H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lx6/x;", "", "", "it", "", "invoke", "(Lx6/x;)Ljava/lang/CharSequence;", "<anonymous>"}, k = 3, mv = {1, 9, 0})
public final class PostgrestFilterBuilderKt$formatJoiningFilter$formattedFilter$1 extends r implements l<x, CharSequence> {
    public static final PostgrestFilterBuilderKt$formatJoiningFilter$formattedFilter$1 INSTANCE = new PostgrestFilterBuilderKt$formatJoiningFilter$formattedFilter$1();

    /* JADX INFO: renamed from: io.github.jan.supabase.postgrest.query.filter.PostgrestFilterBuilderKt$formatJoiningFilter$formattedFilter$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "filter", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 176)
    public static final class AnonymousClass1 extends r implements l<String, CharSequence> {
        final /* synthetic */ x $it;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(x xVar) {
            super(1);
            this.$it = xVar;
        }

        @Override // r7.l
        public final CharSequence invoke(String str) {
            boolean z = false;
            if (u.P(str, "(", false) && u.K(str, ")", false)) {
                z = true;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append((String) this.$it.f22608i);
            return c.p(sb2, z ? "" : ".", str);
        }
    }

    public PostgrestFilterBuilderKt$formatJoiningFilter$formattedFilter$1() {
        super(1);
    }

    @Override // r7.l
    public final CharSequence invoke(x xVar) {
        return kotlin.collections.x.u0((Iterable) xVar.f22609l, ",", null, null, new AnonymousClass1(xVar), 30);
    }
}
