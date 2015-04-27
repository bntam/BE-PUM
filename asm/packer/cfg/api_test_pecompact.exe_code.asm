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
0x004048f6:	call 0x00020188
0x00020188:	pushl %ebx
0x00020189:	pushl %edi
0x0002018a:	pushl %esi
0x0002018b:	pushl %ebp
0x0002018c:	call 0x00020191
0x00020191:	popl %ebp
0x00020192:	subl %ebp, $0x1000134c<UINT32>
0x00020198:	leal %esi, 0x10001343(%ebp)
0x0002019e:	movl %eax, -4(%esi)
0x000201a1:	addl %eax, $0x4<UINT8>
0x000201a4:	subl %esi, %eax
0x000201a6:	cld
0x000201a7:	movl %ebx, %esi
0x000201a9:	movl %edx, 0x8(%esi)
0x000201ac:	movl %esi, 0x1c(%esi)
0x000201af:	addl %esi, %edx
0x000201b1:	leal %edi, 0x10002f2f(%ebp)
0x000201b7:	lodsl %eax, %ds:(%esi)
0x000201b8:	stosl %es:(%edi), %eax
0x000201b9:	lodsl %eax, %ds:(%esi)
0x000201ba:	stosl %es:(%edi), %eax
0x000201bb:	lodsl %eax, %ds:(%esi)
0x000201bc:	stosl %es:(%edi), %eax
0x000201bd:	lodsl %eax, %ds:(%esi)
0x000201be:	stosl %es:(%edi), %eax
0x000201bf:	nop
0x000201c0:	cmpl 0x48(%ebx), $0x1<UINT8>
0x000201c4:	je 21
0x000201c6:	movl %esi, 0x44(%ebx)
0x000201c9:	testl %esi, %esi
0x000201cb:	je 14
0x000201cd:	movl %ecx, $0x23<UINT32>
0x000201d2:	addl %esi, %edx
0x000201d4:	movl %edi, 0x40(%ebx)
0x000201d7:	addl %edi, %edx
0x000201d9:	rep movsb %es:(%edi), %ds:(%esi)
0x000201db:	movl %esi, %ebx
0x000201dd:	leal %edi, 0x10002f1b(%ebp)
0x000201e3:	addl (%edi), %ebp
0x000201e5:	addl 0x4(%edi), %ebp
0x000201e8:	addl 0x8(%edi), %ebp
0x000201eb:	leal %ecx, 0x10002eff(%ebp)
0x000201f1:	pushl %ecx
0x000201f2:	call 0x0002033d
0x0002033d:	pushl %ebp
0x0002033e:	movl %ebp, %esp
0x00020340:	addl %esp, $0xfffffffc<UINT8>
0x00020343:	pushl %ebx
0x00020344:	pushl %edi
0x00020345:	pushl %esi
0x00020346:	call 0x0002034b
0x0002034b:	popl %ebx
0x0002034c:	subl %ebx, $0x10001506<UINT32>
0x00020352:	movl %esi, 0x8(%ebp)
0x00020355:	movl %ecx, (%esi)
0x00020357:	addl %ecx, %ebx
0x00020359:	pushl %ecx
0x0002035a:	call LoadLibraryA@kernel32.dll
LoadLibraryA@kernel32.dll: API Node	
0x00020360:	movl -4(%ebp), %eax
0x00020363:	movl %edx, 0x4(%esi)
0x00020366:	movl %edi, 0x8(%esi)
0x00020369:	addl %edx, %ebx
0x0002036b:	addl %edi, %ebx
0x0002036d:	xorl %eax, %eax
0x0002036f:	addl %eax, (%edx)
0x00020371:	je 0x00020389
0x00020373:	pushl %edx
0x00020374:	movl %eax, (%edx)
0x00020376:	addl %eax, %ebx
0x00020378:	pushl %eax
0x00020379:	pushl -4(%ebp)
0x0002037c:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x00020382:	stosl %es:(%edi), %eax
0x00020383:	popl %edx
0x00020384:	addl %edx, $0x4<UINT8>
0x00020387:	jmp 0x0002036d
0x00020389:	addl %esi, $0xc<UINT8>
0x0002038c:	addl %eax, (%esi)
0x0002038e:	jne 0x00020355
0x00020390:	popl %esi
0x00020391:	popl %edi
0x00020392:	popl %ebx
0x00020393:	leave
0x00020394:	ret $0x4<UINT16>

