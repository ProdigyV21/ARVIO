package androidx.compose.foundation.text;

import androidx.emoji2.text.o;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001b\u0010\u0003\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001b\u0010\u0005\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0005\u0010\u0004\u001a\u0011\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"", "", "index", "findPrecedingBreak", "(Ljava/lang/String;I)I", "findFollowingBreak", "Landroidx/emoji2/text/o;", "getEmojiCompatIfLoaded", "()Landroidx/emoji2/text/o;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StringHelpers_androidKt {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARN: Type inference failed for: r2v0, types: [androidx.emoji2.text.z, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v1, types: [java.text.BreakIterator] */
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
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int findFollowingBreak(java.lang.String r9, int r10) {
        /*
            androidx.emoji2.text.o r0 = getEmojiCompatIfLoaded()
            r1 = 0
            if (r0 == 0) goto L63
            androidx.emoji2.text.g r0 = r0.f2631e
            androidx.emoji2.text.z r2 = r0.f2608b
            r2.getClass()
            r0 = -1
            if (r10 < 0) goto L17
            int r3 = r9.length()
            if (r10 < r3) goto L19
        L17:
            r3 = r9
            goto L59
        L19:
            boolean r3 = r9 instanceof android.text.Spanned
            r4 = 0
            if (r3 == 0) goto L36
            r3 = r9
            android.text.Spanned r3 = (android.text.Spanned) r3
            int r5 = r10 + 1
            java.lang.Class<androidx.emoji2.text.j0> r6 = androidx.emoji2.text.j0.class
            java.lang.Object[] r5 = r3.getSpans(r10, r5, r6)
            androidx.emoji2.text.j0[] r5 = (androidx.emoji2.text.j0[]) r5
            int r6 = r5.length
            if (r6 <= 0) goto L36
            r2 = r5[r4]
            int r2 = r3.getSpanEnd(r2)
            r3 = r9
            goto L5a
        L36:
            int r3 = r10 + (-16)
            int r4 = java.lang.Math.max(r4, r3)
            int r3 = r9.length()
            int r5 = r10 + 16
            int r5 = java.lang.Math.min(r3, r5)
            androidx.emoji2.text.w r8 = new androidx.emoji2.text.w
            r8.<init>(r10)
            r6 = 2147483647(0x7fffffff, float:NaN)
            r7 = 1
            r3 = r9
            java.lang.Object r9 = r2.c(r3, r4, r5, r6, r7, r8)
            androidx.emoji2.text.w r9 = (androidx.emoji2.text.w) r9
            int r2 = r9.f2649c
            goto L5a
        L59:
            r2 = r0
        L5a:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r2)
            if (r2 != r0) goto L61
            goto L64
        L61:
            r1 = r9
            goto L64
        L63:
            r3 = r9
        L64:
            if (r1 == 0) goto L6b
            int r9 = r1.intValue()
            return r9
        L6b:
            java.text.BreakIterator r9 = java.text.BreakIterator.getCharacterInstance()
            r9.setText(r3)
            int r9 = r9.following(r10)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.StringHelpers_androidKt.findFollowingBreak(java.lang.String, int):int");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v1, types: [java.text.BreakIterator] */
    /* JADX WARN: Type inference failed for: r4v0, types: [androidx.emoji2.text.z, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v8, types: [java.lang.CharSequence] */
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
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int findPrecedingBreak(java.lang.String r11, int r12) {
        /*
            androidx.emoji2.text.o r0 = getEmojiCompatIfLoaded()
            r1 = 0
            if (r0 == 0) goto L69
            int r2 = r12 + (-1)
            r3 = 0
            int r2 = java.lang.Math.max(r3, r2)
            androidx.emoji2.text.g r0 = r0.f2631e
            androidx.emoji2.text.z r4 = r0.f2608b
            r4.getClass()
            r0 = -1
            if (r2 < 0) goto L1e
            int r5 = r11.length()
            if (r2 < r5) goto L20
        L1e:
            r5 = r11
            goto L5f
        L20:
            boolean r5 = r11 instanceof android.text.Spanned
            if (r5 == 0) goto L3c
            r5 = r11
            android.text.Spanned r5 = (android.text.Spanned) r5
            int r6 = r2 + 1
            java.lang.Class<androidx.emoji2.text.j0> r7 = androidx.emoji2.text.j0.class
            java.lang.Object[] r6 = r5.getSpans(r2, r6, r7)
            androidx.emoji2.text.j0[] r6 = (androidx.emoji2.text.j0[]) r6
            int r7 = r6.length
            if (r7 <= 0) goto L3c
            r2 = r6[r3]
            int r2 = r5.getSpanStart(r2)
            r5 = r11
            goto L60
        L3c:
            int r5 = r2 + (-16)
            int r6 = java.lang.Math.max(r3, r5)
            int r3 = r11.length()
            int r5 = r2 + 16
            int r7 = java.lang.Math.min(r3, r5)
            androidx.emoji2.text.w r10 = new androidx.emoji2.text.w
            r10.<init>(r2)
            r8 = 2147483647(0x7fffffff, float:NaN)
            r9 = 1
            r5 = r11
            java.lang.Object r11 = r4.c(r5, r6, r7, r8, r9, r10)
            androidx.emoji2.text.w r11 = (androidx.emoji2.text.w) r11
            int r2 = r11.f2648b
            goto L60
        L5f:
            r2 = r0
        L60:
            java.lang.Integer r11 = java.lang.Integer.valueOf(r2)
            if (r2 != r0) goto L67
            goto L6a
        L67:
            r1 = r11
            goto L6a
        L69:
            r5 = r11
        L6a:
            if (r1 == 0) goto L71
            int r11 = r1.intValue()
            return r11
        L71:
            java.text.BreakIterator r11 = java.text.BreakIterator.getCharacterInstance()
            r11.setText(r5)
            int r11 = r11.preceding(r12)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.StringHelpers_androidKt.findPrecedingBreak(java.lang.String, int):int");
    }

    private static final o getEmojiCompatIfLoaded() {
        if (!o.c()) {
            return null;
        }
        o oVarA = o.a();
        if (oVarA.b() == 1) {
            return oVarA;
        }
        return null;
    }
}
