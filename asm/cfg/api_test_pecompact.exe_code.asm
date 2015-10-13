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
0x004048f6:	call 0x00170188
0x00170188:	pushl %ebx
0x00170189:	pushl %edi
0x0017018a:	pushl %esi
0x0017018b:	pushl %ebp
0x0017018c:	call 0x00170191
0x00170191:	popl %ebp
0x00170192:	subl %ebp, $0x1000134c<UINT32>
0x00170198:	leal %esi, 0x10001343(%ebp)
0x0017019e:	movl %eax, -4(%esi)
0x001701a1:	addl %eax, $0x4<UINT8>
0x001701a4:	subl %esi, %eax
0x001701a6:	cld
0x001701a7:	movl %ebx, %esi
0x001701a9:	movl %edx, 0x8(%esi)
0x001701ac:	movl %esi, 0x1c(%esi)
0x001701af:	addl %esi, %edx
0x001701b1:	leal %edi, 0x10002f2f(%ebp)
0x001701b7:	lodsl %eax, %ds:(%esi)
0x001701b8:	stosl %es:(%edi), %eax
0x001701b9:	lodsl %eax, %ds:(%esi)
0x001701ba:	stosl %es:(%edi), %eax
0x001701bb:	lodsl %eax, %ds:(%esi)
0x001701bc:	stosl %es:(%edi), %eax
0x001701bd:	lodsl %eax, %ds:(%esi)
0x001701be:	stosl %es:(%edi), %eax
0x001701bf:	nop
0x001701c0:	cmpl 0x48(%ebx), $0x1<UINT8>
0x001701c4:	je 21
0x001701c6:	movl %esi, 0x44(%ebx)
0x001701c9:	testl %esi, %esi
0x001701cb:	je 14
0x001701cd:	movl %ecx, $0x23<UINT32>
0x001701d2:	addl %esi, %edx
0x001701d4:	movl %edi, 0x40(%ebx)
0x001701d7:	addl %edi, %edx
0x001701d9:	rep movsb %es:(%edi), %ds:(%esi)
0x001701db:	movl %esi, %ebx
0x001701dd:	leal %edi, 0x10002f1b(%ebp)
0x001701e3:	addl (%edi), %ebp
0x001701e5:	addl 0x4(%edi), %ebp
0x001701e8:	addl 0x8(%edi), %ebp
0x001701eb:	leal %ecx, 0x10002eff(%ebp)
0x001701f1:	pushl %ecx
0x001701f2:	call 0x0017033d
0x0017033d:	pushl %ebp
0x0017033e:	movl %ebp, %esp
0x00170340:	addl %esp, $0xfffffffc<UINT8>
0x00170343:	pushl %ebx
0x00170344:	pushl %edi
0x00170345:	pushl %esi
0x00170346:	call 0x0017034b
0x0017034b:	popl %ebx
0x0017034c:	subl %ebx, $0x10001506<UINT32>
0x00170352:	movl %esi, 0x8(%ebp)
0x00170355:	movl %ecx, (%esi)
0x00170357:	addl %ecx, %ebx
0x00170359:	pushl %ecx
0x0017035a:	call LoadLibraryA@kernel32.dll
LoadLibraryA@kernel32.dll: API Node	
0x00170360:	movl -4(%ebp), %eax
0x00170363:	movl %edx, 0x4(%esi)
0x00170366:	movl %edi, 0x8(%esi)
0x00170369:	addl %edx, %ebx
0x0017036b:	addl %edi, %ebx
0x0017036d:	xorl %eax, %eax
0x0017036f:	addl %eax, (%edx)
0x00170371:	je 0x00170389
0x00170373:	pushl %edx
0x00170374:	movl %eax, (%edx)
0x00170376:	addl %eax, %ebx
0x00170378:	pushl %eax
0x00170379:	pushl -4(%ebp)
0x0017037c:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x00170382:	stosl %es:(%edi), %eax
0x00170383:	popl %edx
0x00170384:	addl %edx, $0x4<UINT8>
0x00170387:	jmp 0x0017036d
0x00170389:	addl %esi, $0xc<UINT8>
0x0017038c:	addl %eax, (%esi)
0x0017038e:	jne 0x00170355
0x00170390:	popl %esi
0x00170391:	popl %edi
0x00170392:	popl %ebx
0x00170393:	leave
0x00170394:	ret $0x4<UINT16>