0x000201f7:	nop
0x000201f8:	nop
0x000201f9:	nop
0x000201fa:	nop
0x000201fb:	nop
0x000201fc:	nop
0x000201fd:	nop
0x000201fe:	nop
0x000201ff:	movl %ecx, 0x2c(%esi)
0x00020202:	movl 0x10002f2b(%ebp), %ecx
0x00020208:	pushl $0x4<UINT8>
0x0002020a:	pushl $0x1000<UINT32>
0x0002020f:	pushl %ecx
0x00020210:	pushl $0x0<UINT8>
0x00020212:	call VirtualAlloc@kernel32.dll
0x00020218:	movl 0x10002f27(%ebp), %eax
0x0002021e:	pushl %esi
0x0002021f:	call 0x0002061a
0x0002061a:	pushl %ebp
0x0002061b:	movl %ebp, %esp
0x0002061d:	addl %esp, $0xffffffe8<UINT8>
0x00020620:	pushl %ebx
0x00020621:	pushl %edi
0x00020622:	pushl %esi
0x00020623:	call 0x00020628
0x00020628:	popl %ebx
0x00020629:	subl %ebx, $0x100017e3<UINT32>
0x0002062f:	movl %esi, 0x8(%ebp)
0x00020632:	xorl %eax, %eax
0x00020634:	xorl %ecx, %ecx
0x00020636:	addl %ecx, 0x3c(%esi)
0x00020639:	je 10
0x0002063b:	movl %edx, 0x8(%esi)
0x0002063e:	movl %edi, %esi
0x00020640:	addl %esi, $0x50<UINT8>
0x00020643:	jmp 0x0002064c
0x0002064c:	movl -4(%ebp), %eax
0x0002064f:	movzwl %eax, 0x2(%edi)
0x00020653:	movl -16(%ebp), %eax
0x00020656:	pushl %ecx
0x00020657:	pushl %edx
0x00020658:	pushl %esi
0x00020659:	movzwl %eax, 0x10(%esi)
0x0002065d:	testl %eax, $0x10<UINT32>
0x00020662:	je 0x0002073e
0x0002073e:	popl %esi
0x0002073f:	popl %edx
0x00020740:	popl %ecx
0x00020741:	addl %esi, $0x1c<UINT8>
0x00020744:	decl %ecx
0x00020745:	jne 0x00020656
0x00020668:	pushl %esi
0x00020669:	movl %edi, 0x10002f27(%ebx)
0x0002066f:	movl -20(%ebp), %edi
0x00020672:	movl %ecx, 0x8(%esi)
0x00020675:	movl %eax, 0x14(%esi)
0x00020678:	subl %ecx, %eax
0x0002067a:	movl %esi, (%esi)
0x0002067c:	addl %esi, %edx
0x0002067e:	movl %eax, %ecx
0x00020680:	sarl %ecx, $0x2<UINT8>
0x00020683:	rep movsl %es:(%edi), %ds:(%esi)
0x00020685:	addl %ecx, %eax
0x00020687:	andl %ecx, $0x3<UINT8>
0x0002068a:	rep movsb %es:(%edi), %ds:(%esi)
0x0002068c:	popl %esi
0x0002068d:	nop
0x0002068e:	nop
0x0002068f:	nop
0x00020690:	nop
0x00020691:	nop
0x00020692:	nop
0x00020693:	nop
0x00020694:	nop
0x00020695:	nop
0x00020696:	nop
0x00020697:	nop
0x00020698:	nop
0x00020699:	nop
0x0002069a:	nop
0x0002069b:	nop
0x0002069c:	nop
0x0002069d:	nop
0x0002069e:	nop
0x0002069f:	nop
0x000206a0:	nop
0x000206a1:	nop
0x000206a2:	nop
0x000206a3:	nop
0x000206a4:	nop
0x000206a5:	nop
0x000206a6:	nop
0x000206a7:	nop
0x000206a8:	nop
0x000206a9:	nop
0x000206aa:	nop
0x000206ab:	nop
0x000206ac:	nop
0x000206ad:	nop
0x000206ae:	nop
0x000206af:	nop
0x000206b0:	nop
0x000206b1:	nop
0x000206b2:	nop
0x000206b3:	movl %eax, 0x4(%esi)
0x000206b6:	addl %eax, %edx
0x000206b8:	movl -24(%ebp), %eax
0x000206bb:	movl %eax, -16(%ebp)
0x000206be:	decl %eax
0x000206bf:	movl -12(%ebp), %eax
0x000206c2:	pushl %edx
0x000206c3:	pushl %eax
0x000206c4:	pushl 0x8(%ebp)
0x000206c7:	call 0x00020a08
0x00020a08:	pushl %ebp
0x00020a09:	movl %ebp, %esp
0x00020a0b:	addl %esp, $0xfffffffc<UINT8>
0x00020a0e:	pushl %ebx
0x00020a0f:	pushl %edi
0x00020a10:	pushl %esi
0x00020a11:	movl %ebx, 0x8(%ebp)
0x00020a14:	movl %esi, %ebx
0x00020a16:	movl %ecx, 0x30(%ebx)
0x00020a19:	subl %esi, %ecx
0x00020a1b:	movl -4(%ebp), %esi
0x00020a1e:	xorl %ecx, %ecx
0x00020a20:	lodsl %eax, %ds:(%esi)
0x00020a21:	testl %eax, %eax
0x00020a23:	je 28
0x00020a25:	cmpl %ecx, 0xc(%ebp)
0x00020a28:	je 0x00020a2d
0x00020a2d:	nop
0x00020a2e:	nop
0x00020a2f:	nop
0x00020a30:	nop
0x00020a31:	nop
0x00020a32:	nop
0x00020a33:	nop
0x00020a34:	nop
0x00020a35:	nop
0x00020a36:	nop
0x00020a37:	nop
0x00020a38:	nop
0x00020a39:	nop
0x00020a3a:	nop
0x00020a3b:	nop
0x00020a3c:	nop
0x00020a3d:	nop
0x00020a3e:	addl %eax, -4(%ebp)
0x00020a41:	popl %esi
0x00020a42:	popl %edi
0x00020a43:	popl %ebx
0x00020a44:	leave
0x00020a45:	ret $0x8<UINT16>

0x000206cc:	leal %ecx, 0x10002f2f(%ebx)
0x000206d2:	pushl %ecx
0x000206d3:	pushl -24(%ebp)
0x000206d6:	pushl -20(%ebp)
0x000206d9:	call 0x00020008
0x00020008:	pusha
0x00020009:	movl %esi, 0x24(%esp)
0x0002000d:	movl %edi, 0x28(%esp)
0x00020011:	cld
0x00020012:	lodsl %eax, %ds:(%esi)
0x00020013:	xorl %ecx, %ecx
0x00020015:	testl %eax, %eax
0x00020017:	je 17
0x00020019:	xorl %edx, %edx
0x0002001b:	leal %ebx, (%eax,%edi)
0x0002001e:	movsb %es:(%edi), %ds:(%esi)
0x0002001f:	movb %cl, $0x3<UINT8>
0x00020021:	call 0x00020098
0x00020098:	addl %edx, %edx
0x0002009a:	jne 0x000200a2
0x0002009c:	xchgl %edx, %eax
0x0002009d:	lodsl %eax, %ds:(%esi)
0x0002009e:	xchgl %edx, %eax
0x0002009f:	addl %edx, %edx
0x000200a1:	incl %edx
0x000200a2:	ret

