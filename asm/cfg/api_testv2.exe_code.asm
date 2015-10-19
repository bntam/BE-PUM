0x00401131:	pushl %ebp
0x00401132:	movl %ebp, %esp
0x00401134:	pushl $0xffffffff<UINT8>
0x00401136:	pushl $0x4050a8<UINT32>
0x0040113b:	pushl $0x402584<UINT32>
0x00401140:	movl %eax, %fs:0
0x00401146:	pushl %eax
0x00401147:	movl %fs:0, %esp
0x0040114e:	subl %esp, $0x10<UINT8>
0x00401151:	pushl %ebx
0x00401152:	pushl %esi
0x00401153:	pushl %edi
0x00401154:	movl -24(%ebp), %esp
0x00401157:	call GetVersion@kernel32.dll
GetVersion@kernel32.dll: API Node	
0x0040115d:	xorl %edx, %edx
0x0040115f:	movb %dl, %ah
0x00401161:	movl 0x406950, %edx
0x00401167:	movl %ecx, %eax
0x00401169:	andl %ecx, $0xff<UINT32>
0x0040116f:	movl 0x40694c, %ecx
0x00401175:	shll %ecx, $0x8<UINT8>
0x00401178:	addl %ecx, %edx
0x0040117a:	movl 0x406948, %ecx
0x00401180:	shrl %eax, $0x10<UINT8>
0x00401183:	movl 0x406944, %eax
0x00401188:	pushl $0x0<UINT8>
0x0040118a:	call 0x0040244f
0x0040244f:	xorl %eax, %eax
0x00402451:	pushl $0x0<UINT8>
0x00402453:	cmpl 0x8(%esp), %eax
0x00402457:	pushl $0x1000<UINT32>
0x0040245c:	sete %al
0x0040245f:	pushl %eax
0x00402460:	call HeapCreate@kernel32.dll
HeapCreate@kernel32.dll: API Node	
0x00402466:	testl %eax, %eax
0x00402468:	movl 0x406d08, %eax
0x0040246d:	je 21
0x0040246f:	call 0x00403585
0x00403585:	pushl $0x140<UINT32>
0x0040358a:	pushl $0x0<UINT8>
0x0040358c:	pushl 0x406d08
0x00403592:	call HeapAlloc@kernel32.dll
HeapAlloc@kernel32.dll: API Node	
0x00403598:	testl %eax, %eax
0x0040359a:	movl 0x406ae8, %eax
0x0040359f:	jne 0x004035a2
0x004035a2:	andl 0x406ae0, $0x0<UINT8>
0x004035a9:	andl 0x406ae4, $0x0<UINT8>
0x004035b0:	pushl $0x1<UINT8>
0x004035b2:	movl 0x406adc, %eax
0x004035b7:	movl 0x406ad4, $0x10<UINT32>
0x004035c1:	popl %eax
0x004035c2:	ret

0x00402474:	testl %eax, %eax
0x00402476:	jne 0x00402487
0x00402487:	pushl $0x1<UINT8>
0x00402489:	popl %eax
0x0040248a:	ret

0x0040118f:	popl %ecx
0x00401190:	testl %eax, %eax
0x00401192:	jne 0x0040119c
0x0040119c:	andl -4(%ebp), $0x0<UINT8>
0x004011a0:	call 0x004022a4
0x004022a4:	subl %esp, $0x44<UINT8>
0x004022a7:	pushl %ebx
0x004022a8:	pushl %ebp
0x004022a9:	pushl %esi
0x004022aa:	pushl %edi
0x004022ab:	pushl $0x100<UINT32>
0x004022b0:	call 0x004027e8
0x004027e8:	pushl 0x406aa4
0x004027ee:	pushl 0x8(%esp)
0x004027f2:	call 0x004027fa
0x004027fa:	cmpl 0x4(%esp), $0xffffffe0<UINT8>
0x004027ff:	ja 34
0x00402801:	pushl 0x4(%esp)
0x00402805:	call 0x00402826
0x00402826:	pushl %esi
0x00402827:	movl %esi, 0x8(%esp)
0x0040282b:	cmpl %esi, 0x406790
0x00402831:	ja 0x0040283e
0x00402833:	pushl %esi
0x00402834:	call 0x00403919
0x00403919:	pushl %ebp
0x0040391a:	movl %ebp, %esp
0x0040391c:	subl %esp, $0x14<UINT8>
0x0040391f:	movl %eax, 0x406ae4
0x00403924:	movl %edx, 0x406ae8
0x0040392a:	pushl %ebx
0x0040392b:	pushl %esi
0x0040392c:	leal %eax, (%eax,%eax,4)
0x0040392f:	pushl %edi
0x00403930:	leal %edi, (%edx,%eax,4)
0x00403933:	movl %eax, 0x8(%ebp)
0x00403936:	movl -4(%ebp), %edi
0x00403939:	leal %ecx, 0x17(%eax)
0x0040393c:	andl %ecx, $0xfffffff0<UINT8>
0x0040393f:	movl -16(%ebp), %ecx
0x00403942:	sarl %ecx, $0x4<UINT8>
0x00403945:	decl %ecx
0x00403946:	cmpl %ecx, $0x20<UINT8>
0x00403949:	jnl 14
0x0040394b:	orl %esi, $0xffffffff<UINT8>
0x0040394e:	shrl %esi, %cl
0x00403950:	orl -8(%ebp), $0xffffffff<UINT8>
0x00403954:	movl -12(%ebp), %esi
0x00403957:	jmp 0x00403969
0x00403969:	movl %eax, 0x406adc
0x0040396e:	movl %ebx, %eax
0x00403970:	cmpl %ebx, %edi
0x00403972:	movl 0x8(%ebp), %ebx
0x00403975:	jae 0x00403990
0x00403990:	cmpl %ebx, -4(%ebp)
0x00403993:	jne 0x00403a0e
0x00403995:	movl %ebx, %edx
0x00403997:	cmpl %ebx, %eax
0x00403999:	movl 0x8(%ebp), %ebx
0x0040399c:	jae 0x004039b3
0x004039b3:	jne 89
0x004039b5:	cmpl %ebx, -4(%ebp)
0x004039b8:	jae 0x004039cb
0x004039cb:	jne 38
0x004039cd:	movl %ebx, %edx
0x004039cf:	cmpl %ebx, %eax
0x004039d1:	movl 0x8(%ebp), %ebx
0x004039d4:	jae 0x004039e3
0x004039e3:	jne 14
0x004039e5:	call 0x00403c22
0x00403c22:	movl %eax, 0x406ae4
0x00403c27:	movl %ecx, 0x406ad4
0x00403c2d:	pushl %esi
0x00403c2e:	pushl %edi
0x00403c2f:	xorl %edi, %edi
0x00403c31:	cmpl %eax, %ecx
0x00403c33:	jne 0x00403c65
0x00403c65:	movl %ecx, 0x406ae8
0x00403c6b:	pushl $0x41c4<UINT32>
0x00403c70:	pushl $0x8<UINT8>
0x00403c72:	leal %eax, (%eax,%eax,4)
0x00403c75:	pushl 0x406d08
0x00403c7b:	leal %esi, (%ecx,%eax,4)
0x00403c7e:	call HeapAlloc@kernel32.dll
0x00403c84:	cmpl %eax, %edi
0x00403c86:	movl 0x10(%esi), %eax
0x00403c89:	je 42
0x00403c8b:	pushl $0x4<UINT8>
0x00403c8d:	pushl $0x2000<UINT32>
0x00403c92:	pushl $0x100000<UINT32>
0x00403c97:	pushl %edi
0x00403c98:	call VirtualAlloc@kernel32.dll
VirtualAlloc@kernel32.dll: API Node	
0x00403c9e:	cmpl %eax, %edi
0x00403ca0:	movl 0xc(%esi), %eax
0x00403ca3:	jne 0x00403cb9
0x00403cb9:	orl 0x8(%esi), $0xffffffff<UINT8>
0x00403cbd:	movl (%esi), %edi
0x00403cbf:	movl 0x4(%esi), %edi
0x00403cc2:	incl 0x406ae4
0x00403cc8:	movl %eax, 0x10(%esi)
0x00403ccb:	orl (%eax), $0xffffffff<UINT8>
0x00403cce:	movl %eax, %esi
0x00403cd0:	popl %edi
0x00403cd1:	popl %esi
0x00403cd2:	ret

0x004039ea:	movl %ebx, %eax
0x004039ec:	testl %ebx, %ebx
0x004039ee:	movl 0x8(%ebp), %ebx
0x004039f1:	je 20
0x004039f3:	pushl %ebx
0x004039f4:	call 0x00403cd3
0x00403cd3:	pushl %ebp
0x00403cd4:	movl %ebp, %esp
0x00403cd6:	pushl %ecx
0x00403cd7:	movl %ecx, 0x8(%ebp)
0x00403cda:	pushl %ebx
0x00403cdb:	pushl %esi
0x00403cdc:	pushl %edi
0x00403cdd:	movl %esi, 0x10(%ecx)
0x00403ce0:	movl %eax, 0x8(%ecx)
0x00403ce3:	xorl %ebx, %ebx
0x00403ce5:	testl %eax, %eax
0x00403ce7:	jl 0x00403cee
0x00403cee:	movl %eax, %ebx
0x00403cf0:	pushl $0x3f<UINT8>
0x00403cf2:	imull %eax, %eax, $0x204<UINT32>
0x00403cf8:	popl %edx
0x00403cf9:	leal %eax, 0x144(%eax,%esi)
0x00403d00:	movl -4(%ebp), %eax
0x00403d03:	movl 0x8(%eax), %eax
0x00403d06:	movl 0x4(%eax), %eax
0x00403d09:	addl %eax, $0x8<UINT8>
0x00403d0c:	decl %edx
0x00403d0d:	jne 0x00403d03
0x00403d0f:	movl %edi, %ebx
0x00403d11:	pushl $0x4<UINT8>
0x00403d13:	shll %edi, $0xf<UINT8>
0x00403d16:	addl %edi, 0xc(%ecx)
0x00403d19:	pushl $0x1000<UINT32>
0x00403d1e:	pushl $0x8000<UINT32>
0x00403d23:	pushl %edi
0x00403d24:	call VirtualAlloc@kernel32.dll
0x00403d2a:	testl %eax, %eax
0x00403d2c:	jne 0x00403d36
0x00403d36:	leal %edx, 0x7000(%edi)
0x00403d3c:	cmpl %edi, %edx
0x00403d3e:	ja 60
0x00403d40:	leal %eax, 0x10(%edi)
0x00403d43:	orl -8(%eax), $0xffffffff<UINT8>
0x00403d47:	orl 0xfec(%eax), $0xffffffff<UINT8>
0x00403d4e:	leal %ecx, 0xffc(%eax)
0x00403d54:	movl -4(%eax), $0xff0<UINT32>
0x00403d5b:	movl (%eax), %ecx
0x00403d5d:	leal %ecx, -4100(%eax)
0x00403d63:	movl 0x4(%eax), %ecx
0x00403d66:	movl 0xfe8(%eax), $0xff0<UINT32>
0x00403d70:	addl %eax, $0x1000<UINT32>
0x00403d75:	leal %ecx, -16(%eax)
0x00403d78:	cmpl %ecx, %edx
0x00403d7a:	jbe 0x00403d43
0x00403d7c:	movl %eax, -4(%ebp)
0x00403d7f:	leal %ecx, 0xc(%edi)
0x00403d82:	addl %eax, $0x1f8<UINT32>
0x00403d87:	pushl $0x1<UINT8>
0x00403d89:	popl %edi
0x00403d8a:	movl 0x4(%eax), %ecx
0x00403d8d:	movl 0x8(%ecx), %eax
0x00403d90:	leal %ecx, 0xc(%edx)
0x00403d93:	movl 0x8(%eax), %ecx
0x00403d96:	movl 0x4(%ecx), %eax
0x00403d99:	andl 0x44(%esi,%ebx,4), $0x0<UINT8>
0x00403d9e:	movl 0xc4(%esi,%ebx,4), %edi
0x00403da5:	movb %al, 0x43(%esi)
0x00403da8:	movb %cl, %al
0x00403daa:	incb %cl
0x00403dac:	testb %al, %al
0x00403dae:	movl %eax, 0x8(%ebp)
0x00403db1:	movb 0x43(%esi), %cl
0x00403db4:	jne 3
0x00403db6:	orl 0x4(%eax), %edi
0x00403db9:	movl %edx, $0x80000000<UINT32>
0x00403dbe:	movl %ecx, %ebx
0x00403dc0:	shrl %edx, %cl
0x00403dc2:	notl %edx
0x00403dc4:	andl 0x8(%eax), %edx
0x00403dc7:	movl %eax, %ebx
0x00403dc9:	popl %edi
0x00403dca:	popl %esi
0x00403dcb:	popl %ebx
0x00403dcc:	leave
0x00403dcd:	ret

