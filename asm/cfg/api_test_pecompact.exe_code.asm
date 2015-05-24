0x00401000:	movl %eax, $0x404858<UINT32>
0x00401005:	pushl %eax
0x00401006:	pushl %fs:0
0x0040100d:	movl %fs:0, %esp
0x00401014:	xorl %eax, %eax
0x00401016:	movl (%eax), %ecx
0x00404858:	movl %eax, $0xf04035dd<UINT32>
0x0040485d:	leal %ecx, 0x1000129e(%eax)
0x00404863:	movl 0x1(%ecx), %eax
0x00404866:	movl %edx, 0x4(%esp)
0x0040486a:	movl %edx, 0xc(%edx)
0x0040486d:	movb (%edx), $0xffffffe9<UINT8>
0x00404870:	addl %edx, $0x5<UINT8>
0x00404873:	subl %ecx, %edx
0x00404875:	movl -4(%edx), %ecx
0x00404878:	xorl %eax, %eax
0x0040487a:	ret

0x00401016:	jmp 0x0040487b
0x0040487b:	movl %eax, $0xf04035dd<UINT32>
0x00404880:	popl %fs:0
0x00404887:	addl %esp, $0x4<UINT8>
0x0040488a:	pushl %ebp
0x0040488b:	pushl %ebx
0x0040488c:	pushl %ecx
0x0040488d:	pushl %edi
0x0040488e:	pushl %esi
0x0040488f:	pushl %edx
0x00404890:	leal %ebx, 0x10001257(%eax)
0x00404896:	movl %edx, 0x18(%ebx)
0x00404899:	pushl %edx
0x0040489a:	movl %ebp, %eax
0x0040489c:	pushl $0x40<UINT8>
0x0040489e:	pushl $0x1000<UINT32>
0x004048a3:	pushl 0x4(%ebx)
0x004048a6:	pushl $0x0<UINT8>
0x004048a8:	movl %ecx, 0x10(%ebx)
0x004048ab:	addl %ecx, %edx
0x004048ad:	movl %eax, (%ecx)
0x004048af:	call VirtualAlloc@kernel32.dll
VirtualAlloc@kernel32.dll: API Node	
0x004048b1:	popl %edx
0x004048b2:	movl %edi, %eax
0x004048b4:	pushl %eax
0x004048b5:	pushl %edx
0x004048b6:	movl %esi, (%ebx)
0x004048b8:	movl %eax, 0x20(%ebx)
0x004048bb:	addl %eax, %edx
0x004048bd:	movl %ecx, (%eax)
0x004048bf:	movl 0x20(%ebx), %ecx
0x004048c2:	movl %eax, 0x1c(%ebx)
0x004048c5:	addl %eax, %edx
0x004048c7:	movl %ecx, (%eax)
0x004048c9:	movl 0x1c(%ebx), %ecx
0x004048cc:	addl %esi, %edx
0x004048ce:	movl %ecx, 0xc(%ebx)
0x004048d1:	addl %ecx, %edx
0x004048d3:	leal %eax, 0x1c(%ebx)
0x004048d6:	pushl %eax
0x004048d7:	pushl %edi
0x004048d8:	pushl %esi
0x004048d9:	call 0x0040477b
0x0040477b:	pusha
0x0040477c:	movl %esi, 0x24(%esp)
0x00404780:	movl %edi, 0x28(%esp)
0x00404784:	cld
0x00404785:	lodsl %eax, %ds:(%esi)
0x00404786:	xorl %ecx, %ecx
0x00404788:	testl %eax, %eax
0x0040478a:	je 17
0x0040478c:	xorl %edx, %edx
0x0040478e:	leal %ebx, (%eax,%edi)
0x00404791:	movsb %es:(%edi), %ds:(%esi)
0x00404792:	movb %cl, $0x3<UINT8>
0x00404794:	call 0x0040480b
0x0040480b:	addl %edx, %edx
0x0040480d:	jne 0x00404815
0x0040480f:	xchgl %edx, %eax
0x00404810:	lodsl %eax, %ds:(%esi)
0x00404811:	xchgl %edx, %eax
0x00404812:	addl %edx, %edx
0x00404814:	incl %edx
0x00404815:	ret

