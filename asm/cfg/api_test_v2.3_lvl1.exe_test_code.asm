0x004ef000:	pushl %esi
0x004ef001:	pushl %eax
0x004ef002:	pushl %ebx
0x004ef003:	call 0x004ef009
0x004ef009:	popl %eax
0x004ef00a:	movl %ebx, %eax
0x004ef00c:	incl %eax
0x004ef00d:	subl %eax, $0xe9000<UINT32>
0x004ef012:	subl %eax, $0x100c6b00<UINT32>
0x004ef017:	addl %eax, $0x100c6af7<UINT32>
0x004ef01c:	cmpb (%ebx), $0xffffffcc<UINT8>
0x004ef01f:	jne 25
0x004ef021:	movb (%ebx), $0x0<UINT8>
0x004ef024:	movl %ebx, $0x1000<UINT32>
0x004ef029:	pushl $0x1d9b6636<UINT32>
0x004ef02e:	pushl $0x1ed1ecc6<UINT32>
0x004ef033:	pushl %ebx
0x004ef034:	pushl %eax
0x004ef035:	call 0x004ef044
0x004ef044:	pushl %ebp
0x004ef045:	movl %ebp, %esp
0x004ef047:	pushl %eax
0x004ef048:	pushl %ebx
0x004ef049:	pushl %ecx
0x004ef04a:	pushl %esi
0x004ef04b:	movl %esi, 0x8(%ebp)
0x004ef04e:	movl %ecx, 0xc(%ebp)
0x004ef051:	shrl %ecx, $0x2<UINT8>
0x004ef054:	movl %eax, 0x10(%ebp)
0x004ef057:	movl %ebx, 0x14(%ebp)
0x004ef05a:	testl %ecx, %ecx
0x004ef05c:	je 0x004ef068
0x004ef05e:	xorl (%esi), %eax
0x004ef060:	addl (%esi), %ebx
0x004ef062:	addl %esi, $0x4<UINT8>
0x004ef065:	decl %ecx
0x004ef066:	jmp 0x004ef05a
0x004ef068:	popl %esi
0x004ef069:	popl %ecx
0x004ef06a:	popl %ebx
0x004ef06b:	popl %eax
0x004ef06c:	leave
0x004ef06d:	ret $0x10<UINT16>

0x004ef03a:	addl %eax, $0x14<UINT8>
0x004ef03d:	movl 0x8(%esp), %eax
0x004ef041:	popl %ebx
0x004ef042:	popl %eax
0x004ef043:	ret

0x00406014:	jmp 0x00407751
0x00407751:	movl %eax, %ebp
0x00407753:	movl %edx, %esp
0x00407755:	pusha
0x00407756:	call 0x0040775b
0x0040775b:	popl %ebp
0x0040775c:	subl %ebp, $0x8f61747<UINT32>
0x00407762:	movl 0x8f60009(%ebp), %edx
0x00407768:	movl 0x8f614bd(%ebp), %esi
0x0040776e:	movl 0x8f60535(%ebp), %eax
0x00407774:	movl 0x8f60011(%ebp), %ebx
0x0040777a:	movl 0x8f607e5(%ebp), %edi
0x00407780:	movl %eax, $0x0<UINT32>
0x00407785:	testl %eax, %eax
0x00407787:	je 0x00407796
0x00407796:	movl %eax, 0x24(%esp)
0x0040779a:	cmpl 0x8f60275(%ebp), $0x0<UINT8>
0x004077a1:	je 0x004077a9
0x004077a9:	cmpl 0x8f604e5(%ebp), $0x0<UINT8>
0x004077b0:	je 0x004077be
0x004077be:	pushl $0x45<UINT8>
0x004077c0:	call 0x00407868
0x00407868:	pushl %ebp
0x00407869:	movl %ebp, %esp
0x0040786b:	subl %esp, $0x28<UINT8>
0x0040786e:	pusha
0x0040786f:	call 0x00407874
0x00407874:	popl %edx
0x00407875:	subl %edx, $0x8f61860<UINT32>
0x0040787b:	movl %ebx, %edx
0x0040787d:	movl -4(%ebp), $0x0<UINT32>
0x00407884:	movl %eax, -4(%ebp)
0x00407887:	incl %eax
0x00407888:	movl -4(%ebp), %eax
0x0040788b:	cmpl -4(%ebp), $0x80<UINT32>
0x00407892:	je 0x004078a3
0x00407894:	movl %eax, 0x8(%ebp)
0x00407897:	movl 0x8f617ca(%ebx), %eax
0x0040789d:	incl 0x8(%ebp)
0x004078a0:	incl %ebx
0x004078a1:	jmp 0x00407884
0x004078a3:	movl -8(%ebp), %eax
0x004078a6:	popa
0x004078a7:	movl %eax, -8(%ebp)
0x004078aa:	leave
0x004078ab:	ret $0x4<UINT16>

0x004077c5:	pushl $0x783749a<UINT32>
0x004077ca:	call 0x004078ae
0x004078ae:	pushl %ebp
0x004078af:	movl %ebp, %esp
0x004078b1:	subl %esp, $0x84<UINT32>
0x004078b7:	pusha
0x004078b8:	call 0x004078bd
0x004078bd:	popl %edx
0x004078be:	subl %edx, $0x8f618a9<UINT32>
0x004078c4:	leal %eax, -132(%ebp)
0x004078ca:	movl %ebx, 0x8(%ebp)
0x004078cd:	movl -4(%ebp), $0x0<UINT32>
0x004078d4:	movl %ecx, -4(%ebp)
0x004078d7:	roll %ebx
0x004078d9:	movb (%eax), %bl
0x004078db:	incl %ecx
0x004078dc:	movl -4(%ebp), %ecx
0x004078df:	cmpl -4(%ebp), $0x80<UINT32>
0x004078e6:	jne 0x004078d4
0x004078e8:	movl -4(%ebp), $0x0<UINT32>
0x004078ef:	leal %edi, 0x8f617ca(%edx)
0x004078f5:	leal %esi, -132(%ebp)
0x004078fb:	movb %cl, (%esi)
0x004078fd:	movl %ebx, $0x1f4<UINT32>
0x00407902:	movl %eax, $0x785437ab<UINT32>
0x00407907:	rcll %eax, %cl
0x00407909:	movb %cl, (%edi)
0x0040790b:	rcll %eax, %cl
0x0040790d:	decl %ebx
0x0040790e:	jne 0x00407907
0x00407910:	imull %eax, %ebx
0x00407913:	incl %edi
0x00407914:	incl %esi
0x00407915:	movl %ecx, -4(%ebp)
0x00407918:	incl %ecx
0x00407919:	movl -4(%ebp), %ecx
0x0040791c:	cmpl %ecx, $0x80<UINT32>
0x00407922:	jne 0x004078fb
0x00407924:	popa
0x00407925:	leave
0x00407926:	ret $0x4<UINT16>

