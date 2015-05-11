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
