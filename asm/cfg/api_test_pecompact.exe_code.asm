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
0x004048f6:	call 0x02b00188
0x02b00188:	pushl %ebx
0x02b00189:	pushl %edi
0x02b0018a:	pushl %esi
0x02b0018b:	pushl %ebp
0x02b0018c:	call 0x02b00191
0x02b00191:	popl %ebp
0x02b00192:	subl %ebp, $0x1000134c<UINT32>
0x02b00198:	leal %esi, 0x10001343(%ebp)
0x02b0019e:	movl %eax, -4(%esi)
0x02b001a1:	addl %eax, $0x4<UINT8>
0x02b001a4:	subl %esi, %eax
0x02b001a6:	cld
0x02b001a7:	movl %ebx, %esi
0x02b001a9:	movl %edx, 0x8(%esi)
0x02b001ac:	movl %esi, 0x1c(%esi)
0x02b001af:	addl %esi, %edx
0x02b001b1:	leal %edi, 0x10002f2f(%ebp)
0x02b001b7:	lodsl %eax, %ds:(%esi)
0x02b001b8:	stosl %es:(%edi), %eax
0x02b001b9:	lodsl %eax, %ds:(%esi)
0x02b001ba:	stosl %es:(%edi), %eax
0x02b001bb:	lodsl %eax, %ds:(%esi)
0x02b001bc:	stosl %es:(%edi), %eax
0x02b001bd:	lodsl %eax, %ds:(%esi)
0x02b001be:	stosl %es:(%edi), %eax
0x02b001bf:	nop
0x02b001c0:	cmpl 0x48(%ebx), $0x1<UINT8>
0x02b001c4:	je 21
0x02b001c6:	movl %esi, 0x44(%ebx)
0x02b001c9:	testl %esi, %esi
0x02b001cb:	je 14
0x02b001cd:	movl %ecx, $0x23<UINT32>
0x02b001d2:	addl %esi, %edx
0x02b001d4:	movl %edi, 0x40(%ebx)
0x02b001d7:	addl %edi, %edx
0x02b001d9:	rep movsb %es:(%edi), %ds:(%esi)
0x02b001db:	movl %esi, %ebx
0x02b001dd:	leal %edi, 0x10002f1b(%ebp)
0x02b001e3:	addl (%edi), %ebp
0x02b001e5:	addl 0x4(%edi), %ebp
0x02b001e8:	addl 0x8(%edi), %ebp
0x02b001eb:	leal %ecx, 0x10002eff(%ebp)
0x02b001f1:	pushl %ecx
0x02b001f2:	call 0x02b0033d
0x02b0033d:	pushl %ebp
0x02b0033e:	movl %ebp, %esp
0x02b00340:	addl %esp, $0xfffffffc<UINT8>
0x02b00343:	pushl %ebx
0x02b00344:	pushl %edi
0x02b00345:	pushl %esi
0x02b00346:	call 0x02b0034b
0x02b0034b:	popl %ebx
0x02b0034c:	subl %ebx, $0x10001506<UINT32>
0x02b00352:	movl %esi, 0x8(%ebp)
0x02b00355:	movl %ecx, (%esi)
0x02b00357:	addl %ecx, %ebx
0x02b00359:	pushl %ecx
0x02b0035a:	call LoadLibraryA@kernel32.dll
LoadLibraryA@kernel32.dll: API Node	
0x02b00360:	movl -4(%ebp), %eax
0x02b00363:	movl %edx, 0x4(%esi)
0x02b00366:	movl %edi, 0x8(%esi)
0x02b00369:	addl %edx, %ebx
0x02b0036b:	addl %edi, %ebx
0x02b0036d:	xorl %eax, %eax
0x02b0036f:	addl %eax, (%edx)
0x02b00371:	je 0x02b00389
0x02b00373:	pushl %edx
0x02b00374:	movl %eax, (%edx)
0x02b00376:	addl %eax, %ebx
0x02b00378:	pushl %eax
0x02b00379:	pushl -4(%ebp)
0x02b0037c:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x02b00382:	stosl %es:(%edi), %eax
0x02b00383:	popl %edx
0x02b00384:	addl %edx, $0x4<UINT8>
0x02b00387:	jmp 0x02b0036d
0x02b00389:	addl %esi, $0xc<UINT8>
0x02b0038c:	addl %eax, (%esi)
0x02b0038e:	jne 0x02b00355
0x02b00390:	popl %esi
0x02b00391:	popl %edi
0x02b00392:	popl %ebx
0x02b00393:	leave
0x02b00394:	ret $0x4<UINT16>

