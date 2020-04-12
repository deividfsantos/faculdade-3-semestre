# for (i=0; i<size; i++)
#  cont = cont + i;
# printf ("Valor de cont: %d" cont);

.text
.globl main

main:	la 	$t0, size
	lw 	$t0, 0($t0)	# $t0 conterá o conteúdo da variável size
	
	li 	$t2, 0	# $t2 será o registrador utiizdo para acumular a soma dos valores
	li 	$t1, 0	# $t1 conterá o valor inicial da variável i
	
loop:	beq 	$t1, $t0, fim	# compara se i==size, se igual, deve sair do loop e imprimir o valor de cont ($t2)
	addu 	$t2, $t2, $t1	# cont=cont+i
	addiu 	$t1, $t1, 1	# i++
	j loop
	
fim:	la    $a0,LC1        
	li    $v0,4        # Imprime "Valor de cont: "
  	syscall  
  	
  	move  $a0, $t2     # Imprime o valor da soma calculada
	li    $v0, 1           
  	syscall   


	li $v0, 10
	syscall
	
.data 
LC1: .asciiz "Valor de cont: "
size: .word 4