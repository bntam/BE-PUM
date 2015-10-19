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
0x004048f6:	call 0x001e0188
0x001e0188:	pushl %ebx
0x001e0189:	pushl %edi
0x001e018a:	pushl %esi
0x001e018b:	pushl %ebp
0x001e018c:	call 0x001e0191
0x001e0191:	popl %ebp
0x001e0192:	subl %ebp, $0x1000134c<UINT32>
0x001e0198:	leal %esi, 0x10001343(%ebp)
0x001e019e:	movl %eax, -4(%esi)
0x001e01a1:	addl %eax, $0x4<UINT8>
0x001e01a4:	subl %esi, %eax
0x001e01a6:	cld
0x001e01a7:	movl %ebx, %esi
0x001e01a9:	movl %edx, 0x8(%esi)
0x001e01ac:	movl %esi, 0x1c(%esi)
0x001e01af:	addl %esi, %edx
0x001e01b1:	leal %edi, 0x10002f2f(%ebp)
0x001e01b7:	lodsl %eax, %ds:(%esi)
0x001e01b8:	stosl %es:(%edi), %eax
0x001e01b9:	lodsl %eax, %ds:(%esi)
0x001e01ba:	stosl %es:(%edi), %eax
0x001e01bb:	lodsl %eax, %ds:(%esi)
0x001e01bc:	stosl %es:(%edi), %eax
0x001e01bd:	lodsl %eax, %ds:(%esi)
0x001e01be:	stosl %es:(%edi), %eax
0x001e01bf:	nop
0x001e01c0:	cmpl 0x48(%ebx), $0x1<UINT8>
0x001e01c4:	je 21
0x001e01c6:	movl %esi, 0x44(%ebx)
0x001e01c9:	testl %esi, %esi
0x001e01cb:	je 14
0x001e01cd:	movl %ecx, $0x23<UINT32>
0x001e01d2:	addl %esi, %edx
0x001e01d4:	movl %edi, 0x40(%ebx)
0x001e01d7:	addl %edi, %edx
0x001e01d9:	rep movsb %es:(%edi), %ds:(%esi)
0x001e01db:	movl %esi, %ebx
0x001e01dd:	leal %edi, 0x10002f1b(%ebp)
0x001e01e3:	addl (%edi), %ebp
0x001e01e5:	addl 0x4(%edi), %ebp
0x001e01e8:	addl 0x8(%edi), %ebp
0x001e01eb:	leal %ecx, 0x10002eff(%ebp)
0x001e01f1:	pushl %ecx
0x001e01f2:	call 0x001e033d
0x001e033d:	pushl %ebp
0x001e033e:	movl %ebp, %esp
0x001e0340:	addl %esp, $0xfffffffc<UINT8>
0x001e0343:	pushl %ebx
0x001e0344:	pushl %edi
0x001e0345:	pushl %esi
0x001e0346:	call 0x001e034b
0x001e034b:	popl %ebx
0x001e034c:	subl %ebx, $0x10001506<UINT32>
0x001e0352:	movl %esi, 0x8(%ebp)
0x001e0355:	movl %ecx, (%esi)
0x001e0357:	addl %ecx, %ebx
0x001e0359:	pushl %ecx
0x001e035a:	call LoadLibraryA@kernel32.dll
LoadLibraryA@kernel32.dll: API Node	
0x001e0360:	movl -4(%ebp), %eax
0x001e0363:	movl %edx, 0x4(%esi)
0x001e0366:	movl %edi, 0x8(%esi)
0x001e0369:	addl %edx, %ebx
0x001e036b:	addl %edi, %ebx
0x001e036d:	xorl %eax, %eax
0x001e036f:	addl %eax, (%edx)
0x001e0371:	je 0x001e0389
0x001e0373:	pushl %edx
0x001e0374:	movl %eax, (%edx)
0x001e0376:	addl %eax, %ebx
0x001e0378:	pushl %eax
0x001e0379:	pushl -4(%ebp)
0x001e037c:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x001e0382:	stosl %es:(%edi), %eax
0x001e0383:	popl %edx
0x001e0384:	addl %edx, $0x4<UINT8>
0x001e0387:	jmp 0x001e036d
0x001e0389:	addl %esi, $0xc<UINT8>
0x001e038c:	addl %eax, (%esi)
0x001e038e:	jne 0x001e0355
0x001e0390:	popl %esi
0x001e0391:	popl %edi
0x001e0392:	popl %ebx
0x001e0393:	leave
0x001e0394:	ret $0x4<UINT16>

