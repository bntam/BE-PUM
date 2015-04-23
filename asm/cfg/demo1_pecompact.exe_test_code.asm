0x00401000:	movl %eax, $0x40a858<UINT32>
0x00401005:	pushl %eax
0x00401006:	pushl %fs:0
0x0040100d:	movl %fs:0, %esp
0x00401014:	xorl %eax, %eax
0x00401016:	movl (%eax), %ecx
0x0040a858:	movl %eax, $0xf04095dd<UINT32>
0x0040a85d:	leal %ecx, 0x1000129e(%eax)
0x0040a863:	movl 0x1(%ecx), %eax
0x0040a866:	movl %edx, 0x4(%esp)
0x0040a86a:	movl %edx, 0xc(%edx)
0x0040a86d:	movb (%edx), $0xffffffe9<UINT8>
0x0040a870:	addl %edx, $0x5<UINT8>
0x0040a873:	subl %ecx, %edx
0x0040a875:	movl -4(%edx), %ecx
0x0040a878:	xorl %eax, %eax
0x0040a87a:	ret

0x00401016:	jmp 0x0040a87b
0x0040a87b:	movl %eax, $0xf04095dd<UINT32>
0x0040a880:	popl %fs:0
0x0040a887:	addl %esp, $0x4<UINT8>
0x0040a88a:	pushl %ebp
0x0040a88b:	pushl %ebx
0x0040a88c:	pushl %ecx
0x0040a88d:	pushl %edi
0x0040a88e:	pushl %esi
0x0040a88f:	pushl %edx
0x0040a890:	leal %ebx, 0x10001257(%eax)
0x0040a896:	movl %edx, 0x18(%ebx)
0x0040a899:	pushl %edx
0x0040a89a:	movl %ebp, %eax
0x0040a89c:	pushl $0x40<UINT8>
0x0040a89e:	pushl $0x1000<UINT32>
0x0040a8a3:	pushl 0x4(%ebx)
0x0040a8a6:	pushl $0x0<UINT8>
0x0040a8a8:	movl %ecx, 0x10(%ebx)
0x0040a8ab:	addl %ecx, %edx
0x0040a8ad:	movl %eax, (%ecx)
0x0040a8af:	call VirtualAlloc@kernel32.dll
VirtualAlloc@kernel32.dll: API Node	
0x0040a8b1:	popl %edx
0x0040a8b2:	movl %edi, %eax
0x0040a8b4:	pushl %eax
0x0040a8b5:	pushl %edx
0x0040a8b6:	movl %esi, (%ebx)
0x0040a8b8:	movl %eax, 0x20(%ebx)
0x0040a8bb:	addl %eax, %edx
0x0040a8bd:	movl %ecx, (%eax)
0x0040a8bf:	movl 0x20(%ebx), %ecx
0x0040a8c2:	movl %eax, 0x1c(%ebx)
0x0040a8c5:	addl %eax, %edx
0x0040a8c7:	movl %ecx, (%eax)
0x0040a8c9:	movl 0x1c(%ebx), %ecx
0x0040a8cc:	addl %esi, %edx
0x0040a8ce:	movl %ecx, 0xc(%ebx)
0x0040a8d1:	addl %ecx, %edx
0x0040a8d3:	leal %eax, 0x1c(%ebx)
0x0040a8d6:	pushl %eax
0x0040a8d7:	pushl %edi
0x0040a8d8:	pushl %esi
0x0040a8d9:	call 0x0040a77b
0x0040a77b:	pusha
0x0040a77c:	movl %esi, 0x24(%esp)
0x0040a780:	movl %edi, 0x28(%esp)
0x0040a784:	cld
0x0040a785:	lodsl %eax, %ds:(%esi)
0x0040a786:	xorl %ecx, %ecx
0x0040a788:	testl %eax, %eax
0x0040a78a:	je 17
0x0040a78c:	xorl %edx, %edx
0x0040a78e:	leal %ebx, (%eax,%edi)
0x0040a791:	movsb %es:(%edi), %ds:(%esi)
0x0040a792:	movb %cl, $0x3<UINT8>
0x0040a794:	call 0x0040a80b
0x0040a80b:	addl %edx, %edx
0x0040a80d:	jne 0x0040a815
0x0040a80f:	xchgl %edx, %eax
0x0040a810:	lodsl %eax, %ds:(%esi)
0x0040a811:	xchgl %edx, %eax
0x0040a812:	addl %edx, %edx
0x0040a814:	incl %edx
0x0040a815:	ret