0x02b001f7:	nop
0x02b001f8:	nop
0x02b001f9:	nop
0x02b001fa:	nop
0x02b001fb:	nop
0x02b001fc:	nop
0x02b001fd:	nop
0x02b001fe:	nop
0x02b001ff:	movl %ecx, 0x2c(%esi)
0x02b00202:	movl 0x10002f2b(%ebp), %ecx
0x02b00208:	pushl $0x4<UINT8>
0x02b0020a:	pushl $0x1000<UINT32>
0x02b0020f:	pushl %ecx
0x02b00210:	pushl $0x0<UINT8>
0x02b00212:	call VirtualAlloc@kernel32.dll
0x02b00218:	movl 0x10002f27(%ebp), %eax
0x02b0021e:	pushl %esi
0x02b0021f:	call 0x02b0061a
0x02b0061a:	pushl %ebp
0x02b0061b:	movl %ebp, %esp
0x02b0061d:	addl %esp, $0xffffffe8<UINT8>
0x02b00620:	pushl %ebx
0x02b00621:	pushl %edi
0x02b00622:	pushl %esi
0x02b00623:	call 0x02b00628
0x02b00628:	popl %ebx
0x02b00629:	subl %ebx, $0x100017e3<UINT32>
0x02b0062f:	movl %esi, 0x8(%ebp)
0x02b00632:	xorl %eax, %eax
0x02b00634:	xorl %ecx, %ecx
0x02b00636:	addl %ecx, 0x3c(%esi)
0x02b00639:	je 10
0x02b0063b:	movl %edx, 0x8(%esi)
0x02b0063e:	movl %edi, %esi
0x02b00640:	addl %esi, $0x50<UINT8>
0x02b00643:	jmp 0x02b0064c
0x02b0064c:	movl -4(%ebp), %eax
0x02b0064f:	movzwl %eax, 0x2(%edi)
0x02b00653:	movl -16(%ebp), %eax
0x02b00656:	pushl %ecx
0x02b00657:	pushl %edx
0x02b00658:	pushl %esi
0x02b00659:	movzwl %eax, 0x10(%esi)
0x02b0065d:	testl %eax, $0x10<UINT32>
0x02b00662:	je 0x02b0073e
0x02b0073e:	popl %esi
0x02b0073f:	popl %edx
0x02b00740:	popl %ecx
0x02b00741:	addl %esi, $0x1c<UINT8>
0x02b00744:	decl %ecx
0x02b00745:	jne 0x02b00656
0x02b00668:	pushl %esi
0x02b00669:	movl %edi, 0x10002f27(%ebx)
0x02b0066f:	movl -20(%ebp), %edi
0x02b00672:	movl %ecx, 0x8(%esi)
0x02b00675:	movl %eax, 0x14(%esi)
0x02b00678:	subl %ecx, %eax
0x02b0067a:	movl %esi, (%esi)
0x02b0067c:	addl %esi, %edx
0x02b0067e:	movl %eax, %ecx
0x02b00680:	sarl %ecx, $0x2<UINT8>
0x02b00683:	rep movsl %es:(%edi), %ds:(%esi)
0x02b00685:	addl %ecx, %eax
0x02b00687:	andl %ecx, $0x3<UINT8>
0x02b0068a:	rep movsb %es:(%edi), %ds:(%esi)
0x02b0068c:	popl %esi
0x02b0068d:	nop
0x02b0068e:	nop
0x02b0068f:	nop
0x02b00690:	nop
0x02b00691:	nop
0x02b00692:	nop
0x02b00693:	nop
0x02b00694:	nop
0x02b00695:	nop
0x02b00696:	nop
0x02b00697:	nop
0x02b00698:	nop
0x02b00699:	nop
0x02b0069a:	nop
0x02b0069b:	nop
0x02b0069c:	nop
0x02b0069d:	nop
0x02b0069e:	nop
0x02b0069f:	nop
0x02b006a0:	nop
0x02b006a1:	nop
0x02b006a2:	nop
0x02b006a3:	nop
0x02b006a4:	nop
0x02b006a5:	nop
0x02b006a6:	nop
0x02b006a7:	nop
0x02b006a8:	nop
0x02b006a9:	nop
0x02b006aa:	nop
0x02b006ab:	nop
0x02b006ac:	nop
0x02b006ad:	nop
0x02b006ae:	nop
0x02b006af:	nop
0x02b006b0:	nop
0x02b006b1:	nop
0x02b006b2:	nop
0x02b006b3:	movl %eax, 0x4(%esi)
0x02b006b6:	addl %eax, %edx
0x02b006b8:	movl -24(%ebp), %eax
0x02b006bb:	movl %eax, -16(%ebp)
0x02b006be:	decl %eax
0x02b006bf:	movl -12(%ebp), %eax
0x02b006c2:	pushl %edx
0x02b006c3:	pushl %eax
0x02b006c4:	pushl 0x8(%ebp)
0x02b006c7:	call 0x02b00a08
0x02b00a08:	pushl %ebp
0x02b00a09:	movl %ebp, %esp
0x02b00a0b:	addl %esp, $0xfffffffc<UINT8>
0x02b00a0e:	pushl %ebx
0x02b00a0f:	pushl %edi
0x02b00a10:	pushl %esi
0x02b00a11:	movl %ebx, 0x8(%ebp)
0x02b00a14:	movl %esi, %ebx
0x02b00a16:	movl %ecx, 0x30(%ebx)
0x02b00a19:	subl %esi, %ecx
0x02b00a1b:	movl -4(%ebp), %esi
0x02b00a1e:	xorl %ecx, %ecx
0x02b00a20:	lodsl %eax, %ds:(%esi)
0x02b00a21:	testl %eax, %eax
0x02b00a23:	je 28
0x02b00a25:	cmpl %ecx, 0xc(%ebp)
0x02b00a28:	je 0x02b00a2d
0x02b00a2d:	nop
0x02b00a2e:	nop
0x02b00a2f:	nop
0x02b00a30:	nop
0x02b00a31:	nop
0x02b00a32:	nop
0x02b00a33:	nop
0x02b00a34:	nop
0x02b00a35:	nop
0x02b00a36:	nop
0x02b00a37:	nop
0x02b00a38:	nop
0x02b00a39:	nop
0x02b00a3a:	nop
0x02b00a3b:	nop
0x02b00a3c:	nop
0x02b00a3d:	nop
0x02b00a3e:	addl %eax, -4(%ebp)
0x02b00a41:	popl %esi
0x02b00a42:	popl %edi
0x02b00a43:	popl %ebx
0x02b00a44:	leave
0x02b00a45:	ret $0x8<UINT16>