0x001e01f7:	nop
0x001e01f8:	nop
0x001e01f9:	nop
0x001e01fa:	nop
0x001e01fb:	nop
0x001e01fc:	nop
0x001e01fd:	nop
0x001e01fe:	nop
0x001e01ff:	movl %ecx, 0x2c(%esi)
0x001e0202:	movl 0x10002f2b(%ebp), %ecx
0x001e0208:	pushl $0x4<UINT8>
0x001e020a:	pushl $0x1000<UINT32>
0x001e020f:	pushl %ecx
0x001e0210:	pushl $0x0<UINT8>
0x001e0212:	call VirtualAlloc@kernel32.dll
0x001e0218:	movl 0x10002f27(%ebp), %eax
0x001e021e:	pushl %esi
0x001e021f:	call 0x001e061a
0x001e061a:	pushl %ebp
0x001e061b:	movl %ebp, %esp
0x001e061d:	addl %esp, $0xffffffe8<UINT8>
0x001e0620:	pushl %ebx
0x001e0621:	pushl %edi
0x001e0622:	pushl %esi
0x001e0623:	call 0x001e0628
0x001e0628:	popl %ebx
0x001e0629:	subl %ebx, $0x100017e3<UINT32>
0x001e062f:	movl %esi, 0x8(%ebp)
0x001e0632:	xorl %eax, %eax
0x001e0634:	xorl %ecx, %ecx
0x001e0636:	addl %ecx, 0x3c(%esi)
0x001e0639:	je 10
0x001e063b:	movl %edx, 0x8(%esi)
0x001e063e:	movl %edi, %esi
0x001e0640:	addl %esi, $0x50<UINT8>
0x001e0643:	jmp 0x001e064c
0x001e064c:	movl -4(%ebp), %eax
0x001e064f:	movzwl %eax, 0x2(%edi)
0x001e0653:	movl -16(%ebp), %eax
0x001e0656:	pushl %ecx
0x001e0657:	pushl %edx
0x001e0658:	pushl %esi
0x001e0659:	movzwl %eax, 0x10(%esi)
0x001e065d:	testl %eax, $0x10<UINT32>
0x001e0662:	je 0x001e073e
0x001e073e:	popl %esi
0x001e073f:	popl %edx
0x001e0740:	popl %ecx
0x001e0741:	addl %esi, $0x1c<UINT8>
0x001e0744:	decl %ecx
0x001e0745:	jne 0x001e0656
0x001e0668:	pushl %esi
0x001e0669:	movl %edi, 0x10002f27(%ebx)
0x001e066f:	movl -20(%ebp), %edi
0x001e0672:	movl %ecx, 0x8(%esi)
0x001e0675:	movl %eax, 0x14(%esi)
0x001e0678:	subl %ecx, %eax
0x001e067a:	movl %esi, (%esi)
0x001e067c:	addl %esi, %edx
0x001e067e:	movl %eax, %ecx
0x001e0680:	sarl %ecx, $0x2<UINT8>
0x001e0683:	rep movsl %es:(%edi), %ds:(%esi)
0x001e0685:	addl %ecx, %eax
0x001e0687:	andl %ecx, $0x3<UINT8>
0x001e068a:	rep movsb %es:(%edi), %ds:(%esi)
0x001e068c:	popl %esi
0x001e068d:	nop
0x001e068e:	nop
0x001e068f:	nop
0x001e0690:	nop
0x001e0691:	nop
0x001e0692:	nop
0x001e0693:	nop
0x001e0694:	nop
0x001e0695:	nop
0x001e0696:	nop
0x001e0697:	nop
0x001e0698:	nop
0x001e0699:	nop
0x001e069a:	nop
0x001e069b:	nop
0x001e069c:	nop
0x001e069d:	nop
0x001e069e:	nop
0x001e069f:	nop
0x001e06a0:	nop
0x001e06a1:	nop
0x001e06a2:	nop
0x001e06a3:	nop
0x001e06a4:	nop
0x001e06a5:	nop
0x001e06a6:	nop
0x001e06a7:	nop
0x001e06a8:	nop
0x001e06a9:	nop
0x001e06aa:	nop
0x001e06ab:	nop
0x001e06ac:	nop
0x001e06ad:	nop
0x001e06ae:	nop
0x001e06af:	nop
0x001e06b0:	nop
0x001e06b1:	nop
0x001e06b2:	nop
0x001e06b3:	movl %eax, 0x4(%esi)
0x001e06b6:	addl %eax, %edx
0x001e06b8:	movl -24(%ebp), %eax
0x001e06bb:	movl %eax, -16(%ebp)
0x001e06be:	decl %eax
0x001e06bf:	movl -12(%ebp), %eax
0x001e06c2:	pushl %edx
0x001e06c3:	pushl %eax
0x001e06c4:	pushl 0x8(%ebp)
0x001e06c7:	call 0x001e0a08
0x001e0a08:	pushl %ebp
0x001e0a09:	movl %ebp, %esp
0x001e0a0b:	addl %esp, $0xfffffffc<UINT8>
0x001e0a0e:	pushl %ebx
0x001e0a0f:	pushl %edi
0x001e0a10:	pushl %esi
0x001e0a11:	movl %ebx, 0x8(%ebp)
0x001e0a14:	movl %esi, %ebx
0x001e0a16:	movl %ecx, 0x30(%ebx)
0x001e0a19:	subl %esi, %ecx
0x001e0a1b:	movl -4(%ebp), %esi
0x001e0a1e:	xorl %ecx, %ecx
0x001e0a20:	lodsl %eax, %ds:(%esi)
0x001e0a21:	testl %eax, %eax
0x001e0a23:	je 28
0x001e0a25:	cmpl %ecx, 0xc(%ebp)
0x001e0a28:	je 0x001e0a2d
0x001e0a2d:	nop
0x001e0a2e:	nop
0x001e0a2f:	nop
0x001e0a30:	nop
0x001e0a31:	nop
0x001e0a32:	nop
0x001e0a33:	nop
0x001e0a34:	nop
0x001e0a35:	nop
0x001e0a36:	nop
0x001e0a37:	nop
0x001e0a38:	nop
0x001e0a39:	nop
0x001e0a3a:	nop
0x001e0a3b:	nop
0x001e0a3c:	nop
0x001e0a3d:	nop
0x001e0a3e:	addl %eax, -4(%ebp)
0x001e0a41:	popl %esi
0x001e0a42:	popl %edi
0x001e0a43:	popl %ebx
0x001e0a44:	leave
0x001e0a45:	ret $0x8<UINT16>