0x004039f9:	popl %ecx
0x004039fa:	movl %ecx, 0x10(%ebx)
0x004039fd:	movl (%ecx), %eax
0x004039ff:	movl %eax, 0x10(%ebx)
0x00403a02:	cmpl (%eax), $0xffffffff<UINT8>
0x00403a05:	jne 0x00403a0e
0x00403a0e:	movl 0x406adc, %ebx
0x00403a14:	movl %eax, 0x10(%ebx)
0x00403a17:	movl %edx, (%eax)
0x00403a19:	cmpl %edx, $0xffffffff<UINT8>
0x00403a1c:	movl -4(%ebp), %edx
0x00403a1f:	je 20
0x00403a21:	movl %ecx, 0xc4(%eax,%edx,4)
0x00403a28:	movl %edi, 0x44(%eax,%edx,4)
0x00403a2c:	andl %ecx, -8(%ebp)
0x00403a2f:	andl %edi, %esi
0x00403a31:	orl %ecx, %edi
0x00403a33:	jne 0x00403a6c
0x00403a6c:	movl %ecx, %edx
0x00403a6e:	xorl %edi, %edi
0x00403a70:	imull %ecx, %ecx, $0x204<UINT32>
0x00403a76:	leal %ecx, 0x144(%ecx,%eax)
0x00403a7d:	movl -12(%ebp), %ecx
0x00403a80:	movl %ecx, 0x44(%eax,%edx,4)
0x00403a84:	andl %ecx, %esi
0x00403a86:	jne 13
0x00403a88:	movl %ecx, 0xc4(%eax,%edx,4)
0x00403a8f:	pushl $0x20<UINT8>
0x00403a91:	andl %ecx, -8(%ebp)
0x00403a94:	popl %edi
0x00403a95:	testl %ecx, %ecx
0x00403a97:	jl 0x00403a9e
0x00403a99:	shll %ecx
0x00403a9b:	incl %edi
0x00403a9c:	jmp 0x00403a95
0x00403a9e:	movl %ecx, -12(%ebp)
0x00403aa1:	movl %edx, 0x4(%ecx,%edi,8)
0x00403aa5:	movl %ecx, (%edx)
0x00403aa7:	subl %ecx, -16(%ebp)
0x00403aaa:	movl %esi, %ecx
0x00403aac:	movl -8(%ebp), %ecx
0x00403aaf:	sarl %esi, $0x4<UINT8>
0x00403ab2:	decl %esi
0x00403ab3:	cmpl %esi, $0x3f<UINT8>
0x00403ab6:	jle 3
0x00403ab8:	pushl $0x3f<UINT8>
0x00403aba:	popl %esi
0x00403abb:	cmpl %esi, %edi
0x00403abd:	je 0x00403bd0
0x00403bd0:	testl %ecx, %ecx
0x00403bd2:	je 11
0x00403bd4:	movl (%edx), %ecx
0x00403bd6:	movl -4(%ecx,%edx), %ecx
0x00403bda:	jmp 0x00403bdf
0x00403bdf:	movl %esi, -16(%ebp)
0x00403be2:	addl %edx, %ecx
0x00403be4:	leal %ecx, 0x1(%esi)
0x00403be7:	movl (%edx), %ecx
0x00403be9:	movl -4(%edx,%esi), %ecx
0x00403bed:	movl %esi, -12(%ebp)
0x00403bf0:	movl %ecx, (%esi)
0x00403bf2:	testl %ecx, %ecx
0x00403bf4:	leal %edi, 0x1(%ecx)
0x00403bf7:	movl (%esi), %edi
0x00403bf9:	jne 0x00403c15
0x00403bfb:	cmpl %ebx, 0x406ae0
0x00403c01:	jne 0x00403c15
0x00403c15:	movl %ecx, -4(%ebp)
0x00403c18:	movl (%eax), %ecx
0x00403c1a:	leal %eax, 0x4(%edx)
0x00403c1d:	popl %edi
0x00403c1e:	popl %esi
0x00403c1f:	popl %ebx
0x00403c20:	leave
0x00403c21:	ret

0x00402839:	testl %eax, %eax
0x0040283b:	popl %ecx
0x0040283c:	jne 0x0040285a
0x0040285a:	popl %esi
0x0040285b:	ret

0x0040280a:	testl %eax, %eax
0x0040280c:	popl %ecx
0x0040280d:	jne 0x00402825
0x00402825:	ret

0x004027f7:	popl %ecx
0x004027f8:	popl %ecx
0x004027f9:	ret

0x004022b5:	movl %esi, %eax
0x004022b7:	popl %ecx
0x004022b8:	testl %esi, %esi
0x004022ba:	jne 0x004022c4
0x004022c4:	movl 0x406d20, %esi
0x004022ca:	movl 0x406e20, $0x20<UINT32>
0x004022d4:	leal %eax, 0x100(%esi)
0x004022da:	cmpl %esi, %eax
0x004022dc:	jae 0x004022f8
0x004022de:	andb 0x4(%esi), $0x0<UINT8>
0x004022e2:	orl (%esi), $0xffffffff<UINT8>
0x004022e5:	movb 0x5(%esi), $0xa<UINT8>
0x004022e9:	movl %eax, 0x406d20
0x004022ee:	addl %esi, $0x8<UINT8>
0x004022f1:	addl %eax, $0x100<UINT32>
0x004022f6:	jmp 0x004022da
0x004022f8:	leal %eax, 0x10(%esp)
0x004022fc:	pushl %eax
0x004022fd:	call GetStartupInfoA@kernel32.dll
GetStartupInfoA@kernel32.dll: API Node	
0x00402303:	cmpw 0x42(%esp), $0x0<UINT8>
0x00402309:	je 0x004023d4
0x004023d4:	xorl %ebx, %ebx
0x004023d6:	movl %eax, 0x406d20
0x004023db:	cmpl (%eax,%ebx,8), $0xffffffff<UINT8>
0x004023df:	leal %esi, (%eax,%ebx,8)
0x004023e2:	jne 77
0x004023e4:	testl %ebx, %ebx
0x004023e6:	movb 0x4(%esi), $0xffffff81<UINT8>
0x004023ea:	jne 0x004023f1
0x004023ec:	pushl $0xfffffff6<UINT8>
0x004023ee:	popl %eax
0x004023ef:	jmp 0x004023fb
0x004023fb:	pushl %eax
0x004023fc:	call GetStdHandle@kernel32.dll
GetStdHandle@kernel32.dll: API Node	
0x00402402:	movl %edi, %eax
0x00402404:	cmpl %edi, $0xffffffff<UINT8>
0x00402407:	je 23
0x00402409:	pushl %edi
0x0040240a:	call GetFileType@kernel32.dll
GetFileType@kernel32.dll: API Node	
0x00402410:	testl %eax, %eax
0x00402412:	je 12
0x00402414:	andl %eax, $0xff<UINT32>
0x00402419:	movl (%esi), %edi
0x0040241b:	cmpl %eax, $0x2<UINT8>
0x0040241e:	jne 0x00402426
0x00402426:	cmpl %eax, $0x3<UINT8>
0x00402429:	jne 10
0x0040242b:	orb 0x4(%esi), $0x8<UINT8>
0x0040242f:	jmp 0x00402435
0x00402435:	incl %ebx
0x00402436:	cmpl %ebx, $0x3<UINT8>
0x00402439:	jl 0x004023d6
0x004023f1:	movl %eax, %ebx
0x004023f3:	decl %eax
0x004023f4:	negl %eax
0x004023f6:	sbbl %eax, %eax
0x004023f8:	addl %eax, $0xfffffff5<UINT8>
0x0040243b:	pushl 0x406e20
0x00402441:	call SetHandleCount@kernel32.dll
SetHandleCount@kernel32.dll: API Node	
0x00402447:	popl %edi
0x00402448:	popl %esi
0x00402449:	popl %ebp
0x0040244a:	popl %ebx
0x0040244b:	addl %esp, $0x44<UINT8>
0x0040244e:	ret

0x004011a5:	call GetCommandLineA@kernel32.dll
GetCommandLineA@kernel32.dll: API Node	
0x004011ab:	movl 0x407e44, %eax
0x004011b0:	call 0x00402172
0x00402172:	pushl %ecx
0x00402173:	pushl %ecx
0x00402174:	movl %eax, 0x406a8c
0x00402179:	pushl %ebx
0x0040217a:	pushl %ebp
0x0040217b:	movl %ebp, 0x405038
0x00402181:	pushl %esi
0x00402182:	pushl %edi
0x00402183:	xorl %ebx, %ebx
0x00402185:	xorl %esi, %esi
0x00402187:	xorl %edi, %edi
0x00402189:	cmpl %eax, %ebx
0x0040218b:	jne 51
0x0040218d:	call GetEnvironmentStringsW@kernel32.dll
GetEnvironmentStringsW@kernel32.dll: API Node	
0x0040218f:	movl %esi, %eax
0x00402191:	cmpl %esi, %ebx
0x00402193:	je 12
0x00402195:	movl 0x406a8c, $0x1<UINT32>
0x0040219f:	jmp 0x004021c9
0x004021c9:	cmpl %esi, %ebx
0x004021cb:	jne 0x004021d9
0x004021d9:	cmpw (%esi), %bx
0x004021dc:	movl %eax, %esi
0x004021de:	je 14
0x004021e0:	incl %eax
0x004021e1:	incl %eax
0x004021e2:	cmpw (%eax), %bx
0x004021e5:	jne 0x004021e0
0x004021e7:	incl %eax
0x004021e8:	incl %eax
0x004021e9:	cmpw (%eax), %bx
0x004021ec:	jne 0x004021e0
0x004021ee:	subl %eax, %esi
0x004021f0:	movl %edi, 0x405030
0x004021f6:	sarl %eax
0x004021f8:	pushl %ebx
0x004021f9:	pushl %ebx
0x004021fa:	incl %eax
0x004021fb:	pushl %ebx
0x004021fc:	pushl %ebx
0x004021fd:	pushl %eax
0x004021fe:	pushl %esi
0x004021ff:	pushl %ebx
0x00402200:	pushl %ebx
0x00402201:	movl 0x34(%esp), %eax
0x00402205:	call WideCharToMultiByte@kernel32.dll
WideCharToMultiByte@kernel32.dll: API Node	
0x00402207:	movl %ebp, %eax
0x00402209:	cmpl %ebp, %ebx
0x0040220b:	je 50
0x0040220d:	pushl %ebp
0x0040220e:	call 0x004027e8
0x0040283e:	testl %esi, %esi
0x00402840:	jne 0x00402845
0x00402845:	addl %esi, $0xf<UINT8>
0x00402848:	andl %esi, $0xfffffff0<UINT8>
0x0040284b:	pushl %esi
0x0040284c:	pushl $0x0<UINT8>
0x0040284e:	pushl 0x406d08
0x00402854:	call HeapAlloc@kernel32.dll
0x00402213:	cmpl %eax, %ebx
0x00402215:	popl %ecx
0x00402216:	movl 0x10(%esp), %eax
0x0040221a:	je 35
0x0040221c:	pushl %ebx
0x0040221d:	pushl %ebx
0x0040221e:	pushl %ebp
0x0040221f:	pushl %eax
0x00402220:	pushl 0x24(%esp)
0x00402224:	pushl %esi
0x00402225:	pushl %ebx
0x00402226:	pushl %ebx
0x00402227:	call WideCharToMultiByte@kernel32.dll
0x00402229:	testl %eax, %eax
0x0040222b:	jne 0x0040223b
0x0040223b:	movl %ebx, 0x10(%esp)
0x0040223f:	pushl %esi
0x00402240:	call FreeEnvironmentStringsW@kernel32.dll
FreeEnvironmentStringsW@kernel32.dll: API Node	
0x00402246:	movl %eax, %ebx
0x00402248:	jmp 0x0040229d
0x0040229d:	popl %edi
0x0040229e:	popl %esi
0x0040229f:	popl %ebp
0x004022a0:	popl %ebx
0x004022a1:	popl %ecx
0x004022a2:	popl %ecx
0x004022a3:	ret