0x02b006cc:	leal %ecx, 0x10002f2f(%ebx)
0x02b006d2:	pushl %ecx
0x02b006d3:	pushl -24(%ebp)
0x02b006d6:	pushl -20(%ebp)
0x02b006d9:	call 0x02b00008
0x02b00008:	pusha
0x02b00009:	movl %esi, 0x24(%esp)
0x02b0000d:	movl %edi, 0x28(%esp)
0x02b00011:	cld
0x02b00012:	lodsl %eax, %ds:(%esi)
0x02b00013:	xorl %ecx, %ecx
0x02b00015:	testl %eax, %eax
0x02b00017:	je 17
0x02b00019:	xorl %edx, %edx
0x02b0001b:	leal %ebx, (%eax,%edi)
0x02b0001e:	movsb %es:(%edi), %ds:(%esi)
0x02b0001f:	movb %cl, $0x3<UINT8>
0x02b00021:	call 0x02b00098
0x02b00098:	addl %edx, %edx
0x02b0009a:	jne 0x02b000a2
0x02b0009c:	xchgl %edx, %eax
0x02b0009d:	lodsl %eax, %ds:(%esi)
0x02b0009e:	xchgl %edx, %eax
0x02b0009f:	addl %edx, %edx
0x02b000a1:	incl %edx
0x02b000a2:	ret

