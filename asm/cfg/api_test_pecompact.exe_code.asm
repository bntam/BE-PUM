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
0x004048f6:	call 0x00270188
0x00270188:	pushl %ebx
0x00270189:	pushl %edi
0x0027018a:	pushl %esi
0x0027018b:	pushl %ebp
0x0027018c:	call 0x00270191
0x00270191:	popl %ebp
0x00270192:	subl %ebp, $0x1000134c<UINT32>
0x00270198:	leal %esi, 0x10001343(%ebp)
0x0027019e:	movl %eax, -4(%esi)
0x002701a1:	addl %eax, $0x4<UINT8>
0x002701a4:	subl %esi, %eax
0x002701a6:	cld
0x002701a7:	movl %ebx, %esi
0x002701a9:	movl %edx, 0x8(%esi)
0x002701ac:	movl %esi, 0x1c(%esi)
0x002701af:	addl %esi, %edx
0x002701b1:	leal %edi, 0x10002f2f(%ebp)
0x002701b7:	lodsl %eax, %ds:(%esi)
0x002701b8:	stosl %es:(%edi), %eax
0x002701b9:	lodsl %eax, %ds:(%esi)
0x002701ba:	stosl %es:(%edi), %eax
0x002701bb:	lodsl %eax, %ds:(%esi)
0x002701bc:	stosl %es:(%edi), %eax
0x002701bd:	lodsl %eax, %ds:(%esi)
0x002701be:	stosl %es:(%edi), %eax
0x002701bf:	nop
0x002701c0:	cmpl 0x48(%ebx), $0x1<UINT8>
0x002701c4:	je 21
0x002701c6:	movl %esi, 0x44(%ebx)
0x002701c9:	testl %esi, %esi
0x002701cb:	je 14
0x002701cd:	movl %ecx, $0x23<UINT32>
0x002701d2:	addl %esi, %edx
0x002701d4:	movl %edi, 0x40(%ebx)
0x002701d7:	addl %edi, %edx
0x002701d9:	rep movsb %es:(%edi), %ds:(%esi)
0x002701db:	movl %esi, %ebx
0x002701dd:	leal %edi, 0x10002f1b(%ebp)
0x002701e3:	addl (%edi), %ebp
0x002701e5:	addl 0x4(%edi), %ebp
0x002701e8:	addl 0x8(%edi), %ebp
0x002701eb:	leal %ecx, 0x10002eff(%ebp)
0x002701f1:	pushl %ecx
0x002701f2:	call 0x0027033d
0x0027033d:	pushl %ebp
0x0027033e:	movl %ebp, %esp
0x00270340:	addl %esp, $0xfffffffc<UINT8>
0x00270343:	pushl %ebx
0x00270344:	pushl %edi
0x00270345:	pushl %esi
0x00270346:	call 0x0027034b
0x0027034b:	popl %ebx
0x0027034c:	subl %ebx, $0x10001506<UINT32>
0x00270352:	movl %esi, 0x8(%ebp)
0x00270355:	movl %ecx, (%esi)
0x00270357:	addl %ecx, %ebx
0x00270359:	pushl %ecx
0x0027035a:	call LoadLibraryA@kernel32.dll
LoadLibraryA@kernel32.dll: API Node	
0x00270360:	movl -4(%ebp), %eax
0x00270363:	movl %edx, 0x4(%esi)
0x00270366:	movl %edi, 0x8(%esi)
0x00270369:	addl %edx, %ebx
0x0027036b:	addl %edi, %ebx
0x0027036d:	xorl %eax, %eax
0x0027036f:	addl %eax, (%edx)
0x00270371:	je 0x00270389
0x00270373:	pushl %edx
0x00270374:	movl %eax, (%edx)
0x00270376:	addl %eax, %ebx
0x00270378:	pushl %eax
0x00270379:	pushl -4(%ebp)
0x0027037c:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x00270382:	stosl %es:(%edi), %eax
0x00270383:	popl %edx
0x00270384:	addl %edx, $0x4<UINT8>
0x00270387:	jmp 0x0027036d
0x00270389:	addl %esi, $0xc<UINT8>
0x0027038c:	addl %eax, (%esi)
0x0027038e:	jne 0x00270355
0x00270390:	popl %esi
0x00270391:	popl %edi
0x00270392:	popl %ebx
0x00270393:	leave
0x00270394:	ret $0x4<UINT16>