0x001701f7:	nop
0x001701f8:	nop
0x001701f9:	nop
0x001701fa:	nop
0x001701fb:	nop
0x001701fc:	nop
0x001701fd:	nop
0x001701fe:	nop
0x001701ff:	movl %ecx, 0x2c(%esi)
0x00170202:	movl 0x10002f2b(%ebp), %ecx
0x00170208:	pushl $0x4<UINT8>
0x0017020a:	pushl $0x1000<UINT32>
0x0017020f:	pushl %ecx
0x00170210:	pushl $0x0<UINT8>
0x00170212:	call VirtualAlloc@kernel32.dll
0x00170218:	movl 0x10002f27(%ebp), %eax
0x0017021e:	pushl %esi
0x0017021f:	call 0x0017061a
0x0017061a:	pushl %ebp
0x0017061b:	movl %ebp, %esp
0x0017061d:	addl %esp, $0xffffffe8<UINT8>
0x00170620:	pushl %ebx
0x00170621:	pushl %edi
0x00170622:	pushl %esi
0x00170623:	call 0x00170628
0x00170628:	popl %ebx
0x00170629:	subl %ebx, $0x100017e3<UINT32>
0x0017062f:	movl %esi, 0x8(%ebp)
0x00170632:	xorl %eax, %eax
0x00170634:	xorl %ecx, %ecx
0x00170636:	addl %ecx, 0x3c(%esi)
0x00170639:	je 10
0x0017063b:	movl %edx, 0x8(%esi)
0x0017063e:	movl %edi, %esi
0x00170640:	addl %esi, $0x50<UINT8>
0x00170643:	jmp 0x0017064c
0x0017064c:	movl -4(%ebp), %eax
0x0017064f:	movzwl %eax, 0x2(%edi)
0x00170653:	movl -16(%ebp), %eax
0x00170656:	pushl %ecx
0x00170657:	pushl %edx
0x00170658:	pushl %esi
0x00170659:	movzwl %eax, 0x10(%esi)
0x0017065d:	testl %eax, $0x10<UINT32>
0x00170662:	je 0x0017073e
0x0017073e:	popl %esi
0x0017073f:	popl %edx
0x00170740:	popl %ecx
0x00170741:	addl %esi, $0x1c<UINT8>
0x00170744:	decl %ecx
0x00170745:	jne 0x00170656
0x00170668:	pushl %esi
0x00170669:	movl %edi, 0x10002f27(%ebx)
0x0017066f:	movl -20(%ebp), %edi
0x00170672:	movl %ecx, 0x8(%esi)
0x00170675:	movl %eax, 0x14(%esi)
0x00170678:	subl %ecx, %eax
0x0017067a:	movl %esi, (%esi)
0x0017067c:	addl %esi, %edx
0x0017067e:	movl %eax, %ecx
0x00170680:	sarl %ecx, $0x2<UINT8>
0x00170683:	rep movsl %es:(%edi), %ds:(%esi)
0x00170685:	addl %ecx, %eax
0x00170687:	andl %ecx, $0x3<UINT8>
0x0017068a:	rep movsb %es:(%edi), %ds:(%esi)
0x0017068c:	popl %esi
0x0017068d:	nop
0x0017068e:	nop
0x0017068f:	nop
0x00170690:	nop
0x00170691:	nop
0x00170692:	nop
0x00170693:	nop
0x00170694:	nop
0x00170695:	nop
0x00170696:	nop
0x00170697:	nop
0x00170698:	nop
0x00170699:	nop
0x0017069a:	nop
0x0017069b:	nop
0x0017069c:	nop
0x0017069d:	nop
0x0017069e:	nop
0x0017069f:	nop
0x001706a0:	nop
0x001706a1:	nop
0x001706a2:	nop
0x001706a3:	nop
0x001706a4:	nop
0x001706a5:	nop
0x001706a6:	nop
0x001706a7:	nop
0x001706a8:	nop
0x001706a9:	nop
0x001706aa:	nop
0x001706ab:	nop
0x001706ac:	nop
0x001706ad:	nop
0x001706ae:	nop
0x001706af:	nop
0x001706b0:	nop
0x001706b1:	nop
0x001706b2:	nop
0x001706b3:	movl %eax, 0x4(%esi)
0x001706b6:	addl %eax, %edx
0x001706b8:	movl -24(%ebp), %eax
0x001706bb:	movl %eax, -16(%ebp)
0x001706be:	decl %eax
0x001706bf:	movl -12(%ebp), %eax
0x001706c2:	pushl %edx
0x001706c3:	pushl %eax
0x001706c4:	pushl 0x8(%ebp)
0x001706c7:	call 0x00170a08
0x00170a08:	pushl %ebp
0x00170a09:	movl %ebp, %esp
0x00170a0b:	addl %esp, $0xfffffffc<UINT8>
0x00170a0e:	pushl %ebx
0x00170a0f:	pushl %edi
0x00170a10:	pushl %esi
0x00170a11:	movl %ebx, 0x8(%ebp)
0x00170a14:	movl %esi, %ebx
0x00170a16:	movl %ecx, 0x30(%ebx)
0x00170a19:	subl %esi, %ecx
0x00170a1b:	movl -4(%ebp), %esi
0x00170a1e:	xorl %ecx, %ecx
0x00170a20:	lodsl %eax, %ds:(%esi)
0x00170a21:	testl %eax, %eax
0x00170a23:	je 28
0x00170a25:	cmpl %ecx, 0xc(%ebp)
0x00170a28:	je 0x00170a2d
0x00170a2d:	nop
0x00170a2e:	nop
0x00170a2f:	nop
0x00170a30:	nop
0x00170a31:	nop
0x00170a32:	nop
0x00170a33:	nop
0x00170a34:	nop
0x00170a35:	nop
0x00170a36:	nop
0x00170a37:	nop
0x00170a38:	nop
0x00170a39:	nop
0x00170a3a:	nop
0x00170a3b:	nop
0x00170a3c:	nop
0x00170a3d:	nop
0x00170a3e:	addl %eax, -4(%ebp)
0x00170a41:	popl %esi
0x00170a42:	popl %edi
0x00170a43:	popl %ebx
0x00170a44:	leave
0x00170a45:	ret $0x8<UINT16>