0x02b00026:	jae 0x02b0001e
0x02b00028:	cmpl %edi, %ebx
0x02b0002a:	jae 0x02b000b5
0x02b00030:	pushl %ebx
0x02b00031:	pushl %ebp
0x02b00032:	pushl %edi
0x02b00033:	xorl %ebx, %ebx
0x02b00035:	incl %ebx
0x02b00036:	xorl %ebp, %ebp
0x02b00038:	movl %eax, %ebx
0x02b0003a:	leal %edi, (%ebp,%ebx)
0x02b0003e:	movl %ebp, %ebx
0x02b00040:	movl %ebx, %edi
0x02b00042:	call 0x02b00098
0x02b00047:	jae 0x02b0003a
0x02b00049:	leal %ebx, (%ebp,%edi)
0x02b0004d:	addl %eax, %edi
0x02b0004f:	movl %ebp, %edi
0x02b00051:	call 0x02b00098
0x02b00056:	jae 0x02b0003a
0x02b00058:	popl %edi
0x02b00059:	popl %ebp
0x02b0005a:	popl %ebx
0x02b0005b:	subl %eax, %ecx
0x02b0005d:	jae 0x02b00068
0x02b00068:	movb %cl, $0x6<UINT8>
0x02b0006a:	call 0x02b00098
0x02b0006f:	adcl %eax, %eax
0x02b00071:	decl %ecx
0x02b00072:	jne 0x02b0006a
0x02b00074:	incl %eax
0x02b00075:	call 0x02b000a3
0x02b000a3:	xorl %ecx, %ecx
0x02b000a5:	incl %ecx
0x02b000a6:	call 0x02b00098
0x02b000ab:	adcl %ecx, %ecx
0x02b000ad:	call 0x02b00098
0x02b000b2:	jb 0x02b000a6
0x02b000b4:	ret

0x02b0007a:	movl %ebp, %eax
0x02b0007c:	cmpl %eax, $0x8001<UINT32>
0x02b00081:	sbbl %ecx, $0xffffffff<UINT8>
0x02b00084:	cmpl %eax, $0x781<UINT32>
0x02b00089:	sbbl %ecx, $0xffffffff<UINT8>
0x02b0008c:	pushl %esi
0x02b0008d:	movl %esi, %edi
0x02b0008f:	subl %esi, %eax
0x02b00091:	rep movsb %es:(%edi), %ds:(%esi)
0x02b00093:	popl %esi
0x02b00094:	incl %ecx
0x02b00095:	incl %ecx
0x02b00096:	jmp 0x02b00021
0x02b0005f:	movl %eax, %ebp
0x02b00061:	call 0x02b000a3
0x02b00066:	jmp 0x02b0008c
0x02b000b5:	subl %edi, 0x28(%esp)
0x02b000b9:	movl 0x1c(%esp), %edi
0x02b000bd:	popa
0x02b000be:	ret $0xc<UINT16>

0x02b006db:	movl %ecx, %eax
0x02b006dd:	incl %eax
0x02b006de:	je 116
0x02b006e0:	xorl %eax, %eax
0x02b006e2:	addl %eax, -12(%ebp)
0x02b006e5:	je 0x02b006f9
0x02b006f9:	popl %edx
0x02b006fa:	pushl %esi
0x02b006fb:	movl %edi, 0x4(%esi)
0x02b006fe:	addl %edi, %edx
0x02b00700:	cmpl %edi, -24(%ebp)
0x02b00703:	jne 4
0x02b00705:	addl %edi, %ecx
0x02b00707:	jmp 0x02b0071a
0x02b0071a:	movl %eax, %edi
0x02b0071c:	addl %eax, $0xfff<UINT32>
0x02b00721:	shrl %eax, $0xc<UINT8>
0x02b00724:	shll %eax, $0xc<UINT8>
0x02b00727:	subl %eax, %edi
0x02b00729:	movl %ecx, %eax
0x02b0072b:	xorl %eax, %eax
0x02b0072d:	pushl %edx
0x02b0072e:	movl %edx, %ecx
0x02b00730:	sarl %ecx, $0x2<UINT8>
0x02b00733:	rep stosl %es:(%edi), %eax
0x02b00735:	addl %ecx, %edx
0x02b00737:	andl %ecx, $0x3<UINT8>
0x02b0073a:	rep stosb %es:(%edi), %al
0x02b0073c:	popl %edx
0x02b0073d:	popl %esi
0x02b0074b:	xorl %eax, %eax
0x02b0074d:	popl %esi
0x02b0074e:	popl %edi
0x02b0074f:	popl %ebx
0x02b00750:	leave
0x02b00751:	ret $0x4<UINT16>

