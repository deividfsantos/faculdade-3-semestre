# Deivid Santos && João Pioner

.text
.globl  main
        
main:	la	$a0, A
	la	$a1, prim
	lw	$a1, 0($a1)
	la	$a2, ult
	lw	$a2, 0($a2)
	la	$a3, valor
	lw	$a3, 0($a3)
	
	addiu	$sp, $sp, -4
	sw	$a0, 0($sp)	# Empilha array A
	
	addiu	$sp, $sp, -4
	sw	$a1, 0($sp)	# Empilha prim
	
	addiu	$sp, $sp, -4
	sw	$a2, 0($sp)	# Empilha ult
	
	addiu	$sp, $sp, -4
	sw	$a3, 0($sp)	# Empilha valor
	
	jal	BinSearch

	move	$a1, $v0	# $a0 = $v0
	
	la	$a0, LC1	# Imprime o texto de LC1
	li	$v0, 4
	syscall
	
	move	$a0, $a1	# $a0 = $v0	
	li	$v0, 1		# Imprime o resultado em $a0
	syscall
	
	li	$v0, 10
	syscall

BinSearch:	lw	$a0, 12($sp)	#a0 = A
		lw	$a1, 8($sp)	#a1 = prim
		lw	$a2, 4($sp)	#a2 = ult
		lw	$a3, 0($sp)	#a3 = valor
		addiu	$sp, $sp, 16
		
		addiu	$sp, $sp, -4	
		sw	$ra, 0($sp)	
		
		add 	$t0, $a1, $a2	# PRIM + ULT
		sra	$t0, $t0, 1	# $t0 = Meio = (PRIM + ULT) / 2 
		
		li	$t3, 4				
		mul	$t1, $t0, $t3	# Multiplica a posicao desejada por 4 para pegar o valor do indice
		addu	$t1, $a0, $t1	# coloca endereco de memoria do array na posicao meio
		lw	$t1, 0($t1)	# $t1 = A[Meio] 
		
		bgt 	$a1, $a2, naoEncontrado
		beq	$a3, $t1, achouValor	# salta para "achouValor" caso Valor == A[Meio]
		
		slt	$t2, $a3, $t1	# $t2 = ($a3) valor < a[meio] 0($t1)
		beq	$t2, 1, busca1	# busca1 se valor < a[meio]
		bne	$t2, 1, busca2	# busca2 se valor > a[meio]
				
		lw	$ra, 0($sp)
		addiu	$sp, $sp, 4
		jr	$ra
		
naoEncontrado:	addi	$v0, $zero, -1	
	
		lw	$ra, 0($sp) 	# Desempilhando $ra
		addiu	$sp, $sp, 4
		jr	$ra
		
achouValor:	add	$v0, $zero, $t0 # $v0 = resultado

		lw	$ra, 0($sp)	# Desempilhando $ra
		addiu	$sp, $sp, 4
		jr	$ra
		
busca1:	addiu	$a2, $t0, -1	# ult = Meio - 1
	addiu	$sp, $sp, -16	# abrindo espaço da pilha para os parametros
	sw	$a0, 12($sp)	# a0 = A
	sw	$a1, 8($sp)	# a1 = prim
	sw	$a2, 4($sp)	# a2 = ult
	sw	$a3, 0($sp)	# a3 = valor
	jal	BinSearch
	
	lw	$ra, 0($sp)	# Desempilhando $ra
	addiu	$sp, $sp, 4
	jr	$ra

busca2:	addiu	$a1, $t0, 1	# prim = Meio + 1
	addiu	$sp, $sp, -16   # abrindo espaço da pilha para os parametros
	sw	$a0, 12($sp)	# a0 = A
	sw	$a1, 8($sp)	# a1 = prim
	sw	$a2, 4($sp)	# a2 = ult
	sw	$a3, 0($sp)	# a3 = valor
	jal	BinSearch
	
	lw	$ra, 0($sp)	# Desempilhando $ra
	addiu	$sp, $sp, 4
	jr	$ra

.data                  
A:      .word   -5, -1, 5, 9, 12, 15, 21, 29, 31, 58, 250, 325
prim:	.word	0
ult: 	.word	11
valor: 	.word	-5
LC1: .asciiz "Posição do valor no array: "
