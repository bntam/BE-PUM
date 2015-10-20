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
0x004048f6:	call 0x002b0188
0x002b0188:	pushl %ebx
0x002b0189:	pushl %edi
0x002b018a:	pushl %esi
0x002b018b:	pushl %ebp
0x002b018c:	call 0x002b0191
0x002b0191:	popl %ebp
0x002b0192:	subl %ebp, $0x1000134c<UINT32>
0x002b0198:	leal %esi, 0x10001343(%ebp)
0x002b019e:	movl %eax, -4(%esi)
0x002b01a1:	addl %eax, $0x4<UINT8>
0x002b01a4:	subl %esi, %eax
0x002b01a6:	cld
0x002b01a7:	movl %ebx, %esi
0x002b01a9:	movl %edx, 0x8(%esi)
0x002b01ac:	movl %esi, 0x1c(%esi)
0x002b01af:	addl %esi, %edx
0x002b01b1:	leal %edi, 0x10002f2f(%ebp)
0x002b01b7:	lodsl %eax, %ds:(%esi)
0x002b01b8:	stosl %es:(%edi), %eax
0x002b01b9:	lodsl %eax, %ds:(%esi)
0x002b01ba:	stosl %es:(%edi), %eax
0x002b01bb:	lodsl %eax, %ds:(%esi)
0x002b01bc:	stosl %es:(%edi), %eax
0x002b01bd:	lodsl %eax, %ds:(%esi)
0x002b01be:	stosl %es:(%edi), %eax
0x002b01bf:	nop
0x002b01c0:	cmpl 0x48(%ebx), $0x1<UINT8>
0x002b01c4:	je 21
0x002b01c6:	movl %esi, 0x44(%ebx)
0x002b01c9:	testl %esi, %esi
0x002b01cb:	je 14
0x002b01cd:	movl %ecx, $0x23<UINT32>
0x002b01d2:	addl %esi, %edx
0x002b01d4:	movl %edi, 0x40(%ebx)
0x002b01d7:	addl %edi, %edx
0x002b01d9:	rep movsb %es:(%edi), %ds:(%esi)
0x002b01db:	movl %esi, %ebx
0x002b01dd:	leal %edi, 0x10002f1b(%ebp)
0x002b01e3:	addl (%edi), %ebp
0x002b01e5:	addl 0x4(%edi), %ebp
0x002b01e8:	addl 0x8(%edi), %ebp
0x002b01eb:	leal %ecx, 0x10002eff(%ebp)
0x002b01f1:	pushl %ecx
0x002b01f2:	call 0x002b033d
0x002b033d:	pushl %ebp
0x002b033e:	movl %ebp, %esp
0x002b0340:	addl %esp, $0xfffffffc<UINT8>
0x002b0343:	pushl %ebx
0x002b0344:	pushl %edi
0x002b0345:	pushl %esi
0x002b0346:	call 0x002b034b
0x002b034b:	popl %ebx
0x002b034c:	subl %ebx, $0x10001506<UINT32>
0x002b0352:	movl %esi, 0x8(%ebp)
0x002b0355:	movl %ecx, (%esi)
0x002b0357:	addl %ecx, %ebx
0x002b0359:	pushl %ecx
0x002b035a:	call LoadLibraryA@kernel32.dll
LoadLibraryA@kernel32.dll: API Node	
0x002b0360:	movl -4(%ebp), %eax
0x002b0363:	movl %edx, 0x4(%esi)
0x002b0366:	movl %edi, 0x8(%esi)
0x002b0369:	addl %edx, %ebx
0x002b036b:	addl %edi, %ebx
0x002b036d:	xorl %eax, %eax
0x002b036f:	addl %eax, (%edx)
0x002b0371:	je 0x002b0389
0x002b0373:	pushl %edx
0x002b0374:	movl %eax, (%edx)
0x002b0376:	addl %eax, %ebx
0x002b0378:	pushl %eax
0x002b0379:	pushl -4(%ebp)
0x002b037c:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x002b0382:	stosl %es:(%edi), %eax
0x002b0383:	popl %edx
0x002b0384:	addl %edx, $0x4<UINT8>
0x002b0387:	jmp 0x002b036d
0x002b0389:	addl %esi, $0xc<UINT8>
0x002b038c:	addl %eax, (%esi)
0x002b038e:	jne 0x002b0355
0x002b0390:	popl %esi
0x002b0391:	popl %edi
0x002b0392:	popl %ebx
0x002b0393:	leave
0x002b0394:	ret $0x4<UINT16>