0x002701f7:	nop
0x002701f8:	nop
0x002701f9:	nop
0x002701fa:	nop
0x002701fb:	nop
0x002701fc:	nop
0x002701fd:	nop
0x002701fe:	nop
0x002701ff:	movl %ecx, 0x2c(%esi)
0x00270202:	movl 0x10002f2b(%ebp), %ecx
0x00270208:	pushl $0x4<UINT8>
0x0027020a:	pushl $0x1000<UINT32>
0x0027020f:	pushl %ecx
0x00270210:	pushl $0x0<UINT8>
0x00270212:	call VirtualAlloc@kernel32.dll
0x00270218:	movl 0x10002f27(%ebp), %eax
0x0027021e:	pushl %esi
0x0027021f:	call 0x0027061a
0x0027061a:	pushl %ebp
0x0027061b:	movl %ebp, %esp
0x0027061d:	addl %esp, $0xffffffe8<UINT8>
0x00270620:	pushl %ebx
0x00270621:	pushl %edi
0x00270622:	pushl %esi
0x00270623:	call 0x00270628
0x00270628:	popl %ebx
0x00270629:	subl %ebx, $0x100017e3<UINT32>
0x0027062f:	movl %esi, 0x8(%ebp)
0x00270632:	xorl %eax, %eax
0x00270634:	xorl %ecx, %ecx
0x00270636:	addl %ecx, 0x3c(%esi)
0x00270639:	je 10
0x0027063b:	movl %edx, 0x8(%esi)
0x0027063e:	movl %edi, %esi
0x00270640:	addl %esi, $0x50<UINT8>
0x00270643:	jmp 0x0027064c
0x0027064c:	movl -4(%ebp), %eax
0x0027064f:	movzwl %eax, 0x2(%edi)
0x00270653:	movl -16(%ebp), %eax
0x00270656:	pushl %ecx
0x00270657:	pushl %edx
0x00270658:	pushl %esi
0x00270659:	movzwl %eax, 0x10(%esi)
0x0027065d:	testl %eax, $0x10<UINT32>
0x00270662:	je 0x0027073e
0x0027073e:	popl %esi
0x0027073f:	popl %edx
0x00270740:	popl %ecx
0x00270741:	addl %esi, $0x1c<UINT8>
0x00270744:	decl %ecx
0x00270745:	jne 0x00270656
0x00270668:	pushl %esi
0x00270669:	movl %edi, 0x10002f27(%ebx)
0x0027066f:	movl -20(%ebp), %edi
0x00270672:	movl %ecx, 0x8(%esi)
0x00270675:	movl %eax, 0x14(%esi)
0x00270678:	subl %ecx, %eax
0x0027067a:	movl %esi, (%esi)
0x0027067c:	addl %esi, %edx
0x0027067e:	movl %eax, %ecx
0x00270680:	sarl %ecx, $0x2<UINT8>
0x00270683:	rep movsl %es:(%edi), %ds:(%esi)
0x00270685:	addl %ecx, %eax
0x00270687:	andl %ecx, $0x3<UINT8>
0x0027068a:	rep movsb %es:(%edi), %ds:(%esi)
0x0027068c:	popl %esi
0x0027068d:	nop
0x0027068e:	nop
0x0027068f:	nop
0x00270690:	nop
0x00270691:	nop
0x00270692:	nop
0x00270693:	nop
0x00270694:	nop
0x00270695:	nop
0x00270696:	nop
0x00270697:	nop
0x00270698:	nop
0x00270699:	nop
0x0027069a:	nop
0x0027069b:	nop
0x0027069c:	nop
0x0027069d:	nop
0x0027069e:	nop
0x0027069f:	nop
0x002706a0:	nop
0x002706a1:	nop
0x002706a2:	nop
0x002706a3:	nop
0x002706a4:	nop
0x002706a5:	nop
0x002706a6:	nop
0x002706a7:	nop
0x002706a8:	nop
0x002706a9:	nop
0x002706aa:	nop
0x002706ab:	nop
0x002706ac:	nop
0x002706ad:	nop
0x002706ae:	nop
0x002706af:	nop
0x002706b0:	nop
0x002706b1:	nop
0x002706b2:	nop
0x002706b3:	movl %eax, 0x4(%esi)
0x002706b6:	addl %eax, %edx
0x002706b8:	movl -24(%ebp), %eax
0x002706bb:	movl %eax, -16(%ebp)
0x002706be:	decl %eax
0x002706bf:	movl -12(%ebp), %eax
0x002706c2:	pushl %edx
0x002706c3:	pushl %eax
0x002706c4:	pushl 0x8(%ebp)
0x002706c7:	call 0x00270a08
0x00270a08:	pushl %ebp
0x00270a09:	movl %ebp, %esp
0x00270a0b:	addl %esp, $0xfffffffc<UINT8>
0x00270a0e:	pushl %ebx
0x00270a0f:	pushl %edi
0x00270a10:	pushl %esi
0x00270a11:	movl %ebx, 0x8(%ebp)
0x00270a14:	movl %esi, %ebx
0x00270a16:	movl %ecx, 0x30(%ebx)
0x00270a19:	subl %esi, %ecx
0x00270a1b:	movl -4(%ebp), %esi
0x00270a1e:	xorl %ecx, %ecx
0x00270a20:	lodsl %eax, %ds:(%esi)
0x00270a21:	testl %eax, %eax
0x00270a23:	je 28
0x00270a25:	cmpl %ecx, 0xc(%ebp)
0x00270a28:	je 0x00270a2d
0x00270a2d:	nop
0x00270a2e:	nop
0x00270a2f:	nop
0x00270a30:	nop
0x00270a31:	nop
0x00270a32:	nop
0x00270a33:	nop
0x00270a34:	nop
0x00270a35:	nop
0x00270a36:	nop
0x00270a37:	nop
0x00270a38:	nop
0x00270a39:	nop
0x00270a3a:	nop
0x00270a3b:	nop
0x00270a3c:	nop
0x00270a3d:	nop
0x00270a3e:	addl %eax, -4(%ebp)
0x00270a41:	popl %esi
0x00270a42:	popl %edi
0x00270a43:	popl %ebx
0x00270a44:	leave
0x00270a45:	ret $0x8<UINT16>