0x004011b5:	movl 0x406920, %eax
0x004011ba:	call 0x00401f25
0x00401f25:	pushl %ebp
0x00401f26:	movl %ebp, %esp
0x00401f28:	pushl %ecx
0x00401f29:	pushl %ecx
0x00401f2a:	pushl %ebx
0x00401f2b:	xorl %ebx, %ebx
0x00401f2d:	cmpl 0x406e28, %ebx
0x00401f33:	pushl %esi
0x00401f34:	pushl %edi
0x00401f35:	jne 5
0x00401f37:	call 0x00403234
0x00403234:	cmpl 0x406e28, $0x0<UINT8>
0x0040323b:	jne 0x0040324f
0x0040323d:	pushl $0xfffffffd<UINT8>
0x0040323f:	call 0x00402e70
0x00402e70:	pushl %ebp
0x00402e71:	movl %ebp, %esp
0x00402e73:	subl %esp, $0x18<UINT8>
0x00402e76:	pushl %ebx
0x00402e77:	pushl %esi
0x00402e78:	pushl %edi
0x00402e79:	pushl 0x8(%ebp)
0x00402e7c:	call 0x00403009
0x00403009:	movl %eax, 0x4(%esp)
0x0040300d:	andl 0x406a94, $0x0<UINT8>
0x00403014:	cmpl %eax, $0xfffffffe<UINT8>
0x00403017:	jne 0x00403029
0x00403029:	cmpl %eax, $0xfffffffd<UINT8>
0x0040302c:	jne 16
0x0040302e:	movl 0x406a94, $0x1<UINT32>
0x00403038:	jmp GetACP@kernel32.dll
GetACP@kernel32.dll: API Node	
0x00402e81:	movl %esi, %eax
0x00402e83:	popl %ecx
0x00402e84:	cmpl %esi, 0x406aec
0x00402e8a:	movl 0x8(%ebp), %esi
0x00402e8d:	je 362
0x00402e93:	xorl %ebx, %ebx
0x00402e95:	cmpl %esi, %ebx
0x00402e97:	je 342
0x00402e9d:	xorl %edx, %edx
0x00402e9f:	movl %eax, $0x406698<UINT32>
0x00402ea4:	cmpl (%eax), %esi
0x00402ea6:	je 114
0x00402ea8:	addl %eax, $0x30<UINT8>
0x00402eab:	incl %edx
0x00402eac:	cmpl %eax, $0x406788<UINT32>
0x00402eb1:	jl 0x00402ea4
0x00402eb3:	leal %eax, -24(%ebp)
0x00402eb6:	pushl %eax
0x00402eb7:	pushl %esi
0x00402eb8:	call GetCPInfo@kernel32.dll
GetCPInfo@kernel32.dll: API Node	
0x00402ebe:	cmpl %eax, $0x1<UINT8>
0x00402ec1:	jne 292
0x00402ec7:	pushl $0x40<UINT8>
0x00402ec9:	xorl %eax, %eax
0x00402ecb:	popl %ecx
0x00402ecc:	movl %edi, $0x406c00<UINT32>
0x00402ed1:	cmpl -24(%ebp), $0x1<UINT8>
0x00402ed5:	movl 0x406aec, %esi
0x00402edb:	rep stosl %es:(%edi), %eax
0x00402edd:	stosb %es:(%edi), %al
0x00402ede:	movl 0x406d04, %ebx
0x00402ee4:	jbe 239
0x00402eea:	cmpb -18(%ebp), $0x0<UINT8>
0x00402eee:	je 0x00402faf
0x00402faf:	pushl $0x1<UINT8>
0x00402fb1:	popl %eax
0x00402fb2:	orb 0x406c01(%eax), $0x8<UINT8>
0x00402fb9:	incl %eax
0x00402fba:	cmpl %eax, $0xff<UINT32>
0x00402fbf:	jb 0x00402fb2
0x00402fc1:	pushl %esi
0x00402fc2:	call 0x00403053
0x00403053:	movl %eax, 0x4(%esp)
0x00403057:	subl %eax, $0x3a4<UINT32>
0x0040305c:	je 34
0x0040305e:	subl %eax, $0x4<UINT8>
0x00403061:	je 23
0x00403063:	subl %eax, $0xd<UINT8>
0x00403066:	je 12
0x00403068:	decl %eax
0x00403069:	je 3
0x0040306b:	xorl %eax, %eax
0x0040306d:	ret

0x00402fc7:	popl %ecx
0x00402fc8:	movl 0x406d04, %eax
0x00402fcd:	movl 0x406afc, $0x1<UINT32>
0x00402fd7:	jmp 0x00402fdf
0x00402fdf:	xorl %eax, %eax
0x00402fe1:	movl %edi, $0x406af0<UINT32>
0x00402fe6:	stosl %es:(%edi), %eax
0x00402fe7:	stosl %es:(%edi), %eax
0x00402fe8:	stosl %es:(%edi), %eax
0x00402fe9:	jmp 0x00402ff8
0x00402ff8:	call 0x004030af
0x004030af:	pushl %ebp
0x004030b0:	movl %ebp, %esp
0x004030b2:	subl %esp, $0x514<UINT32>
0x004030b8:	leal %eax, -20(%ebp)
0x004030bb:	pushl %esi
0x004030bc:	pushl %eax
0x004030bd:	pushl 0x406aec
0x004030c3:	call GetCPInfo@kernel32.dll
0x004030c9:	cmpl %eax, $0x1<UINT8>
0x004030cc:	jne 278
0x004030d2:	xorl %eax, %eax
0x004030d4:	movl %esi, $0x100<UINT32>
0x004030d9:	movb -276(%ebp,%eax), %al
0x004030e0:	incl %eax
0x004030e1:	cmpl %eax, %esi
0x004030e3:	jb 0x004030d9
0x004030e5:	movb %al, -14(%ebp)
0x004030e8:	movb -276(%ebp), $0x20<UINT8>
0x004030ef:	testb %al, %al
0x004030f1:	je 0x0040312a
0x0040312a:	pushl $0x0<UINT8>
0x0040312c:	leal %eax, -1300(%ebp)
0x00403132:	pushl 0x406d04
0x00403138:	pushl 0x406aec
0x0040313e:	pushl %eax
0x0040313f:	leal %eax, -276(%ebp)
0x00403145:	pushl %esi
0x00403146:	pushl %eax
0x00403147:	pushl $0x1<UINT8>
0x00403149:	call 0x0040456d
0x0040456d:	pushl %ebp
0x0040456e:	movl %ebp, %esp
0x00404570:	pushl $0xffffffff<UINT8>
0x00404572:	pushl $0x405478<UINT32>
0x00404577:	pushl $0x402584<UINT32>
0x0040457c:	movl %eax, %fs:0
0x00404582:	pushl %eax
0x00404583:	movl %fs:0, %esp
0x0040458a:	subl %esp, $0x18<UINT8>
0x0040458d:	pushl %ebx
0x0040458e:	pushl %esi
0x0040458f:	pushl %edi
0x00404590:	movl -24(%ebp), %esp
0x00404593:	movl %eax, 0x406ad0
0x00404598:	xorl %ebx, %ebx
0x0040459a:	cmpl %eax, %ebx
0x0040459c:	jne 62
0x0040459e:	leal %eax, -28(%ebp)
0x004045a1:	pushl %eax
0x004045a2:	pushl $0x1<UINT8>
0x004045a4:	popl %esi
0x004045a5:	pushl %esi
0x004045a6:	pushl $0x405458<UINT32>
0x004045ab:	pushl %esi
0x004045ac:	call GetStringTypeW@kernel32.dll
GetStringTypeW@kernel32.dll: API Node	
0x004045b2:	testl %eax, %eax
0x004045b4:	je 4
0x004045b6:	movl %eax, %esi
0x004045b8:	jmp 0x004045d7
0x004045d7:	movl 0x406ad0, %eax
0x004045dc:	cmpl %eax, $0x2<UINT8>
0x004045df:	jne 0x00404605
0x00404605:	cmpl %eax, $0x1<UINT8>
0x00404608:	jne 148
0x0040460e:	cmpl 0x18(%ebp), %ebx
0x00404611:	jne 0x0040461b
0x0040461b:	pushl %ebx
0x0040461c:	pushl %ebx
0x0040461d:	pushl 0x10(%ebp)
0x00404620:	pushl 0xc(%ebp)
0x00404623:	movl %eax, 0x20(%ebp)
0x00404626:	negl %eax
0x00404628:	sbbl %eax, %eax
0x0040462a:	andl %eax, $0x8<UINT8>
0x0040462d:	incl %eax
0x0040462e:	pushl %eax
0x0040462f:	pushl 0x18(%ebp)
0x00404632:	call MultiByteToWideChar@kernel32.dll
MultiByteToWideChar@kernel32.dll: API Node	
0x00404638:	movl -32(%ebp), %eax
0x0040463b:	cmpl %eax, %ebx
0x0040463d:	je 99
0x0040463f:	movl -4(%ebp), %ebx
0x00404642:	leal %edi, (%eax,%eax)
0x00404645:	movl %eax, %edi
0x00404647:	addl %eax, $0x3<UINT8>
0x0040464a:	andb %al, $0xfffffffc<UINT8>
0x0040464c:	call 0x00404c00
0x00404c00:	pushl %ecx
0x00404c01:	cmpl %eax, $0x1000<UINT32>
0x00404c06:	leal %ecx, 0x8(%esp)
0x00404c0a:	jb 0x00404c20
0x00404c20:	subl %ecx, %eax
0x00404c22:	movl %eax, %esp
0x00404c24:	testl (%ecx), %eax
0x00404c26:	movl %esp, %ecx
0x00404c28:	movl %ecx, (%eax)
0x00404c2a:	movl %eax, 0x4(%eax)
0x00404c2d:	pushl %eax
0x00404c2e:	ret

0x00404651:	movl -24(%ebp), %esp
0x00404654:	movl %esi, %esp
0x00404656:	movl -36(%ebp), %esi
0x00404659:	pushl %edi
0x0040465a:	pushl %ebx
0x0040465b:	pushl %esi
0x0040465c:	call 0x00404270
0x00404270:	movl %edx, 0xc(%esp)
0x00404274:	movl %ecx, 0x4(%esp)
0x00404278:	testl %edx, %edx
0x0040427a:	je 71
0x0040427c:	xorl %eax, %eax
0x0040427e:	movb %al, 0x8(%esp)
0x00404282:	pushl %edi
0x00404283:	movl %edi, %ecx
0x00404285:	cmpl %edx, $0x4<UINT8>
0x00404288:	jb 45
0x0040428a:	negl %ecx
0x0040428c:	andl %ecx, $0x3<UINT8>
0x0040428f:	je 0x00404299
0x00404299:	movl %ecx, %eax
0x0040429b:	shll %eax, $0x8<UINT8>
0x0040429e:	addl %eax, %ecx
0x004042a0:	movl %ecx, %eax
0x004042a2:	shll %eax, $0x10<UINT8>
0x004042a5:	addl %eax, %ecx
0x004042a7:	movl %ecx, %edx
0x004042a9:	andl %edx, $0x3<UINT8>
0x004042ac:	shrl %ecx, $0x2<UINT8>
0x004042af:	je 6
0x004042b1:	rep stosl %es:(%edi), %eax
0x004042b3:	testl %edx, %edx
0x004042b5:	je 0x004042bd
0x004042bd:	movl %eax, 0x8(%esp)
0x004042c1:	popl %edi
0x004042c2:	ret

0x00404661:	addl %esp, $0xc<UINT8>
0x00404664:	jmp 0x00404671
0x00404671:	orl -4(%ebp), $0xffffffff<UINT8>
0x00404675:	cmpl %esi, %ebx
0x00404677:	je 41
0x00404679:	pushl -32(%ebp)
0x0040467c:	pushl %esi
0x0040467d:	pushl 0x10(%ebp)
0x00404680:	pushl 0xc(%ebp)
0x00404683:	pushl $0x1<UINT8>
0x00404685:	pushl 0x18(%ebp)
0x00404688:	call MultiByteToWideChar@kernel32.dll
0x0040468e:	cmpl %eax, %ebx
0x00404690:	je 16
0x00404692:	pushl 0x14(%ebp)
0x00404695:	pushl %eax
0x00404696:	pushl %esi
0x00404697:	pushl 0x8(%ebp)
0x0040469a:	call GetStringTypeW@kernel32.dll
0x004046a0:	jmp 0x004046a4
0x004046a4:	leal %esp, -52(%ebp)
0x004046a7:	movl %ecx, -16(%ebp)
0x004046aa:	movl %fs:0, %ecx
0x004046b1:	popl %edi
0x004046b2:	popl %esi
0x004046b3:	popl %ebx
0x004046b4:	leave
0x004046b5:	ret