0x002b01f7:	nop
0x002b01f8:	nop
0x002b01f9:	nop
0x002b01fa:	nop
0x002b01fb:	nop
0x002b01fc:	nop
0x002b01fd:	nop
0x002b01fe:	nop
0x002b01ff:	movl %ecx, 0x2c(%esi)
0x002b0202:	movl 0x10002f2b(%ebp), %ecx
0x002b0208:	pushl $0x4<UINT8>
0x002b020a:	pushl $0x1000<UINT32>
0x002b020f:	pushl %ecx
0x002b0210:	pushl $0x0<UINT8>
0x002b0212:	call VirtualAlloc@kernel32.dll
0x002b0218:	movl 0x10002f27(%ebp), %eax
0x002b021e:	pushl %esi
0x002b021f:	call 0x002b061a
0x002b061a:	pushl %ebp
0x002b061b:	movl %ebp, %esp
0x002b061d:	addl %esp, $0xffffffe8<UINT8>
0x002b0620:	pushl %ebx
0x002b0621:	pushl %edi
0x002b0622:	pushl %esi
0x002b0623:	call 0x002b0628
0x002b0628:	popl %ebx
0x002b0629:	subl %ebx, $0x100017e3<UINT32>
0x002b062f:	movl %esi, 0x8(%ebp)
0x002b0632:	xorl %eax, %eax
0x002b0634:	xorl %ecx, %ecx
0x002b0636:	addl %ecx, 0x3c(%esi)
0x002b0639:	je 10
0x002b063b:	movl %edx, 0x8(%esi)
0x002b063e:	movl %edi, %esi
0x002b0640:	addl %esi, $0x50<UINT8>
0x002b0643:	jmp 0x002b064c
0x002b064c:	movl -4(%ebp), %eax
0x002b064f:	movzwl %eax, 0x2(%edi)
0x002b0653:	movl -16(%ebp), %eax
0x002b0656:	pushl %ecx
0x002b0657:	pushl %edx
0x002b0658:	pushl %esi
0x002b0659:	movzwl %eax, 0x10(%esi)
0x002b065d:	testl %eax, $0x10<UINT32>
0x002b0662:	je 0x002b073e
0x002b073e:	popl %esi
0x002b073f:	popl %edx
0x002b0740:	popl %ecx
0x002b0741:	addl %esi, $0x1c<UINT8>
0x002b0744:	decl %ecx
0x002b0745:	jne 0x002b0656
0x002b0668:	pushl %esi
0x002b0669:	movl %edi, 0x10002f27(%ebx)
0x002b066f:	movl -20(%ebp), %edi
0x002b0672:	movl %ecx, 0x8(%esi)
0x002b0675:	movl %eax, 0x14(%esi)
0x002b0678:	subl %ecx, %eax
0x002b067a:	movl %esi, (%esi)
0x002b067c:	addl %esi, %edx
0x002b067e:	movl %eax, %ecx
0x002b0680:	sarl %ecx, $0x2<UINT8>
0x002b0683:	rep movsl %es:(%edi), %ds:(%esi)
0x002b0685:	addl %ecx, %eax
0x002b0687:	andl %ecx, $0x3<UINT8>
0x002b068a:	rep movsb %es:(%edi), %ds:(%esi)
0x002b068c:	popl %esi
0x002b068d:	nop
0x002b068e:	nop
0x002b068f:	nop
0x002b0690:	nop
0x002b0691:	nop
0x002b0692:	nop
0x002b0693:	nop
0x002b0694:	nop
0x002b0695:	nop
0x002b0696:	nop
0x002b0697:	nop
0x002b0698:	nop
0x002b0699:	nop
0x002b069a:	nop
0x002b069b:	nop
0x002b069c:	nop
0x002b069d:	nop
0x002b069e:	nop
0x002b069f:	nop
0x002b06a0:	nop
0x002b06a1:	nop
0x002b06a2:	nop
0x002b06a3:	nop
0x002b06a4:	nop
0x002b06a5:	nop
0x002b06a6:	nop
0x002b06a7:	nop
0x002b06a8:	nop
0x002b06a9:	nop
0x002b06aa:	nop
0x002b06ab:	nop
0x002b06ac:	nop
0x002b06ad:	nop
0x002b06ae:	nop
0x002b06af:	nop
0x002b06b0:	nop
0x002b06b1:	nop
0x002b06b2:	nop
0x002b06b3:	movl %eax, 0x4(%esi)
0x002b06b6:	addl %eax, %edx
0x002b06b8:	movl -24(%ebp), %eax
0x002b06bb:	movl %eax, -16(%ebp)
0x002b06be:	decl %eax
0x002b06bf:	movl -12(%ebp), %eax
0x002b06c2:	pushl %edx
0x002b06c3:	pushl %eax
0x002b06c4:	pushl 0x8(%ebp)
0x002b06c7:	call 0x002b0a08
0x002b0a08:	pushl %ebp
0x002b0a09:	movl %ebp, %esp
0x002b0a0b:	addl %esp, $0xfffffffc<UINT8>
0x002b0a0e:	pushl %ebx
0x002b0a0f:	pushl %edi
0x002b0a10:	pushl %esi
0x002b0a11:	movl %ebx, 0x8(%ebp)
0x002b0a14:	movl %esi, %ebx
0x002b0a16:	movl %ecx, 0x30(%ebx)
0x002b0a19:	subl %esi, %ecx
0x002b0a1b:	movl -4(%ebp), %esi
0x002b0a1e:	xorl %ecx, %ecx
0x002b0a20:	lodsl %eax, %ds:(%esi)
0x002b0a21:	testl %eax, %eax
0x002b0a23:	je 28
0x002b0a25:	cmpl %ecx, 0xc(%ebp)
0x002b0a28:	je 0x002b0a2d
0x002b0a2d:	nop
0x002b0a2e:	nop
0x002b0a2f:	nop
0x002b0a30:	nop
0x002b0a31:	nop
0x002b0a32:	nop
0x002b0a33:	nop
0x002b0a34:	nop
0x002b0a35:	nop
0x002b0a36:	nop
0x002b0a37:	nop
0x002b0a38:	nop
0x002b0a39:	nop
0x002b0a3a:	nop
0x002b0a3b:	nop
0x002b0a3c:	nop
0x002b0a3d:	nop
0x002b0a3e:	addl %eax, -4(%ebp)
0x002b0a41:	popl %esi
0x002b0a42:	popl %edi
0x002b0a43:	popl %ebx
0x002b0a44:	leave
0x002b0a45:	ret $0x8<UINT16>