0x002706cc:	leal %ecx, 0x10002f2f(%ebx)
0x002706d2:	pushl %ecx
0x002706d3:	pushl -24(%ebp)
0x002706d6:	pushl -20(%ebp)
0x002706d9:	call 0x00270008
0x00270008:	pusha
0x00270009:	movl %esi, 0x24(%esp)
0x0027000d:	movl %edi, 0x28(%esp)
0x00270011:	cld
0x00270012:	lodsl %eax, %ds:(%esi)
0x00270013:	xorl %ecx, %ecx
0x00270015:	testl %eax, %eax
0x00270017:	je 17
0x00270019:	xorl %edx, %edx
0x0027001b:	leal %ebx, (%eax,%edi)
0x0027001e:	movsb %es:(%edi), %ds:(%esi)
0x0027001f:	movb %cl, $0x3<UINT8>
0x00270021:	call 0x00270098
0x00270098:	addl %edx, %edx
0x0027009a:	jne 0x002700a2
0x0027009c:	xchgl %edx, %eax
0x0027009d:	lodsl %eax, %ds:(%esi)
0x0027009e:	xchgl %edx, %eax
0x0027009f:	addl %edx, %edx
0x002700a1:	incl %edx
0x002700a2:	ret

0x00270026:	jae 0x0027001e
0x00270028:	cmpl %edi, %ebx
0x0027002a:	jae 0x002700b5
0x00270030:	pushl %ebx
0x00270031:	pushl %ebp
0x00270032:	pushl %edi
0x00270033:	xorl %ebx, %ebx
0x00270035:	incl %ebx
0x00270036:	xorl %ebp, %ebp
0x00270038:	movl %eax, %ebx
0x0027003a:	leal %edi, (%ebp,%ebx)
0x0027003e:	movl %ebp, %ebx
0x00270040:	movl %ebx, %edi
0x00270042:	call 0x00270098
0x00270047:	jae 0x0027003a
0x00270049:	leal %ebx, (%ebp,%edi)
0x0027004d:	addl %eax, %edi
0x0027004f:	movl %ebp, %edi
0x00270051:	call 0x00270098
0x00270056:	jae 0x0027003a
0x00270058:	popl %edi
0x00270059:	popl %ebp
0x0027005a:	popl %ebx
0x0027005b:	subl %eax, %ecx
0x0027005d:	jae 0x00270068
0x00270068:	movb %cl, $0x6<UINT8>
0x0027006a:	call 0x00270098
0x0027006f:	adcl %eax, %eax
0x00270071:	decl %ecx
0x00270072:	jne 0x0027006a
0x00270074:	incl %eax
0x00270075:	call 0x002700a3
0x002700a3:	xorl %ecx, %ecx
0x002700a5:	incl %ecx
0x002700a6:	call 0x00270098
0x002700ab:	adcl %ecx, %ecx
0x002700ad:	call 0x00270098
0x002700b2:	jb 0x002700a6
0x002700b4:	ret

