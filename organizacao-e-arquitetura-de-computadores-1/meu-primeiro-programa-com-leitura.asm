 .text   
 .globl  main            
         
 main:  jal imprimeLerA
        jal	lerValor 
 	move	$t1, $v0 
 	
 	jal imprimeLerB
 	jal	lerValor 
 	move	$t2, $v0 
 	
 	addu	$t3, $t1, $t2
 	jal imprimirFinal
 	
 	li	$v0, 10
 	syscall
 	
 imprimeLerA: la    $a0,LC1        
	      li    $v0,4    
  	      syscall
  	      jr    $ra	
  	  
 imprimeLerB: la    $a0,LC2        
	      li    $v0,4    
  	      syscall 
  	      jr    $ra	
 	
 lerValor: li    $v0, 5  
       	   syscall  
       	   jr    $ra 
 
 imprimirFinal:	la    $a0,LC3        
   	        li    $v0,4       
  	        syscall  
  	
  	        la  $a0, 0($t3)   
	        li    $v0, 1           
  	        syscall   
   	        jr    $ra	
   	        
.data 
LC1: .asciiz "Digite o valor de A: "
LC2: .asciiz "Digite o valor de B: "
LC3: .asciiz "O valor em C da soma é: "
        
 
 