0x002b06cc:	leal %ecx, 0x10002f2f(%ebx)
0x002b06d2:	pushl %ecx
0x002b06d3:	pushl -24(%ebp)
0x002b06d6:	pushl -20(%ebp)
0x002b06d9:	call 0x002b0008
0x002b0008:	pusha
0x002b0009:	movl %esi, 0x24(%esp)
0x002b000d:	movl %edi, 0x28(%esp)
0x002b0011:	cld
0x002b0012:	lodsl %eax, %ds:(%esi)
0x002b0013:	xorl %ecx, %ecx
0x002b0015:	testl %eax, %eax
0x002b0017:	je 17
0x002b0019:	xorl %edx, %edx
0x002b001b:	leal %ebx, (%eax,%edi)
0x002b001e:	movsb %es:(%edi), %ds:(%esi)
0x002b001f:	movb %cl, $0x3<UINT8>
0x002b0021:	call 0x002b0098
0x002b0098:	addl %edx, %edx
0x002b009a:	jne 0x002b00a2
0x002b009c:	xchgl %edx, %eax
0x002b009d:	lodsl %eax, %ds:(%esi)
0x002b009e:	xchgl %edx, %eax
0x002b009f:	addl %edx, %edx
0x002b00a1:	incl %edx
0x002b00a2:	ret

0x002b0026:	jae 0x002b001e
0x002b0028:	cmpl %edi, %ebx
0x002b002a:	jae 0x002b00b5
0x002b0030:	pushl %ebx
0x002b0031:	pushl %ebp
0x002b0032:	pushl %edi
0x002b0033:	xorl %ebx, %ebx
0x002b0035:	incl %ebx
0x002b0036:	xorl %ebp, %ebp
0x002b0038:	movl %eax, %ebx
0x002b003a:	leal %edi, (%ebp,%ebx)
0x002b003e:	movl %ebp, %ebx
0x002b0040:	movl %ebx, %edi
0x002b0042:	call 0x002b0098
0x002b0047:	jae 0x002b003a
0x002b0049:	leal %ebx, (%ebp,%edi)
0x002b004d:	addl %eax, %edi
0x002b004f:	movl %ebp, %edi
0x002b0051:	call 0x002b0098
0x002b0056:	jae 0x002b003a
0x002b0058:	popl %edi
0x002b0059:	popl %ebp
0x002b005a:	popl %ebx
0x002b005b:	subl %eax, %ecx
0x002b005d:	jae 0x002b0068
0x002b0068:	movb %cl, $0x6<UINT8>
0x002b006a:	call 0x002b0098
0x002b006f:	adcl %eax, %eax
0x002b0071:	decl %ecx
0x002b0072:	jne 0x002b006a
0x002b0074:	incl %eax
0x002b0075:	call 0x002b00a3
0x002b00a3:	xorl %ecx, %ecx
0x002b00a5:	incl %ecx
0x002b00a6:	call 0x002b0098
0x002b00ab:	adcl %ecx, %ecx
0x002b00ad:	call 0x002b0098
0x002b00b2:	jb 0x002b00a6
0x002b00b4:	ret

