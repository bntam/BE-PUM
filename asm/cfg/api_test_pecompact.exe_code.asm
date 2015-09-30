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
0x004048f6:	call 0x00310188
0x00310188:	pushl %ebx
0x00310189:	pushl %edi
0x0031018a:	pushl %esi
0x0031018b:	pushl %ebp
0x0031018c:	call 0x00310191
0x00310191:	popl %ebp
0x00310192:	subl %ebp, $0x1000134c<UINT32>
0x00310198:	leal %esi, 0x10001343(%ebp)
0x0031019e:	movl %eax, -4(%esi)
0x003101a1:	addl %eax, $0x4<UINT8>
0x003101a4:	subl %esi, %eax
0x003101a6:	cld
0x003101a7:	movl %ebx, %esi
0x003101a9:	movl %edx, 0x8(%esi)
0x003101ac:	movl %esi, 0x1c(%esi)
0x003101af:	addl %esi, %edx
0x003101b1:	leal %edi, 0x10002f2f(%ebp)
0x003101b7:	lodsl %eax, %ds:(%esi)
0x003101b8:	stosl %es:(%edi), %eax
0x003101b9:	lodsl %eax, %ds:(%esi)
0x003101ba:	stosl %es:(%edi), %eax
0x003101bb:	lodsl %eax, %ds:(%esi)
0x003101bc:	stosl %es:(%edi), %eax
0x003101bd:	lodsl %eax, %ds:(%esi)
0x003101be:	stosl %es:(%edi), %eax
0x003101bf:	nop
0x003101c0:	cmpl 0x48(%ebx), $0x1<UINT8>
0x003101c4:	je 21
0x003101c6:	movl %esi, 0x44(%ebx)
0x003101c9:	testl %esi, %esi
0x003101cb:	je 14
0x003101cd:	movl %ecx, $0x23<UINT32>
0x003101d2:	addl %esi, %edx
0x003101d4:	movl %edi, 0x40(%ebx)
0x003101d7:	addl %edi, %edx
0x003101d9:	rep movsb %es:(%edi), %ds:(%esi)
0x003101db:	movl %esi, %ebx
0x003101dd:	leal %edi, 0x10002f1b(%ebp)
0x003101e3:	addl (%edi), %ebp
0x003101e5:	addl 0x4(%edi), %ebp
0x003101e8:	addl 0x8(%edi), %ebp
0x003101eb:	leal %ecx, 0x10002eff(%ebp)
0x003101f1:	pushl %ecx
0x003101f2:	call 0x0031033d
0x0031033d:	pushl %ebp
0x0031033e:	movl %ebp, %esp
0x00310340:	addl %esp, $0xfffffffc<UINT8>
0x00310343:	pushl %ebx
0x00310344:	pushl %edi
0x00310345:	pushl %esi
0x00310346:	call 0x0031034b
0x0031034b:	popl %ebx
0x0031034c:	subl %ebx, $0x10001506<UINT32>
0x00310352:	movl %esi, 0x8(%ebp)
0x00310355:	movl %ecx, (%esi)
0x00310357:	addl %ecx, %ebx
0x00310359:	pushl %ecx
0x0031035a:	call LoadLibraryA@kernel32.dll
LoadLibraryA@kernel32.dll: API Node	
0x00310360:	movl -4(%ebp), %eax
0x00310363:	movl %edx, 0x4(%esi)
0x00310366:	movl %edi, 0x8(%esi)
0x00310369:	addl %edx, %ebx
0x0031036b:	addl %edi, %ebx
0x0031036d:	xorl %eax, %eax
0x0031036f:	addl %eax, (%edx)
0x00310371:	je 0x00310389
0x00310373:	pushl %edx
0x00310374:	movl %eax, (%edx)
0x00310376:	addl %eax, %ebx
0x00310378:	pushl %eax
0x00310379:	pushl -4(%ebp)
0x0031037c:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x00310382:	stosl %es:(%edi), %eax
0x00310383:	popl %edx
0x00310384:	addl %edx, $0x4<UINT8>
0x00310387:	jmp 0x0031036d
0x00310389:	addl %esi, $0xc<UINT8>
0x0031038c:	addl %eax, (%esi)
0x0031038e:	jne 0x00310355
0x00310390:	popl %esi
0x00310391:	popl %edi
0x00310392:	popl %ebx
0x00310393:	leave
0x00310394:	ret $0x4<UINT16>

