     .text
.globl main
main: 
    la $t0, A
    lw $t1, ($t0)
fim:    la $t0, B
    lw $t2, ($t0)
    and $t1, $t2, $t1
    andi $t1, 100
    slt $t1,$t2, $t1

        lw $t1, ($t0)
        lw $t2, ($t0)
        beq $t1, $t2, fim
        bne $t1, $t2, erro

        lw $t1, ($t0)
        lw $t2, ($t0)
        jr $t1



    li $v0, 10
    syscall
erro:
    li $v0, 10
    syscall


.data
A: .word 5
B: .word 1