0x0040314e:	pushl $0x0<UINT8>
0x00403150:	leal %eax, -532(%ebp)
0x00403156:	pushl 0x406aec
0x0040315c:	pushl %esi
0x0040315d:	pushl %eax
0x0040315e:	leal %eax, -276(%ebp)
0x00403164:	pushl %esi
0x00403165:	pushl %eax
0x00403166:	pushl %esi
0x00403167:	pushl 0x406d04
0x0040316d:	call 0x0040431e
0x0040431e:	pushl %ebp
0x0040431f:	movl %ebp, %esp
0x00404321:	pushl $0xffffffff<UINT8>
0x00404323:	pushl $0x405460<UINT32>
0x00404328:	pushl $0x402584<UINT32>
0x0040432d:	movl %eax, %fs:0
0x00404333:	pushl %eax
0x00404334:	movl %fs:0, %esp
0x0040433b:	subl %esp, $0x1c<UINT8>
0x0040433e:	pushl %ebx
0x0040433f:	pushl %esi
0x00404340:	pushl %edi
0x00404341:	movl -24(%ebp), %esp
0x00404344:	xorl %edi, %edi
0x00404346:	cmpl 0x406acc, %edi
0x0040434c:	jne 0x00404394
0x0040434e:	pushl %edi
0x0040434f:	pushl %edi
0x00404350:	pushl $0x1<UINT8>
0x00404352:	popl %ebx
0x00404353:	pushl %ebx
0x00404354:	pushl $0x405458<UINT32>
0x00404359:	movl %esi, $0x100<UINT32>
0x0040435e:	pushl %esi
0x0040435f:	pushl %edi
0x00404360:	call LCMapStringW@kernel32.dll
LCMapStringW@kernel32.dll: API Node	
0x00404366:	testl %eax, %eax
0x00404368:	je 8
0x0040436a:	movl 0x406acc, %ebx
0x00404370:	jmp 0x00404394
0x00404394:	cmpl 0x14(%ebp), %edi
0x00404397:	jle 16
0x00404399:	pushl 0x14(%ebp)
0x0040439c:	pushl 0x10(%ebp)
0x0040439f:	call 0x00404542
0x00404542:	movl %edx, 0x8(%esp)
0x00404546:	movl %eax, 0x4(%esp)
0x0040454a:	testl %edx, %edx
0x0040454c:	pushl %esi
0x0040454d:	leal %ecx, -1(%edx)
0x00404550:	je 13
0x00404552:	cmpb (%eax), $0x0<UINT8>
0x00404555:	je 8
0x00404557:	incl %eax
0x00404558:	movl %esi, %ecx
0x0040455a:	decl %ecx
0x0040455b:	testl %esi, %esi
0x0040455d:	jne 0x00404552
0x0040455f:	cmpb (%eax), $0x0<UINT8>
0x00404562:	popl %esi
0x00404563:	jne 0x0040456a
0x0040456a:	movl %eax, %edx
0x0040456c:	ret

0x004043a4:	popl %ecx
0x004043a5:	popl %ecx
0x004043a6:	movl 0x14(%ebp), %eax
0x004043a9:	movl %eax, 0x406acc
0x004043ae:	cmpl %eax, $0x2<UINT8>
0x004043b1:	jne 0x004043d0
0x004043d0:	cmpl %eax, $0x1<UINT8>
0x004043d3:	jne 211
0x004043d9:	cmpl 0x20(%ebp), %edi
0x004043dc:	jne 0x004043e6
0x004043e6:	pushl %edi
0x004043e7:	pushl %edi
0x004043e8:	pushl 0x14(%ebp)
0x004043eb:	pushl 0x10(%ebp)
0x004043ee:	movl %eax, 0x24(%ebp)
0x004043f1:	negl %eax
0x004043f3:	sbbl %eax, %eax
0x004043f5:	andl %eax, $0x8<UINT8>
0x004043f8:	incl %eax
0x004043f9:	pushl %eax
0x004043fa:	pushl 0x20(%ebp)
0x004043fd:	call MultiByteToWideChar@kernel32.dll
0x00404403:	movl %ebx, %eax
0x00404405:	movl -28(%ebp), %ebx
0x00404408:	cmpl %ebx, %edi
0x0040440a:	je 156
0x00404410:	movl -4(%ebp), %edi
0x00404413:	leal %eax, (%ebx,%ebx)
0x00404416:	addl %eax, $0x3<UINT8>
0x00404419:	andb %al, $0xfffffffc<UINT8>
0x0040441b:	call 0x00404c00
0x00404420:	movl -24(%ebp), %esp
0x00404423:	movl %eax, %esp
0x00404425:	movl -36(%ebp), %eax
0x00404428:	orl -4(%ebp), $0xffffffff<UINT8>
0x0040442c:	jmp 0x00404441
0x00404441:	cmpl -36(%ebp), %edi
0x00404444:	je 102
0x00404446:	pushl %ebx
0x00404447:	pushl -36(%ebp)
0x0040444a:	pushl 0x14(%ebp)
0x0040444d:	pushl 0x10(%ebp)
0x00404450:	pushl $0x1<UINT8>
0x00404452:	pushl 0x20(%ebp)
0x00404455:	call MultiByteToWideChar@kernel32.dll
0x0040445b:	testl %eax, %eax
0x0040445d:	je 77
0x0040445f:	pushl %edi
0x00404460:	pushl %edi
0x00404461:	pushl %ebx
0x00404462:	pushl -36(%ebp)
0x00404465:	pushl 0xc(%ebp)
0x00404468:	pushl 0x8(%ebp)
0x0040446b:	call LCMapStringW@kernel32.dll
0x00404471:	movl %esi, %eax
0x00404473:	movl -40(%ebp), %esi
0x00404476:	cmpl %esi, %edi
0x00404478:	je 50
0x0040447a:	testb 0xd(%ebp), $0x4<UINT8>
0x0040447e:	je 0x004044c0
0x004044c0:	movl -4(%ebp), $0x1<UINT32>
0x004044c7:	leal %eax, (%esi,%esi)
0x004044ca:	addl %eax, $0x3<UINT8>
0x004044cd:	andb %al, $0xfffffffc<UINT8>
0x004044cf:	call 0x00404c00
0x004044d4:	movl -24(%ebp), %esp
0x004044d7:	movl %ebx, %esp
0x004044d9:	movl -32(%ebp), %ebx
0x004044dc:	orl -4(%ebp), $0xffffffff<UINT8>
0x004044e0:	jmp 0x004044f4
0x004044f4:	cmpl %ebx, %edi
0x004044f6:	je -76
0x004044f8:	pushl %esi
0x004044f9:	pushl %ebx
0x004044fa:	pushl -28(%ebp)
0x004044fd:	pushl -36(%ebp)
0x00404500:	pushl 0xc(%ebp)
0x00404503:	pushl 0x8(%ebp)
0x00404506:	call LCMapStringW@kernel32.dll
0x0040450c:	testl %eax, %eax
0x0040450e:	je -100
0x00404510:	cmpl 0x1c(%ebp), %edi
0x00404513:	pushl %edi
0x00404514:	pushl %edi
0x00404515:	jne 0x0040451b
0x0040451b:	pushl 0x1c(%ebp)
0x0040451e:	pushl 0x18(%ebp)
0x00404521:	pushl %esi
0x00404522:	pushl %ebx
0x00404523:	pushl $0x220<UINT32>
0x00404528:	pushl 0x20(%ebp)
0x0040452b:	call WideCharToMultiByte@kernel32.dll
0x00404531:	movl %esi, %eax
0x00404533:	cmpl %esi, %edi
0x00404535:	je -143
0x0040453b:	movl %eax, %esi
0x0040453d:	jmp 0x004044ae
0x004044ae:	leal %esp, -56(%ebp)
0x004044b1:	movl %ecx, -16(%ebp)
0x004044b4:	movl %fs:0, %ecx
0x004044bb:	popl %edi
0x004044bc:	popl %esi
0x004044bd:	popl %ebx
0x004044be:	leave
0x004044bf:	ret

0x00403172:	pushl $0x0<UINT8>
0x00403174:	leal %eax, -788(%ebp)
0x0040317a:	pushl 0x406aec
0x00403180:	pushl %esi
0x00403181:	pushl %eax
0x00403182:	leal %eax, -276(%ebp)
0x00403188:	pushl %esi
0x00403189:	pushl %eax
0x0040318a:	pushl $0x200<UINT32>
0x0040318f:	pushl 0x406d04
0x00403195:	call 0x0040431e
0x0040319a:	addl %esp, $0x5c<UINT8>
0x0040319d:	xorl %eax, %eax
0x0040319f:	leal %ecx, -1300(%ebp)
0x004031a5:	movw %dx, (%ecx)
0x004031a8:	testb %dl, $0x1<UINT8>
0x004031ab:	je 0x004031c3
0x004031c3:	testb %dl, $0x2<UINT8>
0x004031c6:	je 0x004031d8
0x004031d8:	andb 0x406b00(%eax), $0x0<UINT8>
0x004031df:	incl %eax
0x004031e0:	incl %ecx
0x004031e1:	incl %ecx
0x004031e2:	cmpl %eax, %esi
0x004031e4:	jb 0x004031a5
0x004031ad:	orb 0x406c01(%eax), $0x10<UINT8>
0x004031b4:	movb %dl, -532(%ebp,%eax)
0x004031bb:	movb 0x406b00(%eax), %dl
0x004031c1:	jmp 0x004031df
0x004031e6:	jmp 0x00403231
0x00403231:	popl %esi
0x00403232:	leave
0x00403233:	ret

0x00402ffd:	xorl %eax, %eax
0x00402fff:	jmp 0x00403004
0x00403004:	popl %edi
0x00403005:	popl %esi
0x00403006:	popl %ebx
0x00403007:	leave
0x00403008:	ret

0x00403244:	popl %ecx
0x00403245:	movl 0x406e28, $0x1<UINT32>
0x0040324f:	ret

0x00401f3c:	movl %esi, $0x406988<UINT32>
0x00401f41:	pushl $0x104<UINT32>
0x00401f46:	pushl %esi
0x00401f47:	pushl %ebx
0x00401f48:	call GetModuleFileNameA@kernel32.dll
GetModuleFileNameA@kernel32.dll: API Node	
0x00401f4e:	movl %eax, 0x407e44
0x00401f53:	movl 0x406970, %esi
0x00401f59:	movl %edi, %esi
0x00401f5b:	cmpb (%eax), %bl
0x00401f5d:	je 2
0x00401f5f:	movl %edi, %eax
0x00401f61:	leal %eax, -8(%ebp)
0x00401f64:	pushl %eax
0x00401f65:	leal %eax, -4(%ebp)
0x00401f68:	pushl %eax
0x00401f69:	pushl %ebx
0x00401f6a:	pushl %ebx
0x00401f6b:	pushl %edi
0x00401f6c:	call 0x00401fbe
0x00401fbe:	pushl %ebp
0x00401fbf:	movl %ebp, %esp
0x00401fc1:	movl %ecx, 0x18(%ebp)
0x00401fc4:	movl %eax, 0x14(%ebp)
0x00401fc7:	pushl %ebx
0x00401fc8:	pushl %esi
0x00401fc9:	andl (%ecx), $0x0<UINT8>
0x00401fcc:	movl %esi, 0x10(%ebp)
0x00401fcf:	pushl %edi
0x00401fd0:	movl %edi, 0xc(%ebp)
0x00401fd3:	movl (%eax), $0x1<UINT32>
0x00401fd9:	movl %eax, 0x8(%ebp)
0x00401fdc:	testl %edi, %edi
0x00401fde:	je 0x00401fe8
0x00401fe8:	cmpb (%eax), $0x22<UINT8>
0x00401feb:	jne 68
0x00401fed:	movb %dl, 0x1(%eax)
0x00401ff0:	incl %eax
0x00401ff1:	cmpb %dl, $0x22<UINT8>
0x00401ff4:	je 0x0040201f
0x00401ff6:	testb %dl, %dl
0x00401ff8:	je 37
0x00401ffa:	movzbl %edx, %dl
0x00401ffd:	testb 0x406c01(%edx), $0x4<UINT8>
0x00402004:	je 0x00402012
0x00402012:	incl (%ecx)
0x00402014:	testl %esi, %esi
0x00402016:	je 0x00401fed
0x0040201f:	incl (%ecx)
0x00402021:	testl %esi, %esi
0x00402023:	je 0x00402029
0x00402029:	cmpb (%eax), $0x22<UINT8>
0x0040202c:	jne 70
0x0040202e:	incl %eax
0x0040202f:	jmp 0x00402074
0x00402074:	andl 0x18(%ebp), $0x0<UINT8>
0x00402078:	cmpb (%eax), $0x0<UINT8>
0x0040207b:	je 0x00402161
0x00402081:	movb %dl, (%eax)
0x00402083:	cmpb %dl, $0x20<UINT8>
0x00402086:	je 5
0x00402088:	cmpb %dl, $0x9<UINT8>
0x0040208b:	jne 0x00402090
0x00402090:	cmpb (%eax), $0x0<UINT8>
0x00402093:	je 200
0x00402099:	testl %edi, %edi
0x0040209b:	je 0x004020a5
0x004020a5:	movl %edx, 0x14(%ebp)
0x004020a8:	incl (%edx)
0x004020aa:	movl 0x8(%ebp), $0x1<UINT32>
0x004020b1:	xorl %ebx, %ebx
0x004020b3:	cmpb (%eax), $0x5c<UINT8>
0x004020b6:	jne 0x004020bc
0x004020bc:	cmpb (%eax), $0x22<UINT8>
0x004020bf:	jne 0x004020ed
0x004020ed:	movl %edx, %ebx
0x004020ef:	decl %ebx
0x004020f0:	testl %edx, %edx
0x004020f2:	je 0x00402102
0x00402102:	movb %dl, (%eax)
0x00402104:	testb %dl, %dl
0x00402106:	je 0x00402152
0x00402108:	cmpl 0x18(%ebp), $0x0<UINT8>
0x0040210c:	jne 10
0x0040210e:	cmpb %dl, $0x20<UINT8>
0x00402111:	je 63
0x00402113:	cmpb %dl, $0x9<UINT8>
0x00402116:	je 58
0x00402118:	cmpl 0x8(%ebp), $0x0<UINT8>
0x0040211c:	je 46
0x0040211e:	testl %esi, %esi
0x00402120:	je 0x0040213b
0x0040213b:	movzbl %edx, %dl
0x0040213e:	testb 0x406c01(%edx), $0x4<UINT8>
0x00402145:	je 0x0040214a
0x0040214a:	incl (%ecx)
0x0040214c:	incl %eax
0x0040214d:	jmp 0x004020aa
0x00402152:	testl %esi, %esi
0x00402154:	je 0x0040215a
0x0040215a:	incl (%ecx)
0x0040215c:	jmp 0x00402078
0x00402161:	testl %edi, %edi
0x00402163:	je 0x00402168
0x00402168:	movl %eax, 0x14(%ebp)
0x0040216b:	popl %edi
0x0040216c:	popl %esi
0x0040216d:	popl %ebx
0x0040216e:	incl (%eax)
0x00402170:	popl %ebp
0x00402171:	ret

