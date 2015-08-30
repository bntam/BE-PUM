0x00404001:	pusha
0x00404002:	call 0x0040400a
0x0040400a:	popl %ebp
0x0040400b:	incl %ebp
0x0040400c:	pushl %ebp
0x0040400d:	ret

0x00404008:	jmp 0x0040400e
0x0040400e:	call 0x00404014
0x00404014:	popl %ebp
0x00404015:	movl %ebx, $0xffffffed<UINT32>
0x0040401a:	addl %ebx, %ebp
0x0040401c:	subl %ebx, $0x4000<UINT32>
0x00404022:	cmpl 0x488(%ebp), $0x0<UINT8>
0x00404029:	movl 0x488(%ebp), %ebx
0x0040402f:	jne 971
0x00404035:	leal %eax, 0x494(%ebp)
0x0040403b:	pushl %eax
0x0040403c:	call GetModuleHandleA@kernel32.dll
GetModuleHandleA@kernel32.dll: API Node	
0x00404042:	movl 0x48c(%ebp), %eax
0x00404048:	movl %esi, %eax
0x0040404a:	leal %edi, 0x51(%ebp)
0x0040404d:	pushl %edi
0x0040404e:	pushl %esi
0x0040404f:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x00404055:	stosl %es:(%edi), %eax
0x00404056:	movb %al, $0x0<UINT8>
0x00404058:	scasb %al, %es:(%edi)
0x00404059:	jne 0x00404058
0x0040405b:	cmpb (%edi), %al
0x0040405d:	jne 0x0040404d
0x0040405f:	leal %eax, 0x7a(%ebp)
0x00404062:	jmp 0x0040408d
0x0040408d:	movl %ebx, 0x595(%ebp)
0x00404093:	orl %ebx, %ebx
0x00404095:	je 0x004040a1
0x004040a1:	leal %esi, 0x5c5(%ebp)
0x004040a7:	cmpl (%esi), $0x0<UINT8>
0x004040aa:	je 266
0x004040b0:	pushl $0x4<UINT8>
0x004040b2:	pushl $0x1000<UINT32>
0x004040b7:	pushl $0x1800<UINT32>
0x004040bc:	pushl $0x0<UINT8>
0x004040be:	call 0x51(%ebp)
Unknown Node: Unknown Node	
