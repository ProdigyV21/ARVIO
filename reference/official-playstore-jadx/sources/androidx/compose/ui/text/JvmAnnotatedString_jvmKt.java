package androidx.compose.ui.text;

import androidx.compose.ui.text.AnnotatedString;
import androidx.work.impl.t;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.collections.c;
import kotlin.collections.e;
import kotlin.collections.f;
import kotlin.collections.h0;
import kotlin.jvm.internal.k0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.q;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a3\u0010\u0004\u001a\u00020\u0000*\u00020\u00002\u001e\u0010\u0004\u001a\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00020\u0001H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a1\u0010\f\u001a\u00020\u000b2\u0012\u0010\b\u001a\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0018\u00010\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\tH\u0002¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/ui/text/AnnotatedString;", "Lkotlin/Function3;", "", "", "transform", "(Landroidx/compose/ui/text/AnnotatedString;Lr7/q;)Landroidx/compose/ui/text/AnnotatedString;", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "ranges", "Ljava/util/SortedSet;", "target", "Lx6/t0;", "collectRangeTransitions", "(Ljava/util/List;Ljava/util/SortedSet;)V", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class JvmAnnotatedString_jvmKt {

    /* JADX INFO: renamed from: androidx.compose.ui.text.JvmAnnotatedString_jvmKt$transform$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "<name for destructuring parameter 0>", "", "invoke", "(Ljava/util/List;)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<List<? extends Integer>, Integer> {
        final /* synthetic */ Map<Integer, Integer> $offsetMap;
        final /* synthetic */ k0 $resultStr;
        final /* synthetic */ AnnotatedString $this_transform;
        final /* synthetic */ q<String, Integer, Integer, String> $transform;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(k0 k0Var, q<? super String, ? super Integer, ? super Integer, String> qVar, AnnotatedString annotatedString, Map<Integer, Integer> map) {
            super(1);
            this.$resultStr = k0Var;
            this.$transform = qVar;
            this.$this_transform = annotatedString;
            this.$offsetMap = map;
        }

        @Override // r7.l
        public final Integer invoke(List<Integer> list) {
            int iIntValue = list.get(0).intValue();
            int iIntValue2 = list.get(1).intValue();
            this.$resultStr.f19746i = ((String) this.$resultStr.f19746i) + ((String) this.$transform.invoke(this.$this_transform.getText(), Integer.valueOf(iIntValue), Integer.valueOf(iIntValue2)));
            return this.$offsetMap.put(Integer.valueOf(iIntValue2), Integer.valueOf(((String) this.$resultStr.f19746i).length()));
        }
    }

    private static final void collectRangeTransitions(List<? extends AnnotatedString.Range<?>> list, SortedSet<Integer> sortedSet) {
        if (list != null) {
            int size = list.size();
            for (int i10 = 0; i10 < size; i10++) {
                AnnotatedString.Range<?> range = list.get(i10);
                sortedSet.add(Integer.valueOf(range.getStart()));
                sortedSet.add(Integer.valueOf(range.getEnd()));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final AnnotatedString transform(AnnotatedString annotatedString, q<? super String, ? super Integer, ? super Integer, String> qVar) {
        ArrayList arrayList;
        ArrayList arrayList2;
        Integer[] numArr = {0, Integer.valueOf(annotatedString.getText().length())};
        TreeSet treeSet = new TreeSet();
        kotlin.collections.r.m0(numArr, treeSet);
        collectRangeTransitions(annotatedString.getSpanStylesOrNull$ui_text_release(), treeSet);
        collectRangeTransitions(annotatedString.getParagraphStylesOrNull$ui_text_release(), treeSet);
        collectRangeTransitions(annotatedString.getAnnotations$ui_text_release(), treeSet);
        k0 k0Var = new k0();
        k0Var.f19746i = "";
        LinkedHashMap linkedHashMapU0 = h0.u0(new x(0, 0));
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(k0Var, qVar, annotatedString, linkedHashMapU0);
        t.g(2, 1);
        if ((treeSet instanceof RandomAccess) && (treeSet instanceof List)) {
            List list = (List) treeSet;
            int size = list.size();
            ArrayList arrayList3 = new ArrayList((size % 1 == 0 ? 0 : 1) + size);
            e eVar = new e(list);
            for (int i10 = 0; i10 >= 0 && i10 < size; i10++) {
                int i11 = size - i10;
                if (2 <= i11) {
                    i11 = 2;
                }
                if (i11 < 2) {
                    break;
                }
                int i12 = i11 + i10;
                c cVar = f.Companion;
                int size2 = eVar.f19693n.size();
                cVar.getClass();
                c.d(i10, i12, size2);
                eVar.f19691l = i10;
                eVar.f19692m = i12 - i10;
                arrayList3.add(anonymousClass1.invoke((Object) eVar));
            }
        } else {
            ArrayList arrayList4 = new ArrayList();
            Iterator itC = t.C(treeSet.iterator(), 2, 1, false, true);
            while (itC.hasNext()) {
                arrayList4.add(anonymousClass1.invoke(itC.next()));
            }
        }
        List<AnnotatedString.Range<SpanStyle>> spanStylesOrNull$ui_text_release = annotatedString.getSpanStylesOrNull$ui_text_release();
        ArrayList arrayList5 = null;
        if (spanStylesOrNull$ui_text_release != null) {
            arrayList = new ArrayList(spanStylesOrNull$ui_text_release.size());
            int size3 = spanStylesOrNull$ui_text_release.size();
            for (int i13 = 0; i13 < size3; i13++) {
                AnnotatedString.Range<SpanStyle> range = spanStylesOrNull$ui_text_release.get(i13);
                arrayList.add(new AnnotatedString.Range(range.getItem(), ((Number) linkedHashMapU0.get(Integer.valueOf(range.getStart()))).intValue(), ((Number) linkedHashMapU0.get(Integer.valueOf(range.getEnd()))).intValue()));
            }
        } else {
            arrayList = null;
        }
        List<AnnotatedString.Range<ParagraphStyle>> paragraphStylesOrNull$ui_text_release = annotatedString.getParagraphStylesOrNull$ui_text_release();
        if (paragraphStylesOrNull$ui_text_release != null) {
            arrayList2 = new ArrayList(paragraphStylesOrNull$ui_text_release.size());
            int size4 = paragraphStylesOrNull$ui_text_release.size();
            for (int i14 = 0; i14 < size4; i14++) {
                AnnotatedString.Range<ParagraphStyle> range2 = paragraphStylesOrNull$ui_text_release.get(i14);
                arrayList2.add(new AnnotatedString.Range(range2.getItem(), ((Number) linkedHashMapU0.get(Integer.valueOf(range2.getStart()))).intValue(), ((Number) linkedHashMapU0.get(Integer.valueOf(range2.getEnd()))).intValue()));
            }
        } else {
            arrayList2 = null;
        }
        List<AnnotatedString.Range<? extends Object>> annotations$ui_text_release = annotatedString.getAnnotations$ui_text_release();
        if (annotations$ui_text_release != null) {
            arrayList5 = new ArrayList(annotations$ui_text_release.size());
            int size5 = annotations$ui_text_release.size();
            for (int i15 = 0; i15 < size5; i15++) {
                AnnotatedString.Range<? extends Object> range3 = annotations$ui_text_release.get(i15);
                arrayList5.add(new AnnotatedString.Range(range3.getItem(), ((Number) linkedHashMapU0.get(Integer.valueOf(range3.getStart()))).intValue(), ((Number) linkedHashMapU0.get(Integer.valueOf(range3.getEnd()))).intValue()));
            }
        }
        return new AnnotatedString((String) k0Var.f19746i, arrayList, arrayList2, arrayList5);
    }
}