0x001706cc:	leal %ecx, 0x10002f2f(%ebx)
0x001706d2:	pushl %ecx
0x001706d3:	pushl -24(%ebp)
0x001706d6:	pushl -20(%ebp)
0x001706d9:	call 0x00170008
0x00170008:	pusha
0x00170009:	movl %esi, 0x24(%esp)
0x0017000d:	movl %edi, 0x28(%esp)
0x00170011:	cld
0x00170012:	lodsl %eax, %ds:(%esi)
0x00170013:	xorl %ecx, %ecx
0x00170015:	testl %eax, %eax
0x00170017:	je 17
0x00170019:	xorl %edx, %edx
0x0017001b:	leal %ebx, (%eax,%edi)
0x0017001e:	movsb %es:(%edi), %ds:(%esi)
0x0017001f:	movb %cl, $0x3<UINT8>
0x00170021:	call 0x00170098
0x00170098:	addl %edx, %edx
0x0017009a:	jne 0x001700a2
0x0017009c:	xchgl %edx, %eax
0x0017009d:	lodsl %eax, %ds:(%esi)
0x0017009e:	xchgl %edx, %eax
0x0017009f:	addl %edx, %edx
0x001700a1:	incl %edx
0x001700a2:	ret

0x00170026:	jae 0x0017001e
0x00170028:	cmpl %edi, %ebx
0x0017002a:	jae 0x001700b5
0x00170030:	pushl %ebx
0x00170031:	pushl %ebp
0x00170032:	pushl %edi
0x00170033:	xorl %ebx, %ebx
0x00170035:	incl %ebx
0x00170036:	xorl %ebp, %ebp
0x00170038:	movl %eax, %ebx
0x0017003a:	leal %edi, (%ebp,%ebx)
0x0017003e:	movl %ebp, %ebx
0x00170040:	movl %ebx, %edi
0x00170042:	call 0x00170098
0x00170047:	jae 0x0017003a
0x00170049:	leal %ebx, (%ebp,%edi)
0x0017004d:	addl %eax, %edi
0x0017004f:	movl %ebp, %edi
0x00170051:	call 0x00170098
0x00170056:	jae 0x0017003a
0x00170058:	popl %edi
0x00170059:	popl %ebp
0x0017005a:	popl %ebx
0x0017005b:	subl %eax, %ecx
0x0017005d:	jae 0x00170068
0x00170068:	movb %cl, $0x6<UINT8>
0x0017006a:	call 0x00170098
0x0017006f:	adcl %eax, %eax
0x00170071:	decl %ecx
0x00170072:	jne 0x0017006a
0x00170074:	incl %eax
0x00170075:	call 0x001700a3
0x001700a3:	xorl %ecx, %ecx
0x001700a5:	incl %ecx
0x001700a6:	call 0x00170098
0x001700ab:	adcl %ecx, %ecx
0x001700ad:	call 0x00170098
0x001700b2:	jb 0x001700a6
0x001700b4:	ret