0x00404799:	jae 0x00404791
0x0040479b:	cmpl %edi, %ebx
0x0040479d:	jae 0x00404828
0x004047a3:	pushl %ebx
0x004047a4:	pushl %ebp
0x004047a5:	pushl %edi
0x004047a6:	xorl %ebx, %ebx
0x004047a8:	incl %ebx
0x004047a9:	xorl %ebp, %ebp
0x004047ab:	movl %eax, %ebx
0x004047ad:	leal %edi, (%ebp,%ebx)
0x004047b1:	movl %ebp, %ebx
0x004047b3:	movl %ebx, %edi
0x004047b5:	call 0x0040480b
0x004047ba:	jae 0x004047ad
0x004047bc:	leal %ebx, (%ebp,%edi)
0x004047c0:	addl %eax, %edi
0x004047c2:	movl %ebp, %edi
0x004047c4:	call 0x0040480b
0x004047c9:	jae 0x004047ad
0x004047cb:	popl %edi
0x004047cc:	popl %ebp
0x004047cd:	popl %ebx
0x004047ce:	subl %eax, %ecx
0x004047d0:	jae 0x004047db
0x004047db:	movb %cl, $0x6<UINT8>
0x004047dd:	call 0x0040480b
0x004047e2:	adcl %eax, %eax
0x004047e4:	decl %ecx
0x004047e5:	jne 0x004047dd
0x004047e7:	incl %eax
0x004047e8:	call 0x00404816
0x00404816:	xorl %ecx, %ecx
0x00404818:	incl %ecx
0x00404819:	call 0x0040480b
0x0040481e:	adcl %ecx, %ecx
0x00404820:	call 0x0040480b
0x00404825:	jb 0x00404819
0x00404827:	ret

0x004047ed:	movl %ebp, %eax
0x004047ef:	cmpl %eax, $0x8001<UINT32>
0x004047f4:	sbbl %ecx, $0xffffffff<UINT8>
0x004047f7:	cmpl %eax, $0x781<UINT32>
0x004047fc:	sbbl %ecx, $0xffffffff<UINT8>
0x004047ff:	pushl %esi
0x00404800:	movl %esi, %edi
0x00404802:	subl %esi, %eax
0x00404804:	rep movsb %es:(%edi), %ds:(%esi)
0x00404806:	popl %esi
0x00404807:	incl %ecx
0x00404808:	incl %ecx
0x00404809:	jmp 0x00404794
0x004047d2:	movl %eax, %ebp
0x004047d4:	call 0x00404816
0x004047d9:	jmp 0x004047ff
0x00404828:	subl %edi, 0x28(%esp)
0x0040482c:	movl 0x1c(%esp), %edi
0x00404830:	popa
0x00404831:	ret $0xc<UINT16>

