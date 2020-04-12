.text                   # Add what follows to the text segment of the program
.globl  main            # Declare the label main to be a global one

main:	la	$a0, A		#Array A
	la	$a1, B		#Array B
	la	$t7, C		#Array C
	li	$t0, 0
	la	$t1, size 
	lw      $t1, 0($t1)
	
loop:	beq	$t0, $t1, fim
	la	$a2, 0($t0)	#Indice
	jal	soma_vet
	
	li	$t3, 4
	mul	$t2, $t3, $t0
	addu	$t4, $t2, $t7	
	sw      $v0, 0($t4)  
	
	addiu	$t0, $t0, 1
	j	loop	
	                
fim:	li	$v0, 10
	syscall

soma_vet:	li	$t3, 4
		mul	$t2, $t3, $a2
		
		addu	$t4, $a0, $t2
		lw	$t5, 0($t4)
		
		addu	$t4, $a1, $t2
		lw	$t6, 0($t4)
		
		addu	$v0, $t5, $t6 	
		jr 	$ra

.data                  
A:            .word   1 2 3 4 5
B:            .word   6 7 8 9 0
C:            .word   0 0 0 0 0
size:         .word   5
espaco:       .asciiz " \n"