0x0017007a:	movl %ebp, %eax
0x0017007c:	cmpl %eax, $0x8001<UINT32>
0x00170081:	sbbl %ecx, $0xffffffff<UINT8>
0x00170084:	cmpl %eax, $0x781<UINT32>
0x00170089:	sbbl %ecx, $0xffffffff<UINT8>
0x0017008c:	pushl %esi
0x0017008d:	movl %esi, %edi
0x0017008f:	subl %esi, %eax
0x00170091:	rep movsb %es:(%edi), %ds:(%esi)
0x00170093:	popl %esi
0x00170094:	incl %ecx
0x00170095:	incl %ecx
0x00170096:	jmp 0x00170021
0x0017005f:	movl %eax, %ebp
0x00170061:	call 0x001700a3
0x00170066:	jmp 0x0017008c
0x001700b5:	subl %edi, 0x28(%esp)
0x001700b9:	movl 0x1c(%esp), %edi
0x001700bd:	popa
0x001700be:	ret $0xc<UINT16>

0x001706db:	movl %ecx, %eax
0x001706dd:	incl %eax
0x001706de:	je 116
0x001706e0:	xorl %eax, %eax
0x001706e2:	addl %eax, -12(%ebp)
0x001706e5:	je 0x001706f9
0x001706f9:	popl %edx
0x001706fa:	pushl %esi
0x001706fb:	movl %edi, 0x4(%esi)
0x001706fe:	addl %edi, %edx
0x00170700:	cmpl %edi, -24(%ebp)
0x00170703:	jne 4
0x00170705:	addl %edi, %ecx
0x00170707:	jmp 0x0017071a
0x0017071a:	movl %eax, %edi
0x0017071c:	addl %eax, $0xfff<UINT32>
0x00170721:	shrl %eax, $0xc<UINT8>
0x00170724:	shll %eax, $0xc<UINT8>
0x00170727:	subl %eax, %edi
0x00170729:	movl %ecx, %eax
0x0017072b:	xorl %eax, %eax
0x0017072d:	pushl %edx
0x0017072e:	movl %edx, %ecx
0x00170730:	sarl %ecx, $0x2<UINT8>
0x00170733:	rep stosl %es:(%edi), %eax
0x00170735:	addl %ecx, %edx
0x00170737:	andl %ecx, $0x3<UINT8>
0x0017073a:	rep stosb %es:(%edi), %al
0x0017073c:	popl %edx
0x0017073d:	popl %esi
0x0017074b:	xorl %eax, %eax
0x0017074d:	popl %esi
0x0017074e:	popl %edi
0x0017074f:	popl %ebx
0x00170750:	leave
0x00170751:	ret $0x4<UINT16>

0x00170224:	leal %ecx, 0x10002dbd(%ebp)
0x0017022a:	testl %eax, %eax
0x0017022c:	jne 148
0x00170232:	pushl %esi
0x00170233:	call 0x00170578
0x00170578:	pushl %ebp
0x00170579:	movl %ebp, %esp
0x0017057b:	addl %esp, $0xffffffe8<UINT8>
0x0017057e:	pushl %ebx
0x0017057f:	pushl %edi
0x00170580:	pushl %esi
0x00170581:	call 0x00170586
0x00170586:	popl %ebx
0x00170587:	subl %ebx, $0x10001741<UINT32>
0x0017058d:	movl %esi, 0x8(%ebp)
0x00170590:	xorl %eax, %eax
0x00170592:	xorl %ecx, %ecx
0x00170594:	addl %ecx, 0x3c(%esi)
0x00170597:	je 10
0x00170599:	movl %edx, 0x8(%esi)
0x0017059c:	movl %edi, %esi
0x0017059e:	addl %esi, $0x50<UINT8>
0x001705a1:	jmp 0x001705aa
0x001705aa:	movl %eax, 0x10002f27(%ebx)
0x001705b0:	movl -4(%ebp), %eax
0x001705b3:	movl %ebx, %esi
0x001705b5:	movzwl %eax, 0x10(%ebx)
0x001705b9:	testl %eax, $0x2<UINT32>
0x001705be:	je 0x0017060d
0x001705c0:	pushl %ecx
0x001705c1:	movl %esi, 0x4(%ebx)
0x001705c4:	movl %edi, -4(%ebp)
0x001705c7:	movl %ecx, 0x8(%ebx)
0x001705ca:	addl %esi, %edx
0x001705cc:	movl %eax, %ecx
0x001705ce:	sarl %ecx, $0x2<UINT8>
0x001705d1:	rep movsl %es:(%edi), %ds:(%esi)
0x001705d3:	addl %ecx, %eax
0x001705d5:	andl %ecx, $0x3<UINT8>
0x001705d8:	rep movsb %es:(%edi), %ds:(%esi)
0x001705da:	movl %edi, 0x4(%ebx)
0x001705dd:	movl %ecx, 0x8(%ebx)
0x001705e0:	addl %edi, %edx
0x001705e2:	xorl %eax, %eax
0x001705e4:	pushl %edx
0x001705e5:	movl %edx, %ecx
0x001705e7:	sarl %ecx, $0x2<UINT8>
0x001705ea:	rep stosl %es:(%edi), %eax
0x001705ec:	addl %ecx, %edx
0x001705ee:	andl %ecx, $0x3<UINT8>
0x001705f1:	rep stosb %es:(%edi), %al
0x001705f3:	popl %edx
0x001705f4:	movl %esi, -4(%ebp)
0x001705f7:	movl %edi, (%ebx)
0x001705f9:	addl %edi, %edx
0x001705fb:	movl %ecx, 0x8(%ebx)
0x001705fe:	movl %eax, %ecx
0x00170600:	sarl %ecx, $0x2<UINT8>
0x00170603:	rep movsl %es:(%edi), %ds:(%esi)
0x00170605:	addl %ecx, %eax
0x00170607:	andl %ecx, $0x3<UINT8>
0x0017060a:	rep movsb %es:(%edi), %ds:(%esi)
0x0017060c:	popl %ecx
0x0017060d:	addl %ebx, $0x1c<UINT8>
0x00170610:	decl %ecx
0x00170611:	jne 0x001705b5
0x00170613:	popl %esi
0x00170614:	popl %edi
0x00170615:	popl %ebx
0x00170616:	leave
0x00170617:	ret $0x4<UINT16>