0x0027007a:	movl %ebp, %eax
0x0027007c:	cmpl %eax, $0x8001<UINT32>
0x00270081:	sbbl %ecx, $0xffffffff<UINT8>
0x00270084:	cmpl %eax, $0x781<UINT32>
0x00270089:	sbbl %ecx, $0xffffffff<UINT8>
0x0027008c:	pushl %esi
0x0027008d:	movl %esi, %edi
0x0027008f:	subl %esi, %eax
0x00270091:	rep movsb %es:(%edi), %ds:(%esi)
0x00270093:	popl %esi
0x00270094:	incl %ecx
0x00270095:	incl %ecx
0x00270096:	jmp 0x00270021
0x0027005f:	movl %eax, %ebp
0x00270061:	call 0x002700a3
0x00270066:	jmp 0x0027008c
0x002700b5:	subl %edi, 0x28(%esp)
0x002700b9:	movl 0x1c(%esp), %edi
0x002700bd:	popa
0x002700be:	ret $0xc<UINT16>

0x002706db:	movl %ecx, %eax
0x002706dd:	incl %eax
0x002706de:	je 116
0x002706e0:	xorl %eax, %eax
0x002706e2:	addl %eax, -12(%ebp)
0x002706e5:	je 0x002706f9
0x002706f9:	popl %edx
0x002706fa:	pushl %esi
0x002706fb:	movl %edi, 0x4(%esi)
0x002706fe:	addl %edi, %edx
0x00270700:	cmpl %edi, -24(%ebp)
0x00270703:	jne 4
0x00270705:	addl %edi, %ecx
0x00270707:	jmp 0x0027071a
0x0027071a:	movl %eax, %edi
0x0027071c:	addl %eax, $0xfff<UINT32>
0x00270721:	shrl %eax, $0xc<UINT8>
0x00270724:	shll %eax, $0xc<UINT8>
0x00270727:	subl %eax, %edi
0x00270729:	movl %ecx, %eax
0x0027072b:	xorl %eax, %eax
0x0027072d:	pushl %edx
0x0027072e:	movl %edx, %ecx
0x00270730:	sarl %ecx, $0x2<UINT8>
0x00270733:	rep stosl %es:(%edi), %eax
0x00270735:	addl %ecx, %edx
0x00270737:	andl %ecx, $0x3<UINT8>
0x0027073a:	rep stosb %es:(%edi), %al
0x0027073c:	popl %edx
0x0027073d:	popl %esi
0x0027074b:	xorl %eax, %eax
0x0027074d:	popl %esi
0x0027074e:	popl %edi
0x0027074f:	popl %ebx
0x00270750:	leave
0x00270751:	ret $0x4<UINT16>

0x00270224:	leal %ecx, 0x10002dbd(%ebp)
0x0027022a:	testl %eax, %eax
0x0027022c:	jne 148
0x00270232:	pushl %esi
0x00270233:	call 0x00270578
0x00270578:	pushl %ebp
0x00270579:	movl %ebp, %esp
0x0027057b:	addl %esp, $0xffffffe8<UINT8>
0x0027057e:	pushl %ebx
0x0027057f:	pushl %edi
0x00270580:	pushl %esi
0x00270581:	call 0x00270586
0x00270586:	popl %ebx
0x00270587:	subl %ebx, $0x10001741<UINT32>
0x0027058d:	movl %esi, 0x8(%ebp)
0x00270590:	xorl %eax, %eax
0x00270592:	xorl %ecx, %ecx
0x00270594:	addl %ecx, 0x3c(%esi)
0x00270597:	je 10
0x00270599:	movl %edx, 0x8(%esi)
0x0027059c:	movl %edi, %esi
0x0027059e:	addl %esi, $0x50<UINT8>
0x002705a1:	jmp 0x002705aa
0x002705aa:	movl %eax, 0x10002f27(%ebx)
0x002705b0:	movl -4(%ebp), %eax
0x002705b3:	movl %ebx, %esi
0x002705b5:	movzwl %eax, 0x10(%ebx)
0x002705b9:	testl %eax, $0x2<UINT32>
0x002705be:	je 0x0027060d
0x002705c0:	pushl %ecx
0x002705c1:	movl %esi, 0x4(%ebx)
0x002705c4:	movl %edi, -4(%ebp)
0x002705c7:	movl %ecx, 0x8(%ebx)
0x002705ca:	addl %esi, %edx
0x002705cc:	movl %eax, %ecx
0x002705ce:	sarl %ecx, $0x2<UINT8>
0x002705d1:	rep movsl %es:(%edi), %ds:(%esi)
0x002705d3:	addl %ecx, %eax
0x002705d5:	andl %ecx, $0x3<UINT8>
0x002705d8:	rep movsb %es:(%edi), %ds:(%esi)
0x002705da:	movl %edi, 0x4(%ebx)
0x002705dd:	movl %ecx, 0x8(%ebx)
0x002705e0:	addl %edi, %edx
0x002705e2:	xorl %eax, %eax
0x002705e4:	pushl %edx
0x002705e5:	movl %edx, %ecx
0x002705e7:	sarl %ecx, $0x2<UINT8>
0x002705ea:	rep stosl %es:(%edi), %eax
0x002705ec:	addl %ecx, %edx
0x002705ee:	andl %ecx, $0x3<UINT8>
0x002705f1:	rep stosb %es:(%edi), %al
0x002705f3:	popl %edx
0x002705f4:	movl %esi, -4(%ebp)
0x002705f7:	movl %edi, (%ebx)
0x002705f9:	addl %edi, %edx
0x002705fb:	movl %ecx, 0x8(%ebx)
0x002705fe:	movl %eax, %ecx
0x00270600:	sarl %ecx, $0x2<UINT8>
0x00270603:	rep movsl %es:(%edi), %ds:(%esi)
0x00270605:	addl %ecx, %eax
0x00270607:	andl %ecx, $0x3<UINT8>
0x0027060a:	rep movsb %es:(%edi), %ds:(%esi)
0x0027060c:	popl %ecx
0x0027060d:	addl %ebx, $0x1c<UINT8>
0x00270610:	decl %ecx
0x00270611:	jne 0x002705b5
0x00270613:	popl %esi
0x00270614:	popl %edi
0x00270615:	popl %ebx
0x00270616:	leave
0x00270617:	ret $0x4<UINT16>