0x02b00224:	leal %ecx, 0x10002dbd(%ebp)
0x02b0022a:	testl %eax, %eax
0x02b0022c:	jne 148
0x02b00232:	pushl %esi
0x02b00233:	call 0x02b00578
0x02b00578:	pushl %ebp
0x02b00579:	movl %ebp, %esp
0x02b0057b:	addl %esp, $0xffffffe8<UINT8>
0x02b0057e:	pushl %ebx
0x02b0057f:	pushl %edi
0x02b00580:	pushl %esi
0x02b00581:	call 0x02b00586
0x02b00586:	popl %ebx
0x02b00587:	subl %ebx, $0x10001741<UINT32>
0x02b0058d:	movl %esi, 0x8(%ebp)
0x02b00590:	xorl %eax, %eax
0x02b00592:	xorl %ecx, %ecx
0x02b00594:	addl %ecx, 0x3c(%esi)
0x02b00597:	je 10
0x02b00599:	movl %edx, 0x8(%esi)
0x02b0059c:	movl %edi, %esi
0x02b0059e:	addl %esi, $0x50<UINT8>
0x02b005a1:	jmp 0x02b005aa
0x02b005aa:	movl %eax, 0x10002f27(%ebx)
0x02b005b0:	movl -4(%ebp), %eax
0x02b005b3:	movl %ebx, %esi
0x02b005b5:	movzwl %eax, 0x10(%ebx)
0x02b005b9:	testl %eax, $0x2<UINT32>
0x02b005be:	je 0x02b0060d
0x02b005c0:	pushl %ecx
0x02b005c1:	movl %esi, 0x4(%ebx)
0x02b005c4:	movl %edi, -4(%ebp)
0x02b005c7:	movl %ecx, 0x8(%ebx)
0x02b005ca:	addl %esi, %edx
0x02b005cc:	movl %eax, %ecx
0x02b005ce:	sarl %ecx, $0x2<UINT8>
0x02b005d1:	rep movsl %es:(%edi), %ds:(%esi)
0x02b005d3:	addl %ecx, %eax
0x02b005d5:	andl %ecx, $0x3<UINT8>
0x02b005d8:	rep movsb %es:(%edi), %ds:(%esi)
0x02b005da:	movl %edi, 0x4(%ebx)
0x02b005dd:	movl %ecx, 0x8(%ebx)
0x02b005e0:	addl %edi, %edx
0x02b005e2:	xorl %eax, %eax
0x02b005e4:	pushl %edx
0x02b005e5:	movl %edx, %ecx
0x02b005e7:	sarl %ecx, $0x2<UINT8>
0x02b005ea:	rep stosl %es:(%edi), %eax
0x02b005ec:	addl %ecx, %edx
0x02b005ee:	andl %ecx, $0x3<UINT8>
0x02b005f1:	rep stosb %es:(%edi), %al
0x02b005f3:	popl %edx
0x02b005f4:	movl %esi, -4(%ebp)
0x02b005f7:	movl %edi, (%ebx)
0x02b005f9:	addl %edi, %edx
0x02b005fb:	movl %ecx, 0x8(%ebx)
0x02b005fe:	movl %eax, %ecx
0x02b00600:	sarl %ecx, $0x2<UINT8>
0x02b00603:	rep movsl %es:(%edi), %ds:(%esi)
0x02b00605:	addl %ecx, %eax
0x02b00607:	andl %ecx, $0x3<UINT8>
0x02b0060a:	rep movsb %es:(%edi), %ds:(%esi)
0x02b0060c:	popl %ecx
0x02b0060d:	addl %ebx, $0x1c<UINT8>
0x02b00610:	decl %ecx
0x02b00611:	jne 0x02b005b5
0x02b00613:	popl %esi
0x02b00614:	popl %edi
0x02b00615:	popl %ebx
0x02b00616:	leave
0x02b00617:	ret $0x4<UINT16>