0x002b007a:	movl %ebp, %eax
0x002b007c:	cmpl %eax, $0x8001<UINT32>
0x002b0081:	sbbl %ecx, $0xffffffff<UINT8>
0x002b0084:	cmpl %eax, $0x781<UINT32>
0x002b0089:	sbbl %ecx, $0xffffffff<UINT8>
0x002b008c:	pushl %esi
0x002b008d:	movl %esi, %edi
0x002b008f:	subl %esi, %eax
0x002b0091:	rep movsb %es:(%edi), %ds:(%esi)
0x002b0093:	popl %esi
0x002b0094:	incl %ecx
0x002b0095:	incl %ecx
0x002b0096:	jmp 0x002b0021
0x002b005f:	movl %eax, %ebp
0x002b0061:	call 0x002b00a3
0x002b0066:	jmp 0x002b008c
0x002b00b5:	subl %edi, 0x28(%esp)
0x002b00b9:	movl 0x1c(%esp), %edi
0x002b00bd:	popa
0x002b00be:	ret $0xc<UINT16>

0x002b06db:	movl %ecx, %eax
0x002b06dd:	incl %eax
0x002b06de:	je 116
0x002b06e0:	xorl %eax, %eax
0x002b06e2:	addl %eax, -12(%ebp)
0x002b06e5:	je 0x002b06f9
0x002b06f9:	popl %edx
0x002b06fa:	pushl %esi
0x002b06fb:	movl %edi, 0x4(%esi)
0x002b06fe:	addl %edi, %edx
0x002b0700:	cmpl %edi, -24(%ebp)
0x002b0703:	jne 4
0x002b0705:	addl %edi, %ecx
0x002b0707:	jmp 0x002b071a
0x002b071a:	movl %eax, %edi
0x002b071c:	addl %eax, $0xfff<UINT32>
0x002b0721:	shrl %eax, $0xc<UINT8>
0x002b0724:	shll %eax, $0xc<UINT8>
0x002b0727:	subl %eax, %edi
0x002b0729:	movl %ecx, %eax
0x002b072b:	xorl %eax, %eax
0x002b072d:	pushl %edx
0x002b072e:	movl %edx, %ecx
0x002b0730:	sarl %ecx, $0x2<UINT8>
0x002b0733:	rep stosl %es:(%edi), %eax
0x002b0735:	addl %ecx, %edx
0x002b0737:	andl %ecx, $0x3<UINT8>
0x002b073a:	rep stosb %es:(%edi), %al
0x002b073c:	popl %edx
0x002b073d:	popl %esi
0x002b074b:	xorl %eax, %eax
0x002b074d:	popl %esi
0x002b074e:	popl %edi
0x002b074f:	popl %ebx
0x002b0750:	leave
0x002b0751:	ret $0x4<UINT16>

0x002b0224:	leal %ecx, 0x10002dbd(%ebp)
0x002b022a:	testl %eax, %eax
0x002b022c:	jne 148
0x002b0232:	pushl %esi
0x002b0233:	call 0x002b0578
0x002b0578:	pushl %ebp
0x002b0579:	movl %ebp, %esp
0x002b057b:	addl %esp, $0xffffffe8<UINT8>
0x002b057e:	pushl %ebx
0x002b057f:	pushl %edi
0x002b0580:	pushl %esi
0x002b0581:	call 0x002b0586
0x002b0586:	popl %ebx
0x002b0587:	subl %ebx, $0x10001741<UINT32>
0x002b058d:	movl %esi, 0x8(%ebp)
0x002b0590:	xorl %eax, %eax
0x002b0592:	xorl %ecx, %ecx
0x002b0594:	addl %ecx, 0x3c(%esi)
0x002b0597:	je 10
0x002b0599:	movl %edx, 0x8(%esi)
0x002b059c:	movl %edi, %esi
0x002b059e:	addl %esi, $0x50<UINT8>
0x002b05a1:	jmp 0x002b05aa
0x002b05aa:	movl %eax, 0x10002f27(%ebx)
0x002b05b0:	movl -4(%ebp), %eax
0x002b05b3:	movl %ebx, %esi
0x002b05b5:	movzwl %eax, 0x10(%ebx)
0x002b05b9:	testl %eax, $0x2<UINT32>
0x002b05be:	je 0x002b060d
0x002b05c0:	pushl %ecx
0x002b05c1:	movl %esi, 0x4(%ebx)
0x002b05c4:	movl %edi, -4(%ebp)
0x002b05c7:	movl %ecx, 0x8(%ebx)
0x002b05ca:	addl %esi, %edx
0x002b05cc:	movl %eax, %ecx
0x002b05ce:	sarl %ecx, $0x2<UINT8>
0x002b05d1:	rep movsl %es:(%edi), %ds:(%esi)
0x002b05d3:	addl %ecx, %eax
0x002b05d5:	andl %ecx, $0x3<UINT8>
0x002b05d8:	rep movsb %es:(%edi), %ds:(%esi)
0x002b05da:	movl %edi, 0x4(%ebx)
0x002b05dd:	movl %ecx, 0x8(%ebx)
0x002b05e0:	addl %edi, %edx
0x002b05e2:	xorl %eax, %eax
0x002b05e4:	pushl %edx
0x002b05e5:	movl %edx, %ecx
0x002b05e7:	sarl %ecx, $0x2<UINT8>
0x002b05ea:	rep stosl %es:(%edi), %eax
0x002b05ec:	addl %ecx, %edx
0x002b05ee:	andl %ecx, $0x3<UINT8>
0x002b05f1:	rep stosb %es:(%edi), %al
0x002b05f3:	popl %edx
0x002b05f4:	movl %esi, -4(%ebp)
0x002b05f7:	movl %edi, (%ebx)
0x002b05f9:	addl %edi, %edx
0x002b05fb:	movl %ecx, 0x8(%ebx)
0x002b05fe:	movl %eax, %ecx
0x002b0600:	sarl %ecx, $0x2<UINT8>
0x002b0603:	rep movsl %es:(%edi), %ds:(%esi)
0x002b0605:	addl %ecx, %eax
0x002b0607:	andl %ecx, $0x3<UINT8>
0x002b060a:	rep movsb %es:(%edi), %ds:(%esi)
0x002b060c:	popl %ecx
0x002b060d:	addl %ebx, $0x1c<UINT8>
0x002b0610:	decl %ecx
0x002b0611:	jne 0x002b05b5
0x002b0613:	popl %esi
0x002b0614:	popl %edi
0x002b0615:	popl %ebx
0x002b0616:	leave
0x002b0617:	ret $0x4<UINT16>

