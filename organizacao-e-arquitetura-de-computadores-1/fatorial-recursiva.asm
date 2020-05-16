# int fact (int n)
# {
#   if (n<1) return (1);
#   else return (n*fact(n-1);
# }

# MARS example: Fatoral# Author:  
# Function: implementa uma funcao que calcula o fatorial de um numero passado como parametro 
#

        
        .text                   # Add what follows to the text segment of the program
        .globl  main            # Declare the label main to be a global one
main:
	addiu	$a0, $zero, 3
	addiu	$sp, $sp, -4
	sw	$ra, 0($sp)
	jal	fact
	addu	$s0, $zero, $v0
	lw	$ra, 0($sp)
	addiu	$sp, $sp, 4
	jr	$ra

fact:	addiu	$sp, $sp, -8
	sw	$ra, 4($sp)
	sw	$a0, 0($sp)
	
	sltiu	$t0, $a0, 1	# teste se n<1. Se n>= 1 salta para L1. 		
	beq	$t0, $zero, L1	# Lembrando: se $a0 <1, entao $t0 = 1
	
	addiu	$v0, $zero, 1	# caso n<1, retorna 1
	addiu	$sp, $sp, 8
	jr	$ra
	
L1:	addiu	$a0, $a0, -1	# n-1
	jal 	fact
	
	lw	$a0, 0($sp)
	lw	$ra, 4($sp)
	addiu	$sp, $sp, 8	#ao retornar da chamada recursiva, restaura $a0 (n)
	mul	$v0, $a0, $v0	#retorna n*fact(n-1)
	jr	$ra