0x003101f7:	nop
0x003101f8:	nop
0x003101f9:	nop
0x003101fa:	nop
0x003101fb:	nop
0x003101fc:	nop
0x003101fd:	nop
0x003101fe:	nop
0x003101ff:	movl %ecx, 0x2c(%esi)
0x00310202:	movl 0x10002f2b(%ebp), %ecx
0x00310208:	pushl $0x4<UINT8>
0x0031020a:	pushl $0x1000<UINT32>
0x0031020f:	pushl %ecx
0x00310210:	pushl $0x0<UINT8>
0x00310212:	call VirtualAlloc@kernel32.dll
0x00310218:	movl 0x10002f27(%ebp), %eax
0x0031021e:	pushl %esi
0x0031021f:	call 0x0031061a
0x0031061a:	pushl %ebp
0x0031061b:	movl %ebp, %esp
0x0031061d:	addl %esp, $0xffffffe8<UINT8>
0x00310620:	pushl %ebx
0x00310621:	pushl %edi
0x00310622:	pushl %esi
0x00310623:	call 0x00310628
0x00310628:	popl %ebx
0x00310629:	subl %ebx, $0x100017e3<UINT32>
0x0031062f:	movl %esi, 0x8(%ebp)
0x00310632:	xorl %eax, %eax
0x00310634:	xorl %ecx, %ecx
0x00310636:	addl %ecx, 0x3c(%esi)
0x00310639:	je 10
0x0031063b:	movl %edx, 0x8(%esi)
0x0031063e:	movl %edi, %esi
0x00310640:	addl %esi, $0x50<UINT8>
0x00310643:	jmp 0x0031064c
0x0031064c:	movl -4(%ebp), %eax
0x0031064f:	movzwl %eax, 0x2(%edi)
0x00310653:	movl -16(%ebp), %eax
0x00310656:	pushl %ecx
0x00310657:	pushl %edx
0x00310658:	pushl %esi
0x00310659:	movzwl %eax, 0x10(%esi)
0x0031065d:	testl %eax, $0x10<UINT32>
0x00310662:	je 0x0031073e
0x0031073e:	popl %esi
0x0031073f:	popl %edx
0x00310740:	popl %ecx
0x00310741:	addl %esi, $0x1c<UINT8>
0x00310744:	decl %ecx
0x00310745:	jne 0x00310656
0x00310668:	pushl %esi
0x00310669:	movl %edi, 0x10002f27(%ebx)
0x0031066f:	movl -20(%ebp), %edi
0x00310672:	movl %ecx, 0x8(%esi)
0x00310675:	movl %eax, 0x14(%esi)
0x00310678:	subl %ecx, %eax
0x0031067a:	movl %esi, (%esi)
0x0031067c:	addl %esi, %edx
0x0031067e:	movl %eax, %ecx
0x00310680:	sarl %ecx, $0x2<UINT8>
0x00310683:	rep movsl %es:(%edi), %ds:(%esi)
0x00310685:	addl %ecx, %eax
0x00310687:	andl %ecx, $0x3<UINT8>
0x0031068a:	rep movsb %es:(%edi), %ds:(%esi)
0x0031068c:	popl %esi
0x0031068d:	nop
0x0031068e:	nop
0x0031068f:	nop
0x00310690:	nop
0x00310691:	nop
0x00310692:	nop
0x00310693:	nop
0x00310694:	nop
0x00310695:	nop
0x00310696:	nop
0x00310697:	nop
0x00310698:	nop
0x00310699:	nop
0x0031069a:	nop
0x0031069b:	nop
0x0031069c:	nop
0x0031069d:	nop
0x0031069e:	nop
0x0031069f:	nop
0x003106a0:	nop
0x003106a1:	nop
0x003106a2:	nop
0x003106a3:	nop
0x003106a4:	nop
0x003106a5:	nop
0x003106a6:	nop
0x003106a7:	nop
0x003106a8:	nop
0x003106a9:	nop
0x003106aa:	nop
0x003106ab:	nop
0x003106ac:	nop
0x003106ad:	nop
0x003106ae:	nop
0x003106af:	nop
0x003106b0:	nop
0x003106b1:	nop
0x003106b2:	nop
0x003106b3:	movl %eax, 0x4(%esi)
0x003106b6:	addl %eax, %edx
0x003106b8:	movl -24(%ebp), %eax
0x003106bb:	movl %eax, -16(%ebp)
0x003106be:	decl %eax
0x003106bf:	movl -12(%ebp), %eax
0x003106c2:	pushl %edx
0x003106c3:	pushl %eax
0x003106c4:	pushl 0x8(%ebp)
0x003106c7:	call 0x00310a08
0x00310a08:	pushl %ebp
0x00310a09:	movl %ebp, %esp
0x00310a0b:	addl %esp, $0xfffffffc<UINT8>
0x00310a0e:	pushl %ebx
0x00310a0f:	pushl %edi
0x00310a10:	pushl %esi
0x00310a11:	movl %ebx, 0x8(%ebp)
0x00310a14:	movl %esi, %ebx
0x00310a16:	movl %ecx, 0x30(%ebx)
0x00310a19:	subl %esi, %ecx
0x00310a1b:	movl -4(%ebp), %esi
0x00310a1e:	xorl %ecx, %ecx
0x00310a20:	lodsl %eax, %ds:(%esi)
0x00310a21:	testl %eax, %eax
0x00310a23:	je 28
0x00310a25:	cmpl %ecx, 0xc(%ebp)
0x00310a28:	je 0x00310a2d
0x00310a2d:	nop
0x00310a2e:	nop
0x00310a2f:	nop
0x00310a30:	nop
0x00310a31:	nop
0x00310a32:	nop
0x00310a33:	nop
0x00310a34:	nop
0x00310a35:	nop
0x00310a36:	nop
0x00310a37:	nop
0x00310a38:	nop
0x00310a39:	nop
0x00310a3a:	nop
0x00310a3b:	nop
0x00310a3c:	nop
0x00310a3d:	nop
0x00310a3e:	addl %eax, -4(%ebp)
0x00310a41:	popl %esi
0x00310a42:	popl %edi
0x00310a43:	popl %ebx
0x00310a44:	leave
0x00310a45:	ret $0x8<UINT16>

