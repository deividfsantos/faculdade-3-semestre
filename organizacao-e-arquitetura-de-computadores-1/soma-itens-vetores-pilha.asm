.text                   # Add what follows to the text segment of the program
.globl  main            # Declare the label main to be a global one

main:	la	$t8, A		#Array A
	la	$t9, B		#Array B		
	addiu	$sp, $sp, -8
	sw 	$t8, 0($sp)
	sw 	$t9, 4($sp)
	
	la	$t7, C		#Array C
	li	$t0, 0		#Indice
	li	$t1, 5		#Size 
	
loop:	beq	$t0, $t1, fim
	la	$t5, 0($t0)	#Indice
	addiu	$sp, $sp, -4
	sw 	$t5, 0($sp)
	jal	soma_vet
	
	li	$t3, 4
	mul	$t2, $t3, $t0
	addu	$t4, $t2, $t7	
	
	lw 	$t4, 0($sp)
	addiu	$sp, $sp, 4
	
	addiu	$t0, $t0, 1
	j	loop	
	                
fim:	addiu	$sp, $sp, 8
	li	$v0, 10
	syscall

soma_vet:	lw 	$t4, 0($sp)
		li	$t3, 4
		mul	$t2, $t3, $t4
		
		lw 	$t4, 4($sp)
		addu	$t4, $t4, $t2
		lw	$t5, 0($t4)
				
		lw 	$t4, 8($sp)
		addu	$t4, $t4, $t2
		lw	$t6, 0($t4)
		
		addiu	$sp, $sp, 4
		
		addiu	$sp, $sp, -4
		addu	$t4, $t5, $t6 	
		sw 	$t4, 0($sp)
		
		jr 	$ra

.data                  
A:            .word   1 2 3 4 5
B:            .word   6 7 8 9 0
C:            .word   0 0 0 0 0
espaco:       .asciiz " \n"