0x00170238:	pushl %esi
0x00170239:	call 0x00170493
0x00170493:	pushl %ebp
0x00170494:	movl %ebp, %esp
0x00170496:	addl %esp, $0xffffffe8<UINT8>
0x00170499:	pushl %ebx
0x0017049a:	pushl %edi
0x0017049b:	pushl %esi
0x0017049c:	call 0x001704a1
0x001704a1:	popl %ebx
0x001704a2:	subl %ebx, $0x1000165c<UINT32>
0x001704a8:	movl %esi, 0x8(%ebp)
0x001704ab:	xorl %eax, %eax
0x001704ad:	xorl %ecx, %ecx
0x001704af:	addl %ecx, 0x3c(%esi)
0x001704b2:	je 10
0x001704b4:	movl %edx, 0x8(%esi)
0x001704b7:	movl %edi, %esi
0x001704b9:	addl %esi, $0x50<UINT8>
0x001704bc:	jmp 0x001704c5
0x001704c5:	movl -8(%ebp), %edx
0x001704c8:	movzwl %eax, 0x10(%esi)
0x001704cc:	testl %eax, $0x200<UINT32>
0x001704d1:	jne 125
0x001704d3:	testl %eax, $0x8<UINT32>
0x001704d8:	je 0x00170567
0x00170567:	addl %esi, $0x1c<UINT8>
0x0017056a:	decl %ecx
0x0017056b:	jne 0x001704c8
0x001704de:	pushl %ecx
0x001704df:	pushl %esi
0x001704e0:	movl %edi, 0x8(%esi)
0x001704e3:	xorl %ecx, %ecx
0x001704e5:	movl -4(%ebp), %ecx
0x001704e8:	movzwl %ebx, 0x12(%esi)
0x001704ec:	movl %esi, (%esi)
0x001704ee:	addl %esi, -8(%ebp)
0x001704f1:	cmpl %ecx, %edi
0x001704f3:	jnl 0x00170529
0x001704f5:	movl %eax, (%esi)
0x001704f7:	incl %esi
0x001704f8:	movzbl %edx, %al
0x001704fb:	addl -4(%ebp), %edx
0x001704fe:	subb %al, $0xffffffe8<UINT8>
0x00170500:	movl %edx, %ebx
0x00170502:	je 0x0017050a
0x00170504:	decb %al
0x00170506:	movb %dl, %bh
0x00170508:	jne 0x00170526
0x00170526:	incl %ecx
0x00170527:	jmp 0x001704f1
0x0017050a:	movl %eax, (%esi)
0x0017050c:	cmpb %al, %dl
0x0017050e:	jne 13
0x00170510:	shrw %ax, $0x8<UINT8>
0x00170514:	roll %eax, $0x10<UINT8>
0x00170517:	xchgb %ah, %al
0x00170519:	subl %eax, %ecx
0x0017051b:	movl (%esi), %eax
0x0017051d:	addl -4(%ebp), %eax
0x00170520:	addl %esi, $0x4<UINT8>
0x00170523:	addl %ecx, $0x4<UINT8>
0x00170529:	popl %esi
0x0017052a:	popl %ecx
0x0017052b:	xorl %eax, %eax
0x0017052d:	addl %eax, 0x14(%esi)
0x00170530:	je 53
0x00170532:	cmpl %eax, -4(%ebp)
0x00170535:	je 0x00170567
0x00170571:	popl %esi
0x00170572:	popl %edi
0x00170573:	popl %ebx
0x00170574:	leave
0x00170575:	ret $0x4<UINT16>