0x00270238:	pushl %esi
0x00270239:	call 0x00270493
0x00270493:	pushl %ebp
0x00270494:	movl %ebp, %esp
0x00270496:	addl %esp, $0xffffffe8<UINT8>
0x00270499:	pushl %ebx
0x0027049a:	pushl %edi
0x0027049b:	pushl %esi
0x0027049c:	call 0x002704a1
0x002704a1:	popl %ebx
0x002704a2:	subl %ebx, $0x1000165c<UINT32>
0x002704a8:	movl %esi, 0x8(%ebp)
0x002704ab:	xorl %eax, %eax
0x002704ad:	xorl %ecx, %ecx
0x002704af:	addl %ecx, 0x3c(%esi)
0x002704b2:	je 10
0x002704b4:	movl %edx, 0x8(%esi)
0x002704b7:	movl %edi, %esi
0x002704b9:	addl %esi, $0x50<UINT8>
0x002704bc:	jmp 0x002704c5
0x002704c5:	movl -8(%ebp), %edx
0x002704c8:	movzwl %eax, 0x10(%esi)
0x002704cc:	testl %eax, $0x200<UINT32>
0x002704d1:	jne 125
0x002704d3:	testl %eax, $0x8<UINT32>
0x002704d8:	je 0x00270567
0x00270567:	addl %esi, $0x1c<UINT8>
0x0027056a:	decl %ecx
0x0027056b:	jne 0x002704c8
0x002704de:	pushl %ecx
0x002704df:	pushl %esi
0x002704e0:	movl %edi, 0x8(%esi)
0x002704e3:	xorl %ecx, %ecx
0x002704e5:	movl -4(%ebp), %ecx
0x002704e8:	movzwl %ebx, 0x12(%esi)
0x002704ec:	movl %esi, (%esi)
0x002704ee:	addl %esi, -8(%ebp)
0x002704f1:	cmpl %ecx, %edi
0x002704f3:	jnl 0x00270529
0x002704f5:	movl %eax, (%esi)
0x002704f7:	incl %esi
0x002704f8:	movzbl %edx, %al
0x002704fb:	addl -4(%ebp), %edx
0x002704fe:	subb %al, $0xffffffe8<UINT8>
0x00270500:	movl %edx, %ebx
0x00270502:	je 0x0027050a
0x00270504:	decb %al
0x00270506:	movb %dl, %bh
0x00270508:	jne 0x00270526
0x00270526:	incl %ecx
0x00270527:	jmp 0x002704f1
0x0027050a:	movl %eax, (%esi)
0x0027050c:	cmpb %al, %dl
0x0027050e:	jne 13
0x00270510:	shrw %ax, $0x8<UINT8>
0x00270514:	roll %eax, $0x10<UINT8>
0x00270517:	xchgb %ah, %al
0x00270519:	subl %eax, %ecx
0x0027051b:	movl (%esi), %eax
0x0027051d:	addl -4(%ebp), %eax
0x00270520:	addl %esi, $0x4<UINT8>
0x00270523:	addl %ecx, $0x4<UINT8>
0x00270529:	popl %esi
0x0027052a:	popl %ecx
0x0027052b:	xorl %eax, %eax
0x0027052d:	addl %eax, 0x14(%esi)
0x00270530:	je 53
0x00270532:	cmpl %eax, -4(%ebp)
0x00270535:	je 0x00270567
0x00270571:	popl %esi
0x00270572:	popl %edi
0x00270573:	popl %ebx
0x00270574:	leave
0x00270575:	ret $0x4<UINT16>