0x002b0238:	pushl %esi
0x002b0239:	call 0x002b0493
0x002b0493:	pushl %ebp
0x002b0494:	movl %ebp, %esp
0x002b0496:	addl %esp, $0xffffffe8<UINT8>
0x002b0499:	pushl %ebx
0x002b049a:	pushl %edi
0x002b049b:	pushl %esi
0x002b049c:	call 0x002b04a1
0x002b04a1:	popl %ebx
0x002b04a2:	subl %ebx, $0x1000165c<UINT32>
0x002b04a8:	movl %esi, 0x8(%ebp)
0x002b04ab:	xorl %eax, %eax
0x002b04ad:	xorl %ecx, %ecx
0x002b04af:	addl %ecx, 0x3c(%esi)
0x002b04b2:	je 10
0x002b04b4:	movl %edx, 0x8(%esi)
0x002b04b7:	movl %edi, %esi
0x002b04b9:	addl %esi, $0x50<UINT8>
0x002b04bc:	jmp 0x002b04c5
0x002b04c5:	movl -8(%ebp), %edx
0x002b04c8:	movzwl %eax, 0x10(%esi)
0x002b04cc:	testl %eax, $0x200<UINT32>
0x002b04d1:	jne 125
0x002b04d3:	testl %eax, $0x8<UINT32>
0x002b04d8:	je 0x002b0567
0x002b0567:	addl %esi, $0x1c<UINT8>
0x002b056a:	decl %ecx
0x002b056b:	jne 0x002b04c8
0x002b04de:	pushl %ecx
0x002b04df:	pushl %esi
0x002b04e0:	movl %edi, 0x8(%esi)
0x002b04e3:	xorl %ecx, %ecx
0x002b04e5:	movl -4(%ebp), %ecx
0x002b04e8:	movzwl %ebx, 0x12(%esi)
0x002b04ec:	movl %esi, (%esi)
0x002b04ee:	addl %esi, -8(%ebp)
0x002b04f1:	cmpl %ecx, %edi
0x002b04f3:	jnl 0x002b0529
0x002b04f5:	movl %eax, (%esi)
0x002b04f7:	incl %esi
0x002b04f8:	movzbl %edx, %al
0x002b04fb:	addl -4(%ebp), %edx
0x002b04fe:	subb %al, $0xffffffe8<UINT8>
0x002b0500:	movl %edx, %ebx
0x002b0502:	je 0x002b050a
0x002b0504:	decb %al
0x002b0506:	movb %dl, %bh
0x002b0508:	jne 0x002b0526
0x002b0526:	incl %ecx
0x002b0527:	jmp 0x002b04f1
0x002b050a:	movl %eax, (%esi)
0x002b050c:	cmpb %al, %dl
0x002b050e:	jne 13
0x002b0510:	shrw %ax, $0x8<UINT8>
0x002b0514:	roll %eax, $0x10<UINT8>
0x002b0517:	xchgb %ah, %al
0x002b0519:	subl %eax, %ecx
0x002b051b:	movl (%esi), %eax
0x002b051d:	addl -4(%ebp), %eax
0x002b0520:	addl %esi, $0x4<UINT8>
0x002b0523:	addl %ecx, $0x4<UINT8>
0x002b0529:	popl %esi
0x002b052a:	popl %ecx
0x002b052b:	xorl %eax, %eax
0x002b052d:	addl %eax, 0x14(%esi)
0x002b0530:	je 53
0x002b0532:	cmpl %eax, -4(%ebp)
0x002b0535:	je 0x002b0567
0x002b0571:	popl %esi
0x002b0572:	popl %edi
0x002b0573:	popl %ebx
0x002b0574:	leave
0x002b0575:	ret $0x4<UINT16>