0x0040a799:	jae 0x0040a791
0x0040a79b:	cmpl %edi, %ebx
0x0040a79d:	jae 0x0040a828
0x0040a7a3:	pushl %ebx
0x0040a7a4:	pushl %ebp
0x0040a7a5:	pushl %edi
0x0040a7a6:	xorl %ebx, %ebx
0x0040a7a8:	incl %ebx
0x0040a7a9:	xorl %ebp, %ebp
0x0040a7ab:	movl %eax, %ebx
0x0040a7ad:	leal %edi, (%ebp,%ebx)
0x0040a7b1:	movl %ebp, %ebx
0x0040a7b3:	movl %ebx, %edi
0x0040a7b5:	call 0x0040a80b
0x0040a7ba:	jae 0x0040a7ad
0x0040a7bc:	leal %ebx, (%ebp,%edi)
0x0040a7c0:	addl %eax, %edi
0x0040a7c2:	movl %ebp, %edi
0x0040a7c4:	call 0x0040a80b
0x0040a7c9:	jae 0x0040a7ad
0x0040a7cb:	popl %edi
0x0040a7cc:	popl %ebp
0x0040a7cd:	popl %ebx
0x0040a7ce:	subl %eax, %ecx
0x0040a7d0:	jae 0x0040a7db
0x0040a7db:	movb %cl, $0x6<UINT8>
0x0040a7dd:	call 0x0040a80b
0x0040a7e2:	adcl %eax, %eax
0x0040a7e4:	decl %ecx
0x0040a7e5:	jne 0x0040a7dd
0x0040a7e7:	incl %eax
0x0040a7e8:	call 0x0040a816
0x0040a816:	xorl %ecx, %ecx
0x0040a818:	incl %ecx
0x0040a819:	call 0x0040a80b
0x0040a81e:	adcl %ecx, %ecx
0x0040a820:	call 0x0040a80b
0x0040a825:	jb 0x0040a819
0x0040a827:	ret

0x0040a7ed:	movl %ebp, %eax
0x0040a7ef:	cmpl %eax, $0x8001<UINT32>
0x0040a7f4:	sbbl %ecx, $0xffffffff<UINT8>
0x0040a7f7:	cmpl %eax, $0x781<UINT32>
0x0040a7fc:	sbbl %ecx, $0xffffffff<UINT8>
0x0040a7ff:	pushl %esi
0x0040a800:	movl %esi, %edi
0x0040a802:	subl %esi, %eax
0x0040a804:	rep movsb %es:(%edi), %ds:(%esi)
0x0040a806:	popl %esi
0x0040a807:	incl %ecx
0x0040a808:	incl %ecx
0x0040a809:	jmp 0x0040a794
0x0040a7d2:	movl %eax, %ebp
0x0040a7d4:	call 0x0040a816
0x0040a7d9:	jmp 0x0040a7ff
0x0040a828:	subl %edi, 0x28(%esp)
0x0040a82c:	movl 0x1c(%esp), %edi
0x0040a830:	popa
0x0040a831:	ret $0xc<UINT16>

0x0040a8db:	popl %edx
0x0040a8dc:	popl %eax
0x0040a8dd:	addl %eax, 0x8(%ebx)
0x0040a8e0:	movl %edi, %eax
0x0040a8e2:	pushl %edx
0x0040a8e3:	movl %esi, %eax
0x0040a8e5:	movl %eax, -4(%esi)
0x0040a8e8:	addl %eax, $0x4<UINT8>
0x0040a8eb:	subl %esi, %eax
0x0040a8ed:	movl 0x8(%esi), %edx
0x0040a8f0:	movl %ecx, 0xc(%ebx)
0x0040a8f3:	movl 0x14(%esi), %ecx
0x0040a8f6:	call 0x00020188
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
0x00020056:	jae -30
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
0x000204f3:	jnl 52
0x000204f5:	movl %eax, (%esi)
0x000204f7:	incl %esi
0x000204f8:	movzbl %edx, %al
0x000204fb:	addl -4(%ebp), %edx
0x000204fe:	subb %al, $0xffffffe8<UINT8>
0x00020500:	movl %edx, %ebx
0x00020502:	je 6
0x00020504:	decb %al
0x00020506:	movb %dl, %bh
0x00020508:	jne 0x00020526
0x00020526:	incl %ecx
0x00020527:	jmp 0x000204f1