0x00401f71:	movl %eax, -8(%ebp)
0x00401f74:	movl %ecx, -4(%ebp)
0x00401f77:	leal %eax, (%eax,%ecx,4)
0x00401f7a:	pushl %eax
0x00401f7b:	call 0x004027e8
0x00403977:	movl %ecx, 0x4(%ebx)
0x0040397a:	movl %edi, (%ebx)
0x0040397c:	andl %ecx, -8(%ebp)
0x0040397f:	andl %edi, %esi
0x00403981:	orl %ecx, %edi
0x00403983:	jne 0x00403990
0x00401f80:	movl %esi, %eax
0x00401f82:	addl %esp, $0x18<UINT8>
0x00401f85:	cmpl %esi, %ebx
0x00401f87:	jne 0x00401f91
0x00401f91:	leal %eax, -8(%ebp)
0x00401f94:	pushl %eax
0x00401f95:	leal %eax, -4(%ebp)
0x00401f98:	pushl %eax
0x00401f99:	movl %eax, -4(%ebp)
0x00401f9c:	leal %eax, (%esi,%eax,4)
0x00401f9f:	pushl %eax
0x00401fa0:	pushl %esi
0x00401fa1:	pushl %edi
0x00401fa2:	call 0x00401fbe
0x00401fe0:	movl (%edi), %esi
0x00401fe2:	addl %edi, $0x4<UINT8>
0x00401fe5:	movl 0xc(%ebp), %edi
0x00402018:	movb %dl, (%eax)
0x0040201a:	movb (%esi), %dl
0x0040201c:	incl %esi
0x0040201d:	jmp 0x00401fed
0x00402025:	andb (%esi), $0x0<UINT8>
0x00402028:	incl %esi
0x0040209d:	movl (%edi), %esi
0x0040209f:	addl %edi, $0x4<UINT8>
0x004020a2:	movl 0xc(%ebp), %edi
0x00402122:	movzbl %ebx, %dl
0x00402125:	testb 0x406c01(%ebx), $0x4<UINT8>
0x0040212c:	je 0x00402134
0x00402134:	movb %dl, (%eax)
0x00402136:	movb (%esi), %dl
0x00402138:	incl %esi
0x00402139:	jmp 0x0040214a
0x00402156:	andb (%esi), $0x0<UINT8>
0x00402159:	incl %esi
0x00402165:	andl (%edi), $0x0<UINT8>
0x00401fa7:	movl %eax, -4(%ebp)
0x00401faa:	addl %esp, $0x14<UINT8>
0x00401fad:	decl %eax
0x00401fae:	movl 0x406958, %esi
0x00401fb4:	popl %edi
0x00401fb5:	popl %esi
0x00401fb6:	movl 0x406954, %eax
0x00401fbb:	popl %ebx
0x00401fbc:	leave
0x00401fbd:	ret

0x004011bf:	call 0x00401e6c
0x00401e6c:	pushl %ebx
0x00401e6d:	xorl %ebx, %ebx
0x00401e6f:	cmpl 0x406e28, %ebx
0x00401e75:	pushl %esi
0x00401e76:	pushl %edi
0x00401e77:	jne 0x00401e7e
0x00401e7e:	movl %esi, 0x406920
0x00401e84:	xorl %edi, %edi
0x00401e86:	movb %al, (%esi)
0x00401e88:	cmpb %al, %bl
0x00401e8a:	je 0x00401e9e
0x00401e8c:	cmpb %al, $0x3d<UINT8>
0x00401e8e:	je 0x00401e91
0x00401e91:	pushl %esi
0x00401e92:	call 0x00402990
0x00402990:	movl %ecx, 0x4(%esp)
0x00402994:	testl %ecx, $0x3<UINT32>
0x0040299a:	je 0x004029b0
0x004029b0:	movl %eax, (%ecx)
0x004029b2:	movl %edx, $0x7efefeff<UINT32>
0x004029b7:	addl %edx, %eax
0x004029b9:	xorl %eax, $0xffffffff<UINT8>
0x004029bc:	xorl %eax, %edx
0x004029be:	addl %ecx, $0x4<UINT8>
0x004029c1:	testl %eax, $0x81010100<UINT32>
0x004029c6:	je -24
0x004029c8:	movl %eax, -4(%ecx)
0x004029cb:	testb %al, %al
0x004029cd:	je 50
0x004029cf:	testb %ah, %ah
0x004029d1:	je 0x004029f7
0x004029f7:	leal %eax, -3(%ecx)
0x004029fa:	movl %ecx, 0x4(%esp)
0x004029fe:	subl %eax, %ecx
0x00402a00:	ret

0x00401e97:	popl %ecx
0x00401e98:	leal %esi, 0x1(%esi,%eax)
0x00401e9c:	jmp 0x00401e86
0x00401e9e:	leal %eax, 0x4(,%edi,4)
0x00401ea5:	pushl %eax
0x00401ea6:	call 0x004027e8
0x00401eab:	movl %esi, %eax
0x00401ead:	popl %ecx
0x00401eae:	cmpl %esi, %ebx
0x00401eb0:	movl 0x406960, %esi
0x00401eb6:	jne 0x00401ec0
0x00401ec0:	movl %edi, 0x406920
0x00401ec6:	cmpb (%edi), %bl
0x00401ec8:	je 57
0x00401eca:	pushl %ebp
0x00401ecb:	pushl %edi
0x00401ecc:	call 0x00402990
0x00401ed1:	movl %ebp, %eax
0x00401ed3:	popl %ecx
0x00401ed4:	incl %ebp
0x00401ed5:	cmpb (%edi), $0x3d<UINT8>
0x00401ed8:	je 0x00401efc
0x00401efc:	addl %edi, %ebp
0x00401efe:	cmpb (%edi), %bl
0x00401f00:	jne -55
0x00401f02:	popl %ebp
0x00401f03:	pushl 0x406920
0x00401f09:	call 0x00402d4f
0x00402d4f:	pushl %esi
0x00402d50:	movl %esi, 0x8(%esp)
0x00402d54:	testl %esi, %esi
0x00402d56:	je 36
0x00402d58:	pushl %esi
0x00402d59:	call 0x004035c3
0x004035c3:	movl %eax, 0x406ae4
0x004035c8:	leal %ecx, (%eax,%eax,4)
0x004035cb:	movl %eax, 0x406ae8
0x004035d0:	leal %ecx, (%eax,%ecx,4)
0x004035d3:	cmpl %eax, %ecx
0x004035d5:	jae 0x004035eb
0x004035d7:	movl %edx, 0x4(%esp)
0x004035db:	subl %edx, 0xc(%eax)
0x004035de:	cmpl %edx, $0x100000<UINT32>
0x004035e4:	jb 7
0x004035e6:	addl %eax, $0x14<UINT8>
0x004035e9:	jmp 0x004035d3
0x004035eb:	xorl %eax, %eax
0x004035ed:	ret

0x00402d5e:	popl %ecx
0x00402d5f:	testl %eax, %eax
0x00402d61:	pushl %esi
0x00402d62:	je 0x00402d6e
0x00402d6e:	pushl $0x0<UINT8>
0x00402d70:	pushl 0x406d08
0x00402d76:	call HeapFree@kernel32.dll
HeapFree@kernel32.dll: API Node	
0x00402d7c:	popl %esi
0x00402d7d:	ret

0x00401f0e:	popl %ecx
0x00401f0f:	movl 0x406920, %ebx
0x00401f15:	movl (%esi), %ebx
0x00401f17:	popl %edi
0x00401f18:	popl %esi
0x00401f19:	movl 0x406e24, $0x1<UINT32>
0x00401f23:	popl %ebx
0x00401f24:	ret

0x004011c4:	call 0x00401be6
0x00401be6:	movl %eax, 0x406e34
0x00401beb:	testl %eax, %eax
0x00401bed:	je 0x00401bf1
0x00401bf1:	pushl $0x406014<UINT32>
0x00401bf6:	pushl $0x406008<UINT32>
0x00401bfb:	call 0x00401cce
0x00401cce:	pushl %esi
0x00401ccf:	movl %esi, 0x8(%esp)
0x00401cd3:	cmpl %esi, 0xc(%esp)
0x00401cd7:	jae 0x00401ce6
0x00401cd9:	movl %eax, (%esi)
0x00401cdb:	testl %eax, %eax
0x00401cdd:	je 0x00401ce1
0x00401ce1:	addl %esi, $0x4<UINT8>
0x00401ce4:	jmp 0x00401cd3
0x00401cdf:	call 0x00401bd2
0x00401b2d:	movl %eax, 0x407e40
0x00401b32:	pushl %esi
0x00401b33:	pushl $0x14<UINT8>
0x00401b35:	testl %eax, %eax
0x00401b37:	popl %esi
0x00401b38:	jne 7
0x00401b3a:	movl %eax, $0x200<UINT32>
0x00401b3f:	jmp 0x00401b47
0x00401b47:	movl 0x407e40, %eax
0x00401b4c:	pushl $0x4<UINT8>
0x00401b4e:	pushl %eax
0x00401b4f:	call 0x00402c7a
0x00402c7a:	pushl %ebx
0x00402c7b:	pushl %esi
0x00402c7c:	movl %esi, 0xc(%esp)
0x00402c80:	pushl %edi
0x00402c81:	imull %esi, 0x14(%esp)
0x00402c86:	cmpl %esi, $0xffffffe0<UINT8>
0x00402c89:	movl %ebx, %esi
0x00402c8b:	ja 13
0x00402c8d:	testl %esi, %esi
0x00402c8f:	jne 0x00402c94
0x00402c94:	addl %esi, $0xf<UINT8>
0x00402c97:	andl %esi, $0xfffffff0<UINT8>
0x00402c9a:	xorl %edi, %edi
0x00402c9c:	cmpl %esi, $0xffffffe0<UINT8>
0x00402c9f:	ja 42
0x00402ca1:	cmpl %ebx, 0x406790
0x00402ca7:	ja 0x00402cb6
0x00402cb6:	pushl %esi
0x00402cb7:	pushl $0x8<UINT8>
0x00402cb9:	pushl 0x406d08
0x00402cbf:	call HeapAlloc@kernel32.dll
0x00402cc5:	movl %edi, %eax
0x00402cc7:	testl %edi, %edi
0x00402cc9:	jne 0x00402ced
0x00402ced:	movl %eax, %edi
0x00402cef:	popl %edi
0x00402cf0:	popl %esi
0x00402cf1:	popl %ebx
0x00402cf2:	ret