0x003106cc:	leal %ecx, 0x10002f2f(%ebx)
0x003106d2:	pushl %ecx
0x003106d3:	pushl -24(%ebp)
0x003106d6:	pushl -20(%ebp)
0x003106d9:	call 0x00310008
0x00310008:	pusha
0x00310009:	movl %esi, 0x24(%esp)
0x0031000d:	movl %edi, 0x28(%esp)
0x00310011:	cld
0x00310012:	lodsl %eax, %ds:(%esi)
0x00310013:	xorl %ecx, %ecx
0x00310015:	testl %eax, %eax
0x00310017:	je 17
0x00310019:	xorl %edx, %edx
0x0031001b:	leal %ebx, (%eax,%edi)
0x0031001e:	movsb %es:(%edi), %ds:(%esi)
0x0031001f:	movb %cl, $0x3<UINT8>
0x00310021:	call 0x00310098
0x00310098:	addl %edx, %edx
0x0031009a:	jne 0x003100a2
0x0031009c:	xchgl %edx, %eax
0x0031009d:	lodsl %eax, %ds:(%esi)
0x0031009e:	xchgl %edx, %eax
0x0031009f:	addl %edx, %edx
0x003100a1:	incl %edx
0x003100a2:	ret

0x00310026:	jae 0x0031001e
0x00310028:	cmpl %edi, %ebx
0x0031002a:	jae 0x003100b5
0x00310030:	pushl %ebx
0x00310031:	pushl %ebp
0x00310032:	pushl %edi
0x00310033:	xorl %ebx, %ebx
0x00310035:	incl %ebx
0x00310036:	xorl %ebp, %ebp
0x00310038:	movl %eax, %ebx
0x0031003a:	leal %edi, (%ebp,%ebx)
0x0031003e:	movl %ebp, %ebx
0x00310040:	movl %ebx, %edi
0x00310042:	call 0x00310098
0x00310047:	jae 0x0031003a
0x00310049:	leal %ebx, (%ebp,%edi)
0x0031004d:	addl %eax, %edi
0x0031004f:	movl %ebp, %edi
0x00310051:	call 0x00310098
0x00310056:	jae 0x0031003a
0x00310058:	popl %edi
0x00310059:	popl %ebp
0x0031005a:	popl %ebx
0x0031005b:	subl %eax, %ecx
0x0031005d:	jae 0x00310068
0x00310068:	movb %cl, $0x6<UINT8>
0x0031006a:	call 0x00310098
0x0031006f:	adcl %eax, %eax
0x00310071:	decl %ecx
0x00310072:	jne 0x0031006a
0x00310074:	incl %eax
0x00310075:	call 0x003100a3
0x003100a3:	xorl %ecx, %ecx
0x003100a5:	incl %ecx
0x003100a6:	call 0x00310098
0x003100ab:	adcl %ecx, %ecx
0x003100ad:	call 0x00310098
0x003100b2:	jb 0x003100a6
0x003100b4:	ret

