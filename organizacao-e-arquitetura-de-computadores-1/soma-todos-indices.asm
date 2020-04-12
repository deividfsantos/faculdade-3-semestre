.text                   # Add what follows to the text segment of the program
.globl  main            # Declare the label main to be a global one

main:
        la      $t0, A          # register $t0 contains the address of array A
       
        la      $t1, size      # get address of indice
        lw      $t1, 0($t1)      # register $t1 contains the value of indice ($t1 <- 2)
        
        la	$t7, valorASomar
        lw	$t7, 0($t7)	# register $t7 contains the value of valor ($t1 <- 0x100)
        
        li 	$t2, 0	# $t2 será o registrador utiizdo para acumular a soma dos valores
	li 	$t4, 0	# $t1 conterá o valor inicial da variável i
	li 	$t9, 0	# $t9 conterá o valor inicial da variável i
        
loop:	beq 	$t4, $t1, printArray	# compara se i==size, se igual, deve sair do loop e imprimir o valor de cont ($t2)

	li	$t3, 4
        mul	$t2, $t4, $t3	# $t2 <- i*4
        
        addu	$t5, $t2, $t0	# $t4 <- base_address + offset
                
  	lw      $t8,0($t5)      # get array element ($t5 <- 0x3)
        addu    $t6,$t8,$t7     # add constant ($t6 <- A[2]+valor)
 
        sw      $t6,0($t5)      # update array
        
	addiu 	$t4, $t4, 1	# i++
	j loop
	
printArray: beq   $t9, $t1, fim	# compara se i==size, se igual, deve sair do loop e imprimir o valor de cont ($t2)
	      	
	    li	$t3, 4
            mul	$t2, $t9, $t3	# $t2 <- i*4  	
            
            addu  $t5, $t2, $t0	# $t4 <- base_address + offset
            
            lw    $t8,0($t5)      # get array element ($t5 <- 0x3)
            
  	    move  $a0, $t8     # Imprime o valor da soma calculada
	    li    $v0, 1           
            syscall   
            
            la    $a0,espaco        
   	    li    $v0,4       
  	    syscall  
            
	    addiu 	$t9, $t9, 1	# i++
	    j printArray	
	
fim:	li $v0, 10
	syscall

.data                  
A:            .word   1 2 3 4 5 6 7 8 9 10 11  # Loads the list of ints into successive locations in memory beginning with location A (i.e. we've initialized an array)
valorASomar:  .word   7          # constant to add to each element of the array
size:         .word   11
espaco:       .asciiz " \n"