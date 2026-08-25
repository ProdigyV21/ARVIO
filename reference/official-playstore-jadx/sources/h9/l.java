package h9;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.b0;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.m0;
import kotlin.jvm.internal.v;

/* JADX INFO: loaded from: classes5.dex */
public final class l implements j {
    public static final /* synthetic */ kotlin.reflect.m[] X;
    public final k A;
    public final k B;
    public final k C;
    public final k D;
    public final k E;
    public final k F;
    public final k G;
    public final k H;
    public final k I;
    public final k J;
    public final k K;
    public final k L;
    public final k M;
    public final k N;
    public final k O;
    public final k P;
    public final k Q;
    public final k R;
    public final k S;
    public final k T;
    public final k U;
    public final k V;
    public final k W;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f15927a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k f15928b = new k(b.f15897d, this);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final k f15929c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final k f15930d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final k f15931e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final k f15932f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final k f15933g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final k f15934h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final k f15935i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final k f15936j;
    public final k k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final k f15937l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final k f15938m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final k f15939n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final k f15940o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final k f15941p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final k f15942q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final k f15943r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final k f15944s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final k f15945t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final k f15946u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final k f15947v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final k f15948w;
    public final k x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final k f15949y;
    public final k z;

    static {
        m0 m0Var = l0.f19747a;
        X = new kotlin.reflect.m[]{m0Var.e(new v(m0Var.b(l.class), "classifierNamePolicy", "getClassifierNamePolicy()Lorg/jetbrains/kotlin/renderer/ClassifierNamePolicy;")), m0Var.e(new v(m0Var.b(l.class), "withDefinedIn", "getWithDefinedIn()Z")), m0Var.e(new v(m0Var.b(l.class), "withSourceFileForTopLevel", "getWithSourceFileForTopLevel()Z")), m0Var.e(new v(m0Var.b(l.class), "modifiers", "getModifiers()Ljava/util/Set;")), m0Var.e(new v(m0Var.b(l.class), "startFromName", "getStartFromName()Z")), m0Var.e(new v(m0Var.b(l.class), "startFromDeclarationKeyword", "getStartFromDeclarationKeyword()Z")), m0Var.e(new v(m0Var.b(l.class), "debugMode", "getDebugMode()Z")), m0Var.e(new v(m0Var.b(l.class), "classWithPrimaryConstructor", "getClassWithPrimaryConstructor()Z")), m0Var.e(new v(m0Var.b(l.class), "verbose", "getVerbose()Z")), m0Var.e(new v(m0Var.b(l.class), "unitReturnType", "getUnitReturnType()Z")), m0Var.e(new v(m0Var.b(l.class), "withoutReturnType", "getWithoutReturnType()Z")), m0Var.e(new v(m0Var.b(l.class), "enhancedTypes", "getEnhancedTypes()Z")), m0Var.e(new v(m0Var.b(l.class), "normalizedVisibilities", "getNormalizedVisibilities()Z")), m0Var.e(new v(m0Var.b(l.class), "renderDefaultVisibility", "getRenderDefaultVisibility()Z")), m0Var.e(new v(m0Var.b(l.class), "renderDefaultModality", "getRenderDefaultModality()Z")), m0Var.e(new v(m0Var.b(l.class), "renderConstructorDelegation", "getRenderConstructorDelegation()Z")), m0Var.e(new v(m0Var.b(l.class), "renderPrimaryConstructorParametersAsProperties", "getRenderPrimaryConstructorParametersAsProperties()Z")), m0Var.e(new v(m0Var.b(l.class), "actualPropertiesInPrimaryConstructor", "getActualPropertiesInPrimaryConstructor()Z")), m0Var.e(new v(m0Var.b(l.class), "uninferredTypeParameterAsName", "getUninferredTypeParameterAsName()Z")), m0Var.e(new v(m0Var.b(l.class), "includePropertyConstant", "getIncludePropertyConstant()Z")), m0Var.e(new v(m0Var.b(l.class), "propertyConstantRenderer", "getPropertyConstantRenderer()Lkotlin/jvm/functions/Function1;")), m0Var.e(new v(m0Var.b(l.class), "withoutTypeParameters", "getWithoutTypeParameters()Z")), m0Var.e(new v(m0Var.b(l.class), "withoutSuperTypes", "getWithoutSuperTypes()Z")), m0Var.e(new v(m0Var.b(l.class), "typeNormalizer", "getTypeNormalizer()Lkotlin/jvm/functions/Function1;")), m0Var.e(new v(m0Var.b(l.class), "defaultParameterValueRenderer", "getDefaultParameterValueRenderer()Lkotlin/jvm/functions/Function1;")), m0Var.e(new v(m0Var.b(l.class), "secondaryConstructorsAsPrimary", "getSecondaryConstructorsAsPrimary()Z")), m0Var.e(new v(m0Var.b(l.class), "overrideRenderingPolicy", "getOverrideRenderingPolicy()Lorg/jetbrains/kotlin/renderer/OverrideRenderingPolicy;")), m0Var.e(new v(m0Var.b(l.class), "valueParametersHandler", "getValueParametersHandler()Lorg/jetbrains/kotlin/renderer/DescriptorRenderer$ValueParametersHandler;")), m0Var.e(new v(m0Var.b(l.class), "textFormat", "getTextFormat()Lorg/jetbrains/kotlin/renderer/RenderingFormat;")), m0Var.e(new v(m0Var.b(l.class), "parameterNameRenderingPolicy", "getParameterNameRenderingPolicy()Lorg/jetbrains/kotlin/renderer/ParameterNameRenderingPolicy;")), m0Var.e(new v(m0Var.b(l.class), "receiverAfterName", "getReceiverAfterName()Z")), m0Var.e(new v(m0Var.b(l.class), "renderCompanionObjectName", "getRenderCompanionObjectName()Z")), m0Var.e(new v(m0Var.b(l.class), "propertyAccessorRenderingPolicy", "getPropertyAccessorRenderingPolicy()Lorg/jetbrains/kotlin/renderer/PropertyAccessorRenderingPolicy;")), m0Var.e(new v(m0Var.b(l.class), "renderDefaultAnnotationArguments", "getRenderDefaultAnnotationArguments()Z")), m0Var.e(new v(m0Var.b(l.class), "eachAnnotationOnNewLine", "getEachAnnotationOnNewLine()Z")), m0Var.e(new v(m0Var.b(l.class), "excludedAnnotationClasses", "getExcludedAnnotationClasses()Ljava/util/Set;")), m0Var.e(new v(m0Var.b(l.class), "excludedTypeAnnotationClasses", "getExcludedTypeAnnotationClasses()Ljava/util/Set;")), m0Var.e(new v(m0Var.b(l.class), "annotationFilter", "getAnnotationFilter()Lkotlin/jvm/functions/Function1;")), m0Var.e(new v(m0Var.b(l.class), "annotationArgumentsRenderingPolicy", "getAnnotationArgumentsRenderingPolicy()Lorg/jetbrains/kotlin/renderer/AnnotationArgumentsRenderingPolicy;")), m0Var.e(new v(m0Var.b(l.class), "alwaysRenderModifiers", "getAlwaysRenderModifiers()Z")), m0Var.e(new v(m0Var.b(l.class), "renderConstructorKeyword", "getRenderConstructorKeyword()Z")), m0Var.e(new v(m0Var.b(l.class), "renderUnabbreviatedType", "getRenderUnabbreviatedType()Z")), m0Var.e(new v(m0Var.b(l.class), "renderTypeExpansions", "getRenderTypeExpansions()Z")), m0Var.e(new v(m0Var.b(l.class), "includeAdditionalModifiers", "getIncludeAdditionalModifiers()Z")), m0Var.e(new v(m0Var.b(l.class), "parameterNamesInFunctionalTypes", "getParameterNamesInFunctionalTypes()Z")), m0Var.e(new v(m0Var.b(l.class), "renderFunctionContracts", "getRenderFunctionContracts()Z")), m0Var.e(new v(m0Var.b(l.class), "presentableUnresolvedTypes", "getPresentableUnresolvedTypes()Z")), m0Var.e(new v(m0Var.b(l.class), "boldOnlyForNamesInHtml", "getBoldOnlyForNamesInHtml()Z")), m0Var.e(new v(m0Var.b(l.class), "informativeErrorType", "getInformativeErrorType()Z"))};
    }