0x0027023e:	nop
0x0027023f:	nop
0x00270240:	nop
0x00270241:	nop
0x00270242:	nop
0x00270243:	nop
0x00270244:	nop
0x00270245:	nop
0x00270246:	nop
0x00270247:	nop
0x00270248:	nop
0x00270249:	nop
0x0027024a:	nop
0x0027024b:	nop
0x0027024c:	movl %ecx, 0x34(%esi)
0x0027024f:	testl %ecx, %ecx
0x00270251:	je 137
0x00270257:	addl %ecx, 0x8(%esi)
0x0027025a:	pushl %ecx
0x0027025b:	pushl %esi
0x0027025c:	call 0x002708a8
0x002708a8:	pushl %ebp
0x002708a9:	movl %ebp, %esp
0x002708ab:	pushl %ebx
0x002708ac:	pushl %edi
0x002708ad:	pushl %esi
0x002708ae:	movl %esi, 0xc(%ebp)
0x002708b1:	movl %ebx, 0x8(%ebp)
0x002708b4:	xorl %eax, %eax
0x002708b6:	cmpl 0x10(%esi), %eax
0x002708b9:	jne 0x002708bf
0x002708bf:	addl %eax, (%esi)
0x002708c1:	je 3
0x002708c3:	addl %eax, 0x8(%ebx)
0x002708c6:	movl %ecx, 0xc(%esi)
0x002708c9:	addl %ecx, 0x8(%ebx)
0x002708cc:	movl %edi, 0x10(%esi)
0x002708cf:	testl %edi, %edi
0x002708d1:	je 3
0x002708d3:	addl %edi, 0x8(%ebx)
0x002708d6:	pushl %eax
0x002708d7:	pushl %edi
0x002708d8:	pushl %ecx
0x002708d9:	pushl %ebx
0x002708da:	call 0x002708ef
0x002708ef:	pushl %ebp
0x002708f0:	movl %ebp, %esp
0x002708f2:	addl %esp, $0xffffffe8<UINT8>
0x002708f5:	pushl %ebx
0x002708f6:	pushl %edi
0x002708f7:	pushl %esi
0x002708f8:	call 0x002708fd
0x002708fd:	popl %ebx
0x002708fe:	subl %ebx, $0x10001ab8<UINT32>
0x00270904:	movl %eax, 0xc(%ebp)
0x00270907:	movl 0x10002d9c(%ebx), %eax
0x0027090d:	xorl %eax, %eax
0x0027090f:	movl 0x10002da0(%ebx), %eax
0x00270915:	xorl %esi, %esi
0x00270917:	incl %esi
0x00270918:	incl %esi
0x00270919:	movl %edx, 0x10002eef(%ebx)
0x0027091f:	pushl 0xc(%ebp)
0x00270922:	call GetModuleHandleA@kernel32.dll
GetModuleHandleA@kernel32: API Node	
0x00270924:	movl -4(%ebp), %eax
0x00270927:	movl %edx, 0x10002f1b(%ebx)
0x0027092d:	testl %eax, %eax
0x0027092f:	jne 0x0027093e
0x0027093e:	nop
0x0027093f:	nop
0x00270940:	nop
0x00270941:	nop
0x00270942:	nop
0x00270943:	nop
0x00270944:	nop
0x00270945:	nop
0x00270946:	nop
0x00270947:	nop
0x00270948:	nop
0x00270949:	nop
0x0027094a:	nop
0x0027094b:	nop
0x0027094c:	nop
0x0027094d:	nop
0x0027094e:	nop
0x0027094f:	nop
0x00270950:	nop
0x00270951:	nop
0x00270952:	nop
0x00270953:	nop
0x00270954:	nop
0x00270955:	nop
0x00270956:	nop
0x00270957:	nop
0x00270958:	nop
0x00270959:	nop
0x0027095a:	nop
0x0027095b:	nop
0x0027095c:	nop
0x0027095d:	nop
0x0027095e:	nop
0x0027095f:	nop
0x00270960:	nop
0x00270961:	nop
0x00270962:	nop
0x00270963:	nop
0x00270964:	nop
0x00270965:	nop
0x00270966:	nop
0x00270967:	nop
0x00270968:	movl %esi, 0x10(%ebp)
0x0027096b:	movl %edi, 0x8(%ebp)
0x0027096e:	movl %edx, 0x14(%ebp)
0x00270971:	testl %edx, %edx
0x00270973:	jne 0x00270977
0x00270977:	testl %esi, %esi
0x00270979:	jne 0x0027097d
0x0027097d:	movl 0x10002da0(%ebx), $0x0<UINT32>
0x00270987:	movl %eax, (%edx)
0x00270989:	testl %eax, %eax
0x0027098b:	je 0x002709d1
0x0027098d:	pushl %edx
0x0027098e:	movl 0x10002da0(%ebx), %eax
0x00270994:	testl %eax, $0x80000000<UINT32>
0x00270999:	je 0x002709a4
0x002709a4:	movl %ecx, 0x8(%ebp)
0x002709a7:	addl %eax, 0x8(%ecx)
0x002709aa:	xorl %ecx, %ecx
0x002709ac:	movw %cx, (%eax)
0x002709af:	pushl %ecx
0x002709b0:	incl %eax
0x002709b1:	incl %eax
0x002709b2:	pushl %eax
0x002709b3:	pushl -4(%ebp)
0x002709b6:	call 0x00270ad2
0x00270ad2:	pushl %ebp
0x00270ad3:	movl %ebp, %esp
0x00270ad5:	pushl %ebx
0x00270ad6:	pushl %edi
0x00270ad7:	pushl %esi
0x00270ad8:	call 0x00270add
0x00270add:	popl %ebx
0x00270ade:	subl %ebx, $0x10001c98<UINT32>
0x00270ae4:	pushl 0xc(%ebp)
0x00270ae7:	pushl 0x8(%ebp)
0x00270aea:	call GetProcAddress@kernel32.dll
0x00270af0:	popl %esi
0x00270af1:	popl %edi
0x00270af2:	popl %ebx
0x00270af3:	leave
0x00270af4:	ret $0xc<UINT16>