0x004048db:	popl %edx
0x004048dc:	popl %eax
0x004048dd:	addl %eax, 0x8(%ebx)
0x004048e0:	movl %edi, %eax
0x004048e2:	pushl %edx
0x004048e3:	movl %esi, %eax
0x004048e5:	movl %eax, -4(%esi)
0x004048e8:	addl %eax, $0x4<UINT8>
0x004048eb:	subl %esi, %eax
0x004048ed:	movl 0x8(%esi), %edx
0x004048f0:	movl %ecx, 0xc(%ebx)
0x004048f3:	movl 0x14(%esi), %ecx
0x004048f6:	call 0x02990188
0x02990188:	pushl %ebx
0x02990189:	pushl %edi
0x0299018a:	pushl %esi
0x0299018b:	pushl %ebp
0x0299018c:	call 0x02990191
0x02990191:	popl %ebp
0x02990192:	subl %ebp, $0x1000134c<UINT32>
0x02990198:	leal %esi, 0x10001343(%ebp)
0x0299019e:	movl %eax, -4(%esi)
0x029901a1:	addl %eax, $0x4<UINT8>
0x029901a4:	subl %esi, %eax
0x029901a6:	cld
0x029901a7:	movl %ebx, %esi
0x029901a9:	movl %edx, 0x8(%esi)
0x029901ac:	movl %esi, 0x1c(%esi)
0x029901af:	addl %esi, %edx
0x029901b1:	leal %edi, 0x10002f2f(%ebp)
0x029901b7:	lodsl %eax, %ds:(%esi)
0x029901b8:	stosl %es:(%edi), %eax
0x029901b9:	lodsl %eax, %ds:(%esi)
0x029901ba:	stosl %es:(%edi), %eax
0x029901bb:	lodsl %eax, %ds:(%esi)
0x029901bc:	stosl %es:(%edi), %eax
0x029901bd:	lodsl %eax, %ds:(%esi)
0x029901be:	stosl %es:(%edi), %eax
0x029901bf:	nop
0x029901c0:	cmpl 0x48(%ebx), $0x1<UINT8>
0x029901c4:	je 21
0x029901c6:	movl %esi, 0x44(%ebx)
0x029901c9:	testl %esi, %esi
0x029901cb:	je 14
0x029901cd:	movl %ecx, $0x23<UINT32>
0x029901d2:	addl %esi, %edx
0x029901d4:	movl %edi, 0x40(%ebx)
0x029901d7:	addl %edi, %edx
0x029901d9:	rep movsb %es:(%edi), %ds:(%esi)
0x029901db:	movl %esi, %ebx
0x029901dd:	leal %edi, 0x10002f1b(%ebp)
0x029901e3:	addl (%edi), %ebp
0x029901e5:	addl 0x4(%edi), %ebp
0x029901e8:	addl 0x8(%edi), %ebp
0x029901eb:	leal %ecx, 0x10002eff(%ebp)
0x029901f1:	pushl %ecx
0x029901f2:	call 0x0299033d
0x0299033d:	pushl %ebp
0x0299033e:	movl %ebp, %esp
0x02990340:	addl %esp, $0xfffffffc<UINT8>
0x02990343:	pushl %ebx
0x02990344:	pushl %edi
0x02990345:	pushl %esi
0x02990346:	call 0x0299034b
0x0299034b:	popl %ebx
0x0299034c:	subl %ebx, $0x10001506<UINT32>
0x02990352:	movl %esi, 0x8(%ebp)
0x02990355:	movl %ecx, (%esi)
0x02990357:	addl %ecx, %ebx
0x02990359:	pushl %ecx
0x0299035a:	call LoadLibraryA@kernel32.dll
LoadLibraryA@kernel32.dll: API Node	
0x02990360:	movl -4(%ebp), %eax
0x02990363:	movl %edx, 0x4(%esi)
0x02990366:	movl %edi, 0x8(%esi)
0x02990369:	addl %edx, %ebx
0x0299036b:	addl %edi, %ebx
0x0299036d:	xorl %eax, %eax
0x0299036f:	addl %eax, (%edx)
0x02990371:	je 0x02990389
0x02990373:	pushl %edx
0x02990374:	movl %eax, (%edx)
0x02990376:	addl %eax, %ebx
0x02990378:	pushl %eax
0x02990379:	pushl -4(%ebp)
0x0299037c:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x02990382:	stosl %es:(%edi), %eax
0x02990383:	popl %edx
0x02990384:	addl %edx, $0x4<UINT8>
0x02990387:	jmp 0x0299036d
0x02990389:	addl %esi, $0xc<UINT8>
0x0299038c:	addl %eax, (%esi)
0x0299038e:	jne 0x02990355
0x02990390:	popl %esi
0x02990391:	popl %edi
0x02990392:	popl %ebx
0x02990393:	leave
0x02990394:	ret $0x4<UINT16>

