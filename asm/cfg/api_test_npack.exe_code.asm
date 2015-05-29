0x00404290:	cmpl 0x404e4c, $0x0<UINT8>
0x00404297:	jne 5
0x00404299:	jmp 0x0040429f
0x0040429f:	call 0x004042ea
0x004042ea:	pushl %esi
0x004042eb:	pushl %edi
0x004042ec:	pushl $0x404180<UINT32>
0x004042f1:	call LoadLibraryA@kernel32.dll
LoadLibraryA@kernel32.dll: API Node	
0x004042f7:	movl %esi, 0x404c08
0x004042fd:	movl %edi, %eax
0x004042ff:	pushl $0x404190<UINT32>
0x00404304:	pushl %edi
0x00404305:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x00404307:	pushl $0x4041a0<UINT32>
0x0040430c:	pushl %edi
0x0040430d:	movl 0x404e48, %eax
0x00404312:	call GetProcAddress@kernel32.dll
0x00404314:	popl %edi
0x00404315:	movl 0x404e44, %eax
0x0040431a:	popl %esi
0x0040431b:	ret

0x004042a4:	call 0x0040431c
0x0040431c:	pushl %esi
0x0040431d:	pushl %edi
0x0040431e:	pushl $0x4<UINT8>
0x00404320:	pushl $0x3000<UINT32>
0x00404325:	pushl $0x24<UINT8>
0x00404327:	pushl $0x0<UINT8>
0x00404329:	call 0x00000000
Unknown Node: Unknown Node	