0x002b023e:	nop
0x002b023f:	nop
0x002b0240:	nop
0x002b0241:	nop
0x002b0242:	nop
0x002b0243:	nop
0x002b0244:	nop
0x002b0245:	nop
0x002b0246:	nop
0x002b0247:	nop
0x002b0248:	nop
0x002b0249:	nop
0x002b024a:	nop
0x002b024b:	nop
0x002b024c:	movl %ecx, 0x34(%esi)
0x002b024f:	testl %ecx, %ecx
0x002b0251:	je 137
0x002b0257:	addl %ecx, 0x8(%esi)
0x002b025a:	pushl %ecx
0x002b025b:	pushl %esi
0x002b025c:	call 0x002b08a8
0x002b08a8:	pushl %ebp
0x002b08a9:	movl %ebp, %esp
0x002b08ab:	pushl %ebx
0x002b08ac:	pushl %edi
0x002b08ad:	pushl %esi
0x002b08ae:	movl %esi, 0xc(%ebp)
0x002b08b1:	movl %ebx, 0x8(%ebp)
0x002b08b4:	xorl %eax, %eax
0x002b08b6:	cmpl 0x10(%esi), %eax
0x002b08b9:	jne 0x002b08bf
0x002b08bf:	addl %eax, (%esi)
0x002b08c1:	je 3
0x002b08c3:	addl %eax, 0x8(%ebx)
0x002b08c6:	movl %ecx, 0xc(%esi)
0x002b08c9:	addl %ecx, 0x8(%ebx)
0x002b08cc:	movl %edi, 0x10(%esi)
0x002b08cf:	testl %edi, %edi
0x002b08d1:	je 3
0x002b08d3:	addl %edi, 0x8(%ebx)
0x002b08d6:	pushl %eax
0x002b08d7:	pushl %edi
0x002b08d8:	pushl %ecx
0x002b08d9:	pushl %ebx
0x002b08da:	call 0x002b08ef
0x002b08ef:	pushl %ebp
0x002b08f0:	movl %ebp, %esp
0x002b08f2:	addl %esp, $0xffffffe8<UINT8>
0x002b08f5:	pushl %ebx
0x002b08f6:	pushl %edi
0x002b08f7:	pushl %esi
0x002b08f8:	call 0x002b08fd
0x002b08fd:	popl %ebx
0x002b08fe:	subl %ebx, $0x10001ab8<UINT32>
0x002b0904:	movl %eax, 0xc(%ebp)
0x002b0907:	movl 0x10002d9c(%ebx), %eax
0x002b090d:	xorl %eax, %eax
0x002b090f:	movl 0x10002da0(%ebx), %eax
0x002b0915:	xorl %esi, %esi
0x002b0917:	incl %esi
0x002b0918:	incl %esi
0x002b0919:	movl %edx, 0x10002eef(%ebx)
0x002b091f:	pushl 0xc(%ebp)
0x002b0922:	call GetModuleHandleA@kernel32.dll
GetModuleHandleA@kernel32: API Node	
0x002b0924:	movl -4(%ebp), %eax
0x002b0927:	movl %edx, 0x10002f1b(%ebx)
0x002b092d:	testl %eax, %eax
0x002b092f:	jne 0x002b093e
0x002b093e:	nop
0x002b093f:	nop
0x002b0940:	nop
0x002b0941:	nop
0x002b0942:	nop
0x002b0943:	nop
0x002b0944:	nop
0x002b0945:	nop
0x002b0946:	nop
0x002b0947:	nop
0x002b0948:	nop
0x002b0949:	nop
0x002b094a:	nop
0x002b094b:	nop
0x002b094c:	nop
0x002b094d:	nop
0x002b094e:	nop
0x002b094f:	nop
0x002b0950:	nop
0x002b0951:	nop
0x002b0952:	nop
0x002b0953:	nop
0x002b0954:	nop
0x002b0955:	nop
0x002b0956:	nop
0x002b0957:	nop
0x002b0958:	nop
0x002b0959:	nop
0x002b095a:	nop
0x002b095b:	nop
0x002b095c:	nop
0x002b095d:	nop
0x002b095e:	nop
0x002b095f:	nop
0x002b0960:	nop
0x002b0961:	nop
0x002b0962:	nop
0x002b0963:	nop
0x002b0964:	nop
0x002b0965:	nop
0x002b0966:	nop
0x002b0967:	nop
0x002b0968:	movl %esi, 0x10(%ebp)
0x002b096b:	movl %edi, 0x8(%ebp)
0x002b096e:	movl %edx, 0x14(%ebp)
0x002b0971:	testl %edx, %edx
0x002b0973:	jne 0x002b0977
0x002b0977:	testl %esi, %esi
0x002b0979:	jne 0x002b097d
0x002b097d:	movl 0x10002da0(%ebx), $0x0<UINT32>
0x002b0987:	movl %eax, (%edx)
0x002b0989:	testl %eax, %eax
0x002b098b:	je 0x002b09d1
0x002b098d:	pushl %edx
0x002b098e:	movl 0x10002da0(%ebx), %eax
0x002b0994:	testl %eax, $0x80000000<UINT32>
0x002b0999:	je 0x002b09a4
0x002b09a4:	movl %ecx, 0x8(%ebp)
0x002b09a7:	addl %eax, 0x8(%ecx)
0x002b09aa:	xorl %ecx, %ecx
0x002b09ac:	movw %cx, (%eax)
0x002b09af:	pushl %ecx
0x002b09b0:	incl %eax
0x002b09b1:	incl %eax
0x002b09b2:	pushl %eax
0x002b09b3:	pushl -4(%ebp)
0x002b09b6:	call 0x002b0ad2
0x002b0ad2:	pushl %ebp
0x002b0ad3:	movl %ebp, %esp
0x002b0ad5:	pushl %ebx
0x002b0ad6:	pushl %edi
0x002b0ad7:	pushl %esi
0x002b0ad8:	call 0x002b0add
0x002b0add:	popl %ebx
0x002b0ade:	subl %ebx, $0x10001c98<UINT32>
0x002b0ae4:	pushl 0xc(%ebp)
0x002b0ae7:	pushl 0x8(%ebp)
0x002b0aea:	call GetProcAddress@kernel32.dll
0x002b0af0:	popl %esi
0x002b0af1:	popl %edi
0x002b0af2:	popl %ebx
0x002b0af3:	leave
0x002b0af4:	ret $0xc<UINT16>