0x00020026:	jae 0x0002001e
0x00020028:	cmpl %edi, %ebx
0x0002002a:	jae 0x000200b5
0x00020030:	pushl %ebx
0x00020031:	pushl %ebp
0x00020032:	pushl %edi
0x00020033:	xorl %ebx, %ebx
0x00020035:	incl %ebx
0x00020036:	xorl %ebp, %ebp
0x00020038:	movl %eax, %ebx
0x0002003a:	leal %edi, (%ebp,%ebx)
0x0002003e:	movl %ebp, %ebx
0x00020040:	movl %ebx, %edi
0x00020042:	call 0x00020098
0x00020047:	jae 0x0002003a
0x00020049:	leal %ebx, (%ebp,%edi)
0x0002004d:	addl %eax, %edi
0x0002004f:	movl %ebp, %edi
0x00020051:	call 0x00020098
0x00020056:	jae 0x0002003a
0x00020058:	popl %edi
0x00020059:	popl %ebp
0x0002005a:	popl %ebx
0x0002005b:	subl %eax, %ecx
0x0002005d:	jae 0x00020068
0x00020068:	movb %cl, $0x6<UINT8>
0x0002006a:	call 0x00020098
0x0002006f:	adcl %eax, %eax
0x00020071:	decl %ecx
0x00020072:	jne 0x0002006a
0x00020074:	incl %eax
0x00020075:	call 0x000200a3
0x000200a3:	xorl %ecx, %ecx
0x000200a5:	incl %ecx
0x000200a6:	call 0x00020098
0x000200ab:	adcl %ecx, %ecx
0x000200ad:	call 0x00020098
0x000200b2:	jb 0x000200a6
0x000200b4:	ret

0x0002007a:	movl %ebp, %eax
0x0002007c:	cmpl %eax, $0x8001<UINT32>
0x00020081:	sbbl %ecx, $0xffffffff<UINT8>
0x00020084:	cmpl %eax, $0x781<UINT32>
0x00020089:	sbbl %ecx, $0xffffffff<UINT8>
0x0002008c:	pushl %esi
0x0002008d:	movl %esi, %edi
0x0002008f:	subl %esi, %eax
0x00020091:	rep movsb %es:(%edi), %ds:(%esi)
0x00020093:	popl %esi
0x00020094:	incl %ecx
0x00020095:	incl %ecx
0x00020096:	jmp 0x00020021
0x0002005f:	movl %eax, %ebp
0x00020061:	call 0x000200a3
0x00020066:	jmp 0x0002008c
0x000200b5:	subl %edi, 0x28(%esp)
0x000200b9:	movl 0x1c(%esp), %edi
0x000200bd:	popa
0x000200be:	ret $0xc<UINT16>

0x000206db:	movl %ecx, %eax
0x000206dd:	incl %eax
0x000206de:	je 116
0x000206e0:	xorl %eax, %eax
0x000206e2:	addl %eax, -12(%ebp)
0x000206e5:	je 0x000206f9
0x000206f9:	popl %edx
0x000206fa:	pushl %esi
0x000206fb:	movl %edi, 0x4(%esi)
0x000206fe:	addl %edi, %edx
0x00020700:	cmpl %edi, -24(%ebp)
0x00020703:	jne 4
0x00020705:	addl %edi, %ecx
0x00020707:	jmp 0x0002071a
0x0002071a:	movl %eax, %edi
0x0002071c:	addl %eax, $0xfff<UINT32>
0x00020721:	shrl %eax, $0xc<UINT8>
0x00020724:	shll %eax, $0xc<UINT8>
0x00020727:	subl %eax, %edi
0x00020729:	movl %ecx, %eax
0x0002072b:	xorl %eax, %eax
0x0002072d:	pushl %edx
0x0002072e:	movl %edx, %ecx
0x00020730:	sarl %ecx, $0x2<UINT8>
0x00020733:	rep stosl %es:(%edi), %eax
0x00020735:	addl %ecx, %edx
0x00020737:	andl %ecx, $0x3<UINT8>
0x0002073a:	rep stosb %es:(%edi), %al
0x0002073c:	popl %edx
0x0002073d:	popl %esi
0x0002074b:	xorl %eax, %eax
0x0002074d:	popl %esi
0x0002074e:	popl %edi
0x0002074f:	popl %ebx
0x00020750:	leave
0x00020751:	ret $0x4<UINT16>