0x001e06cc:	leal %ecx, 0x10002f2f(%ebx)
0x001e06d2:	pushl %ecx
0x001e06d3:	pushl -24(%ebp)
0x001e06d6:	pushl -20(%ebp)
0x001e06d9:	call 0x001e0008
0x001e0008:	pusha
0x001e0009:	movl %esi, 0x24(%esp)
0x001e000d:	movl %edi, 0x28(%esp)
0x001e0011:	cld
0x001e0012:	lodsl %eax, %ds:(%esi)
0x001e0013:	xorl %ecx, %ecx
0x001e0015:	testl %eax, %eax
0x001e0017:	je 17
0x001e0019:	xorl %edx, %edx
0x001e001b:	leal %ebx, (%eax,%edi)
0x001e001e:	movsb %es:(%edi), %ds:(%esi)
0x001e001f:	movb %cl, $0x3<UINT8>
0x001e0021:	call 0x001e0098
0x001e0098:	addl %edx, %edx
0x001e009a:	jne 0x001e00a2
0x001e009c:	xchgl %edx, %eax
0x001e009d:	lodsl %eax, %ds:(%esi)
0x001e009e:	xchgl %edx, %eax
0x001e009f:	addl %edx, %edx
0x001e00a1:	incl %edx
0x001e00a2:	ret

0x001e0026:	jae 0x001e001e
0x001e0028:	cmpl %edi, %ebx
0x001e002a:	jae 0x001e00b5
0x001e0030:	pushl %ebx
0x001e0031:	pushl %ebp
0x001e0032:	pushl %edi
0x001e0033:	xorl %ebx, %ebx
0x001e0035:	incl %ebx
0x001e0036:	xorl %ebp, %ebp
0x001e0038:	movl %eax, %ebx
0x001e003a:	leal %edi, (%ebp,%ebx)
0x001e003e:	movl %ebp, %ebx
0x001e0040:	movl %ebx, %edi
0x001e0042:	call 0x001e0098
0x001e0047:	jae 0x001e003a
0x001e0049:	leal %ebx, (%ebp,%edi)
0x001e004d:	addl %eax, %edi
0x001e004f:	movl %ebp, %edi
0x001e0051:	call 0x001e0098
0x001e0056:	jae 0x001e003a
0x001e0058:	popl %edi
0x001e0059:	popl %ebp
0x001e005a:	popl %ebx
0x001e005b:	subl %eax, %ecx
0x001e005d:	jae 0x001e0068
0x001e0068:	movb %cl, $0x6<UINT8>
0x001e006a:	call 0x001e0098
0x001e006f:	adcl %eax, %eax
0x001e0071:	decl %ecx
0x001e0072:	jne 0x001e006a
0x001e0074:	incl %eax
0x001e0075:	call 0x001e00a3
0x001e00a3:	xorl %ecx, %ecx
0x001e00a5:	incl %ecx
0x001e00a6:	call 0x001e0098
0x001e00ab:	adcl %ecx, %ecx
0x001e00ad:	call 0x001e0098
0x001e00b2:	jb 0x001e00a6
0x001e00b4:	ret