0x029901f7:	nop
0x029901f8:	nop
0x029901f9:	nop
0x029901fa:	nop
0x029901fb:	nop
0x029901fc:	nop
0x029901fd:	nop
0x029901fe:	nop
0x029901ff:	movl %ecx, 0x2c(%esi)
0x02990202:	movl 0x10002f2b(%ebp), %ecx
0x02990208:	pushl $0x4<UINT8>
0x0299020a:	pushl $0x1000<UINT32>
0x0299020f:	pushl %ecx
0x02990210:	pushl $0x0<UINT8>
0x02990212:	call VirtualAlloc@kernel32.dll
0x02990218:	movl 0x10002f27(%ebp), %eax
0x0299021e:	pushl %esi
0x0299021f:	call 0x0299061a
0x0299061a:	pushl %ebp
0x0299061b:	movl %ebp, %esp
0x0299061d:	addl %esp, $0xffffffe8<UINT8>
0x02990620:	pushl %ebx
0x02990621:	pushl %edi
0x02990622:	pushl %esi
0x02990623:	call 0x02990628
0x02990628:	popl %ebx
0x02990629:	subl %ebx, $0x100017e3<UINT32>
0x0299062f:	movl %esi, 0x8(%ebp)
0x02990632:	xorl %eax, %eax
0x02990634:	xorl %ecx, %ecx
0x02990636:	addl %ecx, 0x3c(%esi)
0x02990639:	je 10
0x0299063b:	movl %edx, 0x8(%esi)
0x0299063e:	movl %edi, %esi
0x02990640:	addl %esi, $0x50<UINT8>
0x02990643:	jmp 0x0299064c
0x0299064c:	movl -4(%ebp), %eax
0x0299064f:	movzwl %eax, 0x2(%edi)
0x02990653:	movl -16(%ebp), %eax
0x02990656:	pushl %ecx
0x02990657:	pushl %edx
0x02990658:	pushl %esi
0x02990659:	movzwl %eax, 0x10(%esi)
0x0299065d:	testl %eax, $0x10<UINT32>
0x02990662:	je 0x0299073e
0x0299073e:	popl %esi
0x0299073f:	popl %edx
0x02990740:	popl %ecx
0x02990741:	addl %esi, $0x1c<UINT8>
0x02990744:	decl %ecx
0x02990745:	jne 0x02990656
0x02990668:	pushl %esi
0x02990669:	movl %edi, 0x10002f27(%ebx)
0x0299066f:	movl -20(%ebp), %edi
0x02990672:	movl %ecx, 0x8(%esi)
0x02990675:	movl %eax, 0x14(%esi)
0x02990678:	subl %ecx, %eax
0x0299067a:	movl %esi, (%esi)
0x0299067c:	addl %esi, %edx
0x0299067e:	movl %eax, %ecx
0x02990680:	sarl %ecx, $0x2<UINT8>
0x02990683:	rep movsl %es:(%edi), %ds:(%esi)
0x02990685:	addl %ecx, %eax
0x02990687:	andl %ecx, $0x3<UINT8>
0x0299068a:	rep movsb %es:(%edi), %ds:(%esi)
0x0299068c:	popl %esi
0x0299068d:	nop
0x0299068e:	nop
0x0299068f:	nop
0x02990690:	nop
0x02990691:	nop
0x02990692:	nop
0x02990693:	nop
0x02990694:	nop
0x02990695:	nop
0x02990696:	nop
0x02990697:	nop
0x02990698:	nop
0x02990699:	nop
0x0299069a:	nop
0x0299069b:	nop
0x0299069c:	nop
0x0299069d:	nop
0x0299069e:	nop
0x0299069f:	nop
0x029906a0:	nop
0x029906a1:	nop
0x029906a2:	nop
0x029906a3:	nop
0x029906a4:	nop
0x029906a5:	nop
0x029906a6:	nop
0x029906a7:	nop
0x029906a8:	nop
0x029906a9:	nop
0x029906aa:	nop
0x029906ab:	nop
0x029906ac:	nop
0x029906ad:	nop
0x029906ae:	nop
0x029906af:	nop
0x029906b0:	nop
0x029906b1:	nop
0x029906b2:	nop
0x029906b3:	movl %eax, 0x4(%esi)
0x029906b6:	addl %eax, %edx
0x029906b8:	movl -24(%ebp), %eax
0x029906bb:	movl %eax, -16(%ebp)
0x029906be:	decl %eax
0x029906bf:	movl -12(%ebp), %eax
0x029906c2:	pushl %edx
0x029906c3:	pushl %eax
0x029906c4:	pushl 0x8(%ebp)
0x029906c7:	call 0x02990a08
0x02990a08:	pushl %ebp
0x02990a09:	movl %ebp, %esp
0x02990a0b:	addl %esp, $0xfffffffc<UINT8>
0x02990a0e:	pushl %ebx
0x02990a0f:	pushl %edi
0x02990a10:	pushl %esi
0x02990a11:	movl %ebx, 0x8(%ebp)
0x02990a14:	movl %esi, %ebx
0x02990a16:	movl %ecx, 0x30(%ebx)
0x02990a19:	subl %esi, %ecx
0x02990a1b:	movl -4(%ebp), %esi
0x02990a1e:	xorl %ecx, %ecx
0x02990a20:	lodsl %eax, %ds:(%esi)
0x02990a21:	testl %eax, %eax
0x02990a23:	je 28
0x02990a25:	cmpl %ecx, 0xc(%ebp)
0x02990a28:	je 0x02990a2d
0x02990a2d:	nop
0x02990a2e:	nop
0x02990a2f:	nop
0x02990a30:	nop
0x02990a31:	nop
0x02990a32:	nop
0x02990a33:	nop
0x02990a34:	nop
0x02990a35:	nop
0x02990a36:	nop
0x02990a37:	nop
0x02990a38:	nop
0x02990a39:	nop
0x02990a3a:	nop
0x02990a3b:	nop
0x02990a3c:	nop
0x02990a3d:	nop
0x02990a3e:	addl %eax, -4(%ebp)
0x02990a41:	popl %esi
0x02990a42:	popl %edi
0x02990a43:	popl %ebx
0x02990a44:	leave
0x02990a45:	ret $0x8<UINT16>