0x0031007a:	movl %ebp, %eax
0x0031007c:	cmpl %eax, $0x8001<UINT32>
0x00310081:	sbbl %ecx, $0xffffffff<UINT8>
0x00310084:	cmpl %eax, $0x781<UINT32>
0x00310089:	sbbl %ecx, $0xffffffff<UINT8>
0x0031008c:	pushl %esi
0x0031008d:	movl %esi, %edi
0x0031008f:	subl %esi, %eax
0x00310091:	rep movsb %es:(%edi), %ds:(%esi)
0x00310093:	popl %esi
0x00310094:	incl %ecx
0x00310095:	incl %ecx
0x00310096:	jmp 0x00310021
0x0031005f:	movl %eax, %ebp
0x00310061:	call 0x003100a3
0x00310066:	jmp 0x0031008c
0x003100b5:	subl %edi, 0x28(%esp)
0x003100b9:	movl 0x1c(%esp), %edi
0x003100bd:	popa
0x003100be:	ret $0xc<UINT16>

0x003106db:	movl %ecx, %eax
0x003106dd:	incl %eax
0x003106de:	je 116
0x003106e0:	xorl %eax, %eax
0x003106e2:	addl %eax, -12(%ebp)
0x003106e5:	je 0x003106f9
0x003106f9:	popl %edx
0x003106fa:	pushl %esi
0x003106fb:	movl %edi, 0x4(%esi)
0x003106fe:	addl %edi, %edx
0x00310700:	cmpl %edi, -24(%ebp)
0x00310703:	jne 4
0x00310705:	addl %edi, %ecx
0x00310707:	jmp 0x0031071a
0x0031071a:	movl %eax, %edi
0x0031071c:	addl %eax, $0xfff<UINT32>
0x00310721:	shrl %eax, $0xc<UINT8>
0x00310724:	shll %eax, $0xc<UINT8>
0x00310727:	subl %eax, %edi
0x00310729:	movl %ecx, %eax
0x0031072b:	xorl %eax, %eax
0x0031072d:	pushl %edx
0x0031072e:	movl %edx, %ecx
0x00310730:	sarl %ecx, $0x2<UINT8>
0x00310733:	rep stosl %es:(%edi), %eax
0x00310735:	addl %ecx, %edx
0x00310737:	andl %ecx, $0x3<UINT8>
0x0031073a:	rep stosb %es:(%edi), %al
0x0031073c:	popl %edx
0x0031073d:	popl %esi
0x0031074b:	xorl %eax, %eax
0x0031074d:	popl %esi
0x0031074e:	popl %edi
0x0031074f:	popl %ebx
0x00310750:	leave
0x00310751:	ret $0x4<UINT16>

0x00310224:	leal %ecx, 0x10002dbd(%ebp)
0x0031022a:	testl %eax, %eax
0x0031022c:	jne 148
0x00310232:	pushl %esi
0x00310233:	call 0x00310578
0x00310578:	pushl %ebp
0x00310579:	movl %ebp, %esp
0x0031057b:	addl %esp, $0xffffffe8<UINT8>
0x0031057e:	pushl %ebx
0x0031057f:	pushl %edi
0x00310580:	pushl %esi
0x00310581:	call 0x00310586
0x00310586:	popl %ebx
0x00310587:	subl %ebx, $0x10001741<UINT32>
0x0031058d:	movl %esi, 0x8(%ebp)
0x00310590:	xorl %eax, %eax
0x00310592:	xorl %ecx, %ecx
0x00310594:	addl %ecx, 0x3c(%esi)
0x00310597:	je 10
0x00310599:	movl %edx, 0x8(%esi)
0x0031059c:	movl %edi, %esi
0x0031059e:	addl %esi, $0x50<UINT8>
0x003105a1:	jmp 0x003105aa
0x003105aa:	movl %eax, 0x10002f27(%ebx)
0x003105b0:	movl -4(%ebp), %eax
0x003105b3:	movl %ebx, %esi
0x003105b5:	movzwl %eax, 0x10(%ebx)
0x003105b9:	testl %eax, $0x2<UINT32>
0x003105be:	je 0x0031060d
0x003105c0:	pushl %ecx
0x003105c1:	movl %esi, 0x4(%ebx)
0x003105c4:	movl %edi, -4(%ebp)
0x003105c7:	movl %ecx, 0x8(%ebx)
0x003105ca:	addl %esi, %edx
0x003105cc:	movl %eax, %ecx
0x003105ce:	sarl %ecx, $0x2<UINT8>
0x003105d1:	rep movsl %es:(%edi), %ds:(%esi)
0x003105d3:	addl %ecx, %eax
0x003105d5:	andl %ecx, $0x3<UINT8>
0x003105d8:	rep movsb %es:(%edi), %ds:(%esi)
0x003105da:	movl %edi, 0x4(%ebx)
0x003105dd:	movl %ecx, 0x8(%ebx)
0x003105e0:	addl %edi, %edx
0x003105e2:	xorl %eax, %eax
0x003105e4:	pushl %edx
0x003105e5:	movl %edx, %ecx
0x003105e7:	sarl %ecx, $0x2<UINT8>
0x003105ea:	rep stosl %es:(%edi), %eax
0x003105ec:	addl %ecx, %edx
0x003105ee:	andl %ecx, $0x3<UINT8>
0x003105f1:	rep stosb %es:(%edi), %al
0x003105f3:	popl %edx
0x003105f4:	movl %esi, -4(%ebp)
0x003105f7:	movl %edi, (%ebx)
0x003105f9:	addl %edi, %edx
0x003105fb:	movl %ecx, 0x8(%ebx)
0x003105fe:	movl %eax, %ecx
0x00310600:	sarl %ecx, $0x2<UINT8>
0x00310603:	rep movsl %es:(%edi), %ds:(%esi)
0x00310605:	addl %ecx, %eax
0x00310607:	andl %ecx, $0x3<UINT8>
0x0031060a:	rep movsb %es:(%edi), %ds:(%esi)
0x0031060c:	popl %ecx
0x0031060d:	addl %ebx, $0x1c<UINT8>
0x00310610:	decl %ecx
0x00310611:	jne 0x003105b5
0x00310613:	popl %esi
0x00310614:	popl %edi
0x00310615:	popl %ebx
0x00310616:	leave
0x00310617:	ret $0x4<UINT16>

