lui $1,4097
ori $5,$1,0
lw $6,0($5) 
andi $12,$6,150
ori $8,$9,100
lui $10,34
slt $7,$6,$5
sw $12,0($5)
addu $13,$8,$5
addiu $14,$13,1
addiu $15,$14,2
addu $16,$14,$15
xor $17,$5,$12
and $13,$t1,$t1
slt $18,$9,$10
.data
A: .word 500