0x00401b54:	popl %ecx
0x00401b55:	movl 0x406e38, %eax
0x00401b5a:	testl %eax, %eax
0x00401b5c:	popl %ecx
0x00401b5d:	jne 0x00401b80
0x00401b80:	xorl %ecx, %ecx
0x00401b82:	movl %eax, $0x4060a8<UINT32>
0x00401b87:	movl %edx, 0x406e38
0x00401b8d:	movl (%ecx,%edx), %eax
0x00401b90:	addl %eax, $0x20<UINT8>
0x00401b93:	addl %ecx, $0x4<UINT8>
0x00401b96:	cmpl %eax, $0x406328<UINT32>
0x00401b9b:	jl 0x00401b87
0x00401b9d:	xorl %edx, %edx
0x00401b9f:	movl %ecx, $0x4060b8<UINT32>
0x00401ba4:	movl %eax, %edx
0x00401ba6:	movl %esi, %edx
0x00401ba8:	sarl %eax, $0x5<UINT8>
0x00401bab:	andl %esi, $0x1f<UINT8>
0x00401bae:	movl %eax, 0x406d20(,%eax,4)
0x00401bb5:	movl %eax, (%eax,%esi,8)
0x00401bb8:	cmpl %eax, $0xffffffff<UINT8>
0x00401bbb:	je 4
0x00401bbd:	testl %eax, %eax
0x00401bbf:	jne 0x00401bc4
0x00401bc4:	addl %ecx, $0x20<UINT8>
0x00401bc7:	incl %edx
0x00401bc8:	cmpl %ecx, $0x406118<UINT32>
0x00401bce:	jl 0x00401ba4
0x00401bd0:	popl %esi
0x00401bd1:	ret

0x00401ce6:	popl %esi
0x00401ce7:	ret

0x00401c00:	pushl $0x406004<UINT32>
0x00401c05:	pushl $0x406000<UINT32>
0x00401c0a:	call 0x00401cce
0x00401c0f:	addl %esp, $0x10<UINT8>
0x00401c12:	ret

0x004011c9:	movl %eax, 0x406960
0x004011ce:	movl 0x406964, %eax
0x004011d3:	pushl %eax
0x004011d4:	pushl 0x406958
0x004011da:	pushl 0x406954
0x004011e0:	call 0x00401030
0x00401030:	subl %esp, $0x10<UINT8>
0x00401033:	call GetLastError@kernel32.dll
GetLastError@kernel32.dll: API Node	
0x00401039:	pushl %eax
0x0040103a:	pushl $0x406088<UINT32>
0x0040103f:	call 0x00401100
0x00401100:	pushl %ebx
0x00401101:	pushl %esi
0x00401102:	movl %esi, $0x4060c8<UINT32>
0x00401107:	pushl %edi
0x00401108:	pushl %esi
0x00401109:	call 0x00401259
0x00401259:	pushl %esi
0x0040125a:	movl %esi, 0x8(%esp)
0x0040125e:	pushl 0x10(%esi)
0x00401261:	call 0x0040285c
0x0040285c:	movl %eax, 0x4(%esp)
0x00402860:	cmpl %eax, 0x406e20
0x00402866:	jb 0x0040286b
0x0040286b:	movl %ecx, %eax
0x0040286d:	andl %eax, $0x1f<UINT8>
0x00402870:	sarl %ecx, $0x5<UINT8>
0x00402873:	movl %ecx, 0x406d20(,%ecx,4)
0x0040287a:	movb %al, 0x4(%ecx,%eax,8)
0x0040287e:	andl %eax, $0x40<UINT8>
0x00402881:	ret

0x00401266:	testl %eax, %eax
0x00401268:	popl %ecx
0x00401269:	je 0x004012e2
0x004012e2:	xorl %eax, %eax
0x004012e4:	popl %esi
0x004012e5:	ret

0x0040110e:	movl %edi, %eax
0x00401110:	leal %eax, 0x18(%esp)
0x00401114:	pushl %eax
0x00401115:	pushl 0x18(%esp)
0x00401119:	pushl %esi
0x0040111a:	call 0x00401323
0x00401323:	pushl %ebp
0x00401324:	movl %ebp, %esp
0x00401326:	subl %esp, $0x248<UINT32>
0x0040132c:	pushl %ebx
0x0040132d:	pushl %esi
0x0040132e:	pushl %edi
0x0040132f:	movl %edi, 0xc(%ebp)
0x00401332:	xorl %esi, %esi
0x00401334:	movb %bl, (%edi)
0x00401336:	incl %edi
0x00401337:	testb %bl, %bl
0x00401339:	movl -12(%ebp), %esi
0x0040133c:	movl -20(%ebp), %esi
0x0040133f:	movl 0xc(%ebp), %edi
0x00401342:	je 1780
0x00401348:	movl %ecx, -16(%ebp)
0x0040134b:	xorl %edx, %edx
0x0040134d:	jmp 0x00401357
0x00401357:	cmpl -20(%ebp), %edx
0x0040135a:	jl 1756
0x00401360:	cmpb %bl, $0x20<UINT8>
0x00401363:	jl 0x00401378
0x00401365:	cmpb %bl, $0x78<UINT8>
0x00401368:	jg 14
0x0040136a:	movsbl %eax, %bl
0x0040136d:	movb %al, 0x405094(%eax)
0x00401373:	andl %eax, $0xf<UINT8>
0x00401376:	jmp 0x0040137a
0x0040137a:	movsbl %eax, 0x4050b4(%esi,%eax,8)
0x00401382:	sarl %eax, $0x4<UINT8>
0x00401385:	cmpl %eax, $0x7<UINT8>
0x00401388:	movl -48(%ebp), %eax
0x0040138b:	ja 1690
0x00401391:	jmp 0x004014c2
0x004014c2:	movl %ecx, 0x406478
0x004014c8:	movl -36(%ebp), %edx
0x004014cb:	movzbl %eax, %bl
0x004014ce:	testb 0x1(%ecx,%eax,2), $0xffffff80<UINT8>
0x004014d3:	je 0x004014ee
0x004014ee:	leal %eax, -20(%ebp)
0x004014f1:	pushl %eax
0x004014f2:	pushl 0x8(%ebp)
0x004014f5:	movsbl %eax, %bl
0x004014f8:	pushl %eax
0x004014f9:	call 0x00401a64
0x00401a64:	pushl %ebp
0x00401a65:	movl %ebp, %esp
0x00401a67:	movl %ecx, 0xc(%ebp)
0x00401a6a:	decl 0x4(%ecx)
0x00401a6d:	js 0x00401a7d
0x00401a7d:	pushl %ecx
0x00401a7e:	pushl 0x8(%ebp)
0x00401a81:	call 0x00402b65
0x00402b65:	pushl %ebp
0x00402b66:	movl %ebp, %esp
0x00402b68:	pushl %ebx
0x00402b69:	pushl %esi
0x00402b6a:	movl %esi, 0xc(%ebp)
0x00402b6d:	movl %eax, 0xc(%esi)
0x00402b70:	movl %ebx, 0x10(%esi)
0x00402b73:	testb %al, $0xffffff82<UINT8>
0x00402b75:	je 243
0x00402b7b:	testb %al, $0x40<UINT8>
0x00402b7d:	jne 235
0x00402b83:	testb %al, $0x1<UINT8>
0x00402b85:	je 0x00402b9d
0x00402b9d:	movl %eax, 0xc(%esi)
0x00402ba0:	andl 0x4(%esi), $0x0<UINT8>
0x00402ba4:	andl 0xc(%ebp), $0x0<UINT8>
0x00402ba8:	andb %al, $0xffffffef<UINT8>
0x00402baa:	orb %al, $0x2<UINT8>
0x00402bac:	testw %ax, $0x10c<UINT16>
0x00402bb0:	movl 0xc(%esi), %eax
0x00402bb3:	jne 34
0x00402bb5:	cmpl %esi, $0x4060c8<UINT32>
0x00402bbb:	je 0x00402bc5
0x00402bc5:	pushl %ebx
0x00402bc6:	call 0x0040285c
0x00402bcb:	testl %eax, %eax
0x00402bcd:	popl %ecx
0x00402bce:	jne 7
0x00402bd0:	pushl %esi
0x00402bd1:	call 0x00404220
0x00404220:	incl 0x406934
0x00404226:	pushl $0x1000<UINT32>
0x0040422b:	call 0x004027e8
0x00404230:	popl %ecx
0x00404231:	movl %ecx, 0x4(%esp)
0x00404235:	testl %eax, %eax
0x00404237:	movl 0x8(%ecx), %eax
0x0040423a:	je 13
0x0040423c:	orl 0xc(%ecx), $0x8<UINT8>
0x00404240:	movl 0x18(%ecx), $0x1000<UINT32>
0x00404247:	jmp 0x0040425a
0x0040425a:	movl %eax, 0x8(%ecx)
0x0040425d:	andl 0x4(%ecx), $0x0<UINT8>
0x00404261:	movl (%ecx), %eax
0x00404263:	ret

0x00402bd6:	popl %ecx
0x00402bd7:	testw 0xc(%esi), $0x108<UINT16>
0x00402bdd:	pushl %edi
0x00402bde:	je 100
0x00402be0:	movl %eax, 0x8(%esi)
0x00402be3:	movl %edi, (%esi)
0x00402be5:	subl %edi, %eax
0x00402be7:	leal %ecx, 0x1(%eax)
0x00402bea:	movl (%esi), %ecx
0x00402bec:	movl %ecx, 0x18(%esi)
0x00402bef:	decl %ecx
0x00402bf0:	testl %edi, %edi
0x00402bf2:	movl 0x4(%esi), %ecx
0x00402bf5:	jle 0x00402c07
0x00402c07:	cmpl %ebx, $0xffffffff<UINT8>
0x00402c0a:	je 22
0x00402c0c:	movl %eax, %ebx
0x00402c0e:	movl %ecx, %ebx
0x00402c10:	sarl %eax, $0x5<UINT8>
0x00402c13:	andl %ecx, $0x1f<UINT8>
0x00402c16:	movl %eax, 0x406d20(,%eax,4)
0x00402c1d:	leal %eax, (%eax,%ecx,8)
0x00402c20:	jmp 0x00402c27
0x00402c27:	testb 0x4(%eax), $0x20<UINT8>
0x00402c2b:	je 0x00402c3a
0x00402c3a:	movl %eax, 0x8(%esi)
0x00402c3d:	movb %cl, 0x8(%ebp)
0x00402c40:	movb (%eax), %cl
0x00402c42:	jmp 0x00402c58
0x00402c58:	cmpl 0xc(%ebp), %edi
0x00402c5b:	popl %edi
0x00402c5c:	je 0x00402c64
0x00402c64:	movl %eax, 0x8(%ebp)
0x00402c67:	andl %eax, $0xff<UINT32>
0x00402c6c:	jmp 0x00402c76
0x00402c76:	popl %esi
0x00402c77:	popl %ebx
0x00402c78:	popl %ebp
0x00402c79:	ret

0x00401a86:	popl %ecx
0x00401a87:	popl %ecx
0x00401a88:	cmpl %eax, $0xffffffff<UINT8>
0x00401a8b:	movl %eax, 0x10(%ebp)
0x00401a8e:	jne 0x00401a95
0x00401a95:	incl (%eax)
0x00401a97:	popl %ebp
0x00401a98:	ret