0x004077cf:	pushl $0xa894b25<UINT32>
0x004077d4:	call 0x004078ae
0x004077d9:	jmp 0x00407929
0x00407929:	movl %ecx, $0x7000<UINT32>
0x0040792e:	leal %edi, 0x8f61926(%ebp)
0x00407934:	decb (%edi)
0x00407936:	incl %edi
0x00407937:	decl %ecx
0x00407938:	jne 0x00407934
0x0040793a:	movl %eax, $0x48692121<UINT32>
0x0040793f:	movl %edx, $0xf0006000<UINT32>
0x00407944:	subl %edx, $0xf0000000<UINT32>
0x0040794a:	leal %eax, 0x8f61a60(%ebp)
0x00407950:	pushl %eax
0x00407951:	pushl %fs:0
0x00407958:	movl %fs:0, %esp
0x0040795f:	call 0x00407964
0x00407964:	popl %eax
0x00407965:	clc
0x00407966:	movl %ebx, %edx
0x00407968:	subl %eax, %edx
0x0040796a:	movw %bx, $0xfffff345<UINT16>
0x0040796e:	andl %eax, $0xfffff000<UINT32>
0x00407973:	movl %ecx, %edx
0x00407975:	xorl %edi, %edi
0x00407977:	cmpw (%eax), $0x5a4d<UINT16>
0x0040797c:	jne 0x0040798c
0x0040798c:	subl %eax, $0x1000<UINT32>
0x00407991:	incl %edi
0x00407992:	jmp 0x00407977
0x0040797e:	movzwl %edx, 0x3c(%eax)
0x00407982:	addl %edx, %eax
0x00407984:	cmpl (%edx), $0x4550<UINT32>
0x0040798a:	je 0x00407994
0x00407994:	movl 0x8f60551(%ebp), %eax
0x0040799a:	popl %fs:0
0x004079a1:	addl %esp, $0x4<UINT8>
0x004079a4:	leal %edx, 0x8f61a73(%ebp)
0x004079aa:	pushl %edx
0x004079ab:	pushl %fs:0
0x004079b2:	movl %fs:0, %esp
0x004079b9:	movl %edx, %eax
0x004079bb:	addl %eax, %ecx
0x004079bd:	subl %eax, $0x1000<UINT32>
0x004079c2:	movl %esi, $0x1<UINT32>
0x004079c7:	testl %esi, %esi
0x004079c9:	je 44
0x004079cb:	movl %esi, $0x506d<UINT32>
0x004079d0:	testl %esi, %esi
0x004079d2:	jne 0x004079df
0x004079df:	addl %esi, %edx
0x004079e1:	movl %esi, 0x10(%esi)
0x004079e4:	addl %esi, %edx
0x004079e6:	movl %ecx, %esi
0x004079e8:	addl %ecx, $0x8<UINT8>
0x004079eb:	movl 0x8f60825(%ebp), %ecx
0x004079f1:	movl %esi, (%esi)
0x004079f3:	movl %ecx, %esi
0x004079f5:	jmp 0x00407a03
0x00407a03:	andl %esi, $0xffff0000<UINT32>
0x00407a09:	cmpl %esi, $0x80000000<UINT32>
0x00407a0f:	jbe 0x00407a42
0x00407a42:	xorl %eax, %eax
0x00407a44:	cmpl %eax, $0x32<UINT8>
0x00407a47:	je 31
0x00407a49:	cmpw (%esi), $0x5a4d<UINT16>
0x00407a4e:	je 0x00407a59
0x00407a59:	movl %edi, 0x3c(%esi)
0x00407a5c:	addl %edi, %esi
0x00407a5e:	cmpl (%edi), $0x4550<UINT32>
0x00407a64:	je 0x00407aaa
0x00407aaa:	xchgl %esi, %eax
0x00407aab:	popl %fs:0
0x00407ab2:	addl %esp, $0x4<UINT8>
0x00407ab5:	jmp 0x00407ac0
0x00407ac0:	pushl %ebx
0x00407ac1:	movb %bl, %al
0x00407ac3:	popl %ebx
0x00407ac4:	movl %ebx, %esi
0x00407ac6:	movw %cx, %ds
0x00407ac9:	andb %cl, $0x4<UINT8>
0x00407acc:	jmp 0x00407ad6
0x00407ad6:	jmp 0x00407ae6
0x00407ae6:	testb %cl, %cl
0x00407ae8:	jne 54
0x00407aee:	movl %ebx, $0x59c18191<UINT32>
0x00407af3:	clc
0x00407af4:	movl 0x8f60c2d(%ebp), $0x1<UINT32>
0x00407afe:	call 0x00407b17
0x00407b17:	movl %ebx, $0x749bfa20<UINT32>
0x00407b1c:	popl %ebx
0x00407b1d:	jmp 0x00407b82
0x00407b82:	movl 0x8f60285(%ebp), %eax
0x00407b88:	movl %ebx, $0x459d9338<UINT32>
0x00407b8d:	movl 0x8f6052d(%ebp), %eax
0x00407b93:	movb %bh, $0x2a<UINT8>
0x00407b95:	leal %eax, 0x8f6173d(%ebp)
0x00407b9b:	movl %ebx, $0x4643b048<UINT32>
0x00407ba0:	stc
0x00407ba1:	movl 0x8f6083d(%ebp), %eax
0x00407ba7:	pusha
0x00407ba8:	jmp 0x00407bb3
0x00407bb3:	pusha
0x00407bb4:	sbbl %ecx, $0x45814ce4<UINT32>
0x00407bba:	movw %cx, %di
0x00407bbd:	popa
0x00407bbe:	popa
0x00407bbf:	movl %eax, $0x1<UINT32>
0x00407bc4:	movl %ebx, %esi
0x00407bc6:	addb %bl, $0xffffffeb<UINT8>
0x00407bc9:	movl 0x8f6087d(%ebp), %eax
0x00407bcf:	jno 0x00407bdd
0x00407bdd:	movl %eax, $0x0<UINT32>
0x00407be2:	movb %bh, $0xffffffe2<UINT8>
0x00407be4:	testl %eax, %eax
0x00407be6:	je 0x00407c53
0x00407c53:	movl %eax, %eax
0x00407c55:	call 0x00407c5a
0x00407c5a:	popl %eax
0x00407c5b:	movl 0x28(%eax), %ebp
0x00407c5e:	popa
0x00407c5f:	call 0x00407c71
0x00407c71:	pushl %fs:0
0x00407c78:	movl %fs:0, %esp
0x00407c7f:	sti
0x00407c80:	pusha
0x00407c81:	movl %ebp, $0xf74a6014<UINT32>
0x00407c86:	jmp 0x00407e8b
0x00407e8b:	pushl %ebp
0x00407e8c:	movl %ebp, $0x706b<UINT32>
0x00407e91:	pushl %ebp
0x00407e92:	movl %ebp, %esp
0x00407e94:	pushl %ecx
0x00407e95:	movl %ecx, $0x431e2aff<UINT32>
0x00407e9a:	orl %ecx, $0x50ea5c1d<UINT32>
0x00407ea0:	xorl %ecx, $0x53fe7efb<UINT32>
0x00407ea6:	addl %ebp, %ecx
0x00407ea8:	popl %ecx
0x00407ea9:	pushl $0x20e0<UINT32>
0x00407eae:	movl (%esp), %eax
0x00407eb1:	pushl %edi
0x00407eb2:	movl %edi, $0x565f645a<UINT32>
0x00407eb7:	movl %eax, $0x565f645e<UINT32>
0x00407ebc:	xorl %eax, %edi
0x00407ebe:	popl %edi
0x00407ebf:	subl %ebp, %eax
0x00407ec1:	popl %eax
0x00407ec2:	pushl %ebp
0x00407ec3:	pushl 0x4(%esp)
0x00407ec7:	popl %ebp
0x00407ec8:	popl (%esp)
0x00407ecb:	movl %esp, (%esp)
0x00407ece:	movl (%esp), %ebp
0x00407ed1:	subl (%esp), $0x5fda4096<UINT32>
0x00407ed8:	pushl (%esp)
0x00407edb:	popl %eax
0x00407edc:	pushl $0x41d2<UINT32>
0x00407ee1:	movl (%esp), %edx
0x00407ee4:	movl %edx, %esp
0x00407ee6:	addl %edx, $0x4<UINT32>
0x00407eec:	addl %edx, $0x4<UINT32>
0x00407ef2:	pushl %edx
0x00407ef3:	pushl 0x4(%esp)
0x00407ef7:	popl %edx
0x00407ef8:	popl (%esp)
0x00407efb:	popl %esp
0x00407efc:	pushl $0x16ae<UINT32>
0x00407f01:	movl (%esp), %ecx
0x00407f04:	pushl %ebx
0x00407f05:	movl %ebx, $0x6f2d10a2<UINT32>
0x00407f0a:	subl %ebx, $0x6a882394<UINT32>
0x00407f10:	shrl %ebx, $0x6<UINT8>
0x00407f13:	pushl %ecx
0x00407f14:	movl %ecx, $0x504534ac<UINT32>
0x00407f19:	addl %ecx, $0x4ff31e72<UINT32>
0x00407f1f:	subl %ebx, %ecx
0x00407f21:	popl %ecx
0x00407f22:	movl %ecx, %ebx
0x00407f24:	popl %ebx
0x00407f25:	addl %eax, %ecx
0x00407f27:	movl %ecx, (%esp)
0x00407f2a:	addl %esp, $0x4<UINT32>
0x00407f30:	popl %ebp
0x00407f31:	pushl $0x5a08<UINT32>
0x00407f36:	movl (%esp), %ebx
0x00407f39:	movl (%esp), %edx
0x00407f3c:	movl %edx, %esp
0x00407f3e:	pushl $0x1daa<UINT32>
0x00407f43:	movl (%esp), %ecx
0x00407f46:	movl %ecx, $0x4<UINT32>
0x00407f4b:	addl %edx, %ecx
0x00407f4d:	popl %ecx
0x00407f4e:	pushl %ebp
0x00407f4f:	pushl %eax
0x00407f50:	movl %eax, $0x3fe611c2<UINT32>
0x00407f55:	addl %eax, $0xffffffff<UINT32>
0x00407f5a:	shll %eax, $0x4<UINT8>
0x00407f5d:	addl %eax, $0x14071bb8<UINT32>
0x00407f62:	subl %esp, $0x4<UINT32>
0x00407f68:	movl (%esp), %esi
0x00407f6b:	movl %esi, $0x21115c26<UINT32>
0x00407f70:	andl %eax, %esi
0x00407f72:	popl %esi
0x00407f73:	xorl %eax, $0x6b6f6879<UINT32>
0x00407f78:	movl %ebp, %eax
0x00407f7a:	popl %eax
0x00407f7b:	addl %ebp, $0x137c3e3e<UINT32>
0x00407f81:	pushl %ecx
0x00407f82:	pushl %ebp
0x00407f83:	movl %ebp, $0x36c575f5<UINT32>
0x00407f88:	movl %ecx, %ebp
0x00407f8a:	popl %ebp
0x00407f8b:	addl %ebp, %ecx
0x00407f8d:	popl %ecx
0x00407f8e:	pushl %esi
0x00407f8f:	pushl %ecx
0x00407f90:	movl %ecx, $0x6f2e0e37<UINT32>
0x00407f95:	movl %esi, %ecx
0x00407f97:	popl %ecx
0x00407f98:	subl %ebp, $0x52f53dae<UINT32>
0x00407f9e:	addl %ebp, %esi
0x00407fa0:	addl %ebp, $0x52f53dae<UINT32>
0x00407fa6:	movl %esi, (%esp)
0x00407fa9:	addl %esp, $0x4<UINT32>
0x00407faf:	incl %ebp
0x00407fb0:	subl %ebp, $0x1<UINT8>
0x00407fb3:	subl %ebp, $0x24df3edf<UINT32>
0x00407fb9:	addl %edx, $0x472164c6<UINT32>
0x00407fbf:	subl %edx, %ebp
0x00407fc1:	subl %edx, $0x472164c6<UINT32>
0x00407fc7:	popl %ebp
0x00407fc8:	xorl %edx, (%esp)
0x00407fcb:	xorl (%esp), %edx
0x00407fce:	xorl %edx, (%esp)
0x00407fd1:	popl %esp
0x00407fd2:	movl (%esp), %eax
0x00407fd5:	jmp 0x00407fda
0x00407fda:	call 0x00407fdf
0x00407fdf:	pushl (%esp)
0x00407fe2:	pushl (%esp)
0x00407fe5:	popl %ebx
0x00407fe6:	addl %esp, $0x4<UINT8>
0x00407fe9:	pushl %eax
0x00407fea:	movl %eax, %esp
0x00407fec:	addl %eax, $0x4<UINT32>
0x00407ff1:	pushl %ebp
0x00407ff2:	movl %ebp, $0x4<UINT32>
0x00407ff7:	subl %eax, %ebp
0x00407ff9:	popl %ebp
0x00407ffa:	xchgl (%esp), %eax
0x00407ffd:	popl %esp
0x00407ffe:	movl (%esp), %ebx
0x00408001:	pushl $0x162<UINT32>
0x00408006:	pushl %esp
0x00408007:	popl (%esp)
0x0040800a:	pushl %ecx
0x0040800b:	movl %ecx, $0x2bff1665<UINT32>
0x00408010:	pushl %ecx
0x00408011:	notl (%esp)
0x00408014:	movl %ecx, (%esp)
0x00408017:	addl %esp, $0x4<UINT8>
0x0040801a:	notl %ecx
0x0040801c:	subl %ecx, $0x2bff1661<UINT32>
0x00408022:	addl 0x4(%esp), %ecx
0x00408026:	popl %ecx
0x00408027:	movl %ebx, (%esp)
0x0040802a:	addl %esp, $0x4<UINT8>
0x0040802d:	pushl %ecx
0x0040802e:	movl %ecx, $0x4<UINT32>
0x00408033:	addl %ebx, %ecx
0x00408035:	popl %ecx
0x00408036:	pushl %edx
0x00408037:	movl %edx, $0x4<UINT32>
0x0040803c:	addl %ebx, %edx
0x0040803e:	popl %edx
0x0040803f:	xorl %ebx, (%esp)
0x00408042:	xorl (%esp), %ebx
0x00408045:	xorl %ebx, (%esp)
0x00408048:	movl %esp, (%esp)
0x0040804b:	pushl %eax
0x0040804c:	movl (%esp), %edx
0x0040804f:	pushl %eax
0x00408050:	movl %eax, $0x16d813a8<UINT32>
0x00408055:	xorl %eax, $0x3c866824<UINT32>
0x0040805a:	addl %eax, $0x76e0480d<UINT32>
0x0040805f:	subl %eax, $0x362e531b<UINT32>
0x00408064:	movl %edx, %eax
0x00408066:	popl %eax
0x00408067:	pushl %ebp
0x00408068:	movl (%esp), %edi
0x0040806b:	movl %edi, $0x707d6b06<UINT32>
0x00408070:	addl %edx, $0x3eaf1de8<UINT32>
0x00408076:	addl %edx, $0x72cb4928<UINT32>
0x0040807c:	addl %edx, %edi
0x0040807e:	subl %edx, $0x72cb4928<UINT32>
0x00408084:	subl %edx, $0x3eaf1de8<UINT32>
0x0040808a:	popl %edi
0x0040808b:	xorl %edx, $0xb026bae3<UINT32>
0x00408091:	subl %ebx, %edx
0x00408093:	popl %edx
0x00408094:	pushl %eax
0x00408095:	movl %eax, $0x36e70371<UINT32>
0x0040809a:	xorl %eax, $0x72685df0<UINT32>
0x0040809f:	addl %eax, $0x1<UINT32>
0x004080a4:	xorl %eax, $0x50174e53<UINT32>
0x004080a9:	subl %ebx, %eax
0x004080ab:	popl %eax
0x004080ac:	subl %ebx, $0x6e3671aa<UINT32>
0x004080b2:	addl %ebx, (%esp)
0x004080b5:	pushl %edi
0x004080b6:	movl %edi, $0x6e3671aa<UINT32>
0x004080bb:	addl %ebx, %edi
0x004080bd:	movl %edi, (%esp)
0x004080c0:	addl %esp, $0x4<UINT8>
0x004080c3:	addl %ebx, $0x149810d1<UINT32>
0x004080c9:	pushl %ecx
0x004080ca:	pushl %edx
0x004080cb:	movl %edx, $0x6bab6167<UINT32>
0x004080d0:	movl %ecx, %edx
0x004080d2:	movl %edx, (%esp)
0x004080d5:	addl %esp, $0x4<UINT8>
0x004080d8:	addl %ebx, %ecx
0x004080da:	pushl (%esp)
0x004080dd:	popl %ecx
0x004080de:	addl %esp, $0x4<UINT32>
0x004080e4:	pushl %eax
0x004080e5:	movl %eax, %esp
0x004080e7:	addl %eax, $0x4<UINT32>
0x004080ec:	pushl $0x62d3<UINT32>
0x004080f1:	movl (%esp), %ecx
0x004080f4:	pushl %ebx
0x004080f5:	pushl $0x1124d8<UINT32>
0x004080fa:	popl %ebx
0x004080fb:	pushl %esi
0x004080fc:	movl %esi, $0x47911040<UINT32>
0x00408101:	incl %esi
0x00408102:	notl %esi
0x00408104:	decl %esi
0x00408105:	negl %esi
0x00408107:	shrl %esi, $0x3<UINT8>
0x0040810a:	pushl %edx
0x0040810b:	movl %edx, $0x5393f10e<UINT32>
0x00408110:	addl %esi, %edx
0x00408112:	popl %edx
0x00408113:	addl %ebx, %esi
0x00408115:	popl %esi
0x00408116:	subl %ebx, $0x5c9737ea<UINT32>
0x0040811c:	movl %ecx, %ebx
0x0040811e:	popl %ebx
0x0040811f:	pushl %ecx
0x00408120:	movl %ecx, $0x17732059<UINT32>
0x00408125:	subl %eax, $0x9dd288f<UINT32>
0x0040812a:	subl %eax, %ecx
0x0040812c:	addl %eax, $0x9dd288f<UINT32>
0x00408131:	popl %ecx
0x00408132:	addl %eax, $0x1d6169ac<UINT32>
0x00408137:	addl %eax, %ecx
0x00408139:	subl %eax, $0x1d6169ac<UINT32>
0x0040813e:	pushl %ebx
0x0040813f:	movl %ebx, $0x74697cdd<UINT32>
0x00408144:	orl %ebx, $0x15ff1b75<UINT32>
0x0040814a:	xorl %ebx, $0x4d396468<UINT32>
0x00408150:	notl %ebx
0x00408152:	shll %ebx, $0x5<UINT8>
0x00408155:	pushl %edi
0x00408156:	movl %edi, $0xcfc96ce7<UINT32>
0x0040815b:	subl %ebx, %edi
0x0040815d:	popl %edi
0x0040815e:	addl %eax, %ebx
0x00408160:	popl %ebx
0x00408161:	movl %ecx, (%esp)
0x00408164:	pushl %ecx
0x00408165:	pushl %esp
0x00408166:	popl %ecx
0x00408167:	pushl %edx
0x00408168:	movl %edx, $0x4<UINT32>
0x0040816d:	addl %ecx, %edx
0x0040816f:	movl %edx, (%esp)
0x00408172:	addl %esp, $0x4<UINT8>
0x00408175:	addl %ecx, $0x4<UINT32>
0x0040817b:	xchgl (%esp), %ecx
0x0040817e:	movl %esp, (%esp)
0x00408181:	xorl %eax, (%esp)
0x00408184:	xorl (%esp), %eax
0x00408187:	xorl %eax, (%esp)
0x0040818a:	popl %esp
0x0040818b:	sti
0x0040818c:	pushl %esi
0x0040818d:	pushl %ecx
0x0040818e:	movl %ecx, $0x213975d7<UINT32>
0x00408193:	movl %esi, $0x73d151cd<UINT32>
0x00408198:	xorl %esi, %ecx
0x0040819a:	popl %ecx
0x0040819b:	subl %esi, $0x1<UINT32>
0x004081a1:	negl %esi
0x004081a3:	pushl %ebp
0x004081a4:	movl %ebp, $0x16e75327<UINT32>
0x004081a9:	subl %ebp, $0x15c33a0f<UINT32>
0x004081af:	subl %esp, $0x4<UINT8>
0x004081b2:	movl (%esp), %edx
0x004081b5:	pushl %esp
0x004081b6:	popl %edx
0x004081b7:	addl %edx, $0x4<UINT32>
0x004081bd:	subl %edx, $0x4<UINT32>
0x004081c3:	pushl $0x73da<UINT32>
0x004081c8:	movl (%esp), %edx
0x004081cb:	pushl 0x4(%esp)
0x004081cf:	popl %edx
0x004081d0:	popl (%esp)
0x004081d3:	movl %esp, (%esp)
0x004081d6:	movl (%esp), %edi
0x004081d9:	pushl %ebx
0x004081da:	movl %ebx, $0x52ae51f3<UINT32>
0x004081df:	movl %edi, $0x724d5e51<UINT32>
0x004081e4:	xorl %edi, %ebx
0x004081e6:	popl %ebx
0x004081e7:	xorl %edi, $0x744e0664<UINT32>
0x004081ed:	xorl %ebp, %edi
0x004081ef:	movl %edi, (%esp)
0x004081f2:	addl %esp, $0x4<UINT8>
0x004081f5:	subl %esi, $0x69d77c42<UINT32>
0x004081fb:	subl %esi, %ebp
0x004081fd:	pushl %ebp
0x004081fe:	movl %ebp, $0x24377725<UINT32>
0x00408203:	addl %ebp, $0x4c6c3fde<UINT32>
0x00408209:	shrl %ebp, $0x8<UINT8>
0x0040820c:	pushl %esi
0x0040820d:	movl %esi, $0x96992775<UINT32>
0x00408212:	subl %ebp, %esi
0x00408214:	popl %esi
0x00408215:	addl %esi, %ebp
0x00408217:	popl %ebp
0x00408218:	movl %ebp, (%esp)
0x0040821b:	addl %esp, $0x4<UINT8>
0x0040821e:	pushl %eax
0x0040821f:	movl %eax, $0x63f955bd<UINT32>
0x00408224:	addl %eax, $0x4477f972<UINT32>
0x00408229:	addl %esi, %eax
0x0040822b:	movl %eax, (%esp)
0x0040822e:	addl %esp, $0x4<UINT8>
0x00408231:	movl %edi, %esi
0x00408233:	popl %esi
0x00408234:	pushl (%ebx)
0x00408236:	pushl (%esp)
0x00408239:	pushl (%esp)
0x0040823c:	pushl (%esp)
0x0040823f:	pushl (%esp)
0x00408242:	popl %eax
0x00408243:	addl %esp, $0x4<UINT32>
0x00408249:	pushl %eax
0x0040824a:	movl %eax, %esp
0x0040824c:	addl %eax, $0x4<UINT32>
0x00408251:	subl %eax, $0x4<UINT32>
0x00408256:	xchgl (%esp), %eax
0x00408259:	popl %esp
0x0040825a:	movl (%esp), %edi
0x0040825d:	pushl %esp
0x0040825e:	popl %edi
0x0040825f:	addl %edi, $0x4<UINT32>
0x00408265:	addl %edi, $0x4<UINT8>
0x00408268:	xchgl (%esp), %edi
0x0040826b:	movl %esp, (%esp)
0x0040826e:	pushl %edi
0x0040826f:	movl %edi, %esp
0x00408271:	addl %edi, $0x4<UINT32>
0x00408277:	addl %edi, $0x4<UINT8>
0x0040827a:	xchgl (%esp), %edi
0x0040827d:	popl %esp
0x0040827e:	pushl $0x2781<UINT32>
0x00408283:	movl (%esp), %ebp
0x00408286:	pushl %esp
0x00408287:	popl %ebp
0x00408288:	pushl %eax
0x00408289:	movl %eax, $0x4<UINT32>
0x0040828e:	addl %ebp, %eax
0x00408290:	popl %eax
0x00408291:	pushl %ebx
0x00408292:	movl %ebx, $0x4<UINT32>
0x00408297:	addl %ebp, $0x46997f53<UINT32>
0x0040829d:	addl %ebp, %ebx
0x0040829f:	subl %ebp, $0x46997f53<UINT32>
0x004082a5:	popl %ebx
0x004082a6:	xchgl (%esp), %ebp
0x004082a9:	popl %esp
0x004082aa:	pushl $0x39fc<UINT32>
0x004082af:	movl (%esp), %ecx
0x004082b2:	pushl $0x69e1<UINT32>
0x004082b7:	movl (%esp), %ebp
0x004082ba:	movl %ebp, $0x634e2e85<UINT32>
0x004082bf:	pushl %ebp
0x004082c0:	movl %ebp, $0x56963943<UINT32>
0x004082c5:	decl %ebp
0x004082c6:	shll %ebp, $0x8<UINT8>
0x004082c9:	addl %ebp, $0x1c5165f3<UINT32>
0x004082cf:	movl %ecx, %ebp
0x004082d1:	popl %ebp
0x004082d2:	pushl %edx
0x004082d3:	movl %edx, $0x331d64f1<UINT32>
0x004082d8:	addl %ecx, %edx
0x004082da:	popl %edx
0x004082db:	subl %ecx, $0x49744f6b<UINT32>
0x004082e1:	subl %ecx, %ebp
0x004082e3:	pushl %edx
0x004082e4:	movl %edx, $0x49744f6b<UINT32>
0x004082e9:	pushl %eax
0x004082ea:	movl (%esp), %esi
0x004082ed:	movl %esi, $0x7d101929<UINT32>
0x004082f2:	decl %esi
0x004082f3:	addl %esi, $0x2e07050a<UINT32>
0x004082f9:	orl %esi, $0x370949a0<UINT32>
0x004082ff:	addl %esi, $0x6e58cfed<UINT32>
0x00408305:	addl %ecx, %esi
0x00408307:	popl %esi
0x00408308:	addl %ecx, %edx
0x0040830a:	subl %ecx, $0x2d782f9f<UINT32>
0x00408310:	popl %edx
0x00408311:	pushl %edx
0x00408312:	movl %edx, %esp
0x00408314:	addl %edx, $0x4<UINT32>
0x0040831a:	subl %edx, $0x4<UINT32>
0x00408320:	xchgl (%esp), %edx
0x00408323:	popl %esp
0x00408324:	movl (%esp), %edi
0x00408327:	movl %edi, $0xb45717d<UINT32>
0x0040832c:	xchgl %edi, %esi
0x0040832e:	notl %esi
0x00408330:	xchgl %edi, %esi
0x00408332:	pushl %edi
0x00408333:	negl (%esp)
0x00408336:	popl %edi
0x00408337:	notl %edi
0x00408339:	andl %edi, $0x6eb97b82<UINT32>
0x0040833f:	pushl %edx
0x00408340:	movl %edx, $0x319aa58f<UINT32>
0x00408345:	subl %edi, %edx
0x00408347:	popl %edx
0x00408348:	subl %ecx, %edi
0x0040834a:	popl %edi
0x0040834b:	popl %ebp
0x0040834c:	xorl %eax, %ecx
0x0040834e:	pushl (%esp)
0x00408351:	popl %ecx
0x00408352:	addl %esp, $0x4<UINT32>
0x00408358:	pushl %eax
0x00408359:	movl (%esp), %ebp
0x0040835c:	pushl %ecx
0x0040835d:	movl %ecx, $0x64460c3f<UINT32>
0x00408362:	pushl %edx
0x00408363:	movl %edx, $0x12037962<UINT32>
0x00408368:	addl %edx, $0x338b0b9b<UINT32>
0x0040836e:	shll %edx, $0x5<UINT8>
0x00408371:	shll %edx, $0x4<UINT8>
0x00408374:	subl %edx, $0x30bebdc5<UINT32>
0x0040837a:	pushl %ebx
0x0040837b:	movl %ebx, $0x36c76ff2<UINT32>
0x00408380:	addl %ecx, %ebx
0x00408382:	popl %ebx
0x00408383:	addl %ecx, $0x665f4a15<UINT32>
0x00408389:	addl %ecx, %edx
0x0040838b:	pushl %ebx
0x0040838c:	movl %ebx, $0x665f4a15<UINT32>
0x00408391:	subl %ecx, %ebx
0x00408393:	popl %ebx
0x00408394:	pushl %ebp
0x00408395:	movl %ebp, $0x36c76ff2<UINT32>
0x0040839a:	subl %ecx, %ebp
0x0040839c:	popl %ebp
0x0040839d:	movl %edx, (%esp)
0x004083a0:	addl %esp, $0x4<UINT8>
0x004083a3:	movl %ebp, %ecx
0x004083a5:	pushl (%esp)
0x004083a8:	popl %ecx
0x004083a9:	addl %esp, $0x4<UINT32>
0x004083af:	pushl $0x5e1<UINT32>
0x004083b4:	movl (%esp), %ebx
0x004083b7:	movl %ebx, $0x168b3125<UINT32>
0x004083bc:	andl %ebx, $0x455b5330<UINT32>
0x004083c2:	andl %ebx, $0x72a041c9<UINT32>
0x004083c8:	shll %ebx
0x004083ca:	notl %ebx
0x004083cc:	pushl %ebp
0x004083cd:	pushl %edx
0x004083ce:	movl %edx, $0x336107ae<UINT32>
0x004083d3:	addl %edx, $0x2c5e4986<UINT32>
0x004083d9:	negl %edx
0x004083db:	notl %edx
0x004083dd:	subl %edx, $0xe0f60c92<UINT32>
0x004083e3:	movl %ebp, %edx
0x004083e5:	popl %edx
0x004083e6:	negl %ebp
0x004083e8:	addl %ebp, $0x53571061<UINT32>
0x004083ee:	xorl %ebx, %ebp
0x004083f0:	popl %ebp
0x004083f1:	andl %ebp, %ebx
0x004083f3:	popl %ebx
0x004083f4:	addl %ebp, $0xce95643<UINT32>
0x004083fa:	incl %ebp
0x004083fb:	xorl %ebp, $0x1b234bde<UINT32>
0x00408401:	addl %eax, %ebp
0x00408403:	movl %ebp, (%esp)
0x00408406:	addl %esp, $0x4<UINT8>
0x00408409:	pushl %ecx
0x0040840a:	pushl %edx
0x0040840b:	pushl $0x6fc63550<UINT32>
0x00408410:	popl %edx
0x00408411:	decl %edx
0x00408412:	shll %edx, $0x6<UINT8>
0x00408415:	negl %edx
0x00408417:	subl %edx, $0xebb836f4<UINT32>
0x0040841d:	movl %ecx, %edx
0x0040841f:	popl %edx
0x00408420:	shll %ecx, $0x2<UINT8>
0x00408423:	pushl %ebp
0x00408424:	movl %ebp, $0x16321204<UINT32>
0x00408429:	addl %ebp, $0x24306cec<UINT32>
0x0040842f:	addl %ebp, $0xdc0d9664<UINT32>
0x00408435:	xorl %ecx, %ebp
0x00408437:	pushl (%esp)
0x0040843a:	popl %ebp
0x0040843b:	addl %esp, $0x4<UINT8>
0x0040843e:	pushl %edi
0x0040843f:	movl (%esp), %ebx
0x00408442:	pushl %esi
0x00408443:	movl %esi, $0x1a90071<UINT32>
0x00408448:	movl %ebx, $0x35e83eab<UINT32>
0x0040844d:	addl %ebx, %esi
0x0040844f:	movl %esi, (%esp)
0x00408452:	addl %esp, $0x4<UINT32>
0x00408458:	orl %ecx, %ebx
0x0040845a:	popl %ebx
0x0040845b:	subl %ecx, $0x1<UINT8>
0x0040845e:	notl %ecx
0x00408460:	pushl %ebx
0x00408461:	movl %ebx, $0x42726e2c<UINT32>
0x00408466:	subl %ebx, $0x42726e2d<UINT32>
0x0040846c:	addl %ecx, %ebx
0x0040846e:	movl %ebx, (%esp)
0x00408471:	addl %esp, $0x4<UINT32>
0x00408477:	pushl %edi
0x00408478:	subl %esp, $0x4<UINT32>
0x0040847e:	movl (%esp), %eax
0x00408481:	movl %eax, $0x15131d1d<UINT32>
0x00408486:	orl %eax, $0x5c7417e1<UINT32>
0x0040848b:	orl %eax, $0x76f673c2<UINT32>
0x00408490:	negl %eax
0x00408492:	xorl %eax, $0xe724a7fd<UINT32>
0x00408497:	movl %edi, %eax
0x00408499:	popl %eax
0x0040849a:	xorl %ecx, %edi
0x0040849c:	movl %edi, (%esp)
0x0040849f:	addl %esp, $0x4<UINT32>
0x004084a5:	subl %eax, %ecx
0x004084a7:	pushl (%esp)
0x004084aa:	popl %ecx
0x004084ab:	addl %esp, $0x4<UINT8>
0x004084ae:	pushl %edx
0x004084af:	pushl %esp
0x004084b0:	movl %edx, (%esp)
0x004084b3:	addl %esp, $0x4<UINT32>
0x004084b9:	addl %edx, $0x4<UINT32>
0x004084bf:	subl %edx, $0x4<UINT8>
0x004084c2:	xchgl (%esp), %edx
0x004084c5:	popl %esp
0x004084c6:	movl (%esp), %ebx
0x004084c9:	pushl $0xaab<UINT32>
0x004084ce:	pushl %esp
0x004084cf:	popl (%esp)
0x004084d2:	addl (%esp), $0x4<UINT8>
0x004084d6:	pushl (%esp)
0x004084d9:	popl %ebx
0x004084da:	pushl %ebp
0x004084db:	movl %ebp, %esp
0x004084dd:	pushl %edx
0x004084de:	pushl %ebp
0x004084df:	movl %ebp, $0x4<UINT32>
0x004084e4:	movl %edx, %ebp
0x004084e6:	popl %ebp
0x004084e7:	addl %ebp, %edx
0x004084e9:	popl %edx
0x004084ea:	addl %ebp, $0x4<UINT8>
0x004084ed:	xchgl (%esp), %ebp
0x004084f0:	popl %esp
0x004084f1:	pushl $0x6431<UINT32>
0x004084f6:	movl (%esp), %eax
0x004084f9:	movl %eax, $0x4<UINT32>
0x004084fe:	addl %ebx, $0x6faa73a4<UINT32>
0x00408504:	addl %ebx, %eax
0x00408506:	subl %ebx, $0x6faa73a4<UINT32>
0x0040850c:	movl %eax, (%esp)
0x0040850f:	addl %esp, $0x4<UINT8>
0x00408512:	subl %ebx, $0x4<UINT8>
0x00408515:	pushl %ebx
0x00408516:	pushl 0x4(%esp)
0x0040851a:	popl %ebx
0x0040851b:	popl (%esp)
0x0040851e:	movl %esp, (%esp)
0x00408521:	movl (%esp), %eax
0x00408524:	popl (%ebx)
0x00408526:	pushl %edx
0x00408527:	pushl %esp
0x00408528:	popl %edx
0x00408529:	addl %edx, $0x4<UINT32>
0x0040852f:	pushl %ebx
0x00408530:	movl %ebx, $0x4<UINT32>
0x00408535:	addl %edx, $0x348a6ed6<UINT32>
0x0040853b:	subl %edx, %ebx
0x0040853d:	subl %edx, $0x348a6ed6<UINT32>
0x00408543:	popl %ebx
0x00408544:	xchgl (%esp), %edx
0x00408547:	movl %esp, (%esp)
0x0040854a:	movl (%esp), %esi
0x0040854d:	movl %esi, $0x4<UINT32>
0x00408552:	pushl %eax
0x00408553:	movl %eax, $0x649715c9<UINT32>
0x00408558:	subl %ebx, %eax
0x0040855a:	popl %eax
0x0040855b:	subl %ebx, $0x102f3af7<UINT32>
0x00408561:	subl %ebx, $0x392e5def<UINT32>
0x00408567:	subl %ebx, %esi
0x00408569:	addl %ebx, $0x392e5def<UINT32>
0x0040856f:	addl %ebx, $0x102f3af7<UINT32>
0x00408575:	pushl %edi
0x00408576:	pushl %eax
0x00408577:	movl %eax, $0x4f2907b2<UINT32>
0x0040857c:	movl %edi, $0x156e0e17<UINT32>
0x00408581:	addl %edi, %eax
0x00408583:	popl %eax
0x00408584:	addl %ebx, $0x45a757f5<UINT32>
0x0040858a:	addl %ebx, %edi
0x0040858c:	subl %ebx, $0x45a757f5<UINT32>
0x00408592:	popl %edi
0x00408593:	pushl (%esp)
0x00408596:	movl %esi, (%esp)
0x00408599:	addl %esp, $0x4<UINT8>
0x0040859c:	pushl %edx
0x0040859d:	movl (%esp), %ebp
0x004085a0:	pushl %esp
0x004085a1:	popl %ebp
0x004085a2:	addl %ebp, $0x4<UINT32>
0x004085a8:	addl %ebp, $0x4<UINT32>
0x004085ae:	xchgl (%esp), %ebp
0x004085b1:	popl %esp
0x004085b2:	subl %edi, $0x1<UINT32>
0x004085b8:	jne 0x00408234
0x004085be:	sti
0x004085bf:	pushl $0x470a<UINT32>
0x004085c4:	movl (%esp), %edi
0x004085c7:	pushl $0x77527e8f<UINT32>
0x004085cc:	pushl (%esp)
0x004085cf:	popl %edi
0x004085d0:	pushl $0x6e8c<UINT32>
0x004085d5:	movl (%esp), %ebx
0x004085d8:	movl %ebx, %esp
0x004085da:	addl %ebx, $0x4<UINT32>
0x004085e0:	pushl %edi
0x004085e1:	movl %edi, $0x4<UINT32>
0x004085e6:	addl %ebx, %edi
0x004085e8:	popl %edi
0x004085e9:	xorl %ebx, (%esp)
0x004085ec:	xorl (%esp), %ebx
0x004085ef:	xorl %ebx, (%esp)
0x004085f2:	popl %esp
0x004085f3:	pushl %edx
0x004085f4:	pushl %esi
0x004085f5:	movl %esi, $0x74f95a39<UINT32>
0x004085fa:	movl %edx, $0x8ed6be4c<UINT32>
0x004085ff:	subl %edx, $0x543f455e<UINT32>
0x00408605:	addl %edx, %esi
0x00408607:	pushl %ebx
0x00408608:	movl %ebx, $0x24bf05b6<UINT32>
0x0040860d:	decl %ebx
0x0040860e:	pushl %edi
0x0040860f:	movl %edi, $0x426d35b0<UINT32>
0x00408614:	xorl %ebx, %edi
0x00408616:	popl %edi
0x00408617:	andl %ebx, $0x134057a8<UINT32>
0x0040861d:	xorl %ebx, $0x3f470c72<UINT32>
0x00408623:	subl %ebx, $0x40441758<UINT32>
0x00408629:	pushl %edi
0x0040862a:	movl (%esp), %eax
0x0040862d:	movl %eax, $0x442a126f<UINT32>
0x00408632:	addl %eax, $0x5618634c<UINT32>
0x00408637:	pushl %eax
0x00408638:	notl (%esp)
0x0040863b:	popl %eax
0x0040863c:	decl %eax
0x0040863d:	notl %eax
0x0040863f:	notl %eax
0x00408641:	addl %eax, $0xf1beb601<UINT32>
0x00408646:	addl %ebx, %eax
0x00408648:	popl %eax
0x00408649:	addl %edx, %ebx
0x0040864b:	popl %ebx
0x0040864c:	popl %esi
0x0040864d:	xorl %edi, %edx
0x0040864f:	movl %edx, (%esp)
0x00408652:	addl %esp, $0x4<UINT8>
0x00408655:	movl %ebx, %edi
0x00408657:	pushl (%esp)
0x0040865a:	popl %edi
0x0040865b:	addl %esp, $0x4<UINT32>
0x00408661:	pushl %ebx
0x00408662:	movl %ebx, $0x13287869<UINT32>
0x00408667:	pushl %ebx
0x00408668:	addl (%esp), $0x16f817b5<UINT32>
0x0040866f:	movl %eax, (%esp)
0x00408672:	pushl %ecx
0x00408673:	pushl %esp
0x00408674:	popl %ecx
0x00408675:	addl %ecx, $0x4<UINT32>
0x0040867b:	addl %ecx, $0x4<UINT32>
0x00408681:	xchgl (%esp), %ecx
0x00408684:	popl %esp
0x00408685:	pushl $0x1bf8<UINT32>
0x0040868a:	movl (%esp), %ecx
0x0040868d:	pushl %ebp
0x0040868e:	pushl $0x16f817b5<UINT32>
0x00408693:	popl %ebp
0x00408694:	movl %ecx, %ebp
0x00408696:	popl %ebp
0x00408697:	subl %eax, %ecx
0x00408699:	popl %ecx
0x0040869a:	popl %ebx
0x0040869b:	subl %eax, $0x129c7973<UINT32>
0x004086a0:	shrl %eax, $0x8<UINT8>
0x004086a3:	pushl %eax
0x004086a4:	xchgl (%esp), %eax
0x004086a7:	notl %eax
0x004086a9:	xchgl (%esp), %eax
0x004086ac:	popl %eax
0x004086ad:	xorl %eax, %ebx
0x004086af:	xorl %ebx, %eax
0x004086b1:	xorl %eax, %ebx
0x004086b3:	subl %ebx, $0xffffffff<UINT32>
0x004086b9:	xchgl %ebx, %eax
0x004086ba:	pushl %edx
0x004086bb:	movl (%esp), %edi
0x004086be:	pushl $0x231d6998<UINT32>
0x004086c3:	movl %edi, (%esp)
0x004086c6:	addl %esp, $0x4<UINT8>
0x004086c9:	subl %eax, $0x5bfb49f1<UINT32>
0x004086ce:	addl %eax, %edi
0x004086d0:	addl %eax, $0x5bfb49f1<UINT32>
0x004086d5:	popl %edi
0x004086d6:	pushl $0x71fbedff<UINT32>
0x004086db:	movl %eax, (%esp)
0x004086de:	subl %esp, $0x4<UINT32>
0x004086e4:	movl (%esp), %ebp
0x004086e7:	movl %ebp, %esp
0x004086e9:	pushl %esi
0x004086ea:	movl %esi, $0x4<UINT32>
0x004086ef:	addl %ebp, %esi
0x004086f1:	popl %esi
0x004086f2:	pushl %edi
0x004086f3:	pushl %eax
0x004086f4:	pushl %ecx
0x004086f5:	movl %ecx, $0x358a15c8<UINT32>
0x004086fa:	movl %eax, $0x1ff00fef<UINT32>
0x004086ff:	xorl %eax, %ecx
0x00408701:	movl %ecx, (%esp)
0x00408704:	addl %esp, $0x4<UINT32>
0x0040870a:	incl %eax
0x0040870b:	decl %eax
0x0040870c:	pushl %edi
0x0040870d:	movl %edi, $0x2a7a1a23<UINT32>
0x00408712:	xorl %eax, %edi
0x00408714:	popl %edi
0x00408715:	movl %edi, %eax
0x00408717:	popl %eax
0x00408718:	subl %ebp, $0x3d790e84<UINT32>
0x0040871e:	addl %ebp, %edi
0x00408720:	addl %ebp, $0x3d790e84<UINT32>
0x00408726:	movl %edi, (%esp)
0x00408729:	pushl %ebp
0x0040872a:	pushl %esp
0x0040872b:	popl %ebp
0x0040872c:	addl %ebp, $0x4<UINT32>
0x00408732:	pushl %ebx
0x00408733:	movl %ebx, %esp
0x00408735:	addl %ebx, $0x4<UINT32>
0x0040873b:	subl %ebx, $0x4<UINT8>
0x0040873e:	xchgl (%esp), %ebx
0x00408741:	movl %esp, (%esp)
0x00408744:	movl (%esp), %eax
0x00408747:	movl %eax, $0x4<UINT32>
0x0040874c:	subl %ebp, $0x2c982f42<UINT32>
0x00408752:	addl %ebp, %eax
0x00408754:	addl %ebp, $0x2c982f42<UINT32>
0x0040875a:	popl %eax
0x0040875b:	pushl %ebp
0x0040875c:	pushl 0x4(%esp)
0x00408760:	popl %ebp
0x00408761:	popl (%esp)
0x00408764:	popl %esp
0x00408765:	xchgl (%esp), %ebp
0x00408768:	popl %esp
0x00408769:	jmp 0x0040876e
0x0040876e:	pushl %edx
0x0040876f:	subl %esp, $0x4<UINT8>
0x00408772:	movl (%esp), %ecx
0x00408775:	pushl %esi
0x00408776:	pushl $0x3c4d0b36<UINT32>
0x0040877b:	popl %esi
0x0040877c:	andl %esi, $0x1ca7396c<UINT32>
0x00408782:	xorl %esi, $0x412b75e0<UINT32>
0x00408788:	pushl %edx
0x00408789:	movl (%esp), %esi
0x0040878c:	pushl %ecx
0x0040878d:	movl %ecx, $0x27f87ce5<UINT32>
0x00408792:	addl 0x4(%esp), %ecx
0x00408796:	popl %ecx
0x00408797:	popl %ecx
0x00408798:	subl %ecx, $0x27f87ce5<UINT32>
0x0040879e:	popl %esi
0x0040879f:	subl %esp, $0x4<UINT32>
0x004087a5:	movl (%esp), %ecx
0x004087a8:	popl %edx
0x004087a9:	popl %ecx
0x004087aa:	subl %esp, $0x4<UINT32>
0x004087b0:	movl (%esp), %eax
0x004087b3:	pushl %ecx
0x004087b4:	pushl %ebx
0x004087b5:	movl %ebx, $0x50843a50<UINT32>
0x004087ba:	movl %ecx, $0xb0f79a75<UINT32>
0x004087bf:	addl %ecx, $0x7bec16ca<UINT32>
0x004087c5:	subl %ecx, %ebx
0x004087c7:	subl %ecx, $0x7bec16ca<UINT32>
0x004087cd:	popl %ebx
0x004087ce:	andl %ecx, $0x6eb6273f<UINT32>
0x004087d4:	decl %ecx
0x004087d5:	pushl %edx
0x004087d6:	movl %edx, $0x3fc95def<UINT32>
0x004087db:	xorl %ecx, %edx
0x004087dd:	popl %edx
0x004087de:	xorl %ecx, $0x100f788b<UINT32>
0x004087e4:	movl %eax, %ecx
0x004087e6:	movl %ecx, (%esp)
0x004087e9:	addl %esp, $0x4<UINT32>
0x004087ef:	pushl %edx
0x004087f0:	movl %edx, $0x5f8b57cb<UINT32>
0x004087f5:	subl %edx, $0x4931323<UINT32>
0x004087fb:	xorl %eax, %edx
0x004087fd:	popl %edx
0x004087fe:	andl %edx, %eax
0x00408800:	popl %eax
0x00408801:	shll %edx, $0x2<UINT8>
0x00408804:	incl %edx
0x00408805:	subl %esp, $0x4<UINT32>
0x0040880b:	movl (%esp), %esi
0x0040880e:	movl %esi, $0x54317287<UINT32>
0x00408813:	xorl %edx, %esi
0x00408815:	popl %esi
0x00408816:	pushl %edx
0x00408817:	popl %eax
0x00408818:	popl %edx
0x00408819:	subl %esp, $0x4<UINT32>
0x0040881f:	movl (%esp), %ecx
0x00408822:	movl %ecx, %esp
0x00408824:	pushl %esi
0x00408825:	subl %esp, $0x4<UINT8>
0x00408828:	movl (%esp), %ecx
0x0040882b:	movl %ecx, %esp
0x0040882d:	addl %ecx, $0x4<UINT32>
0x00408833:	subl %ecx, $0x4<UINT8>
0x00408836:	xchgl (%esp), %ecx
0x00408839:	popl %esp
0x0040883a:	movl (%esp), %edi
0x0040883d:	movl %edi, $0x734dfb<UINT32>
0x00408842:	pushl %ebx
0x00408843:	pushl $0x45d72ed7<UINT32>
0x00408848:	popl %ebx
0x00408849:	xorl %ebx, $0x7d2d7eca<UINT32>
0x0040884f:	shrl %ebx, $0x8<UINT8>
0x00408852:	addl %ebx, $0x48c909ed<UINT32>
0x00408858:	pushl %edx
0x00408859:	movl %edx, $0x355b718a<UINT32>
0x0040885e:	addl %edx, $0xffffffff<UINT32>
0x00408864:	incl %edx
0x00408865:	andl %edx, $0xa6c7fe6<UINT32>
0x0040886b:	pushl %ebx
0x0040886c:	pushl %edi
0x0040886d:	movl %edi, $0x7ebd72d7<UINT32>
0x00408872:	movl %ebx, $0x841bc64b<UINT32>
0x00408877:	addl %ebx, %edi
0x00408879:	popl %edi
0x0040887a:	subl %ebx, $0xc418f779<UINT32>
0x00408880:	xorl %edx, %ebx
0x00408882:	popl %ebx
0x00408883:	xorl %edx, $0xe86bc6f7<UINT32>
0x00408889:	subl %ebx, %edx
0x0040888b:	popl %edx
0x0040888c:	pushl %ebx
0x0040888d:	movl %esi, (%esp)
0x00408890:	addl %esp, $0x4<UINT8>
0x00408893:	popl %ebx
0x00408894:	xorl %esi, %edi
0x00408896:	popl %edi
0x00408897:	andl %esi, $0x9f447f<UINT32>
0x0040889d:	notl %esi
0x0040889f:	shrl %esi, $0x6<UINT8>
0x004088a2:	orl %esi, $0x1e2f1307<UINT32>
0x004088a8:	pushl %ebx
0x004088a9:	movl %ebx, $0x1fffdbfb<UINT32>
0x004088ae:	xorl %esi, %ebx
0x004088b0:	popl %ebx
0x004088b1:	addl %ecx, %esi
0x004088b3:	popl %esi
0x004088b4:	subl %ecx, $0x4<UINT8>
0x004088b7:	pushl $0x1db1<UINT32>
0x004088bc:	movl (%esp), %ecx
0x004088bf:	pushl 0x4(%esp)
0x004088c3:	pushl (%esp)
0x004088c6:	popl %ecx
0x004088c7:	addl %esp, $0x4<UINT32>
0x004088cd:	popl (%esp)
0x004088d0:	popl %esp
0x004088d1:	movl (%esp), %eax
0x004088d4:	jmp 0x004088d9
0x004088d9:	call 0x004088de
0x004088de:	pushl (%esp)
0x004088e1:	popl %ecx
0x004088e2:	pushl %eax
0x004088e3:	pushl %eax
0x004088e4:	movl (%esp), %esp
0x004088e7:	addl (%esp), $0x4<UINT32>
0x004088ee:	popl %eax
0x004088ef:	pushl %edi
0x004088f0:	pushl %ebp
0x004088f1:	pushl $0x78c91950<UINT32>
0x004088f6:	popl %ebp
0x004088f7:	shrl %ebp, $0x5<UINT8>
0x004088fa:	subl %ebp, $0x3c648c6<UINT32>
0x00408900:	movl %edi, %ebp
0x00408902:	popl %ebp
0x00408903:	subl %eax, $0x51d00c50<UINT32>
0x00408908:	addl %eax, $0x174c5386<UINT32>
0x0040890d:	addl %eax, $0x15305253<UINT32>
0x00408912:	addl %eax, %edi
0x00408914:	subl %esp, $0x4<UINT32>
0x0040891a:	movl (%esp), %ecx
0x0040891d:	pushl %edi
0x0040891e:	pushl %ebp
0x0040891f:	movl %ebp, $0x74f12ac5<UINT32>
0x00408924:	movl %edi, $0x61c17896<UINT32>
0x00408929:	xorl %edi, %ebp
0x0040892b:	popl %ebp
0x0040892c:	movl %ecx, %edi
0x0040892e:	popl %edi
0x0040892f:	pushl %edx
0x00408930:	movl %edx, $0x4e6e0378<UINT32>
0x00408935:	subl %eax, %edx
0x00408937:	popl %edx
0x00408938:	subl %eax, %ecx
0x0040893a:	addl %eax, $0x4e6e0378<UINT32>
0x0040893f:	popl %ecx
0x00408940:	subl %eax, $0x174c5386<UINT32>
0x00408945:	addl %eax, $0x51d00c50<UINT32>
0x0040894a:	movl %edi, (%esp)
0x0040894d:	addl %esp, $0x4<UINT8>
0x00408950:	pushl %ebp
0x00408951:	movl %ebp, $0x4<UINT32>
0x00408956:	addl %eax, %ebp
0x00408958:	movl %ebp, (%esp)
0x0040895b:	addl %esp, $0x4<UINT32>
0x00408961:	xchgl (%esp), %eax
0x00408964:	movl %esp, (%esp)
0x00408967:	pushl %edi
0x00408968:	pushl %ecx
0x00408969:	movl %ecx, $0x4dc45d62<UINT32>
0x0040896e:	movl %edi, %ecx
0x00408970:	popl %ecx
0x00408971:	notl %edi
0x00408973:	decl %edi
0x00408974:	incl %edi
0x00408975:	subl %edi, $0x710d7726<UINT32>
0x0040897b:	xorl %edi, %ecx
0x0040897d:	xorl %ecx, %edi
0x0040897f:	xorl %edi, %ecx
0x00408981:	xchgl %ecx, %edi
0x00408983:	notl %edi
0x00408985:	xchgl %ecx, %edi
0x00408987:	xchgl %edi, %ecx
0x00408989:	notl %edi
0x0040898b:	notl %edi
0x0040898d:	orl %edi, $0x289113ea<UINT32>
0x00408993:	negl %edi
0x00408995:	pushl %esi
0x00408996:	pushl %ecx
0x00408997:	pushl $0x2fa2446a<UINT32>
0x0040899c:	popl %ecx
0x0040899d:	pushl %ecx
0x0040899e:	popl %esi
0x0040899f:	movl %ecx, (%esp)
0x004089a2:	addl %esp, $0x4<UINT32>
0x004089a8:	xorl %edi, %esi
0x004089aa:	popl %esi
0x004089ab:	subl %ecx, %edi
0x004089ad:	popl %edi
0x004089ae:	pushl %ebp
0x004089af:	movl %ebp, $0x36d30e6a<UINT32>
0x004089b4:	addl %ebp, $0x3e6c574a<UINT32>
0x004089ba:	subl %ecx, %ebp
0x004089bc:	popl %ebp
0x004089bd:	addl %ecx, $0x6e0e2474<UINT32>
0x004089c3:	addl %ecx, (%esp)
0x004089c6:	subl %ecx, $0x6e0e2474<UINT32>
0x004089cc:	pushl $0x1636<UINT32>
0x004089d1:	movl (%esp), %ebp
0x004089d4:	movl %ebp, $0x753f65b4<UINT32>
0x004089d9:	addl %ecx, %ebp
0x004089db:	popl %ebp
0x004089dc:	pushl $0x7a2c<UINT32>
0x004089e1:	movl (%esp), %edi
0x004089e4:	movl %edi, $0x6e8c6c7c<UINT32>
0x004089e9:	addl %ecx, %edi
0x004089eb:	movl %edi, (%esp)
0x004089ee:	addl %esp, $0x4<UINT8>
0x004089f1:	pushl %eax
0x004089f2:	movl (%esp), %edx
0x004089f5:	subl %esp, $0x4<UINT8>
0x004089f8:	movl (%esp), %esp
0x004089fb:	addl (%esp), $0x4<UINT8>
0x004089ff:	popl %edx
0x00408a00:	addl %edx, $0x4<UINT32>
0x00408a06:	subl %edx, $0x4<UINT8>
0x00408a09:	pushl %edx
0x00408a0a:	pushl 0x4(%esp)
0x00408a0e:	popl %edx
0x00408a0f:	popl (%esp)
0x00408a12:	movl %esp, (%esp)
0x00408a15:	movl (%esp), %ebx
0x00408a18:	pushl $0x5981<UINT32>
0x00408a1d:	movl (%esp), %eax
0x00408a20:	movl %eax, %esp
0x00408a22:	addl %eax, $0x4<UINT32>
0x00408a27:	subl %eax, $0x4<UINT8>
0x00408a2a:	xchgl (%esp), %eax
0x00408a2d:	movl %esp, (%esp)
0x00408a30:	movl (%esp), %esp
0x00408a33:	addl (%esp), $0x4<UINT8>
0x00408a37:	movl %ebx, (%esp)
0x00408a3a:	addl %esp, $0x4<UINT32>
0x00408a40:	pushl %esi
0x00408a41:	movl %esi, $0x4<UINT32>
0x00408a46:	subl %ebx, $0x255b304e<UINT32>
0x00408a4c:	addl %ebx, %esi
0x00408a4e:	pushl %ecx
0x00408a4f:	pushl %ebx
0x00408a50:	pushl $0x4c8e75c2<UINT32>
0x00408a55:	popl %ebx
0x00408a56:	addl %ebx, $0x579628a<UINT32>
0x00408a5c:	xorl %ebx, $0x775ce802<UINT32>
0x00408a62:	movl %ecx, %ebx
0x00408a64:	popl %ebx
0x00408a65:	addl %ebx, %ecx
0x00408a67:	popl %ecx
0x00408a68:	popl %esi
0x00408a69:	addl %ebx, $0x4<UINT8>
0x00408a6c:	xorl %ebx, (%esp)
0x00408a6f:	xorl (%esp), %ebx
0x00408a72:	xorl %ebx, (%esp)
0x00408a75:	movl %esp, (%esp)
0x00408a78:	sti
0x00408a79:	subl %esp, $0x4<UINT8>
0x00408a7c:	movl (%esp), %edi
0x00408a7f:	movl %edi, $0x69d9794f<UINT32>
0x00408a84:	pushl %ebp
0x00408a85:	movl %ebp, $0x62307a2e<UINT32>
0x00408a8a:	addl %ebp, $0x67135fe9<UINT32>
0x00408a90:	shll %ebp, $0x8<UINT8>
0x00408a93:	pushl %edx
0x00408a94:	movl %edx, $0x780337c0<UINT32>
0x00408a99:	xorl %ebp, %edx
0x00408a9b:	popl %edx
0x00408a9c:	xorl %edi, %ebp
0x00408a9e:	popl %ebp
0x00408a9f:	subl %esp, $0x4<UINT8>
0x00408aa2:	movl (%esp), %esi
0x00408aa5:	movl %esi, $0x4a9b1e6a<UINT32>
0x00408aaa:	xorl %esi, $0x5bf87d29<UINT32>
0x00408ab0:	addl %edi, %esi
0x00408ab2:	popl %esi
0x00408ab3:	pushl %edx
0x00408ab4:	pushl %edi
0x00408ab5:	pushl $0x1462538b<UINT32>
0x00408aba:	popl %edi
0x00408abb:	notl %edi
0x00408abd:	decl %edi
0x00408abe:	shll %edi, $0x8<UINT8>
0x00408ac1:	subl %edi, $0x7dd0e42e<UINT32>
0x00408ac7:	movl %edx, %edi
0x00408ac9:	popl %edi
0x00408aca:	xorl %edi, %edx
0x00408acc:	movl %edx, (%esp)
0x00408acf:	pushl %esi
0x00408ad0:	movl %esi, %esp
0x00408ad2:	addl %esi, $0x4<UINT32>
0x00408ad8:	pushl %edx
0x00408ad9:	movl %edx, $0x753dcc<UINT32>
0x00408ade:	xchgl %edx, %eax
0x00408adf:	incl %eax
0x00408ae0:	xchgl %edx, %eax
0x00408ae1:	pushl %esi
0x00408ae2:	movl %esi, $0x412a765c<UINT32>
0x00408ae7:	andl %esi, $0x29f70d63<UINT32>
0x00408aed:	notl %esi
0x00408aef:	subl %esi, $0xe0f7b17a<UINT32>
0x00408af5:	addl %edx, %esi
0x00408af7:	popl %esi
0x00408af8:	xorl %edx, $0x1e5b8816<UINT32>
0x00408afe:	addl %esi, %edx
0x00408b00:	popl %edx
0x00408b01:	xchgl (%esp), %esi
0x00408b04:	movl %esp, (%esp)
0x00408b07:	pushl %ebp
0x00408b08:	movl (%esp), %edi
0x00408b0b:	pushl (%esp)
0x00408b0e:	popl %eax
0x00408b0f:	addl %esp, $0x4<UINT32>
0x00408b15:	movl %edi, (%esp)
0x00408b18:	addl %esp, $0x4<UINT8>
0x00408b1b:	pushl $0xf3e<UINT32>
0x00408b20:	movl (%esp), %edi
0x00408b23:	movl %edi, $0x2bf51fb6<UINT32>
0x00408b28:	shll %edi
0x00408b2a:	pushl %edx
0x00408b2b:	movl %edx, $0x56b5223e<UINT32>
0x00408b30:	andl %edi, %edx
0x00408b32:	movl %edx, (%esp)
0x00408b35:	pushl %edx
0x00408b36:	movl %edx, %esp
0x00408b38:	addl %edx, $0x4<UINT32>
0x00408b3e:	addl %edx, $0x4<UINT32>
0x00408b44:	xchgl (%esp), %edx
0x00408b47:	popl %esp
0x00408b48:	pushl $0x587e<UINT32>
0x00408b4d:	movl (%esp), %esi
0x00408b50:	movl %esi, $0xa745cf6<UINT32>
0x00408b55:	andl %edi, %esi
0x00408b57:	movl %esi, (%esp)
0x00408b5a:	pushl %edi
0x00408b5b:	movl %edi, %esp
0x00408b5d:	addl %edi, $0x4<UINT32>
0x00408b63:	addl %edi, $0x4<UINT32>
0x00408b69:	xorl %edi, (%esp)
0x00408b6c:	xorl (%esp), %edi
0x00408b6f:	xorl %edi, (%esp)
0x00408b72:	popl %esp
0x00408b73:	pushl %eax
0x00408b74:	movl %eax, $0x7a982844<UINT32>
0x00408b79:	addl %edi, %eax
0x00408b7b:	popl %eax
0x00408b7c:	xorl %eax, %edi
0x00408b7e:	pushl (%esp)
0x00408b81:	movl %edi, (%esp)
0x00408b84:	addl %esp, $0x4<UINT8>
0x00408b87:	pushl %eax
0x00408b88:	movl (%esp), %ecx
0x00408b8b:	movl %ecx, %esp
0x00408b8d:	addl %ecx, $0x4<UINT32>
0x00408b93:	pushl %ebp
0x00408b94:	movl (%esp), %ebx
0x00408b97:	pushl %edi
0x00408b98:	movl %edi, $0x30a3678a<UINT32>
0x00408b9d:	andl %edi, $0x2eb715c6<UINT32>
0x00408ba3:	shll %edi, $0x8<UINT8>
0x00408ba6:	xorl %edi, $0x58c74c7c<UINT32>
0x00408bac:	decl %edi
0x00408bad:	subl %edi, $0x18355251<UINT32>
0x00408bb3:	subl %edi, $0xe38d7c26<UINT32>
0x00408bb9:	movl %ebx, %edi
0x00408bbb:	popl %edi
0x00408bbc:	addl %ecx, %ebx
0x00408bbe:	popl %ebx
0x00408bbf:	xchgl (%esp), %ecx
0x00408bc2:	movl %esp, (%esp)
0x00408bc5:	pushl (%ecx)
0x00408bc7:	pushl %ebp
0x00408bc8:	subl %esp, $0x4<UINT32>
0x00408bce:	movl (%esp), %ecx
0x00408bd1:	pushl %ebx
0x00408bd2:	pushl $0x6f8f79ec<UINT32>
0x00408bd7:	popl %ebx
0x00408bd8:	movl %ecx, %ebx
0x00408bda:	popl %ebx
0x00408bdb:	shrl %ecx, $0x5<UINT8>
0x00408bde:	xorl %ecx, $0x2f177f3f<UINT32>
0x00408be4:	pushl $0x57c1<UINT32>
0x00408be9:	movl (%esp), %ecx
0x00408bec:	movl %ebp, (%esp)
0x00408bef:	addl %esp, $0x4<UINT8>
0x00408bf2:	pushl (%esp)
0x00408bf5:	movl %ecx, (%esp)
0x00408bf8:	addl %esp, $0x4<UINT32>
0x00408bfe:	pushl %ebp
0x00408bff:	movl (%esp), %edi
0x00408c02:	movl %edi, %esp
0x00408c04:	pushl %eax
0x00408c05:	movl %eax, $0x4<UINT32>
0x00408c0a:	subl %edi, $0x63c56a5e<UINT32>
0x00408c10:	addl %edi, %eax
0x00408c12:	addl %edi, $0x63c56a5e<UINT32>
0x00408c18:	popl %eax
0x00408c19:	addl %edi, $0x4<UINT32>
0x00408c1f:	xchgl (%esp), %edi
0x00408c22:	popl %esp
0x00408c23:	xorl 0x4(%esp), %ebp
0x00408c27:	movl %ebp, (%esp)
0x00408c2a:	addl %esp, $0x4<UINT8>
0x00408c2d:	pushl (%esp)
0x00408c30:	movl %esi, (%esp)
0x00408c33:	addl %esp, $0x4<UINT32>
0x00408c39:	addl %esp, $0x4<UINT32>
0x00408c3f:	xorl %esi, $0x2c6b04f0<UINT32>
0x00408c45:	pushl %ecx
0x00408c46:	movl (%esp), %edi
0x00408c49:	movl (%esp), %eax
0x00408c4c:	pushl %edx
0x00408c4d:	movl %edx, $0x519e354a<UINT32>
0x00408c52:	pushl %esi
0x00408c53:	pushl %edx
0x00408c54:	movl %edx, $0x47c5554f<UINT32>
0x00408c59:	movl %esi, %edx
0x00408c5b:	popl %edx
0x00408c5c:	addl %edx, %esi
0x00408c5e:	popl %esi
0x00408c5f:	decl %edx
0x00408c60:	orl %edx, $0x2fc35a39<UINT32>
0x00408c66:	pushl %edi
0x00408c67:	subl %esp, $0x4<UINT32>
0x00408c6d:	movl (%esp), %ecx
0x00408c70:	movl %ecx, $0x976c314f<UINT32>
0x00408c75:	movl %edi, %ecx
0x00408c77:	popl %ecx
0x00408c78:	xorl %edx, %edi
0x00408c7a:	pushl (%esp)
0x00408c7d:	popl %edi
0x00408c7e:	addl %esp, $0x4<UINT32>
0x00408c84:	movl %eax, %edx
0x00408c86:	pushl (%esp)
0x00408c89:	movl %edx, (%esp)
0x00408c8c:	addl %esp, $0x4<UINT8>
0x00408c8f:	addl %esp, $0x4<UINT8>
0x00408c92:	subl %esi, $0x437d6dd7<UINT32>
0x00408c98:	addl %esi, %eax
0x00408c9a:	pushl %ebp
0x00408c9b:	pushl %esi
0x00408c9c:	movl %esi, $0x437d6dd7<UINT32>
0x00408ca1:	movl %ebp, %esi
0x00408ca3:	popl %esi
0x00408ca4:	addl %esi, %ebp
0x00408ca6:	popl %ebp
0x00408ca7:	pushl (%esp)
0x00408caa:	movl %eax, (%esp)
0x00408cad:	addl %esp, $0x4<UINT8>
0x00408cb0:	addl %esp, $0x4<UINT32>
0x00408cb6:	pushl %ebp
0x00408cb7:	movl %ebp, %esp
0x00408cb9:	addl %ebp, $0x4<UINT32>
0x00408cbf:	pushl %esi
0x00408cc0:	movl %esi, $0x4<UINT32>
0x00408cc5:	subl %ebp, $0x3ed7f3d<UINT32>
0x00408ccb:	subl %ebp, %esi
0x00408ccd:	addl %ebp, $0x3ed7f3d<UINT32>
0x00408cd3:	popl %esi
0x00408cd4:	xorl %ebp, (%esp)
0x00408cd7:	xorl (%esp), %ebp
0x00408cda:	xorl %ebp, (%esp)
0x00408cdd:	popl %esp
0x00408cde:	movl (%esp), %edx
0x00408ce1:	pushl %eax
0x00408ce2:	movl %eax, $0x1ad14c7a<UINT32>
0x00408ce7:	movl %edx, %eax
0x00408ce9:	popl %eax
0x00408cea:	orl %edx, $0x4cc144a4<UINT32>
0x00408cf0:	shrl %edx, $0x2<UINT8>
0x00408cf3:	pushl %edi
0x00408cf4:	pushl %esi
0x00408cf5:	pushl %edx
0x00408cf6:	pushl %edx
0x00408cf7:	movl (%esp), %ebp
0x00408cfa:	movl %ebp, $0x3242552f<UINT32>
0x00408cff:	movl %edx, %ebp
0x00408d01:	popl %ebp
0x00408d02:	pushl %edx
0x00408d03:	xorl (%esp), $0x4ec10faf<UINT32>
0x00408d0a:	movl %esi, (%esp)
0x00408d0d:	addl %esp, $0x4<UINT8>
0x00408d10:	xorl %esi, $0x4ec10faf<UINT32>
0x00408d16:	movl %edx, (%esp)
0x00408d19:	addl %esp, $0x4<UINT32>
0x00408d1f:	movl %edi, $0x6e5968f4<UINT32>
0x00408d24:	subl %edi, %esi
0x00408d26:	popl %esi
0x00408d27:	subl %edx, %edi
0x00408d29:	movl %edi, (%esp)
0x00408d2c:	addl %esp, $0x4<UINT8>
0x00408d2f:	pushl $0x4855<UINT32>
0x00408d34:	movl (%esp), %edi
0x00408d37:	movl %edi, $0x1e38057e<UINT32>
0x00408d3c:	subl %edi, $0x165092d2<UINT32>
0x00408d42:	subl %edx, %edi
0x00408d44:	popl %edi
0x00408d45:	addl %edx, $0x6424e210<UINT32>
0x00408d4b:	subl %esi, $0x7c980114<UINT32>
0x00408d51:	subl %esi, %edx
0x00408d53:	addl %esi, $0x7c980114<UINT32>
0x00408d59:	popl %edx
0x00408d5a:	pushl $0x2729<UINT32>
0x00408d5f:	movl (%esp), %eax
0x00408d62:	movl (%esp), %ecx
0x00408d65:	movl (%esp), %edx
0x00408d68:	movl (%esp), %ebx
0x00408d6b:	pushl %edi
0x00408d6c:	pushl $0xd922f4b<UINT32>
0x00408d71:	movl %edi, (%esp)
0x00408d74:	addl %esp, $0x4<UINT32>
0x00408d7a:	subl %edi, $0x19c01dfc<UINT32>
0x00408d80:	shll %edi, $0x8<UINT8>
0x00408d83:	pushl %ebx
0x00408d84:	movl %ebx, $0xab1a465d<UINT32>
0x00408d89:	subl %edi, %ebx
0x00408d8b:	popl %ebx
0x00408d8c:	movl %ebx, %edi
0x00408d8e:	popl %edi
0x00408d8f:	shll %ebx, $0x8<UINT8>
0x00408d92:	shll %ebx, $0x2<UINT8>
0x00408d95:	addl %ebx, $0x11f617dc<UINT32>
0x00408d9b:	pushl %edi
0x00408d9c:	pushl $0x6e09<UINT32>
0x00408da1:	movl (%esp), %ebx
0x00408da4:	movl %ebx, $0x480815b5<UINT32>
0x00408da9:	decl %ebx
0x00408daa:	addl %ebx, $0x2a6b6657<UINT32>
0x00408db0:	movl %edi, %ebx
0x00408db2:	popl %ebx
0x00408db3:	xorl %ebx, %edi
0x00408db5:	popl %edi
0x00408db6:	subl %esp, $0x4<UINT32>
0x00408dbc:	movl (%esp), %ecx
0x00408dbf:	pushl %ebx
0x00408dc0:	movl %ebx, $0x74c67ece<UINT32>
0x00408dc5:	pushl %ebx
0x00408dc6:	notl (%esp)
0x00408dc9:	popl %ebx
0x00408dca:	shrl %ebx, $0x6<UINT8>
0x00408dcd:	xchgl %ebx, %edi
0x00408dcf:	notl %edi
0x00408dd1:	xchgl %ebx, %edi
0x00408dd3:	notl %ebx
0x00408dd5:	incl %ebx
0x00408dd6:	xorl %ebx, $0x22ce605<UINT32>
0x00408ddc:	movl %ecx, %ebx
0x00408dde:	popl %ebx
0x00408ddf:	subl %ecx, %ebx
0x00408de1:	movl %ebx, %ecx
0x00408de3:	popl %ecx
0x00408de4:	pushl %edi
0x00408de5:	pushl %edx
0x00408de6:	movl %edx, $0x51796148<UINT32>
0x00408deb:	subl %edx, $0x1<UINT8>
0x00408dee:	shrl %edx, $0x4<UINT8>
0x00408df1:	decl %edx
0x00408df2:	xorl %edx, $0x5d04fe3c<UINT32>
0x00408df8:	movl %edi, %edx
0x00408dfa:	popl %edx
0x00408dfb:	xorl %edi, $0x49247b94<UINT32>
0x00408e01:	subl %ebx, %edi
0x00408e03:	popl %edi
0x00408e04:	xorl %esi, %ebx
0x00408e06:	popl %ebx
0x00408e07:	pushl $0x683f<UINT32>
0x00408e0c:	movl (%esp), %esi
0x00408e0f:	popl (%ecx)
0x00408e11:	pushl %edi
0x00408e12:	pushl $0x1bb<UINT32>
0x00408e17:	movl (%esp), %eax
0x00408e1a:	pushl %esi
0x00408e1b:	movl %esi, $0xb4478f1<UINT32>
0x00408e20:	pushl %edi
0x00408e21:	movl %edi, $0x2e35a2ef<UINT32>
0x00408e26:	movl %eax, %edi
0x00408e28:	popl %edi
0x00408e29:	addl %eax, $0x8e029a<UINT32>
0x00408e2e:	subl %eax, %esi
0x00408e30:	subl %eax, $0x8e029a<UINT32>
0x00408e35:	popl %esi
0x00408e36:	andl %eax, $0x2de249fe<UINT32>
0x00408e3b:	shll %eax, $0x8<UINT8>
0x00408e3e:	pushl %edx
0x00408e3f:	movl (%esp), %esi
0x00408e42:	movl %esi, $0xffffffff<UINT32>
0x00408e47:	subl %eax, %esi
0x00408e49:	popl %esi
0x00408e4a:	xorl %eax, $0x5d9a2e14<UINT32>
0x00408e4f:	shrl %eax, $0x4<UINT8>
0x00408e52:	xorl %eax, $0x654a4ab<UINT32>
0x00408e57:	pushl %eax
0x00408e58:	pushl (%esp)
0x00408e5b:	popl %edi
0x00408e5c:	addl %esp, $0x4<UINT8>
0x00408e5f:	pushl (%esp)
0x00408e62:	popl %eax
0x00408e63:	addl %esp, $0x4<UINT8>
0x00408e66:	addl %ecx, $0x61cd6e87<UINT32>
0x00408e6c:	addl %ecx, $0x177c55ce<UINT32>
0x00408e72:	subl %ecx, %edi
0x00408e74:	subl %ecx, $0x177c55ce<UINT32>
0x00408e7a:	subl %ecx, $0x61cd6e87<UINT32>
0x00408e80:	pushl (%esp)
0x00408e83:	pushl (%esp)
0x00408e86:	popl %edi
0x00408e87:	addl %esp, $0x4<UINT8>
0x00408e8a:	addl %esp, $0x4<UINT32>
0x00408e90:	pushl $0x6a98<UINT32>
0x00408e95:	movl (%esp), %edx
0x00408e98:	movl %edx, $0x490b7262<UINT32>
0x00408e9d:	notl %edx
0x00408e9f:	decl %edx
0x00408ea0:	notl %edx
0x00408ea2:	pushl %ebp
0x00408ea3:	movl %ebp, $0x766b1bf3<UINT32>
0x00408ea8:	pushl %ebx
0x00408ea9:	movl %ebx, $0x25d34a31<UINT32>
0x00408eae:	shrl %ebx, $0x6<UINT8>
0x00408eb1:	notl %ebx
0x00408eb3:	xorl %ebx, $0xbd48f12e<UINT32>
0x00408eb9:	addl %ebp, $0x42974b6e<UINT32>
0x00408ebf:	addl %ebp, $0x6a366abd<UINT32>
0x00408ec5:	subl %ebp, %ebx
0x00408ec7:	subl %ebp, $0x6a366abd<UINT32>
0x00408ecd:	subl %ebp, $0x42974b6e<UINT32>
0x00408ed3:	popl %ebx
0x00408ed4:	subl %ebp, $0x300915e7<UINT32>
0x00408eda:	pushl %ecx
0x00408edb:	movl %ecx, $0x603c39d3<UINT32>
0x00408ee0:	xorl %ecx, $0x19690059<UINT32>
0x00408ee6:	addl %ecx, $0xd5dd304a<UINT32>
0x00408eec:	orl %ebp, %ecx
0x00408eee:	popl %ecx
0x00408eef:	andl %ebp, $0x796e7c3b<UINT32>
0x00408ef5:	shrl %ebp, $0x7<UINT8>
0x00408ef8:	pushl %ebx
0x00408ef9:	movl (%esp), %esi
0x00408efc:	movl %esi, $0x6e8e0134<UINT32>
0x00408f01:	notl %esi
0x00408f03:	andl %esi, $0x104c011c<UINT32>
0x00408f09:	shll %esi, $0x4<UINT8>
0x00408f0c:	subl %esi, $0x6cb066a1<UINT32>
0x00408f12:	addl %esi, $0x7cf8f6a7<UINT32>
0x00408f18:	xorl %ebp, %esi
0x00408f1a:	popl %esi
0x00408f1b:	subl %edx, %ebp
0x00408f1d:	movl %ebp, (%esp)
0x00408f20:	addl %esp, $0x4<UINT8>
0x00408f23:	notl %edx
0x00408f25:	pushl %ebx
0x00408f26:	movl %ebx, $0x5dc93c55<UINT32>
0x00408f2b:	subl %ebx, $0x88b26a9<UINT32>
0x00408f31:	shll %ebx, $0x2<UINT8>
0x00408f34:	notl %ebx
0x00408f36:	subl %esp, $0x4<UINT8>
0x00408f39:	movl (%esp), %ecx
0x00408f3c:	movl %ecx, $0x3e6aa684<UINT32>
0x00408f41:	addl %ebx, $0x205c4392<UINT32>
0x00408f47:	subl %ebx, %ecx
0x00408f49:	subl %ebx, $0x205c4392<UINT32>
0x00408f4f:	popl %ecx
0x00408f50:	addl %edx, $0x22435d6b<UINT32>
0x00408f56:	addl %edx, %ebx
0x00408f58:	subl %edx, $0x22435d6b<UINT32>
0x00408f5e:	movl %ebx, (%esp)
0x00408f61:	addl %esp, $0x4<UINT8>
0x00408f64:	subl %edx, $0x2ade4b17<UINT32>
0x00408f6a:	addl %ecx, %edx
0x00408f6c:	pushl (%esp)
0x00408f6f:	popl %edx
0x00408f70:	addl %esp, $0x4<UINT8>
0x00408f73:	subl %eax, $0x1<UINT32>
0x00408f79:	jne 0x00408bc5
0x00408f7f:	sti
0x00408f80:	subl %esp, $0x4<UINT8>
0x00408f83:	movl (%esp), %esi
0x00408f86:	pushl $0x18de6057<UINT32>
0x00408f8b:	movl %esi, (%esp)
0x00408f8e:	addl %esp, $0x4<UINT8>
0x00408f91:	pushl %ebp
0x00408f92:	movl %ebp, $0x1c7909a7<UINT32>
0x00408f97:	addl %ebp, $0x30e3596c<UINT32>
0x00408f9d:	pushl %edi
0x00408f9e:	movl %edi, $0xb388c911<UINT32>
0x00408fa3:	xorl %ebp, %edi
0x00408fa5:	movl %edi, (%esp)
0x00408fa8:	pushl %edi
0x00408fa9:	movl %edi, %esp
0x00408fab:	addl %edi, $0x4<UINT32>
0x00408fb1:	addl %edi, $0x4<UINT32>
0x00408fb7:	xchgl (%esp), %edi
0x00408fba:	popl %esp
0x00408fbb:	addl %esi, %ebp
0x00408fbd:	popl %ebp
0x00408fbe:	pushl %esi
0x00408fbf:	subl %esp, $0x4<UINT32>
0x00408fc5:	movl (%esp), %esi
0x00408fc8:	pushl %ebx
0x00408fc9:	movl %ebx, $0x1a434e8b<UINT32>
0x00408fce:	addl %ebx, $0xefcd1cfe<UINT32>
0x00408fd4:	movl %esi, %ebx
0x00408fd6:	popl %ebx
0x00408fd7:	pushl %ebp
0x00408fd8:	movl (%esp), %eax
0x00408fdb:	movl %eax, $0x8c185272<UINT32>
0x00408fe0:	subl %esi, %eax
0x00408fe2:	movl %eax, (%esp)
0x00408fe5:	addl %esp, $0x4<UINT32>
0x00408feb:	xorl 0x4(%esp), %esi
0x00408fef:	popl %esi
0x00408ff0:	movl %eax, (%esp)
0x00408ff3:	addl %esp, $0x4<UINT32>
0x00408ff9:	pushl %ebx
0x00408ffa:	pushl %edx
0x00408ffb:	movl (%esp), %eax
0x00408ffe:	movl %eax, $0x167d3170<UINT32>
0x00409003:	movl %ebx, $0x270c3ad9<UINT32>
0x00409008:	subl %ebx, %eax
0x0040900a:	popl %eax
0x0040900b:	shrl %ebx, $0x3<UINT8>
0x0040900e:	shrl %ebx, $0x4<UINT8>
0x00409011:	xorl %ebx, $0x7dd90705<UINT32>
0x00409017:	xorl %eax, %ebx
0x00409019:	popl %ebx
0x0040901a:	movl %esi, (%esp)
0x0040901d:	addl %esp, $0x4<UINT8>
0x00409020:	movl %edx, $0x106e9a28<UINT32>
0x00409025:	pushl $0x191e4a62<UINT32>
0x0040902a:	pushl (%esp)
0x0040902d:	movl %ecx, (%esp)
0x00409030:	addl %esp, $0x4<UINT32>
0x00409036:	pushl %esi
0x00409037:	pushl %esp
0x00409038:	popl %esi
0x00409039:	pushl %eax
0x0040903a:	movl %eax, $0x5f9b250e<UINT32>
0x0040903f:	pushl %ecx
0x00409040:	movl %ecx, $0x3c46dad<UINT32>
0x00409045:	xorl %eax, %ecx
0x00409047:	popl %ecx
0x00409048:	shrl %eax, $0x6<UINT8>
0x0040904b:	shrl %eax, $0x2<UINT8>
0x0040904e:	subl %eax, $0x61e92508<UINT32>
0x00409053:	decl %eax
0x00409054:	xorl %eax, $0x9e733a3b<UINT32>
0x00409059:	addl %esi, $0x5b977a62<UINT32>
0x0040905f:	addl %esi, %eax
0x00409061:	subl %esi, $0x5b977a62<UINT32>
0x00409067:	popl %eax
0x00409068:	pushl %eax
0x00409069:	movl %eax, $0x58687674<UINT32>
0x0040906e:	pushl %edx
0x0040906f:	movl %edx, $0x2a701e10<UINT32>
0x00409074:	andl %edx, $0x51e64cde<UINT32>
0x0040907a:	decl %edx
0x0040907b:	subl %edx, $0xffffffff<UINT32>
0x00409081:	subl %edx, $0x58c88280<UINT32>
0x00409087:	subl %eax, $0x6f953f4f<UINT32>
0x0040908c:	addl %eax, %edx
0x0040908e:	addl %eax, $0x6f953f4f<UINT32>
0x00409093:	popl %edx
0x00409094:	addl %esi, %eax
0x00409096:	popl %eax
0x00409097:	pushl %ecx
0x00409098:	movl (%esp), %eax
0x0040909b:	movl %eax, %esp
0x0040909d:	addl %eax, $0x4<UINT32>
0x004090a2:	pushl %esi
0x004090a3:	movl %esi, $0x4<UINT32>
0x004090a8:	subl %eax, %esi
0x004090aa:	popl %esi
0x004090ab:	xorl %eax, (%esp)
0x004090ae:	xorl (%esp), %eax
0x004090b1:	xorl %eax, (%esp)
0x004090b4:	popl %esp
0x004090b5:	movl (%esp), %esi
0x004090b8:	pushl 0x4(%esp)
0x004090bc:	popl %esi
0x004090bd:	popl (%esp)
0x004090c0:	movl %esp, (%esp)
0x004090c3:	jmp 0x004090c8
0x004090c8:	jmp 0x0040944d
0x0040944d:	jmp 0x00409453
0x00409453:	jmp 0x00409afb
0x00409afb:	movl 0x8f6253d(%ebp), $0x0<UINT32>
0x00409b05:	jbe 1
0x00409b0b:	cmc
0x00409b0c:	clc
0x00409b0d:	leal %eax, 0x8f6344a(%ebp)
0x00409b13:	jmp 0x00409b27
0x00409b27:	movl 0x8f61e4c(%ebp), %eax
0x00409b2d:	jmp 0x00409b46
0x00409b46:	leal %eax, 0x8f63967(%ebp)
0x00409b4c:	jb 10
0x00409b52:	pusha
0x00409b53:	subl %ebx, 0x8f632c1(%ebp)
0x00409b59:	movb %dh, $0x28<UINT8>
0x00409b5b:	popa
0x00409b5c:	movl 0x8f627ee(%ebp), %eax
0x00409b62:	pusha
0x00409b63:	movl 0x8f626ed(%ebp), %edi
0x00409b69:	movl 0x8f6276c(%ebp), %eax
0x00409b6f:	popa
0x00409b70:	movb 0x8f61e53(%ebp), $0x56<UINT8>
0x00409b77:	orl 0x8f62102(%ebp), %ebx
0x00409b7d:	movl %eax, $0x9ca8b852<UINT32>
0x00409b82:	pusha
0x00409b83:	movzwl %edx, %dx
0x00409b86:	movw %dx, $0x2a6<UINT16>
0x00409b8a:	popa
0x00409b8b:	pushl %eax
0x00409b8c:	pushl %eax
0x00409b8d:	pushl %eax
0x00409b8e:	jo 0
0x00409b94:	popl %eax
0x00409b95:	popl %eax
0x00409b96:	movl (%esp), %eax
0x00409b99:	jmp 0x00409bb2
0x00409bb2:	pushl 0x8f60285(%ebp)
0x00409bb8:	jno 0x00409bc4
0x00409bc4:	call 0x0040945e
0x0040945e:	pusha
0x0040945f:	cmc
0x00409460:	xorl %eax, %eax
0x00409462:	pusha
0x00409463:	movl %edx, $0x6876f23a<UINT32>
0x00409468:	jmp 0x00409477
0x00409477:	popa
0x00409478:	movl %edx, 0x28(%esp)
0x0040947c:	addl 0x8f61e38(%ebp), %ecx
0x00409482:	movl 0x8f631f1(%ebp), %eax
0x00409488:	movl 0x8f61edf(%ebp), %ebx
0x0040948e:	movl 0x8f61e38(%ebp), %edx
0x00409494:	movl %esi, $0x3c<UINT32>
0x00409499:	js 0x004094a0
0x004094a0:	addl %esi, 0x24(%esp)
0x004094a4:	jle 18
0x004094aa:	jmp 0x004094bc
0x004094bc:	lodsw %ax, %ds:(%esi)
0x004094be:	movl 0x8f61e38(%ebp), %edx
0x004094c4:	addl %eax, 0x24(%esp)
0x004094c8:	orl 0x8f62cdb(%ebp), %eax
0x004094ce:	cmc
0x004094cf:	movl %ebx, 0x24(%esp)
0x004094d3:	clc
0x004094d4:	pushl %eax
0x004094d5:	pushl %eax
0x004094d6:	pushl %edx
0x004094d7:	pushl %ebx
0x004094d8:	pushl %eax
0x004094d9:	pushl %edx
0x004094da:	rdtsc
0x004094dc:	popl %edx
0x004094dd:	popl %eax
0x004094de:	popl %ebx
0x004094df:	rdtsc
0x004094e1:	jne 0x004094e9
0x004094e9:	popl %edx
0x004094ea:	popl %eax
0x004094eb:	movl (%esp), %eax
0x004094ee:	clc
0x004094ef:	movl %eax, 0x78(%eax)
0x004094f2:	pusha
0x004094f3:	movl %edx, %ecx
0x004094f5:	jmp 0x00409504
0x00409504:	popa
0x00409505:	cld
0x00409506:	addl %eax, %ebx
0x00409508:	jg 0x00409516
0x00409516:	movl %eax, 0x18(%eax)
0x00409519:	pusha
0x0040951a:	jmp 0x0040952e
0x0040952e:	movl %edi, 0x8f6333d(%ebp)
0x00409534:	popa
0x00409535:	incl %eax
0x00409536:	subl 0x8f61edf(%ebp), %esi
0x0040953c:	cmc
0x0040953d:	movl 0x8f6343b(%ebp), %eax
0x00409543:	subl 0x8f61edf(%ebp), %edx
0x00409549:	popl %eax
0x0040954a:	jmp 0x0040955f
0x0040955f:	movl %esi, 0x78(%eax)
0x00409562:	movl 0x8f61edf(%ebp), %ecx
0x00409568:	clc
0x00409569:	addl %esi, $0x1c<UINT8>
0x0040956c:	movl 0x8f62cdb(%ebp), %edi
0x00409572:	addl %esi, 0x24(%esp)
0x00409576:	stc
0x00409577:	leal %edi, 0x8f63409(%ebp)
0x0040957d:	jmp 0x0040958a
0x0040958a:	lodsl %eax, %ds:(%esi)
0x0040958b:	cmc
0x0040958c:	addl %eax, 0x24(%esp)
0x00409590:	jmp 0x0040959c
0x0040959c:	stosl %es:(%edi), %eax
0x0040959d:	jmp 0x004095b1
0x004095b1:	leal %edi, 0x8f6324d(%ebp)
0x004095b7:	jno 0x004095c7
0x004095c7:	lodsl %eax, %ds:(%esi)
0x004095c8:	movl 0x8f62cdb(%ebp), %ecx
0x004095ce:	addl %eax, 0x24(%esp)
0x004095d2:	movl 0x8f62cdb(%ebp), %edi
0x004095d8:	movl %ebx, %eax
0x004095da:	movl 0x8f62547(%ebp), %eax
0x004095e0:	stosl %es:(%edi), %eax
0x004095e1:	clc
0x004095e2:	leal %edi, 0x8f633b1(%ebp)
0x004095e8:	jmp 0x004095f8
0x004095f8:	clc
0x004095f9:	lodsl %eax, %ds:(%esi)
0x004095fa:	movl 0x8f61edf(%ebp), %esi
0x00409600:	addl %eax, 0x24(%esp)
0x00409604:	stc
0x00409605:	stosl %es:(%edi), %eax
0x00409606:	jmp 0x00409617
0x00409617:	jmp 0x0040962d
0x0040962d:	movl %esi, %ebx
0x0040962f:	jmp 0x0040963e
0x0040963e:	decl 0x8f6343b(%ebp)
0x00409644:	cmpl 0x8f6343b(%ebp), $0x0<UINT8>
0x0040964b:	jne 0x0040965d
0x0040965d:	movl %ebx, 0x24(%esp)
0x00409661:	pushl %esi
0x00409662:	lodsl %eax, %ds:(%esi)
0x00409663:	addl %eax, %ebx
0x00409665:	xchgl %edi, %eax
0x00409666:	movl %ebx, %edi
0x00409668:	movb %al, 0x8f61e53(%ebp)
0x0040966e:	testb %al, %al
0x00409670:	je 4
0x00409672:	cmpb %al, (%edi)
0x00409674:	jne 0x004096c2
0x004096c2:	popl %esi
0x004096c3:	addl %esi, $0x4<UINT8>
0x004096c6:	incl 0x8f631f1(%ebp)
0x004096cc:	jmp 0x0040963e
0x00409676:	pushl %edi
0x00409677:	xorb %al, %al
0x00409679:	scasb %al, %es:(%edi)
0x0040967a:	jne 0x00409679
0x0040967c:	popl %esi
0x0040967d:	subl %edi, %ebx
0x0040967f:	pushl %edx
0x00409680:	cld
0x00409681:	xorl %ecx, %ecx
0x00409683:	decl %ecx
0x00409684:	movl %edx, %ecx
0x00409686:	xorl %eax, %eax
0x00409688:	xorl %ebx, %ebx
0x0040968a:	lodsb %al, %ds:(%esi)
0x0040968b:	xorb %al, %cl
0x0040968d:	movb %cl, %ch
0x0040968f:	movb %ch, %dl
0x00409691:	movb %dl, %dh
0x00409693:	movb %dh, $0x8<UINT8>
0x00409695:	shrw %bx
0x00409698:	rcrw %ax
0x0040969b:	jae 0x004096a6
0x0040969d:	xorw %ax, $0x5041<UINT16>
0x004096a1:	xorw %bx, $0x5449<UINT16>
0x004096a6:	decb %dh
0x004096a8:	jne 0x00409695
0x004096aa:	xorl %ecx, %eax
0x004096ac:	xorl %edx, %ebx
0x004096ae:	decl %edi
0x004096af:	jne 0x00409686
0x004096b1:	notl %edx
0x004096b3:	notl %ecx
0x004096b5:	movl %eax, %edx
0x004096b7:	roll %eax, $0x10<UINT8>
0x004096ba:	movw %ax, %cx
0x004096bd:	popl %edx
0x004096be:	cmpl %edx, %eax
0x004096c0:	je 0x004096d1
0x004096d1:	popl %esi
0x004096d2:	movl 0x8f62cdb(%ebp), %eax
0x004096d8:	subl 0x8f61e38(%ebp), %esi
0x004096de:	movl %eax, 0x8f631f1(%ebp)
0x004096e4:	jmp 0x004096f8
0x004096f8:	shll %eax
0x004096fa:	xorl 0x8f61edf(%ebp), %edx
0x00409700:	addl %eax, 0x8f633b1(%ebp)
0x00409706:	subl 0x8f61e65(%ebp), %ebx
0x0040970c:	jne 0x00409718
0x00409718:	xorl %esi, %esi
0x0040971a:	jno 0x00409729
0x00409729:	xchgl %esi, %eax
0x0040972a:	movl 0x8f61e38(%ebp), %edx
0x00409730:	lodsw %ax, %ds:(%esi)
0x00409732:	movl 0x8f61e38(%ebp), %eax
0x00409738:	shll %eax, $0x2<UINT8>
0x0040973b:	jmp 0x00409751
0x00409751:	addl %eax, 0x8f63409(%ebp)
0x00409757:	jmp 0x0040976b
0x0040976b:	xchgl %esi, %eax
0x0040976c:	jmp 0x00409778
0x00409778:	lodsl %eax, %ds:(%esi)
0x00409779:	cld
0x0040977a:	movl %ecx, 0x24(%esp)
0x0040977e:	pusha
0x0040977f:	subl %ecx, 0x8f63329(%ebp)
0x00409785:	pushl %esi
0x00409786:	orl %ebx, $0x4736ae7e<UINT32>
0x0040978c:	popl %eax
0x0040978d:	popa
0x0040978e:	addl %eax, 0x24(%esp)
0x00409792:	movl 0x8f61e38(%ebp), %edi
0x00409798:	cmpl (%eax), $0x4c44544e<UINT32>
0x0040979e:	jne 0x00409816
0x00409816:	movl 0x1c(%esp), %eax
0x0040981a:	cld
0x0040981b:	xchgl %esi, %eax
0x0040981c:	jne 0x0040982e
0x0040982e:	pusha
0x0040982f:	xorb %dl, $0x72<UINT8>
0x00409832:	movl %ecx, %edi
0x00409834:	popa
0x00409835:	lodsb %al, %ds:(%esi)
0x00409836:	movl 0x8f6285a(%ebp), %esi
0x0040983c:	stc
0x0040983d:	xorb %cl, %cl
0x0040983f:	orl 0x8f62cdb(%ebp), %esi
0x00409845:	incb %cl
0x00409847:	jmp 0x00409859
0x00409859:	rclb %al
0x0040985b:	jae 230
0x00409861:	jo 0x00409876
0x00409876:	rclb %al
0x00409878:	jae 0x00409947
0x00409947:	movb 0x8f61e53(%ebp), $0x0<UINT8>
0x0040994e:	pusha
0x0040994f:	movl %edx, $0x105978ab<UINT32>
0x00409954:	andl %edx, 0x8f61e53(%ebp)
0x0040995a:	popa
0x0040995b:	popa
0x0040995c:	subl 0x8f62cdb(%ebp), %esi
0x00409962:	ret $0x8<UINT16>