0x0017023e:	nop
0x0017023f:	nop
0x00170240:	nop
0x00170241:	nop
0x00170242:	nop
0x00170243:	nop
0x00170244:	nop
0x00170245:	nop
0x00170246:	nop
0x00170247:	nop
0x00170248:	nop
0x00170249:	nop
0x0017024a:	nop
0x0017024b:	nop
0x0017024c:	movl %ecx, 0x34(%esi)
0x0017024f:	testl %ecx, %ecx
0x00170251:	je 137
0x00170257:	addl %ecx, 0x8(%esi)
0x0017025a:	pushl %ecx
0x0017025b:	pushl %esi
0x0017025c:	call 0x001708a8
0x001708a8:	pushl %ebp
0x001708a9:	movl %ebp, %esp
0x001708ab:	pushl %ebx
0x001708ac:	pushl %edi
0x001708ad:	pushl %esi
0x001708ae:	movl %esi, 0xc(%ebp)
0x001708b1:	movl %ebx, 0x8(%ebp)
0x001708b4:	xorl %eax, %eax
0x001708b6:	cmpl 0x10(%esi), %eax
0x001708b9:	jne 0x001708bf
0x001708bf:	addl %eax, (%esi)
0x001708c1:	je 3
0x001708c3:	addl %eax, 0x8(%ebx)
0x001708c6:	movl %ecx, 0xc(%esi)
0x001708c9:	addl %ecx, 0x8(%ebx)
0x001708cc:	movl %edi, 0x10(%esi)
0x001708cf:	testl %edi, %edi
0x001708d1:	je 3
0x001708d3:	addl %edi, 0x8(%ebx)
0x001708d6:	pushl %eax
0x001708d7:	pushl %edi
0x001708d8:	pushl %ecx
0x001708d9:	pushl %ebx
0x001708da:	call 0x001708ef
0x001708ef:	pushl %ebp
0x001708f0:	movl %ebp, %esp
0x001708f2:	addl %esp, $0xffffffe8<UINT8>
0x001708f5:	pushl %ebx
0x001708f6:	pushl %edi
0x001708f7:	pushl %esi
0x001708f8:	call 0x001708fd
0x001708fd:	popl %ebx
0x001708fe:	subl %ebx, $0x10001ab8<UINT32>
0x00170904:	movl %eax, 0xc(%ebp)
0x00170907:	movl 0x10002d9c(%ebx), %eax
0x0017090d:	xorl %eax, %eax
0x0017090f:	movl 0x10002da0(%ebx), %eax
0x00170915:	xorl %esi, %esi
0x00170917:	incl %esi
0x00170918:	incl %esi
0x00170919:	movl %edx, 0x10002eef(%ebx)
0x0017091f:	pushl 0xc(%ebp)
0x00170922:	call GetModuleHandleA@kernel32.dll
GetModuleHandleA@kernel32: API Node	
0x00170924:	movl -4(%ebp), %eax
0x00170927:	movl %edx, 0x10002f1b(%ebx)
0x0017092d:	testl %eax, %eax
0x0017092f:	jne 0x0017093e
0x0017093e:	nop
0x0017093f:	nop
0x00170940:	nop
0x00170941:	nop
0x00170942:	nop
0x00170943:	nop
0x00170944:	nop
0x00170945:	nop
0x00170946:	nop
0x00170947:	nop
0x00170948:	nop
0x00170949:	nop
0x0017094a:	nop
0x0017094b:	nop
0x0017094c:	nop
0x0017094d:	nop
0x0017094e:	nop
0x0017094f:	nop
0x00170950:	nop
0x00170951:	nop
0x00170952:	nop
0x00170953:	nop
0x00170954:	nop
0x00170955:	nop
0x00170956:	nop
0x00170957:	nop
0x00170958:	nop
0x00170959:	nop
0x0017095a:	nop
0x0017095b:	nop
0x0017095c:	nop
0x0017095d:	nop
0x0017095e:	nop
0x0017095f:	nop
0x00170960:	nop
0x00170961:	nop
0x00170962:	nop
0x00170963:	nop
0x00170964:	nop
0x00170965:	nop
0x00170966:	nop
0x00170967:	nop
0x00170968:	movl %esi, 0x10(%ebp)
0x0017096b:	movl %edi, 0x8(%ebp)
0x0017096e:	movl %edx, 0x14(%ebp)
0x00170971:	testl %edx, %edx
0x00170973:	jne 0x00170977
0x00170977:	testl %esi, %esi
0x00170979:	jne 0x0017097d
0x0017097d:	movl 0x10002da0(%ebx), $0x0<UINT32>
0x00170987:	movl %eax, (%edx)
0x00170989:	testl %eax, %eax
0x0017098b:	je 0x001709d1
0x0017098d:	pushl %edx
0x0017098e:	movl 0x10002da0(%ebx), %eax
0x00170994:	testl %eax, $0x80000000<UINT32>
0x00170999:	je 0x001709a4
0x001709a4:	movl %ecx, 0x8(%ebp)
0x001709a7:	addl %eax, 0x8(%ecx)
0x001709aa:	xorl %ecx, %ecx
0x001709ac:	movw %cx, (%eax)
0x001709af:	pushl %ecx
0x001709b0:	incl %eax
0x001709b1:	incl %eax
0x001709b2:	pushl %eax
0x001709b3:	pushl -4(%ebp)
0x001709b6:	call 0x00170ad2
0x00170ad2:	pushl %ebp
0x00170ad3:	movl %ebp, %esp
0x00170ad5:	pushl %ebx
0x00170ad6:	pushl %edi
0x00170ad7:	pushl %esi
0x00170ad8:	call 0x00170add
0x00170add:	popl %ebx
0x00170ade:	subl %ebx, $0x10001c98<UINT32>
0x00170ae4:	pushl 0xc(%ebp)
0x00170ae7:	pushl 0x8(%ebp)
0x00170aea:	call GetProcAddress@kernel32.dll
0x00170af0:	popl %esi
0x00170af1:	popl %edi
0x00170af2:	popl %ebx
0x00170af3:	leave
0x00170af4:	ret $0xc<UINT16>

