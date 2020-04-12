# resultado = A[2] + valor

.text                   # Add what follows to the text segment of the program
.globl  main            # Declare the label main to be a global one

main:
        la      $t0, A          # register $t0 contains the address of array A
       
        la      $t1,indice      # get address of indice
        lw      $t1,0($t1)      # register $t1 contains the value of indice ($t1 <- 2)
        
        la	$t7, valor
        lw	$t7, 0($t7)	# register $t7 contains the value of valor ($t1 <- 0x100)
        
        li	$t3, 4
        mul	$t2, $t1, $t3	# $t2 <- i*4
        
        addu	$t4, $t2, $t0	# $t4 <- base_address + offset
   
        lw      $t5,0($t4)      # get array element ($t5 <- 0x3)
        addu    $t6,$t5,$t7     # add constant ($t6 <- A[2]+valor)
        
        la	$t8, resultado
        sw      $t6,0($t8)      # update array
        
        
end:    li $v0, 10
	syscall       


.data                  
A:      .word   0x12 0xff 0x3 0x14 0x878 0x31 0x62 0x10 0x5 0x16 0x20  # Loads the list of ints into successive locations in memory beginning with location A (i.e. we've initialized an array)
indice: .word   2               # indice 
valor:  .word   0x100           # constant to add to each element of the array
resultado: .word 0
