package j$.time.format;

import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.lang.ref.SoftReference;
import java.text.DateFormatSymbols;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public final class t extends s {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final ConcurrentHashMap f18285i = new ConcurrentHashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final TextStyle f18286e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f18287f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Map f18288g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Map f18289h;

    public t(TextStyle textStyle, boolean z) {
        super(j$.time.temporal.p.f18370e, "ZoneText(" + textStyle + ")");
        this.f18288g = new HashMap();
        this.f18289h = new HashMap();
        this.f18286e = (TextStyle) Objects.requireNonNull(textStyle, "textStyle");
        this.f18287f = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0100  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // j$.time.format.s, j$.time.format.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean l(j$.time.format.w r14, java.lang.StringBuilder r15) {
        /*
            Method dump skipped, instruction units count: 261
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.format.t.l(j$.time.format.w, java.lang.StringBuilder):boolean");
    }

    @Override // j$.time.format.s
    public final m a(u uVar) {
        m mVar;
        if (this.f18286e == TextStyle.NARROW) {
            return super.a(uVar);
        }
        Locale locale = uVar.f18290a.f18200b;
        boolean z = uVar.f18291b;
        Set set = j$.time.zone.i.f18419d;
        int size = set.size();
        Map map = z ? this.f18288g : this.f18289h;
        Map.Entry entry = (Map.Entry) map.get(locale);
        if (entry != null && ((Integer) entry.getKey()).intValue() == size && (mVar = (m) ((SoftReference) entry.getValue()).get()) != null) {
            return mVar;
        }
        m mVar2 = uVar.f18291b ? new m("", null, null) : new l("", null, null);
        for (String[] strArr : DateFormatSymbols.getInstance(locale).getZoneStrings()) {
            String str = strArr[0];
            if (set.contains(str)) {
                mVar2.a(str, str);
                HashMap map2 = (HashMap) d0.f18240d;
                String str2 = (String) map2.get(str);
                if (str2 == null) {
                    HashMap map3 = (HashMap) d0.f18243g;
                    if (map3.containsKey(str)) {
                        str = (String) map3.get(str);
                        str2 = (String) map2.get(str);
                    }
                }
                if (str2 != null) {
                    Map map4 = (Map) ((HashMap) d0.f18242f).get(str2);
                    str = (map4 == null || !map4.containsKey(locale.getCountry())) ? (String) ((HashMap) d0.f18241e).get(str2) : (String) map4.get(locale.getCountry());
                }
                HashMap map5 = (HashMap) d0.f18243g;
                if (map5.containsKey(str)) {
                    str = (String) map5.get(str);
                }
                for (int i10 = this.f18286e == TextStyle.FULL ? 1 : 2; i10 < strArr.length; i10 += 2) {
                    mVar2.a(strArr[i10], str);
                }
            }
        }
        map.put(locale, new AbstractMap.SimpleImmutableEntry(Integer.valueOf(size), new SoftReference(mVar2)));
        return mVar2;
    }
}