0x029906cc:	leal %ecx, 0x10002f2f(%ebx)
0x029906d2:	pushl %ecx
0x029906d3:	pushl -24(%ebp)
0x029906d6:	pushl -20(%ebp)
0x029906d9:	call 0x02990008
0x02990008:	pusha
0x02990009:	movl %esi, 0x24(%esp)
0x0299000d:	movl %edi, 0x28(%esp)
0x02990011:	cld
0x02990012:	lodsl %eax, %ds:(%esi)
0x02990013:	xorl %ecx, %ecx
0x02990015:	testl %eax, %eax
0x02990017:	je 17
0x02990019:	xorl %edx, %edx
0x0299001b:	leal %ebx, (%eax,%edi)
0x0299001e:	movsb %es:(%edi), %ds:(%esi)
0x0299001f:	movb %cl, $0x3<UINT8>
0x02990021:	call 0x02990098
0x02990098:	addl %edx, %edx
0x0299009a:	jne 0x029900a2
0x0299009c:	xchgl %edx, %eax
0x0299009d:	lodsl %eax, %ds:(%esi)
0x0299009e:	xchgl %edx, %eax
0x0299009f:	addl %edx, %edx
0x029900a1:	incl %edx
0x029900a2:	ret

0x02990026:	jae 0x0299001e
0x02990028:	cmpl %edi, %ebx
0x0299002a:	jae 0x029900b5
0x02990030:	pushl %ebx
0x02990031:	pushl %ebp
0x02990032:	pushl %edi
0x02990033:	xorl %ebx, %ebx
0x02990035:	incl %ebx
0x02990036:	xorl %ebp, %ebp
0x02990038:	movl %eax, %ebx
0x0299003a:	leal %edi, (%ebp,%ebx)
0x0299003e:	movl %ebp, %ebx
0x02990040:	movl %ebx, %edi
0x02990042:	call 0x02990098
0x02990047:	jae 0x0299003a
0x02990049:	leal %ebx, (%ebp,%edi)
0x0299004d:	addl %eax, %edi
0x0299004f:	movl %ebp, %edi
0x02990051:	call 0x02990098
0x02990056:	jae 0x0299003a
0x02990058:	popl %edi
0x02990059:	popl %ebp
0x0299005a:	popl %ebx
0x0299005b:	subl %eax, %ecx
0x0299005d:	jae 0x02990068
0x02990068:	movb %cl, $0x6<UINT8>
0x0299006a:	call 0x02990098
0x0299006f:	adcl %eax, %eax
0x02990071:	decl %ecx
0x02990072:	jne 0x0299006a
0x02990074:	incl %eax
0x02990075:	call 0x029900a3
0x029900a3:	xorl %ecx, %ecx
0x029900a5:	incl %ecx
0x029900a6:	call 0x02990098
0x029900ab:	adcl %ecx, %ecx
0x029900ad:	call 0x02990098
0x029900b2:	jb 0x029900a6
0x029900b4:	ret

