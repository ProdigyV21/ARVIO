package i9;

import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.flac.FlacConstants;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.ui.theme.AnimationConstants;
import com.arflix.tv.util.Constants;
import g8.b0;
import g8.h0;
import g8.m0;
import g8.r0;
import g8.w0;
import g8.y0;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import v9.o0;
import v9.w;

/* JADX INFO: loaded from: classes5.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f16249a = 0;

    static {
        new f9.c("kotlin.jvm.JvmName");
    }

    public static /* synthetic */ void a(int i10) {
        String str;
        int i11;
        switch (i10) {
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
            case 43:
            case 47:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 60:
            case 62:
            case ColorSpace.MaxId /* 63 */:
            case 65:
            case 72:
            case 76:
            case 83:
            case 84:
            case 86:
            case TsExtractor.TS_STREAM_TYPE_DVBSUBS /* 89 */:
            case 94:
            case 96:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i10) {
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
            case 43:
            case 47:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 60:
            case 62:
            case ColorSpace.MaxId /* 63 */:
            case 65:
            case 72:
            case 76:
            case 83:
            case 84:
            case 86:
            case TsExtractor.TS_STREAM_TYPE_DVBSUBS /* 89 */:
            case 94:
            case 96:
                i11 = 2;
                break;
            default:
                i11 = 3;
                break;
        }
        Object[] objArr = new Object[i11];
        switch (i10) {
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 8:
            case 11:
            case 13:
            case 14:
            case 15:
            case 21:
            case 23:
            case 24:
            case 34:
            case 35:
            case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
            case 57:
            case 58:
            case 59:
            case 61:
            case 64:
            case 82:
            case 95:
            case 97:
                objArr[0] = "descriptor";
                break;
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
            case 43:
            case 47:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 60:
            case 62:
            case ColorSpace.MaxId /* 63 */:
            case 65:
            case 72:
            case 76:
            case 83:
            case 84:
            case 86:
            case TsExtractor.TS_STREAM_TYPE_DVBSUBS /* 89 */:
            case 94:
            case 96:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorUtils";
                break;
            case 16:
                objArr[0] = "first";
                break;
            case 17:
                objArr[0] = "second";
                break;
            case 18:
            case 19:
                objArr[0] = "aClass";
                break;
            case 20:
                objArr[0] = "kotlinType";
                break;
            case 25:
                objArr[0] = "declarationDescriptor";
                break;
            case 26:
            case 28:
                objArr[0] = "subClass";
                break;
            case 27:
            case 29:
            case 33:
                objArr[0] = "superClass";
                break;
            case 30:
            case 32:
            case TsExtractor.TS_STREAM_TYPE_MHAS /* 45 */:
            case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                objArr[0] = LinkHeader.Parameters.Type;
                break;
            case 31:
                objArr[0] = "other";
                break;
            case 37:
                objArr[0] = "classKind";
                break;
            case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
            case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
            case 41:
            case 44:
            case 48:
            case 54:
            case 68:
            case 69:
            case 70:
            case 77:
            case MdtaMetadataEntry.TYPE_INDICATOR_UNSIGNED_INT64 /* 78 */:
                objArr[0] = "classDescriptor";
                break;
            case 46:
                objArr[0] = "typeConstructor";
                break;
            case 55:
                objArr[0] = "innerClassName";
                break;
            case 56:
                objArr[0] = "location";
                break;
            case 66:
                objArr[0] = "variable";
                break;
            case TsExtractor.TS_SYNC_BYTE /* 71 */:
                objArr[0] = "f";
                break;
            case 73:
                objArr[0] = "current";
                break;
            case 74:
                objArr[0] = "result";
                break;
            case 75:
                objArr[0] = "memberDescriptor";
                break;
            case 79:
            case AnimationConstants.STAGGER_SECTION /* 80 */:
            case 81:
                objArr[0] = "annotated";
                break;
            case 85:
            case 87:
            case Constants.WATCHED_THRESHOLD /* 90 */:
            case 92:
                objArr[0] = "scope";
                break;
            case 88:
            case 91:
            case 93:
                objArr[0] = ContentDisposition.Parameters.Name;
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        switch (i10) {
            case 4:
                objArr[1] = "getFqNameSafe";
                break;
            case 7:
                objArr[1] = "getFqNameUnsafe";
                break;
            case 9:
            case 10:
                objArr[1] = "getFqNameFromTopLevelClass";
                break;
            case 12:
                objArr[1] = "getClassIdForNonLocalClass";
                break;
            case 22:
                objArr[1] = "getContainingModule";
                break;
            case 40:
                objArr[1] = "getSuperclassDescriptors";
                break;
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
            case 43:
                objArr[1] = "getSuperClassType";
                break;
            case 47:
                objArr[1] = "getClassDescriptorForTypeConstructor";
                break;
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
                objArr[1] = "getDefaultConstructorVisibility";
                break;
            case 60:
                objArr[1] = "unwrapFakeOverride";
                break;
            case 62:
            case ColorSpace.MaxId /* 63 */:
                objArr[1] = "unwrapSubstitutionOverride";
                break;
            case 65:
                objArr[1] = "unwrapFakeOverrideToAnyDeclaration";
                break;
            case 72:
                objArr[1] = "getAllOverriddenDescriptors";
                break;
            case 76:
                objArr[1] = "getAllOverriddenDeclarations";
                break;
            case 83:
            case 84:
                objArr[1] = "getContainingSourceFile";
                break;
            case 86:
                objArr[1] = "getAllDescriptors";
                break;
            case TsExtractor.TS_STREAM_TYPE_DVBSUBS /* 89 */:
                objArr[1] = "getFunctionByName";
                break;
            case 94:
                objArr[1] = "getPropertyByName";
                break;
            case 96:
                objArr[1] = "getDirectMember";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorUtils";
                break;
        }
        switch (i10) {
            case 1:
                objArr[2] = "isLocal";
                break;
            case 2:
                objArr[2] = "getFqName";
                break;
            case 3:
                objArr[2] = "getFqNameSafe";
                break;
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
            case 43:
            case 47:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 60:
            case 62:
            case ColorSpace.MaxId /* 63 */:
            case 65:
            case 72:
            case 76:
            case 83:
            case 84:
            case 86:
            case TsExtractor.TS_STREAM_TYPE_DVBSUBS /* 89 */:
            case 94:
            case 96:
                break;
            case 5:
                objArr[2] = "getFqNameSafeIfPossible";
                break;
            case 6:
                objArr[2] = "getFqNameUnsafe";
                break;
            case 8:
                objArr[2] = "getFqNameFromTopLevelClass";
                break;
            case 11:
                objArr[2] = "getClassIdForNonLocalClass";
                break;
            case 13:
                objArr[2] = "isExtension";
                break;
            case 14:
                objArr[2] = "isOverride";
                break;
            case 15:
                objArr[2] = "isStaticDeclaration";
                break;
            case 16:
            case 17:
                objArr[2] = "areInSameModule";
                break;
            case 18:
            case 19:
                objArr[2] = "getParentOfType";
                break;
            case 20:
            case 23:
                objArr[2] = "getContainingModuleOrNull";
                break;
            case 21:
                objArr[2] = "getContainingModule";
                break;
            case 24:
                objArr[2] = "getContainingClass";
                break;
            case 25:
                objArr[2] = "isAncestor";
                break;
            case 26:
            case 27:
                objArr[2] = "isDirectSubclass";
                break;
            case 28:
            case 29:
                objArr[2] = "isSubclass";
                break;
            case 30:
            case 31:
                objArr[2] = "isSameClass";
                break;
            case 32:
            case 33:
                objArr[2] = "isSubtypeOfClass";
                break;
            case 34:
                objArr[2] = "isAnonymousObject";
                break;
            case 35:
                objArr[2] = "isAnonymousFunction";
                break;
            case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
                objArr[2] = "isEnumEntry";
                break;
            case 37:
                objArr[2] = "isKindOf";
                break;
            case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
                objArr[2] = "hasAbstractMembers";
                break;
            case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
                objArr[2] = "getSuperclassDescriptors";
                break;
            case 41:
                objArr[2] = "getSuperClassType";
                break;
            case 44:
                objArr[2] = "getSuperClassDescriptor";
                break;
            case TsExtractor.TS_STREAM_TYPE_MHAS /* 45 */:
                objArr[2] = "getClassDescriptorForType";
                break;
            case 46:
                objArr[2] = "getClassDescriptorForTypeConstructor";
                break;
            case 48:
                objArr[2] = "getDefaultConstructorVisibility";
                break;
            case 54:
            case 55:
            case 56:
                objArr[2] = "getInnerClassByName";
                break;
            case 57:
                objArr[2] = "isStaticNestedClass";
                break;
            case 58:
                objArr[2] = "isTopLevelOrInnerClass";
                break;
            case 59:
                objArr[2] = "unwrapFakeOverride";
                break;
            case 61:
                objArr[2] = "unwrapSubstitutionOverride";
                break;
            case 64:
                objArr[2] = "unwrapFakeOverrideToAnyDeclaration";
                break;
            case 66:
            case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                objArr[2] = "shouldRecordInitializerForProperty";
                break;
            case 68:
                objArr[2] = "classCanHaveAbstractFakeOverride";
                break;
            case 69:
                objArr[2] = "classCanHaveAbstractDeclaration";
                break;
            case 70:
                objArr[2] = "classCanHaveOpenMembers";
                break;
            case TsExtractor.TS_SYNC_BYTE /* 71 */:
                objArr[2] = "getAllOverriddenDescriptors";
                break;
            case 73:
            case 74:
                objArr[2] = "collectAllOverriddenDescriptors";
                break;
            case 75:
                objArr[2] = "getAllOverriddenDeclarations";
                break;
            case 77:
                objArr[2] = "isSingletonOrAnonymousObject";
                break;
            case MdtaMetadataEntry.TYPE_INDICATOR_UNSIGNED_INT64 /* 78 */:
                objArr[2] = "canHaveDeclaredConstructors";
                break;
            case 79:
                objArr[2] = "getJvmName";
                break;
            case AnimationConstants.STAGGER_SECTION /* 80 */:
                objArr[2] = "findJvmNameAnnotation";
                break;
            case 81:
                objArr[2] = "hasJvmNameAnnotation";
                break;
            case 82:
                objArr[2] = "getContainingSourceFile";
                break;
            case 85:
                objArr[2] = "getAllDescriptors";
                break;
            case 87:
            case 88:
                objArr[2] = "getFunctionByName";
                break;
            case Constants.WATCHED_THRESHOLD /* 90 */:
            case 91:
                objArr[2] = "getFunctionByNameOrNull";
                break;
            case 92:
            case 93:
                objArr[2] = "getPropertyByName";
                break;
            case 95:
                objArr[2] = "getDirectMember";
                break;
            case 97:
                objArr[2] = "isMethodOfAny";
                break;
            default:
                objArr[2] = "getDispatchReceiverParameterIfNeeded";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i10) {
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
            case 43:
            case 47:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 60:
            case 62:
            case ColorSpace.MaxId /* 63 */:
            case 65:
            case 72:
            case 76:
            case 83:
            case 84:
            case 86:
            case TsExtractor.TS_STREAM_TYPE_DVBSUBS /* 89 */:
            case 94:
            case 96:
                throw new IllegalStateException(str2);
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    public static void b(g8.a aVar, LinkedHashSet linkedHashSet) {
        if (aVar == null) {
            a(73);
            throw null;
        }
        if (linkedHashSet.contains(aVar)) {
            return;
        }
        Iterator it = aVar.a().k().iterator();
        while (it.hasNext()) {
            g8.a aVarA = ((g8.a) it.next()).a();
            b(aVarA, linkedHashSet);
            linkedHashSet.add(aVarA);
        }
    }

    public static g8.f c(w wVar) {
        if (wVar == null) {
            a(45);
            throw null;
        }
        o0 o0VarI0 = wVar.I0();
        if (o0VarI0 == null) {
            a(46);
            throw null;
        }
        g8.f fVar = (g8.f) o0VarI0.e();
        if (fVar != null) {
            return fVar;
        }
        a(47);
        throw null;
    }

    public static b0 d(g8.k kVar) {
        if (kVar == null) {
            a(21);
            throw null;
        }
        b0 b0VarE = e(kVar);
        if (b0VarE != null) {
            return b0VarE;
        }
        a(22);
        throw null;
    }

    public static b0 e(g8.k kVar) {
        if (kVar == null) {
            a(23);
            throw null;
        }
        while (kVar != null) {
            if (kVar instanceof b0) {
                return (b0) kVar;
            }
            if (kVar instanceof m0) {
                return ((m0) kVar).x0();
            }
            kVar = kVar.d();
        }
        return null;
    }

    public static w0 f(g8.k kVar) {
        if (kVar == null) {
            a(82);
            throw null;
        }
        if (kVar instanceof r0) {
            kVar = ((r0) kVar).T();
        }
        boolean z = kVar instanceof g8.n;
        y0 y0Var = w0.f15555c;
        if (z) {
            ((g8.n) kVar).f().getClass();
        }
        return y0Var;
    }

    public static f9.e g(g8.k kVar) {
        if (kVar != null) {
            f9.c cVarH = h(kVar);
            return cVarH != null ? cVarH.i() : g(kVar.d()).b(kVar.getName());
        }
        a(2);
        throw null;
    }

    public static f9.c h(g8.k kVar) {
        if (kVar == null) {
            a(5);
            throw null;
        }
        if ((kVar instanceof b0) || x9.k.e(kVar)) {
            return f9.c.f15358c;
        }
        if (kVar instanceof m0) {
            return ((m0) kVar).c();
        }
        if (kVar instanceof h0) {
            return ((h0) kVar).c();
        }
        return null;
    }

    public static g8.k i(g8.k kVar, Class cls, boolean z) {
        if (kVar == null) {
            return null;
        }
        if (z) {
            kVar = kVar.d();
        }
        while (kVar != null) {
            if (cls.isInstance(kVar)) {
                return kVar;
            }
            kVar = kVar.d();
        }
        return null;
    }

    public static g8.f j(g8.f fVar) {
        if (fVar == null) {
            a(44);
            throw null;
        }
        Iterator it = fVar.g().d().iterator();
        while (it.hasNext()) {
            g8.f fVarC = c((w) it.next());
            if (fVarC.p0() != 2) {
                return fVarC;
            }
        }
        return null;
    }

    public static boolean k(g8.k kVar) {
        return n(kVar, 1) && kVar.getName().equals(f9.h.f15372a);
    }

    public static boolean l(g8.k kVar) {
        return n(kVar, 6) && ((g8.f) kVar).Y();
    }

    public static boolean m(g8.k kVar) {
        if (kVar != null) {
            return n(kVar, 4);
        }
        a(36);
        throw null;
    }

    public static boolean n(g8.k kVar, int i10) {
        if (i10 != 0) {
            return (kVar instanceof g8.f) && ((g8.f) kVar).p0() == i10;
        }
        a(37);
        throw null;
    }

    public static boolean o(g8.k kVar) {
        if (kVar == null) {
            a(1);
            throw null;
        }
        while (kVar != null) {
            if (k(kVar) || ((kVar instanceof g8.o) && ((g8.o) kVar).getVisibility() == g8.q.f15529f)) {
                return true;
            }
            kVar = kVar.d();
        }
        return false;
    }

    public static boolean p(w wVar, g8.k kVar) {
        if (wVar == null) {
            a(30);
            throw null;
        }
        if (kVar == null) {
            a(31);
            throw null;
        }
        g8.h hVarE = wVar.I0().e();
        if (hVarE == null) {
            return false;
        }
        g8.k kVarA = hVarE.a();
        return (kVarA instanceof g8.h) && (kVar instanceof g8.h) && ((g8.h) kVar).g().equals(((g8.h) kVarA).g());
    }

    public static boolean q(g8.k kVar) {
        return (n(kVar, 1) || n(kVar, 2)) && ((g8.f) kVar).o() == 2;
    }

    public static boolean r(w wVar, g8.k kVar) {
        if (wVar == null) {
            a(32);
            throw null;
        }
        if (kVar == null) {
            a(33);
            throw null;
        }
        if (p(wVar, kVar)) {
            return true;
        }
        Iterator it = wVar.I0().d().iterator();
        while (it.hasNext()) {
            if (r((w) it.next(), kVar)) {
                return true;
            }
        }
        return false;
    }

    public static boolean s(g8.k kVar) {
        return kVar != null && (kVar.d() instanceof h0);
    }

    public static g8.c t(g8.c cVar) {
        if (cVar == null) {
            a(59);
            throw null;
        }
        while (cVar.getKind() == 2) {
            Collection collectionK = cVar.k();
            if (collectionK.isEmpty()) {
                throw new IllegalStateException("Fake override should have at least one overridden descriptor: " + cVar);
            }
            cVar = (g8.c) collectionK.iterator().next();
        }
        return cVar;
    }
}