    public l() {
        Boolean bool = Boolean.TRUE;
        this.f15929c = new k(bool, this);
        this.f15930d = new k(bool, this);
        this.f15931e = new k(i.f15912l, this);
        Boolean bool2 = Boolean.FALSE;
        this.f15932f = new k(bool2, this);
        this.f15933g = new k(bool2, this);
        this.f15934h = new k(bool2, this);
        this.f15935i = new k(bool2, this);
        this.f15936j = new k(bool2, this);
        this.k = new k(bool, this);
        this.f15937l = new k(bool2, this);
        this.f15938m = new k(bool2, this);
        this.f15939n = new k(bool2, this);
        this.f15940o = new k(bool, this);
        this.f15941p = new k(bool, this);
        this.f15942q = new k(bool2, this);
        this.f15943r = new k(bool2, this);
        this.f15944s = new k(bool2, this);
        this.f15945t = new k(bool2, this);
        this.f15946u = new k(bool2, this);
        this.f15947v = new k(null, this);
        this.f15948w = new k(bool2, this);
        this.x = new k(bool2, this);
        this.f15949y = new k(g.f15905o, this);
        this.z = new k(g.f15904n, this);
        this.A = new k(bool, this);
        this.B = new k(o.f15953l, this);
        this.C = new k(d.f15899a, this);
        this.D = new k(t.f15962i, this);
        this.E = new k(p.f15955i, this);
        this.F = new k(bool2, this);
        this.G = new k(bool2, this);
        this.H = new k(q.f15959i, this);
        this.I = new k(bool2, this);
        this.J = new k(bool2, this);
        this.K = new k(b0.f19686i, this);
        this.L = new k(m.f15950a, this);
        this.M = new k(null, this);
        this.N = new k(a.NO_ARGUMENTS, this);
        this.O = new k(bool2, this);
        this.P = new k(bool, this);
        this.Q = new k(bool, this);
        this.R = new k(bool2, this);
        this.S = new k(bool, this);
        this.T = new k(bool, this);
        this.U = new k(bool2, this);
        this.V = new k(bool2, this);
        this.W = new k(bool, this);
    }

