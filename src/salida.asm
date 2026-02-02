.data

.text
.globl main
main:
	move $fp, $sp
	la $t0, str_0
	sw $t0, 0($sp)
	addiu $sp, $sp, -4
	addiu $sp, $sp, 4
	lw $t0, 0($sp)
	move $a0, $t0
	li $v0, 4
	syscall
	li $a0, 10
	li $v0, 11
	syscall
	sw $zero, -4($fp)
	li $v0, 5
	syscall
	sw $v0, -4($fp)
	la $t0, str_1
	sw $t0, 0($sp)
	addiu $sp, $sp, -4
	addiu $sp, $sp, 4
	lw $t0, 0($sp)
	move $a0, $t0
	li $v0, 4
	syscall
	li $a0, 10
	li $v0, 11
	syscall
	lw $t0, -4($fp)
	sw $t0, 0($sp)
	addiu $sp, $sp, -4
	addiu $sp, $sp, 4
	lw $t0, 0($sp)
	move $a0, $t0
	li $v0, 1
	syscall
	li $a0, 10
	li $v0, 11
	syscall
	lw $t0, -4($fp)
	sw $t0, 0($sp)
	addiu $sp, $sp, -4
	li $t0, 70
	sw $t0, 0($sp)
	addiu $sp, $sp, -4
	addiu $sp, $sp, 4
	lw $t1, 0($sp)
	addiu $sp, $sp, 4
	lw $t0, 0($sp)
	sge $t0, $t0, $t1
	sw $t0, 0($sp)
	addiu $sp, $sp, -4
	addiu $sp, $sp, 4
	lw $t0, 0($sp)
	beqz $t0, L1
	la $t0, str_2
	sw $t0, 0($sp)
	addiu $sp, $sp, -4
	addiu $sp, $sp, 4
	lw $t0, 0($sp)
	move $a0, $t0
	li $v0, 4
	syscall
	li $a0, 10
	li $v0, 11
	syscall
	b L0
L1:
	lw $t0, -4($fp)
	sw $t0, 0($sp)
	addiu $sp, $sp, -4
	li $t0, 70
	sw $t0, 0($sp)
	addiu $sp, $sp, -4
	addiu $sp, $sp, 4
	lw $t1, 0($sp)
	addiu $sp, $sp, 4
	lw $t0, 0($sp)
	slt $t0, $t0, $t1
	sw $t0, 0($sp)
	addiu $sp, $sp, -4
	addiu $sp, $sp, 4
	lw $t0, 0($sp)
	beqz $t0, L2
	la $t0, str_3
	sw $t0, 0($sp)
	addiu $sp, $sp, -4
	addiu $sp, $sp, 4
	lw $t0, 0($sp)
	move $a0, $t0
	li $v0, 4
	syscall
	li $a0, 10
	li $v0, 11
	syscall
	b L0
L2:
	la $t0, str_4
	sw $t0, 0($sp)
	addiu $sp, $sp, -4
	addiu $sp, $sp, 4
	lw $t0, 0($sp)
	move $a0, $t0
	li $v0, 4
	syscall
	li $a0, 10
	li $v0, 11
	syscall
L0:
	la $t0, str_5
	sw $t0, 0($sp)
	addiu $sp, $sp, -4
	addiu $sp, $sp, 4
	lw $t0, 0($sp)
	move $a0, $t0
	li $v0, 4
	syscall
	li $a0, 10
	li $v0, 11
	syscall
	li $v0, 10
	syscall
	.data
	str_1: .asciiz "Puntaje: "
	str_0: .asciiz "Prueba DECIDE"
	str_5: .asciiz "Fin"
	str_4: .asciiz "Caso imposible (default)"
	str_3: .asciiz "Resultado: Reprobado"
	str_2: .asciiz "Resultado: Excelente Aprobado"
	li $v0, 10
	syscall