0x02b00238:	pushl %esi
0x02b00239:	call 0x02b00493
0x02b00493:	pushl %ebp
0x02b00494:	movl %ebp, %esp
0x02b00496:	addl %esp, $0xffffffe8<UINT8>
0x02b00499:	pushl %ebx
0x02b0049a:	pushl %edi
0x02b0049b:	pushl %esi
0x02b0049c:	call 0x02b004a1
0x02b004a1:	popl %ebx
0x02b004a2:	subl %ebx, $0x1000165c<UINT32>
0x02b004a8:	movl %esi, 0x8(%ebp)
0x02b004ab:	xorl %eax, %eax
0x02b004ad:	xorl %ecx, %ecx
0x02b004af:	addl %ecx, 0x3c(%esi)
0x02b004b2:	je 10
0x02b004b4:	movl %edx, 0x8(%esi)
0x02b004b7:	movl %edi, %esi
0x02b004b9:	addl %esi, $0x50<UINT8>
0x02b004bc:	jmp 0x02b004c5
0x02b004c5:	movl -8(%ebp), %edx
0x02b004c8:	movzwl %eax, 0x10(%esi)
0x02b004cc:	testl %eax, $0x200<UINT32>
0x02b004d1:	jne 125
0x02b004d3:	testl %eax, $0x8<UINT32>
0x02b004d8:	je 0x02b00567
0x02b00567:	addl %esi, $0x1c<UINT8>
0x02b0056a:	decl %ecx
0x02b0056b:	jne 0x02b004c8
0x02b004de:	pushl %ecx
0x02b004df:	pushl %esi
0x02b004e0:	movl %edi, 0x8(%esi)
0x02b004e3:	xorl %ecx, %ecx
0x02b004e5:	movl -4(%ebp), %ecx
0x02b004e8:	movzwl %ebx, 0x12(%esi)
0x02b004ec:	movl %esi, (%esi)
0x02b004ee:	addl %esi, -8(%ebp)
0x02b004f1:	cmpl %ecx, %edi
0x02b004f3:	jnl 0x02b00529
0x02b004f5:	movl %eax, (%esi)
0x02b004f7:	incl %esi
0x02b004f8:	movzbl %edx, %al
0x02b004fb:	addl -4(%ebp), %edx
0x02b004fe:	subb %al, $0xffffffe8<UINT8>
0x02b00500:	movl %edx, %ebx
0x02b00502:	je 0x02b0050a
0x02b00504:	decb %al
0x02b00506:	movb %dl, %bh
0x02b00508:	jne 0x02b00526
0x02b00526:	incl %ecx
0x02b00527:	jmp 0x02b004f1
0x02b0050a:	movl %eax, (%esi)
0x02b0050c:	cmpb %al, %dl
0x02b0050e:	jne 13
0x02b00510:	shrw %ax, $0x8<UINT8>
0x02b00514:	roll %eax, $0x10<UINT8>
0x02b00517:	xchgb %ah, %al
0x02b00519:	subl %eax, %ecx
0x02b0051b:	movl (%esi), %eax
0x02b0051d:	addl -4(%ebp), %eax
0x02b00520:	addl %esi, $0x4<UINT8>
0x02b00523:	addl %ecx, $0x4<UINT8>
0x02b00529:	popl %esi
0x02b0052a:	popl %ecx
0x02b0052b:	xorl %eax, %eax
0x02b0052d:	addl %eax, 0x14(%esi)
0x02b00530:	je 53
0x02b00532:	cmpl %eax, -4(%ebp)
0x02b00535:	je 0x02b00567
0x02b00571:	popl %esi
0x02b00572:	popl %edi
0x02b00573:	popl %ebx
0x02b00574:	leave
0x02b00575:	ret $0x4<UINT16>