    @Override // h9.j
    public final void a() {
        this.F.setValue(this, X[30], Boolean.TRUE);
    }

    @Override // h9.j
    public final void b() {
        this.G.setValue(this, X[31], Boolean.TRUE);
    }

    @Override // h9.j
    public final void c(c cVar) {
        this.f15928b.setValue(this, X[0], cVar);
    }

    @Override // h9.j
    public final void d() {
        this.f15948w.setValue(this, X[21], Boolean.TRUE);
    }

    @Override // h9.j
    public final void e() {
        this.f15932f.setValue(this, X[4], Boolean.TRUE);
    }

    @Override // h9.j
    public final Set f() {
        return (Set) this.L.getValue(this, X[36]);
    }

    @Override // h9.j
    public final void g() {
        this.x.setValue(this, X[22], Boolean.TRUE);
    }

    @Override // h9.j
    public final void h() {
        this.f15934h.setValue(this, X[6], Boolean.TRUE);
    }

    @Override // h9.j
    public final void i(p pVar) {
        this.E.setValue(this, X[29], pVar);
    }

    @Override // h9.j
    public final void j(Set set) {
        this.f15931e.setValue(this, X[3], set);
    }

    @Override // h9.j
    public final void k(LinkedHashSet linkedHashSet) {
        this.L.setValue(this, X[36], linkedHashSet);
    }

    @Override // h9.j
    public final void l() {
        this.f15929c.setValue(this, X[1], Boolean.FALSE);
    }

    @Override // h9.j
    public final void m() {
        this.D.setValue(this, X[28], t.f15963l);
    }

    public final boolean n() {
        return ((Boolean) this.f15934h.getValue(this, X[6])).booleanValue();
    }
}