0x002709bc:	popl %edx
0x002709bd:	testl %eax, %eax
0x002709bf:	je -145
0x002709c5:	movl (%esi), %eax
0x002709c7:	movl (%edx), %eax
0x002709c9:	addl %edx, $0x4<UINT8>
0x002709cc:	addl %esi, $0x4<UINT8>
0x002709cf:	jmp 0x0027097d
0x002709d1:	xorl %eax, %eax
0x002709d3:	popl %esi
0x002709d4:	popl %edi
0x002709d5:	popl %ebx
0x002709d6:	leave
0x002709d7:	ret $0x10<UINT16>

0x002708df:	incl %eax
0x002708e0:	jne 0x002708ea
0x002708ea:	addl %esi, $0x14<UINT8>
0x002708ed:	jmp 0x002708b4
GetModuleHandleA@kernel32.dll: API Node	
0x002708bb:	cmpl (%esi), %eax
0x002708bd:	je 0x002708e3
0x002708e3:	popl %esi
0x002708e4:	popl %edi
0x002708e5:	popl %ebx
0x002708e6:	leave
0x002708e7:	ret $0x8<UINT16>

0x00270261:	testl %eax, %eax
0x00270263:	je 0x002702e0
0x002702e0:	movl %edi, 0x8(%ebx)
0x002702e3:	movl %ebx, %esi
0x002702e5:	cmpl 0x48(%ebx), $0x1<UINT8>
0x002702e9:	jne 0x00270300
0x00270300:	movl %esi, %ebx
0x00270302:	nop
0x00270303:	nop
0x00270304:	nop
0x00270305:	nop
0x00270306:	nop
0x00270307:	nop
0x00270308:	nop
0x00270309:	nop
0x0027030a:	nop
0x0027030b:	nop
0x0027030c:	nop
0x0027030d:	nop
0x0027030e:	pushl %esi
0x0027030f:	call 0x00270af7
0x00270af7:	pushl %ebp
0x00270af8:	movl %ebp, %esp
0x00270afa:	addl %esp, $0xfffffffc<UINT8>
0x00270afd:	pushl %ebx
0x00270afe:	pushl %edi
0x00270aff:	pushl %esi
0x00270b00:	call 0x00270b05
0x00270b05:	popl %ebx
0x00270b06:	subl %ebx, $0x10001cc0<UINT32>
0x00270b0c:	movl %esi, 0x8(%ebp)
0x00270b0f:	movl %eax, 0x8(%esi)
0x00270b12:	addl %eax, 0x3c(%eax)
0x00270b15:	leal %edi, 0x80(%eax)
0x00270b1b:	movl %ecx, %edi
0x00270b1d:	shrl %ecx, $0xc<UINT8>
0x00270b20:	shll %ecx, $0xc<UINT8>
0x00270b23:	pushl %ecx
0x00270b24:	leal %eax, -4(%ebp)
0x00270b27:	pushl %eax
0x00270b28:	pushl $0x4<UINT8>
0x00270b2a:	pushl $0x1000<UINT32>
0x00270b2f:	pushl %ecx
0x00270b30:	call VirtualProtect@kernel32
VirtualProtect@kernel32: API Node	
0x00270b36:	movl %edx, 0x34(%esi)
0x00270b39:	movl (%edi), %edx
0x00270b3b:	popl %ecx
0x00270b3c:	leal %eax, -4(%ebp)
0x00270b3f:	pushl %eax
0x00270b40:	pushl -4(%ebp)
0x00270b43:	pushl $0x1000<UINT32>
0x00270b48:	pushl %ecx
0x00270b49:	call VirtualProtect@kernel32
0x00270b4f:	popl %esi
0x00270b50:	popl %edi
0x00270b51:	popl %ebx
0x00270b52:	leave
0x00270b53:	ret $0x4<UINT16>

