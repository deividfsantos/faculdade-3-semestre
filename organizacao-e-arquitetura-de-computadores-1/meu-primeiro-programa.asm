 .text   
         .globl  main            
         
 main:  la	$t0,A		# Carrega endereco de A em $t0 - PSEUDO-INSTRUCAO
 	jr 	$t1
 	lw	$t1,0($t0)	# Le valor de A para $t1
 	la	$t2,B		# Carrega endereco de B em $t2 - PSEUDO-INSTRUCAO
 	lw	$t3,0($t2)	# Le valor de B para $t3
 	la	$t4,D		# Carrega endereco de D em $t4 - PSEUDO-INSTRUCAO
 	lw	$t5,0($t4)	# Le valor de D para $t5
 	la	$t6,F		# Carrega endereco de F em $t6 - PSEUDO-INSTRUCAO
 	lw	$t7,0($t6)	# Le valor de F para $t6
	
 	addu	$t0,$t1,$t3	# $t0 recebe A+B
 	addu	$t1,$t0,$t5	# $t1 recebe A+B+D
 	addu	$t2,$t1,-5	# $t2 recebe A+B+D-5
 	addu	$t3,$t2,$t7	# $t3 recebe A+B+D-5+F
 	xor 	$t8, $t2, $t3
 	la	$t5,E		# Carrega endereco de E em $t5 - PSEUDO-INSTRUCAO
 	sw	$t3,0($t5)	# E recebe A+B+D-5+F
 	
 	#jr	$ra		# volta para o kernel do simulador
 	
 	li	$v0, 10
 	syscall
 	
 test:  la	$t5,E
 	li	$v0, 10
 	syscall
 
 	.data
 A:	.word	1
 B:	.word	0x02
 D:  	.word	3
 F:	.word	4
 E:	.word	0
 
