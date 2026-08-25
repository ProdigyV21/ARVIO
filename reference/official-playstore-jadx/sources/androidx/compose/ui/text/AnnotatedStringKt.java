package androidx.compose.ui.text;

import androidx.compose.foundation.c;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.r;
import qb.d;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\f\u001a'\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00040\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a1\u0010\u000b\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u0004\u0018\u00010\u0003*\u00020\u00002\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u000b\u0010\f\u001a1\u0010\r\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0004\u0018\u00010\u0003*\u00020\u00002\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\r\u0010\f\u001a3\u0010\u000f\u001a\u0012\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\u0004\u0018\u00010\u0003*\u00020\u00002\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u000f\u0010\f\u001a#\u0010\u0010\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u0010\u0010\u0011\u001ah\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\u0012*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012>\b\u0004\u0010\u0018\u001a8\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00028\u00000\u0013H\u0080\b¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u001b\u0010\u001d\u001a\u00020\u0000*\u00020\u00002\b\b\u0002\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u001b\u0010\u001f\u001a\u00020\u0000*\u00020\u00002\b\b\u0002\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001f\u0010\u001e\u001a\u001b\u0010 \u001a\u00020\u0000*\u00020\u00002\b\b\u0002\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b \u0010\u001e\u001a\u001b\u0010!\u001a\u00020\u0000*\u00020\u00002\b\b\u0002\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b!\u0010\u001e\u001a?\u0010'\u001a\u00028\u0000\"\b\b\u0000\u0010\"*\u00020\u000e*\u00020#2\u0006\u0010$\u001a\u00020\n2\u0017\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00028\u00000%¢\u0006\u0002\b&H\u0086\b¢\u0006\u0004\b'\u0010(\u001aA\u0010'\u001a\u00028\u0000\"\b\b\u0000\u0010\"*\u00020\u000e*\u00020#2\u0006\u0010$\u001a\u00020\u00012\u0019\b\u0004\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00028\u00000%¢\u0006\u0002\b&H\u0086\b¢\u0006\u0004\b'\u0010)\u001aI\u0010-\u001a\u00028\u0000\"\b\b\u0000\u0010\"*\u00020\u000e*\u00020#2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0019\b\u0004\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00028\u00000%¢\u0006\u0002\b&H\u0087\b¢\u0006\u0004\b-\u0010.\u001aA\u0010-\u001a\u00028\u0000\"\b\b\u0000\u0010\"*\u00020\u000e*\u00020#2\u0006\u00100\u001a\u00020/2\u0019\b\u0004\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00028\u00000%¢\u0006\u0002\b&H\u0087\b¢\u0006\u0004\b-\u00101\u001aA\u0010-\u001a\u00028\u0000\"\b\b\u0000\u0010\"*\u00020\u000e*\u00020#2\u0006\u00103\u001a\u0002022\u0019\b\u0004\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00028\u00000%¢\u0006\u0002\b&H\u0087\b¢\u0006\u0004\b-\u00104\u001aK\u00106\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0018\u00010\u0003\"\u0004\b\u0000\u0010\u00122\u0016\u00105\u001a\u0012\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0004\u0018\u00010\u00032\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0002¢\u0006\u0004\b6\u00107\u001a)\u0010:\u001a\u00020\u00002\u0006\u00108\u001a\u00020*2\u0006\u00109\u001a\u00020\n2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b:\u0010;\u001a\u001d\u0010:\u001a\u00020\u00002\u0006\u00108\u001a\u00020*2\u0006\u0010\u0017\u001a\u00020\u0001¢\u0006\u0004\b:\u0010<\u001a)\u0010?\u001a\u00020\u00002\u0017\u0010>\u001a\u0013\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020=0%¢\u0006\u0002\b&H\u0086\b¢\u0006\u0004\b?\u0010@\u001a/\u0010F\u001a\u00020E2\u0006\u0010A\u001a\u00020\u00072\u0006\u0010B\u001a\u00020\u00072\u0006\u0010C\u001a\u00020\u00072\u0006\u0010D\u001a\u00020\u0007H\u0000¢\u0006\u0004\bF\u0010G\u001a/\u0010L\u001a\u00020E2\u0006\u0010H\u001a\u00020\u00072\u0006\u0010I\u001a\u00020\u00072\u0006\u0010J\u001a\u00020\u00072\u0006\u0010K\u001a\u00020\u0007H\u0000¢\u0006\u0004\bL\u0010G\u001a\u000f\u0010M\u001a\u00020\u0000H\u0000¢\u0006\u0004\bM\u0010N\"\u0014\u0010O\u001a\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bO\u0010P¨\u0006Q"}, d2 = {"Landroidx/compose/ui/text/AnnotatedString;", "Landroidx/compose/ui/text/ParagraphStyle;", "defaultParagraphStyle", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "normalizedParagraphStyles", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/ParagraphStyle;)Ljava/util/List;", "", TtmlNode.START, TtmlNode.END, "Landroidx/compose/ui/text/SpanStyle;", "getLocalSpanStyles", "(Landroidx/compose/ui/text/AnnotatedString;II)Ljava/util/List;", "getLocalParagraphStyles", "", "getLocalAnnotations", "substringWithoutParagraphStyles", "(Landroidx/compose/ui/text/AnnotatedString;II)Landroidx/compose/ui/text/AnnotatedString;", "T", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, "annotatedString", "paragraphStyle", "block", "mapEachParagraphStyle", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/ParagraphStyle;Lr7/p;)Ljava/util/List;", "Landroidx/compose/ui/text/intl/LocaleList;", "localeList", "toUpperCase", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/intl/LocaleList;)Landroidx/compose/ui/text/AnnotatedString;", "toLowerCase", "capitalize", "decapitalize", "R", "Landroidx/compose/ui/text/AnnotatedString$Builder;", "style", "Lkotlin/Function1;", "Lx6/n;", "withStyle", "(Landroidx/compose/ui/text/AnnotatedString$Builder;Landroidx/compose/ui/text/SpanStyle;Lr7/l;)Ljava/lang/Object;", "(Landroidx/compose/ui/text/AnnotatedString$Builder;Landroidx/compose/ui/text/ParagraphStyle;Lr7/l;)Ljava/lang/Object;", "", "tag", "annotation", "withAnnotation", "(Landroidx/compose/ui/text/AnnotatedString$Builder;Ljava/lang/String;Ljava/lang/String;Lr7/l;)Ljava/lang/Object;", "Landroidx/compose/ui/text/TtsAnnotation;", "ttsAnnotation", "(Landroidx/compose/ui/text/AnnotatedString$Builder;Landroidx/compose/ui/text/TtsAnnotation;Lr7/l;)Ljava/lang/Object;", "Landroidx/compose/ui/text/UrlAnnotation;", "urlAnnotation", "(Landroidx/compose/ui/text/AnnotatedString$Builder;Landroidx/compose/ui/text/UrlAnnotation;Lr7/l;)Ljava/lang/Object;", "ranges", "filterRanges", "(Ljava/util/List;II)Ljava/util/List;", "text", "spanStyle", "AnnotatedString", "(Ljava/lang/String;Landroidx/compose/ui/text/SpanStyle;Landroidx/compose/ui/text/ParagraphStyle;)Landroidx/compose/ui/text/AnnotatedString;", "(Ljava/lang/String;Landroidx/compose/ui/text/ParagraphStyle;)Landroidx/compose/ui/text/AnnotatedString;", "Lx6/t0;", "builder", "buildAnnotatedString", "(Lr7/l;)Landroidx/compose/ui/text/AnnotatedString;", "baseStart", "baseEnd", "targetStart", "targetEnd", "", "contains", "(IIII)Z", "lStart", "lEnd", "rStart", "rEnd", "intersect", "emptyAnnotatedString", "()Landroidx/compose/ui/text/AnnotatedString;", "EmptyAnnotatedString", "Landroidx/compose/ui/text/AnnotatedString;", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AnnotatedStringKt {
    private static final AnnotatedString EmptyAnnotatedString = new AnnotatedString("", null, null, 6, null);

    /* JADX INFO: renamed from: androidx.compose.ui.text.AnnotatedStringKt$capitalize$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "str", TtmlNode.START, "", TtmlNode.END, "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements q<String, Integer, Integer, String> {
        final /* synthetic */ LocaleList $localeList;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(LocaleList localeList) {
            super(3);
            this.$localeList = localeList;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((String) obj, ((Number) obj2).intValue(), ((Number) obj3).intValue());
        }

        public final String invoke(String str, int i10, int i11) {
            return i10 == 0 ? StringKt.capitalize(str.substring(i10, i11), this.$localeList) : str.substring(i10, i11);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.text.AnnotatedStringKt$decapitalize$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "str", TtmlNode.START, "", TtmlNode.END, "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C06221 extends r implements q<String, Integer, Integer, String> {
        final /* synthetic */ LocaleList $localeList;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06221(LocaleList localeList) {
            super(3);
            this.$localeList = localeList;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((String) obj, ((Number) obj2).intValue(), ((Number) obj3).intValue());
        }

        public final String invoke(String str, int i10, int i11) {
            return i10 == 0 ? StringKt.decapitalize(str.substring(i10, i11), this.$localeList) : str.substring(i10, i11);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.text.AnnotatedStringKt$toLowerCase$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "str", TtmlNode.START, "", TtmlNode.END, "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C06231 extends r implements q<String, Integer, Integer, String> {
        final /* synthetic */ LocaleList $localeList;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06231(LocaleList localeList) {
            super(3);
            this.$localeList = localeList;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((String) obj, ((Number) obj2).intValue(), ((Number) obj3).intValue());
        }

        public final String invoke(String str, int i10, int i11) {
            return StringKt.toLowerCase(str.substring(i10, i11), this.$localeList);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.text.AnnotatedStringKt$toUpperCase$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "str", TtmlNode.START, "", TtmlNode.END, "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C06241 extends r implements q<String, Integer, Integer, String> {
        final /* synthetic */ LocaleList $localeList;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06241(LocaleList localeList) {
            super(3);
            this.$localeList = localeList;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            return invoke((String) obj, ((Number) obj2).intValue(), ((Number) obj3).intValue());
        }

        public final String invoke(String str, int i10, int i11) {
            return StringKt.toUpperCase(str.substring(i10, i11), this.$localeList);
        }
    }

    public static final AnnotatedString AnnotatedString(String str, SpanStyle spanStyle, ParagraphStyle paragraphStyle) {
        return new AnnotatedString(str, Collections.singletonList(new AnnotatedString.Range(spanStyle, 0, str.length())), paragraphStyle == null ? z.f19728i : Collections.singletonList(new AnnotatedString.Range(paragraphStyle, 0, str.length())));
    }

    public static /* synthetic */ AnnotatedString AnnotatedString$default(String str, SpanStyle spanStyle, ParagraphStyle paragraphStyle, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            paragraphStyle = null;
        }
        return AnnotatedString(str, spanStyle, paragraphStyle);
    }

    public static final AnnotatedString buildAnnotatedString(l<? super AnnotatedString.Builder, t0> lVar) {
        AnnotatedString.Builder builder = new AnnotatedString.Builder(0, 1, null);
        lVar.invoke(builder);
        return builder.toAnnotatedString();
    }

    public static final AnnotatedString capitalize(AnnotatedString annotatedString, LocaleList localeList) {
        return JvmAnnotatedString_jvmKt.transform(annotatedString, new AnonymousClass1(localeList));
    }

    public static /* synthetic */ AnnotatedString capitalize$default(AnnotatedString annotatedString, LocaleList localeList, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            localeList = LocaleList.INSTANCE.getCurrent();
        }
        return capitalize(annotatedString, localeList);
    }

    public static final boolean contains(int i10, int i11, int i12, int i13) {
        if (i10 <= i12 && i13 <= i11) {
            if (i11 == i13) {
                if ((i12 == i13) == (i10 == i11)) {
                }
            }
            return true;
        }
        return false;
    }

    public static final AnnotatedString decapitalize(AnnotatedString annotatedString, LocaleList localeList) {
        return JvmAnnotatedString_jvmKt.transform(annotatedString, new C06221(localeList));
    }

    public static /* synthetic */ AnnotatedString decapitalize$default(AnnotatedString annotatedString, LocaleList localeList, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            localeList = LocaleList.INSTANCE.getCurrent();
        }
        return decapitalize(annotatedString, localeList);
    }

    public static final AnnotatedString emptyAnnotatedString() {
        return EmptyAnnotatedString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T> List<AnnotatedString.Range<T>> filterRanges(List<? extends AnnotatedString.Range<? extends T>> list, int i10, int i11) {
        if (i10 > i11) {
            throw new IllegalArgumentException(c.r("start (", i10, ") should be less than or equal to end (", i11, ')').toString());
        }
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i12 = 0; i12 < size; i12++) {
            AnnotatedString.Range<? extends T> range = list.get(i12);
            AnnotatedString.Range<? extends T> range2 = range;
            if (intersect(i10, i11, range2.getStart(), range2.getEnd())) {
                arrayList.add(range);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size2 = arrayList.size();
        for (int i13 = 0; i13 < size2; i13++) {
            AnnotatedString.Range range3 = (AnnotatedString.Range) arrayList.get(i13);
            arrayList2.add(new AnnotatedString.Range(range3.getItem(), Math.max(i10, range3.getStart()) - i10, Math.min(i11, range3.getEnd()) - i10, range3.getTag()));
        }
        if (arrayList2.isEmpty()) {
            return null;
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<AnnotatedString.Range<? extends Object>> getLocalAnnotations(AnnotatedString annotatedString, int i10, int i11) {
        List<AnnotatedString.Range<? extends Object>> annotations$ui_text_release;
        if (i10 == i11 || (annotations$ui_text_release = annotatedString.getAnnotations$ui_text_release()) == null) {
            return null;
        }
        if (i10 == 0 && i11 >= annotatedString.getText().length()) {
            return annotations$ui_text_release;
        }
        ArrayList arrayList = new ArrayList(annotations$ui_text_release.size());
        int size = annotations$ui_text_release.size();
        for (int i12 = 0; i12 < size; i12++) {
            AnnotatedString.Range<? extends Object> range = annotations$ui_text_release.get(i12);
            AnnotatedString.Range<? extends Object> range2 = range;
            if (intersect(i10, i11, range2.getStart(), range2.getEnd())) {
                arrayList.add(range);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size2 = arrayList.size();
        for (int i13 = 0; i13 < size2; i13++) {
            AnnotatedString.Range range3 = (AnnotatedString.Range) arrayList.get(i13);
            arrayList2.add(new AnnotatedString.Range(range3.getItem(), d.n(range3.getStart(), i10, i11) - i10, d.n(range3.getEnd(), i10, i11) - i10, range3.getTag()));
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<AnnotatedString.Range<ParagraphStyle>> getLocalParagraphStyles(AnnotatedString annotatedString, int i10, int i11) {
        List<AnnotatedString.Range<ParagraphStyle>> paragraphStylesOrNull$ui_text_release;
        if (i10 == i11 || (paragraphStylesOrNull$ui_text_release = annotatedString.getParagraphStylesOrNull$ui_text_release()) == null) {
            return null;
        }
        if (i10 == 0 && i11 >= annotatedString.getText().length()) {
            return paragraphStylesOrNull$ui_text_release;
        }
        ArrayList arrayList = new ArrayList(paragraphStylesOrNull$ui_text_release.size());
        int size = paragraphStylesOrNull$ui_text_release.size();
        for (int i12 = 0; i12 < size; i12++) {
            AnnotatedString.Range<ParagraphStyle> range = paragraphStylesOrNull$ui_text_release.get(i12);
            AnnotatedString.Range<ParagraphStyle> range2 = range;
            if (intersect(i10, i11, range2.getStart(), range2.getEnd())) {
                arrayList.add(range);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size2 = arrayList.size();
        for (int i13 = 0; i13 < size2; i13++) {
            AnnotatedString.Range range3 = (AnnotatedString.Range) arrayList.get(i13);
            arrayList2.add(new AnnotatedString.Range(range3.getItem(), d.n(range3.getStart(), i10, i11) - i10, d.n(range3.getEnd(), i10, i11) - i10));
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<AnnotatedString.Range<SpanStyle>> getLocalSpanStyles(AnnotatedString annotatedString, int i10, int i11) {
        List<AnnotatedString.Range<SpanStyle>> spanStylesOrNull$ui_text_release;
        if (i10 == i11 || (spanStylesOrNull$ui_text_release = annotatedString.getSpanStylesOrNull$ui_text_release()) == null) {
            return null;
        }
        if (i10 == 0 && i11 >= annotatedString.getText().length()) {
            return spanStylesOrNull$ui_text_release;
        }
        ArrayList arrayList = new ArrayList(spanStylesOrNull$ui_text_release.size());
        int size = spanStylesOrNull$ui_text_release.size();
        for (int i12 = 0; i12 < size; i12++) {
            AnnotatedString.Range<SpanStyle> range = spanStylesOrNull$ui_text_release.get(i12);
            AnnotatedString.Range<SpanStyle> range2 = range;
            if (intersect(i10, i11, range2.getStart(), range2.getEnd())) {
                arrayList.add(range);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size2 = arrayList.size();
        for (int i13 = 0; i13 < size2; i13++) {
            AnnotatedString.Range range3 = (AnnotatedString.Range) arrayList.get(i13);
            arrayList2.add(new AnnotatedString.Range(range3.getItem(), d.n(range3.getStart(), i10, i11) - i10, d.n(range3.getEnd(), i10, i11) - i10));
        }
        return arrayList2;
    }

    public static final boolean intersect(int i10, int i11, int i12, int i13) {
        return Math.max(i10, i12) < Math.min(i11, i13) || contains(i10, i11, i12, i13) || contains(i12, i13, i10, i11);
    }

    public static final <T> List<T> mapEachParagraphStyle(AnnotatedString annotatedString, ParagraphStyle paragraphStyle, p<? super AnnotatedString, ? super AnnotatedString.Range<ParagraphStyle>, ? extends T> pVar) {
        List<AnnotatedString.Range<ParagraphStyle>> listNormalizedParagraphStyles = normalizedParagraphStyles(annotatedString, paragraphStyle);
        ArrayList arrayList = new ArrayList(listNormalizedParagraphStyles.size());
        int size = listNormalizedParagraphStyles.size();
        for (int i10 = 0; i10 < size; i10++) {
            AnnotatedString.Range<ParagraphStyle> range = listNormalizedParagraphStyles.get(i10);
            arrayList.add(pVar.invoke(substringWithoutParagraphStyles(annotatedString, range.getStart(), range.getEnd()), range));
        }
        return arrayList;
    }

    public static final List<AnnotatedString.Range<ParagraphStyle>> normalizedParagraphStyles(AnnotatedString annotatedString, ParagraphStyle paragraphStyle) {
        int length = annotatedString.getText().length();
        List<AnnotatedString.Range<ParagraphStyle>> paragraphStylesOrNull$ui_text_release = annotatedString.getParagraphStylesOrNull$ui_text_release();
        if (paragraphStylesOrNull$ui_text_release == null) {
            paragraphStylesOrNull$ui_text_release = z.f19728i;
        }
        ArrayList arrayList = new ArrayList();
        int size = paragraphStylesOrNull$ui_text_release.size();
        int i10 = 0;
        int i11 = 0;
        while (i10 < size) {
            AnnotatedString.Range<ParagraphStyle> range = paragraphStylesOrNull$ui_text_release.get(i10);
            ParagraphStyle paragraphStyleComponent1 = range.component1();
            int start = range.getStart();
            int end = range.getEnd();
            if (start != i11) {
                arrayList.add(new AnnotatedString.Range(paragraphStyle, i11, start));
            }
            arrayList.add(new AnnotatedString.Range(paragraphStyle.merge(paragraphStyleComponent1), start, end));
            i10++;
            i11 = end;
        }
        if (i11 != length) {
            arrayList.add(new AnnotatedString.Range(paragraphStyle, i11, length));
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new AnnotatedString.Range(paragraphStyle, 0, 0));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnnotatedString substringWithoutParagraphStyles(AnnotatedString annotatedString, int i10, int i11) {
        return new AnnotatedString(i10 != i11 ? annotatedString.getText().substring(i10, i11) : "", getLocalSpanStyles(annotatedString, i10, i11), null, null, 12, null);
    }

    public static final AnnotatedString toLowerCase(AnnotatedString annotatedString, LocaleList localeList) {
        return JvmAnnotatedString_jvmKt.transform(annotatedString, new C06231(localeList));
    }

    public static /* synthetic */ AnnotatedString toLowerCase$default(AnnotatedString annotatedString, LocaleList localeList, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            localeList = LocaleList.INSTANCE.getCurrent();
        }
        return toLowerCase(annotatedString, localeList);
    }

    public static final AnnotatedString toUpperCase(AnnotatedString annotatedString, LocaleList localeList) {
        return JvmAnnotatedString_jvmKt.transform(annotatedString, new C06241(localeList));
    }

    public static /* synthetic */ AnnotatedString toUpperCase$default(AnnotatedString annotatedString, LocaleList localeList, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            localeList = LocaleList.INSTANCE.getCurrent();
        }
        return toUpperCase(annotatedString, localeList);
    }

    public static final <R> R withAnnotation(AnnotatedString.Builder builder, String str, String str2, l<? super AnnotatedString.Builder, ? extends R> lVar) {
        int iPushStringAnnotation = builder.pushStringAnnotation(str, str2);
        try {
            return (R) lVar.invoke(builder);
        } finally {
            builder.pop(iPushStringAnnotation);
        }
    }

    public static final <R> R withStyle(AnnotatedString.Builder builder, SpanStyle spanStyle, l<? super AnnotatedString.Builder, ? extends R> lVar) {
        int iPushStyle = builder.pushStyle(spanStyle);
        try {
            return (R) lVar.invoke(builder);
        } finally {
            builder.pop(iPushStyle);
        }
    }

    public static final <R> R withAnnotation(AnnotatedString.Builder builder, TtsAnnotation ttsAnnotation, l<? super AnnotatedString.Builder, ? extends R> lVar) {
        int iPushTtsAnnotation = builder.pushTtsAnnotation(ttsAnnotation);
        try {
            return (R) lVar.invoke(builder);
        } finally {
            builder.pop(iPushTtsAnnotation);
        }
    }

    public static final <R> R withStyle(AnnotatedString.Builder builder, ParagraphStyle paragraphStyle, l<? super AnnotatedString.Builder, ? extends R> lVar) {
        int iPushStyle = builder.pushStyle(paragraphStyle);
        try {
            return (R) lVar.invoke(builder);
        } finally {
            builder.pop(iPushStyle);
        }
    }

    public static final AnnotatedString AnnotatedString(String str, ParagraphStyle paragraphStyle) {
        return new AnnotatedString(str, z.f19728i, Collections.singletonList(new AnnotatedString.Range(paragraphStyle, 0, str.length())));
    }

    public static final <R> R withAnnotation(AnnotatedString.Builder builder, UrlAnnotation urlAnnotation, l<? super AnnotatedString.Builder, ? extends R> lVar) {
        int iPushUrlAnnotation = builder.pushUrlAnnotation(urlAnnotation);
        try {
            return (R) lVar.invoke(builder);
        } finally {
            builder.pop(iPushUrlAnnotation);
        }
    }
}