0x001e007a:	movl %ebp, %eax
0x001e007c:	cmpl %eax, $0x8001<UINT32>
0x001e0081:	sbbl %ecx, $0xffffffff<UINT8>
0x001e0084:	cmpl %eax, $0x781<UINT32>
0x001e0089:	sbbl %ecx, $0xffffffff<UINT8>
0x001e008c:	pushl %esi
0x001e008d:	movl %esi, %edi
0x001e008f:	subl %esi, %eax
0x001e0091:	rep movsb %es:(%edi), %ds:(%esi)
0x001e0093:	popl %esi
0x001e0094:	incl %ecx
0x001e0095:	incl %ecx
0x001e0096:	jmp 0x001e0021
0x001e005f:	movl %eax, %ebp
0x001e0061:	call 0x001e00a3
0x001e0066:	jmp 0x001e008c
0x001e00b5:	subl %edi, 0x28(%esp)
0x001e00b9:	movl 0x1c(%esp), %edi
0x001e00bd:	popa
0x001e00be:	ret $0xc<UINT16>

0x001e06db:	movl %ecx, %eax
0x001e06dd:	incl %eax
0x001e06de:	je 116
0x001e06e0:	xorl %eax, %eax
0x001e06e2:	addl %eax, -12(%ebp)
0x001e06e5:	je 0x001e06f9
0x001e06f9:	popl %edx
0x001e06fa:	pushl %esi
0x001e06fb:	movl %edi, 0x4(%esi)
0x001e06fe:	addl %edi, %edx
0x001e0700:	cmpl %edi, -24(%ebp)
0x001e0703:	jne 4
0x001e0705:	addl %edi, %ecx
0x001e0707:	jmp 0x001e071a
0x001e071a:	movl %eax, %edi
0x001e071c:	addl %eax, $0xfff<UINT32>
0x001e0721:	shrl %eax, $0xc<UINT8>
0x001e0724:	shll %eax, $0xc<UINT8>
0x001e0727:	subl %eax, %edi
0x001e0729:	movl %ecx, %eax
0x001e072b:	xorl %eax, %eax
0x001e072d:	pushl %edx
0x001e072e:	movl %edx, %ecx
0x001e0730:	sarl %ecx, $0x2<UINT8>
0x001e0733:	rep stosl %es:(%edi), %eax
0x001e0735:	addl %ecx, %edx
0x001e0737:	andl %ecx, $0x3<UINT8>
0x001e073a:	rep stosb %es:(%edi), %al
0x001e073c:	popl %edx
0x001e073d:	popl %esi
0x001e074b:	xorl %eax, %eax
0x001e074d:	popl %esi
0x001e074e:	popl %edi
0x001e074f:	popl %ebx
0x001e0750:	leave
0x001e0751:	ret $0x4<UINT16>

0x001e0224:	leal %ecx, 0x10002dbd(%ebp)
0x001e022a:	testl %eax, %eax
0x001e022c:	jne 148
0x001e0232:	pushl %esi
0x001e0233:	call 0x001e0578
0x001e0578:	pushl %ebp
0x001e0579:	movl %ebp, %esp
0x001e057b:	addl %esp, $0xffffffe8<UINT8>
0x001e057e:	pushl %ebx
0x001e057f:	pushl %edi
0x001e0580:	pushl %esi
0x001e0581:	call 0x001e0586
0x001e0586:	popl %ebx
0x001e0587:	subl %ebx, $0x10001741<UINT32>
0x001e058d:	movl %esi, 0x8(%ebp)
0x001e0590:	xorl %eax, %eax
0x001e0592:	xorl %ecx, %ecx
0x001e0594:	addl %ecx, 0x3c(%esi)
0x001e0597:	je 10
0x001e0599:	movl %edx, 0x8(%esi)
0x001e059c:	movl %edi, %esi
0x001e059e:	addl %esi, $0x50<UINT8>
0x001e05a1:	jmp 0x001e05aa
0x001e05aa:	movl %eax, 0x10002f27(%ebx)
0x001e05b0:	movl -4(%ebp), %eax
0x001e05b3:	movl %ebx, %esi
0x001e05b5:	movzwl %eax, 0x10(%ebx)
0x001e05b9:	testl %eax, $0x2<UINT32>
0x001e05be:	je 0x001e060d
0x001e05c0:	pushl %ecx
0x001e05c1:	movl %esi, 0x4(%ebx)
0x001e05c4:	movl %edi, -4(%ebp)
0x001e05c7:	movl %ecx, 0x8(%ebx)
0x001e05ca:	addl %esi, %edx
0x001e05cc:	movl %eax, %ecx
0x001e05ce:	sarl %ecx, $0x2<UINT8>
0x001e05d1:	rep movsl %es:(%edi), %ds:(%esi)
0x001e05d3:	addl %ecx, %eax
0x001e05d5:	andl %ecx, $0x3<UINT8>
0x001e05d8:	rep movsb %es:(%edi), %ds:(%esi)
0x001e05da:	movl %edi, 0x4(%ebx)
0x001e05dd:	movl %ecx, 0x8(%ebx)
0x001e05e0:	addl %edi, %edx
0x001e05e2:	xorl %eax, %eax
0x001e05e4:	pushl %edx
0x001e05e5:	movl %edx, %ecx
0x001e05e7:	sarl %ecx, $0x2<UINT8>
0x001e05ea:	rep stosl %es:(%edi), %eax
0x001e05ec:	addl %ecx, %edx
0x001e05ee:	andl %ecx, $0x3<UINT8>
0x001e05f1:	rep stosb %es:(%edi), %al
0x001e05f3:	popl %edx
0x001e05f4:	movl %esi, -4(%ebp)
0x001e05f7:	movl %edi, (%ebx)
0x001e05f9:	addl %edi, %edx
0x001e05fb:	movl %ecx, 0x8(%ebx)
0x001e05fe:	movl %eax, %ecx
0x001e0600:	sarl %ecx, $0x2<UINT8>
0x001e0603:	rep movsl %es:(%edi), %ds:(%esi)
0x001e0605:	addl %ecx, %eax
0x001e0607:	andl %ecx, $0x3<UINT8>
0x001e060a:	rep movsb %es:(%edi), %ds:(%esi)
0x001e060c:	popl %ecx
0x001e060d:	addl %ebx, $0x1c<UINT8>
0x001e0610:	decl %ecx
0x001e0611:	jne 0x001e05b5
0x001e0613:	popl %esi
0x001e0614:	popl %edi
0x001e0615:	popl %ebx
0x001e0616:	leave
0x001e0617:	ret $0x4<UINT16>

