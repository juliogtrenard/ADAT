#Supongamos que a->5 y b->3, escribe un programa que cambie los
#valores de las variables, es decir, a->3 y b->5

a = 5
b = 3
aux = 0

print("Antes: ", a ," - ", b)

aux = a
a = b
b = aux

print("Despues", a, " - ", b)