0x00020224:	leal %ecx, 0x10002dbd(%ebp)
0x0002022a:	testl %eax, %eax
0x0002022c:	jne 148
0x00020232:	pushl %esi
0x00020233:	call 0x00020578
0x00020578:	pushl %ebp
0x00020579:	movl %ebp, %esp
0x0002057b:	addl %esp, $0xffffffe8<UINT8>
0x0002057e:	pushl %ebx
0x0002057f:	pushl %edi
0x00020580:	pushl %esi
0x00020581:	call 0x00020586
0x00020586:	popl %ebx
0x00020587:	subl %ebx, $0x10001741<UINT32>
0x0002058d:	movl %esi, 0x8(%ebp)
0x00020590:	xorl %eax, %eax
0x00020592:	xorl %ecx, %ecx
0x00020594:	addl %ecx, 0x3c(%esi)
0x00020597:	je 10
0x00020599:	movl %edx, 0x8(%esi)
0x0002059c:	movl %edi, %esi
0x0002059e:	addl %esi, $0x50<UINT8>
0x000205a1:	jmp 0x000205aa
0x000205aa:	movl %eax, 0x10002f27(%ebx)
0x000205b0:	movl -4(%ebp), %eax
0x000205b3:	movl %ebx, %esi
0x000205b5:	movzwl %eax, 0x10(%ebx)
0x000205b9:	testl %eax, $0x2<UINT32>
0x000205be:	je 0x0002060d
0x000205c0:	pushl %ecx
0x000205c1:	movl %esi, 0x4(%ebx)
0x000205c4:	movl %edi, -4(%ebp)
0x000205c7:	movl %ecx, 0x8(%ebx)
0x000205ca:	addl %esi, %edx
0x000205cc:	movl %eax, %ecx
0x000205ce:	sarl %ecx, $0x2<UINT8>
0x000205d1:	rep movsl %es:(%edi), %ds:(%esi)
0x000205d3:	addl %ecx, %eax
0x000205d5:	andl %ecx, $0x3<UINT8>
0x000205d8:	rep movsb %es:(%edi), %ds:(%esi)
0x000205da:	movl %edi, 0x4(%ebx)
0x000205dd:	movl %ecx, 0x8(%ebx)
0x000205e0:	addl %edi, %edx
0x000205e2:	xorl %eax, %eax
0x000205e4:	pushl %edx
0x000205e5:	movl %edx, %ecx
0x000205e7:	sarl %ecx, $0x2<UINT8>
0x000205ea:	rep stosl %es:(%edi), %eax
0x000205ec:	addl %ecx, %edx
0x000205ee:	andl %ecx, $0x3<UINT8>
0x000205f1:	rep stosb %es:(%edi), %al
0x000205f3:	popl %edx
0x000205f4:	movl %esi, -4(%ebp)
0x000205f7:	movl %edi, (%ebx)
0x000205f9:	addl %edi, %edx
0x000205fb:	movl %ecx, 0x8(%ebx)
0x000205fe:	movl %eax, %ecx
0x00020600:	sarl %ecx, $0x2<UINT8>
0x00020603:	rep movsl %es:(%edi), %ds:(%esi)
0x00020605:	addl %ecx, %eax
0x00020607:	andl %ecx, $0x3<UINT8>
0x0002060a:	rep movsb %es:(%edi), %ds:(%esi)
0x0002060c:	popl %ecx
0x0002060d:	addl %ebx, $0x1c<UINT8>
0x00020610:	decl %ecx
0x00020611:	jne 0x000205b5
0x00020613:	popl %esi
0x00020614:	popl %edi
0x00020615:	popl %ebx
0x00020616:	leave
0x00020617:	ret $0x4<UINT16>

0x00020238:	pushl %esi
0x00020239:	call 0x00020493
0x00020493:	pushl %ebp
0x00020494:	movl %ebp, %esp
0x00020496:	addl %esp, $0xffffffe8<UINT8>
0x00020499:	pushl %ebx
0x0002049a:	pushl %edi
0x0002049b:	pushl %esi
0x0002049c:	call 0x000204a1
0x000204a1:	popl %ebx
0x000204a2:	subl %ebx, $0x1000165c<UINT32>
0x000204a8:	movl %esi, 0x8(%ebp)
0x000204ab:	xorl %eax, %eax
0x000204ad:	xorl %ecx, %ecx
0x000204af:	addl %ecx, 0x3c(%esi)
0x000204b2:	je 10
0x000204b4:	movl %edx, 0x8(%esi)
0x000204b7:	movl %edi, %esi
0x000204b9:	addl %esi, $0x50<UINT8>
0x000204bc:	jmp 0x000204c5
0x000204c5:	movl -8(%ebp), %edx
0x000204c8:	movzwl %eax, 0x10(%esi)
0x000204cc:	testl %eax, $0x200<UINT32>
0x000204d1:	jne 125
0x000204d3:	testl %eax, $0x8<UINT32>
0x000204d8:	je 0x00020567
0x00020567:	addl %esi, $0x1c<UINT8>
0x0002056a:	decl %ecx
0x0002056b:	jne 0x000204c8
0x000204de:	pushl %ecx
0x000204df:	pushl %esi
0x000204e0:	movl %edi, 0x8(%esi)
0x000204e3:	xorl %ecx, %ecx
0x000204e5:	movl -4(%ebp), %ecx
0x000204e8:	movzwl %ebx, 0x12(%esi)
0x000204ec:	movl %esi, (%esi)
0x000204ee:	addl %esi, -8(%ebp)
0x000204f1:	cmpl %ecx, %edi
0x000204f3:	jnl 0x00020529
0x000204f5:	movl %eax, (%esi)
0x000204f7:	incl %esi
0x000204f8:	movzbl %edx, %al
0x000204fb:	addl -4(%ebp), %edx
0x000204fe:	subb %al, $0xffffffe8<UINT8>
0x00020500:	movl %edx, %ebx
0x00020502:	je 0x0002050a
0x00020504:	decb %al
0x00020506:	movb %dl, %bh
0x00020508:	jne 0x00020526
0x00020526:	incl %ecx
0x00020527:	jmp 0x000204f1
0x0002050a:	movl %eax, (%esi)
0x0002050c:	cmpb %al, %dl
0x0002050e:	jne 13
0x00020510:	shrw %ax, $0x8<UINT8>
0x00020514:	roll %eax, $0x10<UINT8>
0x00020517:	xchgb %ah, %al
0x00020519:	subl %eax, %ecx
0x0002051b:	movl (%esi), %eax
0x0002051d:	addl -4(%ebp), %eax
0x00020520:	addl %esi, $0x4<UINT8>
0x00020523:	addl %ecx, $0x4<UINT8>
0x00020529:	popl %esi
0x0002052a:	popl %ecx
0x0002052b:	xorl %eax, %eax
0x0002052d:	addl %eax, 0x14(%esi)
0x00020530:	je 53
0x00020532:	cmpl %eax, -4(%ebp)
0x00020535:	je 0x00020567
0x00020571:	popl %esi
0x00020572:	popl %edi
0x00020573:	popl %ebx
0x00020574:	leave
0x00020575:	ret $0x4<UINT16>