0x02b0023e:	nop
0x02b0023f:	nop
0x02b00240:	nop
0x02b00241:	nop
0x02b00242:	nop
0x02b00243:	nop
0x02b00244:	nop
0x02b00245:	nop
0x02b00246:	nop
0x02b00247:	nop
0x02b00248:	nop
0x02b00249:	nop
0x02b0024a:	nop
0x02b0024b:	nop
0x02b0024c:	movl %ecx, 0x34(%esi)
0x02b0024f:	testl %ecx, %ecx
0x02b00251:	je 137
0x02b00257:	addl %ecx, 0x8(%esi)
0x02b0025a:	pushl %ecx
0x02b0025b:	pushl %esi
0x02b0025c:	call 0x02b008a8
0x02b008a8:	pushl %ebp
0x02b008a9:	movl %ebp, %esp
0x02b008ab:	pushl %ebx
0x02b008ac:	pushl %edi
0x02b008ad:	pushl %esi
0x02b008ae:	movl %esi, 0xc(%ebp)
0x02b008b1:	movl %ebx, 0x8(%ebp)
0x02b008b4:	xorl %eax, %eax
0x02b008b6:	cmpl 0x10(%esi), %eax
0x02b008b9:	jne 0x02b008bf
0x02b008bf:	addl %eax, (%esi)
0x02b008c1:	je 3
0x02b008c3:	addl %eax, 0x8(%ebx)
0x02b008c6:	movl %ecx, 0xc(%esi)
0x02b008c9:	addl %ecx, 0x8(%ebx)
0x02b008cc:	movl %edi, 0x10(%esi)
0x02b008cf:	testl %edi, %edi
0x02b008d1:	je 3
0x02b008d3:	addl %edi, 0x8(%ebx)
0x02b008d6:	pushl %eax
0x02b008d7:	pushl %edi
0x02b008d8:	pushl %ecx
0x02b008d9:	pushl %ebx
0x02b008da:	call 0x02b008ef
0x02b008ef:	pushl %ebp
0x02b008f0:	movl %ebp, %esp
0x02b008f2:	addl %esp, $0xffffffe8<UINT8>
0x02b008f5:	pushl %ebx
0x02b008f6:	pushl %edi
0x02b008f7:	pushl %esi
0x02b008f8:	call 0x02b008fd
0x02b008fd:	popl %ebx
0x02b008fe:	subl %ebx, $0x10001ab8<UINT32>
0x02b00904:	movl %eax, 0xc(%ebp)
0x02b00907:	movl 0x10002d9c(%ebx), %eax
0x02b0090d:	xorl %eax, %eax
0x02b0090f:	movl 0x10002da0(%ebx), %eax
0x02b00915:	xorl %esi, %esi
0x02b00917:	incl %esi
0x02b00918:	incl %esi
0x02b00919:	movl %edx, 0x10002eef(%ebx)
0x02b0091f:	pushl 0xc(%ebp)
0x02b00922:	call GetModuleHandleA@kernel32.dll
GetModuleHandleA@kernel32.dll: API Node	
0x02b00924:	movl -4(%ebp), %eax
0x02b00927:	movl %edx, 0x10002f1b(%ebx)
0x02b0092d:	testl %eax, %eax
0x02b0092f:	jne 0x02b0093e
0x02b0093e:	nop
0x02b0093f:	nop
0x02b00940:	nop
0x02b00941:	nop
0x02b00942:	nop
0x02b00943:	nop
0x02b00944:	nop
0x02b00945:	nop
0x02b00946:	nop
0x02b00947:	nop
0x02b00948:	nop
0x02b00949:	nop
0x02b0094a:	nop
0x02b0094b:	nop
0x02b0094c:	nop
0x02b0094d:	nop
0x02b0094e:	nop
0x02b0094f:	nop
0x02b00950:	nop
0x02b00951:	nop
0x02b00952:	nop
0x02b00953:	nop
0x02b00954:	nop
0x02b00955:	nop
0x02b00956:	nop
0x02b00957:	nop
0x02b00958:	nop
0x02b00959:	nop
0x02b0095a:	nop
0x02b0095b:	nop
0x02b0095c:	nop
0x02b0095d:	nop
0x02b0095e:	nop
0x02b0095f:	nop
0x02b00960:	nop
0x02b00961:	nop
0x02b00962:	nop
0x02b00963:	nop
0x02b00964:	nop
0x02b00965:	nop
0x02b00966:	nop
0x02b00967:	nop
0x02b00968:	movl %esi, 0x10(%ebp)
0x02b0096b:	movl %edi, 0x8(%ebp)
0x02b0096e:	movl %edx, 0x14(%ebp)
0x02b00971:	testl %edx, %edx
0x02b00973:	jne 0x02b00977
0x02b00977:	testl %esi, %esi
0x02b00979:	jne 0x02b0097d
0x02b0097d:	movl 0x10002da0(%ebx), $0x0<UINT32>
0x02b00987:	movl %eax, (%edx)
0x02b00989:	testl %eax, %eax
0x02b0098b:	je 68
0x02b0098d:	pushl %edx
0x02b0098e:	movl 0x10002da0(%ebx), %eax
0x02b00994:	testl %eax, $0x80000000<UINT32>
0x02b00999:	je 0x02b009a4
0x02b009a4:	movl %ecx, 0x8(%ebp)
0x02b009a7:	addl %eax, 0x8(%ecx)
0x02b009aa:	xorl %ecx, %ecx
0x02b009ac:	movw %cx, (%eax)
0x02b009af:	pushl %ecx
0x02b009b0:	incl %eax
0x02b009b1:	incl %eax
0x02b009b2:	pushl %eax
0x02b009b3:	pushl -4(%ebp)
0x02b009b6:	call 0x02b00ad2
0x02b00ad2:	pushl %ebp
0x02b00ad3:	movl %ebp, %esp
0x02b00ad5:	pushl %ebx
0x02b00ad6:	pushl %edi
0x02b00ad7:	pushl %esi
0x02b00ad8:	call 0x02b00add
0x02b00add:	popl %ebx
0x02b00ade:	subl %ebx, $0x10001c98<UINT32>
0x02b00ae4:	pushl 0xc(%ebp)
0x02b00ae7:	pushl 0x8(%ebp)
0x02b00aea:	call GetProcAddress@kernel32.dll
0x02b00af0:	popl %esi
0x02b00af1:	popl %edi
0x02b00af2:	popl %ebx
0x02b00af3:	leave
0x02b00af4:	ret $0xc<UINT16>