0x0299007a:	movl %ebp, %eax
0x0299007c:	cmpl %eax, $0x8001<UINT32>
0x02990081:	sbbl %ecx, $0xffffffff<UINT8>
0x02990084:	cmpl %eax, $0x781<UINT32>
0x02990089:	sbbl %ecx, $0xffffffff<UINT8>
0x0299008c:	pushl %esi
0x0299008d:	movl %esi, %edi
0x0299008f:	subl %esi, %eax
0x02990091:	rep movsb %es:(%edi), %ds:(%esi)
0x02990093:	popl %esi
0x02990094:	incl %ecx
0x02990095:	incl %ecx
0x02990096:	jmp 0x02990021
0x0299005f:	movl %eax, %ebp
0x02990061:	call 0x029900a3
0x02990066:	jmp 0x0299008c
0x029900b5:	subl %edi, 0x28(%esp)
0x029900b9:	movl 0x1c(%esp), %edi
0x029900bd:	popa
0x029900be:	ret $0xc<UINT16>

0x029906db:	movl %ecx, %eax
0x029906dd:	incl %eax
0x029906de:	je 116
0x029906e0:	xorl %eax, %eax
0x029906e2:	addl %eax, -12(%ebp)
0x029906e5:	je 0x029906f9
0x029906f9:	popl %edx
0x029906fa:	pushl %esi
0x029906fb:	movl %edi, 0x4(%esi)
0x029906fe:	addl %edi, %edx
0x02990700:	cmpl %edi, -24(%ebp)
0x02990703:	jne 4
0x02990705:	addl %edi, %ecx
0x02990707:	jmp 0x0299071a
0x0299071a:	movl %eax, %edi
0x0299071c:	addl %eax, $0xfff<UINT32>
0x02990721:	shrl %eax, $0xc<UINT8>
0x02990724:	shll %eax, $0xc<UINT8>
0x02990727:	subl %eax, %edi
0x02990729:	movl %ecx, %eax
0x0299072b:	xorl %eax, %eax
0x0299072d:	pushl %edx
0x0299072e:	movl %edx, %ecx
0x02990730:	sarl %ecx, $0x2<UINT8>
0x02990733:	rep stosl %es:(%edi), %eax
0x02990735:	addl %ecx, %edx
0x02990737:	andl %ecx, $0x3<UINT8>
0x0299073a:	rep stosb %es:(%edi), %al
0x0299073c:	popl %edx
0x0299073d:	popl %esi
0x0299074b:	xorl %eax, %eax
0x0299074d:	popl %esi
0x0299074e:	popl %edi
0x0299074f:	popl %ebx
0x02990750:	leave
0x02990751:	ret $0x4<UINT16>

