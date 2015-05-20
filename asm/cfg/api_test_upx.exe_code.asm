0x004052e0:	pusha
0x004052e1:	movl %esi, $0x405000<UINT32>
0x004052e6:	leal %edi, -16384(%esi)
0x004052ec:	pushl %edi
0x004052ed:	jmp 0x004052fa
0x004052fa:	movl %ebx, (%esi)
0x004052fc:	subl %esi, $0xfffffffc<UINT8>
0x004052ff:	adcl %ebx, %ebx
0x00405301:	jb 0x004052f0
0x004052f0:	movb %al, (%esi)
0x004052f2:	incl %esi
0x004052f3:	movb (%edi), %al
0x004052f5:	incl %edi
0x004052f6:	addl %ebx, %ebx
0x004052f8:	jne 0x00405301
0x00405303:	movl %eax, $0x1<UINT32>
0x00405308:	addl %ebx, %ebx
0x0040530a:	jne 0x00405313
0x00405313:	adcl %eax, %eax
0x00405315:	addl %ebx, %ebx
0x00405317:	jae 0x00405308
0x00405319:	jne 0x00405324
0x00405324:	xorl %ecx, %ecx
0x00405326:	subl %eax, $0x3<UINT8>
0x00405329:	jb 0x00405338
0x0040532b:	shll %eax, $0x8<UINT8>
0x0040532e:	movb %al, (%esi)
0x00405330:	incl %esi
0x00405331:	xorl %eax, $0xffffffff<UINT8>
0x00405334:	je 0x004053aa
0x00405336:	movl %ebp, %eax
0x00405338:	addl %ebx, %ebx
0x0040533a:	jne 0x00405343
0x00405343:	adcl %ecx, %ecx
0x00405345:	addl %ebx, %ebx
0x00405347:	jne 0x00405350
0x00405350:	adcl %ecx, %ecx
0x00405352:	jne 0x00405374
0x00405374:	cmpl %ebp, $0xfffff300<UINT32>
0x0040537a:	adcl %ecx, $0x1<UINT8>
0x0040537d:	leal %edx, (%edi,%ebp)
0x00405380:	cmpl %ebp, $0xfffffffc<UINT8>
0x00405383:	jbe 0x00405394
0x00405394:	movl %eax, (%edx)
0x00405396:	addl %edx, $0x4<UINT8>
0x00405399:	movl (%edi), %eax
0x0040539b:	addl %edi, $0x4<UINT8>
0x0040539e:	subl %ecx, $0x4<UINT8>
0x004053a1:	ja 0x00405394
0x004053a3:	addl %edi, %ecx
0x004053a5:	jmp 0x004052f6
0x0040533c:	movl %ebx, (%esi)
0x0040533e:	subl %esi, $0xfffffffc<UINT8>
0x00405341:	adcl %ebx, %ebx
0x00405354:	incl %ecx
0x00405355:	addl %ebx, %ebx
0x00405357:	jne 0x00405360
0x00405360:	adcl %ecx, %ecx
0x00405362:	addl %ebx, %ebx
0x00405364:	jae 0x00405355
0x00405366:	jne 0x00405371
0x00405371:	addl %ecx, $0x2<UINT8>
0x00405385:	movb %al, (%edx)
0x00405387:	incl %edx
0x00405388:	movb (%edi), %al
0x0040538a:	incl %edi
0x0040538b:	decl %ecx
0x0040538c:	jne 0x00405385
0x0040538e:	jmp 0x004052f6
0x00405359:	movl %ebx, (%esi)
0x0040535b:	subl %esi, $0xfffffffc<UINT8>
0x0040535e:	adcl %ebx, %ebx
0x0040530c:	movl %ebx, (%esi)
0x0040530e:	subl %esi, $0xfffffffc<UINT8>
0x00405311:	adcl %ebx, %ebx
0x00405349:	movl %ebx, (%esi)
0x0040534b:	subl %esi, $0xfffffffc<UINT8>
0x0040534e:	adcl %ebx, %ebx
0x0040531b:	movl %ebx, (%esi)
0x0040531d:	subl %esi, $0xfffffffc<UINT8>
0x00405320:	adcl %ebx, %ebx
0x00405322:	jae 0x00405308
0x00405368:	movl %ebx, (%esi)
0x0040536a:	subl %esi, $0xfffffffc<UINT8>
0x0040536d:	adcl %ebx, %ebx
0x0040536f:	jae 0x00405355
0x004053aa:	popl %esi
0x004053ab:	movl %edi, %esi
0x004053ad:	movl %ecx, $0x1c<UINT32>
0x004053b2:	movb %al, (%edi)
0x004053b4:	incl %edi
0x004053b5:	subb %al, $0xffffffe8<UINT8>
0x004053b7:	cmpb %al, $0x1<UINT8>
0x004053b9:	ja 0x004053b2
0x004053bb:	cmpb (%edi), $0x0<UINT8>
0x004053be:	jne -14
0x004053c0:	movl %eax, (%edi)
0x004053c2:	movb %bl, 0x4(%edi)
0x004053c5:	shrw %ax, $0x8<UINT8>
0x004053c9:	roll %eax, $0x10<UINT8>
0x004053cc:	xchgb %ah, %al
0x004053ce:	subl %eax, %edi
0x004053d0:	subb %bl, $0xffffffe8<UINT8>
0x004053d3:	addl %eax, %esi
0x004053d5:	movl (%edi), %eax
0x004053d7:	addl %edi, $0x5<UINT8>
0x004053da:	movb %al, %bl
0x004053dc:	loop 0x004053b7
0x004053de:	leal %edi, 0x3000(%esi)
0x004053e4:	movl %eax, (%edi)
0x004053e6:	orl %eax, %eax
0x004053e8:	je 60
0x004053ea:	movl %ebx, 0x4(%edi)
0x004053ed:	leal %eax, 0x5000(%eax,%esi)
0x004053f4:	addl %ebx, %esi
0x004053f6:	pushl %eax
0x004053f7:	addl %edi, $0x8<UINT8>
0x004053fa:	call LoadLibraryA@kernel32.dll
LoadLibraryA@kernel32.dll: API Node	
0x00405400:	xchgl %ebp, %eax
0x00405401:	movb %al, (%edi)
0x00405403:	incl %edi
0x00405404:	orb %al, %al
0x00405406:	je -36
0x00405408:	movl %ecx, %edi
0x0040540a:	pushl %edi
0x0040540b:	decl %eax
0x0040540c:	repn scasb %al, %es:(%edi)
0x0040540e:	pushl %ebp
0x0040540f:	call GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x00405415:	orl %eax, %eax
0x00405417:	je 0x00405420
0x00405420:	call ExitProcess@kernel32.dll
ExitProcess@kernel32.dll: Exit Node	