0x0002023e:	nop
0x0002023f:	nop
0x00020240:	nop
0x00020241:	nop
0x00020242:	nop
0x00020243:	nop
0x00020244:	nop
0x00020245:	nop
0x00020246:	nop
0x00020247:	nop
0x00020248:	nop
0x00020249:	nop
0x0002024a:	nop
0x0002024b:	nop
0x0002024c:	movl %ecx, 0x34(%esi)
0x0002024f:	testl %ecx, %ecx
0x00020251:	je 137
0x00020257:	addl %ecx, 0x8(%esi)
0x0002025a:	pushl %ecx
0x0002025b:	pushl %esi
0x0002025c:	call 0x000208a8
0x000208a8:	pushl %ebp
0x000208a9:	movl %ebp, %esp
0x000208ab:	pushl %ebx
0x000208ac:	pushl %edi
0x000208ad:	pushl %esi
0x000208ae:	movl %esi, 0xc(%ebp)
0x000208b1:	movl %ebx, 0x8(%ebp)
0x000208b4:	xorl %eax, %eax
0x000208b6:	cmpl 0x10(%esi), %eax
0x000208b9:	jne 0x000208bf
0x000208bf:	addl %eax, (%esi)
0x000208c1:	je 3
0x000208c3:	addl %eax, 0x8(%ebx)
0x000208c6:	movl %ecx, 0xc(%esi)
0x000208c9:	addl %ecx, 0x8(%ebx)
0x000208cc:	movl %edi, 0x10(%esi)
0x000208cf:	testl %edi, %edi
0x000208d1:	je 3
0x000208d3:	addl %edi, 0x8(%ebx)
0x000208d6:	pushl %eax
0x000208d7:	pushl %edi
0x000208d8:	pushl %ecx
0x000208d9:	pushl %ebx
0x000208da:	call 0x000208ef
0x000208ef:	pushl %ebp
0x000208f0:	movl %ebp, %esp
0x000208f2:	addl %esp, $0xffffffe8<UINT8>
0x000208f5:	pushl %ebx
0x000208f6:	pushl %edi
0x000208f7:	pushl %esi
0x000208f8:	call 0x000208fd
0x000208fd:	popl %ebx
0x000208fe:	subl %ebx, $0x10001ab8<UINT32>
0x00020904:	movl %eax, 0xc(%ebp)
0x00020907:	movl 0x10002d9c(%ebx), %eax
0x0002090d:	xorl %eax, %eax
0x0002090f:	movl 0x10002da0(%ebx), %eax
0x00020915:	xorl %esi, %esi
0x00020917:	incl %esi
0x00020918:	incl %esi
0x00020919:	movl %edx, 0x10002eef(%ebx)
0x0002091f:	pushl 0xc(%ebp)
0x00020922:	call 0x00020ab0
GetModuleHandleA@kernel32.dll: API Node	
0x00020924:	movl -4(%ebp), %eax
0x00020927:	movl %edx, 0x10002f1b(%ebx)
0x0002092d:	testl %eax, %eax
0x0002092f:	jne 0x0002093e
0x0002093e:	nop
0x0002093f:	nop
0x00020940:	nop
0x00020941:	nop
0x00020942:	nop
0x00020943:	nop
0x00020944:	nop
0x00020945:	nop
0x00020946:	nop
0x00020947:	nop
0x00020948:	nop
0x00020949:	nop
0x0002094a:	nop
0x0002094b:	nop
0x0002094c:	nop
0x0002094d:	nop
0x0002094e:	nop
0x0002094f:	nop
0x00020950:	nop
0x00020951:	nop
0x00020952:	nop
0x00020953:	nop
0x00020954:	nop
0x00020955:	nop
0x00020956:	nop
0x00020957:	nop
0x00020958:	nop
0x00020959:	nop
0x0002095a:	nop
0x0002095b:	nop
0x0002095c:	nop
0x0002095d:	nop
0x0002095e:	nop
0x0002095f:	nop
0x00020960:	nop
0x00020961:	nop
0x00020962:	nop
0x00020963:	nop
0x00020964:	nop
0x00020965:	nop
0x00020966:	nop
0x00020967:	nop
0x00020968:	movl %esi, 0x10(%ebp)
0x0002096b:	movl %edi, 0x8(%ebp)
0x0002096e:	movl %edx, 0x14(%ebp)
0x00020971:	testl %edx, %edx
0x00020973:	jne 0x00020977
0x00020977:	testl %esi, %esi
0x00020979:	jne 0x0002097d
0x0002097d:	movl 0x10002da0(%ebx), $0x0<UINT32>
0x00020987:	movl %eax, (%edx)
0x00020989:	testl %eax, %eax
0x0002098b:	je 0x000209d1
0x0002098d:	pushl %edx
0x0002098e:	movl 0x10002da0(%ebx), %eax
0x00020994:	testl %eax, $0x80000000<UINT32>
0x00020999:	je 0x000209a4
0x000209a4:	movl %ecx, 0x8(%ebp)
0x000209a7:	addl %eax, 0x8(%ecx)
0x000209aa:	xorl %ecx, %ecx
0x000209ac:	movw %cx, (%eax)
0x000209af:	pushl %ecx
0x000209b0:	incl %eax
0x000209b1:	incl %eax
0x000209b2:	pushl %eax
0x000209b3:	pushl -4(%ebp)
0x000209b6:	call 0x00020ad2
0x00020ad2:	pushl %ebp
0x00020ad3:	movl %ebp, %esp
0x00020ad5:	pushl %ebx
0x00020ad6:	pushl %edi
0x00020ad7:	pushl %esi
0x00020ad8:	call 0x00020add
0x00020add:	popl %ebx
0x00020ade:	subl %ebx, $0x10001c98<UINT32>
0x00020ae4:	pushl 0xc(%ebp)
0x00020ae7:	pushl 0x8(%ebp)
0x00020aea:	call GetProcAddress@kernel32.dll
0x00020af0:	popl %esi
0x00020af1:	popl %edi
0x00020af2:	popl %ebx
0x00020af3:	leave
0x00020af4:	ret $0xc<UINT16>