0x00310238:	pushl %esi
0x00310239:	call 0x00310493
0x00310493:	pushl %ebp
0x00310494:	movl %ebp, %esp
0x00310496:	addl %esp, $0xffffffe8<UINT8>
0x00310499:	pushl %ebx
0x0031049a:	pushl %edi
0x0031049b:	pushl %esi
0x0031049c:	call 0x003104a1
0x003104a1:	popl %ebx
0x003104a2:	subl %ebx, $0x1000165c<UINT32>
0x003104a8:	movl %esi, 0x8(%ebp)
0x003104ab:	xorl %eax, %eax
0x003104ad:	xorl %ecx, %ecx
0x003104af:	addl %ecx, 0x3c(%esi)
0x003104b2:	je 10
0x003104b4:	movl %edx, 0x8(%esi)
0x003104b7:	movl %edi, %esi
0x003104b9:	addl %esi, $0x50<UINT8>
0x003104bc:	jmp 0x003104c5
0x003104c5:	movl -8(%ebp), %edx
0x003104c8:	movzwl %eax, 0x10(%esi)
0x003104cc:	testl %eax, $0x200<UINT32>
0x003104d1:	jne 125
0x003104d3:	testl %eax, $0x8<UINT32>
0x003104d8:	je 0x00310567
0x00310567:	addl %esi, $0x1c<UINT8>
0x0031056a:	decl %ecx
0x0031056b:	jne 0x003104c8
0x003104de:	pushl %ecx
0x003104df:	pushl %esi
0x003104e0:	movl %edi, 0x8(%esi)
0x003104e3:	xorl %ecx, %ecx
0x003104e5:	movl -4(%ebp), %ecx
0x003104e8:	movzwl %ebx, 0x12(%esi)
0x003104ec:	movl %esi, (%esi)
0x003104ee:	addl %esi, -8(%ebp)
0x003104f1:	cmpl %ecx, %edi
0x003104f3:	jnl 0x00310529
0x003104f5:	movl %eax, (%esi)
0x003104f7:	incl %esi
0x003104f8:	movzbl %edx, %al
0x003104fb:	addl -4(%ebp), %edx
0x003104fe:	subb %al, $0xffffffe8<UINT8>
0x00310500:	movl %edx, %ebx
0x00310502:	je 0x0031050a
0x00310504:	decb %al
0x00310506:	movb %dl, %bh
0x00310508:	jne 0x00310526
0x00310526:	incl %ecx
0x00310527:	jmp 0x003104f1
0x0031050a:	movl %eax, (%esi)
0x0031050c:	cmpb %al, %dl
0x0031050e:	jne 13
0x00310510:	shrw %ax, $0x8<UINT8>
0x00310514:	roll %eax, $0x10<UINT8>
0x00310517:	xchgb %ah, %al
0x00310519:	subl %eax, %ecx
0x0031051b:	movl (%esi), %eax
0x0031051d:	addl -4(%ebp), %eax
0x00310520:	addl %esi, $0x4<UINT8>
0x00310523:	addl %ecx, $0x4<UINT8>
0x00310529:	popl %esi
0x0031052a:	popl %ecx
0x0031052b:	xorl %eax, %eax
0x0031052d:	addl %eax, 0x14(%esi)
0x00310530:	je 53
0x00310532:	cmpl %eax, -4(%ebp)
0x00310535:	je 0x00310567
0x00310571:	popl %esi
0x00310572:	popl %edi
0x00310573:	popl %ebx
0x00310574:	leave
0x00310575:	ret $0x4<UINT16>

