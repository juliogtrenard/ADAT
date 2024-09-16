#Crea un programa en Python que pida al usuario 10 números y los guarde en una lista.
#Imprime la lista.

print("Introduce 10 números: ")

lista = []
for i in range(10):
    lista.insert(i, int(input()))

print(lista)