0x000209bc:	popl %edx
0x000209bd:	testl %eax, %eax
0x000209bf:	je -145
0x000209c5:	movl (%esi), %eax
0x000209c7:	movl (%edx), %eax
0x000209c9:	addl %edx, $0x4<UINT8>
0x000209cc:	addl %esi, $0x4<UINT8>
0x000209cf:	jmp 0x0002097d
0x000209d1:	xorl %eax, %eax
0x000209d3:	popl %esi
0x000209d4:	popl %edi
0x000209d5:	popl %ebx
0x000209d6:	leave
0x000209d7:	ret $0x10<UINT16>

0x000208df:	incl %eax
0x000208e0:	jne 0x000208ea
0x000208ea:	addl %esi, $0x14<UINT8>
0x000208ed:	jmp 0x000208b4
0x00020931:	decl %esi
0x00020932:	jne 0x0002091f
0x00020ab0:	pushl %ebp
0x00020ab1:	movl %ebp, %esp
0x00020ab3:	pushl %ebx
0x00020ab4:	pushl %edi
0x00020ab5:	pushl %esi
0x00020ab6:	call 0x00020abb
0x00020abb:	popl %ebx
0x00020abc:	subl %ebx, $0x10001c76<UINT32>
0x00020ac2:	pushl 0x8(%ebp)
0x00020ac5:	call LoadLibraryA@kernel32.dll
0x00020acb:	popl %esi
0x00020acc:	popl %edi
0x00020acd:	popl %ebx
0x00020ace:	leave
0x00020acf:	ret $0x4<UINT16>

0x000208bb:	cmpl (%esi), %eax
0x000208bd:	je 0x000208e3
0x000208e3:	popl %esi
0x000208e4:	popl %edi
0x000208e5:	popl %ebx
0x000208e6:	leave
0x000208e7:	ret $0x8<UINT16>

0x00020261:	testl %eax, %eax
0x00020263:	je 0x000202e0
0x000202e0:	movl %edi, 0x8(%ebx)
0x000202e3:	movl %ebx, %esi
0x000202e5:	cmpl 0x48(%ebx), $0x1<UINT8>
0x000202e9:	jne 0x00020300
0x00020300:	movl %esi, %ebx
0x00020302:	nop
0x00020303:	nop
0x00020304:	nop
0x00020305:	nop
0x00020306:	nop
0x00020307:	nop
0x00020308:	nop
0x00020309:	nop
0x0002030a:	nop
0x0002030b:	nop
0x0002030c:	nop
0x0002030d:	nop
0x0002030e:	pushl %esi
0x0002030f:	call 0x00020af7
0x00020af7:	pushl %ebp
0x00020af8:	movl %ebp, %esp
0x00020afa:	addl %esp, $0xfffffffc<UINT8>
0x00020afd:	pushl %ebx
0x00020afe:	pushl %edi
0x00020aff:	pushl %esi
0x00020b00:	call 0x00020b05
0x00020b05:	popl %ebx
0x00020b06:	subl %ebx, $0x10001cc0<UINT32>
0x00020b0c:	movl %esi, 0x8(%ebp)
0x00020b0f:	movl %eax, 0x8(%esi)
0x00020b12:	addl %eax, 0x3c(%eax)
0x00020b15:	leal %edi, 0x80(%eax)
0x00020b1b:	movl %ecx, %edi
0x00020b1d:	shrl %ecx, $0xc<UINT8>
0x00020b20:	shll %ecx, $0xc<UINT8>
0x00020b23:	pushl %ecx
0x00020b24:	leal %eax, -4(%ebp)
0x00020b27:	pushl %eax
0x00020b28:	pushl $0x4<UINT8>
0x00020b2a:	pushl $0x1000<UINT32>
0x00020b2f:	pushl %ecx
0x00020b30:	call VirtualProtect@kernel32.dll
VirtualProtect@kernel32.dll: API Node	
0x00020b36:	movl %edx, 0x34(%esi)
0x00020b39:	movl (%edi), %edx
0x00020b3b:	popl %ecx
0x00020b3c:	leal %eax, -4(%ebp)
0x00020b3f:	pushl %eax
0x00020b40:	pushl -4(%ebp)
0x00020b43:	pushl $0x1000<UINT32>
0x00020b48:	pushl %ecx
0x00020b49:	call VirtualProtect@kernel32.dll
0x00020b4f:	popl %esi
0x00020b50:	popl %edi
0x00020b51:	popl %ebx
0x00020b52:	leave
0x00020b53:	ret $0x4<UINT16>

