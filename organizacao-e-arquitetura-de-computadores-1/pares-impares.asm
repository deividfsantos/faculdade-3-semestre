.text                   # Add what follows to the text segment of the program
.globl  main            # Declare the label main to be a global one

main:	la      $t6, A  
	li      $t0, 11          
        li      $t1, 0		#Pares
        li	$t2, 0 	 	#Impares
        li 	$t7, 0
        
loop:	beq 	$t4, $t0, fim

	li	$t3, 4		
        mul	$t7, $t4, $t3	
        
        addu	$t5, $t7, $t6
                
  	lw      $t8,0($t5)      
 
 	andi	$t9, $t8, 1
 	
 	beq 	$t9, $zero, par
 	addiu 	$t1, $t1, 1	
	addiu 	$t4, $t4, 1	
        j loop
        
par:    addiu 	$t2, $t2, 1	
	addiu 	$t4, $t4, 1	
	j loop	
	
fim:	li $v0, 10
	syscall

.data                  
A:            .word   1 2 3 4 5 6 7 8 9 10 11  # Loads the list of ints into successive locations in memory beginning with location A (i.e. we've initialized an array)
size:         .word   11
pares:       .asciiz "\nPares: "
impares:       .asciiz "\nImpares: "