0x02990224:	leal %ecx, 0x10002dbd(%ebp)
0x0299022a:	testl %eax, %eax
0x0299022c:	jne 148
0x02990232:	pushl %esi
0x02990233:	call 0x02990578
0x02990578:	pushl %ebp
0x02990579:	movl %ebp, %esp
0x0299057b:	addl %esp, $0xffffffe8<UINT8>
0x0299057e:	pushl %ebx
0x0299057f:	pushl %edi
0x02990580:	pushl %esi
0x02990581:	call 0x02990586
0x02990586:	popl %ebx
0x02990587:	subl %ebx, $0x10001741<UINT32>
0x0299058d:	movl %esi, 0x8(%ebp)
0x02990590:	xorl %eax, %eax
0x02990592:	xorl %ecx, %ecx
0x02990594:	addl %ecx, 0x3c(%esi)
0x02990597:	je 10
0x02990599:	movl %edx, 0x8(%esi)
0x0299059c:	movl %edi, %esi
0x0299059e:	addl %esi, $0x50<UINT8>
0x029905a1:	jmp 0x029905aa
0x029905aa:	movl %eax, 0x10002f27(%ebx)
0x029905b0:	movl -4(%ebp), %eax
0x029905b3:	movl %ebx, %esi
0x029905b5:	movzwl %eax, 0x10(%ebx)
0x029905b9:	testl %eax, $0x2<UINT32>
0x029905be:	je 0x0299060d
0x029905c0:	pushl %ecx
0x029905c1:	movl %esi, 0x4(%ebx)
0x029905c4:	movl %edi, -4(%ebp)
0x029905c7:	movl %ecx, 0x8(%ebx)
0x029905ca:	addl %esi, %edx
0x029905cc:	movl %eax, %ecx
0x029905ce:	sarl %ecx, $0x2<UINT8>
0x029905d1:	rep movsl %es:(%edi), %ds:(%esi)
0x029905d3:	addl %ecx, %eax
0x029905d5:	andl %ecx, $0x3<UINT8>
0x029905d8:	rep movsb %es:(%edi), %ds:(%esi)
0x029905da:	movl %edi, 0x4(%ebx)
0x029905dd:	movl %ecx, 0x8(%ebx)
0x029905e0:	addl %edi, %edx
0x029905e2:	xorl %eax, %eax
0x029905e4:	pushl %edx
0x029905e5:	movl %edx, %ecx
0x029905e7:	sarl %ecx, $0x2<UINT8>
0x029905ea:	rep stosl %es:(%edi), %eax
0x029905ec:	addl %ecx, %edx
0x029905ee:	andl %ecx, $0x3<UINT8>
0x029905f1:	rep stosb %es:(%edi), %al
0x029905f3:	popl %edx
0x029905f4:	movl %esi, -4(%ebp)
0x029905f7:	movl %edi, (%ebx)
0x029905f9:	addl %edi, %edx
0x029905fb:	movl %ecx, 0x8(%ebx)
0x029905fe:	movl %eax, %ecx
0x02990600:	sarl %ecx, $0x2<UINT8>
0x02990603:	rep movsl %es:(%edi), %ds:(%esi)
0x02990605:	addl %ecx, %eax
0x02990607:	andl %ecx, $0x3<UINT8>
0x0299060a:	rep movsb %es:(%edi), %ds:(%esi)
0x0299060c:	popl %ecx
0x0299060d:	addl %ebx, $0x1c<UINT8>
0x02990610:	decl %ecx
0x02990611:	jne 0x029905b5
0x02990613:	popl %esi
0x02990614:	popl %edi
0x02990615:	popl %ebx
0x02990616:	leave
0x02990617:	ret $0x4<UINT16>

0x02990238:	pushl %esi
0x02990239:	call 0x02990493
0x02990493:	pushl %ebp
0x02990494:	movl %ebp, %esp
0x02990496:	addl %esp, $0xffffffe8<UINT8>
0x02990499:	pushl %ebx
0x0299049a:	pushl %edi
0x0299049b:	pushl %esi
0x0299049c:	call 0x029904a1
0x029904a1:	popl %ebx
0x029904a2:	subl %ebx, $0x1000165c<UINT32>
0x029904a8:	movl %esi, 0x8(%ebp)
0x029904ab:	xorl %eax, %eax
0x029904ad:	xorl %ecx, %ecx
0x029904af:	addl %ecx, 0x3c(%esi)
0x029904b2:	je 10
0x029904b4:	movl %edx, 0x8(%esi)
0x029904b7:	movl %edi, %esi
0x029904b9:	addl %esi, $0x50<UINT8>
0x029904bc:	jmp 0x029904c5
0x029904c5:	movl -8(%ebp), %edx
0x029904c8:	movzwl %eax, 0x10(%esi)
0x029904cc:	testl %eax, $0x200<UINT32>
0x029904d1:	jne 125
0x029904d3:	testl %eax, $0x8<UINT32>
0x029904d8:	je 0x02990567
0x02990567:	addl %esi, $0x1c<UINT8>
0x0299056a:	decl %ecx
0x0299056b:	jne 0x029904c8
0x029904de:	pushl %ecx
0x029904df:	pushl %esi
0x029904e0:	movl %edi, 0x8(%esi)
0x029904e3:	xorl %ecx, %ecx
0x029904e5:	movl -4(%ebp), %ecx
0x029904e8:	movzwl %ebx, 0x12(%esi)
0x029904ec:	movl %esi, (%esi)
0x029904ee:	addl %esi, -8(%ebp)
0x029904f1:	cmpl %ecx, %edi
0x029904f3:	jnl 0x02990529
0x029904f5:	movl %eax, (%esi)
0x029904f7:	incl %esi
0x029904f8:	movzbl %edx, %al
0x029904fb:	addl -4(%ebp), %edx
0x029904fe:	subb %al, $0xffffffe8<UINT8>
0x02990500:	movl %edx, %ebx
0x02990502:	je 0x0299050a
0x02990504:	decb %al
0x02990506:	movb %dl, %bh
0x02990508:	jne 0x02990526
0x02990526:	incl %ecx
0x02990527:	jmp 0x029904f1
0x0299050a:	movl %eax, (%esi)
0x0299050c:	cmpb %al, %dl
0x0299050e:	jne 13
0x02990510:	shrw %ax, $0x8<UINT8>
0x02990514:	roll %eax, $0x10<UINT8>
0x02990517:	xchgb %ah, %al
0x02990519:	subl %eax, %ecx
0x0299051b:	movl (%esi), %eax
0x0299051d:	addl -4(%ebp), %eax
0x02990520:	addl %esi, $0x4<UINT8>
0x02990523:	addl %ecx, $0x4<UINT8>
0x02990529:	popl %esi
0x0299052a:	popl %ecx
0x0299052b:	xorl %eax, %eax
0x0299052d:	addl %eax, 0x14(%esi)
0x02990530:	je 53
0x02990532:	cmpl %eax, -4(%ebp)
0x02990535:	je 0x02990567
0x02990571:	popl %esi
0x02990572:	popl %edi
0x02990573:	popl %ebx
0x02990574:	leave
0x02990575:	ret $0x4<UINT16>