0x001e0238:	pushl %esi
0x001e0239:	call 0x001e0493
0x001e0493:	pushl %ebp
0x001e0494:	movl %ebp, %esp
0x001e0496:	addl %esp, $0xffffffe8<UINT8>
0x001e0499:	pushl %ebx
0x001e049a:	pushl %edi
0x001e049b:	pushl %esi
0x001e049c:	call 0x001e04a1
0x001e04a1:	popl %ebx
0x001e04a2:	subl %ebx, $0x1000165c<UINT32>
0x001e04a8:	movl %esi, 0x8(%ebp)
0x001e04ab:	xorl %eax, %eax
0x001e04ad:	xorl %ecx, %ecx
0x001e04af:	addl %ecx, 0x3c(%esi)
0x001e04b2:	je 10
0x001e04b4:	movl %edx, 0x8(%esi)
0x001e04b7:	movl %edi, %esi
0x001e04b9:	addl %esi, $0x50<UINT8>
0x001e04bc:	jmp 0x001e04c5
0x001e04c5:	movl -8(%ebp), %edx
0x001e04c8:	movzwl %eax, 0x10(%esi)
0x001e04cc:	testl %eax, $0x200<UINT32>
0x001e04d1:	jne 125
0x001e04d3:	testl %eax, $0x8<UINT32>
0x001e04d8:	je 0x001e0567
0x001e0567:	addl %esi, $0x1c<UINT8>
0x001e056a:	decl %ecx
0x001e056b:	jne 0x001e04c8
0x001e04de:	pushl %ecx
0x001e04df:	pushl %esi
0x001e04e0:	movl %edi, 0x8(%esi)
0x001e04e3:	xorl %ecx, %ecx
0x001e04e5:	movl -4(%ebp), %ecx
0x001e04e8:	movzwl %ebx, 0x12(%esi)
0x001e04ec:	movl %esi, (%esi)
0x001e04ee:	addl %esi, -8(%ebp)
0x001e04f1:	cmpl %ecx, %edi
0x001e04f3:	jnl 0x001e0529
0x001e04f5:	movl %eax, (%esi)
0x001e04f7:	incl %esi
0x001e04f8:	movzbl %edx, %al
0x001e04fb:	addl -4(%ebp), %edx
0x001e04fe:	subb %al, $0xffffffe8<UINT8>
0x001e0500:	movl %edx, %ebx
0x001e0502:	je 0x001e050a
0x001e0504:	decb %al
0x001e0506:	movb %dl, %bh
0x001e0508:	jne 0x001e0526
0x001e0526:	incl %ecx
0x001e0527:	jmp 0x001e04f1
0x001e050a:	movl %eax, (%esi)
0x001e050c:	cmpb %al, %dl
0x001e050e:	jne 13
0x001e0510:	shrw %ax, $0x8<UINT8>
0x001e0514:	roll %eax, $0x10<UINT8>
0x001e0517:	xchgb %ah, %al
0x001e0519:	subl %eax, %ecx
0x001e051b:	movl (%esi), %eax
0x001e051d:	addl -4(%ebp), %eax
0x001e0520:	addl %esi, $0x4<UINT8>
0x001e0523:	addl %ecx, $0x4<UINT8>
0x001e0529:	popl %esi
0x001e052a:	popl %ecx
0x001e052b:	xorl %eax, %eax
0x001e052d:	addl %eax, 0x14(%esi)
0x001e0530:	je 53
0x001e0532:	cmpl %eax, -4(%ebp)
0x001e0535:	je 0x001e0567
0x001e0571:	popl %esi
0x001e0572:	popl %edi
0x001e0573:	popl %ebx
0x001e0574:	leave
0x001e0575:	ret $0x4<UINT16>