0x001709bc:	popl %edx
0x001709bd:	testl %eax, %eax
0x001709bf:	je -145
0x001709c5:	movl (%esi), %eax
0x001709c7:	movl (%edx), %eax
0x001709c9:	addl %edx, $0x4<UINT8>
0x001709cc:	addl %esi, $0x4<UINT8>
0x001709cf:	jmp 0x0017097d
0x001709d1:	xorl %eax, %eax
0x001709d3:	popl %esi
0x001709d4:	popl %edi
0x001709d5:	popl %ebx
0x001709d6:	leave
0x001709d7:	ret $0x10<UINT16>

0x001708df:	incl %eax
0x001708e0:	jne 0x001708ea
0x001708ea:	addl %esi, $0x14<UINT8>
0x001708ed:	jmp 0x001708b4
GetModuleHandleA@kernel32.dll: API Node	
0x001708bb:	cmpl (%esi), %eax
0x001708bd:	je 0x001708e3
0x001708e3:	popl %esi
0x001708e4:	popl %edi
0x001708e5:	popl %ebx
0x001708e6:	leave
0x001708e7:	ret $0x8<UINT16>

0x00170261:	testl %eax, %eax
0x00170263:	je 0x001702e0
0x001702e0:	movl %edi, 0x8(%ebx)
0x001702e3:	movl %ebx, %esi
0x001702e5:	cmpl 0x48(%ebx), $0x1<UINT8>
0x001702e9:	jne 0x00170300
0x00170300:	movl %esi, %ebx
0x00170302:	nop
0x00170303:	nop
0x00170304:	nop
0x00170305:	nop
0x00170306:	nop
0x00170307:	nop
0x00170308:	nop
0x00170309:	nop
0x0017030a:	nop
0x0017030b:	nop
0x0017030c:	nop
0x0017030d:	nop
0x0017030e:	pushl %esi
0x0017030f:	call 0x00170af7
0x00170af7:	pushl %ebp
0x00170af8:	movl %ebp, %esp
0x00170afa:	addl %esp, $0xfffffffc<UINT8>
0x00170afd:	pushl %ebx
0x00170afe:	pushl %edi
0x00170aff:	pushl %esi
0x00170b00:	call 0x00170b05
0x00170b05:	popl %ebx
0x00170b06:	subl %ebx, $0x10001cc0<UINT32>
0x00170b0c:	movl %esi, 0x8(%ebp)
0x00170b0f:	movl %eax, 0x8(%esi)
0x00170b12:	addl %eax, 0x3c(%eax)
0x00170b15:	leal %edi, 0x80(%eax)
0x00170b1b:	movl %ecx, %edi
0x00170b1d:	shrl %ecx, $0xc<UINT8>
0x00170b20:	shll %ecx, $0xc<UINT8>
0x00170b23:	pushl %ecx
0x00170b24:	leal %eax, -4(%ebp)
0x00170b27:	pushl %eax
0x00170b28:	pushl $0x4<UINT8>
0x00170b2a:	pushl $0x1000<UINT32>
0x00170b2f:	pushl %ecx
0x00170b30:	call VirtualProtect@kernel32
VirtualProtect@kernel32: API Node	
0x00170b36:	movl %edx, 0x34(%esi)
0x00170b39:	movl (%edi), %edx
0x00170b3b:	popl %ecx
0x00170b3c:	leal %eax, -4(%ebp)
0x00170b3f:	pushl %eax
0x00170b40:	pushl -4(%ebp)
0x00170b43:	pushl $0x1000<UINT32>
0x00170b48:	pushl %ecx
0x00170b49:	call VirtualProtect@kernel32
0x00170b4f:	popl %esi
0x00170b50:	popl %edi
0x00170b51:	popl %ebx
0x00170b52:	leave
0x00170b53:	ret $0x4<UINT16>