0x0031023e:	nop
0x0031023f:	nop
0x00310240:	nop
0x00310241:	nop
0x00310242:	nop
0x00310243:	nop
0x00310244:	nop
0x00310245:	nop
0x00310246:	nop
0x00310247:	nop
0x00310248:	nop
0x00310249:	nop
0x0031024a:	nop
0x0031024b:	nop
0x0031024c:	movl %ecx, 0x34(%esi)
0x0031024f:	testl %ecx, %ecx
0x00310251:	je 137
0x00310257:	addl %ecx, 0x8(%esi)
0x0031025a:	pushl %ecx
0x0031025b:	pushl %esi
0x0031025c:	call 0x003108a8
0x003108a8:	pushl %ebp
0x003108a9:	movl %ebp, %esp
0x003108ab:	pushl %ebx
0x003108ac:	pushl %edi
0x003108ad:	pushl %esi
0x003108ae:	movl %esi, 0xc(%ebp)
0x003108b1:	movl %ebx, 0x8(%ebp)
0x003108b4:	xorl %eax, %eax
0x003108b6:	cmpl 0x10(%esi), %eax
0x003108b9:	jne 0x003108bf
0x003108bf:	addl %eax, (%esi)
0x003108c1:	je 3
0x003108c3:	addl %eax, 0x8(%ebx)
0x003108c6:	movl %ecx, 0xc(%esi)
0x003108c9:	addl %ecx, 0x8(%ebx)
0x003108cc:	movl %edi, 0x10(%esi)
0x003108cf:	testl %edi, %edi
0x003108d1:	je 3
0x003108d3:	addl %edi, 0x8(%ebx)
0x003108d6:	pushl %eax
0x003108d7:	pushl %edi
0x003108d8:	pushl %ecx
0x003108d9:	pushl %ebx
0x003108da:	call 0x003108ef
0x003108ef:	pushl %ebp
0x003108f0:	movl %ebp, %esp
0x003108f2:	addl %esp, $0xffffffe8<UINT8>
0x003108f5:	pushl %ebx
0x003108f6:	pushl %edi
0x003108f7:	pushl %esi
0x003108f8:	call 0x003108fd
0x003108fd:	popl %ebx
0x003108fe:	subl %ebx, $0x10001ab8<UINT32>
0x00310904:	movl %eax, 0xc(%ebp)
0x00310907:	movl 0x10002d9c(%ebx), %eax
0x0031090d:	xorl %eax, %eax
0x0031090f:	movl 0x10002da0(%ebx), %eax
0x00310915:	xorl %esi, %esi
0x00310917:	incl %esi
0x00310918:	incl %esi
0x00310919:	movl %edx, 0x10002eef(%ebx)
0x0031091f:	pushl 0xc(%ebp)
0x00310922:	call GetModuleHandleA@kernel32.dll
GetModuleHandleA@kernel32: API Node	
0x00310924:	movl -4(%ebp), %eax
0x00310927:	movl %edx, 0x10002f1b(%ebx)
0x0031092d:	testl %eax, %eax
0x0031092f:	jne 0x0031093e
0x0031093e:	nop
0x0031093f:	nop
0x00310940:	nop
0x00310941:	nop
0x00310942:	nop
0x00310943:	nop
0x00310944:	nop
0x00310945:	nop
0x00310946:	nop
0x00310947:	nop
0x00310948:	nop
0x00310949:	nop
0x0031094a:	nop
0x0031094b:	nop
0x0031094c:	nop
0x0031094d:	nop
0x0031094e:	nop
0x0031094f:	nop
0x00310950:	nop
0x00310951:	nop
0x00310952:	nop
0x00310953:	nop
0x00310954:	nop
0x00310955:	nop
0x00310956:	nop
0x00310957:	nop
0x00310958:	nop
0x00310959:	nop
0x0031095a:	nop
0x0031095b:	nop
0x0031095c:	nop
0x0031095d:	nop
0x0031095e:	nop
0x0031095f:	nop
0x00310960:	nop
0x00310961:	nop
0x00310962:	nop
0x00310963:	nop
0x00310964:	nop
0x00310965:	nop
0x00310966:	nop
0x00310967:	nop
0x00310968:	movl %esi, 0x10(%ebp)
0x0031096b:	movl %edi, 0x8(%ebp)
0x0031096e:	movl %edx, 0x14(%ebp)
0x00310971:	testl %edx, %edx
0x00310973:	jne 0x00310977
0x00310977:	testl %esi, %esi
0x00310979:	jne 0x0031097d
0x0031097d:	movl 0x10002da0(%ebx), $0x0<UINT32>
0x00310987:	movl %eax, (%edx)
0x00310989:	testl %eax, %eax
0x0031098b:	je 0x003109d1
0x0031098d:	pushl %edx
0x0031098e:	movl 0x10002da0(%ebx), %eax
0x00310994:	testl %eax, $0x80000000<UINT32>
0x00310999:	je 0x003109a4
0x003109a4:	movl %ecx, 0x8(%ebp)
0x003109a7:	addl %eax, 0x8(%ecx)
0x003109aa:	xorl %ecx, %ecx
0x003109ac:	movw %cx, (%eax)
0x003109af:	pushl %ecx
0x003109b0:	incl %eax
0x003109b1:	incl %eax
0x003109b2:	pushl %eax
0x003109b3:	pushl -4(%ebp)
0x003109b6:	call 0x00310ad2
0x00310ad2:	pushl %ebp
0x00310ad3:	movl %ebp, %esp
0x00310ad5:	pushl %ebx
0x00310ad6:	pushl %edi
0x00310ad7:	pushl %esi
0x00310ad8:	call 0x00310add
0x00310add:	popl %ebx
0x00310ade:	subl %ebx, $0x10001c98<UINT32>
0x00310ae4:	pushl 0xc(%ebp)
0x00310ae7:	pushl 0x8(%ebp)
0x00310aea:	call GetProcAddress@kernel32.dll
0x00310af0:	popl %esi
0x00310af1:	popl %edi
0x00310af2:	popl %ebx
0x00310af3:	leave
0x00310af4:	ret $0xc<UINT16>