0x02b009bc:	popl %edx
0x02b009bd:	testl %eax, %eax
0x02b009bf:	je 0x02b00934
0x02b00934:	xorl %eax, %eax
0x02b00936:	decl %eax
0x02b00937:	popl %esi
0x02b00938:	popl %edi
0x02b00939:	popl %ebx
0x02b0093a:	leave
0x02b0093b:	ret $0x10<UINT16>

0x02b008df:	incl %eax
0x02b008e0:	jne 8
0x02b008e2:	decl %eax
0x02b008e3:	popl %esi
0x02b008e4:	popl %edi
0x02b008e5:	popl %ebx
0x02b008e6:	leave
0x02b008e7:	ret $0x8<UINT16>

0x02b00261:	testl %eax, %eax
0x02b00263:	je 123
0x02b00265:	movl %edx, 0x10002d9c(%ebp)
0x02b0026b:	movl %ecx, 0x10002da0(%ebp)
0x02b00271:	testl %ecx, %ecx
0x02b00273:	jne 0x02b0027d
0x02b0027d:	testl %ecx, $0x80000000<UINT32>
0x02b00283:	je 0x02b002a3
0x02b002a3:	movl %eax, 0x8(%esi)
0x02b002a6:	addl %ecx, %eax
0x02b002a8:	incl %ecx
0x02b002a9:	incl %ecx
0x02b002aa:	pushl %edx
0x02b002ab:	pushl %ecx
0x02b002ac:	leal %eax, 0x10002dd2(%ebp)
0x02b002b2:	pushl %eax
0x02b002b3:	leal %eax, 0x10002f3f(%ebp)
0x02b002b9:	pushl %eax
0x02b002ba:	call wsprintfA@user32.dll
wsprintfA@user32.dll: API Node	
0x02b002c0:	leal %ecx, 0x10002f3f(%ebp)
0x02b002c6:	pushl $0x10<UINT8>
0x02b002c8:	leal %eax, 0x10002dab(%ebp)
0x02b002ce:	pushl %eax
0x02b002cf:	pushl %ecx
0x02b002d0:	pushl $0x0<UINT8>
0x02b002d2:	call MessageBoxA@user32.dll
MessageBoxA@user32.dll: API Node	
0x02b002d8:	pushl $0x0<UINT8>
0x02b002da:	call ExitProcess@kernel32.dll
ExitProcess@kernel32.dll: Exit Node	