0x001e023e:	nop
0x001e023f:	nop
0x001e0240:	nop
0x001e0241:	nop
0x001e0242:	nop
0x001e0243:	nop
0x001e0244:	nop
0x001e0245:	nop
0x001e0246:	nop
0x001e0247:	nop
0x001e0248:	nop
0x001e0249:	nop
0x001e024a:	nop
0x001e024b:	nop
0x001e024c:	movl %ecx, 0x34(%esi)
0x001e024f:	testl %ecx, %ecx
0x001e0251:	je 137
0x001e0257:	addl %ecx, 0x8(%esi)
0x001e025a:	pushl %ecx
0x001e025b:	pushl %esi
0x001e025c:	call 0x001e08a8
0x001e08a8:	pushl %ebp
0x001e08a9:	movl %ebp, %esp
0x001e08ab:	pushl %ebx
0x001e08ac:	pushl %edi
0x001e08ad:	pushl %esi
0x001e08ae:	movl %esi, 0xc(%ebp)
0x001e08b1:	movl %ebx, 0x8(%ebp)
0x001e08b4:	xorl %eax, %eax
0x001e08b6:	cmpl 0x10(%esi), %eax
0x001e08b9:	jne 0x001e08bf
0x001e08bf:	addl %eax, (%esi)
0x001e08c1:	je 3
0x001e08c3:	addl %eax, 0x8(%ebx)
0x001e08c6:	movl %ecx, 0xc(%esi)
0x001e08c9:	addl %ecx, 0x8(%ebx)
0x001e08cc:	movl %edi, 0x10(%esi)
0x001e08cf:	testl %edi, %edi
0x001e08d1:	je 3
0x001e08d3:	addl %edi, 0x8(%ebx)
0x001e08d6:	pushl %eax
0x001e08d7:	pushl %edi
0x001e08d8:	pushl %ecx
0x001e08d9:	pushl %ebx
0x001e08da:	call 0x001e08ef
0x001e08ef:	pushl %ebp
0x001e08f0:	movl %ebp, %esp
0x001e08f2:	addl %esp, $0xffffffe8<UINT8>
0x001e08f5:	pushl %ebx
0x001e08f6:	pushl %edi
0x001e08f7:	pushl %esi
0x001e08f8:	call 0x001e08fd
0x001e08fd:	popl %ebx
0x001e08fe:	subl %ebx, $0x10001ab8<UINT32>
0x001e0904:	movl %eax, 0xc(%ebp)
0x001e0907:	movl 0x10002d9c(%ebx), %eax
0x001e090d:	xorl %eax, %eax
0x001e090f:	movl 0x10002da0(%ebx), %eax
0x001e0915:	xorl %esi, %esi
0x001e0917:	incl %esi
0x001e0918:	incl %esi
0x001e0919:	movl %edx, 0x10002eef(%ebx)
0x001e091f:	pushl 0xc(%ebp)
0x001e0922:	call GetModuleHandleA@kernel32.dll
GetModuleHandleA@kernel32: API Node	
0x001e0924:	movl -4(%ebp), %eax
0x001e0927:	movl %edx, 0x10002f1b(%ebx)
0x001e092d:	testl %eax, %eax
0x001e092f:	jne 0x001e093e
0x001e093e:	nop
0x001e093f:	nop
0x001e0940:	nop
0x001e0941:	nop
0x001e0942:	nop
0x001e0943:	nop
0x001e0944:	nop
0x001e0945:	nop
0x001e0946:	nop
0x001e0947:	nop
0x001e0948:	nop
0x001e0949:	nop
0x001e094a:	nop
0x001e094b:	nop
0x001e094c:	nop
0x001e094d:	nop
0x001e094e:	nop
0x001e094f:	nop
0x001e0950:	nop
0x001e0951:	nop
0x001e0952:	nop
0x001e0953:	nop
0x001e0954:	nop
0x001e0955:	nop
0x001e0956:	nop
0x001e0957:	nop
0x001e0958:	nop
0x001e0959:	nop
0x001e095a:	nop
0x001e095b:	nop
0x001e095c:	nop
0x001e095d:	nop
0x001e095e:	nop
0x001e095f:	nop
0x001e0960:	nop
0x001e0961:	nop
0x001e0962:	nop
0x001e0963:	nop
0x001e0964:	nop
0x001e0965:	nop
0x001e0966:	nop
0x001e0967:	nop
0x001e0968:	movl %esi, 0x10(%ebp)
0x001e096b:	movl %edi, 0x8(%ebp)
0x001e096e:	movl %edx, 0x14(%ebp)
0x001e0971:	testl %edx, %edx
0x001e0973:	jne 0x001e0977
0x001e0977:	testl %esi, %esi
0x001e0979:	jne 0x001e097d
0x001e097d:	movl 0x10002da0(%ebx), $0x0<UINT32>
0x001e0987:	movl %eax, (%edx)
0x001e0989:	testl %eax, %eax
0x001e098b:	je 0x001e09d1
0x001e098d:	pushl %edx
0x001e098e:	movl 0x10002da0(%ebx), %eax
0x001e0994:	testl %eax, $0x80000000<UINT32>
0x001e0999:	je 0x001e09a4
0x001e09a4:	movl %ecx, 0x8(%ebp)
0x001e09a7:	addl %eax, 0x8(%ecx)
0x001e09aa:	xorl %ecx, %ecx
0x001e09ac:	movw %cx, (%eax)
0x001e09af:	pushl %ecx
0x001e09b0:	incl %eax
0x001e09b1:	incl %eax
0x001e09b2:	pushl %eax
0x001e09b3:	pushl -4(%ebp)
0x001e09b6:	call 0x001e0ad2
0x001e0ad2:	pushl %ebp
0x001e0ad3:	movl %ebp, %esp
0x001e0ad5:	pushl %ebx
0x001e0ad6:	pushl %edi
0x001e0ad7:	pushl %esi
0x001e0ad8:	call 0x001e0add
0x001e0add:	popl %ebx
0x001e0ade:	subl %ebx, $0x10001c98<UINT32>
0x001e0ae4:	pushl 0xc(%ebp)
0x001e0ae7:	pushl 0x8(%ebp)
0x001e0aea:	call GetProcAddress@kernel32.dll
0x001e0af0:	popl %esi
0x001e0af1:	popl %edi
0x001e0af2:	popl %ebx
0x001e0af3:	leave
0x001e0af4:	ret $0xc<UINT16>