0x00020314:	nop
0x00020315:	nop
0x00020316:	nop
0x00020317:	nop
0x00020318:	nop
0x00020319:	nop
0x0002031a:	pushl %edi
0x0002031b:	call 0x00020a48
0x00020a48:	pushl %ebp
0x00020a49:	movl %ebp, %esp
0x00020a4b:	addl %esp, $0xfffffffc<UINT8>
0x00020a4e:	pushl %ebx
0x00020a4f:	pushl %edi
0x00020a50:	pushl %esi
0x00020a51:	call 0x00020a56
0x00020a56:	popl %ebx
0x00020a57:	subl %ebx, $0x10001c11<UINT32>
0x00020a5d:	movl %eax, 0x8(%ebp)
0x00020a60:	addl %eax, 0x3c(%eax)
0x00020a63:	xorl %ecx, %ecx
0x00020a65:	movw %cx, 0x14(%eax)
0x00020a69:	leal %edi, 0x18(%ecx,%eax)
0x00020a6d:	addl %edi, $0x27<UINT8>
0x00020a70:	movl %ecx, %edi
0x00020a72:	shrl %ecx, $0xc<UINT8>
0x00020a75:	shll %ecx, $0xc<UINT8>
0x00020a78:	pushl %ecx
0x00020a79:	leal %eax, -4(%ebp)
0x00020a7c:	pushl %eax
0x00020a7d:	pushl $0x4<UINT8>
0x00020a7f:	pushl $0x1000<UINT32>
0x00020a84:	pushl %ecx
0x00020a85:	call VirtualProtect@kernel32.dll
0x00020a8b:	movb %al, (%edi)
0x00020a8d:	testb %al, $0xffffff80<UINT8>
0x00020a8f:	je 0x00020a95
0x00020a95:	popl %ecx
0x00020a96:	leal %eax, -4(%ebp)
0x00020a99:	pushl %eax
0x00020a9a:	pushl -4(%ebp)
0x00020a9d:	pushl $0x1000<UINT32>
0x00020aa2:	pushl %ecx
0x00020aa3:	call VirtualProtect@kernel32.dll
0x00020aa9:	popl %esi
0x00020aaa:	popl %edi
0x00020aab:	popl %ebx
0x00020aac:	leave
0x00020aad:	ret $0x4<UINT16>

0x00020320:	pushl $0x8000<UINT32>
0x00020325:	pushl $0x0<UINT8>
0x00020327:	pushl 0x10002f27(%ebp)
0x0002032d:	call VirtualFree@kernel32.dll
VirtualFree@kernel32.dll: API Node	
0x00020333:	movl %eax, 0xc(%esi)
0x00020336:	addl %eax, %edi
0x00020338:	popl %ebp
0x00020339:	popl %esi
0x0002033a:	popl %edi
0x0002033b:	popl %ebx
0x0002033c:	ret