0x0299023e:	nop
0x0299023f:	nop
0x02990240:	nop
0x02990241:	nop
0x02990242:	nop
0x02990243:	nop
0x02990244:	nop
0x02990245:	nop
0x02990246:	nop
0x02990247:	nop
0x02990248:	nop
0x02990249:	nop
0x0299024a:	nop
0x0299024b:	nop
0x0299024c:	movl %ecx, 0x34(%esi)
0x0299024f:	testl %ecx, %ecx
0x02990251:	je 137
0x02990257:	addl %ecx, 0x8(%esi)
0x0299025a:	pushl %ecx
0x0299025b:	pushl %esi
0x0299025c:	call 0x029908a8
0x029908a8:	pushl %ebp
0x029908a9:	movl %ebp, %esp
0x029908ab:	pushl %ebx
0x029908ac:	pushl %edi
0x029908ad:	pushl %esi
0x029908ae:	movl %esi, 0xc(%ebp)
0x029908b1:	movl %ebx, 0x8(%ebp)
0x029908b4:	xorl %eax, %eax
0x029908b6:	cmpl 0x10(%esi), %eax
0x029908b9:	jne 0x029908bf
0x029908bf:	addl %eax, (%esi)
0x029908c1:	je 3
0x029908c3:	addl %eax, 0x8(%ebx)
0x029908c6:	movl %ecx, 0xc(%esi)
0x029908c9:	addl %ecx, 0x8(%ebx)
0x029908cc:	movl %edi, 0x10(%esi)
0x029908cf:	testl %edi, %edi
0x029908d1:	je 3
0x029908d3:	addl %edi, 0x8(%ebx)
0x029908d6:	pushl %eax
0x029908d7:	pushl %edi
0x029908d8:	pushl %ecx
0x029908d9:	pushl %ebx
0x029908da:	call 0x029908ef
0x029908ef:	pushl %ebp
0x029908f0:	movl %ebp, %esp
0x029908f2:	addl %esp, $0xffffffe8<UINT8>
0x029908f5:	pushl %ebx
0x029908f6:	pushl %edi
0x029908f7:	pushl %esi
0x029908f8:	call 0x029908fd
0x029908fd:	popl %ebx
0x029908fe:	subl %ebx, $0x10001ab8<UINT32>
0x02990904:	movl %eax, 0xc(%ebp)
0x02990907:	movl 0x10002d9c(%ebx), %eax
0x0299090d:	xorl %eax, %eax
0x0299090f:	movl 0x10002da0(%ebx), %eax
0x02990915:	xorl %esi, %esi
0x02990917:	incl %esi
0x02990918:	incl %esi
0x02990919:	movl %edx, 0x10002eef(%ebx)
0x0299091f:	pushl 0xc(%ebp)
0x02990922:	call 0x00000000
0x00000000:	inb %al, $0xffffffff<UINT8>
0x00000002:	sbbb (%eax), %al
Unknown Node: Unknown Node	