0x001e09bc:	popl %edx
0x001e09bd:	testl %eax, %eax
0x001e09bf:	je -145
0x001e09c5:	movl (%esi), %eax
0x001e09c7:	movl (%edx), %eax
0x001e09c9:	addl %edx, $0x4<UINT8>
0x001e09cc:	addl %esi, $0x4<UINT8>
0x001e09cf:	jmp 0x001e097d
0x001e09d1:	xorl %eax, %eax
0x001e09d3:	popl %esi
0x001e09d4:	popl %edi
0x001e09d5:	popl %ebx
0x001e09d6:	leave
0x001e09d7:	ret $0x10<UINT16>

0x001e08df:	incl %eax
0x001e08e0:	jne 0x001e08ea
0x001e08ea:	addl %esi, $0x14<UINT8>
0x001e08ed:	jmp 0x001e08b4
GetModuleHandleA@kernel32.dll: API Node	
0x001e08bb:	cmpl (%esi), %eax
0x001e08bd:	je 0x001e08e3
0x001e08e3:	popl %esi
0x001e08e4:	popl %edi
0x001e08e5:	popl %ebx
0x001e08e6:	leave
0x001e08e7:	ret $0x8<UINT16>

0x001e0261:	testl %eax, %eax
0x001e0263:	je 0x001e02e0
0x001e02e0:	movl %edi, 0x8(%ebx)
0x001e02e3:	movl %ebx, %esi
0x001e02e5:	cmpl 0x48(%ebx), $0x1<UINT8>
0x001e02e9:	jne 0x001e0300
0x001e0300:	movl %esi, %ebx
0x001e0302:	nop
0x001e0303:	nop
0x001e0304:	nop
0x001e0305:	nop
0x001e0306:	nop
0x001e0307:	nop
0x001e0308:	nop
0x001e0309:	nop
0x001e030a:	nop
0x001e030b:	nop
0x001e030c:	nop
0x001e030d:	nop
0x001e030e:	pushl %esi
0x001e030f:	call 0x001e0af7
0x001e0af7:	pushl %ebp
0x001e0af8:	movl %ebp, %esp
0x001e0afa:	addl %esp, $0xfffffffc<UINT8>
0x001e0afd:	pushl %ebx
0x001e0afe:	pushl %edi
0x001e0aff:	pushl %esi
0x001e0b00:	call 0x001e0b05
0x001e0b05:	popl %ebx
0x001e0b06:	subl %ebx, $0x10001cc0<UINT32>
0x001e0b0c:	movl %esi, 0x8(%ebp)
0x001e0b0f:	movl %eax, 0x8(%esi)
0x001e0b12:	addl %eax, 0x3c(%eax)
0x001e0b15:	leal %edi, 0x80(%eax)
0x001e0b1b:	movl %ecx, %edi
0x001e0b1d:	shrl %ecx, $0xc<UINT8>
0x001e0b20:	shll %ecx, $0xc<UINT8>
0x001e0b23:	pushl %ecx
0x001e0b24:	leal %eax, -4(%ebp)
0x001e0b27:	pushl %eax
0x001e0b28:	pushl $0x4<UINT8>
0x001e0b2a:	pushl $0x1000<UINT32>
0x001e0b2f:	pushl %ecx
0x001e0b30:	call VirtualProtect@kernel32
VirtualProtect@kernel32: API Node	
0x001e0b36:	movl %edx, 0x34(%esi)
0x001e0b39:	movl (%edi), %edx
0x001e0b3b:	popl %ecx
0x001e0b3c:	leal %eax, -4(%ebp)
0x001e0b3f:	pushl %eax
0x001e0b40:	pushl -4(%ebp)
0x001e0b43:	pushl $0x1000<UINT32>
0x001e0b48:	pushl %ecx
0x001e0b49:	call VirtualProtect@kernel32
0x001e0b4f:	popl %esi
0x001e0b50:	popl %edi
0x001e0b51:	popl %ebx
0x001e0b52:	leave
0x001e0b53:	ret $0x4<UINT16>