0x00270314:	nop
0x00270315:	nop
0x00270316:	nop
0x00270317:	nop
0x00270318:	nop
0x00270319:	nop
0x0027031a:	pushl %edi
0x0027031b:	call 0x00270a48
0x00270a48:	pushl %ebp
0x00270a49:	movl %ebp, %esp
0x00270a4b:	addl %esp, $0xfffffffc<UINT8>
0x00270a4e:	pushl %ebx
0x00270a4f:	pushl %edi
0x00270a50:	pushl %esi
0x00270a51:	call 0x00270a56
0x00270a56:	popl %ebx
0x00270a57:	subl %ebx, $0x10001c11<UINT32>
0x00270a5d:	movl %eax, 0x8(%ebp)
0x00270a60:	addl %eax, 0x3c(%eax)
0x00270a63:	xorl %ecx, %ecx
0x00270a65:	movw %cx, 0x14(%eax)
0x00270a69:	leal %edi, 0x18(%ecx,%eax)
0x00270a6d:	addl %edi, $0x27<UINT8>
0x00270a70:	movl %ecx, %edi
0x00270a72:	shrl %ecx, $0xc<UINT8>
0x00270a75:	shll %ecx, $0xc<UINT8>
0x00270a78:	pushl %ecx
0x00270a79:	leal %eax, -4(%ebp)
0x00270a7c:	pushl %eax
0x00270a7d:	pushl $0x4<UINT8>
0x00270a7f:	pushl $0x1000<UINT32>
0x00270a84:	pushl %ecx
0x00270a85:	call VirtualProtect@kernel32
0x00270a8b:	movb %al, (%edi)
0x00270a8d:	testb %al, $0xffffff80<UINT8>
0x00270a8f:	je 4
0x00270a91:	subb %al, $0xffffff80<UINT8>
0x00270a93:	movb (%edi), %al
0x00270a95:	popl %ecx
0x00270a96:	leal %eax, -4(%ebp)
0x00270a99:	pushl %eax
0x00270a9a:	pushl -4(%ebp)
0x00270a9d:	pushl $0x1000<UINT32>
0x00270aa2:	pushl %ecx
0x00270aa3:	call VirtualProtect@kernel32
0x00270aa9:	popl %esi
0x00270aaa:	popl %edi
0x00270aab:	popl %ebx
0x00270aac:	leave
0x00270aad:	ret $0x4<UINT16>

0x00270320:	pushl $0x8000<UINT32>
0x00270325:	pushl $0x0<UINT8>
0x00270327:	pushl 0x10002f27(%ebp)
0x0027032d:	call VirtualFree@kernel32.dll
VirtualFree@kernel32.dll: API Node	
0x00270333:	movl %eax, 0xc(%esi)
0x00270336:	addl %eax, %edi
0x00270338:	popl %ebp
0x00270339:	popl %esi
0x0027033a:	popl %edi
0x0027033b:	popl %ebx
0x0027033c:	ret

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