0x00409bca:	stc
0x00409bcb:	movl 0x8f62542(%ebp), %eax
0x00409bd1:	clc
0x00409bd2:	movl %eax, %eax
0x00409bd4:	jmp 0x00409bfe
0x00409bfe:	leal %eax, 0x8f64344(%ebp)
0x00409c04:	movl 0x8f62694(%ebp), %eax
0x00409c0a:	leal %eax, 0x8f64491(%ebp)
0x00409c10:	movl 0x8f61e45(%ebp), %eax
0x00409c16:	cmpl 0x8f63bce(%ebp), $0x0<UINT8>
0x00409c1d:	je 0x0040a2ba
0x0040a2ba:	pushl %eax
0x0040a2bb:	pushl %edi
0x0040a2bc:	movl %edi, $0x1<UINT32>
0x0040a2c1:	subl 0x4(%esp), %edi
0x0040a2c5:	movl %edi, (%esp)
0x0040a2c8:	addl %esp, $0x4<UINT8>
0x0040a2cb:	pushl (%esp)
0x0040a2ce:	movl %eax, (%esp)
0x0040a2d1:	addl %esp, $0x4<UINT32>
0x0040a2d7:	pushl $0x491a<UINT32>
0x0040a2dc:	movl (%esp), %edi
0x0040a2df:	subl %esp, $0x4<UINT32>
0x0040a2e5:	pushl %esp
0x0040a2e6:	popl (%esp)
0x0040a2e9:	pushl %ecx
0x0040a2ea:	movl %ecx, $0x4<UINT32>
0x0040a2ef:	addl 0x4(%esp), %ecx
0x0040a2f3:	movl %ecx, (%esp)
0x0040a2f6:	addl %esp, $0x4<UINT8>
0x0040a2f9:	popl %edi
0x0040a2fa:	addl %edi, $0x4<UINT32>
0x0040a300:	pushl %eax
0x0040a301:	pushl %ebx
0x0040a302:	movl %ebx, $0x4<UINT32>
0x0040a307:	pushl %edx
0x0040a308:	movl %edx, %ebx
0x0040a30a:	pushl %ecx
0x0040a30b:	movl %ecx, %edx
0x0040a30d:	movl %eax, %ecx
0x0040a30f:	popl %ecx
0x0040a310:	popl %edx
0x0040a311:	movl %ebx, (%esp)
0x0040a314:	addl %esp, $0x4<UINT8>
0x0040a317:	addl %edi, %eax
0x0040a319:	popl %eax
0x0040a31a:	xchgl (%esp), %edi
0x0040a31d:	movl %esp, (%esp)
0x0040a320:	leal %eax, 0x8f6453c(%ebp)
0x0040a326:	movl 0x8f6275c(%ebp), %eax
0x0040a32c:	leal %eax, 0x8f645fd(%ebp)
0x0040a332:	movl 0x8f6272e(%ebp), %eax
0x0040a338:	jmp 0x0040a6f5
0x0040a6f5:	jmp 0x0040a8ea
0x0040a8ea:	movl %edx, $0x929<UINT32>
0x0040a8ef:	leal %eax, 0x8f646e6(%ebp)
0x0040a8f5:	movl 0x8f62c5d(%ebp), %eax
0x0040a8fb:	leal %eax, 0x8f64895(%ebp)
0x0040a901:	movl 0x8f61ebb(%ebp), %eax
0x0040a907:	jmp 0x0040b2df
0x0040b2df:	jmp 0x0040b3c1
0x0040b3c1:	pushl %edx
0x0040b3c2:	movl (%esp), %esi
0x0040b3c5:	movl (%esp), %edx
0x0040b3c8:	movl %edx, %esp
0x0040b3ca:	addl %edx, $0x4<UINT32>
0x0040b3d0:	subl %edx, $0x4<UINT8>
0x0040b3d3:	xorl %edx, (%esp)
0x0040b3d6:	xorl (%esp), %edx
0x0040b3d9:	xorl %edx, (%esp)
0x0040b3dc:	popl %esp
0x0040b3dd:	movl (%esp), %ecx
0x0040b3e0:	addl (%esp), $0x5d6f1503<UINT32>
0x0040b3e7:	pushl (%esp)
0x0040b3ea:	pushl (%esp)
0x0040b3ed:	movl %eax, (%esp)
0x0040b3f0:	addl %esp, $0x4<UINT8>
0x0040b3f3:	addl %esp, $0x4<UINT8>
0x0040b3f6:	subl %esp, $0x4<UINT32>
0x0040b3fc:	movl (%esp), %ebp
0x0040b3ff:	pushl $0x73a3<UINT32>
0x0040b404:	movl (%esp), %esp
0x0040b407:	addl (%esp), $0x4<UINT8>
0x0040b40b:	movl %ebp, (%esp)
0x0040b40e:	addl %esp, $0x4<UINT32>
0x0040b414:	addl %ebp, $0x4<UINT32>
0x0040b41a:	addl %ebp, $0x4<UINT32>
0x0040b420:	pushl %ebp
0x0040b421:	pushl 0x4(%esp)
0x0040b425:	pushl (%esp)
0x0040b428:	popl %ebp
0x0040b429:	addl %esp, $0x4<UINT8>
0x0040b42c:	popl (%esp)
0x0040b42f:	movl %esp, (%esp)
0x0040b432:	subl %eax, $0x5d6f1503<UINT32>
0x0040b437:	leal %eax, 0x8f659e8(%ebp)
0x0040b43d:	movl 0x8f61e3f(%ebp), %eax
0x0040b443:	leal %eax, 0x8f688f6(%ebp)
0x0040b449:	movl 0x8f61e5c(%ebp), %eax
0x0040b44f:	leal %edx, 0x8f60d5d(%ebp)
0x0040b455:	movl 0x8f63155(%ebp), %edx
0x0040b45b:	movl 0x8f631f5(%ebp), %edx
0x0040b461:	jmp 0x0040ec94
0x0040ec94:	movl %edx, $0xf0006000<UINT32>
0x0040ec99:	subl %edx, $0xf0000000<UINT32>
0x0040ec9f:	addl %edx, $0x14<UINT8>
0x0040eca2:	movl 0x8f62810(%ebp), %edx
0x0040eca8:	subl %edx, $0x14<UINT8>
0x0040ecab:	movl %eax, %eax
0x0040ecad:	movl %eax, 0x8f60551(%ebp)
0x0040ecb3:	cmpl 0x8f60275(%ebp), $0x0<UINT8>
0x0040ecba:	jne 6
0x0040ecc0:	movl 0x8f60b79(%ebp), %eax
0x0040ecc6:	movl %eax, 0x3c(%eax)
0x0040ecc9:	addl %eax, 0x8f60551(%ebp)
0x0040eccf:	movzwl %edx, 0x6(%eax)
0x0040ecd3:	movl 0x8f62c6e(%ebp), %edx
0x0040ecd9:	movl %edx, 0x50(%eax)
0x0040ecdc:	movl 0x8f627fa(%ebp), %edx
0x0040ece2:	addl %eax, $0xf8<UINT32>
0x0040ece7:	movl %edx, 0xc(%eax)
0x0040ecea:	addl %edx, 0x8f60551(%ebp)
0x0040ecf0:	movl 0x8f61e7e(%ebp), %edx
0x0040ecf6:	movl %ebx, 0x8(%eax)
0x0040ecf9:	addl %edx, %ebx
0x0040ecfb:	movl 0x8f62b15(%ebp), %edx
0x0040ed01:	jmp 0x0040ed19
0x0040ed19:	cmpl 0x8f60275(%ebp), $0x0<UINT8>
0x0040ed20:	je 0x0040ed49
0x0040ed49:	movl %edx, %ebx
0x0040ed4b:	jmp 0x00433564
0x00433564:	jmp 0x0043358d
0x0043358d:	movl %eax, %eax
0x0043358f:	leal %esi, 0x8f8d55d(%ebp)
0x00433595:	addl %esi, $0x4<UINT8>
0x00433598:	cmpl (%esi), $0xffffffff<UINT8>
0x0043359b:	je 0x004335df
0x004335a1:	movl %edi, (%esi)
0x004335a3:	addl %edi, 0x8f60551(%ebp)
0x004335a9:	movl (%edi), %ebp
0x004335ab:	cmpl 0x4(%esi), $0x0<UINT8>
0x004335af:	je 0x004335d7
0x004335d7:	addl %esi, $0xc<UINT8>
0x004335da:	jmp 0x00433598
0x004335df:	movl %eax, %eax
0x004335e1:	pushl $0x29b9<UINT32>
0x004335e6:	movl (%esp), %edx
0x004335e9:	pushl %eax
0x004335ea:	movl (%esp), %esi
0x004335ed:	movl (%esp), %eax
0x004335f0:	pushl $0x7fee6efc<UINT32>
0x004335f5:	popl %eax
0x004335f6:	orl %eax, $0x7863048b<UINT32>
0x004335fb:	orl %eax, $0x15aa7e31<UINT32>
0x00433600:	pushl %esi
0x00433601:	movl %esi, $0x33394282<UINT32>
0x00433606:	addl %esi, $0x4cb5bfd7<UINT32>
0x0043360c:	xorl %eax, %esi
0x0043360e:	popl %esi
0x0043360f:	movl %edx, %eax
0x00433611:	popl %eax
0x00433612:	pushl %esi
0x00433613:	movl (%esp), %edi
0x00433616:	movl %edi, %edx
0x00433618:	pushl %ebx
0x00433619:	movl (%esp), %edi
0x0043361c:	popl %eax
0x0043361d:	movl %edi, (%esp)
0x00433620:	addl %esp, $0x4<UINT32>
0x00433626:	pushl (%esp)
0x00433629:	pushl (%esp)
0x0043362c:	popl %edx
0x0043362d:	addl %esp, $0x4<UINT32>
0x00433633:	pushl %eax
0x00433634:	movl %eax, %esp
0x00433636:	pushl $0x588f<UINT32>
0x0043363b:	movl (%esp), %edi
0x0043363e:	movl %edi, $0x399b483e<UINT32>
0x00433643:	incl %edi
0x00433644:	shll %edi, $0x8<UINT8>
0x00433647:	subl %edi, $0x52631633<UINT32>
0x0043364d:	pushl %eax
0x0043364e:	movl %eax, $0x5e6416f3<UINT32>
0x00433653:	addl %eax, $0x79045238<UINT32>
0x00433658:	notl %eax
0x0043365a:	decl %eax
0x0043365b:	orl %eax, $0x422517c4<UINT32>
0x00433660:	addl %eax, $0x1365d1d<UINT32>
0x00433665:	xorl %eax, $0x2dc1fa50<UINT32>
0x0043366a:	orl %edi, %eax
0x0043366c:	popl %eax
0x0043366d:	shrl %edi, $0x7<UINT8>
0x00433670:	subl %edi, $0x9dda59<UINT32>
0x00433676:	addl %eax, %edi
0x00433678:	popl %edi
0x00433679:	addl %eax, $0x4<UINT32>
0x0043367e:	xorl %eax, (%esp)
0x00433681:	xorl (%esp), %eax
0x00433684:	xorl %eax, (%esp)
0x00433687:	popl %esp
0x00433688:	pushl $0x1302<UINT32>
0x0043368d:	movl (%esp), %eax
0x00433690:	jmp 0x00433695
0x00433695:	call 0x0043369a
0x0043369a:	movl %eax, (%esp)
0x0043369d:	pushl $0x32d0<UINT32>
0x004336a2:	movl (%esp), %eax
0x004336a5:	movl %eax, %esp
0x004336a7:	pushl %ecx
0x004336a8:	pushl %eax
0x004336a9:	pushl $0x2c751c58<UINT32>
0x004336ae:	popl %eax
0x004336af:	addl %eax, $0xd38ae3ac<UINT32>
0x004336b4:	movl %ecx, %eax
0x004336b6:	popl %eax
0x004336b7:	addl %eax, %ecx
0x004336b9:	popl %ecx
0x004336ba:	pushl $0x1401<UINT32>
0x004336bf:	movl (%esp), %ebx
0x004336c2:	pushl %ecx
0x004336c3:	movl %ecx, $0x4<UINT32>
0x004336c8:	movl %ebx, %ecx
0x004336ca:	popl %ecx
0x004336cb:	subl %eax, $0x39b15965<UINT32>
0x004336d0:	addl %eax, %ebx
0x004336d2:	addl %eax, $0x39b15965<UINT32>
0x004336d7:	pushl (%esp)
0x004336da:	popl %ebx
0x004336db:	addl %esp, $0x4<UINT32>
0x004336e1:	pushl %eax
0x004336e2:	pushl 0x4(%esp)
0x004336e6:	pushl (%esp)
0x004336e9:	movl %eax, (%esp)
0x004336ec:	addl %esp, $0x4<UINT32>
0x004336f2:	pushl %esi
0x004336f3:	pushl %esi
0x004336f4:	movl (%esp), %esp
0x004336f7:	addl (%esp), $0x4<UINT8>
0x004336fb:	popl %esi
0x004336fc:	addl %esi, $0x4<UINT32>
0x00433702:	addl %esi, $0x4<UINT32>
0x00433708:	xchgl (%esp), %esi
0x0043370b:	movl %esp, (%esp)
0x0043370e:	popl (%esp)
0x00433711:	movl %esp, (%esp)
0x00433714:	sti
0x00433715:	pushl %ecx
0x00433716:	movl %ecx, $0x38895552<UINT32>
0x0043371b:	pushl %edi
0x0043371c:	movl %edi, $0x1a9d72ad<UINT32>
0x00433721:	notl %edi
0x00433723:	shll %edi, $0x7<UINT8>
0x00433726:	shrl %edi, $0x8<UINT8>
0x00433729:	subl %edi, $0x60472682<UINT32>
0x0043372f:	shrl %edi, $0x4<UINT8>
0x00433732:	addl %edi, $0x6bccf3b<UINT32>
0x00433738:	xorl %ecx, %edi
0x0043373a:	popl %edi
0x0043373b:	pushl %ecx
0x0043373c:	notl (%esp)
0x0043373f:	popl %ecx
0x00433740:	incl %ecx
0x00433741:	addl %ecx, $0x4bc787d2<UINT32>
0x00433747:	addl %eax, %ecx
0x00433749:	movl %ecx, (%esp)
0x0043374c:	pushl %edi
0x0043374d:	movl %edi, %esp
0x0043374f:	addl %edi, $0x4<UINT32>
0x00433755:	addl %edi, $0x4<UINT32>
0x0043375b:	xchgl (%esp), %edi
0x0043375e:	popl %esp
0x0043375f:	pushl %eax
0x00433760:	movl (%esp), %ebx
0x00433763:	movl %ebx, $0x660717cc<UINT32>
0x00433768:	xorl %ebx, $0x36c6782d<UINT32>
0x0043376e:	subl %ebx, $0x51f935db<UINT32>
0x00433774:	addl %ebx, $0x458c1e4e<UINT32>
0x0043377a:	shrl %ebx, $0x2<UINT8>
0x0043377d:	orl %ebx, $0x133031b7<UINT32>
0x00433783:	addl %ebx, $0xed0b231c<UINT32>
0x00433789:	addl %eax, %ebx
0x0043378b:	popl %ebx
0x0043378c:	addl %eax, $0x7ed85f2d<UINT32>
0x00433791:	addl %eax, (%esp)
0x00433794:	subl %eax, $0x7ed85f2d<UINT32>
0x00433799:	subl %eax, $0x405ad3<UINT32>
0x0043379e:	pushl $0x7d89<UINT32>
0x004337a3:	movl (%esp), %edx
0x004337a6:	pushl %ebx
0x004337a7:	pushl %edx
0x004337a8:	movl %edx, $0x790e2e02<UINT32>
0x004337ad:	shll %edx, $0x5<UINT8>
0x004337b0:	pushl %edx
0x004337b1:	xchgl (%esp), %eax
0x004337b4:	notl %eax
0x004337b6:	xchgl (%esp), %eax
0x004337b9:	popl %edx
0x004337ba:	subl %edx, $0xbd54ea14<UINT32>
0x004337c0:	pushl %edx
0x004337c1:	subl (%esp), $0x485259e2<UINT32>
0x004337c8:	popl %ebx
0x004337c9:	addl %ebx, $0x485259e2<UINT32>
0x004337cf:	popl %edx
0x004337d0:	movl %edx, $0x4462b90e<UINT32>
0x004337d5:	subl %edx, %ebx
0x004337d7:	popl %ebx
0x004337d8:	subl %eax, %edx
0x004337da:	pushl (%esp)
0x004337dd:	popl %edx
0x004337de:	addl %esp, $0x4<UINT32>
0x004337e4:	pushl $0x67b3<UINT32>
0x004337e9:	movl (%esp), %eax
0x004337ec:	movl (%esp), %edx
0x004337ef:	pushl %esp
0x004337f0:	popl %edx
0x004337f1:	pushl %edx
0x004337f2:	movl (%esp), %ecx
0x004337f5:	pushl %eax
0x004337f6:	movl %eax, $0x73ed4562<UINT32>
0x004337fb:	addl %eax, $0x69244d26<UINT32>
0x00433800:	xorl %eax, $0x4bf7361b<UINT32>
0x00433805:	xorl %eax, $0x96e6a497<UINT32>
0x0043380a:	movl %ecx, %eax
0x0043380c:	popl %eax
0x0043380d:	addl %edx, $0x2c8c0642<UINT32>
0x00433813:	addl %edx, %ecx
0x00433815:	subl %edx, $0x2c8c0642<UINT32>
0x0043381b:	pushl (%esp)
0x0043381e:	movl %ecx, (%esp)
0x00433821:	addl %esp, $0x4<UINT8>
0x00433824:	addl %esp, $0x4<UINT32>
0x0043382a:	addl %edx, $0x4<UINT8>
0x0043382d:	pushl %esi
0x0043382e:	movl (%esp), %edx
0x00433831:	pushl 0x4(%esp)
0x00433835:	pushl (%esp)
0x00433838:	popl %edx
0x00433839:	pushl %ebx
0x0043383a:	pushl $0x3525<UINT32>
0x0043383f:	movl (%esp), %esp
0x00433842:	addl (%esp), $0x4<UINT32>
0x00433849:	popl %ebx
0x0043384a:	addl %ebx, $0x4<UINT32>
0x00433850:	addl %ebx, $0x4<UINT8>
0x00433853:	xorl %ebx, (%esp)
0x00433856:	xorl (%esp), %ebx
0x00433859:	xorl %ebx, (%esp)
0x0043385c:	popl %esp
0x0043385d:	popl (%esp)
0x00433860:	movl %esp, (%esp)
0x00433863:	sti
0x00433864:	xorl %ecx, %ecx
0x00433866:	pushl (%eax,%ecx)
0x00433869:	pushl $0x5268<UINT32>
0x0043386e:	movl (%esp), %eax
0x00433871:	movl (%esp), %ebp
0x00433874:	movl (%esp), %ebx
0x00433877:	movl (%esp), %edi
0x0043387a:	movl %edi, $0x81e5b4d<UINT32>
0x0043387f:	addl 0x4(%esp), $0x66d668c7<UINT32>
0x00433887:	addl 0x4(%esp), %edi
0x0043388b:	subl 0x4(%esp), $0x66d668c7<UINT32>
0x00433893:	movl %edi, (%esp)
0x00433896:	pushl %ebx
0x00433897:	movl (%esp), %edx
0x0043389a:	movl %edx, %esp
0x0043389c:	addl %edx, $0x4<UINT32>
0x004338a2:	addl %edx, $0x4<UINT32>
0x004338a8:	xorl %edx, (%esp)
0x004338ab:	xorl (%esp), %edx
0x004338ae:	xorl %edx, (%esp)
0x004338b1:	movl %esp, (%esp)
0x004338b4:	pushl (%esp)
0x004338b7:	pushl (%esp)
0x004338ba:	movl %esi, (%esp)
0x004338bd:	addl %esp, $0x4<UINT8>
0x004338c0:	pushl %ebx
0x004338c1:	movl (%esp), %ecx
0x004338c4:	movl %ecx, %esp
0x004338c6:	addl %ecx, $0x4<UINT32>
0x004338cc:	addl %ecx, $0x4<UINT32>
0x004338d2:	xchgl (%esp), %ecx
0x004338d5:	popl %esp
0x004338d6:	pushl %eax
0x004338d7:	movl %eax, %esp
0x004338d9:	addl %eax, $0x4<UINT32>
0x004338de:	addl %eax, $0x4<UINT32>
0x004338e3:	xchgl (%esp), %eax
0x004338e6:	popl %esp
0x004338e7:	subl %esi, $0x81e5b4d<UINT32>
0x004338ed:	pushl $0x2ca3<UINT32>
0x004338f2:	movl (%esp), %ebp
0x004338f5:	pushl %ecx
0x004338f6:	movl %ecx, %esp
0x004338f8:	addl %ecx, $0x4<UINT32>
0x004338fe:	subl %ecx, $0x4<UINT8>
0x00433901:	xchgl (%esp), %ecx
0x00433904:	popl %esp
0x00433905:	movl (%esp), %esi
0x00433908:	movl %esi, $0x4d827a0<UINT32>
0x0043390d:	movl %ebp, %esi
0x0043390f:	popl %esi
0x00433910:	notl %ebp
0x00433912:	shrl %ebp, $0x6<UINT8>
0x00433915:	shrl %ebp, $0x8<UINT8>
0x00433918:	pushl %ebx
0x00433919:	movl %ebx, $0x13160fe1<UINT32>
0x0043391e:	xorl %ebp, %ebx
0x00433920:	popl %ebx
0x00433921:	pushl %ebx
0x00433922:	pushl %ecx
0x00433923:	movl %ecx, $0x6bc50035<UINT32>
0x00433928:	xorl %ecx, $0xce81922<UINT32>
0x0043392e:	movl %ebx, %ecx
0x00433930:	movl %ecx, (%esp)
0x00433933:	addl %esp, $0x4<UINT32>
0x00433939:	pushl %ebp
0x0043393a:	pushl %edi
0x0043393b:	movl (%esp), %edx
0x0043393e:	movl %edx, $0x1b9d6045<UINT32>
0x00433943:	movl %ebp, %edx
0x00433945:	movl %edx, (%esp)
0x00433948:	addl %esp, $0x4<UINT32>
0x0043394e:	subl %ebp, $0x99fbc4f3<UINT32>
0x00433954:	xorl %ebx, %ebp
0x00433956:	movl %ebp, (%esp)
0x00433959:	addl %esp, $0x4<UINT32>
0x0043395f:	subl %ebp, %ebx
0x00433961:	popl %ebx
0x00433962:	pushl %eax
0x00433963:	pushl %esi
0x00433964:	movl %esi, $0x3f70723c<UINT32>
0x00433969:	movl %eax, $0x5ff019fc<UINT32>
0x0043396e:	xorl %eax, %esi
0x00433970:	popl %esi
0x00433971:	pushl %edi
0x00433972:	movl %edi, $0x177e2944<UINT32>
0x00433977:	xorl %eax, %edi
0x00433979:	popl %edi
0x0043397a:	addl %eax, $0xa58630e6<UINT32>
0x0043397f:	addl %esi, $0x5f383110<UINT32>
0x00433985:	addl %esi, %eax
0x00433987:	subl %esi, $0x5f383110<UINT32>
0x0043398d:	popl %eax
0x0043398e:	subl %esi, %ebp
0x00433990:	subl %esi, $0x1d84736a<UINT32>
0x00433996:	movl %ebp, (%esp)
0x00433999:	addl %esp, $0x4<UINT32>
0x0043399f:	pushl %esi
0x004339a0:	movl (%esp), %ecx
0x004339a3:	movl %ecx, $0x4dc87400<UINT32>
0x004339a8:	pushl %edx
0x004339a9:	movl %edx, $0x41fa40c2<UINT32>
0x004339ae:	decl %edx
0x004339af:	subl %edx, $0xf3453ac<UINT32>
0x004339b5:	xorl %edx, $0x4e82b99e<UINT32>
0x004339bb:	subl %esi, %edx
0x004339bd:	popl %edx
0x004339be:	subl %esi, %ecx
0x004339c0:	pushl %edx
0x004339c1:	pushl %ebx
0x004339c2:	movl %ebx, $0x33ad795b<UINT32>
0x004339c7:	movl %edx, %ebx
0x004339c9:	popl %ebx
0x004339ca:	pushl $0x0<UINT32>
0x004339cf:	subl (%esp), %edx
0x004339d2:	popl %edx
0x004339d3:	addl %edx, $0xaff4cde6<UINT32>
0x004339d9:	pushl %ecx
0x004339da:	pushl $0x6815<UINT32>
0x004339df:	movl (%esp), %esi
0x004339e2:	movl %esi, $0x5ce959d9<UINT32>
0x004339e7:	pushl %esi
0x004339e8:	movl %esi, $0x555e4ceb<UINT32>
0x004339ed:	pushl %edx
0x004339ee:	movl %edx, %esi
0x004339f0:	movl %ecx, %edx
0x004339f2:	movl %edx, (%esp)
0x004339f5:	addl %esp, $0x4<UINT8>
0x004339f8:	popl %esi
0x004339f9:	xorl %ecx, %esi
0x004339fb:	pushl (%esp)
0x004339fe:	popl %esi
0x004339ff:	addl %esp, $0x4<UINT8>
0x00433a02:	addl %esi, $0x4fb4554d<UINT32>
0x00433a08:	subl %esi, $0x747723ef<UINT32>
0x00433a0e:	subl %esi, %ecx
0x00433a10:	pushl %eax
0x00433a11:	movl %eax, $0x167d2c43<UINT32>
0x00433a16:	shll %eax
0x00433a18:	orl %eax, $0x719924ed<UINT32>
0x00433a1d:	xorl %eax, $0x24f150f2<UINT32>
0x00433a22:	shll %eax, $0x7<UINT8>
0x00433a25:	subl %eax, $0x109eea91<UINT32>
0x00433a2a:	addl %esi, %eax
0x00433a2c:	popl %eax
0x00433a2d:	subl %esi, $0x4fb4554d<UINT32>
0x00433a33:	popl %ecx
0x00433a34:	addl %esi, %edx
0x00433a36:	addl %esi, $0x9b71532<UINT32>
0x00433a3c:	movl %edx, (%esp)
0x00433a3f:	addl %esp, $0x4<UINT8>
0x00433a42:	popl %ecx
0x00433a43:	pushl $0xb8f<UINT32>
0x00433a48:	movl (%esp), %ebx
0x00433a4b:	pushl %ecx
0x00433a4c:	pushl %esi
0x00433a4d:	pushl %ebx
0x00433a4e:	pushl $0x7a1548b2<UINT32>
0x00433a53:	popl %ebx
0x00433a54:	xorl %ebx, $0x4b9b23f4<UINT32>
0x00433a5a:	addl %ebx, $0xcc1f0dd<UINT32>
0x00433a60:	movl %esi, %ebx
0x00433a62:	popl %ebx
0x00433a63:	pushl %ebx
0x00433a64:	movl %ebx, $0x66ce61dc<UINT32>
0x00433a69:	subl %ebx, $0x6bce1805<UINT32>
0x00433a6f:	xorl %ebx, $0x46d8dad0<UINT32>
0x00433a75:	movl %ecx, %ebx
0x00433a77:	popl %ebx
0x00433a78:	subl %ecx, %esi
0x00433a7a:	popl %esi
0x00433a7b:	movl %ebx, $0xb00d7093<UINT32>
0x00433a80:	addl %ebx, %ecx
0x00433a82:	popl %ecx
0x00433a83:	xorl %esi, %ebx
0x00433a85:	pushl (%esp)
0x00433a88:	popl %ebx
0x00433a89:	pushl %edx
0x00433a8a:	movl %edx, %esp
0x00433a8c:	pushl %ebx
0x00433a8d:	movl %ebx, $0x1fa01c5f<UINT32>
0x00433a92:	subl %ebx, $0x15be6e94<UINT32>
0x00433a98:	shll %ebx, $0x3<UINT8>
0x00433a9b:	pushl %esi
0x00433a9c:	movl %esi, $0x3b0c29e8<UINT32>
0x00433aa1:	decl %esi
0x00433aa2:	xorl %esi, $0xbe80440<UINT32>
0x00433aa8:	andl %esi, $0x9cf6248<UINT32>
0x00433aae:	subl %esi, $0x4fd18e54<UINT32>
0x00433ab4:	addl %ebx, %esi
0x00433ab6:	popl %esi
0x00433ab7:	addl %edx, %ebx
0x00433ab9:	popl %ebx
0x00433aba:	pushl %ebx
0x00433abb:	pushl %edi
0x00433abc:	movl %edi, $0x4<UINT32>
0x00433ac1:	pushl %edi
0x00433ac2:	popl %ebx
0x00433ac3:	popl %edi
0x00433ac4:	pushl %esi
0x00433ac5:	movl %esi, $0x4051770d<UINT32>
0x00433aca:	subl %edx, %esi
0x00433acc:	popl %esi
0x00433acd:	addl %edx, %ebx
0x00433acf:	pushl %edi
0x00433ad0:	pushl %ecx
0x00433ad1:	movl %ecx, $0x4051770d<UINT32>
0x00433ad6:	movl %edi, %ecx
0x00433ad8:	popl %ecx
0x00433ad9:	addl %edx, %edi
0x00433adb:	popl %edi
0x00433adc:	popl %ebx
0x00433add:	xorl %edx, (%esp)
0x00433ae0:	xorl (%esp), %edx
0x00433ae3:	xorl %edx, (%esp)
0x00433ae6:	popl %esp
0x00433ae7:	pushl $0x7d11<UINT32>
0x00433aec:	movl (%esp), %eax
0x00433aef:	subl %esp, $0x4<UINT32>
0x00433af5:	pushl %esi
0x00433af6:	movl %esi, %esp
0x00433af8:	addl %esi, $0x4<UINT32>
0x00433afe:	subl %esi, $0x4<UINT32>
0x00433b04:	xchgl (%esp), %esi
0x00433b07:	popl %esp
0x00433b08:	movl (%esp), %esp
0x00433b0b:	addl (%esp), $0x4<UINT8>
0x00433b0f:	popl (%esp)
0x00433b12:	pushl %ebp
0x00433b13:	movl %ebp, $0x4<UINT32>
0x00433b18:	addl 0x4(%esp), %ebp
0x00433b1c:	popl %ebp
0x00433b1d:	popl %eax
0x00433b1e:	pushl %esi
0x00433b1f:	movl %esi, $0x4<UINT32>
0x00433b24:	pushl %edx
0x00433b25:	movl %edx, $0x65664e44<UINT32>
0x00433b2a:	decl %edx
0x00433b2b:	notl %edx
0x00433b2d:	negl %edx
0x00433b2f:	addl %edx, $0xdfc0e77f<UINT32>
0x00433b35:	addl %eax, %edx
0x00433b37:	popl %edx
0x00433b38:	addl %eax, %esi
0x00433b3a:	pushl $0x6043<UINT32>
0x00433b3f:	movl (%esp), %edi
0x00433b42:	movl %edi, $0xdc32859<UINT32>
0x00433b47:	xorl %edi, $0x48e41d9a<UINT32>
0x00433b4d:	subl %eax, %edi
0x00433b4f:	movl %edi, (%esp)
0x00433b52:	addl %esp, $0x4<UINT8>
0x00433b55:	movl %esi, (%esp)
0x00433b58:	addl %esp, $0x4<UINT8>
0x00433b5b:	pushl %edi
0x00433b5c:	movl %edi, $0x4<UINT32>
0x00433b61:	subl %eax, %edi
0x00433b63:	popl %edi
0x00433b64:	pushl $0x2437<UINT32>
0x00433b69:	movl (%esp), %eax
0x00433b6c:	pushl 0x4(%esp)
0x00433b70:	popl %eax
0x00433b71:	popl (%esp)
0x00433b74:	movl %esp, (%esp)
0x00433b77:	movl (%esp), %esi
0x00433b7a:	popl (%eax,%ecx)
0x00433b7d:	pushl %ebp
0x00433b7e:	movl %ebp, %esp
0x00433b80:	addl %ebp, $0x4<UINT32>
0x00433b86:	subl %ebp, $0x4<UINT8>
0x00433b89:	xchgl (%esp), %ebp
0x00433b8c:	popl %esp
0x00433b8d:	movl (%esp), %edx
0x00433b90:	pushl %ebx
0x00433b91:	pushl %eax
0x00433b92:	movl %eax, $0x7fba6b58<UINT32>
0x00433b97:	notl %eax
0x00433b99:	pushl %ecx
0x00433b9a:	movl %ecx, $0x5edf3338<UINT32>
0x00433b9f:	xorl %eax, %ecx
0x00433ba1:	popl %ecx
0x00433ba2:	xorl %eax, $0x9dd5f264<UINT32>
0x00433ba7:	pushl %eax
0x00433ba8:	popl %ebx
0x00433ba9:	popl %eax
0x00433baa:	movl %edx, $0x434f55f8<UINT32>
0x00433baf:	xorl %edx, %ebx
0x00433bb1:	movl %ebx, (%esp)
0x00433bb4:	pushl %ecx
0x00433bb5:	movl %ecx, %esp
0x00433bb7:	addl %ecx, $0x4<UINT32>
0x00433bbd:	addl %ecx, $0x4<UINT32>
0x00433bc3:	pushl %ecx
0x00433bc4:	pushl 0x4(%esp)
0x00433bc8:	movl %ecx, (%esp)
0x00433bcb:	addl %esp, $0x4<UINT8>
0x00433bce:	popl (%esp)
0x00433bd1:	popl %esp
0x00433bd2:	pushl %edx
0x00433bd3:	movl %edx, $0x4c15692a<UINT32>
0x00433bd8:	shll %edx, $0x3<UINT8>
0x00433bdb:	pushl %edi
0x00433bdc:	movl %edi, $0x7c46439b<UINT32>
0x00433be1:	xorl %edx, %edi
0x00433be3:	movl %edi, (%esp)
0x00433be6:	addl %esp, $0x4<UINT32>
0x00433bec:	subl %ecx, %edx
0x00433bee:	movl %edx, (%esp)
0x00433bf1:	pushl %ebp
0x00433bf2:	movl %ebp, %esp
0x00433bf4:	addl %ebp, $0x4<UINT32>
0x00433bfa:	addl %ebp, $0x4<UINT8>
0x00433bfd:	xchgl (%esp), %ebp
0x00433c00:	popl %esp
0x00433c01:	subl %ecx, %edx
0x00433c03:	subl %esp, $0x4<UINT8>
0x00433c06:	movl (%esp), %ebx
0x00433c09:	movl %ebx, $0x1ced0acb<UINT32>
0x00433c0e:	addl %ecx, %ebx
0x00433c10:	popl %ebx
0x00433c11:	movl %edx, (%esp)
0x00433c14:	addl %esp, $0x4<UINT8>
0x00433c17:	pushl %edx
0x00433c18:	pushl %esi
0x00433c19:	pushl $0x2e691fba<UINT32>
0x00433c1e:	movl %esi, (%esp)
0x00433c21:	pushl %ebx
0x00433c22:	pushl %esp
0x00433c23:	popl %ebx
0x00433c24:	addl %ebx, $0x4<UINT32>
0x00433c2a:	addl %ebx, $0x4<UINT8>
0x00433c2d:	xorl %ebx, (%esp)
0x00433c30:	xorl (%esp), %ebx
0x00433c33:	xorl %ebx, (%esp)
0x00433c36:	popl %esp
0x00433c37:	shll %esi, $0x5<UINT8>
0x00433c3a:	subl %esp, $0x4<UINT32>
0x00433c40:	movl (%esp), %ebx
0x00433c43:	movl %ebx, $0x32dc08c1<UINT32>
0x00433c48:	addl %esi, %ebx
0x00433c4a:	movl %ebx, (%esp)
0x00433c4d:	addl %esp, $0x4<UINT8>
0x00433c50:	pushl %ebp
0x00433c51:	pushl %esi
0x00433c52:	xorl (%esp), $0x5f5677ad<UINT32>
0x00433c59:	popl %ebp
0x00433c5a:	xorl %ebp, $0x5f5677ad<UINT32>
0x00433c60:	movl %edx, %ebp
0x00433c62:	movl %ebp, (%esp)
0x00433c65:	pushl %ecx
0x00433c66:	movl %ecx, %esp
0x00433c68:	addl %ecx, $0x4<UINT32>
0x00433c6e:	addl %ecx, $0x4<UINT32>
0x00433c74:	xchgl (%esp), %ecx
0x00433c77:	popl %esp
0x00433c78:	movl %esi, (%esp)
0x00433c7b:	addl %esp, $0x4<UINT32>
0x00433c81:	subl %ecx, $0x514f6f34<UINT32>
0x00433c87:	subl %ecx, %edx
0x00433c89:	pushl %ebx
0x00433c8a:	movl %ebx, $0x514f6f34<UINT32>
0x00433c8f:	addl %ecx, %ebx
0x00433c91:	popl %ebx
0x00433c92:	movl %edx, (%esp)
0x00433c95:	pushl %eax
0x00433c96:	movl %eax, %esp
0x00433c98:	addl %eax, $0x4<UINT32>
0x00433c9d:	addl %eax, $0x4<UINT8>
0x00433ca0:	xorl %eax, (%esp)
0x00433ca3:	xorl (%esp), %eax
0x00433ca6:	xorl %eax, (%esp)
0x00433ca9:	popl %esp
0x00433caa:	cmpl %ecx, $0xffff8b6c<UINT32>
0x00433cb0:	jne 0x00433866
0x00433cb6:	sti
0x00433cb7:	pushl %ebp
0x00433cb8:	pushl %ecx
0x00433cb9:	movl %ecx, $0xb416a52<UINT32>
0x00433cbe:	xorl %ecx, $0x6b5a6bf8<UINT32>
0x00433cc4:	subl %ecx, $0x519c4769<UINT32>
0x00433cca:	shrl %ecx, $0x2<UINT8>
0x00433ccd:	xorl %ecx, $0x5d138f5d<UINT32>
0x00433cd3:	pushl %ecx
0x00433cd4:	xorl (%esp), $0x301054b8<UINT32>
0x00433cdb:	movl %ebp, (%esp)
0x00433cde:	addl %esp, $0x4<UINT8>
0x00433ce1:	xorl %ebp, $0x301054b8<UINT32>
0x00433ce7:	popl %ecx
0x00433ce8:	subl %esp, $0x4<UINT8>
0x00433ceb:	movl (%esp), %eax
0x00433cee:	movl %eax, $0x738a2338<UINT32>
0x00433cf3:	shrl %eax, $0x2<UINT8>
0x00433cf6:	notl %eax
0x00433cf8:	pushl %esi
0x00433cf9:	movl %esi, $0x4d4e7245<UINT32>
0x00433cfe:	addl %esi, $0x55e5000c<UINT32>
0x00433d04:	subl %esi, $0x6e3a7b2f<UINT32>
0x00433d0a:	addl %eax, %esi
0x00433d0c:	popl %esi
0x00433d0d:	xorl %ebp, %eax
0x00433d0f:	popl %eax
0x00433d10:	addl %ebp, $0xffffffff<UINT8>
0x00433d13:	pushl %ebx
0x00433d14:	pushl $0x5ff07b71<UINT32>
0x00433d19:	popl %ebx
0x00433d1a:	andl %ebp, %ebx
0x00433d1c:	popl %ebx
0x00433d1d:	subl %ebp, $0x431e454f<UINT32>
0x00433d23:	shll %ebp, $0x7<UINT8>
0x00433d26:	xorl %ebp, $0xf153ab2f<UINT32>
0x00433d2c:	pushl %edx
0x00433d2d:	movl (%esp), %esi
0x00433d30:	movl (%esp), %ebx
0x00433d33:	movl %ebx, %ebp
0x00433d35:	pushl %ebx
0x00433d36:	popl %eax
0x00433d37:	pushl (%esp)
0x00433d3a:	popl %ebx
0x00433d3b:	addl %esp, $0x4<UINT8>
0x00433d3e:	pushl (%esp)
0x00433d41:	popl %ebp
0x00433d42:	addl %esp, $0x4<UINT8>
0x00433d45:	pushl %ebx
0x00433d46:	movl %ebx, %esp
0x00433d48:	addl %ebx, $0x4<UINT32>
0x00433d4e:	pushl %edi
0x00433d4f:	movl %edi, $0x4<UINT32>
0x00433d54:	subl %ebx, %edi
0x00433d56:	popl %edi
0x00433d57:	subl %esp, $0x4<UINT8>
0x00433d5a:	movl (%esp), %ebx
0x00433d5d:	pushl 0x4(%esp)
0x00433d61:	movl %ebx, (%esp)
0x00433d64:	addl %esp, $0x4<UINT8>
0x00433d67:	popl (%esp)
0x00433d6a:	popl %esp
0x00433d6b:	movl (%esp), %ebp
0x00433d6e:	pushl $0x70b54090<UINT32>
0x00433d73:	pushl (%esp)
0x00433d76:	popl %ebp
0x00433d77:	pushl %ecx
0x00433d78:	pushl %esi
0x00433d79:	movl (%esp), %esp
0x00433d7c:	addl (%esp), $0x4<UINT32>
0x00433d83:	movl %ecx, (%esp)
0x00433d86:	addl %esp, $0x4<UINT8>
0x00433d89:	pushl %esi
0x00433d8a:	movl %esi, $0x63997aaa<UINT32>
0x00433d8f:	addl %esi, $0x51256fc1<UINT32>
0x00433d95:	addl %esi, $0x26134e5e<UINT32>
0x00433d9b:	negl %esi
0x00433d9d:	incl %esi
0x00433d9e:	xorl %esi, $0x252dc73c<UINT32>
0x00433da4:	addl %ecx, %esi
0x00433da6:	popl %esi
0x00433da7:	addl %ecx, $0x4<UINT8>
0x00433daa:	xorl %ecx, (%esp)
0x00433dad:	xorl (%esp), %ecx
0x00433db0:	xorl %ecx, (%esp)
0x00433db3:	popl %esp
0x00433db4:	pushl %ebx
0x00433db5:	movl %ebx, $0x384c694c<UINT32>
0x00433dba:	addl %ebp, %ebx
0x00433dbc:	movl %ebx, (%esp)
0x00433dbf:	addl %esp, $0x4<UINT8>
0x00433dc2:	shll %ebp, $0x3<UINT8>
0x00433dc5:	addl %ebp, $0x65970964<UINT32>
0x00433dcb:	pushl %edx
0x00433dcc:	pushl $0x8a914262<UINT32>
0x00433dd1:	popl %edx
0x00433dd2:	addl %ebp, %edx
0x00433dd4:	popl %edx
0x00433dd5:	movl %eax, %ebp
0x00433dd7:	movl %ebp, (%esp)
0x00433dda:	addl %esp, $0x4<UINT8>
0x00433ddd:	pushl %ecx
0x00433dde:	movl (%esp), %esi
0x00433de1:	pushl %ebx
0x00433de2:	movl %ebx, $0x6d4d5950<UINT32>
0x00433de7:	movl %esi, %ebx
0x00433de9:	popl %ebx
0x00433dea:	andl %esi, $0x135d6b69<UINT32>
0x00433df0:	negl %esi
0x00433df2:	pushl %edx
0x00433df3:	pushl $0x2fd6562f<UINT32>
0x00433df8:	popl %edx
0x00433df9:	orl %edx, $0x2b923e7c<UINT32>
0x00433dff:	pushl %ecx
0x00433e00:	movl %ecx, $0xdae18c7<UINT32>
0x00433e05:	addl %edx, %ecx
0x00433e07:	popl %ecx
0x00433e08:	andl %edx, $0x684e0f8a<UINT32>
0x00433e0e:	xorl %edx, $0x5e1b16de<UINT32>
0x00433e14:	subl %esi, %edx
0x00433e16:	popl %edx
0x00433e17:	pushl %ecx
0x00433e18:	movl %ecx, $0x48ce6477<UINT32>
0x00433e1d:	shll %ecx
0x00433e1f:	pushl %edi
0x00433e20:	movl %edi, $0x35302d94<UINT32>
0x00433e25:	pushl %ebp
0x00433e26:	pushl %ebx
0x00433e27:	pushl $0x485c1f4b<UINT32>
0x00433e2c:	popl %ebx
0x00433e2d:	notl %ebx
0x00433e2f:	subl %ebx, $0x4400621f<UINT32>
0x00433e35:	andl %ebx, $0x32217d29<UINT32>
0x00433e3b:	subl %ebx, $0x99a3a64<UINT32>
0x00433e41:	movl %ebp, %ebx
0x00433e43:	popl %ebx
0x00433e44:	addl %ebp, $0x571025c4<UINT32>
0x00433e4a:	xorl %edi, %ebp
0x00433e4c:	popl %ebp
0x00433e4d:	xorl %edi, $0x16f416bf<UINT32>
0x00433e53:	pushl %eax
0x00433e54:	movl %eax, $0x1f644650<UINT32>
0x00433e59:	addl %edi, %eax
0x00433e5b:	popl %eax
0x00433e5c:	xorl %edi, $0x36b2ba13<UINT32>
0x00433e62:	subl %ecx, %edi
0x00433e64:	movl %edi, (%esp)
0x00433e67:	addl %esp, $0x4<UINT32>
0x00433e6d:	xorl %ecx, $0x455df64<UINT32>
0x00433e73:	subl %esi, $0x3be042fa<UINT32>
0x00433e79:	subl %esi, %ecx
0x00433e7b:	addl %esi, $0x3be042fa<UINT32>
0x00433e81:	popl %ecx
0x00433e82:	pushl %esi
0x00433e83:	subl (%esp), $0x21b82038<UINT32>
0x00433e8a:	movl %edx, (%esp)
0x00433e8d:	pushl $0x4313<UINT32>
0x00433e92:	movl (%esp), %edi
0x00433e95:	movl %edi, %esp
0x00433e97:	addl %edi, $0x4<UINT32>
0x00433e9d:	addl %edi, $0x4<UINT8>
0x00433ea0:	xchgl (%esp), %edi
0x00433ea3:	popl %esp
0x00433ea4:	addl %edx, $0x21b82038<UINT32>
0x00433eaa:	popl %esi
0x00433eab:	jmp 0x00433eb0
0x00433eb0:	jmp 0x00433f1c
0x00433f1c:	jmp 0x00436255
0x00436255:	movl %eax, %eax
0x00436257:	call 0x0043625c
0x0043625c:	popl %eax
0x0043625d:	subl %eax, $0x177<UINT32>
0x00436262:	jmp 0x004360e5
0x004360e5:	pusha
0x004360e6:	pushl %esi
0x004360e7:	pushl %eax
0x004360e8:	pushl %edx
0x004360e9:	rdtsc
0x004360eb:	popl %edx
0x004360ec:	popl %eax
0x004360ed:	pusha
0x004360ee:	popa
0x004360ef:	popl %esi
0x004360f0:	jmp 0x00436104
0x00436104:	pusha
0x00436105:	pushl %eax
0x00436106:	pushl %edx
0x00436107:	rdtsc
0x00436109:	popl %edx
0x0043610a:	popl %eax
0x0043610b:	pusha
0x0043610c:	popa
0x0043610d:	pushl %eax
0x0043610e:	pushl %edx
0x0043610f:	rdtsc
0x00436111:	popl %edx
0x00436112:	popl %eax
0x00436113:	pushl %ecx
0x00436114:	popl %ecx
0x00436115:	popa
0x00436116:	popa
0x00436117:	pushfl
0x00436118:	subl %esp, $0x4<UINT8>
0x0043611b:	movl (%esp), %ecx
0x0043611e:	movl %ecx, %esp
0x00436120:	addl %ecx, $0x4<UINT32>
0x00436126:	subl %ecx, $0x4<UINT8>
0x00436129:	xchgl (%esp), %ecx
0x0043612c:	popl %esp
0x0043612d:	movl (%esp), %ebx
0x00436130:	pushl %ecx
0x00436131:	movl %ecx, $0x8<UINT32>
0x00436136:	movl 0x4(%esp), %ecx
0x0043613a:	popl %ecx
0x0043613b:	pushl %edx
0x0043613c:	movl %edx, %esp
0x0043613e:	addl %edx, $0x4<UINT32>
0x00436144:	subl %edx, $0x4<UINT32>
0x0043614a:	xchgl (%esp), %edx
0x0043614d:	popl %esp
0x0043614e:	movl (%esp), %edx
0x00436151:	movl (%esp), $0x755f1206<UINT32>
0x00436158:	notl (%esp)
0x0043615b:	incl (%esp)
0x0043615e:	shrl (%esp), $0x2<UINT8>
0x00436162:	xorl (%esp), $0x7fbb0aed<UINT32>
0x00436169:	shll (%esp), $0x3<UINT8>
0x0043616d:	subl (%esp), $0x2f6ff1c0<UINT32>
0x00436174:	xorl (%esp), $0xb92aa5f9<UINT32>
0x0043617b:	pushl $0x726c9a56<UINT32>
0x00436180:	movl (%esp), %esi
0x00436183:	movl (%esp), $0x579dc8a4<UINT32>
0x0043618a:	subl (%esp), $0x4a1e4b30<UINT32>
0x00436191:	xorl (%esp), $0x5280095a<UINT32>
0x00436198:	shll (%esp), $0x4<UINT8>
0x0043619c:	subl (%esp), $0x5edeef23<UINT32>
0x004361a3:	shll (%esp), $0x6<UINT8>
0x004361a7:	xorl (%esp), $0x1655add<UINT32>
0x004361ae:	movl (%esp), %eax
0x004361b1:	pushl %ebp
0x004361b2:	movl %ebp, %esp
0x004361b4:	addl %ebp, $0x4<UINT32>
0x004361ba:	subl %ebp, $0x4<UINT32>
0x004361c0:	pushl %ecx
0x004361c1:	movl (%esp), %ebp
0x004361c4:	pushl 0x4(%esp)
0x004361c8:	popl %ebp
0x004361c9:	popl (%esp)
0x004361cc:	popl %esp
0x004361cd:	movl (%esp), %esi
0x004361d0:	movl (%esp), %ebx
0x004361d3:	pushl 0x10(%esp)
0x004361d7:	pushl (%esp)
0x004361da:	movl %eax, (%esp)
0x004361dd:	addl %esp, $0x4<UINT32>
0x004361e3:	pushl %edx
0x004361e4:	movl %edx, %esp
0x004361e6:	addl %edx, $0x4<UINT32>
0x004361ec:	addl %edx, $0x4<UINT8>
0x004361ef:	xchgl (%esp), %edx
0x004361f2:	popl %esp
0x004361f3:	pushl 0x8(%esp)
0x004361f7:	pushl (%esp)
0x004361fa:	pushl (%esp)
0x004361fd:	popl %ebx
0x004361fe:	addl %esp, $0x4<UINT32>
0x00436204:	addl %esp, $0x4<UINT32>
0x0043620a:	movl 0x8(%esp), %eax
0x0043620e:	movl 0x10(%esp), %ebx
0x00436212:	pushl (%esp)
0x00436215:	popl %ebx
0x00436216:	pushl %eax
0x00436217:	pushl %esp
0x00436218:	movl %eax, (%esp)
0x0043621b:	addl %esp, $0x4<UINT8>
0x0043621e:	addl %eax, $0x4<UINT32>
0x00436223:	addl %eax, $0x4<UINT32>
0x00436228:	xchgl (%esp), %eax
0x0043622b:	popl %esp
0x0043622c:	movl %eax, (%esp)
0x0043622f:	pushl %ecx
0x00436230:	movl (%esp), $0x70f27bd2<UINT32>
0x00436237:	movl (%esp), %eax
0x0043623a:	movl %eax, %esp
0x0043623c:	addl %eax, $0x4<UINT32>
0x00436241:	addl %eax, $0x4<UINT32>
0x00436246:	xorl %eax, (%esp)
0x00436249:	xorl (%esp), %eax
0x0043624c:	xorl %eax, (%esp)
0x0043624f:	popl %esp
0x00436250:	jmp 0x0043221c
0x0043221c:	pushl %edx
0x0043221d:	pusha
0x0043221e:	pushl %eax
0x0043221f:	pushl %edx
0x00432220:	rdtsc
0x00432222:	popl %edx
0x00432223:	popl %eax
0x00432224:	pushl %ebx
0x00432225:	popl %ebx
0x00432226:	jmp 0x00432234
0x00432234:	pushl %esi
0x00432235:	popl %esi
0x00432236:	popa
0x00432237:	jmp 0x00432244
0x00432244:	popl %edx
0x00432245:	pusha
0x00432246:	jmp 0x00432259
0x00432259:	pushl %edi
0x0043225a:	pushl %ecx
0x0043225b:	popl %ecx
0x0043225c:	popl %edi
0x0043225d:	jmp 0x0043226d
0x0043226d:	pusha
0x0043226e:	jmp 0x0043227c
0x0043227c:	pushl %ecx
0x0043227d:	popl %ecx
0x0043227e:	pusha
0x0043227f:	popa
0x00432280:	popa
0x00432281:	popa
0x00432282:	pushl $0x494c<UINT32>
0x00432287:	movl (%esp), %eax
0x0043228a:	pushl $0x64ce<UINT32>
0x0043228f:	movl (%esp), %edi
0x00432292:	movl %edi, %esp
0x00432294:	pushl $0x2a80<UINT32>
0x00432299:	movl (%esp), %eax
0x0043229c:	movl (%esp), %ebx
0x0043229f:	pushl %eax
0x004322a0:	movl %eax, $0x4<UINT32>
0x004322a5:	movl %ebx, %eax
0x004322a7:	popl %eax
0x004322a8:	addl %edi, %ebx
0x004322aa:	movl %ebx, (%esp)
0x004322ad:	pushl %ebp
0x004322ae:	movl %ebp, %esp
0x004322b0:	addl %ebp, $0x4<UINT32>
0x004322b6:	addl %ebp, $0x4<UINT32>
0x004322bc:	xorl %ebp, (%esp)
0x004322bf:	xorl (%esp), %ebp
0x004322c2:	xorl %ebp, (%esp)
0x004322c5:	popl %esp
0x004322c6:	pushl %ebx
0x004322c7:	movl (%esp), %esi
0x004322ca:	pushl %edx
0x004322cb:	movl %edx, $0x3c442366<UINT32>
0x004322d0:	andl %edx, $0x5d344d32<UINT32>
0x004322d6:	pushl %esi
0x004322d7:	movl %esi, $0xe3fbfee2<UINT32>
0x004322dc:	addl %edx, %esi
0x004322de:	popl %esi
0x004322df:	pushl $0x3bbf<UINT32>
0x004322e4:	movl (%esp), %edi
0x004322e7:	movl (%esp), %edx
0x004322ea:	addl (%esp), $0x547351e6<UINT32>
0x004322f1:	popl %esi
0x004322f2:	pushl %ebx
0x004322f3:	movl %ebx, $0x547351e6<UINT32>
0x004322f8:	subl %esi, %ebx
0x004322fa:	popl %ebx
0x004322fb:	popl %edx
0x004322fc:	subl %edi, %esi
0x004322fe:	popl %esi
0x004322ff:	xorl %edi, (%esp)
0x00432302:	xorl (%esp), %edi
0x00432305:	xorl %edi, (%esp)
0x00432308:	movl %esp, (%esp)
0x0043230b:	movl (%esp), %ecx
0x0043230e:	pushl $0x5e5c<UINT32>
0x00432313:	movl (%esp), %edx
0x00432316:	pushl $0x7b8d<UINT32>
0x0043231b:	movl (%esp), %ebx
0x0043231e:	subl %esp, $0x4<UINT32>
0x00432324:	movl (%esp), %ebp
0x00432327:	movl %ebp, %esp
0x00432329:	addl %ebp, $0x4<UINT32>
0x0043232f:	pushl %ecx
0x00432330:	movl (%esp), %ebx
0x00432333:	pushl $0x2d03<UINT32>
0x00432338:	movl (%esp), %esi
0x0043233b:	pushl $0x4f1f<UINT32>
0x00432340:	movl (%esp), %ebp
0x00432343:	movl %ebp, $0x2d9962e4<UINT32>
0x00432348:	addl %ebp, $0x44e3faf3<UINT32>
0x0043234e:	movl %esi, %ebp
0x00432350:	popl %ebp
0x00432351:	pushl %eax
0x00432352:	movl %eax, $0x727d5ddb<UINT32>
0x00432357:	pushl %eax
0x00432358:	subl (%esp), $0x5d44033f<UINT32>
0x0043235f:	popl %ebx
0x00432360:	addl %ebx, $0x5d44033f<UINT32>
0x00432366:	popl %eax
0x00432367:	addl %ebx, $0x3eea17a5<UINT32>
0x0043236d:	addl %ebx, $0x515a339b<UINT32>
0x00432373:	subl %ebx, %esi
0x00432375:	pushl %eax
0x00432376:	movl %eax, $0x32b642de<UINT32>
0x0043237b:	andl %eax, $0x70b3759b<UINT32>
0x00432380:	shll %eax, $0x7<UINT8>
0x00432383:	xorl %eax, $0x37c13a05<UINT32>
0x00432388:	shll %eax, $0x7<UINT8>
0x0043238b:	negl %eax
0x0043238d:	xorl %eax, $0xde1e4e1b<UINT32>
0x00432392:	subl %ebx, %eax
0x00432394:	popl %eax
0x00432395:	pushl %ecx
0x00432396:	movl %ecx, $0x3eea17a5<UINT32>
0x0043239b:	subl %ebx, %ecx
0x0043239d:	popl %ecx
0x0043239e:	popl %esi
0x0043239f:	subl %ebp, $0x27526e40<UINT32>
0x004323a5:	addl %ebp, $0x1d936b86<UINT32>
0x004323ab:	subl %ebp, %ebx
0x004323ad:	subl %ebp, $0x1d936b86<UINT32>
0x004323b3:	addl %ebp, $0x27526e40<UINT32>
0x004323b9:	popl %ebx
0x004323ba:	xorl %ebp, (%esp)
0x004323bd:	xorl (%esp), %ebp
0x004323c0:	xorl %ebp, (%esp)
0x004323c3:	movl %esp, (%esp)
0x004323c6:	movl (%esp), %ebx
0x004323c9:	pushl $0x7c42<UINT32>
0x004323ce:	movl (%esp), %ebp
0x004323d1:	pushl $0x70a6<UINT32>
0x004323d6:	movl (%esp), %ecx
0x004323d9:	movl (%esp), %eax
0x004323dc:	movl (%esp), %esi
0x004323df:	pushl %edi
0x004323e0:	movl %edi, %esp
0x004323e2:	addl %edi, $0x4<UINT32>
0x004323e8:	subl %edi, $0x4<UINT8>
0x004323eb:	xchgl (%esp), %edi
0x004323ee:	popl %esp
0x004323ef:	movl (%esp), %edx
0x004323f2:	movl %edx, %esp
0x004323f4:	addl %edx, $0x4<UINT32>
0x004323fa:	subl %edx, $0x4<UINT32>
0x00432400:	xchgl (%esp), %edx
0x00432403:	movl %esp, (%esp)
0x00432406:	movl (%esp), %ebx
0x00432409:	pushl %esp
0x0043240a:	movl %ebx, (%esp)
0x0043240d:	addl %esp, $0x4<UINT8>
0x00432410:	pushl $0x27e1<UINT32>
0x00432415:	movl (%esp), %eax
0x00432418:	movl %eax, $0x80459c2<UINT32>
0x0043241d:	pushl %esi
0x0043241e:	subl %esp, $0x4<UINT32>
0x00432424:	movl (%esp), %ebp
0x00432427:	movl %ebp, $0x257b047d<UINT32>
0x0043242c:	movl %esi, $0x652d519c<UINT32>
0x00432431:	xorl %esi, %ebp
0x00432433:	popl %ebp
0x00432434:	shll %esi, $0x3<UINT8>
0x00432437:	pushl %edi
0x00432438:	movl %edi, $0x125a1ace<UINT32>
0x0043243d:	addl %edi, $0x36aaee0a<UINT32>
0x00432443:	orl %esi, %edi
0x00432445:	movl %edi, (%esp)
0x00432448:	addl %esp, $0x4<UINT8>
0x0043244b:	negl %esi
0x0043244d:	shrl %esi, $0x6<UINT8>
0x00432450:	addl %esi, $0x31e20e1d<UINT32>
0x00432456:	orl %eax, %esi
0x00432458:	popl %esi
0x00432459:	notl %eax
0x0043245b:	andl %eax, $0x39bb455c<UINT32>
0x00432460:	pushl %ecx
0x00432461:	movl %ecx, $0x71223265<UINT32>
0x00432466:	andl %eax, %ecx
0x00432468:	popl %ecx
0x00432469:	pushl %edi
0x0043246a:	movl %edi, $0x192d6202<UINT32>
0x0043246f:	orl %edi, $0x8fc611a<UINT32>
0x00432475:	orl %edi, $0x2d643641<UINT32>
0x0043247b:	subl %edi, $0x747b070a<UINT32>
0x00432481:	shll %edi, $0x5<UINT8>
0x00432484:	xorl %edi, $0xd700ffc<UINT32>
0x0043248a:	subl %edi, $0xe92c9de3<UINT32>
0x00432490:	andl %eax, %edi
0x00432492:	popl %edi
0x00432493:	addl %eax, $0x4<UINT32>
0x00432498:	addl %ebx, $0x15c04b0d<UINT32>
0x0043249e:	addl %ebx, %eax
0x004324a0:	subl %ebx, $0x15c04b0d<UINT32>
0x004324a6:	popl %eax
0x004324a7:	subl %ebx, $0x4<UINT8>
0x004324aa:	xchgl (%esp), %ebx
0x004324ad:	popl %esp
0x004324ae:	movl (%esp), %edx
0x004324b1:	movl (%esp), %edi
0x004324b4:	call 0x004324b9
0x004324b9:	pushl (%esp)
0x004324bc:	movl %ecx, (%esp)
0x004324bf:	addl %esp, $0x4<UINT32>
0x004324c5:	pushl %esi
0x004324c6:	movl (%esp), %eax
0x004324c9:	pushl %esp
0x004324ca:	movl %eax, (%esp)
0x004324cd:	subl %esp, $0x4<UINT32>
0x004324d3:	movl (%esp), %ecx
0x004324d6:	pushl %esp
0x004324d7:	popl %ecx
0x004324d8:	addl %ecx, $0x4<UINT32>
0x004324de:	addl %ecx, $0x4<UINT32>
0x004324e4:	xchgl (%esp), %ecx
0x004324e7:	popl %esp
0x004324e8:	pushl %esi
0x004324e9:	pushl %ebp
0x004324ea:	pushl $0x242b2436<UINT32>
0x004324ef:	popl %ebp
0x004324f0:	shrl %ebp, $0x2<UINT8>
0x004324f3:	notl %ebp
0x004324f5:	andl %ebp, $0x123e2bd6<UINT32>
0x004324fb:	subl %ebp, $0x123422ce<UINT32>
0x00432501:	movl %esi, %ebp
0x00432503:	popl %ebp
0x00432504:	pushl %ecx
0x00432505:	movl %ecx, $0x5a3c1b4d<UINT32>
0x0043250a:	subl %ecx, $0xb23e8e3<UINT32>
0x00432510:	addl %eax, $0x1e493ec4<UINT32>
0x00432515:	subl %eax, %ecx
0x00432517:	subl %eax, $0x1e493ec4<UINT32>
0x0043251c:	popl %ecx
0x0043251d:	addl %eax, %esi
0x0043251f:	addl %eax, $0x4f18326a<UINT32>
0x00432524:	popl %esi
0x00432525:	pushl %ebp
0x00432526:	movl %ebp, $0x4<UINT32>
0x0043252b:	addl %eax, %ebp
0x0043252d:	pushl (%esp)
0x00432530:	popl %ebp
0x00432531:	addl %esp, $0x4<UINT8>
0x00432534:	xorl %eax, (%esp)
0x00432537:	xorl (%esp), %eax
0x0043253a:	xorl %eax, (%esp)
0x0043253d:	movl %esp, (%esp)
0x00432540:	pushl %edx
0x00432541:	movl (%esp), %edi
0x00432544:	movl (%esp), %ecx
0x00432547:	movl (%esp), %edx
0x0043254a:	pushl $0x265f04f7<UINT32>
0x0043254f:	popl %edx
0x00432550:	pushl %ebx
0x00432551:	movl %ebx, $0x342505f7<UINT32>
0x00432556:	decl %ebx
0x00432557:	shll %ebx, $0x2<UINT8>
0x0043255a:	shll %ebx, $0x7<UINT8>
0x0043255d:	subl %ebx, $0x1<UINT32>
0x00432563:	subl %ebx, $0xee836db6<UINT32>
0x00432569:	andl %edx, %ebx
0x0043256b:	movl %ebx, (%esp)
0x0043256e:	subl %esp, $0x4<UINT32>
0x00432574:	movl (%esp), %ebp
0x00432577:	pushl %esp
0x00432578:	movl %ebp, (%esp)
0x0043257b:	addl %esp, $0x4<UINT8>
0x0043257e:	pushl $0x50a1<UINT32>
0x00432583:	movl (%esp), %esi
0x00432586:	movl %esi, $0x1a8b254c<UINT32>
0x0043258b:	pushl %edx
0x0043258c:	movl %edx, $0xa3c3568<UINT32>
0x00432591:	shrl %edx, $0x4<UINT8>
0x00432594:	incl %edx
0x00432595:	orl %edx, $0x6fd6076a<UINT32>
0x0043259b:	subl %edx, $0x49752dca<UINT32>
0x004325a1:	notl %edx
0x004325a3:	addl %edx, $0xbf7746e<UINT32>
0x004325a9:	addl %esi, %edx
0x004325ab:	movl %edx, (%esp)
0x004325ae:	addl %esp, $0x4<UINT32>
0x004325b4:	addl %ebp, %esi
0x004325b6:	movl %esi, (%esp)
0x004325b9:	addl %esp, $0x4<UINT8>
0x004325bc:	pushl %esi
0x004325bd:	movl %esi, $0x542b2637<UINT32>
0x004325c2:	xorl %esi, $0x542b2633<UINT32>
0x004325c8:	addl %ebp, %esi
0x004325ca:	popl %esi
0x004325cb:	xchgl (%esp), %ebp
0x004325ce:	popl %esp
0x004325cf:	pushl %ebp
0x004325d0:	movl %ebp, $0x67cd58ce<UINT32>
0x004325d5:	incl %ebp
0x004325d6:	subl %ebp, $0x65c5572b<UINT32>
0x004325dc:	subl %edx, %ebp
0x004325de:	movl %ebp, (%esp)
0x004325e1:	addl %esp, $0x4<UINT32>
0x004325e7:	subl %ecx, %edx
0x004325e9:	popl %edx
0x004325ea:	pushl %ebx
0x004325eb:	pushl %edi
0x004325ec:	movl %edi, $0x3221c<UINT32>
0x004325f1:	pushl $0x61d<UINT32>
0x004325f6:	movl (%esp), %ebp
0x004325f9:	pushl %ebx
0x004325fa:	movl %ebx, %edi
0x004325fc:	pushl %ebx
0x004325fd:	xorl (%esp), $0x21072eb<UINT32>
0x00432604:	popl %ebp
0x00432605:	xorl %ebp, $0x21072eb<UINT32>
0x0043260b:	popl %ebx
0x0043260c:	movl %ebx, %ebp
0x0043260e:	movl %ebp, (%esp)
0x00432611:	pushl %ebp
0x00432612:	movl %ebp, %esp
0x00432614:	pushl %esi
0x00432615:	movl %esi, $0x2da51584<UINT32>
0x0043261a:	xorl %esi, $0x73283b84<UINT32>
0x00432620:	decl %esi
0x00432621:	shrl %esi, $0x8<UINT8>
0x00432624:	orl %esi, $0x22f43530<UINT32>
0x0043262a:	xorl %esi, $0x22febd39<UINT32>
0x00432630:	addl %ebp, %esi
0x00432632:	popl %esi
0x00432633:	pushl %edi
0x00432634:	movl %edi, $0x4<UINT32>
0x00432639:	addl %ebp, %edi
0x0043263b:	popl %edi
0x0043263c:	xchgl (%esp), %ebp
0x0043263f:	popl %esp
0x00432640:	pushl (%esp)
0x00432643:	popl %edi
0x00432644:	addl %esp, $0x4<UINT32>
0x0043264a:	pushl %edx
0x0043264b:	pushl %edi
0x0043264c:	pushl $0x1d384a81<UINT32>
0x00432651:	popl %edi
0x00432652:	notl %edi
0x00432654:	shll %edi
0x00432656:	shll %edi, $0x4<UINT8>
0x00432659:	subl %edi, $0xf7004450<UINT32>
0x0043265f:	movl %edx, %edi
0x00432661:	popl %edi
0x00432662:	addl %edx, $0xcc06b09d<UINT32>
0x00432668:	addl %ecx, $0x23057928<UINT32>
0x0043266e:	subl %ecx, %edx
0x00432670:	subl %ecx, $0x23057928<UINT32>
0x00432676:	pushl (%esp)
0x00432679:	popl %edx
0x0043267a:	addl %esp, $0x4<UINT32>
0x00432680:	subl %ecx, %ebx
0x00432682:	pushl %eax
0x00432683:	movl %eax, $0x5d417261<UINT32>
0x00432688:	andl %eax, $0x7ba12fad<UINT32>
0x0043268d:	notl %eax
0x0043268f:	xorl %eax, $0x32056d2d<UINT32>
0x00432694:	andl %eax, $0x2ffa2eb5<UINT32>
0x00432699:	subl %eax, $0xd6fd04a4<UINT32>
0x0043269e:	addl %ecx, %eax
0x004326a0:	movl %eax, (%esp)
0x004326a3:	addl %esp, $0x4<UINT8>
0x004326a6:	popl %ebx
0x004326a7:	pushl $0x552d<UINT32>
0x004326ac:	movl (%esp), %edx
0x004326af:	pushl $0x25c8<UINT32>
0x004326b4:	movl (%esp), %ebp
0x004326b7:	pushl $0x75c26a01<UINT32>
0x004326bc:	movl %ebp, (%esp)
0x004326bf:	addl %esp, $0x4<UINT8>
0x004326c2:	pushl %eax
0x004326c3:	movl %eax, $0x0<UINT32>
0x004326c8:	subl %eax, %ebp
0x004326ca:	subl %esp, $0x4<UINT8>
0x004326cd:	movl (%esp), %ebp
0x004326d0:	pushl %eax
0x004326d1:	movl %ebp, (%esp)
0x004326d4:	pushl %ebx
0x004326d5:	movl %ebx, %esp
0x004326d7:	addl %ebx, $0x4<UINT32>
0x004326dd:	addl %ebx, $0x4<UINT32>
0x004326e3:	xchgl (%esp), %ebx
0x004326e6:	popl %esp
0x004326e7:	popl %eax
0x004326e8:	popl %eax
0x004326e9:	decl %ebp
0x004326ea:	subl %ebp, $0x8a3ca8ae<UINT32>
0x004326f0:	movl %edx, %ebp
0x004326f2:	popl %ebp
0x004326f3:	pushl %edx
0x004326f4:	popl %ebp
0x004326f5:	pushl (%esp)
0x004326f8:	popl %edx
0x004326f9:	pushl %edx
0x004326fa:	pushl %esp
0x004326fb:	popl %edx
0x004326fc:	pushl %esi
0x004326fd:	movl %esi, $0x4<UINT32>
0x00432702:	subl %edx, $0x48ee291a<UINT32>
0x00432708:	addl %edx, %esi
0x0043270a:	addl %edx, $0x48ee291a<UINT32>
0x00432710:	popl %esi
0x00432711:	pushl $0x7592<UINT32>
0x00432716:	movl (%esp), %ecx
0x00432719:	movl %ecx, $0x7e01026d<UINT32>
0x0043271e:	xorl %ecx, $0x7e010269<UINT32>
0x00432724:	addl %edx, %ecx
0x00432726:	popl %ecx
0x00432727:	xchgl (%esp), %edx
0x0043272a:	popl %esp
0x0043272b:	pushl %ecx
0x0043272c:	pushl $0x20973337<UINT32>
0x00432731:	pushl (%esp)
0x00432734:	popl %ecx
0x00432735:	addl %esp, $0x4<UINT8>
0x00432738:	subl %ebp, %ecx
0x0043273a:	movl %ecx, (%esp)
0x0043273d:	addl %esp, $0x4<UINT8>
0x00432740:	pushl %ecx
0x00432741:	movl %ecx, $0x1cbc1d2c<UINT32>
0x00432746:	shrl %ecx
0x00432748:	pushl %esi
0x00432749:	movl %esi, $0x5ecf0a2c<UINT32>
0x0043274e:	pushl %ebp
0x0043274f:	movl %ebp, $0x5b9f72b3<UINT32>
0x00432754:	xorl %esi, %ebp
0x00432756:	popl %ebp
0x00432757:	andl %esi, $0x38245166<UINT32>
0x0043275d:	subl %esp, $0x4<UINT8>
0x00432760:	movl (%esp), %edx
0x00432763:	pushl $0x25306d22<UINT32>
0x00432768:	popl %edx
0x00432769:	xorl %edx, $0x15a65396<UINT32>
0x0043276f:	subl %esi, %edx
0x00432771:	popl %edx
0x00432772:	subl %esp, $0x4<UINT32>
0x00432778:	movl (%esp), %edi
0x0043277b:	movl %edi, $0x64fb52f9<UINT32>
0x00432780:	shll %edi, $0x8<UINT8>
0x00432783:	addl %edi, $0x210471ab<UINT32>
0x00432789:	shll %edi, $0x4<UINT8>
0x0043278c:	negl %edi
0x0043278e:	xorl %edi, $0x7a4642a0<UINT32>
0x00432794:	addl %esi, %edi
0x00432796:	popl %edi
0x00432797:	addl %ecx, %esi
0x00432799:	movl %esi, (%esp)
0x0043279c:	addl %esp, $0x4<UINT32>
0x004327a2:	subl %ecx, $0xa8dc942<UINT32>
0x004327a8:	addl %ebp, %ecx
0x004327aa:	popl %ecx
0x004327ab:	addl %ebp, %ecx
0x004327ad:	subl %ebp, $0x14096e96<UINT32>
0x004327b3:	pushl $0x62ec<UINT32>
0x004327b8:	movl (%esp), %edx
0x004327bb:	pushl $0x20973337<UINT32>
0x004327c0:	movl %edx, (%esp)
0x004327c3:	addl %esp, $0x4<UINT32>
0x004327c9:	addl %ebp, %edx
0x004327cb:	popl %edx
0x004327cc:	pushl %edx
0x004327cd:	movl (%esp), %eax
0x004327d0:	pushl $0x7fae<UINT32>
0x004327d5:	movl (%esp), %edx
0x004327d8:	movl (%esp), %esp
0x004327db:	addl (%esp), $0x4<UINT8>
0x004327df:	movl %eax, (%esp)
0x004327e2:	addl %esp, $0x4<UINT8>
0x004327e5:	subl %esp, $0x4<UINT8>
0x004327e8:	movl (%esp), %ebx
0x004327eb:	pushl $0x1562<UINT32>
0x004327f0:	movl (%esp), %eax
0x004327f3:	pushl %edx
0x004327f4:	pushl $0x2ea0209d<UINT32>
0x004327f9:	movl %edx, (%esp)
0x004327fc:	addl %esp, $0x4<UINT8>
0x004327ff:	pushl %eax
0x00432800:	movl %eax, $0x6a0a18d3<UINT32>
0x00432805:	subl %edx, %eax
0x00432807:	popl %eax
0x00432808:	addl %edx, $0x676363a3<UINT32>
0x0043280e:	movl %eax, %edx
0x00432810:	movl %edx, (%esp)
0x00432813:	addl %esp, $0x4<UINT32>
0x00432819:	subl %eax, $0x169c2020<UINT32>
0x0043281e:	pushl $0x6291<UINT32>
0x00432823:	movl (%esp), %eax
0x00432826:	xorl (%esp), $0x6ce234e<UINT32>
0x0043282d:	popl %ebx
0x0043282e:	xorl %ebx, $0x6ce234e<UINT32>
0x00432834:	popl %eax
0x00432835:	negl %ebx
0x00432837:	shrl %ebx, $0x6<UINT8>
0x0043283a:	subl %ebx, $0x4b973f96<UINT32>
0x00432840:	addl %ebx, $0x47ecb4c8<UINT32>
0x00432846:	addl %eax, $0x1fe25648<UINT32>
0x0043284b:	addl %eax, %ebx
0x0043284d:	subl %eax, $0x1fe25648<UINT32>
0x00432852:	popl %ebx
0x00432853:	subl %eax, $0x4<UINT8>
0x00432856:	xorl %eax, (%esp)
0x00432859:	xorl (%esp), %eax
0x0043285c:	xorl %eax, (%esp)
0x0043285f:	movl %esp, (%esp)
0x00432862:	movl (%esp), %ecx
0x00432865:	pushl %ebx
0x00432866:	pushl %eax
0x00432867:	movl %eax, $0x48ad1e82<UINT32>
0x0043286c:	movl %ebx, $0xa1cc77b4<UINT32>
0x00432871:	subl %ebx, %eax
0x00432873:	popl %eax
0x00432874:	orl %ebx, $0x72f24523<UINT32>
0x0043287a:	pushl %edi
0x0043287b:	movl %edi, $0x566e0f30<UINT32>
0x00432880:	andl %edi, $0x759d3ed8<UINT32>
0x00432886:	incl %edi
0x00432887:	pushl %ebx
0x00432888:	movl %ebx, $0x4fa0450d<UINT32>
0x0043288d:	subl %ebx, $0x24295cde<UINT32>
0x00432893:	incl %ebx
0x00432894:	addl %ebx, $0x7b6d30d5<UINT32>
0x0043289a:	incl %ebx
0x0043289b:	subl %ebx, $0x93097102<UINT32>
0x004328a1:	subl %edi, %ebx
0x004328a3:	popl %ebx
0x004328a4:	subl %ebx, $0x146d1003<UINT32>
0x004328aa:	subl %ebx, %edi
0x004328ac:	addl %ebx, $0x146d1003<UINT32>
0x004328b2:	popl %edi
0x004328b3:	pushl %esi
0x004328b4:	movl (%esp), %edi
0x004328b7:	movl %edi, $0x3bcdf727<UINT32>
0x004328bc:	xorl %ebx, %edi
0x004328be:	popl %edi
0x004328bf:	pushl %ebx
0x004328c0:	pushl %ecx
0x004328c1:	movl %ecx, %esp
0x004328c3:	addl %ecx, $0x4<UINT32>
0x004328c9:	subl %ecx, $0x4<UINT8>
0x004328cc:	xchgl (%esp), %ecx
0x004328cf:	popl %esp
0x004328d0:	movl (%esp), %edx
0x004328d3:	pushl %ebp
0x004328d4:	movl %ebp, $0x5a305d7a<UINT32>
0x004328d9:	subl %esp, $0x4<UINT32>
0x004328df:	movl (%esp), %ebx
0x004328e2:	movl %ebx, $0x4c6669e3<UINT32>
0x004328e7:	xorl %ebp, %ebx
0x004328e9:	popl %ebx
0x004328ea:	addl %ebp, $0xffffffff<UINT8>
0x004328ed:	subl %ebp, $0xdffc0dcc<UINT32>
0x004328f3:	movl %edx, %ebp
0x004328f5:	popl %ebp
0x004328f6:	xorl 0x4(%esp), %edx
0x004328fa:	pushl (%esp)
0x004328fd:	popl %edx
0x004328fe:	addl %esp, $0x4<UINT32>
0x00432904:	popl %ecx
0x00432905:	pushl %edx
0x00432906:	movl %edx, $0x365a26cc<UINT32>
0x0043290b:	xorl %ecx, %edx
0x0043290d:	popl %edx
0x0043290e:	movl %ebx, (%esp)
0x00432911:	addl %esp, $0x4<UINT8>
0x00432914:	pushl $0x3f7e<UINT32>
0x00432919:	movl (%esp), %edx
0x0043291c:	pushl $0x59<UINT32>
0x00432921:	popl %edx
0x00432922:	pushl %ebp
0x00432923:	movl %ebp, %edx
0x00432925:	movl %ebx, %ebp
0x00432927:	movl %ebp, (%esp)
0x0043292a:	pushl %ebx
0x0043292b:	movl %ebx, %esp
0x0043292d:	pushl %esi
0x0043292e:	movl %esi, $0xb3b6d98<UINT32>
0x00432933:	shll %esi, $0x3<UINT8>
0x00432936:	pushl %edx
0x00432937:	movl %edx, $0x59db6cc4<UINT32>
0x0043293c:	xorl %esi, %edx
0x0043293e:	popl %edx
0x0043293f:	subl %ebx, $0x66025099<UINT32>
0x00432945:	subl %ebx, $0x466944ab<UINT32>
0x0043294b:	addl %ebx, %esi
0x0043294d:	addl %ebx, $0x466944ab<UINT32>
0x00432953:	addl %ebx, $0x66025099<UINT32>
0x00432959:	popl %esi
0x0043295a:	addl %ebx, $0x4<UINT32>
0x00432960:	xchgl (%esp), %ebx
0x00432963:	popl %esp
0x00432964:	movl %edx, (%esp)
0x00432967:	subl %esp, $0x4<UINT8>
0x0043296a:	movl (%esp), %edi
0x0043296d:	pushl %esp
0x0043296e:	popl %edi
0x0043296f:	pushl %ebx
0x00432970:	pushl %eax
0x00432971:	movl %eax, $0x305410f8<UINT32>
0x00432976:	movl %ebx, %eax
0x00432978:	popl %eax
0x00432979:	shrl %ebx, $0x6<UINT8>
0x0043297c:	pushl %esi
0x0043297d:	movl %esi, $0x27016cf0<UINT32>
0x00432982:	andl %ebx, %esi
0x00432984:	popl %esi
0x00432985:	xchgl %ebx, %edi
0x00432987:	negl %edi
0x00432989:	xchgl %ebx, %edi
0x0043298b:	xorl %ebx, $0x6a935922<UINT32>
0x00432991:	notl %ebx
0x00432993:	xorl %ebx, $0x6a921919<UINT32>
0x00432999:	addl %edi, %ebx
0x0043299b:	movl %ebx, (%esp)
0x0043299e:	addl %esp, $0x4<UINT8>
0x004329a1:	addl %edi, $0x4<UINT8>
0x004329a4:	pushl %edi
0x004329a5:	pushl 0x4(%esp)
0x004329a9:	popl %edi
0x004329aa:	popl (%esp)
0x004329ad:	popl %esp
0x004329ae:	xorl %eax, %eax
0x004329b0:	cmpxchgl (%ebp,%ebx), %ecx
0x004329b6:	je 7
