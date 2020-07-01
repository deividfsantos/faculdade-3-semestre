la $s0, A
la $t7, B
la $t8, C
addiu $t1,$t2,1
addiu $t2,$t3,2
addu $t3,$t2,$t1
xor $t4,$t3,$t1
sw $t3,0($s0)
lw $a0,4($s0)
lw $a1,0($t8)
lw $s1,0($s0)
sll $a3, $s1, 4
and $t5,$t1,1
slt $t6,$t1,$t1

.data
A: .word 123
B: .word 321
C: .word 6