0x001e0314:	nop
0x001e0315:	nop
0x001e0316:	nop
0x001e0317:	nop
0x001e0318:	nop
0x001e0319:	nop
0x001e031a:	pushl %edi
0x001e031b:	call 0x001e0a48
0x001e0a48:	pushl %ebp
0x001e0a49:	movl %ebp, %esp
0x001e0a4b:	addl %esp, $0xfffffffc<UINT8>
0x001e0a4e:	pushl %ebx
0x001e0a4f:	pushl %edi
0x001e0a50:	pushl %esi
0x001e0a51:	call 0x001e0a56
0x001e0a56:	popl %ebx
0x001e0a57:	subl %ebx, $0x10001c11<UINT32>
0x001e0a5d:	movl %eax, 0x8(%ebp)
0x001e0a60:	addl %eax, 0x3c(%eax)
0x001e0a63:	xorl %ecx, %ecx
0x001e0a65:	movw %cx, 0x14(%eax)
0x001e0a69:	leal %edi, 0x18(%ecx,%eax)
0x001e0a6d:	addl %edi, $0x27<UINT8>
0x001e0a70:	movl %ecx, %edi
0x001e0a72:	shrl %ecx, $0xc<UINT8>
0x001e0a75:	shll %ecx, $0xc<UINT8>
0x001e0a78:	pushl %ecx
0x001e0a79:	leal %eax, -4(%ebp)
0x001e0a7c:	pushl %eax
0x001e0a7d:	pushl $0x4<UINT8>
0x001e0a7f:	pushl $0x1000<UINT32>
0x001e0a84:	pushl %ecx
0x001e0a85:	call VirtualProtect@kernel32
0x001e0a8b:	movb %al, (%edi)
0x001e0a8d:	testb %al, $0xffffff80<UINT8>
0x001e0a8f:	je 4
0x001e0a91:	subb %al, $0xffffff80<UINT8>
0x001e0a93:	movb (%edi), %al
0x001e0a95:	popl %ecx
0x001e0a96:	leal %eax, -4(%ebp)
0x001e0a99:	pushl %eax
0x001e0a9a:	pushl -4(%ebp)
0x001e0a9d:	pushl $0x1000<UINT32>
0x001e0aa2:	pushl %ecx
0x001e0aa3:	call VirtualProtect@kernel32
0x001e0aa9:	popl %esi
0x001e0aaa:	popl %edi
0x001e0aab:	popl %ebx
0x001e0aac:	leave
0x001e0aad:	ret $0x4<UINT16>

0x001e0320:	pushl $0x8000<UINT32>
0x001e0325:	pushl $0x0<UINT8>
0x001e0327:	pushl 0x10002f27(%ebp)
0x001e032d:	call VirtualFree@kernel32.dll
VirtualFree@kernel32.dll: API Node	
0x001e0333:	movl %eax, 0xc(%esi)
0x001e0336:	addl %eax, %edi
0x001e0338:	popl %ebp
0x001e0339:	popl %esi
0x001e033a:	popl %edi
0x001e033b:	popl %ebx
0x001e033c:	ret

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