0x003109bc:	popl %edx
0x003109bd:	testl %eax, %eax
0x003109bf:	je -145
0x003109c5:	movl (%esi), %eax
0x003109c7:	movl (%edx), %eax
0x003109c9:	addl %edx, $0x4<UINT8>
0x003109cc:	addl %esi, $0x4<UINT8>
0x003109cf:	jmp 0x0031097d
0x003109d1:	xorl %eax, %eax
0x003109d3:	popl %esi
0x003109d4:	popl %edi
0x003109d5:	popl %ebx
0x003109d6:	leave
0x003109d7:	ret $0x10<UINT16>

0x003108df:	incl %eax
0x003108e0:	jne 0x003108ea
0x003108ea:	addl %esi, $0x14<UINT8>
0x003108ed:	jmp 0x003108b4
GetModuleHandleA@kernel32.dll: API Node	
0x003108bb:	cmpl (%esi), %eax
0x003108bd:	je 0x003108e3
0x003108e3:	popl %esi
0x003108e4:	popl %edi
0x003108e5:	popl %ebx
0x003108e6:	leave
0x003108e7:	ret $0x8<UINT16>

0x00310261:	testl %eax, %eax
0x00310263:	je 0x003102e0
0x003102e0:	movl %edi, 0x8(%ebx)
0x003102e3:	movl %ebx, %esi
0x003102e5:	cmpl 0x48(%ebx), $0x1<UINT8>
0x003102e9:	jne 0x00310300
0x00310300:	movl %esi, %ebx
0x00310302:	nop
0x00310303:	nop
0x00310304:	nop
0x00310305:	nop
0x00310306:	nop
0x00310307:	nop
0x00310308:	nop
0x00310309:	nop
0x0031030a:	nop
0x0031030b:	nop
0x0031030c:	nop
0x0031030d:	nop
0x0031030e:	pushl %esi
0x0031030f:	call 0x00310af7
0x00310af7:	pushl %ebp
0x00310af8:	movl %ebp, %esp
0x00310afa:	addl %esp, $0xfffffffc<UINT8>
0x00310afd:	pushl %ebx
0x00310afe:	pushl %edi
0x00310aff:	pushl %esi
0x00310b00:	call 0x00310b05
0x00310b05:	popl %ebx
0x00310b06:	subl %ebx, $0x10001cc0<UINT32>
0x00310b0c:	movl %esi, 0x8(%ebp)
0x00310b0f:	movl %eax, 0x8(%esi)
0x00310b12:	addl %eax, 0x3c(%eax)
0x00310b15:	leal %edi, 0x80(%eax)
0x00310b1b:	movl %ecx, %edi
0x00310b1d:	shrl %ecx, $0xc<UINT8>
0x00310b20:	shll %ecx, $0xc<UINT8>
0x00310b23:	pushl %ecx
0x00310b24:	leal %eax, -4(%ebp)
0x00310b27:	pushl %eax
0x00310b28:	pushl $0x4<UINT8>
0x00310b2a:	pushl $0x1000<UINT32>
0x00310b2f:	pushl %ecx
0x00310b30:	call VirtualProtect@kernel32
VirtualProtect@kernel32: API Node	
0x00310b36:	movl %edx, 0x34(%esi)
0x00310b39:	movl (%edi), %edx
0x00310b3b:	popl %ecx
0x00310b3c:	leal %eax, -4(%ebp)
0x00310b3f:	pushl %eax
0x00310b40:	pushl -4(%ebp)
0x00310b43:	pushl $0x1000<UINT32>
0x00310b48:	pushl %ecx
0x00310b49:	call VirtualProtect@kernel32
0x00310b4f:	popl %esi
0x00310b50:	popl %edi
0x00310b51:	popl %ebx
0x00310b52:	leave
0x00310b53:	ret $0x4<UINT16>

