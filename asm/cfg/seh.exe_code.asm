0x00401000:	pushl $0x401007<UINT32>
0x00401005:	jmp 0x0040101a
0x0040101a:	pushl %fs:0
0x00401021:	movl %fs:0, %esp
0x00401028:	movl %ebx, $0x0<UINT32>
0x0040102d:	movl %eax, $0x12345678<UINT32>
0x00401032:	xchgl (%ebx), %eax
0x00401007:	movl %esp, 0x8(%esp)
0x0040100b:	pushl $0x403000<UINT32>
0x00401010:	call 0x0040103c
0x0040103c:	pushl %ebp
0x0040103d:	movl %ebp, %esp
0x0040103f:	addl %esp, $0xfffffff4<UINT8>
0x00401042:	pushl $0xfffffff5<UINT8>
0x00401044:	call 0x004010f8
0x004010f8:	jmp GetStdHandle@kernel32.dll
GetStdHandle@kernel32.dll: API Node	
0x00401049:	movl -4(%ebp), %eax
0x0040104c:	pushl 0x8(%ebp)
0x0040104f:	call 0x00401080
0x00401080:	movl %eax, 0x4(%esp)
0x00401084:	leal %edx, 0x3(%eax)
0x00401087:	pushl %ebp
0x00401088:	pushl %edi
0x00401089:	movl %ebp, $0x80808080<UINT32>
0x0040108e:	movl %edi, (%eax)
0x00401090:	addl %eax, $0x4<UINT8>
0x00401093:	leal %ecx, -16843009(%edi)
0x00401099:	notl %edi
0x0040109b:	andl %ecx, %edi
0x0040109d:	andl %ecx, %ebp
0x0040109f:	jne 57
0x004010a1:	movl %edi, (%eax)
0x004010a3:	addl %eax, $0x4<UINT8>
0x004010a6:	leal %ecx, -16843009(%edi)
0x004010ac:	notl %edi
0x004010ae:	andl %ecx, %edi
0x004010b0:	andl %ecx, %ebp
0x004010b2:	jne 38
0x004010b4:	movl %edi, (%eax)
0x004010b6:	addl %eax, $0x4<UINT8>
0x004010b9:	leal %ecx, -16843009(%edi)
0x004010bf:	notl %edi
0x004010c1:	andl %ecx, %edi
0x004010c3:	andl %ecx, %ebp
0x004010c5:	jne 19
0x004010c7:	movl %edi, (%eax)
0x004010c9:	addl %eax, $0x4<UINT8>
0x004010cc:	leal %ecx, -16843009(%edi)
0x004010d2:	notl %edi
0x004010d4:	andl %ecx, %edi
0x004010d6:	andl %ecx, %ebp
0x004010d8:	je 0x0040108e
0x004010da:	testl %ecx, $0x8080<UINT32>
0x004010e0:	jne 6
0x004010e2:	shrl %ecx, $0x10<UINT8>
0x004010e5:	addl %eax, $0x2<UINT8>
0x004010e8:	shlb %cl
0x004010ea:	sbbl %eax, %edx
0x004010ec:	popl %edi
0x004010ed:	popl %ebp
0x004010ee:	ret $0x4<UINT16>

0x00401054:	movl -12(%ebp), %eax
0x00401057:	pushl $0x0<UINT8>
0x00401059:	leal %eax, -8(%ebp)
0x0040105c:	pushl %eax
0x0040105d:	pushl -12(%ebp)
0x00401060:	pushl 0x8(%ebp)
0x00401063:	pushl -4(%ebp)
0x00401066:	call 0x004010fe
0x004010fe:	jmp WriteFile@kernel32.dll
WriteFile@kernel32.dll: API Node	
0x0040106b:	movl %eax, -8(%ebp)
0x0040106e:	leave
0x0040106f:	ret $0x4<UINT16>

0x00401015:	call 0x004010f2
0x004010f2:	jmp ExitProcess@kernel32.dll
ExitProcess@kernel32.dll: Exit Node	