0x002b09bc:	popl %edx
0x002b09bd:	testl %eax, %eax
0x002b09bf:	je -145
0x002b09c5:	movl (%esi), %eax
0x002b09c7:	movl (%edx), %eax
0x002b09c9:	addl %edx, $0x4<UINT8>
0x002b09cc:	addl %esi, $0x4<UINT8>
0x002b09cf:	jmp 0x002b097d
0x002b09d1:	xorl %eax, %eax
0x002b09d3:	popl %esi
0x002b09d4:	popl %edi
0x002b09d5:	popl %ebx
0x002b09d6:	leave
0x002b09d7:	ret $0x10<UINT16>

0x002b08df:	incl %eax
0x002b08e0:	jne 0x002b08ea
0x002b08ea:	addl %esi, $0x14<UINT8>
0x002b08ed:	jmp 0x002b08b4
GetModuleHandleA@kernel32.dll: API Node	
0x002b08bb:	cmpl (%esi), %eax
0x002b08bd:	je 0x002b08e3
0x002b08e3:	popl %esi
0x002b08e4:	popl %edi
0x002b08e5:	popl %ebx
0x002b08e6:	leave
0x002b08e7:	ret $0x8<UINT16>

0x002b0261:	testl %eax, %eax
0x002b0263:	je 0x002b02e0
0x002b02e0:	movl %edi, 0x8(%ebx)
0x002b02e3:	movl %ebx, %esi
0x002b02e5:	cmpl 0x48(%ebx), $0x1<UINT8>
0x002b02e9:	jne 0x002b0300
0x002b0300:	movl %esi, %ebx
0x002b0302:	nop
0x002b0303:	nop
0x002b0304:	nop
0x002b0305:	nop
0x002b0306:	nop
0x002b0307:	nop
0x002b0308:	nop
0x002b0309:	nop
0x002b030a:	nop
0x002b030b:	nop
0x002b030c:	nop
0x002b030d:	nop
0x002b030e:	pushl %esi
0x002b030f:	call 0x002b0af7
0x002b0af7:	pushl %ebp
0x002b0af8:	movl %ebp, %esp
0x002b0afa:	addl %esp, $0xfffffffc<UINT8>
0x002b0afd:	pushl %ebx
0x002b0afe:	pushl %edi
0x002b0aff:	pushl %esi
0x002b0b00:	call 0x002b0b05
0x002b0b05:	popl %ebx
0x002b0b06:	subl %ebx, $0x10001cc0<UINT32>
0x002b0b0c:	movl %esi, 0x8(%ebp)
0x002b0b0f:	movl %eax, 0x8(%esi)
0x002b0b12:	addl %eax, 0x3c(%eax)
0x002b0b15:	leal %edi, 0x80(%eax)
0x002b0b1b:	movl %ecx, %edi
0x002b0b1d:	shrl %ecx, $0xc<UINT8>
0x002b0b20:	shll %ecx, $0xc<UINT8>
0x002b0b23:	pushl %ecx
0x002b0b24:	leal %eax, -4(%ebp)
0x002b0b27:	pushl %eax
0x002b0b28:	pushl $0x4<UINT8>
0x002b0b2a:	pushl $0x1000<UINT32>
0x002b0b2f:	pushl %ecx
0x002b0b30:	call VirtualProtect@kernel32
VirtualProtect@kernel32: API Node	
0x002b0b36:	movl %edx, 0x34(%esi)
0x002b0b39:	movl (%edi), %edx
0x002b0b3b:	popl %ecx
0x002b0b3c:	leal %eax, -4(%ebp)
0x002b0b3f:	pushl %eax
0x002b0b40:	pushl -4(%ebp)
0x002b0b43:	pushl $0x1000<UINT32>
0x002b0b48:	pushl %ecx
0x002b0b49:	call VirtualProtect@kernel32
0x002b0b4f:	popl %esi
0x002b0b50:	popl %edi
0x002b0b51:	popl %ebx
0x002b0b52:	leave
0x002b0b53:	ret $0x4<UINT16>

