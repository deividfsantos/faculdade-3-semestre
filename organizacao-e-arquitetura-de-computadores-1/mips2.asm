.text
.globl main

main:     la $t0, A
    lw $t1, ($t0)
    la $t0, B
    lw $t2, ($t0)
    beq $t1, $t2, fim
    bne $t1, $t2, erro

fim:
    li $v0, 10
    syscall
erro: li $v0, 10
    syscall


.data
A: .word 5
B: .word 1