0x004048f8:	movl 0x1000133f(%ebp), %eax
0x004048fe:	movl %esi, %eax
0x00404900:	movl %ecx, 0x14(%ebx)
0x00404903:	popl %edx
0x00404904:	jmp 0x00404912
0x00404912:	movl %eax, %esi
0x00404914:	popl %edx
0x00404915:	popl %esi
0x00404916:	popl %edi
0x00404917:	popl %ecx
0x00404918:	popl %ebx
0x00404919:	popl %ebp
0x0040491a:	jmp 0x00401000
0x00401000:	pushl %eax
0x00401001:	pushl %ebx
0x00401002:	leal %eax, 0x403098
0x00401008:	pushl %eax
0x00401009:	call 0x004011f2
0x004011f2:	jmp SetCurrentDirectoryA@kernel32.dll
SetCurrentDirectoryA@kernel32.dll: API Node	
0x0040100e:	leal %eax, 0x40306d
0x00401014:	pushl $0x0<UINT8>
0x00401016:	pushl $0x80<UINT32>
0x0040101b:	pushl $0x2<UINT8>
0x0040101d:	pushl $0x0<UINT8>
0x0040101f:	pushl $0x1<UINT8>
0x00401021:	pushl $0x40000000<UINT32>
0x00401026:	pushl %eax
0x00401027:	call 0x00401180
0x00401180:	jmp CreateFileA@kernel32.dll
CreateFileA@kernel32.dll: API Node	
0x0040102c:	movl %ebx, %eax
0x0040102e:	pushl $0x0<UINT8>
0x00401030:	pushl $0x0<UINT8>
0x00401032:	pushl $0xa<UINT8>
0x00401034:	leal %eax, 0x403008
0x0040103a:	pushl %eax
0x0040103b:	pushl %ebx
0x0040103c:	call 0x004011fe
0x004011fe:	jmp WriteFile@kernel32.dll
WriteFile@kernel32.dll: API Node	
0x00401041:	pushl %ebx
0x00401042:	call 0x00401174
0x00401174:	jmp CloseHandle@kernel32.dll
CloseHandle@kernel32.dll: API Node	
0x00401047:	pushl $0x0<UINT8>
0x00401049:	leal %eax, 0x403080
0x0040104f:	pushl %eax
0x00401050:	leal %eax, 0x403076
0x00401056:	pushl %eax
0x00401057:	call 0x0040117a
0x0040117a:	jmp CopyFileA@kernel32.dll
CopyFileA@kernel32.dll: API Node	
0x0040105c:	leal %eax, 0x403090
0x00401062:	pushl %eax
0x00401063:	leal %eax, 0x40308a
0x00401069:	pushl %eax
0x0040106a:	call 0x00401198
0x00401198:	jmp FindFirstFileA@kernel32.dll
FindFirstFileA@kernel32.dll: API Node	
0x0040106f:	leal %ebx, 0x403090
0x00401075:	pushl %ebx
0x00401076:	pushl %eax
0x00401077:	call 0x0040119e
0x0040119e:	jmp FindNextFileA@kernel32.dll
FindNextFileA@kernel32.dll: API Node	
0x0040107c:	pushl %eax
0x0040107d:	call 0x00401192
0x00401192:	jmp FindClose@kernel32.dll
FindClose@kernel32.dll: API Node	
0x00401082:	leal %eax, 0x40306d
0x00401088:	pushl %eax
0x00401089:	call 0x004011b0
0x004011b0:	jmp GetFileAttributesA@kernel32.dll
GetFileAttributesA@kernel32.dll: API Node	
0x0040108e:	pushl $0x80<UINT32>
0x00401093:	leal %eax, 0x40306d
0x00401099:	pushl %eax
0x0040109a:	call 0x004011f8
0x004011f8:	jmp SetFileAttributesA@kernel32.dll
SetFileAttributesA@kernel32.dll: API Node	
0x0040109f:	leal %eax, 0x40306d
0x004010a5:	pushl %eax
0x004010a6:	call 0x004011b0
0x004010ab:	leal %eax, 0x40306d
0x004010b1:	pushl %eax
0x004010b2:	call 0x00401186
0x00401186:	jmp DeleteFileA@kernel32.dll
DeleteFileA@kernel32.dll: API Node	
0x004010b7:	leal %eax, 0x403060
0x004010bd:	pushl %eax
0x004010be:	call 0x004011ce
0x004011ce:	jmp GetStartupInfoA@kernel32.dll
GetStartupInfoA@kernel32.dll: API Node	
0x004010c3:	call 0x004011b6
0x004011b6:	jmp GetLastError@kernel32.dll
GetLastError@kernel32.dll: API Node	
0x004010c8:	call 0x004011e0
0x004011e0:	jmp GetVersion@kernel32.dll
GetVersion@kernel32.dll: API Node	
0x004010cd:	leal %ebx, 0x403042
0x004010d3:	pushl $0x64<UINT8>
0x004010d5:	pushl %ebx
0x004010d6:	call 0x004011d4
0x004011d4:	jmp GetSystemDirectoryA@kernel32.dll
GetSystemDirectoryA@kernel32.dll: API Node	
0x004010db:	leal %ebx, 0x403042
0x004010e1:	pushl %ebx
0x004010e2:	pushl $0x64<UINT8>
0x004010e4:	call 0x004011aa
0x004011aa:	jmp GetCurrentDirectoryA@kernel32.dll
GetCurrentDirectoryA@kernel32.dll: API Node	
0x004010e9:	leal %ebx, 0x403042
0x004010ef:	pushl $0x64<UINT8>
0x004010f1:	pushl %ebx
0x004010f2:	call 0x004011e6
0x004011e6:	jmp GetWindowsDirectoryA@kernel32.dll
GetWindowsDirectoryA@kernel32.dll: API Node	
0x004010f7:	call 0x004011a4
0x004011a4:	jmp GetCommandLineA@kernel32.dll
GetCommandLineA@kernel32.dll: API Node	
0x004010fc:	leal %eax, 0x403054
0x00401102:	pushl %eax
0x00401103:	call 0x004011da
0x004011da:	jmp GetSystemTime@kernel32.dll
GetSystemTime@kernel32.dll: API Node	
0x00401108:	leal %eax, 0x403022
0x0040110e:	pushl %eax
0x0040110f:	call 0x004011ec
0x004011ec:	jmp LoadLibraryA@kernel32.dll
0x00401114:	leal %eax, 0x403015
0x0040111a:	pushl %eax
0x0040111b:	call 0x004011ec
0x00401120:	leal %ebx, 0x40302d
0x00401126:	pushl %ebx
0x00401127:	pushl %eax
0x00401128:	call 0x004011c8
0x004011c8:	jmp GetProcAddress@kernel32.dll
0x0040112d:	leal %ebx, 0x403042
0x00401133:	pushl %ebx
0x00401134:	pushl $0x64<UINT8>
0x00401136:	call 0x004011aa
0x0040113b:	pushl $0x64<UINT8>
0x0040113d:	pushl $0x403000<UINT32>
0x00401142:	pushl $0x0<UINT8>
0x00401144:	call 0x004011bc
0x004011bc:	jmp GetModuleFileNameA@kernel32.dll
GetModuleFileNameA@kernel32.dll: API Node	
0x00401149:	leal %eax, 0x403008
0x0040114f:	movl %ebx, $0x0<UINT32>
0x00401154:	pushl %ebx
0x00401155:	pushl %eax
0x00401156:	pushl %eax
0x00401157:	pushl %ebx
0x00401158:	call 0x00401204
0x00401204:	jmp MessageBoxA@user32.dll
MessageBoxA@user32.dll: API Node	
0x0040115d:	pushl $0x0<UINT8>
0x0040115f:	call 0x004011c2
0x004011c2:	jmp GetModuleHandleA@kernel32.dll
0x00401164:	addl %eax, $0x116b<UINT32>
0x00401169:	pushl %eax
0x0040116a:	ret

0x0040116b:	addl %eax, %ebx
0x0040116d:	popl %eax
0x0040116e:	call 0x0040118c
0x0040118c:	jmp ExitProcess@kernel32.dll
ExitProcess@kernel32.dll: Exit Node	
