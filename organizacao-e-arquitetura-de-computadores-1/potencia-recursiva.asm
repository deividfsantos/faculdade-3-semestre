
     
.text
.globl  main
        
main:	la	$a0, value
	lw	$a0, 0($a0)
	la	$a1, pow
	lw	$a1, 0($a1)
	jal	pot

	li	$v0, 10
	syscall

pot:	addiu	$sp, $sp, -8
	sw	$ra, 4($sp)
	sw	$a1, 0($sp)
		
	bne	$a1, $zero, p1	
	
	addiu	$v0, $zero, 1	
	addiu	$sp, $sp, 8
	jr	$ra
	
p1:	addiu	$a1, $a1, -1	
	jal 	pot
	
	lw	$a1, 0($sp)
	lw	$ra, 4($sp)
	addiu	$sp, $sp, 8
	mul	$v0, $a0, $v0
	jr	$ra

.data                  
value:	.word   3
pow:	.word	5
