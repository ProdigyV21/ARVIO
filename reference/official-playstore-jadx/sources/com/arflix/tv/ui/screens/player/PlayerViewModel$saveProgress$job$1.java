package com.arflix.tv.ui.screens.player;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerViewModel$saveProgress$job$1", f = "PlayerViewModel.kt", l = {3983, 3999, 4016, 4051, 4074, 4097, 4103, 4104, 4114, 4130, 4146, 4148, 4152, 4164, 4179, 4187, 4207, 4208}, m = "invokeSuspend", v = 2)
public final class PlayerViewModel$saveProgress$job$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ long $duration;
    final /* synthetic */ boolean $isPlaying;
    final /* synthetic */ int $playbackState;
    final /* synthetic */ long $position;
    final /* synthetic */ int $progressPercent;
    float F$0;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    long J$0;
    long J$1;
    long J$2;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    boolean Z$0;
    int label;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$saveProgress$job$1(int i10, PlayerViewModel playerViewModel, boolean z, long j10, long j11, int i11, d7.d<? super PlayerViewModel$saveProgress$job$1> dVar) {
        super(2, dVar);
        this.$progressPercent = i10;
        this.this$0 = playerViewModel;
        this.$isPlaying = z;
        this.$duration = j10;
        this.$position = j11;
        this.$playbackState = i11;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        PlayerViewModel$saveProgress$job$1 playerViewModel$saveProgress$job$1 = new PlayerViewModel$saveProgress$job$1(this.$progressPercent, this.this$0, this.$isPlaying, this.$duration, this.$position, this.$playbackState, dVar);
        playerViewModel$saveProgress$job$1.L$0 = obj;
        return playerViewModel$saveProgress$job$1;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(19:(1:657)|308|309|(1:643)|310|311|(1:665)|312|313|(1:647)|314|315|(1:649)|316|317|597|318|319|(2:321|703)(36:322|323|403|673|404|405|681|406|407|408|409|655|410|(0)|413|414|418|(0)|512|513|695|514|515|693|516|517|689|518|(0)|521|524|611|525|(0)|531|532)) */
    /* JADX WARN: Can't wrap try/catch for region: R(24:334|335|(1:639)|336|337|(1:629)|338|339|(1:631)|340|341|(1:653)|342|343|(1:663)|344|345|(1:667)|346|347|603|348|349|(1:708)(18:352|353|403|673|404|405|681|406|407|408|409|655|410|(2:412|704)|413|414|418|(18:512|513|695|514|515|693|516|517|689|518|(1:711)|521|524|611|525|(1:706)|531|532)(15:685|422|605|423|424|601|425|426|599|427|428|587|429|430|(2:432|705)(30:433|683|434|(5:436|(1:438)(1:442)|443|(1:445)(1:446)|447)(1:449)|450|659|461|(1:463)|464|(2:574|466)(1:469)|(1:471)(2:473|474)|472|589|475|(3:687|477|478)|480|(1:482)(1:484)|485|(1:487)(1:489)|490|491|671|492|493|494|495|619|496|497|(1:710)(20:500|501|502|513|695|514|515|693|516|517|689|518|(0)|521|524|611|525|(0)|531|532))))) */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x0898, code lost:
    
        if (com.arflix.tv.data.repository.CloudSyncRepository.m6121pushToCloudgIAlus$default(r8, false, r7, 1, null) == r3) goto L207;
     */
    /* JADX WARN: Code restructure failed: missing block: B:237:0x092c, code lost:
    
        if (com.arflix.tv.data.repository.CloudSyncRepository.m6121pushToCloudgIAlus$default(r8, false, r7, 1, null) == r3) goto L207;
     */
    /* JADX WARN: Code restructure failed: missing block: B:249:0x0977, code lost:
    
        if (r8.refreshForCurrentProfile(r7) == r3) goto L207;
     */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x0188: MOVE (r19 I:??[long, double]) = (r11 I:??[long, double]), block:B:28:0x0184 */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x01fc: MOVE (r7 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r11 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:32:0x01e4 */
    /* JADX WARN: Not initialized variable reg: 12, insn: 0x01ec: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r12 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:32:0x01e4 */
    /* JADX WARN: Not initialized variable reg: 13, insn: 0x01ed: MOVE (r8 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r13 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:32:0x01e4 */
    /* JADX WARN: Not initialized variable reg: 14, insn: 0x018a: MOVE (r12 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r14 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:28:0x0184 */
    /* JADX WARN: Not initialized variable reg: 15, insn: 0x0190: MOVE (r7 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r15 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:28:0x0184 */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x01e6: MOVE (r17 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r2 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:32:0x01e4 */
    /* JADX WARN: Not initialized variable reg: 21, insn: 0x01ee: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r21 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:32:0x01e4 */
    /* JADX WARN: Not initialized variable reg: 22, insn: 0x01f0: MOVE (r3 I:??[OBJECT, ARRAY]) = (r22 I:??[OBJECT, ARRAY]), block:B:32:0x01e4 */
    /* JADX WARN: Not initialized variable reg: 23, insn: 0x01f2: MOVE (r2 I:??[OBJECT, ARRAY]) = (r23 I:??[OBJECT, ARRAY]), block:B:32:0x01e4 */
    /* JADX WARN: Not initialized variable reg: 24, insn: 0x01fd: MOVE (r6 I:??[OBJECT, ARRAY]) = (r24 I:??[OBJECT, ARRAY]), block:B:32:0x01e4 */
    /* JADX WARN: Not initialized variable reg: 3, insn: 0x01e8: MOVE (r19 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r3 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:32:0x01e4 */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x01f7: MOVE (r9 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:32:0x01e4 */
    /* JADX WARN: Not initialized variable reg: 5, insn: 0x018d: MOVE (r38 I:??[long, double]) = (r5 I:??[long, double]), block:B:28:0x0184 */
    /* JADX WARN: Not initialized variable reg: 5, insn: 0x01f9: MOVE (r38 I:??[long, double]) = (r5 I:??[long, double]), block:B:32:0x01e4 */
    /* JADX WARN: Not initialized variable reg: 9, insn: 0x0191: MOVE (r14 I:??[long, double]) = (r9 I:??[long, double]), block:B:28:0x0184 */
    /* JADX WARN: Not initialized variable reg: 9, insn: 0x01f6: MOVE (r12 I:??[long, double]) = (r9 I:??[long, double]), block:B:32:0x01e4 */
    /* JADX WARN: Path cross not found for [B:157:0x061e, B:160:0x0632], limit reached: 691 */
    /* JADX WARN: Path cross not found for [B:160:0x0632, B:157:0x061e], limit reached: 691 */
    /* JADX WARN: Path cross not found for [B:163:0x0637, B:168:0x064e], limit reached: 691 */
    /* JADX WARN: Path cross not found for [B:168:0x064e, B:163:0x0637], limit reached: 691 */
    /* JADX WARN: Removed duplicated region for block: B:105:0x050e  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0578  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x05ee  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x05f8  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x05fa  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x061e  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0637  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0651 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:199:0x074f  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0768  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x085b  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x08aa  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x08ae  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x08d3  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x09ab  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0a3d  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0a41  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x0b04  */
    /* JADX WARN: Removed duplicated region for block: B:366:0x0c57  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x0cba  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x0cda  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x0d11  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x0d76  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x0dad A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:436:0x0e06 A[Catch: all -> 0x0e11, TryCatch #67 {all -> 0x0e11, blocks: (B:434:0x0e02, B:436:0x0e06, B:438:0x0e0c, B:443:0x0e21, B:445:0x0e27, B:447:0x0e30, B:446:0x0e2c, B:442:0x0e1d), top: B:683:0x0e02 }] */
    /* JADX WARN: Removed duplicated region for block: B:449:0x0e35  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x0e68  */
    /* JADX WARN: Removed duplicated region for block: B:469:0x0e7a A[Catch: Exception -> 0x0fa6, TRY_ENTER, TRY_LEAVE, TryCatch #55 {Exception -> 0x0fa6, blocks: (B:461:0x0e62, B:464:0x0e69, B:473:0x0e87, B:469:0x0e7a), top: B:659:0x0e62 }] */
    /* JADX WARN: Removed duplicated region for block: B:471:0x0e80 A[Catch: Exception -> 0x0e72, TRY_ENTER, TRY_LEAVE, TryCatch #11 {Exception -> 0x0e72, blocks: (B:466:0x0e6d, B:471:0x0e80), top: B:574:0x0e6d }] */
    /* JADX WARN: Removed duplicated region for block: B:473:0x0e87 A[Catch: Exception -> 0x0fa6, TRY_ENTER, TRY_LEAVE, TryCatch #55 {Exception -> 0x0fa6, blocks: (B:461:0x0e62, B:464:0x0e69, B:473:0x0e87, B:469:0x0e7a), top: B:659:0x0e62 }] */
    /* JADX WARN: Removed duplicated region for block: B:482:0x0edd A[Catch: Exception -> 0x0ebd, TRY_ENTER, TRY_LEAVE, TryCatch #69 {Exception -> 0x0ebd, blocks: (B:477:0x0eb6, B:482:0x0edd, B:487:0x0eea), top: B:687:0x0eb6 }] */
    /* JADX WARN: Removed duplicated region for block: B:484:0x0ee2  */
    /* JADX WARN: Removed duplicated region for block: B:487:0x0eea A[Catch: Exception -> 0x0ebd, TRY_ENTER, TRY_LEAVE, TryCatch #69 {Exception -> 0x0ebd, blocks: (B:477:0x0eb6, B:482:0x0edd, B:487:0x0eea), top: B:687:0x0eb6 }] */
    /* JADX WARN: Removed duplicated region for block: B:489:0x0eef  */
    /* JADX WARN: Removed duplicated region for block: B:500:0x0f6d  */
    /* JADX WARN: Removed duplicated region for block: B:528:0x1056  */
    /* JADX WARN: Removed duplicated region for block: B:529:0x1057  */
    /* JADX WARN: Removed duplicated region for block: B:574:0x0e6d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:675:0x0b29 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:687:0x0eb6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:706:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:707:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:710:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:711:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:377:0x0c9a -> B:378:0x0c9c). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:387:0x0cba -> B:378:0x0c9c). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r43) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 4238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerViewModel$saveProgress$job$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerViewModel$saveProgress$job$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