0x004014fe:	addl %esp, $0xc<UINT8>
0x00401501:	jmp 0x00401a2b
0x00401a2b:	movl %edi, 0xc(%ebp)
0x00401a2e:	movb %bl, (%edi)
0x00401a30:	incl %edi
0x00401a31:	testb %bl, %bl
0x00401a33:	movl 0xc(%ebp), %edi
0x00401a36:	jne 0x0040134f
0x0040134f:	movl %ecx, -16(%ebp)
0x00401352:	movl %esi, -48(%ebp)
0x00401355:	xorl %edx, %edx
0x00401a6f:	movl %edx, (%ecx)
0x00401a71:	movb %al, 0x8(%ebp)
0x00401a74:	movb (%edx), %al
0x00401a76:	incl (%ecx)
0x00401a78:	movzbl %eax, %al
0x00401a7b:	jmp 0x00401a88
0x00401398:	orl -16(%ebp), $0xffffffff<UINT8>
0x0040139c:	movl -52(%ebp), %edx
0x0040139f:	movl -40(%ebp), %edx
0x004013a2:	movl -32(%ebp), %edx
0x004013a5:	movl -28(%ebp), %edx
0x004013a8:	movl -4(%ebp), %edx
0x004013ab:	movl -36(%ebp), %edx
0x004013ae:	jmp 0x00401a2b
0x00401506:	movsbl %eax, %bl
0x00401509:	cmpl %eax, $0x67<UINT8>
0x0040150c:	jg 540
0x00401512:	cmpl %eax, $0x65<UINT8>
0x00401515:	jge 150
0x0040151b:	cmpl %eax, $0x58<UINT8>
0x0040151e:	jg 0x0040160f
0x0040160f:	subl %eax, $0x5a<UINT8>
0x00401612:	je 50
0x00401614:	subl %eax, $0x9<UINT8>
0x00401617:	je -59
0x00401619:	decl %eax
0x0040161a:	je 0x00401808
0x00401808:	orl -4(%ebp), $0x40<UINT8>
0x0040180c:	movl -12(%ebp), $0xa<UINT32>
0x00401813:	testb -3(%ebp), $0xffffff80<UINT8>
0x00401817:	je 0x00401825
0x00401825:	testb -4(%ebp), $0x20<UINT8>
0x00401829:	je 0x0040184c
0x0040184c:	testb -4(%ebp), $0x40<UINT8>
0x00401850:	leal %eax, 0x10(%ebp)
0x00401853:	pushl %eax
0x00401854:	je 8
0x00401856:	call 0x00401b02
0x00401b02:	movl %eax, 0x4(%esp)
0x00401b06:	addl (%eax), $0x4<UINT8>
0x00401b09:	movl %eax, (%eax)
0x00401b0b:	movl %eax, -4(%eax)
0x00401b0e:	ret

0x0040185b:	popl %ecx
0x0040185c:	jmp 0x0040183e
0x0040183e:	cltd
0x0040183f:	jmp 0x00401866
0x00401866:	testb -4(%ebp), $0x40<UINT8>
0x0040186a:	je 27
0x0040186c:	testl %edx, %edx
0x0040186e:	jg 23
0x00401870:	jl 4
0x00401872:	testl %eax, %eax
0x00401874:	jae 0x00401887
0x00401887:	movl %esi, %eax
0x00401889:	movl %edi, %edx
0x0040188b:	testb -3(%ebp), $0xffffff80<UINT8>
0x0040188f:	jne 3
0x00401891:	andl %edi, $0x0<UINT8>
0x00401894:	cmpl -16(%ebp), $0x0<UINT8>
0x00401898:	jnl 9
0x0040189a:	movl -16(%ebp), $0x1<UINT32>
0x004018a1:	jmp 0x004018a7
0x004018a7:	movl %eax, %esi
0x004018a9:	orl %eax, %edi
0x004018ab:	jne 0x004018b1
0x004018ad:	andl -28(%ebp), $0x0<UINT8>
0x004018b1:	leal %eax, -73(%ebp)
0x004018b4:	movl -8(%ebp), %eax
0x004018b7:	movl %eax, -16(%ebp)
0x004018ba:	decl -16(%ebp)
0x004018bd:	testl %eax, %eax
0x004018bf:	jg 0x004018c7
0x004018c7:	movl %eax, -12(%ebp)
0x004018ca:	cltd
0x004018cb:	pushl %edx
0x004018cc:	pushl %eax
0x004018cd:	pushl %edi
0x004018ce:	pushl %esi
0x004018cf:	movl -64(%ebp), %eax
0x004018d2:	movl -60(%ebp), %edx
0x004018d5:	call 0x00402af0
0x00402af0:	pushl %ebx
0x00402af1:	movl %eax, 0x14(%esp)
0x00402af5:	orl %eax, %eax
0x00402af7:	jne 24
0x00402af9:	movl %ecx, 0x10(%esp)
0x00402afd:	movl %eax, 0xc(%esp)
0x00402b01:	xorl %edx, %edx
0x00402b03:	divl %eax, %ecx
0x00402b05:	movl %eax, 0x8(%esp)
0x00402b09:	divl %eax, %ecx
0x00402b0b:	movl %eax, %edx
0x00402b0d:	xorl %edx, %edx
0x00402b0f:	jmp 0x00402b61
0x00402b61:	popl %ebx
0x00402b62:	ret $0x10<UINT16>

0x004018da:	pushl -60(%ebp)
0x004018dd:	movl %ebx, %eax
0x004018df:	addl %ebx, $0x30<UINT8>
0x004018e2:	pushl -64(%ebp)
0x004018e5:	pushl %edi
0x004018e6:	pushl %esi
0x004018e7:	call 0x00402a80
0x00402a80:	pushl %ebx
0x00402a81:	pushl %esi
0x00402a82:	movl %eax, 0x18(%esp)
0x00402a86:	orl %eax, %eax
0x00402a88:	jne 24
0x00402a8a:	movl %ecx, 0x14(%esp)
0x00402a8e:	movl %eax, 0x10(%esp)
0x00402a92:	xorl %edx, %edx
0x00402a94:	divl %eax, %ecx
0x00402a96:	movl %ebx, %eax
0x00402a98:	movl %eax, 0xc(%esp)
0x00402a9c:	divl %eax, %ecx
0x00402a9e:	movl %edx, %ebx
0x00402aa0:	jmp 0x00402ae3
0x00402ae3:	popl %esi
0x00402ae4:	popl %ebx
0x00402ae5:	ret $0x10<UINT16>

0x004018ec:	cmpl %ebx, $0x39<UINT8>
0x004018ef:	movl %esi, %eax
0x004018f1:	movl %edi, %edx
0x004018f3:	jle 0x004018f8
0x004018f8:	movl %eax, -8(%ebp)
0x004018fb:	decl -8(%ebp)
0x004018fe:	movb (%eax), %bl
0x00401900:	jmp 0x004018b7
0x004018c1:	movl %eax, %esi
0x004018c3:	orl %eax, %edi
0x004018c5:	je 0x00401902
0x00401902:	leal %eax, -73(%ebp)
0x00401905:	subl %eax, -8(%ebp)
0x00401908:	incl -8(%ebp)
0x0040190b:	testb -3(%ebp), $0x2<UINT8>
0x0040190f:	movl -12(%ebp), %eax
0x00401912:	je 0x0040192d
0x0040192d:	cmpl -40(%ebp), $0x0<UINT8>
0x00401931:	jne 244
0x00401937:	movl %ebx, -4(%ebp)
0x0040193a:	testb %bl, $0x40<UINT8>
0x0040193d:	je 38
0x0040193f:	testb %bh, $0x1<UINT8>
0x00401942:	je 0x0040194a
0x0040194a:	testb %bl, $0x1<UINT8>
0x0040194d:	je 0x00401955
0x00401955:	testb %bl, $0x2<UINT8>
0x00401958:	je 0x00401965
0x00401965:	movl %esi, -32(%ebp)
0x00401968:	subl %esi, -28(%ebp)
0x0040196b:	subl %esi, -12(%ebp)
0x0040196e:	testb %bl, $0xc<UINT8>
0x00401971:	jne 18
0x00401973:	leal %eax, -20(%ebp)
0x00401976:	pushl %eax
0x00401977:	pushl 0x8(%ebp)
0x0040197a:	pushl %esi
0x0040197b:	pushl $0x20<UINT8>
0x0040197d:	call 0x00401a99
0x00401a99:	pushl %esi
0x00401a9a:	pushl %edi
0x00401a9b:	movl %edi, 0x10(%esp)
0x00401a9f:	movl %eax, %edi
0x00401aa1:	decl %edi
0x00401aa2:	testl %eax, %eax
0x00401aa4:	jle 0x00401ac7
0x00401ac7:	popl %edi
0x00401ac8:	popl %esi
0x00401ac9:	ret

0x00401982:	addl %esp, $0x10<UINT8>
0x00401985:	leal %eax, -20(%ebp)
0x00401988:	pushl %eax
0x00401989:	leal %eax, -22(%ebp)
0x0040198c:	pushl 0x8(%ebp)
0x0040198f:	pushl -28(%ebp)
0x00401992:	pushl %eax
0x00401993:	call 0x00401aca
0x00401aca:	pushl %ebx
0x00401acb:	movl %ebx, 0xc(%esp)
0x00401acf:	movl %eax, %ebx
0x00401ad1:	decl %ebx
0x00401ad2:	pushl %esi
0x00401ad3:	pushl %edi
0x00401ad4:	testl %eax, %eax
0x00401ad6:	jle 0x00401afe
0x00401afe:	popl %edi
0x00401aff:	popl %esi
0x00401b00:	popl %ebx
0x00401b01:	ret

0x00401998:	addl %esp, $0x10<UINT8>
0x0040199b:	testb %bl, $0x8<UINT8>
0x0040199e:	je 0x004019b7
0x004019b7:	cmpl -36(%ebp), $0x0<UINT8>
0x004019bb:	je 0x004019fe
0x004019fe:	leal %eax, -20(%ebp)
0x00401a01:	pushl %eax
0x00401a02:	pushl 0x8(%ebp)
0x00401a05:	pushl -12(%ebp)
0x00401a08:	pushl -8(%ebp)
0x00401a0b:	call 0x00401aca
0x00401ad8:	movl %edi, 0x1c(%esp)
0x00401adc:	movl %esi, 0x10(%esp)
0x00401ae0:	movsbl %eax, (%esi)
0x00401ae3:	pushl %edi
0x00401ae4:	incl %esi
0x00401ae5:	pushl 0x1c(%esp)
0x00401ae9:	pushl %eax
0x00401aea:	call 0x00401a64
0x00401aef:	addl %esp, $0xc<UINT8>
0x00401af2:	cmpl (%edi), $0xffffffff<UINT8>
0x00401af5:	je 7
0x00401af7:	movl %eax, %ebx
0x00401af9:	decl %ebx
0x00401afa:	testl %eax, %eax
0x00401afc:	jg 0x00401ae0
0x00401a10:	addl %esp, $0x10<UINT8>
0x00401a13:	testb -4(%ebp), $0x4<UINT8>
0x00401a17:	je 0x00401a2b
0x00401378:	xorl %eax, %eax
0x00401a3c:	movl %eax, -20(%ebp)
0x00401a3f:	popl %edi
0x00401a40:	popl %esi
0x00401a41:	popl %ebx
0x00401a42:	leave
0x00401a43:	ret

0x0040111f:	pushl %esi
0x00401120:	pushl %edi
0x00401121:	movl %ebx, %eax
0x00401123:	call 0x004012e6
0x004012e6:	cmpl 0x4(%esp), $0x0<UINT8>
0x004012eb:	pushl %esi
0x004012ec:	je 0x00401310
0x00401310:	movl %eax, 0xc(%esp)
0x00401314:	testb 0xd(%eax), $0x10<UINT8>
0x00401318:	je 0x00401321
0x00401321:	popl %esi
0x00401322:	ret

0x00401128:	addl %esp, $0x18<UINT8>
0x0040112b:	movl %eax, %ebx
0x0040112d:	popl %edi
0x0040112e:	popl %esi
0x0040112f:	popl %ebx
0x00401130:	ret

0x00401044:	addl %esp, $0x8<UINT8>
0x00401047:	leal %eax, (%esp)
0x0040104b:	pushl %eax
0x0040104c:	call GetLocalTime@kernel32.dll
GetLocalTime@kernel32.dll: API Node	
0x00401052:	cmpw (%esp), $0x7df<UINT16>
0x00401059:	jne 13
0x0040105b:	pushl $0x406078<UINT32>
0x00401060:	call 0x00401100
0x00401065:	addl %esp, $0x4<UINT8>
0x00401068:	pushl %esi
0x00401069:	call GetACP@kernel32.dll
0x0040106f:	movl %esi, $0x64<UINT32>
0x00401074:	pushl %esi
0x00401075:	call 0x00401000
0x00401000:	pushl %esi
0x00401001:	movl %esi, 0x8(%esp)
0x00401005:	testl %esi, %esi
0x00401007:	jle 0x0040101f
0x00401009:	decl %esi
0x0040100a:	movl %eax, %esi
0x0040100c:	cltd
0x0040100d:	subl %eax, %edx
0x0040100f:	sarl %eax
0x00401011:	pushl %eax
0x00401012:	call 0x00401000
0x0040101f:	xorl %eax, %eax
0x00401021:	popl %esi
0x00401022:	ret

0x00401017:	addl %esp, $0x4<UINT8>
0x0040101a:	leal %eax, (%eax,%esi,2)
0x0040101d:	popl %esi
0x0040101e:	ret