0x00310314:	nop
0x00310315:	nop
0x00310316:	nop
0x00310317:	nop
0x00310318:	nop
0x00310319:	nop
0x0031031a:	pushl %edi
0x0031031b:	call 0x00310a48
0x00310a48:	pushl %ebp
0x00310a49:	movl %ebp, %esp
0x00310a4b:	addl %esp, $0xfffffffc<UINT8>
0x00310a4e:	pushl %ebx
0x00310a4f:	pushl %edi
0x00310a50:	pushl %esi
0x00310a51:	call 0x00310a56
0x00310a56:	popl %ebx
0x00310a57:	subl %ebx, $0x10001c11<UINT32>
0x00310a5d:	movl %eax, 0x8(%ebp)
0x00310a60:	addl %eax, 0x3c(%eax)
0x00310a63:	xorl %ecx, %ecx
0x00310a65:	movw %cx, 0x14(%eax)
0x00310a69:	leal %edi, 0x18(%ecx,%eax)
0x00310a6d:	addl %edi, $0x27<UINT8>
0x00310a70:	movl %ecx, %edi
0x00310a72:	shrl %ecx, $0xc<UINT8>
0x00310a75:	shll %ecx, $0xc<UINT8>
0x00310a78:	pushl %ecx
0x00310a79:	leal %eax, -4(%ebp)
0x00310a7c:	pushl %eax
0x00310a7d:	pushl $0x4<UINT8>
0x00310a7f:	pushl $0x1000<UINT32>
0x00310a84:	pushl %ecx
0x00310a85:	call VirtualProtect@kernel32
0x00310a8b:	movb %al, (%edi)
0x00310a8d:	testb %al, $0xffffff80<UINT8>
0x00310a8f:	je 0x00310a95
0x00310a95:	popl %ecx
0x00310a96:	leal %eax, -4(%ebp)
0x00310a99:	pushl %eax
0x00310a9a:	pushl -4(%ebp)
0x00310a9d:	pushl $0x1000<UINT32>
0x00310aa2:	pushl %ecx
0x00310aa3:	call VirtualProtect@kernel32
0x00310aa9:	popl %esi
0x00310aaa:	popl %edi
0x00310aab:	popl %ebx
0x00310aac:	leave
0x00310aad:	ret $0x4<UINT16>

0x00310320:	pushl $0x8000<UINT32>
0x00310325:	pushl $0x0<UINT8>
0x00310327:	pushl 0x10002f27(%ebp)
0x0031032d:	call VirtualFree@kernel32.dll
VirtualFree@kernel32.dll: API Node	
0x00310333:	movl %eax, 0xc(%esi)
0x00310336:	addl %eax, %edi
0x00310338:	popl %ebp
0x00310339:	popl %esi
0x0031033a:	popl %edi
0x0031033b:	popl %ebx
0x0031033c:	ret

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