0x00170314:	nop
0x00170315:	nop
0x00170316:	nop
0x00170317:	nop
0x00170318:	nop
0x00170319:	nop
0x0017031a:	pushl %edi
0x0017031b:	call 0x00170a48
0x00170a48:	pushl %ebp
0x00170a49:	movl %ebp, %esp
0x00170a4b:	addl %esp, $0xfffffffc<UINT8>
0x00170a4e:	pushl %ebx
0x00170a4f:	pushl %edi
0x00170a50:	pushl %esi
0x00170a51:	call 0x00170a56
0x00170a56:	popl %ebx
0x00170a57:	subl %ebx, $0x10001c11<UINT32>
0x00170a5d:	movl %eax, 0x8(%ebp)
0x00170a60:	addl %eax, 0x3c(%eax)
0x00170a63:	xorl %ecx, %ecx
0x00170a65:	movw %cx, 0x14(%eax)
0x00170a69:	leal %edi, 0x18(%ecx,%eax)
0x00170a6d:	addl %edi, $0x27<UINT8>
0x00170a70:	movl %ecx, %edi
0x00170a72:	shrl %ecx, $0xc<UINT8>
0x00170a75:	shll %ecx, $0xc<UINT8>
0x00170a78:	pushl %ecx
0x00170a79:	leal %eax, -4(%ebp)
0x00170a7c:	pushl %eax
0x00170a7d:	pushl $0x4<UINT8>
0x00170a7f:	pushl $0x1000<UINT32>
0x00170a84:	pushl %ecx
0x00170a85:	call VirtualProtect@kernel32
0x00170a8b:	movb %al, (%edi)
0x00170a8d:	testb %al, $0xffffff80<UINT8>
0x00170a8f:	je 4
0x00170a91:	subb %al, $0xffffff80<UINT8>
0x00170a93:	movb (%edi), %al
0x00170a95:	popl %ecx
0x00170a96:	leal %eax, -4(%ebp)
0x00170a99:	pushl %eax
0x00170a9a:	pushl -4(%ebp)
0x00170a9d:	pushl $0x1000<UINT32>
0x00170aa2:	pushl %ecx
0x00170aa3:	call VirtualProtect@kernel32
0x00170aa9:	popl %esi
0x00170aaa:	popl %edi
0x00170aab:	popl %ebx
0x00170aac:	leave
0x00170aad:	ret $0x4<UINT16>

0x00170320:	pushl $0x8000<UINT32>
0x00170325:	pushl $0x0<UINT8>
0x00170327:	pushl 0x10002f27(%ebp)
0x0017032d:	call VirtualFree@kernel32.dll
VirtualFree@kernel32.dll: API Node	
0x00170333:	movl %eax, 0xc(%esi)
0x00170336:	addl %eax, %edi
0x00170338:	popl %ebp
0x00170339:	popl %esi
0x0017033a:	popl %edi
0x0017033b:	popl %ebx
0x0017033c:	ret

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