0x0040107a:	addl %esp, $0x4<UINT8>
0x0040107d:	cmpl %eax, $0x172<UINT32>
0x00401082:	jne 14
0x00401084:	pushl %eax
0x00401085:	pushl $0x406064<UINT32>
0x0040108a:	call 0x00401100
0x0040108f:	addl %esp, $0x8<UINT8>
0x00401092:	movl %eax, $0xfffffff6<UINT32>
0x00401097:	testl %eax, %eax
0x00401099:	jg 0x0040109e
0x0040109b:	incl %esi
0x0040109c:	jmp 0x0040109f
0x0040109f:	incl %eax
0x004010a0:	cmpl %eax, $0x64<UINT8>
0x004010a3:	jl 0x00401097
0x0040109e:	decl %esi
0x004010a5:	cmpl %esi, $0xc<UINT8>
0x004010a8:	jne 16
0x004010aa:	pushl %esi
0x004010ab:	pushl $0x406050<UINT32>
0x004010b0:	call 0x00401100
0x004010b5:	addl %esp, $0x8<UINT8>
0x004010b8:	jmp 0x004010bf
0x004010bf:	movl %eax, %esi
0x004010c1:	cltd
0x004010c2:	subl %eax, %edx
0x004010c4:	sarl %eax
0x004010c6:	movl %esi, %eax
0x004010c8:	addl %esi, $0x2<UINT8>
0x004010cb:	cmpl %esi, $0x4<UINT8>
0x004010ce:	jg 0x004010bf
0x004010d0:	popl %esi
0x004010d1:	jne 15
0x004010d3:	pushl $0x4<UINT8>
0x004010d5:	pushl $0x40603c<UINT32>
0x004010da:	call 0x00401100
0x004010df:	addl %esp, $0x8<UINT8>
0x004010e2:	pushl $0x406030<UINT32>
0x004010e7:	call 0x00401100
0x004010ec:	xorl %eax, %eax
0x004010ee:	addl %esp, $0x14<UINT8>
0x004010f1:	ret

0x004011e5:	addl %esp, $0xc<UINT8>
0x004011e8:	movl -28(%ebp), %eax
0x004011eb:	pushl %eax
0x004011ec:	call 0x00401c13
0x00401c13:	pushl $0x0<UINT8>
0x00401c15:	pushl $0x0<UINT8>
0x00401c17:	pushl 0xc(%esp)
0x00401c1b:	call 0x00401c35
0x00401c35:	pushl %edi
0x00401c36:	pushl $0x1<UINT8>
0x00401c38:	popl %edi
0x00401c39:	cmpl 0x406980, %edi
0x00401c3f:	jne 0x00401c52
0x00401c52:	cmpl 0xc(%esp), $0x0<UINT8>
0x00401c57:	pushl %ebx
0x00401c58:	movl %ebx, 0x14(%esp)
0x00401c5c:	movl 0x40697c, %edi
0x00401c62:	movb 0x406978, %bl
0x00401c68:	jne 60
0x00401c6a:	movl %eax, 0x406e30
0x00401c6f:	testl %eax, %eax
0x00401c71:	je 0x00401c95
0x00401c95:	pushl $0x406020<UINT32>
0x00401c9a:	pushl $0x406018<UINT32>
0x00401c9f:	call 0x00401cce
0x00401bd2:	call 0x00402919
0x00402919:	pushl $0x1<UINT8>
0x0040291b:	call 0x00402922
0x00402922:	pushl %ebx
0x00402923:	pushl %esi
0x00402924:	pushl %edi
0x00402925:	xorl %esi, %esi
0x00402927:	xorl %ebx, %ebx
0x00402929:	xorl %edi, %edi
0x0040292b:	cmpl 0x407e40, %esi
0x00402931:	jle 77
0x00402933:	movl %eax, 0x406e38
0x00402938:	movl %eax, (%eax,%esi,4)
0x0040293b:	testl %eax, %eax
0x0040293d:	je 0x00402977
0x0040293f:	movl %ecx, 0xc(%eax)
0x00402942:	testb %cl, $0xffffff83<UINT8>
0x00402945:	je 0x00402977
0x00402947:	cmpl 0x10(%esp), $0x1<UINT8>
0x0040294c:	jne 15
0x0040294e:	pushl %eax
0x0040294f:	call 0x00402882
0x00402882:	pushl %esi
0x00402883:	movl %esi, 0x8(%esp)
0x00402887:	testl %esi, %esi
0x00402889:	jne 0x00402894
0x00402894:	pushl %esi
0x00402895:	call 0x004028bd
0x004028bd:	pushl %ebx
0x004028be:	pushl %esi
0x004028bf:	movl %esi, 0xc(%esp)
0x004028c3:	xorl %ebx, %ebx
0x004028c5:	pushl %edi
0x004028c6:	movl %eax, 0xc(%esi)
0x004028c9:	movl %ecx, %eax
0x004028cb:	andl %ecx, $0x3<UINT8>
0x004028ce:	cmpb %cl, $0x2<UINT8>
0x004028d1:	jne 0x0040290a
0x0040290a:	movl %eax, 0x8(%esi)
0x0040290d:	andl 0x4(%esi), $0x0<UINT8>
0x00402911:	movl (%esi), %eax
0x00402913:	popl %edi
0x00402914:	movl %eax, %ebx
0x00402916:	popl %esi
0x00402917:	popl %ebx
0x00402918:	ret

0x0040289a:	testl %eax, %eax
0x0040289c:	popl %ecx
0x0040289d:	je 0x004028a4
0x004028a4:	testb 0xd(%esi), $0x40<UINT8>
0x004028a8:	je 0x004028b9
0x004028b9:	xorl %eax, %eax
0x004028bb:	popl %esi
0x004028bc:	ret

0x00402954:	cmpl %eax, $0xffffffff<UINT8>
0x00402957:	popl %ecx
0x00402958:	je 0x00402977
0x0040295a:	incl %ebx
0x0040295b:	jmp 0x00402977
0x00402977:	incl %esi
0x00402978:	cmpl %esi, 0x407e40
0x0040297e:	jl 0x00402933
0x004028d3:	testw %ax, $0x108<UINT16>
0x004028d7:	je 0x0040290a
0x004028d9:	movl %eax, 0x8(%esi)
0x004028dc:	movl %edi, (%esi)
0x004028de:	subl %edi, %eax
0x004028e0:	testl %edi, %edi
0x004028e2:	jle 38
0x004028e4:	pushl %edi
0x004028e5:	pushl %eax
0x004028e6:	pushl 0x10(%esi)
0x004028e9:	call 0x00403fd0
0x00403fd0:	pushl %ebp
0x00403fd1:	movl %ebp, %esp
0x00403fd3:	subl %esp, $0x414<UINT32>
0x00403fd9:	movl %ecx, 0x8(%ebp)
0x00403fdc:	pushl %ebx
0x00403fdd:	cmpl %ecx, 0x406e20
0x00403fe3:	pushl %esi
0x00403fe4:	pushl %edi
0x00403fe5:	jae 377
0x00403feb:	movl %eax, %ecx
0x00403fed:	movl %esi, %ecx
0x00403fef:	sarl %eax, $0x5<UINT8>
0x00403ff2:	andl %esi, $0x1f<UINT8>
0x00403ff5:	leal %ebx, 0x406d20(,%eax,4)
0x00403ffc:	shll %esi, $0x3<UINT8>
0x00403fff:	movl %eax, (%ebx)
0x00404001:	movb %al, 0x4(%eax,%esi)
0x00404005:	testb %al, $0x1<UINT8>
0x00404007:	je 343
0x0040400d:	xorl %edi, %edi
0x0040400f:	cmpl 0x10(%ebp), %edi
0x00404012:	movl -8(%ebp), %edi
0x00404015:	movl -16(%ebp), %edi
0x00404018:	jne 0x00404021
0x00404021:	testb %al, $0x20<UINT8>
0x00404023:	je 0x00404031
0x00404031:	movl %eax, (%ebx)
0x00404033:	addl %eax, %esi
0x00404035:	testb 0x4(%eax), $0xffffff80<UINT8>
0x00404039:	je 193
0x0040403f:	movl %eax, 0xc(%ebp)
0x00404042:	cmpl 0x10(%ebp), %edi
0x00404045:	movl -4(%ebp), %eax
0x00404048:	movl 0x8(%ebp), %edi
0x0040404b:	jbe 231
0x00404051:	leal %eax, -1044(%ebp)
0x00404057:	movl %ecx, -4(%ebp)
0x0040405a:	subl %ecx, 0xc(%ebp)
0x0040405d:	cmpl %ecx, 0x10(%ebp)
0x00404060:	jae 0x0040408b
0x00404062:	movl %ecx, -4(%ebp)
0x00404065:	incl -4(%ebp)
0x00404068:	movb %cl, (%ecx)
0x0040406a:	cmpb %cl, $0xa<UINT8>
0x0040406d:	jne 0x00404076
0x00404076:	movb (%eax), %cl
0x00404078:	incl %eax
0x00404079:	movl %ecx, %eax
0x0040407b:	leal %edx, -1044(%ebp)
0x00404081:	subl %ecx, %edx
0x00404083:	cmpl %ecx, $0x400<UINT32>
0x00404089:	jl 0x00404057
0x0040406f:	incl -16(%ebp)
0x00404072:	movb (%eax), $0xd<UINT8>
0x00404075:	incl %eax
0x0040408b:	movl %edi, %eax
0x0040408d:	leal %eax, -1044(%ebp)
0x00404093:	subl %edi, %eax
0x00404095:	leal %eax, -12(%ebp)
0x00404098:	pushl $0x0<UINT8>
0x0040409a:	pushl %eax
0x0040409b:	leal %eax, -1044(%ebp)
0x004040a1:	pushl %edi
0x004040a2:	pushl %eax
0x004040a3:	movl %eax, (%ebx)
0x004040a5:	pushl (%eax,%esi)
0x004040a8:	call WriteFile@kernel32.dll
WriteFile@kernel32.dll: API Node	
0x004040ae:	testl %eax, %eax
0x004040b0:	je 67
0x004040b2:	movl %eax, -12(%ebp)
0x004040b5:	addl -8(%ebp), %eax
0x004040b8:	cmpl %eax, %edi
0x004040ba:	jl 0x004040c7
0x004040c7:	xorl %edi, %edi
0x004040c9:	movl %eax, -8(%ebp)
0x004040cc:	cmpl %eax, %edi
0x004040ce:	jne 139
0x004040d4:	cmpl 0x8(%ebp), %edi
0x004040d7:	je 0x00404138
0x00404138:	movl %eax, (%ebx)
0x0040413a:	testb 0x4(%eax,%esi), $0x40<UINT8>
0x0040413f:	je 0x0040414d
0x0040414d:	movl 0x406938, $0x1c<UINT32>
0x00404157:	movl 0x40693c, %edi
0x0040415d:	jmp 0x00404175
0x00404175:	orl %eax, $0xffffffff<UINT8>
0x00404178:	popl %edi
0x00404179:	popl %esi
0x0040417a:	popl %ebx
0x0040417b:	leave
0x0040417c:	ret

0x004028ee:	addl %esp, $0xc<UINT8>
0x004028f1:	cmpl %eax, %edi
0x004028f3:	jne 0x00402903
0x00402903:	orl 0xc(%esi), $0x20<UINT8>
0x00402907:	orl %ebx, $0xffffffff<UINT8>
0x0040289f:	orl %eax, $0xffffffff<UINT8>
0x004028a2:	popl %esi
0x004028a3:	ret

0x00402980:	cmpl 0x10(%esp), $0x1<UINT8>
0x00402985:	movl %eax, %ebx
0x00402987:	je 0x0040298b
0x0040298b:	popl %edi
0x0040298c:	popl %esi
0x0040298d:	popl %ebx
0x0040298e:	ret

0x00402920:	popl %ecx
0x00402921:	ret

0x00401bd7:	cmpb 0x406978, $0x0<UINT8>
0x00401bde:	je 0x00401be5
0x00401be5:	ret

0x00401ca4:	popl %ecx
0x00401ca5:	popl %ecx
0x00401ca6:	pushl $0x406028<UINT32>
0x00401cab:	pushl $0x406024<UINT32>
0x00401cb0:	call 0x00401cce
0x00401cb5:	popl %ecx
0x00401cb6:	popl %ecx
0x00401cb7:	testl %ebx, %ebx
0x00401cb9:	popl %ebx
0x00401cba:	jne 16
0x00401cbc:	pushl 0x8(%esp)
0x00401cc0:	movl 0x406980, %edi
0x00401cc6:	call ExitProcess@kernel32.dll
ExitProcess@kernel32.dll: Exit Node	
