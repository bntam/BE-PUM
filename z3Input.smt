(benchmark program
 :status sat
 :logic QF_LIA
:extrafuns ((eax BitVec[32])(TOP BitVec[32]))
:formula(and ( not  (not(= (bvslt (bvsub eax bv0[32]) bv0[32]) (or (and (bvslt eax bv0[32]) (and (bvsge bv0[32] bv0[32]) (bvsgt (bvsub eax bv0[32]) bv0[32]))) (and (bvsge eax bv0[32]) (and (bvslt bv0[32] bv0[32]) (bvslt (bvsub eax bv0[32]) bv0[32])))))))( not  (and (= (= (bvsub eax bv2[32]) bv0[32]) false) (= (bvslt (bvsub eax bv2[32]) bv0[32]) (or (and (bvslt eax bv0[32]) (and (bvsge bv2[32] bv0[32]) (bvsgt (bvsub eax bv2[32]) bv0[32]))) (and (bvsge eax bv0[32]) (and (bvslt bv2[32] bv0[32]) (bvslt (bvsub eax bv2[32]) bv0[32])))))))( not  (= (bvadd (bvmul eax bv8[32]) bv4198433[32]) bv4198433[32]))( not  (= (bvadd (bvmul eax bv8[32]) bv4198433[32]) bv4198449[32]))( not  (= (bvadd (bvmul eax bv8[32]) bv4198433[32]) bv4198441[32])) )
)