0x002b0314:	nop
0x002b0315:	nop
0x002b0316:	nop
0x002b0317:	nop
0x002b0318:	nop
0x002b0319:	nop
0x002b031a:	pushl %edi
0x002b031b:	call 0x002b0a48
0x002b0a48:	pushl %ebp
0x002b0a49:	movl %ebp, %esp
0x002b0a4b:	addl %esp, $0xfffffffc<UINT8>
0x002b0a4e:	pushl %ebx
0x002b0a4f:	pushl %edi
0x002b0a50:	pushl %esi
0x002b0a51:	call 0x002b0a56
0x002b0a56:	popl %ebx
0x002b0a57:	subl %ebx, $0x10001c11<UINT32>
0x002b0a5d:	movl %eax, 0x8(%ebp)
0x002b0a60:	addl %eax, 0x3c(%eax)
0x002b0a63:	xorl %ecx, %ecx
0x002b0a65:	movw %cx, 0x14(%eax)
0x002b0a69:	leal %edi, 0x18(%ecx,%eax)
0x002b0a6d:	addl %edi, $0x27<UINT8>
0x002b0a70:	movl %ecx, %edi
0x002b0a72:	shrl %ecx, $0xc<UINT8>
0x002b0a75:	shll %ecx, $0xc<UINT8>
0x002b0a78:	pushl %ecx
0x002b0a79:	leal %eax, -4(%ebp)
0x002b0a7c:	pushl %eax
0x002b0a7d:	pushl $0x4<UINT8>
0x002b0a7f:	pushl $0x1000<UINT32>
0x002b0a84:	pushl %ecx
0x002b0a85:	call VirtualProtect@kernel32
0x002b0a8b:	movb %al, (%edi)
0x002b0a8d:	testb %al, $0xffffff80<UINT8>
0x002b0a8f:	je 4
0x002b0a91:	subb %al, $0xffffff80<UINT8>
0x002b0a93:	movb (%edi), %al
0x002b0a95:	popl %ecx
0x002b0a96:	leal %eax, -4(%ebp)
0x002b0a99:	pushl %eax
0x002b0a9a:	pushl -4(%ebp)
0x002b0a9d:	pushl $0x1000<UINT32>
0x002b0aa2:	pushl %ecx
0x002b0aa3:	call VirtualProtect@kernel32
0x002b0aa9:	popl %esi
0x002b0aaa:	popl %edi
0x002b0aab:	popl %ebx
0x002b0aac:	leave
0x002b0aad:	ret $0x4<UINT16>

0x002b0320:	pushl $0x8000<UINT32>
0x002b0325:	pushl $0x0<UINT8>
0x002b0327:	pushl 0x10002f27(%ebp)
0x002b032d:	call VirtualFree@kernel32.dll
VirtualFree@kernel32.dll: API Node	
0x002b0333:	movl %eax, 0xc(%esi)
0x002b0336:	addl %eax, %edi
0x002b0338:	popl %ebp
0x002b0339:	popl %esi
0x002b033a:	popl %edi
0x002b033b:	popl %ebx
0x002b033c:	ret

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
